package com.p024ds.daisi.mvp.managers;

import android.content.Context;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.orhanobut.logger.Logger;
import com.p024ds.daisi.entity.AccountPhone;
import com.p024ds.daisi.entity.AccountResult;
import com.p024ds.daisi.net.URL.MyBuildConfig;
import com.p024ds.daisi.net.URL.URLConstant;
import com.p024ds.daisi.net.volley.VolleyManager;
import com.p024ds.daisi.net.volley.VolleyStrRequestManager;
import org.json.JSONException;
import org.json.JSONObject;
import org.wlf.filedownloader.DownloadFileInfo;

/* JADX INFO: loaded from: classes.dex */
public class VerifyPermissionManager {
    public void checkedUserFreeze(Callback<AccountResult> callback, Context context) {
        requestNetworkByGet(callback, context, URLConstant.USER_CHECKED_ACCOUNT_FREEZE);
    }

    public void getScriptTryCount(Callback<AccountResult> callback, Context context) {
        requestNetworkByGet(callback, context, URLConstant.USER_SCRIPT_TRY_COUNT);
    }

    private void requestNetworkByGet(final Callback<AccountResult> callback, Context context, final String str) {
        VolleyManager.requestStringGet(getRequestUrl(context, str), VerifyPermissionManager.class.getSimpleName(), new VolleyStrRequestManager(context) { // from class: com.ds.daisi.mvp.managers.VerifyPermissionManager.1
            @Override // com.p024ds.daisi.net.volley.VolleyStrRequestManager
            public void onRequestSuccessRes(String str2) {
                Logger.m3442e("请求返回的数据：" + str2, new Object[0]);
                AccountResult accountResult = new AccountResult();
                try {
                    JSONObject jSONObject = new JSONObject(str2);
                    accountResult.Code = jSONObject.getString("Code");
                    if (str.equals(URLConstant.USER_SCRIPT_TRY_COUNT)) {
                        accountResult.TryCount = jSONObject.getString("TryCount");
                    } else {
                        accountResult.Msg = jSONObject.getString("Msg");
                    }
                    callback.finish(accountResult);
                } catch (JSONException e) {
                    ThrowableExtension.printStackTrace(e);
                }
            }

            @Override // com.p024ds.daisi.net.volley.VolleyStrRequestManager
            public void onRequestErrorRes(String str2) {
                Logger.m3442e("错误的信息：" + str2, new Object[0]);
                callback.error(str2);
            }
        });
    }

    private String getRequestUrl(Context context, String str) {
        String string = MyBuildConfig.getBuilder(MyBuildConfig.AUTHORITY, str).appendQueryParameter(URLConstant.USER_ACCOUNT, AccountPhone.getPhone()).appendQueryParameter("packagename", context.getPackageName()).build().toString();
        Logger.m3442e(DownloadFileInfo.Table.COLUMN_NAME_OF_FIELD_URL + string, new Object[0]);
        return string;
    }
}
