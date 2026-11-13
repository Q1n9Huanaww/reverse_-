package com.cyjh.p019mq.p023d;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

/* JADX INFO: renamed from: com.cyjh.mq.d.d */
/* JADX INFO: compiled from: PackageUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0839d {
    /* JADX INFO: renamed from: a */
    public static String m1288a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
            return applicationInfo != null ? (String) packageManager.getApplicationLabel(applicationInfo) : "NULL";
        } catch (PackageManager.NameNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
            return "NULL";
        }
    }

    /* JADX INFO: renamed from: c */
    private static int m1290c(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 128);
            if (packageInfo != null) {
                return packageInfo.versionCode;
            }
            return -1;
        } catch (PackageManager.NameNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
            return -1;
        }
    }

    /* JADX INFO: renamed from: b */
    public static String m1289b(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 128);
            return packageInfo != null ? packageInfo.versionName : "NULL";
        } catch (PackageManager.NameNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
            return "NULL";
        }
    }

    /* JADX INFO: renamed from: d */
    private static String m1291d(Context context) {
        return context.getPackageName();
    }
}
