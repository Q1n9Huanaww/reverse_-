package com.iflytek.voiceads.download;

import android.app.Activity;

/* JADX INFO: renamed from: com.iflytek.voiceads.download.h */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class RunnableC1548h implements Runnable {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ Activity f3282a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ String f3283b;

    /* JADX INFO: renamed from: c */
    final /* synthetic */ C1541d f3284c;

    RunnableC1548h(C1541d c1541d, Activity activity, String str) {
        this.f3284c = c1541d;
        this.f3282a = activity;
        this.f3283b = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f3284c.m2971a(this.f3282a, new C1549i(this));
    }
}
