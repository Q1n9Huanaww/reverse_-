package com.google.p028a.p047g.p048a.p049a.p050a;

import com.google.p028a.p035c.C1190a;

/* JADX INFO: renamed from: com.google.a.g.a.a.a.i */
/* JADX INFO: compiled from: AI01weightDecoder.java */
/* JADX INFO: loaded from: classes.dex */
abstract class AbstractC1257i extends AbstractC1256h {
    /* JADX INFO: renamed from: a */
    protected abstract int mo2051a(int i);

    /* JADX INFO: renamed from: a */
    protected abstract void mo2052a(StringBuilder sb, int i);

    AbstractC1257i(C1190a c1190a) {
        super(c1190a);
    }

    /* JADX INFO: renamed from: b */
    final void m2058b(StringBuilder sb, int i, int i2) {
        int iM2103a = this.f2371c.m2103a(i, i2);
        mo2052a(sb, iM2103a);
        int iMo2051a = mo2051a(iM2103a);
        int i3 = 100000;
        for (int i4 = 0; i4 < 5; i4++) {
            if (iMo2051a / i3 == 0) {
                sb.append('0');
            }
            i3 /= 10;
        }
        sb.append(iMo2051a);
    }
}
