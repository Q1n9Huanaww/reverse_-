package com.google.p028a.p051h.p055c;

/* JADX INFO: renamed from: com.google.a.h.c.b */
/* JADX INFO: compiled from: BarcodeRow.java */
/* JADX INFO: loaded from: classes.dex */
final class C1323b {

    /* JADX INFO: renamed from: a */
    final byte[] f2661a;

    /* JADX INFO: renamed from: b */
    private int f2662b = 0;

    C1323b(int i) {
        this.f2661a = new byte[i];
    }

    /* JADX INFO: renamed from: a */
    private void m2383a(int i, byte b) {
        this.f2661a[i] = b;
    }

    /* JADX INFO: renamed from: a */
    private void m2384a(int i, boolean z) {
        this.f2661a[i] = z ? (byte) 1 : (byte) 0;
    }

    /* JADX INFO: renamed from: a */
    final void m2386a(boolean z, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = this.f2662b;
            this.f2662b = i3 + 1;
            this.f2661a[i3] = z ? (byte) 1 : (byte) 0;
        }
    }

    /* JADX INFO: renamed from: a */
    private byte[] m2385a(int i) {
        byte[] bArr = new byte[this.f2661a.length * i];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = this.f2661a[i2 / i];
        }
        return bArr;
    }
}
