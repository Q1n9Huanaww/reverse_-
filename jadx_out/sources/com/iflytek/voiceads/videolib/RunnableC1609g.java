package com.iflytek.voiceads.videolib;

/* JADX INFO: renamed from: com.iflytek.voiceads.videolib.g */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class RunnableC1609g implements Runnable {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ int f3514a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ C1606d f3515b;

    RunnableC1609g(C1606d c1606d, int i) {
        this.f3515b = c1606d;
        this.f3514a = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (C1617o.m3317c() != null) {
            C1617o.m3317c().m3247d(this.f3514a);
        }
    }
}
