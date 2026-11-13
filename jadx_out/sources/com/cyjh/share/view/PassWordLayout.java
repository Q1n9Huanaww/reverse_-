package com.cyjh.share.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import com.cyjh.share.C0854R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PassWordLayout extends LinearLayout {
    private int inputIndex;
    private int mBoxLineSize;
    private Context mContext;
    private int mDrawType;
    private int mInputColor;
    private int mInterval;
    private boolean mIsShowInputLine;
    private int mItemHeight;
    private int mItemWidth;
    private int mLineColor;
    private int mNoinputColor;
    private List<String> mPassList;
    private int mShowPassType;
    private int mTxtInputColor;
    private int mTxtSize;
    private int maxLength;
    private pwdChangeListener pwdChangeListener;

    public interface pwdChangeListener {
        void onChange(String str);

        void onFinished(String str);

        void onNull();
    }

    public void setPwdChangeListener(pwdChangeListener pwdchangelistener) {
        this.pwdChangeListener = pwdchangelistener;
    }

    public PassWordLayout(Context context) {
        this(context, null);
    }

    public PassWordLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PassWordLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.maxLength = 6;
        this.inputIndex = 0;
        initView(context, attributeSet);
    }

    private void initView(Context context, AttributeSet attributeSet) {
        this.mContext = context;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0854R.styleable.PassWordLayoutStyle);
        this.mInputColor = typedArrayObtainStyledAttributes.getResourceId(C0854R.styleable.PassWordLayoutStyle_box_input_color, C0854R.color.pass_view_rect_input);
        this.mNoinputColor = typedArrayObtainStyledAttributes.getResourceId(C0854R.styleable.PassWordLayoutStyle_box_no_input_color, C0854R.color.regi_line_color);
        this.mLineColor = typedArrayObtainStyledAttributes.getResourceId(C0854R.styleable.PassWordLayoutStyle_input_line_color, C0854R.color.pass_view_rect_input);
        this.mTxtInputColor = typedArrayObtainStyledAttributes.getResourceId(C0854R.styleable.PassWordLayoutStyle_text_input_color, C0854R.color.pass_view_rect_input);
        this.mDrawType = typedArrayObtainStyledAttributes.getInt(C0854R.styleable.PassWordLayoutStyle_box_draw_type, 0);
        this.mInterval = typedArrayObtainStyledAttributes.getDimensionPixelOffset(C0854R.styleable.PassWordLayoutStyle_interval_width, 4);
        this.maxLength = typedArrayObtainStyledAttributes.getInt(C0854R.styleable.PassWordLayoutStyle_pass_leng, 6);
        this.mItemWidth = typedArrayObtainStyledAttributes.getDimensionPixelOffset(C0854R.styleable.PassWordLayoutStyle_item_width, 40);
        this.mItemHeight = typedArrayObtainStyledAttributes.getDimensionPixelOffset(C0854R.styleable.PassWordLayoutStyle_item_height, 40);
        this.mShowPassType = typedArrayObtainStyledAttributes.getInt(C0854R.styleable.PassWordLayoutStyle_pass_inputed_type, 0);
        this.mTxtSize = typedArrayObtainStyledAttributes.getDimensionPixelOffset(C0854R.styleable.PassWordLayoutStyle_draw_txt_size, 18);
        this.mBoxLineSize = typedArrayObtainStyledAttributes.getDimensionPixelOffset(C0854R.styleable.PassWordLayoutStyle_draw_box_line_size, 4);
        this.mIsShowInputLine = typedArrayObtainStyledAttributes.getBoolean(C0854R.styleable.PassWordLayoutStyle_is_show_input_line, true);
        typedArrayObtainStyledAttributes.recycle();
        this.mPassList = new ArrayList();
        setOrientation(0);
        setGravity(17);
        setOnClickListener(new View.OnClickListener() { // from class: com.cyjh.share.view.PassWordLayout.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PassWordLayout.this.setFocusable(true);
                PassWordLayout.this.setFocusableInTouchMode(true);
                PassWordLayout.this.requestFocus();
                ((InputMethodManager) PassWordLayout.this.getContext().getSystemService("input_method")).showSoftInput(PassWordLayout.this, 1);
            }
        });
        setOnKeyListener(new MyKeyListener());
        setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.cyjh.share.view.PassWordLayout.2
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    PassWordView passWordView = (PassWordView) PassWordLayout.this.getChildAt(PassWordLayout.this.inputIndex);
                    if (passWordView != null) {
                        passWordView.setmIsShowRemindLine(PassWordLayout.this.mIsShowInputLine);
                        passWordView.startInputState();
                        return;
                    }
                    return;
                }
                PassWordView passWordView2 = (PassWordView) PassWordLayout.this.getChildAt(PassWordLayout.this.inputIndex);
                if (passWordView2 != null) {
                    passWordView2.setmIsShowRemindLine(false);
                    passWordView2.updateInputState(false);
                }
            }
        });
    }

    private void addChildVIews(Context context) {
        for (int i = 0; i < this.maxLength; i++) {
            PassWordView passWordView = new PassWordView(context);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.mItemWidth, this.mItemHeight);
            if (i > 0) {
                layoutParams.leftMargin = this.mInterval;
            }
            passWordView.setInputStateColor(this.mInputColor);
            passWordView.setNoinputColor(this.mNoinputColor);
            passWordView.setInputStateTextColor(this.mTxtInputColor);
            passWordView.setRemindLineColor(this.mLineColor);
            passWordView.setmBoxDrawType(this.mDrawType);
            passWordView.setmShowPassType(this.mShowPassType);
            passWordView.setmDrawTxtSize(this.mTxtSize);
            passWordView.setmDrawBoxLineSize(this.mBoxLineSize);
            passWordView.setmIsShowRemindLine(this.mIsShowInputLine);
            addView(passWordView, layoutParams);
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (getChildCount() == 0) {
            if ((this.maxLength * this.mItemWidth) + ((this.maxLength - 1) * this.mInterval) > getMeasuredWidth()) {
                this.mItemWidth = (getMeasuredWidth() - ((this.maxLength - 1) * this.mInterval)) / this.maxLength;
                this.mItemHeight = this.mItemWidth;
            }
            addChildVIews(getContext());
        }
    }

    public void addPwd(String str) {
        if (this.mPassList != null && this.mPassList.size() < this.maxLength) {
            this.mPassList.add(str + "");
            setNextInput(str);
        }
        if (this.pwdChangeListener != null) {
            if (this.mPassList.size() < this.maxLength) {
                this.pwdChangeListener.onChange(getPassString());
            } else {
                this.pwdChangeListener.onFinished(getPassString());
            }
        }
    }

    public void removePwd() {
        if (this.mPassList != null && this.mPassList.size() > 0) {
            this.mPassList.remove(this.mPassList.size() - 1);
            setPreviosInput();
        }
        if (this.pwdChangeListener != null) {
            if (this.mPassList.size() > 0) {
                this.pwdChangeListener.onChange(getPassString());
            } else {
                this.pwdChangeListener.onNull();
            }
        }
    }

    public void removeAllPwd() {
        PassWordView passWordView;
        if (this.mPassList != null) {
            for (int size = this.mPassList.size(); size >= 0; size--) {
                if (size > 0) {
                    setNoInput(size, false, "");
                } else if (size == 0 && (passWordView = (PassWordView) getChildAt(size)) != null) {
                    passWordView.setmPassText("");
                    passWordView.startInputState();
                }
            }
            this.mPassList.clear();
            this.inputIndex = 0;
        }
        if (this.pwdChangeListener != null) {
            this.pwdChangeListener.onNull();
        }
    }

    public String getPassString() {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator<String> it2 = this.mPassList.iterator();
        while (it2.hasNext()) {
            stringBuffer.append(it2.next());
        }
        return stringBuffer.toString();
    }

    private void setNextInput(String str) {
        if (this.inputIndex < this.maxLength) {
            setNoInput(this.inputIndex, true, str);
            this.inputIndex++;
            PassWordView passWordView = (PassWordView) getChildAt(this.inputIndex);
            if (passWordView != null) {
                passWordView.setmPassText(str + "");
                passWordView.startInputState();
            }
        }
    }

    private void setPreviosInput() {
        PassWordView passWordView;
        if (this.inputIndex > 0) {
            setNoInput(this.inputIndex, false, "");
            this.inputIndex--;
            PassWordView passWordView2 = (PassWordView) getChildAt(this.inputIndex);
            if (passWordView2 != null) {
                passWordView2.setmPassText("");
                passWordView2.startInputState();
                return;
            }
            return;
        }
        if (this.inputIndex != 0 || (passWordView = (PassWordView) getChildAt(this.inputIndex)) == null) {
            return;
        }
        passWordView.setmPassText("");
        passWordView.startInputState();
    }

    public void setNoInput(int i, boolean z, String str) {
        PassWordView passWordView;
        if (i >= 0 && (passWordView = (PassWordView) getChildAt(i)) != null) {
            passWordView.setmPassText(str);
            passWordView.updateInputState(z);
        }
    }

    @Override // android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        editorInfo.inputType = 2;
        editorInfo.imeOptions = 268435456;
        return new ZanyInputConnection(this, false);
    }

    private class ZanyInputConnection extends BaseInputConnection {
        @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
        public boolean commitText(CharSequence charSequence, int i) {
            return super.commitText(charSequence, i);
        }

        public ZanyInputConnection(View view, boolean z) {
            super(view, z);
        }

        @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
        public boolean sendKeyEvent(KeyEvent keyEvent) {
            return super.sendKeyEvent(keyEvent);
        }

        @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
        public boolean deleteSurroundingText(int i, int i2) {
            if (i == 1 && i2 == 0) {
                return sendKeyEvent(new KeyEvent(0, 67)) && sendKeyEvent(new KeyEvent(1, 67));
            }
            return super.deleteSurroundingText(i, i2);
        }
    }

    class MyKeyListener implements View.OnKeyListener {
        MyKeyListener() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (keyEvent.getAction() != 0 || keyEvent.isShiftPressed()) {
                return false;
            }
            if (i < 7 || i > 16) {
                if (i == 67) {
                    PassWordLayout.this.removePwd();
                    return true;
                }
                ((InputMethodManager) PassWordLayout.this.mContext.getSystemService("input_method")).hideSoftInputFromWindow(PassWordLayout.this.getWindowToken(), 2);
                return true;
            }
            PassWordLayout.this.addPwd((i - 7) + "");
            return true;
        }
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mPassList = savedState.saveString;
        this.inputIndex = this.mPassList.size();
        if (this.mPassList.isEmpty()) {
            return;
        }
        for (int i = 0; i < getChildCount(); i++) {
            PassWordView passWordView = (PassWordView) getChildAt(i);
            if (i > this.mPassList.size() - 1) {
                if (passWordView != null) {
                    passWordView.setmIsShowRemindLine(false);
                    passWordView.updateInputState(false);
                    return;
                }
                return;
            }
            if (passWordView != null) {
                passWordView.setmPassText(this.mPassList.get(i));
                passWordView.updateInputState(true);
            }
        }
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.saveString = this.mPassList;
        return savedState;
    }

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.cyjh.share.view.PassWordLayout.SavedState.1
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
        public List<String> saveString;

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeList(this.saveString);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            parcel.readStringList(this.saveString);
        }
    }
}
