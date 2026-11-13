package com.p024ds.daisi.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.p024ds.daisi.entity.MsgItem;
import com.warnship.autoplay.R;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class TipsDialog extends AlertDialog {
    public static boolean sIsFloat = true;
    public static boolean sIsShow;
    private View.OnClickListener mClickListener;
    private TextView mTvMessage;
    private String message;

    public void setClickListener(View.OnClickListener onClickListener) {
        this.mClickListener = onClickListener;
    }

    public TipsDialog(Context context, boolean z, String str) {
        super(context, R.style.Theme_Dialog);
        if (z) {
            if (Build.VERSION.SDK_INT >= 26) {
                getWindow().setType(2038);
            } else if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 24) {
                getWindow().setType(2005);
            } else {
                getWindow().setType(2002);
            }
        }
        this.message = str;
        sIsFloat = z;
    }

    @Override // android.app.AlertDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_version_inspection);
        EventBus.getDefault().register(this);
        setBackgroundShadow(this);
        this.mTvMessage = (TextView) findViewById(R.id.id_tv_message);
        this.mTvMessage.setText(this.message);
        ((TextView) findViewById(R.id.id_tv_known)).setOnClickListener(this.mClickListener);
        setCancelable(false);
    }

    private void setBackgroundShadow(AlertDialog alertDialog) {
        Window window = alertDialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.dimAmount = 0.6f;
        window.setAttributes(attributes);
        window.addFlags(2);
    }

    public void setMessage(String str) {
        this.mTvMessage.setText(str);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        EventBus.getDefault().unregister(this);
        sIsShow = false;
        sIsFloat = true;
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        sIsShow = true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageMainThread(MsgItem.UpdateMessageToDialog updateMessageToDialog) {
        if (TextUtils.isEmpty(updateMessageToDialog.getMessage())) {
            return;
        }
        this.mTvMessage.setText(updateMessageToDialog.getMessage());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageMainThread(MsgItem msgItem) {
        if (msgItem.getMsgType() == 1006) {
            dismiss();
        }
    }
}
