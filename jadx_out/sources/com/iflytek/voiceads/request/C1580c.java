package com.iflytek.voiceads.request;

import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/* JADX INFO: renamed from: com.iflytek.voiceads.request.c */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class C1580c extends WebChromeClient {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ IFLYBrowser f3395a;

    C1580c(IFLYBrowser iFLYBrowser) {
        this.f3395a = iFLYBrowser;
    }

    @Override // android.webkit.WebChromeClient
    public void onPermissionRequest(PermissionRequest permissionRequest) {
        permissionRequest.grant(permissionRequest.getResources());
    }

    @Override // android.webkit.WebChromeClient
    public void onProgressChanged(WebView webView, int i) {
        if (i != 100) {
            this.f3395a.f3381f.setVisibility(0);
            this.f3395a.f3381f.setProgress(i);
        } else if (this.f3395a.f3381f != null) {
            this.f3395a.f3381f.setVisibility(8);
        }
    }
}
