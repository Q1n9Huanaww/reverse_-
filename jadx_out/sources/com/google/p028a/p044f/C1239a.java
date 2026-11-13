package com.google.p028a.p044f;

import com.google.p028a.C1189c;
import com.google.p028a.C1210d;
import com.google.p028a.C1303h;
import com.google.p028a.C1358m;
import com.google.p028a.C1363r;
import com.google.p028a.C1365t;
import com.google.p028a.EnumC1233e;
import com.google.p028a.InterfaceC1361p;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.f.a */
/* JADX INFO: compiled from: ByQuadrantReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1239a implements InterfaceC1361p {

    /* JADX INFO: renamed from: a */
    private final InterfaceC1361p f2311a;

    private C1239a(InterfaceC1361p interfaceC1361p) {
        this.f2311a = interfaceC1361p;
    }

    @Override // com.google.p028a.InterfaceC1361p
    /* JADX INFO: renamed from: a */
    public final C1363r mo1489a(C1189c c1189c) throws C1210d, C1358m, C1303h {
        return mo1490a(c1189c, null);
    }

    @Override // com.google.p028a.InterfaceC1361p
    /* JADX INFO: renamed from: a */
    public final C1363r mo1490a(C1189c c1189c, Map<EnumC1233e, ?> map) throws C1210d, C1358m, C1303h {
        int iM1738a = c1189c.m1738a() / 2;
        int iM1740b = c1189c.m1740b() / 2;
        try {
            try {
                try {
                    try {
                        return this.f2311a.mo1490a(c1189c.m1739a(0, 0, iM1738a, iM1740b), map);
                    } catch (C1358m unused) {
                        int i = iM1738a / 2;
                        int i2 = iM1740b / 2;
                        C1363r c1363rMo1490a = this.f2311a.mo1490a(c1189c.m1739a(i, i2, iM1738a, iM1740b), map);
                        m2021a(c1363rMo1490a.f2842d, i, i2);
                        return c1363rMo1490a;
                    }
                } catch (C1358m unused2) {
                    C1363r c1363rMo1490a2 = this.f2311a.mo1490a(c1189c.m1739a(iM1738a, iM1740b, iM1738a, iM1740b), map);
                    m2021a(c1363rMo1490a2.f2842d, iM1738a, iM1740b);
                    return c1363rMo1490a2;
                }
            } catch (C1358m unused3) {
                C1363r c1363rMo1490a3 = this.f2311a.mo1490a(c1189c.m1739a(0, iM1740b, iM1738a, iM1740b), map);
                m2021a(c1363rMo1490a3.f2842d, 0, iM1740b);
                return c1363rMo1490a3;
            }
        } catch (C1358m unused4) {
            C1363r c1363rMo1490a4 = this.f2311a.mo1490a(c1189c.m1739a(iM1738a, 0, iM1738a, iM1740b), map);
            m2021a(c1363rMo1490a4.f2842d, iM1738a, 0);
            return c1363rMo1490a4;
        }
    }

    @Override // com.google.p028a.InterfaceC1361p
    /* JADX INFO: renamed from: a */
    public final void mo1491a() {
        this.f2311a.mo1491a();
    }

    /* JADX INFO: renamed from: a */
    private static void m2021a(C1365t[] c1365tArr, int i, int i2) {
        if (c1365tArr != null) {
            for (int i3 = 0; i3 < c1365tArr.length; i3++) {
                C1365t c1365t = c1365tArr[i3];
                c1365tArr[i3] = new C1365t(c1365t.f2858a + i, c1365t.f2859b + i2);
            }
        }
    }
}
