package com.p024ds.daisi.mvp.managers;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.p024ds.daisi.constant.Constants;
import com.p024ds.daisi.entity.AccountResult;
import com.p024ds.daisi.entity.AccountUser;
import com.p024ds.daisi.net.URL.URLConstant;
import com.p024ds.daisi.net.volley.VolleyRequestQueue;
import com.p024ds.daisi.util.DesUtil;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AccountManager implements BaseManager {
    public static final String ACCOUNT_TAG = "AccountManager";
    public static final String CODE_TYPE_FORGET_PASSWORD = "2";
    public static final String CODE_TYPE_REGISTER = "1";
    public static final String CODE_TYPE_RESET_PASSWORD = "3";
    public static final String TYPE_ALTER_PASSWORD = "5";
    public static final String TYPE_CHECKED_ACCOUNT_DUPLICATED = "7";
    public static final String TYPE_FIND_PASSWORD = "2";
    public static final String TYPE_FIND_PASSWORD_CODE = "6";
    public static final String TYPE_GET_PHONE = "4";
    public static final String TYPE_LICENSE = "3";
    public static final String TYPE_LOGIN = "0";
    public static final String TYPE_REGISTER = "1";
    private JsonObjectRequest jsonObjectRequest;
    private Callback<AccountResult> mCallback;

    public void phoneCodes(AccountUser accountUser, Callback<AccountResult> callback) {
        requestPostWithAccount(accountUser, callback);
    }

    public void register(AccountUser accountUser, Callback<AccountResult> callback) {
        requestPostWithAccount(accountUser, callback);
    }

    public void login(AccountUser accountUser, Callback<AccountResult> callback) {
        requestPostWithAccount(accountUser, callback);
    }

    public void alterPassword(AccountUser accountUser, Callback<AccountResult> callback) {
        requestPostWithAccount(accountUser, callback);
    }

    public void findPassword(AccountUser accountUser, Callback<AccountResult> callback) {
        requestPostWithAccount(accountUser, callback);
    }

    public void getPhoneByAccount(AccountUser accountUser, Callback<AccountResult> callback) {
        requestPostWithAccount(accountUser, callback);
    }

    public void checkedAccountDuplicated(AccountUser accountUser, Callback<AccountResult> callback) {
        requestPostWithAccount(accountUser, callback);
    }

    private void requestPostWithAccount(AccountUser accountUser, Callback<AccountResult> callback) {
        JSONObject jSONObject;
        this.mCallback = callback;
        String json = new Gson().toJson(accountUser);
        Logger.m3442e("转化为json后的数据：" + json, new Object[0]);
        try {
            jSONObject = new JSONObject("{\"data\":\"" + URLEncoder.encode(DesUtil.encode(json, Constants.DESkey, Constants.DESIV)) + "\"}");
            try {
                Logger.m3442e("Des加密后又JsonObject转化为字符串后的数据：" + jSONObject.toString(), new Object[0]);
            } catch (Exception e) {
                e = e;
                ThrowableExtension.printStackTrace(e);
            }
        } catch (Exception e2) {
            e = e2;
            jSONObject = null;
        }
        VolleyRequestQueue.getRequestQueue().cancelAll(ACCOUNT_TAG);
        this.jsonObjectRequest = new JsonObjectRequest(1, URLConstant.ACCOUNT_URL, jSONObject, new Response.Listener<JSONObject>() { // from class: com.ds.daisi.mvp.managers.AccountManager.1
            @Override // com.android.volley.Response.Listener
            public void onResponse(JSONObject jSONObject2) {
                Logger.m3442e(jSONObject2.toString(), new Object[0]);
                AccountManager.this.mCallback.finish(AccountManager.this.fillDataToAccount(jSONObject2));
            }
        }, new Response.ErrorListener() { // from class: com.ds.daisi.mvp.managers.AccountManager.2
            @Override // com.android.volley.Response.ErrorListener
            public void onErrorResponse(VolleyError volleyError) {
                AccountManager.this.mCallback.error(volleyError.getMessage());
            }
        });
        this.jsonObjectRequest.setTag(ACCOUNT_TAG);
        this.jsonObjectRequest.setShouldCache(false);
        VolleyRequestQueue.getRequestQueue().add(this.jsonObjectRequest);
    }

    public AccountResult fillDataToAccount(JSONObject jSONObject) {
        AccountResult accountResult = new AccountResult();
        try {
            accountResult.Code = jSONObject.getString("Code");
            accountResult.Msg = jSONObject.getString("Msg");
            accountResult.VerificationCode = jSONObject.getString("VerificationCode");
            accountResult.ExpireDate = jSONObject.getString("ExpireDate");
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
        return accountResult;
    }
}
