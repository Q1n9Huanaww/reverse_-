package com.google.p028a.p047g;

import com.google.p028a.C1368w;
import com.google.p028a.EnumC1138a;
import com.google.p028a.EnumC1245g;
import com.google.p028a.p035c.C1194b;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.g.h */
/* JADX INFO: compiled from: Code93Writer.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1284h extends AbstractC1295s {
    @Override // com.google.p028a.p047g.AbstractC1295s, com.google.p028a.InterfaceC1367v
    /* JADX INFO: renamed from: a */
    public final C1194b mo1517a(String str, EnumC1138a enumC1138a, int i, int i2, Map<EnumC1245g, ?> map) throws C1368w {
        if (enumC1138a != EnumC1138a.CODE_93) {
            throw new IllegalArgumentException("Can only encode CODE_93, but got ".concat(String.valueOf(enumC1138a)));
        }
        return super.mo1517a(str, enumC1138a, i, i2, map);
    }

    @Override // com.google.p028a.p047g.AbstractC1295s
    /* JADX INFO: renamed from: a */
    public final boolean[] mo2159a(String str) {
        int length = str.length();
        if (length > 80) {
            throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got ".concat(String.valueOf(length)));
        }
        int[] iArr = new int[9];
        int length2 = ((str.length() + 2 + 2) * 9) + 1;
        m2179a(C1283g.f2491b[47], iArr);
        boolean[] zArr = new boolean[length2];
        int iM2180b = m2180b(zArr, 0, iArr);
        for (int i = 0; i < length; i++) {
            m2179a(C1283g.f2491b["0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(str.charAt(i))], iArr);
            iM2180b += m2180b(zArr, iM2180b, iArr);
        }
        int iM2177a = m2177a(str, 20);
        m2179a(C1283g.f2491b[iM2177a], iArr);
        int iM2180b2 = iM2180b + m2180b(zArr, iM2180b, iArr);
        m2179a(C1283g.f2491b[m2177a(str + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".charAt(iM2177a), 15)], iArr);
        int iM2180b3 = iM2180b2 + m2180b(zArr, iM2180b2, iArr);
        m2179a(C1283g.f2491b[47], iArr);
        zArr[iM2180b3 + m2180b(zArr, iM2180b3, iArr)] = true;
        return zArr;
    }

    /* JADX INFO: renamed from: a */
    private static void m2179a(int i, int[] iArr) {
        for (int i2 = 0; i2 < 9; i2++) {
            int i3 = 1;
            if (((1 << (8 - i2)) & i) == 0) {
                i3 = 0;
            }
            iArr[i2] = i3;
        }
    }

    @Deprecated
    /* JADX INFO: renamed from: a */
    private static int m2178a(boolean[] zArr, int i, int[] iArr) {
        return m2180b(zArr, i, iArr);
    }

    /* JADX INFO: renamed from: b */
    private static int m2180b(boolean[] zArr, int i, int[] iArr) {
        int length = iArr.length;
        int i2 = i;
        int i3 = 0;
        while (i3 < length) {
            int i4 = i2 + 1;
            zArr[i2] = iArr[i3] != 0;
            i3++;
            i2 = i4;
        }
        return 9;
    }

    /* JADX INFO: renamed from: a */
    private static int m2177a(String str, int i) {
        int iIndexOf = 0;
        int i2 = 1;
        for (int length = str.length() - 1; length >= 0; length--) {
            iIndexOf += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(str.charAt(length)) * i2;
            i2++;
            if (i2 > i) {
                i2 = 1;
            }
        }
        return iIndexOf % 47;
    }
}
