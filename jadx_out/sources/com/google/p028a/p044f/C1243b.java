package com.google.p028a.p044f;

import com.google.p028a.AbstractC1362q;
import com.google.p028a.C1189c;
import com.google.p028a.C1358m;
import com.google.p028a.C1363r;
import com.google.p028a.C1365t;
import com.google.p028a.EnumC1233e;
import com.google.p028a.InterfaceC1361p;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.f.b */
/* JADX INFO: compiled from: GenericMultipleBarcodeReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1243b implements InterfaceC1244c {

    /* JADX INFO: renamed from: a */
    private static final int f2320a = 100;

    /* JADX INFO: renamed from: b */
    private static final int f2321b = 4;

    /* JADX INFO: renamed from: c */
    private final InterfaceC1361p f2322c;

    private C1243b(InterfaceC1361p interfaceC1361p) {
        this.f2322c = interfaceC1361p;
    }

    @Override // com.google.p028a.p044f.InterfaceC1244c
    /* JADX INFO: renamed from: a_ */
    public final C1363r[] mo2023a_(C1189c c1189c) throws C1358m {
        return mo2024b(c1189c);
    }

    @Override // com.google.p028a.p044f.InterfaceC1244c
    /* JADX INFO: renamed from: b */
    public final C1363r[] mo2024b(C1189c c1189c) throws C1358m {
        ArrayList arrayList = new ArrayList();
        m2031a(c1189c, null, arrayList, 0, 0, 0);
        if (arrayList.isEmpty()) {
            throw C1358m.m2611a();
        }
        return (C1363r[]) arrayList.toArray(new C1363r[arrayList.size()]);
    }

    /* JADX INFO: renamed from: a */
    private void m2031a(C1189c c1189c, Map<EnumC1233e, ?> map, List<C1363r> list, int i, int i2, int i3) {
        boolean z;
        int i4;
        C1365t[] c1365tArr;
        C1363r c1363r;
        int i5 = i;
        C1189c c1189cM1739a = c1189c;
        int i6 = i2;
        int i7 = i3;
        while (i7 <= 4) {
            try {
                C1363r c1363rMo1490a = this.f2322c.mo1490a(c1189cM1739a, map);
                Iterator<C1363r> it2 = list.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (it2.next().f2839a.equals(c1363rMo1490a.f2839a)) {
                            z = true;
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
                if (z) {
                    list = list;
                } else {
                    C1365t[] c1365tArr2 = c1363rMo1490a.f2842d;
                    if (c1365tArr2 == null) {
                        c1363r = c1363rMo1490a;
                    } else {
                        C1365t[] c1365tArr3 = new C1365t[c1365tArr2.length];
                        for (int i8 = 0; i8 < c1365tArr2.length; i8++) {
                            C1365t c1365t = c1365tArr2[i8];
                            if (c1365t != null) {
                                c1365tArr3[i8] = new C1365t(c1365t.f2858a + i5, c1365t.f2859b + i6);
                            }
                        }
                        c1363r = new C1363r(c1363rMo1490a.f2839a, c1363rMo1490a.f2840b, c1363rMo1490a.f2841c, c1365tArr3, c1363rMo1490a.f2843e, c1363rMo1490a.f2845g);
                        c1363r.m2625a(c1363rMo1490a.f2844f);
                    }
                    list.add(c1363r);
                }
                C1365t[] c1365tArr4 = c1363rMo1490a.f2842d;
                if (c1365tArr4 == null || c1365tArr4.length == 0) {
                    return;
                }
                int iM1738a = c1189cM1739a.m1738a();
                int iM1740b = c1189cM1739a.m1740b();
                int length = c1365tArr4.length;
                float f = 0.0f;
                float f2 = iM1740b;
                float f3 = 0.0f;
                float f4 = iM1738a;
                int i9 = 0;
                while (i9 < length) {
                    C1365t c1365t2 = c1365tArr4[i9];
                    if (c1365t2 != null) {
                        c1365tArr = c1365tArr4;
                        float f5 = c1365t2.f2858a;
                        float f6 = c1365t2.f2859b;
                        if (f5 < f4) {
                            f4 = f5;
                        }
                        if (f6 < f2) {
                            f2 = f6;
                        }
                        if (f5 <= f3) {
                            f5 = f3;
                        }
                        if (f6 > f) {
                            f3 = f5;
                            f = f6;
                        } else {
                            f3 = f5;
                        }
                    } else {
                        c1365tArr = c1365tArr4;
                    }
                    i9++;
                    c1365tArr4 = c1365tArr;
                }
                if (f4 > 100.0f) {
                    i4 = i;
                    m2031a(c1189cM1739a.m1739a(0, 0, (int) f4, iM1740b), map, list, i4, i6, i7 + 1);
                } else {
                    i4 = i;
                }
                if (f2 > 100.0f) {
                    m2031a(c1189cM1739a.m1739a(0, 0, iM1738a, (int) f2), map, list, i4, i6, i7 + 1);
                }
                float f7 = f3;
                if (f7 < iM1738a - 100) {
                    int i10 = (int) f7;
                    m2031a(c1189cM1739a.m1739a(i10, 0, iM1738a - i10, iM1740b), map, list, i4 + i10, i6, i7 + 1);
                }
                float f8 = f;
                if (f8 >= iM1740b - 100) {
                    return;
                }
                int i11 = (int) f8;
                c1189cM1739a = c1189cM1739a.m1739a(0, i11, iM1738a, iM1740b - i11);
                i6 += i11;
                i7++;
                i5 = i4;
            } catch (AbstractC1362q unused) {
                return;
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private static C1363r m2030a(C1363r c1363r, int i, int i2) {
        C1365t[] c1365tArr = c1363r.f2842d;
        if (c1365tArr == null) {
            return c1363r;
        }
        C1365t[] c1365tArr2 = new C1365t[c1365tArr.length];
        for (int i3 = 0; i3 < c1365tArr.length; i3++) {
            C1365t c1365t = c1365tArr[i3];
            if (c1365t != null) {
                c1365tArr2[i3] = new C1365t(c1365t.f2858a + i, c1365t.f2859b + i2);
            }
        }
        C1363r c1363r2 = new C1363r(c1363r.f2839a, c1363r.f2840b, c1363r.f2841c, c1365tArr2, c1363r.f2843e, c1363r.f2845g);
        c1363r2.m2625a(c1363r.f2844f);
        return c1363r2;
    }
}
