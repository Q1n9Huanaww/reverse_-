package com.cyjh.p019mq.sdk;

import android.app.Application;
import android.content.Intent;
import com.cyjh.mobileanjian.ipc.interfaces.OnEngineStartCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnKeyEventListener;
import com.cyjh.mobileanjian.ipc.interfaces.RootProgressListener;
import com.cyjh.mobileanjian.ipc.log.C0748b;
import com.cyjh.p019mq.p020a.C0830a;
import com.cyjh.p019mq.service.IpcService;

/* JADX INFO: loaded from: classes.dex */
public class MqBridge {
    public static void init(Application application, String str, String str2, OnKeyEventListener onKeyEventListener, RootProgressListener rootProgressListener, OnEngineStartCallback onEngineStartCallback) {
        if (C0830a.f1527g == null) {
            C0830a.f1527g = application;
            C0830a.f1528h = onKeyEventListener;
            C0830a.f1529i = rootProgressListener;
            C0830a.f1530j = onEngineStartCallback;
            application.startService(new Intent(application, (Class<?>) IpcService.class));
        }
        C0748b.m750a(application, str, str2);
    }

    public static void exit() {
        if (C0830a.f1527g != null) {
            C0830a.f1527g.stopService(new Intent(C0830a.f1527g, (Class<?>) IpcService.class));
        }
    }
}
