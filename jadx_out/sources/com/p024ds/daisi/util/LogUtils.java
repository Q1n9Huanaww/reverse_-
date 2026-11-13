package com.p024ds.daisi.util;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class LogUtils {
    private static final String TAG = "ELFIN";

    private static boolean isDebug() {
        return true;
    }

    public static void logError(String str) {
        if (isDebug()) {
            Log.e(TAG, str);
        }
    }

    public static void logInfo(String str) {
        if (isDebug()) {
            Log.i(TAG, str);
        }
    }

    public static void logWarn(String str) {
        if (isDebug()) {
            Log.w(TAG, str);
        }
    }

    /* JADX INFO: renamed from: i */
    public static void m1356i(String str, String str2) {
        if (isDebug()) {
            Log.i(str, str2);
        }
    }

    public static void logVerbose(String str) {
        if (isDebug()) {
            Log.v(TAG, str);
        }
    }
}
