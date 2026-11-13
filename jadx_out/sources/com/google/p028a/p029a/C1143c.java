package com.google.p028a.p029a;

import com.google.p028a.EnumC1138a;
import com.google.p028a.EnumC1245g;
import com.google.p028a.InterfaceC1367v;
import com.google.p028a.p029a.p032c.C1144a;
import com.google.p028a.p029a.p032c.C1146c;
import com.google.p028a.p035c.C1194b;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.a.c */
/* JADX INFO: compiled from: AztecWriter.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1143c implements InterfaceC1367v {
    @Override // com.google.p028a.InterfaceC1367v
    /* JADX INFO: renamed from: a */
    public final C1194b mo1516a(String str, EnumC1138a enumC1138a, int i, int i2) {
        return mo1517a(str, enumC1138a, i, i2, null);
    }

    @Override // com.google.p028a.InterfaceC1367v
    /* JADX INFO: renamed from: a */
    public final C1194b mo1517a(String str, EnumC1138a enumC1138a, int i, int i2, Map<EnumC1245g, ?> map) {
        Charset charsetForName = StandardCharsets.ISO_8859_1;
        int i3 = 33;
        int i4 = 0;
        if (map != null) {
            if (map.containsKey(EnumC1245g.CHARACTER_SET)) {
                charsetForName = Charset.forName(map.get(EnumC1245g.CHARACTER_SET).toString());
            }
            i3 = map.containsKey(EnumC1245g.ERROR_CORRECTION) ? Integer.parseInt(map.get(EnumC1245g.ERROR_CORRECTION).toString()) : 33;
            if (map.containsKey(EnumC1245g.AZTEC_LAYERS)) {
                i4 = Integer.parseInt(map.get(EnumC1245g.AZTEC_LAYERS).toString());
            }
        }
        if (enumC1138a != EnumC1138a.AZTEC) {
            throw new IllegalArgumentException("Can only encode AZTEC, but got ".concat(String.valueOf(enumC1138a)));
        }
        return m1514a(C1146c.m1531a(str.getBytes(charsetForName), i3, i4), i, i2);
    }

    /* JADX INFO: renamed from: a */
    private static C1194b m1515a(String str, EnumC1138a enumC1138a, int i, int i2, Charset charset, int i3, int i4) {
        if (enumC1138a != EnumC1138a.AZTEC) {
            throw new IllegalArgumentException("Can only encode AZTEC, but got ".concat(String.valueOf(enumC1138a)));
        }
        return m1514a(C1146c.m1531a(str.getBytes(charset), i3, i4), i, i2);
    }

    /* JADX INFO: renamed from: a */
    private static C1194b m1514a(C1144a c1144a, int i, int i2) {
        C1194b c1194b = c1144a.f1909e;
        if (c1194b == null) {
            throw new IllegalStateException();
        }
        int i3 = c1194b.f2077a;
        int i4 = c1194b.f2078b;
        int iMax = Math.max(i, i3);
        int iMax2 = Math.max(i2, i4);
        int iMin = Math.min(iMax / i3, iMax2 / i4);
        int i5 = (iMax - (i3 * iMin)) / 2;
        int i6 = (iMax2 - (i4 * iMin)) / 2;
        C1194b c1194b2 = new C1194b(iMax, iMax2);
        int i7 = 0;
        while (i7 < i4) {
            int i8 = i5;
            int i9 = 0;
            while (i9 < i3) {
                if (c1194b.m1789a(i9, i7)) {
                    c1194b2.m1788a(i8, i6, iMin, iMin);
                }
                i9++;
                i8 += iMin;
            }
            i7++;
            i6 += iMin;
        }
        return c1194b2;
    }
}
