package com.google.p028a.p047g.p048a.p049a;

import com.google.p028a.p035c.C1190a;
import java.util.List;

/* JADX INFO: renamed from: com.google.a.g.a.a.a */
/* JADX INFO: compiled from: BitArrayBuilder.java */
/* JADX INFO: loaded from: classes.dex */
final class C1248a {
    private C1248a() {
    }

    /* JADX INFO: renamed from: a */
    private static C1190a m2050a(List<C1268b> list) {
        int size = (list.size() << 1) - 1;
        if (list.get(list.size() - 1).f2399b == null) {
            size--;
        }
        C1190a c1190a = new C1190a(size * 12);
        int i = list.get(0).f2399b.f2422a;
        int i2 = 0;
        for (int i3 = 11; i3 >= 0; i3--) {
            if (((1 << i3) & i) != 0) {
                c1190a.m1758b(i2);
            }
            i2++;
        }
        for (int i4 = 1; i4 < list.size(); i4++) {
            C1268b c1268b = list.get(i4);
            int i5 = c1268b.f2398a.f2422a;
            int i6 = i2;
            for (int i7 = 11; i7 >= 0; i7--) {
                if (((1 << i7) & i5) != 0) {
                    c1190a.m1758b(i6);
                }
                i6++;
            }
            if (c1268b.f2399b != null) {
                int i8 = c1268b.f2399b.f2422a;
                for (int i9 = 11; i9 >= 0; i9--) {
                    if (((1 << i9) & i8) != 0) {
                        c1190a.m1758b(i6);
                    }
                    i6++;
                }
            }
            i2 = i6;
        }
        return c1190a;
    }
}
