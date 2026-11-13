package com.google.p028a;

/* JADX INFO: renamed from: com.google.a.d */
/* JADX INFO: compiled from: ChecksumException.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1210d extends AbstractC1362q {

    /* JADX INFO: renamed from: c */
    private static final C1210d f2177c;

    static {
        C1210d c1210d = new C1210d();
        f2177c = c1210d;
        c1210d.setStackTrace(f2838b);
    }

    private C1210d() {
    }

    private C1210d(Throwable th) {
        super(th);
    }

    /* JADX INFO: renamed from: a */
    public static C1210d m1865a() {
        return f2837a ? new C1210d() : f2177c;
    }

    /* JADX INFO: renamed from: a */
    private static C1210d m1866a(Throwable th) {
        return f2837a ? new C1210d(th) : f2177c;
    }
}
