package com.google.p028a.p038d.p039a;

import com.google.p028a.C1303h;
import com.google.p028a.p035c.C1194b;

/* JADX INFO: renamed from: com.google.a.d.a.a */
/* JADX INFO: compiled from: BitMatrixParser.java */
/* JADX INFO: loaded from: classes.dex */
final class C1212a {

    /* JADX INFO: renamed from: a */
    final C1194b f2180a;

    /* JADX INFO: renamed from: b */
    final C1194b f2181b;

    /* JADX INFO: renamed from: c */
    final C1216e f2182c;

    C1212a(C1194b c1194b) throws C1303h {
        int i = c1194b.f2078b;
        if (i < 8 || i > 144 || (i & 1) != 0) {
            throw C1303h.m2217a();
        }
        this.f2182c = C1216e.m1896a(c1194b.f2078b, c1194b.f2077a);
        this.f2180a = m1873b(c1194b);
        this.f2181b = new C1194b(this.f2180a.f2077a, this.f2180a.f2078b);
    }

    /* JADX INFO: renamed from: a */
    private C1216e m1870a() {
        return this.f2182c;
    }

    /* JADX INFO: renamed from: b */
    private byte[] m1874b() throws C1303h {
        byte[] bArr = new byte[this.f2182c.f2207g];
        int i = this.f2180a.f2078b;
        int i2 = this.f2180a.f2077a;
        int i3 = 4;
        int i4 = 0;
        boolean z = false;
        int i5 = 0;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        while (true) {
            if (i3 == i && i4 == 0 && !z) {
                int i6 = i5 + 1;
                int i7 = i - 1;
                int i8 = (m1877a(i7, 0, i, i2) ? 1 : 0) << 1;
                if (m1877a(i7, 1, i, i2)) {
                    i8 |= 1;
                }
                int i9 = i8 << 1;
                if (m1877a(i7, 2, i, i2)) {
                    i9 |= 1;
                }
                int i10 = i9 << 1;
                if (m1877a(0, i2 - 2, i, i2)) {
                    i10 |= 1;
                }
                int i11 = i10 << 1;
                int i12 = i2 - 1;
                if (m1877a(0, i12, i, i2)) {
                    i11 |= 1;
                }
                int i13 = i11 << 1;
                if (m1877a(1, i12, i, i2)) {
                    i13 |= 1;
                }
                int i14 = i13 << 1;
                if (m1877a(2, i12, i, i2)) {
                    i14 |= 1;
                }
                int i15 = i14 << 1;
                if (m1877a(3, i12, i, i2)) {
                    i15 |= 1;
                }
                bArr[i5] = (byte) i15;
                i3 -= 2;
                i4 += 2;
                i5 = i6;
                z = true;
            } else {
                int i16 = i - 2;
                if (i3 == i16 && i4 == 0 && (i2 & 3) != 0 && !z2) {
                    int i17 = i5 + 1;
                    int i18 = (m1877a(i + (-3), 0, i, i2) ? 1 : 0) << 1;
                    if (m1877a(i16, 0, i, i2)) {
                        i18 |= 1;
                    }
                    int i19 = i18 << 1;
                    if (m1877a(i - 1, 0, i, i2)) {
                        i19 |= 1;
                    }
                    int i20 = i19 << 1;
                    if (m1877a(0, i2 - 4, i, i2)) {
                        i20 |= 1;
                    }
                    int i21 = i20 << 1;
                    if (m1877a(0, i2 - 3, i, i2)) {
                        i21 |= 1;
                    }
                    int i22 = i21 << 1;
                    if (m1877a(0, i2 - 2, i, i2)) {
                        i22 |= 1;
                    }
                    int i23 = i22 << 1;
                    int i24 = i2 - 1;
                    if (m1877a(0, i24, i, i2)) {
                        i23 |= 1;
                    }
                    int i25 = i23 << 1;
                    if (m1877a(1, i24, i, i2)) {
                        i25 |= 1;
                    }
                    bArr[i5] = (byte) i25;
                    i3 -= 2;
                    i4 += 2;
                    i5 = i17;
                    z2 = true;
                } else if (i3 == i + 4 && i4 == 2 && (i2 & 7) == 0 && !z3) {
                    int i26 = i5 + 1;
                    int i27 = i - 1;
                    int i28 = (m1877a(i27, 0, i, i2) ? 1 : 0) << 1;
                    int i29 = i2 - 1;
                    if (m1877a(i27, i29, i, i2)) {
                        i28 |= 1;
                    }
                    int i30 = i28 << 1;
                    int i31 = i2 - 3;
                    if (m1877a(0, i31, i, i2)) {
                        i30 |= 1;
                    }
                    int i32 = i30 << 1;
                    int i33 = i2 - 2;
                    if (m1877a(0, i33, i, i2)) {
                        i32 |= 1;
                    }
                    int i34 = i32 << 1;
                    if (m1877a(0, i29, i, i2)) {
                        i34 |= 1;
                    }
                    int i35 = i34 << 1;
                    if (m1877a(1, i31, i, i2)) {
                        i35 |= 1;
                    }
                    int i36 = i35 << 1;
                    if (m1877a(1, i33, i, i2)) {
                        i36 |= 1;
                    }
                    int i37 = i36 << 1;
                    if (m1877a(1, i29, i, i2)) {
                        i37 |= 1;
                    }
                    bArr[i5] = (byte) i37;
                    i3 -= 2;
                    i4 += 2;
                    i5 = i26;
                    z3 = true;
                } else if (i3 == i16 && i4 == 0 && (i2 & 7) == 4 && !z4) {
                    int i38 = i5 + 1;
                    int i39 = (m1877a(i + (-3), 0, i, i2) ? 1 : 0) << 1;
                    if (m1877a(i16, 0, i, i2)) {
                        i39 |= 1;
                    }
                    int i40 = i39 << 1;
                    if (m1877a(i - 1, 0, i, i2)) {
                        i40 |= 1;
                    }
                    int i41 = i40 << 1;
                    if (m1877a(0, i2 - 2, i, i2)) {
                        i41 |= 1;
                    }
                    int i42 = i41 << 1;
                    int i43 = i2 - 1;
                    if (m1877a(0, i43, i, i2)) {
                        i42 |= 1;
                    }
                    int i44 = i42 << 1;
                    if (m1877a(1, i43, i, i2)) {
                        i44 |= 1;
                    }
                    int i45 = i44 << 1;
                    if (m1877a(2, i43, i, i2)) {
                        i45 |= 1;
                    }
                    int i46 = i45 << 1;
                    if (m1877a(3, i43, i, i2)) {
                        i46 |= 1;
                    }
                    bArr[i5] = (byte) i46;
                    i3 -= 2;
                    i4 += 2;
                    i5 = i38;
                    z4 = true;
                } else {
                    do {
                        if (i3 < i && i4 >= 0 && !this.f2181b.m1789a(i4, i3)) {
                            bArr[i5] = (byte) m1878b(i3, i4, i, i2);
                            i5++;
                        }
                        i3 -= 2;
                        i4 += 2;
                        if (i3 < 0) {
                            break;
                        }
                    } while (i4 < i2);
                    int i47 = i3 + 1;
                    int i48 = i4 + 3;
                    do {
                        if (i47 >= 0 && i48 < i2 && !this.f2181b.m1789a(i48, i47)) {
                            bArr[i5] = (byte) m1878b(i47, i48, i, i2);
                            i5++;
                        }
                        i47 += 2;
                        i48 -= 2;
                        if (i47 >= i) {
                            break;
                        }
                    } while (i48 >= 0);
                    i3 = i47 + 3;
                    i4 = i48 + 1;
                }
            }
            if (i3 >= i && i4 >= i2) {
                break;
            }
        }
        if (i5 != this.f2182c.f2207g) {
            throw C1303h.m2217a();
        }
        return bArr;
    }

