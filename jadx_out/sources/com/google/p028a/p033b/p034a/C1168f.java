package com.google.p028a.p033b.p034a;

import com.google.p028a.C1363r;

/* JADX INFO: renamed from: com.google.a.b.a.f */
/* JADX INFO: compiled from: BookmarkDoCoMoResultParser.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1168f extends AbstractC1152a {
    /* JADX INFO: renamed from: c */
    private static C1155ac m1643c(C1363r c1363r) {
        String str = c1363r.f2839a;
        if (!str.startsWith("MEBKM:")) {
            return null;
        }
        String strA = m1565a("TITLE:", str, true);
        String[] strArrA = m1566a("URL:", str);
        if (strArrA == null) {
            return null;
        }
        String str2 = strArrA[0];
        if (C1156ad.m1577a(str2)) {
            return new C1155ac(str2, strA);
        }
        return null;
    }

    @Override // com.google.p028a.p033b.p034a.AbstractC1183u
    /* JADX INFO: renamed from: a */
    public final /* synthetic */ AbstractC1179q mo1568a(C1363r c1363r) {
        String str = c1363r.f2839a;
        if (!str.startsWith("MEBKM:")) {
            return null;
        }
        String strA = m1565a("TITLE:", str, true);
        String[] strArrA = m1566a("URL:", str);
        if (strArrA == null) {
            return null;
        }
        String str2 = strArrA[0];
        if (C1156ad.m1577a(str2)) {
            return new C1155ac(str2, strA);
        }
        return null;
    }
}
