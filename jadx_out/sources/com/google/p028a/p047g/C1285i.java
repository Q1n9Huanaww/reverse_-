package com.google.p028a.p047g;

import com.google.p028a.C1358m;
import com.google.p028a.EnumC1138a;
import com.google.p028a.p035c.C1190a;

/* JADX INFO: renamed from: com.google.a.g.i */
/* JADX INFO: compiled from: EAN13Reader.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1285i extends AbstractC1301y {

    /* JADX INFO: renamed from: a */
    static final int[] f2496a = {0, 11, 13, 14, 19, 25, 28, 21, 22, 26};

    /* JADX INFO: renamed from: g */
    private final int[] f2497g = new int[4];

    @Override // com.google.p028a.p047g.AbstractC1301y
    /* JADX INFO: renamed from: a */
    protected final int mo2155a(C1190a c1190a, int[] iArr, StringBuilder sb) throws C1358m {
        int[] iArr2 = this.f2497g;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int i = c1190a.f2065b;
        int i2 = iArr[1];
        int i3 = 0;
        int i4 = 0;
        while (i3 < 6 && i2 < i) {
            int iA = m2211a(c1190a, iArr2, i2, f2535f);
            sb.append((char) ((iA % 10) + 48));
            int i5 = i2;
            for (int i6 : iArr2) {
                i5 += i6;
            }
            if (iA >= 10) {
                i4 = (1 << (5 - i3)) | i4;
            }
            i3++;
            i2 = i5;
        }
        for (int i7 = 0; i7 < 10; i7++) {
            if (i4 == f2496a[i7]) {
                sb.insert(0, (char) (i7 + 48));
                int i8 = m2214a(c1190a, i2, true, f2532c)[1];
                int i9 = 0;
                while (i9 < 6 && i8 < i) {
                    sb.append((char) (m2211a(c1190a, iArr2, i8, f2534e) + 48));
                    int i10 = i8;
                    for (int i11 : iArr2) {
                        i10 += i11;
                    }
                    i9++;
                    i8 = i10;
                }
                return i8;
            }
        }
        throw C1358m.m2611a();
    }

    @Override // com.google.p028a.p047g.AbstractC1301y
    /* JADX INFO: renamed from: b */
    final EnumC1138a mo2158b() {
        return EnumC1138a.EAN_13;
    }

    /* JADX INFO: renamed from: a */
    private static void m2181a(StringBuilder sb, int i) throws C1358m {
        for (int i2 = 0; i2 < 10; i2++) {
            if (i == f2496a[i2]) {
                sb.insert(0, (char) (i2 + 48));
                return;
            }
        }
        throw C1358m.m2611a();
    }
}
