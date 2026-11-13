package com.google.p028a.p038d.p041c;

import java.util.Arrays;

/* JADX INFO: renamed from: com.google.a.d.c.e */
/* JADX INFO: compiled from: DefaultPlacement.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1223e {

    /* JADX INFO: renamed from: a */
    public final int f2217a;

    /* JADX INFO: renamed from: b */
    public final byte[] f2218b;

    /* JADX INFO: renamed from: c */
    private final CharSequence f2219c;

    /* JADX INFO: renamed from: d */
    private final int f2220d;

    public C1223e(CharSequence charSequence, int i, int i2) {
        this.f2219c = charSequence;
        this.f2217a = i;
        this.f2220d = i2;
        this.f2218b = new byte[i * i2];
        Arrays.fill(this.f2218b, (byte) -1);
    }

    /* JADX INFO: renamed from: b */
    private int m1938b() {
        return this.f2220d;
    }

    /* JADX INFO: renamed from: c */
    private int m1941c() {
        return this.f2217a;
    }

    /* JADX INFO: renamed from: d */
    private byte[] m1944d() {
        return this.f2218b;
    }

    /* JADX INFO: renamed from: a */
    private boolean m1937a(int i, int i2) {
        return this.f2218b[(i2 * this.f2217a) + i] == 1;
    }

    /* JADX INFO: renamed from: a */
    private void m1936a(int i, int i2, boolean z) {
        this.f2218b[(i2 * this.f2217a) + i] = z ? (byte) 1 : (byte) 0;
    }

    /* JADX INFO: renamed from: b */
    private boolean m1940b(int i, int i2) {
        return this.f2218b[(i2 * this.f2217a) + i] >= 0;
    }

    /* JADX INFO: renamed from: a */
    public final void m1945a() {
        int i = 4;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i == this.f2220d && i2 == 0) {
                m1935a(this.f2220d - 1, 0, i3, 1);
                m1935a(this.f2220d - 1, 1, i3, 2);
                m1935a(this.f2220d - 1, 2, i3, 3);
                m1935a(0, this.f2217a - 2, i3, 4);
                m1935a(0, this.f2217a - 1, i3, 5);
                m1935a(1, this.f2217a - 1, i3, 6);
                m1935a(2, this.f2217a - 1, i3, 7);
                m1935a(3, this.f2217a - 1, i3, 8);
                i3++;
            }
            if (i == this.f2220d - 2 && i2 == 0 && this.f2217a % 4 != 0) {
                m1935a(this.f2220d - 3, 0, i3, 1);
                m1935a(this.f2220d - 2, 0, i3, 2);
                m1935a(this.f2220d - 1, 0, i3, 3);
                m1935a(0, this.f2217a - 4, i3, 4);
                m1935a(0, this.f2217a - 3, i3, 5);
                m1935a(0, this.f2217a - 2, i3, 6);
                m1935a(0, this.f2217a - 1, i3, 7);
                m1935a(1, this.f2217a - 1, i3, 8);
                i3++;
            }
            if (i == this.f2220d - 2 && i2 == 0 && this.f2217a % 8 == 4) {
                m1935a(this.f2220d - 3, 0, i3, 1);
                m1935a(this.f2220d - 2, 0, i3, 2);
                m1935a(this.f2220d - 1, 0, i3, 3);
                m1935a(0, this.f2217a - 2, i3, 4);
                m1935a(0, this.f2217a - 1, i3, 5);
                m1935a(1, this.f2217a - 1, i3, 6);
                m1935a(2, this.f2217a - 1, i3, 7);
                m1935a(3, this.f2217a - 1, i3, 8);
                i3++;
            }
            if (i == this.f2220d + 4 && i2 == 2 && this.f2217a % 8 == 0) {
                m1935a(this.f2220d - 1, 0, i3, 1);
                m1935a(this.f2220d - 1, this.f2217a - 1, i3, 2);
                m1935a(0, this.f2217a - 3, i3, 3);
                m1935a(0, this.f2217a - 2, i3, 4);
                m1935a(0, this.f2217a - 1, i3, 5);
                m1935a(1, this.f2217a - 3, i3, 6);
                m1935a(1, this.f2217a - 2, i3, 7);
                m1935a(1, this.f2217a - 1, i3, 8);
                i3++;
            }
            do {
                if (i < this.f2220d && i2 >= 0 && !m1940b(i2, i)) {
                    m1934a(i, i2, i3);
                    i3++;
                }
                i -= 2;
                i2 += 2;
                if (i < 0) {
                    break;
                }
            } while (i2 < this.f2217a);
            int i4 = i + 1;
            int i5 = i2 + 3;
            do {
                if (i4 >= 0 && i5 < this.f2217a && !m1940b(i5, i4)) {
                    m1934a(i4, i5, i3);
                    i3++;
                }
                i4 += 2;
                i5 -= 2;
                if (i4 >= this.f2220d) {
                    break;
                }
            } while (i5 >= 0);
            i = i4 + 3;
            i2 = i5 + 1;
            if (i >= this.f2220d && i2 >= this.f2217a) {
                break;
            }
        }
        if (m1940b(this.f2217a - 1, this.f2220d - 1)) {
            return;
        }
        m1936a(this.f2217a - 1, this.f2220d - 1, true);
        m1936a(this.f2217a - 2, this.f2220d - 2, true);
    }

    /* JADX INFO: renamed from: a */
    private void m1935a(int i, int i2, int i3, int i4) {
        if (i < 0) {
            i += this.f2220d;
            i2 += 4 - ((this.f2220d + 4) % 8);
        }
        if (i2 < 0) {
            i2 += this.f2217a;
            i += 4 - ((this.f2217a + 4) % 8);
        }
        m1936a(i2, i, (this.f2219c.charAt(i3) & (1 << (8 - i4))) != 0);
    }

    /* JADX INFO: renamed from: a */
    private void m1934a(int i, int i2, int i3) {
        int i4 = i - 2;
        int i5 = i2 - 2;
        m1935a(i4, i5, i3, 1);
        int i6 = i2 - 1;
        m1935a(i4, i6, i3, 2);
        int i7 = i - 1;
        m1935a(i7, i5, i3, 3);
        m1935a(i7, i6, i3, 4);
        m1935a(i7, i2, i3, 5);
        m1935a(i, i5, i3, 6);
        m1935a(i, i6, i3, 7);
        m1935a(i, i2, i3, 8);
    }

    /* JADX INFO: renamed from: a */
    private void m1933a(int i) {
        m1935a(this.f2220d - 1, 0, i, 1);
        m1935a(this.f2220d - 1, 1, i, 2);
        m1935a(this.f2220d - 1, 2, i, 3);
        m1935a(0, this.f2217a - 2, i, 4);
        m1935a(0, this.f2217a - 1, i, 5);
        m1935a(1, this.f2217a - 1, i, 6);
        m1935a(2, this.f2217a - 1, i, 7);
        m1935a(3, this.f2217a - 1, i, 8);
    }

    /* JADX INFO: renamed from: b */
    private void m1939b(int i) {
        m1935a(this.f2220d - 3, 0, i, 1);
        m1935a(this.f2220d - 2, 0, i, 2);
        m1935a(this.f2220d - 1, 0, i, 3);
        m1935a(0, this.f2217a - 4, i, 4);
        m1935a(0, this.f2217a - 3, i, 5);
        m1935a(0, this.f2217a - 2, i, 6);
        m1935a(0, this.f2217a - 1, i, 7);
        m1935a(1, this.f2217a - 1, i, 8);
    }

    /* JADX INFO: renamed from: c */
    private void m1942c(int i) {
        m1935a(this.f2220d - 3, 0, i, 1);
        m1935a(this.f2220d - 2, 0, i, 2);
        m1935a(this.f2220d - 1, 0, i, 3);
        m1935a(0, this.f2217a - 2, i, 4);
        m1935a(0, this.f2217a - 1, i, 5);
        m1935a(1, this.f2217a - 1, i, 6);
        m1935a(2, this.f2217a - 1, i, 7);
        m1935a(3, this.f2217a - 1, i, 8);
    }

    /* JADX INFO: renamed from: d */
    private void m1943d(int i) {
        m1935a(this.f2220d - 1, 0, i, 1);
        m1935a(this.f2220d - 1, this.f2217a - 1, i, 2);
        m1935a(0, this.f2217a - 3, i, 3);
        m1935a(0, this.f2217a - 2, i, 4);
        m1935a(0, this.f2217a - 1, i, 5);
        m1935a(1, this.f2217a - 3, i, 6);
        m1935a(1, this.f2217a - 2, i, 7);
        m1935a(1, this.f2217a - 1, i, 8);
    }
}
