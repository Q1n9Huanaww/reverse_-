package com.umeng.commonsdk.proguard;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.as */
/* JADX INFO: compiled from: TBinaryProtocol.java */
/* JADX INFO: loaded from: classes.dex */
public class C1755as extends AbstractC1762az {

    /* JADX INFO: renamed from: a */
    protected static final int f4034a = -65536;

    /* JADX INFO: renamed from: b */
    protected static final int f4035b = -2147418112;

    /* JADX INFO: renamed from: h */
    private static final C1768be f4036h = new C1768be();

    /* JADX INFO: renamed from: c */
    protected boolean f4037c;

    /* JADX INFO: renamed from: d */
    protected boolean f4038d;

    /* JADX INFO: renamed from: e */
    protected int f4039e;

    /* JADX INFO: renamed from: f */
    protected boolean f4040f;

    /* JADX INFO: renamed from: i */
    private byte[] f4041i;

    /* JADX INFO: renamed from: j */
    private byte[] f4042j;

    /* JADX INFO: renamed from: k */
    private byte[] f4043k;

    /* JADX INFO: renamed from: l */
    private byte[] f4044l;

    /* JADX INFO: renamed from: m */
    private byte[] f4045m;

    /* JADX INFO: renamed from: n */
    private byte[] f4046n;

    /* JADX INFO: renamed from: o */
    private byte[] f4047o;

