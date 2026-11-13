package com.google.p028a.p051h.p055c;

import java.lang.reflect.Array;

/* JADX INFO: renamed from: com.google.a.h.c.a */
/* JADX INFO: compiled from: BarcodeMatrix.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1322a {

    /* JADX INFO: renamed from: a */
    int f2657a;

    /* JADX INFO: renamed from: b */
    private final C1323b[] f2658b;

    /* JADX INFO: renamed from: c */
    private final int f2659c;

    /* JADX INFO: renamed from: d */
    private final int f2660d;

    public C1322a(int i, int i2) {
        this.f2658b = new C1323b[i];
        int length = this.f2658b.length;
        for (int i3 = 0; i3 < length; i3++) {
            this.f2658b[i3] = new C1323b(((i2 + 4) * 17) + 1);
        }
        this.f2660d = i2 * 17;
        this.f2659c = i;
        this.f2657a = -1;
    }

    /* JADX INFO: renamed from: a */
    private void m2378a(int i, int i2, byte b) {
        this.f2658b[i2].f2661a[i] = b;
    }

    /* JADX INFO: renamed from: b */
    private void m2379b() {
        this.f2657a++;
    }

    /* JADX INFO: renamed from: a */
    final C1323b m2381a() {
        return this.f2658b[this.f2657a];
    }

    /* JADX INFO: renamed from: c */
    private byte[][] m2380c() {
        return m2382a(1, 1);
    }

    /* JADX INFO: renamed from: a */
    public final byte[][] m2382a(int i, int i2) {
        byte[][] bArr = (byte[][]) Array.newInstance((Class<?>) byte.class, this.f2659c * i2, this.f2660d * i);
        int i3 = this.f2659c * i2;
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = (i3 - i4) - 1;
            C1323b c1323b = this.f2658b[i4 / i2];
            byte[] bArr2 = new byte[c1323b.f2661a.length * i];
            for (int i6 = 0; i6 < bArr2.length; i6++) {
                bArr2[i6] = c1323b.f2661a[i6 / i];
            }
            bArr[i5] = bArr2;
        }
        return bArr;
    }
}
