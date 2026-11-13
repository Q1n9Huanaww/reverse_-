package com.google.p028a.p051h.p052a;

import com.google.p028a.C1365t;

/* JADX INFO: renamed from: com.google.a.h.a.h */
/* JADX INFO: compiled from: DetectionResultRowIndicatorColumn.java */
/* JADX INFO: loaded from: classes.dex */
final class C1315h extends C1314g {

    /* JADX INFO: renamed from: c */
    final boolean f2627c;

    C1315h(C1310c c1310c, boolean z) {
        super(c1310c);
        this.f2627c = z;
    }

    /* JADX INFO: renamed from: c */
    private int[] m2309c() {
        C1365t c1365t;
        C1365t c1365t2;
        int i;
        C1305a c1305aM2311a = m2311a();
        if (c1305aM2311a == null) {
            return null;
        }
        C1310c c1310c = this.f2625a;
        if (this.f2627c) {
            c1365t = c1310c.f2567b;
        } else {
            c1365t = c1310c.f2569d;
        }
        if (this.f2627c) {
            c1365t2 = c1310c.f2568c;
        } else {
            c1365t2 = c1310c.f2570e;
        }
        int iB = m2304b((int) c1365t2.f2859b);
        C1311d[] c1311dArr = this.f2626b;
        int i2 = -1;
        int i3 = 0;
        int iMax = 1;
        for (int iB2 = m2304b((int) c1365t.f2859b); iB2 < iB; iB2++) {
            if (c1311dArr[iB2] != null) {
                C1311d c1311d = c1311dArr[iB2];
                c1311d.m2273b();
                int i4 = c1311d.f2580e - i2;
                if (i4 == 0) {
                    i3++;
                } else {
                    if (i4 == 1) {
                        iMax = Math.max(iMax, i3);
                        i2 = c1311d.f2580e;
                    } else if (c1311d.f2580e >= c1305aM2311a.f2555e) {
                        c1311dArr[iB2] = null;
                    } else {
                        i2 = c1311d.f2580e;
                    }
                    i3 = 1;
                }
            }
        }
        int[] iArr = new int[c1305aM2311a.f2555e];
        for (C1311d c1311d2 : this.f2626b) {
            if (c1311d2 != null && (i = c1311d2.f2580e) < iArr.length) {
                iArr[i] = iArr[i] + 1;
            }
        }
        return iArr;
    }

