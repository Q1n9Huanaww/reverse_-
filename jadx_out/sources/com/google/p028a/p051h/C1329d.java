package com.google.p028a.p051h;

import com.google.p028a.C1368w;
import com.google.p028a.EnumC1138a;
import com.google.p028a.EnumC1245g;
import com.google.p028a.InterfaceC1367v;
import com.google.p028a.p035c.C1194b;
import com.google.p028a.p051h.p055c.C1322a;
import com.google.p028a.p051h.p055c.C1325d;
import com.google.p028a.p051h.p055c.C1326e;
import com.google.p028a.p051h.p055c.C1327f;
import com.google.p028a.p051h.p055c.C1328g;
import com.google.p028a.p051h.p055c.EnumC1324c;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.h.d */
/* JADX INFO: compiled from: PDF417Writer.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1329d implements InterfaceC1367v {

    /* JADX INFO: renamed from: a */
    private static final int f2708a = 30;

    /* JADX INFO: renamed from: b */
    private static final int f2709b = 2;

    @Override // com.google.p028a.InterfaceC1367v
    /* JADX INFO: renamed from: a */
    public final C1194b mo1517a(String str, EnumC1138a enumC1138a, int i, int i2, Map<EnumC1245g, ?> map) throws C1368w {
        boolean z;
        if (enumC1138a != EnumC1138a.PDF_417) {
            throw new IllegalArgumentException("Can only encode PDF_417, but got ".concat(String.valueOf(enumC1138a)));
        }
        C1326e c1326e = new C1326e();
        int i3 = 30;
        int i4 = 2;
        if (map != null) {
            if (map.containsKey(EnumC1245g.PDF417_COMPACT)) {
                c1326e.f2679b = Boolean.valueOf(map.get(EnumC1245g.PDF417_COMPACT).toString()).booleanValue();
            }
            if (map.containsKey(EnumC1245g.PDF417_COMPACTION)) {
                c1326e.f2680c = EnumC1324c.valueOf(map.get(EnumC1245g.PDF417_COMPACTION).toString());
            }
            if (map.containsKey(EnumC1245g.PDF417_DIMENSIONS)) {
                C1325d c1325d = (C1325d) map.get(EnumC1245g.PDF417_DIMENSIONS);
                int i5 = c1325d.f2669b;
                int i6 = c1325d.f2668a;
                int i7 = c1325d.f2671d;
                int i8 = c1325d.f2670c;
                c1326e.f2683f = i5;
                c1326e.f2682e = i6;
                c1326e.f2684g = i7;
                c1326e.f2685h = i8;
            }
            i3 = map.containsKey(EnumC1245g.MARGIN) ? Integer.parseInt(map.get(EnumC1245g.MARGIN).toString()) : 30;
            i4 = map.containsKey(EnumC1245g.ERROR_CORRECTION) ? Integer.parseInt(map.get(EnumC1245g.ERROR_CORRECTION).toString()) : 2;
            if (map.containsKey(EnumC1245g.CHARACTER_SET)) {
                c1326e.f2681d = Charset.forName(map.get(EnumC1245g.CHARACTER_SET).toString());
            }
        }
        c1326e.m2401a(str, i4);
        byte[][] bArrM2382a = c1326e.f2678a.m2382a(1, 4);
        if ((i2 > i) != (bArrM2382a[0].length < bArrM2382a.length)) {
            bArrM2382a = m2421a(bArrM2382a);
            z = true;
        } else {
            z = false;
        }
        int length = i / bArrM2382a[0].length;
        int length2 = i2 / bArrM2382a.length;
        if (length >= length2) {
            length = length2;
        }
        if (length > 1) {
            byte[][] bArrM2382a2 = c1326e.f2678a.m2382a(length, length << 2);
            if (z) {
                bArrM2382a2 = m2421a(bArrM2382a2);
            }
            return m2420a(bArrM2382a2, i3);
        }
        return m2420a(bArrM2382a, i3);
    }

    @Override // com.google.p028a.InterfaceC1367v
    /* JADX INFO: renamed from: a */
    public final C1194b mo1516a(String str, EnumC1138a enumC1138a, int i, int i2) throws C1368w {
        return mo1517a(str, enumC1138a, i, i2, null);
    }

    /* JADX INFO: renamed from: a */
    private static C1194b m2420a(byte[][] bArr, int i) {
        int i2 = i * 2;
        C1194b c1194b = new C1194b(bArr[0].length + i2, bArr.length + i2);
        c1194b.m1787a();
        int i3 = (c1194b.f2078b - i) - 1;
        int i4 = 0;
        while (i4 < bArr.length) {
            byte[] bArr2 = bArr[i4];
            for (int i5 = 0; i5 < bArr[0].length; i5++) {
                if (bArr2[i5] == 1) {
                    c1194b.m1790b(i5 + i, i3);
                }
            }
            i4++;
            i3--;
        }
        return c1194b;
    }

    /* JADX INFO: renamed from: a */
    private static byte[][] m2421a(byte[][] bArr) {
        byte[][] bArr2 = (byte[][]) Array.newInstance((Class<?>) byte.class, bArr[0].length, bArr.length);
        for (int i = 0; i < bArr.length; i++) {
            int length = (bArr.length - i) - 1;
            for (int i2 = 0; i2 < bArr[0].length; i2++) {
                bArr2[i2][length] = bArr[i][i2];
            }
        }
        return bArr2;
    }

    /* JADX INFO: renamed from: a */
    private static C1194b m2419a(C1326e c1326e, String str, int i, int i2, int i3, int i4) throws C1368w {
        int i5;
        boolean z;
        int iM2391a;
        int iM2402a = C1327f.m2402a(i);
        String strM2408a = C1328g.m2408a(str, c1326e.f2680c, c1326e.f2681d);
        int length = strM2408a.length();
        float f = 0.0f;
        int[] iArr = null;
        for (int i6 = c1326e.f2682e; i6 <= c1326e.f2683f && (iM2391a = C1326e.m2391a(length, iM2402a, i6)) >= c1326e.f2685h; i6++) {
            if (iM2391a <= c1326e.f2684g) {
                float f2 = (((i6 * 17) + 69) * 0.357f) / (iM2391a * 2.0f);
                if (iArr == null || Math.abs(f2 - 3.0f) <= Math.abs(f - 3.0f)) {
                    iArr = new int[]{i6, iM2391a};
                    f = f2;
                }
            }
        }
        if (iArr != null || C1326e.m2391a(length, iM2402a, c1326e.f2682e) >= c1326e.f2685h) {
            i5 = 1;
        } else {
            i5 = 1;
            iArr = new int[]{c1326e.f2682e, c1326e.f2685h};
        }
        if (iArr != null) {
            int i7 = iArr[0];
            int i8 = iArr[i5];
            int iM2392a = C1326e.m2392a(length, iM2402a, i7, i8);
            if (iM2402a + length + i5 > 929) {
                throw new C1368w("Encoded message contains too many code words, message too big (" + str.length() + " bytes)");
            }
            int i9 = length + iM2392a + 1;
            StringBuilder sb = new StringBuilder(i9);
            sb.append((char) i9);
            sb.append(strM2408a);
            for (int i10 = 0; i10 < iM2392a; i10++) {
                sb.append((char) 900);
            }
            String string = sb.toString();
            String strM2403a = C1327f.m2403a(string, i);
            c1326e.f2678a = new C1322a(i8, i7);
            c1326e.m2400a(string + strM2403a, i7, i8, i, c1326e.f2678a);
            byte[][] bArrM2382a = c1326e.f2678a.m2382a(1, 4);
            if ((i3 > i2) != (bArrM2382a[0].length < bArrM2382a.length)) {
                bArrM2382a = m2421a(bArrM2382a);
                z = true;
            } else {
                z = false;
            }
            int length2 = i2 / bArrM2382a[0].length;
            int length3 = i3 / bArrM2382a.length;
            if (length2 >= length3) {
                length2 = length3;
            }
            if (length2 > 1) {
                byte[][] bArrM2382a2 = c1326e.f2678a.m2382a(length2, length2 << 2);
                if (z) {
                    bArrM2382a2 = m2421a(bArrM2382a2);
                }
                return m2420a(bArrM2382a2, i4);
            }
            return m2420a(bArrM2382a, i4);
        }
        throw new C1368w("Unable to fit message in columns");
    }
}
