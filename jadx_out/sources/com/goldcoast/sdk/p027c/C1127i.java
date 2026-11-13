package com.goldcoast.sdk.p027c;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.lang.reflect.Method;

/* JADX INFO: renamed from: com.goldcoast.sdk.c.i */
/* JADX INFO: compiled from: SystemProperties.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1127i {

    /* JADX INFO: renamed from: a */
    private static Class<?> f1802a;

    /* JADX INFO: renamed from: b */
    private static Method f1803b;

    /* JADX INFO: renamed from: c */
    private static Method f1804c;

    /* JADX INFO: renamed from: d */
    private static Method f1805d;

    /* JADX INFO: renamed from: e */
    private static Method f1806e;

    /* JADX INFO: renamed from: f */
    private static Method f1807f;

    /* JADX INFO: renamed from: g */
    private static Method f1808g;

    /* JADX INFO: renamed from: a */
    public static String m1436a(String str) {
        try {
            return (String) f1803b.invoke(null, str);
        } catch (Exception unused) {
            return null;
        }
    }

    static {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            f1802a = cls;
            Method declaredMethod = cls.getDeclaredMethod("get", String.class);
            f1803b = declaredMethod;
            declaredMethod.setAccessible(true);
            Method declaredMethod2 = f1802a.getDeclaredMethod("get", String.class, String.class);
            f1804c = declaredMethod2;
            declaredMethod2.setAccessible(true);
            Method declaredMethod3 = f1802a.getDeclaredMethod("getInt", String.class, Integer.TYPE);
            f1805d = declaredMethod3;
            declaredMethod3.setAccessible(true);
            Method declaredMethod4 = f1802a.getDeclaredMethod("getLong", String.class, Long.TYPE);
            f1806e = declaredMethod4;
            declaredMethod4.setAccessible(true);
            Method declaredMethod5 = f1802a.getDeclaredMethod("getBoolean", String.class, Boolean.TYPE);
            f1807f = declaredMethod5;
            declaredMethod5.setAccessible(true);
            Method declaredMethod6 = f1802a.getDeclaredMethod("set", String.class, String.class);
            f1808g = declaredMethod6;
            declaredMethod6.setAccessible(true);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }
}
