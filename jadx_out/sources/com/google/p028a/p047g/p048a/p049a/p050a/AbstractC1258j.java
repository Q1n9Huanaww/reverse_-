package com.google.p028a.p047g.p048a.p049a.p050a;

import com.google.p028a.C1303h;
import com.google.p028a.C1358m;
import com.google.p028a.p035c.C1190a;

/* JADX INFO: renamed from: com.google.a.g.a.a.a.j */
/* JADX INFO: compiled from: AbstractExpandedDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1258j {

    /* JADX INFO: renamed from: b */
    final C1190a f2370b;

    /* JADX INFO: renamed from: c */
    final C1267s f2371c;

    /* JADX INFO: renamed from: a */
    public abstract String mo2053a() throws C1358m, C1303h;

    AbstractC1258j(C1190a c1190a) {
        this.f2370b = c1190a;
        this.f2371c = new C1267s(c1190a);
    }

    /* JADX INFO: renamed from: b */
    private C1190a m2060b() {
        return this.f2370b;
    }

    /* JADX INFO: renamed from: c */
    private C1267s m2061c() {
        return this.f2371c;
    }

    /* JADX INFO: renamed from: a */
    private static AbstractC1258j m2059a(C1190a c1190a) {
        if (c1190a.m1755a(1)) {
            return new C1255g(c1190a);
        }
        if (!c1190a.m1755a(2)) {
            return new C1259k(c1190a);
        }
        switch (C1267s.m2089a(c1190a, 1, 4)) {
            case 4:
                return new C1249a(c1190a);
            case 5:
                return new C1250b(c1190a);
            default:
                switch (C1267s.m2089a(c1190a, 1, 5)) {
                    case 12:
                        return new C1251c(c1190a);
                    case 13:
                        return new C1252d(c1190a);
                    default:
                        switch (C1267s.m2089a(c1190a, 1, 7)) {
                            case 56:
                                return new C1253e(c1190a, "310", "11");
                            case 57:
                                return new C1253e(c1190a, "320", "11");
                            case 58:
                                return new C1253e(c1190a, "310", "13");
                            case 59:
                                return new C1253e(c1190a, "320", "13");
                            case 60:
                                return new C1253e(c1190a, "310", "15");
                            case 61:
                                return new C1253e(c1190a, "320", "15");
                            case 62:
                                return new C1253e(c1190a, "310", "17");
                            case 63:
                                return new C1253e(c1190a, "320", "17");
                            default:
                                throw new IllegalStateException("unknown decoder: ".concat(String.valueOf(c1190a)));
                        }
                }
        }
    }
}
