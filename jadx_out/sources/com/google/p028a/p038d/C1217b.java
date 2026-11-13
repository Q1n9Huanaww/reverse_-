package com.google.p028a.p038d;

import com.google.p028a.C1238f;
import com.google.p028a.EnumC1138a;
import com.google.p028a.EnumC1245g;
import com.google.p028a.InterfaceC1367v;
import com.google.p028a.p035c.C1194b;
import com.google.p028a.p038d.p041c.C1223e;
import com.google.p028a.p038d.p041c.C1227i;
import com.google.p028a.p038d.p041c.C1228j;
import com.google.p028a.p038d.p041c.C1229k;
import com.google.p028a.p038d.p041c.EnumC1230l;
import com.google.p028a.p056i.p059c.C1350b;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.d.b */
/* JADX INFO: compiled from: DataMatrixWriter.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1217b implements InterfaceC1367v {
    @Override // com.google.p028a.InterfaceC1367v
    /* JADX INFO: renamed from: a */
    public final C1194b mo1516a(String str, EnumC1138a enumC1138a, int i, int i2) {
        return mo1517a(str, enumC1138a, i, i2, null);
    }

    @Override // com.google.p028a.InterfaceC1367v
    /* JADX INFO: renamed from: a */
    public final C1194b mo1517a(String str, EnumC1138a enumC1138a, int i, int i2, Map<EnumC1245g, ?> map) {
        C1238f c1238f;
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        }
        if (enumC1138a != EnumC1138a.DATA_MATRIX) {
            throw new IllegalArgumentException("Can only encode DATA_MATRIX, but got ".concat(String.valueOf(enumC1138a)));
        }
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("Requested dimensions can't be negative: " + i + 'x' + i2);
        }
        EnumC1230l enumC1230l = EnumC1230l.FORCE_NONE;
        C1238f c1238f2 = null;
        if (map != null) {
            EnumC1230l enumC1230l2 = (EnumC1230l) map.get(EnumC1245g.DATA_MATRIX_SHAPE);
            if (enumC1230l2 != null) {
                enumC1230l = enumC1230l2;
            }
            c1238f = (C1238f) map.get(EnumC1245g.MIN_SIZE);
            if (c1238f == null) {
                c1238f = null;
            }
            C1238f c1238f3 = (C1238f) map.get(EnumC1245g.MAX_SIZE);
            if (c1238f3 != null) {
                c1238f2 = c1238f3;
            }
        } else {
            c1238f = null;
        }
        String strM1978a = C1228j.m1978a(str, enumC1230l, c1238f, c1238f2);
        C1229k c1229kM1989a = C1229k.m1989a(strM1978a.length(), enumC1230l, c1238f, c1238f2, true);
        C1223e c1223e = new C1223e(C1227i.m1971a(strM1978a, c1229kM1989a), c1229kM1989a.m2000b(), c1229kM1989a.m2001c());
        c1223e.m1945a();
        return m1908a(c1223e, c1229kM1989a, i, i2);
    }

    /* JADX INFO: renamed from: a */
    private static C1194b m1908a(C1223e c1223e, C1229k c1229k, int i, int i2) {
        C1194b c1194b;
        int iM2000b = c1229k.m2000b();
        int iM2001c = c1229k.m2001c();
        C1350b c1350b = new C1350b(c1229k.m2002d(), c1229k.m2003e());
        int i3 = 0;
        for (int i4 = 0; i4 < iM2001c; i4++) {
            if (i4 % c1229k.f2260e == 0) {
                int i5 = 0;
                for (int i6 = 0; i6 < c1229k.m2002d(); i6++) {
                    c1350b.m2543a(i5, i3, i6 % 2 == 0);
                    i5++;
                }
                i3++;
            }
            int i7 = 0;
            for (int i8 = 0; i8 < iM2000b; i8++) {
                if (i8 % c1229k.f2259d == 0) {
                    c1350b.m2543a(i7, i3, true);
                    i7++;
                }
                c1350b.m2543a(i7, i3, c1223e.f2218b[(c1223e.f2217a * i4) + i8] == 1);
                i7++;
                if (i8 % c1229k.f2259d == c1229k.f2259d - 1) {
                    c1350b.m2543a(i7, i3, i4 % 2 == 0);
                    i7++;
                }
            }
            i3++;
            if (i4 % c1229k.f2260e == c1229k.f2260e - 1) {
                int i9 = 0;
                for (int i10 = 0; i10 < c1229k.m2002d(); i10++) {
                    c1350b.m2543a(i9, i3, true);
                    i9++;
                }
                i3++;
            }
        }
        int i11 = c1350b.f2798b;
        int i12 = c1350b.f2799c;
        int iMax = Math.max(i, i11);
        int iMax2 = Math.max(i2, i12);
        int iMin = Math.min(iMax / i11, iMax2 / i12);
        int i13 = (iMax - (i11 * iMin)) / 2;
        int i14 = (iMax2 - (i12 * iMin)) / 2;
        if (i2 < i12 || i < i11) {
            c1194b = new C1194b(i11, i12);
            i13 = 0;
            i14 = 0;
        } else {
            c1194b = new C1194b(i, i2);
        }
        c1194b.m1787a();
        int i15 = 0;
        while (i15 < i12) {
            int i16 = i13;
            int i17 = 0;
            while (i17 < i11) {
                if (c1350b.m2541a(i17, i15) == 1) {
                    c1194b.m1788a(i16, i14, iMin, iMin);
                }
                i17++;
                i16 += iMin;
            }
            i15++;
            i14 += iMin;
        }
        return c1194b;
    }

    /* JADX INFO: renamed from: a */
    private static C1194b m1909a(C1350b c1350b, int i, int i2) {
        C1194b c1194b;
        int i3 = c1350b.f2798b;
        int i4 = c1350b.f2799c;
        int iMax = Math.max(i, i3);
        int iMax2 = Math.max(i2, i4);
        int iMin = Math.min(iMax / i3, iMax2 / i4);
        int i5 = (iMax - (i3 * iMin)) / 2;
        int i6 = (iMax2 - (i4 * iMin)) / 2;
        if (i2 < i4 || i < i3) {
            c1194b = new C1194b(i3, i4);
            i5 = 0;
            i6 = 0;
        } else {
            c1194b = new C1194b(i, i2);
        }
        c1194b.m1787a();
        int i7 = 0;
        while (i7 < i4) {
            int i8 = i5;
            int i9 = 0;
            while (i9 < i3) {
                if (c1350b.m2541a(i9, i7) == 1) {
                    c1194b.m1788a(i8, i6, iMin, iMin);
                }
                i9++;
                i8 += iMin;
            }
            i7++;
            i6 += iMin;
        }
        return c1194b;
    }
}
