package com.p024ds.daisi.log.engine;

import android.content.Context;
import com.cyjh.feedback.lib.utils.URLConstant;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.entity.UpdateInfo;
import com.p024ds.daisi.mvp.managers.AccountManager;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AppStartLog extends BaseLog {
    private static final String API_NAME = "SetOperationLog";
    private static final int LOG_TYPE = 3;

    public AppStartLog(Context context) {
        super(context);
    }

    @Override // com.p024ds.daisi.log.engine.BaseLog
    protected void initUrl() {
        this.urlApi = URLConstant.BASE_URL + File.separator + API_NAME;
    }

    @Override // com.p024ds.daisi.log.engine.BaseLog
    protected String generateLog() {
        MetaData metaData = MetaData.getInstance();
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("PhoneModel", metaData.model);
            jSONObject2.put("MACMD5", metaData.macMd5Sum);
            jSONObject2.put("AndroidVersion", metaData.androidVersion);
            jSONObject2.put("ROM", metaData.romVersion);
            jSONObject2.put(UpdateInfo.PACKAGENAME, metaData.packageName);
            jSONObject2.put("IsNew", metaData.isNewUser ? "1" : AccountManager.TYPE_LOGIN);
            jSONObject2.put("AppID", metaData.appId);
            jSONObject2.put("AppVersion", metaData.elfinVersion);
            jSONObject2.put(UpdateInfo.VERSION, metaData.appVersion);
            jSONObject2.put("IsFree", metaData.isFreeElfin ? "1" : AccountManager.TYPE_LOGIN);
            jSONObject2.put("UsedTime", AccountManager.TYPE_LOGIN);
            jSONObject2.put("IsRedFingerUser", MetaData.sIsRedFinger ? "1" : AccountManager.TYPE_LOGIN);
            jSONObject.put("LogType", String.valueOf(3));
            jSONObject.put(com.p024ds.daisi.net.URL.URLConstant.TOKEN_PARAMS_DATA, jSONObject2);
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
        return jSONObject.toString();
    }
}
