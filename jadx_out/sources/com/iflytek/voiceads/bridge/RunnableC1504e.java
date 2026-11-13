package com.iflytek.voiceads.bridge;

import java.util.ArrayList;

/* JADX INFO: renamed from: com.iflytek.voiceads.bridge.e */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class RunnableC1504e implements Runnable {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ String f3098a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ DSBridgeWebView f3099b;

    RunnableC1504e(DSBridgeWebView dSBridgeWebView, String str) {
        this.f3099b = dSBridgeWebView;
        this.f3098a = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f3098a != null && this.f3098a.startsWith("javascript:")) {
            super/*android.webkit.WebView*/.loadUrl(this.f3098a);
            return;
        }
        this.f3099b.f3080k = new ArrayList();
        super/*android.webkit.WebView*/.loadUrl(this.f3098a);
    }
}
