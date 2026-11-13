package com.google.p028a.p056i.p059c;

import com.google.p028a.C1368w;
import com.google.p028a.p035c.C1190a;
import com.google.p028a.p056i.p057a.C1341j;
import com.google.p028a.p056i.p057a.EnumC1337f;
import java.util.Arrays;
import org.apache.commons.p092io.IOUtils;

/* JADX INFO: renamed from: com.google.a.i.c.e */
/* JADX INFO: compiled from: MatrixUtil.java */
/* JADX INFO: loaded from: classes.dex */
final class C1353e {

    /* JADX INFO: renamed from: a */
    private static final int[][] f2807a = {new int[]{1, 1, 1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1, 1, 1}};

    /* JADX INFO: renamed from: b */
    private static final int[][] f2808b = {new int[]{1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 0, 1, 0, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1}};

    /* JADX INFO: renamed from: c */
    private static final int[][] f2809c = {new int[]{-1, -1, -1, -1, -1, -1, -1}, new int[]{6, 18, -1, -1, -1, -1, -1}, new int[]{6, 22, -1, -1, -1, -1, -1}, new int[]{6, 26, -1, -1, -1, -1, -1}, new int[]{6, 30, -1, -1, -1, -1, -1}, new int[]{6, 34, -1, -1, -1, -1, -1}, new int[]{6, 22, 38, -1, -1, -1, -1}, new int[]{6, 24, 42, -1, -1, -1, -1}, new int[]{6, 26, 46, -1, -1, -1, -1}, new int[]{6, 28, 50, -1, -1, -1, -1}, new int[]{6, 30, 54, -1, -1, -1, -1}, new int[]{6, 32, 58, -1, -1, -1, -1}, new int[]{6, 34, 62, -1, -1, -1, -1}, new int[]{6, 26, 46, 66, -1, -1, -1}, new int[]{6, 26, 48, 70, -1, -1, -1}, new int[]{6, 26, 50, 74, -1, -1, -1}, new int[]{6, 30, 54, 78, -1, -1, -1}, new int[]{6, 30, 56, 82, -1, -1, -1}, new int[]{6, 30, 58, 86, -1, -1, -1}, new int[]{6, 34, 62, 90, -1, -1, -1}, new int[]{6, 28, 50, 72, 94, -1, -1}, new int[]{6, 26, 50, 74, 98, -1, -1}, new int[]{6, 30, 54, 78, 102, -1, -1}, new int[]{6, 28, 54, 80, 106, -1, -1}, new int[]{6, 32, 58, 84, 110, -1, -1}, new int[]{6, 30, 58, 86, 114, -1, -1}, new int[]{6, 34, 62, 90, 118, -1, -1}, new int[]{6, 26, 50, 74, 98, 122, -1}, new int[]{6, 30, 54, 78, 102, 126, -1}, new int[]{6, 26, 52, 78, 104, 130, -1}, new int[]{6, 30, 56, 82, 108, 134, -1}, new int[]{6, 34, 60, 86, 112, 138, -1}, new int[]{6, 30, 58, 86, 114, 142, -1}, new int[]{6, 34, 62, 90, 118, 146, -1}, new int[]{6, 30, 54, 78, 102, 126, 150}, new int[]{6, 24, 50, 76, 102, 128, 154}, new int[]{6, 28, 54, 80, 106, 132, 158}, new int[]{6, 32, 58, 84, 110, 136, 162}, new int[]{6, 26, 54, 82, 110, 138, 166}, new int[]{6, 30, 58, 86, 114, 142, 170}};

    /* JADX INFO: renamed from: d */
    private static final int[][] f2810d = {new int[]{8, 0}, new int[]{8, 1}, new int[]{8, 2}, new int[]{8, 3}, new int[]{8, 4}, new int[]{8, 5}, new int[]{8, 7}, new int[]{8, 8}, new int[]{7, 8}, new int[]{5, 8}, new int[]{4, 8}, new int[]{3, 8}, new int[]{2, 8}, new int[]{1, 8}, new int[]{0, 8}};

    /* JADX INFO: renamed from: e */
    private static final int f2811e = 7973;

    /* JADX INFO: renamed from: f */
    private static final int f2812f = 1335;

    /* JADX INFO: renamed from: g */
    private static final int f2813g = 21522;

    /* JADX INFO: renamed from: b */
    private static boolean m2589b(int i) {
        return i == -1;
    }

    private C1353e() {
    }

