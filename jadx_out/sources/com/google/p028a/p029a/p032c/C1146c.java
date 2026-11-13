package com.google.p028a.p029a.p032c;

import com.google.p028a.p029a.p032c.C1147d.AnonymousClass1;
import com.google.p028a.p035c.C1190a;
import com.google.p028a.p035c.C1194b;
import com.google.p028a.p035c.p037b.C1195a;
import com.google.p028a.p035c.p037b.C1198d;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

/* JADX INFO: renamed from: com.google.a.a.c.c */
/* JADX INFO: compiled from: Encoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1146c {

    /* JADX INFO: renamed from: a */
    public static final int f1912a = 33;

    /* JADX INFO: renamed from: b */
    public static final int f1913b = 0;

    /* JADX INFO: renamed from: c */
    private static final int f1914c = 32;

    /* JADX INFO: renamed from: d */
    private static final int f1915d = 4;

    /* JADX INFO: renamed from: e */
    private static final int[] f1916e = {4, 6, 6, 8, 8, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};

    /* JADX INFO: renamed from: a */
    private static int m1529a(int i, boolean z) {
        return ((z ? 88 : 112) + (i << 4)) * i;
    }

    private C1146c() {
    }

    /* JADX INFO: renamed from: a */
    private static C1144a m1530a(byte[] bArr) {
        return m1531a(bArr, 33, 0);
    }

    /* JADX WARN: Code duplicated, block: B:17:0x003d  */
    /* JADX INFO: renamed from: a */
    public static C1144a m1531a(byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        C1190a c1190aM1532a;
        int i7;
        boolean z;
        int i8;
        int iM1529a;
        C1190a c1190aM1533a;
        int i9;
        C1147d c1147d = new C1147d(bArr);
        Collection<C1149f> collectionSingletonList = Collections.singletonList(C1149f.f1930a);
        int i10 = 0;
        while (true) {
            i3 = 3;
            i4 = 5;
            i5 = 32;
            i6 = 2;
            if (i10 >= c1147d.f1926i.length) {
                break;
            }
            int i11 = i10 + 1;
            byte b = i11 < c1147d.f1926i.length ? c1147d.f1926i[i11] : (byte) 0;
            byte b2 = c1147d.f1926i[i10];
            if (b2 == 13) {
                i3 = b != 10 ? 0 : 2;
            } else if (b2 == 44) {
                i3 = b != 32 ? 0 : 4;
            } else if (b2 != 46) {
                if (b2 != 58) {
                    i3 = 0;
                } else {
                    i3 = b != 32 ? 0 : 5;
                }
            } else if (b != 32) {
                i3 = 0;
            }
            if (i3 > 0) {
                collectionSingletonList = C1147d.m1542a(collectionSingletonList, i10, i3);
                i10 = i11;
            } else {
                LinkedList linkedList = new LinkedList();
                Iterator it2 = collectionSingletonList.iterator();
                while (it2.hasNext()) {
                    c1147d.m1544a((C1149f) it2.next(), i10, linkedList);
                }
                collectionSingletonList = C1147d.m1540a(linkedList);
            }
            i10++;
        }
        C1190a c1190aM1552a = ((C1149f) Collections.min(collectionSingletonList, c1147d.new AnonymousClass1())).m1552a(c1147d.f1926i);
        int i12 = ((c1190aM1552a.f2065b * i) / 100) + 11;
        int i13 = c1190aM1552a.f2065b + i12;
        if (i2 != 0) {
            boolean z2 = i2 < 0;
            int iAbs = Math.abs(i2);
            if (iAbs > (z2 ? 4 : 32)) {
                throw new IllegalArgumentException(String.format("Illegal value %s for layers", Integer.valueOf(i2)));
            }
            iM1529a = m1529a(iAbs, z2);
            int i14 = f1916e[iAbs];
            int i15 = iM1529a - (iM1529a % i14);
            C1190a c1190aM1532a2 = m1532a(c1190aM1552a, i14);
            if (c1190aM1532a2.f2065b + i12 > i15) {
                throw new IllegalArgumentException("Data to large for user specified layer");
            }
            if (z2 && c1190aM1532a2.f2065b > (i14 << 6)) {
                throw new IllegalArgumentException("Data to large for user specified layer");
            }
            c1190aM1532a = c1190aM1532a2;
            z = z2;
            i8 = iAbs;
            i7 = i14;
        } else {
            c1190aM1532a = null;
            int i16 = 0;
            i7 = 0;
            while (true) {
                if (i16 > i5) {
                    throw new IllegalArgumentException("Data too large for an Aztec code");
                }
                z = i16 <= i3;
                i8 = z ? i16 + 1 : i16;
                int iM1529a2 = m1529a(i8, z);
                if (i13 <= iM1529a2) {
                    if (c1190aM1532a == null || i7 != f1916e[i8]) {
                        int i17 = f1916e[i8];
                        c1190aM1532a = m1532a(c1190aM1552a, i17);
                        i7 = i17;
                    }
                    int i18 = iM1529a2 - (iM1529a2 % i7);
                    if ((!z || c1190aM1532a.f2065b <= (i7 << 6)) && c1190aM1532a.f2065b + i12 <= i18) {
                        iM1529a = iM1529a2;
                        break;
                    }
                }
                i16++;
                i3 = 3;
                i4 = 5;
                i5 = 32;
                i6 = 2;
            }
        }
        C1190a c1190aM1533a2 = m1533a(c1190aM1532a, iM1529a, i7);
        int i19 = c1190aM1532a.f2065b / i7;
        C1190a c1190a = new C1190a();
        if (z) {
            c1190a.m1759b(i8 - 1, i6);
            c1190a.m1759b(i19 - 1, 6);
            c1190aM1533a = m1533a(c1190a, 28, 4);
        } else {
            c1190a.m1759b(i8 - 1, i4);
            c1190a.m1759b(i19 - 1, 11);
            c1190aM1533a = m1533a(c1190a, 40, 4);
        }
        int i20 = (z ? 11 : 14) + (i8 << 2);
        int[] iArr = new int[i20];
        if (z) {
            for (int i21 = 0; i21 < iArr.length; i21++) {
                iArr[i21] = i21;
            }
            i9 = i20;
        } else {
            int i22 = i20 / 2;
            i9 = i20 + 1 + (((i22 - 1) / 15) * 2);
            int i23 = i9 / 2;
            for (int i24 = 0; i24 < i22; i24++) {
                int i25 = (i24 / 15) + i24;
                iArr[(i22 - i24) - 1] = (i23 - i25) - 1;
                iArr[i22 + i24] = i25 + i23 + 1;
            }
        }
        C1194b c1194b = new C1194b(i9);
        int i26 = 0;
        int i27 = 0;
        while (i26 < i8) {
            int i28 = ((i8 - i26) << i6) + (z ? 9 : 12);
            int i29 = 0;
            while (i29 < i28) {
                int i30 = i29 << 1;
                int i31 = 0;
                while (i31 < i6) {
                    if (c1190aM1533a2.m1755a(i27 + i30 + i31)) {
                        int i32 = i26 << 1;
                        c1194b.m1790b(iArr[i32 + i31], iArr[i32 + i29]);
                    }
                    if (c1190aM1533a2.m1755a((i28 << 1) + i27 + i30 + i31)) {
                        int i33 = i26 << 1;
                        c1194b.m1790b(iArr[i33 + i29], iArr[((i20 - 1) - i33) - i31]);
                    }
                    if (c1190aM1533a2.m1755a((i28 << 2) + i27 + i30 + i31)) {
                        int i34 = (i20 - 1) - (i26 << 1);
                        c1194b.m1790b(iArr[i34 - i31], iArr[i34 - i29]);
                    }
                    if (c1190aM1533a2.m1755a((i28 * 6) + i27 + i30 + i31)) {
                        int i35 = i26 << 1;
                        c1194b.m1790b(iArr[((i20 - 1) - i35) - i29], iArr[i35 + i31]);
                    }
                    i31++;
                    i19 = i19;
                    i6 = 2;
                }
                i29++;
                i6 = 2;
            }
            i27 += i28 << 3;
            i26++;
            i19 = i19;
            i6 = 2;
        }
        int i36 = i19;
        m1537a(c1194b, z, i9, c1190aM1533a);
        if (z) {
            m1536a(c1194b, i9 / 2, 5);
        } else {
            int i37 = i9 / 2;
            m1536a(c1194b, i37, 7);
            int i38 = 0;
            int i39 = 0;
            while (i38 < (i20 / 2) - 1) {
                for (int i40 = i37 & 1; i40 < i9; i40 += 2) {
                    int i41 = i37 - i39;
                    c1194b.m1790b(i41, i40);
                    int i42 = i37 + i39;
                    c1194b.m1790b(i42, i40);
                    c1194b.m1790b(i40, i41);
                    c1194b.m1790b(i40, i42);
                }
                i38 += 15;
                i39 += 16;
            }
        }
        C1144a c1144a = new C1144a();
        c1144a.f1905a = z;
        c1144a.f1906b = i9;
        c1144a.f1907c = i8;
        c1144a.f1908d = i36;
        c1144a.f1909e = c1194b;
        return c1144a;
    }

    /* JADX INFO: renamed from: a */
    private static void m1536a(C1194b c1194b, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3 += 2) {
            int i4 = i - i3;
            int i5 = i4;
            while (true) {
                int i6 = i + i3;
                if (i5 <= i6) {
                    c1194b.m1790b(i5, i4);
                    c1194b.m1790b(i5, i6);
                    c1194b.m1790b(i4, i5);
                    c1194b.m1790b(i6, i5);
                    i5++;
                }
            }
        }
        int i7 = i - i2;
        c1194b.m1790b(i7, i7);
        int i8 = i7 + 1;
        c1194b.m1790b(i8, i7);
        c1194b.m1790b(i7, i8);
        int i9 = i + i2;
        c1194b.m1790b(i9, i7);
        c1194b.m1790b(i9, i8);
        c1194b.m1790b(i9, i9 - 1);
    }

    /* JADX INFO: renamed from: a */
    private static C1190a m1534a(boolean z, int i, int i2) {
        C1190a c1190a = new C1190a();
        if (z) {
            c1190a.m1759b(i - 1, 2);
            c1190a.m1759b(i2 - 1, 6);
            return m1533a(c1190a, 28, 4);
        }
        c1190a.m1759b(i - 1, 5);
        c1190a.m1759b(i2 - 1, 11);
        return m1533a(c1190a, 40, 4);
    }

    /* JADX INFO: renamed from: a */
    private static void m1537a(C1194b c1194b, boolean z, int i, C1190a c1190a) {
        int i2 = i / 2;
        int i3 = 0;
        if (z) {
            while (i3 < 7) {
                int i4 = (i2 - 3) + i3;
                if (c1190a.m1755a(i3)) {
                    c1194b.m1790b(i4, i2 - 5);
                }
                if (c1190a.m1755a(i3 + 7)) {
                    c1194b.m1790b(i2 + 5, i4);
                }
                if (c1190a.m1755a(20 - i3)) {
                    c1194b.m1790b(i4, i2 + 5);
                }
                if (c1190a.m1755a(27 - i3)) {
                    c1194b.m1790b(i2 - 5, i4);
                }
                i3++;
            }
            return;
        }
        while (i3 < 10) {
            int i5 = (i2 - 5) + i3 + (i3 / 5);
            if (c1190a.m1755a(i3)) {
                c1194b.m1790b(i5, i2 - 7);
            }
            if (c1190a.m1755a(i3 + 10)) {
                c1194b.m1790b(i2 + 7, i5);
            }
            if (c1190a.m1755a(29 - i3)) {
                c1194b.m1790b(i5, i2 + 7);
            }
            if (c1190a.m1755a(39 - i3)) {
                c1194b.m1790b(i2 - 7, i5);
            }
            i3++;
        }
    }

    /* JADX INFO: renamed from: b */
    private static int[] m1538b(C1190a c1190a, int i, int i2) {
        int[] iArr = new int[i2];
        int i3 = c1190a.f2065b / i;
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = 0;
            for (int i6 = 0; i6 < i; i6++) {
                i5 |= c1190a.m1755a((i4 * i) + i6) ? 1 << ((i - i6) - 1) : 0;
            }
            iArr[i4] = i5;
        }
        return iArr;
    }

    /* JADX INFO: renamed from: a */
    private static C1195a m1535a(int i) {
        if (i == 4) {
            return C1195a.f2084d;
        }
        if (i == 6) {
            return C1195a.f2083c;
        }
        if (i == 8) {
            return C1195a.f2087g;
        }
        if (i == 10) {
            return C1195a.f2082b;
        }
        if (i == 12) {
            return C1195a.f2081a;
        }
        throw new IllegalArgumentException("Unsupported word size ".concat(String.valueOf(i)));
    }

    /* JADX INFO: renamed from: a */
    private static C1190a m1532a(C1190a c1190a, int i) {
        C1190a c1190a2 = new C1190a();
        int i2 = c1190a.f2065b;
        int i3 = (1 << i) - 2;
        int i4 = 0;
        while (i4 < i2) {
            int i5 = 0;
            for (int i6 = 0; i6 < i; i6++) {
                int i7 = i4 + i6;
                if (i7 >= i2 || c1190a.m1755a(i7)) {
                    i5 |= 1 << ((i - 1) - i6);
                }
            }
            int i8 = i5 & i3;
            if (i8 == i3) {
                c1190a2.m1759b(i8, i);
                i4--;
            } else if (i8 == 0) {
                c1190a2.m1759b(i5 | 1, i);
                i4--;
            } else {
                c1190a2.m1759b(i5, i);
            }
            i4 += i;
        }
        return c1190a2;
    }

    /* JADX INFO: renamed from: a */
    private static C1190a m1533a(C1190a c1190a, int i, int i2) {
        C1195a c1195a;
        int i3 = c1190a.f2065b / i2;
        if (i2 == 4) {
            c1195a = C1195a.f2084d;
        } else if (i2 == 6) {
            c1195a = C1195a.f2083c;
        } else if (i2 == 8) {
            c1195a = C1195a.f2087g;
        } else if (i2 == 10) {
            c1195a = C1195a.f2082b;
        } else if (i2 == 12) {
            c1195a = C1195a.f2081a;
        } else {
            throw new IllegalArgumentException("Unsupported word size ".concat(String.valueOf(i2)));
        }
        C1198d c1198d = new C1198d(c1195a);
        int i4 = i / i2;
        int[] iArrM1538b = m1538b(c1190a, i2, i4);
        c1198d.m1821a(iArrM1538b, i4 - i3);
        C1190a c1190a2 = new C1190a();
        c1190a2.m1759b(0, i % i2);
        for (int i5 : iArrM1538b) {
            c1190a2.m1759b(i5, i2);
        }
        return c1190a2;
    }
}
