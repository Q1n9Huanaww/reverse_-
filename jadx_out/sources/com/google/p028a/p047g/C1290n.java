package com.google.p028a.p047g;

import com.google.p028a.C1303h;
import com.google.p028a.C1358m;
import com.google.p028a.C1363r;
import com.google.p028a.C1365t;
import com.google.p028a.EnumC1138a;
import com.google.p028a.EnumC1233e;
import com.google.p028a.p035c.C1190a;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.g.n */
/* JADX INFO: compiled from: ITFReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1290n extends AbstractC1294r {

    /* JADX INFO: renamed from: a */
    private static final float f2503a = 0.38f;

    /* JADX INFO: renamed from: b */
    private static final float f2504b = 0.5f;

    /* JADX INFO: renamed from: c */
    private static final int f2505c = 3;

    /* JADX INFO: renamed from: d */
    private static final int f2506d = 2;

    /* JADX INFO: renamed from: e */
    private static final int f2507e = 1;

    /* JADX INFO: renamed from: f */
    private static final int[] f2508f = {6, 8, 10, 12, 14};

    /* JADX INFO: renamed from: h */
    private static final int[] f2509h = {1, 1, 1, 1};

    /* JADX INFO: renamed from: i */
    private static final int[][] f2510i = {new int[]{1, 1, 2}, new int[]{1, 1, 3}};

    /* JADX INFO: renamed from: j */
    private static final int[][] f2511j = {new int[]{1, 1, 2, 2, 1}, new int[]{2, 1, 1, 1, 2}, new int[]{1, 2, 1, 1, 2}, new int[]{2, 2, 1, 1, 1}, new int[]{1, 1, 2, 1, 2}, new int[]{2, 1, 2, 1, 1}, new int[]{1, 2, 2, 1, 1}, new int[]{1, 1, 1, 2, 2}, new int[]{2, 1, 1, 2, 1}, new int[]{1, 2, 1, 2, 1}, new int[]{1, 1, 3, 3, 1}, new int[]{3, 1, 1, 1, 3}, new int[]{1, 3, 1, 1, 3}, new int[]{3, 3, 1, 1, 1}, new int[]{1, 1, 3, 1, 3}, new int[]{3, 1, 3, 1, 1}, new int[]{1, 3, 3, 1, 1}, new int[]{1, 1, 1, 3, 3}, new int[]{3, 1, 1, 3, 1}, new int[]{1, 3, 1, 3, 1}};

    /* JADX INFO: renamed from: g */
    private int f2512g = -1;

    /* JADX INFO: renamed from: a */
    private static void m2187a(C1190a c1190a, int i, int i2, StringBuilder sb) throws C1358m {
        int[] iArr = new int[10];
        int[] iArr2 = new int[5];
        int[] iArr3 = new int[5];
        while (i < i2) {
            m2193a(c1190a, i, iArr);
            for (int i3 = 0; i3 < 5; i3++) {
                int i4 = i3 * 2;
                iArr2[i3] = iArr[i4];
                iArr3[i3] = iArr[i4 + 1];
            }
            sb.append((char) (m2185a(iArr2) + 48));
            sb.append((char) (m2185a(iArr3) + 48));
            for (int i5 = 0; i5 < 10; i5++) {
                i += iArr[i5];
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private int[] m2188a(C1190a c1190a) throws C1358m {
        int[] iArrM2191c = m2191c(c1190a, m2189b(c1190a), f2509h);
        this.f2512g = (iArrM2191c[1] - iArrM2191c[0]) / 4;
        m2186a(c1190a, iArrM2191c[0]);
        return iArrM2191c;
    }

    /* JADX INFO: renamed from: a */
    private void m2186a(C1190a c1190a, int i) throws C1358m {
        int i2 = this.f2512g * 10;
        if (i2 >= i) {
            i2 = i;
        }
        for (int i3 = i - 1; i2 > 0 && i3 >= 0 && !c1190a.m1755a(i3); i3--) {
            i2--;
        }
        if (i2 != 0) {
            throw C1358m.m2611a();
        }
    }

    /* JADX INFO: renamed from: c */
    private int[] m2190c(C1190a c1190a) throws C1358m {
        int[] iArrM2191c;
        c1190a.m1761c();
        try {
            int iM2189b = m2189b(c1190a);
            try {
                iArrM2191c = m2191c(c1190a, iM2189b, f2510i[0]);
            } catch (C1358m unused) {
                iArrM2191c = m2191c(c1190a, iM2189b, f2510i[1]);
            }
            m2186a(c1190a, iArrM2191c[0]);
            int i = iArrM2191c[0];
            iArrM2191c[0] = c1190a.f2065b - iArrM2191c[1];
            iArrM2191c[1] = c1190a.f2065b - i;
            c1190a.m1761c();
            return iArrM2191c;
        } catch (Throwable th) {
            c1190a.m1761c();
            throw th;
        }
    }

    /* JADX INFO: renamed from: c */
    private static int[] m2191c(C1190a c1190a, int i, int[] iArr) throws C1358m {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        int i2 = c1190a.f2065b;
        int i3 = i;
        boolean z = false;
        int i4 = 0;
        while (i < i2) {
            if (c1190a.m1755a(i) != z) {
                iArr2[i4] = iArr2[i4] + 1;
            } else {
                if (i4 != length - 1) {
                    i4++;
                } else {
                    if (m2192a(iArr2, iArr, f2504b) < f2503a) {
                        return new int[]{i3, i};
                    }
                    i3 += iArr2[0] + iArr2[1];
                    int i5 = i4 - 1;
                    System.arraycopy(iArr2, 2, iArr2, 0, i5);
                    iArr2[i5] = 0;
                    iArr2[i4] = 0;
                    i4--;
                }
                iArr2[i4] = 1;
                z = !z;
            }
            i++;
        }
        throw C1358m.m2611a();
    }

    /* JADX INFO: renamed from: a */
    private static int m2185a(int[] iArr) throws C1358m {
        int length = f2511j.length;
        float f = f2503a;
        int i = -1;
        for (int i2 = 0; i2 < length; i2++) {
            float fA = m2192a(iArr, f2511j[i2], f2504b);
            if (fA < f) {
                i = i2;
                f = fA;
            } else if (fA == f) {
                i = -1;
            }
        }
        if (i >= 0) {
            return i % 10;
        }
        throw C1358m.m2611a();
    }

    @Override // com.google.p028a.p047g.AbstractC1294r
    /* JADX INFO: renamed from: a */
    public final C1363r mo2038a(int i, C1190a c1190a, Map<EnumC1233e, ?> map) throws C1358m, C1303h {
        boolean z;
        int[] iArrM2191c = m2191c(c1190a, m2189b(c1190a), f2509h);
        this.f2512g = (iArrM2191c[1] - iArrM2191c[0]) / 4;
        m2186a(c1190a, iArrM2191c[0]);
        int[] iArrM2190c = m2190c(c1190a);
        StringBuilder sb = new StringBuilder(20);
        m2187a(c1190a, iArrM2191c[1], iArrM2190c[0], sb);
        String string = sb.toString();
        int[] iArr = map != null ? (int[]) map.get(EnumC1233e.ALLOWED_LENGTHS) : null;
        if (iArr == null) {
            iArr = f2508f;
        }
        int length = string.length();
        int length2 = iArr.length;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= length2) {
                z = false;
                break;
            }
            int i4 = iArr[i2];
            if (length == i4) {
                z = true;
                break;
            }
            if (i4 > i3) {
                i3 = i4;
            }
            i2++;
        }
        if (!z && length > i3) {
            z = true;
        }
        if (!z) {
            throw C1303h.m2217a();
        }
        float f = i;
        return new C1363r(string, null, new C1365t[]{new C1365t(iArrM2191c[1], f), new C1365t(iArrM2190c[0], f)}, EnumC1138a.ITF);
    }

    /* JADX INFO: renamed from: b */
    private static int m2189b(C1190a c1190a) throws C1358m {
        int i = c1190a.f2065b;
        int iM1760c = c1190a.m1760c(0);
        if (iM1760c == i) {
            throw C1358m.m2611a();
        }
        return iM1760c;
    }
}
