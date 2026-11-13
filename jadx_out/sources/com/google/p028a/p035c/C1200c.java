package com.google.p028a.p035c;

/* JADX INFO: renamed from: com.google.a.c.c */
/* JADX INFO: compiled from: BitSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1200c {

    /* JADX INFO: renamed from: a */
    public int f2101a;

    /* JADX INFO: renamed from: b */
    public int f2102b;

    /* JADX INFO: renamed from: c */
    private final byte[] f2103c;

    public C1200c(byte[] bArr) {
        this.f2103c = bArr;
    }

    /* JADX INFO: renamed from: b */
    private int m1822b() {
        return this.f2102b;
    }

    /* JADX INFO: renamed from: c */
    private int m1823c() {
        return this.f2101a;
    }

    /* JADX INFO: renamed from: a */
    public final int m1825a(int i) {
        int i2;
        if (i <= 0 || i > 32 || i > m1824a()) {
            throw new IllegalArgumentException(String.valueOf(i));
        }
        if (this.f2102b > 0) {
            int i3 = 8 - this.f2102b;
            int i4 = i < i3 ? i : i3;
            int i5 = i3 - i4;
            i2 = (((255 >> (8 - i4)) << i5) & this.f2103c[this.f2101a]) >> i5;
            i -= i4;
            this.f2102b += i4;
            if (this.f2102b == 8) {
                this.f2102b = 0;
                this.f2101a++;
            }
        } else {
            i2 = 0;
        }
        if (i <= 0) {
            return i2;
        }
        while (i >= 8) {
            i2 = (i2 << 8) | (this.f2103c[this.f2101a] & 255);
            this.f2101a++;
            i -= 8;
        }
        if (i <= 0) {
            return i2;
        }
        int i6 = 8 - i;
        int i7 = (i2 << i) | ((((255 >> i6) << i6) & this.f2103c[this.f2101a]) >> i6);
        this.f2102b += i;
        return i7;
    }

    /* JADX INFO: renamed from: a */
    public final int m1824a() {
        return ((this.f2103c.length - this.f2101a) * 8) - this.f2102b;
    }
}
