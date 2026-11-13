package com.iflytek.voiceads;

import android.content.Context;
import com.iflytek.voiceads.listener.IFLYNativeListener;
import com.iflytek.voiceads.p064c.C1515a;

/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class IFLYNativeAdImpl extends IFLYNativeAd {

    /* JADX INFO: renamed from: a */
    private C1515a f3050a;

    public IFLYNativeAdImpl(Context context, String str, IFLYNativeListener iFLYNativeListener) {
        this.f3050a = new C1515a(context, str, iFLYNativeListener);
    }

    @Override // com.iflytek.voiceads.IFLYNativeAd
    public void loadAd() {
        if (this.f3050a != null) {
            this.f3050a.m2890a();
        }
    }

    @Override // com.iflytek.voiceads.IFLYNativeAd
    public void setParameter(String str, Object obj) {
        if (this.f3050a != null) {
            this.f3050a.m2891a(str, obj);
        }
    }
}
