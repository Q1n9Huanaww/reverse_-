package com.goldcoast.sdk.p025a;

import android.support.v4.app.NotificationCompat;
import com.umeng.commonsdk.proguard.C1799v;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.goldcoast.sdk.a.g */
/* JADX INFO: compiled from: Refer.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1116g extends AbstractC1112c<C1116g> {

    /* JADX INFO: renamed from: a */
    private String f1770a;

    /* JADX INFO: renamed from: b */
    private int f1771b;

    /* JADX INFO: renamed from: c */
    private String f1772c;

    /* JADX INFO: renamed from: d */
    private long f1773d;

    /* JADX INFO: renamed from: e */
    private String f1774e;

    /* JADX INFO: renamed from: f */
    private String f1775f;

    /* JADX INFO: renamed from: g */
    private String f1776g;

    /* JADX INFO: renamed from: h */
    private String f1777h;

    /* JADX INFO: renamed from: i */
    private String f1778i;

    /* JADX INFO: renamed from: j */
    private String f1779j;

    /* JADX INFO: renamed from: k */
    private int f1780k;

    /* JADX INFO: renamed from: l */
    private C1115f f1781l;

    /* JADX INFO: renamed from: m */
    private String f1782m;

    /* JADX INFO: renamed from: n */
    private String f1783n;

    /* JADX INFO: renamed from: o */
    private String f1784o;

    /* JADX INFO: renamed from: a */
    public final void m1390a(String str) {
        this.f1770a = str;
    }

    /* JADX INFO: renamed from: a */
    public final void m1387a(int i) {
        this.f1771b = i;
    }

    /* JADX INFO: renamed from: b */
    public final void m1392b(String str) {
        this.f1772c = str;
    }

    /* JADX INFO: renamed from: a */
    public final void m1388a(long j) {
        this.f1773d = j;
    }

    /* JADX INFO: renamed from: c */
    public final void m1393c(String str) {
        this.f1774e = str;
    }

    /* JADX INFO: renamed from: d */
    public final void m1394d(String str) {
        this.f1775f = str;
    }

    /* JADX INFO: renamed from: e */
    public final void m1395e(String str) {
        this.f1776g = str;
    }

    /* JADX INFO: renamed from: f */
    public final void m1396f(String str) {
        this.f1777h = str;
    }

    /* JADX INFO: renamed from: g */
    public final void m1397g(String str) {
        this.f1778i = str;
    }

    /* JADX INFO: renamed from: h */
    public final void m1398h(String str) {
        this.f1779j = str;
    }

    /* JADX INFO: renamed from: b */
    public final void m1391b(int i) {
        this.f1780k = i;
    }

    /* JADX INFO: renamed from: a */
    public final void m1389a(C1115f c1115f) {
        this.f1781l = c1115f;
    }

    /* JADX INFO: renamed from: i */
    public final void m1399i(String str) {
        this.f1782m = str;
    }

    /* JADX INFO: renamed from: j */
    public final void m1400j(String str) {
        this.f1783n = str;
    }

    /* JADX INFO: renamed from: k */
    public final void m1401k(String str) {
        this.f1784o = str;
    }

    /* JADX INFO: renamed from: a */
    public final JSONObject m1386a() {
        JSONObject jSONObject;
        if (this.f1781l != null) {
            jSONObject = this.f1781l.m1385b();
        } else {
            jSONObject = new JSONObject();
        }
        try {
            jSONObject.put("rf_md5", this.f1770a);
            jSONObject.put("status", this.f1771b);
            jSONObject.put(NotificationCompat.CATEGORY_MESSAGE, this.f1772c);
            jSONObject.put("runTime", this.f1773d);
            jSONObject.put("androidId", this.f1774e);
            jSONObject.put("netOperator", this.f1775f);
            jSONObject.put("simOperatorName", this.f1776g);
            jSONObject.put("displayLanguage", this.f1777h);
            jSONObject.put("displayCountry", this.f1778i);
            jSONObject.put("token", this.f1779j);
            jSONObject.put("category", this.f1780k);
            jSONObject.put("sdkV", this.f1782m);
            jSONObject.put(C1799v.f4319j, this.f1783n);
            jSONObject.put("signature", this.f1784o);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
