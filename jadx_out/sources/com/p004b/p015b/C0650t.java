package com.p004b.p015b;

/* JADX INFO: renamed from: com.b.b.t */
/* JADX INFO: compiled from: Segment.java */
/* JADX INFO: loaded from: classes.dex */
final class C0650t {

    /* JADX INFO: renamed from: a */
    final byte[] f843a;

    /* JADX INFO: renamed from: b */
    int f844b;

    /* JADX INFO: renamed from: c */
    int f845c;

    /* JADX INFO: renamed from: d */
    boolean f846d;

    /* JADX INFO: renamed from: e */
    boolean f847e;

    /* JADX INFO: renamed from: f */
    C0650t f848f;

    /* JADX INFO: renamed from: g */
    C0650t f849g;

    C0650t() {
        this.f843a = new byte[8192];
        this.f847e = true;
        this.f846d = false;
    }

    C0650t(C0650t c0650t) {
        this(c0650t.f843a, c0650t.f844b, c0650t.f845c);
        c0650t.f846d = true;
    }

    C0650t(byte[] bArr, int i, int i2) {
        this.f843a = bArr;
        this.f844b = i;
        this.f845c = i2;
        this.f847e = false;
        this.f846d = true;
    }

    /* JADX INFO: renamed from: a */
    public final C0650t m612a() {
        C0650t c0650t = this.f848f != this ? this.f848f : null;
        this.f849g.f848f = this.f848f;
        this.f848f.f849g = this.f849g;
        this.f848f = null;
        this.f849g = null;
        return c0650t;
    }

    /* JADX INFO: renamed from: a */
    public final C0650t m613a(C0650t c0650t) {
        c0650t.f849g = this;
        c0650t.f848f = this.f848f;
        this.f848f.f849g = c0650t;
        this.f848f = c0650t;
        return c0650t;
    }

    /* JADX INFO: renamed from: a */
    public final void m614a(C0650t c0650t, int i) {
        if (!c0650t.f847e) {
            throw new IllegalArgumentException();
        }
        if (c0650t.f845c + i > 8192) {
            if (c0650t.f846d) {
                throw new IllegalArgumentException();
            }
            if ((c0650t.f845c + i) - c0650t.f844b > 8192) {
                throw new IllegalArgumentException();
            }
            System.arraycopy(c0650t.f843a, c0650t.f844b, c0650t.f843a, 0, c0650t.f845c - c0650t.f844b);
            c0650t.f845c -= c0650t.f844b;
            c0650t.f844b = 0;
        }
        System.arraycopy(this.f843a, this.f844b, c0650t.f843a, c0650t.f845c, i);
        c0650t.f845c += i;
        this.f844b += i;
    }
}
