package com.google.p028a.p033b.p034a;

/* JADX INFO: renamed from: com.google.a.b.a.ah */
/* JADX INFO: compiled from: VINParsedResult.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1160ah extends AbstractC1179q {

    /* JADX INFO: renamed from: a */
    private final String f1955a;

    /* JADX INFO: renamed from: b */
    private final String f1956b;

    /* JADX INFO: renamed from: c */
    private final String f1957c;

    /* JADX INFO: renamed from: d */
    private final String f1958d;

    /* JADX INFO: renamed from: e */
    private final String f1959e;

    /* JADX INFO: renamed from: f */
    private final String f1960f;

    /* JADX INFO: renamed from: g */
    private final int f1961g;

    /* JADX INFO: renamed from: h */
    private final char f1962h;

    /* JADX INFO: renamed from: i */
    private final String f1963i;

    public C1160ah(String str, String str2, String str3, String str4, String str5, String str6, int i, char c, String str7) {
        super(C1180r.f2046l);
        this.f1955a = str;
        this.f1956b = str2;
        this.f1957c = str3;
        this.f1958d = str4;
        this.f1959e = str5;
        this.f1960f = str6;
        this.f1961g = i;
        this.f1962h = c;
        this.f1963i = str7;
    }

    /* JADX INFO: renamed from: b */
    private String m1595b() {
        return this.f1955a;
    }

    /* JADX INFO: renamed from: c */
    private String m1596c() {
        return this.f1956b;
    }

    /* JADX INFO: renamed from: d */
    private String m1597d() {
        return this.f1957c;
    }

    /* JADX INFO: renamed from: e */
    private String m1598e() {
        return this.f1958d;
    }

    /* JADX INFO: renamed from: f */
    private String m1599f() {
        return this.f1959e;
    }

    /* JADX INFO: renamed from: g */
    private String m1600g() {
        return this.f1960f;
    }

    /* JADX INFO: renamed from: h */
    private int m1601h() {
        return this.f1961g;
    }

    /* JADX INFO: renamed from: i */
    private char m1602i() {
        return this.f1962h;
    }

    /* JADX INFO: renamed from: j */
    private String m1603j() {
        return this.f1963i;
    }

    @Override // com.google.p028a.p033b.p034a.AbstractC1179q
    /* JADX INFO: renamed from: a */
    public final String mo1571a() {
        StringBuilder sb = new StringBuilder(50);
        sb.append(this.f1956b);
        sb.append(' ');
        sb.append(this.f1957c);
        sb.append(' ');
        sb.append(this.f1958d);
        sb.append('\n');
        if (this.f1959e != null) {
            sb.append(this.f1959e);
            sb.append(' ');
        }
        sb.append(this.f1961g);
        sb.append(' ');
        sb.append(this.f1962h);
        sb.append(' ');
        sb.append(this.f1963i);
        sb.append('\n');
        return sb.toString();
    }
}
