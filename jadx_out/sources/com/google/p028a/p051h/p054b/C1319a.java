package com.google.p028a.p051h.p054b;

import com.google.p028a.C1189c;
import com.google.p028a.C1358m;
import com.google.p028a.C1365t;
import com.google.p028a.p035c.C1190a;
import com.google.p028a.p035c.C1194b;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: renamed from: com.google.a.h.b.a */
/* JADX INFO: compiled from: Detector.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1319a {

    /* JADX INFO: renamed from: c */
    private static final float f2635c = 0.42f;

    /* JADX INFO: renamed from: d */
    private static final float f2636d = 0.8f;

    /* JADX INFO: renamed from: g */
    private static final int f2639g = 3;

    /* JADX INFO: renamed from: h */
    private static final int f2640h = 5;

    /* JADX INFO: renamed from: i */
    private static final int f2641i = 25;

    /* JADX INFO: renamed from: j */
    private static final int f2642j = 5;

    /* JADX INFO: renamed from: k */
    private static final int f2643k = 10;

    /* JADX INFO: renamed from: a */
    private static final int[] f2633a = {0, 4, 1, 5};

    /* JADX INFO: renamed from: b */
    private static final int[] f2634b = {6, 2, 7, 3};

    /* JADX INFO: renamed from: e */
    private static final int[] f2637e = {8, 1, 1, 1, 1, 1, 1, 3};

    /* JADX INFO: renamed from: f */
    private static final int[] f2638f = {7, 1, 1, 3, 1, 1, 1, 2, 1};

    private C1319a() {
    }

    /* JADX INFO: renamed from: a */
    public static C1320b m2348a(C1189c c1189c, boolean z) throws C1358m {
        C1194b c1194bM1741c = c1189c.m1741c();
        List<C1365t[]> listM2349a = m2349a(z, c1194bM1741c);
        if (listM2349a.isEmpty()) {
            c1194bM1741c = c1194bM1741c.clone();
            int i = c1194bM1741c.f2077a;
            int i2 = c1194bM1741c.f2078b;
            C1190a c1190a = new C1190a(i);
            C1190a c1190a2 = new C1190a(i);
            for (int i3 = 0; i3 < (i2 + 1) / 2; i3++) {
                c1190a = c1194bM1741c.m1786a(i3, c1190a);
                int i4 = (i2 - 1) - i3;
                c1190a2 = c1194bM1741c.m1786a(i4, c1190a2);
                c1190a.m1761c();
                c1190a2.m1761c();
                c1194bM1741c.m1791b(i3, c1190a2);
                c1194bM1741c.m1791b(i4, c1190a);
            }
            listM2349a = m2349a(z, c1194bM1741c);
        }
        return new C1320b(c1194bM1741c, listM2349a);
    }

    /* JADX INFO: renamed from: a */
    private static List<C1365t[]> m2349a(boolean z, C1194b c1194b) {
        int i;
        int i2;
        float f;
        ArrayList<C1365t[]> arrayList = new ArrayList();
        int iMax = 0;
        loop0: while (true) {
            int i3 = 0;
            boolean z2 = false;
            while (iMax < c1194b.f2078b) {
                int i4 = c1194b.f2078b;
                int i5 = c1194b.f2077a;
                C1365t[] c1365tArr = new C1365t[8];
                m2350a(c1365tArr, m2353a(c1194b, i4, i5, iMax, i3, f2637e), f2633a);
                if (c1365tArr[4] != null) {
                    i2 = (int) c1365tArr[4].f2858a;
                    i = (int) c1365tArr[4].f2859b;
                } else {
                    i = iMax;
                    i2 = i3;
                }
                m2350a(c1365tArr, m2353a(c1194b, i4, i5, i, i2, f2638f), f2634b);
                if (c1365tArr[0] == null && c1365tArr[3] == null) {
                    if (!z2) {
                        break;
                    }
                    for (C1365t[] c1365tArr2 : arrayList) {
                        if (c1365tArr2[1] != null) {
                            iMax = (int) Math.max(iMax, c1365tArr2[1].f2859b);
                        }
                        if (c1365tArr2[3] != null) {
                            iMax = Math.max(iMax, (int) c1365tArr2[3].f2859b);
                        }
                    }
                    iMax += 5;
                } else {
                    arrayList.add(c1365tArr);
                    if (!z) {
                        break loop0;
                    }
                    if (c1365tArr[2] != null) {
                        i3 = (int) c1365tArr[2].f2858a;
                        f = c1365tArr[2].f2859b;
                    } else {
                        i3 = (int) c1365tArr[4].f2858a;
                        f = c1365tArr[4].f2859b;
                    }
                    iMax = (int) f;
                    z2 = true;
                }
            }
            break loop0;
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: a */
    private static void m2350a(C1365t[] c1365tArr, C1365t[] c1365tArr2, int[] iArr) {
        for (int i = 0; i < iArr.length; i++) {
            c1365tArr[iArr[i]] = c1365tArr2[i];
        }
    }

    /* JADX INFO: renamed from: a */
    private static C1365t[] m2353a(C1194b c1194b, int i, int i2, int i3, int i4, int[] iArr) {
        boolean z;
        int i5;
        int[] iArr2;
        C1365t[] c1365tArr = new C1365t[4];
        int[] iArr3 = new int[iArr.length];
        int i6 = i3;
        while (true) {
            if (i6 >= i) {
                z = false;
                break;
            }
            int[] iArrM2351a = m2351a(c1194b, i4, i6, i2, iArr, iArr3);
            if (iArrM2351a != null) {
                while (true) {
                    iArr2 = iArrM2351a;
                    if (i6 <= 0) {
                        break;
                    }
                    i6--;
                    iArrM2351a = m2351a(c1194b, i4, i6, i2, iArr, iArr3);
                    if (iArrM2351a == null) {
                        i6++;
                        break;
                    }
                }
                float f = i6;
                c1365tArr[0] = new C1365t(iArr2[0], f);
                c1365tArr[1] = new C1365t(iArr2[1], f);
                z = true;
                break;
            }
            i6 += 5;
        }
        int i7 = i6 + 1;
        if (z) {
            int[] iArr4 = {(int) c1365tArr[0].f2858a, (int) c1365tArr[1].f2858a};
            int i8 = i7;
            int i9 = 0;
            while (true) {
                if (i8 >= i) {
                    i5 = i9;
                    break;
                }
                i5 = i9;
                int[] iArrM2351a2 = m2351a(c1194b, iArr4[0], i8, i2, iArr, iArr3);
                if (iArrM2351a2 != null && Math.abs(iArr4[0] - iArrM2351a2[0]) < 5 && Math.abs(iArr4[1] - iArrM2351a2[1]) < 5) {
                    iArr4 = iArrM2351a2;
                    i9 = 0;
                } else {
                    if (i5 > 25) {
                        break;
                    }
                    i9 = i5 + 1;
                }
                i8++;
            }
            i7 = i8 - (i5 + 1);
            float f2 = i7;
            c1365tArr[2] = new C1365t(iArr4[0], f2);
            c1365tArr[3] = new C1365t(iArr4[1], f2);
        }
        if (i7 - i6 < 10) {
            Arrays.fill(c1365tArr, (Object) null);
        }
        return c1365tArr;
    }

    /* JADX INFO: renamed from: a */
    private static int[] m2351a(C1194b c1194b, int i, int i2, int i3, int[] iArr, int[] iArr2) {
        Arrays.fill(iArr2, 0, iArr2.length, 0);
        int i4 = 0;
        while (c1194b.m1789a(i, i2) && i > 0) {
            int i5 = i4 + 1;
            if (i4 >= 3) {
                break;
            }
            i--;
            i4 = i5;
        }
        int length = iArr.length;
        int i6 = i;
        boolean z = false;
        int i7 = 0;
        while (i < i3) {
            if (c1194b.m1789a(i, i2) != z) {
                iArr2[i7] = iArr2[i7] + 1;
            } else {
                if (i7 != length - 1) {
                    i7++;
                } else {
                    if (m2347a(iArr2, iArr) < f2635c) {
                        return new int[]{i6, i};
                    }
                    i6 += iArr2[0] + iArr2[1];
                    int i8 = i7 - 1;
                    System.arraycopy(iArr2, 2, iArr2, 0, i8);
                    iArr2[i8] = 0;
                    iArr2[i7] = 0;
                    i7--;
                }
                iArr2[i7] = 1;
                z = !z;
            }
            i++;
        }
        if (i7 != length - 1 || m2347a(iArr2, iArr) >= f2635c) {
            return null;
        }
        return new int[]{i6, i - 1};
    }

    /* JADX INFO: renamed from: a */
    private static float m2347a(int[] iArr, int[] iArr2) {
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            i += iArr[i3];
            i2 += iArr2[i3];
        }
        if (i < i2) {
            return Float.POSITIVE_INFINITY;
        }
        float f = i;
        float f2 = f / i2;
        float f3 = f2636d * f2;
        float f4 = 0.0f;
        for (int i4 = 0; i4 < length; i4++) {
            int i5 = iArr[i4];
            float f5 = iArr2[i4] * f2;
            float f6 = i5;
            float f7 = f6 > f5 ? f6 - f5 : f5 - f6;
            if (f7 > f3) {
                return Float.POSITIVE_INFINITY;
            }
            f4 += f7;
        }
        return f4 / f;
    }

    /* JADX INFO: renamed from: a */
    private static C1365t[] m2352a(C1194b c1194b, int i, int i2) {
        int i3 = c1194b.f2078b;
        int i4 = c1194b.f2077a;
        C1365t[] c1365tArr = new C1365t[8];
        m2350a(c1365tArr, m2353a(c1194b, i3, i4, i, i2, f2637e), f2633a);
        if (c1365tArr[4] != null) {
            i2 = (int) c1365tArr[4].f2858a;
            i = (int) c1365tArr[4].f2859b;
        }
        m2350a(c1365tArr, m2353a(c1194b, i3, i4, i, i2, f2638f), f2634b);
        return c1365tArr;
    }
}
