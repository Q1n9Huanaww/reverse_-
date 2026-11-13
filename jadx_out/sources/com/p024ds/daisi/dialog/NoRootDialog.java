package com.p024ds.daisi.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class NoRootDialog extends BaseDialog {
    private Button mBtnSure;

    public NoRootDialog(Context context) {
        super(context, R.style.Theme_Dialog);
    }

    @Override // com.p024ds.daisi.dialog.BaseDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_no_root);
        setDialogSize(0.8f, 0.4f);
        setBlurEffect(0.5f);
        setCanceledOnTouchOutside(false);
        this.mBtnSure = (Button) findViewById(R.id.id_btn_sure);
        this.mBtnSure.setOnClickListener(new View.OnClickListener() { // from class: com.ds.daisi.dialog.NoRootDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NoRootDialog.this.dismiss();
            }
        });
    }
}
