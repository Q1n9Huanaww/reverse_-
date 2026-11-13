package com.google.p028a;

import com.google.p028a.p035c.C1190a;
import com.google.p028a.p035c.C1194b;

/* JADX INFO: renamed from: com.google.a.c */
/* JADX INFO: compiled from: BinaryBitmap.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1189c {

    /* JADX INFO: renamed from: a */
    public final AbstractC1151b f2062a;

    /* JADX INFO: renamed from: b */
    private C1194b f2063b;

    public C1189c(AbstractC1151b abstractC1151b) {
        if (abstractC1151b == null) {
            throw new IllegalArgumentException("Binarizer must be non-null.");
        }
        this.f2062a = abstractC1151b;
    }

    /* JADX INFO: renamed from: a */
    public final int m1738a() {
        return this.f2062a.f1937a.f2820a;
    }

    /* JADX INFO: renamed from: b */
    public final int m1740b() {
        return this.f2062a.f1937a.f2821b;
    }

    /* JADX INFO: renamed from: a */
    private C1190a m1733a(int i, C1190a c1190a) throws C1358m {
        return this.f2062a.mo1563a(i, c1190a);
    }

    /* JADX INFO: renamed from: c */
    public final C1194b m1741c() throws C1358m {
        if (this.f2063b == null) {
            this.f2063b = this.f2062a.mo1564a();
        }
        return this.f2063b;
    }

    /* JADX INFO: renamed from: d */
    private boolean m1734d() {
        return this.f2062a.f1937a.mo2425b();
    }

    /* JADX INFO: renamed from: a */
    public final C1189c m1739a(int i, int i2, int i3, int i4) {
        return new C1189c(this.f2062a.mo1562a(this.f2062a.f1937a.mo2422a(i, i2, i3, i4)));
    }

    /* JADX INFO: renamed from: e */
    private boolean m1735e() {
        return this.f2062a.f1937a.mo2426c();
    }

    /* JADX INFO: renamed from: f */
    private C1189c m1736f() {
        return new C1189c(this.f2062a.mo1562a(this.f2062a.f1937a.mo2428e()));
    }

    /* JADX INFO: renamed from: g */
    private C1189c m1737g() {
        return new C1189c(this.f2062a.mo1562a(this.f2062a.f1937a.mo2429f()));
    }

    public final String toString() {
        try {
            return m1741c().toString();
        } catch (C1358m unused) {
            return "";
        }
    }
}
