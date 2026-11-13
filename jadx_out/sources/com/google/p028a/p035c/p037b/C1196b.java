package com.google.p028a.p035c.p037b;

/* JADX INFO: renamed from: com.google.a.c.b.b */
/* JADX INFO: compiled from: GenericGFPoly.java */
/* JADX INFO: loaded from: classes.dex */
final class C1196b {

    /* JADX INFO: renamed from: a */
    final int[] f2096a;

    /* JADX INFO: renamed from: b */
    private final C1195a f2097b;

    C1196b(C1195a c1195a, int[] iArr) {
        if (iArr.length == 0) {
            throw new IllegalArgumentException();
        }
        this.f2097b = c1195a;
        int length = iArr.length;
        if (length > 1 && iArr[0] == 0) {
            int i = 1;
            while (i < length && iArr[i] == 0) {
                i++;
            }
            if (i == length) {
                this.f2096a = new int[]{0};
                return;
            } else {
                this.f2096a = new int[length - i];
                System.arraycopy(iArr, i, this.f2096a, 0, this.f2096a.length);
                return;
            }
        }
        this.f2096a = iArr;
    }

    /* JADX INFO: renamed from: b */
    private int[] m1806b() {
        return this.f2096a;
    }

    /* JADX INFO: renamed from: c */
    private int m1807c() {
        return this.f2096a.length - 1;
    }

    /* JADX INFO: renamed from: a */
    final boolean m1811a() {
        return this.f2096a[0] == 0;
    }

    /* JADX INFO: renamed from: a */
    final int m1808a(int i) {
        return this.f2096a[(this.f2096a.length - 1) - i];
    }

    /* JADX INFO: renamed from: b */
    final int m1812b(int i) {
        if (i == 0) {
            return m1808a(0);
        }
        if (i == 1) {
            int iM1797b = 0;
            for (int i2 : this.f2096a) {
                iM1797b = C1195a.m1797b(iM1797b, i2);
            }
            return iM1797b;
        }
        int iM1797b2 = this.f2096a[0];
        int length = this.f2096a.length;
        for (int i3 = 1; i3 < length; i3++) {
            iM1797b2 = C1195a.m1797b(this.f2097b.m1805c(i, iM1797b2), this.f2096a[i3]);
        }
        return iM1797b2;
    }

    /* JADX INFO: renamed from: a */
    final C1196b m1810a(C1196b c1196b) {
        if (!this.f2097b.equals(c1196b.f2097b)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        }
        if (m1811a()) {
            return c1196b;
        }
        if (c1196b.m1811a()) {
            return this;
        }
        int[] iArr = this.f2096a;
        int[] iArr2 = c1196b.f2096a;
        if (iArr.length > iArr2.length) {
            iArr = iArr2;
            iArr2 = iArr;
        }
        int[] iArr3 = new int[iArr2.length];
        int length = iArr2.length - iArr.length;
        System.arraycopy(iArr2, 0, iArr3, 0, length);
        for (int i = length; i < iArr2.length; i++) {
            iArr3[i] = C1195a.m1797b(iArr[i - length], iArr2[i]);
        }
        return new C1196b(this.f2097b, iArr3);
    }

    /* JADX INFO: renamed from: b */
    final C1196b m1813b(C1196b c1196b) {
        if (!this.f2097b.equals(c1196b.f2097b)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        }
        if (!m1811a() && !c1196b.m1811a()) {
            int[] iArr = this.f2096a;
            int length = iArr.length;
            int[] iArr2 = c1196b.f2096a;
            int length2 = iArr2.length;
            int[] iArr3 = new int[(length + length2) - 1];
            for (int i = 0; i < length; i++) {
                int i2 = iArr[i];
                for (int i3 = 0; i3 < length2; i3++) {
                    int i4 = i + i3;
                    iArr3[i4] = C1195a.m1797b(iArr3[i4], this.f2097b.m1805c(i2, iArr2[i3]));
                }
            }
            return new C1196b(this.f2097b, iArr3);
        }
        return this.f2097b.f2090j;
    }

    /* JADX INFO: renamed from: c */
    final C1196b m1814c(int i) {
        if (i == 0) {
            return this.f2097b.f2090j;
        }
        if (i == 1) {
            return this;
        }
        int length = this.f2096a.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = this.f2097b.m1805c(this.f2096a[i2], i);
        }
        return new C1196b(this.f2097b, iArr);
    }

    /* JADX INFO: renamed from: a */
    final C1196b m1809a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        if (i2 != 0) {
            int length = this.f2096a.length;
            int[] iArr = new int[i + length];
            for (int i3 = 0; i3 < length; i3++) {
                iArr[i3] = this.f2097b.m1805c(this.f2096a[i3], i2);
            }
            return new C1196b(this.f2097b, iArr);
        }
        return this.f2097b.f2090j;
    }

    /* JADX INFO: renamed from: c */
    final C1196b[] m1815c(C1196b c1196b) {
        if (!this.f2097b.equals(c1196b.f2097b)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        }
        if (c1196b.m1811a()) {
            throw new IllegalArgumentException("Divide by 0");
        }
        C1196b c1196b2 = this.f2097b.f2090j;
        int iM1804b = this.f2097b.m1804b(c1196b.m1808a(c1196b.f2096a.length - 1));
        C1196b c1196bM1810a = c1196b2;
        C1196b c1196bM1810a2 = this;
        while (c1196bM1810a2.f2096a.length - 1 >= c1196b.f2096a.length - 1 && !c1196bM1810a2.m1811a()) {
            int length = (c1196bM1810a2.f2096a.length - 1) - (c1196b.f2096a.length - 1);
            int iM1805c = this.f2097b.m1805c(c1196bM1810a2.m1808a(c1196bM1810a2.f2096a.length - 1), iM1804b);
            C1196b c1196bM1809a = c1196b.m1809a(length, iM1805c);
            c1196bM1810a = c1196bM1810a.m1810a(this.f2097b.m1803a(length, iM1805c));
            c1196bM1810a2 = c1196bM1810a2.m1810a(c1196bM1809a);
        }
        return new C1196b[]{c1196bM1810a, c1196bM1810a2};
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder((this.f2096a.length - 1) * 8);
        for (int length = this.f2096a.length - 1; length >= 0; length--) {
            int iM1808a = m1808a(length);
            if (iM1808a != 0) {
                if (iM1808a < 0) {
                    sb.append(" - ");
                    iM1808a = -iM1808a;
                } else if (sb.length() > 0) {
                    sb.append(" + ");
                }
                if (length == 0 || iM1808a != 1) {
                    int iM1802a = this.f2097b.m1802a(iM1808a);
                    if (iM1802a == 0) {
                        sb.append('1');
                    } else if (iM1802a == 1) {
                        sb.append('a');
                    } else {
                        sb.append("a^");
                        sb.append(iM1802a);
                    }
                }
                if (length != 0) {
                    if (length == 1) {
                        sb.append('x');
                    } else {
                        sb.append("x^");
                        sb.append(length);
                    }
                }
            }
        }
        return sb.toString();
    }
}
