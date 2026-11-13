package com.iflytek.voiceads.p074f;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.utils.C1593g;

/* JADX INFO: renamed from: com.iflytek.voiceads.f.b */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1567b extends WebViewClient {

    /* JADX INFO: renamed from: a */
    private boolean f3363a = false;

    /* JADX INFO: renamed from: b */
    private boolean f3364b = false;

    /* JADX INFO: renamed from: c */
    private boolean f3365c = false;

    /* JADX INFO: renamed from: d */
    private InterfaceC1569d f3366d;

    public C1567b(InterfaceC1569d interfaceC1569d) {
        this.f3366d = interfaceC1569d;
    }

    /* JADX INFO: renamed from: a */
    public void m3068a(boolean z) {
        this.f3365c = z;
    }

    @Override // android.webkit.WebViewClient
    public void onLoadResource(WebView webView, String str) {
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        C1593g.m3188a(SDKConstants.TAG, "onPageFinished:" + this.f3364b);
        if (!this.f3364b) {
            this.f3363a = true;
        }
        if (!this.f3363a || this.f3364b) {
            this.f3364b = false;
        } else {
            this.f3366d.mo3074b();
        }
        super.onPageFinished(webView, str);
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
        m3068a(false);
        this.f3363a = false;
        this.f3364b = false;
        this.f3366d.mo3071a();
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        this.f3366d.mo3072a(i, str);
        super.onReceivedError(webView, i, str, str2);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        if (sslError.getPrimaryError() == 5) {
            sslErrorHandler.proceed();
        } else {
            sslErrorHandler.cancel();
            this.f3366d.mo3072a(-1, sslError.toString());
        }
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        C1593g.m3188a(SDKConstants.TAG, "shouldOverrideUrlLoading:" + this.f3365c + this.f3363a);
        if (this.f3365c) {
            m3068a(false);
            this.f3366d.mo3073a(webView, str);
        } else {
            if (!this.f3363a) {
                this.f3364b = true;
            }
            this.f3363a = false;
            webView.loadUrl(str);
            C1593g.m3188a(SDKConstants.TAG, "shouldOverrideUrlLoading click=false");
        }
        return true;
    }
}
