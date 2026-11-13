package com.iflytek.voiceads.view;

import android.webkit.WebView;
import com.iflytek.voiceads.config.ErrorCode;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.p074f.InterfaceC1569d;
import com.iflytek.voiceads.utils.C1593g;
import org.wlf.filedownloader.base.BaseDownloadConfigBuilder;

/* JADX INFO: renamed from: com.iflytek.voiceads.view.d */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class C1627d implements InterfaceC1569d {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ AdView f3570a;

    C1627d(AdView adView) {
        this.f3570a = adView;
    }

    @Override // com.iflytek.voiceads.p074f.InterfaceC1569d
    /* JADX INFO: renamed from: a */
    public void mo3071a() {
        C1593g.m3188a(SDKConstants.TAG, "onPageStarted");
        this.f3570a.f3542l.m3364a(this.f3570a.f3542l.obtainMessage(4), BaseDownloadConfigBuilder.DEFAULT_CONNECT_TIMEOUT);
    }

    @Override // com.iflytek.voiceads.p074f.InterfaceC1569d
    /* JADX INFO: renamed from: a */
    public void mo3072a(int i, String str) {
        C1593g.m3188a(SDKConstants.TAG, "WebViewClientCallback onLoadError");
        this.f3570a.f3542l.m3363a(5, ErrorCode.ERROR_PAGE_LOAD_TIMEOUT);
    }

    @Override // com.iflytek.voiceads.p074f.InterfaceC1569d
    /* JADX INFO: renamed from: a */
    public void mo3073a(WebView webView, String str) {
        C1593g.m3188a(SDKConstants.TAG, "shouldOverrideUrlLoading： height:" + this.f3570a.getContentHeight() + ", progress:" + this.f3570a.f3534d.m3067a());
        this.f3570a.m3338a(str);
    }

    @Override // com.iflytek.voiceads.p074f.InterfaceC1569d
    /* JADX INFO: renamed from: b */
    public void mo3074b() {
        C1593g.m3188a(SDKConstants.TAG, "onPageFinished： height:" + this.f3570a.getContentHeight() + ", progress:" + this.f3570a.f3534d.m3067a());
        this.f3570a.m3355m();
    }
}
