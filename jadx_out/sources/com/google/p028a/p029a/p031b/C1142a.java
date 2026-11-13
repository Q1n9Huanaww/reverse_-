package com.google.p028a.p029a.p031b;

import com.google.p028a.C1358m;
import com.google.p028a.C1365t;
import com.google.p028a.p029a.C1139a;
import com.google.p028a.p035c.AbstractC1206i;
import com.google.p028a.p035c.C1194b;
import com.google.p028a.p035c.p036a.C1191a;
import com.google.p028a.p035c.p036a.C1193c;
import com.google.p028a.p035c.p037b.C1195a;
import com.google.p028a.p035c.p037b.C1197c;
import com.google.p028a.p035c.p037b.C1199e;

/* JADX INFO: renamed from: com.google.a.a.b.a */
/* JADX INFO: compiled from: Detector.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1142a {

    /* JADX INFO: renamed from: a */
    private static final int[] f1896a = {3808, 476, 2107, 1799};

    /* JADX INFO: renamed from: b */
    private final C1194b f1897b;

    /* JADX INFO: renamed from: c */
    private boolean f1898c;

    /* JADX INFO: renamed from: d */
    private int f1899d;

    /* JADX INFO: renamed from: e */
    private int f1900e;

    /* JADX INFO: renamed from: f */
    private int f1901f;

    /* JADX INFO: renamed from: g */
    private int f1902g;

    public C1142a(C1194b c1194b) {
        this.f1897b = c1194b;
    }

    /* JADX INFO: renamed from: a */
    private C1139a m1497a() throws C1358m {
        return m1510a(false);
    }

    /* JADX INFO: renamed from: a */
    public final C1139a m1510a(boolean z) throws C1358m {
        int i;
        a aVarM1507b = m1507b();
        int i2 = 1;
        this.f1901f = 1;
        a aVar = aVarM1507b;
        a aVar2 = aVar;
        a aVar3 = aVar2;
        boolean z2 = true;
        while (this.f1901f < 9) {
            a aVarM1498a = m1498a(aVarM1507b, z2, i2, -1);
            a aVarM1498a2 = m1498a(aVar, z2, i2, i2);
            a aVarM1498a3 = m1498a(aVar2, z2, -1, i2);
            a aVarM1498a4 = m1498a(aVar3, z2, -1, -1);
            if (this.f1901f > 2) {
                double dM1506b = (m1506b(aVarM1498a4, aVarM1498a) * this.f1901f) / (m1506b(aVar3, aVarM1507b) * (this.f1901f + 2));
                if (dM1506b < 0.75d || dM1506b > 1.25d) {
                    break;
                }
                a aVar4 = new a(aVarM1498a.f1903a - 3, aVarM1498a.f1904b + 3);
                a aVar5 = new a(aVarM1498a2.f1903a - 3, aVarM1498a2.f1904b - 3);
                a aVar6 = new a(aVarM1498a3.f1903a + 3, aVarM1498a3.f1904b - 3);
                a aVar7 = new a(aVarM1498a4.f1903a + 3, aVarM1498a4.f1904b + 3);
                int iM1494a = m1494a(aVar7, aVar4);
                if (!(iM1494a != 0 && m1494a(aVar4, aVar5) == iM1494a && m1494a(aVar5, aVar6) == iM1494a && m1494a(aVar6, aVar7) == iM1494a)) {
                    break;
                }
            }
            z2 = !z2;
            this.f1901f++;
            aVar3 = aVarM1498a4;
            aVar = aVarM1498a2;
            aVar2 = aVarM1498a3;
            aVarM1507b = aVarM1498a;
            i2 = 1;
        }
        if (this.f1901f != 5 && this.f1901f != 7) {
            throw C1358m.m2611a();
        }
        this.f1898c = this.f1901f == 5;
        C1365t[] c1365tArrM1505a = m1505a(new C1365t[]{new C1365t(aVarM1507b.f1903a + 0.5f, aVarM1507b.f1904b - 0.5f), new C1365t(aVar.f1903a + 0.5f, aVar.f1904b + 0.5f), new C1365t(aVar2.f1903a - 0.5f, aVar2.f1904b + 0.5f), new C1365t(aVar3.f1903a - 0.5f, aVar3.f1904b - 0.5f)}, (this.f1901f * 2) - 3, this.f1901f * 2);
        if (z) {
            C1365t c1365t = c1365tArrM1505a[0];
            c1365tArrM1505a[0] = c1365tArrM1505a[2];
            c1365tArrM1505a[2] = c1365t;
        }
        if (!m1503a(c1365tArrM1505a[0]) || !m1503a(c1365tArrM1505a[1]) || !m1503a(c1365tArrM1505a[2]) || !m1503a(c1365tArrM1505a[3])) {
            throw C1358m.m2611a();
        }
        int i3 = this.f1901f * 2;
        int[] iArr = {m1495a(c1365tArrM1505a[0], c1365tArrM1505a[1], i3), m1495a(c1365tArrM1505a[1], c1365tArrM1505a[2], i3), m1495a(c1365tArrM1505a[2], c1365tArrM1505a[3], i3), m1495a(c1365tArrM1505a[3], c1365tArrM1505a[0], i3)};
        int i4 = 0;
        for (int i5 = 0; i5 < 4; i5++) {
            int i6 = iArr[i5];
            i4 = (i4 << 3) + ((i6 >> (i3 - 2)) << 1) + (i6 & 1);
        }
        int i7 = ((i4 & 1) << 11) + (i4 >> 1);
        for (int i8 = 0; i8 < 4; i8++) {
            if (Integer.bitCount(f1896a[i8] ^ i7) <= 2) {
                this.f1902g = i8;
                long j = 0;
                for (int i9 = 0; i9 < 4; i9++) {
                    int i10 = iArr[(this.f1902g + i9) % 4];
                    j = this.f1898c ? (j << 7) + ((long) ((i10 >> 1) & 127)) : (j << 10) + ((long) (((i10 >> 2) & 992) + ((i10 >> 1) & 31)));
                }
                int iM1493a = m1493a(j, this.f1898c);
                if (this.f1898c) {
                    i = 1;
                    this.f1899d = (iM1493a >> 6) + 1;
                    this.f1900e = (iM1493a & 63) + 1;
                } else {
                    i = 1;
                    this.f1899d = (iM1493a >> 11) + 1;
                    this.f1900e = (iM1493a & 2047) + 1;
                }
                C1194b c1194b = this.f1897b;
                C1365t c1365t2 = c1365tArrM1505a[this.f1902g % 4];
                C1365t c1365t3 = c1365tArrM1505a[(this.f1902g + i) % 4];
                C1365t c1365t4 = c1365tArrM1505a[(this.f1902g + 2) % 4];
                C1365t c1365t5 = c1365tArrM1505a[(this.f1902g + 3) % 4];
                AbstractC1206i abstractC1206iM1850a = AbstractC1206i.m1850a();
                int iM1509c = m1509c();
                float f = iM1509c / 2.0f;
                float f2 = f - this.f1901f;
                float f3 = f + this.f1901f;
                return new C1139a(abstractC1206iM1850a.mo1844a(c1194b, iM1509c, iM1509c, f2, f2, f3, f2, f3, f3, f2, f3, c1365t2.f2858a, c1365t2.f2859b, c1365t3.f2858a, c1365t3.f2859b, c1365t4.f2858a, c1365t4.f2859b, c1365t5.f2858a, c1365t5.f2859b), m1505a(c1365tArrM1505a, this.f1901f * 2, m1509c()), this.f1898c, this.f1900e, this.f1899d);
            }
        }
        throw C1358m.m2611a();
    }

    /* JADX INFO: renamed from: a */
    private void m1500a(C1365t[] c1365tArr) throws C1358m {
        if (!m1503a(c1365tArr[0]) || !m1503a(c1365tArr[1]) || !m1503a(c1365tArr[2]) || !m1503a(c1365tArr[3])) {
            throw C1358m.m2611a();
        }
        int i = this.f1901f * 2;
        int[] iArr = {m1495a(c1365tArr[0], c1365tArr[1], i), m1495a(c1365tArr[1], c1365tArr[2], i), m1495a(c1365tArr[2], c1365tArr[3], i), m1495a(c1365tArr[3], c1365tArr[0], i)};
        int i2 = 0;
        for (int i3 = 0; i3 < 4; i3++) {
            int i4 = iArr[i3];
            i2 = (i2 << 3) + ((i4 >> (i - 2)) << 1) + (i4 & 1);
        }
        int i5 = ((i2 & 1) << 11) + (i2 >> 1);
        for (int i6 = 0; i6 < 4; i6++) {
            if (Integer.bitCount(f1896a[i6] ^ i5) <= 2) {
                this.f1902g = i6;
                long j = 0;
                for (int i7 = 0; i7 < 4; i7++) {
                    int i8 = iArr[(this.f1902g + i7) % 4];
                    j = this.f1898c ? (j << 7) + ((long) ((i8 >> 1) & 127)) : (j << 10) + ((long) (((i8 >> 2) & 992) + ((i8 >> 1) & 31)));
                }
                int iM1493a = m1493a(j, this.f1898c);
                if (this.f1898c) {
                    this.f1899d = (iM1493a >> 6) + 1;
                    this.f1900e = (iM1493a & 63) + 1;
                    return;
                } else {
                    this.f1899d = (iM1493a >> 11) + 1;
                    this.f1900e = (iM1493a & 2047) + 1;
                    return;
                }
            }
        }
        throw C1358m.m2611a();
    }

    /* JADX INFO: renamed from: a */
    private static int m1496a(int[] iArr, int i) throws C1358m {
        int i2 = 0;
        for (int i3 = 0; i3 < 4; i3++) {
            int i4 = iArr[i3];
            i2 = (i2 << 3) + ((i4 >> (i - 2)) << 1) + (i4 & 1);
        }
        int i5 = ((i2 & 1) << 11) + (i2 >> 1);
        for (int i6 = 0; i6 < 4; i6++) {
            if (Integer.bitCount(f1896a[i6] ^ i5) <= 2) {
                return i6;
            }
        }
        throw C1358m.m2611a();
    }

    /* JADX INFO: renamed from: a */
    private static int m1493a(long j, boolean z) throws C1358m {
        int i;
        int i2;
        if (z) {
            i = 7;
            i2 = 2;
        } else {
            i = 10;
            i2 = 4;
        }
        int i3 = i - i2;
        int[] iArr = new int[i];
        for (int i4 = i - 1; i4 >= 0; i4--) {
            iArr[i4] = ((int) j) & 15;
            j >>= 4;
        }
        try {
            new C1197c(C1195a.f2084d).m1819a(iArr, i3);
            int i5 = 0;
            for (int i6 = 0; i6 < i2; i6++) {
                i5 = (i5 << 4) + iArr[i6];
            }
            return i5;
        } catch (C1199e unused) {
            throw C1358m.m2611a();
        }
    }

    /* JADX INFO: renamed from: a */
    private C1365t[] m1504a(a aVar) throws C1358m {
        int i = 1;
        this.f1901f = 1;
        a aVar2 = aVar;
        a aVar3 = aVar2;
        a aVar4 = aVar3;
        a aVar5 = aVar4;
        boolean z = true;
        while (this.f1901f < 9) {
            a aVarM1498a = m1498a(aVar2, z, i, -1);
            a aVarM1498a2 = m1498a(aVar3, z, i, i);
            a aVarM1498a3 = m1498a(aVar4, z, -1, i);
            a aVarM1498a4 = m1498a(aVar5, z, -1, -1);
            if (this.f1901f > 2) {
                double dM1506b = (m1506b(aVarM1498a4, aVarM1498a) * this.f1901f) / (m1506b(aVar5, aVar2) * (this.f1901f + 2));
                if (dM1506b >= 0.75d && dM1506b <= 1.25d) {
                    a aVar6 = new a(aVarM1498a.f1903a - 3, aVarM1498a.f1904b + 3);
                    a aVar7 = new a(aVarM1498a2.f1903a - 3, aVarM1498a2.f1904b - 3);
                    a aVar8 = new a(aVarM1498a3.f1903a + 3, aVarM1498a3.f1904b - 3);
                    a aVar9 = new a(aVarM1498a4.f1903a + 3, aVarM1498a4.f1904b + 3);
                    int iM1494a = m1494a(aVar9, aVar6);
                    if (!(iM1494a != 0 && m1494a(aVar6, aVar7) == iM1494a && m1494a(aVar7, aVar8) == iM1494a && m1494a(aVar8, aVar9) == iM1494a)) {
                        break;
                    }
                } else {
                    break;
                }
            }
            z = !z;
            this.f1901f++;
            aVar5 = aVarM1498a4;
            aVar3 = aVarM1498a2;
            aVar4 = aVarM1498a3;
            aVar2 = aVarM1498a;
            i = 1;
        }
        if (this.f1901f != 5 && this.f1901f != 7) {
            throw C1358m.m2611a();
        }
        this.f1898c = this.f1901f == 5;
        return m1505a(new C1365t[]{new C1365t(aVar2.f1903a + 0.5f, aVar2.f1904b - 0.5f), new C1365t(aVar3.f1903a + 0.5f, aVar3.f1904b + 0.5f), new C1365t(aVar4.f1903a - 0.5f, aVar4.f1904b + 0.5f), new C1365t(aVar5.f1903a - 0.5f, aVar5.f1904b - 0.5f)}, (this.f1901f * 2) - 3, this.f1901f * 2);
    }

    /* JADX INFO: renamed from: b */
    private a m1507b() {
        C1365t c1365tM1513a;
        C1365t c1365t;
        C1365t c1365t2;
        C1365t c1365t3;
        C1365t c1365tM1513a2;
        C1365t c1365tM1513a3;
        C1365t c1365tM1513a4;
        C1365t c1365tM1513a5;
        try {
            C1365t[] c1365tArrM1773a = new C1193c(this.f1897b).m1773a();
            c1365t2 = c1365tArrM1773a[0];
            c1365t3 = c1365tArrM1773a[1];
            c1365t = c1365tArrM1773a[2];
            c1365tM1513a = c1365tArrM1773a[3];
        } catch (C1358m unused) {
            int i = this.f1897b.f2077a / 2;
            int i2 = this.f1897b.f2078b / 2;
            int i3 = i + 7;
            int i4 = i2 - 7;
            C1365t c1365tM1513a6 = m1498a(new a(i3, i4), false, 1, -1).m1513a();
            int i5 = i2 + 7;
            C1365t c1365tM1513a7 = m1498a(new a(i3, i5), false, 1, 1).m1513a();
            int i6 = i - 7;
            C1365t c1365tM1513a8 = m1498a(new a(i6, i5), false, -1, 1).m1513a();
            c1365tM1513a = m1498a(new a(i6, i4), false, -1, -1).m1513a();
            c1365t = c1365tM1513a8;
            c1365t2 = c1365tM1513a6;
            c1365t3 = c1365tM1513a7;
        }
        int iM1765a = C1191a.m1765a((((c1365t2.f2858a + c1365tM1513a.f2858a) + c1365t3.f2858a) + c1365t.f2858a) / 4.0f);
        int iM1765a2 = C1191a.m1765a((((c1365t2.f2859b + c1365tM1513a.f2859b) + c1365t3.f2859b) + c1365t.f2859b) / 4.0f);
        try {
            C1365t[] c1365tArrM1773a2 = new C1193c(this.f1897b, 15, iM1765a, iM1765a2).m1773a();
            c1365tM1513a2 = c1365tArrM1773a2[0];
            c1365tM1513a3 = c1365tArrM1773a2[1];
            c1365tM1513a4 = c1365tArrM1773a2[2];
            c1365tM1513a5 = c1365tArrM1773a2[3];
        } catch (C1358m unused2) {
            int i7 = iM1765a + 7;
            int i8 = iM1765a2 - 7;
            c1365tM1513a2 = m1498a(new a(i7, i8), false, 1, -1).m1513a();
            int i9 = iM1765a2 + 7;
            c1365tM1513a3 = m1498a(new a(i7, i9), false, 1, 1).m1513a();
            int i10 = iM1765a - 7;
            c1365tM1513a4 = m1498a(new a(i10, i9), false, -1, 1).m1513a();
            c1365tM1513a5 = m1498a(new a(i10, i8), false, -1, -1).m1513a();
        }
        return new a(C1191a.m1765a((((c1365tM1513a2.f2858a + c1365tM1513a5.f2858a) + c1365tM1513a3.f2858a) + c1365tM1513a4.f2858a) / 4.0f), C1191a.m1765a((((c1365tM1513a2.f2859b + c1365tM1513a5.f2859b) + c1365tM1513a3.f2859b) + c1365tM1513a4.f2859b) / 4.0f));
    }

    /* JADX INFO: renamed from: b */
    private C1365t[] m1508b(C1365t[] c1365tArr) {
        return m1505a(c1365tArr, this.f1901f * 2, m1509c());
    }

    /* JADX INFO: renamed from: a */
    private C1194b m1499a(C1194b c1194b, C1365t c1365t, C1365t c1365t2, C1365t c1365t3, C1365t c1365t4) throws C1358m {
        AbstractC1206i abstractC1206iM1850a = AbstractC1206i.m1850a();
        int iM1509c = m1509c();
        float f = iM1509c / 2.0f;
        float f2 = f - this.f1901f;
        float f3 = f + this.f1901f;
        return abstractC1206iM1850a.mo1844a(c1194b, iM1509c, iM1509c, f2, f2, f3, f2, f3, f3, f2, f3, c1365t.f2858a, c1365t.f2859b, c1365t2.f2858a, c1365t2.f2859b, c1365t3.f2858a, c1365t3.f2859b, c1365t4.f2858a, c1365t4.f2859b);
    }

    /* JADX INFO: renamed from: a */
    private boolean m1502a(a aVar, a aVar2, a aVar3, a aVar4) {
        a aVar5 = new a(aVar.f1903a - 3, aVar.f1904b + 3);
        a aVar6 = new a(aVar2.f1903a - 3, aVar2.f1904b - 3);
        a aVar7 = new a(aVar3.f1903a + 3, aVar3.f1904b - 3);
        a aVar8 = new a(aVar4.f1903a + 3, aVar4.f1904b + 3);
        int iM1494a = m1494a(aVar8, aVar5);
        return iM1494a != 0 && m1494a(aVar5, aVar6) == iM1494a && m1494a(aVar6, aVar7) == iM1494a && m1494a(aVar7, aVar8) == iM1494a;
    }

    /* JADX INFO: renamed from: a */
    private int m1494a(a aVar, a aVar2) {
        float fM1506b = m1506b(aVar, aVar2);
        float f = (aVar2.f1903a - aVar.f1903a) / fM1506b;
        float f2 = (aVar2.f1904b - aVar.f1904b) / fM1506b;
        float f3 = aVar.f1903a;
        float f4 = aVar.f1904b;
        boolean zM1789a = this.f1897b.m1789a(aVar.f1903a, aVar.f1904b);
        int iCeil = (int) Math.ceil(fM1506b);
        float f5 = f4;
        int i = 0;
        float f6 = f3;
        for (int i2 = 0; i2 < iCeil; i2++) {
            f6 += f;
            f5 += f2;
            if (this.f1897b.m1789a(C1191a.m1765a(f6), C1191a.m1765a(f5)) != zM1789a) {
                i++;
            }
        }
        float f7 = i / fM1506b;
        if (f7 <= 0.1f || f7 >= 0.9f) {
            return (f7 <= 0.1f) == zM1789a ? 1 : -1;
        }
        return 0;
    }

    /* JADX INFO: renamed from: a */
    private static C1365t[] m1505a(C1365t[] c1365tArr, int i, int i2) {
        float f = i2 / (i * 2.0f);
        float f2 = c1365tArr[0].f2858a - c1365tArr[2].f2858a;
        float f3 = c1365tArr[0].f2859b - c1365tArr[2].f2859b;
        float f4 = (c1365tArr[0].f2858a + c1365tArr[2].f2858a) / 2.0f;
        float f5 = (c1365tArr[0].f2859b + c1365tArr[2].f2859b) / 2.0f;
        float f6 = f2 * f;
        float f7 = f3 * f;
        C1365t c1365t = new C1365t(f4 + f6, f5 + f7);
        C1365t c1365t2 = new C1365t(f4 - f6, f5 - f7);
        float f8 = c1365tArr[1].f2858a - c1365tArr[3].f2858a;
        float f9 = c1365tArr[1].f2859b - c1365tArr[3].f2859b;
        float f10 = (c1365tArr[1].f2858a + c1365tArr[3].f2858a) / 2.0f;
        float f11 = (c1365tArr[1].f2859b + c1365tArr[3].f2859b) / 2.0f;
        float f12 = f8 * f;
        float f13 = f * f9;
        return new C1365t[]{c1365t, new C1365t(f10 + f12, f11 + f13), c1365t2, new C1365t(f10 - f12, f11 - f13)};
    }

    /* JADX INFO: renamed from: a */
    private boolean m1501a(int i, int i2) {
        return i >= 0 && i < this.f1897b.f2077a && i2 > 0 && i2 < this.f1897b.f2078b;
    }

    /* JADX INFO: renamed from: c */
    private int m1509c() {
        if (this.f1898c) {
            return (this.f1899d * 4) + 11;
        }
        if (this.f1899d <= 4) {
            return (this.f1899d * 4) + 15;
        }
        return (this.f1899d * 4) + ((((this.f1899d - 4) / 8) + 1) * 2) + 15;
    }

    /* JADX INFO: renamed from: com.google.a.a.b.a$a */
    /* JADX INFO: compiled from: Detector.java */
    static final class a {

        /* JADX INFO: renamed from: a */
        final int f1903a;

        /* JADX INFO: renamed from: b */
        final int f1904b;

        /* JADX INFO: renamed from: a */
        final C1365t m1513a() {
            return new C1365t(this.f1903a, this.f1904b);
        }

        a(int i, int i2) {
            this.f1903a = i;
            this.f1904b = i2;
        }

        /* JADX INFO: renamed from: b */
        private int m1511b() {
            return this.f1903a;
        }

        /* JADX INFO: renamed from: c */
        private int m1512c() {
            return this.f1904b;
        }

        public final String toString() {
            return "<" + this.f1903a + ' ' + this.f1904b + '>';
        }
    }

    /* JADX INFO: renamed from: a */
    private int m1495a(C1365t c1365t, C1365t c1365t2, int i) {
        float fM1763a = C1191a.m1763a(c1365t.f2858a, c1365t.f2859b, c1365t2.f2858a, c1365t2.f2859b);
        float f = fM1763a / i;
        float f2 = c1365t.f2858a;
        float f3 = c1365t.f2859b;
        float f4 = ((c1365t2.f2858a - c1365t.f2858a) * f) / fM1763a;
        float f5 = (f * (c1365t2.f2859b - c1365t.f2859b)) / fM1763a;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            float f6 = i3;
            if (this.f1897b.m1789a(C1191a.m1765a((f6 * f4) + f2), C1191a.m1765a((f6 * f5) + f3))) {
                i2 |= 1 << ((i - i3) - 1);
            }
        }
        return i2;
    }

    /* JADX INFO: renamed from: a */
    private a m1498a(a aVar, boolean z, int i, int i2) {
        int i3 = aVar.f1903a + i;
        int i4 = aVar.f1904b;
        while (true) {
            i4 += i2;
            if (!m1501a(i3, i4) || this.f1897b.m1789a(i3, i4) != z) {
                break;
            }
            i3 += i;
        }
        int i5 = i3 - i;
        int i6 = i4 - i2;
        while (m1501a(i5, i6) && this.f1897b.m1789a(i5, i6) == z) {
            i5 += i;
        }
        int i7 = i5 - i;
        while (m1501a(i7, i6) && this.f1897b.m1789a(i7, i6) == z) {
            i6 += i2;
        }
        return new a(i7, i6 - i2);
    }

    /* JADX INFO: renamed from: a */
    private boolean m1503a(C1365t c1365t) {
        return m1501a(C1191a.m1765a(c1365t.f2858a), C1191a.m1765a(c1365t.f2859b));
    }

    /* JADX INFO: renamed from: b */
    private static float m1506b(a aVar, a aVar2) {
        return C1191a.m1764a(aVar.f1903a, aVar.f1904b, aVar2.f1903a, aVar2.f1904b);
    }

    /* JADX INFO: renamed from: a */
    private static float m1492a(C1365t c1365t, C1365t c1365t2) {
        return C1191a.m1763a(c1365t.f2858a, c1365t.f2859b, c1365t2.f2858a, c1365t2.f2859b);
    }
}
