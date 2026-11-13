package com.cyjh.feedback.lib.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import com.cyjh.feedback.lib.base.BaseDialog;
import com.cyjh.feedback.libraryfeedbackinfo.C0738R;

/* JADX INFO: loaded from: classes.dex */
public class FeedBackCommitDialog extends BaseDialog {
    public FeedBackCommitDialog(Context context) {
        super(context, C0738R.style.Theme_Dialog);
    }

    @Override // com.cyjh.feedback.lib.base.BaseDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0738R.layout.dialog_feedback_commit);
        setBlurEffect(0.5f);
        setDialogSize(1.0f, 1.0f);
    }

    @Override // android.app.Dialog
    public boolean onTouchEvent(MotionEvent motionEvent) {
        dismiss();
        return true;
    }
}
