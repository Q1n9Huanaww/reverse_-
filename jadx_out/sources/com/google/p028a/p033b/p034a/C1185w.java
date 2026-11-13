package com.google.p028a.p033b.p034a;

/* JADX INFO: renamed from: com.google.a.b.a.w */
/* JADX INFO: compiled from: SMSParsedResult.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1185w extends AbstractC1179q {

    /* JADX INFO: renamed from: a */
    private final String[] f2055a;

    /* JADX INFO: renamed from: b */
    private final String[] f2056b;

    /* JADX INFO: renamed from: c */
    private final String f2057c;

    /* JADX INFO: renamed from: d */
    private final String f2058d;

    public C1185w(String str, String str2) {
        super(C1180r.f2042h);
        this.f2055a = new String[]{str};
        this.f2056b = new String[]{null};
        this.f2057c = null;
        this.f2058d = str2;
    }

    public C1185w(String[] strArr, String[] strArr2, String str, String str2) {
        super(C1180r.f2042h);
        this.f2055a = strArr;
        this.f2056b = strArr2;
        this.f2057c = str;
        this.f2058d = str2;
    }

    /* JADX INFO: renamed from: b */
    private String m1723b() {
        StringBuilder sb = new StringBuilder();
        sb.append("sms:");
        boolean z = true;
        for (int i = 0; i < this.f2055a.length; i++) {
            if (z) {
                z = false;
            } else {
                sb.append(',');
            }
            sb.append(this.f2055a[i]);
            if (this.f2056b != null && this.f2056b[i] != null) {
                sb.append(";via=");
                sb.append(this.f2056b[i]);
            }
        }
        boolean z2 = this.f2058d != null;
        boolean z3 = this.f2057c != null;
        if (z2 || z3) {
            sb.append('?');
            if (z2) {
                sb.append("body=");
                sb.append(this.f2058d);
            }
            if (z3) {
                if (z2) {
                    sb.append('&');
                }
                sb.append("subject=");
                sb.append(this.f2057c);
            }
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: c */
    private String[] m1724c() {
        return this.f2055a;
    }

    /* JADX INFO: renamed from: d */
    private String[] m1725d() {
        return this.f2056b;
    }

    /* JADX INFO: renamed from: e */
    private String m1726e() {
        return this.f2057c;
    }

    /* JADX INFO: renamed from: f */
    private String m1727f() {
        return this.f2058d;
    }

    @Override // com.google.p028a.p033b.p034a.AbstractC1179q
    /* JADX INFO: renamed from: a */
    public final String mo1571a() {
        StringBuilder sb = new StringBuilder(100);
        m1700a(this.f2055a, sb);
        m1699a(this.f2057c, sb);
        m1699a(this.f2058d, sb);
        return sb.toString();
    }
}
