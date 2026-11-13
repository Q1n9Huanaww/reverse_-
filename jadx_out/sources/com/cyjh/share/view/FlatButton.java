package com.cyjh.share.view;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;
import com.cyjh.share.C0854R;

/* JADX INFO: loaded from: classes.dex */
public class FlatButton extends Button {
    private float cornerRadius;
    private StateListDrawable mNormalDrawable;
    private CharSequence mNormalText;

    public FlatButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    public FlatButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public FlatButton(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attributeSet) {
        this.mNormalDrawable = new StateListDrawable();
        if (attributeSet != null) {
            initAttributes(context, attributeSet);
        }
        this.mNormalText = getText().toString();
        setBackgroundCompat(this.mNormalDrawable);
    }

    private void initAttributes(Context context, AttributeSet attributeSet) {
        TypedArray typedArray = getTypedArray(context, attributeSet, C0854R.styleable.FlatButton);
        if (typedArray == null) {
            return;
        }
        try {
            this.cornerRadius = typedArray.getDimension(C0854R.styleable.FlatButton_pb_cornerRadius, getDimension(C0854R.dimen.corner_radius));
            this.mNormalDrawable.addState(new int[]{R.attr.state_pressed}, createPressedDrawable(typedArray));
            this.mNormalDrawable.addState(new int[]{R.attr.state_focused}, createPressedDrawable(typedArray));
            this.mNormalDrawable.addState(new int[]{R.attr.state_selected}, createPressedDrawable(typedArray));
            this.mNormalDrawable.addState(new int[0], createNormalDrawable(typedArray));
        } finally {
            typedArray.recycle();
        }
    }

    private LayerDrawable createNormalDrawable(TypedArray typedArray) {
        LayerDrawable layerDrawable = (LayerDrawable) getDrawable(C0854R.drawable.rect_normal).mutate();
        GradientDrawable gradientDrawable = (GradientDrawable) layerDrawable.getDrawable(0).mutate();
        gradientDrawable.setCornerRadius(getCornerRadius());
        gradientDrawable.setColor(typedArray.getColor(C0854R.styleable.FlatButton_pb_colorPressed, getColor(C0854R.color.blue_pressed)));
        GradientDrawable gradientDrawable2 = (GradientDrawable) layerDrawable.getDrawable(1).mutate();
        gradientDrawable2.setCornerRadius(getCornerRadius());
        gradientDrawable2.setColor(typedArray.getColor(C0854R.styleable.FlatButton_pb_colorNormal, getColor(C0854R.color.blue_normal)));
        return layerDrawable;
    }

    private Drawable createPressedDrawable(TypedArray typedArray) {
        GradientDrawable gradientDrawable = (GradientDrawable) getDrawable(C0854R.drawable.rect_pressed).mutate();
        gradientDrawable.setCornerRadius(getCornerRadius());
        gradientDrawable.setColor(typedArray.getColor(C0854R.styleable.FlatButton_pb_colorPressed, getColor(C0854R.color.blue_pressed)));
        return gradientDrawable;
    }

    protected Drawable getDrawable(int i) {
        return getResources().getDrawable(i);
    }

    protected float getDimension(int i) {
        return getResources().getDimension(i);
    }

    protected int getColor(int i) {
        return getResources().getColor(i);
    }

    protected TypedArray getTypedArray(Context context, AttributeSet attributeSet, int[] iArr) {
        return context.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }

    public float getCornerRadius() {
        return this.cornerRadius;
    }

    public StateListDrawable getNormalDrawable() {
        return this.mNormalDrawable;
    }

    public CharSequence getNormalText() {
        return this.mNormalText;
    }

    @SuppressLint({"NewApi"})
    public void setBackgroundCompat(Drawable drawable) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        if (Build.VERSION.SDK_INT >= 16) {
            setBackground(drawable);
        } else {
            setBackgroundDrawable(drawable);
        }
        setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }
}
