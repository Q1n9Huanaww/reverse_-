package com.google.p028a;

/* JADX INFO: renamed from: com.google.a.n */
/* JADX INFO: compiled from: PlanarYUVLuminanceSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1359n extends AbstractC1355j {

    /* JADX INFO: renamed from: c */
    private static final int f2826c = 2;

    /* JADX INFO: renamed from: d */
    private final byte[] f2827d;

    /* JADX INFO: renamed from: e */
    private final int f2828e;

    /* JADX INFO: renamed from: f */
    private final int f2829f;

    /* JADX INFO: renamed from: g */
    private final int f2830g;

    /* JADX INFO: renamed from: h */
    private final int f2831h;

    @Override // com.google.p028a.AbstractC1355j
    /* JADX INFO: renamed from: b */
    public final boolean mo2425b() {
        return true;
    }

    private C1359n(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6) {
        super(i5, i6);
        if (i5 + i3 > i || i6 + i4 > i2) {
            throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
        }
        this.f2827d = bArr;
        this.f2828e = i;
        this.f2829f = i2;
        this.f2830g = i3;
        this.f2831h = i4;
    }

    @Override // com.google.p028a.AbstractC1355j
    /* JADX INFO: renamed from: a */
    public final AbstractC1355j mo2422a(int i, int i2, int i3, int i4) {
        return new C1359n(this.f2827d, this.f2828e, this.f2829f, this.f2830g + i, this.f2831h + i2, i3, i4);
    }

    /* JADX INFO: renamed from: a */
    private void m2612a(int i, int i2) {
        byte[] bArr = this.f2827d;
        int i3 = (this.f2831h * this.f2828e) + this.f2830g;
        int i4 = 0;
        while (i4 < i2) {
            int i5 = (i / 2) + i3;
            int i6 = (i3 + i) - 1;
            int i7 = i3;
            while (i7 < i5) {
                byte b = bArr[i7];
                bArr[i7] = bArr[i6];
                bArr[i6] = b;
                i7++;
                i6--;
            }
            i4++;
            i3 += this.f2828e;
        }
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
        System.arraycopy(this.f2827d, ((i + this.f2831h) * this.f2828e) + this.f2830g, bArr, 0, i2);
        return bArr;
    }

    @Override // com.google.p028a.AbstractC1355j
    /* JADX INFO: renamed from: a */
    public final byte[] mo2423a() {
        int i = this.f2820a;
        int i2 = this.f2821b;
        if (i == this.f2828e && i2 == this.f2829f) {
            return this.f2827d;
        }
        int i3 = i * i2;
        byte[] bArr = new byte[i3];
        int i4 = (this.f2831h * this.f2828e) + this.f2830g;
        if (i == this.f2828e) {
            System.arraycopy(this.f2827d, i4, bArr, 0, i3);
            return bArr;
        }
        for (int i5 = 0; i5 < i2; i5++) {
            System.arraycopy(this.f2827d, i4, bArr, i5 * i, i);
            i4 += this.f2828e;
        }
        return bArr;
    }

    /* JADX INFO: renamed from: g */
    private int[] m2613g() {
        int i = this.f2820a / 2;
        int i2 = this.f2821b / 2;
        int[] iArr = new int[i * i2];
        byte[] bArr = this.f2827d;
        int i3 = (this.f2831h * this.f2828e) + this.f2830g;
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = i4 * i;
            for (int i6 = 0; i6 < i; i6++) {
                iArr[i5 + i6] = ((bArr[(i6 << 1) + i3] & 255) * 65793) | (-16777216);
            }
            i3 += this.f2828e << 1;
        }
        return iArr;
    }

    /* JADX INFO: renamed from: h */
    private int m2614h() {
        return this.f2820a / 2;
    }

    /* JADX INFO: renamed from: i */
    private int m2615i() {
        return this.f2821b / 2;
    }
}