    /* JADX INFO: renamed from: a */
    final boolean m1877a(int i, int i2, int i3, int i4) {
        if (i < 0) {
            i += i3;
            i2 += 4 - ((i3 + 4) & 7);
        }
        if (i2 < 0) {
            i2 += i4;
            i += 4 - ((i4 + 4) & 7);
        }
        this.f2181b.m1790b(i2, i);
        return this.f2180a.m1789a(i2, i);
    }

    /* JADX INFO: renamed from: b */
    final int m1878b(int i, int i2, int i3, int i4) {
        int i5 = i - 2;
        int i6 = i2 - 2;
        int i7 = (m1877a(i5, i6, i3, i4) ? 1 : 0) << 1;
        int i8 = i2 - 1;
        if (m1877a(i5, i8, i3, i4)) {
            i7 |= 1;
        }
        int i9 = i7 << 1;
        int i10 = i - 1;
        if (m1877a(i10, i6, i3, i4)) {
            i9 |= 1;
        }
        int i11 = i9 << 1;
        if (m1877a(i10, i8, i3, i4)) {
            i11 |= 1;
        }
        int i12 = i11 << 1;
        if (m1877a(i10, i2, i3, i4)) {
            i12 |= 1;
        }
        int i13 = i12 << 1;
        if (m1877a(i, i6, i3, i4)) {
            i13 |= 1;
        }
        int i14 = i13 << 1;
        if (m1877a(i, i8, i3, i4)) {
            i14 |= 1;
        }
        int i15 = i14 << 1;
        return m1877a(i, i2, i3, i4) ? i15 | 1 : i15;
    }

    /* JADX INFO: renamed from: a */
    private int m1869a(int i, int i2) {
        int i3 = i - 1;
        int i4 = (m1877a(i3, 0, i, i2) ? 1 : 0) << 1;
        if (m1877a(i3, 1, i, i2)) {
            i4 |= 1;
        }
        int i5 = i4 << 1;
        if (m1877a(i3, 2, i, i2)) {
            i5 |= 1;
        }
        int i6 = i5 << 1;
        if (m1877a(0, i2 - 2, i, i2)) {
            i6 |= 1;
        }
        int i7 = i6 << 1;
        int i8 = i2 - 1;
        if (m1877a(0, i8, i, i2)) {
            i7 |= 1;
        }
        int i9 = i7 << 1;
        if (m1877a(1, i8, i, i2)) {
            i9 |= 1;
        }
        int i10 = i9 << 1;
        if (m1877a(2, i8, i, i2)) {
            i10 |= 1;
        }
        int i11 = i10 << 1;
        return m1877a(3, i8, i, i2) ? i11 | 1 : i11;
    }

