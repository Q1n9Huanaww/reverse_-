package com.androidquery.auth;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import com.androidquery.AQuery;
import com.androidquery.callback.AbstractAjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.AQUtility;
import com.androidquery.util.Constants;
import org.apache.http.HttpRequest;

/* JADX INFO: loaded from: classes.dex */
public class GoogleHandle extends AccountHandle implements DialogInterface.OnClickListener, DialogInterface.OnCancelListener {
    private Account acc;
    private Account[] accs;
    private Activity act;

    /* JADX INFO: renamed from: am */
    private AccountManager f53am;
    private String email;
    private String token;
    private String type;

    public GoogleHandle(Activity activity, String str, String str2) {
        str2 = Constants.ACTIVE_ACCOUNT.equals(str2) ? getActiveAccount(activity) : str2;
        this.act = activity;
        this.type = str.substring(2);
        this.email = str2;
        this.f53am = AccountManager.get(activity);
    }

    @Override // com.androidquery.auth.AccountHandle
    protected void auth() {
        if (this.email == null) {
            accountDialog();
            return;
        }
        for (Account account : this.f53am.getAccountsByType("com.google")) {
            if (this.email.equals(account.name)) {
                auth(account);
                return;
            }
        }
    }

    @Override // com.androidquery.auth.AccountHandle
    public boolean reauth(AbstractAjaxCallback<?, ?> abstractAjaxCallback) {
        this.f53am.invalidateAuthToken(this.acc.type, this.token);
        try {
            this.token = this.f53am.blockingGetAuthToken(this.acc, this.type, true);
            AQUtility.debug("re token", this.token);
        } catch (Exception e) {
            AQUtility.debug((Throwable) e);
            this.token = null;
        }
        return this.token != null;
    }

    public String getType() {
        return this.type;
    }

    private void accountDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.act);
        this.accs = this.f53am.getAccountsByType("com.google");
        int length = this.accs.length;
        if (length == 1) {
            auth(this.accs[0]);
            return;
        }
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = this.accs[i].name;
        }
        builder.setItems(strArr, this);
        builder.setOnCancelListener(this);
        new AQuery(this.act).show(builder.create());
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        Account account = this.accs[i];
        AQUtility.debug("acc", account.name);
        setActiveAccount(this.act, account.name);
        auth(account);
    }

    public static void setActiveAccount(Context context, String str) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(Constants.ACTIVE_ACCOUNT, str).commit();
    }

    public static String getActiveAccount(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(Constants.ACTIVE_ACCOUNT, null);
    }

    private void auth(Account account) {
        this.acc = account;
        new Task(this, null).execute(new String[0]);
    }

    private class Task extends AsyncTask<String, String, Bundle> {
        private Task() {
        }

        /* synthetic */ Task(GoogleHandle googleHandle, Task task) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Bundle doInBackground(String... strArr) {
            try {
                return GoogleHandle.this.f53am.getAuthToken(GoogleHandle.this.acc, GoogleHandle.this.type, (Bundle) null, GoogleHandle.this.act, (AccountManagerCallback<Bundle>) null, (Handler) null).getResult();
            } catch (AuthenticatorException e) {
                AQUtility.debug((Throwable) e);
                return null;
            } catch (OperationCanceledException unused) {
                return null;
            } catch (Exception e2) {
                AQUtility.debug((Throwable) e2);
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Bundle bundle) {
            if (bundle == null || !bundle.containsKey("authtoken")) {
                GoogleHandle.this.failure(GoogleHandle.this.act, AjaxStatus.AUTH_ERROR, "rejected");
                return;
            }
            GoogleHandle.this.token = bundle.getString("authtoken");
            GoogleHandle.this.success(GoogleHandle.this.act);
        }
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        failure(this.act, AjaxStatus.AUTH_ERROR, "cancel");
    }

    @Override // com.androidquery.auth.AccountHandle
    public boolean expired(AbstractAjaxCallback<?, ?> abstractAjaxCallback, AjaxStatus ajaxStatus) {
        int code = ajaxStatus.getCode();
        return code == 401 || code == 403;
    }

    @Override // com.androidquery.auth.AccountHandle
    public void applyToken(AbstractAjaxCallback<?, ?> abstractAjaxCallback, HttpRequest httpRequest) {
        httpRequest.addHeader("Authorization", "GoogleLogin auth=" + this.token);
    }

    @Override // com.androidquery.auth.AccountHandle
    public String getCacheUrl(String str) {
        return String.valueOf(str) + "#" + this.token;
    }

    @Override // com.androidquery.auth.AccountHandle
    public boolean authenticated() {
        return this.token != null;
    }
}
