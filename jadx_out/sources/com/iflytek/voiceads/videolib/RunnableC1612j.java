package com.iflytek.voiceads.videolib;

/* JADX INFO: renamed from: com.iflytek.voiceads.videolib.j */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class RunnableC1612j implements Runnable {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ int f3520a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ C1606d f3521b;

    RunnableC1612j(C1606d c1606d, int i) {
        this.f3521b = c1606d;
        this.f3520a = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (C1617o.m3317c() == null || this.f3520a != 3) {
            return;
        }
        C1617o.m3317c().m3255l();
    }
}
