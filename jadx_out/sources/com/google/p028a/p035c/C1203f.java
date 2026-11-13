package com.google.p028a.p035c;

import com.google.p028a.C1358m;

/* JADX INFO: renamed from: com.google.a.c.f */
/* JADX INFO: compiled from: DefaultGridSampler.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1203f extends AbstractC1206i {
    @Override // com.google.p028a.p035c.AbstractC1206i
    /* JADX INFO: renamed from: a */
    public final C1194b mo1844a(C1194b c1194b, int i, int i2, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16) throws C1358m {
        return mo1845a(c1194b, i, i2, C1208k.m1859a(f, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16));
    }

    /* JADX WARN: Code duplicated, block: B:31:0x009e  */
    /* JADX WARN: Code duplicated, block: B:33:0x00a2 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:34:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:54:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:56:0x00e0 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:57:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:89:0x00aa A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:93:0x00e8 A[SYNTHETIC] */
    @Override // com.google.p028a.p035c.AbstractC1206i
    /* JADX INFO: renamed from: a */
    public final C1194b mo1845a(C1194b c1194b, int i, int i2, C1208k c1208k) throws C1358m {
        int i3 = i2;
        if (i <= 0 || i3 <= 0) {
            throw C1358m.m2611a();
        }
        C1194b c1194b2 = new C1194b(i, i3);
        float[] fArr = new float[i * 2];
        int i4 = 0;
        while (i4 < i3) {
            int length = fArr.length;
            float f = i4 + 0.5f;
            for (int i5 = 0; i5 < length; i5 += 2) {
                fArr[i5] = (i5 / 2) + 0.5f;
                fArr[i5 + 1] = f;
            }
            int length2 = fArr.length;
            float f2 = c1208k.f2161a;
            float f3 = c1208k.f2162b;
            float f4 = c1208k.f2163c;
            float f5 = c1208k.f2164d;
            float f6 = c1208k.f2165e;
            float f7 = c1208k.f2166f;
            float f8 = c1208k.f2167g;
            float f9 = c1208k.f2168h;
            float f10 = c1208k.f2169i;
            for (int i6 = 0; i6 < length2; i6 += 2) {
                float f11 = fArr[i6];
                int i7 = i6 + 1;
                float f12 = fArr[i7];
                float f13 = (f4 * f11) + (f7 * f12) + f10;
                fArr[i6] = (((f2 * f11) + (f5 * f12)) + f8) / f13;
                fArr[i7] = (((f11 * f3) + (f12 * f6)) + f9) / f13;
            }
            int i8 = c1194b.f2077a;
            int i9 = c1194b.f2078b;
            boolean z = true;
            for (int i10 = 0; i10 < fArr.length && z; i10 += 2) {
                int i11 = (int) fArr[i10];
                int i12 = i10 + 1;
                int i13 = (int) fArr[i12];
                if (i11 < -1 || i11 > i8 || i13 < -1 || i13 > i9) {
                    throw C1358m.m2611a();
                }
                if (i11 == -1) {
                    fArr[i10] = 0.0f;
                } else {
                    if (i11 == i8) {
                        fArr[i10] = i8 - 1;
                    } else {
                        z = false;
                    }
                    if (i13 == -1) {
                        fArr[i12] = 0.0f;
                    } else {
                        if (i13 == i9) {
                            fArr[i12] = i9 - 1;
                        }
                    }
                    z = true;
                }
                z = true;
                if (i13 == -1) {
                    fArr[i12] = 0.0f;
                } else {
                    if (i13 == i9) {
                        fArr[i12] = i9 - 1;
                    }
                }
                z = true;
            }
            boolean z2 = true;
            for (int length3 = fArr.length - 2; length3 >= 0 && z2; length3 -= 2) {
                int i14 = (int) fArr[length3];
                int i15 = length3 + 1;
                int i16 = (int) fArr[i15];
                if (i14 < -1 || i14 > i8 || i16 < -1 || i16 > i9) {
                    throw C1358m.m2611a();
                }
                if (i14 == -1) {
                    fArr[length3] = 0.0f;
                } else {
                    if (i14 == i8) {
                        fArr[length3] = i8 - 1;
                    } else {
                        z2 = false;
                    }
                    if (i16 == -1) {
                        fArr[i15] = 0.0f;
                    } else {
                        if (i16 == i9) {
                            fArr[i15] = i9 - 1;
                        }
                    }
                    z2 = true;
                }
                z2 = true;
                if (i16 == -1) {
                    fArr[i15] = 0.0f;
                } else {
                    if (i16 == i9) {
                        fArr[i15] = i9 - 1;
                    }
                }
                z2 = true;
            }
            for (int i17 = 0; i17 < length; i17 += 2) {
                try {
                    if (c1194b.m1789a((int) fArr[i17], (int) fArr[i17 + 1])) {
                        c1194b2.m1790b(i17 / 2, i4);
                    }
                } catch (ArrayIndexOutOfBoundsException unused) {
                    throw C1358m.m2611a();
                }
            }
            i4++;
            i3 = i2;
        }
        return c1194b2;
    }
}
