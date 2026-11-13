package com.iflytek.voiceads.param;

import com.iflytek.voiceads.bean.AdDeal;
import com.iflytek.voiceads.bean.AdDebug;
import com.iflytek.voiceads.config.AdKeys;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.utils.C1593g;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.iflytek.voiceads.param.g */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1576g {

    /* JADX INFO: renamed from: a */
    public static int[] f3375a = {1, 2};

    /* JADX INFO: renamed from: a */
    public static String m3143a(C1570a c1570a) {
        try {
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("adunit_id", c1570a.m3078a("adunit_id"));
            JSONArray jSONArray2 = new JSONArray();
            for (int i : f3375a) {
                jSONArray2.put(i);
            }
            jSONObject.put("dp_support_status", jSONArray2);
            jSONObject.put("voice_ad_support_status", 1);
            jSONObject.put("secure", 3);
            if (c1570a.m3078a("debug") != null) {
                AdDebug adDebug = (AdDebug) c1570a.m3078a("debug");
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("action_type", adDebug.action_type);
                jSONObject2.put("landing_type", adDebug.landing_type);
                jSONObject.put("debug", jSONObject2);
            }
            if (c1570a.m3078a(AdKeys.BID_FLOOR) != null) {
                jSONObject.put("bidfloor", c1570a.m3087e(AdKeys.BID_FLOOR));
            }
            Object objM3078a = c1570a.m3078a(AdKeys.PMP);
            if (objM3078a != null) {
                JSONArray jSONArray3 = new JSONArray();
                for (AdDeal adDeal : (List) objM3078a) {
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("id", adDeal.f3069id);
                    jSONObject3.put("bidfloor", adDeal.bidFloor);
                    jSONArray3.put(jSONObject3);
                }
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put("deals", jSONArray3);
                jSONObject.put(AdKeys.PMP, jSONObject4);
            }
            jSONArray.put(jSONObject);
            return jSONArray.toString();
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "getImpsInfo:" + e.getMessage());
            return "";
        }
    }
}
