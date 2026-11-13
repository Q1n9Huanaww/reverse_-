package com.androidquery.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Picture;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/* JADX INFO: loaded from: classes.dex */
public class WebImage extends WebViewClient {
    private static final String DOUBLE_TAP_TOAST_COUNT = "double_tap_toast_count";
    private static final String PREF_FILE = "WebViewSettings";
    private static String template;
    private int color;
    private boolean control;
    private Object progress;
    private String url;

    /* JADX INFO: renamed from: wv */
    private WebView f69wv;
    private boolean zoom;

    @Override // android.webkit.WebViewClient
    public void onScaleChanged(WebView webView, float f, float f2) {
    }

    private static String getSource(Context context) {
        if (template == null) {
            try {
                template = new String(AQUtility.toBytes(context.getClassLoader().getResourceAsStream("com/androidquery/util/web_image.html")));
            } catch (Exception e) {
                AQUtility.debug((Throwable) e);
            }
        }
        return template;
    }

    private static void fixWebviewTip(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE, 0);
        if (sharedPreferences.getInt(DOUBLE_TAP_TOAST_COUNT, 1) > 0) {
            sharedPreferences.edit().putInt(DOUBLE_TAP_TOAST_COUNT, 0).commit();
        }
    }

    public WebImage(WebView webView, String str, Object obj, boolean z, boolean z2, int i) {
        this.f69wv = webView;
        this.url = str;
        this.progress = obj;
        this.zoom = z;
        this.control = z2;
        this.color = i;
    }

    public void load() {
        if (this.url.equals(this.f69wv.getTag(Constants.TAG_URL))) {
            return;
        }
        this.f69wv.setTag(Constants.TAG_URL, this.url);
        if (Build.VERSION.SDK_INT <= 10) {
            this.f69wv.setDrawingCacheEnabled(true);
        }
        fixWebviewTip(this.f69wv.getContext());
        WebSettings settings = this.f69wv.getSettings();
        settings.setSupportZoom(this.zoom);
        settings.setBuiltInZoomControls(this.zoom);
        if (!this.control) {
            disableZoomControl(this.f69wv);
        }
        settings.setJavaScriptEnabled(true);
        this.f69wv.setBackgroundColor(this.color);
        if (this.progress != null) {
            Common.showProgress(this.progress, this.url, true);
        }
        if (this.f69wv.getWidth() > 0) {
            setup();
        } else {
            delaySetup();
        }
    }

    private void delaySetup() {
        this.f69wv.setPictureListener(new WebView.PictureListener() { // from class: com.androidquery.util.WebImage.1
            @Override // android.webkit.WebView.PictureListener
            public void onNewPicture(WebView webView, Picture picture) {
                WebImage.this.f69wv.setPictureListener(null);
                WebImage.this.setup();
            }
        });
        this.f69wv.loadData("<html></html>", "text/html", "utf-8");
        this.f69wv.setBackgroundColor(this.color);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setup() {
        String strReplace = getSource(this.f69wv.getContext()).replace("@src", this.url).replace("@color", Integer.toHexString(this.color));
        this.f69wv.setWebViewClient(this);
        this.f69wv.loadDataWithBaseURL(null, strReplace, "text/html", "utf-8", null);
        this.f69wv.setBackgroundColor(this.color);
    }

    private void done(WebView webView) {
        if (this.progress != null) {
            webView.setVisibility(0);
            Common.showProgress(this.progress, this.url, false);
        }
        webView.setWebViewClient(null);
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        done(webView);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        done(webView);
    }

    private static void disableZoomControl(WebView webView) {
        if (Build.VERSION.SDK_INT < 11) {
            return;
        }
        AQUtility.invokeHandler(webView.getSettings(), "setDisplayZoomControls", false, false, new Class[]{Boolean.TYPE}, false);
    }
}
