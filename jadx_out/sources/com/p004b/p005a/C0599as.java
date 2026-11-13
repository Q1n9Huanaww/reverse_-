package com.p004b.p005a;

import java.io.Closeable;

/* JADX INFO: renamed from: com.b.a.as */
/* JADX INFO: compiled from: Response.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0599as implements Closeable {

    /* JADX INFO: renamed from: a */
    final C0595ao f563a;

    /* JADX INFO: renamed from: b */
    final EnumC0592al f564b;

    /* JADX INFO: renamed from: c */
    final int f565c;

    /* JADX INFO: renamed from: d */
    final String f566d;

    /* JADX INFO: renamed from: e */
    final C0629z f567e;

    /* JADX INFO: renamed from: f */
    final C0581aa f568f;

    /* JADX INFO: renamed from: g */
    final AbstractC0601au f569g;

    /* JADX INFO: renamed from: h */
    final C0599as f570h;

    /* JADX INFO: renamed from: i */
    final C0599as f571i;

    /* JADX INFO: renamed from: j */
    final C0599as f572j;

    /* JADX INFO: renamed from: k */
    final long f573k;

    /* JADX INFO: renamed from: l */
    final long f574l;

    /* JADX INFO: renamed from: m */
    private volatile C0608e f575m;

    C0599as(C0600at c0600at) {
        this.f563a = c0600at.f576a;
        this.f564b = c0600at.f577b;
        this.f565c = c0600at.f578c;
        this.f566d = c0600at.f579d;
        this.f567e = c0600at.f580e;
        this.f568f = c0600at.f581f.m327a();
        this.f569g = c0600at.f582g;
        this.f570h = c0600at.f583h;
        this.f571i = c0600at.f584i;
        this.f572j = c0600at.f585j;
        this.f573k = c0600at.f586k;
        this.f574l = c0600at.f587l;
    }

    /* JADX INFO: renamed from: a */
    public final C0595ao m418a() {
        return this.f563a;
    }

    /* JADX INFO: renamed from: b */
    public final int m420b() {
        return this.f565c;
    }

    /* JADX INFO: renamed from: c */
    public final C0629z m421c() {
        return this.f567e;
    }

    /* JADX INFO: renamed from: d */
    public final C0581aa m422d() {
        return this.f568f;
    }

    /* JADX INFO: renamed from: e */
    public final AbstractC0601au m423e() {
        return this.f569g;
    }

    /* JADX INFO: renamed from: f */
    public final C0600at m424f() {
        return new C0600at(this);
    }

    /* JADX INFO: renamed from: g */
    public final C0608e m425g() {
        C0608e c0608e = this.f575m;
        if (c0608e != null) {
            return c0608e;
        }
        C0608e c0608eM451a = C0608e.m451a(this.f568f);
        this.f575m = c0608eM451a;
        return c0608eM451a;
    }

    /* JADX INFO: renamed from: h */
    public final long m426h() {
        return this.f573k;
    }

    /* JADX INFO: renamed from: i */
    public final long m427i() {
        return this.f574l;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f569g.close();
    }

    public final String toString() {
        return "Response{protocol=" + this.f564b + ", code=" + this.f565c + ", message=" + this.f566d + ", url=" + this.f563a.f548a + '}';
    }

    /* JADX INFO: renamed from: a */
    public final String m419a(String str) {
        String strM323a = this.f568f.m323a(str);
        if (strM323a != null) {
            return strM323a;
        }
        return null;
    }
}