    /* JADX INFO: renamed from: p */
    private byte[] f4048p;

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: a */
    public void mo3850a() {
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: a */
    public void mo3860a(C1768be c1768be) {
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: b */
    public void mo3866b() {
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: c */
    public void mo3867c() {
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: e */
    public void mo3871e() {
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: f */
    public void mo3872f() {
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: g */
    public void mo3873g() {
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: i */
    public void mo3875i() {
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: k */
    public void mo3877k() {
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: m */
    public void mo3879m() {
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: o */
    public void mo3881o() {
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: q */
    public void mo3883q() {
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: s */
    public void mo3885s() {
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.proguard.as$a */
    /* JADX INFO: compiled from: TBinaryProtocol.java */
    public static class a implements InterfaceC1765bb {

        /* JADX INFO: renamed from: a */
        protected boolean f4049a;

        /* JADX INFO: renamed from: b */
        protected boolean f4050b;

        /* JADX INFO: renamed from: c */
        protected int f4051c;

        public a() {
            this(false, true);
        }

        public a(boolean z, boolean z2) {
            this(z, z2, 0);
        }

        public a(boolean z, boolean z2, int i) {
            this.f4049a = false;
            this.f4050b = true;
            this.f4049a = z;
            this.f4050b = z2;
            this.f4051c = i;
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1765bb
        /* JADX INFO: renamed from: a */
        public AbstractC1762az mo3893a(AbstractC1777bn abstractC1777bn) {
            C1755as c1755as = new C1755as(abstractC1777bn, this.f4049a, this.f4050b);
            if (this.f4051c != 0) {
                c1755as.m3868c(this.f4051c);
            }
            return c1755as;
        }
    }

    public C1755as(AbstractC1777bn abstractC1777bn) {
        this(abstractC1777bn, false, true);
    }

    public C1755as(AbstractC1777bn abstractC1777bn, boolean z, boolean z2) {
        super(abstractC1777bn);
        this.f4037c = false;
        this.f4038d = true;
        this.f4040f = false;
        this.f4041i = new byte[1];
        this.f4042j = new byte[2];
        this.f4043k = new byte[4];
        this.f4044l = new byte[8];
        this.f4045m = new byte[1];
        this.f4046n = new byte[2];
        this.f4047o = new byte[4];
        this.f4048p = new byte[8];
        this.f4037c = z;
        this.f4038d = z2;
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: a */
    public void mo3858a(C1760ax c1760ax) throws C1743ag {
        if (this.f4038d) {
            mo3853a(f4035b | c1760ax.f4091b);
            mo3861a(c1760ax.f4090a);
            mo3853a(c1760ax.f4092c);
        } else {
            mo3861a(c1760ax.f4090a);
            mo3851a(c1760ax.f4091b);
            mo3853a(c1760ax.f4092c);
        }
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: a */
    public void mo3855a(C1757au c1757au) throws C1743ag {
        mo3851a(c1757au.f4083b);
        mo3863a(c1757au.f4084c);
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: d */
    public void mo3869d() throws C1743ag {
        mo3851a((byte) 0);
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: a */
    public void mo3857a(C1759aw c1759aw) throws C1743ag {
        mo3851a(c1759aw.f4087a);
        mo3851a(c1759aw.f4088b);
        mo3853a(c1759aw.f4089c);
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: a */
    public void mo3856a(C1758av c1758av) throws C1743ag {
        mo3851a(c1758av.f4085a);
        mo3853a(c1758av.f4086b);
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: a */
    public void mo3859a(C1767bd c1767bd) throws C1743ag {
        mo3851a(c1767bd.f4110a);
        mo3853a(c1767bd.f4111b);
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: a */
    public void mo3864a(boolean z) throws C1743ag {
        mo3851a(z ? (byte) 1 : (byte) 0);
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: a */
    public void mo3851a(byte b) throws C1743ag {
        this.f4041i[0] = b;
        this.f4097g.mo3933b(this.f4041i, 0, 1);
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: a */
    public void mo3863a(short s) throws C1743ag {
        this.f4042j[0] = (byte) ((s >> 8) & 255);
        this.f4042j[1] = (byte) (s & 255);
        this.f4097g.mo3933b(this.f4042j, 0, 2);
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: a */
    public void mo3853a(int i) throws C1743ag {
        this.f4043k[0] = (byte) ((i >> 24) & 255);
        this.f4043k[1] = (byte) ((i >> 16) & 255);
        this.f4043k[2] = (byte) ((i >> 8) & 255);
        this.f4043k[3] = (byte) (i & 255);
        this.f4097g.mo3933b(this.f4043k, 0, 4);
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: a */
    public void mo3854a(long j) throws C1743ag {
        this.f4044l[0] = (byte) ((j >> 56) & 255);
        this.f4044l[1] = (byte) ((j >> 48) & 255);
        this.f4044l[2] = (byte) ((j >> 40) & 255);
        this.f4044l[3] = (byte) ((j >> 32) & 255);
        this.f4044l[4] = (byte) ((j >> 24) & 255);
        this.f4044l[5] = (byte) ((j >> 16) & 255);
        this.f4044l[6] = (byte) ((j >> 8) & 255);
        this.f4044l[7] = (byte) (j & 255);
        this.f4097g.mo3933b(this.f4044l, 0, 8);
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: a */
    public void mo3852a(double d) throws C1743ag {
        mo3854a(Double.doubleToLongBits(d));
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: a */
    public void mo3861a(String str) throws C1743ag {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            mo3853a(bytes.length);
            this.f4097g.mo3933b(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException unused) {
            throw new C1743ag("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: a */
    public void mo3862a(ByteBuffer byteBuffer) throws C1743ag {
        int iLimit = byteBuffer.limit() - byteBuffer.position();
        mo3853a(iLimit);
        this.f4097g.mo3933b(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), iLimit);
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: h */
    public C1760ax mo3874h() throws C1743ag {
        int iMo3889w = mo3889w();
        if (iMo3889w < 0) {
            if (((-65536) & iMo3889w) != f4035b) {
                throw new C1764ba(4, "Bad version in readMessageBegin");
            }
            return new C1760ax(mo3892z(), (byte) (iMo3889w & 255), mo3889w());
        }
        if (this.f4037c) {
            throw new C1764ba(4, "Missing version in readMessageBegin, old client?");
        }
        return new C1760ax(m3865b(iMo3889w), mo3887u(), mo3889w());
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: j */
    public C1768be mo3876j() {
        return f4036h;
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: l */
    public C1757au mo3878l() throws C1743ag {
        byte bMo3887u = mo3887u();
        return new C1757au("", bMo3887u, bMo3887u == 0 ? (short) 0 : mo3888v());
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: n */
    public C1759aw mo3880n() throws C1743ag {
        return new C1759aw(mo3887u(), mo3887u(), mo3889w());
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: p */
    public C1758av mo3882p() throws C1743ag {
        return new C1758av(mo3887u(), mo3889w());
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: r */
    public C1767bd mo3884r() throws C1743ag {
        return new C1767bd(mo3887u(), mo3889w());
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: t */
    public boolean mo3886t() throws C1743ag {
        return mo3887u() == 1;
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: u */
    public byte mo3887u() throws C1743ag {
        if (this.f4097g.mo3942h() >= 1) {
            byte b = this.f4097g.mo3940f()[this.f4097g.mo3941g()];
            this.f4097g.mo3936a(1);
            return b;
        }
        m3848a(this.f4045m, 0, 1);
        return this.f4045m[0];
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: v */
    public short mo3888v() throws C1743ag {
        byte[] bArrMo3940f = this.f4046n;
        int iMo3941g = 0;
        if (this.f4097g.mo3942h() >= 2) {
            bArrMo3940f = this.f4097g.mo3940f();
            iMo3941g = this.f4097g.mo3941g();
            this.f4097g.mo3936a(2);
        } else {
            m3848a(this.f4046n, 0, 2);
        }
        return (short) ((bArrMo3940f[iMo3941g + 1] & 255) | ((bArrMo3940f[iMo3941g] & 255) << 8));
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: w */
    public int mo3889w() throws C1743ag {
        byte[] bArrMo3940f = this.f4047o;
        int iMo3941g = 0;
        if (this.f4097g.mo3942h() >= 4) {
            bArrMo3940f = this.f4097g.mo3940f();
            iMo3941g = this.f4097g.mo3941g();
            this.f4097g.mo3936a(4);
        } else {
            m3848a(this.f4047o, 0, 4);
        }
        return (bArrMo3940f[iMo3941g + 3] & 255) | ((bArrMo3940f[iMo3941g] & 255) << 24) | ((bArrMo3940f[iMo3941g + 1] & 255) << 16) | ((bArrMo3940f[iMo3941g + 2] & 255) << 8);
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: x */
    public long mo3890x() throws C1743ag {
        byte[] bArrMo3940f = this.f4048p;
        int iMo3941g = 0;
        if (this.f4097g.mo3942h() >= 8) {
            bArrMo3940f = this.f4097g.mo3940f();
            iMo3941g = this.f4097g.mo3941g();
            this.f4097g.mo3936a(8);
        } else {
            m3848a(this.f4048p, 0, 8);
        }
        return (((long) (bArrMo3940f[iMo3941g] & 255)) << 56) | (((long) (bArrMo3940f[iMo3941g + 1] & 255)) << 48) | (((long) (bArrMo3940f[iMo3941g + 2] & 255)) << 40) | (((long) (bArrMo3940f[iMo3941g + 3] & 255)) << 32) | (((long) (bArrMo3940f[iMo3941g + 4] & 255)) << 24) | (((long) (bArrMo3940f[iMo3941g + 5] & 255)) << 16) | (((long) (bArrMo3940f[iMo3941g + 6] & 255)) << 8) | ((long) (bArrMo3940f[iMo3941g + 7] & 255));
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: y */
    public double mo3891y() throws C1743ag {
        return Double.longBitsToDouble(mo3890x());
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: z */
    public String mo3892z() throws C1743ag {
        int iMo3889w = mo3889w();
        if (this.f4097g.mo3942h() >= iMo3889w) {
            try {
                String str = new String(this.f4097g.mo3940f(), this.f4097g.mo3941g(), iMo3889w, "UTF-8");
                this.f4097g.mo3936a(iMo3889w);
                return str;
            } catch (UnsupportedEncodingException unused) {
                throw new C1743ag("JVM DOES NOT SUPPORT UTF-8");
            }
        }
        return m3865b(iMo3889w);
    }

    /* JADX INFO: renamed from: b */
    public String m3865b(int i) throws C1743ag {
        try {
            m3870d(i);
            byte[] bArr = new byte[i];
            this.f4097g.m3944d(bArr, 0, i);
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            throw new C1743ag("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: A */
    public ByteBuffer mo3849A() throws C1743ag {
        int iMo3889w = mo3889w();
        m3870d(iMo3889w);
        if (this.f4097g.mo3942h() >= iMo3889w) {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(this.f4097g.mo3940f(), this.f4097g.mo3941g(), iMo3889w);
            this.f4097g.mo3936a(iMo3889w);
            return byteBufferWrap;
        }
        byte[] bArr = new byte[iMo3889w];
        this.f4097g.m3944d(bArr, 0, iMo3889w);
        return ByteBuffer.wrap(bArr);
    }

    /* JADX INFO: renamed from: a */
    private int m3848a(byte[] bArr, int i, int i2) throws C1743ag {
        m3870d(i2);
        return this.f4097g.m3944d(bArr, i, i2);
    }

    /* JADX INFO: renamed from: c */
    public void m3868c(int i) {
        this.f4039e = i;
        this.f4040f = true;
    }

    /* JADX INFO: renamed from: d */
    protected void m3870d(int i) throws C1743ag {
        if (i < 0) {
            throw new C1764ba("Negative length: " + i);
        }
        if (this.f4040f) {
            this.f4039e -= i;
            if (this.f4039e < 0) {
                throw new C1764ba("Message length exceeded: " + i);
            }
        }
    }
}
