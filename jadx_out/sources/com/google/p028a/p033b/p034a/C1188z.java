package com.google.p028a.p033b.p034a;

/* JADX INFO: renamed from: com.google.a.b.a.z */
/* JADX INFO: compiled from: TelParsedResult.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1188z extends AbstractC1179q {

    /* JADX INFO: renamed from: a */
    private final String f2059a;

    /* JADX INFO: renamed from: b */
    private final String f2060b;

    /* JADX INFO: renamed from: c */
    private final String f2061c;

    public C1188z(String str, String str2) {
        super(C1180r.f2041g);
        this.f2059a = str;
        this.f2060b = str2;
        this.f2061c = null;
    }

    /* JADX INFO: renamed from: b */
    private String m1730b() {
        return this.f2059a;
    }

    /* JADX INFO: renamed from: c */
    private String m1731c() {
        return this.f2060b;
    }

    /* JADX INFO: renamed from: d */
    private String m1732d() {
        return this.f2061c;
    }

    @Override // com.google.p028a.p033b.p034a.AbstractC1179q
    /* JADX INFO: renamed from: a */
    public final String mo1571a() {
        StringBuilder sb = new StringBuilder(20);
        m1699a(this.f2059a, sb);
        m1699a(this.f2061c, sb);
        return sb.toString();
    }
}
