package com.google.p028a.p056i.p057a;

/* JADX INFO: renamed from: com.google.a.i.a.b */
/* JADX INFO: compiled from: DataBlock.java */
/* JADX INFO: loaded from: classes.dex */
final class C1333b {

    /* JADX INFO: renamed from: a */
    final int f2717a;

    /* JADX INFO: renamed from: b */
    final byte[] f2718b;

    C1333b(int i, byte[] bArr) {
        this.f2717a = i;
        this.f2718b = bArr;
    }

    /* JADX INFO: renamed from: a */
    private static C1333b[] m2441a(byte[] bArr, C1341j c1341j, EnumC1337f enumC1337f) {
        if (bArr.length != c1341j.f2761c) {
            throw new IllegalArgumentException();
        }
        C1341j.b bVarM2480a = c1341j.m2480a(enumC1337f);
        C1341j.a[] aVarArr = bVarM2480a.f2766b;
        int i = 0;
        for (C1341j.a aVar : aVarArr) {
            i += aVar.f2763a;
        }
        C1333b[] c1333bArr = new C1333b[i];
        int length = aVarArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            C1341j.a aVar2 = aVarArr[i2];
            int i4 = i3;
            int i5 = 0;
            while (i5 < aVar2.f2763a) {
                int i6 = aVar2.f2764b;
                c1333bArr[i4] = new C1333b(i6, new byte[bVarM2480a.f2765a + i6]);
                i5++;
                i4++;
            }
            i2++;
            i3 = i4;
        }
        int length2 = c1333bArr[0].f2718b.length;
        int length3 = c1333bArr.length - 1;
        while (length3 >= 0 && c1333bArr[length3].f2718b.length != length2) {
            length3--;
        }
        int i7 = length3 + 1;
        int i8 = length2 - bVarM2480a.f2765a;
        int i9 = 0;
        int i10 = 0;
        while (i9 < i8) {
            int i11 = i10;
            int i12 = 0;
            while (i12 < i3) {
                c1333bArr[i12].f2718b[i9] = bArr[i11];
                i12++;
                i11++;
            }
            i9++;
            i10 = i11;
        }
        int i13 = i7;
        while (i13 < i3) {
            c1333bArr[i13].f2718b[i8] = bArr[i10];
            i13++;
            i10++;
        }
        int length4 = c1333bArr[0].f2718b.length;
        while (i8 < length4) {
            int i14 = i10;
            int i15 = 0;
            while (i15 < i3) {
                c1333bArr[i15].f2718b[i15 < i7 ? i8 : i8 + 1] = bArr[i14];
                i15++;
                i14++;
            }
            i8++;
            i10 = i14;
        }
        return c1333bArr;
    }

    /* JADX INFO: renamed from: a */
    private int m2440a() {
        return this.f2717a;
    }

    /* JADX INFO: renamed from: b */
    private byte[] m2442b() {
        return this.f2718b;
    }
}
