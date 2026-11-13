package com.google.p028a.p056i.p059c;

import android.support.v7.widget.ActivityChooserView;
import com.google.p028a.C1368w;
import com.google.p028a.EnumC1245g;
import com.google.p028a.p035c.C1190a;
import com.google.p028a.p035c.EnumC1201d;
import com.google.p028a.p035c.p037b.C1195a;
import com.google.p028a.p035c.p037b.C1198d;
import com.google.p028a.p056i.p057a.C1341j;
import com.google.p028a.p056i.p057a.EnumC1337f;
import com.google.p028a.p056i.p057a.EnumC1339h;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.i.c.c */
/* JADX INFO: compiled from: Encoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1351c {

    /* JADX INFO: renamed from: a */
    static final String f2800a = "ISO-8859-1";

    /* JADX INFO: renamed from: b */
    private static final int[] f2801b = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};

    private C1351c() {
    }

    /* JADX INFO: renamed from: a */
    private static C1354f m2553a(String str, EnumC1337f enumC1337f) throws C1368w {
        return m2554a(str, enumC1337f, (Map<EnumC1245g, ?>) null);
    }

    /* JADX INFO: renamed from: a */
    public static C1354f m2554a(String str, EnumC1337f enumC1337f, Map<EnumC1245g, ?> map) throws C1368w {
        C1341j c1341jM2551a;
        EnumC1201d enumC1201dM1828a;
        int i = 0;
        boolean z = map != null && map.containsKey(EnumC1245g.CHARACTER_SET);
        String string = z ? map.get(EnumC1245g.CHARACTER_SET).toString() : "ISO-8859-1";
        EnumC1339h enumC1339hM2550a = m2550a(str, string);
        C1190a c1190a = new C1190a();
        if (enumC1339hM2550a == EnumC1339h.BYTE && z && (enumC1201dM1828a = EnumC1201d.m1828a(string)) != null) {
            c1190a.m1759b(EnumC1339h.ECI.f2754k, 4);
            c1190a.m1759b(enumC1201dM1828a.f2134B[0], 8);
        }
        if ((map != null && map.containsKey(EnumC1245g.GS1_FORMAT)) && Boolean.valueOf(map.get(EnumC1245g.GS1_FORMAT).toString()).booleanValue()) {
            m2559a(EnumC1339h.FNC1_FIRST_POSITION, c1190a);
        }
        m2559a(enumC1339hM2550a, c1190a);
        C1190a c1190a2 = new C1190a();
        switch (enumC1339hM2550a) {
            case NUMERIC:
                int length = str.length();
                while (i < length) {
                    int iCharAt = str.charAt(i) - '0';
                    int i2 = i + 2;
                    if (i2 < length) {
                        c1190a2.m1759b((iCharAt * 100) + ((str.charAt(i + 1) - '0') * 10) + (str.charAt(i2) - '0'), 10);
                        i += 3;
                    } else {
                        i++;
                        if (i < length) {
                            c1190a2.m1759b((iCharAt * 10) + (str.charAt(i) - '0'), 7);
                            i = i2;
                        } else {
                            c1190a2.m1759b(iCharAt, 4);
                        }
                    }
                }
                break;
            case ALPHANUMERIC:
                int length2 = str.length();
                while (i < length2) {
                    int iM2544a = m2544a(str.charAt(i));
                    if (iM2544a == -1) {
                        throw new C1368w();
                    }
                    int i3 = i + 1;
                    if (i3 < length2) {
                        int iM2544a2 = m2544a(str.charAt(i3));
                        if (iM2544a2 == -1) {
                            throw new C1368w();
                        }
                        c1190a2.m1759b((iM2544a * 45) + iM2544a2, 11);
                        i += 2;
                    } else {
                        c1190a2.m1759b(iM2544a, 6);
                        i = i3;
                    }
                }
                break;
            case BYTE:
                try {
                    byte[] bytes = str.getBytes(string);
                    int length3 = bytes.length;
                    while (i < length3) {
                        c1190a2.m1759b(bytes[i], 8);
                        i++;
                    }
                } catch (UnsupportedEncodingException e) {
                    throw new C1368w(e);
                }
                break;
            case KANJI:
                try {
                    byte[] bytes2 = str.getBytes("Shift_JIS");
                    int length4 = bytes2.length;
                    while (i < length4) {
                        int i4 = ((bytes2[i] & 255) << 8) | (bytes2[i + 1] & 255);
                        int i5 = (i4 < 33088 || i4 > 40956) ? (i4 < 57408 || i4 > 60351) ? -1 : i4 - 49472 : i4 - 33088;
                        if (i5 == -1) {
                            throw new C1368w("Invalid byte sequence");
                        }
                        c1190a2.m1759b(((i5 >> 8) * 192) + (i5 & 255), 13);
                        i += 2;
                    }
                } catch (UnsupportedEncodingException e2) {
                    throw new C1368w(e2);
                }
                break;
            default:
                throw new C1368w("Invalid mode: ".concat(String.valueOf(enumC1339hM2550a)));
        }
        if (map != null && map.containsKey(EnumC1245g.QR_VERSION)) {
            c1341jM2551a = C1341j.m2473b(Integer.parseInt(map.get(EnumC1245g.QR_VERSION).toString()));
            if (!m2564a(m2546a(enumC1339hM2550a, c1190a, c1190a2, c1341jM2551a), c1341jM2551a, enumC1337f)) {
                throw new C1368w("Data too big for requested version");
            }
        } else {
            c1341jM2551a = m2551a(m2546a(enumC1339hM2550a, c1190a, c1190a2, m2551a(m2546a(enumC1339hM2550a, c1190a, c1190a2, C1341j.m2473b(1)), enumC1337f)), enumC1337f);
        }
        C1190a c1190a3 = new C1190a();
        c1190a3.m1753a(c1190a);
        int iM1751a = enumC1339hM2550a == EnumC1339h.BYTE ? c1190a2.m1751a() : str.length();
        int iM2468a = enumC1339hM2550a.m2468a(c1341jM2551a);
        int i6 = 1 << iM2468a;
        if (iM1751a >= i6) {
            throw new C1368w(iM1751a + " is bigger than " + (i6 - 1));
        }
        c1190a3.m1759b(iM1751a, iM2468a);
        c1190a3.m1753a(c1190a2);
        C1341j.b bVarM2480a = c1341jM2551a.m2480a(enumC1337f);
        int iM2486b = c1341jM2551a.f2761c - bVarM2480a.m2486b();
        m2556a(iM2486b, c1190a3);
        C1190a c1190aM2548a = m2548a(c1190a3, c1341jM2551a.f2761c, iM2486b, bVarM2480a.m2485a());
        C1354f c1354f = new C1354f();
        c1354f.f2816c = enumC1337f;
        c1354f.f2815b = enumC1339hM2550a;
        c1354f.f2817d = c1341jM2551a;
        int iM2479a = c1341jM2551a.m2479a();
        C1350b c1350b = new C1350b(iM2479a, iM2479a);
        int iM2545a = m2545a(c1190aM2548a, enumC1337f, c1341jM2551a, c1350b);
        c1354f.f2818e = iM2545a;
        C1353e.m2580a(c1190aM2548a, enumC1337f, c1341jM2551a, iM2545a, c1350b);
        c1354f.f2819f = c1350b;
        return c1354f;
    }

    /* JADX INFO: renamed from: a */
    private static C1341j m2552a(EnumC1337f enumC1337f, EnumC1339h enumC1339h, C1190a c1190a, C1190a c1190a2) throws C1368w {
        return m2551a(m2546a(enumC1339h, c1190a, c1190a2, m2551a(m2546a(enumC1339h, c1190a, c1190a2, C1341j.m2473b(1)), enumC1337f)), enumC1337f);
    }

    /* JADX INFO: renamed from: a */
    private static int m2544a(int i) {
        if (i < f2801b.length) {
            return f2801b[i];
        }
        return -1;
    }

    /* JADX INFO: renamed from: a */
    private static EnumC1339h m2549a(String str) {
        return m2550a(str, (String) null);
    }

    /* JADX INFO: renamed from: a */
    private static EnumC1339h m2550a(String str, String str2) {
        if ("Shift_JIS".equals(str2) && m2567b(str)) {
            return EnumC1339h.KANJI;
        }
        boolean z = false;
        boolean z2 = false;
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt >= '0' && cCharAt <= '9') {
                z2 = true;
            } else {
                if (m2544a(cCharAt) == -1) {
                    return EnumC1339h.BYTE;
                }
                z = true;
            }
        }
        if (z) {
            return EnumC1339h.ALPHANUMERIC;
        }
        if (z2) {
            return EnumC1339h.NUMERIC;
        }
        return EnumC1339h.BYTE;
    }

    /* JADX INFO: renamed from: b */
    private static boolean m2567b(String str) {
        try {
            byte[] bytes = str.getBytes("Shift_JIS");
            int length = bytes.length;
            if (length % 2 != 0) {
                return false;
            }
            for (int i = 0; i < length; i += 2) {
                int i2 = bytes[i] & 255;
                if ((i2 < 129 || i2 > 159) && (i2 < 224 || i2 > 235)) {
                    return false;
                }
            }
            return true;
        } catch (UnsupportedEncodingException unused) {
            return false;
        }
    }

    /* JADX INFO: renamed from: a */
    private static int m2545a(C1190a c1190a, EnumC1337f enumC1337f, C1341j c1341j, C1350b c1350b) throws C1368w {
        boolean z = false;
        int i = -1;
        int i2 = 0;
        int i3 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        while (i2 < 8) {
            C1353e.m2580a(c1190a, enumC1337f, c1341j, i2, c1350b);
            int iM2569a = C1352d.m2569a(c1350b, true) + C1352d.m2569a(c1350b, z);
            byte[][] bArr = c1350b.f2797a;
            int i4 = c1350b.f2798b;
            int i5 = c1350b.f2799c;
            int i6 = 0;
            int i7 = 0;
            while (i6 < i5 - 1) {
                byte[] bArr2 = bArr[i6];
                int i8 = i7;
                int i9 = 0;
                while (i9 < i4 - 1) {
                    byte b = bArr2[i9];
                    int i10 = i9 + 1;
                    if (b == bArr2[i10]) {
                        int i11 = i6 + 1;
                        if (b == bArr[i11][i9] && b == bArr[i11][i10]) {
                            i8++;
                        }
                    }
                    i9 = i10;
                }
                i6++;
                i7 = i8;
            }
            int i12 = iM2569a + (i7 * 3);
            byte[][] bArr3 = c1350b.f2797a;
            int i13 = c1350b.f2798b;
            int i14 = c1350b.f2799c;
            int i15 = 0;
            int i16 = 0;
            while (i15 < i14) {
                int i17 = i16;
                for (int i18 = 0; i18 < i13; i18++) {
                    byte[] bArr4 = bArr3[i15];
                    int i19 = i18 + 6;
                    if (i19 < i13 && bArr4[i18] == 1 && bArr4[i18 + 1] == 0 && bArr4[i18 + 2] == 1 && bArr4[i18 + 3] == 1 && bArr4[i18 + 4] == 1 && bArr4[i18 + 5] == 0 && bArr4[i19] == 1 && (C1352d.m2571a(bArr4, i18 - 4, i18) || C1352d.m2571a(bArr4, i18 + 7, i18 + 11))) {
                        i17++;
                    }
                    int i20 = i15 + 6;
                    if (i20 < i14 && bArr3[i15][i18] == 1 && bArr3[i15 + 1][i18] == 0 && bArr3[i15 + 2][i18] == 1 && bArr3[i15 + 3][i18] == 1 && bArr3[i15 + 4][i18] == 1 && bArr3[i15 + 5][i18] == 0 && bArr3[i20][i18] == 1 && (C1352d.m2572a(bArr3, i18, i15 - 4, i15) || C1352d.m2572a(bArr3, i18, i15 + 7, i15 + 11))) {
                        i17++;
                    }
                }
                i15++;
                i16 = i17;
            }
            int i21 = i12 + (i16 * 40);
            byte[][] bArr5 = c1350b.f2797a;
            int i22 = c1350b.f2798b;
            int i23 = c1350b.f2799c;
            int i24 = 0;
            int i25 = 0;
            while (i24 < i23) {
                byte[] bArr6 = bArr5[i24];
                int i26 = i25;
                for (int i27 = 0; i27 < i22; i27++) {
                    if (bArr6[i27] == 1) {
                        i26++;
                    }
                }
                i24++;
                i25 = i26;
            }
            int i28 = c1350b.f2799c * c1350b.f2798b;
            int iAbs = i21 + (((Math.abs((i25 << 1) - i28) * 10) / i28) * 10);
            if (iAbs < i3) {
                i = i2;
                i3 = iAbs;
            }
            i2++;
            z = false;
        }
        return i;
    }

    /* JADX INFO: renamed from: a */
    private static C1341j m2551a(int i, EnumC1337f enumC1337f) throws C1368w {
        for (int i2 = 1; i2 <= 40; i2++) {
            C1341j c1341jM2473b = C1341j.m2473b(i2);
            if (m2564a(i, c1341jM2473b, enumC1337f)) {
                return c1341jM2473b;
            }
        }
        throw new C1368w("Data too big");
    }

    /* JADX INFO: renamed from: a */
    private static void m2555a(int i, int i2, int i3, int i4, int[] iArr, int[] iArr2) throws C1368w {
        if (i4 >= i3) {
            throw new C1368w("Block ID too large");
        }
        int i5 = i % i3;
        int i6 = i3 - i5;
        int i7 = i / i3;
        int i8 = i7 + 1;
        int i9 = i2 / i3;
        int i10 = i9 + 1;
        int i11 = i7 - i9;
        int i12 = i8 - i10;
        if (i11 != i12) {
            throw new C1368w("EC bytes mismatch");
        }
        if (i3 != i6 + i5) {
            throw new C1368w("RS blocks mismatch");
        }
        if (i != ((i9 + i11) * i6) + ((i10 + i12) * i5)) {
            throw new C1368w("Total bytes mismatch");
        }
        if (i4 < i6) {
            iArr[0] = i9;
            iArr2[0] = i11;
        } else {
            iArr[0] = i10;
            iArr2[0] = i12;
        }
    }

    /* JADX INFO: renamed from: a */
    private static C1190a m2548a(C1190a c1190a, int i, int i2, int i3) throws C1368w {
        char c;
        if (c1190a.m1751a() != i2) {
            throw new C1368w("Number of bits and data bytes does not match");
        }
        ArrayList arrayList = new ArrayList(i3);
        int i4 = 0;
        int iMax = 0;
        int iMax2 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            int[] iArr = new int[1];
            int[] iArr2 = new int[1];
            if (i5 >= i3) {
                throw new C1368w("Block ID too large");
            }
            int i6 = i % i3;
            int i7 = i3 - i6;
            int i8 = i / i3;
            int i9 = i8 + 1;
            int i10 = i2 / i3;
            int i11 = i10 + 1;
            int i12 = i8 - i10;
            int i13 = i9 - i11;
            if (i12 != i13) {
                throw new C1368w("EC bytes mismatch");
            }
            if (i3 != i7 + i6) {
                throw new C1368w("RS blocks mismatch");
            }
            if (i == ((i10 + i12) * i7) + ((i11 + i13) * i6)) {
                if (i5 < i7) {
                    c = 0;
                    iArr[0] = i10;
                    iArr2[0] = i12;
                } else {
                    c = 0;
                    iArr[0] = i11;
                    iArr2[0] = i13;
                }
                int i14 = iArr[c];
                byte[] bArr = new byte[i14];
                c1190a.m1752a(i4 << 3, bArr, i14);
                byte[] bArrM2565a = m2565a(bArr, iArr2[c]);
                arrayList.add(new C1349a(bArr, bArrM2565a));
                iMax = Math.max(iMax, i14);
                iMax2 = Math.max(iMax2, bArrM2565a.length);
                i4 += iArr[c];
            } else {
                throw new C1368w("Total bytes mismatch");
            }
        }
        if (i2 != i4) {
            throw new C1368w("Data bytes does not match offset");
        }
        C1190a c1190a2 = new C1190a();
        for (int i15 = 0; i15 < iMax; i15++) {
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                byte[] bArr2 = ((C1349a) it2.next()).f2795a;
                if (i15 < bArr2.length) {
                    c1190a2.m1759b(bArr2[i15], 8);
                }
            }
        }
        for (int i16 = 0; i16 < iMax2; i16++) {
            Iterator it3 = arrayList.iterator();
            while (it3.hasNext()) {
                byte[] bArr3 = ((C1349a) it3.next()).f2796b;
                if (i16 < bArr3.length) {
                    c1190a2.m1759b(bArr3[i16], 8);
                }
            }
        }
        if (i == c1190a2.m1751a()) {
            return c1190a2;
        }
        throw new C1368w("Interleaving error: " + i + " and " + c1190a2.m1751a() + " differ.");
    }

    /* JADX INFO: renamed from: a */
    private static byte[] m2565a(byte[] bArr, int i) {
        int length = bArr.length;
        int[] iArr = new int[length + i];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        new C1198d(C1195a.f2085e).m1821a(iArr, i);
        byte[] bArr2 = new byte[i];
        for (int i3 = 0; i3 < i; i3++) {
            bArr2[i3] = (byte) iArr[length + i3];
        }
        return bArr2;
    }

    /* JADX INFO: renamed from: a */
    private static void m2557a(int i, C1341j c1341j, EnumC1339h enumC1339h, C1190a c1190a) throws C1368w {
        int iM2468a = enumC1339h.m2468a(c1341j);
        int i2 = 1 << iM2468a;
        if (i >= i2) {
            throw new C1368w(i + " is bigger than " + (i2 - 1));
        }
        c1190a.m1759b(i, iM2468a);
    }

    /* JADX INFO: renamed from: a */
    private static void m2563a(String str, EnumC1339h enumC1339h, C1190a c1190a, String str2) throws C1368w {
        int i;
        int i2 = 0;
        switch (enumC1339h) {
            case NUMERIC:
                int length = str.length();
                while (i2 < length) {
                    int iCharAt = str.charAt(i2) - '0';
                    int i3 = i2 + 2;
                    if (i3 < length) {
                        c1190a.m1759b((iCharAt * 100) + ((str.charAt(i2 + 1) - '0') * 10) + (str.charAt(i3) - '0'), 10);
                        i2 += 3;
                    } else {
                        i2++;
                        if (i2 < length) {
                            c1190a.m1759b((iCharAt * 10) + (str.charAt(i2) - '0'), 7);
                            i2 = i3;
                        } else {
                            c1190a.m1759b(iCharAt, 4);
                        }
                    }
                }
                return;
            case ALPHANUMERIC:
                int length2 = str.length();
                while (i2 < length2) {
                    int iM2544a = m2544a(str.charAt(i2));
                    if (iM2544a == -1) {
                        throw new C1368w();
                    }
                    int i4 = i2 + 1;
                    if (i4 < length2) {
                        int iM2544a2 = m2544a(str.charAt(i4));
                        if (iM2544a2 == -1) {
                            throw new C1368w();
                        }
                        c1190a.m1759b((iM2544a * 45) + iM2544a2, 11);
                        i2 += 2;
                    } else {
                        c1190a.m1759b(iM2544a, 6);
                        i2 = i4;
                    }
                }
                return;
            case BYTE:
                try {
                    byte[] bytes = str.getBytes(str2);
                    int length3 = bytes.length;
                    while (i2 < length3) {
                        c1190a.m1759b(bytes[i2], 8);
                        i2++;
                    }
                    return;
                } catch (UnsupportedEncodingException e) {
                    throw new C1368w(e);
                }
            case KANJI:
                try {
                    byte[] bytes2 = str.getBytes("Shift_JIS");
                    int length4 = bytes2.length;
                    while (i2 < length4) {
                        int i5 = ((bytes2[i2] & 255) << 8) | (bytes2[i2 + 1] & 255);
                        if (i5 < 33088 || i5 > 40956) {
                            i = (i5 < 57408 || i5 > 60351) ? -1 : i5 - 49472;
                        } else {
                            i = i5 - 33088;
                        }
                        if (i == -1) {
                            throw new C1368w("Invalid byte sequence");
                        }
                        c1190a.m1759b(((i >> 8) * 192) + (i & 255), 13);
                        i2 += 2;
                    }
                    return;
                } catch (UnsupportedEncodingException e2) {
                    throw new C1368w(e2);
                }
            default:
                throw new C1368w("Invalid mode: ".concat(String.valueOf(enumC1339h)));
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m2560a(CharSequence charSequence, C1190a c1190a) {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            int iCharAt = charSequence.charAt(i) - '0';
            int i2 = i + 2;
            if (i2 < length) {
                c1190a.m1759b((iCharAt * 100) + ((charSequence.charAt(i + 1) - '0') * 10) + (charSequence.charAt(i2) - '0'), 10);
                i += 3;
            } else {
                i++;
                if (i < length) {
                    c1190a.m1759b((iCharAt * 10) + (charSequence.charAt(i) - '0'), 7);
                    i = i2;
                } else {
                    c1190a.m1759b(iCharAt, 4);
                }
            }
        }
    }

    /* JADX INFO: renamed from: b */
    private static void m2566b(CharSequence charSequence, C1190a c1190a) throws C1368w {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            int iM2544a = m2544a(charSequence.charAt(i));
            if (iM2544a == -1) {
                throw new C1368w();
            }
            int i2 = i + 1;
            if (i2 < length) {
                int iM2544a2 = m2544a(charSequence.charAt(i2));
                if (iM2544a2 == -1) {
                    throw new C1368w();
                }
                c1190a.m1759b((iM2544a * 45) + iM2544a2, 11);
                i += 2;
            } else {
                c1190a.m1759b(iM2544a, 6);
                i = i2;
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m2562a(String str, C1190a c1190a, String str2) throws C1368w {
        try {
            for (byte b : str.getBytes(str2)) {
                c1190a.m1759b(b, 8);
            }
        } catch (UnsupportedEncodingException e) {
            throw new C1368w(e);
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m2561a(String str, C1190a c1190a) throws C1368w {
        try {
            byte[] bytes = str.getBytes("Shift_JIS");
            int length = bytes.length;
            for (int i = 0; i < length; i += 2) {
                int i2 = ((bytes[i] & 255) << 8) | (bytes[i + 1] & 255);
                int i3 = (i2 < 33088 || i2 > 40956) ? (i2 < 57408 || i2 > 60351) ? -1 : i2 - 49472 : i2 - 33088;
                if (i3 == -1) {
                    throw new C1368w("Invalid byte sequence");
                }
                c1190a.m1759b(((i3 >> 8) * 192) + (i3 & 255), 13);
            }
        } catch (UnsupportedEncodingException e) {
            throw new C1368w(e);
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m2558a(EnumC1201d enumC1201d, C1190a c1190a) {
        c1190a.m1759b(EnumC1339h.ECI.f2754k, 4);
        c1190a.m1759b(enumC1201d.f2134B[0], 8);
    }

    /* JADX INFO: renamed from: a */
    private static int m2547a(C1350b c1350b) {
        int iM2569a = C1352d.m2569a(c1350b, true) + C1352d.m2569a(c1350b, false);
        byte[][] bArr = c1350b.f2797a;
        int i = c1350b.f2798b;
        int i2 = c1350b.f2799c;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2 - 1) {
            byte[] bArr2 = bArr[i3];
            int i5 = i4;
            int i6 = 0;
            while (i6 < i - 1) {
                byte b = bArr2[i6];
                int i7 = i6 + 1;
                if (b == bArr2[i7]) {
                    int i8 = i3 + 1;
                    if (b == bArr[i8][i6] && b == bArr[i8][i7]) {
                        i5++;
                    }
                }
                i6 = i7;
            }
            i3++;
            i4 = i5;
        }
        int i9 = iM2569a + (i4 * 3);
        byte[][] bArr3 = c1350b.f2797a;
        int i10 = c1350b.f2798b;
        int i11 = c1350b.f2799c;
        int i12 = 0;
        int i13 = 0;
        while (i12 < i11) {
            int i14 = i13;
            for (int i15 = 0; i15 < i10; i15++) {
                byte[] bArr4 = bArr3[i12];
                int i16 = i15 + 6;
                if (i16 < i10 && bArr4[i15] == 1 && bArr4[i15 + 1] == 0 && bArr4[i15 + 2] == 1 && bArr4[i15 + 3] == 1 && bArr4[i15 + 4] == 1 && bArr4[i15 + 5] == 0 && bArr4[i16] == 1 && (C1352d.m2571a(bArr4, i15 - 4, i15) || C1352d.m2571a(bArr4, i15 + 7, i15 + 11))) {
                    i14++;
                }
                int i17 = i12 + 6;
                if (i17 < i11 && bArr3[i12][i15] == 1 && bArr3[i12 + 1][i15] == 0 && bArr3[i12 + 2][i15] == 1 && bArr3[i12 + 3][i15] == 1 && bArr3[i12 + 4][i15] == 1 && bArr3[i12 + 5][i15] == 0 && bArr3[i17][i15] == 1 && (C1352d.m2572a(bArr3, i15, i12 - 4, i12) || C1352d.m2572a(bArr3, i15, i12 + 7, i12 + 11))) {
                    i14++;
                }
            }
            i12++;
            i13 = i14;
        }
        int i18 = i9 + (i13 * 40);
        byte[][] bArr5 = c1350b.f2797a;
        int i19 = c1350b.f2798b;
        int i20 = c1350b.f2799c;
        int i21 = 0;
        int i22 = 0;
        while (i21 < i20) {
            byte[] bArr6 = bArr5[i21];
            int i23 = i22;
            for (int i24 = 0; i24 < i19; i24++) {
                if (bArr6[i24] == 1) {
                    i23++;
                }
            }
            i21++;
            i22 = i23;
        }
        int i25 = c1350b.f2799c * c1350b.f2798b;
        return i18 + (((Math.abs((i22 << 1) - i25) * 10) / i25) * 10);
    }

    /* JADX INFO: renamed from: a */
    private static int m2546a(EnumC1339h enumC1339h, C1190a c1190a, C1190a c1190a2, C1341j c1341j) {
        return c1190a.f2065b + enumC1339h.m2468a(c1341j) + c1190a2.f2065b;
    }

    /* JADX INFO: renamed from: a */
    private static boolean m2564a(int i, C1341j c1341j, EnumC1337f enumC1337f) {
        return c1341j.f2761c - c1341j.m2480a(enumC1337f).m2486b() >= (i + 7) / 8;
    }

    /* JADX INFO: renamed from: a */
    private static void m2556a(int i, C1190a c1190a) throws C1368w {
        int i2 = i << 3;
        if (c1190a.f2065b > i2) {
            throw new C1368w("data bits cannot fit in the QR Code" + c1190a.f2065b + " > " + i2);
        }
        for (int i3 = 0; i3 < 4 && c1190a.f2065b < i2; i3++) {
            c1190a.m1754a(false);
        }
        int i4 = c1190a.f2065b & 7;
        if (i4 > 0) {
            while (i4 < 8) {
                c1190a.m1754a(false);
                i4++;
            }
        }
        int iM1751a = i - c1190a.m1751a();
        for (int i5 = 0; i5 < iM1751a; i5++) {
            c1190a.m1759b((i5 & 1) == 0 ? 236 : 17, 8);
        }
        if (c1190a.f2065b != i2) {
            throw new C1368w("Bits size does not equal capacity");
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m2559a(EnumC1339h enumC1339h, C1190a c1190a) {
        c1190a.m1759b(enumC1339h.f2754k, 4);
    }
}
