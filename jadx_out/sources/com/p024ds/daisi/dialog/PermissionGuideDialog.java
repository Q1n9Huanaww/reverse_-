package com.p024ds.daisi.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import com.p024ds.daisi.util.AppDeviceUtils;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class PermissionGuideDialog extends BaseDialog implements View.OnClickListener {
    public static final int CANCEL_OPERATE = 101;
    public static final int CONFIRM_OPERATE = 100;
    public static final String FIRST_OPEN_FLOAT_PERMISSION = "first_open_float_permission";
    private Button mButtonOpen;
    private OnDialogClickListener mListener;

    public interface OnDialogClickListener {
        void onDialogClick(int i);
    }

    public PermissionGuideDialog(Context context) {
        super(context, R.style.Theme_Dialog);
    }

    public PermissionGuideDialog(Context context, OnDialogClickListener onDialogClickListener) {
        super(context, R.style.Theme_Dialog);
        this.mListener = onDialogClickListener;
    }

    @Override // com.p024ds.daisi.dialog.BaseDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_miuifloatview);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        this.mButtonOpen = (Button) findViewById(R.id.dialog_miuiguide_button_open);
        this.mButtonOpen.setOnClickListener(this);
    }

    private void initData() {
        Point resolution = AppDeviceUtils.getResolution(getContext());
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = (int) (((double) resolution.x) * 0.8d);
        attributes.height = -2;
        getWindow().setAttributes(attributes);
    }

    private void initListener() {
        setOnDismissListener(new DialogInterface.OnDismissListener(this) { // from class: com.ds.daisi.dialog.PermissionGuideDialog$$Lambda$0
            private final PermissionGuideDialog arg$1;

            {
                this.arg$1 = this;
            }

            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                this.arg$1.lambda$initListener$0$PermissionGuideDialog(dialogInterface);
            }
        });
    }

    final /* synthetic */ void lambda$initListener$0$PermissionGuideDialog(DialogInterface dialogInterface) {
        if (this.mListener != null) {
            this.mListener.onDialogClick(101);
        }
    }

    public void setListener(OnDialogClickListener onDialogClickListener) {
        this.mListener = onDialogClickListener;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.dialog_miuiguide_button_open) {
            return;
        }
        jumpSettingPage();
    }

    private void jumpSettingPage() {
        getContext().startActivity(getAppDetailSettingIntent());
        if (this.mListener != null) {
            this.mListener.onDialogClick(100);
        }
        SharedPreferences.Editor editorEdit = this.mAppContext.sharedPreferences.edit();
        editorEdit.putBoolean(FIRST_OPEN_FLOAT_PERMISSION, false);
        editorEdit.commit();
        dismiss();
    }

    private Intent getAppDetailSettingIntent() {
        Intent intent = new Intent();
        int i = Build.VERSION.SDK_INT;
        if (i >= 26 || i >= 23) {
            intent.setAction("android.settings.action.MANAGE_OVERLAY_PERMISSION");
            intent.setData(Uri.fromParts("package", getContext().getPackageName(), null));
        } else if (i >= 9) {
            intent.addFlags(268435456);
            intent.setAction("android.settings.action.MANAGE_WRITE_SETTINGS");
            intent.setData(Uri.fromParts("package", getContext().getPackageName(), null));
        } else if (i <= 8) {
            intent.addFlags(268435456);
            intent.setAction("android.intent.action.VIEW");
            intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            intent.putExtra("com.android.settings.ApplicationPkgName", getContext().getPackageName());
        }
        return intent;
    }
}
