package com.p024ds.daisi.manager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.p024ds.daisi.listener.BaseTextViewKnownListener;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class DialogManager {
    public static final float ALPHA_VALUE = 0.6f;
    public static DialogManager dialogManager;
    public ProgressDialog progressDialog;

    private DialogManager() {
    }

    public static DialogManager getInstance() {
        if (dialogManager == null) {
            dialogManager = new DialogManager();
        }
        return dialogManager;
    }

    public AlertDialog showMessageDialog(Context context, int i, int i2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, i2);
        builder.setView(LayoutInflater.from(context).inflate(i, (ViewGroup) null));
        AlertDialog alertDialogCreate = builder.create();
        setBackgroundShadow(alertDialogCreate);
        return alertDialogCreate;
    }

    private void setBackgroundShadow(AlertDialog alertDialog) {
        Window window = alertDialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.dimAmount = 0.6f;
        window.setAttributes(attributes);
        window.addFlags(2);
    }

    public void showProgressDialog(Context context, int i) {
        showProgressDialog(context, context.getString(i));
    }

    private void showProgressDialog(Context context, String str) {
        if (this.progressDialog == null) {
            this.progressDialog = new ProgressDialog(context);
        }
        this.progressDialog.setMessage(str);
        this.progressDialog.show();
    }

    public void dismissProgressDialog() {
        if (this.progressDialog == null || !this.progressDialog.isShowing()) {
            return;
        }
        this.progressDialog.dismiss();
        this.progressDialog = null;
    }

    public void displayDialogMessage(int i, String str, Context context, BaseTextViewKnownListener baseTextViewKnownListener) {
        AlertDialog alertDialogShowMessageDialog = showMessageDialog(context, R.layout.dialog_version_inspection, R.style.Theme_Dialog);
        alertDialogShowMessageDialog.setCancelable(false);
        alertDialogShowMessageDialog.show();
        ((TextView) alertDialogShowMessageDialog.findViewById(R.id.id_tv_message)).setText(str);
        TextView textView = (TextView) alertDialogShowMessageDialog.findViewById(R.id.id_tv_known);
        baseTextViewKnownListener.setAlertDialog(alertDialogShowMessageDialog);
        baseTextViewKnownListener.setReturnCode(i);
        textView.setOnClickListener(baseTextViewKnownListener);
    }
}
