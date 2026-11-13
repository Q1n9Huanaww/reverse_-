package com.google.p028a.p033b.p034a;

import com.google.p028a.C1363r;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: com.google.a.b.a.ad */
/* JADX INFO: compiled from: URIResultParser.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1156ad extends AbstractC1183u {

    /* JADX INFO: renamed from: a */
    private static final Pattern f1943a = Pattern.compile("[a-zA-Z][a-zA-Z0-9+-.]+:");

    /* JADX INFO: renamed from: b */
    private static final Pattern f1944b = Pattern.compile("([a-zA-Z0-9\\-]+\\.){1,6}[a-zA-Z]{2,}(:\\d{1,5})?(/|\\?|$)");

    /* JADX INFO: renamed from: c */
    private static C1155ac m1578c(C1363r c1363r) {
        String strB = m1715b(c1363r);
        if (strB.startsWith("URL:") || strB.startsWith("URI:")) {
            return new C1155ac(strB.substring(4).trim(), null);
        }
        String strTrim = strB.trim();
        if (m1577a(strTrim)) {
            return new C1155ac(strTrim, null);
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    static boolean m1577a(String str) {
        if (str.contains(" ")) {
            return false;
        }
        Matcher matcher = f1943a.matcher(str);
        if (matcher.find() && matcher.start() == 0) {
            return true;
        }
        Matcher matcher2 = f1944b.matcher(str);
        return matcher2.find() && matcher2.start() == 0;
    }

    @Override // com.google.p028a.p033b.p034a.AbstractC1183u
    /* JADX INFO: renamed from: a */
    public final /* synthetic */ AbstractC1179q mo1568a(C1363r c1363r) {
        String strB = m1715b(c1363r);
        if (strB.startsWith("URL:") || strB.startsWith("URI:")) {
            return new C1155ac(strB.substring(4).trim(), null);
        }
        String strTrim = strB.trim();
        if (m1577a(strTrim)) {
            return new C1155ac(strTrim, null);
        }
        return null;
    }
}
