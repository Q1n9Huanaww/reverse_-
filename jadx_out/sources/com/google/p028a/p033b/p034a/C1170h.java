package com.google.p028a.p033b.p034a;

/* JADX INFO: renamed from: com.google.a.b.a.h */
/* JADX INFO: compiled from: EmailAddressParsedResult.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1170h extends AbstractC1179q {

    /* JADX INFO: renamed from: a */
    private final String[] f2004a;

    /* JADX INFO: renamed from: b */
    private final String[] f2005b;

    /* JADX INFO: renamed from: c */
    private final String[] f2006c;

    /* JADX INFO: renamed from: d */
    private final String f2007d;

    /* JADX INFO: renamed from: e */
    private final String f2008e;

    @Deprecated
    /* JADX INFO: renamed from: h */
    private static String m1667h() {
        return "mailto:";
    }

    C1170h(String str) {
        this(new String[]{str}, null, null, null, null);
    }

    C1170h(String[] strArr, String[] strArr2, String[] strArr3, String str, String str2) {
        super(C1180r.f2036b);
        this.f2004a = strArr;
        this.f2005b = strArr2;
        this.f2006c = strArr3;
        this.f2007d = str;
        this.f2008e = str2;
    }

    @Deprecated
    /* JADX INFO: renamed from: b */
    private String m1661b() {
        if (this.f2004a == null || this.f2004a.length == 0) {
            return null;
        }
        return this.f2004a[0];
    }

    /* JADX INFO: renamed from: c */
    private String[] m1662c() {
        return this.f2004a;
    }

    /* JADX INFO: renamed from: d */
    private String[] m1663d() {
        return this.f2005b;
    }

    /* JADX INFO: renamed from: e */
    private String[] m1664e() {
        return this.f2006c;
    }

    /* JADX INFO: renamed from: f */
    private String m1665f() {
        return this.f2007d;
    }

    /* JADX INFO: renamed from: g */
    private String m1666g() {
        return this.f2008e;
    }

    @Override // com.google.p028a.p033b.p034a.AbstractC1179q
    /* JADX INFO: renamed from: a */
    public final String mo1571a() {
        StringBuilder sb = new StringBuilder(30);
        m1700a(this.f2004a, sb);
        m1700a(this.f2005b, sb);
        m1700a(this.f2006c, sb);
        m1699a(this.f2007d, sb);
        m1699a(this.f2008e, sb);
        return sb.toString();
    }
}
