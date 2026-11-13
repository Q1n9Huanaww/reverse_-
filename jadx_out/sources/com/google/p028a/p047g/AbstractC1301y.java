package com.google.p028a.p047g;

import com.google.p028a.AbstractC1362q;
import com.google.p028a.C1210d;
import com.google.p028a.C1303h;
import com.google.p028a.C1358m;
import com.google.p028a.C1363r;
import com.google.p028a.C1365t;
import com.google.p028a.EnumC1138a;
import com.google.p028a.EnumC1233e;
import com.google.p028a.EnumC1364s;
import com.google.p028a.InterfaceC1366u;
import com.google.p028a.p035c.C1190a;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.g.y */
/* JADX INFO: compiled from: UPCEANReader.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1301y extends AbstractC1294r {

    /* JADX INFO: renamed from: a */
    private static final float f2530a = 0.48f;

    /* JADX INFO: renamed from: b */
    static final int[] f2531b = {1, 1, 1};

    /* JADX INFO: renamed from: c */
    static final int[] f2532c = {1, 1, 1, 1, 1};

    /* JADX INFO: renamed from: d */
    static final int[] f2533d = {1, 1, 1, 1, 1, 1};

    /* JADX INFO: renamed from: e */
    static final int[][] f2534e = {new int[]{3, 2, 1, 1}, new int[]{2, 2, 2, 1}, new int[]{2, 1, 2, 2}, new int[]{1, 4, 1, 1}, new int[]{1, 1, 3, 2}, new int[]{1, 2, 3, 1}, new int[]{1, 1, 1, 4}, new int[]{1, 3, 1, 2}, new int[]{1, 2, 1, 3}, new int[]{3, 1, 1, 2}};

    /* JADX INFO: renamed from: f */
    static final int[][] f2535f = new int[20][];

    /* JADX INFO: renamed from: g */
    private static final float f2536g = 0.7f;

    /* JADX INFO: renamed from: h */
    private final StringBuilder f2537h = new StringBuilder(20);

    /* JADX INFO: renamed from: i */
    private final C1300x f2538i = new C1300x();

    /* JADX INFO: renamed from: j */
    private final C1289m f2539j = new C1289m();

    /* JADX INFO: renamed from: a */
    protected abstract int mo2155a(C1190a c1190a, int[] iArr, StringBuilder sb) throws C1358m;

    /* JADX INFO: renamed from: b */
    abstract EnumC1138a mo2158b();

    static {
        System.arraycopy(f2534e, 0, f2535f, 0, 10);
        for (int i = 10; i < 20; i++) {
            int[] iArr = f2534e[i - 10];
            int[] iArr2 = new int[iArr.length];
            for (int i2 = 0; i2 < iArr.length; i2++) {
                iArr2[i2] = iArr[(iArr.length - i2) - 1];
            }
            f2535f[i] = iArr2;
        }
    }

    protected AbstractC1301y() {
    }

    /* JADX INFO: renamed from: a */
    static int[] m2213a(C1190a c1190a) throws C1358m {
        int[] iArr = new int[f2531b.length];
        int[] iArrM2215a = null;
        boolean zM1756a = false;
        int i = 0;
        while (!zM1756a) {
            Arrays.fill(iArr, 0, f2531b.length, 0);
            iArrM2215a = m2215a(c1190a, i, false, f2531b, iArr);
            int i2 = iArrM2215a[0];
            int i3 = iArrM2215a[1];
            int i4 = i2 - (i3 - i2);
            if (i4 >= 0) {
                zM1756a = c1190a.m1756a(i4, i2);
            }
            i = i3;
        }
        return iArrM2215a;
    }

    @Override // com.google.p028a.p047g.AbstractC1294r
    /* JADX INFO: renamed from: a */
    public C1363r mo2038a(int i, C1190a c1190a, Map<EnumC1233e, ?> map) throws C1210d, C1358m, C1303h {
        return mo2200a(i, c1190a, m2213a(c1190a), map);
    }

    /* JADX INFO: renamed from: a */
    public C1363r mo2200a(int i, C1190a c1190a, int[] iArr, Map<EnumC1233e, ?> map) throws C1210d, C1358m, C1303h {
        int length;
        boolean z;
        String str = null;
        InterfaceC1366u interfaceC1366u = map == null ? null : (InterfaceC1366u) map.get(EnumC1233e.NEED_RESULT_POINT_CALLBACK);
        if (interfaceC1366u != null) {
            new C1365t((iArr[0] + iArr[1]) / 2.0f, i);
        }
        StringBuilder sb = this.f2537h;
        sb.setLength(0);
        int iMo2155a = mo2155a(c1190a, iArr, sb);
        if (interfaceC1366u != null) {
            new C1365t(iMo2155a, i);
        }
        int[] iArrMo2157a = mo2157a(c1190a, iMo2155a);
        if (interfaceC1366u != null) {
            new C1365t((iArrMo2157a[0] + iArrMo2157a[1]) / 2.0f, i);
        }
        int i2 = iArrMo2157a[1];
        int i3 = (i2 - iArrMo2157a[0]) + i2;
        if (i3 >= c1190a.f2065b || !c1190a.m1756a(i2, i3)) {
            throw C1358m.m2611a();
        }
        String string = sb.toString();
        if (string.length() < 8) {
            throw C1303h.m2217a();
        }
        if (!mo2156a(string)) {
            throw C1210d.m1865a();
        }
        float f = (iArr[1] + iArr[0]) / 2.0f;
        float f2 = (iArrMo2157a[1] + iArrMo2157a[0]) / 2.0f;
        EnumC1138a enumC1138aMo2158b = mo2158b();
        float f3 = i;
        C1363r c1363r = new C1363r(string, null, new C1365t[]{new C1365t(f, f3), new C1365t(f2, f3)}, enumC1138aMo2158b);
        try {
            C1363r c1363rM2210a = this.f2538i.m2210a(i, c1190a, iArrMo2157a[1]);
            c1363r.m2624a(EnumC1364s.UPC_EAN_EXTENSION, c1363rM2210a.f2839a);
            c1363r.m2625a(c1363rM2210a.f2844f);
            C1365t[] c1365tArr = c1363rM2210a.f2842d;
            C1365t[] c1365tArr2 = c1363r.f2842d;
            if (c1365tArr2 == null) {
                c1363r.f2842d = c1365tArr;
            } else if (c1365tArr != null && c1365tArr.length > 0) {
                C1365t[] c1365tArr3 = new C1365t[c1365tArr2.length + c1365tArr.length];
                System.arraycopy(c1365tArr2, 0, c1365tArr3, 0, c1365tArr2.length);
                System.arraycopy(c1365tArr, 0, c1365tArr3, c1365tArr2.length, c1365tArr.length);
                c1363r.f2842d = c1365tArr3;
            }
            length = c1363rM2210a.f2839a.length();
        } catch (AbstractC1362q unused) {
            length = 0;
        }
        int[] iArr2 = map == null ? null : (int[]) map.get(EnumC1233e.ALLOWED_EAN_EXTENSIONS);
        if (iArr2 != null) {
            int length2 = iArr2.length;
            int i4 = 0;
            while (true) {
                if (i4 >= length2) {
                    z = false;
                    break;
                }
                if (length == iArr2[i4]) {
                    z = true;
                    break;
                }
                i4++;
            }
            if (!z) {
                throw C1358m.m2611a();
            }
        }
        if (enumC1138aMo2158b == EnumC1138a.EAN_13 || enumC1138aMo2158b == EnumC1138a.UPC_A) {
            C1289m c1289m = this.f2539j;
            c1289m.m2184a();
            int i5 = Integer.parseInt(string.substring(0, 3));
            int size = c1289m.f2501a.size();
            for (int i6 = 0; i6 < size; i6++) {
                int[] iArr3 = c1289m.f2501a.get(i6);
                int i7 = iArr3[0];
                if (i5 < i7) {
                    break;
                }
                if (iArr3.length != 1) {
                    i7 = iArr3[1];
                }
                if (i5 <= i7) {
                    str = c1289m.f2502b.get(i6);
                    break;
                }
            }
            if (str != null) {
                c1363r.m2624a(EnumC1364s.POSSIBLE_COUNTRY, str);
            }
        }
        return c1363r;
    }

    /* JADX INFO: renamed from: a */
    boolean mo2156a(String str) throws C1303h {
        return m2212a((CharSequence) str);
    }

    /* JADX INFO: renamed from: a */
    static boolean m2212a(CharSequence charSequence) throws C1303h {
        int length = charSequence.length();
        if (length == 0) {
            return false;
        }
        int i = length - 1;
        return m2216b(charSequence.subSequence(0, i)) == Character.digit(charSequence.charAt(i), 10);
    }

    /* JADX INFO: renamed from: b */
    static int m2216b(CharSequence charSequence) throws C1303h {
        int length = charSequence.length();
        int i = 0;
        for (int i2 = length - 1; i2 >= 0; i2 -= 2) {
            int iCharAt = charSequence.charAt(i2) - '0';
            if (iCharAt < 0 || iCharAt > 9) {
                throw C1303h.m2217a();
            }
            i += iCharAt;
        }
        int i3 = i * 3;
        for (int i4 = length - 2; i4 >= 0; i4 -= 2) {
            int iCharAt2 = charSequence.charAt(i4) - '0';
            if (iCharAt2 < 0 || iCharAt2 > 9) {
                throw C1303h.m2217a();
            }
            i3 += iCharAt2;
        }
        return (1000 - i3) % 10;
    }

    /* JADX INFO: renamed from: a */
    int[] mo2157a(C1190a c1190a, int i) throws C1358m {
        return m2214a(c1190a, i, false, f2531b);
    }

    /* JADX INFO: renamed from: a */
    static int[] m2214a(C1190a c1190a, int i, boolean z, int[] iArr) throws C1358m {
        return m2215a(c1190a, i, z, iArr, new int[iArr.length]);
    }

    /* JADX INFO: renamed from: a */
    static int m2211a(C1190a c1190a, int[] iArr, int i, int[][] iArr2) throws C1358m {
        m2193a(c1190a, i, iArr);
        int length = iArr2.length;
        float f = f2530a;
        int i2 = -1;
        for (int i3 = 0; i3 < length; i3++) {
            float fA = m2192a(iArr, iArr2[i3], f2536g);
            if (fA < f) {
                i2 = i3;
                f = fA;
            }
        }
        if (i2 >= 0) {
            return i2;
        }
        throw C1358m.m2611a();
    }

    /* JADX INFO: renamed from: a */
    private static int[] m2215a(C1190a c1190a, int i, boolean z, int[] iArr, int[] iArr2) throws C1358m {
        int i2 = c1190a.f2065b;
        int iM1762d = z ? c1190a.m1762d(i) : c1190a.m1760c(i);
        int length = iArr.length;
        int i3 = iM1762d;
        int i4 = 0;
        while (iM1762d < i2) {
            if (c1190a.m1755a(iM1762d) != z) {
                iArr2[i4] = iArr2[i4] + 1;
            } else {
                if (i4 != length - 1) {
                    i4++;
                } else {
                    if (m2192a(iArr2, iArr, f2536g) < f2530a) {
                        return new int[]{i3, iM1762d};
                    }
                    i3 += iArr2[0] + iArr2[1];
                    int i5 = i4 - 1;
                    System.arraycopy(iArr2, 2, iArr2, 0, i5);
                    iArr2[i5] = 0;
                    iArr2[i4] = 0;
                    i4--;
                }
                iArr2[i4] = 1;
                z = z ? false : true;
            }
            iM1762d++;
        }
        throw C1358m.m2611a();
    }
}
