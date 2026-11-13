package com.google.p028a.p033b.p034a;

import com.google.p028a.C1363r;

/* JADX INFO: renamed from: com.google.a.b.a.ae */
/* JADX INFO: compiled from: URLTOResultParser.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1157ae extends AbstractC1183u {
    /* JADX INFO: renamed from: c */
    private static C1155ac m1579c(C1363r c1363r) {
        int iIndexOf;
        String strB = m1715b(c1363r);
        if ((strB.startsWith("urlto:") || strB.startsWith("URLTO:")) && (iIndexOf = strB.indexOf(58, 6)) >= 0) {
            return new C1155ac(strB.substring(iIndexOf + 1), iIndexOf > 6 ? strB.substring(6, iIndexOf) : null);
        }
        return null;
    }

    @Override // com.google.p028a.p033b.p034a.AbstractC1183u
    /* JADX INFO: renamed from: a */
    public final /* synthetic */ AbstractC1179q mo1568a(C1363r c1363r) {
        int iIndexOf;
        String strB = m1715b(c1363r);
        if ((strB.startsWith("urlto:") || strB.startsWith("URLTO:")) && (iIndexOf = strB.indexOf(58, 6)) >= 0) {
            return new C1155ac(strB.substring(iIndexOf + 1), iIndexOf > 6 ? strB.substring(6, iIndexOf) : null);
        }
        return null;
    }
}
