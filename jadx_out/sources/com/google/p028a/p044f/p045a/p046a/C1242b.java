package com.google.p028a.p044f.p045a.p046a;

import com.google.p028a.C1358m;
import com.google.p028a.C1365t;
import com.google.p028a.p035c.C1194b;
import com.google.p028a.p056i.p058b.C1346d;
import com.google.p028a.p056i.p058b.C1347e;
import com.google.p028a.p056i.p058b.C1348f;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: renamed from: com.google.a.f.a.a.b */
/* JADX INFO: compiled from: MultiFinderPatternFinder.java */
/* JADX INFO: loaded from: classes.dex */
final class C1242b extends C1347e {

    /* JADX INFO: renamed from: e */
    private static final C1348f[] f2315e = new C1348f[0];

    /* JADX INFO: renamed from: f */
    private static final float f2316f = 180.0f;

    /* JADX INFO: renamed from: g */
    private static final float f2317g = 9.0f;

    /* JADX INFO: renamed from: h */
    private static final float f2318h = 0.05f;

    /* JADX INFO: renamed from: i */
    private static final float f2319i = 0.5f;

    /* JADX INFO: renamed from: com.google.a.f.a.a.b$a */
    /* JADX INFO: compiled from: MultiFinderPatternFinder.java */
    private static final class a implements Serializable, Comparator<C1346d> {
        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }

        @Override // java.util.Comparator
        public final /* bridge */ /* synthetic */ int compare(C1346d c1346d, C1346d c1346d2) {
            double d = c1346d2.f2780c - c1346d.f2780c;
            if (d < 0.0d) {
                return -1;
            }
            return d > 0.0d ? 1 : 0;
        }

