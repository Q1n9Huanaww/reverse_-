package com.google.p028a.p056i.p058b;

import com.google.p028a.C1303h;
import com.google.p028a.C1358m;
import com.google.p028a.C1365t;
import com.google.p028a.EnumC1233e;
import com.google.p028a.InterfaceC1366u;
import com.google.p028a.p035c.AbstractC1206i;
import com.google.p028a.p035c.C1194b;
import com.google.p028a.p035c.C1204g;
import com.google.p028a.p035c.C1208k;
import com.google.p028a.p035c.p036a.C1191a;
import com.google.p028a.p056i.p057a.C1341j;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.i.b.c */
/* JADX INFO: compiled from: Detector.java */
/* JADX INFO: loaded from: classes.dex */
public class C1345c {

    /* JADX INFO: renamed from: a */
    protected final C1194b f2778a;

    /* JADX INFO: renamed from: b */
    private InterfaceC1366u f2779b;

    public C1345c(C1194b c1194b) {
        this.f2778a = c1194b;
    }

    /* JADX INFO: renamed from: a */
    private C1194b m2499a() {
        return this.f2778a;
    }

    /* JADX INFO: renamed from: b */
    private InterfaceC1366u m2504b() {
        return this.f2779b;
    }

    /* JADX INFO: renamed from: c */
    private C1204g m2505c() throws C1358m, C1303h {
        return m2507a((Map<EnumC1233e, ?>) null);
    }

    /* JADX INFO: renamed from: a */
    public final C1204g m2507a(Map<EnumC1233e, ?> map) throws C1358m, C1303h {
        this.f2779b = map == null ? null : (InterfaceC1366u) map.get(EnumC1233e.NEED_RESULT_POINT_CALLBACK);
        return m2506a(new C1347e(this.f2778a, this.f2779b).m2527a(map));
    }

    /* JADX INFO: renamed from: a */
    private static C1194b m2500a(C1194b c1194b, C1208k c1208k, int i) throws C1358m {
        return AbstractC1206i.m1850a().mo1845a(c1194b, i, i, c1208k);
    }

    /* JADX INFO: renamed from: a */
    private static int m2498a(C1365t c1365t, C1365t c1365t2, C1365t c1365t3, float f) throws C1358m {
        int iM1765a = ((C1191a.m1765a(C1365t.m2627a(c1365t, c1365t2) / f) + C1191a.m1765a(C1365t.m2627a(c1365t, c1365t3) / f)) / 2) + 7;
        int i = iM1765a & 3;
        if (i == 0) {
            return iM1765a + 1;
        }
        switch (i) {
            case 2:
                return iM1765a - 1;
            case 3:
                throw C1358m.m2611a();
            default:
                return iM1765a;
        }
    }

    /* JADX INFO: renamed from: a */
    private float m2497a(C1365t c1365t, C1365t c1365t2, C1365t c1365t3) {
        return (m2496a(c1365t, c1365t2) + m2496a(c1365t, c1365t3)) / 2.0f;
    }

    /* JADX INFO: renamed from: a */
    private float m2495a(int i, int i2, int i3, int i4) {
        int i5;
        float f;
        float f2;
        float fM2503b = m2503b(i, i2, i3, i4);
        int i6 = i - (i3 - i);
        int i7 = 0;
        if (i6 < 0) {
            f = i / (i - i6);
            i5 = 0;
        } else if (i6 >= this.f2778a.f2077a) {
            f = ((this.f2778a.f2077a - 1) - i) / (i6 - i);
            i5 = this.f2778a.f2077a - 1;
        } else {
            i5 = i6;
            f = 1.0f;
        }
        float f3 = i2;
        int i8 = (int) (f3 - ((i4 - i2) * f));
        if (i8 < 0) {
            f2 = f3 / (i2 - i8);
        } else if (i8 >= this.f2778a.f2078b) {
            f2 = ((this.f2778a.f2078b - 1) - i2) / (i8 - i2);
            i7 = this.f2778a.f2078b - 1;
        } else {
            i7 = i8;
            f2 = 1.0f;
        }
        return (fM2503b + m2503b(i, i2, (int) (i + ((i5 - i) * f2)), i7)) - 1.0f;
    }

