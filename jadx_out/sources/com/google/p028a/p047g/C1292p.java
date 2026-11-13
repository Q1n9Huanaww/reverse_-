package com.google.p028a.p047g;

import com.google.p028a.AbstractC1362q;
import com.google.p028a.C1358m;
import com.google.p028a.C1363r;
import com.google.p028a.EnumC1138a;
import com.google.p028a.EnumC1233e;
import com.google.p028a.p035c.C1190a;
import com.google.p028a.p047g.p048a.C1274e;
import com.google.p028a.p047g.p048a.p049a.C1270d;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.g.p */
/* JADX INFO: compiled from: MultiFormatOneDReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1292p extends AbstractC1294r {

    /* JADX INFO: renamed from: a */
    private final AbstractC1294r[] f2518a;

    public C1292p(Map<EnumC1233e, ?> map) {
        Collection collection = map == null ? null : (Collection) map.get(EnumC1233e.POSSIBLE_FORMATS);
        boolean z = (map == null || map.get(EnumC1233e.ASSUME_CODE_39_CHECK_DIGIT) == null) ? false : true;
        ArrayList arrayList = new ArrayList();
        if (collection != null) {
            if (collection.contains(EnumC1138a.EAN_13) || collection.contains(EnumC1138a.UPC_A) || collection.contains(EnumC1138a.EAN_8) || collection.contains(EnumC1138a.UPC_E)) {
                arrayList.add(new C1293q(map));
            }
            if (collection.contains(EnumC1138a.CODE_39)) {
                arrayList.add(new C1281e(z));
            }
            if (collection.contains(EnumC1138a.CODE_93)) {
                arrayList.add(new C1283g());
            }
            if (collection.contains(EnumC1138a.CODE_128)) {
                arrayList.add(new C1279c());
            }
            if (collection.contains(EnumC1138a.ITF)) {
                arrayList.add(new C1290n());
            }
            if (collection.contains(EnumC1138a.CODABAR)) {
                arrayList.add(new C1246a());
            }
            if (collection.contains(EnumC1138a.RSS_14)) {
                arrayList.add(new C1274e());
            }
            if (collection.contains(EnumC1138a.RSS_EXPANDED)) {
                arrayList.add(new C1270d());
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add(new C1293q(map));
            arrayList.add(new C1281e());
            arrayList.add(new C1246a());
            arrayList.add(new C1283g());
            arrayList.add(new C1279c());
            arrayList.add(new C1290n());
            arrayList.add(new C1274e());
            arrayList.add(new C1270d());
        }
        this.f2518a = (AbstractC1294r[]) arrayList.toArray(new AbstractC1294r[arrayList.size()]);
    }

    @Override // com.google.p028a.p047g.AbstractC1294r
    /* JADX INFO: renamed from: a */
    public final C1363r mo2038a(int i, C1190a c1190a, Map<EnumC1233e, ?> map) throws C1358m {
        for (AbstractC1294r abstractC1294r : this.f2518a) {
            try {
                return abstractC1294r.mo2038a(i, c1190a, map);
            } catch (AbstractC1362q unused) {
            }
        }
        throw C1358m.m2611a();
    }

    @Override // com.google.p028a.p047g.AbstractC1294r, com.google.p028a.InterfaceC1361p
    /* JADX INFO: renamed from: a */
    public final void mo1491a() {
        for (AbstractC1294r abstractC1294r : this.f2518a) {
            abstractC1294r.mo1491a();
        }
    }
}
