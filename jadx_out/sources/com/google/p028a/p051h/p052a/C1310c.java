package com.google.p028a.p051h.p052a;

import com.google.p028a.C1358m;
import com.google.p028a.C1365t;
import com.google.p028a.p035c.C1194b;

/* JADX INFO: renamed from: com.google.a.h.a.c */
/* JADX INFO: compiled from: BoundingBox.java */
/* JADX INFO: loaded from: classes.dex */
final class C1310c {

    /* JADX INFO: renamed from: a */
    final C1194b f2566a;

    /* JADX INFO: renamed from: b */
    final C1365t f2567b;

    /* JADX INFO: renamed from: c */
    final C1365t f2568c;

    /* JADX INFO: renamed from: d */
    final C1365t f2569d;

    /* JADX INFO: renamed from: e */
    final C1365t f2570e;

    /* JADX INFO: renamed from: f */
    final int f2571f;

    /* JADX INFO: renamed from: g */
    final int f2572g;

    /* JADX INFO: renamed from: h */
    final int f2573h;

    /* JADX INFO: renamed from: i */
    final int f2574i;

    C1310c(C1194b c1194b, C1365t c1365t, C1365t c1365t2, C1365t c1365t3, C1365t c1365t4) throws C1358m {
        boolean z = c1365t == null || c1365t2 == null;
        boolean z2 = c1365t3 == null || c1365t4 == null;
        if (z && z2) {
            throw C1358m.m2611a();
        }
        if (z) {
            c1365t = new C1365t(0.0f, c1365t3.f2859b);
            c1365t2 = new C1365t(0.0f, c1365t4.f2859b);
        } else if (z2) {
            c1365t3 = new C1365t(c1194b.f2077a - 1, c1365t.f2859b);
            c1365t4 = new C1365t(c1194b.f2077a - 1, c1365t2.f2859b);
        }
        this.f2566a = c1194b;
        this.f2567b = c1365t;
        this.f2568c = c1365t2;
        this.f2569d = c1365t3;
        this.f2570e = c1365t4;
        this.f2571f = (int) Math.min(c1365t.f2858a, c1365t2.f2858a);
        this.f2572g = (int) Math.max(c1365t3.f2858a, c1365t4.f2858a);
        this.f2573h = (int) Math.min(c1365t.f2859b, c1365t3.f2859b);
        this.f2574i = (int) Math.max(c1365t2.f2859b, c1365t4.f2859b);
    }

    C1310c(C1310c c1310c) {
        this.f2566a = c1310c.f2566a;
        this.f2567b = c1310c.f2567b;
        this.f2568c = c1310c.f2568c;
        this.f2569d = c1310c.f2569d;
        this.f2570e = c1310c.f2570e;
        this.f2571f = c1310c.f2571f;
        this.f2572g = c1310c.f2572g;
        this.f2573h = c1310c.f2573h;
        this.f2574i = c1310c.f2574i;
    }

    /* JADX INFO: renamed from: a */
    private static C1310c m2257a(C1310c c1310c, C1310c c1310c2) throws C1358m {
        if (c1310c == null) {
            return c1310c2;
        }
        return c1310c2 == null ? c1310c : new C1310c(c1310c.f2566a, c1310c.f2567b, c1310c.f2568c, c1310c2.f2569d, c1310c2.f2570e);
    }

    /* JADX WARN: Code duplicated, block: B:17:0x002b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:18:0x002d  */
    /* JADX WARN: Code duplicated, block: B:19:0x0030  */
    /* JADX WARN: Code duplicated, block: B:22:0x003c  */
    /* JADX WARN: Code duplicated, block: B:25:0x004c  */
    /* JADX WARN: Code duplicated, block: B:26:0x004e  */
    /* JADX WARN: Code duplicated, block: B:27:0x0051  */
    /* JADX INFO: renamed from: a */
    private C1310c m2256a(int i, int i2, boolean z) throws C1358m {
        C1365t c1365t;
        C1365t c1365t2;
        C1365t c1365t3;
        C1365t c1365t4;
        C1365t c1365t5;
        int i3;
        C1365t c1365t6;
        C1365t c1365t7 = this.f2567b;
        C1365t c1365t8 = this.f2568c;
        C1365t c1365t9 = this.f2569d;
        C1365t c1365t10 = this.f2570e;
        if (i > 0) {
            C1365t c1365t11 = z ? this.f2567b : this.f2569d;
            int i4 = ((int) c1365t11.f2859b) - i;
            if (i4 < 0) {
                i4 = 0;
            }
            C1365t c1365t12 = new C1365t(c1365t11.f2858a, i4);
            if (z) {
                c1365t = c1365t12;
            } else {
                c1365t2 = c1365t12;
                c1365t = c1365t7;
            }
            if (i2 > 0) {
                if (z) {
                    c1365t5 = this.f2568c;
                } else {
                    c1365t5 = this.f2570e;
                }
                i3 = ((int) c1365t5.f2859b) + i2;
                if (i3 >= this.f2566a.f2078b) {
                    i3 = this.f2566a.f2078b - 1;
                }
                c1365t6 = new C1365t(c1365t5.f2858a, i3);
                if (z) {
                    c1365t3 = c1365t6;
                } else {
                    c1365t4 = c1365t6;
                    c1365t3 = c1365t8;
                }
                return new C1310c(this.f2566a, c1365t, c1365t3, c1365t2, c1365t4);
            }
            c1365t3 = c1365t8;
            c1365t4 = c1365t10;
            return new C1310c(this.f2566a, c1365t, c1365t3, c1365t2, c1365t4);
        }
        c1365t = c1365t7;
        c1365t2 = c1365t9;
        if (i2 > 0) {
            if (z) {
                c1365t5 = this.f2568c;
            } else {
                c1365t5 = this.f2570e;
            }
            i3 = ((int) c1365t5.f2859b) + i2;
            if (i3 >= this.f2566a.f2078b) {
                i3 = this.f2566a.f2078b - 1;
            }
            c1365t6 = new C1365t(c1365t5.f2858a, i3);
            if (z) {
                c1365t3 = c1365t6;
            } else {
                c1365t4 = c1365t6;
                c1365t3 = c1365t8;
            }
            return new C1310c(this.f2566a, c1365t, c1365t3, c1365t2, c1365t4);
        }
        c1365t3 = c1365t8;
        c1365t4 = c1365t10;
        return new C1310c(this.f2566a, c1365t, c1365t3, c1365t2, c1365t4);
    }

    /* JADX INFO: renamed from: a */
    private int m2255a() {
        return this.f2571f;
    }

    /* JADX INFO: renamed from: b */
    private int m2258b() {
        return this.f2572g;
    }

    /* JADX INFO: renamed from: c */
    private int m2259c() {
        return this.f2573h;
    }

    /* JADX INFO: renamed from: d */
    private int m2260d() {
        return this.f2574i;
    }

    /* JADX INFO: renamed from: e */
    private C1365t m2261e() {
        return this.f2567b;
    }

    /* JADX INFO: renamed from: f */
    private C1365t m2262f() {
        return this.f2569d;
    }

    /* JADX INFO: renamed from: g */
    private C1365t m2263g() {
        return this.f2568c;
    }

    /* JADX INFO: renamed from: h */
    private C1365t m2264h() {
        return this.f2570e;
    }
}
