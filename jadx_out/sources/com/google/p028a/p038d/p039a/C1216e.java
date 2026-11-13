package com.google.p028a.p038d.p039a;

import com.google.p028a.C1303h;

/* JADX INFO: renamed from: com.google.a.d.a.e */
/* JADX INFO: compiled from: Version.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1216e {

    /* JADX INFO: renamed from: h */
    private static final C1216e[] f2200h;

    /* JADX INFO: renamed from: a */
    final int f2201a;

    /* JADX INFO: renamed from: b */
    final int f2202b;

    /* JADX INFO: renamed from: c */
    final int f2203c;

    /* JADX INFO: renamed from: d */
    final int f2204d;

    /* JADX INFO: renamed from: e */
    final int f2205e;

    /* JADX INFO: renamed from: f */
    final b f2206f;

    /* JADX INFO: renamed from: g */
    final int f2207g;

    private C1216e(int i, int i2, int i3, int i4, int i5, b bVar) {
        this.f2201a = i;
        this.f2202b = i2;
        this.f2203c = i3;
        this.f2204d = i4;
        this.f2205e = i5;
        this.f2206f = bVar;
        int i6 = bVar.f2210a;
        int i7 = 0;
        for (a aVar : bVar.f2211b) {
            i7 += aVar.f2208a * (aVar.f2209b + i6);
        }
        this.f2207g = i7;
    }

    /* JADX INFO: renamed from: a */
    private int m1895a() {
        return this.f2201a;
    }

    /* JADX INFO: renamed from: b */
    private int m1897b() {
        return this.f2202b;
    }

    /* JADX INFO: renamed from: c */
    private int m1898c() {
        return this.f2203c;
    }

    /* JADX INFO: renamed from: d */
    private int m1899d() {
        return this.f2204d;
    }

    /* JADX INFO: renamed from: e */
    private int m1900e() {
        return this.f2205e;
    }

    /* JADX INFO: renamed from: f */
    private int m1901f() {
        return this.f2207g;
    }

    /* JADX INFO: renamed from: g */
    private b m1902g() {
        return this.f2206f;
    }

    /* JADX INFO: renamed from: a */
    public static C1216e m1896a(int i, int i2) throws C1303h {
        if ((i & 1) != 0 || (i2 & 1) != 0) {
            throw C1303h.m2217a();
        }
        for (C1216e c1216e : f2200h) {
            if (c1216e.f2202b == i && c1216e.f2203c == i2) {
                return c1216e;
            }
        }
        throw C1303h.m2217a();
    }

    /* JADX INFO: renamed from: com.google.a.d.a.e$b */
    /* JADX INFO: compiled from: Version.java */
    static final class b {

        /* JADX INFO: renamed from: a */
        final int f2210a;

        /* JADX INFO: renamed from: b */
        final a[] f2211b;

        /* synthetic */ b(int i, a aVar, byte b) {
            this(i, aVar);
        }

        /* synthetic */ b(a aVar, a aVar2, byte b) {
            this(aVar, aVar2);
        }

        private b(int i, a aVar) {
            this.f2210a = i;
            this.f2211b = new a[]{aVar};
        }

        private b(a aVar, a aVar2) {
            this.f2210a = 62;
            this.f2211b = new a[]{aVar, aVar2};
        }

        /* JADX INFO: renamed from: a */
        private int m1906a() {
            return this.f2210a;
        }

        /* JADX INFO: renamed from: b */
        private a[] m1907b() {
            return this.f2211b;
        }
    }

    /* JADX INFO: renamed from: com.google.a.d.a.e$a */
    /* JADX INFO: compiled from: Version.java */
    static final class a {

        /* JADX INFO: renamed from: a */
        final int f2208a;

        /* JADX INFO: renamed from: b */
        final int f2209b;

        /* synthetic */ a(int i, int i2, byte b) {
            this(i, i2);
        }

        private a(int i, int i2) {
            this.f2208a = i;
            this.f2209b = i2;
        }

        /* JADX INFO: renamed from: a */
        private int m1904a() {
            return this.f2208a;
        }

        /* JADX INFO: renamed from: b */
        private int m1905b() {
            return this.f2209b;
        }
    }

    public final String toString() {
        return String.valueOf(this.f2201a);
    }

    /* JADX INFO: renamed from: h */
    private static C1216e[] m1903h() {
        int i = 1;
        byte b2 = 0;
        int i2 = 5;
        int i3 = 8;
        int i4 = 7;
        C1216e c1216e = new C1216e(3, 14, 14, 12, 12, new b(10, new a(i, i3, b2), b2));
        int i5 = 2;
        int i6 = 12;
        int i7 = 18;
        C1216e c1216e2 = new C1216e(5, 18, 18, 16, 16, new b(14, new a(i, 18, b2), b2));
        int i8 = 4;
        C1216e c1216e3 = new C1216e(7, 22, 22, 20, 20, new b(20, new a(i, 30, b2), b2));
        int i9 = 6;
        a aVar = new a(i, 36, b2);
        a aVar2 = new a(i, 44, b2);
        a aVar3 = new a(i, 62, b2);
        a aVar4 = new a(i, 86, b2);
        a aVar5 = new a(i, 114, b2);
        a aVar6 = new a(i, 144, b2);
        int i10 = 56;
        a aVar7 = new a(i, 174, b2);
        return new C1216e[]{new C1216e(1, 10, 10, 8, 8, new b(i2, new a(i, 3, b2), b2)), new C1216e(2, 12, 12, 10, 10, new b(i4, new a(i, i2, b2), b2)), c1216e, new C1216e(4, 16, 16, 14, 14, new b(i6, new a(i, i6, b2), b2)), c1216e2, new C1216e(6, 20, 20, 18, 18, new b(i7, new a(i, 22, b2), b2)), c1216e3, new C1216e(8, 24, 24, 22, 22, new b(24, aVar, b2)), new C1216e(9, 26, 26, 24, 24, new b(28, aVar2, b2)), new C1216e(10, 32, 32, 14, 14, new b(36, aVar3, b2)), new C1216e(11, 36, 36, 16, 16, new b(42, aVar4, b2)), new C1216e(12, 40, 40, 18, 18, new b(48, aVar5, b2)), new C1216e(13, 44, 44, 20, 20, new b(56, aVar6, b2)), new C1216e(14, 48, 48, 22, 22, new b(68, aVar7, b2)), new C1216e(15, 52, 52, 24, 24, new b(42, new a(i5, 102, b2), b2)), new C1216e(16, 64, 64, 14, 14, new b(i10, new a(i5, 140, b2), b2)), new C1216e(17, 72, 72, 16, 16, new b(36, new a(i8, 92, b2), b2)), new C1216e(18, 80, 80, 18, 18, new b(48, new a(i8, 114, b2), b2)), new C1216e(19, 88, 88, 20, 20, new b(i10, new a(i8, 144, b2), b2)), new C1216e(20, 96, 96, 22, 22, new b(68, new a(i8, 174, b2), b2)), new C1216e(21, 104, 104, 24, 24, new b(i10, new a(i9, 136, b2), b2)), new C1216e(22, 120, 120, 18, 18, new b(68, new a(i9, 175, b2), b2)), new C1216e(23, 132, 132, 20, 20, new b(62, new a(i3, 163, b2), b2)), new C1216e(24, 144, 144, 22, 22, new b(new a(i3, 156, b2), new a(i5, 155, b2), b2)), new C1216e(25, 8, 18, 6, 16, new b(i4, new a(i, i2, b2), b2)), new C1216e(26, 8, 32, 6, 14, new b(11, new a(i, 10, b2), b2)), new C1216e(27, 12, 26, 10, 24, new b(14, new a(i, 16, b2), b2)), new C1216e(28, 12, 36, 10, 16, new b(i7, new a(i, 22, b2), b2)), new C1216e(29, 16, 36, 14, 16, new b(24, new a(i, 32, b2), b2)), new C1216e(30, 16, 48, 14, 22, new b(28, new a(i, 49, b2), b2))};
    }

    static {
        int i = 1;
        byte b2 = 0;
        int i2 = 5;
        int i3 = 8;
        int i4 = 7;
        C1216e c1216e = new C1216e(3, 14, 14, 12, 12, new b(10, new a(i, i3, b2), b2));
        int i5 = 2;
        int i6 = 12;
        int i7 = 18;
        C1216e c1216e2 = new C1216e(5, 18, 18, 16, 16, new b(14, new a(i, 18, b2), b2));
        int i8 = 4;
        C1216e c1216e3 = new C1216e(7, 22, 22, 20, 20, new b(20, new a(i, 30, b2), b2));
        int i9 = 6;
        a aVar = new a(i, 36, b2);
        a aVar2 = new a(i, 44, b2);
        a aVar3 = new a(i, 62, b2);
        a aVar4 = new a(i, 86, b2);
        a aVar5 = new a(i, 114, b2);
        a aVar6 = new a(i, 144, b2);
        int i10 = 56;
        a aVar7 = new a(i, 174, b2);
        f2200h = new C1216e[]{new C1216e(1, 10, 10, 8, 8, new b(i2, new a(i, 3, b2), b2)), new C1216e(2, 12, 12, 10, 10, new b(i4, new a(i, i2, b2), b2)), c1216e, new C1216e(4, 16, 16, 14, 14, new b(i6, new a(i, i6, b2), b2)), c1216e2, new C1216e(6, 20, 20, 18, 18, new b(i7, new a(i, 22, b2), b2)), c1216e3, new C1216e(8, 24, 24, 22, 22, new b(24, aVar, b2)), new C1216e(9, 26, 26, 24, 24, new b(28, aVar2, b2)), new C1216e(10, 32, 32, 14, 14, new b(36, aVar3, b2)), new C1216e(11, 36, 36, 16, 16, new b(42, aVar4, b2)), new C1216e(12, 40, 40, 18, 18, new b(48, aVar5, b2)), new C1216e(13, 44, 44, 20, 20, new b(56, aVar6, b2)), new C1216e(14, 48, 48, 22, 22, new b(68, aVar7, b2)), new C1216e(15, 52, 52, 24, 24, new b(42, new a(i5, 102, b2), b2)), new C1216e(16, 64, 64, 14, 14, new b(i10, new a(i5, 140, b2), b2)), new C1216e(17, 72, 72, 16, 16, new b(36, new a(i8, 92, b2), b2)), new C1216e(18, 80, 80, 18, 18, new b(48, new a(i8, 114, b2), b2)), new C1216e(19, 88, 88, 20, 20, new b(i10, new a(i8, 144, b2), b2)), new C1216e(20, 96, 96, 22, 22, new b(68, new a(i8, 174, b2), b2)), new C1216e(21, 104, 104, 24, 24, new b(i10, new a(i9, 136, b2), b2)), new C1216e(22, 120, 120, 18, 18, new b(68, new a(i9, 175, b2), b2)), new C1216e(23, 132, 132, 20, 20, new b(62, new a(i3, 163, b2), b2)), new C1216e(24, 144, 144, 22, 22, new b(new a(i3, 156, b2), new a(i5, 155, b2), b2)), new C1216e(25, 8, 18, 6, 16, new b(i4, new a(i, i2, b2), b2)), new C1216e(26, 8, 32, 6, 14, new b(11, new a(i, 10, b2), b2)), new C1216e(27, 12, 26, 10, 24, new b(14, new a(i, 16, b2), b2)), new C1216e(28, 12, 36, 10, 16, new b(i7, new a(i, 22, b2), b2)), new C1216e(29, 16, 36, 14, 16, new b(24, new a(i, 32, b2), b2)), new C1216e(30, 16, 48, 14, 22, new b(28, new a(i, 49, b2), b2))};
    }
}
