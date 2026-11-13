package com.p024ds.daisi.mvp.managers;

import android.content.Context;
import android.net.Uri;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.orhanobut.logger.Logger;
import com.p024ds.daisi.entity.TemplateStatus;
import com.p024ds.daisi.net.URL.MyBuildConfig;
import com.p024ds.daisi.net.URL.URLConstant;
import com.p024ds.daisi.net.volley.VolleyManager;
import com.p024ds.daisi.net.volley.VolleyStrRequestManager;
import com.p024ds.daisi.util.AppDeviceUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class TemplateManager implements BaseManager {
    private Callback<TemplateStatus> callback;

    private void templateChecked(String str, final Callback<TemplateStatus> callback, Context context, String str2) {
        this.callback = callback;
        VolleyManager.requestStringGet(str2, str, new VolleyStrRequestManager(context) { // from class: com.ds.daisi.mvp.managers.TemplateManager.1
            @Override // com.p024ds.daisi.net.volley.VolleyStrRequestManager
            public void onRequestSuccessRes(String str3) {
                Logger.m3442e("返回的数据：" + str3, new Object[0]);
                try {
                    JSONObject jSONObject = new JSONObject(str3).getJSONObject(URLConstant.TOKEN_PARAMS_DATA);
                    TemplateStatus templateStatus = new TemplateStatus();
                    templateStatus.status = jSONObject.getString("Code");
                    templateStatus.remark = jSONObject.getString("Remark");
                    callback.finish(templateStatus);
                } catch (JSONException e) {
                    ThrowableExtension.printStackTrace(e);
                }
            }

            @Override // com.p024ds.daisi.net.volley.VolleyStrRequestManager
            public void onRequestErrorRes(String str3) {
                callback.error(str3);
            }
        });
    }

    public void singleApkOrTemplateChecked(String str, Callback<TemplateStatus> callback, Context context, String str2) {
        Uri.Builder builder;
        if (str2.equals(URLConstant.MONOMER_INSTALLATION_PACKAGE)) {
            builder = MyBuildConfig.getBuilder(MyBuildConfig.AUTHORITY_VERIFY, str2).appendQueryParameter(URLConstant.MONOMER_PACKAGE_NAME, context.getPackageName());
        } else {
            builder = MyBuildConfig.getBuilder(MyBuildConfig.AUTHORITY_VERIFY, str2);
        }
        String string = builder.appendQueryParameter(URLConstant.TEMPLATE_TYPE, URLConstant.PAY_OR_Free).appendQueryParameter(URLConstant.TEMPLATE_VERSION, AppDeviceUtils.getVersionName(context)).build().toString();
        Logger.m3442e("模板url:" + string, new Object[0]);
        templateChecked(str, callback, context, string);
    }
}
