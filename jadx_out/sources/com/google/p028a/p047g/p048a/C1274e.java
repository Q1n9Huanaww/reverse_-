package com.google.p028a.p047g.p048a;

import com.google.p028a.C1358m;
import com.google.p028a.C1363r;
import com.google.p028a.C1365t;
import com.google.p028a.EnumC1138a;
import com.google.p028a.EnumC1233e;
import com.google.p028a.InterfaceC1366u;
import com.google.p028a.p035c.C1190a;
import com.google.p028a.p035c.p036a.C1191a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.g.a.e */
/* JADX INFO: compiled from: RSS14Reader.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1274e extends AbstractC1247a {

    /* JADX INFO: renamed from: g */
    private static final int[] f2429g = {1, 10, 34, 70, 126};

    /* JADX INFO: renamed from: h */
    private static final int[] f2430h = {4, 20, 48, 81};

    /* JADX INFO: renamed from: i */
    private static final int[] f2431i = {0, 161, 961, 2015, 2715};

    /* JADX INFO: renamed from: j */
    private static final int[] f2432j = {0, 336, 1036, 1516};

    /* JADX INFO: renamed from: k */
    private static final int[] f2433k = {8, 6, 4, 3, 1};

    /* JADX INFO: renamed from: l */
    private static final int[] f2434l = {2, 4, 6, 8};

    /* JADX INFO: renamed from: m */
    private static final int[][] f2435m = {new int[]{3, 8, 2, 1}, new int[]{3, 5, 5, 1}, new int[]{3, 3, 7, 1}, new int[]{3, 1, 9, 1}, new int[]{2, 7, 4, 1}, new int[]{2, 5, 6, 1}, new int[]{2, 3, 8, 1}, new int[]{1, 5, 7, 1}, new int[]{1, 3, 9, 1}};

    /* JADX INFO: renamed from: n */
    private final List<C1273d> f2436n = new ArrayList();

    /* JADX INFO: renamed from: o */
    private final List<C1273d> f2437o = new ArrayList();

    @Override // com.google.p028a.p047g.AbstractC1294r
    /* JADX INFO: renamed from: a */
    public final C1363r mo2038a(int i, C1190a c1190a, Map<EnumC1233e, ?> map) throws C1358m {
        m2147a(this.f2436n, m2145a(c1190a, false, i, map));
        c1190a.m1761c();
        m2147a(this.f2437o, m2145a(c1190a, true, i, map));
        c1190a.m1761c();
        for (C1273d c1273d : this.f2436n) {
            if (c1273d.f2428d > 1) {
                for (C1273d c1273d2 : this.f2437o) {
                    if (c1273d2.f2428d > 1) {
                        int i2 = (c1273d.f2423b + (c1273d2.f2423b * 16)) % 79;
                        int i3 = (c1273d.f2427c.f2424a * 9) + c1273d2.f2427c.f2424a;
                        if (i3 > 72) {
                            i3--;
                        }
                        if (i3 > 8) {
                            i3--;
                        }
                        if (i2 == i3) {
                            String strValueOf = String.valueOf((((long) c1273d.f2422a) * 4537077) + ((long) c1273d2.f2422a));
                            StringBuilder sb = new StringBuilder(14);
                            for (int length = 13 - strValueOf.length(); length > 0; length--) {
                                sb.append('0');
                            }
                            sb.append(strValueOf);
                            int i4 = 0;
                            for (int i5 = 0; i5 < 13; i5++) {
                                int iCharAt = sb.charAt(i5) - '0';
                                if ((i5 & 1) == 0) {
                                    iCharAt *= 3;
                                }
                                i4 += iCharAt;
                            }
                            int i6 = 10 - (i4 % 10);
                            if (i6 == 10) {
                                i6 = 0;
                            }
                            sb.append(i6);
                            C1365t[] c1365tArr = c1273d.f2427c.f2426c;
                            C1365t[] c1365tArr2 = c1273d2.f2427c.f2426c;
                            return new C1363r(sb.toString(), null, new C1365t[]{c1365tArr[0], c1365tArr[1], c1365tArr2[0], c1365tArr2[1]}, EnumC1138a.RSS_14);
                        }
                    }
                }
            }
        }
        throw C1358m.m2611a();
    }

    /* JADX INFO: renamed from: a */
    private static void m2147a(Collection<C1273d> collection, C1273d c1273d) {
        if (c1273d == null) {
            return;
        }
        boolean z = false;
        for (C1273d c1273d2 : collection) {
            if (c1273d2.f2422a == c1273d.f2422a) {
                c1273d2.f2428d++;
                z = true;
                break;
            }
        }
        if (z) {
            return;
        }
        collection.add(c1273d);
    }

    @Override // com.google.p028a.p047g.AbstractC1294r, com.google.p028a.InterfaceC1361p
    /* JADX INFO: renamed from: a */
    public final void mo1491a() {
        this.f2436n.clear();
        this.f2437o.clear();
    }

    /* JADX INFO: renamed from: a */
    private C1272c m2144a(C1190a c1190a, int i, boolean z, int[] iArr) throws C1358m {
        int i2;
        int i3;
        boolean zM1755a = c1190a.m1755a(iArr[0]);
        int i4 = iArr[0] - 1;
        while (i4 >= 0 && zM1755a != c1190a.m1755a(i4)) {
            i4--;
        }
        int i5 = i4 + 1;
        int i6 = iArr[0] - i5;
        int[] iArr2 = this.f2350a;
        System.arraycopy(iArr2, 0, iArr2, 1, iArr2.length - 1);
        iArr2[0] = i6;
        int iA = m2039a(iArr2, f2435m);
        int i7 = iArr[1];
        if (z) {
            int i8 = (c1190a.f2065b - 1) - i5;
            i2 = (c1190a.f2065b - 1) - i7;
            i3 = i8;
        } else {
            i2 = i7;
            i3 = i5;
        }
        return new C1272c(iA, new int[]{i5, iArr[1]}, i3, i2, i);
    }

    /* JADX INFO: renamed from: a */
    private static C1363r m2146a(C1273d c1273d, C1273d c1273d2) {
        String strValueOf = String.valueOf((((long) c1273d.f2422a) * 4537077) + ((long) c1273d2.f2422a));
        StringBuilder sb = new StringBuilder(14);
        for (int length = 13 - strValueOf.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(strValueOf);
        int i = 0;
        for (int i2 = 0; i2 < 13; i2++) {
            int iCharAt = sb.charAt(i2) - '0';
            if ((i2 & 1) == 0) {
                iCharAt *= 3;
            }
            i += iCharAt;
        }
        int i3 = 10 - (i % 10);
        if (i3 == 10) {
            i3 = 0;
        }
        sb.append(i3);
        C1365t[] c1365tArr = c1273d.f2427c.f2426c;
        C1365t[] c1365tArr2 = c1273d2.f2427c.f2426c;
        return new C1363r(sb.toString(), null, new C1365t[]{c1365tArr[0], c1365tArr[1], c1365tArr2[0], c1365tArr2[1]}, EnumC1138a.RSS_14);
    }

    /* JADX INFO: renamed from: b */
    private static boolean m2150b(C1273d c1273d, C1273d c1273d2) {
        int i = (c1273d.f2423b + (c1273d2.f2423b * 16)) % 79;
        int i2 = (c1273d.f2427c.f2424a * 9) + c1273d2.f2427c.f2424a;
        if (i2 > 72) {
            i2--;
        }
        if (i2 > 8) {
            i2--;
        }
        return i == i2;
    }

    /* JADX INFO: renamed from: a */
    private C1273d m2145a(C1190a c1190a, boolean z, int i, Map<EnumC1233e, ?> map) {
        int i2;
        int i3;
        try {
            int[] iArr = this.f2350a;
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            iArr[3] = 0;
            int i4 = c1190a.f2065b;
            int i5 = 0;
            boolean z2 = false;
            while (i5 < i4) {
                z2 = !c1190a.m1755a(i5);
                if (z == z2) {
                    break;
                }
                i5++;
            }
            int i6 = i5;
            int i7 = 0;
            while (i5 < i4) {
                if (c1190a.m1755a(i5) != z2) {
                    iArr[i7] = iArr[i7] + 1;
                } else {
                    if (i7 != 3) {
                        i7++;
                    } else {
                        if (m2041a(iArr)) {
                            int[] iArr2 = {i6, i5};
                            boolean zM1755a = c1190a.m1755a(iArr2[0]);
                            int i8 = iArr2[0] - 1;
                            while (i8 >= 0 && zM1755a != c1190a.m1755a(i8)) {
                                i8--;
                            }
                            int i9 = i8 + 1;
                            int i10 = iArr2[0] - i9;
                            int[] iArr3 = this.f2350a;
                            System.arraycopy(iArr3, 0, iArr3, 1, iArr3.length - 1);
                            iArr3[0] = i10;
                            int iA = m2039a(iArr3, f2435m);
                            int i11 = iArr2[1];
                            if (z) {
                                i3 = (c1190a.f2065b - 1) - i9;
                                i2 = (c1190a.f2065b - 1) - i11;
                            } else {
                                i2 = i11;
                                i3 = i9;
                            }
                            C1272c c1272c = new C1272c(iA, new int[]{i9, iArr2[1]}, i3, i2, i);
                            if ((map == null ? null : (InterfaceC1366u) map.get(EnumC1233e.NEED_RESULT_POINT_CALLBACK)) != null) {
                                float f = (iArr2[0] + iArr2[1]) / 2.0f;
                                if (z) {
                                    f = (c1190a.f2065b - 1) - f;
                                }
                                new C1365t(f, i);
                            }
                            C1271b c1271bM2143a = m2143a(c1190a, c1272c, true);
                            C1271b c1271bM2143a2 = m2143a(c1190a, c1272c, false);
                            return new C1273d((c1271bM2143a.f2422a * 1597) + c1271bM2143a2.f2422a, c1271bM2143a.f2423b + (c1271bM2143a2.f2423b * 4), c1272c);
                        }
                        i6 += iArr[0] + iArr[1];
                        iArr[0] = iArr[2];
                        iArr[1] = iArr[3];
                        iArr[2] = 0;
                        iArr[3] = 0;
                        i7--;
                    }
                    iArr[i7] = 1;
                    z2 = !z2;
                }
                i5++;
            }
            throw C1358m.m2611a();
        } catch (C1358m unused) {
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:37:0x009c A[PHI: r12 r13
  0x009c: PHI (r12v16 boolean) = (r12v6 boolean), (r12v20 boolean) binds: [B:50:0x00b4, B:36:0x009a] A[DONT_GENERATE, DONT_INLINE]
  0x009c: PHI (r13v11 boolean) = (r13v1 boolean), (r13v13 boolean) binds: [B:50:0x00b4, B:36:0x009a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:39:0x00a1 A[PHI: r12 r13
  0x00a1: PHI (r12v10 boolean) = (r12v6 boolean), (r12v20 boolean) binds: [B:52:0x00b7, B:38:0x009f] A[DONT_GENERATE, DONT_INLINE]
  0x00a1: PHI (r13v5 boolean) = (r13v1 boolean), (r13v13 boolean) binds: [B:52:0x00b7, B:38:0x009f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:40:0x00a3 A[PHI: r12 r13
  0x00a3: PHI (r12v8 boolean) = (r12v6 boolean), (r12v20 boolean) binds: [B:52:0x00b7, B:38:0x009f] A[DONT_GENERATE, DONT_INLINE]
  0x00a3: PHI (r13v3 boolean) = (r13v1 boolean), (r13v13 boolean) binds: [B:52:0x00b7, B:38:0x009f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX INFO: renamed from: a */
    private C1271b m2143a(C1190a c1190a, C1272c c1272c, boolean z) throws C1358m {
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        int[] iArr = this.f2351b;
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = 0;
        }
        if (z) {
            m2195b(c1190a, c1272c.f2425b[0], iArr);
        } else {
            m2193a(c1190a, c1272c.f2425b[1] + 1, iArr);
            int i2 = 0;
            for (int length = iArr.length - 1; i2 < length; length--) {
                int i3 = iArr[i2];
                iArr[i2] = iArr[length];
                iArr[length] = i3;
                i2++;
            }
        }
        int i4 = z ? 16 : 15;
        float fM1766a = C1191a.m1766a(iArr) / i4;
        int[] iArr2 = this.f2354e;
        int[] iArr3 = this.f2355f;
        float[] fArr = this.f2352c;
        float[] fArr2 = this.f2353d;
        for (int i5 = 0; i5 < iArr.length; i5++) {
            float f = iArr[i5] / fM1766a;
            int i6 = (int) (0.5f + f);
            if (i6 <= 0) {
                i6 = 1;
            } else if (i6 > 8) {
                i6 = 8;
            }
            int i7 = i5 / 2;
            if ((i5 & 1) == 0) {
                iArr2[i7] = i6;
                fArr[i7] = f - i6;
            } else {
                iArr3[i7] = i6;
                fArr2[i7] = f - i6;
            }
        }
        int iM1766a = C1191a.m1766a(this.f2354e);
        int iM1766a2 = C1191a.m1766a(this.f2355f);
        if (z) {
            if (iM1766a > 12) {
                z2 = false;
                z3 = true;
            } else {
                z2 = iM1766a < 4;
                z3 = false;
            }
            if (iM1766a2 > 12) {
                z4 = false;
                z5 = true;
            } else {
                if (iM1766a2 < 4) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                z5 = false;
            }
        } else {
            if (iM1766a > 11) {
                z2 = false;
                z3 = true;
            } else {
                z2 = iM1766a < 5;
                z3 = false;
            }
            if (iM1766a2 > 10) {
                z4 = false;
                z5 = true;
            } else {
                if (iM1766a2 < 4) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                z5 = false;
            }
        }
        int i8 = (iM1766a + iM1766a2) - i4;
        boolean z6 = (iM1766a & 1) == z;
        boolean z7 = (iM1766a2 & 1) == 1;
        if (i8 == 1) {
            if (!z6) {
                if (!z7) {
                    throw C1358m.m2611a();
                }
                z5 = true;
            } else {
                if (z7) {
                    throw C1358m.m2611a();
                }
                z3 = true;
            }
        } else if (i8 != -1) {
            if (i8 != 0) {
                throw C1358m.m2611a();
            }
            if (z6) {
                if (!z7) {
                    throw C1358m.m2611a();
                }
                if (iM1766a < iM1766a2) {
                    z2 = true;
                    z5 = true;
                } else {
                    z3 = true;
                    z4 = true;
                }
            } else if (z7) {
                throw C1358m.m2611a();
            }
        } else if (!z6) {
            if (!z7) {
                throw C1358m.m2611a();
            }
            z4 = true;
        } else {
            if (z7) {
                throw C1358m.m2611a();
            }
            z2 = true;
        }
        if (z2) {
            if (z3) {
                throw C1358m.m2611a();
            }
            m2040a(this.f2354e, this.f2352c);
        }
        if (z3) {
            m2043b(this.f2354e, this.f2352c);
        }
        if (z4) {
            if (z5) {
                throw C1358m.m2611a();
            }
            m2040a(this.f2355f, this.f2352c);
        }
        if (z5) {
            m2043b(this.f2355f, this.f2353d);
        }
        int i9 = 0;
        int i10 = 0;
        for (int length2 = iArr2.length - 1; length2 >= 0; length2--) {
            i9 = (i9 * 9) + iArr2[length2];
            i10 += iArr2[length2];
        }
        int i11 = 0;
        int i12 = 0;
        for (int length3 = iArr3.length - 1; length3 >= 0; length3--) {
            i11 = (i11 * 9) + iArr3[length3];
            i12 += iArr3[length3];
        }
        int i13 = i9 + (i11 * 3);
        if (!z) {
            if ((i12 & 1) != 0 || i12 > 10 || i12 < 4) {
                throw C1358m.m2611a();
            }
            int i14 = (10 - i12) / 2;
            int i15 = f2434l[i14];
            return new C1271b((C1275f.m2152a(iArr3, 9 - i15, false) * f2430h[i14]) + C1275f.m2152a(iArr2, i15, true) + f2432j[i14], i13);
        }
        if ((i10 & 1) != 0 || i10 > 12 || i10 < 4) {
            throw C1358m.m2611a();
        }
        int i16 = (12 - i10) / 2;
        int i17 = f2433k[i16];
        return new C1271b((C1275f.m2152a(iArr2, i17, false) * f2429g[i16]) + C1275f.m2152a(iArr3, 9 - i17, true) + f2431i[i16], i13);
    }

    /* JADX INFO: renamed from: a */
    private int[] m2149a(C1190a c1190a, boolean z) throws C1358m {
        int[] iArr = this.f2350a;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        int i = c1190a.f2065b;
        int i2 = 0;
        boolean z2 = false;
        while (i2 < i) {
            z2 = !c1190a.m1755a(i2);
            if (z == z2) {
                break;
            }
            i2++;
        }
        int i3 = i2;
        int i4 = 0;
        while (i2 < i) {
            if (c1190a.m1755a(i2) != z2) {
                iArr[i4] = iArr[i4] + 1;
            } else {
                if (i4 != 3) {
                    i4++;
                } else {
                    if (m2041a(iArr)) {
                        return new int[]{i3, i2};
                    }
                    i3 += iArr[0] + iArr[1];
                    iArr[0] = iArr[2];
                    iArr[1] = iArr[3];
                    iArr[2] = 0;
                    iArr[3] = 0;
                    i4--;
                }
                iArr[i4] = 1;
                z2 = !z2;
            }
            i2++;
        }
        throw C1358m.m2611a();
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0021  */
    /* JADX WARN: Code duplicated, block: B:13:0x0023 A[PHI: r5 r6
  0x0023: PHI (r5v13 boolean) = (r5v4 boolean), (r5v17 boolean) binds: [B:29:0x0042, B:12:0x0021] A[DONT_GENERATE, DONT_INLINE]
  0x0023: PHI (r6v11 boolean) = (r6v1 boolean), (r6v17 boolean) binds: [B:29:0x0042, B:12:0x0021] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:14:0x0026 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:15:0x0028  */
    /* JADX WARN: Code duplicated, block: B:16:0x002a A[PHI: r5 r6
  0x002a: PHI (r5v9 boolean) = (r5v4 boolean), (r5v16 boolean) binds: [B:31:0x0045, B:15:0x0028] A[DONT_GENERATE, DONT_INLINE]
  0x002a: PHI (r6v5 boolean) = (r6v1 boolean), (r6v16 boolean) binds: [B:31:0x0045, B:15:0x0028] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:17:0x002c  */
    /* JADX WARN: Code duplicated, block: B:18:0x002e A[PHI: r5 r6
  0x002e: PHI (r5v7 boolean) = (r5v4 boolean), (r5v15 boolean) binds: [B:31:0x0045, B:17:0x002c] A[DONT_GENERATE, DONT_INLINE]
  0x002e: PHI (r6v3 boolean) = (r6v1 boolean), (r6v15 boolean) binds: [B:31:0x0045, B:17:0x002c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:31:0x0045 A[DONT_INVERT] */
    /* JADX INFO: renamed from: a */
    private void m2148a(boolean z, int i) throws C1358m {
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        int iM1766a = C1191a.m1766a(this.f2354e);
        int iM1766a2 = C1191a.m1766a(this.f2355f);
        if (z) {
            if (iM1766a > 12) {
                z6 = true;
            } else {
                if (iM1766a < 4) {
                    z6 = false;
                    z7 = true;
                } else {
                    z6 = false;
                }
                if (iM1766a2 > 12) {
                    z2 = z6;
                    z3 = z7;
                    z4 = false;
                    z5 = true;
                } else if (iM1766a2 < 4) {
                    z2 = z6;
                    z3 = z7;
                    z4 = true;
                    z5 = false;
                } else {
                    z2 = z6;
                    z3 = z7;
                    z4 = false;
                    z5 = false;
                }
            }
            z7 = false;
            if (iM1766a2 > 12) {
                z2 = z6;
                z3 = z7;
                z4 = false;
                z5 = true;
            } else if (iM1766a2 < 4) {
                z2 = z6;
                z3 = z7;
                z4 = true;
                z5 = false;
            } else {
                z2 = z6;
                z3 = z7;
                z4 = false;
                z5 = false;
            }
        } else {
            if (iM1766a > 11) {
                z2 = true;
            } else {
                if (iM1766a < 5) {
                    z2 = false;
                    z3 = true;
                } else {
                    z2 = false;
                }
                if (iM1766a2 > 10) {
                    z4 = false;
                    z5 = true;
                } else {
                    if (iM1766a2 < 4) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    z5 = false;
                }
            }
            z3 = false;
            if (iM1766a2 > 10) {
                z4 = false;
                z5 = true;
            } else {
                if (iM1766a2 < 4) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                z5 = false;
            }
        }
        int i2 = (iM1766a + iM1766a2) - i;
        boolean z8 = (iM1766a & 1) == z;
        boolean z9 = (iM1766a2 & 1) == 1;
        if (i2 == 1) {
            if (z8) {
                if (z9) {
                    throw C1358m.m2611a();
                }
                z2 = true;
            } else {
                if (!z9) {
                    throw C1358m.m2611a();
                }
                z5 = true;
            }
        } else if (i2 == -1) {
            if (z8) {
                if (z9) {
                    throw C1358m.m2611a();
                }
                z3 = true;
            } else {
                if (!z9) {
                    throw C1358m.m2611a();
                }
                z4 = true;
            }
        } else {
            if (i2 != 0) {
                throw C1358m.m2611a();
            }
            if (z8) {
                if (!z9) {
                    throw C1358m.m2611a();
                }
                if (iM1766a < iM1766a2) {
                    z3 = true;
                    z5 = true;
                } else {
                    z4 = true;
                    z2 = true;
                }
            } else if (z9) {
                throw C1358m.m2611a();
            }
        }
        if (z3) {
            if (z2) {
                throw C1358m.m2611a();
            }
            m2040a(this.f2354e, this.f2352c);
        }
        if (z2) {
            m2043b(this.f2354e, this.f2352c);
        }
        if (z4) {
            if (z5) {
                throw C1358m.m2611a();
            }
            m2040a(this.f2355f, this.f2352c);
        }
        if (z5) {
            m2043b(this.f2355f, this.f2353d);
        }
    }
}
