package com.iflytek.voiceads.bridge;

/* JADX INFO: renamed from: com.iflytek.voiceads.bridge.d */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class RunnableC1503d implements Runnable {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ String f3096a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ DSBridgeWebView f3097b;

    RunnableC1503d(DSBridgeWebView dSBridgeWebView, String str) {
        this.f3097b = dSBridgeWebView;
        this.f3096a = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f3097b.m2865c(this.f3096a);
    }
}
