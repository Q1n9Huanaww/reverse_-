package com.google.p028a.p033b.p034a;

/* JADX INFO: renamed from: com.google.a.b.a.d */
/* JADX INFO: compiled from: AddressBookParsedResult.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1166d extends AbstractC1179q {

    /* JADX INFO: renamed from: a */
    private final String[] f1974a;

    /* JADX INFO: renamed from: b */
    private final String[] f1975b;

    /* JADX INFO: renamed from: c */
    private final String f1976c;

    /* JADX INFO: renamed from: d */
    private final String[] f1977d;

    /* JADX INFO: renamed from: e */
    private final String[] f1978e;

    /* JADX INFO: renamed from: f */
    private final String[] f1979f;

    /* JADX INFO: renamed from: g */
    private final String[] f1980g;

    /* JADX INFO: renamed from: h */
    private final String f1981h;

    /* JADX INFO: renamed from: i */
    private final String f1982i;

    /* JADX INFO: renamed from: j */
    private final String[] f1983j;

    /* JADX INFO: renamed from: k */
    private final String[] f1984k;

    /* JADX INFO: renamed from: l */
    private final String f1985l;

    /* JADX INFO: renamed from: m */
    private final String f1986m;

    /* JADX INFO: renamed from: n */
    private final String f1987n;

    /* JADX INFO: renamed from: o */
    private final String[] f1988o;

    /* JADX INFO: renamed from: p */
    private final String[] f1989p;

    private C1166d(String[] strArr, String[] strArr2, String[] strArr3, String[] strArr4, String[] strArr5, String[] strArr6, String[] strArr7) {
        this(strArr, null, null, strArr2, strArr3, strArr4, strArr5, null, null, strArr6, strArr7, null, null, null, null, null);
    }

    public C1166d(String[] strArr, String[] strArr2, String str, String[] strArr3, String[] strArr4, String[] strArr5, String[] strArr6, String str2, String str3, String[] strArr7, String[] strArr8, String str4, String str5, String str6, String[] strArr9, String[] strArr10) {
        super(C1180r.f2035a);
        if (strArr3 != null && strArr4 != null && strArr3.length != strArr4.length) {
            throw new IllegalArgumentException("Phone numbers and types lengths differ");
        }
        if (strArr5 != null && strArr6 != null && strArr5.length != strArr6.length) {
            throw new IllegalArgumentException("Emails and types lengths differ");
        }
        if (strArr7 != null && strArr8 != null && strArr7.length != strArr8.length) {
            throw new IllegalArgumentException("Addresses and types lengths differ");
        }
        this.f1974a = strArr;
        this.f1975b = strArr2;
        this.f1976c = str;
        this.f1977d = strArr3;
        this.f1978e = strArr4;
        this.f1979f = strArr5;
        this.f1980g = strArr6;
        this.f1981h = str2;
        this.f1982i = str3;
        this.f1983j = strArr7;
        this.f1984k = strArr8;
        this.f1985l = str4;
        this.f1986m = str5;
        this.f1987n = str6;
        this.f1988o = strArr9;
        this.f1989p = strArr10;
    }

    /* JADX INFO: renamed from: b */
    private String[] m1624b() {
        return this.f1974a;
    }

    /* JADX INFO: renamed from: c */
    private String[] m1625c() {
        return this.f1975b;
    }

    /* JADX INFO: renamed from: d */
    private String m1626d() {
        return this.f1976c;
    }

    /* JADX INFO: renamed from: e */
    private String[] m1627e() {
        return this.f1977d;
    }

    /* JADX INFO: renamed from: f */
    private String[] m1628f() {
        return this.f1978e;
    }

    /* JADX INFO: renamed from: g */
    private String[] m1629g() {
        return this.f1979f;
    }

    /* JADX INFO: renamed from: h */
    private String[] m1630h() {
        return this.f1980g;
    }

    /* JADX INFO: renamed from: i */
    private String m1631i() {
        return this.f1981h;
    }

    /* JADX INFO: renamed from: j */
    private String m1632j() {
        return this.f1982i;
    }

    /* JADX INFO: renamed from: k */
    private String[] m1633k() {
        return this.f1983j;
    }

    /* JADX INFO: renamed from: l */
    private String[] m1634l() {
        return this.f1984k;
    }

    /* JADX INFO: renamed from: m */
    private String m1635m() {
        return this.f1987n;
    }

    /* JADX INFO: renamed from: n */
    private String m1636n() {
        return this.f1985l;
    }

    /* JADX INFO: renamed from: o */
    private String[] m1637o() {
        return this.f1988o;
    }

    /* JADX INFO: renamed from: p */
    private String m1638p() {
        return this.f1986m;
    }

    /* JADX INFO: renamed from: q */
    private String[] m1639q() {
        return this.f1989p;
    }

    @Override // com.google.p028a.p033b.p034a.AbstractC1179q
    /* JADX INFO: renamed from: a */
    public final String mo1571a() {
        StringBuilder sb = new StringBuilder(100);
        m1700a(this.f1974a, sb);
        m1700a(this.f1975b, sb);
        m1699a(this.f1976c, sb);
        m1699a(this.f1987n, sb);
        m1699a(this.f1985l, sb);
        m1700a(this.f1983j, sb);
        m1700a(this.f1977d, sb);
        m1700a(this.f1979f, sb);
        m1699a(this.f1981h, sb);
        m1700a(this.f1988o, sb);
        m1699a(this.f1986m, sb);
        m1700a(this.f1989p, sb);
        m1699a(this.f1982i, sb);
        return sb.toString();
    }
}
