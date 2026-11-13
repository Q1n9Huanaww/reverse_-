package com.didi.virtualapk.delegate;

import android.app.ActivityManagerNative;
import android.app.IActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import com.didi.virtualapk.PluginManager;
import com.didi.virtualapk.internal.utils.PluginUtil;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class ActivityManagerProxy implements InvocationHandler {
    public static final int INTENT_SENDER_ACTIVITY = 2;
    public static final int INTENT_SENDER_ACTIVITY_RESULT = 3;
    public static final int INTENT_SENDER_BROADCAST = 1;
    public static final int INTENT_SENDER_SERVICE = 4;
    private static final String TAG = "VA.IActivityManagerProxy";
    private IActivityManager mActivityManager;
    private PluginManager mPluginManager;

    public ActivityManagerProxy(PluginManager pluginManager, IActivityManager iActivityManager) {
        this.mPluginManager = pluginManager;
        this.mActivityManager = iActivityManager;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        IBinder service;
        if ("startService".equals(method.getName())) {
            try {
                return startService(obj, method, objArr);
            } catch (Throwable th) {
                Log.e(TAG, "Start service error", th);
            }
        } else if ("stopService".equals(method.getName())) {
            try {
                return stopService(obj, method, objArr);
            } catch (Throwable th2) {
                Log.e(TAG, "Stop Service error", th2);
            }
        } else if ("stopServiceToken".equals(method.getName())) {
            try {
                return stopServiceToken(obj, method, objArr);
            } catch (Throwable th3) {
                Log.e(TAG, "Stop service token error", th3);
            }
        } else if ("bindService".equals(method.getName())) {
            try {
                return bindService(obj, method, objArr);
            } catch (Throwable th4) {
                Log.w(TAG, th4);
            }
        } else if ("unbindService".equals(method.getName())) {
            try {
                return unbindService(obj, method, objArr);
            } catch (Throwable th5) {
                Log.w(TAG, th5);
            }
        } else if ("getIntentSender".equals(method.getName())) {
            try {
                getIntentSender(method, objArr);
            } catch (Exception e) {
                Log.w(TAG, e);
            }
        } else if ("overridePendingTransition".equals(method.getName())) {
            try {
                overridePendingTransition(method, objArr);
            } catch (Exception e2) {
                Log.w(TAG, e2);
            }
        }
        try {
            return method.invoke(this.mActivityManager, objArr);
        } catch (Throwable th6) {
            Throwable cause = th6.getCause();
            if (cause != null && (cause instanceof DeadObjectException) && (service = ServiceManager.getService("activity")) != null) {
                this.mActivityManager = ActivityManagerNative.asInterface(service);
            }
            Throwable cause2 = th6;
            while (!(cause2 instanceof RemoteException)) {
                cause2 = cause2.getCause();
                if (cause2 == null) {
                    if (cause != null) {
                        throw cause;
                    }
                    throw th6;
                }
            }
            throw cause2;
        }
    }

    protected Object startService(Object obj, Method method, Object[] objArr) throws Throwable {
        Intent intent = (Intent) objArr[1];
        ResolveInfo resolveInfoResolveService = this.mPluginManager.resolveService(intent, 0);
        if (resolveInfoResolveService == null || resolveInfoResolveService.serviceInfo == null) {
            return method.invoke(this.mActivityManager, objArr);
        }
        return startDelegateServiceForTarget(intent, resolveInfoResolveService.serviceInfo, null, 1);
    }

    protected Object stopService(Object obj, Method method, Object[] objArr) throws Throwable {
        Intent intent = (Intent) objArr[1];
        ResolveInfo resolveInfoResolveService = this.mPluginManager.resolveService(intent, 0);
        if (resolveInfoResolveService == null || resolveInfoResolveService.serviceInfo == null) {
            return method.invoke(this.mActivityManager, objArr);
        }
        startDelegateServiceForTarget(intent, resolveInfoResolveService.serviceInfo, null, 2);
        return 1;
    }

    protected Object stopServiceToken(Object obj, Method method, Object[] objArr) throws Throwable {
        Intent component = new Intent().setComponent((ComponentName) objArr[0]);
        ResolveInfo resolveInfoResolveService = this.mPluginManager.resolveService(component, 0);
        if (resolveInfoResolveService == null || resolveInfoResolveService.serviceInfo == null) {
            return method.invoke(this.mActivityManager, objArr);
        }
        startDelegateServiceForTarget(component, resolveInfoResolveService.serviceInfo, null, 2);
        return true;
    }

    protected Object bindService(Object obj, Method method, Object[] objArr) throws Throwable {
        Intent intent = (Intent) objArr[2];
        ResolveInfo resolveInfoResolveService = this.mPluginManager.resolveService(intent, 0);
        if (resolveInfoResolveService == null || resolveInfoResolveService.serviceInfo == null) {
            return method.invoke(this.mActivityManager, objArr);
        }
        Bundle bundle = new Bundle();
        PluginUtil.putBinder(bundle, "sc", (IBinder) objArr[4]);
        startDelegateServiceForTarget(intent, resolveInfoResolveService.serviceInfo, bundle, 3);
        this.mPluginManager.getComponentsHandler().remberIServiceConnection((IBinder) objArr[4], intent);
        return 1;
    }

    protected Object unbindService(Object obj, Method method, Object[] objArr) throws Throwable {
        Intent intentForgetIServiceConnection = this.mPluginManager.getComponentsHandler().forgetIServiceConnection((IBinder) objArr[0]);
        if (intentForgetIServiceConnection == null) {
            return method.invoke(this.mActivityManager, objArr);
        }
        startDelegateServiceForTarget(intentForgetIServiceConnection, this.mPluginManager.resolveService(intentForgetIServiceConnection, 0).serviceInfo, null, 4);
        return true;
    }

    protected ComponentName startDelegateServiceForTarget(Intent intent, ServiceInfo serviceInfo, Bundle bundle, int i) {
        return this.mPluginManager.getHostContext().startService(wrapperTargetIntent(intent, serviceInfo, bundle, i));
    }

    protected Intent wrapperTargetIntent(Intent intent, ServiceInfo serviceInfo, Bundle bundle, int i) {
        intent.setComponent(new ComponentName(serviceInfo.packageName, serviceInfo.name));
        String location = this.mPluginManager.getLoadedPlugin(intent.getComponent()).getLocation();
        Class<?> cls = PluginUtil.isLocalService(serviceInfo) ? LocalService.class : RemoteService.class;
        Intent intent2 = new Intent();
        intent2.setClass(this.mPluginManager.getHostContext(), cls);
        intent2.putExtra(LocalService.EXTRA_TARGET, intent);
        intent2.putExtra(LocalService.EXTRA_COMMAND, i);
        intent2.putExtra(LocalService.EXTRA_PLUGIN_LOCATION, location);
        if (bundle != null) {
            intent2.putExtras(bundle);
        }
        return intent2;
    }

    protected void getIntentSender(Method method, Object[] objArr) {
        ResolveInfo resolveInfoResolveService;
        objArr[1] = this.mPluginManager.getHostContext().getPackageName();
        Intent intent = ((Intent[]) objArr[5])[0];
        int iIntValue = ((Integer) objArr[0]).intValue();
        if (iIntValue == 2) {
            this.mPluginManager.getComponentsHandler().transformIntentToExplicitAsNeeded(intent);
            this.mPluginManager.getComponentsHandler().markIntentIfNeeded(intent);
        } else {
            if (iIntValue != 4 || (resolveInfoResolveService = this.mPluginManager.resolveService(intent, 0)) == null || resolveInfoResolveService.serviceInfo == null) {
                return;
            }
            ((Intent[]) objArr[5])[0] = wrapperTargetIntent(intent, resolveInfoResolveService.serviceInfo, null, 1);
        }
    }

    protected void overridePendingTransition(Method method, Object[] objArr) {
        objArr[1] = this.mPluginManager.getHostContext().getPackageName();
    }
}
