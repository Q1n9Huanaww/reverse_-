package com.cyjh.share.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import com.cyjh.share.C0854R;

/* JADX INFO: loaded from: classes.dex */
public class ActionProcessButton extends ProcessButton {
    private int mColor1;
    private int mColor2;
    private int mColor3;
    private int mColor4;
    private Mode mMode;
    private ProgressBar mProgressBar;

    public enum Mode {
        PROGRESS,
        ENDLESS
    }

    public ActionProcessButton(Context context) {
        super(context);
        init(context);
    }

    public ActionProcessButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public ActionProcessButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        Resources resources = context.getResources();
        this.mMode = Mode.ENDLESS;
        this.mColor1 = resources.getColor(C0854R.color.holo_blue_bright);
        this.mColor2 = resources.getColor(C0854R.color.holo_green_light);
        this.mColor3 = resources.getColor(C0854R.color.holo_orange_light);
        this.mColor4 = resources.getColor(C0854R.color.holo_red_light);
    }

    public void setMode(Mode mode) {
        this.mMode = mode;
    }

    public void setColorScheme(int i, int i2, int i3, int i4) {
        this.mColor1 = i;
        this.mColor2 = i2;
        this.mColor3 = i3;
        this.mColor4 = i4;
    }

    @Override // com.cyjh.share.view.ProcessButton
    public void drawProgress(Canvas canvas) {
        if (getBackground() != getNormalDrawable()) {
            setBackgroundDrawable(getNormalDrawable());
        }
        switch (this.mMode) {
            case ENDLESS:
                drawEndlessProgress(canvas);
                break;
            case PROGRESS:
                drawLineProgress(canvas);
                break;
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.mProgressBar != null) {
            setupProgressBarBounds();
        }
    }

    private void drawLineProgress(Canvas canvas) {
        getProgressDrawable().setBounds(0, (int) (((double) getMeasuredHeight()) - (((double) getMeasuredHeight()) * 0.05d)), (int) (getMeasuredWidth() * (getProgress() / getMaxProgress())), getMeasuredHeight());
        getProgressDrawable().draw(canvas);
    }

    private void drawEndlessProgress(Canvas canvas) {
        if (this.mProgressBar == null) {
            this.mProgressBar = new ProgressBar(this);
            setupProgressBarBounds();
            this.mProgressBar.setColorScheme(this.mColor1, this.mColor2, this.mColor3, this.mColor4);
            this.mProgressBar.start();
        }
        if (getProgress() > 0) {
            this.mProgressBar.draw(canvas);
        }
    }

    private void setupProgressBarBounds() {
        this.mProgressBar.setBounds(0, (int) (((double) getMeasuredHeight()) - ((double) getDimension(C0854R.dimen.layer_padding))), getMeasuredWidth(), getMeasuredHeight());
    }

    public static class ProgressBar {
        private static final int ANIMATION_DURATION_MS = 2000;
        private static final int COLOR1 = -1291845632;
        private static final int COLOR2 = Integer.MIN_VALUE;
        private static final int COLOR3 = 1291845632;
        private static final int COLOR4 = 436207616;
        private static final int FINISH_ANIMATION_DURATION_MS = 1000;
        private static final Interpolator INTERPOLATOR = new AccelerateDecelerateInterpolator();
        private long mFinishTime;
        private View mParent;
        private boolean mRunning;
        private long mStartTime;
        private float mTriggerPercentage;
        private final Paint mPaint = new Paint();
        private final RectF mClipRect = new RectF();
        private Rect mBounds = new Rect();
        private int mColor1 = COLOR1;
        private int mColor2 = Integer.MIN_VALUE;
        private int mColor3 = COLOR3;
        private int mColor4 = COLOR4;

        public ProgressBar(View view) {
            this.mParent = view;
        }

        void setColorScheme(int i, int i2, int i3, int i4) {
            this.mColor1 = i;
            this.mColor2 = i2;
            this.mColor3 = i3;
            this.mColor4 = i4;
        }

        void start() {
            if (this.mRunning) {
                return;
            }
            this.mTriggerPercentage = 0.0f;
            this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
            this.mRunning = true;
            this.mParent.postInvalidate();
        }

        void draw(Canvas canvas) {
            long j;
            boolean z;
            int iWidth = this.mBounds.width();
            int iHeight = this.mBounds.height();
            int i = iWidth / 2;
            int i2 = iHeight / 2;
            int iSave = canvas.save();
            canvas.clipRect(this.mBounds);
            if (this.mRunning || this.mFinishTime > 0) {
                long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
                long j2 = (jCurrentAnimationTimeMillis - this.mStartTime) % 2000;
                long j3 = (jCurrentAnimationTimeMillis - this.mStartTime) / 2000;
                float f = j2 / 20.0f;
                if (this.mRunning) {
                    j = 0;
                    z = false;
                } else {
                    if (jCurrentAnimationTimeMillis - this.mFinishTime >= 1000) {
                        this.mFinishTime = 0L;
                        return;
                    }
                    float f2 = i;
                    float interpolation = INTERPOLATOR.getInterpolation((((jCurrentAnimationTimeMillis - this.mFinishTime) % 1000) / 10.0f) / 100.0f) * f2;
                    this.mClipRect.set(f2 - interpolation, 0.0f, f2 + interpolation, iHeight);
                    canvas.saveLayerAlpha(this.mClipRect, 0, 0);
                    j = 0;
                    z = true;
                }
                if (j3 == j) {
                    canvas.drawColor(this.mColor1);
                } else if (f >= 0.0f && f < 25.0f) {
                    canvas.drawColor(this.mColor4);
                } else if (f >= 25.0f && f < 50.0f) {
                    canvas.drawColor(this.mColor1);
                } else if (f >= 50.0f && f < 75.0f) {
                    canvas.drawColor(this.mColor2);
                } else {
                    canvas.drawColor(this.mColor3);
                }
                if (f >= 0.0f && f <= 25.0f) {
                    drawCircle(canvas, i, i2, this.mColor1, ((f + 25.0f) * 2.0f) / 100.0f);
                }
                if (f >= 0.0f && f <= 50.0f) {
                    drawCircle(canvas, i, i2, this.mColor2, (f * 2.0f) / 100.0f);
                }
                if (f >= 25.0f && f <= 75.0f) {
                    drawCircle(canvas, i, i2, this.mColor3, ((f - 25.0f) * 2.0f) / 100.0f);
                }
                if (f >= 50.0f && f <= 100.0f) {
                    drawCircle(canvas, i, i2, this.mColor4, ((f - 50.0f) * 2.0f) / 100.0f);
                }
                if (f >= 75.0f && f <= 100.0f) {
                    drawCircle(canvas, i, i2, this.mColor1, ((f - 75.0f) * 2.0f) / 100.0f);
                }
                if (this.mTriggerPercentage > 0.0f && z) {
                    canvas.restoreToCount(iSave);
                    int iSave2 = canvas.save();
                    canvas.clipRect(this.mBounds);
                    drawTrigger(canvas, i, i2);
                    iSave = iSave2;
                }
                ViewCompat.postInvalidateOnAnimation(this.mParent);
            } else if (this.mTriggerPercentage > 0.0f && this.mTriggerPercentage <= 1.0d) {
                drawTrigger(canvas, i, i2);
            }
            canvas.restoreToCount(iSave);
        }

        private void drawTrigger(Canvas canvas, int i, int i2) {
            this.mPaint.setColor(this.mColor1);
            float f = i;
            canvas.drawCircle(f, i2, this.mTriggerPercentage * f, this.mPaint);
        }

        private void drawCircle(Canvas canvas, float f, float f2, int i, float f3) {
            this.mPaint.setColor(i);
            canvas.save();
            canvas.translate(f, f2);
            float interpolation = INTERPOLATOR.getInterpolation(f3);
            canvas.scale(interpolation, interpolation);
            canvas.drawCircle(0.0f, 0.0f, f, this.mPaint);
            canvas.restore();
        }

        void setBounds(int i, int i2, int i3, int i4) {
            this.mBounds.left = i;
            this.mBounds.top = i2;
            this.mBounds.right = i3;
            this.mBounds.bottom = i4;
        }
    }
}
