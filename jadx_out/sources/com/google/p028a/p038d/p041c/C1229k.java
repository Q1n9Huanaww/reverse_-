package com.google.p028a.p038d.p041c;

import com.cyjh.mobileanjian.ipc.share.proto.UiMessage;
import com.google.p028a.C1238f;
import com.p024ds.daisi.services.BootService;

/* JADX INFO: renamed from: com.google.a.d.c.k */
/* JADX INFO: compiled from: SymbolInfo.java */
/* JADX INFO: loaded from: classes.dex */
public class C1229k {

    /* JADX INFO: renamed from: a */
    static final C1229k[] f2255a;

    /* JADX INFO: renamed from: g */
    private static C1229k[] f2256g;

    /* JADX INFO: renamed from: b */
    final int f2257b;

    /* JADX INFO: renamed from: c */
    final int f2258c;

    /* JADX INFO: renamed from: d */
    public final int f2259d;

    /* JADX INFO: renamed from: e */
    public final int f2260e;

    /* JADX INFO: renamed from: f */
    final int f2261f;

    /* JADX INFO: renamed from: h */
    private final boolean f2262h;

    /* JADX INFO: renamed from: i */
    private final int f2263i;

    /* JADX INFO: renamed from: j */
    private final int f2264j;

    static {
        C1229k[] c1229kArr = {new C1229k(false, 3, 5, 8, 8, 1), new C1229k(false, 5, 7, 10, 10, 1), new C1229k(true, 5, 7, 16, 6, 1), new C1229k(false, 8, 10, 12, 12, 1), new C1229k(true, 10, 11, 14, 6, 2), new C1229k(false, 12, 12, 14, 14, 1), new C1229k(true, 16, 14, 24, 10, 1), new C1229k(false, 18, 14, 16, 16, 1), new C1229k(false, 22, 18, 18, 18, 1), new C1229k(true, 22, 18, 16, 10, 2), new C1229k(false, 30, 20, 20, 20, 1), new C1229k(true, 32, 24, 16, 14, 2), new C1229k(false, 36, 24, 22, 22, 1), new C1229k(false, 44, 28, 24, 24, 1), new C1229k(true, 49, 28, 22, 14, 2), new C1229k(false, 62, 36, 14, 14, 4), new C1229k(false, 86, 42, 16, 16, 4), new C1229k(false, 114, 48, 18, 18, 4), new C1229k(false, 144, 56, 20, 20, 4), new C1229k(false, 174, 68, 22, 22, 4), new C1229k(false, UiMessage.CommandToUi.Command_Type.SET_RADIO_GROUP_VALUE, 84, 24, 24, 4, 102, 42), new C1229k(false, 280, 112, 14, 14, 16, 140, 56), new C1229k(false, 368, 144, 16, 16, 16, 92, 36), new C1229k(false, 456, 192, 18, 18, 16, 114, 48), new C1229k(false, 576, 224, 20, 20, 16, 144, 56), new C1229k(false, 696, BootService.MSG_DEFAULT, 22, 22, 16, 174, 68), new C1229k(false, 816, 336, 24, 24, 16, 136, 56), new C1229k(false, 1050, UiMessage.CommandToUi.Command_Type.GET_VALUE_VALUE, 18, 18, 36, 175, 68), new C1229k(false, 1304, 496, 20, 20, 36, 163, 62), new C1222d()};
        f2255a = c1229kArr;
        f2256g = c1229kArr;
    }

    /* JADX INFO: renamed from: a */
    private static void m1992a(C1229k[] c1229kArr) {
        f2256g = c1229kArr;
    }

    private C1229k(boolean z, int i, int i2, int i3, int i4, int i5) {
        this(z, i, i2, i3, i4, i5, i, i2);
    }

    C1229k(boolean z, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.f2262h = z;
        this.f2257b = i;
        this.f2258c = i2;
        this.f2259d = i3;
        this.f2260e = i4;
        this.f2263i = i5;
        this.f2264j = i6;
        this.f2261f = i7;
    }

    /* JADX INFO: renamed from: b */
    private static C1229k m1993b(int i) {
        return m1990a(i, EnumC1230l.FORCE_NONE, true);
    }

