package com.iflytek.voiceads.utils;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: renamed from: com.iflytek.voiceads.utils.c */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1589c {
    /* JADX INFO: renamed from: a */
    public static long m3172a(Context context, String str) {
        return m3173a(context).getLong(str, 0L);
    }

    /* JADX INFO: renamed from: a */
    public static SharedPreferences m3173a(Context context) {
        return context.getSharedPreferences("IFLY_AD_SHARED", 0);
    }

    /* JADX INFO: renamed from: a */
    public static void m3174a(Context context, String str, long j) {
        m3173a(context).edit().putLong(str, j).apply();
    }

    /* JADX INFO: renamed from: a */
    public static void m3175a(Context context, String str, String str2) {
        m3173a(context).edit().putString(str, str2).apply();
    }

    /* JADX INFO: renamed from: a */
    public static void m3176a(Context context, String str, boolean z) {
        m3173a(context).edit().putBoolean(str, z).apply();
    }

    /* JADX INFO: renamed from: b */
    public static String m3177b(Context context, String str) {
        return m3173a(context).getString(str, "");
    }

    /* JADX INFO: renamed from: c */
    public static boolean m3178c(Context context, String str) {
        return m3173a(context).getBoolean(str, false);
    }
}
