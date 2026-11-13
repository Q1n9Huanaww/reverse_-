package com.iflytek.voiceads.view;

import android.view.View;

/* JADX INFO: renamed from: com.iflytek.voiceads.view.f */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class ViewOnClickListenerC1629f implements View.OnClickListener {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ InterstitialAdView f3572a;

    ViewOnClickListenerC1629f(InterstitialAdView interstitialAdView) {
        this.f3572a = interstitialAdView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.f3572a.f3541k.onAdDestroy();
        this.f3572a.f3540j.onAdClose();
    }
}
