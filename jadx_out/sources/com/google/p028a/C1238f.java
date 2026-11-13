package com.google.p028a;

/* JADX INFO: renamed from: com.google.a.f */
/* JADX INFO: compiled from: Dimension.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1238f {

    /* JADX INFO: renamed from: a */
    public final int f2309a;

    /* JADX INFO: renamed from: b */
    public final int f2310b;

    private C1238f(int i, int i2) {
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException();
        }
        this.f2309a = i;
        this.f2310b = i2;
    }

    /* JADX INFO: renamed from: a */
    private int m2019a() {
        return this.f2309a;
    }

    /* JADX INFO: renamed from: b */
    private int m2020b() {
        return this.f2310b;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C1238f)) {
            return false;
        }
        C1238f c1238f = (C1238f) obj;
        return this.f2309a == c1238f.f2309a && this.f2310b == c1238f.f2310b;
    }

    public final int hashCode() {
        return (this.f2309a * 32713) + this.f2310b;
    }

    public final String toString() {
        return this.f2309a + "x" + this.f2310b;
    }
}
