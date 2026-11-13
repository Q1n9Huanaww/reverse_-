package com.google.p028a.p033b.p034a;

import com.google.p028a.C1363r;
import com.google.p028a.EnumC1138a;

/* JADX INFO: renamed from: com.google.a.b.a.p */
/* JADX INFO: compiled from: ISBNResultParser.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1178p extends AbstractC1183u {
    /* JADX INFO: renamed from: c */
    private static C1177o m1698c(C1363r c1363r) {
        if (c1363r.f2843e != EnumC1138a.EAN_13) {
            return null;
        }
        String strB = m1715b(c1363r);
        if (strB.length() != 13) {
            return null;
        }
        if (strB.startsWith("978") || strB.startsWith("979")) {
            return new C1177o(strB);
        }
        return null;
    }

    @Override // com.google.p028a.p033b.p034a.AbstractC1183u
    /* JADX INFO: renamed from: a */
    public final /* synthetic */ AbstractC1179q mo1568a(C1363r c1363r) {
        if (c1363r.f2843e != EnumC1138a.EAN_13) {
            return null;
        }
        String strB = m1715b(c1363r);
        if (strB.length() != 13) {
            return null;
        }
        if (strB.startsWith("978") || strB.startsWith("979")) {
            return new C1177o(strB);
        }
        return null;
    }
}
