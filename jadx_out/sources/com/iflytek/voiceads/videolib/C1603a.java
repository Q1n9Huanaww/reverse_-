package com.iflytek.voiceads.videolib;

import java.util.HashMap;
import java.util.LinkedHashMap;

/* JADX INFO: renamed from: com.iflytek.voiceads.videolib.a */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1603a {

    /* JADX INFO: renamed from: b */
    public int f3492b;

    /* JADX INFO: renamed from: d */
    public String f3494d;

    /* JADX INFO: renamed from: g */
    public Object[] f3497g;

    /* JADX INFO: renamed from: a */
    public final String f3491a = "URL_KEY_DEFAULT";

    /* JADX INFO: renamed from: c */
    public LinkedHashMap f3493c = new LinkedHashMap();

    /* JADX INFO: renamed from: e */
    public HashMap f3495e = new HashMap();

    /* JADX INFO: renamed from: f */
    public boolean f3496f = false;

    public C1603a(String str, String str2) {
        this.f3494d = "";
        this.f3493c.put("URL_KEY_DEFAULT", str);
        this.f3494d = str2;
        this.f3492b = 0;
    }

    /* JADX INFO: renamed from: a */
    public Object m3286a() {
        return m3287a(this.f3492b);
    }

    /* JADX INFO: renamed from: a */
    public Object m3287a(int i) {
        int i2 = 0;
        for (Object obj : this.f3493c.keySet()) {
            if (i2 == i) {
                return this.f3493c.get(obj);
            }
            i2++;
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    public boolean m3288a(Object obj) {
        if (obj != null) {
            return this.f3493c.containsValue(obj);
        }
        return false;
    }
}
