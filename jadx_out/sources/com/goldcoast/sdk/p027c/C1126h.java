package com.goldcoast.sdk.p027c;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/* JADX INFO: renamed from: com.goldcoast.sdk.c.h */
/* JADX INFO: compiled from: SPUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1126h {

    /* JADX INFO: renamed from: a */
    private static SharedPreferences f1799a;

    /* JADX INFO: renamed from: b */
    private static C1126h f1800b;

    /* JADX INFO: renamed from: c */
    private static Context f1801c;

    private C1126h() {
    }

    /* JADX INFO: renamed from: a */
    public static void m1434a(Context context) {
        f1801c = context;
        if (f1799a == null) {
            f1799a = f1801c.getSharedPreferences("dispatch_log", 0);
        }
    }

    /* JADX INFO: renamed from: a */
    public static C1126h m1432a() {
        if (f1800b == null) {
            f1800b = new C1126h();
        }
        return f1800b;
    }

    /* JADX INFO: renamed from: a */
    public static void m1435a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        SharedPreferences.Editor editorEdit = f1799a.edit();
        editorEdit.putString(str, str2);
        editorEdit.commit();
    }

    /* JADX INFO: renamed from: a */
    public static String m1433a(String str) {
        return TextUtils.isEmpty(str) ? "" : f1799a.getString(str, "");
    }
}
