package mobi.oneway.export.p078a;

import android.content.Context;
import mobi.oneway.export.p087g.C1959m;

/* JADX INFO: renamed from: mobi.oneway.export.a.b */
/* JADX INFO: loaded from: classes.dex */
public class C1900b {

    /* JADX INFO: renamed from: a */
    private static Context f4832a;

    /* JADX INFO: renamed from: b */
    private static String f4833b;

    /* JADX INFO: renamed from: c */
    private static boolean f4834c;

    /* JADX INFO: renamed from: d */
    private static String f4835d;

    /* JADX INFO: renamed from: e */
    private static String f4836e;

    /* JADX INFO: renamed from: a */
    public static Context m4706a() {
        if (f4832a == null) {
            C1959m.m5053d("OnewaySdk.init method must invoke first and paramter context must not be null");
        }
        return f4832a;
    }

    /* JADX INFO: renamed from: a */
    public static void m4707a(Context context) {
        f4832a = context;
    }

    /* JADX INFO: renamed from: a */
    public static void m4708a(String str) {
        f4833b = str;
    }

    /* JADX INFO: renamed from: a */
    public static void m4709a(boolean z) {
        f4834c = z;
    }

    /* JADX INFO: renamed from: b */
    public static String m4710b() {
        return f4833b;
    }

    /* JADX INFO: renamed from: b */
    public static void m4711b(String str) {
        f4835d = str;
    }

    /* JADX INFO: renamed from: c */
    public static void m4712c(String str) {
        f4836e = str;
    }

    /* JADX INFO: renamed from: c */
    public static boolean m4713c() {
        return f4834c;
    }

    /* JADX INFO: renamed from: d */
    public static String m4714d() {
        return f4835d;
    }

    /* JADX INFO: renamed from: e */
    public static String m4715e() {
        return f4836e;
    }
}
