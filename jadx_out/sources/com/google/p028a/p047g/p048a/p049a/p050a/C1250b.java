package com.google.p028a.p047g.p048a.p049a.p050a;

import com.google.p028a.p035c.C1190a;
import com.p024ds.daisi.constant.Constants;

/* JADX INFO: renamed from: com.google.a.g.a.a.a.b */
/* JADX INFO: compiled from: AI01320xDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1250b extends AbstractC1254f {
    @Override // com.google.p028a.p047g.p048a.p049a.p050a.AbstractC1257i
    /* JADX INFO: renamed from: a */
    protected final int mo2051a(int i) {
        return i < 10000 ? i : i - Constants.REQUEST_CODE_APP_INSTALL;
    }

    public C1250b(C1190a c1190a) {
        super(c1190a);
    }

    @Override // com.google.p028a.p047g.p048a.p049a.p050a.AbstractC1257i
    /* JADX INFO: renamed from: a */
    protected final void mo2052a(StringBuilder sb, int i) {
        if (i < 10000) {
            sb.append("(3202)");
        } else {
            sb.append("(3203)");
        }
    }
}
