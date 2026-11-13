package com.google.p028a.p033b.p034a;

import com.google.p028a.C1363r;

/* JADX INFO: renamed from: com.google.a.b.a.x */
/* JADX INFO: compiled from: SMSTOMMSTOResultParser.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1186x extends AbstractC1183u {
    /* JADX INFO: renamed from: c */
    private static C1185w m1728c(C1363r c1363r) {
        String strB = m1715b(c1363r);
        String strSubstring = null;
        if (!strB.startsWith("smsto:") && !strB.startsWith("SMSTO:") && !strB.startsWith("mmsto:") && !strB.startsWith("MMSTO:")) {
            return null;
        }
        String strSubstring2 = strB.substring(6);
        int iIndexOf = strSubstring2.indexOf(58);
        if (iIndexOf >= 0) {
            strSubstring = strSubstring2.substring(iIndexOf + 1);
            strSubstring2 = strSubstring2.substring(0, iIndexOf);
        }
        return new C1185w(strSubstring2, strSubstring);
    }

    @Override // com.google.p028a.p033b.p034a.AbstractC1183u
    /* JADX INFO: renamed from: a */
    public final /* synthetic */ AbstractC1179q mo1568a(C1363r c1363r) {
        String strB = m1715b(c1363r);
        String strSubstring = null;
        if (!strB.startsWith("smsto:") && !strB.startsWith("SMSTO:") && !strB.startsWith("mmsto:") && !strB.startsWith("MMSTO:")) {
            return null;
        }
        String strSubstring2 = strB.substring(6);
        int iIndexOf = strSubstring2.indexOf(58);
        if (iIndexOf >= 0) {
            strSubstring = strSubstring2.substring(iIndexOf + 1);
            strSubstring2 = strSubstring2.substring(0, iIndexOf);
        }
        return new C1185w(strSubstring2, strSubstring);
    }
}
