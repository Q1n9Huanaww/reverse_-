package com.google.p028a.p051h.p052a;

/* JADX INFO: renamed from: com.google.a.h.a.d */
/* JADX INFO: compiled from: Codeword.java */
/* JADX INFO: loaded from: classes.dex */
final class C1311d {

    /* JADX INFO: renamed from: f */
    private static final int f2575f = -1;

    /* JADX INFO: renamed from: a */
    final int f2576a;

    /* JADX INFO: renamed from: b */
    final int f2577b;

    /* JADX INFO: renamed from: c */
    final int f2578c;

    /* JADX INFO: renamed from: d */
    final int f2579d;

    /* JADX INFO: renamed from: e */
    int f2580e = -1;

    C1311d(int i, int i2, int i3, int i4) {
        this.f2576a = i;
        this.f2577b = i2;
        this.f2578c = i3;
        this.f2579d = i4;
    }

    /* JADX INFO: renamed from: a */
    final boolean m2271a() {
        return m2272a(this.f2580e);
    }

    /* JADX INFO: renamed from: a */
    final boolean m2272a(int i) {
        return i != -1 && this.f2578c == (i % 3) * 3;
    }

    /* JADX INFO: renamed from: b */
    final void m2273b() {
        this.f2580e = ((this.f2579d / 30) * 3) + (this.f2578c / 3);
    }

    /* JADX INFO: renamed from: c */
    final int m2274c() {
        return this.f2577b - this.f2576a;
    }

    /* JADX INFO: renamed from: d */
    private int m2266d() {
        return this.f2576a;
    }

    /* JADX INFO: renamed from: e */
    private int m2267e() {
        return this.f2577b;
    }

    /* JADX INFO: renamed from: f */
    private int m2268f() {
        return this.f2578c;
    }

    /* JADX INFO: renamed from: g */
    private int m2269g() {
        return this.f2579d;
    }

    /* JADX INFO: renamed from: h */
    private int m2270h() {
        return this.f2580e;
    }

    /* JADX INFO: renamed from: b */
    private void m2265b(int i) {
        this.f2580e = i;
    }

    public final String toString() {
        return this.f2580e + "|" + this.f2579d;
    }
}
