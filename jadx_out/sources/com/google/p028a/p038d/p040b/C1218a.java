package com.google.p028a.p038d.p040b;

import com.google.p028a.C1358m;
import com.google.p028a.C1365t;
import com.google.p028a.p035c.AbstractC1206i;
import com.google.p028a.p035c.C1194b;
import com.google.p028a.p035c.C1204g;
import com.google.p028a.p035c.p036a.C1191a;
import com.google.p028a.p035c.p036a.C1193c;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import net.lingala.zip4j.util.InternalZipConstants;
import org.apache.commons.p092io.IOUtils;

/* JADX INFO: renamed from: com.google.a.d.b.a */
/* JADX INFO: compiled from: Detector.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1218a {

    /* JADX INFO: renamed from: a */
    public final C1194b f2212a;

    /* JADX INFO: renamed from: b */
    public final C1193c f2213b;

    public C1218a(C1194b c1194b) throws C1358m {
        this.f2212a = c1194b;
        this.f2213b = new C1193c(c1194b);
    }

    /* JADX WARN: Code duplicated, block: B:46:0x017f  */
    /* JADX WARN: Code duplicated, block: B:65:0x024c  */
    /* JADX INFO: renamed from: a */
    private C1204g m1912a() throws C1358m {
        C1365t c1365t;
        C1194b c1194bM1911a;
        C1365t c1365t2;
        C1365t[] c1365tArrM1773a = this.f2213b.m1773a();
        C1365t c1365t3 = c1365tArrM1773a[0];
        C1365t c1365t4 = c1365tArrM1773a[1];
        C1365t c1365t5 = c1365tArrM1773a[2];
        C1365t c1365t6 = c1365tArrM1773a[3];
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(m1917b(c1365t3, c1365t4));
        arrayList.add(m1917b(c1365t3, c1365t5));
        arrayList.add(m1917b(c1365t4, c1365t6));
        arrayList.add(m1917b(c1365t5, c1365t6));
        Collections.sort(arrayList, new b((byte) 0));
        a aVar = (a) arrayList.get(0);
        a aVar2 = (a) arrayList.get(1);
        HashMap map = new HashMap();
        m1915a(map, aVar.f2214a);
        m1915a(map, aVar.f2215b);
        m1915a(map, aVar2.f2214a);
        m1915a(map, aVar2.f2215b);
        C1365t c1365t7 = null;
        C1365t c1365t8 = null;
        C1365t c1365t9 = null;
        for (Map.Entry entry : map.entrySet()) {
            C1365t c1365t10 = (C1365t) entry.getKey();
            if (((Integer) entry.getValue()).intValue() == 2) {
                c1365t8 = c1365t10;
            } else if (c1365t7 == null) {
                c1365t7 = c1365t10;
            } else {
                c1365t9 = c1365t10;
            }
        }
        if (c1365t7 == null || c1365t8 == null || c1365t9 == null) {
            throw C1358m.m2611a();
        }
        C1365t[] c1365tArr = {c1365t7, c1365t8, c1365t9};
        C1365t.m2629a(c1365tArr);
        C1365t c1365t11 = c1365tArr[0];
        C1365t c1365t12 = c1365tArr[1];
        C1365t c1365t13 = c1365tArr[2];
        if (!map.containsKey(c1365t3)) {
            c1365t6 = c1365t3;
        } else if (!map.containsKey(c1365t4)) {
            c1365t6 = c1365t4;
        } else if (!map.containsKey(c1365t5)) {
            c1365t6 = c1365t5;
        }
        int i = m1917b(c1365t13, c1365t6).f2216c;
        int i2 = m1917b(c1365t11, c1365t6).f2216c;
        if ((i & 1) == 1) {
            i++;
        }
        int i3 = i + 2;
        if ((i2 & 1) == 1) {
            i2++;
        }
        int i4 = i2 + 2;
        if (i3 * 4 < i4 * 7 && i4 * 4 < i3 * 7) {
            float fMin = Math.min(i4, i3);
            float fM1910a = m1910a(c1365t12, c1365t11) / fMin;
            float fM1910a2 = m1910a(c1365t13, c1365t6);
            C1365t c1365t14 = new C1365t(c1365t6.f2858a + (((c1365t6.f2858a - c1365t13.f2858a) / fM1910a2) * fM1910a), c1365t6.f2859b + (fM1910a * ((c1365t6.f2859b - c1365t13.f2859b) / fM1910a2)));
            float fM1910a3 = m1910a(c1365t12, c1365t13) / fMin;
            float fM1910a4 = m1910a(c1365t11, c1365t6);
            C1365t c1365t15 = new C1365t(c1365t6.f2858a + (((c1365t6.f2858a - c1365t11.f2858a) / fM1910a4) * fM1910a3), c1365t6.f2859b + (fM1910a3 * ((c1365t6.f2859b - c1365t11.f2859b) / fM1910a4)));
            if (!m1916a(c1365t14)) {
                if (m1916a(c1365t15)) {
                    c1365t2 = c1365t15;
                } else {
                    c1365t2 = null;
                }
            } else if (m1916a(c1365t15) && Math.abs(m1917b(c1365t13, c1365t14).f2216c - m1917b(c1365t11, c1365t14).f2216c) > Math.abs(m1917b(c1365t13, c1365t15).f2216c - m1917b(c1365t11, c1365t15).f2216c)) {
                c1365t2 = c1365t15;
            } else {
                c1365t2 = c1365t14;
            }
            if (c1365t2 != null) {
                c1365t6 = c1365t2;
            }
            int iMax = Math.max(m1917b(c1365t13, c1365t6).f2216c, m1917b(c1365t11, c1365t6).f2216c) + 1;
            if ((iMax & 1) == 1) {
                iMax++;
            }
            int i5 = iMax;
            c1194bM1911a = m1911a(this.f2212a, c1365t13, c1365t12, c1365t11, c1365t6, i5, i5);
        } else {
            float fM1910a5 = m1910a(c1365t12, c1365t11) / i3;
            float fM1910a6 = m1910a(c1365t13, c1365t6);
            C1365t c1365t16 = new C1365t(c1365t6.f2858a + (((c1365t6.f2858a - c1365t13.f2858a) / fM1910a6) * fM1910a5), c1365t6.f2859b + (fM1910a5 * ((c1365t6.f2859b - c1365t13.f2859b) / fM1910a6)));
            float fM1910a7 = m1910a(c1365t12, c1365t13) / i4;
            float fM1910a8 = m1910a(c1365t11, c1365t6);
            C1365t c1365t17 = new C1365t(c1365t6.f2858a + (((c1365t6.f2858a - c1365t11.f2858a) / fM1910a8) * fM1910a7), c1365t6.f2859b + (fM1910a7 * ((c1365t6.f2859b - c1365t11.f2859b) / fM1910a8)));
            if (!m1916a(c1365t16)) {
                if (m1916a(c1365t17)) {
                    c1365t = c1365t17;
                } else {
                    c1365t = null;
                }
            } else if (m1916a(c1365t17) && Math.abs(i3 - m1917b(c1365t13, c1365t16).f2216c) + Math.abs(i4 - m1917b(c1365t11, c1365t16).f2216c) > Math.abs(i3 - m1917b(c1365t13, c1365t17).f2216c) + Math.abs(i4 - m1917b(c1365t11, c1365t17).f2216c)) {
                c1365t = c1365t17;
            } else {
                c1365t = c1365t16;
            }
            if (c1365t != null) {
                c1365t6 = c1365t;
            }
            int i6 = m1917b(c1365t13, c1365t6).f2216c;
            int i7 = m1917b(c1365t11, c1365t6).f2216c;
            if ((i6 & 1) == 1) {
                i6++;
            }
            int i8 = i6;
            if ((i7 & 1) == 1) {
                i7++;
            }
            c1194bM1911a = m1911a(this.f2212a, c1365t13, c1365t12, c1365t11, c1365t6, i8, i7);
        }
        return new C1204g(c1194bM1911a, new C1365t[]{c1365t13, c1365t12, c1365t11, c1365t6});
    }

    /* JADX INFO: renamed from: a */
    private C1365t m1914a(C1365t c1365t, C1365t c1365t2, C1365t c1365t3, C1365t c1365t4, int i, int i2) {
        float fM1910a = m1910a(c1365t, c1365t2) / i;
        float fM1910a2 = m1910a(c1365t3, c1365t4);
        C1365t c1365t5 = new C1365t(c1365t4.f2858a + (((c1365t4.f2858a - c1365t3.f2858a) / fM1910a2) * fM1910a), c1365t4.f2859b + (fM1910a * ((c1365t4.f2859b - c1365t3.f2859b) / fM1910a2)));
        float fM1910a3 = m1910a(c1365t, c1365t3) / i2;
        float fM1910a4 = m1910a(c1365t2, c1365t4);
        C1365t c1365t6 = new C1365t(c1365t4.f2858a + (((c1365t4.f2858a - c1365t2.f2858a) / fM1910a4) * fM1910a3), c1365t4.f2859b + (fM1910a3 * ((c1365t4.f2859b - c1365t2.f2859b) / fM1910a4)));
        if (m1916a(c1365t5)) {
            return (m1916a(c1365t6) && Math.abs(i - m1917b(c1365t3, c1365t5).f2216c) + Math.abs(i2 - m1917b(c1365t2, c1365t5).f2216c) > Math.abs(i - m1917b(c1365t3, c1365t6).f2216c) + Math.abs(i2 - m1917b(c1365t2, c1365t6).f2216c)) ? c1365t6 : c1365t5;
        }
        if (m1916a(c1365t6)) {
            return c1365t6;
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    private C1365t m1913a(C1365t c1365t, C1365t c1365t2, C1365t c1365t3, C1365t c1365t4, int i) {
        float f = i;
        float fM1910a = m1910a(c1365t, c1365t2) / f;
        float fM1910a2 = m1910a(c1365t3, c1365t4);
        C1365t c1365t5 = new C1365t(c1365t4.f2858a + (((c1365t4.f2858a - c1365t3.f2858a) / fM1910a2) * fM1910a), c1365t4.f2859b + (fM1910a * ((c1365t4.f2859b - c1365t3.f2859b) / fM1910a2)));
        float fM1910a3 = m1910a(c1365t, c1365t3) / f;
        float fM1910a4 = m1910a(c1365t2, c1365t4);
        C1365t c1365t6 = new C1365t(c1365t4.f2858a + (((c1365t4.f2858a - c1365t2.f2858a) / fM1910a4) * fM1910a3), c1365t4.f2859b + (fM1910a3 * ((c1365t4.f2859b - c1365t2.f2859b) / fM1910a4)));
        if (m1916a(c1365t5)) {
            return (m1916a(c1365t6) && Math.abs(m1917b(c1365t3, c1365t5).f2216c - m1917b(c1365t2, c1365t5).f2216c) > Math.abs(m1917b(c1365t3, c1365t6).f2216c - m1917b(c1365t2, c1365t6).f2216c)) ? c1365t6 : c1365t5;
        }
        if (m1916a(c1365t6)) {
            return c1365t6;
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    public static int m1910a(C1365t c1365t, C1365t c1365t2) {
        return C1191a.m1765a(C1365t.m2627a(c1365t, c1365t2));
    }

    /* JADX INFO: renamed from: a */
    public static void m1915a(Map<C1365t, Integer> map, C1365t c1365t) {
        Integer num = map.get(c1365t);
        map.put(c1365t, Integer.valueOf(num != null ? 1 + num.intValue() : 1));
    }

    /* JADX INFO: renamed from: a */
    public static C1194b m1911a(C1194b c1194b, C1365t c1365t, C1365t c1365t2, C1365t c1365t3, C1365t c1365t4, int i, int i2) throws C1358m {
        float f = i - 0.5f;
        float f2 = i2 - 0.5f;
        return AbstractC1206i.m1850a().mo1844a(c1194b, i, i2, 0.5f, 0.5f, f, 0.5f, f, f2, 0.5f, f2, c1365t.f2858a, c1365t.f2859b, c1365t4.f2858a, c1365t4.f2859b, c1365t3.f2858a, c1365t3.f2859b, c1365t2.f2858a, c1365t2.f2859b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: com.google.a.d.b.a$a */
    /* JADX INFO: compiled from: Detector.java */
    public static final class a {

        /* JADX INFO: renamed from: a */
        public final C1365t f2214a;

        /* JADX INFO: renamed from: b */
        public final C1365t f2215b;

        /* JADX INFO: renamed from: c */
        public final int f2216c;

        /* synthetic */ a(C1365t c1365t, C1365t c1365t2, int i, byte b) {
            this(c1365t, c1365t2, i);
        }

        private a(C1365t c1365t, C1365t c1365t2, int i) {
            this.f2214a = c1365t;
            this.f2215b = c1365t2;
            this.f2216c = i;
        }

        /* JADX INFO: renamed from: a */
        private C1365t m1918a() {
            return this.f2214a;
        }

        /* JADX INFO: renamed from: b */
        private C1365t m1919b() {
            return this.f2215b;
        }

        /* JADX INFO: renamed from: c */
        private int m1920c() {
            return this.f2216c;
        }

        public final String toString() {
            return this.f2214a + InternalZipConstants.ZIP_FILE_SEPARATOR + this.f2215b + IOUtils.DIR_SEPARATOR_UNIX + this.f2216c;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: com.google.a.d.b.a$b */
    /* JADX INFO: compiled from: Detector.java */
    public static final class b implements Serializable, Comparator<a> {
        private b() {
        }

        public /* synthetic */ b(byte b) {
            this();
        }

        @Override // java.util.Comparator
        public final /* bridge */ /* synthetic */ int compare(a aVar, a aVar2) {
            return aVar.f2216c - aVar2.f2216c;
        }

        /* JADX INFO: renamed from: a */
        private static int m1921a(a aVar, a aVar2) {
            return aVar.f2216c - aVar2.f2216c;
        }
    }

    /* JADX INFO: renamed from: a */
    public final boolean m1916a(C1365t c1365t) {
        return c1365t.f2858a >= 0.0f && c1365t.f2858a < ((float) this.f2212a.f2077a) && c1365t.f2859b > 0.0f && c1365t.f2859b < ((float) this.f2212a.f2078b);
    }

    /* JADX INFO: renamed from: b */
    public final a m1917b(C1365t c1365t, C1365t c1365t2) {
        C1218a c1218a = this;
        int i = (int) c1365t.f2858a;
        int i2 = (int) c1365t.f2859b;
        int i3 = (int) c1365t2.f2858a;
        int i4 = (int) c1365t2.f2859b;
        boolean z = Math.abs(i4 - i2) > Math.abs(i3 - i);
        if (z) {
            i2 = i;
            i = i2;
            i4 = i3;
            i3 = i4;
        }
        int iAbs = Math.abs(i3 - i);
        int iAbs2 = Math.abs(i4 - i2);
        int i5 = (-iAbs) / 2;
        int i6 = i2 < i4 ? 1 : -1;
        int i7 = i < i3 ? 1 : -1;
        boolean zM1789a = c1218a.f2212a.m1789a(z ? i2 : i, z ? i : i2);
        int i8 = 0;
        while (i != i3) {
            boolean zM1789a2 = c1218a.f2212a.m1789a(z ? i2 : i, z ? i : i2);
            if (zM1789a2 != zM1789a) {
                i8++;
                zM1789a = zM1789a2;
            }
            i5 += iAbs2;
            if (i5 > 0) {
                if (i2 == i4) {
                    break;
                }
                i2 += i6;
                i5 -= iAbs;
            }
            i += i7;
            i3 = i3;
            c1218a = this;
        }
        return new a(c1365t, c1365t2, i8, (byte) 0);
    }
}
