package com.google.p028a.p029a;

import com.google.p028a.C1189c;
import com.google.p028a.C1303h;
import com.google.p028a.C1358m;
import com.google.p028a.C1363r;
import com.google.p028a.C1365t;
import com.google.p028a.EnumC1138a;
import com.google.p028a.EnumC1233e;
import com.google.p028a.EnumC1364s;
import com.google.p028a.InterfaceC1361p;
import com.google.p028a.p029a.p030a.C1140a;
import com.google.p028a.p029a.p031b.C1142a;
import com.google.p028a.p035c.C1202e;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.a.b */
/* JADX INFO: compiled from: AztecReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1141b implements InterfaceC1361p {
    @Override // com.google.p028a.InterfaceC1361p
    /* JADX INFO: renamed from: a */
    public final void mo1491a() {
    }

    @Override // com.google.p028a.InterfaceC1361p
    /* JADX INFO: renamed from: a */
    public final C1363r mo1489a(C1189c c1189c) throws C1358m, C1303h {
        return mo1490a(c1189c, null);
    }

    @Override // com.google.p028a.InterfaceC1361p
    /* JADX INFO: renamed from: a */
    public final C1363r mo1490a(C1189c c1189c, Map<EnumC1233e, ?> map) throws C1358m, C1303h {
        C1365t[] c1365tArr;
        C1365t[] c1365tArr2;
        C1303h c1303h;
        C1142a c1142a = new C1142a(c1189c.m1741c());
        C1202e c1202eM1487a = null;
        try {
            C1139a c1139aM1510a = c1142a.m1510a(false);
            c1365tArr = c1139aM1510a.f2147e;
            try {
                c1365tArr2 = c1365tArr;
                c1303h = null;
                c1202eM1487a = new C1140a().m1487a(c1139aM1510a);
                e = null;
            } catch (C1303h e) {
                e = e;
                c1365tArr2 = c1365tArr;
                c1303h = e;
                e = null;
            } catch (C1358m e2) {
                e = e2;
                c1365tArr2 = c1365tArr;
                c1303h = null;
            }
        } catch (C1303h e3) {
            e = e3;
            c1365tArr = null;
        } catch (C1358m e4) {
            e = e4;
            c1365tArr = null;
        }
        if (c1202eM1487a == null) {
            try {
                C1139a c1139aM1510a2 = c1142a.m1510a(true);
                c1365tArr2 = c1139aM1510a2.f2147e;
                c1202eM1487a = new C1140a().m1487a(c1139aM1510a2);
            } catch (C1303h | C1358m e5) {
                if (e != null) {
                    throw e;
                }
                if (c1303h != null) {
                    throw c1303h;
                }
                throw e5;
            }
        }
        C1365t[] c1365tArr3 = c1365tArr2;
        if (map != null) {
            map.get(EnumC1233e.NEED_RESULT_POINT_CALLBACK);
        }
        C1363r c1363r = new C1363r(c1202eM1487a.f2138c, c1202eM1487a.f2136a, c1202eM1487a.f2137b, c1365tArr3, EnumC1138a.AZTEC, System.currentTimeMillis());
        List<byte[]> list = c1202eM1487a.f2139d;
        if (list != null) {
            c1363r.m2624a(EnumC1364s.BYTE_SEGMENTS, list);
        }
        String str = c1202eM1487a.f2140e;
        if (str != null) {
            c1363r.m2624a(EnumC1364s.ERROR_CORRECTION_LEVEL, str);
        }
        return c1363r;
    }
}
