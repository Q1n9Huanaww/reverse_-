package com.iflytek.collector.p060a.p061a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* JADX INFO: renamed from: com.iflytek.collector.a.a.k */
/* JADX INFO: loaded from: classes.dex */
public class C1488k {
    /* JADX INFO: renamed from: a */
    public static boolean m2831a(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        return m2832a(context, "android.permission.INTERNET") && (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isAvailable();
    }

    /* JADX INFO: renamed from: a */
    private static boolean m2832a(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        return packageManager != null && packageManager.checkPermission(str, context.getPackageName()) == 0;
    }

    /* JADX INFO: renamed from: b */
    public static SharedPreferences m2833b(Context context) {
        return context.getSharedPreferences("iflytek_collect_state", 0);
    }
}
