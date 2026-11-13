package com.google.p028a.p038d.p041c;

import com.google.p028a.C1238f;
import java.nio.charset.StandardCharsets;

/* JADX INFO: renamed from: com.google.a.d.c.h */
/* JADX INFO: compiled from: EncoderContext.java */
/* JADX INFO: loaded from: classes.dex */
final class C1226h {

    /* JADX INFO: renamed from: a */
    final String f2221a;

    /* JADX INFO: renamed from: b */
    EnumC1230l f2222b;

    /* JADX INFO: renamed from: c */
    C1238f f2223c;

    /* JADX INFO: renamed from: d */
    C1238f f2224d;

    /* JADX INFO: renamed from: e */
    final StringBuilder f2225e;

    /* JADX INFO: renamed from: f */
    int f2226f;

    /* JADX INFO: renamed from: g */
    int f2227g;

    /* JADX INFO: renamed from: h */
    C1229k f2228h;

    /* JADX INFO: renamed from: i */
    int f2229i;

    C1226h(String str) {
        byte[] bytes = str.getBytes(StandardCharsets.ISO_8859_1);
        StringBuilder sb = new StringBuilder(bytes.length);
        int length = bytes.length;
        for (int i = 0; i < length; i++) {
            char c = (char) (bytes[i] & 255);
            if (c == '?' && str.charAt(i) != '?') {
                throw new IllegalArgumentException("Message contains characters outside ISO-8859-1 encoding.");
            }
            sb.append(c);
        }
        this.f2221a = sb.toString();
        this.f2222b = EnumC1230l.FORCE_NONE;
        this.f2225e = new StringBuilder(str.length());
        this.f2227g = -1;
    }

    /* JADX INFO: renamed from: a */
    private void m1949a(EnumC1230l enumC1230l) {
        this.f2222b = enumC1230l;
    }

    /* JADX INFO: renamed from: a */
    private void m1950a(C1238f c1238f, C1238f c1238f2) {
        this.f2223c = c1238f;
        this.f2224d = c1238f2;
    }

    /* JADX INFO: renamed from: e */
    private String m1952e() {
        return this.f2221a;
    }

    /* JADX INFO: renamed from: f */
    private void m1953f() {
        this.f2229i = 2;
    }

    /* JADX INFO: renamed from: a */
    public final char m1962a() {
        return this.f2221a.charAt(this.f2226f);
    }

    /* JADX INFO: renamed from: g */
    private char m1954g() {
        return this.f2221a.charAt(this.f2226f);
    }

    /* JADX INFO: renamed from: h */
    private StringBuilder m1955h() {
        return this.f2225e;
    }

    /* JADX INFO: renamed from: a */
    public final void m1965a(String str) {
        this.f2225e.append(str);
    }

    /* JADX INFO: renamed from: a */
    public final void m1963a(char c) {
        this.f2225e.append(c);
    }

    /* JADX INFO: renamed from: i */
    private int m1956i() {
        return this.f2225e.length();
    }

    /* JADX INFO: renamed from: j */
    private int m1957j() {
        return this.f2227g;
    }

    /* JADX INFO: renamed from: b */
    private void m1951b(int i) {
        this.f2227g = i;
    }

    /* JADX INFO: renamed from: k */
    private void m1958k() {
        this.f2227g = -1;
    }

    /* JADX INFO: renamed from: b */
    public final boolean m1966b() {
        return this.f2226f < m1959l();
    }

    /* JADX INFO: renamed from: l */
    private int m1959l() {
        return this.f2221a.length() - this.f2229i;
    }

    /* JADX INFO: renamed from: c */
    public final int m1967c() {
        return m1959l() - this.f2226f;
    }

    /* JADX INFO: renamed from: m */
    private C1229k m1960m() {
        return this.f2228h;
    }

    /* JADX INFO: renamed from: a */
    public final void m1964a(int i) {
        if (this.f2228h == null || i > this.f2228h.f2257b) {
            this.f2228h = C1229k.m1989a(i, this.f2222b, this.f2223c, this.f2224d, true);
        }
    }

    /* JADX INFO: renamed from: n */
    private void m1961n() {
        this.f2228h = null;
    }

    /* JADX INFO: renamed from: d */
    public final void m1968d() {
        m1964a(this.f2225e.length());
    }
}
