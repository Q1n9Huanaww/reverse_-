package com.google.p028a;

/* JADX INFO: renamed from: com.google.a.q */
/* JADX INFO: compiled from: ReaderException.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1362q extends Exception {

    /* JADX INFO: renamed from: a */
    protected static final boolean f2837a;

    /* JADX INFO: renamed from: b */
    protected static final StackTraceElement[] f2838b;

    static {
        f2837a = System.getProperty("surefire.test.class.path") != null;
        f2838b = new StackTraceElement[0];
    }

    AbstractC1362q() {
    }

    AbstractC1362q(Throwable th) {
        super(th);
    }

    @Override // java.lang.Throwable
    public final synchronized Throwable fillInStackTrace() {
        return null;
    }
}
