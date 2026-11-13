package com.google.p028a.p035c;

import com.google.p028a.AbstractC1151b;
import com.google.p028a.AbstractC1355j;
import com.google.p028a.C1358m;

/* JADX INFO: renamed from: com.google.a.c.h */
/* JADX INFO: compiled from: GlobalHistogramBinarizer.java */
/* JADX INFO: loaded from: classes.dex */
public class C1205h extends AbstractC1151b {

    /* JADX INFO: renamed from: b */
    private static final int f2148b = 5;

    /* JADX INFO: renamed from: c */
    private static final int f2149c = 3;

    /* JADX INFO: renamed from: d */
    private static final int f2150d = 32;

    /* JADX INFO: renamed from: e */
    private static final byte[] f2151e = new byte[0];

    /* JADX INFO: renamed from: f */
    private byte[] f2152f;

    /* JADX INFO: renamed from: g */
    private final int[] f2153g;

    public C1205h(AbstractC1355j abstractC1355j) {
        super(abstractC1355j);
        this.f2152f = f2151e;
        this.f2153g = new int[32];
    }

    @Override // com.google.p028a.AbstractC1151b
    /* JADX INFO: renamed from: a */
    public AbstractC1151b mo1562a(AbstractC1355j abstractC1355j) {
        return new C1205h(abstractC1355j);
    }

    /* JADX INFO: renamed from: a */
    private void m1849a(int i) {
        if (this.f2152f.length < i) {
            this.f2152f = new byte[i];
        }
        for (int i2 = 0; i2 < 32; i2++) {
            this.f2153g[i2] = 0;
        }
    }

    /* JADX INFO: renamed from: a */
    private static int m1848a(int[] iArr) throws C1358m {
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            if (iArr[i4] > i) {
                i = iArr[i4];
                i3 = i4;
            }
            if (iArr[i4] > i2) {
                i2 = iArr[i4];
            }
        }
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < length; i7++) {
            int i8 = i7 - i3;
            int i9 = iArr[i7] * i8 * i8;
            if (i9 > i5) {
                i6 = i7;
                i5 = i9;
            }
        }
        if (i3 > i6) {
            int i10 = i3;
            i3 = i6;
            i6 = i10;
        }
        if (i6 - i3 <= length / 16) {
            throw C1358m.m2611a();
        }
        int i11 = i6 - 1;
        int i12 = -1;
        int i13 = i11;
        while (i11 > i3) {
            int i14 = i11 - i3;
            int i15 = i14 * i14 * (i6 - i11) * (i2 - iArr[i11]);
            if (i15 > i12) {
                i13 = i11;
                i12 = i15;
            }
            i11--;
        }
        return i13 << 3;
    }

    @Override // com.google.p028a.AbstractC1151b
    /* JADX INFO: renamed from: a */
    public final C1190a mo1563a(int i, C1190a c1190a) throws C1358m {
        AbstractC1355j abstractC1355j = this.f1937a;
        int i2 = abstractC1355j.f2820a;
        if (c1190a == null || c1190a.f2065b < i2) {
            c1190a = new C1190a(i2);
        } else {
            c1190a.m1757b();
        }
        m1849a(i2);
        byte[] bArrMo2424a = abstractC1355j.mo2424a(i, this.f2152f);
        int[] iArr = this.f2153g;
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = (bArrMo2424a[i3] & 255) >> 3;
            iArr[i4] = iArr[i4] + 1;
        }
        int iM1848a = m1848a(iArr);
        if (i2 < 3) {
            for (int i5 = 0; i5 < i2; i5++) {
                if ((bArrMo2424a[i5] & 255) < iM1848a) {
                    c1190a.m1758b(i5);
                }
            }
        } else {
            int i6 = bArrMo2424a[0] & 255;
            int i7 = bArrMo2424a[1] & 255;
            int i8 = i6;
            int i9 = 1;
            while (i9 < i2 - 1) {
                int i10 = i9 + 1;
                int i11 = bArrMo2424a[i10] & 255;
                if ((((i7 << 2) - i8) - i11) / 2 < iM1848a) {
                    c1190a.m1758b(i9);
                }
                i8 = i7;
                i9 = i10;
                i7 = i11;
            }
        }
        return c1190a;
    }

    @Override // com.google.p028a.AbstractC1151b
    /* JADX INFO: renamed from: a */
    public C1194b mo1564a() throws C1358m {
        AbstractC1355j abstractC1355j = this.f1937a;
        int i = abstractC1355j.f2820a;
        int i2 = abstractC1355j.f2821b;
        C1194b c1194b = new C1194b(i, i2);
        m1849a(i);
        int[] iArr = this.f2153g;
        for (int i3 = 1; i3 < 5; i3++) {
            byte[] bArrMo2424a = abstractC1355j.mo2424a((i2 * i3) / 5, this.f2152f);
            int i4 = (i << 2) / 5;
            for (int i5 = i / 5; i5 < i4; i5++) {
                int i6 = (bArrMo2424a[i5] & 255) >> 3;
                iArr[i6] = iArr[i6] + 1;
            }
        }
        int iM1848a = m1848a(iArr);
        byte[] bArrMo2423a = abstractC1355j.mo2423a();
        for (int i7 = 0; i7 < i2; i7++) {
            int i8 = i7 * i;
            for (int i9 = 0; i9 < i; i9++) {
                if ((bArrMo2423a[i8 + i9] & 255) < iM1848a) {
                    c1194b.m1790b(i9, i7);
                }
            }
        }
        return c1194b;
    }
}
