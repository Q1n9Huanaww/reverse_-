package com.google.p028a.p047g.p048a;

/* JADX INFO: renamed from: com.google.a.g.a.b */
/* JADX INFO: compiled from: DataCharacter.java */
/* JADX INFO: loaded from: classes.dex */
public class C1271b {

    /* JADX INFO: renamed from: a */
    public final int f2422a;

    /* JADX INFO: renamed from: b */
    public final int f2423b;

    public C1271b(int i, int i2) {
        this.f2422a = i;
        this.f2423b = i2;
    }

    /* JADX INFO: renamed from: a */
    private int m2135a() {
        return this.f2422a;
    }

    /* JADX INFO: renamed from: b */
    private int m2136b() {
        return this.f2423b;
    }

    public final String toString() {
        return this.f2422a + "(" + this.f2423b + ')';
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C1271b)) {
            return false;
        }
        C1271b c1271b = (C1271b) obj;
        return this.f2422a == c1271b.f2422a && this.f2423b == c1271b.f2423b;
    }

    public final int hashCode() {
        return this.f2422a ^ this.f2423b;
    }
}
