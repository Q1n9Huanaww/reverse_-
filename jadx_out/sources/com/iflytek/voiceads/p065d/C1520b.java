package com.iflytek.voiceads.p065d;

import android.content.Context;
import com.iflytek.voiceads.config.AdError;
import com.iflytek.voiceads.config.AdKeys;
import com.iflytek.voiceads.config.ErrorCode;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.utils.C1589c;
import com.iflytek.voiceads.utils.C1593g;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.iflytek.voiceads.d.b */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1520b {

    /* JADX INFO: renamed from: a */
    public int f3171a;

    /* JADX INFO: renamed from: b */
    public String f3172b;

    /* JADX INFO: renamed from: c */
    public String f3173c;

    /* JADX INFO: renamed from: d */
    public String f3174d;

    /* JADX INFO: renamed from: e */
    public String f3175e;

    /* JADX INFO: renamed from: f */
    public C1519a f3176f;

    /* JADX INFO: renamed from: g */
    public boolean f3177g;

    /* JADX INFO: renamed from: h */
    public JSONObject f3178h;

    /* JADX INFO: renamed from: i */
    public Context f3179i;

    public C1520b(Context context) {
        this.f3179i = context;
    }

    /* JADX INFO: renamed from: a */
    public void m2905a() {
        this.f3171a = -1;
        this.f3172b = "";
        this.f3173c = "";
        this.f3174d = "";
        this.f3175e = "";
        this.f3176f = null;
        this.f3177g = false;
        this.f3178h = null;
    }

    /* JADX INFO: renamed from: a */
    public void m2906a(String str) throws AdError {
        try {
            JSONObject jSONObject = new JSONObject(str);
            C1593g.m3188a(SDKConstants.TAG, "response:" + jSONObject.toString());
            m2905a();
            this.f3171a = jSONObject.optInt("rc");
            this.f3172b = jSONObject.optString("id");
            this.f3173c = jSONObject.optString("bid_id");
            this.f3174d = jSONObject.optString("info");
            this.f3175e = jSONObject.optString("cur");
            if (this.f3171a == 70200) {
                this.f3176f = new C1519a();
                this.f3176f.m2904a(jSONObject.optJSONArray("ads").getJSONObject(0));
            }
            if (jSONObject.has("extra_data_toggle")) {
                this.f3177g = jSONObject.optBoolean("extra_data_toggle");
                C1589c.m3176a(this.f3179i, "dataToggle", this.f3177g);
            }
            if (jSONObject.has(AdKeys.EXT)) {
                this.f3178h = jSONObject.optJSONObject(AdKeys.EXT);
            }
            C1589c.m3175a(this.f3179i, "sessionID", this.f3173c);
        } catch (JSONException e) {
            C1593g.m3190b(SDKConstants.TAG, "Invalid response data!");
            throw new AdError(ErrorCode.ERROR_SERVER);
        }
    }
}
