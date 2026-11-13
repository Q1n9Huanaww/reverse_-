package com.google.p028a.p035c.p036a;

import com.google.p028a.C1358m;
import com.google.p028a.C1365t;
import com.google.p028a.p035c.C1194b;

/* JADX INFO: renamed from: com.google.a.c.a.b */
/* JADX INFO: compiled from: MonochromeRectangleDetector.java */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class C1192b {

    /* JADX INFO: renamed from: a */
    private static final int f2066a = 32;

    /* JADX INFO: renamed from: b */
    private final C1194b f2067b;

    private C1192b(C1194b c1194b) {
        this.f2067b = c1194b;
    }

    /* JADX INFO: renamed from: a */
    private C1365t[] m1769a() throws C1358m {
        int i = this.f2067b.f2078b;
        int i2 = this.f2067b.f2077a;
        int i3 = i / 2;
        int i4 = i2 / 2;
        int iMax = Math.max(1, i / 256);
        int iMax2 = Math.max(1, i2 / 256);
        int i5 = -iMax;
        int i6 = i4 / 2;
        int i7 = ((int) m1767a(i4, 0, 0, i2, i3, i5, 0, i, i6).f2859b) - 1;
        int i8 = i3 / 2;
        C1365t c1365tM1767a = m1767a(i4, -iMax2, 0, i2, i3, 0, i7, i, i8);
        int i9 = ((int) c1365tM1767a.f2858a) - 1;
        C1365t c1365tM1767a2 = m1767a(i4, iMax2, i9, i2, i3, 0, i7, i, i8);
        int i10 = ((int) c1365tM1767a2.f2858a) + 1;
        C1365t c1365tM1767a3 = m1767a(i4, 0, i9, i10, i3, iMax, i7, i, i6);
        return new C1365t[]{m1767a(i4, 0, i9, i10, i3, i5, i7, ((int) c1365tM1767a3.f2859b) + 1, i4 / 4), c1365tM1767a, c1365tM1767a2, c1365tM1767a3};
    }

    /* JADX INFO: renamed from: a */
    private C1365t m1767a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) throws C1358m {
        int[] iArrM1768a;
        int i10 = i;
        int i11 = i5;
        int[] iArr = null;
        while (i11 < i8 && i11 >= i7 && i10 < i4 && i10 >= i3) {
            if (i2 == 0) {
                iArrM1768a = m1768a(i11, i9, i3, i4, true);
            } else {
                iArrM1768a = m1768a(i10, i9, i7, i8, false);
            }
            if (iArrM1768a == null) {
                if (iArr == null) {
                    throw C1358m.m2611a();
                }
                if (i2 == 0) {
                    int i12 = i11 - i6;
                    if (iArr[0] < i) {
                        if (iArr[1] > i) {
                            return new C1365t(iArr[i6 > 0 ? (char) 0 : (char) 1], i12);
                        }
                        return new C1365t(iArr[0], i12);
                    }
                    return new C1365t(iArr[1], i12);
                }
                int i13 = i10 - i2;
                if (iArr[0] < i5) {
                    if (iArr[1] > i5) {
                        return new C1365t(i13, iArr[i2 < 0 ? (char) 0 : (char) 1]);
                    }
                    return new C1365t(i13, iArr[0]);
                }
                return new C1365t(i13, iArr[1]);
            }
            i11 += i6;
            i10 += i2;
            iArr = iArrM1768a;
        }
        throw C1358m.m2611a();
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0022 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:37:0x005c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:64:0x0035 A[EDGE_INSN: B:64:0x0035->B:20:0x0035 BREAK  A[LOOP:1: B:12:0x001e->B:69:0x001e], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:65:0x0035 A[EDGE_INSN: B:65:0x0035->B:20:0x0035 BREAK  A[LOOP:1: B:12:0x001e->B:69:0x001e], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:66:0x002d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:67:0x0035 A[EDGE_INSN: B:67:0x0035->B:20:0x0035 BREAK  A[LOOP:1: B:12:0x001e->B:69:0x001e], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:68:0x0024 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:70:0x001e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:71:0x001e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:82:0x006f A[EDGE_INSN: B:82:0x006f->B:43:0x006f BREAK  A[LOOP:3: B:35:0x0059->B:87:0x0059], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:83:0x0067 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:84:0x006f A[EDGE_INSN: B:84:0x006f->B:43:0x006f BREAK  A[LOOP:3: B:35:0x0059->B:87:0x0059], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:85:0x005e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:86:0x006f A[EDGE_INSN: B:86:0x006f->B:43:0x006f BREAK  A[LOOP:3: B:35:0x0059->B:87:0x0059], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:88:0x0059 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:89:0x0059 A[SYNTHETIC] */
    /* JADX INFO: renamed from: a */
    private int[] m1768a(int i, int i2, int i3, int i4, boolean z) {
        int i5;
        int i6;
        int i7 = (i3 + i4) / 2;
        int i8 = i7;
        while (i8 >= i3) {
            if (!z) {
                if (!this.f2067b.m1789a(i, i8)) {
                    i6 = i8;
                    while (true) {
                        i6--;
                        if (i6 < i3) {
                            break;
                            break;
                        }
                        if (z) {
                            if (this.f2067b.m1789a(i6, i)) {
                                break;
                                break;
                            }
                        } else if (this.f2067b.m1789a(i, i6)) {
                            break;
                            break;
                        }
                    }
                    int i9 = i8 - i6;
                    if (i6 < i3) {
                        break;
                    }
                    break;
                    break;
                }
                i8--;
            } else if (this.f2067b.m1789a(i8, i)) {
                i8--;
            } else {
                i6 = i8;
                while (true) {
                    i6--;
                    if (i6 < i3) {
                        break;
                    }
                    if (z) {
                        if (this.f2067b.m1789a(i6, i)) {
                            break;
                        }
                    } else if (this.f2067b.m1789a(i, i6)) {
                        break;
                    }
                }
                int i10 = i8 - i6;
                if (i6 < i3 || i10 > i2) {
                    break;
                }
                i8 = i6;
            }
        }
        int i11 = i8 + 1;
        while (i7 < i4) {
            if (!z) {
                if (!this.f2067b.m1789a(i, i7)) {
                    i5 = i7;
                    while (true) {
                        i5++;
                        if (i5 >= i4) {
                            break;
                            break;
                        }
                        if (z) {
                            if (this.f2067b.m1789a(i5, i)) {
                                break;
                                break;
                            }
                        } else if (this.f2067b.m1789a(i, i5)) {
                            break;
                            break;
                        }
                    }
                    int i12 = i5 - i7;
                    if (i5 >= i4) {
                        break;
                    }
                    break;
                    break;
                }
                i7++;
            } else if (this.f2067b.m1789a(i7, i)) {
                i7++;
            } else {
                i5 = i7;
                while (true) {
                    i5++;
                    if (i5 >= i4) {
                        break;
                    }
                    if (z) {
                        if (this.f2067b.m1789a(i5, i)) {
                            break;
                        }
                    } else if (this.f2067b.m1789a(i, i5)) {
                        break;
                    }
                }
                int i13 = i5 - i7;
                if (i5 >= i4 || i13 > i2) {
                    break;
                }
                i7 = i5;
            }
        }
        int i14 = i7 - 1;
        if (i14 > i11) {
            return new int[]{i11, i14};
        }
        return null;
    }
}
