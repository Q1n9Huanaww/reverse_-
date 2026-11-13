package com.google.p028a.p033b.p034a;

import com.google.p028a.C1363r;

/* JADX INFO: renamed from: com.google.a.b.a.ak */
/* JADX INFO: compiled from: WifiResultParser.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1163ak extends AbstractC1183u {
    /* JADX INFO: renamed from: c */
    private static C1162aj m1619c(C1363r c1363r) {
        String strSubstring;
        String strB;
        String strB2 = m1715b(c1363r);
        if (!strB2.startsWith("WIFI:") || (strB = m1716b("S:", (strSubstring = strB2.substring(5)), ';', false)) == null || strB.isEmpty()) {
            return null;
        }
        String strB3 = m1716b("P:", strSubstring, ';', false);
        String strB4 = m1716b("T:", strSubstring, ';', false);
        if (strB4 == null) {
            strB4 = "nopass";
        }
        return new C1162aj(strB4, strB, strB3, Boolean.parseBoolean(m1716b("H:", strSubstring, ';', false)), m1716b("I:", strSubstring, ';', false), m1716b("A:", strSubstring, ';', false), m1716b("E:", strSubstring, ';', false), m1716b("H:", strSubstring, ';', false));
    }

    @Override // com.google.p028a.p033b.p034a.AbstractC1183u
    /* JADX INFO: renamed from: a */
    public final /* synthetic */ AbstractC1179q mo1568a(C1363r c1363r) {
        String strSubstring;
        String strB;
        String strB2 = m1715b(c1363r);
        if (!strB2.startsWith("WIFI:") || (strB = m1716b("S:", (strSubstring = strB2.substring(5)), ';', false)) == null || strB.isEmpty()) {
            return null;
        }
        String strB3 = m1716b("P:", strSubstring, ';', false);
        String strB4 = m1716b("T:", strSubstring, ';', false);
        if (strB4 == null) {
            strB4 = "nopass";
        }
        return new C1162aj(strB4, strB, strB3, Boolean.parseBoolean(m1716b("H:", strSubstring, ';', false)), m1716b("I:", strSubstring, ';', false), m1716b("A:", strSubstring, ';', false), m1716b("E:", strSubstring, ';', false), m1716b("H:", strSubstring, ';', false));
    }
}
