package com.google.p028a.p047g;

import com.google.p028a.C1368w;
import com.google.p028a.EnumC1138a;
import com.google.p028a.EnumC1245g;
import com.google.p028a.InterfaceC1367v;
import com.google.p028a.p035c.C1194b;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.g.s */
/* JADX INFO: compiled from: OneDimensionalCodeWriter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1295s implements InterfaceC1367v {
    /* JADX INFO: renamed from: a */
    public int mo2198a() {
        return 10;
    }

    /* JADX INFO: renamed from: a */
    public abstract boolean[] mo2159a(String str);

    @Override // com.google.p028a.InterfaceC1367v
    /* JADX INFO: renamed from: a */
    public final C1194b mo1516a(String str, EnumC1138a enumC1138a, int i, int i2) throws C1368w {
        return mo1517a(str, enumC1138a, i, i2, null);
    }

    @Override // com.google.p028a.InterfaceC1367v
    /* JADX INFO: renamed from: a */
    public C1194b mo1517a(String str, EnumC1138a enumC1138a, int i, int i2, Map<EnumC1245g, ?> map) throws C1368w {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        }
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("Negative size is not allowed. Input: " + i + 'x' + i2);
        }
        int iMo2198a = mo2198a();
        if (map != null && map.containsKey(EnumC1245g.MARGIN)) {
            iMo2198a = Integer.parseInt(map.get(EnumC1245g.MARGIN).toString());
        }
        return m2197a(mo2159a(str), i, i2, iMo2198a);
    }

    /* JADX INFO: renamed from: a */
    private static C1194b m2197a(boolean[] zArr, int i, int i2, int i3) {
        int length = zArr.length;
        int i4 = i3 + length;
        int iMax = Math.max(i, i4);
        int iMax2 = Math.max(1, i2);
        int i5 = iMax / i4;
        C1194b c1194b = new C1194b(iMax, iMax2);
        int i6 = (iMax - (length * i5)) / 2;
        int i7 = 0;
        while (i7 < length) {
            if (zArr[i7]) {
                c1194b.m1788a(i6, 0, i5, iMax2);
            }
            i7++;
            i6 += i5;
        }
        return c1194b;
    }

    /* JADX INFO: renamed from: a */
    protected static int m2196a(boolean[] zArr, int i, int[] iArr, boolean z) {
        int length = iArr.length;
        int i2 = i;
        boolean z2 = z;
        int i3 = 0;
        int i4 = 0;
        while (i3 < length) {
            int i5 = iArr[i3];
            int i6 = i2;
            int i7 = 0;
            while (i7 < i5) {
                zArr[i6] = z2;
                i7++;
                i6++;
            }
            i4 += i5;
            z2 = !z2;
            i3++;
            i2 = i6;
        }
        return i4;
    }
}
