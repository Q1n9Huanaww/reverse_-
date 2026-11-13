package com.p024ds.daisi.log.engine;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.cyjh.feedback.lib.utils.URLConstant;
import com.cyjh.mobileanjian.ipc.stuff.MqmCode;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.entity.UpdateInfo;
import com.p024ds.daisi.mvp.managers.AccountManager;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ScriptUnexpectedStopLog extends BaseLog {
    private static final String API_NAME = "SetErrorLog";
    private String extraLog;
    private int mqmCode;

    public ScriptUnexpectedStopLog(Context context) {
        super(context);
    }

    public ScriptUnexpectedStopLog setMqmCode(int i) {
        this.mqmCode = i;
        return this;
    }

    public ScriptUnexpectedStopLog setExtraLog(String str) {
        this.extraLog = str;
        return this;
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
            jSONObject2.put("AppID", metaData.appId);
            jSONObject2.put("AppVersion", metaData.elfinVersion);
            jSONObject2.put(UpdateInfo.VERSION, metaData.appVersion);
            jSONObject2.put("IsFree", metaData.isFreeElfin ? "1" : AccountManager.TYPE_LOGIN);
            jSONObject2.put("NetworkType", getNetworkType(this.mContext));
            jSONObject2.put("MqmCode", String.valueOf(this.mqmCode));
            jSONObject2.put("MqmMessage", MqmCode.getMessageFromCode(this.mqmCode));
            jSONObject2.put("ExtraMessage", this.extraLog);
            jSONObject.put(com.p024ds.daisi.net.URL.URLConstant.TOKEN_PARAMS_DATA, jSONObject2);
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
        return jSONObject.toString();
    }

    private static String getNetworkType(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return "No Network";
        }
        switch (activeNetworkInfo.getType()) {
            case 0:
                return "MOBILE";
            case 1:
                return "WIFI";
            default:
                return "UNKNOWN";
        }
    }
}