    /* JADX INFO: renamed from: a */
    private static C1229k m1988a(int i, EnumC1230l enumC1230l) {
        return m1990a(i, enumC1230l, true);
    }

    /* JADX INFO: renamed from: a */
    private static C1229k m1991a(int i, boolean z, boolean z2) {
        return m1990a(i, z ? EnumC1230l.FORCE_NONE : EnumC1230l.FORCE_SQUARE, z2);
    }

    /* JADX INFO: renamed from: a */
    private static C1229k m1990a(int i, EnumC1230l enumC1230l, boolean z) {
        return m1989a(i, enumC1230l, null, null, z);
    }

    /* JADX INFO: renamed from: a */
    public static C1229k m1989a(int i, EnumC1230l enumC1230l, C1238f c1238f, C1238f c1238f2, boolean z) {
        for (C1229k c1229k : f2256g) {
            if (!(enumC1230l == EnumC1230l.FORCE_SQUARE && c1229k.f2262h) && ((enumC1230l != EnumC1230l.FORCE_RECTANGLE || c1229k.f2262h) && ((c1238f == null || (c1229k.m2002d() >= c1238f.f2309a && c1229k.m2003e() >= c1238f.f2310b)) && ((c1238f2 == null || (c1229k.m2002d() <= c1238f2.f2309a && c1229k.m2003e() <= c1238f2.f2310b)) && i <= c1229k.f2257b)))) {
                return c1229k;
            }
        }
        if (z) {
            throw new IllegalArgumentException("Can't find a symbol arrangement that matches the message. Data codewords: ".concat(String.valueOf(i)));
        }
        return null;
    }

    /* JADX INFO: renamed from: f */
    private int m1994f() {
        int i = this.f2263i;
        if (i == 4) {
            return 2;
        }
        if (i == 16) {
            return 4;
        }
        if (i == 36) {
            return 6;
        }
        switch (i) {
            case 1:
                return 1;
            case 2:
                return 2;
            default:
                throw new IllegalStateException("Cannot handle this number of data regions");
        }
    }

    /* JADX INFO: renamed from: g */
    private int m1995g() {
        int i = this.f2263i;
        if (i == 4) {
            return 2;
        }
        if (i == 16) {
            return 4;
        }
        if (i == 36) {
            return 6;
        }
        switch (i) {
            case 1:
            case 2:
                return 1;
            default:
                throw new IllegalStateException("Cannot handle this number of data regions");
        }
    }

    /* JADX INFO: renamed from: b */
    public final int m2000b() {
        return m1994f() * this.f2259d;
    }

    /* JADX INFO: renamed from: c */
    public final int m2001c() {
        return m1995g() * this.f2260e;
    }

    /* JADX INFO: renamed from: d */
    public final int m2002d() {
        return m2000b() + (m1994f() << 1);
    }

    /* JADX INFO: renamed from: e */
    public final int m2003e() {
        return m2001c() + (m1995g() << 1);
    }

    /* JADX INFO: renamed from: h */
    private int m1996h() {
        return this.f2257b + this.f2258c;
    }

    /* JADX INFO: renamed from: a */
    public int mo1931a() {
        return this.f2257b / this.f2264j;
    }

    /* JADX INFO: renamed from: i */
    private int m1997i() {
        return this.f2257b;
    }

    /* JADX INFO: renamed from: j */
    private int m1998j() {
        return this.f2258c;
    }

    /* JADX INFO: renamed from: a */
    public int mo1932a(int i) {
        return this.f2264j;
    }

    /* JADX INFO: renamed from: k */
    private int m1999k() {
        return this.f2261f;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f2262h ? "Rectangular Symbol:" : "Square Symbol:");
        sb.append(" data region ");
        sb.append(this.f2259d);
        sb.append('x');
        sb.append(this.f2260e);
        sb.append(", symbol size ");
        sb.append(m2002d());
        sb.append('x');
        sb.append(m2003e());
        sb.append(", symbol data size ");
        sb.append(m2000b());
        sb.append('x');
        sb.append(m2001c());
        sb.append(", codewords ");
        sb.append(this.f2257b);
        sb.append('+');
        sb.append(this.f2258c);
        return sb.toString();
    }
}
