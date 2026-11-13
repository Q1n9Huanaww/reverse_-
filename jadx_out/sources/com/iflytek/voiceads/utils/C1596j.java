package com.iflytek.voiceads.utils;

import android.content.Context;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.request.C1578a;
import org.json.JSONArray;
import org.json.JSONException;
import org.wlf.filedownloader.base.BaseDownloadConfigBuilder;

/* JADX INFO: renamed from: com.iflytek.voiceads.utils.j */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1596j {
    /* JADX INFO: renamed from: a */
    public static JSONArray m3202a(double d, JSONArray jSONArray) {
        try {
            String string = jSONArray.toString();
            if (!string.contains("${AUCTION_PRICE}")) {
                return jSONArray;
            }
            if (d < 0.0d) {
                return null;
            }
            return new JSONArray(string.replace("${AUCTION_PRICE}", C1590d.m3180b(d + "")));
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "exp array" + e.getMessage());
            return jSONArray;
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m3203a(Object... objArr) {
        C1593g.m3188a(SDKConstants.TAG, "sendMonitor");
        try {
            JSONArray jSONArray = (JSONArray) objArr[0];
            if (jSONArray != null) {
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    String string = jSONArray.getString(i);
                    if (objArr.length <= 1 || i != 0) {
                        m3204b(string);
                    } else {
                        m3204b(string, objArr[1], objArr[2]);
                    }
                }
            }
        } catch (JSONException e) {
            C1593g.m3190b(SDKConstants.TAG, "MonitorUtil::sendMonitor() error");
        }
    }

    /* JADX INFO: renamed from: b */
    public static void m3204b(Object... objArr) {
        C1578a c1578a = new C1578a();
        if (objArr == null || objArr.length <= 1) {
            c1578a.m3158a(false);
        } else {
            c1578a.m3155a((Context) objArr[1]);
            c1578a.m3154a(((Integer) objArr[2]).intValue());
        }
        c1578a.m3159b(0);
        c1578a.m3160c(BaseDownloadConfigBuilder.DEFAULT_CONNECT_TIMEOUT);
        c1578a.m3157a((String) objArr[0], null, null);
        c1578a.m3156a((C1578a.a) null);
    }
}
