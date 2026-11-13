package com.umeng.analytics.game;

import android.content.Context;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.statistics.common.MLog;

/* JADX INFO: loaded from: classes.dex */
public class UMGameAgent extends MobclickAgent {

    /* JADX INFO: renamed from: a */
    private static final String f3643a = "Input string is null or empty";

    /* JADX INFO: renamed from: b */
    private static final String f3644b = "Input string must be less than 64 chars";

    /* JADX INFO: renamed from: c */
    private static final String f3645c = "Input value type is negative";

    /* JADX INFO: renamed from: d */
    private static final String f3646d = "The int value for 'Pay Channels' ranges between 1 ~ 99 ";

    /* JADX INFO: renamed from: e */
    private static final C1706b f3647e = new C1706b();

    /* JADX INFO: renamed from: f */
    private static Context f3648f;

    public static void init(Context context) throws Throwable {
        try {
            if (f3648f == null && context != null) {
                f3648f = context.getApplicationContext();
            }
            f3647e.m3525a(f3648f);
        } catch (Throwable unused) {
            MLog.m4337e("please check Context!");
        }
    }

    public static void setTraceSleepTime(boolean z) throws Throwable {
        f3647e.m3529a(z);
    }

    public static void setPlayerLevel(int i) {
        f3647e.m3526a(String.valueOf(i));
    }

    public static void startLevel(String str) throws Throwable {
        if (m3507a(str)) {
            MLog.m4337e(f3643a);
        } else if (str.length() > 64) {
            MLog.m4337e(f3644b);
        } else {
            f3647e.m3531b(str);
        }
    }

    public static void finishLevel(String str) throws Throwable {
        if (m3507a(str)) {
            MLog.m4337e(f3643a);
        } else if (str.length() > 64) {
            MLog.m4337e(f3644b);
        } else {
            f3647e.m3533c(str);
        }
    }

    public static void failLevel(String str) throws Throwable {
        if (m3507a(str)) {
            MLog.m4337e(f3643a);
        } else if (str.length() > 64) {
            MLog.m4337e(f3644b);
        } else {
            f3647e.m3534d(str);
        }
    }

    public static void pay(double d, double d2, int i) throws Throwable {
        if (i <= 0 || i >= 100) {
            MLog.m4337e(f3646d);
        } else if (d < 0.0d || d2 < 0.0d) {
            MLog.m4337e(f3645c);
        } else {
            f3647e.m3521a(d, d2, i);
        }
    }

    public static void pay(double d, String str, int i, double d2, int i2) throws Throwable {
        if (i2 <= 0 || i2 >= 100) {
            MLog.m4337e(f3646d);
            return;
        }
        if (d < 0.0d || i < 0 || d2 < 0.0d) {
            MLog.m4337e(f3645c);
        } else if (m3507a(str)) {
            MLog.m4337e(f3643a);
        } else {
            f3647e.m3524a(d, str, i, d2, i2);
        }
    }

    public static void exchange(double d, String str, double d2, int i, String str2) throws Throwable {
        if (d < 0.0d || d2 < 0.0d) {
            MLog.m4337e(f3645c);
        } else if (i <= 0 || i >= 100) {
            MLog.m4337e(f3646d);
        } else {
            f3647e.m3523a(d, str, d2, i, str2);
        }
    }

    public static void buy(String str, int i, double d) throws Throwable {
        if (m3507a(str)) {
            MLog.m4337e(f3643a);
        } else if (i < 0 || d < 0.0d) {
            MLog.m4337e(f3645c);
        } else {
            f3647e.m3527a(str, i, d);
        }
    }

    public static void use(String str, int i, double d) throws Throwable {
        if (m3507a(str)) {
            MLog.m4337e(f3643a);
        } else if (i < 0 || d < 0.0d) {
            MLog.m4337e(f3645c);
        } else {
            f3647e.m3532b(str, i, d);
        }
    }

    public static void bonus(double d, int i) throws Throwable {
        if (d < 0.0d) {
            MLog.m4337e(f3645c);
        } else if (i <= 0 || i >= 100) {
            MLog.m4337e(f3646d);
        } else {
            f3647e.m3522a(d, i);
        }
    }

    public static void bonus(String str, int i, double d, int i2) throws Throwable {
        if (m3507a(str)) {
            MLog.m4337e(f3643a);
            return;
        }
        if (i < 0 || d < 0.0d) {
            MLog.m4337e(f3645c);
        } else if (i2 <= 0 || i2 >= 100) {
            MLog.m4337e(f3646d);
        } else {
            f3647e.m3528a(str, i, d, i2);
        }
    }

    /* JADX INFO: renamed from: a */
    private static boolean m3507a(String str) {
        return str == null || str.trim().length() <= 0;
    }
}
