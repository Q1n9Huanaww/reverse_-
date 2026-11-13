package com.iflytek.voiceads.p065d;

import com.iflytek.voiceads.config.AdError;
import com.iflytek.voiceads.config.AdKeys;
import com.iflytek.voiceads.config.ErrorCode;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.utils.C1593g;
import com.p024ds.daisi.net.URL.URLConstant;
import com.umeng.analytics.pro.C1708b;
import com.umeng.commonsdk.proguard.C1799v;
import org.apache.commons.mail.EmailConstants;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.iflytek.voiceads.d.a */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1519a {

    /* JADX INFO: renamed from: A */
    public double f3131A;

    /* JADX INFO: renamed from: B */
    public String f3132B;

    /* JADX INFO: renamed from: C */
    public String f3133C;

    /* JADX INFO: renamed from: D */
    public String f3134D;

    /* JADX INFO: renamed from: E */
    public String f3135E;

    /* JADX INFO: renamed from: F */
    public String f3136F;

    /* JADX INFO: renamed from: G */
    public JSONArray f3137G;

    /* JADX INFO: renamed from: H */
    public JSONObject f3138H;

    /* JADX INFO: renamed from: I */
    public String f3139I;

    /* JADX INFO: renamed from: J */
    public JSONObject f3140J;

    /* JADX INFO: renamed from: K */
    public String f3141K;

    /* JADX INFO: renamed from: L */
    public String f3142L;

    /* JADX INFO: renamed from: M */
    public int f3143M;

    /* JADX INFO: renamed from: N */
    public String f3144N;

    /* JADX INFO: renamed from: a */
    public int f3145a;

    /* JADX INFO: renamed from: b */
    public String f3146b;

    /* JADX INFO: renamed from: c */
    public double f3147c;

    /* JADX INFO: renamed from: d */
    public String f3148d;

    /* JADX INFO: renamed from: e */
    public JSONObject f3149e;

    /* JADX INFO: renamed from: f */
    public JSONObject f3150f;

    /* JADX INFO: renamed from: g */
    public JSONObject f3151g;

    /* JADX INFO: renamed from: h */
    public JSONObject f3152h;

    /* JADX INFO: renamed from: i */
    public JSONObject f3153i;

    /* JADX INFO: renamed from: j */
    public JSONObject f3154j;

    /* JADX INFO: renamed from: k */
    public JSONObject f3155k;

    /* JADX INFO: renamed from: l */
    public String f3156l;

    /* JADX INFO: renamed from: m */
    public String f3157m;

    /* JADX INFO: renamed from: n */
    public JSONObject f3158n;

    /* JADX INFO: renamed from: o */
    public String f3159o;

    /* JADX INFO: renamed from: p */
    public String f3160p;

    /* JADX INFO: renamed from: q */
    public String f3161q;

    /* JADX INFO: renamed from: r */
    public int f3162r;

    /* JADX INFO: renamed from: s */
    public String f3163s;

    /* JADX INFO: renamed from: t */
    public String f3164t;

    /* JADX INFO: renamed from: u */
    public String f3165u;

    /* JADX INFO: renamed from: v */
    public String f3166v;

    /* JADX INFO: renamed from: w */
    public double f3167w;

    /* JADX INFO: renamed from: x */
    public String f3168x;

    /* JADX INFO: renamed from: y */
    public int f3169y;

    /* JADX INFO: renamed from: z */
    public double f3170z;

    /* JADX INFO: renamed from: a */
    public void m2903a() {
        this.f3145a = -1;
        this.f3146b = "";
        this.f3147c = -1.0d;
        this.f3148d = "";
        this.f3149e = null;
        this.f3150f = null;
        this.f3151g = null;
        this.f3152h = null;
        this.f3153i = null;
        this.f3154j = null;
        this.f3155k = null;
        this.f3156l = "";
        this.f3157m = "";
        this.f3158n = null;
        this.f3159o = "";
        this.f3160p = "";
        this.f3161q = "";
        this.f3162r = -1;
        this.f3163s = "";
        this.f3164t = "";
        this.f3165u = "";
        this.f3166v = "";
        this.f3167w = -1.0d;
        this.f3168x = "";
        this.f3169y = -1;
        this.f3170z = -1.0d;
        this.f3131A = -1.0d;
        this.f3132B = "";
        this.f3133C = "";
        this.f3134D = "";
        this.f3135E = "";
        this.f3136F = "";
        this.f3137G = null;
        this.f3138H = null;
        this.f3140J = null;
        this.f3141K = "";
        this.f3142L = "";
        this.f3143M = -1;
        this.f3144N = "";
    }

    /* JADX INFO: renamed from: a */
    public void m2904a(JSONObject jSONObject) throws AdError {
        try {
            m2903a();
            this.f3145a = jSONObject.optInt("template_id");
            this.f3146b = jSONObject.optString("creative_id");
            this.f3147c = jSONObject.optDouble("price");
            this.f3148d = jSONObject.optString("deal_id");
            this.f3149e = jSONObject.optJSONObject(EmailConstants.TEXT_SUBTYPE_HTML);
            this.f3150f = jSONObject.optJSONObject("img");
            this.f3151g = jSONObject.optJSONObject("img1");
            this.f3152h = jSONObject.optJSONObject("img2");
            this.f3153i = jSONObject.optJSONObject("img3");
            this.f3154j = jSONObject.optJSONObject("video");
            this.f3155k = jSONObject.optJSONObject("audio");
            this.f3156l = jSONObject.optString("title");
            this.f3157m = jSONObject.optString("desc");
            this.f3158n = jSONObject.optJSONObject("icon");
            this.f3159o = jSONObject.optString(C1708b.f3711W);
            this.f3160p = jSONObject.optString("ctatext");
            this.f3161q = jSONObject.optString("app_name");
            this.f3162r = jSONObject.optInt("downloads");
            this.f3163s = jSONObject.optString("rating");
            this.f3164t = jSONObject.optString("app_download_url");
            this.f3165u = jSONObject.optString("app_intro_url");
            this.f3166v = jSONObject.optString(AdKeys.APP_VER);
            this.f3167w = jSONObject.optDouble("app_size");
            this.f3168x = jSONObject.optString(URLConstant.USER_ACCOUNT);
            this.f3169y = jSONObject.optInt("likes");
            this.f3170z = jSONObject.optDouble("original_price");
            this.f3131A = jSONObject.optDouble("current_price");
            this.f3132B = jSONObject.optString("sponsored");
            this.f3133C = jSONObject.optString("address");
            this.f3134D = jSONObject.optString("app_id");
            this.f3135E = jSONObject.optString(C1799v.f4319j);
            this.f3136F = jSONObject.optString("brand");
            this.f3137G = jSONObject.optJSONArray("display_labels");
            this.f3138H = jSONObject.optJSONObject("voice_ad_url");
            this.f3139I = jSONObject.optString("close_icon");
            this.f3140J = jSONObject.optJSONObject("monitor");
            this.f3141K = jSONObject.optString("deeplink");
            this.f3142L = jSONObject.optString("landing");
            this.f3143M = jSONObject.optInt("action_type");
            this.f3144N = jSONObject.optString("ad_source_mark");
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "parseAdData error:" + e.getMessage());
            throw new AdError(ErrorCode.ERROR_SERVER);
        }
    }
}
