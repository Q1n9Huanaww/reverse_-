package com.google.p028a.p047g;

import com.google.p028a.C1303h;
import com.google.p028a.C1368w;
import com.google.p028a.EnumC1138a;
import com.google.p028a.EnumC1245g;
import com.google.p028a.p035c.C1194b;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.g.l */
/* JADX INFO: compiled from: EAN8Writer.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1288l extends AbstractC1302z {

    /* JADX INFO: renamed from: a */
    private static final int f2500a = 67;

    @Override // com.google.p028a.p047g.AbstractC1295s, com.google.p028a.InterfaceC1367v
    /* JADX INFO: renamed from: a */
    public final C1194b mo1517a(String str, EnumC1138a enumC1138a, int i, int i2, Map<EnumC1245g, ?> map) throws C1368w {
        if (enumC1138a != EnumC1138a.EAN_8) {
            throw new IllegalArgumentException("Can only encode EAN_8, but got ".concat(String.valueOf(enumC1138a)));
        }
        return super.mo1517a(str, enumC1138a, i, i2, map);
    }

    @Override // com.google.p028a.p047g.AbstractC1295s
    /* JADX INFO: renamed from: a */
    public final boolean[] mo2159a(String str) {
        int length = str.length();
        switch (length) {
            case 7:
                try {
                    str = str + AbstractC1301y.m2216b(str);
                } catch (C1303h e) {
                    throw new IllegalArgumentException(e);
                }
                break;
            case 8:
                try {
                    if (!AbstractC1301y.m2212a((CharSequence) str)) {
                        throw new IllegalArgumentException("Contents do not pass checksum");
                    }
                } catch (C1303h unused) {
                    throw new IllegalArgumentException("Illegal contents");
                }
            default:
                throw new IllegalArgumentException("Requested contents should be 8 digits long, but got ".concat(String.valueOf(length)));
        }
        boolean[] zArr = new boolean[67];
        int iA = m2196a(zArr, 0, AbstractC1301y.f2531b, true) + 0;
        for (int i = 0; i <= 3; i++) {
            iA += m2196a(zArr, iA, AbstractC1301y.f2534e[Character.digit(str.charAt(i), 10)], false);
        }
        int iA2 = iA + m2196a(zArr, iA, AbstractC1301y.f2532c, false);
        for (int i2 = 4; i2 <= 7; i2++) {
            iA2 += m2196a(zArr, iA2, AbstractC1301y.f2534e[Character.digit(str.charAt(i2), 10)], true);
        }
        m2196a(zArr, iA2, AbstractC1301y.f2531b, true);
        return zArr;
    }
}
