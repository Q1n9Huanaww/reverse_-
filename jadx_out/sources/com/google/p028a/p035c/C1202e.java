package com.google.p028a.p035c;

import java.util.List;

/* JADX INFO: renamed from: com.google.a.c.e */
/* JADX INFO: compiled from: DecoderResult.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1202e {

    /* JADX INFO: renamed from: a */
    public final byte[] f2136a;

    /* JADX INFO: renamed from: b */
    public int f2137b;

    /* JADX INFO: renamed from: c */
    public final String f2138c;

    /* JADX INFO: renamed from: d */
    public final List<byte[]> f2139d;

    /* JADX INFO: renamed from: e */
    public final String f2140e;

    /* JADX INFO: renamed from: f */
    public Integer f2141f;

    /* JADX INFO: renamed from: g */
    public Integer f2142g;

    /* JADX INFO: renamed from: h */
    public Object f2143h;

    /* JADX INFO: renamed from: i */
    public final int f2144i;

    /* JADX INFO: renamed from: j */
    public final int f2145j;

    public C1202e(byte[] bArr, String str, List<byte[]> list, String str2) {
        this(bArr, str, list, str2, -1, -1);
    }

    public C1202e(byte[] bArr, String str, List<byte[]> list, String str2, int i, int i2) {
        this.f2136a = bArr;
        this.f2137b = bArr == null ? 0 : bArr.length * 8;
        this.f2138c = str;
        this.f2139d = list;
        this.f2140e = str2;
        this.f2144i = i2;
        this.f2145j = i;
    }

    /* JADX INFO: renamed from: b */
    private byte[] m1833b() {
        return this.f2136a;
    }

    /* JADX INFO: renamed from: c */
    private int m1834c() {
        return this.f2137b;
    }

    /* JADX INFO: renamed from: a */
    private void m1829a(int i) {
        this.f2137b = i;
    }

    /* JADX INFO: renamed from: d */
    private String m1835d() {
        return this.f2138c;
    }

    /* JADX INFO: renamed from: e */
    private List<byte[]> m1836e() {
        return this.f2139d;
    }

    /* JADX INFO: renamed from: f */
    private String m1837f() {
        return this.f2140e;
    }

    /* JADX INFO: renamed from: g */
    private Integer m1838g() {
        return this.f2141f;
    }

    /* JADX INFO: renamed from: a */
    private void m1830a(Integer num) {
        this.f2141f = num;
    }

    /* JADX INFO: renamed from: h */
    private Integer m1839h() {
        return this.f2142g;
    }

    /* JADX INFO: renamed from: b */
    private void m1832b(Integer num) {
        this.f2142g = num;
    }

    /* JADX INFO: renamed from: i */
    private Object m1840i() {
        return this.f2143h;
    }

    /* JADX INFO: renamed from: a */
    private void m1831a(Object obj) {
        this.f2143h = obj;
    }

    /* JADX INFO: renamed from: a */
    public final boolean m1843a() {
        return this.f2144i >= 0 && this.f2145j >= 0;
    }

    /* JADX INFO: renamed from: j */
    private int m1841j() {
        return this.f2144i;
    }

    /* JADX INFO: renamed from: k */
    private int m1842k() {
        return this.f2145j;
    }
}
