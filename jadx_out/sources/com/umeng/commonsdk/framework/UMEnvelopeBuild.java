package com.umeng.commonsdk.framework;

import android.content.Context;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class UMEnvelopeBuild {
    public static boolean isReadyBuild(Context context, UMLogDataProtocol.UMBusinessType uMBusinessType) {
        return C1731a.m3711a(context, uMBusinessType);
    }

    public static JSONObject buildEnvelopeWithExtHeader(Context context, JSONObject jSONObject, JSONObject jSONObject2) {
        return C1731a.m3710a(context, jSONObject, jSONObject2);
    }

    public static String imprintProperty(Context context, String str, String str2) {
        return C1731a.m3709a(context, str, str2);
    }

    public static long maxDataSpace(Context context) {
        return C1731a.m3712b(context);
    }

    public static long lastSuccessfulBuildTime(Context context) {
        return C1731a.m3708a(context);
    }
}
