package com.umeng.commonsdk.proguard;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.ad */
/* JADX INFO: compiled from: TDeserializer.java */
/* JADX INFO: loaded from: classes.dex */
public class C1740ad {

    /* JADX INFO: renamed from: a */
    private final AbstractC1762az f4008a;

    /* JADX INFO: renamed from: b */
    private final C1776bm f4009b;

    public C1740ad() {
        this(new C1756at.a());
    }

    public C1740ad(InterfaceC1765bb interfaceC1765bb) {
        this.f4009b = new C1776bm();
        this.f4008a = interfaceC1765bb.mo3893a(this.f4009b);
    }

    /* JADX INFO: renamed from: a */
    public void m3795a(InterfaceC1737aa interfaceC1737aa, byte[] bArr) throws C1743ag {
        try {
            this.f4009b.m3937a(bArr);
            interfaceC1737aa.read(this.f4008a);
        } finally {
            this.f4009b.m3939e();
            this.f4008a.mo3913B();
        }
    }

    /* JADX INFO: renamed from: a */
    public void m3794a(InterfaceC1737aa interfaceC1737aa, String str, String str2) throws C1743ag {
        try {
            try {
                m3795a(interfaceC1737aa, str.getBytes(str2));
                this.f4008a.mo3913B();
            } catch (UnsupportedEncodingException unused) {
                throw new C1743ag("JVM DOES NOT SUPPORT ENCODING: " + str2);
            }
        } catch (Throwable th) {
            this.f4008a.mo3913B();
            throw th;
        }
    }

    /* JADX INFO: renamed from: a */
    public void m3796a(InterfaceC1737aa interfaceC1737aa, byte[] bArr, InterfaceC1744ah interfaceC1744ah, InterfaceC1744ah... interfaceC1744ahArr) throws C1743ag {
        try {
            try {
                if (m3791j(bArr, interfaceC1744ah, interfaceC1744ahArr) != null) {
                    interfaceC1737aa.read(this.f4008a);
                }
                this.f4009b.m3939e();
                this.f4008a.mo3913B();
            } catch (Exception e) {
                throw new C1743ag(e);
            }
        } catch (Throwable th) {
            this.f4009b.m3939e();
            this.f4008a.mo3913B();
            throw th;
        }
    }

    /* JADX INFO: renamed from: a */
    public Boolean m3792a(byte[] bArr, InterfaceC1744ah interfaceC1744ah, InterfaceC1744ah... interfaceC1744ahArr) throws C1743ag {
        return (Boolean) m3790a((byte) 2, bArr, interfaceC1744ah, interfaceC1744ahArr);
    }

    /* JADX INFO: renamed from: b */
    public Byte m3797b(byte[] bArr, InterfaceC1744ah interfaceC1744ah, InterfaceC1744ah... interfaceC1744ahArr) throws C1743ag {
        return (Byte) m3790a((byte) 3, bArr, interfaceC1744ah, interfaceC1744ahArr);
    }

    /* JADX INFO: renamed from: c */
    public Double m3798c(byte[] bArr, InterfaceC1744ah interfaceC1744ah, InterfaceC1744ah... interfaceC1744ahArr) throws C1743ag {
        return (Double) m3790a((byte) 4, bArr, interfaceC1744ah, interfaceC1744ahArr);
    }

    /* JADX INFO: renamed from: d */
    public Short m3799d(byte[] bArr, InterfaceC1744ah interfaceC1744ah, InterfaceC1744ah... interfaceC1744ahArr) throws C1743ag {
        return (Short) m3790a((byte) 6, bArr, interfaceC1744ah, interfaceC1744ahArr);
    }

    /* JADX INFO: renamed from: e */
    public Integer m3800e(byte[] bArr, InterfaceC1744ah interfaceC1744ah, InterfaceC1744ah... interfaceC1744ahArr) throws C1743ag {
        return (Integer) m3790a((byte) 8, bArr, interfaceC1744ah, interfaceC1744ahArr);
    }

    /* JADX INFO: renamed from: f */
    public Long m3801f(byte[] bArr, InterfaceC1744ah interfaceC1744ah, InterfaceC1744ah... interfaceC1744ahArr) throws C1743ag {
        return (Long) m3790a((byte) 10, bArr, interfaceC1744ah, interfaceC1744ahArr);
    }

    /* JADX INFO: renamed from: g */
    public String m3802g(byte[] bArr, InterfaceC1744ah interfaceC1744ah, InterfaceC1744ah... interfaceC1744ahArr) throws C1743ag {
        return (String) m3790a((byte) 11, bArr, interfaceC1744ah, interfaceC1744ahArr);
    }

    /* JADX INFO: renamed from: h */
    public ByteBuffer m3803h(byte[] bArr, InterfaceC1744ah interfaceC1744ah, InterfaceC1744ah... interfaceC1744ahArr) throws C1743ag {
        return (ByteBuffer) m3790a((byte) 100, bArr, interfaceC1744ah, interfaceC1744ahArr);
    }

