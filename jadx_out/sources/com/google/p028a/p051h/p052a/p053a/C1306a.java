package com.google.p028a.p051h.p052a.p053a;

import com.google.p028a.C1210d;

/* JADX INFO: renamed from: com.google.a.h.a.a.a */
/* JADX INFO: compiled from: ErrorCorrection.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1306a {

    /* JADX INFO: renamed from: a */
    public final C1307b f2556a = C1307b.f2557a;

    /* JADX INFO: renamed from: a */
    private int m2227a(int[] iArr, int i, int[] iArr2) throws C1210d {
        int i2;
        C1308c c1308c;
        C1308c c1308c2 = new C1308c(this.f2556a, iArr);
        int[] iArr3 = new int[i];
        int i3 = i;
        boolean z = false;
        while (true) {
            i2 = 1;
            if (i3 <= 0) {
                break;
            }
            int iM2247b = c1308c2.m2247b(this.f2556a.f2558b[i3]);
            iArr3[i - i3] = iM2247b;
            if (iM2247b != 0) {
                z = true;
            }
            i3--;
        }
        if (!z) {
            return 0;
        }
        C1308c c1308c3 = this.f2556a.f2561e;
        if (iArr2 != null) {
            C1308c c1308cM2251c = c1308c3;
            for (int i4 : iArr2) {
                c1308cM2251c = c1308cM2251c.m2251c(new C1308c(this.f2556a, new int[]{this.f2556a.m2239c(0, this.f2556a.f2558b[(iArr.length - 1) - i4]), 1}));
            }
        }
        C1308c c1308c4 = new C1308c(this.f2556a, iArr3);
        C1308c c1308cM2237a = this.f2556a.m2237a(i, 1);
        if (c1308cM2237a.f2564b.length - 1 >= c1308c4.f2564b.length - 1) {
            c1308cM2237a = c1308c4;
            c1308c4 = c1308cM2237a;
        }
        C1308c c1308c5 = this.f2556a.f2560d;
        C1308c c1308cM2248b = this.f2556a.f2561e;
        C1308c c1308c6 = c1308cM2237a;
        C1308c c1308cM2249b = c1308c4;
        C1308c c1308c7 = c1308c6;
        while (c1308c7.f2564b.length - i2 >= i / 2) {
            if (c1308c7.m2246a()) {
                throw C1210d.m1865a();
            }
            C1308c c1308cM2245a = this.f2556a.f2560d;
            int iM2236a = this.f2556a.m2236a(c1308c7.m2244a(c1308c7.f2564b.length - i2));
            while (c1308cM2249b.f2564b.length - i2 >= c1308c7.f2564b.length - i2 && !c1308cM2249b.m2246a()) {
                int length = (c1308cM2249b.f2564b.length - i2) - (c1308c7.f2564b.length - i2);
                int iM2240d = this.f2556a.m2240d(c1308cM2249b.m2244a(c1308cM2249b.f2564b.length - i2), iM2236a);
                c1308cM2245a = c1308cM2245a.m2245a(this.f2556a.m2237a(length, iM2240d));
                if (length >= 0) {
                    if (iM2240d != 0) {
                        int length2 = c1308c7.f2564b.length;
                        int[] iArr4 = new int[length + length2];
                        for (int i5 = 0; i5 < length2; i5++) {
                            iArr4[i5] = c1308c7.f2563a.m2240d(c1308c7.f2564b[i5], iM2240d);
                        }
                        c1308c = new C1308c(c1308c7.f2563a, iArr4);
                    } else {
                        c1308c = c1308c7.f2563a.f2560d;
                    }
                    c1308cM2249b = c1308cM2249b.m2249b(c1308c);
                    i2 = 1;
                } else {
                    throw new IllegalArgumentException();
                }
            }
            i2 = 1;
            C1308c c1308c8 = c1308cM2249b;
            c1308cM2249b = c1308c7;
            c1308c7 = c1308c8;
            C1308c c1308c9 = c1308cM2248b;
            c1308cM2248b = c1308cM2245a.m2251c(c1308cM2248b).m2249b(c1308c5).m2248b();
            c1308c5 = c1308c9;
        }
        int iM2244a = c1308cM2248b.m2244a(0);
        if (iM2244a == 0) {
            throw C1210d.m1865a();
        }
        int iM2236a2 = this.f2556a.m2236a(iM2244a);
        C1308c[] c1308cArr = {c1308cM2248b.m2250c(iM2236a2), c1308c7.m2250c(iM2236a2)};
        C1308c c1308c10 = c1308cArr[0];
        C1308c c1308c11 = c1308cArr[1];
        int[] iArrM2229a = m2229a(c1308c10);
        int[] iArrM2230a = m2230a(c1308c11, c1308c10, iArrM2229a);
        for (int i6 = 0; i6 < iArrM2229a.length; i6++) {
            int length3 = iArr.length - 1;
            C1307b c1307b = this.f2556a;
            int i7 = iArrM2229a[i6];
            if (i7 == 0) {
                throw new IllegalArgumentException();
            }
            int i8 = length3 - c1307b.f2559c[i7];
            if (i8 < 0) {
                throw C1210d.m1865a();
            }
            iArr[i8] = this.f2556a.m2239c(iArr[i8], iArrM2230a[i6]);
        }
        return iArrM2229a.length;
    }

    /* JADX INFO: renamed from: a */
    private C1308c[] m2228a(C1308c c1308c, C1308c c1308c2, int i) throws C1210d {
        C1308c c1308c3;
        C1308c c1308c4 = c1308c;
        C1308c c1308c5 = c1308c2;
        if (c1308c4.f2564b.length - 1 < c1308c5.f2564b.length - 1) {
            c1308c5 = c1308c4;
            c1308c4 = c1308c5;
        }
        C1308c c1308c6 = this.f2556a.f2560d;
        C1308c c1308cM2248b = this.f2556a.f2561e;
        C1308c c1308cM2249b = c1308c4;
        C1308c c1308c7 = c1308c5;
        C1308c c1308c8 = c1308c6;
        while (c1308c7.f2564b.length - 1 >= i / 2) {
            if (c1308c7.m2246a()) {
                throw C1210d.m1865a();
            }
            C1308c c1308cM2245a = this.f2556a.f2560d;
            int iM2236a = this.f2556a.m2236a(c1308c7.m2244a(c1308c7.f2564b.length - 1));
            while (c1308cM2249b.f2564b.length - 1 >= c1308c7.f2564b.length - 1 && !c1308cM2249b.m2246a()) {
                int length = (c1308cM2249b.f2564b.length - 1) - (c1308c7.f2564b.length - 1);
                int iM2240d = this.f2556a.m2240d(c1308cM2249b.m2244a(c1308cM2249b.f2564b.length - 1), iM2236a);
                c1308cM2245a = c1308cM2245a.m2245a(this.f2556a.m2237a(length, iM2240d));
                if (length >= 0) {
                    if (iM2240d != 0) {
                        int length2 = c1308c7.f2564b.length;
                        int[] iArr = new int[length + length2];
                        for (int i2 = 0; i2 < length2; i2++) {
                            iArr[i2] = c1308c7.f2563a.m2240d(c1308c7.f2564b[i2], iM2240d);
                        }
                        c1308c3 = new C1308c(c1308c7.f2563a, iArr);
                    } else {
                        c1308c3 = c1308c7.f2563a.f2560d;
                    }
                    c1308cM2249b = c1308cM2249b.m2249b(c1308c3);
                } else {
                    throw new IllegalArgumentException();
                }
            }
            C1308c c1308c9 = c1308cM2249b;
            c1308cM2249b = c1308c7;
            c1308c7 = c1308c9;
            C1308c c1308c10 = c1308cM2248b;
            c1308cM2248b = c1308cM2245a.m2251c(c1308cM2248b).m2249b(c1308c8).m2248b();
            c1308c8 = c1308c10;
        }
        int iM2244a = c1308cM2248b.m2244a(0);
        if (iM2244a == 0) {
            throw C1210d.m1865a();
        }
        int iM2236a2 = this.f2556a.m2236a(iM2244a);
        return new C1308c[]{c1308cM2248b.m2250c(iM2236a2), c1308c7.m2250c(iM2236a2)};
    }

    /* JADX INFO: renamed from: a */
    public final int[] m2229a(C1308c c1308c) throws C1210d {
        int length = c1308c.f2564b.length - 1;
        int[] iArr = new int[length];
        int i = 0;
        for (int i2 = 1; i2 < this.f2556a.f2562f && i < length; i2++) {
            if (c1308c.m2247b(i2) == 0) {
                iArr[i] = this.f2556a.m2236a(i2);
                i++;
            }
        }
        if (i != length) {
            throw C1210d.m1865a();
        }
        return iArr;
    }

    /* JADX INFO: renamed from: a */
    public final int[] m2230a(C1308c c1308c, C1308c c1308c2, int[] iArr) {
        int length = c1308c2.f2564b.length - 1;
        int[] iArr2 = new int[length];
        for (int i = 1; i <= length; i++) {
            iArr2[length - i] = this.f2556a.m2240d(i, c1308c2.m2244a(i));
        }
        C1308c c1308c3 = new C1308c(this.f2556a, iArr2);
        int length2 = iArr.length;
        int[] iArr3 = new int[length2];
        for (int i2 = 0; i2 < length2; i2++) {
            int iM2236a = this.f2556a.m2236a(iArr[i2]);
            iArr3[i2] = this.f2556a.m2240d(this.f2556a.m2239c(0, c1308c.m2247b(iM2236a)), this.f2556a.m2236a(c1308c3.m2247b(iM2236a)));
        }
        return iArr3;
    }
}
