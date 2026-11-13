package com.google.p028a.p047g.p048a.p049a.p050a;

import com.google.p028a.C1303h;
import com.google.p028a.C1358m;
import com.google.p028a.p035c.C1190a;

/* JADX INFO: renamed from: com.google.a.g.a.a.a.d */
/* JADX INFO: compiled from: AI01393xDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1252d extends AbstractC1256h {

    /* JADX INFO: renamed from: d */
    private static final int f2358d = 8;

    /* JADX INFO: renamed from: e */
    private static final int f2359e = 2;

    /* JADX INFO: renamed from: f */
    private static final int f2360f = 10;

    public C1252d(C1190a c1190a) {
        super(c1190a);
    }

    @Override // com.google.p028a.p047g.p048a.p049a.p050a.AbstractC1258j
    /* JADX INFO: renamed from: a */
    public final String mo2053a() throws C1358m, C1303h {
        if (this.f2370b.f2065b < 48) {
            throw C1358m.m2611a();
        }
        StringBuilder sb = new StringBuilder();
        m2057b(sb, 8);
        int iM2103a = this.f2371c.m2103a(48, 2);
        sb.append("(393");
        sb.append(iM2103a);
        sb.append(')');
        int iM2103a2 = this.f2371c.m2103a(50, 10);
        if (iM2103a2 / 100 == 0) {
            sb.append('0');
        }
        if (iM2103a2 / 10 == 0) {
            sb.append('0');
        }
        sb.append(iM2103a2);
        sb.append(this.f2371c.m2104a(60, (String) null).f2383a);
        return sb.toString();
    }
}
