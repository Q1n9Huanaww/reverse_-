package com.p024ds.daisi.mvp.managers;

import android.content.Context;
import com.orhanobut.logger.Logger;
import com.p024ds.daisi.mvp.presenters.BasePresenter;
import com.p024ds.daisi.net.URL.MyBuildConfig;
import com.p024ds.daisi.net.URL.URLConstant;
import com.p024ds.daisi.net.volley.VolleyManager;
import com.p024ds.daisi.net.volley.VolleyStrRequestManager;

/* JADX INFO: loaded from: classes.dex */
public class TokenBillManager implements BasePresenter {
    public void getCodes(Context context, String str, final Callback<String> callback) {
        String string = MyBuildConfig.getBuilder(MyBuildConfig.AUTHORITY, URLConstant.CODES_SOURCE_NAME).build().toString();
        Logger.m3442e("url:" + string, new Object[0]);
        VolleyManager.requestStringGet(string, str, new VolleyStrRequestManager(context) { // from class: com.ds.daisi.mvp.managers.TokenBillManager.1
            @Override // com.p024ds.daisi.net.volley.VolleyStrRequestManager
            public void onRequestSuccessRes(String str2) {
                callback.finish(str2);
            }

            @Override // com.p024ds.daisi.net.volley.VolleyStrRequestManager
            public void onRequestErrorRes(String str2) {
                Logger.m3442e("VerifyTokenManager:" + str2, new Object[0]);
                callback.error(str2);
            }
        });
    }
}
