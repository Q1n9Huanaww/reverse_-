package com.cyjh.mobileanjian.ipc.stuff;

import android.content.Context;
import android.content.pm.PackageManager;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.stuff.a */
/* JADX INFO: compiled from: AppAttr.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0774a {

    /* JADX INFO: renamed from: a */
    public static final String f1159a = "com.cyjh.mobileanjian";

    /* JADX INFO: renamed from: b */
    private static boolean f1160b = true;

    /* JADX INFO: renamed from: c */
    private static String f1161c = "12345678-0000-0000-0000-BA9876543210";

    /* JADX INFO: renamed from: d */
    private static String f1162d = "2015-06-24";

    /* JADX INFO: renamed from: e */
    private static String f1163e = "";

    /* JADX INFO: renamed from: a */
    private static void m808a(boolean z) {
        f1160b = z;
    }

    /* JADX INFO: renamed from: a */
    public static boolean m809a() {
        return f1160b;
    }

    /* JADX INFO: renamed from: a */
    private static void m807a(String str) {
        f1162d = str;
    }

    /* JADX INFO: renamed from: b */
    public static String m810b() {
        return f1162d;
    }

    /* JADX INFO: renamed from: b */
    private static void m812b(String str) {
        f1161c = str;
    }

    /* JADX INFO: renamed from: c */
    public static String m814c() {
        return f1161c;
    }

    /* JADX INFO: renamed from: a */
    private static String m806a(Context context) {
        return context.getApplicationContext().getPackageName();
    }

    /* JADX INFO: renamed from: b */
    private static String m811b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
            return "NOT_FOUND";
        }
    }

    /* JADX INFO: renamed from: c */
    private static int m813c(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
            return -2000;
        }
    }

    /* JADX INFO: renamed from: c */
    private static void m815c(String str) {
        f1163e = str;
    }

    /* JADX INFO: renamed from: d */
    private static String m816d() {
        return f1163e;
    }

    /* JADX INFO: renamed from: d */
    private static boolean m817d(Context context) {
        return context.getApplicationContext().getPackageName().equals(f1159a);
    }
}
