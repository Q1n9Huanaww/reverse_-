package com.google.p028a.p033b.p034a;

import com.google.p028a.C1363r;
import com.google.p028a.EnumC1138a;
import com.google.p028a.p047g.C1276aa;

/* JADX INFO: renamed from: com.google.a.b.a.t */
/* JADX INFO: compiled from: ProductResultParser.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1182t extends AbstractC1183u {
    /* JADX INFO: renamed from: c */
    private static C1181s m1705c(C1363r c1363r) {
        EnumC1138a enumC1138a = c1363r.f2843e;
        if (enumC1138a != EnumC1138a.UPC_A && enumC1138a != EnumC1138a.UPC_E && enumC1138a != EnumC1138a.EAN_8 && enumC1138a != EnumC1138a.EAN_13) {
            return null;
        }
        String strB = m1715b(c1363r);
        if (m1711a(strB, strB.length())) {
            return new C1181s(strB, (enumC1138a == EnumC1138a.UPC_E && strB.length() == 8) ? C1276aa.m2154b(strB) : strB);
        }
        return null;
    }

    @Override // com.google.p028a.p033b.p034a.AbstractC1183u
    /* JADX INFO: renamed from: a */
    public final /* synthetic */ AbstractC1179q mo1568a(C1363r c1363r) {
        EnumC1138a enumC1138a = c1363r.f2843e;
        if (enumC1138a != EnumC1138a.UPC_A && enumC1138a != EnumC1138a.UPC_E && enumC1138a != EnumC1138a.EAN_8 && enumC1138a != EnumC1138a.EAN_13) {
            return null;
        }
        String strB = m1715b(c1363r);
        if (m1711a(strB, strB.length())) {
            return new C1181s(strB, (enumC1138a == EnumC1138a.UPC_E && strB.length() == 8) ? C1276aa.m2154b(strB) : strB);
        }
        return null;
    }
}
