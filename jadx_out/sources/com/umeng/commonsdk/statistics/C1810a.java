package com.umeng.commonsdk.statistics;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.statistics.common.C1816d;

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.a */
/* JADX INFO: compiled from: AnalyticsConfig.java */
/* JADX INFO: loaded from: classes.dex */
public class C1810a {

    /* JADX INFO: renamed from: a */
    public static int f4481a;

    /* JADX INFO: renamed from: b */
    private static String f4482b;

    /* JADX INFO: renamed from: a */
    public static String m4320a(Context context) {
        if (TextUtils.isEmpty(f4482b)) {
            f4482b = C1816d.m4375a(context).m4381b();
        }
        return f4482b;
    }
}
