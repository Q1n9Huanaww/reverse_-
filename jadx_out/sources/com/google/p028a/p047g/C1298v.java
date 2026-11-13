package com.google.p028a.p047g;

import com.google.p028a.C1358m;
import com.google.p028a.C1363r;
import com.google.p028a.C1365t;
import com.google.p028a.EnumC1138a;
import com.google.p028a.EnumC1364s;
import com.google.p028a.p035c.C1190a;
import java.util.EnumMap;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.g.v */
/* JADX INFO: compiled from: UPCEANExtension2Support.java */
/* JADX INFO: loaded from: classes.dex */
final class C1298v {

    /* JADX INFO: renamed from: a */
    private final int[] f2522a = new int[4];

    /* JADX INFO: renamed from: b */
    private final StringBuilder f2523b = new StringBuilder();

    C1298v() {
    }

    /* JADX INFO: renamed from: a */
    final C1363r m2203a(int i, C1190a c1190a, int[] iArr) throws C1358m {
        EnumMap enumMap;
        StringBuilder sb = this.f2523b;
        sb.setLength(0);
        int[] iArr2 = this.f2522a;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int i2 = c1190a.f2065b;
        int iM1762d = iArr[1];
        int i3 = 0;
        int i4 = 0;
        while (i3 < 2 && iM1762d < i2) {
            int iM2211a = AbstractC1301y.m2211a(c1190a, iArr2, iM1762d, AbstractC1301y.f2535f);
            sb.append((char) ((iM2211a % 10) + 48));
            int i5 = iM1762d;
            for (int i6 : iArr2) {
                i5 += i6;
            }
            if (iM2211a >= 10) {
                i4 = (1 << (1 - i3)) | i4;
            }
            iM1762d = i3 != 1 ? c1190a.m1762d(c1190a.m1760c(i5)) : i5;
            i3++;
        }
        if (sb.length() != 2) {
            throw C1358m.m2611a();
        }
        if (Integer.parseInt(sb.toString()) % 4 == i4) {
            String string = sb.toString();
            if (string.length() != 2) {
                enumMap = null;
            } else {
                enumMap = new EnumMap(EnumC1364s.class);
                enumMap.put(EnumC1364s.ISSUE_NUMBER, Integer.valueOf(string));
            }
            float f = i;
            C1363r c1363r = new C1363r(string, null, new C1365t[]{new C1365t((iArr[0] + iArr[1]) / 2.0f, f), new C1365t(iM1762d, f)}, EnumC1138a.UPC_EAN_EXTENSION);
            if (enumMap != null) {
                c1363r.m2625a(enumMap);
            }
            return c1363r;
        }
        throw C1358m.m2611a();
    }

    /* JADX INFO: renamed from: a */
    private int m2201a(C1190a c1190a, int[] iArr, StringBuilder sb) throws C1358m {
        int[] iArr2 = this.f2522a;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int i = c1190a.f2065b;
        int iM1762d = iArr[1];
        int i2 = 0;
        int i3 = 0;
        while (i2 < 2 && iM1762d < i) {
            int iM2211a = AbstractC1301y.m2211a(c1190a, iArr2, iM1762d, AbstractC1301y.f2535f);
            sb.append((char) ((iM2211a % 10) + 48));
            int i4 = iM1762d;
            for (int i5 : iArr2) {
                i4 += i5;
            }
            if (iM2211a >= 10) {
                i3 = (1 << (1 - i2)) | i3;
            }
            iM1762d = i2 != 1 ? c1190a.m1762d(c1190a.m1760c(i4)) : i4;
            i2++;
        }
        if (sb.length() != 2) {
            throw C1358m.m2611a();
        }
        if (Integer.parseInt(sb.toString()) % 4 != i3) {
            throw C1358m.m2611a();
        }
        return iM1762d;
    }

    /* JADX INFO: renamed from: a */
    private static Map<EnumC1364s, Object> m2202a(String str) {
        if (str.length() != 2) {
            return null;
        }
        EnumMap enumMap = new EnumMap(EnumC1364s.class);
        enumMap.put(EnumC1364s.ISSUE_NUMBER, Integer.valueOf(str));
        return enumMap;
    }
}
