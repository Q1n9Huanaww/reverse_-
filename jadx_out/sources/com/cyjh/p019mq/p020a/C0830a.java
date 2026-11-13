package com.cyjh.p019mq.p020a;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import com.cyjh.mobileanjian.ipc.interfaces.OnEngineStartCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnKeyEventListener;
import com.cyjh.mobileanjian.ipc.interfaces.RootProgressListener;
import com.cyjh.p019mq.service.IpcService;
import java.io.File;

/* JADX INFO: renamed from: com.cyjh.mq.a.a */
/* JADX INFO: compiled from: MyApplication.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0830a {

    /* JADX INFO: renamed from: a */
    public static final String f1521a = "start_eventsrv";

    /* JADX INFO: renamed from: b */
    public static final String f1522b = "start_eventsrvR";

    /* JADX INFO: renamed from: c */
    public static final String f1523c = ".event.localserver";

    /* JADX INFO: renamed from: d */
    public static final String f1524d = "com.cyjh.mobileanjian.ipc.ClientService";

    /* JADX INFO: renamed from: e */
    public static final String f1525e = "DaemonClient.zip";

    /* JADX INFO: renamed from: f */
    public static final String f1526f = "libmqm.so";

    /* JADX INFO: renamed from: g */
    public static Application f1527g;

    /* JADX INFO: renamed from: h */
    public static OnKeyEventListener f1528h;

    /* JADX INFO: renamed from: i */
    public static RootProgressListener f1529i;

    /* JADX INFO: renamed from: j */
    public static OnEngineStartCallback f1530j;

    /* JADX INFO: renamed from: a */
    private static void m1169a(Application application, OnKeyEventListener onKeyEventListener, RootProgressListener rootProgressListener, OnEngineStartCallback onEngineStartCallback) {
        if (f1527g != null) {
            return;
        }
        f1527g = application;
        f1528h = onKeyEventListener;
        f1529i = rootProgressListener;
        f1530j = onEngineStartCallback;
        application.startService(new Intent(application, (Class<?>) IpcService.class));
    }

    /* JADX INFO: renamed from: b */
    private static void m1170b() {
        if (f1527g != null) {
            f1527g.stopService(new Intent(f1527g, (Class<?>) IpcService.class));
        }
    }

    /* JADX INFO: renamed from: c */
    private static Application m1171c() {
        return f1527g;
    }

    /* JADX INFO: renamed from: d */
    private static Context m1172d() {
        return f1527g.getApplicationContext();
    }

    /* JADX INFO: renamed from: e */
    private static File m1173e() {
        return new File(f1527g.getFilesDir(), f1521a);
    }

    /* JADX INFO: renamed from: a */
    public static File m1168a() {
        return new File(f1527g.getFilesDir(), f1522b);
    }

    /* JADX INFO: renamed from: f */
    private static OnKeyEventListener m1174f() {
        return f1528h;
    }

    /* JADX INFO: renamed from: g */
    private static RootProgressListener m1175g() {
        return f1529i;
    }

    /* JADX INFO: renamed from: h */
    private static OnEngineStartCallback m1176h() {
        return f1530j;
    }
}
