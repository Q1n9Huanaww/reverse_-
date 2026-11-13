package com.google.p028a.p056i.p057a;

import com.google.p028a.C1210d;
import com.google.p028a.C1303h;
import com.google.p028a.EnumC1233e;
import com.google.p028a.p035c.C1194b;
import com.google.p028a.p035c.C1202e;
import com.google.p028a.p035c.p037b.C1195a;
import com.google.p028a.p035c.p037b.C1197c;
import com.google.p028a.p035c.p037b.C1199e;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.i.a.e */
/* JADX INFO: compiled from: Decoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1336e {

    /* JADX INFO: renamed from: a */
    private final C1197c f2731a = new C1197c(C1195a.f2085e);

    /* JADX INFO: renamed from: b */
    private C1202e m2457b(boolean[][] zArr) throws C1210d, C1303h {
        return m2458a(C1194b.m1775a(zArr), (Map<EnumC1233e, ?>) null);
    }

    /* JADX INFO: renamed from: a */
    private C1202e m2453a(C1194b c1194b) throws C1210d, C1303h {
        return m2458a(c1194b, (Map<EnumC1233e, ?>) null);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0015 A[Catch: d | h -> 0x006e, TryCatch #2 {d | h -> 0x006e, blocks: (B:9:0x0011, B:11:0x0015, B:12:0x0028, B:13:0x0036, B:15:0x003c, B:16:0x003f, B:18:0x0045, B:20:0x0053, B:21:0x005d, B:23:0x0062), top: B:28:0x0011 }] */
    /* JADX WARN: Code duplicated, block: B:15:0x003c A[Catch: d | h -> 0x006e, TryCatch #2 {d | h -> 0x006e, blocks: (B:9:0x0011, B:11:0x0015, B:12:0x0028, B:13:0x0036, B:15:0x003c, B:16:0x003f, B:18:0x0045, B:20:0x0053, B:21:0x005d, B:23:0x0062), top: B:28:0x0011 }] */
    /* JADX WARN: Code duplicated, block: B:18:0x0045 A[Catch: d | h -> 0x006e, TryCatch #2 {d | h -> 0x006e, blocks: (B:9:0x0011, B:11:0x0015, B:12:0x0028, B:13:0x0036, B:15:0x003c, B:16:0x003f, B:18:0x0045, B:20:0x0053, B:21:0x005d, B:23:0x0062), top: B:28:0x0011 }] */
    /* JADX WARN: Code duplicated, block: B:20:0x0053 A[Catch: d | h -> 0x006e, TryCatch #2 {d | h -> 0x006e, blocks: (B:9:0x0011, B:11:0x0015, B:12:0x0028, B:13:0x0036, B:15:0x003c, B:16:0x003f, B:18:0x0045, B:20:0x0053, B:21:0x005d, B:23:0x0062), top: B:28:0x0011 }] */
    /* JADX WARN: Code duplicated, block: B:35:0x005d A[SYNTHETIC] */
    /* JADX INFO: renamed from: a */
    public final C1202e m2458a(C1194b c1194b, Map<EnumC1233e, ?> map) throws C1210d, C1303h {
        C1210d c1210d;
        int i;
        int i2;
        C1332a c1332a = new C1332a(c1194b);
        try {
            return m2454a(c1332a, map);
        } catch (C1210d e) {
            c1210d = e;
            e = null;
            try {
                if (c1332a.f2715c != null) {
                    EnumC1334c.values()[c1332a.f2715c.f2742b].m2443a(c1332a.f2713a, c1332a.f2713a.f2078b);
                }
                c1332a.f2714b = null;
                c1332a.f2715c = null;
                c1332a.f2716d = true;
                c1332a.m2439b();
                c1332a.m2438a();
                i = 0;
                while (i < c1332a.f2713a.f2077a) {
                    int i3 = i + 1;
                    for (i2 = i3; i2 < c1332a.f2713a.f2078b; i2++) {
                        if (c1332a.f2713a.m1789a(i, i2) != c1332a.f2713a.m1789a(i2, i)) {
                            c1332a.f2713a.m1793c(i2, i);
                            c1332a.f2713a.m1793c(i, i2);
                        }
                    }
                    i = i3;
                }
                C1202e c1202eM2454a = m2454a(c1332a, map);
                c1202eM2454a.f2143h = new C1340i();
                return c1202eM2454a;
            } catch (C1210d | C1303h unused) {
                if (e != null) {
                    throw e;
                }
                throw c1210d;
            }
        } catch (C1303h e2) {
            e = e2;
            c1210d = null;
            if (c1332a.f2715c != null) {
                EnumC1334c.values()[c1332a.f2715c.f2742b].m2443a(c1332a.f2713a, c1332a.f2713a.f2078b);
            }
            c1332a.f2714b = null;
            c1332a.f2715c = null;
            c1332a.f2716d = true;
            c1332a.m2439b();
            c1332a.m2438a();
            i = 0;
            while (i < c1332a.f2713a.f2077a) {
                int i4 = i + 1;
                while (i2 < c1332a.f2713a.f2078b) {
                    if (c1332a.f2713a.m1789a(i, i2) != c1332a.f2713a.m1789a(i2, i)) {
                        c1332a.f2713a.m1793c(i2, i);
                        c1332a.f2713a.m1793c(i, i2);
                    }
                }
                i = i4;
            }
            C1202e c1202eM2454a2 = m2454a(c1332a, map);
            c1202eM2454a2.f2143h = new C1340i();
            return c1202eM2454a2;
        }
    }

    /* JADX INFO: renamed from: a */
    private C1202e m2454a(C1332a c1332a, Map<EnumC1233e, ?> map) throws C1210d, C1303h {
        int i;
        C1341j c1341jM2439b = c1332a.m2439b();
        EnumC1337f enumC1337f = c1332a.m2438a().f2741a;
        C1338g c1338gM2438a = c1332a.m2438a();
        C1341j c1341jM2439b2 = c1332a.m2439b();
        EnumC1334c enumC1334c = EnumC1334c.values()[c1338gM2438a.f2742b];
        int i2 = c1332a.f2713a.f2078b;
        enumC1334c.m2443a(c1332a.f2713a, i2);
        int iM2479a = c1341jM2439b2.m2479a();
        C1194b c1194b = new C1194b(iM2479a);
        c1194b.m1788a(0, 0, 9, 9);
        int i3 = iM2479a - 8;
        c1194b.m1788a(i3, 0, 8, 9);
        c1194b.m1788a(0, i3, 9, 8);
        int length = c1341jM2439b2.f2760b.length;
        int i4 = 0;
        while (true) {
            i = 2;
            if (i4 >= length) {
                break;
            }
            int i5 = c1341jM2439b2.f2760b[i4] - 2;
            for (int i6 = 0; i6 < length; i6++) {
                if ((i4 != 0 || (i6 != 0 && i6 != length - 1)) && (i4 != length - 1 || i6 != 0)) {
                    c1194b.m1788a(c1341jM2439b2.f2760b[i6] - 2, i5, 5, 5);
                }
            }
            i4++;
        }
        int i7 = iM2479a - 17;
        int i8 = 6;
        c1194b.m1788a(6, 9, 1, i7);
        c1194b.m1788a(9, 6, i7, 1);
        if (c1341jM2439b2.f2759a > 6) {
            int i9 = iM2479a - 11;
            c1194b.m1788a(i9, 0, 3, 6);
            c1194b.m1788a(0, i9, 6, 3);
        }
        byte[] bArr = new byte[c1341jM2439b2.f2761c];
        int i10 = i2 - 1;
        int i11 = i10;
        int i12 = 0;
        boolean z = true;
        int i13 = 0;
        int i14 = 0;
        while (i11 > 0) {
            if (i11 == i8) {
                i11--;
            }
            int i15 = i14;
            int i16 = i13;
            int i17 = i12;
            int i18 = 0;
            while (i18 < i2) {
                int i19 = z ? i10 - i18 : i18;
                int i20 = i16;
                int i21 = i17;
                int i22 = 0;
                while (i22 < i) {
                    int i23 = i11 - i22;
                    if (!c1194b.m1789a(i23, i19)) {
                        int i24 = i15 + 1;
                        int i25 = i20 << 1;
                        int i26 = c1332a.f2713a.m1789a(i23, i19) ? i25 | 1 : i25;
                        if (i24 == 8) {
                            bArr[i21] = (byte) i26;
                            i21++;
                            i15 = 0;
                            i20 = 0;
                        } else {
                            i20 = i26;
                            i15 = i24;
                        }
                    }
                    i22++;
                    i = 2;
                }
                i18++;
                i17 = i21;
                i16 = i20;
                i = 2;
            }
            z = !z;
            i11 -= 2;
            i12 = i17;
            i13 = i16;
            i14 = i15;
            i8 = 6;
            i = 2;
        }
        if (i12 != c1341jM2439b2.f2761c) {
            throw C1303h.m2217a();
        }
        if (bArr.length != c1341jM2439b.f2761c) {
            throw new IllegalArgumentException();
        }
        C1341j.b bVarM2480a = c1341jM2439b.m2480a(enumC1337f);
        C1341j.a[] aVarArr = bVarM2480a.f2766b;
        int i27 = 0;
        for (C1341j.a aVar : aVarArr) {
            i27 += aVar.f2763a;
        }
        C1333b[] c1333bArr = new C1333b[i27];
        int length2 = aVarArr.length;
        int i28 = 0;
        int i29 = 0;
        while (i28 < length2) {
            C1341j.a aVar2 = aVarArr[i28];
            int i30 = i29;
            int i31 = 0;
            while (i31 < aVar2.f2763a) {
                int i32 = aVar2.f2764b;
                c1333bArr[i30] = new C1333b(i32, new byte[bVarM2480a.f2765a + i32]);
                i31++;
                i30++;
            }
            i28++;
            i29 = i30;
        }
        int length3 = c1333bArr[0].f2718b.length;
        int length4 = c1333bArr.length - 1;
        while (length4 >= 0 && c1333bArr[length4].f2718b.length != length3) {
            length4--;
        }
        int i33 = length4 + 1;
        int i34 = length3 - bVarM2480a.f2765a;
        int i35 = 0;
        int i36 = 0;
        while (i35 < i34) {
            int i37 = i36;
            int i38 = 0;
            while (i38 < i29) {
                c1333bArr[i38].f2718b[i35] = bArr[i37];
                i38++;
                i37++;
            }
            i35++;
            i36 = i37;
        }
        int i39 = i33;
        while (i39 < i29) {
            c1333bArr[i39].f2718b[i34] = bArr[i36];
            i39++;
            i36++;
        }
        int length5 = c1333bArr[0].f2718b.length;
        while (i34 < length5) {
            int i40 = i36;
            int i41 = 0;
            while (i41 < i29) {
                c1333bArr[i41].f2718b[i41 < i33 ? i34 : i34 + 1] = bArr[i40];
                i41++;
                i40++;
            }
            i34++;
            i36 = i40;
        }
        int i42 = 0;
        for (C1333b c1333b : c1333bArr) {
            i42 += c1333b.f2717a;
        }
        byte[] bArr2 = new byte[i42];
        int length6 = c1333bArr.length;
        int i43 = 0;
        int i44 = 0;
        while (i43 < length6) {
            C1333b c1333b2 = c1333bArr[i43];
            byte[] bArr3 = c1333b2.f2718b;
            int i45 = c1333b2.f2717a;
            m2456a(bArr3, i45);
            int i46 = i44;
            int i47 = 0;
            while (i47 < i45) {
                bArr2[i46] = bArr3[i47];
                i47++;
                i46++;
            }
            i43++;
            i44 = i46;
        }
        return C1335d.m2447a(bArr2, c1341jM2439b, enumC1337f, map);
    }

    /* JADX INFO: renamed from: a */
    private void m2456a(byte[] bArr, int i) throws C1210d {
        int length = bArr.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        try {
            this.f2731a.m1819a(iArr, bArr.length - i);
            for (int i3 = 0; i3 < i; i3++) {
                bArr[i3] = (byte) iArr[i3];
            }
        } catch (C1199e unused) {
            throw C1210d.m1865a();
        }
    }

    /* JADX INFO: renamed from: a */
    private C1202e m2455a(boolean[][] zArr) throws C1210d, C1303h {
        return m2458a(C1194b.m1775a(zArr), (Map<EnumC1233e, ?>) null);
    }
}
