package com.p024ds.daisi.dialog;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;
import android.widget.TextView;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.constant.APPConstant;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class ScriptRTDDialog extends BaseDialog {
    private TextView mTextvViewNoRtd;
    private WebView mWebView;

    public ScriptRTDDialog(Context context) {
        super(context, R.style.Theme_Dialog);
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

    @Override // com.p024ds.daisi.dialog.BaseDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_scriptrtd);
        setBlurEffect(0.1f);
        if (this.mAppContext.checkedScreenOrientation) {
            setDialogSize(0.9f, 0.8f);
        } else {
            setDialogSize(0.9f, 0.6f);
        }
        initView();
        initEvent();
    }

    private void initView() {
        initWebView();
    }

    private void initEvent() {
        this.mAppContext.setScreenConversion(new AppContext.ScreenOrientationConversion() { // from class: com.ds.daisi.dialog.ScriptRTDDialog.1
            @Override // com.ds.daisi.AppContext.ScreenOrientationConversion
            public void onLandscapeOrientation() {
                ScriptRTDDialog.this.convertScreen(0.9f, 0.8f);
            }

            @Override // com.ds.daisi.AppContext.ScreenOrientationConversion
            public void onPortraitOrientation() {
                ScriptRTDDialog.this.convertScreen(0.9f, 0.6f);
            }
        });
    }

    private void initWebView() {
        this.mTextvViewNoRtd = (TextView) findViewById(R.id.scriptrtd_textview_nortd);
        this.mWebView = (WebView) findViewById(R.id.scriptrtd_webview);
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        try {
            this.mAppContext.getAssets().open(APPConstant.SCRIPT_FILENAME_RTD);
            this.mWebView.loadUrl("file:///android_asset/script.rtd");
        } catch (Exception unused) {
            this.mWebView.setVisibility(8);
            this.mTextvViewNoRtd.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void convertScreen(final float f, final float f2) {
        if (isShowing()) {
            dismiss();
            new Handler().postDelayed(new Runnable() { // from class: com.ds.daisi.dialog.ScriptRTDDialog.2
                @Override // java.lang.Runnable
                public void run() {
                    ScriptRTDDialog.this.setDialogSize(f, f2);
                    ScriptRTDDialog.this.show();
                }
            }, 500L);
        }
    }
}
