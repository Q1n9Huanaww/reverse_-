package com.google.p028a.p038d.p039a;

import com.google.p028a.C1210d;
import com.google.p028a.C1303h;
import com.google.p028a.p035c.C1194b;
import com.google.p028a.p035c.C1202e;
import com.google.p028a.p035c.p037b.C1195a;
import com.google.p028a.p035c.p037b.C1197c;
import com.google.p028a.p035c.p037b.C1199e;

/* JADX INFO: renamed from: com.google.a.d.a.d */
/* JADX INFO: compiled from: Decoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1215d {

    /* JADX INFO: renamed from: a */
    private final C1197c f2199a = new C1197c(C1195a.f2086f);

    /* JADX INFO: renamed from: a */
    private C1202e m1892a(boolean[][] zArr) throws C1210d, C1303h {
        return m1894a(C1194b.m1775a(zArr));
    }

    /* JADX INFO: renamed from: a */
    public final C1202e m1894a(C1194b c1194b) throws C1210d, C1303h {
        boolean z;
        C1212a c1212a = new C1212a(c1194b);
        C1216e c1216e = c1212a.f2182c;
        byte[] bArr = new byte[c1212a.f2182c.f2207g];
        int i = c1212a.f2180a.f2078b;
        int i2 = c1212a.f2180a.f2077a;
        int i3 = 0;
        int i4 = 4;
        int i5 = 0;
        boolean z2 = false;
        int i6 = 0;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        while (true) {
            if (i4 == i && i5 == 0 && !z2) {
                int i7 = i6 + 1;
                int i8 = i - 1;
                int i9 = (c1212a.m1877a(i8, i3, i, i2) ? 1 : 0) << 1;
                if (c1212a.m1877a(i8, 1, i, i2)) {
                    i9 |= 1;
                }
                int i10 = i9 << 1;
                if (c1212a.m1877a(i8, 2, i, i2)) {
                    i10 |= 1;
                }
                int i11 = i10 << 1;
                if (c1212a.m1877a(i3, i2 - 2, i, i2)) {
                    i11 |= 1;
                }
                int i12 = i11 << 1;
                int i13 = i2 - 1;
                if (c1212a.m1877a(i3, i13, i, i2)) {
                    i12 |= 1;
                }
                int i14 = i12 << 1;
                if (c1212a.m1877a(1, i13, i, i2)) {
                    i14 |= 1;
                }
                int i15 = i14 << 1;
                if (c1212a.m1877a(2, i13, i, i2)) {
                    i15 |= 1;
                }
                int i16 = i15 << 1;
                if (c1212a.m1877a(3, i13, i, i2)) {
                    i16 |= 1;
                }
                bArr[i6] = (byte) i16;
                i4 -= 2;
                i5 += 2;
                i6 = i7;
                z = true;
                z2 = true;
            } else {
                int i17 = i - 2;
                if (i4 == i17 && i5 == 0 && (i2 & 3) != 0 && !z3) {
                    int i18 = i6 + 1;
                    int i19 = (c1212a.m1877a(i + (-3), 0, i, i2) ? 1 : 0) << 1;
                    if (c1212a.m1877a(i17, 0, i, i2)) {
                        i19 |= 1;
                    }
                    int i20 = i19 << 1;
                    if (c1212a.m1877a(i - 1, 0, i, i2)) {
                        i20 |= 1;
                    }
                    int i21 = i20 << 1;
                    if (c1212a.m1877a(0, i2 - 4, i, i2)) {
                        i21 |= 1;
                    }
                    int i22 = i21 << 1;
                    if (c1212a.m1877a(0, i2 - 3, i, i2)) {
                        i22 |= 1;
                    }
                    int i23 = i22 << 1;
                    if (c1212a.m1877a(0, i2 - 2, i, i2)) {
                        i23 |= 1;
                    }
                    int i24 = i23 << 1;
                    int i25 = i2 - 1;
                    if (c1212a.m1877a(0, i25, i, i2)) {
                        i24 |= 1;
                    }
                    int i26 = i24 << 1;
                    if (c1212a.m1877a(1, i25, i, i2)) {
                        i26 |= 1;
                    }
                    bArr[i6] = (byte) i26;
                    i4 -= 2;
                    i5 += 2;
                    i6 = i18;
                    z3 = true;
                } else if (i4 == i + 4 && i5 == 2 && (i2 & 7) == 0 && !z4) {
                    int i27 = i6 + 1;
                    int i28 = i - 1;
                    int i29 = (c1212a.m1877a(i28, 0, i, i2) ? 1 : 0) << 1;
                    int i30 = i2 - 1;
                    if (c1212a.m1877a(i28, i30, i, i2)) {
                        i29 |= 1;
                    }
                    int i31 = i29 << 1;
                    int i32 = i2 - 3;
                    if (c1212a.m1877a(0, i32, i, i2)) {
                        i31 |= 1;
                    }
                    int i33 = i31 << 1;
                    int i34 = i2 - 2;
                    if (c1212a.m1877a(0, i34, i, i2)) {
                        i33 |= 1;
                    }
                    int i35 = i33 << 1;
                    if (c1212a.m1877a(0, i30, i, i2)) {
                        i35 |= 1;
                    }
                    int i36 = i35 << 1;
                    if (c1212a.m1877a(1, i32, i, i2)) {
                        i36 |= 1;
                    }
                    int i37 = i36 << 1;
                    if (c1212a.m1877a(1, i34, i, i2)) {
                        i37 |= 1;
                    }
                    int i38 = i37 << 1;
                    if (c1212a.m1877a(1, i30, i, i2)) {
                        i38 |= 1;
                    }
                    bArr[i6] = (byte) i38;
                    i4 -= 2;
                    i5 += 2;
                    i6 = i27;
                    z4 = true;
                } else {
                    z2 = z2;
                    if (i4 == i17 && i5 == 0) {
                        if ((i2 & 7) == 4 && !z5) {
                            int i39 = i6 + 1;
                            z = true;
                            int i40 = (c1212a.m1877a(i + (-3), 0, i, i2) ? 1 : 0) << 1;
                            if (c1212a.m1877a(i17, 0, i, i2)) {
                                i40 |= 1;
                            }
                            int i41 = i40 << 1;
                            if (c1212a.m1877a(i - 1, 0, i, i2)) {
                                i41 |= 1;
                            }
                            int i42 = i41 << 1;
                            if (c1212a.m1877a(0, i2 - 2, i, i2)) {
                                i42 |= 1;
                            }
                            int i43 = i42 << 1;
                            int i44 = i2 - 1;
                            if (c1212a.m1877a(0, i44, i, i2)) {
                                i43 |= 1;
                            }
                            int i45 = i43 << 1;
                            if (c1212a.m1877a(1, i44, i, i2)) {
                                i45 |= 1;
                            }
                            int i46 = i45 << 1;
                            if (c1212a.m1877a(2, i44, i, i2)) {
                                i46 |= 1;
                            }
                            int i47 = i46 << 1;
                            if (c1212a.m1877a(3, i44, i, i2)) {
                                i47 |= 1;
                            }
                            bArr[i6] = (byte) i47;
                            i4 -= 2;
                            i5 += 2;
                            i6 = i39;
                            z5 = true;
                        }
                    }
                    z = true;
                    do {
                        if (i4 < i && i5 >= 0 && !c1212a.f2181b.m1789a(i5, i4)) {
                            bArr[i6] = (byte) c1212a.m1878b(i4, i5, i, i2);
                            i6++;
                        }
                        i4 -= 2;
                        i5 += 2;
                        if (i4 < 0) {
                            break;
                        }
                    } while (i5 < i2);
                    int i48 = i4 + 1;
                    int i49 = i5 + 3;
                    do {
                        if (i48 >= 0 && i49 < i2 && !c1212a.f2181b.m1789a(i49, i48)) {
                            bArr[i6] = (byte) c1212a.m1878b(i48, i49, i, i2);
                            i6++;
                        }
                        i48 += 2;
                        i49 -= 2;
                        if (i48 >= i) {
                            break;
                        }
                    } while (i49 >= 0);
                    i4 = i48 + 3;
                    i5 = i49 + 1;
                }
                z = true;
            }
            if (i4 >= i && i5 >= i2) {
                break;
            }
            z2 = z2;
            i3 = 0;
        }
        if (i6 != c1212a.f2182c.f2207g) {
            throw C1303h.m2217a();
        }
        C1216e.b bVar = c1216e.f2206f;
        C1216e.a[] aVarArr = bVar.f2211b;
        int i50 = 0;
        for (C1216e.a aVar : aVarArr) {
            i50 += aVar.f2208a;
        }
        C1213b[] c1213bArr = new C1213b[i50];
        int length = aVarArr.length;
        int i51 = 0;
        int i52 = 0;
        while (i51 < length) {
            C1216e.a aVar2 = aVarArr[i51];
            int i53 = i52;
            int i54 = 0;
            while (i54 < aVar2.f2208a) {
                int i55 = aVar2.f2209b;
                c1213bArr[i53] = new C1213b(i55, new byte[bVar.f2210a + i55]);
                i54++;
                i53++;
            }
            i51++;
            i52 = i53;
        }
        int length2 = c1213bArr[0].f2184b.length - bVar.f2210a;
        int i56 = length2 - 1;
        int i57 = 0;
        int i58 = 0;
        while (i57 < i56) {
            int i59 = i58;
            int i60 = 0;
            while (i60 < i52) {
                c1213bArr[i60].f2184b[i57] = bArr[i59];
                i60++;
                i59++;
            }
            i57++;
            i58 = i59;
        }
        if (c1216e.f2201a != 24) {
            z = false;
        }
        int i61 = z ? 8 : i52;
        int i62 = 0;
        while (i62 < i61) {
            c1213bArr[i62].f2184b[i56] = bArr[i58];
            i62++;
            i58++;
        }
        int length3 = c1213bArr[0].f2184b.length;
        while (length2 < length3) {
            int i63 = 0;
            while (i63 < i52) {
                int i64 = z ? (i63 + 8) % i52 : i63;
                c1213bArr[i64].f2184b[(!z || i64 <= 7) ? length2 : length2 - 1] = bArr[i58];
                i63++;
                i58++;
            }
            length2++;
        }
        if (i58 == bArr.length) {
            int i65 = 0;
            for (C1213b c1213b : c1213bArr) {
                i65 += c1213b.f2183a;
            }
            byte[] bArr2 = new byte[i65];
            int length4 = c1213bArr.length;
            for (int i66 = 0; i66 < length4; i66++) {
                C1213b c1213b2 = c1213bArr[i66];
                byte[] bArr3 = c1213b2.f2184b;
                int i67 = c1213b2.f2183a;
                m1893a(bArr3, i67);
                for (int i68 = 0; i68 < i67; i68++) {
                    bArr2[(i68 * length4) + i66] = bArr3[i68];
                }
            }
            return C1214c.m1884a(bArr2);
        }
        throw new IllegalArgumentException();
    }

    /* JADX INFO: renamed from: a */
    private void m1893a(byte[] bArr, int i) throws C1210d {
        int length = bArr.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        try {
            this.f2199a.m1819a(iArr, bArr.length - i);
            for (int i3 = 0; i3 < i; i3++) {
                bArr[i3] = (byte) iArr[i3];
            }
        } catch (C1199e unused) {
            throw C1210d.m1865a();
        }
    }
}
