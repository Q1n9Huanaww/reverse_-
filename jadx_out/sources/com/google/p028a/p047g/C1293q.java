package com.google.p028a.p047g;

import com.google.p028a.AbstractC1362q;
import com.google.p028a.C1358m;
import com.google.p028a.C1363r;
import com.google.p028a.EnumC1138a;
import com.google.p028a.EnumC1233e;
import com.google.p028a.p035c.C1190a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.g.q */
/* JADX INFO: compiled from: MultiFormatUPCEANReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1293q extends AbstractC1294r {

    /* JADX INFO: renamed from: a */
    private final AbstractC1301y[] f2519a;

    public C1293q(Map<EnumC1233e, ?> map) {
        Collection collection = map == null ? null : (Collection) map.get(EnumC1233e.POSSIBLE_FORMATS);
        ArrayList arrayList = new ArrayList();
        if (collection != null) {
            if (collection.contains(EnumC1138a.EAN_13)) {
                arrayList.add(new C1285i());
            } else if (collection.contains(EnumC1138a.UPC_A)) {
                arrayList.add(new C1296t());
            }
            if (collection.contains(EnumC1138a.EAN_8)) {
                arrayList.add(new C1287k());
            }
            if (collection.contains(EnumC1138a.UPC_E)) {
                arrayList.add(new C1276aa());
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add(new C1285i());
            arrayList.add(new C1287k());
            arrayList.add(new C1276aa());
        }
        this.f2519a = (AbstractC1301y[]) arrayList.toArray(new AbstractC1301y[arrayList.size()]);
    }

    @Override // com.google.p028a.p047g.AbstractC1294r
    /* JADX INFO: renamed from: a */
    public final C1363r mo2038a(int i, C1190a c1190a, Map<EnumC1233e, ?> map) throws C1358m {
        int[] iArrM2213a = AbstractC1301y.m2213a(c1190a);
        for (AbstractC1301y abstractC1301y : this.f2519a) {
            try {
                C1363r c1363rMo2200a = abstractC1301y.mo2200a(i, c1190a, iArrM2213a, map);
                boolean z = c1363rMo2200a.f2843e == EnumC1138a.EAN_13 && c1363rMo2200a.f2839a.charAt(0) == '0';
                Collection collection = map == null ? null : (Collection) map.get(EnumC1233e.POSSIBLE_FORMATS);
                boolean z2 = collection == null || collection.contains(EnumC1138a.UPC_A);
                if (!z || !z2) {
                    return c1363rMo2200a;
                }
                C1363r c1363r = new C1363r(c1363rMo2200a.f2839a.substring(1), c1363rMo2200a.f2840b, c1363rMo2200a.f2842d, EnumC1138a.UPC_A);
                c1363r.m2625a(c1363rMo2200a.f2844f);
                return c1363r;
            } catch (AbstractC1362q unused) {
            }
        }
        throw C1358m.m2611a();
    }

    @Override // com.google.p028a.p047g.AbstractC1294r, com.google.p028a.InterfaceC1361p
    /* JADX INFO: renamed from: a */
    public final void mo1491a() {
        for (AbstractC1301y abstractC1301y : this.f2519a) {
            abstractC1301y.mo1491a();
        }
    }
}
