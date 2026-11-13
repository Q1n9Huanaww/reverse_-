package com.androidquery.auth;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.androidquery.AQuery;
import com.androidquery.WebDialog;
import com.androidquery.callback.AbstractAjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.AQUtility;
import com.cyjh.mobileanjian.ipc.share.proto.UiMessage;
import java.net.HttpURLConnection;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import org.apache.http.HttpRequest;

/* JADX INFO: loaded from: classes.dex */
public class TwitterHandle extends AccountHandle {
    private static final String CALLBACK_URI = "twitter://callback";
    private static final String CANCEL_URI = "twitter://cancel";
    private static final String OAUTH_ACCESS_TOKEN = "https://api.twitter.com/oauth/access_token";
    private static final String OAUTH_AUTHORIZE = "https://api.twitter.com/oauth/authorize";
    private static final String OAUTH_REQUEST_TOKEN = "https://api.twitter.com/oauth/request_token";
    private static final String TW_SECRET = "aq.tw.secret";
    private static final String TW_TOKEN = "aq.tw.token";
    private Activity act;
    private CommonsHttpOAuthConsumer consumer;
    private WebDialog dialog;
    private CommonsHttpOAuthProvider provider;
    private String token = fetchToken(TW_TOKEN);
    private String secret = fetchToken(TW_SECRET);

    protected void authenticated(String str, String str2) {
    }

    public TwitterHandle(Activity activity, String str, String str2) {
        this.act = activity;
        this.consumer = new CommonsHttpOAuthConsumer(str, str2);
        if (this.token != null && this.secret != null) {
            this.consumer.setTokenWithSecret(this.token, this.secret);
        }
        this.provider = new CommonsHttpOAuthProvider(OAUTH_REQUEST_TOKEN, OAUTH_ACCESS_TOKEN, OAUTH_AUTHORIZE);
    }

    public String getToken() {
        return this.token;
    }

