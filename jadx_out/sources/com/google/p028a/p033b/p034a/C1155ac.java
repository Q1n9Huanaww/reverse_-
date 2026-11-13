package com.google.p028a.p033b.p034a;

import java.util.regex.Pattern;

/* JADX INFO: renamed from: com.google.a.b.a.ac */
/* JADX INFO: compiled from: URIParsedResult.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1155ac extends AbstractC1179q {

    /* JADX INFO: renamed from: a */
    private static final Pattern f1940a = Pattern.compile(":/*([^/@]+)@[^/]+");

    /* JADX INFO: renamed from: b */
    private final String f1941b;

    /* JADX INFO: renamed from: c */
    private final String f1942c;

    /* JADX WARN: Code duplicated, block: B:9:0x0026  */
    public C1155ac(String str, String str2) {
        super(C1180r.f2038d);
        String strTrim = str.trim();
        int iIndexOf = strTrim.indexOf(58);
        if (iIndexOf >= 0) {
            int i = iIndexOf + 1;
            int iIndexOf2 = strTrim.indexOf(47, i);
            strTrim = AbstractC1183u.m1712a(strTrim, i, (iIndexOf2 < 0 ? strTrim.length() : iIndexOf2) - i) ? "http://".concat(String.valueOf(strTrim)) : strTrim;
        }
        this.f1941b = strTrim;
        this.f1942c = str2;
    }

    /* JADX INFO: renamed from: b */
    private String m1574b() {
        return this.f1941b;
    }

    /* JADX INFO: renamed from: c */
    private String m1575c() {
        return this.f1942c;
    }

    /* JADX INFO: renamed from: d */
    private boolean m1576d() {
        return f1940a.matcher(this.f1941b).find();
    }

    @Override // com.google.p028a.p033b.p034a.AbstractC1179q
    /* JADX INFO: renamed from: a */
    public final String mo1571a() {
        StringBuilder sb = new StringBuilder(30);
        m1699a(this.f1942c, sb);
        m1699a(this.f1941b, sb);
        return sb.toString();
    }

    /* JADX INFO: renamed from: a */
    private static String m1572a(String str) {
        String strTrim = str.trim();
        int iIndexOf = strTrim.indexOf(58);
        if (iIndexOf >= 0) {
            int i = iIndexOf + 1;
            int iIndexOf2 = strTrim.indexOf(47, i);
            if (iIndexOf2 < 0) {
                iIndexOf2 = strTrim.length();
            }
            if (!AbstractC1183u.m1712a(strTrim, i, iIndexOf2 - i)) {
                return strTrim;
            }
        }
        return "http://".concat(String.valueOf(strTrim));
    }

    /* JADX INFO: renamed from: a */
    private static boolean m1573a(String str, int i) {
        int i2 = i + 1;
        int iIndexOf = str.indexOf(47, i2);
        if (iIndexOf < 0) {
            iIndexOf = str.length();
        }
        return AbstractC1183u.m1712a(str, i2, iIndexOf - i2);
    }
}
