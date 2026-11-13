package com.umeng.analytics.pro;

import com.umeng.analytics.AnalyticsConfig;

/* JADX INFO: renamed from: com.umeng.analytics.pro.j */
/* JADX INFO: compiled from: CrashHandler.java */
/* JADX INFO: loaded from: classes.dex */
public class C1716j implements Thread.UncaughtExceptionHandler {

    /* JADX INFO: renamed from: a */
    private Thread.UncaughtExceptionHandler f3902a;

    /* JADX INFO: renamed from: b */
    private InterfaceC1720n f3903b;

    public C1716j() {
        if (Thread.getDefaultUncaughtExceptionHandler() == this) {
            return;
        }
        this.f3902a = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /* JADX INFO: renamed from: a */
    public void m3638a(InterfaceC1720n interfaceC1720n) {
        this.f3903b = interfaceC1720n;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        m3637a(th);
        if (this.f3902a == null || this.f3902a == Thread.getDefaultUncaughtExceptionHandler()) {
            return;
        }
        this.f3902a.uncaughtException(thread, th);
    }

    /* JADX INFO: renamed from: a */
    private void m3637a(Throwable th) {
        if (AnalyticsConfig.CATCH_EXCEPTION) {
            this.f3903b.mo3482a(th);
        } else {
            this.f3903b.mo3482a(null);
        }
    }
}
