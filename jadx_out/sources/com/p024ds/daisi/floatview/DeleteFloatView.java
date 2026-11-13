package com.p024ds.daisi.floatview;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.p024ds.daisi.log.CommonLog;
import com.p024ds.daisi.util.AppDeviceUtils;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class DeleteFloatView extends LinearLayout {
    CommonLog commonLog;
    private ImageView mImageView;
    private LinearLayout mLinearLayoutDeleteFloatView;
    private TextView mTextViewDelete;
    private WindowManager.LayoutParams mWindowManagerParams;

    public DeleteFloatView(Context context) {
        super(context);
        this.commonLog = new CommonLog();
        initView();
        initWindowManagerParams();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.delete_floatview, this);
        this.mTextViewDelete = (TextView) findViewById(R.id.deletefloatview_textview_delete);
        this.mImageView = (ImageView) findViewById(R.id.deletefloatview_imageview_delete);
        this.mLinearLayoutDeleteFloatView = (LinearLayout) findViewById(R.id.deletefloatview_linearlayout);
        setVisibility(8);
    }

    private void initWindowManagerParams() {
        this.mWindowManagerParams = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= 26) {
            this.mWindowManagerParams.type = 2038;
        } else if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 24) {
            this.mWindowManagerParams.type = 2005;
        } else {
            this.mWindowManagerParams.type = 2002;
        }
        this.mWindowManagerParams.format = 1;
        this.mWindowManagerParams.flags = 40;
        this.mWindowManagerParams.gravity = 8388659;
        this.mWindowManagerParams.x = (AppDeviceUtils.getResolution(getContext()).x - this.mLinearLayoutDeleteFloatView.getLayoutParams().width) / 2;
        this.mWindowManagerParams.y = AppDeviceUtils.getResolution(getContext()).y;
        this.mWindowManagerParams.width = this.mLinearLayoutDeleteFloatView.getLayoutParams().width;
        this.mWindowManagerParams.height = this.mLinearLayoutDeleteFloatView.getLayoutParams().height;
        this.commonLog.m1348e("小精灵宽>>>" + this.mWindowManagerParams.width);
        this.commonLog.m1348e("小精灵高>>>" + this.mWindowManagerParams.height);
    }

    @Override // android.view.View
    public WindowManager.LayoutParams getLayoutParams() {
        return this.mWindowManagerParams;
    }

    public int geTextViewtWidth() {
        return this.mLinearLayoutDeleteFloatView.getLayoutParams().width;
    }

    public void setHide() {
        this.mImageView.setImageResource(R.drawable.delete_floatview_in);
        this.mTextViewDelete.setTextColor(getResources().getColor(R.color.green));
    }

    public void setNormal() {
        this.mImageView.setImageResource(R.drawable.delete_floatview_out);
        this.mTextViewDelete.setTextColor(getResources().getColor(android.R.color.white));
    }

    public void rotateScreen() {
        this.commonLog.m1348e(" mLinearLayoutDeleteFloatView.getLayoutParams().width>>" + this.mLinearLayoutDeleteFloatView.getLayoutParams().width);
        WindowManager windowManager = (WindowManager) getContext().getSystemService("window");
        this.mWindowManagerParams.x = (AppDeviceUtils.getResolution(getContext()).x - this.mLinearLayoutDeleteFloatView.getLayoutParams().width) / 2;
        this.mWindowManagerParams.y = AppDeviceUtils.getResolution(getContext()).y;
        windowManager.updateViewLayout(this, this.mWindowManagerParams);
    }
}
