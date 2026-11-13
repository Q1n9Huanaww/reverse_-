package com.iflytek.collector.p060a.p061a;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.iflytek.voiceads.config.SDKLogger;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.iflytek.collector.a.a.i */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1486i implements Runnable {

    /* JADX INFO: renamed from: a */
    private Context f3045a;

    /* JADX INFO: renamed from: b */
    private C1484g.a f3046b = new C1487j(this);

    RunnableC1486i(Context context) {
        this.f3045a = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public void m2830a(String str) {
        try {
            JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONObject("rsp");
            if (jSONObjectOptJSONObject != null) {
                String strOptString = jSONObjectOptJSONObject.optString("des");
                if (TextUtils.isEmpty(strOptString)) {
                    return;
                }
                SharedPreferences sharedPreferencesM2833b = C1488k.m2833b(this.f3045a);
                String string = sharedPreferencesM2833b.getString("ifly_dynamic_business_conf", "");
                SharedPreferences.Editor editorEdit = sharedPreferencesM2833b.edit();
                if ("no".equals(strOptString)) {
                    if (TextUtils.isEmpty(string)) {
                        return;
                    } else {
                        editorEdit.putString("ifly_dynamic_business_conf", "");
                    }
                } else if (strOptString.equals(string)) {
                    return;
                } else {
                    editorEdit.putString("ifly_dynamic_business_conf", strOptString);
                }
                editorEdit.apply();
            }
        } catch (Throwable th) {
            SDKLogger.m2902e("Collector", "parse result error:" + th.toString());
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        C1479b c1479bM2789a;
        JSONObject jSONObjectM2794a;
        try {
            if (!C1488k.m2831a(this.f3045a) || (jSONObjectM2794a = (c1479bM2789a = C1479b.m2789a(this.f3045a)).m2794a()) == null) {
                return;
            }
            C1484g c1484g = new C1484g();
            c1484g.m2825b(20000);
            c1484g.m2821a(1);
            byte[] bArrM2811a = C1483f.m2811a(jSONObjectM2794a.toString().getBytes("utf-8"));
            if (bArrM2811a != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("product=freelog&appid=" + c1479bM2789a.m2800e());
                String strM2801f = c1479bM2789a.m2801f();
                if (!TextUtils.isEmpty(strM2801f)) {
                    sb.append("&subid=" + strM2801f);
                }
                sb.append("&size=" + bArrM2811a.length + "&platform=android&source=daas&pv=1.1");
                c1484g.m2823a("https://log.iflytek.com/log", sb.toString(), bArrM2811a);
                c1484g.m2822a(this.f3046b);
                c1484g.m2820a();
            }
        } catch (Throwable th) {
            SDKLogger.m2902e("Collector", "exception occur while upload:" + th.toString());
        }
    }
}
