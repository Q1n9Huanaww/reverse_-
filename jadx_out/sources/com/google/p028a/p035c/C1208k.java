package com.google.p028a.p035c;

/* JADX INFO: renamed from: com.google.a.c.k */
/* JADX INFO: compiled from: PerspectiveTransform.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1208k {

    /* JADX INFO: renamed from: a */
    final float f2161a;

    /* JADX INFO: renamed from: b */
    final float f2162b;

    /* JADX INFO: renamed from: c */
    final float f2163c;

    /* JADX INFO: renamed from: d */
    final float f2164d;

    /* JADX INFO: renamed from: e */
    final float f2165e;

    /* JADX INFO: renamed from: f */
    final float f2166f;

    /* JADX INFO: renamed from: g */
    final float f2167g;

    /* JADX INFO: renamed from: h */
    final float f2168h;

    /* JADX INFO: renamed from: i */
    final float f2169i;

    private C1208k(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        this.f2161a = f;
        this.f2162b = f4;
        this.f2163c = f7;
        this.f2164d = f2;
        this.f2165e = f5;
        this.f2166f = f8;
        this.f2167g = f3;
        this.f2168h = f6;
        this.f2169i = f9;
    }

    /* JADX INFO: renamed from: a */
    private void m1861a(float[] fArr) {
        int length = fArr.length;
        float f = this.f2161a;
        float f2 = this.f2162b;
        float f3 = this.f2163c;
        float f4 = this.f2164d;
        float f5 = this.f2165e;
        float f6 = this.f2166f;
        float f7 = this.f2167g;
        float f8 = this.f2168h;
        float f9 = this.f2169i;
        for (int i = 0; i < length; i += 2) {
            float f10 = fArr[i];
            int i2 = i + 1;
            float f11 = fArr[i2];
            float f12 = (f3 * f10) + (f6 * f11) + f9;
            fArr[i] = (((f * f10) + (f4 * f11)) + f7) / f12;
            fArr[i2] = (((f10 * f2) + (f11 * f5)) + f8) / f12;
        }
    }

    /* JADX INFO: renamed from: a */
    private void m1862a(float[] fArr, float[] fArr2) {
        int length = fArr.length;
        for (int i = 0; i < length; i++) {
            float f = fArr[i];
            float f2 = fArr2[i];
            float f3 = (this.f2163c * f) + (this.f2166f * f2) + this.f2169i;
            fArr[i] = (((this.f2161a * f) + (this.f2164d * f2)) + this.f2167g) / f3;
            fArr2[i] = (((this.f2162b * f) + (this.f2165e * f2)) + this.f2168h) / f3;
        }
    }

    /* JADX INFO: renamed from: a */
    private static C1208k m1858a(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        float f9 = ((f - f3) + f5) - f7;
        float f10 = ((f2 - f4) + f6) - f8;
        if (f9 == 0.0f && f10 == 0.0f) {
            return new C1208k(f3 - f, f5 - f3, f, f4 - f2, f6 - f4, f2, 0.0f, 0.0f, 1.0f);
        }
        float f11 = f3 - f5;
        float f12 = f7 - f5;
        float f13 = f4 - f6;
        float f14 = f8 - f6;
        float f15 = (f11 * f14) - (f12 * f13);
        float f16 = ((f14 * f9) - (f12 * f10)) / f15;
        float f17 = ((f11 * f10) - (f9 * f13)) / f15;
        return new C1208k((f3 - f) + (f16 * f3), (f17 * f7) + (f7 - f), f, (f4 - f2) + (f16 * f4), (f8 - f2) + (f17 * f8), f2, f16, f17, 1.0f);
    }

    /* JADX INFO: renamed from: b */
    private static C1208k m1863b(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        C1208k c1208kM1858a = m1858a(f, f2, f3, f4, f5, f6, f7, f8);
        return new C1208k((c1208kM1858a.f2165e * c1208kM1858a.f2169i) - (c1208kM1858a.f2166f * c1208kM1858a.f2168h), (c1208kM1858a.f2166f * c1208kM1858a.f2167g) - (c1208kM1858a.f2164d * c1208kM1858a.f2169i), (c1208kM1858a.f2164d * c1208kM1858a.f2168h) - (c1208kM1858a.f2165e * c1208kM1858a.f2167g), (c1208kM1858a.f2163c * c1208kM1858a.f2168h) - (c1208kM1858a.f2162b * c1208kM1858a.f2169i), (c1208kM1858a.f2161a * c1208kM1858a.f2169i) - (c1208kM1858a.f2163c * c1208kM1858a.f2167g), (c1208kM1858a.f2162b * c1208kM1858a.f2167g) - (c1208kM1858a.f2161a * c1208kM1858a.f2168h), (c1208kM1858a.f2162b * c1208kM1858a.f2166f) - (c1208kM1858a.f2163c * c1208kM1858a.f2165e), (c1208kM1858a.f2163c * c1208kM1858a.f2164d) - (c1208kM1858a.f2161a * c1208kM1858a.f2166f), (c1208kM1858a.f2161a * c1208kM1858a.f2165e) - (c1208kM1858a.f2162b * c1208kM1858a.f2164d));
    }

    /* JADX INFO: renamed from: a */
    private C1208k m1857a() {
        return new C1208k((this.f2165e * this.f2169i) - (this.f2166f * this.f2168h), (this.f2166f * this.f2167g) - (this.f2164d * this.f2169i), (this.f2164d * this.f2168h) - (this.f2165e * this.f2167g), (this.f2163c * this.f2168h) - (this.f2162b * this.f2169i), (this.f2161a * this.f2169i) - (this.f2163c * this.f2167g), (this.f2162b * this.f2167g) - (this.f2161a * this.f2168h), (this.f2162b * this.f2166f) - (this.f2163c * this.f2165e), (this.f2163c * this.f2164d) - (this.f2161a * this.f2166f), (this.f2161a * this.f2165e) - (this.f2162b * this.f2164d));
    }

    /* JADX INFO: renamed from: a */
    private C1208k m1860a(C1208k c1208k) {
        return new C1208k((this.f2167g * c1208k.f2163c) + (this.f2161a * c1208k.f2161a) + (this.f2164d * c1208k.f2162b), (this.f2167g * c1208k.f2166f) + (this.f2161a * c1208k.f2164d) + (this.f2164d * c1208k.f2165e), (this.f2167g * c1208k.f2169i) + (this.f2161a * c1208k.f2167g) + (this.f2164d * c1208k.f2168h), (this.f2168h * c1208k.f2163c) + (this.f2162b * c1208k.f2161a) + (this.f2165e * c1208k.f2162b), (this.f2168h * c1208k.f2166f) + (this.f2162b * c1208k.f2164d) + (this.f2165e * c1208k.f2165e), (this.f2168h * c1208k.f2169i) + (this.f2162b * c1208k.f2167g) + (this.f2165e * c1208k.f2168h), (this.f2169i * c1208k.f2163c) + (this.f2163c * c1208k.f2161a) + (this.f2166f * c1208k.f2162b), (this.f2169i * c1208k.f2166f) + (this.f2163c * c1208k.f2164d) + (this.f2166f * c1208k.f2165e), (this.f2169i * c1208k.f2169i) + (this.f2163c * c1208k.f2167g) + (this.f2166f * c1208k.f2168h));
    }

    /* JADX INFO: renamed from: a */
    public static C1208k m1859a(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16) {
        C1208k c1208kM1858a = m1858a(f, f2, f3, f4, f5, f6, f7, f8);
        C1208k c1208k = new C1208k((c1208kM1858a.f2165e * c1208kM1858a.f2169i) - (c1208kM1858a.f2166f * c1208kM1858a.f2168h), (c1208kM1858a.f2166f * c1208kM1858a.f2167g) - (c1208kM1858a.f2164d * c1208kM1858a.f2169i), (c1208kM1858a.f2164d * c1208kM1858a.f2168h) - (c1208kM1858a.f2165e * c1208kM1858a.f2167g), (c1208kM1858a.f2163c * c1208kM1858a.f2168h) - (c1208kM1858a.f2162b * c1208kM1858a.f2169i), (c1208kM1858a.f2161a * c1208kM1858a.f2169i) - (c1208kM1858a.f2163c * c1208kM1858a.f2167g), (c1208kM1858a.f2162b * c1208kM1858a.f2167g) - (c1208kM1858a.f2161a * c1208kM1858a.f2168h), (c1208kM1858a.f2162b * c1208kM1858a.f2166f) - (c1208kM1858a.f2163c * c1208kM1858a.f2165e), (c1208kM1858a.f2163c * c1208kM1858a.f2164d) - (c1208kM1858a.f2161a * c1208kM1858a.f2166f), (c1208kM1858a.f2161a * c1208kM1858a.f2165e) - (c1208kM1858a.f2162b * c1208kM1858a.f2164d));
        C1208k c1208kM1858a2 = m1858a(f9, f10, f11, f12, f13, f14, f15, f16);
        return new C1208k((c1208kM1858a2.f2167g * c1208k.f2163c) + (c1208kM1858a2.f2161a * c1208k.f2161a) + (c1208kM1858a2.f2164d * c1208k.f2162b), (c1208kM1858a2.f2167g * c1208k.f2166f) + (c1208kM1858a2.f2161a * c1208k.f2164d) + (c1208kM1858a2.f2164d * c1208k.f2165e), (c1208kM1858a2.f2167g * c1208k.f2169i) + (c1208kM1858a2.f2161a * c1208k.f2167g) + (c1208kM1858a2.f2164d * c1208k.f2168h), (c1208kM1858a2.f2168h * c1208k.f2163c) + (c1208kM1858a2.f2162b * c1208k.f2161a) + (c1208kM1858a2.f2165e * c1208k.f2162b), (c1208kM1858a2.f2168h * c1208k.f2166f) + (c1208kM1858a2.f2162b * c1208k.f2164d) + (c1208kM1858a2.f2165e * c1208k.f2165e), (c1208kM1858a2.f2168h * c1208k.f2169i) + (c1208kM1858a2.f2162b * c1208k.f2167g) + (c1208kM1858a2.f2165e * c1208k.f2168h), (c1208kM1858a2.f2169i * c1208k.f2163c) + (c1208kM1858a2.f2163c * c1208k.f2161a) + (c1208kM1858a2.f2166f * c1208k.f2162b), (c1208kM1858a2.f2169i * c1208k.f2166f) + (c1208kM1858a2.f2163c * c1208k.f2164d) + (c1208kM1858a2.f2166f * c1208k.f2165e), (c1208kM1858a2.f2163c * c1208k.f2167g) + (c1208kM1858a2.f2166f * c1208k.f2168h) + (c1208kM1858a2.f2169i * c1208k.f2169i));
    }
}
