package com.iflytek.voiceads.view;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.iflytek.voiceads.p063b.C1492a;

/* JADX INFO: renamed from: com.iflytek.voiceads.view.g */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class C1630g implements C1492a.a {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ ImageView f3573a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ InterstitialAdView f3574b;

    C1630g(InterstitialAdView interstitialAdView, ImageView imageView) {
        this.f3574b = interstitialAdView;
        this.f3573a = imageView;
    }

    @Override // com.iflytek.voiceads.p063b.C1492a.a
    /* JADX INFO: renamed from: a */
    public void mo2839a(Bitmap bitmap) {
        if (bitmap != null) {
            this.f3573a.setImageBitmap(bitmap);
        }
    }
}