    public String getSecret() {
        return this.secret;
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

    /* JADX INFO: Access modifiers changed from: private */
    public void failure() {
        dismiss();
        failure(this.act, UiMessage.CommandToUi.Command_Type.GET_ENABLED_VALUE, "cancel");
    }

    @Override // com.androidquery.auth.AccountHandle
    protected void auth() {
        new Task(this, null).execute(new String[0]);
    }

    private class Task extends AsyncTask<String, String, String> implements DialogInterface.OnCancelListener, Runnable {

        /* JADX INFO: renamed from: cb */
        private AbstractAjaxCallback<?, ?> f54cb;

        private Task() {
        }

        /* synthetic */ Task(TwitterHandle twitterHandle, Task task) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public String doInBackground(String... strArr) {
            try {
                return TwitterHandle.this.provider.retrieveRequestToken(TwitterHandle.this.consumer, TwitterHandle.CALLBACK_URI);
            } catch (Exception e) {
                AQUtility.report(e);
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(String str) {
            if (str == null) {
                TwitterHandle.this.failure();
                return;
            }
            TwitterHandle.this.dialog = new WebDialog(TwitterHandle.this.act, str, new TwWebViewClient(TwitterHandle.this, null));
            TwitterHandle.this.dialog.setOnCancelListener(this);
            TwitterHandle.this.show();
            TwitterHandle.this.dialog.load();
        }

        @Override // android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialogInterface) {
            TwitterHandle.this.failure();
        }

        @Override // java.lang.Runnable
        public void run() {
            TwitterHandle.this.auth(this.f54cb);
        }
    }

    public void authenticate(boolean z) {
        if (!z && this.token != null && this.secret != null) {
            authenticated(this.secret, this.token);
        } else {
            auth();
        }
    }

    private String fetchToken(String str) {
        return PreferenceManager.getDefaultSharedPreferences(this.act).getString(str, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void storeToken(String str, String str2, String str3, String str4) {
        PreferenceManager.getDefaultSharedPreferences(this.act).edit().putString(str, str2).putString(str3, str4).commit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String extract(String str, String str2) {
        return Uri.parse(str).getQueryParameter(str2);
    }

    private class Task2 extends AsyncTask<String, String, String> {
        private Task2() {
        }

        /* synthetic */ Task2(TwitterHandle twitterHandle, Task2 task2) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public String doInBackground(String... strArr) {
            try {
                TwitterHandle.this.provider.retrieveAccessToken(TwitterHandle.this.consumer, strArr[0]);
                return "";
            } catch (Exception e) {
                AQUtility.report(e);
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(String str) {
            if (str == null) {
                TwitterHandle.this.failure();
                TwitterHandle.this.authenticated(null, null);
                return;
            }
            TwitterHandle.this.token = TwitterHandle.this.consumer.getToken();
            TwitterHandle.this.secret = TwitterHandle.this.consumer.getTokenSecret();
            AQUtility.debug("token", TwitterHandle.this.token);
            AQUtility.debug("secret", TwitterHandle.this.secret);
            TwitterHandle.this.storeToken(TwitterHandle.TW_TOKEN, TwitterHandle.this.token, TwitterHandle.TW_SECRET, TwitterHandle.this.secret);
            TwitterHandle.this.dismiss();
            TwitterHandle.this.success(TwitterHandle.this.act);
            TwitterHandle.this.authenticated(TwitterHandle.this.secret, TwitterHandle.this.token);
        }
    }

    private class TwWebViewClient extends WebViewClient {
        private TwWebViewClient() {
        }

        /* synthetic */ TwWebViewClient(TwitterHandle twitterHandle, TwWebViewClient twWebViewClient) {
            this();
        }

        private boolean checkDone(String str) {
            if (str.startsWith(TwitterHandle.CALLBACK_URI)) {
                String strExtract = TwitterHandle.this.extract(str, "oauth_verifier");
                TwitterHandle.this.dismiss();
                new Task2(TwitterHandle.this, null).execute(strExtract);
                return true;
            }
            if (!str.startsWith(TwitterHandle.CANCEL_URI)) {
                return false;
            }
            TwitterHandle.this.failure();
            return true;
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
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
            AQUtility.debug("finished", str);
            super.onPageFinished(webView, str);
            TwitterHandle.this.show();
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            TwitterHandle.this.failure();
        }
    }

    @Override // com.androidquery.auth.AccountHandle
    public boolean expired(AbstractAjaxCallback<?, ?> abstractAjaxCallback, AjaxStatus ajaxStatus) {
        int code = ajaxStatus.getCode();
        return code == 400 || code == 401;
    }

    @Override // com.androidquery.auth.AccountHandle
    public boolean reauth(AbstractAjaxCallback<?, ?> abstractAjaxCallback) {
        this.token = null;
        this.secret = null;
        storeToken(TW_TOKEN, null, TW_SECRET, null);
        new Task(this, null).f54cb = abstractAjaxCallback;
        AQUtility.post(abstractAjaxCallback);
        return false;
    }

    @Override // com.androidquery.auth.AccountHandle
    public void applyToken(AbstractAjaxCallback<?, ?> abstractAjaxCallback, HttpRequest httpRequest) {
        AQUtility.debug("apply token", abstractAjaxCallback.getUrl());
        try {
            this.consumer.sign(httpRequest);
        } catch (Exception e) {
            AQUtility.report(e);
        }
    }

    @Override // com.androidquery.auth.AccountHandle
    public void applyToken(AbstractAjaxCallback<?, ?> abstractAjaxCallback, HttpURLConnection httpURLConnection) {
        AQUtility.debug("apply token multipart", abstractAjaxCallback.getUrl());
        DefaultOAuthConsumer defaultOAuthConsumer = new DefaultOAuthConsumer(this.consumer.getConsumerKey(), this.consumer.getConsumerSecret());
        defaultOAuthConsumer.setTokenWithSecret(this.consumer.getToken(), this.consumer.getTokenSecret());
        try {
            defaultOAuthConsumer.sign(httpURLConnection);
        } catch (Exception e) {
            AQUtility.report(e);
        }
    }

    @Override // com.androidquery.auth.AccountHandle
    public boolean authenticated() {
        return (this.token == null || this.secret == null) ? false : true;
    }

    @Override // com.androidquery.auth.AccountHandle
    public void unauth() {
        this.token = null;
        this.secret = null;
        CookieSyncManager.createInstance(this.act);
        CookieManager.getInstance().removeAllCookie();
        storeToken(TW_TOKEN, null, TW_SECRET, null);
    }
}
