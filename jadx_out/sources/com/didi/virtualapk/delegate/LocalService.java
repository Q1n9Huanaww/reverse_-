package com.didi.virtualapk.delegate;

import android.app.ActivityThread;
import android.app.Application;
import android.app.IServiceConnection;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import com.didi.virtualapk.PluginManager;
import com.didi.virtualapk.internal.LoadedPlugin;
import com.didi.virtualapk.internal.utils.PluginUtil;
import com.didi.virtualapk.utils.Reflector;

/* JADX INFO: loaded from: classes.dex */
public class LocalService extends Service {
    public static final String EXTRA_COMMAND = "command";
    public static final int EXTRA_COMMAND_BIND_SERVICE = 3;
    public static final int EXTRA_COMMAND_START_SERVICE = 1;
    public static final int EXTRA_COMMAND_STOP_SERVICE = 2;
    public static final int EXTRA_COMMAND_UNBIND_SERVICE = 4;
    public static final String EXTRA_PLUGIN_LOCATION = "plugin_location";
    public static final String EXTRA_TARGET = "target";
    private static final String TAG = "VA.LocalService";
    private PluginManager mPluginManager;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return new Binder();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mPluginManager = PluginManager.getInstance(this);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        Service service;
        Throwable th;
        Service service2;
        if (intent == null || !intent.hasExtra(EXTRA_TARGET) || !intent.hasExtra(EXTRA_COMMAND)) {
            return 1;
        }
        Intent intent2 = (Intent) intent.getParcelableExtra(EXTRA_TARGET);
        int intExtra = intent.getIntExtra(EXTRA_COMMAND, 0);
        if (intent2 == null || intExtra <= 0) {
            return 1;
        }
        ComponentName component = intent2.getComponent();
        LoadedPlugin loadedPlugin = this.mPluginManager.getLoadedPlugin(component);
        if (loadedPlugin == null) {
            Log.w(TAG, "Error target: " + intent2.toURI());
            return 1;
        }
        intent2.setExtrasClassLoader(loadedPlugin.getClassLoader());
        switch (intExtra) {
            case 1:
                ActivityThread activityThreadCurrentActivityThread = ActivityThread.currentActivityThread();
                ActivityThread.ApplicationThread applicationThread = activityThreadCurrentActivityThread.getApplicationThread();
                if (this.mPluginManager.getComponentsHandler().isServiceAvailable(component)) {
                    service = this.mPluginManager.getComponentsHandler().getService(component);
                } else {
                    try {
                        Service service3 = (Service) loadedPlugin.getClassLoader().loadClass(component.getClassName()).newInstance();
                        service3.getClass().getMethod("attach", Context.class, ActivityThread.class, String.class, IBinder.class, Application.class, Object.class).invoke(service3, loadedPlugin.getPluginContext(), activityThreadCurrentActivityThread, component.getClassName(), applicationThread.asBinder(), loadedPlugin.getApplication(), this.mPluginManager.getActivityManager());
                        service3.onCreate();
                        this.mPluginManager.getComponentsHandler().rememberService(component, service3);
                        service = service3;
                    } catch (Throwable unused) {
                        return 1;
                    }
                }
                service.onStartCommand(intent2, 0, this.mPluginManager.getComponentsHandler().getServiceCounter(service).getAndIncrement());
                return 1;
            case 2:
                Service serviceForgetService = this.mPluginManager.getComponentsHandler().forgetService(component);
                if (serviceForgetService != null) {
                    try {
                        serviceForgetService.onDestroy();
                    } catch (Exception e) {
                        Log.e(TAG, "Unable to stop service " + serviceForgetService + ": " + e.toString());
                    }
                    break;
                } else {
                    Log.i(TAG, component + " not found");
                }
                return 1;
            case 3:
                ActivityThread activityThreadCurrentActivityThread2 = ActivityThread.currentActivityThread();
                ActivityThread.ApplicationThread applicationThread2 = activityThreadCurrentActivityThread2.getApplicationThread();
                if (this.mPluginManager.getComponentsHandler().isServiceAvailable(component)) {
                    service2 = this.mPluginManager.getComponentsHandler().getService(component);
                } else {
                    try {
                        service2 = (Service) loadedPlugin.getClassLoader().loadClass(component.getClassName()).newInstance();
                        try {
                            service2.getClass().getMethod("attach", Context.class, ActivityThread.class, String.class, IBinder.class, Application.class, Object.class).invoke(service2, loadedPlugin.getPluginContext(), activityThreadCurrentActivityThread2, component.getClassName(), applicationThread2.asBinder(), loadedPlugin.getApplication(), this.mPluginManager.getActivityManager());
                            service2.onCreate();
                            this.mPluginManager.getComponentsHandler().rememberService(component, service2);
                        } catch (Throwable th2) {
                            th = th2;
                            Log.w(TAG, th);
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        service2 = null;
                    }
                    break;
                }
                try {
                    IBinder iBinderOnBind = service2.onBind(intent2);
                    IServiceConnection iServiceConnectionAsInterface = IServiceConnection.Stub.asInterface(PluginUtil.getBinder(intent.getExtras(), "sc"));
                    if (Build.VERSION.SDK_INT >= 26) {
                        iServiceConnectionAsInterface.connected(component, iBinderOnBind, false);
                    } else {
                        Reflector.QuietReflector.with((Object) iServiceConnectionAsInterface).method("connected", ComponentName.class, IBinder.class).call(component, iBinderOnBind);
                    }
                    break;
                } catch (Exception e2) {
                    Log.w(TAG, e2);
                }
                return 1;
            case 4:
                Service serviceForgetService2 = this.mPluginManager.getComponentsHandler().forgetService(component);
                if (serviceForgetService2 != null) {
                    try {
                        serviceForgetService2.onUnbind(intent2);
                        serviceForgetService2.onDestroy();
                    } catch (Exception e3) {
                        Log.e(TAG, "Unable to unbind service " + serviceForgetService2 + ": " + e3.toString());
                    }
                    break;
                } else {
                    Log.i(TAG, component + " not found");
                }
                return 1;
            default:
                return 1;
        }
    }
}
