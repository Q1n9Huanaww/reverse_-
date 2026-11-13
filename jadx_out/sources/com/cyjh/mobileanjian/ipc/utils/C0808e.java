package com.cyjh.mobileanjian.ipc.utils;

import android.content.Context;
import android.os.SystemClock;
import com.cyjh.mobileanjian.ipc.stuff.C0774a;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;
import com.p024ds.daisi.mvp.managers.AccountManager;
import com.p024ds.daisi.net.URL.URLConstant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.utils.e */
/* JADX INFO: compiled from: DbLog.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0808e {

    /* JADX INFO: renamed from: a */
    private static final String f1419a = "http://logapi.mobileanjian.com/api/SetLog";

    /* JADX INFO: renamed from: b */
    private static final int f1420b = 4;

    /* JADX INFO: renamed from: c */
    private static final String f1421c = "RunScriptDurationApi";

    /* JADX INFO: renamed from: d */
    private static String f1422d;

    /* JADX INFO: renamed from: a */
    private static String m1079a(Context context, long... jArr) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("AppID", C0774a.m814c());
            jSONObject2.put("MachineCode", C0821r.m1120a(context));
            jSONObject2.put("AppVersion", C0774a.m810b());
            jSONObject2.put("IsFree", C0774a.m809a() ? "1" : AccountManager.TYPE_LOGIN);
            jSONObject2.put("UsedTime", String.valueOf((SystemClock.uptimeMillis() - jArr[0]) / 1000));
            jSONObject.put("LogType", AccountManager.TYPE_GET_PHONE);
            jSONObject.put(URLConstant.TOKEN_PARAMS_DATA, jSONObject2);
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
        return jSONObject.toString();
    }

    /* JADX INFO: renamed from: a */
    private static void m1080a(Context context, long j) {
        f1422d = f1419a;
        new StringBuilder("log uri: ").append(f1422d);
        HttpUtils httpUtils = new HttpUtils();
        RequestParams requestParams = new RequestParams();
        requestParams.addQueryStringParameter(URLConstant.TOKEN_PARAMS_DATA, m1079a(context, j));
        httpUtils.send(HttpRequest.HttpMethod.GET, f1422d, requestParams, null);
    }
}
