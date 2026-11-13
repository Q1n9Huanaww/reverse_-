package com.cyjh.share.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.cyjh.share.util.CommonUtil;

/* JADX INFO: loaded from: classes.dex */
public class BaseDialog extends Dialog {
    private float heightPercentage;
    protected Context mContext;
    private float widthPercentage;

    public BaseDialog(Context context, int i) {
        super(context, i);
        this.widthPercentage = 0.8f;
        this.heightPercentage = 0.8f;
        this.mContext = context;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setBlurEffect();
    }

    protected void setBlurEffect() {
        setBlurEffect(0.5f);
    }

    protected void setBlurEffect(float f) {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.dimAmount = f;
        window.setAttributes(attributes);
        window.addFlags(2);
    }

    protected void setDialogSize() {
        Point resolution = CommonUtil.getResolution(getContext());
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = (int) (resolution.x * this.widthPercentage);
        attributes.height = (int) (resolution.y * this.heightPercentage);
        getWindow().setAttributes(attributes);
    }

    protected void setDialogSize(float f, float f2) {
        this.widthPercentage = f;
        this.heightPercentage = f2;
        setDialogSize();
    }
}
