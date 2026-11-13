package com.iflytek.voiceads.download;

import android.app.Activity;

/* JADX INFO: renamed from: com.iflytek.voiceads.download.f */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class RunnableC1546f implements Runnable {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ Activity f3278a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ C1541d.b f3279b;

    /* JADX INFO: renamed from: c */
    final /* synthetic */ C1541d f3280c;

    RunnableC1546f(C1541d c1541d, Activity activity, C1541d.b bVar) {
        this.f3280c = c1541d;
        this.f3278a = activity;
        this.f3279b = bVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f3280c.m2971a(this.f3278a, new C1547g(this));
    }
}
