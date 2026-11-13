package com.iflytek.voiceads.utils;

import android.util.Log;

/* JADX INFO: renamed from: com.iflytek.voiceads.utils.g */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1593g {

    /* JADX INFO: renamed from: a */
    private static boolean f3421a = false;

    /* JADX INFO: renamed from: a */
    public static void m3188a(String str, String str2) {
        if (f3421a) {
            Log.d(str, str2);
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m3189a(boolean z) {
        f3421a = z;
    }

    /* JADX INFO: renamed from: b */
    public static void m3190b(String str, String str2) {
        if (f3421a) {
            Log.e(str, str2);
            C1592f.m3185a(str2);
        }
    }
}
