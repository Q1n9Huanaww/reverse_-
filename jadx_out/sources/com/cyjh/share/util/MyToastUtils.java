package com.cyjh.share.util;

import android.content.Context;
import android.widget.Toast;

/* JADX INFO: loaded from: classes.dex */
public class MyToastUtils {
    private static String oldMsg;
    private static long oneTime;
    private static Toast toast;
    private static long twoTime;

    public static void showToast(Context context, String str) {
        if (toast == null) {
            toast = Toast.makeText(context, str, 0);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (!str.equals(oldMsg)) {
                oldMsg = str;
                toast.setText(str);
                toast.show();
            } else if (twoTime - oneTime > 0) {
                toast.show();
            }
        }
        oneTime = twoTime;
    }
}
