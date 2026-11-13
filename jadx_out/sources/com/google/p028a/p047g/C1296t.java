package com.google.p028a.p047g;

import com.google.p028a.C1189c;
import com.google.p028a.C1210d;
import com.google.p028a.C1303h;
import com.google.p028a.C1358m;
import com.google.p028a.C1363r;
import com.google.p028a.EnumC1138a;
import com.google.p028a.EnumC1233e;
import com.google.p028a.p035c.C1190a;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.g.t */
/* JADX INFO: compiled from: UPCAReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1296t extends AbstractC1301y {

    /* JADX INFO: renamed from: a */
    private final AbstractC1301y f2520a = new C1285i();

    @Override // com.google.p028a.p047g.AbstractC1301y
    /* JADX INFO: renamed from: a */
    public final C1363r mo2200a(int i, C1190a c1190a, int[] iArr, Map<EnumC1233e, ?> map) throws C1210d, C1358m, C1303h {
        return m2199a(this.f2520a.mo2200a(i, c1190a, iArr, map));
    }

    @Override // com.google.p028a.p047g.AbstractC1301y, com.google.p028a.p047g.AbstractC1294r
    /* JADX INFO: renamed from: a */
    public final C1363r mo2038a(int i, C1190a c1190a, Map<EnumC1233e, ?> map) throws C1210d, C1358m, C1303h {
        return m2199a(this.f2520a.mo2038a(i, c1190a, map));
    }

    @Override // com.google.p028a.p047g.AbstractC1294r, com.google.p028a.InterfaceC1361p
    /* JADX INFO: renamed from: a */
    public final C1363r mo1489a(C1189c c1189c) throws C1358m, C1303h {
        return m2199a(this.f2520a.mo1489a(c1189c));
    }

    @Override // com.google.p028a.p047g.AbstractC1294r, com.google.p028a.InterfaceC1361p
    /* JADX INFO: renamed from: a */
    public final C1363r mo1490a(C1189c c1189c, Map<EnumC1233e, ?> map) throws C1358m, C1303h {
        return m2199a(this.f2520a.mo1490a(c1189c, map));
    }

    @Override // com.google.p028a.p047g.AbstractC1301y
    /* JADX INFO: renamed from: b */
    final EnumC1138a mo2158b() {
        return EnumC1138a.UPC_A;
    }

    @Override // com.google.p028a.p047g.AbstractC1301y
    /* JADX INFO: renamed from: a */
    protected final int mo2155a(C1190a c1190a, int[] iArr, StringBuilder sb) throws C1358m {
        return this.f2520a.mo2155a(c1190a, iArr, sb);
    }

    /* JADX INFO: renamed from: a */
    private static C1363r m2199a(C1363r c1363r) throws C1303h {
        String str = c1363r.f2839a;
        if (str.charAt(0) == '0') {
            C1363r c1363r2 = new C1363r(str.substring(1), null, c1363r.f2842d, EnumC1138a.UPC_A);
            if (c1363r.f2844f != null) {
                c1363r2.m2625a(c1363r.f2844f);
            }
            return c1363r2;
        }
        throw C1303h.m2217a();
    }
}
