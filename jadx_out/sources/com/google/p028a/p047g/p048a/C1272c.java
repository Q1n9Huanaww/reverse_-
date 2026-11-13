package com.google.p028a.p047g.p048a;

import com.google.p028a.C1365t;

/* JADX INFO: renamed from: com.google.a.g.a.c */
/* JADX INFO: compiled from: FinderPattern.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1272c {

    /* JADX INFO: renamed from: a */
    public final int f2424a;

    /* JADX INFO: renamed from: b */
    public final int[] f2425b;

    /* JADX INFO: renamed from: c */
    public final C1365t[] f2426c;

    public C1272c(int i, int[] iArr, int i2, int i3, int i4) {
        this.f2424a = i;
        this.f2425b = iArr;
        float f = i4;
        this.f2426c = new C1365t[]{new C1365t(i2, f), new C1365t(i3, f)};
    }

    /* JADX INFO: renamed from: a */
    private int m2137a() {
        return this.f2424a;
    }

    /* JADX INFO: renamed from: b */
    private int[] m2138b() {
        return this.f2425b;
    }

    /* JADX INFO: renamed from: c */
    private C1365t[] m2139c() {
        return this.f2426c;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof C1272c) && this.f2424a == ((C1272c) obj).f2424a;
    }

    public final int hashCode() {
        return this.f2424a;
    }
}
