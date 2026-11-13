package com.umeng.commonsdk.proguard;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.bm */
/* JADX INFO: compiled from: TMemoryInputTransport.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1776bm extends AbstractC1777bn {

    /* JADX INFO: renamed from: a */
    private byte[] f4129a;

    /* JADX INFO: renamed from: b */
    private int f4130b;

    /* JADX INFO: renamed from: c */
    private int f4131c;

    @Override // com.umeng.commonsdk.proguard.AbstractC1777bn
    /* JADX INFO: renamed from: a */
    public boolean mo3931a() {
        return true;
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1777bn
    /* JADX INFO: renamed from: b */
    public void mo3932b() throws C1778bo {
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1777bn
    /* JADX INFO: renamed from: c */
    public void mo3934c() {
    }

    public C1776bm() {
    }

    public C1776bm(byte[] bArr) {
        m3937a(bArr);
    }

    public C1776bm(byte[] bArr, int i, int i2) {
        m3938c(bArr, i, i2);
    }

    /* JADX INFO: renamed from: a */
    public void m3937a(byte[] bArr) {
        m3938c(bArr, 0, bArr.length);
    }

    /* JADX INFO: renamed from: c */
    public void m3938c(byte[] bArr, int i, int i2) {
        this.f4129a = bArr;
        this.f4130b = i;
        this.f4131c = i + i2;
    }

    /* JADX INFO: renamed from: e */
    public void m3939e() {
        this.f4129a = null;
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1777bn
    /* JADX INFO: renamed from: a */
    public int mo3930a(byte[] bArr, int i, int i2) throws C1778bo {
        int iMo3942h = mo3942h();
        if (i2 > iMo3942h) {
            i2 = iMo3942h;
        }
        if (i2 > 0) {
            System.arraycopy(this.f4129a, this.f4130b, bArr, i, i2);
            mo3936a(i2);
        }
        return i2;
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1777bn
    /* JADX INFO: renamed from: b */
    public void mo3933b(byte[] bArr, int i, int i2) throws C1778bo {
        throw new UnsupportedOperationException("No writing allowed!");
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1777bn
    /* JADX INFO: renamed from: f */
    public byte[] mo3940f() {
        return this.f4129a;
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1777bn
    /* JADX INFO: renamed from: g */
    public int mo3941g() {
        return this.f4130b;
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1777bn
    /* JADX INFO: renamed from: h */
    public int mo3942h() {
        return this.f4131c - this.f4130b;
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1777bn
    /* JADX INFO: renamed from: a */
    public void mo3936a(int i) {
        this.f4130b += i;
    }
}
