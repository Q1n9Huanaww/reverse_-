package com.cyjh.share.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

/* JADX INFO: loaded from: classes.dex */
public class GenerateProcessButton extends ProcessButton {
    public GenerateProcessButton(Context context) {
        super(context);
    }

    public GenerateProcessButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public GenerateProcessButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.cyjh.share.view.ProcessButton
    public void drawProgress(Canvas canvas) {
        getProgressDrawable().setBounds(0, 0, getMeasuredWidth(), (int) (getMeasuredHeight() * (getProgress() / getMaxProgress())));
        getProgressDrawable().draw(canvas);
    }
}