    /* JADX INFO: renamed from: b */
    private float m2503b(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9 = 1;
        boolean z = Math.abs(i4 - i2) > Math.abs(i3 - i);
        if (z) {
            i6 = i;
            i5 = i2;
            i8 = i3;
            i7 = i4;
        } else {
            i5 = i;
            i6 = i2;
            i7 = i3;
            i8 = i4;
        }
        int iAbs = Math.abs(i7 - i5);
        int iAbs2 = Math.abs(i8 - i6);
        int i10 = (-iAbs) / 2;
        int i11 = i5 < i7 ? 1 : -1;
        int i12 = i6 < i8 ? 1 : -1;
        int i13 = i7 + i11;
        int i14 = i6;
        int i15 = i10;
        int i16 = 0;
        int i17 = i5;
        while (true) {
            if (i17 == i13) {
                i13 = i13;
                break;
            }
            if ((i16 == i9) == this.f2778a.m1789a(z ? i14 : i17, z ? i17 : i14)) {
                if (i16 == 2) {
                    return C1191a.m1764a(i17, i14, i5, i6);
                }
                i16++;
            }
            i15 += iAbs2;
            if (i15 > 0) {
                if (i14 == i8) {
                    break;
                }
                i14 += i12;
                i15 -= iAbs;
            }
            i17 += i11;
            z = z;
            i13 = i13;
            i9 = 1;
        }
        if (i16 == 2) {
            return C1191a.m1764a(i13, i8, i5, i6);
        }
        return Float.NaN;
    }

    /* JADX INFO: renamed from: a */
    private C1343a m2502a(float f, int i, int i2, float f2) throws C1358m {
        C1343a c1343aM2493a;
        C1343a c1343aM2493a2;
        int i3 = (int) (f2 * f);
        int iMax = Math.max(0, i - i3);
        int iMin = Math.min(this.f2778a.f2077a - 1, i + i3) - iMax;
        float f3 = 3.0f * f;
        if (iMin < f3) {
            throw C1358m.m2611a();
        }
        int iMax2 = Math.max(0, i2 - i3);
        int iMin2 = Math.min(this.f2778a.f2078b - 1, i2 + i3) - iMax2;
        if (iMin2 < f3) {
            throw C1358m.m2611a();
        }
        C1344b c1344b = new C1344b(this.f2778a, iMax, iMax2, iMin, iMin2, f, this.f2779b);
        int i4 = c1344b.f2771c;
        int i5 = c1344b.f2774f;
        int i6 = c1344b.f2773e + i4;
        int i7 = c1344b.f2772d + (i5 / 2);
        int[] iArr = new int[3];
        for (int i8 = 0; i8 < i5; i8++) {
            int i9 = ((i8 & 1) == 0 ? (i8 + 1) / 2 : -((i8 + 1) / 2)) + i7;
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            int i10 = i4;
            while (i10 < i6 && !c1344b.f2769a.m1789a(i10, i9)) {
                i10++;
            }
            int i11 = 0;
            while (i10 < i6) {
                if (!c1344b.f2769a.m1789a(i10, i9)) {
                    if (i11 == 1) {
                        i11++;
                    }
                    iArr[i11] = iArr[i11] + 1;
                } else if (i11 == 1) {
                    iArr[1] = iArr[1] + 1;
                } else if (i11 == 2) {
                    if (c1344b.m2494a(iArr) && (c1343aM2493a2 = c1344b.m2493a(iArr, i9, i10)) != null) {
                        return c1343aM2493a2;
                    }
                    iArr[0] = iArr[2];
                    iArr[1] = 1;
                    iArr[2] = 0;
                    i11 = 1;
                } else {
                    i11++;
                    iArr[i11] = iArr[i11] + 1;
                }
                i10++;
            }
            if (c1344b.m2494a(iArr) && (c1343aM2493a = c1344b.m2493a(iArr, i9, i6)) != null) {
                return c1343aM2493a;
            }
        }
        if (!c1344b.f2770b.isEmpty()) {
            return c1344b.f2770b.get(0);
        }
        throw C1358m.m2611a();
    }

