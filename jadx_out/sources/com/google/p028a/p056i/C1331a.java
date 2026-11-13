package com.google.p028a.p056i;

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
import com.google.p028a.p056i.p057a.C1336e;
import com.google.p028a.p056i.p057a.C1340i;
import com.google.p028a.p056i.p058b.C1345c;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.i.a */
/* JADX INFO: compiled from: QRCodeReader.java */
/* JADX INFO: loaded from: classes.dex */
public class C1331a implements InterfaceC1361p {

    /* JADX INFO: renamed from: b */
    private static final C1365t[] f2711b = new C1365t[0];

    /* JADX INFO: renamed from: a */
    protected final C1336e f2712a = new C1336e();

    @Override // com.google.p028a.InterfaceC1361p
    /* JADX INFO: renamed from: a */
    public final void mo1491a() {
    }

    /* JADX INFO: renamed from: b */
    private C1336e m2432b() {
        return this.f2712a;
    }

    @Override // com.google.p028a.InterfaceC1361p
    /* JADX INFO: renamed from: a */
    public final C1363r mo1489a(C1189c c1189c) throws C1210d, C1358m, C1303h {
        return mo1490a(c1189c, (Map<EnumC1233e, ?>) null);
    }

    @Override // com.google.p028a.InterfaceC1361p
    /* JADX INFO: renamed from: a */
    public final C1363r mo1490a(C1189c c1189c, Map<EnumC1233e, ?> map) throws C1210d, C1358m, C1303h {
        C1365t[] c1365tArr;
        C1202e c1202eM2458a;
        if (map != null && map.containsKey(EnumC1233e.PURE_BARCODE)) {
            C1194b c1194bM1741c = c1189c.m1741c();
            int[] iArrM1792b = c1194bM1741c.m1792b();
            int[] iArrM1794c = c1194bM1741c.m1794c();
            if (iArrM1792b == null || iArrM1794c == null) {
                throw C1358m.m2611a();
            }
            int i = c1194bM1741c.f2078b;
            int i2 = c1194bM1741c.f2077a;
            int i3 = iArrM1792b[0];
            int i4 = iArrM1792b[1];
            boolean z = true;
            int i5 = 0;
            while (i3 < i2 && i4 < i) {
                if (z != c1194bM1741c.m1789a(i3, i4)) {
                    i5++;
                    if (i5 == 5) {
                        break;
                    }
                    z = !z;
                }
                i3++;
                i4++;
            }
            if (i3 == i2 || i4 == i) {
                throw C1358m.m2611a();
            }
            float f = (i3 - iArrM1792b[0]) / 7.0f;
            int i6 = iArrM1792b[1];
            int i7 = iArrM1794c[1];
            int i8 = iArrM1792b[0];
            int i9 = iArrM1794c[0];
            if (i8 >= i9 || i6 >= i7) {
                throw C1358m.m2611a();
            }
            int i10 = i7 - i6;
            if (i10 != i9 - i8 && (i9 = i8 + i10) >= c1194bM1741c.f2077a) {
                throw C1358m.m2611a();
            }
            int iRound = Math.round(((i9 - i8) + 1) / f);
            int iRound2 = Math.round((i10 + 1) / f);
            if (iRound <= 0 || iRound2 <= 0) {
                throw C1358m.m2611a();
            }
            if (iRound2 != iRound) {
                throw C1358m.m2611a();
            }
            int i11 = (int) (f / 2.0f);
            int i12 = i6 + i11;
            int i13 = i8 + i11;
            int i14 = (((int) ((iRound - 1) * f)) + i13) - i9;
            if (i14 > 0) {
                if (i14 > i11) {
                    throw C1358m.m2611a();
                }
                i13 -= i14;
            }
            int i15 = (((int) ((iRound2 - 1) * f)) + i12) - i7;
            if (i15 > 0) {
                if (i15 > i11) {
                    throw C1358m.m2611a();
                }
                i12 -= i15;
            }
            C1194b c1194b = new C1194b(iRound, iRound2);
            for (int i16 = 0; i16 < iRound2; i16++) {
                int i17 = ((int) (i16 * f)) + i12;
                for (int i18 = 0; i18 < iRound; i18++) {
                    if (c1194bM1741c.m1789a(((int) (i18 * f)) + i13, i17)) {
                        c1194b.m1790b(i18, i16);
                    }
                }
            }
            c1202eM2458a = this.f2712a.m2458a(c1194b, map);
            c1365tArr = f2711b;
        } else {
            C1204g c1204gM2507a = new C1345c(c1189c.m1741c()).m2507a(map);
            C1202e c1202eM2458a2 = this.f2712a.m2458a(c1204gM2507a.f2146d, map);
            c1365tArr = c1204gM2507a.f2147e;
            c1202eM2458a = c1202eM2458a2;
        }
        if (c1202eM2458a.f2143h instanceof C1340i) {
            ((C1340i) c1202eM2458a.f2143h).m2470a(c1365tArr);
        }
        C1363r c1363r = new C1363r(c1202eM2458a.f2138c, c1202eM2458a.f2136a, c1365tArr, EnumC1138a.QR_CODE);
        List<byte[]> list = c1202eM2458a.f2139d;
        if (list != null) {
            c1363r.m2624a(EnumC1364s.BYTE_SEGMENTS, list);
        }
        String str = c1202eM2458a.f2140e;
        if (str != null) {
            c1363r.m2624a(EnumC1364s.ERROR_CORRECTION_LEVEL, str);
        }
        if (c1202eM2458a.m1843a()) {
            c1363r.m2624a(EnumC1364s.STRUCTURED_APPEND_SEQUENCE, Integer.valueOf(c1202eM2458a.f2145j));
            c1363r.m2624a(EnumC1364s.STRUCTURED_APPEND_PARITY, Integer.valueOf(c1202eM2458a.f2144i));
        }
        return c1363r;
    }

