package com.google.p028a.p033b.p034a;

import com.google.p028a.C1363r;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: com.google.a.b.a.j */
/* JADX INFO: compiled from: EmailDoCoMoResultParser.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1172j extends AbstractC1152a {

    /* JADX INFO: renamed from: a */
    private static final Pattern f2010a = Pattern.compile("[a-zA-Z0-9@.!#$%&'*+\\-/=?^_`{|}~]+");

    /* JADX INFO: renamed from: c */
    private static C1170h m1670c(C1363r c1363r) {
        String[] strArrA;
        String strB = m1715b(c1363r);
        if (!strB.startsWith("MATMSG:") || (strArrA = m1566a("TO:", strB)) == null) {
            return null;
        }
        for (String str : strArrA) {
            if (!m1669a(str)) {
                return null;
            }
        }
        return new C1170h(strArrA, null, null, m1565a("SUB:", strB, false), m1565a("BODY:", strB, false));
    }

    /* JADX INFO: renamed from: a */
    static boolean m1669a(String str) {
        return str != null && f2010a.matcher(str).matches() && str.indexOf(64) >= 0;
    }

    @Override // com.google.p028a.p033b.p034a.AbstractC1183u
    /* JADX INFO: renamed from: a */
    public final /* synthetic */ AbstractC1179q mo1568a(C1363r c1363r) {
        String[] strArrA;
        String strB = m1715b(c1363r);
        if (!strB.startsWith("MATMSG:") || (strArrA = m1566a("TO:", strB)) == null) {
            return null;
        }
        for (String str : strArrA) {
            if (!m1669a(str)) {
                return null;
            }
        }
        return new C1170h(strArrA, null, null, m1565a("SUB:", strB, false), m1565a("BODY:", strB, false));
    }
}
