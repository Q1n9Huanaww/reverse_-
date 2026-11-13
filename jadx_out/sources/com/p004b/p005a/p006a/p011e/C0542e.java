package com.p004b.p005a.p006a.p011e;

import com.p004b.p015b.C0639i;
import com.p004b.p015b.C0644n;
import com.p004b.p015b.InterfaceC0638h;
import com.p004b.p015b.InterfaceC0654x;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: renamed from: com.b.a.a.e.e */
/* JADX INFO: compiled from: Hpack.java */
/* JADX INFO: loaded from: classes.dex */
final class C0542e {

    /* JADX INFO: renamed from: a */
    C0540c[] f299a;

    /* JADX INFO: renamed from: b */
    int f300b;

    /* JADX INFO: renamed from: c */
    int f301c;

    /* JADX INFO: renamed from: d */
    int f302d;

    /* JADX INFO: renamed from: e */
    private final List<C0540c> f303e;

    /* JADX INFO: renamed from: f */
    private final InterfaceC0638h f304f;

    /* JADX INFO: renamed from: g */
    private final int f305g;

    /* JADX INFO: renamed from: h */
    private int f306h;

    C0542e(InterfaceC0654x interfaceC0654x) {
        this(interfaceC0654x, (byte) 0);
    }

    private C0542e(InterfaceC0654x interfaceC0654x, byte b) {
        this.f303e = new ArrayList();
        this.f299a = new C0540c[8];
        this.f300b = this.f299a.length - 1;
        this.f301c = 0;
        this.f302d = 0;
        this.f305g = 4096;
        this.f306h = 4096;
        this.f304f = C0644n.m606a(interfaceC0654x);
    }

    /* JADX INFO: renamed from: c */
    private void m238c() {
        if (this.f306h < this.f302d) {
            if (this.f306h == 0) {
                m239d();
            } else {
                m233a(this.f302d - this.f306h);
            }
        }
    }

    /* JADX INFO: renamed from: d */
    private void m239d() {
        Arrays.fill(this.f299a, (Object) null);
        this.f300b = this.f299a.length - 1;
        this.f301c = 0;
        this.f302d = 0;
    }

    /* JADX INFO: renamed from: a */
    private int m233a(int i) {
        int i2 = 0;
        if (i > 0) {
            int length = this.f299a.length;
            while (true) {
                length--;
                if (length < this.f300b || i <= 0) {
                    break;
                }
                i -= this.f299a[length].f296i;
                this.f302d -= this.f299a[length].f296i;
                this.f301c--;
                i2++;
            }
            System.arraycopy(this.f299a, this.f300b + 1, this.f299a, this.f300b + 1 + i2, this.f301c);
            this.f300b += i2;
        }
        return i2;
    }

    /* JADX INFO: renamed from: a */
    final void m243a() {
        while (!this.f304f.mo551d()) {
            int iMo552e = this.f304f.mo552e() & 255;
            if (iMo552e == 128) {
                throw new IOException("index == 0");
            }
            if ((iMo552e & 128) == 128) {
                int iM234a = m234a(iMo552e, 127) - 1;
                if (m240d(iM234a)) {
                    this.f303e.add(C0541d.f297a[iM234a]);
                } else {
                    int iM236b = m236b(iM234a - C0541d.f297a.length);
                    if (iM236b < 0 || iM236b > this.f299a.length - 1) {
                        throw new IOException("Header index too large " + (iM234a + 1));
                    }
                    this.f303e.add(this.f299a[iM236b]);
                }
            } else if (iMo552e == 64) {
                m235a(new C0540c(C0541d.m232a(m242f()), m242f()));
            } else if ((iMo552e & 64) == 64) {
                m235a(new C0540c(m237c(m234a(iMo552e, 63) - 1), m242f()));
            } else if ((iMo552e & 32) == 32) {
                this.f306h = m234a(iMo552e, 31);
                if (this.f306h < 0 || this.f306h > this.f305g) {
                    throw new IOException("Invalid dynamic table size update " + this.f306h);
                }
                m238c();
            } else if (iMo552e == 16 || iMo552e == 0) {
                this.f303e.add(new C0540c(C0541d.m232a(m242f()), m242f()));
            } else {
                this.f303e.add(new C0540c(m237c(m234a(iMo552e, 15) - 1), m242f()));
            }
        }
    }

    /* JADX INFO: renamed from: b */
    public final List<C0540c> m244b() {
        ArrayList arrayList = new ArrayList(this.f303e);
        this.f303e.clear();
        return arrayList;
    }

    /* JADX INFO: renamed from: b */
    private int m236b(int i) {
        return this.f300b + 1 + i;
    }

    /* JADX INFO: renamed from: c */
    private C0639i m237c(int i) {
        if (m240d(i)) {
            return C0541d.f297a[i].f294g;
        }
        return this.f299a[m236b(i - C0541d.f297a.length)].f294g;
    }

    /* JADX INFO: renamed from: d */
    private static boolean m240d(int i) {
        return i >= 0 && i <= C0541d.f297a.length - 1;
    }

    /* JADX INFO: renamed from: a */
    private void m235a(C0540c c0540c) {
        this.f303e.add(c0540c);
        int i = c0540c.f296i;
        if (i > this.f306h) {
            m239d();
            return;
        }
        m233a((this.f302d + i) - this.f306h);
        if (this.f301c + 1 > this.f299a.length) {
            C0540c[] c0540cArr = new C0540c[this.f299a.length * 2];
            System.arraycopy(this.f299a, 0, c0540cArr, this.f299a.length, this.f299a.length);
            this.f300b = this.f299a.length - 1;
            this.f299a = c0540cArr;
        }
        int i2 = this.f300b;
        this.f300b = i2 - 1;
        this.f299a[i2] = c0540c;
        this.f301c++;
        this.f302d += i;
    }

    /* JADX INFO: renamed from: e */
    private int m241e() {
        return this.f304f.mo552e() & 255;
    }

    /* JADX INFO: renamed from: a */
    private int m234a(int i, int i2) {
        int i3 = i & i2;
        if (i3 < i2) {
            return i3;
        }
        int i4 = 0;
        while (true) {
            int iM241e = m241e();
            if ((iM241e & 128) == 0) {
                return i2 + (iM241e << i4);
            }
            i2 += (iM241e & 127) << i4;
            i4 += 7;
        }
    }

    /* JADX INFO: renamed from: f */
    private C0639i m242f() {
        int iM241e = m241e();
        boolean z = (iM241e & 128) == 128;
        int iM234a = m234a(iM241e, 127);
        if (z) {
            return C0639i.m576a(C0532ag.m212a().m215a(this.f304f.mo554e(iM234a)));
        }
        return this.f304f.mo548c(iM234a);
    }
}
