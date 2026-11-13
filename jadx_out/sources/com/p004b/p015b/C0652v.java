package com.p004b.p015b;

import java.util.Arrays;

/* JADX INFO: renamed from: com.b.b.v */
/* JADX INFO: compiled from: SegmentedByteString.java */
/* JADX INFO: loaded from: classes.dex */
final class C0652v extends C0639i {

    /* JADX INFO: renamed from: f */
    final transient byte[][] f852f;

    /* JADX INFO: renamed from: g */
    final transient int[] f853g;

    C0652v(C0636f c0636f, int i) {
        super(null);
        C0631aa.m519a(c0636f.f814b, 0L, i);
        int i2 = 0;
        C0650t c0650t = c0636f.f813a;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            if (c0650t.f845c == c0650t.f844b) {
                throw new AssertionError("s.limit == s.pos");
            }
            i3 += c0650t.f845c - c0650t.f844b;
            i4++;
            c0650t = c0650t.f848f;
        }
        this.f852f = new byte[i4][];
        this.f853g = new int[i4 * 2];
        C0650t c0650t2 = c0636f.f813a;
        int i5 = 0;
        while (i2 < i) {
            this.f852f[i5] = c0650t2.f843a;
            i2 += c0650t2.f845c - c0650t2.f844b;
            if (i2 > i) {
                i2 = i;
            }
            this.f853g[i5] = i2;
            this.f853g[this.f852f.length + i5] = c0650t2.f844b;
            c0650t2.f846d = true;
            i5++;
            c0650t2 = c0650t2.f848f;
        }
    }

    @Override // com.p004b.p015b.C0639i
    /* JADX INFO: renamed from: a */
    public final String mo581a() {
        return m618i().mo581a();
    }

    @Override // com.p004b.p015b.C0639i
    /* JADX INFO: renamed from: b */
    public final String mo585b() {
        return m618i().mo585b();
    }

    @Override // com.p004b.p015b.C0639i
    /* JADX INFO: renamed from: e */
    public final String mo588e() {
        return m618i().mo588e();
    }

    @Override // com.p004b.p015b.C0639i
    /* JADX INFO: renamed from: f */
    public final C0639i mo589f() {
        return m618i().mo589f();
    }

    @Override // com.p004b.p015b.C0639i
    /* JADX INFO: renamed from: c */
    public final C0639i mo586c() {
        return m618i().mo586c();
    }

    @Override // com.p004b.p015b.C0639i
    /* JADX INFO: renamed from: d */
    public final C0639i mo587d() {
        return m618i().mo587d();
    }

    @Override // com.p004b.p015b.C0639i
    /* JADX INFO: renamed from: a */
    public final C0639i mo580a(int i, int i2) {
        return m618i().mo580a(i, i2);
    }

    @Override // com.p004b.p015b.C0639i
    /* JADX INFO: renamed from: a */
    public final byte mo579a(int i) {
        C0631aa.m519a(this.f853g[this.f852f.length - 1], i, 1L);
        int iM617b = m617b(i);
        return this.f852f[iM617b][(i - (iM617b == 0 ? 0 : this.f853g[iM617b - 1])) + this.f853g[this.f852f.length + iM617b]];
    }

    /* JADX INFO: renamed from: b */
    private int m617b(int i) {
        int iBinarySearch = Arrays.binarySearch(this.f853g, 0, this.f852f.length, i + 1);
        return iBinarySearch >= 0 ? iBinarySearch : iBinarySearch ^ (-1);
    }

    @Override // com.p004b.p015b.C0639i
    /* JADX INFO: renamed from: g */
    public final int mo590g() {
        return this.f853g[this.f852f.length - 1];
    }

    @Override // com.p004b.p015b.C0639i
    /* JADX INFO: renamed from: h */
    public final byte[] mo591h() {
        byte[] bArr = new byte[this.f853g[this.f852f.length - 1]];
        int length = this.f852f.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = this.f853g[length + i];
            int i4 = this.f853g[i];
            System.arraycopy(this.f852f[i], i3, bArr, i2, i4 - i2);
            i++;
            i2 = i4;
        }
        return bArr;
    }

    @Override // com.p004b.p015b.C0639i
    /* JADX INFO: renamed from: a */
    final void mo582a(C0636f c0636f) {
        int length = this.f852f.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = this.f853g[length + i];
            int i4 = this.f853g[i];
            C0650t c0650t = new C0650t(this.f852f[i], i3, (i3 + i4) - i2);
            if (c0636f.f813a == null) {
                c0650t.f849g = c0650t;
                c0650t.f848f = c0650t;
                c0636f.f813a = c0650t;
            } else {
                c0636f.f813a.f849g.m613a(c0650t);
            }
            i++;
            i2 = i4;
        }
        c0636f.f814b += (long) i2;
    }

    @Override // com.p004b.p015b.C0639i
    /* JADX INFO: renamed from: a */
    public final boolean mo583a(int i, C0639i c0639i, int i2, int i3) {
        if (mo590g() - i3 < 0) {
            return false;
        }
        int iM617b = m617b(0);
        while (i3 > 0) {
            int i4 = iM617b == 0 ? 0 : this.f853g[iM617b - 1];
            int iMin = Math.min(i3, ((this.f853g[iM617b] - i4) + i4) - i);
            if (!c0639i.mo584a(i2, this.f852f[iM617b], (i - i4) + this.f853g[this.f852f.length + iM617b], iMin)) {
                return false;
            }
            i += iMin;
            i2 += iMin;
            i3 -= iMin;
            iM617b++;
        }
        return true;
    }

    @Override // com.p004b.p015b.C0639i
    /* JADX INFO: renamed from: a */
    public final boolean mo584a(int i, byte[] bArr, int i2, int i3) {
        if (i < 0 || i > mo590g() - i3 || i2 < 0 || i2 > bArr.length - i3) {
            return false;
        }
        int iM617b = m617b(i);
        while (i3 > 0) {
            int i4 = iM617b == 0 ? 0 : this.f853g[iM617b - 1];
            int iMin = Math.min(i3, ((this.f853g[iM617b] - i4) + i4) - i);
            if (!C0631aa.m521a(this.f852f[iM617b], (i - i4) + this.f853g[this.f852f.length + iM617b], bArr, i2, iMin)) {
                return false;
            }
            i += iMin;
            i2 += iMin;
            i3 -= iMin;
            iM617b++;
        }
        return true;
    }

    /* JADX INFO: renamed from: i */
    private C0639i m618i() {
        return new C0639i(mo591h());
    }

    @Override // com.p004b.p015b.C0639i
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C0639i) {
            C0639i c0639i = (C0639i) obj;
            if (c0639i.mo590g() == mo590g() && mo583a(0, c0639i, 0, mo590g())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.p004b.p015b.C0639i
    public final int hashCode() {
        int i = this.f818d;
        if (i != 0) {
            return i;
        }
        int length = this.f852f.length;
        int i2 = 0;
        int i3 = 0;
        int i4 = 1;
        while (i2 < length) {
            byte[] bArr = this.f852f[i2];
            int i5 = this.f853g[length + i2];
            int i6 = this.f853g[i2];
            int i7 = (i6 - i3) + i5;
            while (i5 < i7) {
                i4 = (i4 * 31) + bArr[i5];
                i5++;
            }
            i2++;
            i3 = i6;
        }
        this.f818d = i4;
        return i4;
    }

    @Override // com.p004b.p015b.C0639i
    public final String toString() {
        return m618i().toString();
    }
}
