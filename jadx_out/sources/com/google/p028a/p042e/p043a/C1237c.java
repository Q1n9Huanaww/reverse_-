package com.google.p028a.p042e.p043a;

import com.google.p028a.C1210d;
import com.google.p028a.C1303h;
import com.google.p028a.p035c.C1194b;
import com.google.p028a.p035c.C1202e;
import com.google.p028a.p035c.p037b.C1195a;
import com.google.p028a.p035c.p037b.C1197c;
import com.google.p028a.p035c.p037b.C1199e;
import com.umeng.commonsdk.proguard.C1770bg;

/* JADX INFO: renamed from: com.google.a.e.a.c */
/* JADX INFO: compiled from: Decoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1237c {

    /* JADX INFO: renamed from: a */
    private static final int f2305a = 0;

    /* JADX INFO: renamed from: b */
    private static final int f2306b = 1;

    /* JADX INFO: renamed from: c */
    private static final int f2307c = 2;

    /* JADX INFO: renamed from: d */
    private final C1197c f2308d = new C1197c(C1195a.f2088h);

    /* JADX INFO: renamed from: b */
    private C1202e m2017b(C1194b c1194b) throws C1210d, C1303h {
        return m2018a(c1194b);
    }

    /* JADX INFO: renamed from: a */
    public final C1202e m2018a(C1194b c1194b) throws C1210d, C1303h {
        byte[] bArr;
        C1235a c1235a = new C1235a(c1194b);
        byte[] bArr2 = new byte[144];
        int i = c1235a.f2287b.f2078b;
        int i2 = c1235a.f2287b.f2077a;
        for (int i3 = 0; i3 < i; i3++) {
            int[] iArr = C1235a.f2286a[i3];
            for (int i4 = 0; i4 < i2; i4++) {
                int i5 = iArr[i4];
                if (i5 >= 0 && c1235a.f2287b.m1789a(i4, i3)) {
                    int i6 = i5 / 6;
                    bArr2[i6] = (byte) (((byte) (1 << (5 - (i5 % 6)))) | bArr2[i6]);
                }
            }
        }
        m2016a(bArr2, 0, 10, 10, 0);
        int i7 = bArr2[0] & C1770bg.f4125m;
        switch (i7) {
            case 2:
            case 3:
            case 4:
                m2016a(bArr2, 20, 84, 40, 1);
                m2016a(bArr2, 20, 84, 40, 2);
                bArr = new byte[94];
                break;
            case 5:
                m2016a(bArr2, 20, 68, 56, 1);
                m2016a(bArr2, 20, 68, 56, 2);
                bArr = new byte[78];
                break;
            default:
                throw C1303h.m2217a();
        }
        System.arraycopy(bArr2, 0, bArr, 0, 10);
        System.arraycopy(bArr2, 20, bArr, 10, bArr.length - 10);
        return C1236b.m2010a(bArr, i7);
    }

    /* JADX INFO: renamed from: a */
    private void m2016a(byte[] bArr, int i, int i2, int i3, int i4) throws C1210d {
        int i5 = i2 + i3;
        int i6 = i4 == 0 ? 1 : 2;
        int[] iArr = new int[i5 / i6];
        for (int i7 = 0; i7 < i5; i7++) {
            if (i4 == 0 || i7 % 2 == i4 - 1) {
                iArr[i7 / i6] = bArr[i7 + i] & 255;
            }
        }
        try {
            this.f2308d.m1819a(iArr, i3 / i6);
            for (int i8 = 0; i8 < i2; i8++) {
                if (i4 == 0 || i8 % 2 == i4 - 1) {
                    bArr[i8 + i] = (byte) iArr[i8 / i6];
                }
            }
        } catch (C1199e unused) {
            throw C1210d.m1865a();
        }
    }
}
