package com.p024ds.daisi.customview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class TitleView extends RelativeLayout {
    private ImageView imgLeft;
    private ImageView imgRight;
    private Context mContext;
    private TextView mRightTextView;
    private OnLeftImageViewListener onLeftImgeViewListener;
    private OnRightImageViewListener onRightImageViewListener;
    private OnRightTextViewListener onRightTextViewListener;
    private TextView tvTitle;

    public interface OnLeftImageViewListener {
        void onClick(View view);
    }

    public interface OnRightImageViewListener {
        void onClick(View view);
    }

    public interface OnRightTextViewListener {
        void onClicker(View view);
    }

    public TitleView(Context context) {
        this(context, null);
    }

    public TitleView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TitleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        initLayout();
        initView();
    }

    private void initLayout() {
        setBackgroundColor(Color.rgb(60, 122, 197));
    }

    private void initView() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        this.tvTitle = new TextView(this.mContext);
        this.tvTitle.setTextColor(-1);
        this.tvTitle.setTextSize(18.0f);
        this.tvTitle.setGravity(16);
        this.tvTitle.setSingleLine();
        addView(this.tvTitle, layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        this.imgLeft = new ImageView(this.mContext);
        layoutParams2.addRule(15);
        layoutParams2.addRule(9);
        layoutParams2.setMargins(20, 10, 0, 10);
        this.imgLeft.setImageResource(R.drawable.ic_setting);
        this.imgLeft.setOnClickListener(new onLeftImageViewClickListener());
        addView(this.imgLeft, layoutParams2);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(15);
        layoutParams3.addRule(11);
        layoutParams3.setMargins(0, 10, 20, 10);
        this.imgRight = new ImageView(this.mContext);
        this.imgRight.setImageResource(R.drawable.ic_setting);
        this.imgRight.setOnClickListener(new onRightImageViewClickListener());
        addView(this.imgRight, layoutParams3);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams4.addRule(11);
        layoutParams4.addRule(15);
        layoutParams4.setMargins(0, 10, 20, 10);
        this.mRightTextView = new TextView(this.mContext);
        this.mRightTextView.setVisibility(8);
        this.mRightTextView.setOnClickListener(new OnRightTextViewOnClickerListener());
        this.mRightTextView.setSingleLine();
        this.mRightTextView.setTextSize(16.0f);
        this.mRightTextView.setTextColor(-1);
        addView(this.mRightTextView, layoutParams4);
    }

    public void setTitleText(String str) {
        this.tvTitle.setText(str);
    }

    public void setTitleText(int i) {
        this.tvTitle.setText(i);
    }

    public void setVisibilityRightImage(int i) {
        this.imgRight.setVisibility(i);
    }

    public void setVisibilityLeftImage(int i) {
        this.imgLeft.setVisibility(i);
    }

    public void setleftImage(int i) {
        this.imgLeft.setImageResource(i);
    }

    public void setRightImage(int i) {
        this.imgRight.setImageResource(i);
    }

    public void setTitleSize(int i) {
        this.tvTitle.setTextSize(i);
    }

    public void setVisibilityTvRight(int i) {
        this.mRightTextView.setVisibility(i);
    }

    public void setRightTvText(int i) {
        this.mRightTextView.setText(i);
    }

    public void setRightTvText(String str) {
        this.mRightTextView.setText(str);
    }

    public void setOnLeftImageViewListener(OnLeftImageViewListener onLeftImageViewListener) {
        this.onLeftImgeViewListener = onLeftImageViewListener;
    }

    public void setOnRightImageViewListener(OnRightImageViewListener onRightImageViewListener) {
        this.onRightImageViewListener = onRightImageViewListener;
    }

    public void setOnRightTextViewListener(OnRightTextViewListener onRightTextViewListener) {
        this.onRightTextViewListener = onRightTextViewListener;
    }

    private class onLeftImageViewClickListener implements View.OnClickListener {
        private onLeftImageViewClickListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (TitleView.this.onLeftImgeViewListener != null) {
                TitleView.this.onLeftImgeViewListener.onClick(view);
            }
        }
    }

    private class onRightImageViewClickListener implements View.OnClickListener {
        private onRightImageViewClickListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (TitleView.this.onRightImageViewListener != null) {
                TitleView.this.onRightImageViewListener.onClick(view);
            }
        }
    }

    private class OnRightTextViewOnClickerListener implements View.OnClickListener {
        private OnRightTextViewOnClickerListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (TitleView.this.onRightTextViewListener != null) {
                TitleView.this.onRightTextViewListener.onClicker(view);
            }
        }
    }
}
