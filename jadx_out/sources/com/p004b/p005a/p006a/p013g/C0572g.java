package com.p004b.p005a.p006a.p013g;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: renamed from: com.b.a.a.g.g */
/* JADX INFO: compiled from: OptionalMethod.java */
/* JADX INFO: loaded from: classes.dex */
final class C0572g<T> {

    /* JADX INFO: renamed from: a */
    private final Class<?> f432a;

    /* JADX INFO: renamed from: b */
    private final String f433b;

    /* JADX INFO: renamed from: c */
    private final Class[] f434c;

    public C0572g(Class<?> cls, String str, Class... clsArr) {
        this.f432a = cls;
        this.f433b = str;
        this.f434c = clsArr;
    }

    /* JADX INFO: renamed from: a */
    public final boolean m304a(T t) {
        return m299a(t.getClass()) != null;
    }

    /* JADX INFO: renamed from: c */
    private Object m301c(T t, Object... objArr) {
        Method methodM299a = m299a(t.getClass());
        if (methodM299a == null) {
            return null;
        }
        try {
            return methodM299a.invoke(t, objArr);
        } catch (IllegalAccessException unused) {
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    public final Object m303a(T t, Object... objArr) {
        try {
            return m301c(t, objArr);
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw ((RuntimeException) targetException);
            }
            AssertionError assertionError = new AssertionError("Unexpected exception");
            assertionError.initCause(targetException);
            throw assertionError;
        }
    }

    /* JADX INFO: renamed from: d */
    private Object m302d(T t, Object... objArr) {
        Method methodM299a = m299a(t.getClass());
        if (methodM299a == null) {
            throw new AssertionError("Method " + this.f433b + " not supported for object " + t);
        }
        try {
            return methodM299a.invoke(t, objArr);
        } catch (IllegalAccessException e) {
            AssertionError assertionError = new AssertionError("Unexpectedly could not call: " + methodM299a);
            assertionError.initCause(e);
            throw assertionError;
        }
    }

    /* JADX INFO: renamed from: b */
    public final Object m305b(T t, Object... objArr) {
        try {
            return m302d(t, objArr);
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw ((RuntimeException) targetException);
            }
            AssertionError assertionError = new AssertionError("Unexpected exception");
            assertionError.initCause(targetException);
            throw assertionError;
        }
    }

    /* JADX INFO: renamed from: a */
    private Method m299a(Class<?> cls) {
        if (this.f433b == null) {
            return null;
        }
        Method methodM300a = m300a(cls, this.f433b, this.f434c);
        if (methodM300a == null || this.f432a == null || this.f432a.isAssignableFrom(methodM300a.getReturnType())) {
            return methodM300a;
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    private static Method m300a(Class<?> cls, String str, Class[] clsArr) {
        try {
            Method method = cls.getMethod(str, clsArr);
            try {
                if ((method.getModifiers() & 1) != 0) {
                    return method;
                }
                return null;
            } catch (NoSuchMethodException unused) {
                return method;
            }
        } catch (NoSuchMethodException unused2) {
        }
    }
}
