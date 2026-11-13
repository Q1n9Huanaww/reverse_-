package com.iflytek.voiceads.p074f;

import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/* JADX INFO: renamed from: com.iflytek.voiceads.f.a */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1566a extends WebChromeClient {

    /* JADX INFO: renamed from: a */
    private int f3361a;

    /* JADX INFO: renamed from: b */
    private InterfaceC1568c f3362b;

    public C1566a(InterfaceC1568c interfaceC1568c) {
        this.f3362b = interfaceC1568c;
    }

    /* JADX INFO: renamed from: a */
    private void m3066a(int i) {
        this.f3361a = i;
    }

    /* JADX INFO: renamed from: a */
    public int m3067a() {
        return this.f3361a;
    }

    @Override // android.webkit.WebChromeClient
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        return super.onConsoleMessage(consoleMessage);
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        this.f3362b.mo3070a(str, str2, str3, jsPromptResult);
        return true;
    }

    @Override // android.webkit.WebChromeClient
    public void onProgressChanged(WebView webView, int i) {
        super.onProgressChanged(webView, i);
        m3066a(i);
        this.f3362b.mo3069a(i);
    }
}
