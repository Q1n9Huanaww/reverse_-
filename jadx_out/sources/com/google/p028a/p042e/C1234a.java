package com.google.p028a.p042e;

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
import com.google.p028a.p042e.p043a.C1237c;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.e.a */
/* JADX INFO: compiled from: MaxiCodeReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1234a implements InterfaceC1361p {

    /* JADX INFO: renamed from: a */
    private static final C1365t[] f2282a = new C1365t[0];

    /* JADX INFO: renamed from: b */
    private static final int f2283b = 30;

    /* JADX INFO: renamed from: c */
    private static final int f2284c = 33;

    /* JADX INFO: renamed from: d */
    private final C1237c f2285d = new C1237c();

    @Override // com.google.p028a.InterfaceC1361p
    /* JADX INFO: renamed from: a */
    public final void mo1491a() {
    }

    @Override // com.google.p028a.InterfaceC1361p
    /* JADX INFO: renamed from: a */
    public final C1363r mo1489a(C1189c c1189c) throws C1210d, C1358m, C1303h {
        return mo1490a(c1189c, null);
    }

    @Override // com.google.p028a.InterfaceC1361p
    /* JADX INFO: renamed from: a */
    public final C1363r mo1490a(C1189c c1189c, Map<EnumC1233e, ?> map) throws C1210d, C1358m, C1303h {
        if (map != null && map.containsKey(EnumC1233e.PURE_BARCODE)) {
            C1194b c1194bM1741c = c1189c.m1741c();
            int i = c1194bM1741c.f2077a;
            int i2 = -1;
            int i3 = c1194bM1741c.f2078b;
            int i4 = -1;
            int i5 = i;
            int i6 = 0;
            while (i6 < c1194bM1741c.f2078b) {
                int i7 = i3;
                int i8 = i5;
                for (int i9 = 0; i9 < c1194bM1741c.f2079c; i9++) {
                    int i10 = c1194bM1741c.f2080d[(c1194bM1741c.f2079c * i6) + i9];
                    if (i10 != 0) {
                        if (i6 < i7) {
                            i7 = i6;
                        }
                        if (i6 > i4) {
                            i4 = i6;
                        }
                        int i11 = i9 << 5;
                        int i12 = 31;
                        if (i11 < i8) {
                            int i13 = 0;
                            while ((i10 << (31 - i13)) == 0) {
                                i13++;
                            }
                            int i14 = i13 + i11;
                            if (i14 < i8) {
                                i8 = i14;
                            }
                        }
                        if (i11 + 31 > i2) {
                            while ((i10 >>> i12) == 0) {
                                i12--;
                            }
                            int i15 = i11 + i12;
                            if (i15 > i2) {
                                i2 = i15;
                            }
                        }
                    }
                }
                i6++;
                i5 = i8;
                i3 = i7;
            }
            int[] iArr = (i2 < i5 || i4 < i3) ? null : new int[]{i5, i3, (i2 - i5) + 1, (i4 - i3) + 1};
            if (iArr == null) {
                throw C1358m.m2611a();
            }
            int i16 = iArr[0];
            int i17 = iArr[1];
            int i18 = iArr[2];
            int i19 = iArr[3];
            C1194b c1194b = new C1194b(30, 33);
            for (int i20 = 0; i20 < 33; i20++) {
                int i21 = (((i20 * i19) + (i19 / 2)) / 33) + i17;
                for (int i22 = 0; i22 < 30; i22++) {
                    if (c1194bM1741c.m1789a(((((i22 * i18) + (i18 / 2)) + (((i20 & 1) * i18) / 2)) / 30) + i16, i21)) {
                        c1194b.m1790b(i22, i20);
                    }
                }
            }
            C1202e c1202eM2018a = this.f2285d.m2018a(c1194b);
            C1363r c1363r = new C1363r(c1202eM2018a.f2138c, c1202eM2018a.f2136a, f2282a, EnumC1138a.MAXICODE);
            String str = c1202eM2018a.f2140e;
            if (str != null) {
                c1363r.m2624a(EnumC1364s.ERROR_CORRECTION_LEVEL, str);
            }
            return c1363r;
        }
        throw C1358m.m2611a();
    }

    /* JADX INFO: renamed from: a */
    private static C1194b m2005a(C1194b c1194b) throws C1358m {
        int i = c1194b.f2077a;
        int i2 = -1;
        int i3 = c1194b.f2078b;
        int i4 = -1;
        int i5 = i;
        int i6 = 0;
        while (i6 < c1194b.f2078b) {
            int i7 = i4;
            int i8 = i2;
            int i9 = i5;
            for (int i10 = 0; i10 < c1194b.f2079c; i10++) {
                int i11 = c1194b.f2080d[(c1194b.f2079c * i6) + i10];
                if (i11 != 0) {
                    if (i6 < i3) {
                        i3 = i6;
                    }
                    if (i6 > i7) {
                        i7 = i6;
                    }
                    int i12 = i10 << 5;
                    int i13 = 31;
                    if (i12 < i9) {
                        int i14 = 0;
                        while ((i11 << (31 - i14)) == 0) {
                            i14++;
                        }
                        int i15 = i14 + i12;
                        if (i15 < i9) {
                            i9 = i15;
                        }
                    }
                    if (i12 + 31 > i8) {
                        while ((i11 >>> i13) == 0) {
                            i13--;
                        }
                        int i16 = i12 + i13;
                        if (i16 > i8) {
                            i8 = i16;
                        }
                    }
                }
            }
            i6++;
            i5 = i9;
            i2 = i8;
            i4 = i7;
        }
        int[] iArr = (i2 < i5 || i4 < i3) ? null : new int[]{i5, i3, (i2 - i5) + 1, (i4 - i3) + 1};
        if (iArr == null) {
            throw C1358m.m2611a();
        }
        int i17 = iArr[0];
        int i18 = iArr[1];
        int i19 = iArr[2];
        int i20 = iArr[3];
        C1194b c1194b2 = new C1194b(30, 33);
        for (int i21 = 0; i21 < 33; i21++) {
            int i22 = (((i21 * i20) + (i20 / 2)) / 33) + i18;
            for (int i23 = 0; i23 < 30; i23++) {
                if (c1194b.m1789a(((((i23 * i19) + (i19 / 2)) + (((i21 & 1) * i19) / 2)) / 30) + i17, i22)) {
                    c1194b2.m1790b(i23, i21);
                }
            }
        }
        return c1194b2;
    }
}
