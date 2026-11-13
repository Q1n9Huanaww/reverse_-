package com.google.p028a.p047g.p048a.p049a.p050a;

import com.google.p028a.C1303h;
import com.google.p028a.C1358m;
import com.google.p028a.p035c.C1190a;

/* JADX INFO: renamed from: com.google.a.g.a.a.a.c */
/* JADX INFO: compiled from: AI01392xDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1251c extends AbstractC1256h {

    /* JADX INFO: renamed from: d */
    private static final int f2356d = 8;

    /* JADX INFO: renamed from: e */
    private static final int f2357e = 2;

    public C1251c(C1190a c1190a) {
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
        sb.append("(392");
        sb.append(iM2103a);
        sb.append(')');
        sb.append(this.f2371c.m2104a(50, (String) null).f2383a);
        return sb.toString();
    }
}