        /* JADX INFO: renamed from: a */
        private static int m2029a(C1346d c1346d, C1346d c1346d2) {
            double d = c1346d2.f2780c - c1346d.f2780c;
            if (d < 0.0d) {
                return -1;
            }
            return d > 0.0d ? 1 : 0;
        }
    }

    private C1242b(C1194b c1194b) {
        super(c1194b);
    }

    C1242b(C1194b c1194b, byte b) {
        super(c1194b, null);
    }

    /* JADX INFO: renamed from: b */
    private C1346d[][] m2027b() throws C1358m {
        List<C1346d> list = this.f2786d;
        int size = list.size();
        int i = 3;
        if (size < 3) {
            throw C1358m.m2611a();
        }
        byte b = 0;
        if (size == 3) {
            return new C1346d[][]{new C1346d[]{list.get(0), list.get(1), list.get(2)}};
        }
        Collections.sort(list, new a(b));
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 < size - 2) {
            C1346d c1346d = list.get(i2);
            if (c1346d != null) {
                int i3 = i2 + 1;
                while (i3 < size - 1) {
                    C1346d c1346d2 = list.get(i3);
                    if (c1346d2 != null) {
                        float fMin = (c1346d.f2780c - c1346d2.f2780c) / Math.min(c1346d.f2780c, c1346d2.f2780c);
                        float fAbs = Math.abs(c1346d.f2780c - c1346d2.f2780c);
                        float f = f2319i;
                        float f2 = f2318h;
                        if (fAbs > f2319i && fMin >= f2318h) {
                            break;
                        }
                        int i4 = i3 + 1;
                        while (i4 < size) {
                            C1346d c1346d3 = list.get(i4);
                            if (c1346d3 != null) {
                                float fMin2 = (c1346d2.f2780c - c1346d3.f2780c) / Math.min(c1346d2.f2780c, c1346d3.f2780c);
                                if (Math.abs(c1346d2.f2780c - c1346d3.f2780c) > f && fMin2 >= f2) {
                                    break;
                                }
                                C1346d[] c1346dArr = new C1346d[i];
                                c1346dArr[0] = c1346d;
                                c1346dArr[1] = c1346d2;
                                c1346dArr[2] = c1346d3;
                                C1365t.m2629a(c1346dArr);
                                C1348f c1348f = new C1348f(c1346dArr);
                                float fM2627a = C1365t.m2627a(c1348f.f2793b, c1348f.f2792a);
                                float fM2627a2 = C1365t.m2627a(c1348f.f2794c, c1348f.f2792a);
                                float fM2627a3 = C1365t.m2627a(c1348f.f2793b, c1348f.f2794c);
                                float f3 = (fM2627a + fM2627a3) / (c1346d.f2780c * 2.0f);
                                if (f3 <= f2316f && f3 >= f2317g && Math.abs((fM2627a - fM2627a3) / Math.min(fM2627a, fM2627a3)) < 0.1f) {
                                    float fSqrt = (float) Math.sqrt((fM2627a * fM2627a) + (fM2627a3 * fM2627a3));
                                    if (Math.abs((fM2627a2 - fSqrt) / Math.min(fM2627a2, fSqrt)) < 0.1f) {
                                        arrayList.add(c1346dArr);
                                    }
                                }
                            }
                            i4++;
                            i = 3;
                            f = f2319i;
                            f2 = f2318h;
                        }
                    }
                    i3++;
                    i = 3;
                }
            }
            i2++;
            i = 3;
        }
        if (!arrayList.isEmpty()) {
            return (C1346d[][]) arrayList.toArray(new C1346d[arrayList.size()][]);
        }
        throw C1358m.m2611a();
    }

    /* JADX INFO: renamed from: a */
    public final C1348f[] m2028a() throws C1358m {
        byte b;
        C1346d[][] c1346dArr;
        C1194b c1194b = this.f2785c;
        int i = c1194b.f2078b;
        int i2 = c1194b.f2077a;
        int i3 = (i * 3) / 388;
        int i4 = 3;
        if (i3 < 3) {
            i3 = 3;
        }
        int[] iArr = new int[5];
        int i5 = i3 - 1;
        while (true) {
            b = 0;
            if (i5 >= i) {
                break;
            }
            m2519b(iArr);
            int i6 = 0;
            for (int i7 = 0; i7 < i2; i7++) {
                if (c1194b.m1789a(i7, i5)) {
                    if ((i6 & 1) == 1) {
                        i6++;
                    }
                    iArr[i6] = iArr[i6] + 1;
                } else if ((i6 & 1) != 0) {
                    iArr[i6] = iArr[i6] + 1;
                } else if (i6 == 4) {
                    if (m2516a(iArr) && m2528a(iArr, i5, i7)) {
                        m2519b(iArr);
                        i6 = 0;
                    } else {
                        m2521c(iArr);
                        i6 = 3;
                    }
                } else {
                    i6++;
                    iArr[i6] = iArr[i6] + 1;
                }
            }
            if (m2516a(iArr)) {
                m2528a(iArr, i5, i2);
            }
            i5 += i3;
        }
        List<C1346d> list = this.f2786d;
        int size = list.size();
        if (size >= 3) {
            if (size == 3) {
                c1346dArr = new C1346d[][]{new C1346d[]{list.get(0), list.get(1), list.get(2)}};
            } else {
                Collections.sort(list, new a(b));
                ArrayList arrayList = new ArrayList();
                int i8 = 0;
                while (i8 < size - 2) {
                    C1346d c1346d = list.get(i8);
                    if (c1346d != null) {
                        int i9 = i8 + 1;
                        while (i9 < size - 1) {
                            C1346d c1346d2 = list.get(i9);
                            if (c1346d2 != null) {
                                float fMin = (c1346d.f2780c - c1346d2.f2780c) / Math.min(c1346d.f2780c, c1346d2.f2780c);
                                float fAbs = Math.abs(c1346d.f2780c - c1346d2.f2780c);
                                float f = f2319i;
                                float f2 = f2318h;
                                if (fAbs > f2319i && fMin >= f2318h) {
                                    break;
                                }
                                int i10 = i9 + 1;
                                while (i10 < size) {
                                    C1346d c1346d3 = list.get(i10);
                                    if (c1346d3 != null) {
                                        float fMin2 = (c1346d2.f2780c - c1346d3.f2780c) / Math.min(c1346d2.f2780c, c1346d3.f2780c);
                                        if (Math.abs(c1346d2.f2780c - c1346d3.f2780c) > f && fMin2 >= f2) {
                                            break;
                                        }
                                        C1346d[] c1346dArr2 = new C1346d[i4];
                                        c1346dArr2[0] = c1346d;
                                        c1346dArr2[1] = c1346d2;
                                        c1346dArr2[2] = c1346d3;
                                        C1365t.m2629a(c1346dArr2);
                                        C1348f c1348f = new C1348f(c1346dArr2);
                                        float fM2627a = C1365t.m2627a(c1348f.f2793b, c1348f.f2792a);
                                        float fM2627a2 = C1365t.m2627a(c1348f.f2794c, c1348f.f2792a);
                                        float fM2627a3 = C1365t.m2627a(c1348f.f2793b, c1348f.f2794c);
                                        float f3 = (fM2627a + fM2627a3) / (c1346d.f2780c * 2.0f);
                                        if (f3 <= f2316f && f3 >= f2317g && Math.abs((fM2627a - fM2627a3) / Math.min(fM2627a, fM2627a3)) < 0.1f) {
                                            float fSqrt = (float) Math.sqrt((fM2627a * fM2627a) + (fM2627a3 * fM2627a3));
                                            if (Math.abs((fM2627a2 - fSqrt) / Math.min(fM2627a2, fSqrt)) < 0.1f) {
                                                arrayList.add(c1346dArr2);
                                            }
                                        }
                                    }
                                    i10++;
                                    i4 = 3;
                                    f = f2319i;
                                    f2 = f2318h;
                                }
                            }
                            i9++;
                            i4 = 3;
                        }
                    }
                    i8++;
                    i4 = 3;
                }
                if (!arrayList.isEmpty()) {
                    c1346dArr = (C1346d[][]) arrayList.toArray(new C1346d[arrayList.size()][]);
                } else {
                    throw C1358m.m2611a();
                }
            }
            ArrayList arrayList2 = new ArrayList();
            for (C1346d[] c1346dArr3 : c1346dArr) {
                C1365t.m2629a(c1346dArr3);
                arrayList2.add(new C1348f(c1346dArr3));
            }
            if (arrayList2.isEmpty()) {
                return f2315e;
            }
            return (C1348f[]) arrayList2.toArray(new C1348f[arrayList2.size()]);
        }
        throw C1358m.m2611a();
    }
}
