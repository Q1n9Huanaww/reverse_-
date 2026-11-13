package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.pro.C1726t;
import com.umeng.commonsdk.utils.UMUtils;

/* JADX INFO: loaded from: classes.dex */
public class AnalyticsConfig {
    public static boolean ACTIVITY_DURATION_OPEN = true;
    public static boolean CATCH_EXCEPTION = true;
    public static boolean FLAG_DPLUS = false;
    public static String GPU_RENDERER = "";
    public static String GPU_VENDER = "";

    /* JADX INFO: renamed from: a */
    static double[] f3613a = null;

    /* JADX INFO: renamed from: b */
    private static String f3614b = null;

    /* JADX INFO: renamed from: c */
    private static String f3615c = null;

    /* JADX INFO: renamed from: d */
    private static String f3616d = null;

    /* JADX INFO: renamed from: e */
    private static int f3617e = 0;
    public static long kContinueSessionMillis = 30000;
    public static String mWrapperType;
    public static String mWrapperVersion;

    /* JADX INFO: renamed from: a */
    static void m3460a(String str) {
        f3615c = str;
    }

    public static String getAppkey(Context context) {
        return UMUtils.getAppkey(context);
    }

    public static String getChannel(Context context) {
        return UMUtils.getChannel(context);
    }

    public static double[] getLocation() {
        return f3613a;
    }

    /* JADX INFO: renamed from: a */
    static void m3459a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        f3616d = str;
        C1726t.m3692a(context).m3695a(f3616d);
    }

    public static String getSecretKey(Context context) {
        if (TextUtils.isEmpty(f3616d)) {
            f3616d = C1726t.m3692a(context).m3699c();
        }
        return f3616d;
    }

    /* JADX INFO: renamed from: a */
    static void m3458a(Context context, int i) {
        f3617e = i;
        C1726t.m3692a(context).m3694a(f3617e);
    }

    public static int getVerticalType(Context context) {
        if (f3617e == 0) {
            f3617e = C1726t.m3692a(context).m3700d();
        }
        return f3617e;
    }
}
