package com.cyjh.share.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import org.slf4j.Marker;

/* JADX INFO: loaded from: classes.dex */
public class PassWordView extends View {
    private boolean isDrawText;
    private boolean isInputState;
    private boolean isShowRemindLine;
    private int mBoxDrawType;
    private Context mContext;
    private int mDrawBoxLineSize;
    private boolean mDrawRemindLineState;
    private String mDrawTxtContent;
    private int mDrawTxtSize;
    private Handler mHandler;
    private int mInputStateBoxColor;
    private int mInputStateTextColor;
    private int mNoInputStateBoxColor;
    private Paint mPaint;
    private String mPassText;
    private int mRemindLineColor;
    private int mShowPassType;
    private int mWidth;
    private int mheight;

    public void setInputStateColor(int i) {
        this.mInputStateBoxColor = i;
    }

    public void setmPassText(String str) {
        this.mPassText = str;
    }

    public void setNoinputColor(int i) {
        this.mNoInputStateBoxColor = i;
    }

    public void setInputState(boolean z) {
        this.isInputState = z;
    }

    public void setRemindLineColor(int i) {
        this.mRemindLineColor = i;
    }

    public void setInputStateTextColor(int i) {
        this.mInputStateTextColor = i;
    }

    public PassWordView(Context context) {
        this(context, null);
    }

    public PassWordView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void setmBoxDrawType(int i) {
        this.mBoxDrawType = i;
    }

    public void setmShowPassType(int i) {
        this.mShowPassType = i;
    }

    public void setmDrawTxtSize(int i) {
        this.mDrawTxtSize = i;
    }

    public void setDrawTxtContent(String str) {
        this.mDrawTxtContent = str;
    }

    public void setmIsShowRemindLine(boolean z) {
        this.isShowRemindLine = z;
    }

    public void setmDrawBoxLineSize(int i) {
        this.mDrawBoxLineSize = i;
    }

    public PassWordView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isInputState = false;
        this.mBoxDrawType = 0;
        this.mShowPassType = 0;
        this.isShowRemindLine = true;
        this.mWidth = 40;
        this.mheight = 40;
        this.mPassText = "";
        this.mDrawTxtContent = Marker.ANY_MARKER;
        this.mDrawTxtSize = 18;
        this.mDrawBoxLineSize = 4;
        this.mContext = context;
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStrokeWidth(this.mDrawBoxLineSize);
        this.mPaint.setPathEffect(new CornerPathEffect(1.0f));
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode != 1073741824) {
            int i3 = this.mWidth;
            size = mode == Integer.MIN_VALUE ? Math.min(i3, size) : i3;
        }
        if (mode2 != 1073741824) {
            int i4 = this.mheight;
            size2 = mode2 == Integer.MIN_VALUE ? Math.min(i4, size2) : i4;
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        drawInputBox(canvas);
        drawRemindLine(canvas);
        drawInputTextOrPicture(canvas);
    }

    private void drawInputTextOrPicture(Canvas canvas) {
        if (this.isDrawText) {
            this.mPaint.setColor(ContextCompat.getColor(this.mContext, this.mInputStateTextColor));
            this.mPaint.setStyle(Paint.Style.FILL);
            switch (this.mShowPassType) {
                case 0:
                    canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, 8.0f, this.mPaint);
                    break;
                case 1:
                    this.mPaint.setTextSize((getMeasuredWidth() / 2) + 10);
                    float fMeasureText = this.mPaint.measureText(this.mDrawTxtContent);
                    canvas.drawText(this.mDrawTxtContent, (getMeasuredWidth() / 2) - (fMeasureText / 2.0f), ((getMeasuredHeight() / 2) - ((this.mPaint.descent() + this.mPaint.ascent()) / 2.0f)) + (fMeasureText / 3.0f), this.mPaint);
                    break;
                case 2:
                    this.mPaint.setTextSize(this.mDrawTxtSize);
                    float fMeasureText2 = this.mPaint.measureText(this.mPassText);
                    canvas.drawText(this.mPassText, (getMeasuredWidth() / 2) - (fMeasureText2 / 2.0f), ((getMeasuredHeight() / 2) - ((this.mPaint.descent() + this.mPaint.ascent()) / 2.0f)) + (fMeasureText2 / 5.0f), this.mPaint);
                    break;
            }
        }
    }

    private void drawRemindLine(Canvas canvas) {
        if (this.mDrawRemindLineState && this.isShowRemindLine) {
            int measuredWidth = (getMeasuredWidth() / 2) - 10;
            if (measuredWidth < 0) {
                measuredWidth = getMeasuredWidth() / 2;
            }
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setColor(ContextCompat.getColor(this.mContext, this.mRemindLineColor));
            int i = measuredWidth / 2;
            canvas.drawLine(getMeasuredWidth() / 2, (getMeasuredHeight() / 2) - i, getMeasuredWidth() / 2, (getMeasuredHeight() / 2) + i, this.mPaint);
        }
    }

    private void drawInputBox(Canvas canvas) {
        if (this.isInputState) {
            this.mPaint.setColor(ContextCompat.getColor(this.mContext, this.mInputStateBoxColor));
        } else {
            this.mPaint.setColor(ContextCompat.getColor(this.mContext, this.mNoInputStateBoxColor));
        }
        this.mPaint.setStyle(Paint.Style.STROKE);
        switch (this.mBoxDrawType) {
            case 1:
                canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, (getMeasuredWidth() / 2) - 5, this.mPaint);
                break;
            case 2:
                canvas.drawLine(0.0f, getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight(), this.mPaint);
                break;
            default:
                canvas.drawRoundRect(new RectF(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight()), 6.0f, 6.0f, this.mPaint);
                break;
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mHandler != null) {
            this.mHandler.removeCallbacksAndMessages(null);
        }
    }

    public void updateInputState(boolean z) {
        if (this.mHandler != null) {
            this.mHandler.removeCallbacksAndMessages(null);
        }
        if (z) {
            this.isInputState = true;
            this.isDrawText = true;
        } else {
            this.isInputState = false;
            this.isDrawText = false;
        }
        this.mDrawRemindLineState = false;
        invalidate();
    }

    public void startInputState() {
        this.isInputState = true;
        this.isDrawText = false;
        if (this.mHandler == null) {
            this.mHandler = new Handler();
        }
        this.mHandler.removeCallbacksAndMessages(null);
        if (!this.isShowRemindLine) {
            invalidate();
        } else {
            this.mHandler.post(new Runnable() { // from class: com.cyjh.share.view.PassWordView.1
                @Override // java.lang.Runnable
                public void run() {
                    PassWordView.this.mDrawRemindLineState = !PassWordView.this.mDrawRemindLineState;
                    PassWordView.this.invalidate();
                    PassWordView.this.mHandler.postDelayed(this, 800L);
                }
            });
        }
    }
}
