package com.google.p028a;

/* JADX INFO: renamed from: com.google.a.h */
/* JADX INFO: compiled from: FormatException.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1303h extends AbstractC1362q {

    /* JADX INFO: renamed from: c */
    private static final C1303h f2540c;

    static {
        C1303h c1303h = new C1303h();
        f2540c = c1303h;
        c1303h.setStackTrace(f2838b);
    }

    private C1303h() {
    }

    private C1303h(Throwable th) {
        super(th);
    }

    /* JADX INFO: renamed from: a */
    public static C1303h m2217a() {
        return f2837a ? new C1303h() : f2540c;
    }

    /* JADX INFO: renamed from: a */
    public static C1303h m2218a(Throwable th) {
        return f2837a ? new C1303h(th) : f2540c;
    }
}
