package com.google.p028a.p033b.p034a;

import com.google.p028a.C1363r;
import java.util.ArrayList;

/* JADX INFO: renamed from: com.google.a.b.a.e */
/* JADX INFO: compiled from: BizcardResultParser.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1167e extends AbstractC1152a {
    /* JADX INFO: renamed from: c */
    private static C1166d m1642c(C1363r c1363r) {
        String strB = m1715b(c1363r);
        if (!strB.startsWith("BIZCARD:")) {
            return null;
        }
        String strA = m1565a("N:", strB, true);
        String strA2 = m1565a("X:", strB, true);
        if (strA == null) {
            strA = strA2;
        } else if (strA2 != null) {
            strA = strA + ' ' + strA2;
        }
        String strA3 = m1565a("T:", strB, true);
        String strA4 = m1565a("C:", strB, true);
        String[] strArrA = m1566a("A:", strB);
        String strA5 = m1565a("B:", strB, true);
        String strA6 = m1565a("M:", strB, true);
        String strA7 = m1565a("F:", strB, true);
        String strA8 = m1565a("E:", strB, true);
        String[] strArrB = m1717b(strA);
        ArrayList arrayList = new ArrayList(3);
        if (strA5 != null) {
            arrayList.add(strA5);
        }
        if (strA6 != null) {
            arrayList.add(strA6);
        }
        if (strA7 != null) {
            arrayList.add(strA7);
        }
        int size = arrayList.size();
        return new C1166d(strArrB, null, null, size != 0 ? (String[]) arrayList.toArray(new String[size]) : null, null, m1717b(strA8), null, null, null, strArrA, null, strA4, null, strA3, null, null);
    }

    /* JADX INFO: renamed from: a */
    private static String[] m1640a(String str, String str2, String str3) {
        ArrayList arrayList = new ArrayList(3);
        if (str != null) {
            arrayList.add(str);
        }
        if (str2 != null) {
            arrayList.add(str2);
        }
        if (str3 != null) {
            arrayList.add(str3);
        }
        int size = arrayList.size();
        if (size == 0) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[size]);
    }

    /* JADX INFO: renamed from: b */
    private static String m1641b(String str, String str2) {
        if (str == null) {
            return str2;
        }
        if (str2 == null) {
            return str;
        }
        return str + ' ' + str2;
    }

    @Override // com.google.p028a.p033b.p034a.AbstractC1183u
    /* JADX INFO: renamed from: a */
    public final /* synthetic */ AbstractC1179q mo1568a(C1363r c1363r) {
        String strB = m1715b(c1363r);
        if (!strB.startsWith("BIZCARD:")) {
            return null;
        }
        String strA = m1565a("N:", strB, true);
        String strA2 = m1565a("X:", strB, true);
        if (strA == null) {
            strA = strA2;
        } else if (strA2 != null) {
            strA = strA + ' ' + strA2;
        }
        String strA3 = m1565a("T:", strB, true);
        String strA4 = m1565a("C:", strB, true);
        String[] strArrA = m1566a("A:", strB);
        String strA5 = m1565a("B:", strB, true);
        String strA6 = m1565a("M:", strB, true);
        String strA7 = m1565a("F:", strB, true);
        String strA8 = m1565a("E:", strB, true);
        String[] strArrB = m1717b(strA);
        ArrayList arrayList = new ArrayList(3);
        if (strA5 != null) {
            arrayList.add(strA5);
        }
        if (strA6 != null) {
            arrayList.add(strA6);
        }
        if (strA7 != null) {
            arrayList.add(strA7);
        }
        int size = arrayList.size();
        return new C1166d(strArrB, null, null, size != 0 ? (String[]) arrayList.toArray(new String[size]) : null, null, m1717b(strA8), null, null, null, strArrA, null, strA4, null, strA3, null, null);
    }
}
