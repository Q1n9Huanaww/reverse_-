package com.google.p028a.p038d.p039a;

/* JADX INFO: renamed from: com.google.a.d.a.b */
/* JADX INFO: compiled from: DataBlock.java */
/* JADX INFO: loaded from: classes.dex */
final class C1213b {

    /* JADX INFO: renamed from: a */
    final int f2183a;

    /* JADX INFO: renamed from: b */
    final byte[] f2184b;

    C1213b(int i, byte[] bArr) {
        this.f2183a = i;
        this.f2184b = bArr;
    }

    /* JADX INFO: renamed from: a */
    private int m1879a() {
        return this.f2183a;
    }

    /* JADX INFO: renamed from: b */
    private byte[] m1881b() {
        return this.f2184b;
    }

    /* JADX INFO: renamed from: a */
    private static C1213b[] m1880a(byte[] bArr, C1216e c1216e) {
        C1216e.b bVar = c1216e.f2206f;
        C1216e.a[] aVarArr = bVar.f2211b;
        int i = 0;
        for (C1216e.a aVar : aVarArr) {
            i += aVar.f2208a;
        }
        C1213b[] c1213bArr = new C1213b[i];
        int length = aVarArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            C1216e.a aVar2 = aVarArr[i2];
            int i4 = i3;
            int i5 = 0;
            while (i5 < aVar2.f2208a) {
                int i6 = aVar2.f2209b;
                c1213bArr[i4] = new C1213b(i6, new byte[bVar.f2210a + i6]);
                i5++;
                i4++;
            }
            i2++;
            i3 = i4;
        }
        int length2 = c1213bArr[0].f2184b.length - bVar.f2210a;
        int i7 = length2 - 1;
        int i8 = 0;
        int i9 = 0;
        while (i8 < i7) {
            int i10 = i9;
            int i11 = 0;
            while (i11 < i3) {
                c1213bArr[i11].f2184b[i8] = bArr[i10];
                i11++;
                i10++;
            }
            i8++;
            i9 = i10;
        }
        boolean z = c1216e.f2201a == 24;
        int i12 = z ? 8 : i3;
        int i13 = i9;
        int i14 = 0;
        while (i14 < i12) {
            c1213bArr[i14].f2184b[i7] = bArr[i13];
            i14++;
            i13++;
        }
        int length3 = c1213bArr[0].f2184b.length;
        while (length2 < length3) {
            int i15 = 0;
            while (i15 < i3) {
                int i16 = z ? (i15 + 8) % i3 : i15;
                c1213bArr[i16].f2184b[(!z || i16 <= 7) ? length2 : length2 - 1] = bArr[i13];
                i15++;
                i13++;
            }
            length2++;
        }
        if (i13 != bArr.length) {
            throw new IllegalArgumentException();
        }
        return c1213bArr;
    }
}
