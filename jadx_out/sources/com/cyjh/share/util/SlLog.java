package com.cyjh.share.util;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class SlLog {
    public static boolean debugMode = true;

    /* JADX INFO: renamed from: d */
    public static void m1332d(String str, String str2) {
        if (str2 == null || !debugMode) {
            return;
        }
        Log.d(str, str2);
    }

    /* JADX INFO: renamed from: e */
    public static void m1333e(String str, String str2) {
        if (str2 == null || !debugMode) {
            return;
        }
        Log.e(str, str2);
    }

    /* JADX INFO: renamed from: i */
    public static void m1334i(String str, String str2) {
        if (str2 == null || !debugMode) {
            return;
        }
        Log.i(str, str2);
    }

    /* JADX INFO: renamed from: v */
    public static void m1335v(String str, String str2) {
        if (str2 == null || !debugMode) {
            return;
        }
        Log.v(str, str2);
    }

    /* JADX INFO: renamed from: w */
    public static void m1336w(String str, String str2) {
        if (str2 == null || !debugMode) {
            return;
        }
        Log.w(str, str2);
    }
}
