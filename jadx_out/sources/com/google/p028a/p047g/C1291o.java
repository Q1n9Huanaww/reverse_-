package com.google.p028a.p047g;

import com.google.p028a.C1368w;
import com.google.p028a.EnumC1138a;
import com.google.p028a.EnumC1245g;
import com.google.p028a.p035c.C1194b;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.g.o */
/* JADX INFO: compiled from: ITFWriter.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1291o extends AbstractC1295s {

    /* JADX INFO: renamed from: c */
    private static final int f2515c = 3;

    /* JADX INFO: renamed from: d */
    private static final int f2516d = 1;

    /* JADX INFO: renamed from: a */
    private static final int[] f2513a = {1, 1, 1, 1};

    /* JADX INFO: renamed from: b */
    private static final int[] f2514b = {3, 1, 1};

    /* JADX INFO: renamed from: e */
    private static final int[][] f2517e = {new int[]{1, 1, 3, 3, 1}, new int[]{3, 1, 1, 1, 3}, new int[]{1, 3, 1, 1, 3}, new int[]{3, 3, 1, 1, 1}, new int[]{1, 1, 3, 1, 3}, new int[]{3, 1, 3, 1, 1}, new int[]{1, 3, 3, 1, 1}, new int[]{1, 1, 1, 3, 3}, new int[]{3, 1, 1, 3, 1}, new int[]{1, 3, 1, 3, 1}};

    @Override // com.google.p028a.p047g.AbstractC1295s, com.google.p028a.InterfaceC1367v
    /* JADX INFO: renamed from: a */
    public final C1194b mo1517a(String str, EnumC1138a enumC1138a, int i, int i2, Map<EnumC1245g, ?> map) throws C1368w {
        if (enumC1138a != EnumC1138a.ITF) {
            throw new IllegalArgumentException("Can only encode ITF, but got ".concat(String.valueOf(enumC1138a)));
        }
        return super.mo1517a(str, enumC1138a, i, i2, map);
    }

    @Override // com.google.p028a.p047g.AbstractC1295s
    /* JADX INFO: renamed from: a */
    public final boolean[] mo2159a(String str) {
        int length = str.length();
        if (length % 2 != 0) {
            throw new IllegalArgumentException("The length of the input should be even");
        }
        if (length > 80) {
            throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got ".concat(String.valueOf(length)));
        }
        boolean[] zArr = new boolean[(length * 9) + 9];
        int iA = m2196a(zArr, 0, f2513a, true);
        for (int i = 0; i < length; i += 2) {
            int iDigit = Character.digit(str.charAt(i), 10);
            int iDigit2 = Character.digit(str.charAt(i + 1), 10);
            int[] iArr = new int[10];
            for (int i2 = 0; i2 < 5; i2++) {
                int i3 = i2 * 2;
                iArr[i3] = f2517e[iDigit][i2];
                iArr[i3 + 1] = f2517e[iDigit2][i2];
            }
            iA += m2196a(zArr, iA, iArr, true);
        }
        m2196a(zArr, iA, f2514b, true);
        return zArr;
    }
}
