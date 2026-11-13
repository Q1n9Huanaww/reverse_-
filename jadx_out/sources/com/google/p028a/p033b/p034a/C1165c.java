package com.google.p028a.p033b.p034a;

import com.google.p028a.C1363r;

/* JADX INFO: renamed from: com.google.a.b.a.c */
/* JADX INFO: compiled from: AddressBookDoCoMoResultParser.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1165c extends AbstractC1152a {
    /* JADX INFO: renamed from: c */
    private static C1166d m1623c(C1363r c1363r) {
        String[] strArrA;
        String strB = m1715b(c1363r);
        if (!strB.startsWith("MECARD:") || (strArrA = m1566a("N:", strB)) == null) {
            return null;
        }
        String str = strArrA[0];
        int iIndexOf = str.indexOf(44);
        if (iIndexOf >= 0) {
            str = str.substring(iIndexOf + 1) + ' ' + str.substring(0, iIndexOf);
        }
        String strA = m1565a("SOUND:", strB, true);
        String[] strArrA2 = m1566a("TEL:", strB);
        String[] strArrA3 = m1566a("EMAIL:", strB);
        String strA2 = m1565a("NOTE:", strB, false);
        String[] strArrA4 = m1566a("ADR:", strB);
        String strA3 = m1565a("BDAY:", strB, true);
        return new C1166d(m1717b(str), null, strA, strArrA2, null, strArrA3, null, null, strA2, strArrA4, null, m1565a("ORG:", strB, true), !m1711a(strA3, 8) ? null : strA3, null, m1566a("URL:", strB), null);
    }

    /* JADX INFO: renamed from: a */
    private static String m1622a(String str) {
        int iIndexOf = str.indexOf(44);
        if (iIndexOf < 0) {
            return str;
        }
        return str.substring(iIndexOf + 1) + ' ' + str.substring(0, iIndexOf);
    }

    @Override // com.google.p028a.p033b.p034a.AbstractC1183u
    /* JADX INFO: renamed from: a */
    public final /* synthetic */ AbstractC1179q mo1568a(C1363r c1363r) {
        String[] strArrA;
        String strB = m1715b(c1363r);
        if (!strB.startsWith("MECARD:") || (strArrA = m1566a("N:", strB)) == null) {
            return null;
        }
        String str = strArrA[0];
        int iIndexOf = str.indexOf(44);
        if (iIndexOf >= 0) {
            str = str.substring(iIndexOf + 1) + ' ' + str.substring(0, iIndexOf);
        }
        String strA = m1565a("SOUND:", strB, true);
        String[] strArrA2 = m1566a("TEL:", strB);
        String[] strArrA3 = m1566a("EMAIL:", strB);
        String strA2 = m1565a("NOTE:", strB, false);
        String[] strArrA4 = m1566a("ADR:", strB);
        String strA3 = m1565a("BDAY:", strB, true);
        return new C1166d(m1717b(str), null, strA, strArrA2, null, strArrA3, null, null, strA2, strArrA4, null, m1565a("ORG:", strB, true), !m1711a(strA3, 8) ? null : strA3, null, m1566a("URL:", strB), null);
    }
}
