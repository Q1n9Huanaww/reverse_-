package com.cyjh.share.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.cyjh.share.C0854R;

/* JADX INFO: loaded from: classes.dex */
public class SlLoadingView extends ImageView {
    protected Animation rotateAnimation;

    public SlLoadingView(Context context) {
        super(context);
        init();
    }

    public SlLoadingView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public SlLoadingView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this.rotateAnimation = AnimationUtils.loadAnimation(getContext(), C0854R.anim.downing_animation);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setAnimation(this.rotateAnimation);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        clearAnimation();
    }
}
