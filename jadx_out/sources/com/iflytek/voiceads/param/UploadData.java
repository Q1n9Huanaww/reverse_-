package com.iflytek.voiceads.param;

import android.content.Context;
import com.iflytek.voiceads.config.AdKeys;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.utils.C1589c;
import com.iflytek.voiceads.utils.C1593g;
import com.umeng.commonsdk.proguard.C1799v;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class UploadData {
    public static final String API_VERSION = "3.0.2";
    public static final String SDK_VERSION = "4.0.1";
    public static C1570a mAdParam;
    public static Context mContext;
    private static JSONObject mCurDeviceInfo;
    private static JSONObject mCurExtraInfo;

    public static String getAdStatis() {
        try {
            boolean zM3178c = C1589c.m3178c(mContext, "dataToggle");
            if (mContext == null || !zM3178c) {
                return "";
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("last_session_id", C1589c.m3177b(mContext, "sessionID"));
            jSONObject.put("last_adunit_id", C1589c.m3177b(mContext, "adUnitID"));
            jSONObject.put("last_req_duration", C1589c.m3172a(mContext, "reqDuration"));
            jSONObject.put("last_imp_duration", C1589c.m3172a(mContext, "impFailCnt"));
            jSONObject.put("last_clk_duration", C1589c.m3172a(mContext, "clkFailCnt"));
            jSONObject.put("req_fail_cnt", C1589c.m3172a(mContext, "reqFailCnt"));
            jSONObject.put("imp_fail_cnt", C1589c.m3172a(mContext, "impFailCnt"));
            jSONObject.put("clk_fail_cnt", C1589c.m3172a(mContext, "clkFailCnt"));
            return jSONObject.toString();
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "getAdStatis error " + e.getMessage());
            return "";
        }
    }

    public static String getApiVer() {
        return API_VERSION;
    }

    public static String getAppInfo() {
        return (mAdParam == null || mAdParam.m3078a(AdKeys.APP_VER) == null) ? "" : C1571b.m3089a(mAdParam);
    }

    public static String getCurrency() {
        if (mAdParam == null || mAdParam.m3078a(AdKeys.CURRENCY) == null) {
            return "";
        }
        try {
            JSONArray jSONArray = new JSONArray();
            for (String str : mAdParam.m3082b(AdKeys.CURRENCY).split(",")) {
                jSONArray.put(str);
            }
            return jSONArray.toString();
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "getCurrency error " + e.getMessage());
            return "CNY";
        }
    }

    public static String getDevice() {
        if (mContext == null) {
            return "";
        }
        try {
            if (mCurDeviceInfo == null) {
                mCurDeviceInfo = C1573d.m3091a(mContext);
            }
            mCurDeviceInfo.put("ts", C1573d.m3090a());
            return mCurDeviceInfo.toString();
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "getDevice error " + e.getMessage());
            return "";
        }
    }

    public static String getExt() {
        return (mAdParam == null || mAdParam.m3078a(AdKeys.EXT) == null) ? "" : mAdParam.m3078a(AdKeys.EXT).toString();
    }

    public static String getExtraInfo() {
        if (mContext == null) {
            return "";
        }
        try {
            if (mCurExtraInfo == null) {
                mCurExtraInfo = C1574e.m3115a(mContext);
            }
            mCurExtraInfo.put(C1799v.f4280S, C1574e.m3116b(mContext));
            return mCurExtraInfo.toString().replaceAll("\"", "\\\\\"");
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "getDevice error " + e.getMessage());
            return "";
        }
    }

    public static String getImps() {
        return mAdParam == null ? "" : C1576g.m3143a(mAdParam);
    }

    public static String getRequestID() {
        return mAdParam == null ? "" : mAdParam.m3082b(AdKeys.REQUEST_ID);
    }

    public static String getSdkVer() {
        return SDK_VERSION;
    }

    public static void initParam(Context context, C1570a c1570a) {
        mContext = context;
        mAdParam = c1570a;
    }
}
