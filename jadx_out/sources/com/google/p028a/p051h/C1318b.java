package com.google.p028a.p051h;

import android.support.v7.widget.ActivityChooserView;
import com.google.p028a.C1189c;
import com.google.p028a.C1210d;
import com.google.p028a.C1303h;
import com.google.p028a.C1358m;
import com.google.p028a.C1363r;
import com.google.p028a.C1365t;
import com.google.p028a.EnumC1138a;
import com.google.p028a.EnumC1233e;
import com.google.p028a.EnumC1364s;
import com.google.p028a.InterfaceC1361p;
import com.google.p028a.p035c.C1202e;
import com.google.p028a.p044f.InterfaceC1244c;
import com.google.p028a.p051h.p052a.C1317j;
import com.google.p028a.p051h.p054b.C1319a;
import com.google.p028a.p051h.p054b.C1320b;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.h.b */
/* JADX INFO: compiled from: PDF417Reader.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1318b implements InterfaceC1244c, InterfaceC1361p {
    @Override // com.google.p028a.InterfaceC1361p
    /* JADX INFO: renamed from: a */
    public final void mo1491a() {
    }

    @Override // com.google.p028a.InterfaceC1361p
    /* JADX INFO: renamed from: a */
    public final C1363r mo1489a(C1189c c1189c) throws C1210d, C1358m, C1303h {
        return mo1490a(c1189c, (Map<EnumC1233e, ?>) null);
    }

    @Override // com.google.p028a.InterfaceC1361p
    /* JADX INFO: renamed from: a */
    public final C1363r mo1490a(C1189c c1189c, Map<EnumC1233e, ?> map) throws C1210d, C1358m, C1303h {
        C1363r[] c1363rArrM2344a = m2344a(c1189c, false);
        if (c1363rArrM2344a == null || c1363rArrM2344a.length == 0 || c1363rArrM2344a[0] == null) {
            throw C1358m.m2611a();
        }
        return c1363rArrM2344a[0];
    }

    @Override // com.google.p028a.p044f.InterfaceC1244c
    /* JADX INFO: renamed from: a_ */
    public final C1363r[] mo2023a_(C1189c c1189c) throws C1358m {
        return mo2024b(c1189c);
    }

    @Override // com.google.p028a.p044f.InterfaceC1244c
    /* JADX INFO: renamed from: b */
    public final C1363r[] mo2024b(C1189c c1189c) throws C1358m {
        try {
            return m2344a(c1189c, true);
        } catch (C1210d | C1303h unused) {
            throw C1358m.m2611a();
        }
    }

    /* JADX INFO: renamed from: a */
    private static C1363r[] m2344a(C1189c c1189c, boolean z) throws C1210d, C1358m, C1303h {
        ArrayList arrayList = new ArrayList();
        C1320b c1320bM2348a = C1319a.m2348a(c1189c, z);
        for (C1365t[] c1365tArr : c1320bM2348a.f2645b) {
            C1202e c1202eM2323a = C1317j.m2323a(c1320bM2348a.f2644a, c1365tArr[4], c1365tArr[5], c1365tArr[6], c1365tArr[7], Math.min(Math.min(m2345b(c1365tArr[0], c1365tArr[4]), (m2345b(c1365tArr[6], c1365tArr[2]) * 17) / 18), Math.min(m2345b(c1365tArr[1], c1365tArr[5]), (m2345b(c1365tArr[7], c1365tArr[3]) * 17) / 18)), Math.max(Math.max(m2342a(c1365tArr[0], c1365tArr[4]), (m2342a(c1365tArr[6], c1365tArr[2]) * 17) / 18), Math.max(m2342a(c1365tArr[1], c1365tArr[5]), (m2342a(c1365tArr[7], c1365tArr[3]) * 17) / 18)));
            C1363r c1363r = new C1363r(c1202eM2323a.f2138c, c1202eM2323a.f2136a, c1365tArr, EnumC1138a.PDF_417);
            c1363r.m2624a(EnumC1364s.ERROR_CORRECTION_LEVEL, c1202eM2323a.f2140e);
            C1321c c1321c = (C1321c) c1202eM2323a.f2143h;
            if (c1321c != null) {
                c1363r.m2624a(EnumC1364s.PDF417_EXTRA_METADATA, c1321c);
            }
            arrayList.add(c1363r);
        }
        return (C1363r[]) arrayList.toArray(new C1363r[arrayList.size()]);
    }

    /* JADX INFO: renamed from: a */
    private static int m2343a(C1365t[] c1365tArr) {
        return Math.max(Math.max(m2342a(c1365tArr[0], c1365tArr[4]), (m2342a(c1365tArr[6], c1365tArr[2]) * 17) / 18), Math.max(m2342a(c1365tArr[1], c1365tArr[5]), (m2342a(c1365tArr[7], c1365tArr[3]) * 17) / 18));
    }

    /* JADX INFO: renamed from: b */
    private static int m2346b(C1365t[] c1365tArr) {
        return Math.min(Math.min(m2345b(c1365tArr[0], c1365tArr[4]), (m2345b(c1365tArr[6], c1365tArr[2]) * 17) / 18), Math.min(m2345b(c1365tArr[1], c1365tArr[5]), (m2345b(c1365tArr[7], c1365tArr[3]) * 17) / 18));
    }

    /* JADX INFO: renamed from: a */
    private static int m2342a(C1365t c1365t, C1365t c1365t2) {
        if (c1365t == null || c1365t2 == null) {
            return 0;
        }
        return (int) Math.abs(c1365t.f2858a - c1365t2.f2858a);
    }

    /* JADX INFO: renamed from: b */
    private static int m2345b(C1365t c1365t, C1365t c1365t2) {
        return (c1365t == null || c1365t2 == null) ? ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED : (int) Math.abs(c1365t.f2858a - c1365t2.f2858a);
    }
}
