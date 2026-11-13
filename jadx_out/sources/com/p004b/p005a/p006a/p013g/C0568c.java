package com.p004b.p005a.p006a.p013g;

import java.lang.reflect.Method;

/* JADX INFO: renamed from: com.b.a.a.g.c */
/* JADX INFO: compiled from: AndroidPlatform.java */
/* JADX INFO: loaded from: classes.dex */
final class C0568c {

    /* JADX INFO: renamed from: a */
    private final Method f419a;

    /* JADX INFO: renamed from: b */
    private final Method f420b;

    /* JADX INFO: renamed from: c */
    private final Method f421c;

    private C0568c(Method method, Method method2, Method method3) {
        this.f419a = method;
        this.f420b = method2;
        this.f421c = method3;
    }

    /* JADX INFO: renamed from: a */
    final Object m294a(String str) {
        if (this.f419a != null) {
            try {
                Object objInvoke = this.f419a.invoke(null, new Object[0]);
                this.f420b.invoke(objInvoke, str);
                return objInvoke;
            } catch (Exception unused) {
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    final boolean m295a(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            this.f421c.invoke(obj, new Object[0]);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: renamed from: a */
    static C0568c m293a() {
        Method method;
        Method method2;
        Method method3 = null;
        try {
            Class<?> cls = Class.forName("dalvik.system.CloseGuard");
            Method method4 = cls.getMethod("get", new Class[0]);
            method2 = cls.getMethod("open", String.class);
            method = cls.getMethod("warnIfOpen", new Class[0]);
            method3 = method4;
        } catch (Exception unused) {
            method = null;
            method2 = null;
        }
        return new C0568c(method3, method2, method);
    }
}
