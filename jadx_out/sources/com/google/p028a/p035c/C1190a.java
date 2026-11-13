package com.google.p028a.p035c;

import java.util.Arrays;
import org.apache.commons.p092io.FilenameUtils;

/* JADX INFO: renamed from: com.google.a.c.a */
/* JADX INFO: compiled from: BitArray.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1190a implements Cloneable {

    /* JADX INFO: renamed from: a */
    public int[] f2064a;

    /* JADX INFO: renamed from: b */
    public int f2065b;

    public C1190a() {
        this.f2065b = 0;
        this.f2064a = new int[1];
    }

    public C1190a(int i) {
        this.f2065b = i;
        this.f2064a = m1750g(i);
    }

    private C1190a(int[] iArr, int i) {
        this.f2064a = iArr;
        this.f2065b = i;
    }

    /* JADX INFO: renamed from: d */
    private int m1744d() {
        return this.f2065b;
    }

    /* JADX INFO: renamed from: a */
    public final int m1751a() {
        return (this.f2065b + 7) / 8;
    }

    /* JADX INFO: renamed from: e */
    private void m1746e(int i) {
        if (i > (this.f2064a.length << 5)) {
            int[] iArrM1750g = m1750g(i);
            System.arraycopy(this.f2064a, 0, iArrM1750g, 0, this.f2064a.length);
            this.f2064a = iArrM1750g;
        }
    }

    /* JADX INFO: renamed from: a */
    public final boolean m1755a(int i) {
        return ((1 << (i & 31)) & this.f2064a[i / 32]) != 0;
    }

    /* JADX INFO: renamed from: b */
    public final void m1758b(int i) {
        int[] iArr = this.f2064a;
        int i2 = i / 32;
        iArr[i2] = (1 << (i & 31)) | iArr[i2];
    }

    /* JADX INFO: renamed from: f */
    private void m1749f(int i) {
        int[] iArr = this.f2064a;
        int i2 = i / 32;
        iArr[i2] = (1 << (i & 31)) ^ iArr[i2];
    }

    /* JADX INFO: renamed from: c */
    public final int m1760c(int i) {
        if (i >= this.f2065b) {
            return this.f2065b;
        }
        int i2 = i / 32;
        int i3 = (((1 << (i & 31)) - 1) ^ (-1)) & this.f2064a[i2];
        while (i3 == 0) {
            i2++;
            if (i2 == this.f2064a.length) {
                return this.f2065b;
            }
            i3 = this.f2064a[i2];
        }
        int iNumberOfTrailingZeros = (i2 << 5) + Integer.numberOfTrailingZeros(i3);
        return iNumberOfTrailingZeros > this.f2065b ? this.f2065b : iNumberOfTrailingZeros;
    }

    /* JADX INFO: renamed from: d */
    public final int m1762d(int i) {
        if (i >= this.f2065b) {
            return this.f2065b;
        }
        int i2 = i / 32;
        int i3 = (((1 << (i & 31)) - 1) ^ (-1)) & (this.f2064a[i2] ^ (-1));
        while (i3 == 0) {
            i2++;
            if (i2 == this.f2064a.length) {
                return this.f2065b;
            }
            i3 = this.f2064a[i2] ^ (-1);
        }
        int iNumberOfTrailingZeros = (i2 << 5) + Integer.numberOfTrailingZeros(i3);
        return iNumberOfTrailingZeros > this.f2065b ? this.f2065b : iNumberOfTrailingZeros;
    }

    /* JADX INFO: renamed from: c */
    private void m1743c(int i, int i2) {
        this.f2064a[i / 32] = i2;
    }

    /* JADX INFO: renamed from: d */
    private void m1745d(int i, int i2) {
        if (i2 < i || i < 0 || i2 > this.f2065b) {
            throw new IllegalArgumentException();
        }
        if (i2 == i) {
            return;
        }
        int i3 = i2 - 1;
        int i4 = i / 32;
        int i5 = i3 / 32;
        int i6 = i4;
        while (i6 <= i5) {
            int i7 = 31;
            int i8 = i6 > i4 ? 0 : i & 31;
            if (i6 >= i5) {
                i7 = 31 & i3;
            }
            int i9 = (2 << i7) - (1 << i8);
            int[] iArr = this.f2064a;
            iArr[i6] = i9 | iArr[i6];
            i6++;
        }
    }

    /* JADX INFO: renamed from: b */
    public final void m1757b() {
        int length = this.f2064a.length;
        for (int i = 0; i < length; i++) {
            this.f2064a[i] = 0;
        }
    }

    /* JADX INFO: renamed from: a */
    public final boolean m1756a(int i, int i2) {
        if (i2 < i || i < 0 || i2 > this.f2065b) {
            throw new IllegalArgumentException();
        }
        if (i2 == i) {
            return true;
        }
        int i3 = i2 - 1;
        int i4 = i / 32;
        int i5 = i3 / 32;
        int i6 = i4;
        while (i6 <= i5) {
            if ((((2 << (i6 >= i5 ? 31 & i3 : 31)) - (1 << (i6 > i4 ? 0 : i & 31))) & this.f2064a[i6]) != 0) {
                return false;
            }
            i6++;
        }
        return true;
    }

    /* JADX INFO: renamed from: a */
    public final void m1754a(boolean z) {
        m1746e(this.f2065b + 1);
        if (z) {
            int[] iArr = this.f2064a;
            int i = this.f2065b / 32;
            iArr[i] = iArr[i] | (1 << (this.f2065b & 31));
        }
        this.f2065b++;
    }

    /* JADX INFO: renamed from: b */
    public final void m1759b(int i, int i2) {
        if (i2 < 0 || i2 > 32) {
            throw new IllegalArgumentException("Num bits must be between 0 and 32");
        }
        m1746e(this.f2065b + i2);
        while (i2 > 0) {
            boolean z = true;
            if (((i >> (i2 - 1)) & 1) != 1) {
                z = false;
            }
            m1754a(z);
            i2--;
        }
    }

    /* JADX INFO: renamed from: a */
    public final void m1753a(C1190a c1190a) {
        int i = c1190a.f2065b;
        m1746e(this.f2065b + i);
        for (int i2 = 0; i2 < i; i2++) {
            m1754a(c1190a.m1755a(i2));
        }
    }

    /* JADX INFO: renamed from: b */
    private void m1742b(C1190a c1190a) {
        if (this.f2065b != c1190a.f2065b) {
            throw new IllegalArgumentException("Sizes don't match");
        }
        for (int i = 0; i < this.f2064a.length; i++) {
            int[] iArr = this.f2064a;
            iArr[i] = iArr[i] ^ c1190a.f2064a[i];
        }
    }

    /* JADX INFO: renamed from: a */
    public final void m1752a(int i, byte[] bArr, int i2) {
        int i3 = i;
        int i4 = 0;
        while (i4 < i2) {
            int i5 = i3;
            int i6 = 0;
            for (int i7 = 0; i7 < 8; i7++) {
                if (m1755a(i5)) {
                    i6 |= 1 << (7 - i7);
                }
                i5++;
            }
            bArr[i4 + 0] = (byte) i6;
            i4++;
            i3 = i5;
        }
    }

    /* JADX INFO: renamed from: e */
    private int[] m1747e() {
        return this.f2064a;
    }

    /* JADX INFO: renamed from: c */
    public final void m1761c() {
        int[] iArr = new int[this.f2064a.length];
        int i = (this.f2065b - 1) / 32;
        int i2 = i + 1;
        for (int i3 = 0; i3 < i2; i3++) {
            long j = this.f2064a[i3];
            long j2 = ((j >> 1) & 1431655765) | ((j & 1431655765) << 1);
            long j3 = ((j2 >> 2) & 858993459) | ((j2 & 858993459) << 2);
            long j4 = ((j3 >> 4) & 252645135) | ((j3 & 252645135) << 4);
            long j5 = ((j4 >> 8) & 16711935) | ((j4 & 16711935) << 8);
            iArr[i - i3] = (int) (((j5 >> 16) & 65535) | ((j5 & 65535) << 16));
        }
        int i4 = i2 << 5;
        if (this.f2065b != i4) {
            int i5 = i4 - this.f2065b;
            int i6 = iArr[0] >>> i5;
            for (int i7 = 1; i7 < i2; i7++) {
                int i8 = iArr[i7];
                iArr[i7 - 1] = i6 | (i8 << (32 - i5));
                i6 = i8 >>> i5;
            }
            iArr[i2 - 1] = i6;
        }
        this.f2064a = iArr;
    }

    /* JADX INFO: renamed from: g */
    private static int[] m1750g(int i) {
        return new int[(i + 31) / 32];
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C1190a)) {
            return false;
        }
        C1190a c1190a = (C1190a) obj;
        return this.f2065b == c1190a.f2065b && Arrays.equals(this.f2064a, c1190a.f2064a);
    }

    public final int hashCode() {
        return (this.f2065b * 31) + Arrays.hashCode(this.f2064a);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(this.f2065b + (this.f2065b / 8) + 1);
        for (int i = 0; i < this.f2065b; i++) {
            if ((i & 7) == 0) {
                sb.append(' ');
            }
            sb.append(m1755a(i) ? 'X' : FilenameUtils.EXTENSION_SEPARATOR);
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: f */
    private C1190a m1748f() {
        return new C1190a((int[]) this.f2064a.clone(), this.f2065b);
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return new C1190a((int[]) this.f2064a.clone(), this.f2065b);
    }
}
