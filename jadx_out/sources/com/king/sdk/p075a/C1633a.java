package com.king.sdk.p075a;

import android.content.Intent;
import android.os.IBinder;
import java.lang.reflect.Method;

/* JADX INFO: renamed from: com.king.sdk.a.a */
/* JADX INFO: compiled from: BinderIntent.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1633a {

    /* JADX INFO: renamed from: a */
    private static Method f3582a;

    /* JADX INFO: renamed from: b */
    private static Method f3583b;

    static {
        try {
            Method declaredMethod = Intent.class.getDeclaredMethod("getIBinderExtra", String.class);
            f3582a = declaredMethod;
            declaredMethod.setAccessible(true);
            Method declaredMethod2 = Intent.class.getDeclaredMethod("putExtra", String.class, IBinder.class);
            f3583b = declaredMethod2;
            declaredMethod2.setAccessible(true);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: renamed from: a */
    public static IBinder m3373a(Intent intent, String str) {
        try {
            return (IBinder) f3582a.invoke(intent, str);
        } catch (Exception unused) {
            return null;
        }
    }
}
