package com.iflytek.voiceads.dex;

import com.iflytek.voiceads.config.SDKLogger;
import org.json.JSONException;
import org.json.JSONObject;
import org.wlf.filedownloader.DownloadFileInfo;

/* JADX INFO: renamed from: com.iflytek.voiceads.dex.b */
/* JADX INFO: loaded from: classes.dex */
public class C1522b {

    /* JADX INFO: renamed from: a */
    private boolean f3181a;

    /* JADX INFO: renamed from: b */
    private String f3182b;

    /* JADX INFO: renamed from: c */
    private String f3183c;

    /* JADX INFO: renamed from: d */
    private String f3184d;

    public C1522b(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.f3181a = jSONObject.getBoolean("re");
            if (this.f3181a) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                this.f3182b = jSONObject2.getString("version");
                this.f3183c = jSONObject2.getString(DownloadFileInfo.Table.COLUMN_NAME_OF_FIELD_URL);
                this.f3184d = "AdDex." + jSONObject2.getString("sub_version") + ".dex";
            }
        } catch (JSONException e) {
            SDKLogger.m2901e(e.getMessage());
        }
    }

    /* JADX INFO: renamed from: a */
    public String m2917a() {
        return this.f3183c;
    }

    /* JADX INFO: renamed from: b */
    public String m2918b() {
        return this.f3184d;
    }
}
