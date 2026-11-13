package com.cyjh.feedback.lib.dialog;

import android.content.Context;
import android.os.Bundle;
import com.cyjh.feedback.lib.base.BaseDialog;
import com.cyjh.feedback.libraryfeedbackinfo.C0738R;

/* JADX INFO: loaded from: classes.dex */
public class LoadingDialog extends BaseDialog {
    public LoadingDialog(Context context) {
        super(context, C0738R.style.Theme_Dialog);
    }

    @Override // com.cyjh.feedback.lib.base.BaseDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0738R.layout.dialog_loading);
        setBlurEffect(0.3f);
        setDialogSize(1.0f, 1.0f);
    }
}