    /* JADX INFO: renamed from: a */
    private static C1194b m2431a(C1194b c1194b) throws C1358m {
        int[] iArrM1792b = c1194b.m1792b();
        int[] iArrM1794c = c1194b.m1794c();
        if (iArrM1792b == null || iArrM1794c == null) {
            throw C1358m.m2611a();
        }
        int i = c1194b.f2078b;
        int i2 = c1194b.f2077a;
        int i3 = iArrM1792b[0];
        int i4 = iArrM1792b[1];
        boolean z = true;
        int i5 = 0;
        while (i3 < i2 && i4 < i) {
            if (z != c1194b.m1789a(i3, i4)) {
                i5++;
                if (i5 == 5) {
                    break;
                }
                z = !z;
            }
            i3++;
            i4++;
        }
        if (i3 == i2 || i4 == i) {
            throw C1358m.m2611a();
        }
        float f = (i3 - iArrM1792b[0]) / 7.0f;
        int i6 = iArrM1792b[1];
        int i7 = iArrM1794c[1];
        int i8 = iArrM1792b[0];
        int i9 = iArrM1794c[0];
        if (i8 >= i9 || i6 >= i7) {
            throw C1358m.m2611a();
        }
        int i10 = i7 - i6;
        if (i10 != i9 - i8 && (i9 = i8 + i10) >= c1194b.f2077a) {
            throw C1358m.m2611a();
        }
        int iRound = Math.round(((i9 - i8) + 1) / f);
        int iRound2 = Math.round((i10 + 1) / f);
        if (iRound <= 0 || iRound2 <= 0) {
            throw C1358m.m2611a();
        }
        if (iRound2 != iRound) {
            throw C1358m.m2611a();
        }
        int i11 = (int) (f / 2.0f);
        int i12 = i6 + i11;
        int i13 = i8 + i11;
        int i14 = (((int) ((iRound - 1) * f)) + i13) - i9;
        if (i14 > 0) {
            if (i14 > i11) {
                throw C1358m.m2611a();
            }
            i13 -= i14;
        }
        int i15 = (((int) ((iRound2 - 1) * f)) + i12) - i7;
        if (i15 > 0) {
            if (i15 > i11) {
                throw C1358m.m2611a();
            }
            i12 -= i15;
        }
        C1194b c1194b2 = new C1194b(iRound, iRound2);
        for (int i16 = 0; i16 < iRound2; i16++) {
            int i17 = ((int) (i16 * f)) + i12;
            for (int i18 = 0; i18 < iRound; i18++) {
                if (c1194b.m1789a(((int) (i18 * f)) + i13, i17)) {
                    c1194b2.m1790b(i18, i16);
                }
            }
        }
        return c1194b2;
    }

    /* JADX INFO: renamed from: a */
    private static float m2430a(int[] iArr, C1194b c1194b) throws C1358m {
        int i = c1194b.f2078b;
        int i2 = c1194b.f2077a;
        int i3 = iArr[0];
        boolean z = true;
        int i4 = iArr[1];
        int i5 = 0;
        while (i3 < i2 && i4 < i) {
            if (z != c1194b.m1789a(i3, i4)) {
                i5++;
                if (i5 == 5) {
                    break;
                }
                z = !z;
            }
            i3++;
            i4++;
        }
        if (i3 == i2 || i4 == i) {
            throw C1358m.m2611a();
        }
        return (i3 - iArr[0]) / 7.0f;
    }
}
