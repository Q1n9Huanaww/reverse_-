package com.google.p028a.p051h.p052a;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.p028a.C1210d;
import com.google.p028a.C1303h;
import com.google.p028a.C1358m;
import com.google.p028a.C1365t;
import com.google.p028a.p035c.C1194b;
import com.google.p028a.p035c.C1202e;
import com.google.p028a.p035c.p036a.C1191a;
import com.google.p028a.p051h.C1304a;
import com.google.p028a.p051h.p052a.p053a.C1306a;
import com.google.p028a.p051h.p052a.p053a.C1307b;
import com.google.p028a.p051h.p052a.p053a.C1308c;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Formatter;

/* JADX INFO: renamed from: com.google.a.h.a.j */
/* JADX INFO: compiled from: PDF417ScanningDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1317j {

    /* JADX INFO: renamed from: a */
    private static final int f2629a = 2;

    /* JADX INFO: renamed from: b */
    private static final int f2630b = 3;

    /* JADX INFO: renamed from: c */
    private static final int f2631c = 512;

    /* JADX INFO: renamed from: d */
    private static final C1306a f2632d = new C1306a();

    /* JADX INFO: renamed from: a */
    private static int m2318a(int i) {
        return 2 << i;
    }

    /* JADX INFO: renamed from: a */
    private static boolean m2333a(int i, int i2, int i3) {
        return i2 + (-2) <= i && i <= i3 + 2;
    }

    private C1317j() {
    }

    /* JADX WARN: Code duplicated, block: B:122:0x01af  */
    /* JADX WARN: Code duplicated, block: B:124:0x01b2  */
    /* JADX WARN: Code duplicated, block: B:127:0x01ce  */
    /* JADX WARN: Code duplicated, block: B:128:0x01e3  */
    /* JADX WARN: Code duplicated, block: B:12:0x003f  */
    /* JADX WARN: Code duplicated, block: B:165:0x029d  */
    /* JADX WARN: Code duplicated, block: B:29:0x0069  */
    /* JADX INFO: renamed from: a */
    public static C1202e m2323a(C1194b c1194b, C1365t c1365t, C1365t c1365t2, C1365t c1365t3, C1365t c1365t4, int i, int i2) throws C1210d, C1358m, C1303h {
        C1315h c1315h;
        int i3;
        int i4;
        boolean z;
        int i5;
        int i6;
        C1314g c1315h2;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        C1311d c1311dM2327a;
        C1305a c1305aM2311a;
        C1305a c1305aM2311a2;
        C1310c c1310c = new C1310c(c1194b, c1365t, c1365t2, c1365t3, c1365t4);
        int i12 = 0;
        C1315h c1315hM2329a = null;
        C1313f c1313f = null;
        C1315h c1315hM2329a2 = null;
        while (true) {
            if (i12 >= 2) {
                c1315h = c1315hM2329a;
                break;
            }
            if (c1365t != null) {
                c1315hM2329a = m2329a(c1194b, c1310c, c1365t, true, i, i2);
            }
            c1315h = c1315hM2329a;
            if (c1365t3 != null) {
                c1315hM2329a2 = m2329a(c1194b, c1310c, c1365t3, false, i, i2);
            }
            if (c1315h == null && c1315hM2329a2 == null) {
                c1313f = null;
            } else {
                if (c1315h == null || (c1305aM2311a = c1315h.m2311a()) == null) {
                    if (c1315hM2329a2 == null) {
                        c1305aM2311a = null;
                    } else {
                        c1305aM2311a = c1315hM2329a2.m2311a();
                    }
                } else if (c1315hM2329a2 != null && (c1305aM2311a2 = c1315hM2329a2.m2311a()) != null && c1305aM2311a.f2551a != c1305aM2311a2.f2551a && c1305aM2311a.f2552b != c1305aM2311a2.f2552b && c1305aM2311a.f2555e != c1305aM2311a2.f2555e) {
                    c1305aM2311a = null;
                }
                if (c1305aM2311a == null) {
                    c1313f = null;
                } else {
                    C1310c c1310cM2326a = m2326a(c1315h);
                    C1310c c1310cM2326a2 = m2326a(c1315hM2329a2);
                    if (c1310cM2326a == null) {
                        c1310cM2326a = c1310cM2326a2;
                    } else if (c1310cM2326a2 != null) {
                        c1310cM2326a = new C1310c(c1310cM2326a.f2566a, c1310cM2326a.f2567b, c1310cM2326a.f2568c, c1310cM2326a2.f2569d, c1310cM2326a2.f2570e);
                    }
                    c1313f = new C1313f(c1305aM2311a, c1310cM2326a);
                }
            }
            if (c1313f == null) {
                throw C1358m.m2611a();
            }
            if (i12 == 0 && c1313f.f2622c != null && (c1313f.f2622c.f2573h < c1310c.f2573h || c1313f.f2622c.f2574i > c1310c.f2574i)) {
                c1310c = c1313f.f2622c;
                i12++;
                c1315hM2329a = c1315h;
            } else {
                c1313f.f2622c = c1310c;
                break;
            }
        }
        int i13 = c1313f.f2623d + 1;
        c1313f.f2621b[0] = c1315h;
        c1313f.f2621b[i13] = c1315hM2329a2;
        boolean z2 = c1315h != null;
        int i14 = i;
        int i15 = i2;
        int i16 = 1;
        while (i16 <= i13) {
            int i17 = z2 ? i16 : i13 - i16;
            if (c1313f.f2621b[i17] == null) {
                if (i17 == 0 || i17 == i13) {
                    c1315h2 = new C1315h(c1310c, i17 == 0);
                } else {
                    c1315h2 = new C1314g(c1310c);
                }
                c1313f.f2621b[i17] = c1315h2;
                int i18 = c1310c.f2573h;
                int iMax = i15;
                int iMin = i14;
                int i19 = -1;
                while (i18 <= c1310c.f2574i) {
                    int i20 = z2 ? 1 : -1;
                    int i21 = i17 - i20;
                    C1311d c1311dM2305c = m2334a(c1313f, i21) ? c1313f.f2621b[i21].m2305c(i18) : null;
                    if (c1311dM2305c == null) {
                        C1311d c1311dM2302a = c1313f.f2621b[i17].m2302a(i18);
                        if (c1311dM2302a == null) {
                            if (m2334a(c1313f, i21)) {
                                c1311dM2302a = c1313f.f2621b[i21].m2302a(i18);
                            }
                            if (c1311dM2302a == null) {
                                int i22 = i17;
                                int i23 = 0;
                                while (true) {
                                    int i24 = i22 - i20;
                                    if (!m2334a(c1313f, i24)) {
                                        i7 = i13;
                                        if (z2) {
                                            i8 = c1313f.f2622c.f2571f;
                                            break;
                                        }
                                        i8 = c1313f.f2622c.f2572g;
                                        break;
                                    }
                                    C1311d[] c1311dArr = c1313f.f2621b[i24].f2626b;
                                    int length = c1311dArr.length;
                                    int i25 = 0;
                                    while (i25 < length) {
                                        i7 = i13;
                                        C1311d c1311d = c1311dArr[i25];
                                        if (c1311d != null) {
                                            if (z2) {
                                                i9 = c1311d.f2577b;
                                            } else {
                                                i9 = c1311d.f2576a;
                                            }
                                            i8 = (i20 * i23 * (c1311d.f2577b - c1311d.f2576a)) + i9;
                                            break;
                                        }
                                        i25++;
                                        i13 = i7;
                                    }
                                    i23++;
                                    i22 = i24;
                                }
                            } else if (z2) {
                                i8 = c1311dM2302a.f2577b;
                            } else {
                                i8 = c1311dM2302a.f2576a;
                            }
                            if (i8 >= 0 || i8 > c1310c.f2572g) {
                                if (i19 != -1) {
                                    i11 = i19;
                                } else {
                                    i10 = iMax;
                                }
                                iMax = i10;
                                i18++;
                                i13 = i7;
                            } else {
                                i11 = i8;
                            }
                            i10 = iMax;
                            c1311dM2327a = m2327a(c1194b, c1310c.f2571f, c1310c.f2572g, z2, i11, i18, iMin, i10);
                            if (c1311dM2327a != null) {
                                c1315h2.m2303a(i18, c1311dM2327a);
                                iMin = Math.min(iMin, c1311dM2327a.m2274c());
                                iMax = Math.max(i10, c1311dM2327a.m2274c());
                                i19 = i11;
                            } else {
                                iMax = i10;
                            }
                            i18++;
                            i13 = i7;
                        } else if (z2) {
                            i8 = c1311dM2302a.f2576a;
                        } else {
                            i8 = c1311dM2302a.f2577b;
                        }
                    } else if (z2) {
                        i8 = c1311dM2305c.f2577b;
                    } else {
                        i8 = c1311dM2305c.f2576a;
                    }
                    i7 = i13;
                    if (i8 >= 0) {
                        if (i19 != -1) {
                            i11 = i19;
                            i10 = iMax;
                            c1311dM2327a = m2327a(c1194b, c1310c.f2571f, c1310c.f2572g, z2, i11, i18, iMin, i10);
                            if (c1311dM2327a != null) {
                                c1315h2.m2303a(i18, c1311dM2327a);
                                iMin = Math.min(iMin, c1311dM2327a.m2274c());
                                iMax = Math.max(i10, c1311dM2327a.m2274c());
                                i19 = i11;
                            }
                            i18++;
                            i13 = i7;
                        } else {
                            i10 = iMax;
                        }
                    } else if (i19 != -1) {
                        i11 = i19;
                        i10 = iMax;
                        c1311dM2327a = m2327a(c1194b, c1310c.f2571f, c1310c.f2572g, z2, i11, i18, iMin, i10);
                        if (c1311dM2327a != null) {
                            c1315h2.m2303a(i18, c1311dM2327a);
                            iMin = Math.min(iMin, c1311dM2327a.m2274c());
                            iMax = Math.max(i10, c1311dM2327a.m2274c());
                            i19 = i11;
                        }
                        i18++;
                        i13 = i7;
                    } else {
                        i10 = iMax;
                    }
                    iMax = i10;
                    i18++;
                    i13 = i7;
                }
                i6 = i13;
                i14 = iMin;
                i15 = iMax;
            } else {
                i6 = i13;
            }
            i16++;
            i13 = i6;
        }
        C1309b[][] c1309bArr = (C1309b[][]) Array.newInstance((Class<?>) C1309b.class, c1313f.f2620a.f2555e, c1313f.f2623d + 2);
        for (int i26 = 0; i26 < c1309bArr.length; i26++) {
            for (int i27 = 0; i27 < c1309bArr[i26].length; i27++) {
                c1309bArr[i26][i27] = new C1309b();
            }
        }
        char c = 0;
        c1313f.m2299a(c1313f.f2621b[0]);
        int i28 = 1;
        c1313f.m2299a(c1313f.f2621b[c1313f.f2623d + 1]);
        int i29 = C1304a.f2542b;
        while (true) {
            if (c1313f.f2621b[c] != null && c1313f.f2621b[c1313f.f2623d + i28] != null) {
                C1311d[] c1311dArr2 = c1313f.f2621b[c].f2626b;
                C1311d[] c1311dArr3 = c1313f.f2621b[c1313f.f2623d + i28].f2626b;
                for (int i30 = 0; i30 < c1311dArr2.length; i30++) {
                    if (c1311dArr2[i30] != null && c1311dArr3[i30] != null && c1311dArr2[i30].f2580e == c1311dArr3[i30].f2580e) {
                        for (int i31 = 1; i31 <= c1313f.f2623d; i31++) {
                            C1311d c1311d2 = c1313f.f2621b[i31].f2626b[i30];
                            if (c1311d2 != null) {
                                c1311d2.f2580e = c1311dArr2[i30].f2580e;
                                if (!c1311d2.m2271a()) {
                                    c1313f.f2621b[i31].f2626b[i30] = null;
                                }
                            }
                        }
                    }
                }
            }
            if (c1313f.f2621b[0] == null) {
                i3 = 0;
            } else {
                C1311d[] c1311dArr4 = c1313f.f2621b[0].f2626b;
                i3 = 0;
                for (int i32 = 0; i32 < c1311dArr4.length; i32++) {
                    if (c1311dArr4[i32] != null) {
                        int i33 = c1311dArr4[i32].f2580e;
                        int i34 = i3;
                        int iM2283a = 0;
                        for (int i35 = 1; i35 < c1313f.f2623d + 1 && iM2283a < 2; i35++) {
                            C1311d c1311d3 = c1313f.f2621b[i35].f2626b[i32];
                            if (c1311d3 != null) {
                                iM2283a = C1313f.m2283a(i33, iM2283a, c1311d3);
                                if (!c1311d3.m2271a()) {
                                    i34++;
                                }
                            }
                        }
                        i3 = i34;
                    }
                }
            }
            int i36 = 1;
            if (c1313f.f2621b[c1313f.f2623d + 1] == null) {
                i4 = 0;
            } else {
                C1311d[] c1311dArr5 = c1313f.f2621b[c1313f.f2623d + 1].f2626b;
                int i37 = 0;
                i4 = 0;
                while (i37 < c1311dArr5.length) {
                    if (c1311dArr5[i37] != null) {
                        int i38 = c1311dArr5[i37].f2580e;
                        int iM2283a2 = 0;
                        for (int i39 = c1313f.f2623d + i36; i39 > 0 && iM2283a2 < 2; i39--) {
                            C1311d c1311d4 = c1313f.f2621b[i39].f2626b[i37];
                            if (c1311d4 != null) {
                                iM2283a2 = C1313f.m2283a(i38, iM2283a2, c1311d4);
                                if (!c1311d4.m2271a()) {
                                    i4++;
                                }
                            }
                        }
                    }
                    i37++;
                    i36 = 1;
                }
            }
            int i40 = i3 + i4;
            if (i40 == 0) {
                i40 = 0;
            } else {
                for (int i41 = 1; i41 < c1313f.f2623d + 1; i41++) {
                    C1311d[] c1311dArr6 = c1313f.f2621b[i41].f2626b;
                    for (int i42 = 0; i42 < c1311dArr6.length; i42++) {
                        if (c1311dArr6[i42] != null && !c1311dArr6[i42].m2271a()) {
                            C1311d c1311d5 = c1311dArr6[i42];
                            C1311d[] c1311dArr7 = c1313f.f2621b[i41 - 1].f2626b;
                            int i43 = i41 + 1;
                            C1311d[] c1311dArr8 = c1313f.f2621b[i43] != null ? c1313f.f2621b[i43].f2626b : c1311dArr7;
                            C1311d[] c1311dArr9 = new C1311d[14];
                            c1311dArr9[2] = c1311dArr7[i42];
                            c1311dArr9[3] = c1311dArr8[i42];
                            if (i42 > 0) {
                                int i44 = i42 - 1;
                                c1311dArr9[0] = c1311dArr6[i44];
                                c1311dArr9[4] = c1311dArr7[i44];
                                c1311dArr9[5] = c1311dArr8[i44];
                            }
                            if (i42 > 1) {
                                int i45 = i42 - 2;
                                c1311dArr9[8] = c1311dArr6[i45];
                                c1311dArr9[10] = c1311dArr7[i45];
                                c1311dArr9[11] = c1311dArr8[i45];
                            }
                            if (i42 < c1311dArr6.length - 1) {
                                int i46 = i42 + 1;
                                c1311dArr9[1] = c1311dArr6[i46];
                                c1311dArr9[6] = c1311dArr7[i46];
                                c1311dArr9[7] = c1311dArr8[i46];
                            }
                            if (i42 < c1311dArr6.length - 2) {
                                int i47 = i42 + 2;
                                c1311dArr9[9] = c1311dArr6[i47];
                                c1311dArr9[12] = c1311dArr7[i47];
                                c1311dArr9[13] = c1311dArr8[i47];
                            }
                            for (int i48 = 0; i48 < 14; i48++) {
                                C1311d c1311d6 = c1311dArr9[i48];
                                if (c1311d6 != null && c1311d6.m2271a() && c1311d6.f2578c == c1311d5.f2578c) {
                                    c1311d5.f2580e = c1311d6.f2580e;
                                    z = true;
                                } else {
                                    z = false;
                                }
                                if (z) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if (i40 <= 0 || i40 >= i29) {
                break;
            }
            i29 = i40;
            c = 0;
            i28 = 1;
        }
        int i49 = 0;
        for (C1314g c1314g : c1313f.f2621b) {
            if (c1314g != null) {
                for (C1311d c1311d7 : c1314g.f2626b) {
                    if (c1311d7 != null && (i5 = c1311d7.f2580e) >= 0 && i5 < c1309bArr.length) {
                        c1309bArr[i5][i49].m2253a(c1311d7.f2579d);
                    }
                }
            }
            i49++;
        }
        C1309b c1309b = c1309bArr[0][1];
        int[] iArrM2254a = c1309b.m2254a();
        int i50 = (c1313f.f2623d * c1313f.f2620a.f2555e) - (2 << c1313f.f2620a.f2552b);
        if (iArrM2254a.length == 0) {
            if (i50 <= 0 || i50 > 928) {
                throw C1358m.m2611a();
            }
            c1309b.m2253a(i50);
        } else if (iArrM2254a[0] != i50) {
            c1309b.m2253a(i50);
        }
        ArrayList arrayList = new ArrayList();
        int[] iArr = new int[c1313f.f2620a.f2555e * c1313f.f2623d];
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (int i51 = 0; i51 < c1313f.f2620a.f2555e; i51++) {
            int i52 = 0;
            while (i52 < c1313f.f2623d) {
                int i53 = i52 + 1;
                int[] iArrM2254a2 = c1309bArr[i51][i53].m2254a();
                int i54 = (c1313f.f2623d * i51) + i52;
                if (iArrM2254a2.length == 0) {
                    arrayList.add(Integer.valueOf(i54));
                } else if (iArrM2254a2.length == 1) {
                    iArr[i54] = iArrM2254a2[0];
                } else {
                    arrayList3.add(Integer.valueOf(i54));
                    arrayList2.add(iArrM2254a2);
                }
                i52 = i53;
            }
        }
        int[][] iArr2 = new int[arrayList2.size()][];
        for (int i55 = 0; i55 < iArr2.length; i55++) {
            iArr2[i55] = (int[]) arrayList2.get(i55);
        }
        return m2322a(c1313f.f2620a.f2552b, iArr, C1304a.m2221a(arrayList), C1304a.m2221a(arrayList3), iArr2);
    }

    /* JADX INFO: renamed from: a */
    private static int m2320a(int[] iArr) {
        int iMax = -1;
        for (int i : iArr) {
            iMax = Math.max(iMax, i);
        }
        return iMax;
    }

    /* JADX INFO: renamed from: b */
    private static C1305a m2338b(C1315h c1315h, C1315h c1315h2) {
        C1305a c1305aM2311a;
        C1305a c1305aM2311a2;
        if (c1315h == null || (c1305aM2311a = c1315h.m2311a()) == null) {
            if (c1315h2 == null) {
                return null;
            }
            return c1315h2.m2311a();
        }
        if (c1315h2 == null || (c1305aM2311a2 = c1315h2.m2311a()) == null || c1305aM2311a.f2551a == c1305aM2311a2.f2551a || c1305aM2311a.f2552b == c1305aM2311a2.f2552b || c1305aM2311a.f2555e == c1305aM2311a2.f2555e) {
            return c1305aM2311a;
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    private static C1315h m2329a(C1194b c1194b, C1310c c1310c, C1365t c1365t, boolean z, int i, int i2) {
        C1315h c1315h = new C1315h(c1310c, z);
        int i3 = 0;
        while (i3 < 2) {
            int i4 = i3 == 0 ? 1 : -1;
            int i5 = (int) c1365t.f2858a;
            for (int i6 = (int) c1365t.f2859b; i6 <= c1310c.f2574i && i6 >= c1310c.f2573h; i6 += i4) {
                C1311d c1311dM2327a = m2327a(c1194b, 0, c1194b.f2077a, z, i5, i6, i, i2);
                if (c1311dM2327a != null) {
                    c1315h.m2303a(i6, c1311dM2327a);
                    if (z) {
                        i5 = c1311dM2327a.f2576a;
                    } else {
                        i5 = c1311dM2327a.f2577b;
                    }
                }
            }
            i3++;
        }
        return c1315h;
    }

    /* JADX INFO: renamed from: a */
    private static void m2331a(C1313f c1313f, C1309b[][] c1309bArr) throws C1358m {
        C1309b c1309b = c1309bArr[0][1];
        int[] iArrM2254a = c1309b.m2254a();
        int i = (c1313f.f2623d * c1313f.f2620a.f2555e) - (2 << c1313f.f2620a.f2552b);
        if (iArrM2254a.length != 0) {
            if (iArrM2254a[0] != i) {
                c1309b.m2253a(i);
            }
        } else {
            if (i <= 0 || i > 928) {
                throw C1358m.m2611a();
            }
            c1309b.m2253a(i);
        }
    }

    /* JADX INFO: renamed from: a */
    private static C1202e m2322a(int i, int[] iArr, int[] iArr2, int[] iArr3, int[][] iArr4) throws C1210d, C1303h {
        int i2;
        int length;
        int i3;
        C1308c c1308c;
        int[] iArr5 = iArr3;
        int[] iArr6 = new int[iArr5.length];
        int i4 = 100;
        while (true) {
            int i5 = i4 - 1;
            if (i4 > 0) {
                for (int i6 = 0; i6 < iArr6.length; i6++) {
                    iArr[iArr5[i6]] = iArr4[i6][iArr6[i6]];
                }
                int i7 = 1;
                try {
                    if (iArr.length == 0) {
                        throw C1303h.m2217a();
                    }
                    int i8 = 1 << (i + 1);
                    try {
                        if ((iArr2 == null || iArr2.length <= (i8 / 2) + 3) && i8 >= 0 && i8 <= 512) {
                            C1306a c1306a = f2632d;
                            C1308c c1308c2 = new C1308c(c1306a.f2556a, iArr);
                            int[] iArr7 = new int[i8];
                            boolean z = false;
                            for (int i9 = i8; i9 > 0; i9--) {
                                int iM2247b = c1308c2.m2247b(c1306a.f2556a.f2558b[i9]);
                                iArr7[i8 - i9] = iM2247b;
                                if (iM2247b != 0) {
                                    z = true;
                                }
                            }
                            if (z) {
                                C1308c c1308c3 = c1306a.f2556a.f2561e;
                                if (iArr2 != null) {
                                    int length2 = iArr2.length;
                                    C1308c c1308cM2251c = c1308c3;
                                    int i10 = 0;
                                    while (i10 < length2) {
                                        int i11 = i5;
                                        c1308cM2251c = c1308cM2251c.m2251c(new C1308c(c1306a.f2556a, new int[]{c1306a.f2556a.m2239c(0, c1306a.f2556a.f2558b[(iArr.length - i7) - iArr2[i10]]), 1}));
                                        i10++;
                                        i5 = i11;
                                        length2 = length2;
                                        i7 = 1;
                                    }
                                }
                                C1308c c1308c4 = new C1308c(c1306a.f2556a, iArr7);
                                C1308c c1308cM2237a = c1306a.f2556a.m2237a(i8, 1);
                                if (c1308cM2237a.f2564b.length - 1 >= c1308c4.f2564b.length - 1) {
                                    c1308cM2237a = c1308c4;
                                    c1308c4 = c1308cM2237a;
                                }
                                C1308c c1308c5 = c1306a.f2556a.f2560d;
                                C1308c c1308cM2248b = c1306a.f2556a.f2561e;
                                C1308c c1308c6 = c1308cM2237a;
                                C1308c c1308cM2249b = c1308c4;
                                C1308c c1308c7 = c1308c6;
                                while (c1308c7.f2564b.length - 1 >= i8 / 2) {
                                    if (c1308c7.m2246a()) {
                                        throw C1210d.m1865a();
                                    }
                                    C1308c c1308c8 = c1306a.f2556a.f2560d;
                                    int iM2236a = c1306a.f2556a.m2236a(c1308c7.m2244a(c1308c7.f2564b.length - 1));
                                    while (c1308cM2249b.f2564b.length - 1 >= c1308c7.f2564b.length - 1 && !c1308cM2249b.m2246a()) {
                                        int length3 = (c1308cM2249b.f2564b.length - 1) - (c1308c7.f2564b.length - 1);
                                        int iM2240d = c1306a.f2556a.m2240d(c1308cM2249b.m2244a(c1308cM2249b.f2564b.length - 1), iM2236a);
                                        C1308c c1308cM2245a = c1308c8.m2245a(c1306a.f2556a.m2237a(length3, iM2240d));
                                        if (length3 >= 0) {
                                            if (iM2240d != 0) {
                                                int length4 = c1308c7.f2564b.length;
                                                int[] iArr8 = new int[length3 + length4];
                                                int i12 = 0;
                                                while (i12 < length4) {
                                                    iArr8[i12] = c1308c7.f2563a.m2240d(c1308c7.f2564b[i12], iM2240d);
                                                    i12++;
                                                    iM2236a = iM2236a;
                                                    length4 = length4;
                                                }
                                                i3 = iM2236a;
                                                c1308c = new C1308c(c1308c7.f2563a, iArr8);
                                            } else {
                                                i3 = iM2236a;
                                                c1308c = c1308c7.f2563a.f2560d;
                                            }
                                            c1308cM2249b = c1308cM2249b.m2249b(c1308c);
                                            c1308c8 = c1308cM2245a;
                                            iM2236a = i3;
                                        } else {
                                            throw new IllegalArgumentException();
                                        }
                                    }
                                    C1308c c1308c9 = c1308cM2249b;
                                    c1308cM2249b = c1308c7;
                                    c1308c7 = c1308c9;
                                    C1308c c1308c10 = c1308cM2248b;
                                    c1308cM2248b = c1308c8.m2251c(c1308cM2248b).m2249b(c1308c5).m2248b();
                                    c1308c5 = c1308c10;
                                }
                                int iM2244a = c1308cM2248b.m2244a(0);
                                if (iM2244a == 0) {
                                    throw C1210d.m1865a();
                                }
                                int iM2236a2 = c1306a.f2556a.m2236a(iM2244a);
                                C1308c[] c1308cArr = {c1308cM2248b.m2250c(iM2236a2), c1308c7.m2250c(iM2236a2)};
                                C1308c c1308c11 = c1308cArr[0];
                                C1308c c1308c12 = c1308cArr[1];
                                int[] iArrM2229a = c1306a.m2229a(c1308c11);
                                int[] iArrM2230a = c1306a.m2230a(c1308c12, c1308c11, iArrM2229a);
                                for (int i13 = 0; i13 < iArrM2229a.length; i13++) {
                                    int length5 = iArr.length - 1;
                                    C1307b c1307b = c1306a.f2556a;
                                    int i14 = iArrM2229a[i13];
                                    if (i14 == 0) {
                                        throw new IllegalArgumentException();
                                    }
                                    int i15 = length5 - c1307b.f2559c[i14];
                                    if (i15 < 0) {
                                        throw C1210d.m1865a();
                                    }
                                    iArr[i15] = c1306a.f2556a.m2239c(iArr[i15], iArrM2230a[i13]);
                                }
                                length = iArrM2229a.length;
                            } else {
                                length = 0;
                            }
                            if (iArr.length < 4) {
                                throw C1303h.m2217a();
                            }
                            int i16 = iArr[0];
                            if (i16 <= iArr.length) {
                                if (i16 == 0) {
                                    if (i8 < iArr.length) {
                                        iArr[0] = iArr.length - i8;
                                    } else {
                                        throw C1303h.m2217a();
                                    }
                                }
                                C1202e c1202eM2278a = C1312e.m2278a(iArr, String.valueOf(i));
                                c1202eM2278a.f2141f = Integer.valueOf(length);
                                c1202eM2278a.f2142g = Integer.valueOf(iArr2.length);
                                return c1202eM2278a;
                            }
                            throw C1303h.m2217a();
                        }
                        throw C1210d.m1865a();
                    } catch (C1210d unused) {
                        if (iArr6.length == 0) {
                            throw C1210d.m1865a();
                        }
                        for (int i17 = 0; i17 < iArr6.length; i17++) {
                            if (iArr6[i17] < iArr4[i17].length - 1) {
                                iArr6[i17] = iArr6[i17] + 1;
                                break;
                            }
                            iArr6[i17] = 0;
                            if (i17 == iArr6.length - 1) {
                                throw C1210d.m1865a();
                            }
                        }
                        i4 = i2;
                        iArr5 = iArr3;
                    }
                } catch (C1210d unused2) {
                    i2 = i5;
                }
            } else {
                throw C1210d.m1865a();
            }
            i4 = i2;
            iArr5 = iArr3;
        }
    }

    /* JADX INFO: renamed from: a */
    private static int m2319a(C1313f c1313f, int i, int i2, boolean z) {
        int i3;
        int i4 = z ? 1 : -1;
        int i5 = i - i4;
        C1311d c1311dM2305c = m2334a(c1313f, i5) ? c1313f.f2621b[i5].m2305c(i2) : null;
        if (c1311dM2305c != null) {
            if (z) {
                return c1311dM2305c.f2577b;
            }
            return c1311dM2305c.f2576a;
        }
        C1311d c1311dM2302a = c1313f.f2621b[i].m2302a(i2);
        if (c1311dM2302a != null) {
            if (z) {
                return c1311dM2302a.f2576a;
            }
            return c1311dM2302a.f2577b;
        }
        if (m2334a(c1313f, i5)) {
            c1311dM2302a = c1313f.f2621b[i5].m2302a(i2);
        }
        if (c1311dM2302a != null) {
            if (z) {
                return c1311dM2302a.f2577b;
            }
            return c1311dM2302a.f2576a;
        }
        int i6 = 0;
        while (true) {
            i -= i4;
            if (!m2334a(c1313f, i)) {
                if (z) {
                    return c1313f.f2622c.f2571f;
                }
                return c1313f.f2622c.f2572g;
            }
            for (C1311d c1311d : c1313f.f2621b[i].f2626b) {
                if (c1311d != null) {
                    if (z) {
                        i3 = c1311d.f2577b;
                    } else {
                        i3 = c1311d.f2576a;
                    }
                    return i3 + (i4 * i6 * (c1311d.f2577b - c1311d.f2576a));
                }
            }
            i6++;
        }
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0014 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:12:0x0016  */
    /* JADX WARN: Code duplicated, block: B:31:0x002b A[EDGE_INSN: B:31:0x002b->B:19:0x002b BREAK  A[LOOP:0: B:7:0x000d->B:36:0x000d], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:32:0x0023 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:33:0x0029 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:34:0x001c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:35:0x0027 A[SYNTHETIC] */
    /* JADX INFO: renamed from: a */
    private static int[] m2335a(C1194b c1194b, int i, int i2, boolean z, int i3, int i4) {
        int[] iArr = new int[8];
        int i5 = z ? 1 : -1;
        boolean z2 = z;
        int i6 = 0;
        while (true) {
            if (!z) {
                if (i3 < i) {
                    break;
                }
                if (i6 < 8) {
                    break;
                    break;
                }
                if (c1194b.m1789a(i3, i4) == z2) {
                    iArr[i6] = iArr[i6] + 1;
                    i3 += i5;
                } else {
                    i6++;
                    if (!z2) {
                    }
                }
            } else {
                if (i3 >= i2) {
                    break;
                }
                if (i6 < 8) {
                    break;
                }
                if (c1194b.m1789a(i3, i4) == z2) {
                    iArr[i6] = iArr[i6] + 1;
                    i3 += i5;
                } else {
                    i6++;
                    z2 = !z2;
                }
            }
        }
        if (i6 != 8) {
            if (z) {
                i = i2;
            }
            if (i3 != i || i6 != 7) {
                return null;
            }
        }
        return iArr;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0015  */
    /* JADX WARN: Code duplicated, block: B:15:0x001b  */
    /* JADX WARN: Code duplicated, block: B:18:0x0024 A[LOOP:1: B:9:0x000e->B:18:0x0024, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:26:0x0023 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:31:0x0026 A[EDGE_INSN: B:31:0x0026->B:19:0x0026 BREAK  A[LOOP:1: B:9:0x000e->B:18:0x0024], SYNTHETIC] */
    /* JADX INFO: renamed from: b */
    private static int m2336b(C1194b c1194b, int i, int i2, boolean z, int i3, int i4) {
        boolean z2 = z;
        int i5 = z ? -1 : 1;
        int i6 = i3;
        for (int i7 = 0; i7 < 2; i7++) {
            while (true) {
                if (!z2) {
                    if (i6 >= i2) {
                        break;
                    }
                    if (z2 == c1194b.m1789a(i6, i4)) {
                        break;
                        break;
                    }
                    if (Math.abs(i3 - i6) > 2) {
                        return i3;
                    }
                    i6 += i5;
                } else {
                    if (i6 < i) {
                        break;
                    }
                    if (z2 == c1194b.m1789a(i6, i4)) {
                        break;
                    }
                    if (Math.abs(i3 - i6) > 2) {
                        return i3;
                    }
                    i6 += i5;
                }
            }
            i5 = -i5;
            z2 = !z2;
        }
        return i6;
    }

    /* JADX INFO: renamed from: a */
    private static C1202e m2325a(int[] iArr, int i, int[] iArr2) throws C1210d, C1303h {
        C1308c c1308c;
        C1308c c1308c2;
        if (iArr.length == 0) {
            throw C1303h.m2217a();
        }
        int i2 = 1;
        int i3 = 1 << (i + 1);
        if ((iArr2 != null && iArr2.length > (i3 / 2) + 3) || i3 < 0 || i3 > 512) {
            throw C1210d.m1865a();
        }
        C1306a c1306a = f2632d;
        C1308c c1308c3 = new C1308c(c1306a.f2556a, iArr);
        int[] iArr3 = new int[i3];
        int length = 0;
        boolean z = false;
        for (int i4 = i3; i4 > 0; i4--) {
            int iM2247b = c1308c3.m2247b(c1306a.f2556a.f2558b[i4]);
            iArr3[i3 - i4] = iM2247b;
            if (iM2247b != 0) {
                z = true;
            }
        }
        if (z) {
            C1308c c1308c4 = c1306a.f2556a.f2561e;
            int i5 = 2;
            if (iArr2 != null) {
                int length2 = iArr2.length;
                C1308c c1308cM2251c = c1308c4;
                int i6 = 0;
                while (i6 < length2) {
                    int i7 = c1306a.f2556a.f2558b[(iArr.length - 1) - iArr2[i6]];
                    C1307b c1307b = c1306a.f2556a;
                    int[] iArr4 = new int[i5];
                    iArr4[0] = c1306a.f2556a.m2239c(0, i7);
                    iArr4[1] = 1;
                    c1308cM2251c = c1308cM2251c.m2251c(new C1308c(c1307b, iArr4));
                    i6++;
                    i5 = 2;
                }
            }
            C1308c c1308c5 = new C1308c(c1306a.f2556a, iArr3);
            C1308c c1308cM2237a = c1306a.f2556a.m2237a(i3, 1);
            if (c1308cM2237a.f2564b.length - 1 >= c1308c5.f2564b.length - 1) {
                c1308cM2237a = c1308c5;
                c1308c5 = c1308cM2237a;
            }
            C1308c c1308c6 = c1306a.f2556a.f2560d;
            C1308c c1308c7 = c1306a.f2556a.f2561e;
            while (true) {
                C1308c c1308c8 = c1308cM2237a;
                c1308cM2237a = c1308c5;
                c1308c5 = c1308c8;
                if (c1308c5.f2564b.length - i2 >= i3 / 2) {
                    if (c1308c5.m2246a()) {
                        throw C1210d.m1865a();
                    }
                    C1308c c1308c9 = c1306a.f2556a.f2560d;
                    int iM2236a = c1306a.f2556a.m2236a(c1308c5.m2244a(c1308c5.f2564b.length - i2));
                    while (c1308cM2237a.f2564b.length - i2 >= c1308c5.f2564b.length - i2 && !c1308cM2237a.m2246a()) {
                        int length3 = (c1308cM2237a.f2564b.length - i2) - (c1308c5.f2564b.length - i2);
                        int iM2240d = c1306a.f2556a.m2240d(c1308cM2237a.m2244a(c1308cM2237a.f2564b.length - i2), iM2236a);
                        C1308c c1308cM2245a = c1308c9.m2245a(c1306a.f2556a.m2237a(length3, iM2240d));
                        if (length3 >= 0) {
                            if (iM2240d != 0) {
                                int length4 = c1308c5.f2564b.length;
                                int[] iArr5 = new int[length3 + length4];
                                int i8 = 0;
                                while (i8 < length4) {
                                    iArr5[i8] = c1308c5.f2563a.m2240d(c1308c5.f2564b[i8], iM2240d);
                                    i8++;
                                    c1308cM2245a = c1308cM2245a;
                                }
                                c1308c = c1308cM2245a;
                                c1308c2 = new C1308c(c1308c5.f2563a, iArr5);
                            } else {
                                c1308c2 = c1308c5.f2563a.f2560d;
                                c1308c = c1308cM2245a;
                            }
                            c1308cM2237a = c1308cM2237a.m2249b(c1308c2);
                            c1308c9 = c1308c;
                            i2 = 1;
                        } else {
                            throw new IllegalArgumentException();
                        }
                    }
                    C1308c c1308cM2248b = c1308c9.m2251c(c1308c7).m2249b(c1308c6).m2248b();
                    c1308c6 = c1308c7;
                    c1308c7 = c1308cM2248b;
                    i2 = 1;
                } else {
                    int iM2244a = c1308c7.m2244a(0);
                    if (iM2244a == 0) {
                        throw C1210d.m1865a();
                    }
                    int iM2236a2 = c1306a.f2556a.m2236a(iM2244a);
                    C1308c[] c1308cArr = {c1308c7.m2250c(iM2236a2), c1308c5.m2250c(iM2236a2)};
                    C1308c c1308c10 = c1308cArr[0];
                    C1308c c1308c11 = c1308cArr[1];
                    int[] iArrM2229a = c1306a.m2229a(c1308c10);
                    int[] iArrM2230a = c1306a.m2230a(c1308c11, c1308c10, iArrM2229a);
                    for (int i9 = 0; i9 < iArrM2229a.length; i9++) {
                        int length5 = iArr.length - 1;
                        C1307b c1307b2 = c1306a.f2556a;
                        int i10 = iArrM2229a[i9];
                        if (i10 == 0) {
                            throw new IllegalArgumentException();
                        }
                        int i11 = length5 - c1307b2.f2559c[i10];
                        if (i11 < 0) {
                            throw C1210d.m1865a();
                        }
                        iArr[i11] = c1306a.f2556a.m2239c(iArr[i11], iArrM2230a[i9]);
                    }
                    length = iArrM2229a.length;
                    break;
                }
            }
        }
        if (iArr.length < 4) {
            throw C1303h.m2217a();
        }
        int i12 = iArr[0];
        if (i12 <= iArr.length) {
            if (i12 == 0) {
                if (i3 < iArr.length) {
                    iArr[0] = iArr.length - i3;
                } else {
                    throw C1303h.m2217a();
                }
            }
            C1202e c1202eM2278a = C1312e.m2278a(iArr, String.valueOf(i));
            c1202eM2278a.f2141f = Integer.valueOf(length);
            c1202eM2278a.f2142g = Integer.valueOf(iArr2.length);
            return c1202eM2278a;
        }
        throw C1303h.m2217a();
    }

    /* JADX INFO: renamed from: a */
    private static int m2321a(int[] iArr, int[] iArr2, int i) throws C1210d {
        int i2;
        C1308c c1308c;
        if ((iArr2 != null && iArr2.length > (i / 2) + 3) || i < 0 || i > 512) {
            throw C1210d.m1865a();
        }
        C1306a c1306a = f2632d;
        C1308c c1308c2 = new C1308c(c1306a.f2556a, iArr);
        int[] iArr3 = new int[i];
        int i3 = i;
        boolean z = false;
        while (true) {
            i2 = 1;
            if (i3 <= 0) {
                break;
            }
            int iM2247b = c1308c2.m2247b(c1306a.f2556a.f2558b[i3]);
            iArr3[i - i3] = iM2247b;
            if (iM2247b != 0) {
                z = true;
            }
            i3--;
        }
        if (!z) {
            return 0;
        }
        C1308c c1308c3 = c1306a.f2556a.f2561e;
        if (iArr2 != null) {
            C1308c c1308cM2251c = c1308c3;
            for (int i4 : iArr2) {
                c1308cM2251c = c1308cM2251c.m2251c(new C1308c(c1306a.f2556a, new int[]{c1306a.f2556a.m2239c(0, c1306a.f2556a.f2558b[(iArr.length - 1) - i4]), 1}));
            }
        }
        C1308c c1308c4 = new C1308c(c1306a.f2556a, iArr3);
        C1308c c1308cM2237a = c1306a.f2556a.m2237a(i, 1);
        if (c1308cM2237a.f2564b.length - 1 >= c1308c4.f2564b.length - 1) {
            c1308cM2237a = c1308c4;
            c1308c4 = c1308cM2237a;
        }
        C1308c c1308c5 = c1306a.f2556a.f2560d;
        C1308c c1308cM2248b = c1306a.f2556a.f2561e;
        C1308c c1308c6 = c1308cM2237a;
        C1308c c1308cM2249b = c1308c4;
        C1308c c1308c7 = c1308c6;
        while (c1308c7.f2564b.length - i2 >= i / 2) {
            if (c1308c7.m2246a()) {
                throw C1210d.m1865a();
            }
            C1308c c1308cM2245a = c1306a.f2556a.f2560d;
            int iM2236a = c1306a.f2556a.m2236a(c1308c7.m2244a(c1308c7.f2564b.length - i2));
            while (c1308cM2249b.f2564b.length - i2 >= c1308c7.f2564b.length - i2 && !c1308cM2249b.m2246a()) {
                int length = (c1308cM2249b.f2564b.length - i2) - (c1308c7.f2564b.length - i2);
                int iM2240d = c1306a.f2556a.m2240d(c1308cM2249b.m2244a(c1308cM2249b.f2564b.length - i2), iM2236a);
                c1308cM2245a = c1308cM2245a.m2245a(c1306a.f2556a.m2237a(length, iM2240d));
                if (length >= 0) {
                    if (iM2240d != 0) {
                        int length2 = c1308c7.f2564b.length;
                        int[] iArr4 = new int[length + length2];
                        for (int i5 = 0; i5 < length2; i5++) {
                            iArr4[i5] = c1308c7.f2563a.m2240d(c1308c7.f2564b[i5], iM2240d);
                        }
                        c1308c = new C1308c(c1308c7.f2563a, iArr4);
                    } else {
                        c1308c = c1308c7.f2563a.f2560d;
                    }
                    c1308cM2249b = c1308cM2249b.m2249b(c1308c);
                    i2 = 1;
                } else {
                    throw new IllegalArgumentException();
                }
            }
            i2 = 1;
            C1308c c1308c8 = c1308cM2249b;
            c1308cM2249b = c1308c7;
            c1308c7 = c1308c8;
            C1308c c1308c9 = c1308cM2248b;
            c1308cM2248b = c1308cM2245a.m2251c(c1308cM2248b).m2249b(c1308c5).m2248b();
            c1308c5 = c1308c9;
        }
        int iM2244a = c1308cM2248b.m2244a(0);
        if (iM2244a == 0) {
            throw C1210d.m1865a();
        }
        int iM2236a2 = c1306a.f2556a.m2236a(iM2244a);
        C1308c[] c1308cArr = {c1308cM2248b.m2250c(iM2236a2), c1308c7.m2250c(iM2236a2)};
        C1308c c1308c10 = c1308cArr[0];
        C1308c c1308c11 = c1308cArr[1];
        int[] iArrM2229a = c1306a.m2229a(c1308c10);
        int[] iArrM2230a = c1306a.m2230a(c1308c11, c1308c10, iArrM2229a);
        for (int i6 = 0; i6 < iArrM2229a.length; i6++) {
            int length3 = iArr.length - 1;
            C1307b c1307b = c1306a.f2556a;
            int i7 = iArrM2229a[i6];
            if (i7 == 0) {
                throw new IllegalArgumentException();
            }
            int i8 = length3 - c1307b.f2559c[i7];
            if (i8 < 0) {
                throw C1210d.m1865a();
            }
            iArr[i8] = c1306a.f2556a.m2239c(iArr[i8], iArrM2230a[i6]);
        }
        return iArrM2229a.length;
    }

    /* JADX INFO: renamed from: a */
    private static void m2332a(int[] iArr, int i) throws C1303h {
        if (iArr.length < 4) {
            throw C1303h.m2217a();
        }
        int i2 = iArr[0];
        if (i2 > iArr.length) {
            throw C1303h.m2217a();
        }
        if (i2 == 0) {
            if (i < iArr.length) {
                iArr[0] = iArr.length - i;
                return;
            }
            throw C1303h.m2217a();
        }
    }

    /* JADX INFO: renamed from: b */
    private static int[] m2339b(int i) {
        int[] iArr = new int[8];
        int i2 = 0;
        int i3 = 7;
        while (true) {
            int i4 = i & 1;
            if (i4 != i2) {
                i3--;
                if (i3 < 0) {
                    return iArr;
                }
                i2 = i4;
            }
            iArr[i3] = iArr[i3] + 1;
            i >>= 1;
        }
    }

    /* JADX INFO: renamed from: b */
    private static int m2337b(int[] iArr) {
        return ((((iArr[0] - iArr[2]) + iArr[4]) - iArr[6]) + 9) % 9;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x007d  */
    /* JADX WARN: Code duplicated, block: B:33:0x0074 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX INFO: renamed from: a */
    private static String m2330a(C1309b[][] c1309bArr) {
        Formatter formatter = new Formatter();
        int i = 0;
        while (true) {
            Throwable th = null;
            try {
                try {
                    if (i < c1309bArr.length) {
                        formatter.format("Row %2d: ", Integer.valueOf(i));
                        for (int i2 = 0; i2 < c1309bArr[i].length; i2++) {
                            C1309b c1309b = c1309bArr[i][i2];
                            if (c1309b.m2254a().length == 0) {
                                formatter.format("        ", null);
                            } else {
                                formatter.format("%4d(%2d)", Integer.valueOf(c1309b.m2254a()[0]), c1309b.f2565a.get(Integer.valueOf(c1309b.m2254a()[0])));
                            }
                        }
                        formatter.format("%n", new Object[0]);
                        i++;
                    } else {
                        String string = formatter.toString();
                        formatter.close();
                        return string;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            } catch (Throwable th3) {
                if (th != null) {
                    try {
                        formatter.close();
                    } catch (Throwable th4) {
                        ThrowableExtension.addSuppressed(th, th4);
                    }
                } else {
                    formatter.close();
                }
                throw th3;
            }
            if (th != null) {
                formatter.close();
            } else {
                formatter.close();
            }
            throw th3;
        }
    }

    /* JADX WARN: Code duplicated, block: B:22:0x002d  */
    /* JADX INFO: renamed from: a */
    private static C1313f m2328a(C1315h c1315h, C1315h c1315h2) throws C1358m {
        C1305a c1305aM2311a;
        C1305a c1305aM2311a2;
        if (c1315h == null && c1315h2 == null) {
            return null;
        }
        if (c1315h == null || (c1305aM2311a = c1315h.m2311a()) == null) {
            if (c1315h2 == null) {
                c1305aM2311a = null;
            } else {
                c1305aM2311a = c1315h2.m2311a();
            }
        } else if (c1315h2 != null && (c1305aM2311a2 = c1315h2.m2311a()) != null && c1305aM2311a.f2551a != c1305aM2311a2.f2551a && c1305aM2311a.f2552b != c1305aM2311a2.f2552b && c1305aM2311a.f2555e != c1305aM2311a2.f2555e) {
            c1305aM2311a = null;
        }
        if (c1305aM2311a == null) {
            return null;
        }
        C1310c c1310cM2326a = m2326a(c1315h);
        C1310c c1310cM2326a2 = m2326a(c1315h2);
        if (c1310cM2326a == null) {
            c1310cM2326a = c1310cM2326a2;
        } else if (c1310cM2326a2 != null) {
            c1310cM2326a = new C1310c(c1310cM2326a.f2566a, c1310cM2326a.f2567b, c1310cM2326a.f2568c, c1310cM2326a2.f2569d, c1310cM2326a2.f2570e);
        }
        return new C1313f(c1305aM2311a, c1310cM2326a);
    }

    /* JADX INFO: renamed from: a */
    private static C1310c m2326a(C1315h c1315h) throws C1358m {
        C1365t c1365t;
        C1365t c1365t2;
        int[] iArr;
        int i;
        C1365t c1365t3;
        C1365t c1365t4;
        C1365t c1365t5;
        C1365t c1365t6;
        if (c1315h == null) {
            return null;
        }
        C1305a c1305aM2311a = c1315h.m2311a();
        if (c1305aM2311a == null) {
            iArr = null;
        } else {
            C1310c c1310c = c1315h.f2625a;
            if (c1315h.f2627c) {
                c1365t = c1310c.f2567b;
            } else {
                c1365t = c1310c.f2569d;
            }
            if (c1315h.f2627c) {
                c1365t2 = c1310c.f2568c;
            } else {
                c1365t2 = c1310c.f2570e;
            }
            int iB = c1315h.m2304b((int) c1365t2.f2859b);
            C1311d[] c1311dArr = c1315h.f2626b;
            int i2 = -1;
            int i3 = 0;
            int iMax = 1;
            for (int iB2 = c1315h.m2304b((int) c1365t.f2859b); iB2 < iB; iB2++) {
                if (c1311dArr[iB2] != null) {
                    C1311d c1311d = c1311dArr[iB2];
                    c1311d.m2273b();
                    int i4 = c1311d.f2580e - i2;
                    if (i4 == 0) {
                        i3++;
                    } else {
                        if (i4 == 1) {
                            iMax = Math.max(iMax, i3);
                            i2 = c1311d.f2580e;
                        } else if (c1311d.f2580e >= c1305aM2311a.f2555e) {
                            c1311dArr[iB2] = null;
                        } else {
                            i2 = c1311d.f2580e;
                        }
                        i3 = 1;
                    }
                }
            }
            iArr = new int[c1305aM2311a.f2555e];
            for (C1311d c1311d2 : c1315h.f2626b) {
                if (c1311d2 != null && (i = c1311d2.f2580e) < iArr.length) {
                    iArr[i] = iArr[i] + 1;
                }
            }
        }
        if (iArr == null) {
            return null;
        }
        int iMax2 = -1;
        for (int i5 : iArr) {
            iMax2 = Math.max(iMax2, i5);
        }
        int i6 = 0;
        for (int i7 : iArr) {
            i6 += iMax2 - i7;
            if (i7 > 0) {
                break;
            }
        }
        C1311d[] c1311dArr2 = c1315h.f2626b;
        for (int i8 = 0; i6 > 0 && c1311dArr2[i8] == null; i8++) {
            i6--;
        }
        int i9 = 0;
        for (int length = iArr.length - 1; length >= 0; length--) {
            i9 += iMax2 - iArr[length];
            if (iArr[length] > 0) {
                break;
            }
        }
        for (int length2 = c1311dArr2.length - 1; i9 > 0 && c1311dArr2[length2] == null; length2--) {
            i9--;
        }
        C1310c c1310c2 = c1315h.f2625a;
        boolean z = c1315h.f2627c;
        C1365t c1365t7 = c1310c2.f2567b;
        C1365t c1365t8 = c1310c2.f2568c;
        C1365t c1365t9 = c1310c2.f2569d;
        C1365t c1365t10 = c1310c2.f2570e;
        if (i6 > 0) {
            C1365t c1365t11 = z ? c1310c2.f2567b : c1310c2.f2569d;
            int i10 = ((int) c1365t11.f2859b) - i6;
            C1365t c1365t12 = new C1365t(c1365t11.f2858a, i10 >= 0 ? i10 : 0);
            if (z) {
                c1365t4 = c1365t9;
                c1365t3 = c1365t12;
            } else {
                c1365t3 = c1365t7;
                c1365t4 = c1365t12;
            }
        } else {
            c1365t3 = c1365t7;
            c1365t4 = c1365t9;
        }
        if (i9 > 0) {
            C1365t c1365t13 = z ? c1310c2.f2568c : c1310c2.f2570e;
            int i11 = ((int) c1365t13.f2859b) + i9;
            if (i11 >= c1310c2.f2566a.f2078b) {
                i11 = c1310c2.f2566a.f2078b - 1;
            }
            C1365t c1365t14 = new C1365t(c1365t13.f2858a, i11);
            if (z) {
                c1365t5 = c1365t14;
            } else {
                c1365t5 = c1365t8;
                c1365t6 = c1365t14;
            }
            return new C1310c(c1310c2.f2566a, c1365t3, c1365t5, c1365t4, c1365t6);
        }
        c1365t5 = c1365t8;
        c1365t6 = c1365t10;
        return new C1310c(c1310c2.f2566a, c1365t3, c1365t5, c1365t4, c1365t6);
    }

    /* JADX INFO: renamed from: a */
    private static C1202e m2324a(C1313f c1313f) throws C1210d, C1358m, C1303h {
        int i;
        int i2;
        boolean z;
        int i3;
        C1309b[][] c1309bArr = (C1309b[][]) Array.newInstance((Class<?>) C1309b.class, c1313f.f2620a.f2555e, c1313f.f2623d + 2);
        for (int i4 = 0; i4 < c1309bArr.length; i4++) {
            for (int i5 = 0; i5 < c1309bArr[i4].length; i5++) {
                c1309bArr[i4][i5] = new C1309b();
            }
        }
        c1313f.m2299a(c1313f.f2621b[0]);
        c1313f.m2299a(c1313f.f2621b[c1313f.f2623d + 1]);
        int i6 = C1304a.f2542b;
        while (true) {
            if (c1313f.f2621b[0] != null && c1313f.f2621b[c1313f.f2623d + 1] != null) {
                C1311d[] c1311dArr = c1313f.f2621b[0].f2626b;
                C1311d[] c1311dArr2 = c1313f.f2621b[c1313f.f2623d + 1].f2626b;
                for (int i7 = 0; i7 < c1311dArr.length; i7++) {
                    if (c1311dArr[i7] != null && c1311dArr2[i7] != null && c1311dArr[i7].f2580e == c1311dArr2[i7].f2580e) {
                        for (int i8 = 1; i8 <= c1313f.f2623d; i8++) {
                            C1311d c1311d = c1313f.f2621b[i8].f2626b[i7];
                            if (c1311d != null) {
                                c1311d.f2580e = c1311dArr[i7].f2580e;
                                if (!c1311d.m2271a()) {
                                    c1313f.f2621b[i8].f2626b[i7] = null;
                                }
                            }
                        }
                    }
                }
            }
            if (c1313f.f2621b[0] == null) {
                i = 0;
            } else {
                C1311d[] c1311dArr3 = c1313f.f2621b[0].f2626b;
                i = 0;
                for (int i9 = 0; i9 < c1311dArr3.length; i9++) {
                    if (c1311dArr3[i9] != null) {
                        int i10 = c1311dArr3[i9].f2580e;
                        int i11 = i;
                        int iM2283a = 0;
                        for (int i12 = 1; i12 < c1313f.f2623d + 1 && iM2283a < 2; i12++) {
                            C1311d c1311d2 = c1313f.f2621b[i12].f2626b[i9];
                            if (c1311d2 != null) {
                                iM2283a = C1313f.m2283a(i10, iM2283a, c1311d2);
                                if (!c1311d2.m2271a()) {
                                    i11++;
                                }
                            }
                        }
                        i = i11;
                    }
                }
            }
            if (c1313f.f2621b[c1313f.f2623d + 1] == null) {
                i2 = 0;
            } else {
                C1311d[] c1311dArr4 = c1313f.f2621b[c1313f.f2623d + 1].f2626b;
                i2 = 0;
                for (int i13 = 0; i13 < c1311dArr4.length; i13++) {
                    if (c1311dArr4[i13] != null) {
                        int i14 = c1311dArr4[i13].f2580e;
                        int i15 = i2;
                        int iM2283a2 = 0;
                        for (int i16 = c1313f.f2623d + 1; i16 > 0 && iM2283a2 < 2; i16--) {
                            C1311d c1311d3 = c1313f.f2621b[i16].f2626b[i13];
                            if (c1311d3 != null) {
                                iM2283a2 = C1313f.m2283a(i14, iM2283a2, c1311d3);
                                if (!c1311d3.m2271a()) {
                                    i15++;
                                }
                            }
                        }
                        i2 = i15;
                    }
                }
            }
            int i17 = i + i2;
            if (i17 == 0) {
                i17 = 0;
            } else {
                for (int i18 = 1; i18 < c1313f.f2623d + 1; i18++) {
                    C1311d[] c1311dArr5 = c1313f.f2621b[i18].f2626b;
                    for (int i19 = 0; i19 < c1311dArr5.length; i19++) {
                        if (c1311dArr5[i19] != null && !c1311dArr5[i19].m2271a()) {
                            C1311d c1311d4 = c1311dArr5[i19];
                            C1311d[] c1311dArr6 = c1313f.f2621b[i18 - 1].f2626b;
                            int i20 = i18 + 1;
                            C1311d[] c1311dArr7 = c1313f.f2621b[i20] != null ? c1313f.f2621b[i20].f2626b : c1311dArr6;
                            C1311d[] c1311dArr8 = new C1311d[14];
                            c1311dArr8[2] = c1311dArr6[i19];
                            c1311dArr8[3] = c1311dArr7[i19];
                            if (i19 > 0) {
                                int i21 = i19 - 1;
                                c1311dArr8[0] = c1311dArr5[i21];
                                c1311dArr8[4] = c1311dArr6[i21];
                                c1311dArr8[5] = c1311dArr7[i21];
                            }
                            if (i19 > 1) {
                                int i22 = i19 - 2;
                                c1311dArr8[8] = c1311dArr5[i22];
                                c1311dArr8[10] = c1311dArr6[i22];
                                c1311dArr8[11] = c1311dArr7[i22];
                            }
                            if (i19 < c1311dArr5.length - 1) {
                                int i23 = i19 + 1;
                                c1311dArr8[1] = c1311dArr5[i23];
                                c1311dArr8[6] = c1311dArr6[i23];
                                c1311dArr8[7] = c1311dArr7[i23];
                            }
                            if (i19 < c1311dArr5.length - 2) {
                                int i24 = i19 + 2;
                                c1311dArr8[9] = c1311dArr5[i24];
                                c1311dArr8[12] = c1311dArr6[i24];
                                c1311dArr8[13] = c1311dArr7[i24];
                            }
                            int i25 = 0;
                            for (int i26 = 14; i25 < i26; i26 = 14) {
                                C1311d c1311d5 = c1311dArr8[i25];
                                if (c1311d5 != null && c1311d5.m2271a() && c1311d5.f2578c == c1311d4.f2578c) {
                                    c1311d4.f2580e = c1311d5.f2580e;
                                    z = true;
                                } else {
                                    z = false;
                                }
                                if (z) {
                                    break;
                                }
                                i25++;
                            }
                        }
                    }
                }
            }
            if (i17 <= 0 || i17 >= i6) {
                break;
            }
            i6 = i17;
        }
        int i27 = 0;
        for (C1314g c1314g : c1313f.f2621b) {
            if (c1314g != null) {
                for (C1311d c1311d6 : c1314g.f2626b) {
                    if (c1311d6 != null && (i3 = c1311d6.f2580e) >= 0 && i3 < c1309bArr.length) {
                        c1309bArr[i3][i27].m2253a(c1311d6.f2579d);
                    }
                }
            }
            i27++;
        }
        C1309b c1309b = c1309bArr[0][1];
        int[] iArrM2254a = c1309b.m2254a();
        int i28 = (c1313f.f2623d * c1313f.f2620a.f2555e) - (2 << c1313f.f2620a.f2552b);
        if (iArrM2254a.length == 0) {
            if (i28 <= 0 || i28 > 928) {
                throw C1358m.m2611a();
            }
            c1309b.m2253a(i28);
        } else if (iArrM2254a[0] != i28) {
            c1309b.m2253a(i28);
        }
        ArrayList arrayList = new ArrayList();
        int[] iArr = new int[c1313f.f2620a.f2555e * c1313f.f2623d];
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (int i29 = 0; i29 < c1313f.f2620a.f2555e; i29++) {
            int i30 = 0;
            while (i30 < c1313f.f2623d) {
                int i31 = i30 + 1;
                int[] iArrM2254a2 = c1309bArr[i29][i31].m2254a();
                int i32 = (c1313f.f2623d * i29) + i30;
                if (iArrM2254a2.length == 0) {
                    arrayList.add(Integer.valueOf(i32));
                } else if (iArrM2254a2.length == 1) {
                    iArr[i32] = iArrM2254a2[0];
                } else {
                    arrayList3.add(Integer.valueOf(i32));
                    arrayList2.add(iArrM2254a2);
                }
                i30 = i31;
            }
        }
        int[][] iArr2 = new int[arrayList2.size()][];
        for (int i33 = 0; i33 < iArr2.length; i33++) {
            iArr2[i33] = (int[]) arrayList2.get(i33);
        }
        return m2322a(c1313f.f2620a.f2552b, iArr, C1304a.m2221a(arrayList), C1304a.m2221a(arrayList3), iArr2);
    }

    /* JADX INFO: renamed from: b */
    private static C1309b[][] m2340b(C1313f c1313f) {
        int i;
        int i2;
        int i3;
        boolean z;
        C1309b[][] c1309bArr = (C1309b[][]) Array.newInstance((Class<?>) C1309b.class, c1313f.f2620a.f2555e, c1313f.f2623d + 2);
        int i4 = 0;
        for (int i5 = 0; i5 < c1309bArr.length; i5++) {
            for (int i6 = 0; i6 < c1309bArr[i5].length; i6++) {
                c1309bArr[i5][i6] = new C1309b();
            }
        }
        c1313f.m2299a(c1313f.f2621b[0]);
        c1313f.m2299a(c1313f.f2621b[c1313f.f2623d + 1]);
        int i7 = C1304a.f2542b;
        while (true) {
            if (c1313f.f2621b[i4] != null && c1313f.f2621b[c1313f.f2623d + 1] != null) {
                C1311d[] c1311dArr = c1313f.f2621b[i4].f2626b;
                C1311d[] c1311dArr2 = c1313f.f2621b[c1313f.f2623d + 1].f2626b;
                for (int i8 = 0; i8 < c1311dArr.length; i8++) {
                    if (c1311dArr[i8] != null && c1311dArr2[i8] != null && c1311dArr[i8].f2580e == c1311dArr2[i8].f2580e) {
                        for (int i9 = 1; i9 <= c1313f.f2623d; i9++) {
                            C1311d c1311d = c1313f.f2621b[i9].f2626b[i8];
                            if (c1311d != null) {
                                c1311d.f2580e = c1311dArr[i8].f2580e;
                                if (!c1311d.m2271a()) {
                                    c1313f.f2621b[i9].f2626b[i8] = null;
                                }
                            }
                        }
                    }
                }
            }
            if (c1313f.f2621b[i4] == null) {
                i = 0;
            } else {
                C1311d[] c1311dArr3 = c1313f.f2621b[i4].f2626b;
                i = 0;
                for (int i10 = 0; i10 < c1311dArr3.length; i10++) {
                    if (c1311dArr3[i10] != null) {
                        int i11 = c1311dArr3[i10].f2580e;
                        int i12 = i;
                        int iM2283a = 0;
                        for (int i13 = 1; i13 < c1313f.f2623d + 1 && iM2283a < 2; i13++) {
                            C1311d c1311d2 = c1313f.f2621b[i13].f2626b[i10];
                            if (c1311d2 != null) {
                                iM2283a = C1313f.m2283a(i11, iM2283a, c1311d2);
                                if (!c1311d2.m2271a()) {
                                    i12++;
                                }
                            }
                        }
                        i = i12;
                    }
                }
            }
            if (c1313f.f2621b[c1313f.f2623d + 1] == null) {
                i2 = 0;
            } else {
                C1311d[] c1311dArr4 = c1313f.f2621b[c1313f.f2623d + 1].f2626b;
                i2 = 0;
                for (int i14 = 0; i14 < c1311dArr4.length; i14++) {
                    if (c1311dArr4[i14] != null) {
                        int i15 = c1311dArr4[i14].f2580e;
                        int i16 = i2;
                        int iM2283a2 = 0;
                        for (int i17 = c1313f.f2623d + 1; i17 > 0 && iM2283a2 < 2; i17--) {
                            C1311d c1311d3 = c1313f.f2621b[i17].f2626b[i14];
                            if (c1311d3 != null) {
                                iM2283a2 = C1313f.m2283a(i15, iM2283a2, c1311d3);
                                if (!c1311d3.m2271a()) {
                                    i16++;
                                }
                            }
                        }
                        i2 = i16;
                    }
                }
            }
            int i18 = i + i2;
            if (i18 != 0) {
                int i19 = 1;
                while (i19 < c1313f.f2623d + 1) {
                    C1311d[] c1311dArr5 = c1313f.f2621b[i19].f2626b;
                    int i20 = 0;
                    while (i20 < c1311dArr5.length) {
                        if (c1311dArr5[i20] != null && !c1311dArr5[i20].m2271a()) {
                            C1311d c1311d4 = c1311dArr5[i20];
                            C1311d[] c1311dArr6 = c1313f.f2621b[i19 - 1].f2626b;
                            int i21 = i19 + 1;
                            C1311d[] c1311dArr7 = c1313f.f2621b[i21] != null ? c1313f.f2621b[i21].f2626b : c1311dArr6;
                            C1311d[] c1311dArr8 = new C1311d[14];
                            c1311dArr8[2] = c1311dArr6[i20];
                            c1311dArr8[3] = c1311dArr7[i20];
                            if (i20 > 0) {
                                int i22 = i20 - 1;
                                c1311dArr8[i4] = c1311dArr5[i22];
                                c1311dArr8[4] = c1311dArr6[i22];
                                c1311dArr8[5] = c1311dArr7[i22];
                            }
                            if (i20 > 1) {
                                int i23 = i20 - 2;
                                c1311dArr8[8] = c1311dArr5[i23];
                                c1311dArr8[10] = c1311dArr6[i23];
                                c1311dArr8[11] = c1311dArr7[i23];
                            }
                            if (i20 < c1311dArr5.length - 1) {
                                int i24 = i20 + 1;
                                c1311dArr8[1] = c1311dArr5[i24];
                                c1311dArr8[6] = c1311dArr6[i24];
                                c1311dArr8[7] = c1311dArr7[i24];
                            }
                            if (i20 < c1311dArr5.length - 2) {
                                int i25 = i20 + 2;
                                c1311dArr8[9] = c1311dArr5[i25];
                                c1311dArr8[12] = c1311dArr6[i25];
                                c1311dArr8[13] = c1311dArr7[i25];
                            }
                            for (int i26 = 0; i26 < 14; i26++) {
                                C1311d c1311d5 = c1311dArr8[i26];
                                if (c1311d5 != null && c1311d5.m2271a() && c1311d5.f2578c == c1311d4.f2578c) {
                                    c1311d4.f2580e = c1311d5.f2580e;
                                    z = true;
                                } else {
                                    z = false;
                                }
                                if (z) {
                                    break;
                                }
                            }
                        }
                        i20++;
                        i4 = 0;
                    }
                    i19++;
                    i4 = 0;
                }
                i4 = i18;
            }
            if (i4 <= 0 || i4 >= i7) {
                break;
            }
            i7 = i4;
            i4 = 0;
        }
        int i27 = 0;
        for (C1314g c1314g : c1313f.f2621b) {
            if (c1314g != null) {
                for (C1311d c1311d6 : c1314g.f2626b) {
                    if (c1311d6 != null && (i3 = c1311d6.f2580e) >= 0 && i3 < c1309bArr.length) {
                        c1309bArr[i3][i27].m2253a(c1311d6.f2579d);
                    }
                }
            }
            i27++;
        }
        return c1309bArr;
    }

    /* JADX INFO: renamed from: a */
    private static boolean m2334a(C1313f c1313f, int i) {
        return i >= 0 && i <= c1313f.f2623d + 1;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x001f  */
    /* JADX WARN: Code duplicated, block: B:15:0x0025  */
    /* JADX WARN: Code duplicated, block: B:18:0x0030 A[LOOP:1: B:9:0x0018->B:18:0x0030, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:33:0x004f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:34:0x0051  */
    /* JADX WARN: Code duplicated, block: B:74:0x002d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:79:0x0032 A[EDGE_INSN: B:79:0x0032->B:19:0x0032 BREAK  A[LOOP:1: B:9:0x0018->B:18:0x0030], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:81:0x0066 A[EDGE_INSN: B:81:0x0066->B:41:0x0066 BREAK  A[LOOP:2: B:29:0x0048->B:87:0x0048], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:82:0x005e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:84:0x0057 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:85:0x0064 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:86:0x0062 A[SYNTHETIC] */
    /* JADX INFO: renamed from: a */
    private static C1311d m2327a(C1194b c1194b, int i, int i2, boolean z, int i3, int i4, int i5, int i6) {
        int i7;
        int iM2313a;
        int iM2219a;
        int i8 = i;
        boolean z2 = z;
        int i9 = i3;
        int i10 = z ? -1 : 1;
        loop0: for (int i11 = 0; i11 < 2; i11++) {
            while (true) {
                if (!z2) {
                    if (i9 >= i2) {
                        break;
                    }
                    if (z2 == c1194b.m1789a(i9, i4)) {
                        break;
                        break;
                    }
                    if (Math.abs(i3 - i9) > 2) {
                        i9 = i3;
                        break loop0;
                    }
                    i9 += i10;
                } else {
                    if (i9 < i8) {
                        break;
                    }
                    if (z2 == c1194b.m1789a(i9, i4)) {
                        break;
                    }
                    if (Math.abs(i3 - i9) > 2) {
                        i9 = i3;
                        break loop0;
                    }
                    i9 += i10;
                }
            }
            i10 = -i10;
            z2 = !z2;
        }
        int[] iArr = new int[8];
        int i12 = z ? 1 : -1;
        boolean z3 = z;
        int i13 = i9;
        int i14 = 0;
        while (true) {
            if (!z) {
                if (i13 < i8) {
                    break;
                }
                if (i14 < 8) {
                    break;
                    break;
                }
                if (c1194b.m1789a(i13, i4) == z3) {
                    iArr[i14] = iArr[i14] + 1;
                    i13 += i12;
                } else {
                    i14++;
                    if (!z3) {
                    }
                }
            } else {
                if (i13 >= i2) {
                    break;
                }
                if (i14 < 8) {
                    break;
                }
                if (c1194b.m1789a(i13, i4) == z3) {
                    iArr[i14] = iArr[i14] + 1;
                    i13 += i12;
                } else {
                    i14++;
                    z3 = !z3;
                }
            }
        }
        if (i14 != 8) {
            if (z) {
                i8 = i2;
            }
            if (i13 != i8 || i14 != 7) {
                iArr = null;
            }
        }
        if (iArr == null) {
            return null;
        }
        int iM1766a = C1191a.m1766a(iArr);
        if (z) {
            i7 = i9 + iM1766a;
        } else {
            for (int i15 = 0; i15 < iArr.length / 2; i15++) {
                int i16 = iArr[i15];
                iArr[i15] = iArr[(iArr.length - 1) - i15];
                iArr[(iArr.length - 1) - i15] = i16;
            }
            i7 = i9;
            i9 -= iM1766a;
        }
        if (!(i5 + (-2) <= iM1766a && iM1766a <= i6 + 2) || (iM2219a = C1304a.m2219a((iM2313a = C1316i.m2313a(iArr)))) == -1) {
            return null;
        }
        int[] iArrM2339b = m2339b(iM2313a);
        return new C1311d(i9, i7, ((((iArrM2339b[0] - iArrM2339b[2]) + iArrM2339b[4]) - iArrM2339b[6]) + 9) % 9, iM2219a);
    }

    /* JADX INFO: renamed from: c */
    private static int m2341c(int i) {
        int[] iArr = new int[8];
        int i2 = 0;
        int i3 = 7;
        while (true) {
            int i4 = i & 1;
            if (i4 != i2) {
                i3--;
                if (i3 < 0) {
                    return ((((iArr[0] - iArr[2]) + iArr[4]) - iArr[6]) + 9) % 9;
                }
                i2 = i4;
            }
            iArr[i3] = iArr[i3] + 1;
            i >>= 1;
        }
    }
}
