package com.google.p028a;

import java.util.EnumMap;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.r */
/* JADX INFO: compiled from: Result.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1363r {

    /* JADX INFO: renamed from: a */
    public final String f2839a;

    /* JADX INFO: renamed from: b */
    public final byte[] f2840b;

    /* JADX INFO: renamed from: c */
    public final int f2841c;

    /* JADX INFO: renamed from: d */
    public C1365t[] f2842d;

    /* JADX INFO: renamed from: e */
    public final EnumC1138a f2843e;

    /* JADX INFO: renamed from: f */
    public Map<EnumC1364s, Object> f2844f;

    /* JADX INFO: renamed from: g */
    public final long f2845g;

    public C1363r(String str, byte[] bArr, C1365t[] c1365tArr, EnumC1138a enumC1138a) {
        this(str, bArr, c1365tArr, enumC1138a, System.currentTimeMillis());
    }

    private C1363r(String str, byte[] bArr, C1365t[] c1365tArr, EnumC1138a enumC1138a, long j) {
        this(str, bArr, bArr == null ? 0 : bArr.length * 8, c1365tArr, enumC1138a, j);
    }

    public C1363r(String str, byte[] bArr, int i, C1365t[] c1365tArr, EnumC1138a enumC1138a, long j) {
        this.f2839a = str;
        this.f2840b = bArr;
        this.f2841c = i;
        this.f2842d = c1365tArr;
        this.f2843e = enumC1138a;
        this.f2844f = null;
        this.f2845g = j;
    }

    /* JADX INFO: renamed from: a */
    private String m2616a() {
        return this.f2839a;
    }

    /* JADX INFO: renamed from: b */
    private byte[] m2618b() {
        return this.f2840b;
    }

    /* JADX INFO: renamed from: c */
    private int m2619c() {
        return this.f2841c;
    }

    /* JADX INFO: renamed from: d */
    private C1365t[] m2620d() {
        return this.f2842d;
    }

    /* JADX INFO: renamed from: e */
    private EnumC1138a m2621e() {
        return this.f2843e;
    }

    /* JADX INFO: renamed from: f */
    private Map<EnumC1364s, Object> m2622f() {
        return this.f2844f;
    }

    /* JADX INFO: renamed from: a */
    public final void m2624a(EnumC1364s enumC1364s, Object obj) {
        if (this.f2844f == null) {
            this.f2844f = new EnumMap(EnumC1364s.class);
        }
        this.f2844f.put(enumC1364s, obj);
    }

    /* JADX INFO: renamed from: a */
    public final void m2625a(Map<EnumC1364s, Object> map) {
        if (map != null) {
            if (this.f2844f == null) {
                this.f2844f = map;
            } else {
                this.f2844f.putAll(map);
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private void m2617a(C1365t[] c1365tArr) {
        C1365t[] c1365tArr2 = this.f2842d;
        if (c1365tArr2 == null) {
            this.f2842d = c1365tArr;
            return;
        }
        if (c1365tArr == null || c1365tArr.length <= 0) {
            return;
        }
        C1365t[] c1365tArr3 = new C1365t[c1365tArr2.length + c1365tArr.length];
        System.arraycopy(c1365tArr2, 0, c1365tArr3, 0, c1365tArr2.length);
        System.arraycopy(c1365tArr, 0, c1365tArr3, c1365tArr2.length, c1365tArr.length);
        this.f2842d = c1365tArr3;
    }

    /* JADX INFO: renamed from: g */
    private long m2623g() {
        return this.f2845g;
    }

    public final String toString() {
        return this.f2839a;
    }
}
