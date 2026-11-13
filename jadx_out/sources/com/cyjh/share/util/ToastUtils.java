package com.cyjh.share.util;

import android.content.Context;
import android.widget.Toast;

/* JADX INFO: loaded from: classes.dex */
public class ToastUtils {
    public static void showToastShort(Context context, String str) {
        Toast.makeText(context, str, 0).show();
    }

    public static void showToastShort(Context context, int i) {
        Toast.makeText(context, i, 0).show();
    }

    public static void showToastLong(Context context, String str) {
        Toast.makeText(context, str, 1).show();
    }

    public static void showToastLong(Context context, int i) {
        Toast.makeText(context, i, 1).show();
    }

    public static void showToastLocation(Context context, int i, int i2, int i3) {
        Toast toastMakeText = Toast.makeText(context, i, 0);
        toastMakeText.setGravity(17, i2, i3);
        toastMakeText.show();
    }
}
