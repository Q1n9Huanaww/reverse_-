package com.umeng.analytics.pro;

import android.content.Context;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.umeng.analytics.C1703b;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.umeng.analytics.pro.l */
/* JADX INFO: compiled from: DefconProcesser.java */
/* JADX INFO: loaded from: classes.dex */
public class C1718l {

    /* JADX INFO: renamed from: a */
    private static final int f3904a = 0;

    /* JADX INFO: renamed from: b */
    private static final int f3905b = 1;

    /* JADX INFO: renamed from: c */
    private static final int f3906c = 2;

    /* JADX INFO: renamed from: d */
    private static final int f3907d = 3;

    /* JADX INFO: renamed from: e */
    private final long f3908e;

    private C1718l() {
        this.f3908e = 60000L;
    }

    /* JADX INFO: renamed from: com.umeng.analytics.pro.l$a */
    /* JADX INFO: compiled from: DefconProcesser.java */
    private static class a {

        /* JADX INFO: renamed from: a */
        public static final C1718l f3909a = new C1718l();

        private a() {
        }
    }

    /* JADX INFO: renamed from: a */
    public static C1718l m3646a() {
        return a.f3909a;
    }

    /* JADX INFO: renamed from: a */
    public int m3648a(Context context) {
        return Integer.valueOf(UMEnvelopeBuild.imprintProperty(context, "defcon", String.valueOf(0))).intValue();
    }

    /* JADX INFO: renamed from: a */
    public void m3649a(JSONObject jSONObject, Context context) {
        int iM3648a = m3648a(context);
        if (iM3648a == 1) {
            jSONObject.remove(C1708b.f3713Y);
            C1713g.m3553a(context).m3577f();
            return;
        }
        if (iM3648a != 2) {
            if (iM3648a == 3) {
                jSONObject.remove(C1708b.f3713Y);
                jSONObject.remove(C1708b.f3718ac);
                jSONObject.remove(C1708b.f3723ah);
                C1713g.m3553a(context).m3577f();
                return;
            }
            return;
        }
        jSONObject.remove(C1708b.f3713Y);
        jSONObject.remove(C1708b.f3718ac);
        jSONObject.remove(C1708b.f3723ah);
        try {
            jSONObject.put(C1708b.f3718ac, m3647c());
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
        C1713g.m3553a(context).m3577f();
    }

    /* JADX INFO: renamed from: b */
    public void m3651b(JSONObject jSONObject, Context context) throws Throwable {
        int iM3648a = m3648a(context);
        if (iM3648a == 1) {
            jSONObject.remove(C1708b.f3698J);
            jSONObject.remove(C1708b.f3702N);
            jSONObject.remove(C1708b.f3703O);
            C1713g.m3553a(context).m3568a(false, true);
            return;
        }
        if (iM3648a == 2) {
            jSONObject.remove(C1708b.f3745n);
            try {
                jSONObject.put(C1708b.f3745n, m3650b());
            } catch (Exception unused) {
            }
            jSONObject.remove(C1708b.f3698J);
            jSONObject.remove(C1708b.f3702N);
            jSONObject.remove(C1708b.f3703O);
            C1713g.m3553a(context).m3568a(false, true);
            return;
        }
        if (iM3648a == 3) {
            jSONObject.remove(C1708b.f3745n);
            jSONObject.remove(C1708b.f3698J);
            jSONObject.remove(C1708b.f3702N);
            jSONObject.remove(C1708b.f3703O);
            C1713g.m3553a(context).m3568a(false, true);
        }
    }

    /* JADX INFO: renamed from: b */
    public JSONObject m3650b() {
        JSONObject jSONObject = new JSONObject();
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            jSONObject.put("id", C1721o.m3668a().m3682d());
            jSONObject.put(C1708b.f3747p, jCurrentTimeMillis);
            jSONObject.put(C1708b.f3748q, jCurrentTimeMillis + 60000);
            jSONObject.put("duration", 60000L);
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    /* JADX INFO: renamed from: c */
    private JSONArray m3647c() {
        JSONArray jSONArray = new JSONArray();
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObjectM3501i = C1703b.m3462a().m3501i();
            if (jSONObjectM3501i.length() > 0) {
                jSONObject.put(C1708b.f3717ab, jSONObjectM3501i);
            }
            jSONObject.put(C1708b.f3719ad, C1721o.m3668a().m3682d());
            jSONObject.put(C1708b.f3720ae, jCurrentTimeMillis);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(C1708b.f3721af, C1721o.m3668a().m3682d());
            jSONObject2.put(C1708b.f3722ag, jCurrentTimeMillis + 60000);
            if (jSONObjectM3501i.length() > 0) {
                jSONObject2.put(C1708b.f3717ab, jSONObjectM3501i);
            }
            jSONArray.put(jSONObject).put(jSONObject2);
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
        return jSONArray;
    }
}
