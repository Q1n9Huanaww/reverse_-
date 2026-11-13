package com.iflytek.voiceads.config;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class SDKLogger {
    public static boolean dev;

    /* JADX INFO: renamed from: d */
    public static void m2899d(String str) {
        if (dev) {
            Log.d(SDKConstants.TAG, str);
        }
    }

    /* JADX INFO: renamed from: d */
    public static void m2900d(String str, String str2) {
        if (dev) {
            Log.d(str, str2);
        }
    }

    /* JADX INFO: renamed from: e */
    public static void m2901e(String str) {
        if (dev) {
            Log.e(SDKConstants.TAG, str);
        }
    }

    /* JADX INFO: renamed from: e */
    public static void m2902e(String str, String str2) {
        if (dev) {
            Log.e(str, str2);
        }
    }

    public static void setDebug(boolean z) {
        dev = z;
    }
}