    /* JADX INFO: renamed from: a */
    private static void m2582a(EnumC1337f enumC1337f, int i, C1350b c1350b) throws C1368w {
        C1190a c1190a = new C1190a();
        if (!(i >= 0 && i < 8)) {
            throw new C1368w("Invalid mask pattern");
        }
        int i2 = (enumC1337f.f2738e << 3) | i;
        c1190a.m1759b(i2, 5);
        c1190a.m1759b(m2577a(i2, f2812f), 10);
        C1190a c1190a2 = new C1190a();
        c1190a2.m1759b(f2813g, 15);
        if (c1190a.f2065b != c1190a2.f2065b) {
            throw new IllegalArgumentException("Sizes don't match");
        }
        for (int i3 = 0; i3 < c1190a.f2064a.length; i3++) {
            int[] iArr = c1190a.f2064a;
            iArr[i3] = iArr[i3] ^ c1190a2.f2064a[i3];
        }
        if (c1190a.f2065b != 15) {
            throw new C1368w("should not happen but we got: " + c1190a.f2065b);
        }
        for (int i4 = 0; i4 < c1190a.f2065b; i4++) {
            boolean zM1755a = c1190a.m1755a((c1190a.f2065b - 1) - i4);
            int[] iArr2 = f2810d[i4];
            c1350b.m2543a(iArr2[0], iArr2[1], zM1755a);
            if (i4 < 8) {
                c1350b.m2543a((c1350b.f2798b - i4) - 1, 8, zM1755a);
            } else {
                c1350b.m2543a(8, (c1350b.f2799c - 7) + (i4 - 8), zM1755a);
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private static int m2576a(int i) {
        return 32 - Integer.numberOfLeadingZeros(i);
    }

    /* JADX INFO: renamed from: a */
    private static int m2577a(int i, int i2) {
        if (i2 == 0) {
            throw new IllegalArgumentException("0 polynomial");
        }
        int iNumberOfLeadingZeros = 32 - Integer.numberOfLeadingZeros(i2);
        int iNumberOfLeadingZeros2 = i << (iNumberOfLeadingZeros - 1);
        while (32 - Integer.numberOfLeadingZeros(iNumberOfLeadingZeros2) >= iNumberOfLeadingZeros) {
            iNumberOfLeadingZeros2 ^= i2 << ((32 - Integer.numberOfLeadingZeros(iNumberOfLeadingZeros2)) - iNumberOfLeadingZeros);
        }
        return iNumberOfLeadingZeros2;
    }

    /* JADX INFO: renamed from: a */
    private static void m2581a(EnumC1337f enumC1337f, int i, C1190a c1190a) throws C1368w {
        if (!(i >= 0 && i < 8)) {
            throw new C1368w("Invalid mask pattern");
        }
        int i2 = (enumC1337f.f2738e << 3) | i;
        c1190a.m1759b(i2, 5);
        c1190a.m1759b(m2577a(i2, f2812f), 10);
        C1190a c1190a2 = new C1190a();
        c1190a2.m1759b(f2813g, 15);
        if (c1190a.f2065b != c1190a2.f2065b) {
            throw new IllegalArgumentException("Sizes don't match");
        }
        for (int i3 = 0; i3 < c1190a.f2064a.length; i3++) {
            int[] iArr = c1190a.f2064a;
            iArr[i3] = iArr[i3] ^ c1190a2.f2064a[i3];
        }
        if (c1190a.f2065b != 15) {
            throw new C1368w("should not happen but we got: " + c1190a.f2065b);
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m2578a(int i, int i2, C1350b c1350b) throws C1368w {
        for (int i3 = 0; i3 < 8; i3++) {
            int i4 = i + i3;
            if (!m2589b(c1350b.m2541a(i4, i2))) {
                throw new C1368w();
            }
            c1350b.m2542a(i4, i2, 0);
        }
    }

    /* JADX INFO: renamed from: b */
    private static void m2586b(int i, int i2, C1350b c1350b) throws C1368w {
        for (int i3 = 0; i3 < 7; i3++) {
            int i4 = i2 + i3;
            if (!m2589b(c1350b.m2541a(i, i4))) {
                throw new C1368w();
            }
            c1350b.m2542a(i, i4, 0);
        }
    }

    /* JADX INFO: renamed from: c */
    private static void m2590c(int i, int i2, C1350b c1350b) {
        for (int i3 = 0; i3 < 5; i3++) {
            int[] iArr = f2808b[i3];
            for (int i4 = 0; i4 < 5; i4++) {
                c1350b.m2542a(i + i4, i2 + i3, iArr[i4]);
            }
        }
    }

    /* JADX INFO: renamed from: d */
    private static void m2593d(int i, int i2, C1350b c1350b) {
        for (int i3 = 0; i3 < 7; i3++) {
            int[] iArr = f2807a[i3];
            for (int i4 = 0; i4 < 7; i4++) {
                c1350b.m2542a(i + i4, i2 + i3, iArr[i4]);
            }
        }
    }

    /* JADX INFO: renamed from: d */
    private static void m2594d(C1350b c1350b) throws C1368w {
        int length = f2807a[0].length;
        m2593d(0, 0, c1350b);
        m2593d(c1350b.f2798b - length, 0, c1350b);
        m2593d(0, c1350b.f2798b - length, c1350b);
        m2578a(0, 7, c1350b);
        m2578a(c1350b.f2798b - 8, 7, c1350b);
        m2578a(0, c1350b.f2798b - 8, c1350b);
        m2586b(7, 0, c1350b);
        m2586b((c1350b.f2799c - 7) - 1, 0, c1350b);
        m2586b(7, c1350b.f2799c - 7, c1350b);
    }

    /* JADX INFO: renamed from: a */
    private static void m2585a(C1350b c1350b) {
        for (byte[] bArr : c1350b.f2797a) {
            Arrays.fill(bArr, (byte) -1);
        }
    }

    /* JADX INFO: renamed from: a */
    static void m2580a(C1190a c1190a, EnumC1337f enumC1337f, C1341j c1341j, int i, C1350b c1350b) throws C1368w {
        int i2 = 0;
        for (byte[] bArr : c1350b.f2797a) {
            Arrays.fill(bArr, (byte) -1);
        }
        int length = f2807a[0].length;
        m2593d(0, 0, c1350b);
        m2593d(c1350b.f2798b - length, 0, c1350b);
        m2593d(0, c1350b.f2798b - length, c1350b);
        m2578a(0, 7, c1350b);
        m2578a(c1350b.f2798b - 8, 7, c1350b);
        m2578a(0, c1350b.f2798b - 8, c1350b);
        m2586b(7, 0, c1350b);
        m2586b((c1350b.f2799c - 7) - 1, 0, c1350b);
        m2586b(7, c1350b.f2799c - 7, c1350b);
        if (c1350b.m2541a(8, c1350b.f2799c - 8) == 0) {
            throw new C1368w();
        }
        c1350b.m2542a(8, c1350b.f2799c - 8, 1);
        int i3 = 5;
        if (c1341j.f2759a >= 2) {
            int[] iArr = f2809c[c1341j.f2759a - 1];
            int length2 = iArr.length;
            int i4 = 0;
            while (i4 < length2) {
                int i5 = iArr[i4];
                if (i5 >= 0) {
                    int length3 = iArr.length;
                    int i6 = 0;
                    while (i6 < length3) {
                        int i7 = iArr[i6];
                        if (i7 >= 0 && m2589b(c1350b.m2541a(i7, i5))) {
                            int i8 = i7 - 2;
                            int i9 = i5 - 2;
                            int i10 = 0;
                            while (i10 < i3) {
                                int[] iArr2 = f2808b[i10];
                                while (i2 < i3) {
                                    c1350b.m2542a(i8 + i2, i9 + i10, iArr2[i2]);
                                    i2++;
                                    i3 = 5;
                                }
                                i10++;
                                i2 = 0;
                                i3 = 5;
                            }
                        }
                        i6++;
                        i2 = 0;
                        i3 = 5;
                    }
                }
                i4++;
                i2 = 0;
                i3 = 5;
            }
        }
        int i11 = 8;
        while (i11 < c1350b.f2798b - 8) {
            int i12 = i11 + 1;
            int i13 = i12 % 2;
            if (m2589b(c1350b.m2541a(i11, 6))) {
                c1350b.m2542a(i11, 6, i13);
            }
            if (m2589b(c1350b.m2541a(6, i11))) {
                c1350b.m2542a(6, i11, i13);
            }
            i11 = i12;
        }
        C1190a c1190a2 = new C1190a();
        if (!(i >= 0 && i < 8)) {
            throw new C1368w("Invalid mask pattern");
        }
        int i14 = (enumC1337f.f2738e << 3) | i;
        c1190a2.m1759b(i14, 5);
        c1190a2.m1759b(m2577a(i14, f2812f), 10);
        C1190a c1190a3 = new C1190a();
        c1190a3.m1759b(f2813g, 15);
        if (c1190a2.f2065b != c1190a3.f2065b) {
            throw new IllegalArgumentException("Sizes don't match");
        }
        for (int i15 = 0; i15 < c1190a2.f2064a.length; i15++) {
            int[] iArr3 = c1190a2.f2064a;
            iArr3[i15] = iArr3[i15] ^ c1190a3.f2064a[i15];
        }
        if (c1190a2.f2065b != 15) {
            throw new C1368w("should not happen but we got: " + c1190a2.f2065b);
        }
        for (int i16 = 0; i16 < c1190a2.f2065b; i16++) {
            boolean zM1755a = c1190a2.m1755a((c1190a2.f2065b - 1) - i16);
            int[] iArr4 = f2810d[i16];
            c1350b.m2543a(iArr4[0], iArr4[1], zM1755a);
            if (i16 < 8) {
                c1350b.m2543a((c1350b.f2798b - i16) - 1, 8, zM1755a);
            } else {
                c1350b.m2543a(8, (c1350b.f2799c - 7) + (i16 - 8), zM1755a);
            }
        }
        m2587b(c1341j, c1350b);
        m2579a(c1190a, i, c1350b);
    }

    /* JADX INFO: renamed from: a */
    private static void m2584a(C1341j c1341j, C1350b c1350b) throws C1368w {
        int length = f2807a[0].length;
        m2593d(0, 0, c1350b);
        m2593d(c1350b.f2798b - length, 0, c1350b);
        m2593d(0, c1350b.f2798b - length, c1350b);
        m2578a(0, 7, c1350b);
        m2578a(c1350b.f2798b - 8, 7, c1350b);
        m2578a(0, c1350b.f2798b - 8, c1350b);
        m2586b(7, 0, c1350b);
        m2586b((c1350b.f2799c - 7) - 1, 0, c1350b);
        m2586b(7, c1350b.f2799c - 7, c1350b);
        if (c1350b.m2541a(8, c1350b.f2799c - 8) == 0) {
            throw new C1368w();
        }
        c1350b.m2542a(8, c1350b.f2799c - 8, 1);
        if (c1341j.f2759a >= 2) {
            int[] iArr = f2809c[c1341j.f2759a - 1];
            for (int i : iArr) {
                if (i >= 0) {
                    for (int i2 : iArr) {
                        if (i2 >= 0 && m2589b(c1350b.m2541a(i2, i))) {
                            int i3 = i2 - 2;
                            int i4 = i - 2;
                            int i5 = 0;
                            while (true) {
                                if (i5 < 5) {
                                    int[] iArr2 = f2808b[i5];
                                    int i6 = 0;
                                    for (int i7 = 5; i6 < i7; i7 = 5) {
                                        c1350b.m2542a(i3 + i6, i4 + i5, iArr2[i6]);
                                        i6++;
                                    }
                                    i5++;
                                }
                            }
                        }
                    }
                }
            }
        }
        int i8 = 8;
        while (i8 < c1350b.f2798b - 8) {
            int i9 = i8 + 1;
            int i10 = i9 % 2;
            if (m2589b(c1350b.m2541a(i8, 6))) {
                c1350b.m2542a(i8, 6, i10);
            }
            if (m2589b(c1350b.m2541a(6, i8))) {
                c1350b.m2542a(6, i8, i10);
            }
            i8 = i9;
        }
    }

    /* JADX INFO: renamed from: b */
    private static void m2587b(C1341j c1341j, C1350b c1350b) throws C1368w {
        if (c1341j.f2759a < 7) {
            return;
        }
        C1190a c1190a = new C1190a();
        c1190a.m1759b(c1341j.f2759a, 6);
        c1190a.m1759b(m2577a(c1341j.f2759a, f2811e), 12);
        if (c1190a.f2065b != 18) {
            throw new C1368w("should not happen but we got: " + c1190a.f2065b);
        }
        int i = 0;
        int i2 = 17;
        while (i < 6) {
            int i3 = i2;
            for (int i4 = 0; i4 < 3; i4++) {
                boolean zM1755a = c1190a.m1755a(i3);
                i3--;
                c1350b.m2543a(i, (c1350b.f2799c - 11) + i4, zM1755a);
                c1350b.m2543a((c1350b.f2799c - 11) + i4, i, zM1755a);
            }
            i++;
            i2 = i3;
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m2579a(C1190a c1190a, int i, C1350b c1350b) throws C1368w {
        boolean zM1755a;
        int i2;
        int i3 = c1350b.f2798b - 1;
        int i4 = c1350b.f2799c - 1;
        int i5 = 0;
        int i6 = -1;
        while (i3 > 0) {
            if (i3 == 6) {
                i3--;
            }
            while (i4 >= 0 && i4 < c1350b.f2799c) {
                int i7 = i5;
                for (int i8 = 0; i8 < 2; i8++) {
                    int i9 = i3 - i8;
                    if (m2589b(c1350b.m2541a(i9, i4))) {
                        if (i7 < c1190a.f2065b) {
                            zM1755a = c1190a.m1755a(i7);
                            i7++;
                        } else {
                            zM1755a = false;
                        }
                        if (i != -1) {
                            switch (i) {
                                case 0:
                                    i2 = (i4 + i9) & 1;
                                    break;
                                case 1:
                                    i2 = i4 & 1;
                                    break;
                                case 2:
                                    i2 = i9 % 3;
                                    break;
                                case 3:
                                    i2 = (i4 + i9) % 3;
                                    break;
                                case 4:
                                    i2 = ((i4 / 2) + (i9 / 3)) & 1;
                                    break;
                                case 5:
                                    int i10 = i4 * i9;
                                    i2 = (i10 % 3) + (i10 & 1);
                                    break;
                                case 6:
                                    int i11 = i4 * i9;
                                    i2 = ((i11 & 1) + (i11 % 3)) & 1;
                                    break;
                                case 7:
                                    i2 = (((i4 * i9) % 3) + ((i4 + i9) & 1)) & 1;
                                    break;
                                default:
                                    throw new IllegalArgumentException("Invalid mask pattern: ".concat(String.valueOf(i)));
                            }
                            if (i2 == 0) {
                                zM1755a = !zM1755a;
                            }
                        }
                        c1350b.m2543a(i9, i4, zM1755a);
                    }
                }
                i4 += i6;
                i5 = i7;
            }
            i6 = -i6;
            i4 += i6;
            i3 -= 2;
        }
        if (i5 != c1190a.f2065b) {
            throw new C1368w("Not all bits consumed: " + i5 + IOUtils.DIR_SEPARATOR_UNIX + c1190a.f2065b);
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m2583a(C1341j c1341j, C1190a c1190a) throws C1368w {
        c1190a.m1759b(c1341j.f2759a, 6);
        c1190a.m1759b(m2577a(c1341j.f2759a, f2811e), 12);
        if (c1190a.f2065b != 18) {
            throw new C1368w("should not happen but we got: " + c1190a.f2065b);
        }
    }

    /* JADX INFO: renamed from: b */
    private static void m2588b(C1350b c1350b) {
        int i = 8;
        while (i < c1350b.f2798b - 8) {
            int i2 = i + 1;
            int i3 = i2 % 2;
            if (m2589b(c1350b.m2541a(i, 6))) {
                c1350b.m2542a(i, 6, i3);
            }
            if (m2589b(c1350b.m2541a(6, i))) {
                c1350b.m2542a(6, i, i3);
            }
            i = i2;
        }
    }

    /* JADX INFO: renamed from: c */
    private static void m2592c(C1350b c1350b) throws C1368w {
        if (c1350b.m2541a(8, c1350b.f2799c - 8) == 0) {
            throw new C1368w();
        }
        c1350b.m2542a(8, c1350b.f2799c - 8, 1);
    }

    /* JADX INFO: renamed from: c */
    private static void m2591c(C1341j c1341j, C1350b c1350b) {
        if (c1341j.f2759a < 2) {
            return;
        }
        int[] iArr = f2809c[c1341j.f2759a - 1];
        for (int i : iArr) {
            if (i >= 0) {
                for (int i2 : iArr) {
                    if (i2 >= 0 && m2589b(c1350b.m2541a(i2, i))) {
                        int i3 = i2 - 2;
                        int i4 = i - 2;
                        for (int i5 = 0; i5 < 5; i5++) {
                            int[] iArr2 = f2808b[i5];
                            for (int i6 = 0; i6 < 5; i6++) {
                                c1350b.m2542a(i3 + i6, i4 + i5, iArr2[i6]);
                            }
                        }
                    }
                }
            }
        }
    }
}
