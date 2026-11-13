package com.p024ds.daisi.listener;

import android.app.AlertDialog;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class BaseTextViewKnownListener implements View.OnClickListener {
    private AlertDialog mAlertDialog;
    private int returnCode;

    public void closeApp() {
    }

    public void pass() {
    }

    public void setAlertDialog(AlertDialog alertDialog) {
        this.mAlertDialog = alertDialog;
    }

    public void setReturnCode(int i) {
        this.returnCode = i;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.returnCode == 0) {
            closeApp();
        } else {
            pass();
        }
        this.mAlertDialog.dismiss();
    }
}
