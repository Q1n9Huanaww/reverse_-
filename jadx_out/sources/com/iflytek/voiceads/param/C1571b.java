package com.iflytek.voiceads.param;

import com.iflytek.voiceads.config.AdKeys;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.utils.C1593g;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.iflytek.voiceads.param.b */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1571b {
    /* JADX INFO: renamed from: a */
    public static String m3089a(C1570a c1570a) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(AdKeys.APP_VER, c1570a.m3078a(AdKeys.APP_VER));
            return jSONObject.toString();
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "getAppInfo:" + e.getMessage());
            return "";
        }
    }
}
