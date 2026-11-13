package com.google.p028a.p047g;

import com.google.p028a.C1303h;
import com.google.p028a.C1358m;
import com.google.p028a.EnumC1138a;
import com.google.p028a.p035c.C1190a;

/* JADX INFO: renamed from: com.google.a.g.aa */
/* JADX INFO: compiled from: UPCEReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1276aa extends AbstractC1301y {

    /* JADX INFO: renamed from: h */
    private final int[] f2440h = new int[4];

    /* JADX INFO: renamed from: g */
    private static final int[] f2439g = {1, 1, 1, 1, 1, 1};

    /* JADX INFO: renamed from: a */
    static final int[][] f2438a = {new int[]{56, 52, 50, 49, 44, 38, 35, 42, 41, 37}, new int[]{7, 11, 13, 14, 19, 25, 28, 21, 22, 26}};

    @Override // com.google.p028a.p047g.AbstractC1301y
    /* JADX INFO: renamed from: a */
    protected final int mo2155a(C1190a c1190a, int[] iArr, StringBuilder sb) throws C1358m {
        int[] iArr2 = this.f2440h;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int i = c1190a.f2065b;
        int i2 = iArr[1];
        int i3 = 0;
        int i4 = 0;
        while (i3 < 6 && i2 < i) {
            int iA = m2211a(c1190a, iArr2, i2, f2535f);
            sb.append((char) ((iA % 10) + 48));
            int i5 = i2;
            for (int i6 : iArr2) {
                i5 += i6;
            }
            if (iA >= 10) {
                i4 = (1 << (5 - i3)) | i4;
            }
            i3++;
            i2 = i5;
        }
        for (int i7 = 0; i7 <= 1; i7++) {
            for (int i8 = 0; i8 < 10; i8++) {
                if (i4 == f2438a[i7][i8]) {
                    sb.insert(0, (char) (i7 + 48));
                    sb.append((char) (i8 + 48));
                    return i2;
                }
            }
        }
        throw C1358m.m2611a();
    }

    @Override // com.google.p028a.p047g.AbstractC1301y
    /* JADX INFO: renamed from: a */
    protected final int[] mo2157a(C1190a c1190a, int i) throws C1358m {
        return m2214a(c1190a, i, true, f2439g);
    }

    @Override // com.google.p028a.p047g.AbstractC1301y
    /* JADX INFO: renamed from: a */
    protected final boolean mo2156a(String str) throws C1303h {
        return super.mo2156a(m2154b(str));
    }

    /* JADX INFO: renamed from: a */
    private static void m2153a(StringBuilder sb, int i) throws C1358m {
        for (int i2 = 0; i2 <= 1; i2++) {
            for (int i3 = 0; i3 < 10; i3++) {
                if (i == f2438a[i2][i3]) {
                    sb.insert(0, (char) (i2 + 48));
                    sb.append((char) (i3 + 48));
                    return;
                }
            }
        }
        throw C1358m.m2611a();
    }

    @Override // com.google.p028a.p047g.AbstractC1301y
    /* JADX INFO: renamed from: b */
    final EnumC1138a mo2158b() {
        return EnumC1138a.UPC_E;
    }

    /* JADX INFO: renamed from: b */
    public static String m2154b(String str) {
        char[] cArr = new char[6];
        str.getChars(1, 7, cArr, 0);
        StringBuilder sb = new StringBuilder(12);
        sb.append(str.charAt(0));
        char c = cArr[5];
        switch (c) {
            case '0':
            case '1':
            case '2':
                sb.append(cArr, 0, 2);
                sb.append(c);
                sb.append("0000");
                sb.append(cArr, 2, 3);
                break;
            case '3':
                sb.append(cArr, 0, 3);
                sb.append("00000");
                sb.append(cArr, 3, 2);
                break;
            case '4':
                sb.append(cArr, 0, 4);
                sb.append("00000");
                sb.append(cArr[4]);
                break;
            default:
                sb.append(cArr, 0, 5);
                sb.append("0000");
                sb.append(c);
                break;
        }
        if (str.length() >= 8) {
            sb.append(str.charAt(7));
        }
        return sb.toString();
    }
}
