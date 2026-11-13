package com.google.p028a.p035c.p037b;

import com.cyjh.mobileanjian.ipc.share.proto.UiMessage;

/* JADX INFO: renamed from: com.google.a.c.b.a */
/* JADX INFO: compiled from: GenericGF.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1195a {

    /* JADX INFO: renamed from: a */
    public static final C1195a f2081a = new C1195a(4201, 4096, 1);

    /* JADX INFO: renamed from: b */
    public static final C1195a f2082b = new C1195a(1033, 1024, 1);

    /* JADX INFO: renamed from: c */
    public static final C1195a f2083c = new C1195a(67, 64, 1);

    /* JADX INFO: renamed from: d */
    public static final C1195a f2084d = new C1195a(19, 16, 1);

    /* JADX INFO: renamed from: e */
    public static final C1195a f2085e = new C1195a(285, 256, 0);

    /* JADX INFO: renamed from: f */
    public static final C1195a f2086f;

    /* JADX INFO: renamed from: g */
    public static final C1195a f2087g;

    /* JADX INFO: renamed from: h */
    public static final C1195a f2088h;

    /* JADX INFO: renamed from: i */
    final int[] f2089i;

    /* JADX INFO: renamed from: j */
    final C1196b f2090j;

    /* JADX INFO: renamed from: k */
    final C1196b f2091k;

    /* JADX INFO: renamed from: l */
    final int f2092l;

    /* JADX INFO: renamed from: m */
    final int f2093m;

    /* JADX INFO: renamed from: n */
    private final int[] f2094n;

    /* JADX INFO: renamed from: o */
    private final int f2095o;

    /* JADX INFO: renamed from: b */
    static int m1797b(int i, int i2) {
        return i ^ i2;
    }

    static {
        C1195a c1195a = new C1195a(UiMessage.CommandToUi.Command_Type.SET_ENABLED_VALUE, 256, 1);
        f2086f = c1195a;
        f2087g = c1195a;
        f2088h = f2083c;
    }

    private C1195a(int i, int i2, int i3) {
        this.f2095o = i;
        this.f2092l = i2;
        this.f2093m = i3;
        this.f2089i = new int[i2];
        this.f2094n = new int[i2];
        int i4 = 1;
        for (int i5 = 0; i5 < i2; i5++) {
            this.f2089i[i5] = i4;
            i4 <<= 1;
            if (i4 >= i2) {
                i4 = (i4 ^ i) & (i2 - 1);
            }
        }
        for (int i6 = 0; i6 < i2 - 1; i6++) {
            this.f2094n[this.f2089i[i6]] = i6;
        }
        this.f2090j = new C1196b(this, new int[]{0});
        this.f2091k = new C1196b(this, new int[]{1});
    }

    /* JADX INFO: renamed from: a */
    private C1196b m1796a() {
        return this.f2090j;
    }

    /* JADX INFO: renamed from: b */
    private C1196b m1798b() {
        return this.f2091k;
    }

    /* JADX INFO: renamed from: a */
    final C1196b m1803a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        if (i2 == 0) {
            return this.f2090j;
        }
        int[] iArr = new int[i + 1];
        iArr[0] = i2;
        return new C1196b(this, iArr);
    }

    /* JADX INFO: renamed from: c */
    private int m1800c(int i) {
        return this.f2089i[i];
    }

    /* JADX INFO: renamed from: a */
    final int m1802a(int i) {
        if (i == 0) {
            throw new IllegalArgumentException();
        }
        return this.f2094n[i];
    }

    /* JADX INFO: renamed from: b */
    final int m1804b(int i) {
        if (i == 0) {
            throw new ArithmeticException();
        }
        return this.f2089i[(this.f2092l - this.f2094n[i]) - 1];
    }

    /* JADX INFO: renamed from: c */
    final int m1805c(int i, int i2) {
        if (i == 0 || i2 == 0) {
            return 0;
        }
        return this.f2089i[(this.f2094n[i] + this.f2094n[i2]) % (this.f2092l - 1)];
    }

    /* JADX INFO: renamed from: c */
    private int m1799c() {
        return this.f2092l;
    }

    /* JADX INFO: renamed from: d */
    private int m1801d() {
        return this.f2093m;
    }

    public final String toString() {
        return "GF(0x" + Integer.toHexString(this.f2095o) + ',' + this.f2092l + ')';
    }
}
