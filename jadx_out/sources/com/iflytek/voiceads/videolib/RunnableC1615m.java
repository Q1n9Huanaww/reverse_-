package com.iflytek.voiceads.videolib;

/* JADX INFO: renamed from: com.iflytek.voiceads.videolib.m */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class RunnableC1615m implements Runnable {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ JZPlayer.C1601a f3524a;

    RunnableC1615m(JZPlayer.C1601a c1601a) {
        this.f3524a = c1601a;
    }

    @Override // java.lang.Runnable
    public void run() {
        long jM3219E = JZPlayer.this.m3219E();
        long jM3220F = JZPlayer.this.m3220F();
        JZPlayer.this.m3232a((int) ((jM3219E * 100) / (jM3220F == 0 ? 1L : jM3220F)), jM3219E, jM3220F);
    }
}
