package com.google.p028a.p051h.p052a.p053a;

/* JADX INFO: renamed from: com.google.a.h.a.a.c */
/* JADX INFO: compiled from: ModulusPoly.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1308c {

    /* JADX INFO: renamed from: a */
    public final C1307b f2563a;

    /* JADX INFO: renamed from: b */
    public final int[] f2564b;

    public C1308c(C1307b c1307b, int[] iArr) {
        if (iArr.length == 0) {
            throw new IllegalArgumentException();
        }
        this.f2563a = c1307b;
        int length = iArr.length;
        if (length > 1 && iArr[0] == 0) {
            int i = 1;
            while (i < length && iArr[i] == 0) {
                i++;
            }
            if (i == length) {
                this.f2564b = new int[]{0};
                return;
            } else {
                this.f2564b = new int[length - i];
                System.arraycopy(iArr, i, this.f2564b, 0, this.f2564b.length);
                return;
            }
        }
        this.f2564b = iArr;
    }

    /* JADX INFO: renamed from: c */
    private int[] m2242c() {
        return this.f2564b;
    }

    /* JADX INFO: renamed from: d */
    private int m2243d() {
        return this.f2564b.length - 1;
    }

    /* JADX INFO: renamed from: a */
    public final boolean m2246a() {
        return this.f2564b[0] == 0;
    }

    /* JADX INFO: renamed from: a */
    public final int m2244a(int i) {
        return this.f2564b[(this.f2564b.length - 1) - i];
    }

    /* JADX INFO: renamed from: b */
    public final int m2247b(int i) {
        if (i == 0) {
            return m2244a(0);
        }
        if (i == 1) {
            int iM2238b = 0;
            for (int i2 : this.f2564b) {
                iM2238b = this.f2563a.m2238b(iM2238b, i2);
            }
            return iM2238b;
        }
        int iM2238b2 = this.f2564b[0];
        int length = this.f2564b.length;
        for (int i3 = 1; i3 < length; i3++) {
            iM2238b2 = this.f2563a.m2238b(this.f2563a.m2240d(i, iM2238b2), this.f2564b[i3]);
        }
        return iM2238b2;
    }

    /* JADX INFO: renamed from: a */
    public final C1308c m2245a(C1308c c1308c) {
        if (!this.f2563a.equals(c1308c.f2563a)) {
            throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
        }
        if (m2246a()) {
            return c1308c;
        }
        if (c1308c.m2246a()) {
            return this;
        }
        int[] iArr = this.f2564b;
        int[] iArr2 = c1308c.f2564b;
        if (iArr.length > iArr2.length) {
            iArr = iArr2;
            iArr2 = iArr;
        }
        int[] iArr3 = new int[iArr2.length];
        int length = iArr2.length - iArr.length;
        System.arraycopy(iArr2, 0, iArr3, 0, length);
        for (int i = length; i < iArr2.length; i++) {
            iArr3[i] = this.f2563a.m2238b(iArr[i - length], iArr2[i]);
        }
        return new C1308c(this.f2563a, iArr3);
    }

    /* JADX INFO: renamed from: b */
    public final C1308c m2249b(C1308c c1308c) {
        if (this.f2563a.equals(c1308c.f2563a)) {
            return c1308c.m2246a() ? this : m2245a(c1308c.m2248b());
        }
        throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
    }

    /* JADX INFO: renamed from: c */
    public final C1308c m2251c(C1308c c1308c) {
        if (!this.f2563a.equals(c1308c.f2563a)) {
            throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
        }
        if (!m2246a() && !c1308c.m2246a()) {
            int[] iArr = this.f2564b;
            int length = iArr.length;
            int[] iArr2 = c1308c.f2564b;
            int length2 = iArr2.length;
            int[] iArr3 = new int[(length + length2) - 1];
            for (int i = 0; i < length; i++) {
                int i2 = iArr[i];
                for (int i3 = 0; i3 < length2; i3++) {
                    int i4 = i + i3;
                    iArr3[i4] = this.f2563a.m2238b(iArr3[i4], this.f2563a.m2240d(i2, iArr2[i3]));
                }
            }
            return new C1308c(this.f2563a, iArr3);
        }
        return this.f2563a.f2560d;
    }

    /* JADX INFO: renamed from: b */
    public final C1308c m2248b() {
        int length = this.f2564b.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = this.f2563a.m2239c(0, this.f2564b[i]);
        }
        return new C1308c(this.f2563a, iArr);
    }

    /* JADX INFO: renamed from: c */
    public final C1308c m2250c(int i) {
        if (i == 0) {
            return this.f2563a.f2560d;
        }
        if (i == 1) {
            return this;
        }
        int length = this.f2564b.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = this.f2563a.m2240d(this.f2564b[i2], i);
        }
        return new C1308c(this.f2563a, iArr);
    }

    /* JADX INFO: renamed from: a */
    private C1308c m2241a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        if (i2 != 0) {
            int length = this.f2564b.length;
            int[] iArr = new int[i + length];
            for (int i3 = 0; i3 < length; i3++) {
                iArr[i3] = this.f2563a.m2240d(this.f2564b[i3], i2);
            }
            return new C1308c(this.f2563a, iArr);
        }
        return this.f2563a.f2560d;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder((this.f2564b.length - 1) * 8);
        for (int length = this.f2564b.length - 1; length >= 0; length--) {
            int iM2244a = m2244a(length);
            if (iM2244a != 0) {
                if (iM2244a < 0) {
                    sb.append(" - ");
                    iM2244a = -iM2244a;
                } else if (sb.length() > 0) {
                    sb.append(" + ");
                }
                if (length == 0 || iM2244a != 1) {
                    sb.append(iM2244a);
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
