package com.iflytek.voiceads.p073e;

import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.p065d.C1519a;
import com.iflytek.voiceads.utils.C1593g;
import com.umeng.analytics.pro.C1708b;
import org.json.JSONArray;
import org.json.JSONObject;
import org.wlf.filedownloader.DownloadFileInfo;

/* JADX INFO: renamed from: com.iflytek.voiceads.e.g */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1564g {

    /* JADX INFO: renamed from: a */
    public String f3321a;

    /* JADX INFO: renamed from: b */
    public int f3322b;

    /* JADX INFO: renamed from: c */
    public int f3323c;

    /* JADX INFO: renamed from: d */
    public int f3324d;

    /* JADX INFO: renamed from: e */
    public int f3325e;

    /* JADX INFO: renamed from: f */
    public int f3326f;

    /* JADX INFO: renamed from: g */
    public long f3327g;

    /* JADX INFO: renamed from: h */
    public JSONObject f3328h;

    /* JADX INFO: renamed from: i */
    public JSONArray f3329i;

    /* JADX INFO: renamed from: j */
    public JSONArray f3330j;

    /* JADX INFO: renamed from: k */
    public JSONArray f3331k;

    /* JADX INFO: renamed from: l */
    public JSONArray f3332l;

    /* JADX INFO: renamed from: m */
    public JSONArray f3333m;

    /* JADX INFO: renamed from: n */
    public JSONArray f3334n;

    /* JADX INFO: renamed from: o */
    public JSONArray f3335o;

    /* JADX INFO: renamed from: p */
    public JSONArray f3336p;

    /* JADX INFO: renamed from: q */
    public JSONArray f3337q;

    /* JADX INFO: renamed from: r */
    public JSONArray f3338r;

    /* JADX INFO: renamed from: s */
    public JSONArray f3339s;

    /* JADX INFO: renamed from: t */
    public JSONArray f3340t;

    /* JADX INFO: renamed from: u */
    public JSONArray f3341u;

    /* JADX INFO: renamed from: v */
    public JSONArray f3342v;

    /* JADX INFO: renamed from: w */
    public JSONArray f3343w;

    /* JADX INFO: renamed from: x */
    public JSONArray f3344x;

    public C1564g(C1519a c1519a) {
        try {
            this.f3321a = c1519a.f3154j.optString(DownloadFileInfo.Table.COLUMN_NAME_OF_FIELD_URL);
            this.f3322b = c1519a.f3154j.optInt("duration");
            this.f3323c = c1519a.f3154j.optInt("width");
            this.f3324d = c1519a.f3154j.optInt("height");
            this.f3325e = c1519a.f3154j.optInt("format");
            this.f3326f = c1519a.f3154j.optInt("bitrate");
            this.f3327g = c1519a.f3154j.optLong(C1708b.f3748q);
            this.f3328h = c1519a.f3150f;
            JSONObject jSONObject = c1519a.f3140J;
            this.f3329i = jSONObject.optJSONArray("start_urls");
            this.f3330j = jSONObject.optJSONArray("first_quartile_urls");
            this.f3331k = jSONObject.optJSONArray("mid_point_urls");
            this.f3332l = jSONObject.optJSONArray("third_quartile_urls");
            this.f3333m = jSONObject.optJSONArray("complete_urls");
            this.f3334n = jSONObject.optJSONArray("pause_urls");
            this.f3335o = jSONObject.optJSONArray("resume_urls");
            this.f3336p = jSONObject.optJSONArray("skip_urls");
            this.f3337q = jSONObject.optJSONArray("mute_urls");
            this.f3338r = jSONObject.optJSONArray("unmute_urls");
            this.f3339s = jSONObject.optJSONArray("replay_urls");
            this.f3340t = jSONObject.optJSONArray("close_linear_urls");
            this.f3341u = jSONObject.optJSONArray("fullscreen_urls");
            this.f3342v = jSONObject.optJSONArray("exit_fullscreen_urls");
            this.f3343w = jSONObject.optJSONArray("up_scroll_urls");
            this.f3344x = jSONObject.optJSONArray("down_scroll_urls");
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "VideoAdSelected Invalid response data!");
        }
    }
}
