package com.google.p028a.p029a.p032c;

import com.google.p028a.p035c.C1190a;
import java.util.Iterator;
import java.util.LinkedList;

/* JADX INFO: renamed from: com.google.a.a.c.f */
/* JADX INFO: compiled from: State.java */
/* JADX INFO: loaded from: classes.dex */
final class C1149f {

    /* JADX INFO: renamed from: a */
    static final C1149f f1930a = new C1149f(AbstractC1150g.f1935a, 0, 0, 0);

    /* JADX INFO: renamed from: b */
    final int f1931b;

    /* JADX INFO: renamed from: c */
    final int f1932c;

    /* JADX INFO: renamed from: d */
    final int f1933d;

    /* JADX INFO: renamed from: e */
    private final AbstractC1150g f1934e;

    private C1149f(AbstractC1150g abstractC1150g, int i, int i2, int i3) {
        this.f1934e = abstractC1150g;
        this.f1931b = i;
        this.f1932c = i2;
        this.f1933d = i3;
    }

    /* JADX INFO: renamed from: a */
    private int m1546a() {
        return this.f1931b;
    }

    /* JADX INFO: renamed from: b */
    private AbstractC1150g m1547b() {
        return this.f1934e;
    }

    /* JADX INFO: renamed from: c */
    private int m1548c() {
        return this.f1932c;
    }

    /* JADX INFO: renamed from: d */
    private int m1549d() {
        return this.f1933d;
    }

    /* JADX INFO: renamed from: a */
    final C1149f m1551a(int i, int i2) {
        int i3 = this.f1933d;
        AbstractC1150g abstractC1150gM1558a = this.f1934e;
        if (i != this.f1931b) {
            int i4 = C1147d.f1923g[this.f1931b][i];
            int i5 = 65535 & i4;
            int i6 = i4 >> 16;
            abstractC1150gM1558a = abstractC1150gM1558a.m1558a(i5, i6);
            i3 += i6;
        }
        int i7 = i == 2 ? 4 : 5;
        return new C1149f(abstractC1150gM1558a.m1558a(i2, i7), i, 0, i3 + i7);
    }

    /* JADX INFO: renamed from: b */
    final C1149f m1555b(int i, int i2) {
        AbstractC1150g abstractC1150g = this.f1934e;
        int i3 = this.f1931b == 2 ? 4 : 5;
        return new C1149f(abstractC1150g.m1558a(C1147d.f1924h[this.f1931b][i], i3).m1558a(i2, 5), this.f1931b, 0, this.f1933d + i3 + 5);
    }

    /* JADX INFO: renamed from: a */
    final C1149f m1550a(int i) {
        int i2;
        AbstractC1150g abstractC1150gM1558a = this.f1934e;
        int i3 = this.f1931b;
        int i4 = this.f1933d;
        if (this.f1931b == 4 || this.f1931b == 2) {
            int i5 = C1147d.f1923g[i3][0];
            int i6 = 65535 & i5;
            int i7 = i5 >> 16;
            abstractC1150gM1558a = abstractC1150gM1558a.m1558a(i6, i7);
            i4 += i7;
            i3 = 0;
        }
        if (this.f1932c == 0 || this.f1932c == 31) {
            i2 = 18;
        } else {
            i2 = this.f1932c == 62 ? 9 : 8;
        }
        C1149f c1149f = new C1149f(abstractC1150gM1558a, i3, this.f1932c + 1, i4 + i2);
        return c1149f.f1932c == 2078 ? c1149f.m1554b(i + 1) : c1149f;
    }

    /* JADX INFO: renamed from: b */
    final C1149f m1554b(int i) {
        return this.f1932c == 0 ? this : new C1149f(new C1145b(this.f1934e, i - this.f1932c, this.f1932c), this.f1931b, 0, this.f1933d);
    }

    /* JADX INFO: renamed from: a */
    final boolean m1553a(C1149f c1149f) {
        int i = this.f1933d + (C1147d.f1923g[this.f1931b][c1149f.f1931b] >> 16);
        if (c1149f.f1932c > 0 && (this.f1932c == 0 || this.f1932c > c1149f.f1932c)) {
            i += 10;
        }
        return i <= c1149f.f1933d;
    }

    /* JADX INFO: renamed from: a */
    final C1190a m1552a(byte[] bArr) {
        LinkedList linkedList = new LinkedList();
        for (AbstractC1150g abstractC1150g = m1554b(bArr.length).f1934e; abstractC1150g != null; abstractC1150g = abstractC1150g.f1936b) {
            linkedList.addFirst(abstractC1150g);
        }
        C1190a c1190a = new C1190a();
        Iterator it2 = linkedList.iterator();
        while (it2.hasNext()) {
            ((AbstractC1150g) it2.next()).mo1528a(c1190a, bArr);
        }
        return c1190a;
    }

    public final String toString() {
        return String.format("%s bits=%d bytes=%d", C1147d.f1917a[this.f1931b], Integer.valueOf(this.f1933d), Integer.valueOf(this.f1932c));
    }
}
