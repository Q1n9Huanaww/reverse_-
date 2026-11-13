package com.google.p028a.p056i.p057a;

import android.support.v7.widget.ActivityChooserView;

/* JADX INFO: renamed from: com.google.a.i.a.g */
/* JADX INFO: compiled from: FormatInformation.java */
/* JADX INFO: loaded from: classes.dex */
final class C1338g {

    /* JADX INFO: renamed from: c */
    private static final int f2739c = 21522;

    /* JADX INFO: renamed from: d */
    private static final int[][] f2740d = {new int[]{f2739c, 0}, new int[]{20773, 1}, new int[]{24188, 2}, new int[]{23371, 3}, new int[]{17913, 4}, new int[]{16590, 5}, new int[]{20375, 6}, new int[]{19104, 7}, new int[]{30660, 8}, new int[]{29427, 9}, new int[]{32170, 10}, new int[]{30877, 11}, new int[]{26159, 12}, new int[]{25368, 13}, new int[]{27713, 14}, new int[]{26998, 15}, new int[]{5769, 16}, new int[]{5054, 17}, new int[]{7399, 18}, new int[]{6608, 19}, new int[]{1890, 20}, new int[]{597, 21}, new int[]{3340, 22}, new int[]{2107, 23}, new int[]{13663, 24}, new int[]{12392, 25}, new int[]{16177, 26}, new int[]{14854, 27}, new int[]{9396, 28}, new int[]{8579, 29}, new int[]{11994, 30}, new int[]{11245, 31}};

    /* JADX INFO: renamed from: a */
    final EnumC1337f f2741a;

    /* JADX INFO: renamed from: b */
    final byte f2742b;

    private C1338g(int i) {
        this.f2741a = EnumC1337f.m2460a((i >> 3) & 3);
        this.f2742b = (byte) (i & 7);
    }

    /* JADX INFO: renamed from: a */
    static int m2461a(int i, int i2) {
        return Integer.bitCount(i ^ i2);
    }

    /* JADX INFO: renamed from: b */
    static C1338g m2464b(int i, int i2) {
        C1338g c1338gM2465c = m2465c(i, i2);
        return c1338gM2465c != null ? c1338gM2465c : m2465c(i ^ f2739c, i2 ^ f2739c);
    }

    /* JADX INFO: renamed from: c */
    private static C1338g m2465c(int i, int i2) {
        int iBitCount;
        int[][] iArr = f2740d;
        int i3 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        int i4 = 0;
        for (int[] iArr2 : iArr) {
            int i5 = iArr2[0];
            if (i5 == i || i5 == i2) {
                return new C1338g(iArr2[1]);
            }
            int iBitCount2 = Integer.bitCount(i ^ i5);
            if (iBitCount2 < i3) {
                i4 = iArr2[1];
                i3 = iBitCount2;
            }
            if (i != i2 && (iBitCount = Integer.bitCount(i5 ^ i2)) < i3) {
                i4 = iArr2[1];
                i3 = iBitCount;
            }
        }
        if (i3 <= 3) {
            return new C1338g(i4);
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    private EnumC1337f m2462a() {
        return this.f2741a;
    }

    /* JADX INFO: renamed from: b */
    private byte m2463b() {
        return this.f2742b;
    }

    public final int hashCode() {
        return (this.f2741a.ordinal() << 3) | this.f2742b;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C1338g)) {
            return false;
        }
        C1338g c1338g = (C1338g) obj;
        return this.f2741a == c1338g.f2741a && this.f2742b == c1338g.f2742b;
    }
}
