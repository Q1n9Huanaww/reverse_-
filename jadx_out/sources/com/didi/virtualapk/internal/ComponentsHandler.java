package com.didi.virtualapk.internal;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.IBinder;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.didi.virtualapk.PluginManager;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public class ComponentsHandler {
    public static final String TAG = "VA.PluginManager";
    private Context mContext;
    private PluginManager mPluginManager;
    private StubActivityInfo mStubActivityInfo = new StubActivityInfo();
    private ArrayMap<ComponentName, Service> mServices = new ArrayMap<>();
    private ArrayMap<IBinder, Intent> mBoundServices = new ArrayMap<>();
    private ArrayMap<Service, AtomicInteger> mServiceCounters = new ArrayMap<>();

    public ComponentsHandler(PluginManager pluginManager) {
        this.mPluginManager = pluginManager;
        this.mContext = pluginManager.getHostContext();
    }

    public Intent transformIntentToExplicitAsNeeded(Intent intent) {
        ResolveInfo resolveInfoResolveActivity;
        ComponentName component = intent.getComponent();
        if ((component == null || component.getPackageName().equals(this.mContext.getPackageName())) && (resolveInfoResolveActivity = this.mPluginManager.resolveActivity(intent)) != null && resolveInfoResolveActivity.activityInfo != null) {
            intent.setComponent(new ComponentName(resolveInfoResolveActivity.activityInfo.packageName, resolveInfoResolveActivity.activityInfo.name));
        }
        return intent;
    }

    public void markIntentIfNeeded(Intent intent) {
        if (intent.getComponent() == null) {
            return;
        }
        String packageName = intent.getComponent().getPackageName();
        String className = intent.getComponent().getClassName();
        if (packageName.equals(this.mContext.getPackageName()) || this.mPluginManager.getLoadedPlugin(packageName) == null) {
            return;
        }
        Set<String> categories = intent.getCategories();
        if (categories != null) {
            intent.putStringArrayListExtra(Constants.KEY_CATEGORY, new ArrayList<>(categories));
            categories.clear();
        }
        intent.addCategory(Constants.CATEGORY_PREFIX_TARGET_PACKAGE + packageName);
        intent.addCategory(Constants.CATEGORY_PREFIX_TARGET_ACTIVITY + className);
        dispatchStubActivity(intent);
    }

    private void dispatchStubActivity(Intent intent) {
        ComponentName component = intent.getComponent();
        String className = intent.getComponent().getClassName();
        LoadedPlugin loadedPlugin = this.mPluginManager.getLoadedPlugin(intent);
        ActivityInfo activityInfo = loadedPlugin.getActivityInfo(component);
        if (activityInfo == null) {
            throw new RuntimeException("can not find " + component);
        }
        int i = activityInfo.launchMode;
        Resources.Theme themeNewTheme = loadedPlugin.getResources().newTheme();
        themeNewTheme.applyStyle(activityInfo.theme, true);
        String stubActivity = this.mStubActivityInfo.getStubActivity(className, i, themeNewTheme);
        Log.i("VA.PluginManager", String.format("dispatchStubActivity,[%s -> %s]", className, stubActivity));
        intent.setClassName(this.mContext, stubActivity);
    }

    public AtomicInteger getServiceCounter(Service service) {
        return this.mServiceCounters.get(service);
    }

    public Service getService(ComponentName componentName) {
        return this.mServices.get(componentName);
    }

    public void rememberService(ComponentName componentName, Service service) {
        synchronized (this.mServices) {
            this.mServices.put(componentName, service);
            this.mServiceCounters.put(service, new AtomicInteger(0));
        }
    }

    public Service forgetService(ComponentName componentName) {
        Service serviceRemove;
        synchronized (this.mServices) {
            serviceRemove = this.mServices.remove(componentName);
            this.mServiceCounters.remove(serviceRemove);
        }
        return serviceRemove;
    }

    public Intent forgetIServiceConnection(IBinder iBinder) {
        Intent intentRemove;
        synchronized (this.mBoundServices) {
            intentRemove = this.mBoundServices.remove(iBinder);
        }
        return intentRemove;
    }

    public void remberIServiceConnection(IBinder iBinder, Intent intent) {
        synchronized (this.mBoundServices) {
            this.mBoundServices.put(iBinder, intent);
        }
    }

    public boolean isServiceAvailable(ComponentName componentName) {
        return this.mServices.containsKey(componentName);
    }
}
