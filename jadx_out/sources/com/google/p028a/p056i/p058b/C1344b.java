package com.google.p028a.p056i.p058b;

import com.google.p028a.C1358m;
import com.google.p028a.InterfaceC1366u;
import com.google.p028a.p035c.C1194b;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: com.google.a.i.b.b */
/* JADX INFO: compiled from: AlignmentPatternFinder.java */
/* JADX INFO: loaded from: classes.dex */
final class C1344b {

    /* JADX INFO: renamed from: a */
    final C1194b f2769a;

    /* JADX INFO: renamed from: c */
    final int f2771c;

    /* JADX INFO: renamed from: d */
    final int f2772d;

    /* JADX INFO: renamed from: e */
    final int f2773e;

    /* JADX INFO: renamed from: f */
    final int f2774f;

    /* JADX INFO: renamed from: g */
    private final float f2775g;

    /* JADX INFO: renamed from: i */
    private final InterfaceC1366u f2777i;

    /* JADX INFO: renamed from: b */
    final List<C1343a> f2770b = new ArrayList(5);

    /* JADX INFO: renamed from: h */
    private final int[] f2776h = new int[3];

    C1344b(C1194b c1194b, int i, int i2, int i3, int i4, float f, InterfaceC1366u interfaceC1366u) {
        this.f2769a = c1194b;
        this.f2771c = i;
        this.f2772d = i2;
        this.f2773e = i3;
        this.f2774f = i4;
        this.f2775g = f;
        this.f2777i = interfaceC1366u;
    }

