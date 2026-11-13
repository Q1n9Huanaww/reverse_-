package com.p024ds.daisi.activity.news;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import com.p024ds.daisi.activity.BaseActivity;
import com.p024ds.daisi.customview.TitleView;
import com.umeng.analytics.MobclickAgent;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class H5LinkJumpPageActivity extends BaseActivity {
    private LinearLayout mRootLayout;
    private WebView mWebView;

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_h5_link_page);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(H5LinkJumpPageActivity.class.getCanonicalName());
        String stringExtra2 = intent.getStringExtra(H5LinkJumpPageActivity.class.getSimpleName());
        initView(stringExtra);
        initData(stringExtra2);
    }

    private void initView(String str) {
        TitleView titleView = (TitleView) findViewById(R.id.title_view);
        titleView.setTitleText(str);
        titleView.setleftImage(R.drawable.ic_back);
        titleView.setVisibilityRightImage(8);
        titleView.setVisibilityTvRight(0);
        titleView.setOnLeftImageViewListener(new OnLeftBackListener());
        this.mRootLayout = (LinearLayout) findViewById(R.id.ll_root);
        this.mWebView = new WebView(getApplicationContext());
        this.mRootLayout.addView(this.mWebView, new LinearLayout.LayoutParams(-1, -1));
    }

    private void initData(String str) {
        WebSettings settings = this.mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setDatabaseEnabled(true);
        this.mWebView.setVisibility(0);
        this.mWebView.setWebViewClient(new WebViewClient() { // from class: com.ds.daisi.activity.news.H5LinkJumpPageActivity.1
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str2) {
                webView.loadUrl(str2);
                return true;
            }
        });
        this.mWebView.loadUrl(str);
    }

    private class OnLeftBackListener implements TitleView.OnLeftImageViewListener {
        private OnLeftBackListener() {
        }

        @Override // com.ds.daisi.customview.TitleView.OnLeftImageViewListener
        public void onClick(View view) {
            H5LinkJumpPageActivity.this.finish();
        }
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

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (this.mWebView != null) {
            this.mRootLayout.removeView(this.mWebView);
            this.mWebView.removeAllViews();
            this.mWebView.destroy();
            this.mWebView = null;
        }
    }
}
