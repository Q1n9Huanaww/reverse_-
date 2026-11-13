package com.cyjh.share.util;

import android.content.Context;
import android.widget.Toast;

/* JADX INFO: loaded from: classes.dex */
public class SlToast {
    public static boolean debugMode;

    public static void showToastShort(Context context, String str) {
        if (debugMode) {
            Toast.makeText(context, str, 0).show();
        }
    }

    public static void showToastShort(Context context, int i) {
        if (debugMode) {
            Toast.makeText(context, i, 0).show();
        }
    }

    public static void showToastLong(Context context, String str) {
        if (debugMode) {
            Toast.makeText(context, str, 1).show();
        }
    }

    public static void showToastLong(Context context, int i) {
        if (debugMode) {
            Toast.makeText(context, i, 1).show();
        }
    }
}
