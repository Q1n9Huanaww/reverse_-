package com.google.p028a.p047g.p048a.p049a.p050a;

import com.google.p028a.C1358m;
import com.google.p028a.p035c.C1190a;

/* JADX INFO: renamed from: com.google.a.g.a.a.a.e */
/* JADX INFO: compiled from: AI013x0x1xDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1253e extends AbstractC1257i {

    /* JADX INFO: renamed from: d */
    private static final int f2361d = 8;

    /* JADX INFO: renamed from: e */
    private static final int f2362e = 20;

    /* JADX INFO: renamed from: f */
    private static final int f2363f = 16;

    /* JADX INFO: renamed from: g */
    private final String f2364g;

    /* JADX INFO: renamed from: h */
    private final String f2365h;

    public C1253e(C1190a c1190a, String str, String str2) {
        super(c1190a);
        this.f2364g = str2;
        this.f2365h = str;
    }

    @Override // com.google.p028a.p047g.p048a.p049a.p050a.AbstractC1257i
    /* JADX INFO: renamed from: a */
    protected final void mo2052a(StringBuilder sb, int i) {
        sb.append('(');
        sb.append(this.f2365h);
        sb.append(i / 100000);
        sb.append(')');
    }

    @Override // com.google.p028a.p047g.p048a.p049a.p050a.AbstractC1257i
    /* JADX INFO: renamed from: a */
    protected final int mo2051a(int i) {
        return i % 100000;
    }

    @Override // com.google.p028a.p047g.p048a.p049a.p050a.AbstractC1258j
    /* JADX INFO: renamed from: a */
    public final String mo2053a() throws C1358m {
        if (this.f2370b.f2065b != 84) {
            throw C1358m.m2611a();
        }
        StringBuilder sb = new StringBuilder();
        m2057b(sb, 8);
        m2058b(sb, 48, 20);
        int iM2103a = this.f2371c.m2103a(68, 16);
        if (iM2103a != 38400) {
            sb.append('(');
            sb.append(this.f2364g);
            sb.append(')');
            int i = iM2103a % 32;
            int i2 = iM2103a / 32;
            int i3 = (i2 % 12) + 1;
            int i4 = i2 / 12;
            if (i4 / 10 == 0) {
                sb.append('0');
            }
            sb.append(i4);
            if (i3 / 10 == 0) {
                sb.append('0');
            }
            sb.append(i3);
            if (i / 10 == 0) {
                sb.append('0');
            }
            sb.append(i);
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: a */
    private void m2054a(StringBuilder sb) {
        int iM2103a = this.f2371c.m2103a(68, 16);
        if (iM2103a == 38400) {
            return;
        }
        sb.append('(');
        sb.append(this.f2364g);
        sb.append(')');
        int i = iM2103a % 32;
        int i2 = iM2103a / 32;
        int i3 = (i2 % 12) + 1;
        int i4 = i2 / 12;
        if (i4 / 10 == 0) {
            sb.append('0');
        }
        sb.append(i4);
        if (i3 / 10 == 0) {
            sb.append('0');
        }
        sb.append(i3);
        if (i / 10 == 0) {
            sb.append('0');
        }
        sb.append(i);
    }
}
