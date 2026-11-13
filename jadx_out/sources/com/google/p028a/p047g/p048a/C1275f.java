package com.google.p028a.p047g.p048a;

/* JADX INFO: renamed from: com.google.a.g.a.f */
/* JADX INFO: compiled from: RSSUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1275f {
    private C1275f() {
    }

    /* JADX INFO: renamed from: a */
    public static int m2152a(int[] iArr, int i, boolean z) {
        int[] iArr2 = iArr;
        int i2 = 0;
        for (int i3 : iArr2) {
            i2 += i3;
        }
        int length = iArr2.length;
        int i4 = i2;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (true) {
            int i8 = length - 1;
            if (i5 >= i8) {
                return i7;
            }
            int i9 = 1 << i5;
            int i10 = i6 | i9;
            int i11 = 1;
            while (i11 < iArr2[i5]) {
                int i12 = i4 - i11;
                int i13 = length - i5;
                int i14 = i13 - 2;
                int iM2151a = m2151a(i12 - 1, i14);
                if (z && i10 == 0) {
                    int i15 = i13 - 1;
                    if (i12 - i15 >= i15) {
                        iM2151a -= m2151a(i12 - i13, i14);
                    }
                }
                if (i13 - 1 > 1) {
                    int iM2151a2 = 0;
                    for (int i16 = i12 - i14; i16 > i; i16--) {
                        iM2151a2 += m2151a((i12 - i16) - 1, i13 - 3);
                    }
                    iM2151a -= iM2151a2 * (i8 - i5);
                } else if (i12 > i) {
                    iM2151a--;
                }
                i7 += iM2151a;
                i11++;
                i10 &= i9 ^ (-1);
                iArr2 = iArr;
            }
            i4 -= i11;
            i5++;
            i6 = i10;
            iArr2 = iArr;
        }
    }

    /* JADX INFO: renamed from: a */
    private static int m2151a(int i, int i2) {
        int i3 = i - i2;
        if (i3 > i2) {
            i3 = i2;
            i2 = i3;
        }
        int i4 = 1;
        int i5 = 1;
        while (i > i2) {
            i4 *= i;
            if (i5 <= i3) {
                i4 /= i5;
                i5++;
            }
            i--;
        }
        while (i5 <= i3) {
            i4 /= i5;
            i5++;
        }
        return i4;
    }
}