    /* JADX INFO: renamed from: a */
    protected final C1204g m2506a(C1348f c1348f) throws C1358m, C1303h {
        float f;
        float f2;
        float f3;
        C1365t[] c1365tArr;
        float f4;
        int i;
        int i2;
        int i3;
        C1343a c1343aM2493a;
        char c;
        int i4;
        C1346d c1346d = c1348f.f2793b;
        C1346d c1346d2 = c1348f.f2794c;
        C1346d c1346d3 = c1348f.f2792a;
        float fM2496a = (m2496a(c1346d, c1346d2) + m2496a(c1346d, c1346d3)) / 2.0f;
        if (fM2496a < 1.0f) {
            throw C1358m.m2611a();
        }
        int iM1765a = ((C1191a.m1765a(C1365t.m2627a(c1346d, c1346d2) / fM2496a) + C1191a.m1765a(C1365t.m2627a(c1346d, c1346d3) / fM2496a)) / 2) + 7;
        int i5 = iM1765a & 3;
        if (i5 != 0) {
            switch (i5) {
                case 2:
                    iM1765a--;
                    break;
                case 3:
                    throw C1358m.m2611a();
            }
        } else {
            iM1765a++;
        }
        int i6 = iM1765a;
        C1341j c1341jM2471a = C1341j.m2471a(i6);
        int iM2479a = c1341jM2471a.m2479a() - 7;
        C1343a c1343a = null;
        int i7 = 0;
        int i8 = 1;
        if (c1341jM2471a.f2760b.length > 0) {
            float f5 = (c1346d2.f2858a - c1346d.f2858a) + c1346d3.f2858a;
            float f6 = (c1346d2.f2859b - c1346d.f2859b) + c1346d3.f2859b;
            float f7 = 1.0f - (3.0f / iM2479a);
            int i9 = (int) (c1346d.f2858a + ((f5 - c1346d.f2858a) * f7));
            int i10 = (int) (c1346d.f2859b + (f7 * (f6 - c1346d.f2859b)));
            int i11 = 4;
            while (i11 <= 16) {
                int i12 = (int) (i11 * fM2496a);
                try {
                    int iMax = Math.max(i7, i9 - i12);
                    int iMin = Math.min(this.f2778a.f2077a - i8, i9 + i12) - iMax;
                    float f8 = fM2496a * 3.0f;
                    if (iMin < f8) {
                        throw C1358m.m2611a();
                    }
                    int iMax2 = Math.max(i7, i10 - i12);
                    int iMin2 = Math.min(this.f2778a.f2078b - i8, i12 + i10) - iMax2;
                    if (iMin2 < f8) {
                        throw C1358m.m2611a();
                    }
                    i2 = i11;
                    i3 = i9;
                    try {
                        C1344b c1344b = new C1344b(this.f2778a, iMax, iMax2, iMin, iMin2, fM2496a, this.f2779b);
                        int i13 = c1344b.f2771c;
                        int i14 = c1344b.f2774f;
                        int i15 = c1344b.f2773e + i13;
                        int i16 = c1344b.f2772d + (i14 / 2);
                        int[] iArr = new int[3];
                        int i17 = 0;
                        while (true) {
                            if (i17 < i14) {
                                if ((i17 & 1) == 0) {
                                    c = 2;
                                    i4 = (i17 + 1) / 2;
                                } else {
                                    c = 2;
                                    i4 = -((i17 + 1) / 2);
                                }
                                int i18 = i4 + i16;
                                iArr[0] = 0;
                                f4 = fM2496a;
                                try {
                                    iArr[1] = 0;
                                    iArr[c] = 0;
                                    int i19 = i13;
                                    while (i19 < i15 && !c1344b.f2769a.m1789a(i19, i18)) {
                                        i19++;
                                    }
                                    int i20 = 0;
                                    while (true) {
                                        if (i19 < i15) {
                                            i = i10;
                                            try {
                                                if (!c1344b.f2769a.m1789a(i19, i18)) {
                                                    if (i20 == 1) {
                                                        i20++;
                                                    }
                                                    iArr[i20] = iArr[i20] + 1;
                                                } else if (i20 == 1) {
                                                    iArr[1] = iArr[1] + 1;
                                                } else if (i20 == 2) {
                                                    if (!c1344b.m2494a(iArr) || (c1343aM2493a = c1344b.m2493a(iArr, i18, i19)) == null) {
                                                        iArr[0] = iArr[2];
                                                        iArr[1] = 1;
                                                        iArr[2] = 0;
                                                        i20 = 1;
                                                    }
                                                } else {
                                                    i20++;
                                                    iArr[i20] = iArr[i20] + 1;
                                                }
                                                i19++;
                                                i10 = i;
                                                i13 = i13;
                                            } catch (C1358m unused) {
                                                continue;
                                                i11 = i2 << 1;
                                                i9 = i3;
                                                fM2496a = f4;
                                                i10 = i;
                                                i8 = 1;
                                                i7 = 0;
                                            }
                                        } else {
                                            int i21 = i10;
                                            int i22 = i13;
                                            if (!c1344b.m2494a(iArr) || (c1343aM2493a = c1344b.m2493a(iArr, i18, i15)) == null) {
                                                i17++;
                                                fM2496a = f4;
                                                i10 = i21;
                                                i13 = i22;
                                            }
                                        }
                                    }
                                } catch (C1358m unused2) {
                                    i = i10;
                                    i11 = i2 << 1;
                                    i9 = i3;
                                    fM2496a = f4;
                                    i10 = i;
                                    i8 = 1;
                                    i7 = 0;
                                }
                            } else if (!c1344b.f2770b.isEmpty()) {
                                c1343aM2493a = c1344b.f2770b.get(0);
                            } else {
                                throw C1358m.m2611a();
                            }
                        }
                        c1343a = c1343aM2493a;
                    } catch (C1358m unused3) {
                        f4 = fM2496a;
                    }
                } catch (C1358m unused4) {
                    f4 = fM2496a;
                    i = i10;
                    i2 = i11;
                    i3 = i9;
                }
            }
        }
        C1343a c1343a2 = c1343a;
        float f9 = i6 - 3.5f;
        if (c1343a2 != null) {
            f = c1343a2.f2858a;
            f2 = c1343a2.f2859b;
            f3 = f9 - 3.0f;
        } else {
            f = (c1346d2.f2858a - c1346d.f2858a) + c1346d3.f2858a;
            f2 = (c1346d2.f2859b - c1346d.f2859b) + c1346d3.f2859b;
            f3 = f9;
        }
        C1194b c1194bMo1845a = AbstractC1206i.m1850a().mo1845a(this.f2778a, i6, i6, C1208k.m1859a(3.5f, 3.5f, f9, 3.5f, f3, f3, 3.5f, f9, c1346d.f2858a, c1346d.f2859b, c1346d2.f2858a, c1346d2.f2859b, f, f2, c1346d3.f2858a, c1346d3.f2859b));
        if (c1343a2 == null) {
            c1365tArr = new C1365t[]{c1346d3, c1346d, c1346d2};
        } else {
            c1365tArr = new C1365t[]{c1346d3, c1346d, c1346d2, c1343a2};
        }
        return new C1204g(c1194bMo1845a, c1365tArr);
    }

