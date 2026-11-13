package com.androidquery;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.androidquery.util.AQUtility;

/* JADX INFO: loaded from: classes.dex */
public class WebDialog extends Dialog {
    private WebViewClient client;

    /* JADX INFO: renamed from: ll */
    private LinearLayout f51ll;
    private String message;
    private String url;

    /* JADX INFO: renamed from: wv */
    private WebView f52wv;

    public WebDialog(Context context, String str, WebViewClient webViewClient) {
        super(context, R.style.Theme.NoTitleBar);
        this.url = str;
        this.client = webViewClient;
    }

    public void setLoadingMessage(String str) {
        this.message = str;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        relativeLayout.setBackgroundColor(-1);
        setupWebView(relativeLayout);
        setupProgress(relativeLayout);
        addContentView(relativeLayout, new FrameLayout.LayoutParams(-1, -1));
    }

    private void setupProgress(RelativeLayout relativeLayout) {
        Context context = getContext();
        this.f51ll = new LinearLayout(context);
        ProgressBar progressBar = new ProgressBar(context);
        int iDip2pixel = AQUtility.dip2pixel(context, 30.0f);
        this.f51ll.addView(progressBar, new LinearLayout.LayoutParams(iDip2pixel, iDip2pixel));
        if (this.message != null) {
            TextView textView = new TextView(context);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.leftMargin = AQUtility.dip2pixel(context, 5.0f);
            layoutParams.gravity = 16;
            textView.setText(this.message);
            this.f51ll.addView(textView, layoutParams);
        }
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(13);
        relativeLayout.addView(this.f51ll, layoutParams2);
    }

    private void setupWebView(RelativeLayout relativeLayout) {
        this.f52wv = new WebView(getContext());
        this.f52wv.setVerticalScrollBarEnabled(false);
        this.f52wv.setHorizontalScrollBarEnabled(false);
        if (this.client == null) {
            this.client = new WebViewClient();
        }
        this.f52wv.setWebViewClient(new DialogWebViewClient(this, null));
        this.f52wv.getSettings().setJavaScriptEnabled(true);
        relativeLayout.addView(this.f52wv, new RelativeLayout.LayoutParams(-1, -1));
    }

    public void load() {
        if (this.f52wv != null) {
            this.f52wv.loadUrl(this.url);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showProgress(boolean z) {
        if (this.f51ll != null) {
            if (z) {
                this.f51ll.setVisibility(0);
            } else {
                this.f51ll.setVisibility(8);
            }
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        try {
            super.dismiss();
        } catch (Exception unused) {
        }
    }

    private class DialogWebViewClient extends WebViewClient {
        private DialogWebViewClient() {
        }

        /* synthetic */ DialogWebViewClient(WebDialog webDialog, DialogWebViewClient dialogWebViewClient) {
            this();
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            WebDialog.this.showProgress(false);
            WebDialog.this.client.onPageFinished(webView, str);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            WebDialog.this.client.onPageStarted(webView, str, bitmap);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            WebDialog.this.client.onReceivedError(webView, i, str, str2);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return WebDialog.this.client.shouldOverrideUrlLoading(webView, str);
        }
    }
}
