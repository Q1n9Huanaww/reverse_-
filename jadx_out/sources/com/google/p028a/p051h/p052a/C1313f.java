package com.google.p028a.p051h.p052a;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.p028a.C1365t;
import com.google.p028a.p051h.C1304a;
import java.util.Formatter;

/* JADX INFO: renamed from: com.google.a.h.a.f */
/* JADX INFO: compiled from: DetectionResult.java */
/* JADX INFO: loaded from: classes.dex */
final class C1313f {

    /* JADX INFO: renamed from: e */
    private static final int f2619e = 2;

    /* JADX INFO: renamed from: a */
    final C1305a f2620a;

    /* JADX INFO: renamed from: b */
    final C1314g[] f2621b;

    /* JADX INFO: renamed from: c */
    C1310c f2622c;

    /* JADX INFO: renamed from: d */
    final int f2623d;

    C1313f(C1305a c1305a, C1310c c1310c) {
        this.f2620a = c1305a;
        this.f2623d = c1305a.f2551a;
        this.f2622c = c1310c;
        this.f2621b = new C1314g[this.f2623d + 2];
    }

    /* JADX INFO: renamed from: a */
    private C1314g[] m2289a() {
        int i;
        int i2;
        boolean z;
        m2299a(this.f2621b[0]);
        m2299a(this.f2621b[this.f2623d + 1]);
        int i3 = C1304a.f2542b;
        while (true) {
            if (this.f2621b[0] != null && this.f2621b[this.f2623d + 1] != null) {
                C1311d[] c1311dArr = this.f2621b[0].f2626b;
                C1311d[] c1311dArr2 = this.f2621b[this.f2623d + 1].f2626b;
                for (int i4 = 0; i4 < c1311dArr.length; i4++) {
                    if (c1311dArr[i4] != null && c1311dArr2[i4] != null && c1311dArr[i4].f2580e == c1311dArr2[i4].f2580e) {
                        for (int i5 = 1; i5 <= this.f2623d; i5++) {
                            C1311d c1311d = this.f2621b[i5].f2626b[i4];
                            if (c1311d != null) {
                                c1311d.f2580e = c1311dArr[i4].f2580e;
                                if (!c1311d.m2271a()) {
                                    this.f2621b[i5].f2626b[i4] = null;
                                }
                            }
                        }
                    }
                }
            }
            if (this.f2621b[0] == null) {
                i = 0;
            } else {
                C1311d[] c1311dArr3 = this.f2621b[0].f2626b;
                i = 0;
                for (int i6 = 0; i6 < c1311dArr3.length; i6++) {
                    if (c1311dArr3[i6] != null) {
                        int i7 = c1311dArr3[i6].f2580e;
                        int i8 = i;
                        int iM2283a = 0;
                        for (int i9 = 1; i9 < this.f2623d + 1 && iM2283a < 2; i9++) {
                            C1311d c1311d2 = this.f2621b[i9].f2626b[i6];
                            if (c1311d2 != null) {
                                iM2283a = m2283a(i7, iM2283a, c1311d2);
                                if (!c1311d2.m2271a()) {
                                    i8++;
                                }
                            }
                        }
                        i = i8;
                    }
                }
            }
            if (this.f2621b[this.f2623d + 1] == null) {
                i2 = 0;
            } else {
                C1311d[] c1311dArr4 = this.f2621b[this.f2623d + 1].f2626b;
                i2 = 0;
                for (int i10 = 0; i10 < c1311dArr4.length; i10++) {
                    if (c1311dArr4[i10] != null) {
                        int i11 = c1311dArr4[i10].f2580e;
                        int i12 = i2;
                        int iM2283a2 = 0;
                        for (int i13 = this.f2623d + 1; i13 > 0 && iM2283a2 < 2; i13--) {
                            C1311d c1311d3 = this.f2621b[i13].f2626b[i10];
                            if (c1311d3 != null) {
                                iM2283a2 = m2283a(i11, iM2283a2, c1311d3);
                                if (!c1311d3.m2271a()) {
                                    i12++;
                                }
                            }
                        }
                        i2 = i12;
                    }
                }
            }
            int i14 = i + i2;
            if (i14 == 0) {
                i14 = 0;
            } else {
                for (int i15 = 1; i15 < this.f2623d + 1; i15++) {
                    C1311d[] c1311dArr5 = this.f2621b[i15].f2626b;
                    for (int i16 = 0; i16 < c1311dArr5.length; i16++) {
                        if (c1311dArr5[i16] != null && !c1311dArr5[i16].m2271a()) {
                            C1311d c1311d4 = c1311dArr5[i16];
                            C1311d[] c1311dArr6 = this.f2621b[i15 - 1].f2626b;
                            int i17 = i15 + 1;
                            C1311d[] c1311dArr7 = this.f2621b[i17] != null ? this.f2621b[i17].f2626b : c1311dArr6;
                            C1311d[] c1311dArr8 = new C1311d[14];
                            c1311dArr8[2] = c1311dArr6[i16];
                            c1311dArr8[3] = c1311dArr7[i16];
                            if (i16 > 0) {
                                int i18 = i16 - 1;
                                c1311dArr8[0] = c1311dArr5[i18];
                                c1311dArr8[4] = c1311dArr6[i18];
                                c1311dArr8[5] = c1311dArr7[i18];
                            }
                            if (i16 > 1) {
                                int i19 = i16 - 2;
                                c1311dArr8[8] = c1311dArr5[i19];
                                c1311dArr8[10] = c1311dArr6[i19];
                                c1311dArr8[11] = c1311dArr7[i19];
                            }
                            if (i16 < c1311dArr5.length - 1) {
                                int i20 = i16 + 1;
                                c1311dArr8[1] = c1311dArr5[i20];
                                c1311dArr8[6] = c1311dArr6[i20];
                                c1311dArr8[7] = c1311dArr7[i20];
                            }
                            if (i16 < c1311dArr5.length - 2) {
                                int i21 = i16 + 2;
                                c1311dArr8[9] = c1311dArr5[i21];
                                c1311dArr8[12] = c1311dArr6[i21];
                                c1311dArr8[13] = c1311dArr7[i21];
                            }
                            for (int i22 = 0; i22 < 14; i22++) {
                                C1311d c1311d5 = c1311dArr8[i22];
                                if (c1311d5 != null && c1311d5.m2271a() && c1311d5.f2578c == c1311d4.f2578c) {
                                    c1311d4.f2580e = c1311d5.f2580e;
                                    z = true;
                                } else {
                                    z = false;
                                }
                                if (z) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if (i14 <= 0 || i14 >= i3) {
                break;
            }
            i3 = i14;
        }
        return this.f2621b;
    }

    /* JADX INFO: renamed from: a */
    final void m2299a(C1314g c1314g) {
        C1365t c1365t;
        C1365t c1365t2;
        if (c1314g != null) {
            C1315h c1315h = (C1315h) c1314g;
            C1305a c1305a = this.f2620a;
            C1311d[] c1311dArr = c1315h.f2626b;
            for (C1311d c1311d : c1315h.f2626b) {
                if (c1311d != null) {
                    c1311d.m2273b();
                }
            }
            c1315h.m2312a(c1311dArr, c1305a);
            C1310c c1310c = c1315h.f2625a;
            if (c1315h.f2627c) {
                c1365t = c1310c.f2567b;
            } else {
                c1365t = c1310c.f2569d;
            }
            if (c1315h.f2627c) {
                c1365t2 = c1310c.f2568c;
            } else {
                c1365t2 = c1310c.f2570e;
            }
            int iB = c1315h.m2304b((int) c1365t.f2859b);
            int iB2 = c1315h.m2304b((int) c1365t2.f2859b);
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
    }

    /* JADX INFO: renamed from: d */
    private void m2292d() {
        if (this.f2621b[0] == null || this.f2621b[this.f2623d + 1] == null) {
            return;
        }
        C1311d[] c1311dArr = this.f2621b[0].f2626b;
        C1311d[] c1311dArr2 = this.f2621b[this.f2623d + 1].f2626b;
        for (int i = 0; i < c1311dArr.length; i++) {
            if (c1311dArr[i] != null && c1311dArr2[i] != null && c1311dArr[i].f2580e == c1311dArr2[i].f2580e) {
                for (int i2 = 1; i2 <= this.f2623d; i2++) {
                    C1311d c1311d = this.f2621b[i2].f2626b[i];
                    if (c1311d != null) {
                        c1311d.f2580e = c1311dArr[i].f2580e;
                        if (!c1311d.m2271a()) {
                            this.f2621b[i2].f2626b[i] = null;
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: e */
    private int m2293e() {
        if (this.f2621b[this.f2623d + 1] == null) {
            return 0;
        }
        C1311d[] c1311dArr = this.f2621b[this.f2623d + 1].f2626b;
        int i = 0;
        for (int i2 = 0; i2 < c1311dArr.length; i2++) {
            if (c1311dArr[i2] != null) {
                int i3 = c1311dArr[i2].f2580e;
                int i4 = i;
                int iM2283a = 0;
                for (int i5 = this.f2623d + 1; i5 > 0 && iM2283a < 2; i5--) {
                    C1311d c1311d = this.f2621b[i5].f2626b[i2];
                    if (c1311d != null) {
                        iM2283a = m2283a(i3, iM2283a, c1311d);
                        if (!c1311d.m2271a()) {
                            i4++;
                        }
                    }
                }
                i = i4;
            }
        }
        return i;
    }

    /* JADX INFO: renamed from: f */
    private int m2294f() {
        if (this.f2621b[0] == null) {
            return 0;
        }
        C1311d[] c1311dArr = this.f2621b[0].f2626b;
        int i = 0;
        for (int i2 = 0; i2 < c1311dArr.length; i2++) {
            if (c1311dArr[i2] != null) {
                int i3 = c1311dArr[i2].f2580e;
                int i4 = i;
                int iM2283a = 0;
                for (int i5 = 1; i5 < this.f2623d + 1 && iM2283a < 2; i5++) {
                    C1311d c1311d = this.f2621b[i5].f2626b[i2];
                    if (c1311d != null) {
                        iM2283a = m2283a(i3, iM2283a, c1311d);
                        if (!c1311d.m2271a()) {
                            i4++;
                        }
                    }
                }
                i = i4;
            }
        }
        return i;
    }

    /* JADX INFO: renamed from: a */
    static int m2283a(int i, int i2, C1311d c1311d) {
        if (c1311d == null || c1311d.m2271a()) {
            return i2;
        }
        if (!c1311d.m2272a(i)) {
            return i2 + 1;
        }
        c1311d.f2580e = i;
        return 0;
    }

    /* JADX INFO: renamed from: a */
    private void m2285a(int i, int i2, C1311d[] c1311dArr) {
        boolean z;
        C1311d c1311d = c1311dArr[i2];
        C1311d[] c1311dArr2 = this.f2621b[i - 1].f2626b;
        int i3 = i + 1;
        C1311d[] c1311dArr3 = this.f2621b[i3] != null ? this.f2621b[i3].f2626b : c1311dArr2;
        C1311d[] c1311dArr4 = new C1311d[14];
        c1311dArr4[2] = c1311dArr2[i2];
        c1311dArr4[3] = c1311dArr3[i2];
        if (i2 > 0) {
            int i4 = i2 - 1;
            c1311dArr4[0] = c1311dArr[i4];
            c1311dArr4[4] = c1311dArr2[i4];
            c1311dArr4[5] = c1311dArr3[i4];
        }
        if (i2 > 1) {
            int i5 = i2 - 2;
            c1311dArr4[8] = c1311dArr[i5];
            c1311dArr4[10] = c1311dArr2[i5];
            c1311dArr4[11] = c1311dArr3[i5];
        }
        if (i2 < c1311dArr.length - 1) {
            int i6 = i2 + 1;
            c1311dArr4[1] = c1311dArr[i6];
            c1311dArr4[6] = c1311dArr2[i6];
            c1311dArr4[7] = c1311dArr3[i6];
        }
        if (i2 < c1311dArr.length - 2) {
            int i7 = i2 + 2;
            c1311dArr4[9] = c1311dArr[i7];
            c1311dArr4[12] = c1311dArr2[i7];
            c1311dArr4[13] = c1311dArr3[i7];
        }
        for (int i8 = 0; i8 < 14; i8++) {
            C1311d c1311d2 = c1311dArr4[i8];
            if (c1311d2 != null && c1311d2.m2271a() && c1311d2.f2578c == c1311d.f2578c) {
                c1311d.f2580e = c1311d2.f2580e;
                z = true;
            } else {
                z = false;
            }
            if (z) {
                return;
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private static boolean m2288a(C1311d c1311d, C1311d c1311d2) {
        if (c1311d2 == null || !c1311d2.m2271a() || c1311d2.f2578c != c1311d.f2578c) {
            return false;
        }
        c1311d.f2580e = c1311d2.f2580e;
        return true;
    }

    /* JADX INFO: renamed from: g */
    private int m2295g() {
        return this.f2623d;
    }

    /* JADX INFO: renamed from: h */
    private int m2296h() {
        return this.f2620a.f2555e;
    }

    /* JADX INFO: renamed from: i */
    private int m2297i() {
        return this.f2620a.f2552b;
    }

    /* JADX INFO: renamed from: a */
    private void m2287a(C1310c c1310c) {
        this.f2622c = c1310c;
    }

    /* JADX INFO: renamed from: j */
    private C1310c m2298j() {
        return this.f2622c;
    }

    /* JADX INFO: renamed from: a */
    private void m2286a(int i, C1314g c1314g) {
        this.f2621b[i] = c1314g;
    }

    /* JADX INFO: renamed from: a */
    private C1314g m2284a(int i) {
        return this.f2621b[i];
    }

    public final String toString() {
        C1314g c1314g = this.f2621b[0];
        if (c1314g == null) {
            c1314g = this.f2621b[this.f2623d + 1];
        }
        Formatter formatter = new Formatter();
        Throwable th = null;
        for (int i = 0; i < c1314g.f2626b.length; i++) {
            try {
                try {
                    formatter.format("CW %3d:", Integer.valueOf(i));
                    for (int i2 = 0; i2 < this.f2623d + 2; i2++) {
                        if (this.f2621b[i2] == null) {
                            formatter.format("    |   ", new Object[0]);
                        } else {
                            C1311d c1311d = this.f2621b[i2].f2626b[i];
                            if (c1311d == null) {
                                formatter.format("    |   ", new Object[0]);
                            } else {
                                formatter.format(" %3d|%3d", Integer.valueOf(c1311d.f2580e), Integer.valueOf(c1311d.f2579d));
                            }
                        }
                    }
                    formatter.format("%n", new Object[0]);
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            } catch (Throwable th3) {
                if (th != null) {
                    try {
                        formatter.close();
                    } catch (Throwable th4) {
                        ThrowableExtension.addSuppressed(th, th4);
                    }
                } else {
                    formatter.close();
                }
                throw th3;
            }
        }
        String string = formatter.toString();
        formatter.close();
        return string;
    }

    /* JADX INFO: renamed from: b */
    private int m2290b() {
        int i;
        int i2;
        boolean z;
        if (this.f2621b[0] != null && this.f2621b[this.f2623d + 1] != null) {
            C1311d[] c1311dArr = this.f2621b[0].f2626b;
            C1311d[] c1311dArr2 = this.f2621b[this.f2623d + 1].f2626b;
            for (int i3 = 0; i3 < c1311dArr.length; i3++) {
                if (c1311dArr[i3] != null && c1311dArr2[i3] != null && c1311dArr[i3].f2580e == c1311dArr2[i3].f2580e) {
                    for (int i4 = 1; i4 <= this.f2623d; i4++) {
                        C1311d c1311d = this.f2621b[i4].f2626b[i3];
                        if (c1311d != null) {
                            c1311d.f2580e = c1311dArr[i3].f2580e;
                            if (!c1311d.m2271a()) {
                                this.f2621b[i4].f2626b[i3] = null;
                            }
                        }
                    }
                }
            }
        }
        if (this.f2621b[0] == null) {
            i = 0;
        } else {
            C1311d[] c1311dArr3 = this.f2621b[0].f2626b;
            i = 0;
            for (int i5 = 0; i5 < c1311dArr3.length; i5++) {
                if (c1311dArr3[i5] != null) {
                    int i6 = c1311dArr3[i5].f2580e;
                    int i7 = i;
                    int iM2283a = 0;
                    for (int i8 = 1; i8 < this.f2623d + 1 && iM2283a < 2; i8++) {
                        C1311d c1311d2 = this.f2621b[i8].f2626b[i5];
                        if (c1311d2 != null) {
                            iM2283a = m2283a(i6, iM2283a, c1311d2);
                            if (!c1311d2.m2271a()) {
                                i7++;
                            }
                        }
                    }
                    i = i7;
                }
            }
        }
        if (this.f2621b[this.f2623d + 1] == null) {
            i2 = 0;
        } else {
            C1311d[] c1311dArr4 = this.f2621b[this.f2623d + 1].f2626b;
            i2 = 0;
            for (int i9 = 0; i9 < c1311dArr4.length; i9++) {
                if (c1311dArr4[i9] != null) {
                    int i10 = c1311dArr4[i9].f2580e;
                    int i11 = i2;
                    int iM2283a2 = 0;
                    for (int i12 = this.f2623d + 1; i12 > 0 && iM2283a2 < 2; i12--) {
                        C1311d c1311d3 = this.f2621b[i12].f2626b[i9];
                        if (c1311d3 != null) {
                            iM2283a2 = m2283a(i10, iM2283a2, c1311d3);
                            if (!c1311d3.m2271a()) {
                                i11++;
                            }
                        }
                    }
                    i2 = i11;
                }
            }
        }
        int i13 = i + i2;
        if (i13 == 0) {
            return 0;
        }
        for (int i14 = 1; i14 < this.f2623d + 1; i14++) {
            C1311d[] c1311dArr5 = this.f2621b[i14].f2626b;
            for (int i15 = 0; i15 < c1311dArr5.length; i15++) {
                if (c1311dArr5[i15] != null && !c1311dArr5[i15].m2271a()) {
                    C1311d c1311d4 = c1311dArr5[i15];
                    C1311d[] c1311dArr6 = this.f2621b[i14 - 1].f2626b;
                    int i16 = i14 + 1;
                    C1311d[] c1311dArr7 = this.f2621b[i16] != null ? this.f2621b[i16].f2626b : c1311dArr6;
                    C1311d[] c1311dArr8 = new C1311d[14];
                    c1311dArr8[2] = c1311dArr6[i15];
                    c1311dArr8[3] = c1311dArr7[i15];
                    if (i15 > 0) {
                        int i17 = i15 - 1;
                        c1311dArr8[0] = c1311dArr5[i17];
                        c1311dArr8[4] = c1311dArr6[i17];
                        c1311dArr8[5] = c1311dArr7[i17];
                    }
                    if (i15 > 1) {
                        int i18 = i15 - 2;
                        c1311dArr8[8] = c1311dArr5[i18];
                        c1311dArr8[10] = c1311dArr6[i18];
                        c1311dArr8[11] = c1311dArr7[i18];
                    }
                    if (i15 < c1311dArr5.length - 1) {
                        int i19 = i15 + 1;
                        c1311dArr8[1] = c1311dArr5[i19];
                        c1311dArr8[6] = c1311dArr6[i19];
                        c1311dArr8[7] = c1311dArr7[i19];
                    }
                    if (i15 < c1311dArr5.length - 2) {
                        int i20 = i15 + 2;
                        c1311dArr8[9] = c1311dArr5[i20];
                        c1311dArr8[12] = c1311dArr6[i20];
                        c1311dArr8[13] = c1311dArr7[i20];
                    }
                    for (int i21 = 0; i21 < 14; i21++) {
                        C1311d c1311d5 = c1311dArr8[i21];
                        if (c1311d5 != null && c1311d5.m2271a() && c1311d5.f2578c == c1311d4.f2578c) {
                            c1311d4.f2580e = c1311d5.f2580e;
                            z = true;
                        } else {
                            z = false;
                        }
                        if (z) {
                            break;
                        }
                    }
                }
            }
        }
        return i13;
    }

    /* JADX INFO: renamed from: c */
    private int m2291c() {
        int i;
        int i2 = 0;
        if (this.f2621b[0] != null && this.f2621b[this.f2623d + 1] != null) {
            C1311d[] c1311dArr = this.f2621b[0].f2626b;
            C1311d[] c1311dArr2 = this.f2621b[this.f2623d + 1].f2626b;
            for (int i3 = 0; i3 < c1311dArr.length; i3++) {
                if (c1311dArr[i3] != null && c1311dArr2[i3] != null && c1311dArr[i3].f2580e == c1311dArr2[i3].f2580e) {
                    for (int i4 = 1; i4 <= this.f2623d; i4++) {
                        C1311d c1311d = this.f2621b[i4].f2626b[i3];
                        if (c1311d != null) {
                            c1311d.f2580e = c1311dArr[i3].f2580e;
                            if (!c1311d.m2271a()) {
                                this.f2621b[i4].f2626b[i3] = null;
                            }
                        }
                    }
                }
            }
        }
        if (this.f2621b[0] == null) {
            i = 0;
        } else {
            C1311d[] c1311dArr3 = this.f2621b[0].f2626b;
            i = 0;
            for (int i5 = 0; i5 < c1311dArr3.length; i5++) {
                if (c1311dArr3[i5] != null) {
                    int i6 = c1311dArr3[i5].f2580e;
                    int i7 = i;
                    int iM2283a = 0;
                    for (int i8 = 1; i8 < this.f2623d + 1 && iM2283a < 2; i8++) {
                        C1311d c1311d2 = this.f2621b[i8].f2626b[i5];
                        if (c1311d2 != null) {
                            iM2283a = m2283a(i6, iM2283a, c1311d2);
                            if (!c1311d2.m2271a()) {
                                i7++;
                            }
                        }
                    }
                    i = i7;
                }
            }
        }
        if (this.f2621b[this.f2623d + 1] != null) {
            C1311d[] c1311dArr4 = this.f2621b[this.f2623d + 1].f2626b;
            int i9 = 0;
            for (int i10 = 0; i10 < c1311dArr4.length; i10++) {
                if (c1311dArr4[i10] != null) {
                    int i11 = c1311dArr4[i10].f2580e;
                    int i12 = i9;
                    int iM2283a2 = 0;
                    for (int i13 = this.f2623d + 1; i13 > 0 && iM2283a2 < 2; i13--) {
                        C1311d c1311d3 = this.f2621b[i13].f2626b[i10];
                        if (c1311d3 != null) {
                            iM2283a2 = m2283a(i11, iM2283a2, c1311d3);
                            if (!c1311d3.m2271a()) {
                                i12++;
                            }
                        }
                    }
                    i9 = i12;
                }
            }
            i2 = i9;
        }
        return i + i2;
    }
}
