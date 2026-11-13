package com.google.p028a.p056i.p057a;

/* JADX INFO: renamed from: com.google.a.i.a.f */
/* JADX INFO: compiled from: ErrorCorrectionLevel.java */
/* JADX INFO: loaded from: classes.dex */
public enum EnumC1337f {
    L(1),
    M(0),
    Q(3),
    H(2);


    /* JADX INFO: renamed from: e */
    public final int f2738e;

    /* JADX INFO: renamed from: f */
    private static final EnumC1337f[] f2736f = {M, L, H, Q};

    EnumC1337f(int i) {
        this.f2738e = i;
    }

    /* JADX INFO: renamed from: a */
    private int m2459a() {
        return this.f2738e;
    }

    /* JADX INFO: renamed from: a */
    public static EnumC1337f m2460a(int i) {
        if (i < 0 || i >= f2736f.length) {
            throw new IllegalArgumentException();
        }
        return f2736f[i];
    }
}
