package com.iflytek.voiceads;

import android.content.Context;
import android.view.ViewGroup;
import com.iflytek.voiceads.listener.IFLYVideoListener;
import com.iflytek.voiceads.p073e.C1558a;

/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class IFLYVideoAdImpl extends IFLYVideoAd {

    /* JADX INFO: renamed from: a */
    private C1558a f3051a;

    public IFLYVideoAdImpl(Context context, String str, int i, IFLYVideoListener iFLYVideoListener) {
        this.f3051a = new C1558a(context, str, i, iFLYVideoListener);
    }

    @Override // com.iflytek.voiceads.IFLYVideoAd
    public boolean backPressed() {
        if (this.f3051a != null) {
            return this.f3051a.m3058j();
        }
        return false;
    }

    @Override // com.iflytek.voiceads.IFLYVideoAd
    public void cacheVideo() {
        if (this.f3051a != null) {
            this.f3051a.m3048b();
        }
    }

    @Override // com.iflytek.voiceads.IFLYVideoAd
    public ViewGroup getVideoView() {
        if (this.f3051a != null) {
            return this.f3051a.f3298a;
        }
        return null;
    }

    @Override // com.iflytek.voiceads.IFLYVideoAd
    public boolean isPlaying() {
        if (this.f3051a != null) {
            return this.f3051a.m3057i();
        }
        return false;
    }

    @Override // com.iflytek.voiceads.IFLYVideoAd
    public void loadAd() {
        if (this.f3051a != null) {
            this.f3051a.m3044a();
        }
    }

    @Override // com.iflytek.voiceads.IFLYVideoAd
    public void onPause() {
        if (this.f3051a != null) {
            this.f3051a.m3055g();
        }
    }

    @Override // com.iflytek.voiceads.IFLYVideoAd
    public void onResume() {
        if (this.f3051a != null) {
            this.f3051a.m3056h();
        }
    }

    @Override // com.iflytek.voiceads.IFLYVideoAd
    public void release() {
        if (this.f3051a != null) {
            this.f3051a.m3053e();
        }
    }

    @Override // com.iflytek.voiceads.IFLYVideoAd
    public void setDirectJump(boolean z) {
        if (this.f3051a != null) {
            this.f3051a.m3051c(z);
        }
    }

    @Override // com.iflytek.voiceads.IFLYVideoAd
    public void setParameter(String str, Object obj) {
        if (this.f3051a != null) {
            this.f3051a.m3045a(str, obj);
        }
    }

    @Override // com.iflytek.voiceads.IFLYVideoAd
    public void setVolume(boolean z) {
        if (this.f3051a != null) {
            this.f3051a.m3046a(z);
        }
    }

    @Override // com.iflytek.voiceads.IFLYVideoAd
    public void setWifiTip(boolean z) {
        if (this.f3051a != null) {
            this.f3051a.m3049b(z);
        }
    }

    @Override // com.iflytek.voiceads.IFLYVideoAd
    public void showAd(Object... objArr) {
        if (this.f3051a != null) {
            this.f3051a.m3047a(objArr);
        }
    }

    @Override // com.iflytek.voiceads.IFLYVideoAd
    public void skipAd() {
        if (this.f3051a != null) {
            this.f3051a.m3054f();
        }
    }

    @Override // com.iflytek.voiceads.IFLYVideoAd
    public void startPlay() {
        if (this.f3051a != null) {
            this.f3051a.m3052d();
        }
    }

    @Override // com.iflytek.voiceads.IFLYVideoAd
    public void videoDownScroll() {
        if (this.f3051a != null) {
            this.f3051a.m3060l();
        }
    }

    @Override // com.iflytek.voiceads.IFLYVideoAd
    public void videoUpScroll() {
        if (this.f3051a != null) {
            this.f3051a.m3059k();
        }
    }
}
