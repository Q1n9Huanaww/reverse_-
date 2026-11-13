package com.iflytek.voiceads.bridge;

import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: renamed from: com.iflytek.voiceads.bridge.f */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class RunnableC1505f implements Runnable {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ String f3100a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ Map f3101b;

    /* JADX INFO: renamed from: c */
    final /* synthetic */ DSBridgeWebView f3102c;

    RunnableC1505f(DSBridgeWebView dSBridgeWebView, String str, Map map) {
        this.f3102c = dSBridgeWebView;
        this.f3100a = str;
        this.f3101b = map;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f3100a != null && this.f3100a.startsWith("javascript:")) {
            super/*android.webkit.WebView*/.loadUrl(this.f3100a, this.f3101b);
            return;
        }
        this.f3102c.f3080k = new ArrayList();
        super/*android.webkit.WebView*/.loadUrl(this.f3100a, this.f3101b);
    }
}
