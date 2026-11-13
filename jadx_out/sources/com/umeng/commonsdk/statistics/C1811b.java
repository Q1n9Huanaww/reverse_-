package com.umeng.commonsdk.statistics;

import com.umeng.commonsdk.statistics.common.C1817e;

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.b */
/* JADX INFO: compiled from: AnalyticsConstants.java */
/* JADX INFO: loaded from: classes.dex */
public class C1811b {

    /* JADX INFO: renamed from: a */
    public static final String f4483a = "Android";

    /* JADX INFO: renamed from: b */
    public static final String f4484b = "Android";

    /* JADX INFO: renamed from: c */
    public static final String f4485c = "MobclickAgent";

    /* JADX INFO: renamed from: d */
    public static boolean f4486d = true;

    /* JADX INFO: renamed from: e */
    public static String[] f4487e = {UMServerURL.DEFAULT_URL, UMServerURL.SECONDARY_URL};

    /* JADX INFO: renamed from: f */
    public static final boolean f4488f = C1817e.f4515a;

    /* JADX INFO: renamed from: g */
    private static int f4489g = 1;

    /* JADX INFO: renamed from: a */
    public static void m4322a(int i) {
        f4489g = i;
    }

    /* JADX INFO: renamed from: a */
    public static synchronized int m4321a() {
        return f4489g;
    }
}
