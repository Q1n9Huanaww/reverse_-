package com.p004b.p005a.p006a.p011e;

import android.support.v7.widget.ActivityChooserView;
import com.p004b.p005a.p006a.C0503c;
import com.p004b.p015b.C0636f;
import com.p004b.p015b.C0639i;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: renamed from: com.b.a.a.e.f */
/* JADX INFO: compiled from: Hpack.java */
/* JADX INFO: loaded from: classes.dex */
final class C0543f {

    /* JADX INFO: renamed from: a */
    int f307a;

    /* JADX INFO: renamed from: b */
    int f308b;

    /* JADX INFO: renamed from: c */
    C0540c[] f309c;

    /* JADX INFO: renamed from: d */
    int f310d;

    /* JADX INFO: renamed from: e */
    int f311e;

    /* JADX INFO: renamed from: f */
    int f312f;

    /* JADX INFO: renamed from: g */
    private final C0636f f313g;

    /* JADX INFO: renamed from: h */
    private final boolean f314h;

    /* JADX INFO: renamed from: i */
    private int f315i;

    /* JADX INFO: renamed from: j */
    private boolean f316j;

    C0543f(C0636f c0636f) {
        this(c0636f, (byte) 0);
    }

    private C0543f(C0636f c0636f, byte b) {
        this.f315i = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.f309c = new C0540c[8];
        this.f310d = this.f309c.length - 1;
        this.f311e = 0;
        this.f312f = 0;
        this.f307a = 4096;
        this.f308b = 4096;
        this.f314h = true;
        this.f313g = c0636f;
    }

    /* JADX INFO: renamed from: a */
    private void m245a() {
        Arrays.fill(this.f309c, (Object) null);
        this.f310d = this.f309c.length - 1;
        this.f311e = 0;
        this.f312f = 0;
    }

    /* JADX INFO: renamed from: b */
    private int m249b(int i) {
        int i2 = 0;
        if (i > 0) {
            int length = this.f309c.length;
            while (true) {
                length--;
                if (length < this.f310d || i <= 0) {
                    break;
                }
                i -= this.f309c[length].f296i;
                this.f312f -= this.f309c[length].f296i;
                this.f311e--;
                i2++;
            }
            System.arraycopy(this.f309c, this.f310d + 1, this.f309c, this.f310d + 1 + i2, this.f311e);
            Arrays.fill(this.f309c, this.f310d + 1, this.f310d + 1 + i2, (Object) null);
            this.f310d += i2;
        }
        return i2;
    }

    /* JADX INFO: renamed from: a */
    private void m247a(C0540c c0540c) {
        int i = c0540c.f296i;
        if (i > this.f308b) {
            m245a();
            return;
        }
        m249b((this.f312f + i) - this.f308b);
        if (this.f311e + 1 > this.f309c.length) {
            C0540c[] c0540cArr = new C0540c[this.f309c.length * 2];
            System.arraycopy(this.f309c, 0, c0540cArr, this.f309c.length, this.f309c.length);
            this.f310d = this.f309c.length - 1;
            this.f309c = c0540cArr;
        }
        int i2 = this.f310d;
        this.f310d = i2 - 1;
        this.f309c[i2] = c0540c;
        this.f311e++;
        this.f312f += i;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x006e  */
    /* JADX INFO: renamed from: a */
    final void m251a(List<C0540c> list) {
        int length;
        int length2;
        if (this.f316j) {
            if (this.f315i < this.f308b) {
                m246a(this.f315i, 31, 32);
            }
            this.f316j = false;
            this.f315i = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            m246a(this.f308b, 31, 32);
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            C0540c c0540c = list.get(i);
            C0639i c0639iMo589f = c0540c.f294g.mo589f();
            C0639i c0639i = c0540c.f295h;
            Integer num = C0541d.f298b.get(c0639iMo589f);
            if (num != null) {
                length = num.intValue() + 1;
                if (length <= 1 || length >= 8) {
                    length2 = length;
                    length = -1;
                } else if (C0503c.m121a(C0541d.f297a[length - 1].f295h, c0639i)) {
                    length2 = length;
                } else if (C0503c.m121a(C0541d.f297a[length].f295h, c0639i)) {
                    length2 = length;
                    length++;
                } else {
                    length2 = length;
                    length = -1;
                }
            } else {
                length = -1;
                length2 = -1;
            }
            if (length == -1) {
                int length3 = this.f309c.length;
                for (int i2 = this.f310d + 1; i2 < length3; i2++) {
                    if (C0503c.m121a(this.f309c[i2].f294g, c0639iMo589f)) {
                        if (C0503c.m121a(this.f309c[i2].f295h, c0639i)) {
                            length = C0541d.f297a.length + (i2 - this.f310d);
                            break;
                        } else if (length2 == -1) {
                            length2 = (i2 - this.f310d) + C0541d.f297a.length;
                        }
                    }
                }
            }
            if (length != -1) {
                m246a(length, 127, 128);
            } else if (length2 == -1) {
                this.f313g.mo562h(64);
                m248a(c0639iMo589f);
                m248a(c0639i);
                m247a(c0540c);
            } else {
                C0639i c0639i2 = C0540c.f288a;
                if (c0639iMo589f.mo583a(0, c0639i2, 0, c0639i2.mo590g()) && !C0540c.f293f.equals(c0639iMo589f)) {
                    m246a(length2, 15, 0);
                    m248a(c0639i);
                } else {
                    m246a(length2, 63, 64);
                    m248a(c0639i);
                    m247a(c0540c);
                }
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private void m246a(int i, int i2, int i3) {
        if (i < i2) {
            this.f313g.mo562h(i | i3);
            return;
        }
        this.f313g.mo562h(i3 | i2);
        int i4 = i - i2;
        while (i4 >= 128) {
            this.f313g.mo562h(128 | (i4 & 127));
            i4 >>>= 7;
        }
        this.f313g.mo562h(i4);
    }

    /* JADX INFO: renamed from: a */
    private void m248a(C0639i c0639i) {
        if (this.f314h) {
            C0532ag.m212a();
            if (C0532ag.m211a(c0639i) < c0639i.mo590g()) {
                C0636f c0636f = new C0636f();
                C0532ag.m212a();
                C0532ag.m213a(c0639i, c0636f);
                C0639i c0639iM568k = c0636f.m568k();
                m246a(c0639iM568k.mo590g(), 127, 128);
                this.f313g.m532a(c0639iM568k);
                return;
            }
        }
        m246a(c0639i.mo590g(), 127, 0);
        this.f313g.m532a(c0639i);
    }

    /* JADX INFO: renamed from: a */
    final void m250a(int i) {
        this.f307a = i;
        int iMin = Math.min(i, 16384);
        if (this.f308b == iMin) {
            return;
        }
        if (iMin < this.f308b) {
            this.f315i = Math.min(this.f315i, iMin);
        }
        this.f316j = true;
        this.f308b = iMin;
        if (this.f308b < this.f312f) {
            if (this.f308b == 0) {
                m245a();
            } else {
                m249b(this.f312f - this.f308b);
            }
        }
    }
}
