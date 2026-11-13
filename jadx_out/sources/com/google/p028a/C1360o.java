package com.google.p028a;

/* JADX INFO: renamed from: com.google.a.o */
/* JADX INFO: compiled from: RGBLuminanceSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1360o extends AbstractC1355j {

    /* JADX INFO: renamed from: c */
    private final byte[] f2832c;

    /* JADX INFO: renamed from: d */
    private final int f2833d;

    /* JADX INFO: renamed from: e */
    private final int f2834e;

    /* JADX INFO: renamed from: f */
    private final int f2835f;

    /* JADX INFO: renamed from: g */
    private final int f2836g;

    @Override // com.google.p028a.AbstractC1355j
    /* JADX INFO: renamed from: b */
    public final boolean mo2425b() {
        return true;
    }

    public C1360o(int i, int i2, int[] iArr) {
        super(i, i2);
        this.f2833d = i;
        this.f2834e = i2;
        this.f2835f = 0;
        this.f2836g = 0;
        int i3 = i * i2;
        this.f2832c = new byte[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = iArr[i4];
            this.f2832c[i4] = (byte) (((((i5 >> 16) & 255) + ((i5 >> 7) & 510)) + (i5 & 255)) / 4);
        }
    }

    private C1360o(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6) {
        super(i5, i6);
        if (i5 + i3 > i || i6 + i4 > i2) {
            throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
        }
        this.f2832c = bArr;
        this.f2833d = i;
        this.f2834e = i2;
        this.f2835f = i3;
        this.f2836g = i4;
    }

    @Override // com.google.p028a.AbstractC1355j
    /* JADX INFO: renamed from: a */
    public final AbstractC1355j mo2422a(int i, int i2, int i3, int i4) {
        return new C1360o(this.f2832c, this.f2833d, this.f2834e, this.f2835f + i, this.f2836g + i2, i3, i4);
    }

    @Override // com.google.p028a.AbstractC1355j
    /* JADX INFO: renamed from: a */
    public final byte[] mo2424a(int i, byte[] bArr) {
        if (i < 0 || i >= this.f2821b) {
            throw new IllegalArgumentException("Requested row is outside the image: ".concat(String.valueOf(i)));
        }
        int i2 = this.f2820a;
        if (bArr == null || bArr.length < i2) {
            bArr = new byte[i2];
        }
        System.arraycopy(this.f2832c, ((i + this.f2836g) * this.f2833d) + this.f2835f, bArr, 0, i2);
        return bArr;
    }

    @Override // com.google.p028a.AbstractC1355j
    /* JADX INFO: renamed from: a */
    public final byte[] mo2423a() {
        int i = this.f2820a;
        int i2 = this.f2821b;
        if (i == this.f2833d && i2 == this.f2834e) {
            return this.f2832c;
        }
        int i3 = i * i2;
        byte[] bArr = new byte[i3];
        int i4 = (this.f2836g * this.f2833d) + this.f2835f;
        if (i == this.f2833d) {
            System.arraycopy(this.f2832c, i4, bArr, 0, i3);
            return bArr;
        }
        for (int i5 = 0; i5 < i2; i5++) {
            System.arraycopy(this.f2832c, i4, bArr, i5 * i, i);
            i4 += this.f2833d;
        }
        return bArr;
    }
}