    /* JADX INFO: renamed from: a */
    final void m2312a(C1311d[] c1311dArr, C1305a c1305a) {
        for (int i = 0; i < c1311dArr.length; i++) {
            C1311d c1311d = c1311dArr[i];
            if (c1311dArr[i] != null) {
                int i2 = c1311d.f2579d % 30;
                int i3 = c1311d.f2580e;
                if (i3 > c1305a.f2555e) {
                    c1311dArr[i] = null;
                } else {
                    if (!this.f2627c) {
                        i3 += 2;
                    }
                    switch (i3 % 3) {
                        case 0:
                            if ((i2 * 3) + 1 != c1305a.f2553c) {
                                c1311dArr[i] = null;
                            }
                            break;
                        case 1:
                            if (i2 / 3 != c1305a.f2552b || i2 % 3 != c1305a.f2554d) {
                                c1311dArr[i] = null;
                            }
                            break;
                        case 2:
                            if (i2 + 1 != c1305a.f2551a) {
                                c1311dArr[i] = null;
                            }
                            break;
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: d */
    private boolean m2310d() {
        return this.f2627c;
    }

    @Override // com.google.p028a.p051h.p052a.C1314g
    public final String toString() {
        return "IsLeft: " + this.f2627c + '\n' + super.toString();
    }

    /* JADX INFO: renamed from: b */
    private void m2307b() {
        for (C1311d c1311d : this.f2626b) {
            if (c1311d != null) {
                c1311d.m2273b();
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private void m2306a(C1305a c1305a) {
        C1365t c1365t;
        C1365t c1365t2;
        C1311d[] c1311dArr = this.f2626b;
        for (C1311d c1311d : this.f2626b) {
            if (c1311d != null) {
                c1311d.m2273b();
            }
        }
        m2312a(c1311dArr, c1305a);
        C1310c c1310c = this.f2625a;
        if (this.f2627c) {
            c1365t = c1310c.f2567b;
        } else {
            c1365t = c1310c.f2569d;
        }
        if (this.f2627c) {
            c1365t2 = c1310c.f2568c;
        } else {
            c1365t2 = c1310c.f2570e;
        }
        int iB = m2304b((int) c1365t.f2859b);
        int iB2 = m2304b((int) c1365t2.f2859b);
        int i = -1;
        int i2 = 0;
        int iMax = 1;
        while (iB < iB2) {
            if (c1311dArr[iB] != null) {
                C1311d c1311d2 = c1311dArr[iB];
                int i3 = c1311d2.f2580e - i;
                if (i3 == 0) {
                    i2++;
                } else {
                    if (i3 == 1) {
                        iMax = Math.max(iMax, i2);
                        i = c1311d2.f2580e;
                    } else if (i3 < 0 || c1311d2.f2580e >= c1305a.f2555e || i3 > iB) {
                        c1311dArr[iB] = null;
                    } else {
                        if (iMax > 2) {
                            i3 *= iMax - 2;
                        }
                        boolean z = i3 >= iB;
                        for (int i4 = 1; i4 <= i3 && !z; i4++) {
                            z = c1311dArr[iB - i4] != null;
                        }
                        if (z) {
                            c1311dArr[iB] = null;
                        } else {
                            i = c1311d2.f2580e;
                        }
                    }
                    i2 = 1;
                }
            }
            iB++;
        }
    }

    /* JADX INFO: renamed from: b */
    private void m2308b(C1305a c1305a) {
        C1365t c1365t;
        C1365t c1365t2;
        C1310c c1310c = this.f2625a;
        if (this.f2627c) {
            c1365t = c1310c.f2567b;
        } else {
            c1365t = c1310c.f2569d;
        }
        if (this.f2627c) {
            c1365t2 = c1310c.f2568c;
        } else {
            c1365t2 = c1310c.f2570e;
        }
        int iB = m2304b((int) c1365t2.f2859b);
        C1311d[] c1311dArr = this.f2626b;
        int i = -1;
        int i2 = 0;
        int iMax = 1;
        for (int iB2 = m2304b((int) c1365t.f2859b); iB2 < iB; iB2++) {
            if (c1311dArr[iB2] != null) {
                C1311d c1311d = c1311dArr[iB2];
                c1311d.m2273b();
                int i3 = c1311d.f2580e - i;
                if (i3 == 0) {
                    i2++;
                } else {
                    if (i3 == 1) {
                        iMax = Math.max(iMax, i2);
                        i = c1311d.f2580e;
                    } else if (c1311d.f2580e >= c1305a.f2555e) {
                        c1311dArr[iB2] = null;
                    } else {
                        i = c1311d.f2580e;
                    }
                    i2 = 1;
                }
            }
        }
    }

    /* JADX INFO: renamed from: a */
    final C1305a m2311a() {
        C1311d[] c1311dArr = this.f2626b;
        C1309b c1309b = new C1309b();
        C1309b c1309b2 = new C1309b();
        C1309b c1309b3 = new C1309b();
        C1309b c1309b4 = new C1309b();
        for (C1311d c1311d : c1311dArr) {
            if (c1311d != null) {
                c1311d.m2273b();
                int i = c1311d.f2579d % 30;
                int i2 = c1311d.f2580e;
                if (!this.f2627c) {
                    i2 += 2;
                }
                switch (i2 % 3) {
                    case 0:
                        c1309b2.m2253a((i * 3) + 1);
                        break;
                    case 1:
                        c1309b4.m2253a(i / 3);
                        c1309b3.m2253a(i % 3);
                        break;
                    case 2:
                        c1309b.m2253a(i + 1);
                        break;
                }
            }
        }
        if (c1309b.m2254a().length == 0 || c1309b2.m2254a().length == 0 || c1309b3.m2254a().length == 0 || c1309b4.m2254a().length == 0 || c1309b.m2254a()[0] <= 0 || c1309b2.m2254a()[0] + c1309b3.m2254a()[0] < 3 || c1309b2.m2254a()[0] + c1309b3.m2254a()[0] > 90) {
            return null;
        }
        C1305a c1305a = new C1305a(c1309b.m2254a()[0], c1309b2.m2254a()[0], c1309b3.m2254a()[0], c1309b4.m2254a()[0]);
        m2312a(c1311dArr, c1305a);
        return c1305a;
    }
}
