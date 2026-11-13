package com.google.p028a.p035c.p037b;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: com.google.a.c.b.d */
/* JADX INFO: compiled from: ReedSolomonEncoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1198d {

    /* JADX INFO: renamed from: a */
    private final C1195a f2099a;

    /* JADX INFO: renamed from: b */
    private final List<C1196b> f2100b = new ArrayList();

    public C1198d(C1195a c1195a) {
        this.f2099a = c1195a;
        this.f2100b.add(new C1196b(c1195a, new int[]{1}));
    }

    /* JADX INFO: renamed from: a */
    private C1196b m1820a(int i) {
        if (i >= this.f2100b.size()) {
            C1196b c1196bM1813b = this.f2100b.get(this.f2100b.size() - 1);
            for (int size = this.f2100b.size(); size <= i; size++) {
                c1196bM1813b = c1196bM1813b.m1813b(new C1196b(this.f2099a, new int[]{1, this.f2099a.f2089i[(size - 1) + this.f2099a.f2093m]}));
                this.f2100b.add(c1196bM1813b);
            }
        }
        return this.f2100b.get(i);
    }

    /* JADX INFO: renamed from: a */
    public final void m1821a(int[] iArr, int i) {
        if (i == 0) {
            throw new IllegalArgumentException("No error correction bytes");
        }
        int length = iArr.length - i;
        if (length <= 0) {
            throw new IllegalArgumentException("No data bytes provided");
        }
        C1196b c1196bM1820a = m1820a(i);
        int[] iArr2 = new int[length];
        System.arraycopy(iArr, 0, iArr2, 0, length);
        int[] iArr3 = new C1196b(this.f2099a, iArr2).m1809a(i, 1).m1815c(c1196bM1820a)[1].f2096a;
        int length2 = i - iArr3.length;
        for (int i2 = 0; i2 < length2; i2++) {
            iArr[length + i2] = 0;
        }
        System.arraycopy(iArr3, 0, iArr, length + length2, iArr3.length);
    }
}
