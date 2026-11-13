package com.google.p028a.p056i.p058b;

import com.google.p028a.C1358m;
import com.google.p028a.C1365t;
import com.google.p028a.EnumC1233e;
import com.google.p028a.InterfaceC1366u;
import com.google.p028a.p035c.C1194b;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.i.b.e */
/* JADX INFO: compiled from: FinderPatternFinder.java */
/* JADX INFO: loaded from: classes.dex */
public class C1347e {

    /* JADX INFO: renamed from: a */
    protected static final int f2782a = 3;

    /* JADX INFO: renamed from: b */
    protected static final int f2783b = 97;

    /* JADX INFO: renamed from: e */
    private static final int f2784e = 2;

    /* JADX INFO: renamed from: c */
    protected final C1194b f2785c;

    /* JADX INFO: renamed from: d */
    protected final List<C1346d> f2786d;

    /* JADX INFO: renamed from: f */
    private boolean f2787f;

    /* JADX INFO: renamed from: g */
    private final int[] f2788g;

    /* JADX INFO: renamed from: h */
    private final InterfaceC1366u f2789h;

    public C1347e(C1194b c1194b) {
        this(c1194b, null);
    }

    public C1347e(C1194b c1194b, InterfaceC1366u interfaceC1366u) {
        this.f2785c = c1194b;
        this.f2786d = new ArrayList();
        this.f2788g = new int[5];
        this.f2789h = interfaceC1366u;
    }

    /* JADX INFO: renamed from: a */
    private C1194b m2514a() {
        return this.f2785c;
    }

    /* JADX INFO: renamed from: b */
    private List<C1346d> m2518b() {
        return this.f2786d;
    }

