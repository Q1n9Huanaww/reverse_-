package com.google.p028a.p038d;

import com.google.p028a.C1189c;
import com.google.p028a.C1210d;
import com.google.p028a.C1303h;
import com.google.p028a.C1358m;
import com.google.p028a.C1363r;
import com.google.p028a.C1365t;
import com.google.p028a.EnumC1138a;
import com.google.p028a.EnumC1233e;
import com.google.p028a.EnumC1364s;
import com.google.p028a.InterfaceC1361p;
import com.google.p028a.p035c.C1194b;
import com.google.p028a.p035c.C1202e;
import com.google.p028a.p035c.C1204g;
import com.google.p028a.p038d.p039a.C1215d;
import com.google.p028a.p038d.p040b.C1218a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.d.a */
/* JADX INFO: compiled from: DataMatrixReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1211a implements InterfaceC1361p {

    /* JADX INFO: renamed from: a */
    private static final C1365t[] f2178a = new C1365t[0];

    /* JADX INFO: renamed from: b */
    private final C1215d f2179b = new C1215d();

    @Override // com.google.p028a.InterfaceC1361p
    /* JADX INFO: renamed from: a */
    public final void mo1491a() {
    }

    @Override // com.google.p028a.InterfaceC1361p
    /* JADX INFO: renamed from: a */
    public final C1363r mo1489a(C1189c c1189c) throws C1210d, C1358m, C1303h {
        return mo1490a(c1189c, (Map<EnumC1233e, ?>) null);
    }

    /* JADX WARN: Code duplicated, block: B:103:0x02e0  */
    /* JADX WARN: Code duplicated, block: B:84:0x0213  */
    @Override // com.google.p028a.InterfaceC1361p
    /* JADX INFO: renamed from: a */
    public final C1363r mo1490a(C1189c c1189c, Map<EnumC1233e, ?> map) throws C1210d, C1358m, C1303h {
        C1365t c1365t;
        C1194b c1194bM1911a;
        C1202e c1202eM1894a;
        C1365t[] c1365tArr;
        C1365t c1365t2;
        if (map != null && map.containsKey(EnumC1233e.PURE_BARCODE)) {
            C1194b c1194bM1741c = c1189c.m1741c();
            int[] iArrM1792b = c1194bM1741c.m1792b();
            int[] iArrM1794c = c1194bM1741c.m1794c();
            if (iArrM1792b == null || iArrM1794c == null) {
                throw C1358m.m2611a();
            }
            int i = c1194bM1741c.f2077a;
            int i2 = iArrM1792b[0];
            int i3 = iArrM1792b[1];
            while (i2 < i && c1194bM1741c.m1789a(i2, i3)) {
                i2++;
            }
            if (i2 == i) {
                throw C1358m.m2611a();
            }
            int i4 = i2 - iArrM1792b[0];
            if (i4 != 0) {
                int i5 = iArrM1792b[1];
                int i6 = iArrM1794c[1];
                int i7 = iArrM1792b[0];
                int i8 = ((iArrM1794c[0] - i7) + 1) / i4;
                int i9 = ((i6 - i5) + 1) / i4;
                if (i8 <= 0 || i9 <= 0) {
                    throw C1358m.m2611a();
                }
                int i10 = i4 / 2;
                int i11 = i5 + i10;
                int i12 = i7 + i10;
                C1194b c1194b = new C1194b(i8, i9);
                for (int i13 = 0; i13 < i9; i13++) {
                    int i14 = (i13 * i4) + i11;
                    for (int i15 = 0; i15 < i8; i15++) {
                        if (c1194bM1741c.m1789a((i15 * i4) + i12, i14)) {
                            c1194b.m1790b(i15, i13);
                        }
                    }
                }
                c1202eM1894a = this.f2179b.m1894a(c1194b);
                c1365tArr = f2178a;
            } else {
                throw C1358m.m2611a();
            }
        } else {
            C1218a c1218a = new C1218a(c1189c.m1741c());
            C1365t[] c1365tArrM1773a = c1218a.f2213b.m1773a();
            C1365t c1365t3 = c1365tArrM1773a[0];
            C1365t c1365t4 = c1365tArrM1773a[1];
            C1365t c1365t5 = c1365tArrM1773a[2];
            C1365t c1365t6 = c1365tArrM1773a[3];
            ArrayList arrayList = new ArrayList(4);
            arrayList.add(c1218a.m1917b(c1365t3, c1365t4));
            arrayList.add(c1218a.m1917b(c1365t3, c1365t5));
            arrayList.add(c1218a.m1917b(c1365t4, c1365t6));
            arrayList.add(c1218a.m1917b(c1365t5, c1365t6));
            Collections.sort(arrayList, new C1218a.b((byte) 0));
            C1218a.a aVar = (C1218a.a) arrayList.get(0);
            C1218a.a aVar2 = (C1218a.a) arrayList.get(1);
            HashMap map2 = new HashMap();
            C1218a.m1915a(map2, aVar.f2214a);
            C1218a.m1915a(map2, aVar.f2215b);
            C1218a.m1915a(map2, aVar2.f2214a);
            C1218a.m1915a(map2, aVar2.f2215b);
            C1365t c1365t7 = null;
            C1365t c1365t8 = null;
            C1365t c1365t9 = null;
            for (Map.Entry entry : map2.entrySet()) {
                C1365t c1365t10 = (C1365t) entry.getKey();
                if (((Integer) entry.getValue()).intValue() == 2) {
                    c1365t8 = c1365t10;
                } else if (c1365t7 == null) {
                    c1365t7 = c1365t10;
                } else {
                    c1365t9 = c1365t10;
                }
            }
            if (c1365t7 == null || c1365t8 == null || c1365t9 == null) {
                throw C1358m.m2611a();
            }
            C1365t[] c1365tArr2 = {c1365t7, c1365t8, c1365t9};
            C1365t.m2629a(c1365tArr2);
            C1365t c1365t11 = c1365tArr2[0];
            C1365t c1365t12 = c1365tArr2[1];
            C1365t c1365t13 = c1365tArr2[2];
            if (!map2.containsKey(c1365t3)) {
                c1365t6 = c1365t3;
            } else if (!map2.containsKey(c1365t4)) {
                c1365t6 = c1365t4;
            } else if (!map2.containsKey(c1365t5)) {
                c1365t6 = c1365t5;
            }
            int i16 = c1218a.m1917b(c1365t13, c1365t6).f2216c;
            int i17 = c1218a.m1917b(c1365t11, c1365t6).f2216c;
            if ((i16 & 1) == 1) {
                i16++;
            }
            int i18 = i16 + 2;
            if ((i17 & 1) == 1) {
                i17++;
            }
            int i19 = i17 + 2;
            if (i18 * 4 < i19 * 7 && i19 * 4 < i18 * 7) {
                float fMin = Math.min(i19, i18);
                float fM1910a = C1218a.m1910a(c1365t12, c1365t11) / fMin;
                float fM1910a2 = C1218a.m1910a(c1365t13, c1365t6);
                C1365t c1365t14 = new C1365t(c1365t6.f2858a + (((c1365t6.f2858a - c1365t13.f2858a) / fM1910a2) * fM1910a), c1365t6.f2859b + (fM1910a * ((c1365t6.f2859b - c1365t13.f2859b) / fM1910a2)));
                float fM1910a3 = C1218a.m1910a(c1365t12, c1365t13) / fMin;
                float fM1910a4 = C1218a.m1910a(c1365t11, c1365t6);
                C1365t c1365t15 = new C1365t(c1365t6.f2858a + (((c1365t6.f2858a - c1365t11.f2858a) / fM1910a4) * fM1910a3), c1365t6.f2859b + (fM1910a3 * ((c1365t6.f2859b - c1365t11.f2859b) / fM1910a4)));
                if (!c1218a.m1916a(c1365t14)) {
                    if (c1218a.m1916a(c1365t15)) {
                        c1365t2 = c1365t15;
                    } else {
                        c1365t2 = null;
                    }
                } else if (c1218a.m1916a(c1365t15) && Math.abs(c1218a.m1917b(c1365t13, c1365t14).f2216c - c1218a.m1917b(c1365t11, c1365t14).f2216c) > Math.abs(c1218a.m1917b(c1365t13, c1365t15).f2216c - c1218a.m1917b(c1365t11, c1365t15).f2216c)) {
                    c1365t2 = c1365t15;
                } else {
                    c1365t2 = c1365t14;
                }
                if (c1365t2 != null) {
                    c1365t6 = c1365t2;
                }
                int iMax = Math.max(c1218a.m1917b(c1365t13, c1365t6).f2216c, c1218a.m1917b(c1365t11, c1365t6).f2216c) + 1;
                if ((iMax & 1) == 1) {
                    iMax++;
                }
                int i20 = iMax;
                c1194bM1911a = C1218a.m1911a(c1218a.f2212a, c1365t13, c1365t12, c1365t11, c1365t6, i20, i20);
            } else {
                float fM1910a5 = C1218a.m1910a(c1365t12, c1365t11) / i18;
                float fM1910a6 = C1218a.m1910a(c1365t13, c1365t6);
                C1365t c1365t16 = new C1365t(c1365t6.f2858a + (((c1365t6.f2858a - c1365t13.f2858a) / fM1910a6) * fM1910a5), c1365t6.f2859b + (fM1910a5 * ((c1365t6.f2859b - c1365t13.f2859b) / fM1910a6)));
                float fM1910a7 = C1218a.m1910a(c1365t12, c1365t13) / i19;
                float fM1910a8 = C1218a.m1910a(c1365t11, c1365t6);
                C1365t c1365t17 = new C1365t(c1365t6.f2858a + (((c1365t6.f2858a - c1365t11.f2858a) / fM1910a8) * fM1910a7), c1365t6.f2859b + (fM1910a7 * ((c1365t6.f2859b - c1365t11.f2859b) / fM1910a8)));
                if (!c1218a.m1916a(c1365t16)) {
                    if (c1218a.m1916a(c1365t17)) {
                        c1365t = c1365t17;
                    } else {
                        c1365t = null;
                    }
                } else if (c1218a.m1916a(c1365t17) && Math.abs(i18 - c1218a.m1917b(c1365t13, c1365t16).f2216c) + Math.abs(i19 - c1218a.m1917b(c1365t11, c1365t16).f2216c) > Math.abs(i18 - c1218a.m1917b(c1365t13, c1365t17).f2216c) + Math.abs(i19 - c1218a.m1917b(c1365t11, c1365t17).f2216c)) {
                    c1365t = c1365t17;
                } else {
                    c1365t = c1365t16;
                }
                if (c1365t != null) {
                    c1365t6 = c1365t;
                }
                int i21 = c1218a.m1917b(c1365t13, c1365t6).f2216c;
                int i22 = c1218a.m1917b(c1365t11, c1365t6).f2216c;
                if ((i21 & 1) == 1) {
                    i21++;
                }
                int i23 = i21;
                if ((i22 & 1) == 1) {
                    i22++;
                }
                c1194bM1911a = C1218a.m1911a(c1218a.f2212a, c1365t13, c1365t12, c1365t11, c1365t6, i23, i22);
            }
            C1204g c1204g = new C1204g(c1194bM1911a, new C1365t[]{c1365t13, c1365t12, c1365t11, c1365t6});
            c1202eM1894a = this.f2179b.m1894a(c1204g.f2146d);
            c1365tArr = c1204g.f2147e;
        }
        C1363r c1363r = new C1363r(c1202eM1894a.f2138c, c1202eM1894a.f2136a, c1365tArr, EnumC1138a.DATA_MATRIX);
        List<byte[]> list = c1202eM1894a.f2139d;
        if (list != null) {
            c1363r.m2624a(EnumC1364s.BYTE_SEGMENTS, list);
        }
        String str = c1202eM1894a.f2140e;
        if (str != null) {
            c1363r.m2624a(EnumC1364s.ERROR_CORRECTION_LEVEL, str);
        }
        return c1363r;
    }

    /* JADX INFO: renamed from: a */
    private static C1194b m1868a(C1194b c1194b) throws C1358m {
        int[] iArrM1792b = c1194b.m1792b();
        int[] iArrM1794c = c1194b.m1794c();
        if (iArrM1792b == null || iArrM1794c == null) {
            throw C1358m.m2611a();
        }
        int i = c1194b.f2077a;
        int i2 = iArrM1792b[0];
        int i3 = iArrM1792b[1];
        while (i2 < i && c1194b.m1789a(i2, i3)) {
            i2++;
        }
        if (i2 == i) {
            throw C1358m.m2611a();
        }
        int i4 = i2 - iArrM1792b[0];
        if (i4 != 0) {
            int i5 = iArrM1792b[1];
            int i6 = iArrM1794c[1];
            int i7 = iArrM1792b[0];
            int i8 = ((iArrM1794c[0] - i7) + 1) / i4;
            int i9 = ((i6 - i5) + 1) / i4;
            if (i8 <= 0 || i9 <= 0) {
                throw C1358m.m2611a();
            }
            int i10 = i4 / 2;
            int i11 = i5 + i10;
            int i12 = i7 + i10;
            C1194b c1194b2 = new C1194b(i8, i9);
            for (int i13 = 0; i13 < i9; i13++) {
                int i14 = (i13 * i4) + i11;
                for (int i15 = 0; i15 < i8; i15++) {
                    if (c1194b.m1789a((i15 * i4) + i12, i14)) {
                        c1194b2.m1790b(i15, i13);
                    }
                }
            }
            return c1194b2;
        }
        throw C1358m.m2611a();
    }

    /* JADX INFO: renamed from: a */
    private static int m1867a(int[] iArr, C1194b c1194b) throws C1358m {
        int i = c1194b.f2077a;
        int i2 = iArr[0];
        int i3 = iArr[1];
        while (i2 < i && c1194b.m1789a(i2, i3)) {
            i2++;
        }
        if (i2 == i) {
            throw C1358m.m2611a();
        }
        int i4 = i2 - iArr[0];
        if (i4 == 0) {
            throw C1358m.m2611a();
        }
        return i4;
    }
}
