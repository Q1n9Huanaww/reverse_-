package com.iflytek.voiceads.videolib;

/* JADX INFO: renamed from: com.iflytek.voiceads.videolib.i */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class RunnableC1611i implements Runnable {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ int f3517a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ int f3518b;

    /* JADX INFO: renamed from: c */
    final /* synthetic */ C1606d f3519c;

    RunnableC1611i(C1606d c1606d, int i, int i2) {
        this.f3519c = c1606d;
        this.f3517a = i;
        this.f3518b = i2;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (C1617o.m3317c() != null) {
            C1617o.m3317c().m3229a(this.f3517a, this.f3518b);
        }
    }
}
