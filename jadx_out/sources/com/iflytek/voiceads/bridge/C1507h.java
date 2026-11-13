package com.iflytek.voiceads.bridge;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Message;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.FrameLayout;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.utils.C1593g;

/* JADX INFO: renamed from: com.iflytek.voiceads.bridge.h */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class C1507h extends WebChromeClient {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ DSBridgeWebView f3104a;

    C1507h(DSBridgeWebView dSBridgeWebView) {
        this.f3104a = dSBridgeWebView;
    }

    @Override // android.webkit.WebChromeClient
    public Bitmap getDefaultVideoPoster() {
        return this.f3104a.f3076g != null ? this.f3104a.f3076g.getDefaultVideoPoster() : super.getDefaultVideoPoster();
    }

    @Override // android.webkit.WebChromeClient
    public View getVideoLoadingProgressView() {
        return this.f3104a.f3076g != null ? this.f3104a.f3076g.getVideoLoadingProgressView() : super.getVideoLoadingProgressView();
    }

    @Override // android.webkit.WebChromeClient
    public void getVisitedHistory(ValueCallback<String[]> valueCallback) {
        if (this.f3104a.f3076g != null) {
            this.f3104a.f3076g.getVisitedHistory(valueCallback);
        } else {
            super.getVisitedHistory(valueCallback);
        }
    }

    @Override // android.webkit.WebChromeClient
    public void onCloseWindow(WebView webView) {
        if (this.f3104a.f3076g != null) {
            this.f3104a.f3076g.onCloseWindow(webView);
        } else {
            super.onCloseWindow(webView);
        }
    }

    @Override // android.webkit.WebChromeClient
    public void onConsoleMessage(String str, int i, String str2) {
        if (this.f3104a.f3076g != null) {
            this.f3104a.f3076g.onConsoleMessage(str, i, str2);
        } else {
            super.onConsoleMessage(str, i, str2);
        }
    }

    @Override // android.webkit.WebChromeClient
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        return this.f3104a.f3076g != null ? this.f3104a.f3076g.onConsoleMessage(consoleMessage) : super.onConsoleMessage(consoleMessage);
    }

    @Override // android.webkit.WebChromeClient
    public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
        return this.f3104a.f3076g != null ? this.f3104a.f3076g.onCreateWindow(webView, z, z2, message) : super.onCreateWindow(webView, z, z2, message);
    }

    @Override // android.webkit.WebChromeClient
    public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
        if (this.f3104a.f3076g != null) {
            this.f3104a.f3076g.onExceededDatabaseQuota(str, str2, j, j2, j3, quotaUpdater);
        } else {
            super.onExceededDatabaseQuota(str, str2, j, j2, j3, quotaUpdater);
        }
    }

    @Override // android.webkit.WebChromeClient
    public void onGeolocationPermissionsHidePrompt() {
        if (this.f3104a.f3076g != null) {
            this.f3104a.f3076g.onGeolocationPermissionsHidePrompt();
        } else {
            super.onGeolocationPermissionsHidePrompt();
        }
    }

    @Override // android.webkit.WebChromeClient
    public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
        if (this.f3104a.f3076g != null) {
            this.f3104a.f3076g.onGeolocationPermissionsShowPrompt(str, callback);
        } else {
            super.onGeolocationPermissionsShowPrompt(str, callback);
        }
    }

    @Override // android.webkit.WebChromeClient
    public void onHideCustomView() {
        if (this.f3104a.f3076g != null) {
            this.f3104a.f3076g.onHideCustomView();
        } else {
            super.onHideCustomView();
        }
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        if (!this.f3104a.f3078i) {
            jsResult.confirm();
        }
        if (this.f3104a.f3076g == null || !this.f3104a.f3076g.onJsAlert(webView, str, str2, jsResult)) {
            try {
                if (!(this.f3104a.getContext() instanceof Activity) || !((Activity) this.f3104a.getContext()).isFinishing()) {
                    new AlertDialog.Builder(this.f3104a.getContext()).setMessage(str2).setCancelable(false).setPositiveButton(R.string.ok, new DialogInterfaceOnClickListenerC1508i(this, jsResult)).create().show();
                }
            } catch (Exception e) {
                C1593g.m3190b(SDKConstants.TAG, "onJsAlert:" + e.getMessage());
            }
        }
        return true;
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
        return this.f3104a.f3076g != null ? this.f3104a.f3076g.onJsBeforeUnload(webView, str, str2, jsResult) : super.onJsBeforeUnload(webView, str, str2, jsResult);
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
        if (!this.f3104a.f3078i) {
            jsResult.confirm();
        }
        if (this.f3104a.f3076g == null || !this.f3104a.f3076g.onJsConfirm(webView, str, str2, jsResult)) {
            DialogInterfaceOnClickListenerC1509j dialogInterfaceOnClickListenerC1509j = new DialogInterfaceOnClickListenerC1509j(this, jsResult);
            try {
                if (!(this.f3104a.getContext() instanceof Activity) || !((Activity) this.f3104a.getContext()).isFinishing()) {
                    new AlertDialog.Builder(this.f3104a.getContext()).setMessage(str2).setCancelable(false).setPositiveButton(R.string.ok, dialogInterfaceOnClickListenerC1509j).setNegativeButton(R.string.cancel, dialogInterfaceOnClickListenerC1509j).show();
                }
            } catch (Exception e) {
                C1593g.m3190b(SDKConstants.TAG, "onJsConfirm:" + e.getMessage());
            }
        }
        return true;
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        if (Build.VERSION.SDK_INT > 16 || !str2.startsWith("_dsbridge=")) {
            if (!this.f3104a.f3078i) {
                jsPromptResult.confirm();
            }
            if (this.f3104a.f3076g == null || !this.f3104a.f3076g.onJsPrompt(webView, str, str2, str3, jsPromptResult)) {
                EditText editText = new EditText(this.f3104a.getContext());
                editText.setText(str3);
                if (str3 != null) {
                    editText.setSelection(str3.length());
                }
                float f = this.f3104a.getContext().getResources().getDisplayMetrics().density;
                DialogInterfaceOnClickListenerC1510k dialogInterfaceOnClickListenerC1510k = new DialogInterfaceOnClickListenerC1510k(this, jsPromptResult, editText);
                new AlertDialog.Builder(this.f3104a.getContext()).setTitle(str2).setView(editText).setCancelable(false).setPositiveButton(R.string.ok, dialogInterfaceOnClickListenerC1510k).setNegativeButton(R.string.cancel, dialogInterfaceOnClickListenerC1510k).show();
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
                int i = (int) (16.0f * f);
                layoutParams.setMargins(i, 0, i, 0);
                layoutParams.gravity = 1;
                editText.setLayoutParams(layoutParams);
                int i2 = (int) (15.0f * f);
                editText.setPadding(i2 - ((int) (f * 5.0f)), i2, i2, i2);
            }
        } else {
            jsPromptResult.confirm(this.f3104a.f3081l.m2877a(str2.substring("_dsbridge=".length()), str3));
        }
        return true;
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsTimeout() {
        return this.f3104a.f3076g != null ? this.f3104a.f3076g.onJsTimeout() : super.onJsTimeout();
    }

    @Override // android.webkit.WebChromeClient
    public void onPermissionRequest(PermissionRequest permissionRequest) {
        if (this.f3104a.f3076g != null) {
            this.f3104a.f3076g.onPermissionRequest(permissionRequest);
        } else {
            super.onPermissionRequest(permissionRequest);
        }
    }

    @Override // android.webkit.WebChromeClient
    public void onPermissionRequestCanceled(PermissionRequest permissionRequest) {
        if (this.f3104a.f3076g != null) {
            this.f3104a.f3076g.onPermissionRequestCanceled(permissionRequest);
        } else {
            super.onPermissionRequestCanceled(permissionRequest);
        }
    }

    @Override // android.webkit.WebChromeClient
    public void onProgressChanged(WebView webView, int i) {
        if (this.f3104a.f3076g != null) {
            this.f3104a.f3076g.onProgressChanged(webView, i);
        } else {
            super.onProgressChanged(webView, i);
        }
    }

    public void onReachedMaxAppCacheSize(long j, long j2, WebStorage.QuotaUpdater quotaUpdater) {
        if (this.f3104a.f3076g != null) {
            this.f3104a.f3076g.onReachedMaxAppCacheSize(j, j2, quotaUpdater);
        }
        super.onReachedMaxAppCacheSize(j, j2, quotaUpdater);
    }

    @Override // android.webkit.WebChromeClient
    public void onReceivedIcon(WebView webView, Bitmap bitmap) {
        if (this.f3104a.f3076g != null) {
            this.f3104a.f3076g.onReceivedIcon(webView, bitmap);
        } else {
            super.onReceivedIcon(webView, bitmap);
        }
    }

    @Override // android.webkit.WebChromeClient
    public void onReceivedTitle(WebView webView, String str) {
        if (this.f3104a.f3076g != null) {
            this.f3104a.f3076g.onReceivedTitle(webView, str);
        } else {
            super.onReceivedTitle(webView, str);
        }
    }

    @Override // android.webkit.WebChromeClient
    public void onReceivedTouchIconUrl(WebView webView, String str, boolean z) {
        if (this.f3104a.f3076g != null) {
            this.f3104a.f3076g.onReceivedTouchIconUrl(webView, str, z);
        } else {
            super.onReceivedTouchIconUrl(webView, str, z);
        }
    }

    @Override // android.webkit.WebChromeClient
    public void onRequestFocus(WebView webView) {
        if (this.f3104a.f3076g != null) {
            this.f3104a.f3076g.onRequestFocus(webView);
        } else {
            super.onRequestFocus(webView);
        }
    }

    @Override // android.webkit.WebChromeClient
    public void onShowCustomView(View view, int i, WebChromeClient.CustomViewCallback customViewCallback) {
        if (this.f3104a.f3076g != null) {
            this.f3104a.f3076g.onShowCustomView(view, i, customViewCallback);
        } else {
            super.onShowCustomView(view, i, customViewCallback);
        }
    }

    @Override // android.webkit.WebChromeClient
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        if (this.f3104a.f3076g != null) {
            this.f3104a.f3076g.onShowCustomView(view, customViewCallback);
        } else {
            super.onShowCustomView(view, customViewCallback);
        }
    }

    @Override // android.webkit.WebChromeClient
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        return this.f3104a.f3076g != null ? this.f3104a.f3076g.onShowFileChooser(webView, valueCallback, fileChooserParams) : super.onShowFileChooser(webView, valueCallback, fileChooserParams);
    }
}
