package com.google.p028a;

/* JADX INFO: renamed from: com.google.a.i */
/* JADX INFO: compiled from: InvertedLuminanceSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1330i extends AbstractC1355j {

    /* JADX INFO: renamed from: c */
    private final AbstractC1355j f2710c;

    @Override // com.google.p028a.AbstractC1355j
    /* JADX INFO: renamed from: a */
    public final byte[] mo2424a(int i, byte[] bArr) {
        byte[] bArrMo2424a = this.f2710c.mo2424a(i, bArr);
        int i2 = this.f2820a;
        for (int i3 = 0; i3 < i2; i3++) {
            bArrMo2424a[i3] = (byte) (255 - (bArrMo2424a[i3] & 255));
        }
        return bArrMo2424a;
    }

    @Override // com.google.p028a.AbstractC1355j
    /* JADX INFO: renamed from: a */
    public final byte[] mo2423a() {
        byte[] bArrMo2423a = this.f2710c.mo2423a();
        int i = this.f2820a * this.f2821b;
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) (255 - (bArrMo2423a[i2] & 255));
        }
        return bArr;
    }

    @Override // com.google.p028a.AbstractC1355j
    /* JADX INFO: renamed from: b */
    public final boolean mo2425b() {
        return this.f2710c.mo2425b();
    }

    @Override // com.google.p028a.AbstractC1355j
    /* JADX INFO: renamed from: a */
    public final AbstractC1355j mo2422a(int i, int i2, int i3, int i4) {
        return new C1330i(this.f2710c.mo2422a(i, i2, i3, i4));
    }

    @Override // com.google.p028a.AbstractC1355j
    /* JADX INFO: renamed from: c */
    public final boolean mo2426c() {
        return this.f2710c.mo2426c();
    }

    @Override // com.google.p028a.AbstractC1355j
    /* JADX INFO: renamed from: d */
    public final AbstractC1355j mo2427d() {
        return this.f2710c;
    }

    @Override // com.google.p028a.AbstractC1355j
    /* JADX INFO: renamed from: e */
    public final AbstractC1355j mo2428e() {
        return new C1330i(this.f2710c.mo2428e());
    }

    @Override // com.google.p028a.AbstractC1355j
    /* JADX INFO: renamed from: f */
    public final AbstractC1355j mo2429f() {
        return new C1330i(this.f2710c.mo2429f());
    }

    public C1330i(AbstractC1355j abstractC1355j) {
        super(abstractC1355j.f2820a, abstractC1355j.f2821b);
        this.f2710c = abstractC1355j;
    }
}
