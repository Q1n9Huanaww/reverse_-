package com.google.p028a.p033b.p034a;

import com.google.p028a.C1363r;

/* JADX INFO: renamed from: com.google.a.b.a.aa */
/* JADX INFO: compiled from: TelResultParser.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1153aa extends AbstractC1183u {
    /* JADX INFO: renamed from: c */
    private static C1188z m1567c(C1363r c1363r) {
        String str;
        String strB = m1715b(c1363r);
        if (!strB.startsWith("tel:") && !strB.startsWith("TEL:")) {
            return null;
        }
        if (strB.startsWith("TEL:")) {
            str = "tel:" + strB.substring(4);
        } else {
            str = strB;
        }
        int iIndexOf = strB.indexOf(63, 4);
        return new C1188z(iIndexOf < 0 ? strB.substring(4) : strB.substring(4, iIndexOf), str);
    }

    @Override // com.google.p028a.p033b.p034a.AbstractC1183u
    /* JADX INFO: renamed from: a */
    public final /* synthetic */ AbstractC1179q mo1568a(C1363r c1363r) {
        String str;
        String strB = m1715b(c1363r);
        if (!strB.startsWith("tel:") && !strB.startsWith("TEL:")) {
            return null;
        }
        if (strB.startsWith("TEL:")) {
            str = "tel:" + strB.substring(4);
        } else {
            str = strB;
        }
        int iIndexOf = strB.indexOf(63, 4);
        return new C1188z(iIndexOf < 0 ? strB.substring(4) : strB.substring(4, iIndexOf), str);
    }
}
