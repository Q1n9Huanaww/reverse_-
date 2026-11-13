package com.google.p028a.p047g;

import com.google.p028a.AbstractC1362q;
import com.google.p028a.C1189c;
import com.google.p028a.C1210d;
import com.google.p028a.C1303h;
import com.google.p028a.C1358m;
import com.google.p028a.C1363r;
import com.google.p028a.C1365t;
import com.google.p028a.EnumC1233e;
import com.google.p028a.EnumC1364s;
import com.google.p028a.InterfaceC1361p;
import com.google.p028a.p035c.C1190a;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.g.r */
/* JADX INFO: compiled from: OneDReader.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1294r implements InterfaceC1361p {
    /* JADX INFO: renamed from: a */
    public abstract C1363r mo2038a(int i, C1190a c1190a, Map<EnumC1233e, ?> map) throws C1210d, C1358m, C1303h;

    @Override // com.google.p028a.InterfaceC1361p
    /* JADX INFO: renamed from: a */
    public void mo1491a() {
    }

    @Override // com.google.p028a.InterfaceC1361p
    /* JADX INFO: renamed from: a */
    public C1363r mo1489a(C1189c c1189c) throws C1358m, C1303h {
        return mo1490a(c1189c, null);
    }

    @Override // com.google.p028a.InterfaceC1361p
    /* JADX INFO: renamed from: a */
    public C1363r mo1490a(C1189c c1189c, Map<EnumC1233e, ?> map) throws C1358m, C1303h {
        try {
            return m2194b(c1189c, map);
        } catch (C1358m e) {
            if (!(map != null && map.containsKey(EnumC1233e.TRY_HARDER)) || !c1189c.f2062a.f1937a.mo2426c()) {
                throw e;
            }
            C1189c c1189c2 = new C1189c(c1189c.f2062a.mo1562a(c1189c.f2062a.f1937a.mo2428e()));
            C1363r c1363rM2194b = m2194b(c1189c2, map);
            Map<EnumC1364s, Object> map2 = c1363rM2194b.f2844f;
            int iIntValue = 270;
            if (map2 != null && map2.containsKey(EnumC1364s.ORIENTATION)) {
                iIntValue = (((Integer) map2.get(EnumC1364s.ORIENTATION)).intValue() + 270) % 360;
            }
            c1363rM2194b.m2624a(EnumC1364s.ORIENTATION, Integer.valueOf(iIntValue));
            C1365t[] c1365tArr = c1363rM2194b.f2842d;
            if (c1365tArr != null) {
                int iM1740b = c1189c2.m1740b();
                for (int i = 0; i < c1365tArr.length; i++) {
                    c1365tArr[i] = new C1365t((iM1740b - c1365tArr[i].f2859b) - 1.0f, c1365tArr[i].f2858a);
                }
            }
            return c1363rM2194b;
        }
    }

    /* JADX INFO: renamed from: b */
    private C1363r m2194b(C1189c c1189c, Map<EnumC1233e, ?> map) throws C1358m {
        int i;
        int i2;
        int i3;
        int i4;
        int iM1738a = c1189c.m1738a();
        int iM1740b = c1189c.m1740b();
        C1190a c1190a = new C1190a(iM1738a);
        int i5 = 1;
        boolean z = map != null && map.containsKey(EnumC1233e.TRY_HARDER);
        int iMax = Math.max(1, iM1740b >> (z ? 8 : 5));
        int i6 = z ? iM1740b : 15;
        int i7 = iM1740b / 2;
        Map<EnumC1233e, ?> map2 = map;
        int i8 = 0;
        while (i8 < i6) {
            int i9 = i8 + 1;
            int i10 = i9 / 2;
            if (!((i8 & 1) == 0)) {
                i10 = -i10;
            }
            int i11 = (i10 * iMax) + i7;
            if (i11 < 0 || i11 >= iM1740b) {
                break;
            }
            try {
                C1190a c1190aMo1563a = c1189c.f2062a.mo1563a(i11, c1190a);
                int i12 = 0;
                while (i12 < 2) {
                    if (i12 == i5) {
                        c1190aMo1563a.m1761c();
                        if (map2 != null && map2.containsKey(EnumC1233e.NEED_RESULT_POINT_CALLBACK)) {
                            EnumMap enumMap = new EnumMap(EnumC1233e.class);
                            enumMap.putAll(map2);
                            enumMap.remove(EnumC1233e.NEED_RESULT_POINT_CALLBACK);
                            map2 = enumMap;
                        }
                    }
                    try {
                        C1363r c1363rMo2038a = mo2038a(i11, c1190aMo1563a, map2);
                        if (i12 == i5) {
                            c1363rMo2038a.m2624a(EnumC1364s.ORIENTATION, 180);
                            C1365t[] c1365tArr = c1363rMo2038a.f2842d;
                            if (c1365tArr != null) {
                                float f = iM1738a;
                                i3 = iM1738a;
                                try {
                                    i4 = iM1740b;
                                    try {
                                        c1365tArr[0] = new C1365t((f - c1365tArr[0].f2858a) - 1.0f, c1365tArr[0].f2859b);
                                        try {
                                            c1365tArr[1] = new C1365t((f - c1365tArr[1].f2858a) - 1.0f, c1365tArr[1].f2859b);
                                        } catch (AbstractC1362q unused) {
                                            continue;
                                            i12++;
                                            iM1738a = i3;
                                            iM1740b = i4;
                                            i5 = 1;
                                        }
                                    } catch (AbstractC1362q unused2) {
                                        i12++;
                                        iM1738a = i3;
                                        iM1740b = i4;
                                        i5 = 1;
                                    }
                                } catch (AbstractC1362q unused3) {
                                    i4 = iM1740b;
                                    i12++;
                                    iM1738a = i3;
                                    iM1740b = i4;
                                    i5 = 1;
                                }
                            }
                        }
                        return c1363rMo2038a;
                    } catch (AbstractC1362q unused4) {
                        i3 = iM1738a;
                    }
                }
                i = iM1738a;
                i2 = iM1740b;
                c1190a = c1190aMo1563a;
            } catch (C1358m unused5) {
                i = iM1738a;
                i2 = iM1740b;
            }
            i8 = i9;
            iM1738a = i;
            iM1740b = i2;
            i5 = 1;
        }
        throw C1358m.m2611a();
    }

    /* JADX INFO: renamed from: a */
    protected static void m2193a(C1190a c1190a, int i, int[] iArr) throws C1358m {
        int length = iArr.length;
        Arrays.fill(iArr, 0, length, 0);
        int i2 = c1190a.f2065b;
        if (i >= i2) {
            throw C1358m.m2611a();
        }
        boolean z = !c1190a.m1755a(i);
        int i3 = 0;
        while (i < i2) {
            if (c1190a.m1755a(i) == z) {
                i3++;
                if (i3 == length) {
                    break;
                }
                iArr[i3] = 1;
                z = !z;
            } else {
                iArr[i3] = iArr[i3] + 1;
            }
            i++;
        }
        if (i3 != length) {
            if (i3 != length - 1 || i != i2) {
                throw C1358m.m2611a();
            }
        }
    }

    /* JADX INFO: renamed from: b */
    protected static void m2195b(C1190a c1190a, int i, int[] iArr) throws C1358m {
        int length = iArr.length;
        boolean zM1755a = c1190a.m1755a(i);
        while (i > 0 && length >= 0) {
            i--;
            if (c1190a.m1755a(i) != zM1755a) {
                length--;
                zM1755a = !zM1755a;
            }
        }
        if (length >= 0) {
            throw C1358m.m2611a();
        }
        m2193a(c1190a, i + 1, iArr);
    }

    /* JADX INFO: renamed from: a */
    protected static float m2192a(int[] iArr, int[] iArr2, float f) {
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            i += iArr[i3];
            i2 += iArr2[i3];
        }
        if (i < i2) {
            return Float.POSITIVE_INFINITY;
        }
        float f2 = i;
        float f3 = f2 / i2;
        float f4 = f * f3;
        float f5 = 0.0f;
        for (int i4 = 0; i4 < length; i4++) {
            int i5 = iArr[i4];
            float f6 = iArr2[i4] * f3;
            float f7 = i5;
            float f8 = f7 > f6 ? f7 - f6 : f6 - f7;
            if (f8 > f4) {
                return Float.POSITIVE_INFINITY;
            }
            f5 += f8;
        }
        return f5 / f2;
    }
}
