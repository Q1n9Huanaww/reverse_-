package com.umeng.commonsdk.proguard;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.bn */
/* JADX INFO: compiled from: TTransport.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1777bn {
    /* JADX INFO: renamed from: a */
    public abstract int mo3930a(byte[] bArr, int i, int i2) throws C1778bo;

    /* JADX INFO: renamed from: a */
    public void mo3936a(int i) {
    }

    /* JADX INFO: renamed from: a */
    public abstract boolean mo3931a();

    /* JADX INFO: renamed from: b */
    public abstract void mo3932b() throws C1778bo;

    /* JADX INFO: renamed from: b */
    public abstract void mo3933b(byte[] bArr, int i, int i2) throws C1778bo;

    /* JADX INFO: renamed from: c */
    public abstract void mo3934c();

    /* JADX INFO: renamed from: d */
    public void mo3935d() throws C1778bo {
    }

    /* JADX INFO: renamed from: f */
    public byte[] mo3940f() {
        return null;
    }

    /* JADX INFO: renamed from: g */
    public int mo3941g() {
        return 0;
    }

    /* JADX INFO: renamed from: h */
    public int mo3942h() {
        return -1;
    }

    /* JADX INFO: renamed from: i */
    public boolean m3945i() {
        return mo3931a();
    }

    /* JADX INFO: renamed from: d */
    public int m3944d(byte[] bArr, int i, int i2) throws C1778bo {
        int i3 = 0;
        while (i3 < i2) {
            int iMo3930a = mo3930a(bArr, i + i3, i2 - i3);
            if (iMo3930a <= 0) {
                throw new C1778bo("Cannot read. Remote side has closed. Tried to read " + i2 + " bytes, but only got " + i3 + " bytes. (This is often indicative of an internal error on the server side. Please check your server logs.)");
            }
            i3 += iMo3930a;
        }
        return i3;
    }

    /* JADX INFO: renamed from: b */
    public void m3943b(byte[] bArr) throws C1778bo {
        mo3933b(bArr, 0, bArr.length);
    }
}
