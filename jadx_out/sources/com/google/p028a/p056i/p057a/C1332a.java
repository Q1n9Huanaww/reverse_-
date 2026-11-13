package com.google.p028a.p056i.p057a;

import com.google.p028a.C1303h;
import com.google.p028a.p035c.C1194b;

/* JADX INFO: renamed from: com.google.a.i.a.a */
/* JADX INFO: compiled from: BitMatrixParser.java */
/* JADX INFO: loaded from: classes.dex */
final class C1332a {

    /* JADX INFO: renamed from: a */
    final C1194b f2713a;

    /* JADX INFO: renamed from: b */
    C1341j f2714b;

    /* JADX INFO: renamed from: c */
    C1338g f2715c;

    /* JADX INFO: renamed from: d */
    boolean f2716d;

    C1332a(C1194b c1194b) throws C1303h {
        int i = c1194b.f2078b;
        if (i < 21 || (i & 3) != 1) {
            throw C1303h.m2217a();
        }
        this.f2713a = c1194b;
    }

    /* JADX INFO: renamed from: a */
    final C1338g m2438a() throws C1303h {
        if (this.f2715c != null) {
            return this.f2715c;
        }
        int iM2433a = 0;
        int iM2433a2 = 0;
        for (int i = 0; i < 6; i++) {
            iM2433a2 = m2433a(i, 8, iM2433a2);
        }
        int iM2433a3 = m2433a(8, 7, m2433a(8, 8, m2433a(7, 8, iM2433a2)));
        for (int i2 = 5; i2 >= 0; i2--) {
            iM2433a3 = m2433a(8, i2, iM2433a3);
        }
        int i3 = this.f2713a.f2078b;
        int i4 = i3 - 7;
        for (int i5 = i3 - 1; i5 >= i4; i5--) {
            iM2433a = m2433a(8, i5, iM2433a);
        }
        for (int i6 = i3 - 8; i6 < i3; i6++) {
            iM2433a = m2433a(i6, 8, iM2433a);
        }
        this.f2715c = C1338g.m2464b(iM2433a3, iM2433a);
        if (this.f2715c != null) {
            return this.f2715c;
        }
        throw C1303h.m2217a();
    }

    /* JADX INFO: renamed from: b */
    final C1341j m2439b() throws C1303h {
        if (this.f2714b != null) {
            return this.f2714b;
        }
        int i = this.f2713a.f2078b;
        int i2 = (i - 17) / 4;
        if (i2 <= 6) {
            return C1341j.m2473b(i2);
        }
        int i3 = i - 11;
        int iM2433a = 0;
        int iM2433a2 = 0;
        for (int i4 = 5; i4 >= 0; i4--) {
            for (int i5 = i - 9; i5 >= i3; i5--) {
                iM2433a2 = m2433a(i5, i4, iM2433a2);
            }
        }
        C1341j c1341jM2474c = C1341j.m2474c(iM2433a2);
        if (c1341jM2474c != null && c1341jM2474c.m2479a() == i) {
            this.f2714b = c1341jM2474c;
            return c1341jM2474c;
        }
        for (int i6 = 5; i6 >= 0; i6--) {
            for (int i7 = i - 9; i7 >= i3; i7--) {
                iM2433a = m2433a(i6, i7, iM2433a);
            }
        }
        C1341j c1341jM2474c2 = C1341j.m2474c(iM2433a);
        if (c1341jM2474c2 != null && c1341jM2474c2.m2479a() == i) {
            this.f2714b = c1341jM2474c2;
            return c1341jM2474c2;
        }
        throw C1303h.m2217a();
    }

    /* JADX INFO: renamed from: a */
    private int m2433a(int i, int i2, int i3) {
        return this.f2716d ? this.f2713a.m1789a(i2, i) : this.f2713a.m1789a(i, i2) ? (i3 << 1) | 1 : i3 << 1;
    }

    /* JADX INFO: renamed from: c */
    private byte[] m2434c() throws C1303h {
        int i;
        C1338g c1338gM2438a = m2438a();
        C1341j c1341jM2439b = m2439b();
        EnumC1334c enumC1334c = EnumC1334c.values()[c1338gM2438a.f2742b];
        int i2 = this.f2713a.f2078b;
        enumC1334c.m2443a(this.f2713a, i2);
        int iM2479a = c1341jM2439b.m2479a();
        C1194b c1194b = new C1194b(iM2479a);
        c1194b.m1788a(0, 0, 9, 9);
        int i3 = iM2479a - 8;
        c1194b.m1788a(i3, 0, 8, 9);
        c1194b.m1788a(0, i3, 9, 8);
        int length = c1341jM2439b.f2760b.length;
        int i4 = 0;
        while (true) {
            i = 2;
            if (i4 >= length) {
                break;
            }
            int i5 = c1341jM2439b.f2760b[i4] - 2;
            for (int i6 = 0; i6 < length; i6++) {
                if ((i4 != 0 || (i6 != 0 && i6 != length - 1)) && (i4 != length - 1 || i6 != 0)) {
                    c1194b.m1788a(c1341jM2439b.f2760b[i6] - 2, i5, 5, 5);
                }
            }
            i4++;
        }
        int i7 = iM2479a - 17;
        int i8 = 6;
        c1194b.m1788a(6, 9, 1, i7);
        c1194b.m1788a(9, 6, i7, 1);
        if (c1341jM2439b.f2759a > 6) {
            int i9 = iM2479a - 11;
            c1194b.m1788a(i9, 0, 3, 6);
            c1194b.m1788a(0, i9, 6, 3);
        }
        byte[] bArr = new byte[c1341jM2439b.f2761c];
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
                        i15++;
                        int i24 = i20 << 1;
                        int i25 = this.f2713a.m1789a(i23, i19) ? i24 | 1 : i24;
                        if (i15 == 8) {
                            bArr[i21] = (byte) i25;
                            i21++;
                            i15 = 0;
                            i20 = 0;
                        } else {
                            i20 = i25;
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
        if (i12 != c1341jM2439b.f2761c) {
            throw C1303h.m2217a();
        }
        return bArr;
    }

    /* JADX INFO: renamed from: d */
    private void m2435d() {
        if (this.f2715c == null) {
            return;
        }
        EnumC1334c.values()[this.f2715c.f2742b].m2443a(this.f2713a, this.f2713a.f2078b);
    }

    /* JADX INFO: renamed from: e */
    private void m2436e() {
        this.f2714b = null;
        this.f2715c = null;
        this.f2716d = true;
    }

    /* JADX INFO: renamed from: f */
    private void m2437f() {
        int i = 0;
        while (i < this.f2713a.f2077a) {
            int i2 = i + 1;
            for (int i3 = i2; i3 < this.f2713a.f2078b; i3++) {
                if (this.f2713a.m1789a(i, i3) != this.f2713a.m1789a(i3, i)) {
                    this.f2713a.m1793c(i3, i);
                    this.f2713a.m1793c(i, i3);
                }
            }
            i = i2;
        }
    }
}
