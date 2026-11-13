package com.iflytek.voiceads.request;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import com.iflytek.voiceads.bridge.InterfaceC1514o;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.param.C1572c;
import com.iflytek.voiceads.utils.C1588b;
import com.iflytek.voiceads.utils.C1593g;
import com.iflytek.voiceads.utils.C1596j;

/* JADX INFO: renamed from: com.iflytek.voiceads.request.b */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class C1579b implements InterfaceC1514o {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ IFLYBrowser f3394a;

    C1579b(IFLYBrowser iFLYBrowser) {
        this.f3394a = iFLYBrowser;
    }

    @Override // com.iflytek.voiceads.bridge.InterfaceC1514o
    /* JADX INFO: renamed from: a */
    public void mo2881a() {
    }

    @Override // com.iflytek.voiceads.bridge.InterfaceC1514o
    /* JADX INFO: renamed from: a */
    public void mo2882a(int i, String str) {
    }

    @Override // com.iflytek.voiceads.bridge.InterfaceC1514o
    /* JADX INFO: renamed from: a */
    public boolean mo2883a(WebView webView, String str) {
        C1593g.m3188a(SDKConstants.TAG, "IFLYBrowser shouldOverrideUrlLoading:" + str);
        if (!C1588b.m3169a(str)) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        if (!C1588b.m3166a(this.f3394a.getApplicationContext(), intent)) {
            C1596j.m3204b(C1572c.f3370b + "type=H5NotInstall&sid=" + this.f3394a.f3383h);
            return true;
        }
        try {
            intent.addFlags(32768);
            intent.addFlags(268435456);
            this.f3394a.startActivity(intent);
            C1596j.m3204b(C1572c.f3370b + "type=H5DeepLink&sid=" + this.f3394a.f3383h);
            return true;
        } catch (Exception e) {
            C1596j.m3204b(C1572c.f3370b + "type=H5DeepFail&sid=" + this.f3394a.f3383h);
            C1593g.m3190b(SDKConstants.TAG, e.getMessage());
            return true;
        }
    }

    @Override // com.iflytek.voiceads.bridge.InterfaceC1514o
    /* JADX INFO: renamed from: b */
    public void mo2884b() {
    }
}
