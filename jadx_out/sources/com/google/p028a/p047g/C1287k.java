package com.google.p028a.p047g;

import com.google.p028a.C1358m;
import com.google.p028a.EnumC1138a;
import com.google.p028a.p035c.C1190a;

/* JADX INFO: renamed from: com.google.a.g.k */
/* JADX INFO: compiled from: EAN8Reader.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1287k extends AbstractC1301y {

    /* JADX INFO: renamed from: a */
    private final int[] f2499a = new int[4];

    @Override // com.google.p028a.p047g.AbstractC1301y
    /* JADX INFO: renamed from: a */
    protected final int mo2155a(C1190a c1190a, int[] iArr, StringBuilder sb) throws C1358m {
        int[] iArr2 = this.f2499a;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int i = c1190a.f2065b;
        int i2 = iArr[1];
        int i3 = 0;
        while (i3 < 4 && i2 < i) {
            sb.append((char) (m2211a(c1190a, iArr2, i2, f2534e) + 48));
            int i4 = i2;
            for (int i5 : iArr2) {
                i4 += i5;
            }
            i3++;
            i2 = i4;
        }
        int i6 = m2214a(c1190a, i2, true, f2532c)[1];
        int i7 = 0;
        while (i7 < 4 && i6 < i) {
            sb.append((char) (m2211a(c1190a, iArr2, i6, f2534e) + 48));
            int i8 = i6;
            for (int i9 : iArr2) {
                i8 += i9;
            }
            i7++;
            i6 = i8;
        }
        return i6;
    }

    @Override // com.google.p028a.p047g.AbstractC1301y
    /* JADX INFO: renamed from: b */
    final EnumC1138a mo2158b() {
        return EnumC1138a.EAN_8;
    }
}