    /* JADX INFO: renamed from: a */
    private static C1208k m2501a(C1365t c1365t, C1365t c1365t2, C1365t c1365t3, C1365t c1365t4, int i) {
        float f;
        float f2;
        float f3;
        float f4 = i - 3.5f;
        if (c1365t4 != null) {
            float f5 = c1365t4.f2858a;
            f2 = c1365t4.f2859b;
            f = f5;
            f3 = f4 - 3.0f;
        } else {
            f = (c1365t2.f2858a - c1365t.f2858a) + c1365t3.f2858a;
            f2 = (c1365t2.f2859b - c1365t.f2859b) + c1365t3.f2859b;
            f3 = f4;
        }
        return C1208k.m1859a(3.5f, 3.5f, f4, 3.5f, f3, f3, 3.5f, f4, c1365t.f2858a, c1365t.f2859b, c1365t2.f2858a, c1365t2.f2859b, f, f2, c1365t3.f2858a, c1365t3.f2859b);
    }

    /* JADX INFO: renamed from: a */
    private float m2496a(C1365t c1365t, C1365t c1365t2) {
        float fM2495a = m2495a((int) c1365t.f2858a, (int) c1365t.f2859b, (int) c1365t2.f2858a, (int) c1365t2.f2859b);
        float fM2495a2 = m2495a((int) c1365t2.f2858a, (int) c1365t2.f2859b, (int) c1365t.f2858a, (int) c1365t.f2859b);
        if (Float.isNaN(fM2495a)) {
            return fM2495a2 / 7.0f;
        }
        return Float.isNaN(fM2495a2) ? fM2495a / 7.0f : (fM2495a + fM2495a2) / 14.0f;
    }
}
