package com.google.p028a;

import com.google.p028a.p029a.C1141b;
import com.google.p028a.p038d.C1211a;
import com.google.p028a.p042e.C1234a;
import com.google.p028a.p047g.C1292p;
import com.google.p028a.p051h.C1318b;
import com.google.p028a.p056i.C1331a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.k */
/* JADX INFO: compiled from: MultiFormatReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1356k implements InterfaceC1361p {

    /* JADX INFO: renamed from: a */
    private Map<EnumC1233e, ?> f2822a;

    /* JADX INFO: renamed from: b */
    private InterfaceC1361p[] f2823b;

    @Override // com.google.p028a.InterfaceC1361p
    /* JADX INFO: renamed from: a */
    public final C1363r mo1489a(C1189c c1189c) throws C1358m {
        m2608a((Map<EnumC1233e, ?>) null);
        return m2610c(c1189c);
    }

    @Override // com.google.p028a.InterfaceC1361p
    /* JADX INFO: renamed from: a */
    public final C1363r mo1490a(C1189c c1189c, Map<EnumC1233e, ?> map) throws C1358m {
        m2608a(map);
        return m2610c(c1189c);
    }

    /* JADX INFO: renamed from: b */
    private C1363r m2609b(C1189c c1189c) throws C1358m {
        if (this.f2823b == null) {
            m2608a((Map<EnumC1233e, ?>) null);
        }
        return m2610c(c1189c);
    }

    /* JADX INFO: renamed from: a */
    private void m2608a(Map<EnumC1233e, ?> map) {
        this.f2822a = map;
        boolean z = map != null && map.containsKey(EnumC1233e.TRY_HARDER);
        Collection collection = map == null ? null : (Collection) map.get(EnumC1233e.POSSIBLE_FORMATS);
        ArrayList arrayList = new ArrayList();
        if (collection != null) {
            boolean z2 = collection.contains(EnumC1138a.UPC_A) || collection.contains(EnumC1138a.UPC_E) || collection.contains(EnumC1138a.EAN_13) || collection.contains(EnumC1138a.EAN_8) || collection.contains(EnumC1138a.CODABAR) || collection.contains(EnumC1138a.CODE_39) || collection.contains(EnumC1138a.CODE_93) || collection.contains(EnumC1138a.CODE_128) || collection.contains(EnumC1138a.ITF) || collection.contains(EnumC1138a.RSS_14) || collection.contains(EnumC1138a.RSS_EXPANDED);
            if (z2 && !z) {
                arrayList.add(new C1292p(map));
            }
            if (collection.contains(EnumC1138a.QR_CODE)) {
                arrayList.add(new C1331a());
            }
            if (collection.contains(EnumC1138a.DATA_MATRIX)) {
                arrayList.add(new C1211a());
            }
            if (collection.contains(EnumC1138a.AZTEC)) {
                arrayList.add(new C1141b());
            }
            if (collection.contains(EnumC1138a.PDF_417)) {
                arrayList.add(new C1318b());
            }
            if (collection.contains(EnumC1138a.MAXICODE)) {
                arrayList.add(new C1234a());
            }
            if (z2 && z) {
                arrayList.add(new C1292p(map));
            }
        }
        if (arrayList.isEmpty()) {
            if (!z) {
                arrayList.add(new C1292p(map));
            }
            arrayList.add(new C1331a());
            arrayList.add(new C1211a());
            arrayList.add(new C1141b());
            arrayList.add(new C1318b());
            arrayList.add(new C1234a());
            if (z) {
                arrayList.add(new C1292p(map));
            }
        }
        this.f2823b = (InterfaceC1361p[]) arrayList.toArray(new InterfaceC1361p[arrayList.size()]);
    }

    @Override // com.google.p028a.InterfaceC1361p
    /* JADX INFO: renamed from: a */
    public final void mo1491a() {
        if (this.f2823b != null) {
            for (InterfaceC1361p interfaceC1361p : this.f2823b) {
                interfaceC1361p.mo1491a();
            }
        }
    }

    /* JADX INFO: renamed from: c */
    private C1363r m2610c(C1189c c1189c) throws C1358m {
        if (this.f2823b != null) {
            for (InterfaceC1361p interfaceC1361p : this.f2823b) {
                try {
                    return interfaceC1361p.mo1490a(c1189c, this.f2822a);
                } catch (AbstractC1362q unused) {
                }
            }
        }
        throw C1358m.m2611a();
    }
}
