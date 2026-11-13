package com.google.p028a.p033b.p034a;

import com.google.p028a.C1363r;
import java.util.List;

/* JADX INFO: renamed from: com.google.a.b.a.ag */
/* JADX INFO: compiled from: VEventResultParser.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1159ag extends AbstractC1183u {
    @Override // com.google.p028a.p033b.p034a.AbstractC1183u
    /* JADX INFO: renamed from: a */
    public final /* synthetic */ AbstractC1179q mo1568a(C1363r c1363r) {
        return m1594c(c1363r);
    }

    /* JADX INFO: renamed from: c */
    private static C1169g m1594c(C1363r c1363r) {
        String[] strArr;
        double d;
        String strB = m1715b(c1363r);
        if (strB.indexOf("BEGIN:VEVENT") < 0) {
            return null;
        }
        String strM1591a = m1591a("SUMMARY", strB);
        String strM1591a2 = m1591a("DTSTART", strB);
        if (strM1591a2 == null) {
            return null;
        }
        String strM1591a3 = m1591a("DTEND", strB);
        String strM1591a4 = m1591a("DURATION", strB);
        String strM1591a5 = m1591a("LOCATION", strB);
        String strM1592a = m1592a(m1591a("ORGANIZER", strB));
        List<List<String>> listM1582a = C1158af.m1582a((CharSequence) "ATTENDEE", strB, true, false);
        if (listM1582a == null || listM1582a.isEmpty()) {
            strArr = null;
        } else {
            int size = listM1582a.size();
            strArr = new String[size];
            for (int i = 0; i < size; i++) {
                strArr[i] = listM1582a.get(i).get(0);
            }
        }
        if (strArr != null) {
            for (int i2 = 0; i2 < strArr.length; i2++) {
                strArr[i2] = m1592a(strArr[i2]);
            }
        }
        String strM1591a6 = m1591a("DESCRIPTION", strB);
        String strM1591a7 = m1591a("GEO", strB);
        double d2 = Double.NaN;
        if (strM1591a7 == null) {
            d = Double.NaN;
        } else {
            int iIndexOf = strM1591a7.indexOf(59);
            if (iIndexOf < 0) {
                return null;
            }
            try {
                d2 = Double.parseDouble(strM1591a7.substring(0, iIndexOf));
                d = Double.parseDouble(strM1591a7.substring(iIndexOf + 1));
            } catch (NumberFormatException unused) {
                return null;
            }
        }
        try {
            return new C1169g(strM1591a, strM1591a2, strM1591a3, strM1591a4, strM1591a5, strM1592a, strArr, strM1591a6, d2, d);
        } catch (IllegalArgumentException unused2) {
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    private static String m1591a(CharSequence charSequence, String str) {
        List<String> listM1588b = C1158af.m1588b(charSequence, str, true, false);
        if (listM1588b == null || listM1588b.isEmpty()) {
            return null;
        }
        return listM1588b.get(0);
    }

    /* JADX INFO: renamed from: b */
    private static String[] m1593b(CharSequence charSequence, String str) {
        List<List<String>> listM1582a = C1158af.m1582a(charSequence, str, true, false);
        if (listM1582a == null || listM1582a.isEmpty()) {
            return null;
        }
        int size = listM1582a.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = listM1582a.get(i).get(0);
        }
        return strArr;
    }

    /* JADX INFO: renamed from: a */
    private static String m1592a(String str) {
        if (str != null) {
            return (str.startsWith("mailto:") || str.startsWith("MAILTO:")) ? str.substring(7) : str;
        }
        return str;
    }
}
