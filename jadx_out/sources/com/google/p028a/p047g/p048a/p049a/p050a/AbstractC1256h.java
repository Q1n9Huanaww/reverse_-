package com.google.p028a.p047g.p048a.p049a.p050a;

import com.google.p028a.p035c.C1190a;

/* JADX INFO: renamed from: com.google.a.g.a.a.a.h */
/* JADX INFO: compiled from: AI01decoder.java */
/* JADX INFO: loaded from: classes.dex */
abstract class AbstractC1256h extends AbstractC1258j {

    /* JADX INFO: renamed from: a */
    static final int f2369a = 40;

    AbstractC1256h(C1190a c1190a) {
        super(c1190a);
    }

    /* JADX INFO: renamed from: b */
    final void m2057b(StringBuilder sb, int i) {
        sb.append("(01)");
        int length = sb.length();
        sb.append('9');
        m2056a(sb, i, length);
    }

    /* JADX INFO: renamed from: a */
    private static void m2055a(StringBuilder sb, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < 13; i3++) {
            int iCharAt = sb.charAt(i3 + i) - '0';
            if ((i3 & 1) == 0) {
                iCharAt *= 3;
            }
            i2 += iCharAt;
        }
        int i4 = 10 - (i2 % 10);
        sb.append(i4 != 10 ? i4 : 0);
    }

    /* JADX INFO: renamed from: a */
    final void m2056a(StringBuilder sb, int i, int i2) {
        for (int i3 = 0; i3 < 4; i3++) {
            int iM2103a = this.f2371c.m2103a((i3 * 10) + i, 10);
            if (iM2103a / 100 == 0) {
                sb.append('0');
            }
            if (iM2103a / 10 == 0) {
                sb.append('0');
            }
            sb.append(iM2103a);
        }
        int i4 = 0;
        for (int i5 = 0; i5 < 13; i5++) {
            int iCharAt = sb.charAt(i5 + i2) - '0';
            if ((i5 & 1) == 0) {
                iCharAt *= 3;
            }
            i4 += iCharAt;
        }
        int i6 = 10 - (i4 % 10);
        if (i6 == 10) {
            i6 = 0;
        }
        sb.append(i6);
    }
}
