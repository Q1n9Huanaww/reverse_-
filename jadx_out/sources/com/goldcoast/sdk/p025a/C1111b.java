package com.goldcoast.sdk.p025a;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.goldcoast.sdk.a.b */
/* JADX INFO: compiled from: Device.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1111b extends AbstractC1112c<C1111b> {

    /* JADX INFO: renamed from: a */
    private String f1724a;

    /* JADX INFO: renamed from: b */
    private String f1725b;

    /* JADX INFO: renamed from: c */
    private C1110a f1726c;

    /* JADX INFO: renamed from: d */
    private C1114e f1727d;

    /* JADX INFO: renamed from: e */
    private String f1728e;

    /* JADX INFO: renamed from: f */
    private String f1729f;

    /* JADX INFO: renamed from: g */
    private String f1730g;

    /* JADX INFO: renamed from: h */
    private C1115f f1731h;

    /* JADX INFO: renamed from: i */
    private String f1732i;

    /* JADX INFO: renamed from: j */
    private String f1733j;

    /* JADX INFO: renamed from: a */
    public final JSONObject m1360a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("cpuinfo", this.f1726c.m1359a());
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
        try {
            jSONObject.put("prop", this.f1727d.m1380a());
        } catch (JSONException e2) {
            ThrowableExtension.printStackTrace(e2);
        }
        try {
            jSONObject.put("propInfo", this.f1731h.m1385b());
        } catch (JSONException e3) {
            ThrowableExtension.printStackTrace(e3);
        }
        try {
            jSONObject.put("MemTotal", this.f1724a);
            jSONObject.put("linuxversion", this.f1725b);
            jSONObject.put("imei", this.f1728e);
            jSONObject.put("macAddr", this.f1729f);
            jSONObject.put("androidId", this.f1730g);
            jSONObject.put("currentTime", this.f1732i);
            jSONObject.put("cpuType", this.f1733j);
        } catch (JSONException e4) {
            ThrowableExtension.printStackTrace(e4);
        }
        return jSONObject;
    }

    /* JADX INFO: renamed from: a */
    public final void m1364a(String str) {
        this.f1724a = str;
    }

    /* JADX INFO: renamed from: b */
    public final void m1366b(String str) {
        this.f1725b = str;
    }

    /* JADX INFO: renamed from: a */
    public final void m1361a(C1110a c1110a) {
        this.f1726c = c1110a;
    }

    /* JADX INFO: renamed from: b */
    public final C1114e m1365b() {
        return this.f1727d;
    }

    /* JADX INFO: renamed from: a */
    public final void m1362a(C1114e c1114e) {
        this.f1727d = c1114e;
    }

    /* JADX INFO: renamed from: c */
    public final void m1367c(String str) {
        this.f1728e = str;
    }

    /* JADX INFO: renamed from: d */
    public final void m1368d(String str) {
        this.f1729f = str;
    }

    /* JADX INFO: renamed from: e */
    public final void m1369e(String str) {
        this.f1730g = str;
    }

    /* JADX INFO: renamed from: a */
    public final void m1363a(C1115f c1115f) {
        this.f1731h = c1115f;
    }

    /* JADX INFO: renamed from: f */
    public final void m1370f(String str) {
        this.f1732i = str;
    }

    /* JADX INFO: renamed from: g */
    public final void m1371g(String str) {
        this.f1733j = str;
    }
}
