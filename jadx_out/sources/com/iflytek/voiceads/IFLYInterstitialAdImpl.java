package com.iflytek.voiceads;

import android.content.Context;
import android.text.TextUtils;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.listener.IFLYAdListener;
import com.iflytek.voiceads.utils.C1593g;
import com.iflytek.voiceads.utils.C1595i;
import com.iflytek.voiceads.view.InterstitialAdView;

/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class IFLYInterstitialAdImpl extends IFLYInterstitialAd {

    /* JADX INFO: renamed from: a */
    public InterstitialAdView f3049a;

    private IFLYInterstitialAdImpl(Context context, String str) {
        super(context);
        this.f3049a = new InterstitialAdView(context, this, str, this.mInternalListener);
    }

    public static synchronized IFLYInterstitialAd createInterstitialAd(Context context, String str) {
        IFLYInterstitialAdImpl iFLYInterstitialAdImpl = null;
        synchronized (IFLYInterstitialAdImpl.class) {
            if (TextUtils.isEmpty(str) || context == null) {
                C1593g.m3188a(SDKConstants.TAG, "Ad constructor parameters error!");
            } else if (C1595i.m3197a(context)) {
                iFLYInterstitialAdImpl = new IFLYInterstitialAdImpl(context, str);
            } else {
                C1593g.m3188a(SDKConstants.TAG, "please check your uses-permission");
            }
        }
        return iFLYInterstitialAdImpl;
    }

    @Override // com.iflytek.voiceads.view.AdLayout
    public void destroy() {
        super.destroy();
        if (this.f3049a != null) {
            this.f3049a.m3354l();
            this.f3049a = null;
        }
    }

    @Override // com.iflytek.voiceads.IFLYInterstitialAd
    public synchronized void loadAd(IFLYAdListener iFLYAdListener) {
        if (this.f3049a != null) {
            this.f3049a.mo3337a(iFLYAdListener);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // com.iflytek.voiceads.IFLYInterstitialAd
    public void setParameter(String str, Object obj) {
        if (this.f3049a != null) {
            this.f3049a.m3339a(str, obj);
        }
    }

    @Override // com.iflytek.voiceads.IFLYInterstitialAd
    public synchronized void showAd() {
        if (this.f3049a != null) {
            this.f3049a.m3349g();
        }
    }
}
