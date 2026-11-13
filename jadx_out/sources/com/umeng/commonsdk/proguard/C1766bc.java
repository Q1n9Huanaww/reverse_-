package com.umeng.commonsdk.proguard;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.bc */
/* JADX INFO: compiled from: TProtocolUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class C1766bc {

    /* JADX INFO: renamed from: a */
    private static int f4109a = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: a */
    public static void m3923a(int i) {
        f4109a = i;
    }

    /* JADX INFO: renamed from: a */
    public static void m3924a(AbstractC1762az abstractC1762az, byte b) throws C1743ag {
        m3925a(abstractC1762az, b, f4109a);
    }

    /* JADX INFO: renamed from: a */
    public static void m3925a(AbstractC1762az abstractC1762az, byte b, int i) throws C1743ag {
        if (i <= 0) {
            throw new C1743ag("Maximum skip depth exceeded");
        }
        int i2 = 0;
        switch (b) {
            case 2:
                abstractC1762az.mo3886t();
                return;
            case 3:
                abstractC1762az.mo3887u();
                return;
            case 4:
                abstractC1762az.mo3891y();
                return;
            case 5:
            case 7:
            case 9:
            default:
                return;
            case 6:
                abstractC1762az.mo3888v();
                return;
            case 8:
                abstractC1762az.mo3889w();
                return;
            case 10:
                abstractC1762az.mo3890x();
                return;
            case 11:
                abstractC1762az.mo3849A();
                return;
            case 12:
                abstractC1762az.mo3876j();
                while (true) {
                    C1757au c1757auMo3878l = abstractC1762az.mo3878l();
                    if (c1757auMo3878l.f4083b != 0) {
                        m3925a(abstractC1762az, c1757auMo3878l.f4083b, i - 1);
                        abstractC1762az.mo3879m();
                    } else {
                        abstractC1762az.mo3877k();
                        return;
                    }
                }
                break;
            case 13:
                C1759aw c1759awMo3880n = abstractC1762az.mo3880n();
                while (i2 < c1759awMo3880n.f4089c) {
                    int i3 = i - 1;
                    m3925a(abstractC1762az, c1759awMo3880n.f4087a, i3);
                    m3925a(abstractC1762az, c1759awMo3880n.f4088b, i3);
                    i2++;
                }
                abstractC1762az.mo3881o();
                return;
            case 14:
                C1767bd c1767bdMo3884r = abstractC1762az.mo3884r();
                while (i2 < c1767bdMo3884r.f4111b) {
                    m3925a(abstractC1762az, c1767bdMo3884r.f4110a, i - 1);
                    i2++;
                }
                abstractC1762az.mo3885s();
                return;
            case 15:
                C1758av c1758avMo3882p = abstractC1762az.mo3882p();
                while (i2 < c1758avMo3882p.f4086b) {
                    m3925a(abstractC1762az, c1758avMo3882p.f4085a, i - 1);
                    i2++;
                }
                abstractC1762az.mo3883q();
                return;
        }
    }

    /* JADX INFO: renamed from: a */
    public static InterfaceC1765bb m3922a(byte[] bArr, InterfaceC1765bb interfaceC1765bb) {
        if (bArr[0] > 16) {
            return new C1756at.a();
        }
        return (bArr.length <= 1 || (bArr[1] & 128) == 0) ? interfaceC1765bb : new C1756at.a();
    }
}