    /* JADX INFO: renamed from: a */
    private C1343a m2492a() throws C1358m {
        C1343a c1343aM2493a;
        C1343a c1343aM2493a2;
        int i = this.f2771c;
        int i2 = this.f2774f;
        int i3 = this.f2773e + i;
        int i4 = this.f2772d + (i2 / 2);
        int[] iArr = new int[3];
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = ((i5 & 1) == 0 ? (i5 + 1) / 2 : -((i5 + 1) / 2)) + i4;
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            int i7 = i;
            while (i7 < i3 && !this.f2769a.m1789a(i7, i6)) {
                i7++;
            }
            int i8 = 0;
            while (i7 < i3) {
                if (!this.f2769a.m1789a(i7, i6)) {
                    if (i8 == 1) {
                        i8++;
                    }
                    iArr[i8] = iArr[i8] + 1;
                } else if (i8 == 1) {
                    iArr[1] = iArr[1] + 1;
                } else if (i8 == 2) {
                    if (m2494a(iArr) && (c1343aM2493a2 = m2493a(iArr, i6, i7)) != null) {
                        return c1343aM2493a2;
                    }
                    iArr[0] = iArr[2];
                    iArr[1] = 1;
                    iArr[2] = 0;
                    i8 = 1;
                } else {
                    i8++;
                    iArr[i8] = iArr[i8] + 1;
                }
                i7++;
            }
            if (m2494a(iArr) && (c1343aM2493a = m2493a(iArr, i6, i3)) != null) {
                return c1343aM2493a;
            }
        }
        if (!this.f2770b.isEmpty()) {
            return this.f2770b.get(0);
        }
        throw C1358m.m2611a();
    }

    /* JADX INFO: renamed from: a */
    private static float m2491a(int[] iArr, int i) {
        return (i - iArr[2]) - (iArr[1] / 2.0f);
    }

    /* JADX INFO: renamed from: a */
    final boolean m2494a(int[] iArr) {
        float f = this.f2775g;
        float f2 = f / 2.0f;
        for (int i = 0; i < 3; i++) {
            if (Math.abs(f - iArr[i]) >= f2) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: renamed from: a */
    private float m2490a(int i, int i2, int i3, int i4) {
        C1194b c1194b = this.f2769a;
        int i5 = c1194b.f2078b;
        int[] iArr = this.f2776h;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        int i6 = i;
        while (i6 >= 0 && c1194b.m1789a(i2, i6) && iArr[1] <= i3) {
            iArr[1] = iArr[1] + 1;
            i6--;
        }
        if (i6 < 0 || iArr[1] > i3) {
            return Float.NaN;
        }
        while (i6 >= 0 && !c1194b.m1789a(i2, i6) && iArr[0] <= i3) {
            iArr[0] = iArr[0] + 1;
            i6--;
        }
        if (iArr[0] > i3) {
            return Float.NaN;
        }
        int i7 = i + 1;
        while (i7 < i5 && c1194b.m1789a(i2, i7) && iArr[1] <= i3) {
            iArr[1] = iArr[1] + 1;
            i7++;
        }
        if (i7 == i5 || iArr[1] > i3) {
            return Float.NaN;
        }
        while (i7 < i5 && !c1194b.m1789a(i2, i7) && iArr[2] <= i3) {
            iArr[2] = iArr[2] + 1;
            i7++;
        }
        if (iArr[2] <= i3 && Math.abs(((iArr[0] + iArr[1]) + iArr[2]) - i4) * 5 < i4 * 2 && m2494a(iArr)) {
            return m2491a(iArr, i7);
        }
        return Float.NaN;
    }

    /* JADX WARN: Code duplicated, block: B:63:0x00fd  */
    /* JADX INFO: renamed from: a */
    final C1343a m2493a(int[] iArr, int i, int i2) {
        boolean z;
        int i3 = iArr[0] + iArr[1] + iArr[2];
        float fM2491a = m2491a(iArr, i2);
        int i4 = (int) fM2491a;
        int i5 = iArr[1] * 2;
        C1194b c1194b = this.f2769a;
        int i6 = c1194b.f2078b;
        int[] iArr2 = this.f2776h;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        int i7 = i;
        while (i7 >= 0 && c1194b.m1789a(i4, i7) && iArr2[1] <= i5) {
            iArr2[1] = iArr2[1] + 1;
            i7--;
        }
        float fM2491a2 = Float.NaN;
        if (i7 >= 0 && iArr2[1] <= i5) {
            while (i7 >= 0 && !c1194b.m1789a(i4, i7) && iArr2[0] <= i5) {
                iArr2[0] = iArr2[0] + 1;
                i7--;
            }
            if (iArr2[0] <= i5) {
                int i8 = i + 1;
                while (i8 < i6 && c1194b.m1789a(i4, i8) && iArr2[1] <= i5) {
                    iArr2[1] = iArr2[1] + 1;
                    i8++;
                }
                if (i8 != i6 && iArr2[1] <= i5) {
                    while (i8 < i6 && !c1194b.m1789a(i4, i8) && iArr2[2] <= i5) {
                        iArr2[2] = iArr2[2] + 1;
                        i8++;
                    }
                    if (iArr2[2] <= i5 && Math.abs(((iArr2[0] + iArr2[1]) + iArr2[2]) - i3) * 5 < i3 * 2 && m2494a(iArr2)) {
                        fM2491a2 = m2491a(iArr2, i8);
                    }
                }
            }
        }
        if (Float.isNaN(fM2491a2)) {
            return null;
        }
        float f = ((iArr[0] + iArr[1]) + iArr[2]) / 3.0f;
        for (C1343a c1343a : this.f2770b) {
            if (Math.abs(fM2491a2 - c1343a.f2859b) > f || Math.abs(fM2491a - c1343a.f2858a) > f) {
                z = false;
            } else {
                float fAbs = Math.abs(f - c1343a.f2768c);
                if (fAbs <= 1.0f || fAbs <= c1343a.f2768c) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z) {
                return new C1343a((c1343a.f2858a + fM2491a) / 2.0f, (c1343a.f2859b + fM2491a2) / 2.0f, (c1343a.f2768c + f) / 2.0f);
            }
        }
        this.f2770b.add(new C1343a(fM2491a, fM2491a2, f));
        return null;
    }
}
