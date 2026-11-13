package com.umeng.analytics.pro;

import android.content.Context;
import com.umeng.commonsdk.statistics.common.MLog;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.umeng.analytics.pro.k */
/* JADX INFO: compiled from: DataSpliter.java */
/* JADX INFO: loaded from: classes.dex */
public class C1717k {
    /* JADX INFO: renamed from: a */
    public static JSONObject m3642a(Context context, long j, JSONObject jSONObject) {
        JSONObject jSONObject2;
        JSONObject jSONObject3 = new JSONObject();
        try {
            C1713g.m3553a(context).m3563a();
            JSONObject jSONObject4 = new JSONObject();
            long jM3640a = 0;
            if (jSONObject.has("header")) {
                jSONObject4 = jSONObject.getJSONObject("header");
                if (jSONObject4 == null || jSONObject4.length() <= 0) {
                    jSONObject4 = null;
                } else {
                    jM3640a = j - m3640a(jSONObject4);
                }
            }
            if (jSONObject.has(C1708b.f3711W) && (jSONObject2 = jSONObject.getJSONObject(C1708b.f3711W)) != null && jSONObject2.length() > 0) {
                m3643a(context, jM3640a, jSONObject2, jSONObject3);
                if (jSONObject3.length() < 1) {
                    m3645b(context, jM3640a, jSONObject2, jSONObject3);
                }
                if (jSONObject3.length() > 0 && jSONObject4 != null) {
                    jSONObject3.put("header", jSONObject4);
                }
            }
        } catch (Throwable unused) {
        }
        return jSONObject3;
    }

    /* JADX INFO: renamed from: a */
    private static void m3643a(Context context, long j, JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            if (jSONObject.has("dplus")) {
                JSONObject jSONObject3 = jSONObject.getJSONObject("dplus");
                if (jSONObject3 != null && jSONObject3.length() > 0 && m3640a(jSONObject3) > j) {
                    jSONObject3 = m3644b(context, j, jSONObject3);
                }
                if (jSONObject3.length() > 0) {
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put("dplus", jSONObject3);
                    jSONObject2.put(C1708b.f3711W, jSONObject4);
                }
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: b */
    private static void m3645b(Context context, long j, JSONObject jSONObject, JSONObject jSONObject2) {
        JSONObject jSONObject3;
        try {
            if (!jSONObject.has("analytics") || (jSONObject3 = jSONObject.getJSONObject("analytics")) == null || jSONObject3.length() <= 0) {
                return;
            }
            if (m3640a(jSONObject3) > j) {
                jSONObject3 = m3641a(context, j);
            }
            if (jSONObject3.length() > 0) {
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put("analytics", jSONObject3);
                jSONObject2.put(C1708b.f3711W, jSONObject4);
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: b */
    private static JSONObject m3644b(Context context, long j, JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            if (jSONObject.has(C1708b.f3718ac)) {
                JSONArray jSONArray = jSONObject.getJSONArray(C1708b.f3718ac);
                if (m3639a(jSONArray) > j) {
                    jSONObject.remove(C1708b.f3718ac);
                    C1713g.m3553a(context).m3564a(4);
                    return m3644b(context, j, jSONObject);
                }
                jSONObject2.put(C1708b.f3718ac, jSONArray);
            } else if (jSONObject.has(C1708b.f3713Y)) {
                JSONArray jSONArray2 = jSONObject.getJSONArray(C1708b.f3713Y);
                if (m3639a(jSONArray2) > j) {
                    jSONObject.remove(C1708b.f3713Y);
                    C1713g.m3553a(context).m3564a(0);
                    return m3644b(context, j, jSONObject);
                }
                jSONObject2.put(C1708b.f3713Y, jSONArray2);
            } else if (jSONObject.has(C1708b.f3723ah)) {
                JSONArray jSONArray3 = jSONObject.getJSONArray(C1708b.f3723ah);
                if (m3639a(jSONArray3) > j) {
                    jSONObject.remove(C1708b.f3723ah);
                    C1713g.m3553a(context).m3564a(1);
                    return m3644b(context, j, jSONObject);
                }
                jSONObject2.put(C1708b.f3723ah, jSONArray3);
            }
        } catch (Throwable unused) {
        }
        return jSONObject2;
    }

    /* JADX INFO: renamed from: a */
    private static JSONObject m3641a(Context context, long j) throws Throwable {
        JSONObject jSONObjectM3619b;
        JSONObject jSONObject = new JSONObject();
        try {
            MLog.m4337e("splitAnalyticsData========");
            jSONObjectM3619b = C1715i.m3588a(context).m3619b(true);
            if (jSONObjectM3619b != null) {
                try {
                    if (jSONObjectM3619b.length() > 0 && m3640a(jSONObjectM3619b) > j) {
                        String string = null;
                        boolean z = false;
                        if (jSONObjectM3619b.has(C1708b.f3745n)) {
                            JSONObject jSONObject2 = jSONObjectM3619b.getJSONArray(C1708b.f3745n).getJSONObject(0);
                            if (jSONObject2 != null) {
                                string = jSONObject2.getString("id");
                                jSONObject2.remove(C1708b.f3751t);
                                jSONObject2.remove(C1708b.f3750s);
                                JSONArray jSONArray = new JSONArray();
                                jSONArray.put(jSONObject2);
                                jSONObjectM3619b.put(C1708b.f3745n, jSONArray);
                            }
                            z = true;
                        }
                        if (jSONObjectM3619b.has(C1708b.f3702N)) {
                            jSONObjectM3619b.remove(C1708b.f3702N);
                        }
                        if (jSONObjectM3619b.has(C1708b.f3703O)) {
                            jSONObjectM3619b.remove(C1708b.f3703O);
                        }
                        if (jSONObjectM3619b.has(C1708b.f3698J)) {
                            jSONObjectM3619b.remove(C1708b.f3698J);
                        }
                        C1713g.m3553a(context).m3567a(z, string);
                    }
                } catch (Throwable th) {
                    th = th;
                    MLog.m4341e(th);
                }
            }
        } catch (Throwable th2) {
            th = th2;
            jSONObjectM3619b = jSONObject;
        }
        return jSONObjectM3619b;
    }

    /* JADX INFO: renamed from: a */
    public static long m3640a(JSONObject jSONObject) {
        return jSONObject.toString().getBytes().length;
    }

    /* JADX INFO: renamed from: a */
    public static long m3639a(JSONArray jSONArray) {
        return jSONArray.toString().getBytes().length;
    }
}
