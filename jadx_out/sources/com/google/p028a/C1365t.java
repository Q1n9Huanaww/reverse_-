package com.google.p028a;

import com.google.p028a.p035c.p036a.C1191a;

/* JADX INFO: renamed from: com.google.a.t */
/* JADX INFO: compiled from: ResultPoint.java */
/* JADX INFO: loaded from: classes.dex */
public class C1365t {

    /* JADX INFO: renamed from: a */
    public final float f2858a;

    /* JADX INFO: renamed from: b */
    public final float f2859b;

    public C1365t(float f, float f2) {
        this.f2858a = f;
        this.f2859b = f2;
    }

    /* JADX INFO: renamed from: a */
    private float m2626a() {
        return this.f2858a;
    }

    /* JADX INFO: renamed from: b */
    private float m2630b() {
        return this.f2859b;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C1365t)) {
            return false;
        }
        C1365t c1365t = (C1365t) obj;
        return this.f2858a == c1365t.f2858a && this.f2859b == c1365t.f2859b;
    }

    public final int hashCode() {
        return (Float.floatToIntBits(this.f2858a) * 31) + Float.floatToIntBits(this.f2859b);
    }

    public final String toString() {
        return "(" + this.f2858a + ',' + this.f2859b + ')';
    }

    /* JADX INFO: renamed from: a */
    public static void m2629a(C1365t[] c1365tArr) {
        C1365t c1365t;
        C1365t c1365t2;
        C1365t c1365t3;
        float fM2627a = m2627a(c1365tArr[0], c1365tArr[1]);
        float fM2627a2 = m2627a(c1365tArr[1], c1365tArr[2]);
        float fM2627a3 = m2627a(c1365tArr[0], c1365tArr[2]);
        if (fM2627a2 >= fM2627a && fM2627a2 >= fM2627a3) {
            c1365t = c1365tArr[0];
            c1365t2 = c1365tArr[1];
            c1365t3 = c1365tArr[2];
        } else if (fM2627a3 >= fM2627a2 && fM2627a3 >= fM2627a) {
            c1365t = c1365tArr[1];
            c1365t2 = c1365tArr[0];
            c1365t3 = c1365tArr[2];
        } else {
            c1365t = c1365tArr[2];
            c1365t2 = c1365tArr[0];
            c1365t3 = c1365tArr[1];
        }
        float f = c1365t.f2858a;
        float f2 = c1365t.f2859b;
        if (((c1365t3.f2858a - f) * (c1365t2.f2859b - f2)) - ((c1365t3.f2859b - f2) * (c1365t2.f2858a - f)) < 0.0f) {
            C1365t c1365t4 = c1365t3;
            c1365t3 = c1365t2;
            c1365t2 = c1365t4;
        }
        c1365tArr[0] = c1365t2;
        c1365tArr[1] = c1365t;
        c1365tArr[2] = c1365t3;
    }

    /* JADX INFO: renamed from: a */
    public static float m2627a(C1365t c1365t, C1365t c1365t2) {
        return C1191a.m1763a(c1365t.f2858a, c1365t.f2859b, c1365t2.f2858a, c1365t2.f2859b);
    }

    /* JADX INFO: renamed from: a */
    private static float m2628a(C1365t c1365t, C1365t c1365t2, C1365t c1365t3) {
        float f = c1365t2.f2858a;
        float f2 = c1365t2.f2859b;
        return ((c1365t3.f2858a - f) * (c1365t.f2859b - f2)) - ((c1365t3.f2859b - f2) * (c1365t.f2858a - f));
    }
}
