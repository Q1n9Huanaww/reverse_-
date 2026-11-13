package com.google.p028a.p056i.p059c;

import java.lang.reflect.Array;
import java.util.Arrays;

/* JADX INFO: renamed from: com.google.a.i.c.b */
/* JADX INFO: compiled from: ByteMatrix.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1350b {

    /* JADX INFO: renamed from: a */
    final byte[][] f2797a;

    /* JADX INFO: renamed from: b */
    public final int f2798b;

    /* JADX INFO: renamed from: c */
    public final int f2799c;

    public C1350b(int i, int i2) {
        this.f2797a = (byte[][]) Array.newInstance((Class<?>) byte.class, i2, i);
        this.f2798b = i;
        this.f2799c = i2;
    }

    /* JADX INFO: renamed from: a */
    private int m2536a() {
        return this.f2799c;
    }

    /* JADX INFO: renamed from: b */
    private int m2538b() {
        return this.f2798b;
    }

    /* JADX INFO: renamed from: a */
    public final byte m2541a(int i, int i2) {
        return this.f2797a[i2][i];
    }

    /* JADX INFO: renamed from: c */
    private byte[][] m2539c() {
        return this.f2797a;
    }

    /* JADX INFO: renamed from: a */
    private void m2537a(int i, int i2, byte b) {
        this.f2797a[i2][i] = b;
    }

    /* JADX INFO: renamed from: a */
    public final void m2542a(int i, int i2, int i3) {
        this.f2797a[i2][i] = (byte) i3;
    }

    /* JADX INFO: renamed from: a */
    public final void m2543a(int i, int i2, boolean z) {
        this.f2797a[i2][i] = z ? (byte) 1 : (byte) 0;
    }

    /* JADX INFO: renamed from: d */
    private void m2540d() {
        for (byte[] bArr : this.f2797a) {
            Arrays.fill(bArr, (byte) -1);
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder((this.f2798b * 2 * this.f2799c) + 2);
        for (int i = 0; i < this.f2799c; i++) {
            byte[] bArr = this.f2797a[i];
            for (int i2 = 0; i2 < this.f2798b; i2++) {
                switch (bArr[i2]) {
                    case 0:
                        sb.append(" 0");
                        break;
                    case 1:
                        sb.append(" 1");
                        break;
                    default:
                        sb.append("  ");
                        break;
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
