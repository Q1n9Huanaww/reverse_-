package com.umeng.analytics.pro;

import android.content.Context;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.umeng.analytics.pro.p */
/* JADX INFO: compiled from: ViewPageTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class C1722p {

    /* JADX INFO: renamed from: a */
    private static JSONObject f3926a = new JSONObject();

    /* JADX INFO: renamed from: b */
    private final Map<String, Long> f3927b = new HashMap();

    /* JADX INFO: renamed from: a */
    public static void m3684a(Context context) {
        if (context != null) {
            try {
                synchronized (f3926a) {
                    if (f3926a.length() > 0) {
                        C1713g.m3553a(context).m3570a(C1721o.m3668a().m3682d(), f3926a, C1713g.a.PAGE);
                        f3926a = new JSONObject();
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public void m3686a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        synchronized (this.f3927b) {
            this.f3927b.put(str, Long.valueOf(System.currentTimeMillis()));
        }
    }

    /* JADX INFO: renamed from: b */
    public void m3687b(String str) {
        Long l;
        if (TextUtils.isEmpty(str) || !this.f3927b.containsKey(str)) {
            return;
        }
        synchronized (this.f3927b) {
            l = this.f3927b.get(str);
        }
        if (l == null) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis() - l.longValue();
        synchronized (f3926a) {
            try {
                f3926a = new JSONObject();
                f3926a.put(C1708b.f3752u, str);
                f3926a.put("duration", jCurrentTimeMillis);
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public void m3685a() {
        String key;
        synchronized (this.f3927b) {
            key = null;
            long j = 0;
            for (Map.Entry<String, Long> entry : this.f3927b.entrySet()) {
                if (entry.getValue().longValue() > j) {
                    long jLongValue = entry.getValue().longValue();
                    key = entry.getKey();
                    j = jLongValue;
                }
            }
        }
        if (key != null) {
            m3687b(key);
        }
    }
}