    /* JADX INFO: renamed from: i */
    public Short m3804i(byte[] bArr, InterfaceC1744ah interfaceC1744ah, InterfaceC1744ah... interfaceC1744ahArr) throws C1743ag {
        Short shValueOf;
        try {
            try {
                if (m3791j(bArr, interfaceC1744ah, interfaceC1744ahArr) != null) {
                    this.f4008a.mo3876j();
                    shValueOf = Short.valueOf(this.f4008a.mo3878l().f4084c);
                } else {
                    shValueOf = null;
                }
                this.f4009b.m3939e();
                this.f4008a.mo3913B();
                return shValueOf;
            } catch (Exception e) {
                throw new C1743ag(e);
            }
        } catch (Throwable th) {
            this.f4009b.m3939e();
            this.f4008a.mo3913B();
            throw th;
        }
    }

    /* JADX WARN: Code duplicated, block: B:40:0x009a  */
    /* JADX INFO: renamed from: a */
    private Object m3790a(byte b, byte[] bArr, InterfaceC1744ah interfaceC1744ah, InterfaceC1744ah... interfaceC1744ahArr) throws C1743ag {
        Object objValueOf;
        try {
            try {
                C1757au c1757auM3791j = m3791j(bArr, interfaceC1744ah, interfaceC1744ahArr);
                if (c1757auM3791j == null) {
                    objValueOf = null;
                } else if (b != 6) {
                    if (b != 8) {
                        if (b != 100) {
                            switch (b) {
                                case 2:
                                    if (c1757auM3791j.f4083b != 2) {
                                        objValueOf = null;
                                    } else {
                                        objValueOf = Boolean.valueOf(this.f4008a.mo3886t());
                                    }
                                    break;
                                case 3:
                                    if (c1757auM3791j.f4083b != 3) {
                                        objValueOf = null;
                                    } else {
                                        objValueOf = Byte.valueOf(this.f4008a.mo3887u());
                                    }
                                    break;
                                case 4:
                                    if (c1757auM3791j.f4083b != 4) {
                                        objValueOf = null;
                                    } else {
                                        objValueOf = Double.valueOf(this.f4008a.mo3891y());
                                    }
                                    break;
                                default:
                                    switch (b) {
                                        case 10:
                                            if (c1757auM3791j.f4083b != 10) {
                                                objValueOf = null;
                                            } else {
                                                objValueOf = Long.valueOf(this.f4008a.mo3890x());
                                            }
                                            break;
                                        case 11:
                                            if (c1757auM3791j.f4083b != 11) {
                                                objValueOf = null;
                                            } else {
                                                objValueOf = this.f4008a.mo3892z();
                                            }
                                            break;
                                        default:
                                            objValueOf = null;
                                            break;
                                    }
                                    break;
                            }
                        } else if (c1757auM3791j.f4083b == 11) {
                            objValueOf = this.f4008a.mo3849A();
                        } else {
                            objValueOf = null;
                        }
                    } else if (c1757auM3791j.f4083b == 8) {
                        objValueOf = Integer.valueOf(this.f4008a.mo3889w());
                    } else {
                        objValueOf = null;
                    }
                } else if (c1757auM3791j.f4083b == 6) {
                    objValueOf = Short.valueOf(this.f4008a.mo3888v());
                } else {
                    objValueOf = null;
                }
                this.f4009b.m3939e();
                this.f4008a.mo3913B();
                return objValueOf;
            } catch (Exception e) {
                throw new C1743ag(e);
            }
        } catch (Throwable th) {
            this.f4009b.m3939e();
            this.f4008a.mo3913B();
            throw th;
        }
    }

    /* JADX INFO: renamed from: j */
    private C1757au m3791j(byte[] bArr, InterfaceC1744ah interfaceC1744ah, InterfaceC1744ah... interfaceC1744ahArr) throws C1743ag {
        this.f4009b.m3937a(bArr);
        InterfaceC1744ah[] interfaceC1744ahArr2 = new InterfaceC1744ah[interfaceC1744ahArr.length + 1];
        int i = 0;
        interfaceC1744ahArr2[0] = interfaceC1744ah;
        int i2 = 0;
        while (i2 < interfaceC1744ahArr.length) {
            int i3 = i2 + 1;
            interfaceC1744ahArr2[i3] = interfaceC1744ahArr[i2];
            i2 = i3;
        }
        this.f4008a.mo3876j();
        C1757au c1757auMo3878l = null;
        while (i < interfaceC1744ahArr2.length) {
            c1757auMo3878l = this.f4008a.mo3878l();
            if (c1757auMo3878l.f4083b == 0 || c1757auMo3878l.f4084c > interfaceC1744ahArr2[i].mo3806a()) {
                return null;
            }
            if (c1757auMo3878l.f4084c != interfaceC1744ahArr2[i].mo3806a()) {
                C1766bc.m3924a(this.f4008a, c1757auMo3878l.f4083b);
                this.f4008a.mo3879m();
            } else {
                i++;
                if (i < interfaceC1744ahArr2.length) {
                    this.f4008a.mo3876j();
                }
            }
        }
        return c1757auMo3878l;
    }

    /* JADX INFO: renamed from: a */
    public void m3793a(InterfaceC1737aa interfaceC1737aa, String str) throws C1743ag {
        m3795a(interfaceC1737aa, str.getBytes());
    }
}
