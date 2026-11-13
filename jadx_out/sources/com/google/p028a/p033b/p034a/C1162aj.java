package com.google.p028a.p033b.p034a;

/* JADX INFO: renamed from: com.google.a.b.a.aj */
/* JADX INFO: compiled from: WifiParsedResult.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1162aj extends AbstractC1179q {

    /* JADX INFO: renamed from: a */
    private final String f1966a;

    /* JADX INFO: renamed from: b */
    private final String f1967b;

    /* JADX INFO: renamed from: c */
    private final String f1968c;

    /* JADX INFO: renamed from: d */
    private final boolean f1969d;

    /* JADX INFO: renamed from: e */
    private final String f1970e;

    /* JADX INFO: renamed from: f */
    private final String f1971f;

    /* JADX INFO: renamed from: g */
    private final String f1972g;

    /* JADX INFO: renamed from: h */
    private final String f1973h;

    private C1162aj(String str, String str2, String str3) {
        this(str, str2, str3, (byte) 0);
    }

    private C1162aj(String str, String str2, String str3, byte b) {
        this(str, str2, str3, false, null, null, null, null);
    }

    public C1162aj(String str, String str2, String str3, boolean z, String str4, String str5, String str6, String str7) {
        super(C1180r.f2044j);
        this.f1966a = str2;
        this.f1967b = str;
        this.f1968c = str3;
        this.f1969d = z;
        this.f1970e = str4;
        this.f1971f = str5;
        this.f1972g = str6;
        this.f1973h = str7;
    }

    /* JADX INFO: renamed from: b */
    private String m1611b() {
        return this.f1966a;
    }

    /* JADX INFO: renamed from: c */
    private String m1612c() {
        return this.f1967b;
    }

    /* JADX INFO: renamed from: d */
    private String m1613d() {
        return this.f1968c;
    }

    /* JADX INFO: renamed from: e */
    private boolean m1614e() {
        return this.f1969d;
    }

    /* JADX INFO: renamed from: f */
    private String m1615f() {
        return this.f1970e;
    }

    /* JADX INFO: renamed from: g */
    private String m1616g() {
        return this.f1971f;
    }

    /* JADX INFO: renamed from: h */
    private String m1617h() {
        return this.f1972g;
    }

    /* JADX INFO: renamed from: i */
    private String m1618i() {
        return this.f1973h;
    }

    @Override // com.google.p028a.p033b.p034a.AbstractC1179q
    /* JADX INFO: renamed from: a */
    public final String mo1571a() {
        StringBuilder sb = new StringBuilder(80);
        m1699a(this.f1966a, sb);
        m1699a(this.f1967b, sb);
        m1699a(this.f1968c, sb);
        m1699a(Boolean.toString(this.f1969d), sb);
        return sb.toString();
    }
}
