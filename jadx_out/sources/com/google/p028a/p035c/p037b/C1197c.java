package com.google.p028a.p035c.p037b;

/* JADX INFO: renamed from: com.google.a.c.b.c */
/* JADX INFO: compiled from: ReedSolomonDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1197c {

    /* JADX INFO: renamed from: a */
    private final C1195a f2098a;

    public C1197c(C1195a c1195a) {
        this.f2098a = c1195a;
    }

    /* JADX INFO: renamed from: a */
    public final void m1819a(int[] iArr, int i) throws C1199e {
        C1196b c1196b = new C1196b(this.f2098a, iArr);
        int[] iArr2 = new int[i];
        boolean z = true;
        for (int i2 = 0; i2 < i; i2++) {
            C1195a c1195a = this.f2098a;
            int iM1812b = c1196b.m1812b(c1195a.f2089i[c1195a.f2093m + i2]);
            iArr2[(i - 1) - i2] = iM1812b;
            if (iM1812b != 0) {
                z = false;
            }
        }
        if (z) {
            return;
        }
        C1196b c1196b2 = new C1196b(this.f2098a, iArr2);
        C1196b c1196bM1803a = this.f2098a.m1803a(i, 1);
        if (c1196bM1803a.f2096a.length - 1 >= c1196b2.f2096a.length - 1) {
            c1196bM1803a = c1196b2;
            c1196b2 = c1196bM1803a;
        }
        C1196b c1196b3 = this.f2098a.f2090j;
        C1196b c1196b4 = this.f2098a.f2091k;
        C1196b c1196b5 = c1196bM1803a;
        C1196b c1196bM1810a = c1196b2;
        C1196b c1196b6 = c1196b5;
        while (c1196b6.f2096a.length - 1 >= i / 2) {
            if (c1196b6.m1811a()) {
                throw new C1199e("r_{i-1} was zero");
            }
            C1196b c1196bM1810a2 = this.f2098a.f2090j;
            int iM1804b = this.f2098a.m1804b(c1196b6.m1808a(c1196b6.f2096a.length - 1));
            while (c1196bM1810a.f2096a.length - 1 >= c1196b6.f2096a.length - 1 && !c1196bM1810a.m1811a()) {
                int length = (c1196bM1810a.f2096a.length - 1) - (c1196b6.f2096a.length - 1);
                int iM1805c = this.f2098a.m1805c(c1196bM1810a.m1808a(c1196bM1810a.f2096a.length - 1), iM1804b);
                c1196bM1810a2 = c1196bM1810a2.m1810a(this.f2098a.m1803a(length, iM1805c));
                c1196bM1810a = c1196bM1810a.m1810a(c1196b6.m1809a(length, iM1805c));
            }
            C1196b c1196bM1810a3 = c1196bM1810a2.m1813b(c1196b4).m1810a(c1196b3);
            if (c1196bM1810a.f2096a.length - 1 >= c1196b6.f2096a.length - 1) {
                throw new IllegalStateException("Division algorithm failed to reduce polynomial?");
            }
            C1196b c1196b7 = c1196bM1810a;
            c1196bM1810a = c1196b6;
            c1196b6 = c1196b7;
            C1196b c1196b8 = c1196b4;
            c1196b4 = c1196bM1810a3;
            c1196b3 = c1196b8;
        }
        int iM1808a = c1196b4.m1808a(0);
        if (iM1808a == 0) {
            throw new C1199e("sigmaTilde(0) was zero");
        }
        int iM1804b2 = this.f2098a.m1804b(iM1808a);
        C1196b[] c1196bArr = {c1196b4.m1814c(iM1804b2), c1196b6.m1814c(iM1804b2)};
        C1196b c1196b9 = c1196bArr[0];
        C1196b c1196b10 = c1196bArr[1];
        int[] iArrM1816a = m1816a(c1196b9);
        int[] iArrM1817a = m1817a(c1196b10, iArrM1816a);
        for (int i3 = 0; i3 < iArrM1816a.length; i3++) {
            int length2 = (iArr.length - 1) - this.f2098a.m1802a(iArrM1816a[i3]);
            if (length2 < 0) {
                throw new C1199e("Bad error location");
            }
            iArr[length2] = C1195a.m1797b(iArr[length2], iArrM1817a[i3]);
        }
    }

    /* JADX INFO: renamed from: a */
    private int[] m1817a(C1196b c1196b, int[] iArr) {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        for (int i = 0; i < length; i++) {
            int iM1804b = this.f2098a.m1804b(iArr[i]);
            int iM1805c = 1;
            for (int i2 = 0; i2 < length; i2++) {
                if (i != i2) {
                    int iM1805c2 = this.f2098a.m1805c(iArr[i2], iM1804b);
                    iM1805c = this.f2098a.m1805c(iM1805c, (iM1805c2 & 1) == 0 ? iM1805c2 | 1 : iM1805c2 & (-2));
                }
            }
            iArr2[i] = this.f2098a.m1805c(c1196b.m1812b(iM1804b), this.f2098a.m1804b(iM1805c));
            if (this.f2098a.f2093m != 0) {
                iArr2[i] = this.f2098a.m1805c(iArr2[i], iM1804b);
            }
        }
        return iArr2;
    }

    /* JADX INFO: renamed from: a */
    private C1196b[] m1818a(C1196b c1196b, C1196b c1196b2, int i) throws C1199e {
        if (c1196b.f2096a.length - 1 < c1196b2.f2096a.length - 1) {
            c1196b2 = c1196b;
            c1196b = c1196b2;
        }
        C1196b c1196b3 = this.f2098a.f2090j;
        C1196b c1196b4 = this.f2098a.f2091k;
        C1196b c1196b5 = c1196b2;
        C1196b c1196bM1810a = c1196b;
        C1196b c1196b6 = c1196b5;
        while (c1196b6.f2096a.length - 1 >= i / 2) {
            if (c1196b6.m1811a()) {
                throw new C1199e("r_{i-1} was zero");
            }
            C1196b c1196bM1810a2 = this.f2098a.f2090j;
            int iM1804b = this.f2098a.m1804b(c1196b6.m1808a(c1196b6.f2096a.length - 1));
            while (c1196bM1810a.f2096a.length - 1 >= c1196b6.f2096a.length - 1 && !c1196bM1810a.m1811a()) {
                int length = (c1196bM1810a.f2096a.length - 1) - (c1196b6.f2096a.length - 1);
                int iM1805c = this.f2098a.m1805c(c1196bM1810a.m1808a(c1196bM1810a.f2096a.length - 1), iM1804b);
                c1196bM1810a2 = c1196bM1810a2.m1810a(this.f2098a.m1803a(length, iM1805c));
                c1196bM1810a = c1196bM1810a.m1810a(c1196b6.m1809a(length, iM1805c));
            }
            C1196b c1196bM1810a3 = c1196bM1810a2.m1813b(c1196b4).m1810a(c1196b3);
            if (c1196bM1810a.f2096a.length - 1 >= c1196b6.f2096a.length - 1) {
                throw new IllegalStateException("Division algorithm failed to reduce polynomial?");
            }
            C1196b c1196b7 = c1196bM1810a;
            c1196bM1810a = c1196b6;
            c1196b6 = c1196b7;
            C1196b c1196b8 = c1196b4;
            c1196b4 = c1196bM1810a3;
            c1196b3 = c1196b8;
        }
        int iM1808a = c1196b4.m1808a(0);
        if (iM1808a == 0) {
            throw new C1199e("sigmaTilde(0) was zero");
        }
        int iM1804b2 = this.f2098a.m1804b(iM1808a);
        return new C1196b[]{c1196b4.m1814c(iM1804b2), c1196b6.m1814c(iM1804b2)};
    }

    /* JADX INFO: renamed from: a */
    private int[] m1816a(C1196b c1196b) throws C1199e {
        int length = c1196b.f2096a.length - 1;
        int i = 0;
        if (length == 1) {
            return new int[]{c1196b.m1808a(1)};
        }
        int[] iArr = new int[length];
        for (int i2 = 1; i2 < this.f2098a.f2092l && i < length; i2++) {
            if (c1196b.m1812b(i2) == 0) {
                iArr[i] = this.f2098a.m1804b(i2);
                i++;
            }
        }
        if (i != length) {
            throw new C1199e("Error locator degree does not match number of roots");
        }
        return iArr;
    }
}
