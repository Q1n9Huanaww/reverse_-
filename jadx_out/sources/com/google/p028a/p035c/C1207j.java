package com.google.p028a.p035c;

import com.google.p028a.AbstractC1151b;
import com.google.p028a.AbstractC1355j;
import com.google.p028a.C1358m;
import java.lang.reflect.Array;

/* JADX INFO: renamed from: com.google.a.c.j */
/* JADX INFO: compiled from: HybridBinarizer.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1207j extends C1205h {

    /* JADX INFO: renamed from: b */
    private static final int f2155b = 3;

    /* JADX INFO: renamed from: c */
    private static final int f2156c = 8;

    /* JADX INFO: renamed from: d */
    private static final int f2157d = 7;

    /* JADX INFO: renamed from: e */
    private static final int f2158e = 40;

    /* JADX INFO: renamed from: f */
    private static final int f2159f = 24;

    /* JADX INFO: renamed from: g */
    private C1194b f2160g;

    /* JADX INFO: renamed from: a */
    private static int m1853a(int i, int i2) {
        if (i < 2) {
            return 2;
        }
        return i > i2 ? i2 : i;
    }

    public C1207j(AbstractC1355j abstractC1355j) {
        super(abstractC1355j);
    }

    @Override // com.google.p028a.p035c.C1205h, com.google.p028a.AbstractC1151b
    /* JADX INFO: renamed from: a */
    public final C1194b mo1564a() throws C1358m {
        C1207j c1207j;
        if (this.f2160g != null) {
            return this.f2160g;
        }
        AbstractC1355j abstractC1355j = this.f1937a;
        int i = abstractC1355j.f2820a;
        int i2 = abstractC1355j.f2821b;
        if (i >= 40 && i2 >= 40) {
            byte[] bArrMo2423a = abstractC1355j.mo2423a();
            int i3 = i >> 3;
            if ((i & 7) != 0) {
                i3++;
            }
            int i4 = i2 >> 3;
            if ((i2 & 7) != 0) {
                i4++;
            }
            int i5 = i2 - 8;
            int i6 = i - 8;
            int[][] iArr = (int[][]) Array.newInstance((Class<?>) int.class, i4, i3);
            int i7 = 0;
            while (true) {
                int i8 = 8;
                if (i7 >= i4) {
                    break;
                }
                int i9 = i7 << 3;
                if (i9 > i5) {
                    i9 = i5;
                }
                int i10 = 0;
                while (i10 < i3) {
                    int i11 = i10 << 3;
                    if (i11 > i6) {
                        i11 = i6;
                    }
                    int i12 = (i9 * i) + i11;
                    int i13 = 0;
                    int i14 = 255;
                    int i15 = 0;
                    int i16 = 0;
                    while (i13 < i8) {
                        int i17 = i14;
                        int i18 = i9;
                        int i19 = i16;
                        int i20 = 0;
                        while (i20 < i8) {
                            int i21 = bArrMo2423a[i12 + i20] & 255;
                            i15 += i21;
                            if (i21 < i17) {
                                i17 = i21;
                            }
                            if (i21 > i19) {
                                i19 = i21;
                            }
                            i20++;
                            i8 = 8;
                        }
                        if (i19 - i17 > 24) {
                            while (true) {
                                i13++;
                                i12 += i;
                                if (i13 < 8) {
                                    int i22 = 0;
                                    for (int i23 = 8; i22 < i23; i23 = 8) {
                                        i15 += bArrMo2423a[i12 + i22] & 255;
                                        i22++;
                                    }
                                }
                            }
                        }
                        i13++;
                        i12 += i;
                        i14 = i17;
                        i16 = i19;
                        i9 = i18;
                        i8 = 8;
                    }
                    int i24 = i9;
                    int i25 = i15 >> 6;
                    if (i16 - i14 <= 24) {
                        i25 = i14 / 2;
                        if (i7 > 0 && i10 > 0) {
                            int i26 = i7 - 1;
                            int i27 = i10 - 1;
                            int i28 = ((iArr[i26][i10] + (iArr[i7][i27] * 2)) + iArr[i26][i27]) / 4;
                            if (i14 < i28) {
                                i25 = i28;
                            }
                        }
                    }
                    iArr[i7][i10] = i25;
                    i10++;
                    i9 = i24;
                    i8 = 8;
                }
                i7++;
            }
            C1194b c1194b = new C1194b(i, i2);
            for (int i29 = 0; i29 < i4; i29++) {
                int i30 = i29 << 3;
                if (i30 > i5) {
                    i30 = i5;
                }
                int iM1853a = m1853a(i29, i4 - 3);
                int i31 = 0;
                while (i31 < i3) {
                    int i32 = i31 << 3;
                    if (i32 > i6) {
                        i32 = i6;
                    }
                    int iM1853a2 = m1853a(i31, i3 - 3);
                    int i33 = -2;
                    int i34 = i3;
                    int i35 = 0;
                    for (int i36 = 2; i33 <= i36; i36 = 2) {
                        int[] iArr2 = iArr[iM1853a + i33];
                        i35 += iArr2[iM1853a2 - 2] + iArr2[iM1853a2 - 1] + iArr2[iM1853a2] + iArr2[iM1853a2 + 1] + iArr2[iM1853a2 + 2];
                        i33++;
                    }
                    int i37 = i35 / 25;
                    int i38 = (i30 * i) + i32;
                    int i39 = 0;
                    while (true) {
                        if (i39 < 8) {
                            int i40 = i4;
                            int i41 = 0;
                            for (int i42 = 8; i41 < i42; i42 = 8) {
                                byte[] bArr = bArrMo2423a;
                                if ((bArrMo2423a[i38 + i41] & 255) <= i37) {
                                    c1194b.m1790b(i32 + i41, i30 + i39);
                                }
                                i41++;
                                bArrMo2423a = bArr;
                            }
                            i39++;
                            i38 += i;
                            i4 = i40;
                        }
                    }
                    i31++;
                    i3 = i34;
                }
            }
            c1207j = this;
            c1207j.f2160g = c1194b;
        } else {
            c1207j = this;
            c1207j.f2160g = super.mo1564a();
        }
        return c1207j.f2160g;
    }

    @Override // com.google.p028a.p035c.C1205h, com.google.p028a.AbstractC1151b
    /* JADX INFO: renamed from: a */
    public final AbstractC1151b mo1562a(AbstractC1355j abstractC1355j) {
        return new C1207j(abstractC1355j);
    }

    /* JADX INFO: renamed from: a */
    private static void m1855a(byte[] bArr, int i, int i2, int i3, int i4, int[][] iArr, C1194b c1194b) {
        int i5 = 8;
        int i6 = i4 - 8;
        int i7 = i3 - 8;
        int i8 = 0;
        while (i8 < i2) {
            int i9 = i8 << 3;
            if (i9 > i6) {
                i9 = i6;
            }
            int iM1853a = m1853a(i8, i2 - 3);
            int i10 = 0;
            while (i10 < i) {
                int i11 = i10 << 3;
                if (i11 > i7) {
                    i11 = i7;
                }
                int iM1853a2 = m1853a(i10, i - 3);
                int i12 = 0;
                for (int i13 = -2; i13 <= 2; i13++) {
                    int[] iArr2 = iArr[iM1853a + i13];
                    i12 += iArr2[iM1853a2 - 2] + iArr2[iM1853a2 - 1] + iArr2[iM1853a2] + iArr2[iM1853a2 + 1] + iArr2[2 + iM1853a2];
                }
                int i14 = i12 / 25;
                int i15 = (i9 * i3) + i11;
                int i16 = 0;
                while (i16 < i5) {
                    int i17 = 0;
                    while (i17 < i5) {
                        if ((bArr[i15 + i17] & 255) <= i14) {
                            c1194b.m1790b(i11 + i17, i9 + i16);
                        }
                        i17++;
                        i5 = 8;
                    }
                    i16++;
                    i15 += i3;
                    i5 = 8;
                }
                i10++;
                i = i;
                i5 = 8;
            }
            i8++;
            i5 = 8;
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m1854a(byte[] bArr, int i, int i2, int i3, int i4, C1194b c1194b) {
        int i5 = (i2 * i4) + i;
        int i6 = 0;
        while (i6 < 8) {
            for (int i7 = 0; i7 < 8; i7++) {
                if ((bArr[i5 + i7] & 255) <= i3) {
                    c1194b.m1790b(i + i7, i2 + i6);
                }
            }
            i6++;
            i5 += i4;
        }
    }

    /* JADX INFO: renamed from: a */
    private static int[][] m1856a(byte[] bArr, int i, int i2, int i3, int i4) {
        int i5 = i;
        int i6 = 8;
        int i7 = i4 - 8;
        int i8 = i3 - 8;
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) int.class, i2, i5);
        int i9 = 0;
        while (i9 < i2) {
            int i10 = i9 << 3;
            if (i10 > i7) {
                i10 = i7;
            }
            int i11 = 0;
            while (i11 < i5) {
                int i12 = i11 << 3;
                if (i12 > i8) {
                    i12 = i8;
                }
                int i13 = (i10 * i3) + i12;
                int i14 = 255;
                int i15 = 0;
                int i16 = 0;
                int i17 = 0;
                while (i15 < i6) {
                    int i18 = i17;
                    int i19 = i14;
                    int i20 = 0;
                    while (i20 < i6) {
                        int i21 = bArr[i13 + i20] & 255;
                        i16 += i21;
                        if (i21 < i19) {
                            i19 = i21;
                        }
                        if (i21 > i18) {
                            i18 = i21;
                        }
                        i20++;
                        i6 = 8;
                    }
                    if (i18 - i19 > 24) {
                        while (true) {
                            i15++;
                            i13 += i3;
                            if (i15 < 8) {
                                int i22 = 0;
                                for (int i23 = 8; i22 < i23; i23 = 8) {
                                    i16 += bArr[i13 + i22] & 255;
                                    i22++;
                                }
                            }
                        }
                    }
                    i15++;
                    i13 += i3;
                    i14 = i19;
                    i6 = 8;
                    i17 = i18;
                }
                int i24 = i16 >> 6;
                if (i17 - i14 <= 24) {
                    i24 = i14 / 2;
                    if (i9 > 0 && i11 > 0) {
                        int i25 = i9 - 1;
                        int i26 = i11 - 1;
                        int i27 = ((iArr[i25][i11] + (iArr[i9][i26] * 2)) + iArr[i25][i26]) / 4;
                        if (i14 < i27) {
                            i24 = i27;
                        }
                    }
                }
                iArr[i9][i11] = i24;
                i11++;
                i5 = i;
                i6 = 8;
            }
            i9++;
            i5 = i;
            i6 = 8;
        }
        return iArr;
    }
}