    /* JADX INFO: renamed from: a */
    final C1348f m2527a(Map<EnumC1233e, ?> map) throws C1358m {
        int iAbs;
        byte b2 = 0;
        boolean z = map != null && map.containsKey(EnumC1233e.TRY_HARDER);
        int i = this.f2785c.f2078b;
        int i2 = this.f2785c.f2077a;
        int i3 = (i * 3) / 388;
        if (i3 < 3 || z) {
            i3 = 3;
        }
        int[] iArr = new int[5];
        int i4 = i3 - 1;
        int i5 = i3;
        boolean zM2525e = false;
        while (i4 < i && !zM2525e) {
            m2519b(iArr);
            boolean zM2525e2 = zM2525e;
            int i6 = i5;
            int i7 = 0;
            int i8 = 0;
            while (i7 < i2) {
                if (this.f2785c.m1789a(i7, i4)) {
                    if ((i8 & 1) == 1) {
                        i8++;
                    }
                    iArr[i8] = iArr[i8] + 1;
                } else if ((i8 & 1) != 0) {
                    iArr[i8] = iArr[i8] + 1;
                } else if (i8 == 4) {
                    if (m2516a(iArr) && m2528a(iArr, i4, i7)) {
                        if (this.f2787f) {
                            zM2525e2 = m2525e();
                        } else {
                            if (this.f2786d.size() <= 1) {
                                iAbs = 0;
                                break;
                            }
                            C1346d c1346d = null;
                            Iterator<C1346d> it2 = this.f2786d.iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    iAbs = 0;
                                    break;
                                }
                                C1346d next = it2.next();
                                if (next.f2781d >= 2) {
                                    if (c1346d != null) {
                                        this.f2787f = true;
                                        iAbs = ((int) (Math.abs(c1346d.f2858a - next.f2858a) - Math.abs(c1346d.f2859b - next.f2859b))) / 2;
                                        break;
                                    }
                                    c1346d = next;
                                }
                            }
                            if (iAbs > iArr[2]) {
                                i4 += (iAbs - iArr[2]) - 2;
                                i7 = i2 - 1;
                            }
                        }
                        m2519b(iArr);
                        i8 = 0;
                        i6 = 2;
                    } else {
                        m2521c(iArr);
                        i8 = 3;
                    }
                } else {
                    i8++;
                    iArr[i8] = iArr[i8] + 1;
                }
                i7++;
            }
            if (m2516a(iArr) && m2528a(iArr, i4, i2)) {
                int i9 = iArr[0];
                if (this.f2787f) {
                    i5 = i9;
                    zM2525e = m2525e();
                } else {
                    i5 = i9;
                }
                i4 += i5;
            } else {
                i5 = i6;
            }
            zM2525e = zM2525e2;
            i4 += i5;
        }
        int size = this.f2786d.size();
        if (size < 3) {
            throw C1358m.m2611a();
        }
        float f = 0.0f;
        if (size > 3) {
            Iterator<C1346d> it3 = this.f2786d.iterator();
            float f2 = 0.0f;
            float f3 = 0.0f;
            while (it3.hasNext()) {
                float f4 = it3.next().f2780c;
                f2 += f4;
                f3 += f4 * f4;
            }
            float f5 = size;
            float f6 = f2 / f5;
            float fSqrt = (float) Math.sqrt((f3 / f5) - (f6 * f6));
            Collections.sort(this.f2786d, new b(f6, b2));
            float fMax = Math.max(0.2f * f6, fSqrt);
            int i10 = 0;
            while (i10 < this.f2786d.size() && this.f2786d.size() > 3) {
                if (Math.abs(this.f2786d.get(i10).f2780c - f6) > fMax) {
                    this.f2786d.remove(i10);
                    i10--;
                }
                i10++;
            }
        }
        if (this.f2786d.size() > 3) {
            Iterator<C1346d> it4 = this.f2786d.iterator();
            while (it4.hasNext()) {
                f += it4.next().f2780c;
            }
            Collections.sort(this.f2786d, new a(f / this.f2786d.size(), b2));
            this.f2786d.subList(3, this.f2786d.size()).clear();
        }
        C1346d[] c1346dArr = {this.f2786d.get(0), this.f2786d.get(1), this.f2786d.get(2)};
        C1365t.m2629a(c1346dArr);
        return new C1348f(c1346dArr);
    }

    /* JADX INFO: renamed from: a */
    private static float m2513a(int[] iArr, int i) {
        return ((i - iArr[4]) - iArr[3]) - (iArr[2] / 2.0f);
    }

    /* JADX INFO: renamed from: a */
    protected static boolean m2516a(int[] iArr) {
        int i = 0;
        for (int i2 = 0; i2 < 5; i2++) {
            int i3 = iArr[i2];
            if (i3 == 0) {
                return false;
            }
            i += i3;
        }
        if (i < 7) {
            return false;
        }
        float f = i / 7.0f;
        float f2 = f / 2.0f;
        return Math.abs(f - ((float) iArr[0])) < f2 && Math.abs(f - ((float) iArr[1])) < f2 && Math.abs((f * 3.0f) - ((float) iArr[2])) < 3.0f * f2 && Math.abs(f - ((float) iArr[3])) < f2 && Math.abs(f - ((float) iArr[4])) < f2;
    }

    /* JADX INFO: renamed from: d */
    private static boolean m2524d(int[] iArr) {
        int i = 0;
        for (int i2 = 0; i2 < 5; i2++) {
            int i3 = iArr[i2];
            if (i3 == 0) {
                return false;
            }
            i += i3;
        }
        if (i < 7) {
            return false;
        }
        float f = i / 7.0f;
        float f2 = f / 1.333f;
        return Math.abs(f - ((float) iArr[0])) < f2 && Math.abs(f - ((float) iArr[1])) < f2 && Math.abs((f * 3.0f) - ((float) iArr[2])) < 3.0f * f2 && Math.abs(f - ((float) iArr[3])) < f2 && Math.abs(f - ((float) iArr[4])) < f2;
    }

    /* JADX INFO: renamed from: c */
    private int[] m2522c() {
        m2519b(this.f2788g);
        return this.f2788g;
    }

    /* JADX INFO: renamed from: b */
    protected static void m2519b(int[] iArr) {
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = 0;
        }
    }

    /* JADX INFO: renamed from: c */
    protected static void m2521c(int[] iArr) {
        iArr[0] = iArr[2];
        iArr[1] = iArr[3];
        iArr[2] = iArr[4];
        iArr[3] = 1;
        iArr[4] = 0;
    }

    /* JADX INFO: renamed from: a */
    private boolean m2515a(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int[] iArrM2522c = m2522c();
        int i6 = 0;
        while (i >= i6 && i2 >= i6 && this.f2785c.m1789a(i2 - i6, i - i6)) {
            iArrM2522c[2] = iArrM2522c[2] + 1;
            i6++;
        }
        if (iArrM2522c[2] == 0) {
            return false;
        }
        while (i >= i6 && i2 >= i6 && !this.f2785c.m1789a(i2 - i6, i - i6)) {
            iArrM2522c[1] = iArrM2522c[1] + 1;
            i6++;
        }
        if (iArrM2522c[1] == 0) {
            return false;
        }
        while (i >= i6 && i2 >= i6 && this.f2785c.m1789a(i2 - i6, i - i6)) {
            iArrM2522c[0] = iArrM2522c[0] + 1;
            i6++;
        }
        if (iArrM2522c[0] == 0) {
            return false;
        }
        int i7 = this.f2785c.f2078b;
        int i8 = this.f2785c.f2077a;
        int i9 = 1;
        while (true) {
            int i10 = i + i9;
            if (i10 >= i7 || (i5 = i2 + i9) >= i8 || !this.f2785c.m1789a(i5, i10)) {
                break;
            }
            iArrM2522c[2] = iArrM2522c[2] + 1;
            i9++;
        }
        while (true) {
            int i11 = i + i9;
            if (i11 >= i7 || (i4 = i2 + i9) >= i8 || this.f2785c.m1789a(i4, i11)) {
                break;
            }
            iArrM2522c[3] = iArrM2522c[3] + 1;
            i9++;
        }
        if (iArrM2522c[3] == 0) {
            return false;
        }
        while (true) {
            int i12 = i + i9;
            if (i12 >= i7 || (i3 = i2 + i9) >= i8 || !this.f2785c.m1789a(i3, i12)) {
                break;
            }
            iArrM2522c[4] = iArrM2522c[4] + 1;
            i9++;
        }
        if (iArrM2522c[4] == 0) {
            return false;
        }
        int i13 = 0;
        for (int i14 = 0; i14 < 5; i14++) {
            int i15 = iArrM2522c[i14];
            if (i15 == 0) {
                return false;
            }
            i13 += i15;
        }
        if (i13 >= 7) {
            float f = i13 / 7.0f;
            float f2 = f / 1.333f;
            if (Math.abs(f - iArrM2522c[0]) < f2 && Math.abs(f - iArrM2522c[1]) < f2 && Math.abs((f * 3.0f) - iArrM2522c[2]) < 3.0f * f2 && Math.abs(f - iArrM2522c[3]) < f2 && Math.abs(f - iArrM2522c[4]) < f2) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: a */
    private float m2512a(int i, int i2, int i3, int i4) {
        C1194b c1194b = this.f2785c;
        int i5 = c1194b.f2078b;
        int[] iArrM2522c = m2522c();
        int i6 = i;
        while (i6 >= 0 && c1194b.m1789a(i2, i6)) {
            iArrM2522c[2] = iArrM2522c[2] + 1;
            i6--;
        }
        if (i6 < 0) {
            return Float.NaN;
        }
        while (i6 >= 0 && !c1194b.m1789a(i2, i6) && iArrM2522c[1] <= i3) {
            iArrM2522c[1] = iArrM2522c[1] + 1;
            i6--;
        }
        if (i6 < 0 || iArrM2522c[1] > i3) {
            return Float.NaN;
        }
        while (i6 >= 0 && c1194b.m1789a(i2, i6) && iArrM2522c[0] <= i3) {
            iArrM2522c[0] = iArrM2522c[0] + 1;
            i6--;
        }
        if (iArrM2522c[0] > i3) {
            return Float.NaN;
        }
        int i7 = i + 1;
        while (i7 < i5 && c1194b.m1789a(i2, i7)) {
            iArrM2522c[2] = iArrM2522c[2] + 1;
            i7++;
        }
        if (i7 == i5) {
            return Float.NaN;
        }
        while (i7 < i5 && !c1194b.m1789a(i2, i7) && iArrM2522c[3] < i3) {
            iArrM2522c[3] = iArrM2522c[3] + 1;
            i7++;
        }
        if (i7 == i5 || iArrM2522c[3] >= i3) {
            return Float.NaN;
        }
        while (i7 < i5 && c1194b.m1789a(i2, i7) && iArrM2522c[4] < i3) {
            iArrM2522c[4] = iArrM2522c[4] + 1;
            i7++;
        }
        if (iArrM2522c[4] < i3 && Math.abs(((((iArrM2522c[0] + iArrM2522c[1]) + iArrM2522c[2]) + iArrM2522c[3]) + iArrM2522c[4]) - i4) * 5 < i4 * 2 && m2516a(iArrM2522c)) {
            return m2513a(iArrM2522c, i7);
        }
        return Float.NaN;
    }

    /* JADX INFO: renamed from: b */
    private float m2517b(int i, int i2, int i3, int i4) {
        C1194b c1194b = this.f2785c;
        int i5 = c1194b.f2077a;
        int[] iArrM2522c = m2522c();
        int i6 = i;
        while (i6 >= 0 && c1194b.m1789a(i6, i2)) {
            iArrM2522c[2] = iArrM2522c[2] + 1;
            i6--;
        }
        if (i6 < 0) {
            return Float.NaN;
        }
        while (i6 >= 0 && !c1194b.m1789a(i6, i2) && iArrM2522c[1] <= i3) {
            iArrM2522c[1] = iArrM2522c[1] + 1;
            i6--;
        }
        if (i6 < 0 || iArrM2522c[1] > i3) {
            return Float.NaN;
        }
        while (i6 >= 0 && c1194b.m1789a(i6, i2) && iArrM2522c[0] <= i3) {
            iArrM2522c[0] = iArrM2522c[0] + 1;
            i6--;
        }
        if (iArrM2522c[0] > i3) {
            return Float.NaN;
        }
        int i7 = i + 1;
        while (i7 < i5 && c1194b.m1789a(i7, i2)) {
            iArrM2522c[2] = iArrM2522c[2] + 1;
            i7++;
        }
        if (i7 == i5) {
            return Float.NaN;
        }
        while (i7 < i5 && !c1194b.m1789a(i7, i2) && iArrM2522c[3] < i3) {
            iArrM2522c[3] = iArrM2522c[3] + 1;
            i7++;
        }
        if (i7 == i5 || iArrM2522c[3] >= i3) {
            return Float.NaN;
        }
        while (i7 < i5 && c1194b.m1789a(i7, i2) && iArrM2522c[4] < i3) {
            iArrM2522c[4] = iArrM2522c[4] + 1;
            i7++;
        }
        if (iArrM2522c[4] < i3 && Math.abs(((((iArrM2522c[0] + iArrM2522c[1]) + iArrM2522c[2]) + iArrM2522c[3]) + iArrM2522c[4]) - i4) * 5 < i4 && m2516a(iArrM2522c)) {
            return m2513a(iArrM2522c, i7);
        }
        return Float.NaN;
    }

    @Deprecated
    /* JADX INFO: renamed from: b */
    private boolean m2520b(int[] iArr, int i, int i2) {
        return m2528a(iArr, i, i2);
    }

    /* JADX WARN: Code duplicated, block: B:189:0x02ce A[EDGE_INSN: B:189:0x02ce->B:190:0x02cf BREAK  A[LOOP:8: B:172:0x026f->B:175:0x0275]] */
    /* JADX WARN: Code duplicated, block: B:203:0x0314  */
    /* JADX WARN: Code duplicated, block: B:69:0x0109  */
    /* JADX WARN: Code duplicated, block: B:9:0x003d  */
    /* JADX INFO: renamed from: a */
    protected final boolean m2528a(int[] iArr, int i, int i2) {
        float fM2513a;
        float fM2513a2;
        boolean z;
        boolean z2;
        boolean z3;
        int i3;
        int i4;
        int i5;
        int i6 = iArr[0] + iArr[1] + iArr[2] + iArr[3] + iArr[4];
        int iM2513a = (int) m2513a(iArr, i2);
        int i7 = iArr[2];
        C1194b c1194b = this.f2785c;
        int i8 = c1194b.f2078b;
        int[] iArrM2522c = m2522c();
        int i9 = i;
        while (i9 >= 0 && c1194b.m1789a(iM2513a, i9)) {
            iArrM2522c[2] = iArrM2522c[2] + 1;
            i9--;
        }
        if (i9 < 0) {
            fM2513a = Float.NaN;
        } else {
            while (i9 >= 0 && !c1194b.m1789a(iM2513a, i9) && iArrM2522c[1] <= i7) {
                iArrM2522c[1] = iArrM2522c[1] + 1;
                i9--;
            }
            if (i9 < 0 || iArrM2522c[1] > i7) {
                fM2513a = Float.NaN;
            } else {
                while (i9 >= 0 && c1194b.m1789a(iM2513a, i9) && iArrM2522c[0] <= i7) {
                    iArrM2522c[0] = iArrM2522c[0] + 1;
                    i9--;
                }
                if (iArrM2522c[0] > i7) {
                    fM2513a = Float.NaN;
                } else {
                    int i10 = i + 1;
                    while (i10 < i8 && c1194b.m1789a(iM2513a, i10)) {
                        iArrM2522c[2] = iArrM2522c[2] + 1;
                        i10++;
                    }
                    if (i10 == i8) {
                        fM2513a = Float.NaN;
                    } else {
                        while (i10 < i8 && !c1194b.m1789a(iM2513a, i10) && iArrM2522c[3] < i7) {
                            iArrM2522c[3] = iArrM2522c[3] + 1;
                            i10++;
                        }
                        if (i10 == i8 || iArrM2522c[3] >= i7) {
                            fM2513a = Float.NaN;
                        } else {
                            while (i10 < i8 && c1194b.m1789a(iM2513a, i10) && iArrM2522c[4] < i7) {
                                iArrM2522c[4] = iArrM2522c[4] + 1;
                                i10++;
                            }
                            if (iArrM2522c[4] < i7 && Math.abs(((((iArrM2522c[0] + iArrM2522c[1]) + iArrM2522c[2]) + iArrM2522c[3]) + iArrM2522c[4]) - i6) * 5 < i6 * 2 && m2516a(iArrM2522c)) {
                                fM2513a = m2513a(iArrM2522c, i10);
                            } else {
                                fM2513a = Float.NaN;
                            }
                        }
                    }
                }
            }
        }
        if (Float.isNaN(fM2513a)) {
            return false;
        }
        int i11 = (int) fM2513a;
        int i12 = iArr[2];
        C1194b c1194b2 = this.f2785c;
        int i13 = c1194b2.f2077a;
        int[] iArrM2522c2 = m2522c();
        int i14 = iM2513a;
        while (i14 >= 0 && c1194b2.m1789a(i14, i11)) {
            iArrM2522c2[2] = iArrM2522c2[2] + 1;
            i14--;
        }
        if (i14 < 0) {
            fM2513a2 = Float.NaN;
        } else {
            while (i14 >= 0 && !c1194b2.m1789a(i14, i11) && iArrM2522c2[1] <= i12) {
                iArrM2522c2[1] = iArrM2522c2[1] + 1;
                i14--;
            }
            if (i14 < 0 || iArrM2522c2[1] > i12) {
                fM2513a2 = Float.NaN;
            } else {
                while (i14 >= 0 && c1194b2.m1789a(i14, i11) && iArrM2522c2[0] <= i12) {
                    iArrM2522c2[0] = iArrM2522c2[0] + 1;
                    i14--;
                }
                if (iArrM2522c2[0] > i12) {
                    fM2513a2 = Float.NaN;
                } else {
                    int i15 = iM2513a + 1;
                    while (i15 < i13 && c1194b2.m1789a(i15, i11)) {
                        iArrM2522c2[2] = iArrM2522c2[2] + 1;
                        i15++;
                    }
                    if (i15 == i13) {
                        fM2513a2 = Float.NaN;
                    } else {
                        while (i15 < i13 && !c1194b2.m1789a(i15, i11) && iArrM2522c2[3] < i12) {
                            iArrM2522c2[3] = iArrM2522c2[3] + 1;
                            i15++;
                        }
                        if (i15 == i13 || iArrM2522c2[3] >= i12) {
                            fM2513a2 = Float.NaN;
                        } else {
                            while (i15 < i13 && c1194b2.m1789a(i15, i11) && iArrM2522c2[4] < i12) {
                                iArrM2522c2[4] = iArrM2522c2[4] + 1;
                                i15++;
                            }
                            if (iArrM2522c2[4] < i12 && Math.abs(((((iArrM2522c2[0] + iArrM2522c2[1]) + iArrM2522c2[2]) + iArrM2522c2[3]) + iArrM2522c2[4]) - i6) * 5 < i6 && m2516a(iArrM2522c2)) {
                                fM2513a2 = m2513a(iArrM2522c2, i15);
                            } else {
                                fM2513a2 = Float.NaN;
                            }
                        }
                    }
                }
            }
        }
        if (Float.isNaN(fM2513a2)) {
            return false;
        }
        int i16 = (int) fM2513a2;
        int[] iArrM2522c3 = m2522c();
        int i17 = 0;
        while (i11 >= i17 && i16 >= i17 && this.f2785c.m1789a(i16 - i17, i11 - i17)) {
            iArrM2522c3[2] = iArrM2522c3[2] + 1;
            i17++;
        }
        if (iArrM2522c3[2] == 0) {
            z = false;
            break;
        }
        while (i11 >= i17 && i16 >= i17 && !this.f2785c.m1789a(i16 - i17, i11 - i17)) {
            iArrM2522c3[1] = iArrM2522c3[1] + 1;
            i17++;
        }
        if (iArrM2522c3[1] == 0) {
            z = false;
            break;
        }
        while (i11 >= i17 && i16 >= i17 && this.f2785c.m1789a(i16 - i17, i11 - i17)) {
            iArrM2522c3[0] = iArrM2522c3[0] + 1;
            i17++;
        }
        if (iArrM2522c3[0] == 0) {
            z = false;
            break;
        }
        int i18 = this.f2785c.f2078b;
        int i19 = this.f2785c.f2077a;
        int i20 = 1;
        while (true) {
            int i21 = i11 + i20;
            if (i21 >= i18 || (i5 = i16 + i20) >= i19 || !this.f2785c.m1789a(i5, i21)) {
                break;
            }
            iArrM2522c3[2] = iArrM2522c3[2] + 1;
            i20++;
        }
        while (true) {
            int i22 = i11 + i20;
            if (i22 >= i18 || (i4 = i16 + i20) >= i19 || this.f2785c.m1789a(i4, i22)) {
                break;
            }
            iArrM2522c3[3] = iArrM2522c3[3] + 1;
            i20++;
        }
        if (iArrM2522c3[3] == 0) {
            z = false;
            break;
        }
        while (true) {
            int i23 = i11 + i20;
            if (i23 >= i18 || (i3 = i16 + i20) >= i19 || !this.f2785c.m1789a(i3, i23)) {
                break;
            }
            iArrM2522c3[4] = iArrM2522c3[4] + 1;
            i20++;
        }
        if (iArrM2522c3[4] == 0) {
            z = false;
            break;
        }
        int i24 = 0;
        int i25 = 0;
        while (true) {
            if (i24 >= 5) {
                if (i25 >= 7) {
                    float f = i25 / 7.0f;
                    float f2 = f / 1.333f;
                    if (Math.abs(f - iArrM2522c3[0]) < f2 && Math.abs(f - iArrM2522c3[1]) < f2 && Math.abs((f * 3.0f) - iArrM2522c3[2]) < 3.0f * f2 && Math.abs(f - iArrM2522c3[3]) < f2 && Math.abs(f - iArrM2522c3[4]) < f2) {
                        z = true;
                        break;
                    }
                    break;
                }
            } else {
                int i26 = iArrM2522c3[i24];
                if (i26 != 0) {
                    i25 += i26;
                    i24++;
                }
            }
            z = false;
            break;
        }
        if (!z) {
            return false;
        }
        float f3 = i6 / 7.0f;
        int i27 = 0;
        while (true) {
            if (i27 >= this.f2786d.size()) {
                z2 = false;
                break;
            }
            C1346d c1346d = this.f2786d.get(i27);
            if (Math.abs(fM2513a - c1346d.f2859b) > f3 || Math.abs(fM2513a2 - c1346d.f2858a) > f3) {
                z3 = false;
            } else {
                float fAbs = Math.abs(f3 - c1346d.f2780c);
                if (fAbs <= 1.0f || fAbs <= c1346d.f2780c) {
                    z3 = true;
                } else {
                    z3 = false;
                }
            }
            if (z3) {
                List<C1346d> list = this.f2786d;
                int i28 = c1346d.f2781d + 1;
                float f4 = i28;
                list.set(i27, new C1346d(((c1346d.f2781d * c1346d.f2858a) + fM2513a2) / f4, ((c1346d.f2781d * c1346d.f2859b) + fM2513a) / f4, ((c1346d.f2781d * c1346d.f2780c) + f3) / f4, i28));
                z2 = true;
                break;
            }
            i27++;
        }
        if (!z2) {
            this.f2786d.add(new C1346d(fM2513a2, fM2513a, f3));
        }
        return true;
    }

    /* JADX INFO: renamed from: d */
    private int m2523d() {
        if (this.f2786d.size() <= 1) {
            return 0;
        }
        C1346d c1346d = null;
        for (C1346d c1346d2 : this.f2786d) {
            if (c1346d2.f2781d >= 2) {
                if (c1346d != null) {
                    this.f2787f = true;
                    return ((int) (Math.abs(c1346d.f2858a - c1346d2.f2858a) - Math.abs(c1346d.f2859b - c1346d2.f2859b))) / 2;
                }
                c1346d = c1346d2;
            }
        }
        return 0;
    }

    /* JADX INFO: renamed from: e */
    private boolean m2525e() {
        int size = this.f2786d.size();
        float fAbs = 0.0f;
        int i = 0;
        float f = 0.0f;
        for (C1346d c1346d : this.f2786d) {
            if (c1346d.f2781d >= 2) {
                i++;
                f += c1346d.f2780c;
            }
        }
        if (i < 3) {
            return false;
        }
        float f2 = f / size;
        Iterator<C1346d> it2 = this.f2786d.iterator();
        while (it2.hasNext()) {
            fAbs += Math.abs(it2.next().f2780c - f2);
        }
        return fAbs <= f * 0.05f;
    }

    /* JADX INFO: renamed from: f */
    private C1346d[] m2526f() throws C1358m {
        int size = this.f2786d.size();
        if (size < 3) {
            throw C1358m.m2611a();
        }
        float f = 0.0f;
        byte b2 = 0;
        if (size > 3) {
            Iterator<C1346d> it2 = this.f2786d.iterator();
            float f2 = 0.0f;
            float f3 = 0.0f;
            while (it2.hasNext()) {
                float f4 = it2.next().f2780c;
                f2 += f4;
                f3 += f4 * f4;
            }
            float f5 = size;
            float f6 = f2 / f5;
            float fSqrt = (float) Math.sqrt((f3 / f5) - (f6 * f6));
            Collections.sort(this.f2786d, new b(f6, b2));
            float fMax = Math.max(0.2f * f6, fSqrt);
            int i = 0;
            while (i < this.f2786d.size() && this.f2786d.size() > 3) {
                if (Math.abs(this.f2786d.get(i).f2780c - f6) > fMax) {
                    this.f2786d.remove(i);
                    i--;
                }
                i++;
            }
        }
        if (this.f2786d.size() > 3) {
            Iterator<C1346d> it3 = this.f2786d.iterator();
            while (it3.hasNext()) {
                f += it3.next().f2780c;
            }
            Collections.sort(this.f2786d, new a(f / this.f2786d.size(), b2));
            this.f2786d.subList(3, this.f2786d.size()).clear();
        }
        return new C1346d[]{this.f2786d.get(0), this.f2786d.get(1), this.f2786d.get(2)};
    }

    /* JADX INFO: renamed from: com.google.a.i.b.e$b */
    /* JADX INFO: compiled from: FinderPatternFinder.java */
    private static final class b implements Serializable, Comparator<C1346d> {

        /* JADX INFO: renamed from: a */
        private final float f2791a;

        /* synthetic */ b(float f, byte b) {
            this(f);
        }

        @Override // java.util.Comparator
        public final /* synthetic */ int compare(C1346d c1346d, C1346d c1346d2) {
            return Float.compare(Math.abs(c1346d2.f2780c - this.f2791a), Math.abs(c1346d.f2780c - this.f2791a));
        }

        private b(float f) {
            this.f2791a = f;
        }

        /* JADX INFO: renamed from: a */
        private int m2530a(C1346d c1346d, C1346d c1346d2) {
            return Float.compare(Math.abs(c1346d2.f2780c - this.f2791a), Math.abs(c1346d.f2780c - this.f2791a));
        }
    }

    /* JADX INFO: renamed from: com.google.a.i.b.e$a */
    /* JADX INFO: compiled from: FinderPatternFinder.java */
    private static final class a implements Serializable, Comparator<C1346d> {

        /* JADX INFO: renamed from: a */
        private final float f2790a;

        /* synthetic */ a(float f, byte b) {
            this(f);
        }

        @Override // java.util.Comparator
        public final /* synthetic */ int compare(C1346d c1346d, C1346d c1346d2) {
            C1346d c1346d3 = c1346d;
            C1346d c1346d4 = c1346d2;
            int iCompare = Integer.compare(c1346d4.f2781d, c1346d3.f2781d);
            return iCompare == 0 ? Float.compare(Math.abs(c1346d3.f2780c - this.f2790a), Math.abs(c1346d4.f2780c - this.f2790a)) : iCompare;
        }

        private a(float f) {
            this.f2790a = f;
        }

        /* JADX INFO: renamed from: a */
        private int m2529a(C1346d c1346d, C1346d c1346d2) {
            int iCompare = Integer.compare(c1346d2.f2781d, c1346d.f2781d);
            return iCompare == 0 ? Float.compare(Math.abs(c1346d.f2780c - this.f2790a), Math.abs(c1346d2.f2780c - this.f2790a)) : iCompare;
        }
    }
}
