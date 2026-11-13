package com.google.p028a.p051h.p052a.p053a;

import com.google.p028a.p051h.C1304a;

/* JADX INFO: renamed from: com.google.a.h.a.a.b */
/* JADX INFO: compiled from: ModulusGF.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1307b {

    /* JADX INFO: renamed from: a */
    public static final C1307b f2557a = new C1307b();

    /* JADX INFO: renamed from: d */
    public final C1308c f2560d;

    /* JADX INFO: renamed from: e */
    public final C1308c f2561e;

    /* JADX INFO: renamed from: f */
    final int f2562f = C1304a.f2541a;

    /* JADX INFO: renamed from: b */
    public final int[] f2558b = new int[C1304a.f2541a];

    /* JADX INFO: renamed from: c */
    public final int[] f2559c = new int[C1304a.f2541a];

    private C1307b() {
        int i = 1;
        for (int i2 = 0; i2 < 929; i2++) {
            this.f2558b[i2] = i;
            i = (i * 3) % C1304a.f2541a;
        }
        for (int i3 = 0; i3 < 928; i3++) {
            this.f2559c[this.f2558b[i3]] = i3;
        }
        this.f2560d = new C1308c(this, new int[]{0});
        this.f2561e = new C1308c(this, new int[]{1});
    }

    /* JADX INFO: renamed from: a */
    private C1308c m2231a() {
        return this.f2560d;
    }

    /* JADX INFO: renamed from: b */
    private C1308c m2233b() {
        return this.f2561e;
    }

    /* JADX INFO: renamed from: a */
    public final C1308c m2237a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        if (i2 == 0) {
            return this.f2560d;
        }
        int[] iArr = new int[i + 1];
        iArr[0] = i2;
        return new C1308c(this, iArr);
    }

    /* JADX INFO: renamed from: b */
    final int m2238b(int i, int i2) {
        return (i + i2) % this.f2562f;
    }

    /* JADX INFO: renamed from: c */
    public final int m2239c(int i, int i2) {
        return ((this.f2562f + i) - i2) % this.f2562f;
    }

    /* JADX INFO: renamed from: b */
    private int m2232b(int i) {
        return this.f2558b[i];
    }

    /* JADX INFO: renamed from: c */
    private int m2235c(int i) {
        if (i == 0) {
            throw new IllegalArgumentException();
        }
        return this.f2559c[i];
    }

    /* JADX INFO: renamed from: a */
    public final int m2236a(int i) {
        if (i == 0) {
            throw new ArithmeticException();
        }
        return this.f2558b[(this.f2562f - this.f2559c[i]) - 1];
    }

    /* JADX INFO: renamed from: d */
    public final int m2240d(int i, int i2) {
        if (i == 0 || i2 == 0) {
            return 0;
        }
        return this.f2558b[(this.f2559c[i] + this.f2559c[i2]) % (this.f2562f - 1)];
    }

    /* JADX INFO: renamed from: c */
    private int m2234c() {
        return this.f2562f;
    }
}
