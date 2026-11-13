package com.cyjh.share.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.cyjh.share.C0854R;

/* JADX INFO: loaded from: classes.dex */
public class SLEditTextView extends AppCompatEditText implements View.OnFocusChangeListener, TextWatcher {
    private static final int DRAWABLE_BOTTOM = 3;
    private static final int DRAWABLE_LEFT = 0;
    private static final int DRAWABLE_RIGHT = 2;
    private static final int DRAWABLE_TOP = 1;
    private int color;
    private boolean hasFoucs;
    private Drawable mClearDrawable;
    private Context mContext;
    private Drawable mLeftDrawable;
    private Paint mPaint;

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public SLEditTextView(Context context) {
        super(context);
        init(context);
    }

    public SLEditTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public SLEditTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        setDrawable(context);
        setOnFocusChangeListener(this);
        addTextChangedListener(this);
        updateDrawable(true);
    }

    private void drawLine() {
        this.mPaint = new Paint();
        this.mPaint.setStrokeWidth(3.0f);
        this.color = Color.parseColor("#a9b7b7");
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void setDrawable(Context context) {
        this.mClearDrawable = getCompoundDrawables()[2];
        if (this.mClearDrawable == null) {
            this.mClearDrawable = getResources().getDrawable(C0854R.drawable.icon_clear_edit);
        }
        this.mClearDrawable.setBounds(0, 0, dip2px(context, 25.0f), dip2px(context, 25.0f));
        this.mLeftDrawable = getCompoundDrawables()[0];
        if (this.mLeftDrawable != null) {
            this.mLeftDrawable.setBounds(0, 0, dip2px(context, 28.0f), dip2px(context, 28.0f));
        }
    }

    private void updateDrawable(boolean z) {
        if (length() > 0 && z) {
            setCompoundDrawables(this.mLeftDrawable, getCompoundDrawables()[1], this.mClearDrawable, getCompoundDrawables()[3]);
        } else {
            setCompoundDrawables(this.mLeftDrawable, getCompoundDrawables()[1], null, getCompoundDrawables()[3]);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (getCompoundDrawables()[2] != null && motionEvent.getAction() == 1 && getWidth() - getCompoundPaddingRight() < motionEvent.getX() && getWidth() - getPaddingRight() > motionEvent.getX()) {
            setText("");
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z) {
        this.hasFoucs = z;
        updateDrawable(z);
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        updateDrawable(true);
    }

    @Override // android.widget.TextView, android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
    }

    protected void finalize() throws Throwable {
        this.mClearDrawable = null;
        super.finalize();
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public void clearDeleteIcon() {
        setCompoundDrawables(this.mLeftDrawable, getCompoundDrawables()[1], null, getCompoundDrawables()[3]);
    }
}
