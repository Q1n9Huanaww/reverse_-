package com.google.p028a.p056i;

import com.google.p028a.C1368w;
import com.google.p028a.EnumC1138a;
import com.google.p028a.EnumC1245g;
import com.google.p028a.InterfaceC1367v;
import com.google.p028a.p035c.C1194b;
import com.google.p028a.p056i.p057a.EnumC1337f;
import com.google.p028a.p056i.p059c.C1350b;
import com.google.p028a.p056i.p059c.C1351c;
import com.google.p028a.p056i.p059c.C1354f;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.i.b */
/* JADX INFO: compiled from: QRCodeWriter.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1342b implements InterfaceC1367v {

    /* JADX INFO: renamed from: a */
    private static final int f2767a = 4;

    @Override // com.google.p028a.InterfaceC1367v
    /* JADX INFO: renamed from: a */
    public final C1194b mo1516a(String str, EnumC1138a enumC1138a, int i, int i2) throws C1368w {
        return mo1517a(str, enumC1138a, i, i2, null);
    }

    @Override // com.google.p028a.InterfaceC1367v
    /* JADX INFO: renamed from: a */
    public final C1194b mo1517a(String str, EnumC1138a enumC1138a, int i, int i2, Map<EnumC1245g, ?> map) throws C1368w {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        }
        if (enumC1138a != EnumC1138a.QR_CODE) {
            throw new IllegalArgumentException("Can only encode QR_CODE, but got ".concat(String.valueOf(enumC1138a)));
        }
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("Requested dimensions are too small: " + i + 'x' + i2);
        }
        EnumC1337f enumC1337fValueOf = EnumC1337f.L;
        int i3 = 4;
        if (map != null) {
            if (map.containsKey(EnumC1245g.ERROR_CORRECTION)) {
                enumC1337fValueOf = EnumC1337f.valueOf(map.get(EnumC1245g.ERROR_CORRECTION).toString());
            }
            if (map.containsKey(EnumC1245g.MARGIN)) {
                i3 = Integer.parseInt(map.get(EnumC1245g.MARGIN).toString());
            }
        }
        return m2487a(C1351c.m2554a(str, enumC1337fValueOf, map), i, i2, i3);
    }

    /* JADX INFO: renamed from: a */
    private static C1194b m2487a(C1354f c1354f, int i, int i2, int i3) {
        C1350b c1350b = c1354f.f2819f;
        if (c1350b == null) {
            throw new IllegalStateException();
        }
        int i4 = c1350b.f2798b;
        int i5 = c1350b.f2799c;
        int i6 = i3 << 1;
        int i7 = i4 + i6;
        int i8 = i6 + i5;
        int iMax = Math.max(i, i7);
        int iMax2 = Math.max(i2, i8);
        int iMin = Math.min(iMax / i7, iMax2 / i8);
        int i9 = (iMax - (i4 * iMin)) / 2;
        int i10 = (iMax2 - (i5 * iMin)) / 2;
        C1194b c1194b = new C1194b(iMax, iMax2);
        int i11 = 0;
        while (i11 < i5) {
            int i12 = i9;
            int i13 = 0;
            while (i13 < i4) {
                if (c1350b.m2541a(i13, i11) == 1) {
                    c1194b.m1788a(i12, i10, iMin, iMin);
                }
                i13++;
                i12 += iMin;
            }
            i11++;
            i10 += iMin;
        }
        return c1194b;
    }
}
