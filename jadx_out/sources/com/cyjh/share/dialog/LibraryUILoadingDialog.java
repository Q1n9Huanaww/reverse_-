package com.cyjh.share.dialog;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import com.cyjh.share.C0854R;
import com.cyjh.share.view.SlLoadingView;

/* JADX INFO: loaded from: classes.dex */
public class LibraryUILoadingDialog extends BaseDialog implements View.OnClickListener {
    private static final int ANDROID_SDK_19 = 19;
    private static final int ANDROID_SDK_24 = 24;
    public static final String TAG = "LibraryUILoadingDialog";
    public Context mContext;
    private SlLoadingView mSlLoadingView;

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    public LibraryUILoadingDialog(Context context) {
        super(context, C0854R.style.Theme_Dialog);
        this.mContext = context;
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

    @Override // com.cyjh.share.dialog.BaseDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0854R.layout.dialog_library_ui_loading);
        setBlurEffect(0.2f);
        setDialogSize(0.8f, 0.6f);
        initView();
    }

    private void initView() {
        this.mSlLoadingView = (SlLoadingView) findViewById(C0854R.id.slLoadingView);
        setCancelable(false);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
    }
}
