package com.iflytek.voiceads;

import android.content.Context;
import android.text.TextUtils;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.listener.IFLYAdListener;
import com.iflytek.voiceads.utils.C1593g;
import com.iflytek.voiceads.utils.C1595i;
import com.iflytek.voiceads.view.BannerAdView;

/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class IFLYBannerAdImpl extends IFLYBannerAd {

    /* JADX INFO: renamed from: a */
    public BannerAdView f3048a;

    private IFLYBannerAdImpl(Context context, String str) {
        super(context);
        this.f3048a = new BannerAdView(context, this, str, this.mInternalListener);
    }

    public static IFLYBannerAdImpl createBannerAd(Context context, String str) {
        if (TextUtils.isEmpty(str) || context == null) {
            C1593g.m3188a(SDKConstants.TAG, "Ad constructor parameters error!");
            return null;
        }
        if (C1595i.m3197a(context)) {
            return new IFLYBannerAdImpl(context, str);
        }
        C1593g.m3188a(SDKConstants.TAG, "please check your uses-permission");
        return null;
    }

    @Override // com.iflytek.voiceads.view.AdLayout
    public synchronized void destroy() {
        super.destroy();
        if (this.f3048a != null) {
            this.f3048a.m3354l();
            this.f3048a = null;
        }
    }

    @Override // com.iflytek.voiceads.IFLYBannerAd
    public synchronized void loadAd(IFLYAdListener iFLYAdListener) {
        if (this.f3048a != null) {
            this.f3048a.mo3337a(iFLYAdListener);
        }
    }

    @Override // com.iflytek.voiceads.IFLYBannerAd
    public void setParameter(String str, Object obj) {
        if (this.f3048a != null) {
            this.f3048a.m3339a(str, obj);
        }
    }

    @Override // com.iflytek.voiceads.IFLYBannerAd
    public synchronized void showAd() {
        if (this.f3048a != null) {
            this.f3048a.m3349g();
        }
    }
}
