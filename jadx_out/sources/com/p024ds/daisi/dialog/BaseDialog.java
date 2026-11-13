package com.p024ds.daisi.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.constant.APPConstant;
import com.p024ds.daisi.log.CommonLog;
import com.p024ds.daisi.util.AppDeviceUtils;

/* JADX INFO: loaded from: classes.dex */
public class BaseDialog extends Dialog implements APPConstant {
    protected CommonLog commonLog;
    private float heightPercentage;
    protected AppContext mAppContext;
    private float widthPercentage;

    public BaseDialog(Context context, int i) {
        super(context, i);
        this.commonLog = new CommonLog();
        this.widthPercentage = 0.8f;
        this.heightPercentage = 0.8f;
        this.mAppContext = (AppContext) context.getApplicationContext();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setBlurEffect();
    }

    protected void setBlurEffect(float f) {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.dimAmount = f;
        window.setAttributes(attributes);
        window.addFlags(2);
    }

    protected void setBlurEffect() {
        setBlurEffect(0.5f);
    }

    protected void setDialogSize() {
        Point resolution = AppDeviceUtils.getResolution(getContext());
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
