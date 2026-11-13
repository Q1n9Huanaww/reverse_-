package com.p024ds.daisi.mvp.managers;

import android.content.Context;
import android.text.TextUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.orhanobut.logger.Logger;
import com.p024ds.daisi.net.URL.MyBuildConfig;
import com.p024ds.daisi.net.URL.URLConstant;
import com.p024ds.daisi.net.volley.VolleyManager;
import com.p024ds.daisi.net.volley.VolleyStrRequestManager;
import com.p024ds.daisi.util.ToastUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class BlackListManager extends TokenBillManager {
    public void isSetBlackList(final Context context, String str, final Callback<String> callback) {
        String blackListUrl = getBlackListUrl(context);
        Logger.m3442e("黑名单请求：" + blackListUrl, new Object[0]);
        VolleyManager.requestStringGet(blackListUrl, str, new VolleyStrRequestManager(context) { // from class: com.ds.daisi.mvp.managers.BlackListManager.1
            @Override // com.p024ds.daisi.net.volley.VolleyStrRequestManager
            public void onRequestSuccessRes(String str2) {
                Logger.m3442e("BlackListResult：" + str2, new Object[0]);
                String str3 = null;
                try {
                    String string = new JSONObject(str2).getString("Code");
                    Logger.m3442e("Code=" + string, new Object[0]);
                    if (string.equals(URLConstant.PAY_OR_Free)) {
                        str3 = string;
                    }
                } catch (JSONException e) {
                    ThrowableExtension.printStackTrace(e);
                }
                callback.finish(str3);
            }

            @Override // com.p024ds.daisi.net.volley.VolleyStrRequestManager
            public void onRequestErrorRes(String str2) {
                if (TextUtils.isEmpty(str2)) {
                    return;
                }
                ToastUtils.showToastLong(context, str2);
                callback.error(str2);
            }
        });
    }

    private String getBlackListUrl(Context context) {
        return MyBuildConfig.getBuilder(MyBuildConfig.AUTHORITY, URLConstant.BLACK_LIST_NAME).appendQueryParameter("packagename", context.getPackageName()).appendQueryParameter("type", URLConstant.PAY_OR_Free).build().toString();
    }
}
