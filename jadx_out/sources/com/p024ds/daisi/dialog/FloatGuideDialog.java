package com.p024ds.daisi.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class FloatGuideDialog extends BaseDialog implements View.OnClickListener {
    private Button mButtonOpen;
    private OnConfirmResult mConfirmResult;

    public interface OnConfirmResult {
        void confirmResult(boolean z);
    }

    public FloatGuideDialog(Context context) {
        super(context, R.style.Theme_Dialog);
    }

    @Override // com.p024ds.daisi.dialog.BaseDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_miuifloatview);
        setDialogSize(0.8f, 0.3f);
        initView();
    }

    private void initView() {
        this.mButtonOpen = (Button) findViewById(R.id.dialog_miuiguide_button_open);
        this.mButtonOpen.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.dialog_miuiguide_button_open && this.mConfirmResult != null) {
            this.mConfirmResult.confirmResult(true);
        }
    }

    public void setConfirmResult(OnConfirmResult onConfirmResult) {
        this.mConfirmResult = onConfirmResult;
    }
}
