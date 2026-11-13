package com.google.p028a.p051h.p052a;

import com.google.p028a.p035c.p036a.C1191a;
import com.google.p028a.p051h.C1304a;
import java.lang.reflect.Array;

/* JADX INFO: renamed from: com.google.a.h.a.i */
/* JADX INFO: compiled from: PDF417CodewordDecoder.java */
/* JADX INFO: loaded from: classes.dex */
final class C1316i {

    /* JADX INFO: renamed from: a */
    private static final float[][] f2628a = (float[][]) Array.newInstance((Class<?>) float.class, C1304a.f2548h.length, 8);

    static {
        int i;
        for (int i2 = 0; i2 < C1304a.f2548h.length; i2++) {
            int i3 = C1304a.f2548h[i2];
            int i4 = i3 & 1;
            int i5 = i3;
            int i6 = 0;
            while (i6 < 8) {
                float f = 0.0f;
                while (true) {
                    i = i5 & 1;
                    if (i == i4) {
                        f += 1.0f;
                        i5 >>= 1;
                    }
                }
                f2628a[i2][(8 - i6) - 1] = f / 17.0f;
                i6++;
                i4 = i;
            }
        }
    }

    private C1316i() {
    }

    /* JADX INFO: renamed from: b */
    private static int[] m2314b(int[] iArr) {
        float fM1766a = C1191a.m1766a(iArr);
        int[] iArr2 = new int[8];
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < 17; i3++) {
            if (iArr[i2] + i <= (fM1766a / 34.0f) + ((i3 * fM1766a) / 17.0f)) {
                i += iArr[i2];
                i2++;
            }
            iArr2[i2] = iArr2[i2] + 1;
        }
        return iArr2;
    }

    /* JADX INFO: renamed from: d */
    private static int m2316d(int[] iArr) {
        long j = 0;
        int i = 0;
        while (i < iArr.length) {
            long j2 = j;
            int i2 = 0;
            while (i2 < iArr[i]) {
                int i3 = 1;
                long j3 = j2 << 1;
                if (i % 2 != 0) {
                    i3 = 0;
                }
                i2++;
                j2 = j3 | ((long) i3);
            }
            i++;
            j = j2;
        }
        return (int) j;
    }

    /* JADX INFO: renamed from: e */
    private static int m2317e(int[] iArr) {
        int iM1766a = C1191a.m1766a(iArr);
        float[] fArr = new float[8];
        if (iM1766a > 1) {
            for (int i = 0; i < 8; i++) {
                fArr[i] = iArr[i] / iM1766a;
            }
        }
        int i2 = -1;
        float f = Float.MAX_VALUE;
        for (int i3 = 0; i3 < f2628a.length; i3++) {
            float[] fArr2 = f2628a[i3];
            float f2 = 0.0f;
            for (int i4 = 0; i4 < 8; i4++) {
                float f3 = fArr2[i4] - fArr[i4];
                f2 += f3 * f3;
                if (f2 >= f) {
                    break;
                }
            }
            if (f2 < f) {
                i2 = C1304a.f2548h[i3];
                f = f2;
            }
        }
        return i2;
    }

    /* JADX INFO: renamed from: a */
    static int m2313a(int[] iArr) {
        float fM1766a = C1191a.m1766a(iArr);
        int[] iArr2 = new int[8];
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < 17; i3++) {
            if (iArr[i2] + i <= (fM1766a / 34.0f) + ((i3 * fM1766a) / 17.0f)) {
                i += iArr[i2];
                i2++;
            }
            iArr2[i2] = iArr2[i2] + 1;
        }
        long j = 0;
        int i4 = 0;
        while (i4 < 8) {
            long j2 = j;
            int i5 = 0;
            while (i5 < iArr2[i4]) {
                i5++;
                j2 = (j2 << 1) | ((long) (i4 % 2 == 0 ? 1 : 0));
            }
            i4++;
            j = j2;
        }
        int i6 = (int) j;
        int i7 = -1;
        if (C1304a.m2219a(i6) == -1) {
            i6 = -1;
        }
        if (i6 != -1) {
            return i6;
        }
        int iM1766a = C1191a.m1766a(iArr);
        float[] fArr = new float[8];
        if (iM1766a > 1) {
            for (int i8 = 0; i8 < 8; i8++) {
                fArr[i8] = iArr[i8] / iM1766a;
            }
        }
        float f = Float.MAX_VALUE;
        for (int i9 = 0; i9 < f2628a.length; i9++) {
            float[] fArr2 = f2628a[i9];
            float f2 = 0.0f;
            for (int i10 = 0; i10 < 8; i10++) {
                float f3 = fArr2[i10] - fArr[i10];
                f2 += f3 * f3;
                if (f2 >= f) {
                    break;
                }
            }
            if (f2 < f) {
                i7 = C1304a.f2548h[i9];
                f = f2;
            }
        }
        return i7;
    }

    /* JADX INFO: renamed from: c */
    private static int m2315c(int[] iArr) {
        long j = 0;
        int i = 0;
        while (i < iArr.length) {
            long j2 = j;
            int i2 = 0;
            while (i2 < iArr[i]) {
                int i3 = 1;
                long j3 = j2 << 1;
                if (i % 2 != 0) {
                    i3 = 0;
                }
                i2++;
                j2 = j3 | ((long) i3);
            }
            i++;
            j = j2;
        }
        int i4 = (int) j;
        if (C1304a.m2219a(i4) == -1) {
            return -1;
        }
        return i4;
    }
}
