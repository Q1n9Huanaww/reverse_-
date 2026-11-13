package com.umeng.commonsdk.proguard;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.at */
/* JADX INFO: compiled from: TCompactProtocol.java */
/* JADX INFO: loaded from: classes.dex */
public class C1756at extends AbstractC1762az {

    /* JADX INFO: renamed from: d */
    private static final C1768be f4052d = new C1768be("");

    /* JADX INFO: renamed from: e */
    private static final C1757au f4053e = new C1757au("", (byte) 0, 0);

    /* JADX INFO: renamed from: f */
    private static final byte[] f4054f = new byte[16];

    /* JADX INFO: renamed from: h */
    private static final byte f4055h = -126;

    /* JADX INFO: renamed from: i */
    private static final byte f4056i = 1;

    /* JADX INFO: renamed from: j */
    private static final byte f4057j = 31;

    /* JADX INFO: renamed from: k */
    private static final byte f4058k = -32;

    /* JADX INFO: renamed from: l */
    private static final int f4059l = 5;

    /* JADX INFO: renamed from: a */
    byte[] f4060a;

    /* JADX INFO: renamed from: b */
    byte[] f4061b;

    /* JADX INFO: renamed from: c */
    byte[] f4062c;

    /* JADX INFO: renamed from: m */
    private C1802y f4063m;

    /* JADX INFO: renamed from: n */
    private short f4064n;

    /* JADX INFO: renamed from: o */
    private C1757au f4065o;

    /* JADX INFO: renamed from: p */
    private Boolean f4066p;

    /* JADX INFO: renamed from: q */
    private final long f4067q;

    /* JADX INFO: renamed from: r */
    private byte[] f4068r;

    /* JADX INFO: renamed from: c */
    private int m3903c(int i) {
        return (i >> 31) ^ (i << 1);
    }

    /* JADX INFO: renamed from: c */
    private long m3904c(long j) {
        return (j << 1) ^ (j >> 63);
    }

    /* JADX INFO: renamed from: c */
    private boolean m3905c(byte b2) {
        int i = b2 & C1770bg.f4125m;
        return i == 1 || i == 2;
    }

    /* JADX INFO: renamed from: d */
    private long m3907d(long j) {
        return (j >>> 1) ^ (-(j & 1));
    }

