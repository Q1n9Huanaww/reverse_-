package com.iflytek.voiceads.bridge;

import java.util.ArrayList;

/* JADX INFO: renamed from: com.iflytek.voiceads.bridge.g */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class RunnableC1506g implements Runnable {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ DSBridgeWebView f3103a;

    RunnableC1506g(DSBridgeWebView dSBridgeWebView) {
        this.f3103a = dSBridgeWebView;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f3103a.f3080k = new ArrayList();
        super/*android.webkit.WebView*/.reload();
    }
}
