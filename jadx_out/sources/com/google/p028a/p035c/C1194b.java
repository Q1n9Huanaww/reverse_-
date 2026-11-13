package com.google.p028a.p035c;

import java.util.Arrays;

/* JADX INFO: renamed from: com.google.a.c.b */
/* JADX INFO: compiled from: BitMatrix.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1194b implements Cloneable {

    /* JADX INFO: renamed from: a */
    public final int f2077a;

    /* JADX INFO: renamed from: b */
    public final int f2078b;

    /* JADX INFO: renamed from: c */
    public final int f2079c;

    /* JADX INFO: renamed from: d */
    public final int[] f2080d;

    public C1194b(int i) {
        this(i, i);
    }

    public C1194b(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            throw new IllegalArgumentException("Both dimensions must be greater than 0");
        }
        this.f2077a = i;
        this.f2078b = i2;
        this.f2079c = (i + 31) / 32;
        this.f2080d = new int[this.f2079c * i2];
    }

    private C1194b(int i, int i2, int i3, int[] iArr) {
        this.f2077a = i;
        this.f2078b = i2;
        this.f2079c = i3;
        this.f2080d = iArr;
    }

    /* JADX INFO: renamed from: a */
    public static C1194b m1775a(boolean[][] zArr) {
        int length = zArr.length;
        int length2 = zArr[0].length;
        C1194b c1194b = new C1194b(length2, length);
        for (int i = 0; i < length; i++) {
            boolean[] zArr2 = zArr[i];
            for (int i2 = 0; i2 < length2; i2++) {
                if (zArr2[i2]) {
                    c1194b.m1790b(i2, i);
                }
            }
        }
        return c1194b;
    }

    /* JADX INFO: renamed from: a */
    private static C1194b m1774a(String str, String str2, String str3) {
        if (str == null) {
            throw new IllegalArgumentException();
        }
        boolean[] zArr = new boolean[str.length()];
        int length = 0;
        int i = 0;
        int i2 = 0;
        int i3 = -1;
        int i4 = 0;
        while (length < str.length()) {
            if (str.charAt(length) == '\n' || str.charAt(length) == '\r') {
                if (i > i2) {
                    if (i3 == -1) {
                        i3 = i - i2;
                    } else if (i - i2 != i3) {
                        throw new IllegalArgumentException("row lengths do not match");
                    }
                    i4++;
                    i2 = i;
                }
                length++;
            } else if (str.substring(length, str2.length() + length).equals(str2)) {
                length += str2.length();
                zArr[i] = true;
                i++;
            } else if (str.substring(length, str3.length() + length).equals(str3)) {
                length += str3.length();
                zArr[i] = false;
                i++;
            } else {
                throw new IllegalArgumentException("illegal character encountered: " + str.substring(length));
            }
        }
        if (i > i2) {
            if (i3 == -1) {
                i3 = i - i2;
            } else if (i - i2 != i3) {
                throw new IllegalArgumentException("row lengths do not match");
            }
            i4++;
        }
        C1194b c1194b = new C1194b(i3, i4);
        for (int i5 = 0; i5 < i; i5++) {
            if (zArr[i5]) {
                c1194b.m1790b(i5 % i3, i5 / i3);
            }
        }
        return c1194b;
    }

    /* JADX INFO: renamed from: a */
    public final boolean m1789a(int i, int i2) {
        return ((this.f2080d[(i2 * this.f2079c) + (i / 32)] >>> (i & 31)) & 1) != 0;
    }

    /* JADX INFO: renamed from: b */
    public final void m1790b(int i, int i2) {
        int i3 = (i2 * this.f2079c) + (i / 32);
        int[] iArr = this.f2080d;
        iArr[i3] = (1 << (i & 31)) | iArr[i3];
    }

    /* JADX INFO: renamed from: d */
    private void m1780d(int i, int i2) {
        int i3 = (i2 * this.f2079c) + (i / 32);
        int[] iArr = this.f2080d;
        iArr[i3] = ((1 << (i & 31)) ^ (-1)) & iArr[i3];
    }

    /* JADX INFO: renamed from: c */
    public final void m1793c(int i, int i2) {
        int i3 = (i2 * this.f2079c) + (i / 32);
        int[] iArr = this.f2080d;
        iArr[i3] = (1 << (i & 31)) ^ iArr[i3];
    }

    /* JADX INFO: renamed from: a */
    private void m1777a(C1194b c1194b) {
        if (this.f2077a != c1194b.f2077a || this.f2078b != c1194b.f2078b || this.f2079c != c1194b.f2079c) {
            throw new IllegalArgumentException("input matrix dimensions do not match");
        }
        C1190a c1190a = new C1190a(this.f2077a);
        for (int i = 0; i < this.f2078b; i++) {
            int i2 = this.f2079c * i;
            int[] iArr = c1194b.m1786a(i, c1190a).f2064a;
            for (int i3 = 0; i3 < this.f2079c; i3++) {
                int[] iArr2 = this.f2080d;
                int i4 = i2 + i3;
                iArr2[i4] = iArr2[i4] ^ iArr[i3];
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public final void m1787a() {
        int length = this.f2080d.length;
        for (int i = 0; i < length; i++) {
            this.f2080d[i] = 0;
        }
    }

    /* JADX INFO: renamed from: a */
    public final void m1788a(int i, int i2, int i3, int i4) {
        if (i2 < 0 || i < 0) {
            throw new IllegalArgumentException("Left and top must be nonnegative");
        }
        if (i4 <= 0 || i3 <= 0) {
            throw new IllegalArgumentException("Height and width must be at least 1");
        }
        int i5 = i3 + i;
        int i6 = i4 + i2;
        if (i6 > this.f2078b || i5 > this.f2077a) {
            throw new IllegalArgumentException("The region must fit inside the matrix");
        }
        while (i2 < i6) {
            int i7 = this.f2079c * i2;
            for (int i8 = i; i8 < i5; i8++) {
                int[] iArr = this.f2080d;
                int i9 = (i8 / 32) + i7;
                iArr[i9] = iArr[i9] | (1 << (i8 & 31));
            }
            i2++;
        }
    }

    /* JADX INFO: renamed from: f */
    private int[] m1782f() {
        int i = this.f2077a;
        int i2 = -1;
        int i3 = this.f2078b;
        int i4 = -1;
        int i5 = i;
        int i6 = 0;
        while (i6 < this.f2078b) {
            int i7 = i4;
            int i8 = i2;
            int i9 = i5;
            for (int i10 = 0; i10 < this.f2079c; i10++) {
                int i11 = this.f2080d[(this.f2079c * i6) + i10];
                if (i11 != 0) {
                    if (i6 < i3) {
                        i3 = i6;
                    }
                    if (i6 > i7) {
                        i7 = i6;
                    }
                    int i12 = i10 << 5;
                    int i13 = 31;
                    if (i12 < i9) {
                        int i14 = 0;
                        while ((i11 << (31 - i14)) == 0) {
                            i14++;
                        }
                        int i15 = i14 + i12;
                        if (i15 < i9) {
                            i9 = i15;
                        }
                    }
                    if (i12 + 31 > i8) {
                        while ((i11 >>> i13) == 0) {
                            i13--;
                        }
                        int i16 = i12 + i13;
                        if (i16 > i8) {
                            i8 = i16;
                        }
                    }
                }
            }
            i6++;
            i5 = i9;
            i2 = i8;
            i4 = i7;
        }
        if (i2 < i5 || i4 < i3) {
            return null;
        }
        return new int[]{i5, i3, (i2 - i5) + 1, (i4 - i3) + 1};
    }

    /* JADX INFO: renamed from: b */
    public final int[] m1792b() {
        int i = 0;
        while (i < this.f2080d.length && this.f2080d[i] == 0) {
            i++;
        }
        if (i == this.f2080d.length) {
            return null;
        }
        int i2 = i / this.f2079c;
        int i3 = (i % this.f2079c) << 5;
        int i4 = 0;
        while ((this.f2080d[i] << (31 - i4)) == 0) {
            i4++;
        }
        return new int[]{i3 + i4, i2};
    }

    /* JADX INFO: renamed from: c */
    public final int[] m1794c() {
        int length = this.f2080d.length - 1;
        while (length >= 0 && this.f2080d[length] == 0) {
            length--;
        }
        if (length < 0) {
            return null;
        }
        int i = length / this.f2079c;
        int i2 = (length % this.f2079c) << 5;
        int i3 = 31;
        while ((this.f2080d[length] >>> i3) == 0) {
            i3--;
        }
        return new int[]{i2 + i3, i};
    }

    /* JADX INFO: renamed from: g */
    private int m1783g() {
        return this.f2077a;
    }

    /* JADX INFO: renamed from: h */
    private int m1784h() {
        return this.f2078b;
    }

    /* JADX INFO: renamed from: i */
    private int m1785i() {
        return this.f2079c;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C1194b)) {
            return false;
        }
        C1194b c1194b = (C1194b) obj;
        return this.f2077a == c1194b.f2077a && this.f2078b == c1194b.f2078b && this.f2079c == c1194b.f2079c && Arrays.equals(this.f2080d, c1194b.f2080d);
    }

    public final int hashCode() {
        return (((((((this.f2077a * 31) + this.f2077a) * 31) + this.f2078b) * 31) + this.f2079c) * 31) + Arrays.hashCode(this.f2080d);
    }

    /* JADX INFO: renamed from: a */
    private String m1776a(String str, String str2) {
        return m1779c(str, str2, "\n");
    }

    @Deprecated
    /* JADX INFO: renamed from: b */
    private String m1778b(String str, String str2, String str3) {
        return m1779c(str, str2, str3);
    }

    /* JADX INFO: renamed from: c */
    private String m1779c(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(this.f2078b * (this.f2077a + 1));
        for (int i = 0; i < this.f2078b; i++) {
            for (int i2 = 0; i2 < this.f2077a; i2++) {
                sb.append(m1789a(i2, i) ? str : str2);
            }
            sb.append(str3);
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public final C1194b clone() {
        return new C1194b(this.f2077a, this.f2078b, this.f2079c, (int[]) this.f2080d.clone());
    }

    /* JADX INFO: renamed from: a */
    public final C1190a m1786a(int i, C1190a c1190a) {
        if (c1190a == null || c1190a.f2065b < this.f2077a) {
            c1190a = new C1190a(this.f2077a);
        } else {
            c1190a.m1757b();
        }
        int i2 = i * this.f2079c;
        for (int i3 = 0; i3 < this.f2079c; i3++) {
            c1190a.f2064a[(i3 << 5) / 32] = this.f2080d[i2 + i3];
        }
        return c1190a;
    }

    /* JADX INFO: renamed from: b */
    public final void m1791b(int i, C1190a c1190a) {
        System.arraycopy(c1190a.f2064a, 0, this.f2080d, i * this.f2079c, this.f2079c);
    }

    /* JADX INFO: renamed from: e */
    private void m1781e() {
        int i = this.f2077a;
        int i2 = this.f2078b;
        C1190a c1190a = new C1190a(i);
        C1190a c1190a2 = new C1190a(i);
        for (int i3 = 0; i3 < (i2 + 1) / 2; i3++) {
            c1190a = m1786a(i3, c1190a);
            int i4 = (i2 - 1) - i3;
            c1190a2 = m1786a(i4, c1190a2);
            c1190a.m1761c();
            c1190a2.m1761c();
            m1791b(i3, c1190a2);
            m1791b(i4, c1190a);
        }
    }

    public final String toString() {
        return m1779c("X ", "  ", "\n");
    }
}
