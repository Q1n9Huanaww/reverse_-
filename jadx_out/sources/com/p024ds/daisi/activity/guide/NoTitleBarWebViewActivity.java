package com.p024ds.daisi.activity.guide;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.activity.BaseActivity;
import com.p024ds.daisi.util.LogUtils;
import com.p024ds.daisi.util.NetworkUtils;
import com.umeng.analytics.MobclickAgent;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class NoTitleBarWebViewActivity extends BaseActivity {
    public static final String WEB_VIEW_URL = "web_view_url";
    private final String TAG = NoTitleBarWebViewActivity.class.getSimpleName();
    private ValueCallback<Uri[]> mMultiFileCallback;
    private ProgressBar mProgressBar;
    private ValueCallback<Uri> mSingleFileCallback;
    private WebView mWvContent;

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_no_title_bar_web_view);
        initView();
        initData();
    }

    private void initView() {
        this.mWvContent = (WebView) findViewById(R.id.wv_content);
        this.mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    private void initData() {
        String stringExtra = getIntent().getStringExtra("web_view_url");
        WebSettings settings = this.mWvContent.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSavePassword(true);
        settings.setSaveFormData(true);
        settings.setJavaScriptEnabled(true);
        settings.setGeolocationEnabled(true);
        settings.setGeolocationDatabasePath("/data/data/com.ds.daisi/databases/");
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(true);
        if (Build.VERSION.SDK_INT > 21) {
            settings.setMixedContentMode(0);
        }
        if (NetworkUtils.isAvailable(this)) {
            settings.setCacheMode(-1);
        } else {
            settings.setCacheMode(1);
        }
        this.mWvContent.requestFocus();
        this.mWvContent.setScrollBarStyle(0);
        this.mWvContent.setWebChromeClient(new WebChromeClient() { // from class: com.ds.daisi.activity.guide.NoTitleBarWebViewActivity.1
            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView webView, int i) {
                if (i == 100) {
                    NoTitleBarWebViewActivity.this.mProgressBar.setVisibility(8);
                } else {
                    NoTitleBarWebViewActivity.this.mProgressBar.setVisibility(0);
                    NoTitleBarWebViewActivity.this.mProgressBar.setProgress(i);
                }
            }

            @Override // android.webkit.WebChromeClient
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                NoTitleBarWebViewActivity.this.mMultiFileCallback = valueCallback;
                return true;
            }

            public void openFileChooser(ValueCallback<Uri> valueCallback) {
                LogUtils.m1356i(NoTitleBarWebViewActivity.this.TAG, "openFileChooser");
                NoTitleBarWebViewActivity.this.mSingleFileCallback = valueCallback;
            }
        });
        this.mWvContent.setWebViewClient(new WebViewClient() { // from class: com.ds.daisi.activity.guide.NoTitleBarWebViewActivity.2
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str.startsWith("http://") || str.startsWith("https://")) {
                    webView.loadUrl(str);
                    return true;
                }
                try {
                    NoTitleBarWebViewActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                } catch (Exception e) {
                    ThrowableExtension.printStackTrace(e);
                }
                return true;
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView webView, int i, String str, String str2) {
                webView.setVisibility(4);
                Message messageObtain = Message.obtain();
                messageObtain.what = i;
                messageObtain.obj = str;
                new RequestErrHandler().sendMessage(messageObtain);
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView, String str) {
                LogUtils.m1356i(NoTitleBarWebViewActivity.this.TAG, "onPageFinished --> url=" + str);
                super.onPageFinished(webView, str);
            }
        });
        this.mWvContent.loadUrl(stringExtra);
    }

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onResume() throws Throwable {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onPause() throws Throwable {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    private class RequestErrHandler extends Handler {
        private RequestErrHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
        }
    }

    public static boolean isDeepLink(String str) {
        return !isHttpUrl(str);
    }

    public static boolean isHttpUrl(String str) {
        if (str == null) {
            return false;
        }
        return str.startsWith("http:") || str.startsWith("https:");
    }

    public static boolean deviceCanHandleIntent(Context context, Intent intent) {
        try {
            return !context.getPackageManager().queryIntentActivities(intent, 0).isEmpty();
        } catch (NullPointerException unused) {
            return false;
        }
    }
}
