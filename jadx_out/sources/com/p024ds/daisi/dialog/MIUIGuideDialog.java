package com.p024ds.daisi.dialog;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.p024ds.daisi.constant.APPConstant;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class MIUIGuideDialog extends BaseDialog implements View.OnClickListener {
    private Button mButtonOpen;

    public MIUIGuideDialog(Context context) {
        super(context, R.style.Theme_Dialog);
    }

    @Override // com.p024ds.daisi.dialog.BaseDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_miuifloatview);
        setDialogSize(0.8f, 0.4f);
        initView();
    }

    private void initView() {
        this.mButtonOpen = (Button) findViewById(R.id.dialog_miuiguide_button_open);
        this.mButtonOpen.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.dialog_miuiguide_button_open) {
            return;
        }
        getContext().startActivity(getAppDetailSettingIntent());
        SharedPreferences.Editor editorEdit = this.mAppContext.sharedPreferences.edit();
        editorEdit.putBoolean(APPConstant.SP_KEY_GUIDEACTIVITY, false);
        editorEdit.commit();
        dismiss();
    }

    private Intent getAppDetailSettingIntent() {
        Intent intent = new Intent();
        intent.addFlags(268435456);
        if (Build.VERSION.SDK_INT >= 9) {
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", getContext().getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            intent.setAction("android.intent.action.VIEW");
            intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            intent.putExtra("com.android.settings.ApplicationPkgName", getContext().getPackageName());
        }
        return intent;
    }
}
