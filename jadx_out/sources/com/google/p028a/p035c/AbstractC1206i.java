package com.google.p028a.p035c;

import com.google.p028a.C1358m;

/* JADX INFO: renamed from: com.google.a.c.i */
/* JADX INFO: compiled from: GridSampler.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1206i {

    /* JADX INFO: renamed from: a */
    private static AbstractC1206i f2154a = new C1203f();

    /* JADX INFO: renamed from: a */
    public abstract C1194b mo1844a(C1194b c1194b, int i, int i2, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16) throws C1358m;

    /* JADX INFO: renamed from: a */
    public abstract C1194b mo1845a(C1194b c1194b, int i, int i2, C1208k c1208k) throws C1358m;

    /* JADX INFO: renamed from: a */
    private static void m1852a(AbstractC1206i abstractC1206i) {
        f2154a = abstractC1206i;
    }

    /* JADX INFO: renamed from: a */
    public static AbstractC1206i m1850a() {
        return f2154a;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0031  */
    /* JADX WARN: Code duplicated, block: B:21:0x0035 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:22:0x0037  */
    /* JADX WARN: Code duplicated, block: B:42:0x006f  */
    /* JADX WARN: Code duplicated, block: B:44:0x0073 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:45:0x0075  */
    /* JADX WARN: Code duplicated, block: B:57:0x003d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:65:0x007b A[SYNTHETIC] */
    /* JADX INFO: renamed from: a */
    private static void m1851a(C1194b c1194b, float[] fArr) throws C1358m {
        int i = c1194b.f2077a;
        int i2 = c1194b.f2078b;
        boolean z = true;
        for (int i3 = 0; i3 < fArr.length && z; i3 += 2) {
            int i4 = (int) fArr[i3];
            int i5 = i3 + 1;
            int i6 = (int) fArr[i5];
            if (i4 < -1 || i4 > i || i6 < -1 || i6 > i2) {
                throw C1358m.m2611a();
            }
            if (i4 == -1) {
                fArr[i3] = 0.0f;
            } else {
                if (i4 == i) {
                    fArr[i3] = i - 1;
                } else {
                    z = false;
                }
                if (i6 == -1) {
                    fArr[i5] = 0.0f;
                } else {
                    if (i6 == i2) {
                        fArr[i5] = i2 - 1;
                    }
                }
                z = true;
            }
            z = true;
            if (i6 == -1) {
                fArr[i5] = 0.0f;
            } else {
                if (i6 == i2) {
                    fArr[i5] = i2 - 1;
                }
            }
            z = true;
        }
        boolean z2 = true;
        for (int length = fArr.length - 2; length >= 0 && z2; length -= 2) {
            int i7 = (int) fArr[length];
            int i8 = length + 1;
            int i9 = (int) fArr[i8];
            if (i7 < -1 || i7 > i || i9 < -1 || i9 > i2) {
                throw C1358m.m2611a();
            }
            if (i7 == -1) {
                fArr[length] = 0.0f;
            } else {
                if (i7 == i) {
                    fArr[length] = i - 1;
                } else {
                    z2 = false;
                }
                if (i9 == -1) {
                    fArr[i8] = 0.0f;
                } else {
                    if (i9 == i2) {
                        fArr[i8] = i2 - 1;
                    }
                }
                z2 = true;
            }
            z2 = true;
            if (i9 == -1) {
                fArr[i8] = 0.0f;
            } else {
                if (i9 == i2) {
                    fArr[i8] = i2 - 1;
                }
            }
            z2 = true;
        }
    }
}
