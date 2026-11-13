package com.google.p028a.p035c.p036a;

import com.google.p028a.C1358m;
import com.google.p028a.C1365t;
import com.google.p028a.p035c.C1194b;

/* JADX INFO: renamed from: com.google.a.c.a.c */
/* JADX INFO: compiled from: WhiteRectangleDetector.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1193c {

    /* JADX INFO: renamed from: a */
    private static final int f2068a = 10;

    /* JADX INFO: renamed from: b */
    private static final int f2069b = 1;

    /* JADX INFO: renamed from: c */
    private final C1194b f2070c;

    /* JADX INFO: renamed from: d */
    private final int f2071d;

    /* JADX INFO: renamed from: e */
    private final int f2072e;

    /* JADX INFO: renamed from: f */
    private final int f2073f;

    /* JADX INFO: renamed from: g */
    private final int f2074g;

    /* JADX INFO: renamed from: h */
    private final int f2075h;

    /* JADX INFO: renamed from: i */
    private final int f2076i;

    public C1193c(C1194b c1194b, int i, int i2, int i3) throws C1358m {
        this.f2070c = c1194b;
        this.f2071d = c1194b.f2078b;
        this.f2072e = c1194b.f2077a;
        int i4 = i / 2;
        this.f2073f = i2 - i4;
        this.f2074g = i2 + i4;
        this.f2076i = i3 - i4;
        this.f2075h = i3 + i4;
        if (this.f2076i < 0 || this.f2073f < 0 || this.f2075h >= this.f2071d || this.f2074g >= this.f2072e) {
            throw C1358m.m2611a();
        }
    }

    /* JADX INFO: renamed from: a */
    public final C1365t[] m1773a() throws C1358m {
        boolean z;
        int i = this.f2073f;
        int i2 = this.f2074g;
        int i3 = this.f2076i;
        int i4 = this.f2075h;
        int i5 = i;
        boolean z2 = true;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        while (true) {
            if (!z2) {
                z = false;
                break;
            }
            boolean zM1771a = true;
            boolean z8 = false;
            while (true) {
                if ((!zM1771a && z3) || i2 >= this.f2072e) {
                    break;
                }
                zM1771a = m1771a(i3, i4, i2, false);
                if (zM1771a) {
                    i2++;
                    z3 = true;
                    z8 = true;
                } else if (!z3) {
                    i2++;
                }
            }
            if (i2 < this.f2072e) {
                boolean zM1771a2 = true;
                while (true) {
                    if ((!zM1771a2 && z4) || i4 >= this.f2071d) {
                        break;
                    }
                    zM1771a2 = m1771a(i5, i2, i4, true);
                    if (zM1771a2) {
                        i4++;
                        z4 = true;
                        z8 = true;
                    } else if (!z4) {
                        i4++;
                    }
                }
                if (i4 < this.f2071d) {
                    boolean zM1771a3 = true;
                    while (true) {
                        if ((!zM1771a3 && z5) || i5 < 0) {
                            break;
                        }
                        zM1771a3 = m1771a(i3, i4, i5, false);
                        if (zM1771a3) {
                            i5--;
                            z5 = true;
                            z8 = true;
                        } else if (!z5) {
                            i5--;
                        }
                    }
                    if (i5 >= 0) {
                        boolean zM1771a4 = true;
                        while (true) {
                            if ((!zM1771a4 && z7) || i3 < 0) {
                                break;
                            }
                            zM1771a4 = m1771a(i5, i2, i3, true);
                            if (zM1771a4) {
                                i3--;
                                z7 = true;
                                z8 = true;
                            } else if (!z7) {
                                i3--;
                            }
                        }
                        if (i3 >= 0) {
                            if (z8) {
                                z6 = true;
                            }
                            z2 = z8;
                        }
                    }
                }
            }
            z = true;
            break;
        }
        if (z || !z6) {
            throw C1358m.m2611a();
        }
        int i6 = i2 - i5;
        C1365t c1365tM1770a = null;
        C1365t c1365tM1770a2 = null;
        for (int i7 = 1; c1365tM1770a2 == null && i7 < i6; i7++) {
            c1365tM1770a2 = m1770a(i5, i4 - i7, i5 + i7, i4);
        }
        if (c1365tM1770a2 == null) {
            throw C1358m.m2611a();
        }
        C1365t c1365tM1770a3 = null;
        for (int i8 = 1; c1365tM1770a3 == null && i8 < i6; i8++) {
            c1365tM1770a3 = m1770a(i5, i3 + i8, i5 + i8, i3);
        }
        if (c1365tM1770a3 == null) {
            throw C1358m.m2611a();
        }
        C1365t c1365tM1770a4 = null;
        for (int i9 = 1; c1365tM1770a4 == null && i9 < i6; i9++) {
            c1365tM1770a4 = m1770a(i2, i3 + i9, i2 - i9, i3);
        }
        if (c1365tM1770a4 == null) {
            throw C1358m.m2611a();
        }
        for (int i10 = 1; c1365tM1770a == null && i10 < i6; i10++) {
            c1365tM1770a = m1770a(i2, i4 - i10, i2 - i10, i4);
        }
        if (c1365tM1770a == null) {
            throw C1358m.m2611a();
        }
        float f = c1365tM1770a.f2858a;
        float f2 = c1365tM1770a.f2859b;
        float f3 = c1365tM1770a2.f2858a;
        float f4 = c1365tM1770a2.f2859b;
        float f5 = c1365tM1770a4.f2858a;
        float f6 = c1365tM1770a4.f2859b;
        float f7 = c1365tM1770a3.f2858a;
        float f8 = c1365tM1770a3.f2859b;
        if (f < this.f2072e / 2.0f) {
            return new C1365t[]{new C1365t(f7 - 1.0f, f8 + 1.0f), new C1365t(f3 + 1.0f, f4 + 1.0f), new C1365t(f5 - 1.0f, f6 - 1.0f), new C1365t(f + 1.0f, f2 - 1.0f)};
        }
        return new C1365t[]{new C1365t(f7 + 1.0f, f8 + 1.0f), new C1365t(f3 + 1.0f, f4 - 1.0f), new C1365t(f5 - 1.0f, f6 + 1.0f), new C1365t(f - 1.0f, f2 - 1.0f)};
    }

    /* JADX INFO: renamed from: a */
    private C1365t m1770a(float f, float f2, float f3, float f4) {
        int iM1765a = C1191a.m1765a(C1191a.m1763a(f, f2, f3, f4));
        float f5 = iM1765a;
        float f6 = (f3 - f) / f5;
        float f7 = (f4 - f2) / f5;
        for (int i = 0; i < iM1765a; i++) {
            float f8 = i;
            int iM1765a2 = C1191a.m1765a((f8 * f6) + f);
            int iM1765a3 = C1191a.m1765a((f8 * f7) + f2);
            if (this.f2070c.m1789a(iM1765a2, iM1765a3)) {
                return new C1365t(iM1765a2, iM1765a3);
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    private boolean m1771a(int i, int i2, int i3, boolean z) {
        if (z) {
            while (i <= i2) {
                if (this.f2070c.m1789a(i, i3)) {
                    return true;
                }
                i++;
            }
            return false;
        }
        while (i <= i2) {
            if (this.f2070c.m1789a(i3, i)) {
                return true;
            }
            i++;
        }
        return false;
    }

    public C1193c(C1194b c1194b) throws C1358m {
        this(c1194b, 10, c1194b.f2077a / 2, c1194b.f2078b / 2);
    }

    /* JADX INFO: renamed from: a */
    private C1365t[] m1772a(C1365t c1365t, C1365t c1365t2, C1365t c1365t3, C1365t c1365t4) {
        float f = c1365t.f2858a;
        float f2 = c1365t.f2859b;
        float f3 = c1365t2.f2858a;
        float f4 = c1365t2.f2859b;
        float f5 = c1365t3.f2858a;
        float f6 = c1365t3.f2859b;
        float f7 = c1365t4.f2858a;
        float f8 = c1365t4.f2859b;
        if (f < this.f2072e / 2.0f) {
            return new C1365t[]{new C1365t(f7 - 1.0f, f8 + 1.0f), new C1365t(f3 + 1.0f, f4 + 1.0f), new C1365t(f5 - 1.0f, f6 - 1.0f), new C1365t(f + 1.0f, f2 - 1.0f)};
        }
        return new C1365t[]{new C1365t(f7 + 1.0f, f8 + 1.0f), new C1365t(f3 + 1.0f, f4 - 1.0f), new C1365t(f5 - 1.0f, f6 + 1.0f), new C1365t(f - 1.0f, f2 - 1.0f)};
    }
}
