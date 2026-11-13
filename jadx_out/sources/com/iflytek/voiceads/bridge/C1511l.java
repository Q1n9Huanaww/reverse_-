package com.iflytek.voiceads.bridge;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/* JADX INFO: renamed from: com.iflytek.voiceads.bridge.l */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class C1511l extends WebViewClient {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ DSBridgeWebView f3112a;

    C1511l(DSBridgeWebView dSBridgeWebView) {
        this.f3112a = dSBridgeWebView;
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        if (this.f3112a.f3077h != null) {
            this.f3112a.f3077h.mo2884b();
        } else {
            super.onPageFinished(webView, str);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (this.f3112a.f3077h != null) {
            this.f3112a.f3077h.mo2881a();
        } else {
            super.onPageStarted(webView, str, bitmap);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        if (this.f3112a.f3077h != null) {
            this.f3112a.f3077h.mo2882a(i, str);
        } else {
            super.onReceivedError(webView, i, str, str2);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        if (sslError.getPrimaryError() == 5) {
            sslErrorHandler.proceed();
        } else {
            sslErrorHandler.cancel();
        }
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return this.f3112a.f3077h != null ? this.f3112a.f3077h.mo2883a(webView, str) : super.shouldOverrideUrlLoading(webView, str);
    }
}