    /* JADX INFO: renamed from: b */
    private int m1872b(int i, int i2) {
        int i3 = (m1877a(i + (-3), 0, i, i2) ? 1 : 0) << 1;
        if (m1877a(i - 2, 0, i, i2)) {
            i3 |= 1;
        }
        int i4 = i3 << 1;
        if (m1877a(i - 1, 0, i, i2)) {
            i4 |= 1;
        }
        int i5 = i4 << 1;
        if (m1877a(0, i2 - 4, i, i2)) {
            i5 |= 1;
        }
        int i6 = i5 << 1;
        if (m1877a(0, i2 - 3, i, i2)) {
            i6 |= 1;
        }
        int i7 = i6 << 1;
        if (m1877a(0, i2 - 2, i, i2)) {
            i7 |= 1;
        }
        int i8 = i7 << 1;
        int i9 = i2 - 1;
        if (m1877a(0, i9, i, i2)) {
            i8 |= 1;
        }
        int i10 = i8 << 1;
        return m1877a(1, i9, i, i2) ? i10 | 1 : i10;
    }

    /* JADX INFO: renamed from: c */
    private int m1875c(int i, int i2) {
        int i3 = i - 1;
        int i4 = (m1877a(i3, 0, i, i2) ? 1 : 0) << 1;
        int i5 = i2 - 1;
        if (m1877a(i3, i5, i, i2)) {
            i4 |= 1;
        }
        int i6 = i4 << 1;
        int i7 = i2 - 3;
        if (m1877a(0, i7, i, i2)) {
            i6 |= 1;
        }
        int i8 = i6 << 1;
        int i9 = i2 - 2;
        if (m1877a(0, i9, i, i2)) {
            i8 |= 1;
        }
        int i10 = i8 << 1;
        if (m1877a(0, i5, i, i2)) {
            i10 |= 1;
        }
        int i11 = i10 << 1;
        if (m1877a(1, i7, i, i2)) {
            i11 |= 1;
        }
        int i12 = i11 << 1;
        if (m1877a(1, i9, i, i2)) {
            i12 |= 1;
        }
        int i13 = i12 << 1;
        return m1877a(1, i5, i, i2) ? i13 | 1 : i13;
    }

    /* JADX INFO: renamed from: d */
    private int m1876d(int i, int i2) {
        int i3 = (m1877a(i + (-3), 0, i, i2) ? 1 : 0) << 1;
        if (m1877a(i - 2, 0, i, i2)) {
            i3 |= 1;
        }
        int i4 = i3 << 1;
        if (m1877a(i - 1, 0, i, i2)) {
            i4 |= 1;
        }
        int i5 = i4 << 1;
        if (m1877a(0, i2 - 2, i, i2)) {
            i5 |= 1;
        }
        int i6 = i5 << 1;
        int i7 = i2 - 1;
        if (m1877a(0, i7, i, i2)) {
            i6 |= 1;
        }
        int i8 = i6 << 1;
        if (m1877a(1, i7, i, i2)) {
            i8 |= 1;
        }
        int i9 = i8 << 1;
        if (m1877a(2, i7, i, i2)) {
            i9 |= 1;
        }
        int i10 = i9 << 1;
        return m1877a(3, i7, i, i2) ? i10 | 1 : i10;
    }

    /* JADX INFO: renamed from: b */
    private C1194b m1873b(C1194b c1194b) {
        int i = this.f2182c.f2202b;
        int i2 = this.f2182c.f2203c;
        if (c1194b.f2078b != i) {
            throw new IllegalArgumentException("Dimension of bitMatrix must match the version size");
        }
        int i3 = this.f2182c.f2204d;
        int i4 = this.f2182c.f2205e;
        int i5 = i / i3;
        int i6 = i2 / i4;
        C1194b c1194b2 = new C1194b(i6 * i4, i5 * i3);
        for (int i7 = 0; i7 < i5; i7++) {
            int i8 = i7 * i3;
            for (int i9 = 0; i9 < i6; i9++) {
                int i10 = i9 * i4;
                for (int i11 = 0; i11 < i3; i11++) {
                    int i12 = ((i3 + 2) * i7) + 1 + i11;
                    int i13 = i8 + i11;
                    for (int i14 = 0; i14 < i4; i14++) {
                        if (c1194b.m1789a(((i4 + 2) * i9) + 1 + i14, i12)) {
                            c1194b2.m1790b(i10 + i14, i13);
                        }
                    }
                }
            }
        }
        return c1194b2;
    }

    /* JADX INFO: renamed from: a */
    private static C1216e m1871a(C1194b c1194b) throws C1303h {
        return C1216e.m1896a(c1194b.f2078b, c1194b.f2077a);
    }
}
