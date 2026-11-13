package com.p024ds.daisi.dialog;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class HideFWDialog extends BaseDialog implements View.OnClickListener {
    private Button mButtonCancel;
    private Button mButtonOK;
    private OnHideCancelClickListener mOnHideCancelClickListener;
    private OnHideOKClickListener mOnHideOKClickListener;

    public interface OnHideCancelClickListener {
        void onClick(View view);
    }

    public interface OnHideOKClickListener {
        void onClick(View view);
    }

    public HideFWDialog(Context context) {
        super(context, R.style.Theme_Dialog);
        if (Build.VERSION.SDK_INT >= 26) {
            getWindow().setType(2038);
            return;
        }
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 24) {
            if (getWindow() != null) {
                getWindow().setType(2005);
            }
        } else if (getWindow() != null) {
            getWindow().setType(2002);
        }
    }

    @Override // com.p024ds.daisi.dialog.BaseDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_hidefw);
        setBlurEffect(0.1f);
        setDialogSize(0.8f, 0.3f);
        initView();
    }

    private void initView() {
        this.mButtonOK = (Button) findViewById(R.id.hidefw_button_ok);
        this.mButtonCancel = (Button) findViewById(R.id.hidefw_button_cancel);
        this.mButtonOK.setOnClickListener(this);
        this.mButtonCancel.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hidefw_button_cancel /* 2131755300 */:
                if (this.mOnHideCancelClickListener != null) {
                    this.mOnHideCancelClickListener.onClick(view);
                }
                break;
            case R.id.hidefw_button_ok /* 2131755301 */:
                if (this.mOnHideOKClickListener != null) {
                    this.mOnHideOKClickListener.onClick(view);
                }
                break;
        }
        dismiss();
    }

    public void setOnHideOKClickListener(OnHideOKClickListener onHideOKClickListener) {
        this.mOnHideOKClickListener = onHideOKClickListener;
    }

    public void setOnHideCancelClickListener(OnHideCancelClickListener onHideCancelClickListener) {
        this.mOnHideCancelClickListener = onHideCancelClickListener;
    }
}
