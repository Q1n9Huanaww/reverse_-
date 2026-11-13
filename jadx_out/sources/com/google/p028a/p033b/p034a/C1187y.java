package com.google.p028a.p033b.p034a;

import com.google.p028a.C1363r;

/* JADX INFO: renamed from: com.google.a.b.a.y */
/* JADX INFO: compiled from: SMTPResultParser.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1187y extends AbstractC1183u {
    /* JADX INFO: renamed from: c */
    private static C1170h m1729c(C1363r c1363r) {
        String strSubstring;
        String str;
        String strB = m1715b(c1363r);
        if (!strB.startsWith("smtp:") && !strB.startsWith("SMTP:")) {
            return null;
        }
        String strSubstring2 = strB.substring(5);
        int iIndexOf = strSubstring2.indexOf(58);
        if (iIndexOf >= 0) {
            String strSubstring3 = strSubstring2.substring(iIndexOf + 1);
            strSubstring2 = strSubstring2.substring(0, iIndexOf);
            int iIndexOf2 = strSubstring3.indexOf(58);
            if (iIndexOf2 >= 0) {
                String strSubstring4 = strSubstring3.substring(iIndexOf2 + 1);
                strSubstring = strSubstring3.substring(0, iIndexOf2);
                str = strSubstring4;
            } else {
                str = null;
                strSubstring = strSubstring3;
            }
        } else {
            strSubstring = null;
            str = null;
        }
        return new C1170h(new String[]{strSubstring2}, null, null, strSubstring, str);
    }

    @Override // com.google.p028a.p033b.p034a.AbstractC1183u
    /* JADX INFO: renamed from: a */
    public final /* synthetic */ AbstractC1179q mo1568a(C1363r c1363r) {
        String strSubstring;
        String str;
        String strB = m1715b(c1363r);
        if (!strB.startsWith("smtp:") && !strB.startsWith("SMTP:")) {
            return null;
        }
        String strSubstring2 = strB.substring(5);
        int iIndexOf = strSubstring2.indexOf(58);
        if (iIndexOf >= 0) {
            String strSubstring3 = strSubstring2.substring(iIndexOf + 1);
            strSubstring2 = strSubstring2.substring(0, iIndexOf);
            int iIndexOf2 = strSubstring3.indexOf(58);
            if (iIndexOf2 >= 0) {
                String strSubstring4 = strSubstring3.substring(iIndexOf2 + 1);
                strSubstring = strSubstring3.substring(0, iIndexOf2);
                str = strSubstring4;
            } else {
                str = null;
                strSubstring = strSubstring3;
            }
        } else {
            strSubstring = null;
            str = null;
        }
        return new C1170h(new String[]{strSubstring2}, null, null, strSubstring, str);
    }
}
