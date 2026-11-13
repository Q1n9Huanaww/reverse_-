package com.cyjh.share.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.GradientDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import com.cyjh.share.C0854R;

/* JADX INFO: loaded from: classes.dex */
public abstract class ProcessButton extends FlatButton {
    private GradientDrawable mCompleteDrawable;
    private CharSequence mCompleteText;
    private GradientDrawable mErrorDrawable;
    private CharSequence mErrorText;
    private CharSequence mLoadingText;
    private int mMaxProgress;
    private int mMinProgress;
    private int mProgress;
    private GradientDrawable mProgressDrawable;

    public abstract void drawProgress(Canvas canvas);

    public ProcessButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    public ProcessButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public ProcessButton(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attributeSet) {
        this.mMinProgress = 0;
        this.mMaxProgress = 100;
        this.mProgressDrawable = (GradientDrawable) getDrawable(C0854R.drawable.rect_progress).mutate();
        this.mProgressDrawable.setCornerRadius(getCornerRadius());
        this.mCompleteDrawable = (GradientDrawable) getDrawable(C0854R.drawable.rect_complete).mutate();
        this.mCompleteDrawable.setCornerRadius(getCornerRadius());
        this.mErrorDrawable = (GradientDrawable) getDrawable(C0854R.drawable.rect_error).mutate();
        this.mErrorDrawable.setCornerRadius(getCornerRadius());
        if (attributeSet != null) {
            initAttributes(context, attributeSet);
        }
    }

    private void initAttributes(Context context, AttributeSet attributeSet) {
        TypedArray typedArray = getTypedArray(context, attributeSet, C0854R.styleable.ProcessButton);
        if (typedArray == null) {
            return;
        }
        try {
            this.mLoadingText = typedArray.getString(C0854R.styleable.ProcessButton_pb_textProgress);
            this.mCompleteText = typedArray.getString(C0854R.styleable.ProcessButton_pb_textComplete);
            this.mErrorText = typedArray.getString(C0854R.styleable.ProcessButton_pb_textError);
            this.mProgressDrawable.setColor(typedArray.getColor(C0854R.styleable.ProcessButton_pb_colorProgress, getColor(C0854R.color.purple_progress)));
            this.mCompleteDrawable.setColor(typedArray.getColor(C0854R.styleable.ProcessButton_pb_colorComplete, getColor(C0854R.color.green_complete)));
            this.mErrorDrawable.setColor(typedArray.getColor(C0854R.styleable.ProcessButton_pb_colorError, getColor(C0854R.color.red_error)));
        } finally {
            typedArray.recycle();
        }
    }

    public void setProgress(int i) {
        this.mProgress = i;
        if (this.mProgress == this.mMinProgress) {
            onNormalState();
        } else if (this.mProgress == this.mMaxProgress) {
            onCompleteState();
        } else if (this.mProgress < this.mMinProgress) {
            onErrorState();
        } else {
            onProgress();
        }
        invalidate();
    }

    protected void onErrorState() {
        if (getErrorText() != null) {
            setText(getErrorText());
        }
        setBackgroundCompat(getErrorDrawable());
    }

    protected void onProgress() {
        if (getLoadingText() != null) {
            setText(getLoadingText());
        }
        setBackgroundCompat(getNormalDrawable());
    }

    protected void onCompleteState() {
        if (getCompleteText() != null) {
            setText(getCompleteText());
        }
        setBackgroundCompat(getCompleteDrawable());
    }

    protected void onNormalState() {
        if (getNormalText() != null) {
            setText(getNormalText());
        }
        setBackgroundCompat(getNormalDrawable());
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mProgress > this.mMinProgress && this.mProgress < this.mMaxProgress) {
            drawProgress(canvas);
        }
        super.onDraw(canvas);
    }

    public int getProgress() {
        return this.mProgress;
    }

    public int getMaxProgress() {
        return this.mMaxProgress;
    }

    public int getMinProgress() {
        return this.mMinProgress;
    }

    public GradientDrawable getProgressDrawable() {
        return this.mProgressDrawable;
    }

    public GradientDrawable getCompleteDrawable() {
        return this.mCompleteDrawable;
    }

    public CharSequence getLoadingText() {
        return this.mLoadingText;
    }

    public CharSequence getCompleteText() {
        return this.mCompleteText;
    }

    public void setProgressDrawable(GradientDrawable gradientDrawable) {
        this.mProgressDrawable = gradientDrawable;
    }

    public void setCompleteDrawable(GradientDrawable gradientDrawable) {
        this.mCompleteDrawable = gradientDrawable;
    }

    public void setLoadingText(CharSequence charSequence) {
        this.mLoadingText = charSequence;
    }

    public void setCompleteText(CharSequence charSequence) {
        this.mCompleteText = charSequence;
    }

    public GradientDrawable getErrorDrawable() {
        return this.mErrorDrawable;
    }

    public void setErrorDrawable(GradientDrawable gradientDrawable) {
        this.mErrorDrawable = gradientDrawable;
    }

    public CharSequence getErrorText() {
        return this.mErrorText;
    }

    public void setErrorText(CharSequence charSequence) {
        this.mErrorText = charSequence;
    }

    @Override // android.widget.TextView, android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.mProgress = this.mProgress;
        return savedState;
    }

    @Override // android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.mProgress = savedState.mProgress;
            super.onRestoreInstanceState(savedState.getSuperState());
            setProgress(this.mProgress);
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.cyjh.share.view.ProcessButton.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        private int mProgress;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.mProgress = parcel.readInt();
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.mProgress);
        }
    }
}
