package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.pro.C1726t;

/* JADX INFO: renamed from: com.umeng.analytics.c */
/* JADX INFO: compiled from: InternalConfig.java */
/* JADX INFO: loaded from: classes.dex */
public class C1704c {

    /* JADX INFO: renamed from: a */
    private static String[] f3642a = new String[2];

    /* JADX INFO: renamed from: a */
    public static void m3504a(Context context, String str, String str2) {
        f3642a[0] = str;
        f3642a[1] = str2;
        if (context != null) {
            C1726t.m3692a(context).m3696a(str, str2);
        }
    }

    /* JADX INFO: renamed from: a */
    public static String[] m3505a(Context context) {
        String[] strArrM3697a;
        if (!TextUtils.isEmpty(f3642a[0]) && !TextUtils.isEmpty(f3642a[1])) {
            return f3642a;
        }
        if (context == null || (strArrM3697a = C1726t.m3692a(context).m3697a()) == null) {
            return null;
        }
        f3642a[0] = strArrM3697a[0];
        f3642a[1] = strArrM3697a[1];
        return f3642a;
    }

    /* JADX INFO: renamed from: b */
    public static void m3506b(Context context) {
        f3642a[0] = null;
        f3642a[1] = null;
        if (context != null) {
            C1726t.m3692a(context).m3698b();
        }
    }
}
