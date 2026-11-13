package com.cyjh.feedback.lib.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.cyjh.feedback.lib.utils.AppDevicesUtils;

/* JADX INFO: loaded from: classes.dex */
public class BaseDialog extends Dialog {
    public static final String TAG = "BaseDialog";
    public Context mContext;
    private float mDialogHeight;
    private float mDialogWidth;

    public BaseDialog(Context context, int i) {
        super(context, i);
        this.mDialogWidth = 0.8f;
        this.mDialogHeight = 0.8f;
        this.mContext = context;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void setBlurEffect(float f) {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.dimAmount = f;
        window.setAttributes(attributes);
        window.addFlags(2);
    }

    private void setDialogSize() {
        Point resolution = AppDevicesUtils.getResolution(this.mContext);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = (int) (resolution.x * this.mDialogWidth);
        attributes.height = (int) (resolution.y * this.mDialogHeight);
        getWindow().setAttributes(attributes);
    }

    public void setDialogSize(float f, float f2) {
        this.mDialogWidth = f;
        this.mDialogHeight = f2;
        setDialogSize();
    }
}
