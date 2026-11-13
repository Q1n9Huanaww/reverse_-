package com.google.p028a.p056i.p059c;

/* JADX INFO: renamed from: com.google.a.i.c.d */
/* JADX INFO: compiled from: MaskUtil.java */
/* JADX INFO: loaded from: classes.dex */
final class C1352d {

    /* JADX INFO: renamed from: a */
    private static final int f2803a = 3;

    /* JADX INFO: renamed from: b */
    private static final int f2804b = 3;

    /* JADX INFO: renamed from: c */
    private static final int f2805c = 40;

    /* JADX INFO: renamed from: d */
    private static final int f2806d = 10;

    private C1352d() {
    }

    /* JADX INFO: renamed from: a */
    private static int m2568a(C1350b c1350b) {
        return m2569a(c1350b, true) + m2569a(c1350b, false);
    }

    /* JADX INFO: renamed from: a */
    static boolean m2571a(byte[] bArr, int i, int i2) {
        int iMin = Math.min(i2, bArr.length);
        for (int iMax = Math.max(i, 0); iMax < iMin; iMax++) {
            if (bArr[iMax] == 1) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: renamed from: a */
    static boolean m2572a(byte[][] bArr, int i, int i2, int i3) {
        int iMin = Math.min(i3, bArr.length);
        for (int iMax = Math.max(i2, 0); iMax < iMin; iMax++) {
            if (bArr[iMax][i] == 1) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: renamed from: a */
    private static boolean m2570a(int i, int i2, int i3) {
        int i4;
        switch (i) {
            case 0:
                i4 = (i3 + i2) & 1;
                break;
            case 1:
                i4 = i3 & 1;
                break;
            case 2:
                i4 = i2 % 3;
                break;
            case 3:
                i4 = (i3 + i2) % 3;
                break;
            case 4:
                i4 = ((i3 / 2) + (i2 / 3)) & 1;
                break;
            case 5:
                int i5 = i3 * i2;
                i4 = (i5 & 1) + (i5 % 3);
                break;
            case 6:
                int i6 = i3 * i2;
                i4 = ((i6 & 1) + (i6 % 3)) & 1;
                break;
            case 7:
                i4 = (((i3 * i2) % 3) + ((i3 + i2) & 1)) & 1;
                break;
            default:
                throw new IllegalArgumentException("Invalid mask pattern: ".concat(String.valueOf(i)));
        }
        return i4 == 0;
    }

    /* JADX INFO: renamed from: b */
    private static int m2573b(C1350b c1350b) {
        byte[][] bArr = c1350b.f2797a;
        int i = c1350b.f2798b;
        int i2 = c1350b.f2799c;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2 - 1) {
            byte[] bArr2 = bArr[i3];
            int i5 = i4;
            int i6 = 0;
            while (i6 < i - 1) {
                byte b = bArr2[i6];
                int i7 = i6 + 1;
                if (b == bArr2[i7]) {
                    int i8 = i3 + 1;
                    if (b == bArr[i8][i6] && b == bArr[i8][i7]) {
                        i5++;
                    }
                }
                i6 = i7;
            }
            i3++;
            i4 = i5;
        }
        return i4 * 3;
    }

    /* JADX INFO: renamed from: c */
    private static int m2574c(C1350b c1350b) {
        byte[][] bArr = c1350b.f2797a;
        int i = c1350b.f2798b;
        int i2 = c1350b.f2799c;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            int i5 = i4;
            for (int i6 = 0; i6 < i; i6++) {
                byte[] bArr2 = bArr[i3];
                int i7 = i6 + 6;
                if (i7 < i && bArr2[i6] == 1 && bArr2[i6 + 1] == 0 && bArr2[i6 + 2] == 1 && bArr2[i6 + 3] == 1 && bArr2[i6 + 4] == 1 && bArr2[i6 + 5] == 0 && bArr2[i7] == 1 && (m2571a(bArr2, i6 - 4, i6) || m2571a(bArr2, i6 + 7, i6 + 11))) {
                    i5++;
                }
                int i8 = i3 + 6;
                if (i8 < i2 && bArr[i3][i6] == 1 && bArr[i3 + 1][i6] == 0 && bArr[i3 + 2][i6] == 1 && bArr[i3 + 3][i6] == 1 && bArr[i3 + 4][i6] == 1 && bArr[i3 + 5][i6] == 0 && bArr[i8][i6] == 1 && (m2572a(bArr, i6, i3 - 4, i3) || m2572a(bArr, i6, i3 + 7, i3 + 11))) {
                    i5++;
                }
            }
            i3++;
            i4 = i5;
        }
        return i4 * 40;
    }

    /* JADX INFO: renamed from: d */
    private static int m2575d(C1350b c1350b) {
        byte[][] bArr = c1350b.f2797a;
        int i = c1350b.f2798b;
        int i2 = c1350b.f2799c;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            byte[] bArr2 = bArr[i3];
            int i5 = i4;
            for (int i6 = 0; i6 < i; i6++) {
                if (bArr2[i6] == 1) {
                    i5++;
                }
            }
            i3++;
            i4 = i5;
        }
        int i7 = c1350b.f2799c * c1350b.f2798b;
        return ((Math.abs((i4 << 1) - i7) * 10) / i7) * 10;
    }

    /* JADX INFO: renamed from: a */
    static int m2569a(C1350b c1350b, boolean z) {
        int i;
        int i2;
        if (z) {
            i = c1350b.f2799c;
        } else {
            i = c1350b.f2798b;
        }
        if (z) {
            i2 = c1350b.f2798b;
        } else {
            i2 = c1350b.f2799c;
        }
        byte[][] bArr = c1350b.f2797a;
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            int i5 = i3;
            int i6 = 0;
            byte b = -1;
            for (int i7 = 0; i7 < i2; i7++) {
                byte b2 = z ? bArr[i4][i7] : bArr[i7][i4];
                if (b2 == b) {
                    i6++;
                } else {
                    if (i6 >= 5) {
                        i5 += (i6 - 5) + 3;
                    }
                    i6 = 1;
                    b = b2;
                }
            }
            if (i6 >= 5) {
                i5 += (i6 - 5) + 3;
            }
            i3 = i5;
        }
        return i3;
    }
}
