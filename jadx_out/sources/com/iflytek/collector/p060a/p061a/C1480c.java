package com.iflytek.collector.p060a.p061a;

import android.os.SystemClock;

/* JADX INFO: renamed from: com.iflytek.collector.a.a.c */
/* JADX INFO: loaded from: classes.dex */
class C1480c extends Thread {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C1479b f3033a;

    C1480c(C1479b c1479b) {
        this.f3033a = c1479b;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        SystemClock.sleep(5000L);
        new RunnableC1486i(this.f3033a.f3029c).run();
    }
}
