package com.google.p028a.p047g.p048a.p049a.p050a;

import com.google.p028a.C1303h;

/* JADX INFO: renamed from: com.google.a.g.a.a.a.p */
/* JADX INFO: compiled from: DecodedNumeric.java */
/* JADX INFO: loaded from: classes.dex */
final class C1264p extends AbstractC1265q {

    /* JADX INFO: renamed from: c */
    static final int f2386c = 10;

    /* JADX INFO: renamed from: a */
    final int f2387a;

    /* JADX INFO: renamed from: b */
    final int f2388b;

    C1264p(int i, int i2, int i3) throws C1303h {
        super(i);
        if (i2 < 0 || i2 > 10 || i3 < 0 || i3 > 10) {
            throw C1303h.m2217a();
        }
        this.f2387a = i2;
        this.f2388b = i3;
    }

    /* JADX INFO: renamed from: b */
    private int m2079b() {
        return this.f2387a;
    }

    /* JADX INFO: renamed from: c */
    private int m2080c() {
        return this.f2388b;
    }

    /* JADX INFO: renamed from: d */
    private int m2081d() {
        return (this.f2387a * 10) + this.f2388b;
    }

    /* JADX INFO: renamed from: e */
    private boolean m2082e() {
        return this.f2387a == 10;
    }

    /* JADX INFO: renamed from: a */
    final boolean m2084a() {
        return this.f2388b == 10;
    }

    /* JADX INFO: renamed from: f */
    private boolean m2083f() {
        return this.f2387a == 10 || this.f2388b == 10;
    }
}
