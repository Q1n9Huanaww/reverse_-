package com.iflytek.voiceads.view;

import com.iflytek.voiceads.config.AdError;
import com.iflytek.voiceads.config.ErrorCode;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.request.C1578a;
import com.iflytek.voiceads.utils.C1593g;

/* JADX INFO: renamed from: com.iflytek.voiceads.view.c */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class C1626c implements C1578a.a {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ AdView f3569a;

    C1626c(AdView adView) {
        this.f3569a = adView;
    }

    @Override // com.iflytek.voiceads.request.C1578a.a
    /* JADX INFO: renamed from: a */
    public void mo2892a(Exception exc, int i) {
        this.f3569a.f3542l.m3363a(5, i);
    }

    @Override // com.iflytek.voiceads.request.C1578a.a
    /* JADX INFO: renamed from: a */
    public void mo2893a(byte[] bArr) {
        try {
            if (bArr != null) {
                this.f3569a.m3324b(new String(bArr, "utf-8"));
            } else {
                C1593g.m3190b(SDKConstants.TAG, "Invalid response data!");
            }
        } catch (AdError e) {
            this.f3569a.f3542l.m3363a(5, e.getErrorCode());
        } catch (Exception e2) {
            this.f3569a.f3542l.m3363a(5, ErrorCode.ERROR_NETWORK);
            C1593g.m3190b(SDKConstants.TAG, "html parse1:" + e2.getMessage());
        }
    }
}
