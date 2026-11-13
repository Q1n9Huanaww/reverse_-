package com.iflytek.voiceads.view;

import android.content.Context;
import android.os.Message;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.listener.IFLYAdListener;
import com.iflytek.voiceads.listener.InternalListener;
import com.iflytek.voiceads.param.C1573d;
import com.iflytek.voiceads.utils.C1587a;
import com.iflytek.voiceads.utils.C1588b;
import com.iflytek.voiceads.utils.C1593g;

/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class BannerAdView extends AdView {

    /* JADX INFO: renamed from: t */
    private boolean f3556t;

    /* JADX INFO: renamed from: u */
    private Context f3557u;

    /* JADX INFO: renamed from: v */
    private boolean f3558v;

    public BannerAdView(Context context) {
        super(context);
        this.f3556t = false;
        this.f3558v = true;
    }

    public BannerAdView(Context context, RelativeLayout relativeLayout, String str, InternalListener internalListener) {
        super(context, relativeLayout, str, C1587a.a.BANNER, internalListener);
        this.f3556t = false;
        this.f3558v = true;
        this.f3557u = context.getApplicationContext();
    }

    @Override // com.iflytek.voiceads.view.AdView
    /* JADX INFO: renamed from: a */
    public synchronized void mo3337a(IFLYAdListener iFLYAdListener) {
        if (!this.f3556t) {
            this.f3556t = true;
            super.mo3337a(iFLYAdListener);
        }
    }

    @Override // com.iflytek.voiceads.view.AdView
    /* JADX INFO: renamed from: d */
    protected synchronized void mo3346d(Message message) throws Exception {
        if (C1588b.m3165a(this.f3557u) || C1588b.m3170b(this.f3557u)) {
            C1593g.m3188a(SDKConstants.TAG, "Ad is invisible, please check the app's state!");
            this.f3542l.m3364a(this.f3542l.obtainMessage(1), m3347e());
        } else {
            ViewGroup viewGroup = (ViewGroup) this.f3532b.getParent();
            if (viewGroup == null) {
                C1593g.m3188a(SDKConstants.TAG, "Ad has no parent!");
            } else {
                if (this.f3558v) {
                    this.f3558v = false;
                } else if (!this.f3532b.isShown()) {
                    C1593g.m3188a(SDKConstants.TAG, "Ad is invisible, recycle delayed!");
                    this.f3542l.m3364a(this.f3542l.obtainMessage(1), m3347e());
                } else if (!C1588b.m3167a(this.f3557u, viewGroup)) {
                    C1593g.m3188a(SDKConstants.TAG, "Ad is invisible,not in screen, recycle delayed!");
                    this.f3542l.m3364a(this.f3542l.obtainMessage(1), m3347e());
                }
                super.mo3346d(message);
            }
        }
    }

    @Override // com.iflytek.voiceads.view.AdView
    /* JADX INFO: renamed from: h */
    protected synchronized void mo3350h() {
        C1593g.m3188a(SDKConstants.TAG, "recycleLoadAd");
        if (this.f3532b.getParent() == null) {
            C1593g.m3188a(SDKConstants.TAG, "There isn't valid parent widget!");
        } else if (C1587a.c.end != this.f3542l.m3361a()) {
            C1593g.m3188a(SDKConstants.TAG, "Ad is requesting, can't recycle load ad!");
        } else if (m3348f()) {
            this.f3542l.m3364a(this.f3542l.obtainMessage(1), m3347e());
        } else {
            this.f3556t = false;
        }
    }

    @Override // com.iflytek.voiceads.view.AdView
    /* JADX INFO: renamed from: k */
    public void mo3353k() {
        int iMin = Math.min(C1573d.m3094c(this.f3557u), C1573d.m3096d(this.f3557u));
        int iM3081b = (this.f3535e.m3081b() * iMin) / this.f3535e.m3077a();
        this.f3532b.setGravity(17);
        m3335a(iMin, iM3081b);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
