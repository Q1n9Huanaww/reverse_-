package com.iflytek.voiceads.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.listener.InternalListener;
import com.iflytek.voiceads.p063b.C1492a;
import com.iflytek.voiceads.param.C1573d;
import com.iflytek.voiceads.utils.C1587a;
import com.iflytek.voiceads.utils.C1593g;

/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class InterstitialAdView extends AdView {

    /* JADX INFO: renamed from: t */
    private Context f3559t;

    /* JADX INFO: renamed from: u */
    private final String f3560u;

    public InterstitialAdView(Context context) {
        super(context);
        this.f3560u = "close_widget";
    }

    public InterstitialAdView(Context context, RelativeLayout relativeLayout, String str, InternalListener internalListener) {
        super(context, relativeLayout, str, C1587a.a.INTERSTITIAL, internalListener);
        this.f3560u = "close_widget";
        this.f3559t = context.getApplicationContext();
    }

    /* JADX INFO: renamed from: a */
    private void m3356a(ImageView imageView) {
        new C1492a(this.f3559t.getApplicationContext(), this.f3537g.f3139I).m2838a(new C1630g(this, imageView));
    }

    @Override // com.iflytek.voiceads.view.AdView
    /* JADX INFO: renamed from: j */
    public void mo3352j() {
        if (this.f3537g == null || TextUtils.isEmpty(this.f3537g.f3139I)) {
            return;
        }
        ViewGroup viewGroup = (ViewGroup) getParent();
        if (viewGroup == null) {
            C1593g.m3188a(SDKConstants.TAG, "adview parent is null");
            return;
        }
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            if ("close_widget".equals(viewGroup.getChildAt(i).getTag())) {
                return;
            }
        }
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float f = displayMetrics.density;
        int iMin = Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) - 40;
        int i2 = (int) (25.0f * f);
        int i3 = (int) (0.3d * ((double) i2));
        ImageView imageView = new ImageView(this.f3559t);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i2, (int) (f * 25.0f));
        layoutParams.topMargin = i3;
        layoutParams.leftMargin = (iMin - i2) - i3;
        imageView.setTag("close_widget");
        viewGroup.addView(imageView, layoutParams);
        m3356a(imageView);
        imageView.setOnClickListener(new ViewOnClickListenerC1629f(this));
    }

    @Override // com.iflytek.voiceads.view.AdView
    /* JADX INFO: renamed from: k */
    public void mo3353k() {
        int iMin = Math.min(C1573d.m3094c(this.f3559t), C1573d.m3096d(this.f3559t)) - 40;
        int iM3081b = (this.f3535e.m3081b() * iMin) / this.f3535e.m3077a();
        C1593g.m3188a(SDKConstants.TAG, "InterstitialAdView adWidth=" + iMin + ", adHeight=" + iM3081b);
        this.f3532b.setGravity(17);
        m3335a(iMin, iM3081b);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