    /* JADX INFO: renamed from: g */
    private int m3912g(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: a */
    public void mo3850a() throws C1743ag {
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: c */
    public void mo3867c() throws C1743ag {
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: e */
    public void mo3871e() throws C1743ag {
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: f */
    public void mo3872f() throws C1743ag {
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: g */
    public void mo3873g() throws C1743ag {
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: i */
    public void mo3875i() throws C1743ag {
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: m */
    public void mo3879m() throws C1743ag {
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: o */
    public void mo3881o() throws C1743ag {
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: q */
    public void mo3883q() throws C1743ag {
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: s */
    public void mo3885s() throws C1743ag {
    }

    static {
        f4054f[0] = 0;
        f4054f[2] = 1;
        f4054f[3] = 3;
        f4054f[6] = 4;
        f4054f[8] = 5;
        f4054f[10] = 6;
        f4054f[4] = 7;
        f4054f[11] = 8;
        f4054f[15] = 9;
        f4054f[14] = 10;
        f4054f[13] = 11;
        f4054f[12] = 12;
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.proguard.at$a */
    /* JADX INFO: compiled from: TCompactProtocol.java */
    public static class a implements InterfaceC1765bb {

        /* JADX INFO: renamed from: a */
        private final long f4069a;

        public a() {
            this.f4069a = -1L;
        }

        public a(int i) {
            this.f4069a = i;
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1765bb
        /* JADX INFO: renamed from: a */
        public AbstractC1762az mo3893a(AbstractC1777bn abstractC1777bn) {
            return new C1756at(abstractC1777bn, this.f4069a);
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.proguard.at$b */
    /* JADX INFO: compiled from: TCompactProtocol.java */
    private static class b {

        /* JADX INFO: renamed from: a */
        public static final byte f4070a = 1;

        /* JADX INFO: renamed from: b */
        public static final byte f4071b = 2;

        /* JADX INFO: renamed from: c */
        public static final byte f4072c = 3;

        /* JADX INFO: renamed from: d */
        public static final byte f4073d = 4;

        /* JADX INFO: renamed from: e */
        public static final byte f4074e = 5;

        /* JADX INFO: renamed from: f */
        public static final byte f4075f = 6;

        /* JADX INFO: renamed from: g */
        public static final byte f4076g = 7;

        /* JADX INFO: renamed from: h */
        public static final byte f4077h = 8;

        /* JADX INFO: renamed from: i */
        public static final byte f4078i = 9;

        /* JADX INFO: renamed from: j */
        public static final byte f4079j = 10;

        /* JADX INFO: renamed from: k */
        public static final byte f4080k = 11;

        /* JADX INFO: renamed from: l */
        public static final byte f4081l = 12;

        private b() {
        }
    }

    public C1756at(AbstractC1777bn abstractC1777bn, long j) {
        super(abstractC1777bn);
        this.f4063m = new C1802y(15);
        this.f4064n = (short) 0;
        this.f4065o = null;
        this.f4066p = null;
        this.f4060a = new byte[5];
        this.f4061b = new byte[10];
        this.f4068r = new byte[1];
        this.f4062c = new byte[1];
        this.f4067q = j;
    }

    public C1756at(AbstractC1777bn abstractC1777bn) {
        this(abstractC1777bn, -1L);
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: B */
    public void mo3913B() {
        this.f4063m.m4207c();
        this.f4064n = (short) 0;
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: a */
    public void mo3858a(C1760ax c1760ax) throws C1743ag {
        m3900b(f4055h);
        m3908d(((c1760ax.f4091b << 5) & (-32)) | 1);
        m3901b(c1760ax.f4092c);
        mo3861a(c1760ax.f4090a);
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: a */
    public void mo3860a(C1768be c1768be) throws C1743ag {
        this.f4063m.m4205a(this.f4064n);
        this.f4064n = (short) 0;
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: b */
    public void mo3866b() throws C1743ag {
        this.f4064n = this.f4063m.m4204a();
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: a */
    public void mo3855a(C1757au c1757au) throws C1743ag {
        if (c1757au.f4083b == 2) {
            this.f4065o = c1757au;
        } else {
            m3898a(c1757au, (byte) -1);
        }
    }

    /* JADX INFO: renamed from: a */
    private void m3898a(C1757au c1757au, byte b2) throws C1743ag {
        if (b2 == -1) {
            b2 = m3909e(c1757au.f4083b);
        }
        if (c1757au.f4084c > this.f4064n && c1757au.f4084c - this.f4064n <= 15) {
            m3908d(b2 | ((c1757au.f4084c - this.f4064n) << 4));
        } else {
            m3900b(b2);
            mo3863a(c1757au.f4084c);
        }
        this.f4064n = c1757au.f4084c;
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: d */
    public void mo3869d() throws C1743ag {
        m3900b((byte) 0);
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: a */
    public void mo3857a(C1759aw c1759aw) throws C1743ag {
        if (c1759aw.f4089c == 0) {
            m3908d(0);
            return;
        }
        m3901b(c1759aw.f4089c);
        m3908d(m3909e(c1759aw.f4088b) | (m3909e(c1759aw.f4087a) << 4));
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: a */
    public void mo3856a(C1758av c1758av) throws C1743ag {
        m3914a(c1758av.f4085a, c1758av.f4086b);
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: a */
    public void mo3859a(C1767bd c1767bd) throws C1743ag {
        m3914a(c1767bd.f4110a, c1767bd.f4111b);
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: a */
    public void mo3864a(boolean z) throws C1743ag {
        if (this.f4065o != null) {
            m3898a(this.f4065o, z ? (byte) 1 : (byte) 2);
            this.f4065o = null;
        } else {
            m3900b(z ? (byte) 1 : (byte) 2);
        }
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: a */
    public void mo3851a(byte b2) throws C1743ag {
        m3900b(b2);
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: a */
    public void mo3863a(short s) throws C1743ag {
        m3901b(m3903c((int) s));
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: a */
    public void mo3853a(int i) throws C1743ag {
        m3901b(m3903c(i));
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: a */
    public void mo3854a(long j) throws C1743ag {
        m3902b(m3904c(j));
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: a */
    public void mo3852a(double d) throws C1743ag {
        byte[] bArr = {0, 0, 0, 0, 0, 0, 0, 0};
        m3897a(Double.doubleToLongBits(d), bArr, 0);
        this.f4097g.m3943b(bArr);
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: a */
    public void mo3861a(String str) throws C1743ag {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            m3899a(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException unused) {
            throw new C1743ag("UTF-8 not supported!");
        }
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: a */
    public void mo3862a(ByteBuffer byteBuffer) throws C1743ag {
        m3899a(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), byteBuffer.limit() - byteBuffer.position());
    }

    /* JADX INFO: renamed from: a */
    private void m3899a(byte[] bArr, int i, int i2) throws C1743ag {
        m3901b(i2);
        this.f4097g.mo3933b(bArr, i, i2);
    }

    /* JADX INFO: renamed from: a */
    protected void m3914a(byte b2, int i) throws C1743ag {
        if (i <= 14) {
            m3908d(m3909e(b2) | (i << 4));
        } else {
            m3908d(m3909e(b2) | 240);
            m3901b(i);
        }
    }

    /* JADX INFO: renamed from: b */
    private void m3901b(int i) throws C1743ag {
        int i2 = 0;
        while ((i & (-128)) != 0) {
            this.f4060a[i2] = (byte) ((i & 127) | 128);
            i >>>= 7;
            i2++;
        }
        this.f4060a[i2] = (byte) i;
        this.f4097g.mo3933b(this.f4060a, 0, i2 + 1);
    }

    /* JADX INFO: renamed from: b */
    private void m3902b(long j) throws C1743ag {
        int i = 0;
        while ((j & (-128)) != 0) {
            this.f4061b[i] = (byte) ((j & 127) | 128);
            j >>>= 7;
            i++;
        }
        this.f4061b[i] = (byte) j;
        this.f4097g.mo3933b(this.f4061b, 0, i + 1);
    }

    /* JADX INFO: renamed from: a */
    private void m3897a(long j, byte[] bArr, int i) {
        bArr[i + 0] = (byte) (j & 255);
        bArr[i + 1] = (byte) ((j >> 8) & 255);
        bArr[i + 2] = (byte) ((j >> 16) & 255);
        bArr[i + 3] = (byte) ((j >> 24) & 255);
        bArr[i + 4] = (byte) ((j >> 32) & 255);
        bArr[i + 5] = (byte) ((j >> 40) & 255);
        bArr[i + 6] = (byte) ((j >> 48) & 255);
        bArr[i + 7] = (byte) ((j >> 56) & 255);
    }

    /* JADX INFO: renamed from: b */
    private void m3900b(byte b2) throws C1743ag {
        this.f4068r[0] = b2;
        this.f4097g.m3943b(this.f4068r);
    }

    /* JADX INFO: renamed from: d */
    private void m3908d(int i) throws C1743ag {
        m3900b((byte) i);
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: h */
    public C1760ax mo3874h() throws C1743ag {
        byte bMo3887u = mo3887u();
        if (bMo3887u != -126) {
            throw new C1764ba("Expected protocol id " + Integer.toHexString(-126) + " but got " + Integer.toHexString(bMo3887u));
        }
        byte bMo3887u2 = mo3887u();
        byte b2 = (byte) (bMo3887u2 & f4057j);
        if (b2 != 1) {
            throw new C1764ba("Expected version 1 but got " + ((int) b2));
        }
        return new C1760ax(mo3892z(), (byte) ((bMo3887u2 >> 5) & 3), m3894E());
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: j */
    public C1768be mo3876j() throws C1743ag {
        this.f4063m.m4205a(this.f4064n);
        this.f4064n = (short) 0;
        return f4052d;
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: k */
    public void mo3877k() throws C1743ag {
        this.f4064n = this.f4063m.m4204a();
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: l */
    public C1757au mo3878l() throws C1743ag {
        short sMo3888v;
        byte bMo3887u = mo3887u();
        if (bMo3887u == 0) {
            return f4053e;
        }
        short s = (short) ((bMo3887u & 240) >> 4);
        if (s == 0) {
            sMo3888v = mo3888v();
        } else {
            sMo3888v = (short) (this.f4064n + s);
        }
        byte b2 = (byte) (bMo3887u & C1770bg.f4125m);
        C1757au c1757au = new C1757au("", m3906d(b2), sMo3888v);
        if (m3905c(bMo3887u)) {
            this.f4066p = b2 == 1 ? Boolean.TRUE : Boolean.FALSE;
        }
        this.f4064n = c1757au.f4084c;
        return c1757au;
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: n */
    public C1759aw mo3880n() throws C1743ag {
        int iM3894E = m3894E();
        byte bMo3887u = iM3894E == 0 ? (byte) 0 : mo3887u();
        return new C1759aw(m3906d((byte) (bMo3887u >> 4)), m3906d((byte) (bMo3887u & C1770bg.f4125m)), iM3894E);
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: p */
    public C1758av mo3882p() throws C1743ag {
        byte bMo3887u = mo3887u();
        int iM3894E = (bMo3887u >> 4) & 15;
        if (iM3894E == 15) {
            iM3894E = m3894E();
        }
        return new C1758av(m3906d(bMo3887u), iM3894E);
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: r */
    public C1767bd mo3884r() throws C1743ag {
        return new C1767bd(mo3882p());
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: t */
    public boolean mo3886t() throws C1743ag {
        if (this.f4066p == null) {
            return mo3887u() == 1;
        }
        boolean zBooleanValue = this.f4066p.booleanValue();
        this.f4066p = null;
        return zBooleanValue;
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: u */
    public byte mo3887u() throws C1743ag {
        if (this.f4097g.mo3942h() > 0) {
            byte b2 = this.f4097g.mo3940f()[this.f4097g.mo3941g()];
            this.f4097g.mo3936a(1);
            return b2;
        }
        this.f4097g.m3944d(this.f4062c, 0, 1);
        return this.f4062c[0];
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: v */
    public short mo3888v() throws C1743ag {
        return (short) m3912g(m3894E());
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: w */
    public int mo3889w() throws C1743ag {
        return m3912g(m3894E());
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: x */
    public long mo3890x() throws C1743ag {
        return m3907d(m3895F());
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: y */
    public double mo3891y() throws C1743ag {
        byte[] bArr = new byte[8];
        this.f4097g.m3944d(bArr, 0, 8);
        return Double.longBitsToDouble(m3896a(bArr));
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: z */
    public String mo3892z() throws C1743ag {
        int iM3894E = m3894E();
        m3911f(iM3894E);
        if (iM3894E == 0) {
            return "";
        }
        try {
            if (this.f4097g.mo3942h() >= iM3894E) {
                String str = new String(this.f4097g.mo3940f(), this.f4097g.mo3941g(), iM3894E, "UTF-8");
                this.f4097g.mo3936a(iM3894E);
                return str;
            }
            return new String(m3910e(iM3894E), "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            throw new C1743ag("UTF-8 not supported!");
        }
    }

    @Override // com.umeng.commonsdk.proguard.AbstractC1762az
    /* JADX INFO: renamed from: A */
    public ByteBuffer mo3849A() throws C1743ag {
        int iM3894E = m3894E();
        m3911f(iM3894E);
        if (iM3894E == 0) {
            return ByteBuffer.wrap(new byte[0]);
        }
        byte[] bArr = new byte[iM3894E];
        this.f4097g.m3944d(bArr, 0, iM3894E);
        return ByteBuffer.wrap(bArr);
    }

    /* JADX INFO: renamed from: e */
    private byte[] m3910e(int i) throws C1743ag {
        if (i == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[i];
        this.f4097g.m3944d(bArr, 0, i);
        return bArr;
    }

    /* JADX INFO: renamed from: f */
    private void m3911f(int i) throws C1764ba {
        if (i < 0) {
            throw new C1764ba("Negative length: " + i);
        }
        if (this.f4067q == -1 || i <= this.f4067q) {
            return;
        }
        throw new C1764ba("Length exceeded max allowed: " + i);
    }

    /* JADX INFO: renamed from: E */
    private int m3894E() throws C1743ag {
        int i = 0;
        if (this.f4097g.mo3942h() >= 5) {
            byte[] bArrMo3940f = this.f4097g.mo3940f();
            int iMo3941g = this.f4097g.mo3941g();
            int i2 = 0;
            int i3 = 0;
            while (true) {
                byte b2 = bArrMo3940f[iMo3941g + i];
                i2 |= (b2 & 127) << i3;
                if ((b2 & 128) != 128) {
                    this.f4097g.mo3936a(i + 1);
                    return i2;
                }
                i3 += 7;
                i++;
            }
        } else {
            int i4 = 0;
            while (true) {
                byte bMo3887u = mo3887u();
                i |= (bMo3887u & 127) << i4;
                if ((bMo3887u & 128) != 128) {
                    return i;
                }
                i4 += 7;
            }
        }
    }

    /* JADX INFO: renamed from: F */
    private long m3895F() throws C1743ag {
        int i = 0;
        long j = 0;
        if (this.f4097g.mo3942h() >= 10) {
            byte[] bArrMo3940f = this.f4097g.mo3940f();
            int iMo3941g = this.f4097g.mo3941g();
            int i2 = 0;
            while (true) {
                byte b2 = bArrMo3940f[iMo3941g + i];
                long j2 = j | (((long) (b2 & 127)) << i2);
                if ((b2 & 128) != 128) {
                    this.f4097g.mo3936a(i + 1);
                    return j2;
                }
                i2 += 7;
                i++;
                j = j2;
            }
        } else {
            while (true) {
                byte bMo3887u = mo3887u();
                long j3 = j | (((long) (bMo3887u & 127)) << i);
                if ((bMo3887u & 128) != 128) {
                    return j3;
                }
                i += 7;
                j = j3;
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private long m3896a(byte[] bArr) {
        return ((((long) bArr[7]) & 255) << 56) | ((((long) bArr[6]) & 255) << 48) | ((((long) bArr[5]) & 255) << 40) | ((((long) bArr[4]) & 255) << 32) | ((((long) bArr[3]) & 255) << 24) | ((((long) bArr[2]) & 255) << 16) | ((((long) bArr[1]) & 255) << 8) | (((long) bArr[0]) & 255);
    }

    /* JADX INFO: renamed from: d */
    private byte m3906d(byte b2) throws C1764ba {
        byte b3 = (byte) (b2 & C1770bg.f4125m);
        switch (b3) {
            case 0:
                return (byte) 0;
            case 1:
            case 2:
                return (byte) 2;
            case 3:
                return (byte) 3;
            case 4:
                return (byte) 6;
            case 5:
                return (byte) 8;
            case 6:
                return (byte) 10;
            case 7:
                return (byte) 4;
            case 8:
                return (byte) 11;
            case 9:
                return C1770bg.f4125m;
            case 10:
                return C1770bg.f4124l;
            case 11:
                return C1770bg.f4123k;
            case 12:
                return (byte) 12;
            default:
                throw new C1764ba("don't know what type: " + ((int) b3));
        }
    }

    /* JADX INFO: renamed from: e */
    private byte m3909e(byte b2) {
        return f4054f[b2];
    }
}
