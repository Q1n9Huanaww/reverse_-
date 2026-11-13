package com.androidquery.auth;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.androidquery.AQuery;
import com.androidquery.WebDialog;
import com.androidquery.callback.AbstractAjaxCallback;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.AQUtility;
import com.umeng.analytics.pro.C1708b;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class FacebookHandle extends AccountHandle {
    private static final String CANCEL_URI = "fbconnect:cancel";
    public static final String FB_APP_SIGNATURE = "30820268308201d102044a9c4610300d06092a864886f70d0101040500307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e3020170d3039303833313231353231365a180f32303530303932353231353231365a307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e30819f300d06092a864886f70d010101050003818d0030818902818100c207d51df8eb8c97d93ba0c8c1002c928fab00dc1b42fca5e66e99cc3023ed2d214d822bc59e8e35ddcf5f44c7ae8ade50d7e0c434f500e6c131f4a2834f987fc46406115de2018ebbb0d5a3c261bd97581ccfef76afc7135a6d59e8855ecd7eacc8f8737e794c60a761c536b72b11fac8e603f5da1a2d54aa103b8a13c0dbc10203010001300d06092a864886f70d0101040500038181005ee9be8bcbb250648d3b741290a82a1c9dc2e76a0af2f2228f1d9f9c4007529c446a70175c5a900d5141812866db46be6559e2141616483998211f4a673149fb2232a10d247663b26a9031e15f84bc1c74d141ff98a02d76f85b2c8ab2571b6469b232d8e768a7f7ca04f7abe4a775615916c07940656b58717457b42bd928a2";
    private static final String FB_PERMISSION = "aq.fb.permission";
    private static final String FB_TOKEN = "aq.fb.token";
    private static final String OAUTH_ENDPOINT = "https://graph.facebook.com/oauth/authorize";
    private static final String REDIRECT_URI = "https://www.facebook.com/connect/login_success.html";
    private static Boolean hasSSO;
    private Activity act;
    private String appId;
    private WebDialog dialog;
    private boolean first;
    private String message;
    private String permissions;
    private int requestId;
    private boolean sso;
    private String token;

    protected void authenticated(String str) {
    }

    public FacebookHandle(Activity activity, String str, String str2) {
        this(activity, str, str2, null);
    }

    public FacebookHandle(Activity activity, String str, String str2, String str3) {
        this.appId = str;
        this.act = activity;
        this.permissions = str2;
        this.token = str3;
        if (this.token == null && permissionOk(str2, fetchPermission())) {
            this.token = fetchToken();
        }
        this.first = this.token == null;
    }

    public String getToken() {
        return this.token;
    }

    public static String getToken(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(FB_TOKEN, null);
    }

    public FacebookHandle sso(int i) {
        this.sso = true;
        this.requestId = i;
        return this;
    }

    private boolean permissionOk(String str, String str2) {
        if (str == null) {
            return true;
        }
        if (str2 == null) {
            return false;
        }
        HashSet hashSet = new HashSet(Arrays.asList(str2.split("[,\\s]+")));
        for (String str3 : str.split("[,\\s]+")) {
            if (!hashSet.contains(str3)) {
                AQUtility.debug("perm mismatch");
                return false;
            }
        }
        return true;
    }

    public FacebookHandle message(String str) {
        this.message = str;
        return this;
    }

    public FacebookHandle setLoadingMessage(int i) {
        this.message = this.act.getString(i);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismiss() {
        if (this.dialog != null) {
            new AQuery(this.act).dismiss(this.dialog);
            this.dialog = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void show() {
        if (this.dialog != null) {
            new AQuery(this.act).show(this.dialog);
        }
    }

    private void hide() {
        if (this.dialog != null) {
            try {
                this.dialog.hide();
            } catch (Exception e) {
                AQUtility.debug((Throwable) e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void failure() {
        failure("cancel");
    }

    private void failure(String str) {
        dismiss();
        failure(this.act, AjaxStatus.AUTH_ERROR, str);
    }

    @Override // com.androidquery.auth.AccountHandle
    protected void auth() {
        if (this.act.isFinishing()) {
            return;
        }
        boolean zSso = sso();
        AQUtility.debug("authing", Boolean.valueOf(zSso));
        if (zSso) {
            return;
        }
        webAuth();
    }

    private boolean sso() {
        if (this.sso) {
            return startSingleSignOn(this.act, this.appId, this.permissions, this.requestId);
        }
        return false;
    }

    private void webAuth() {
        AQUtility.debug("web auth");
        Bundle bundle = new Bundle();
        bundle.putString("client_id", this.appId);
        bundle.putString("type", "user_agent");
        if (this.permissions != null) {
            bundle.putString("scope", this.permissions);
        }
        bundle.putString("redirect_uri", REDIRECT_URI);
        String str = "https://graph.facebook.com/oauth/authorize?" + encodeUrl(bundle);
        FbWebViewClient fbWebViewClient = new FbWebViewClient(this, null);
        this.dialog = new WebDialog(this.act, str, fbWebViewClient);
        this.dialog.setLoadingMessage(this.message);
        this.dialog.setOnCancelListener(fbWebViewClient);
        show();
        if (!this.first || this.token != null) {
            AQUtility.debug("auth hide");
            hide();
        }
        this.dialog.load();
        AQUtility.debug("auth started");
    }

    private String fetchToken() {
        return PreferenceManager.getDefaultSharedPreferences(this.act).getString(FB_TOKEN, null);
    }

    private String fetchPermission() {
        return PreferenceManager.getDefaultSharedPreferences(this.act).getString(FB_PERMISSION, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void storeToken(String str, String str2) {
        SharedPreferences.Editor editorEdit = PreferenceManager.getDefaultSharedPreferences(this.act).edit();
        editorEdit.putString(FB_TOKEN, str).putString(FB_PERMISSION, str2);
        AQUtility.apply(editorEdit);
    }

    private class FbWebViewClient extends WebViewClient implements DialogInterface.OnCancelListener {
        private FbWebViewClient() {
        }

        /* synthetic */ FbWebViewClient(FacebookHandle facebookHandle, FbWebViewClient fbWebViewClient) {
            this();
        }

        private boolean checkDone(String str) {
            if (str.startsWith(FacebookHandle.REDIRECT_URI)) {
                String string = FacebookHandle.parseUrl(str).getString("error_reason");
                AQUtility.debug(C1708b.f3698J, string);
                if (string == null) {
                    FacebookHandle.this.token = FacebookHandle.this.extractToken(str);
                }
                if (FacebookHandle.this.token != null) {
                    FacebookHandle.this.dismiss();
                    FacebookHandle.this.storeToken(FacebookHandle.this.token, FacebookHandle.this.permissions);
                    FacebookHandle.this.first = false;
                    FacebookHandle.this.authenticated(FacebookHandle.this.token);
                    FacebookHandle.this.success(FacebookHandle.this.act);
                } else {
                    FacebookHandle.this.failure();
                }
                return true;
            }
            if (!str.startsWith(FacebookHandle.CANCEL_URI)) {
                return false;
            }
            AQUtility.debug("cancelled");
            FacebookHandle.this.failure();
            return true;
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            AQUtility.debug("return url: " + str);
            return checkDone(str);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            AQUtility.debug("started", str);
            if (checkDone(str)) {
                return;
            }
            super.onPageStarted(webView, str, bitmap);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            FacebookHandle.this.show();
            AQUtility.debug("finished", str);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            FacebookHandle.this.failure();
        }

        @Override // android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialogInterface) {
            FacebookHandle.this.failure();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String extractToken(String str) {
        String queryParameter = Uri.parse(str.replace('#', '?')).getQueryParameter("access_token");
        AQUtility.debug("token", queryParameter);
        return queryParameter;
    }

    private static String encodeUrl(Bundle bundle) {
        if (bundle == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String str : bundle.keySet()) {
            if (z) {
                z = false;
            } else {
                sb.append("&");
            }
            sb.append(String.valueOf(str) + "=" + bundle.getString(str));
        }
        return sb.toString();
    }

    private static Bundle decodeUrl(String str) {
        Bundle bundle = new Bundle();
        if (str != null) {
            for (String str2 : str.split("&")) {
                String[] strArrSplit = str2.split("=");
                bundle.putString(strArrSplit[0], strArrSplit[1]);
            }
        }
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bundle parseUrl(String str) {
        try {
            URL url = new URL(str);
            Bundle bundleDecodeUrl = decodeUrl(url.getQuery());
            bundleDecodeUrl.putAll(decodeUrl(url.getRef()));
            return bundleDecodeUrl;
        } catch (MalformedURLException unused) {
            return new Bundle();
        }
    }

    @Override // com.androidquery.auth.AccountHandle
    public boolean expired(AbstractAjaxCallback<?, ?> abstractAjaxCallback, AjaxStatus ajaxStatus) {
        int code = ajaxStatus.getCode();
        if (code == 200) {
            return false;
        }
        String error = ajaxStatus.getError();
        if (error != null && error.contains("OAuthException")) {
            AQUtility.debug("fb token expired");
            return true;
        }
        String url = abstractAjaxCallback.getUrl();
        if (code == 400 && (url.endsWith("/likes") || url.endsWith("/comments") || url.endsWith("/checkins"))) {
            return false;
        }
        if (code == 403 && (url.endsWith("/feed") || url.contains("method=delete"))) {
            return false;
        }
        return code == 400 || code == 401 || code == 403;
    }

    @Override // com.androidquery.auth.AccountHandle
    public boolean reauth(final AbstractAjaxCallback<?, ?> abstractAjaxCallback) {
        AQUtility.debug("reauth requested");
        this.token = null;
        AQUtility.post(new Runnable() { // from class: com.androidquery.auth.FacebookHandle.1
            @Override // java.lang.Runnable
            public void run() {
                FacebookHandle.this.auth(abstractAjaxCallback);
            }
        });
        return false;
    }

    @Override // com.androidquery.auth.AccountHandle
    public String getNetworkUrl(String str) {
        String str2;
        if (str.indexOf(63) == -1) {
            str2 = String.valueOf(str) + "?";
        } else {
            str2 = String.valueOf(str) + "&";
        }
        return String.valueOf(str2) + "access_token=" + this.token;
    }

    @Override // com.androidquery.auth.AccountHandle
    public String getCacheUrl(String str) {
        return getNetworkUrl(str);
    }

    @Override // com.androidquery.auth.AccountHandle
    public boolean authenticated() {
        return this.token != null;
    }

    @Override // com.androidquery.auth.AccountHandle
    public void unauth() {
        this.token = null;
        CookieSyncManager.createInstance(this.act);
        CookieManager.getInstance().removeAllCookie();
        storeToken(null, null);
    }

    private boolean startSingleSignOn(Activity activity, String str, String str2, int i) {
        Intent intent = new Intent();
        intent.setClassName("com.facebook.katana", "com.facebook.katana.ProxyAuth");
        intent.putExtra("client_id", str);
        if (str2 != null) {
            intent.putExtra("scope", str2);
        }
        if (!validateAppSignatureForIntent(activity, intent)) {
            return false;
        }
        try {
            activity.startActivityForResult(intent, i);
            return true;
        } catch (ActivityNotFoundException unused) {
            return false;
        }
    }

    public boolean isSSOAvailable() {
        if (hasSSO == null) {
            Intent intent = new Intent();
            intent.setClassName("com.facebook.katana", "com.facebook.katana.ProxyAuth");
            hasSSO = Boolean.valueOf(validateAppSignatureForIntent(this.act, intent));
        }
        return hasSSO.booleanValue();
    }

    public void ajaxProfile(AjaxCallback<JSONObject> ajaxCallback) {
        ajaxProfile(ajaxCallback, 0L);
    }

    public void ajaxProfile(AjaxCallback<JSONObject> ajaxCallback, long j) {
        new AQuery(this.act).auth(this).ajax("https://graph.facebook.com/me", JSONObject.class, j, ajaxCallback);
    }

    private boolean validateAppSignatureForIntent(Context context, Intent intent) {
        PackageManager packageManager = context.getPackageManager();
        ResolveInfo resolveInfoResolveActivity = packageManager.resolveActivity(intent, 0);
        if (resolveInfoResolveActivity == null) {
            return false;
        }
        try {
            for (Signature signature : packageManager.getPackageInfo(resolveInfoResolveActivity.activityInfo.packageName, 64).signatures) {
                if (signature.toCharsString().equals(FB_APP_SIGNATURE)) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        AQUtility.debug("on result", Integer.valueOf(i2));
        if (i2 != -1) {
            if (i2 == 0) {
                failure();
                return;
            }
            return;
        }
        String stringExtra = intent.getStringExtra(C1708b.f3698J);
        if (stringExtra == null) {
            stringExtra = intent.getStringExtra("error_type");
        }
        if (stringExtra != null) {
            AQUtility.debug(C1708b.f3698J, stringExtra);
            if (stringExtra.equals("service_disabled") || stringExtra.equals("AndroidAuthKillSwitchException")) {
                webAuth();
                return;
            }
            String stringExtra2 = intent.getStringExtra("error_description");
            AQUtility.debug("fb error", stringExtra2);
            Log.e("fb error", stringExtra2);
            failure(stringExtra2);
            return;
        }
        this.token = intent.getStringExtra("access_token");
        AQUtility.debug("onComplete", this.token);
        if (this.token != null) {
            storeToken(this.token, this.permissions);
            this.first = false;
            authenticated(this.token);
            success(this.act);
            return;
        }
        failure();
    }
}
