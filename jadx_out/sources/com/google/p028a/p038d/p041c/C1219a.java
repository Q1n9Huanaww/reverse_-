package com.google.p028a.p038d.p041c;

/* JADX INFO: renamed from: com.google.a.d.c.a */
/* JADX INFO: compiled from: ASCIIEncoder.java */
/* JADX INFO: loaded from: classes.dex */
final class C1219a implements InterfaceC1225g {
    @Override // com.google.p028a.p038d.p041c.InterfaceC1225g
    /* JADX INFO: renamed from: a */
    public final int mo1923a() {
        return 0;
    }

    C1219a() {
    }

    /* JADX INFO: renamed from: a */
    private static char m1922a(char c, char c2) {
        if (C1228j.m1980a(c) && C1228j.m1980a(c2)) {
            return (char) (((c - '0') * 10) + (c2 - '0') + 130);
        }
        throw new IllegalArgumentException("not digits: " + c + c2);
    }

    @Override // com.google.p028a.p038d.p041c.InterfaceC1225g
    /* JADX INFO: renamed from: a */
    public final void mo1924a(C1226h c1226h) {
        int i;
        String str = c1226h.f2221a;
        int i2 = c1226h.f2226f;
        int length = str.length();
        if (i2 < length) {
            char cCharAt = str.charAt(i2);
            i = 0;
            while (C1228j.m1980a(cCharAt) && i2 < length) {
                i++;
                i2++;
                if (i2 < length) {
                    cCharAt = str.charAt(i2);
                }
            }
        } else {
            i = 0;
        }
        if (i >= 2) {
            char cCharAt2 = c1226h.f2221a.charAt(c1226h.f2226f);
            char cCharAt3 = c1226h.f2221a.charAt(c1226h.f2226f + 1);
            if (C1228j.m1980a(cCharAt2) && C1228j.m1980a(cCharAt3)) {
                c1226h.m1963a((char) (((cCharAt2 - '0') * 10) + (cCharAt3 - '0') + 130));
                c1226h.f2226f += 2;
                return;
            } else {
                throw new IllegalArgumentException("not digits: " + cCharAt2 + cCharAt3);
            }
        }
        char cM1962a = c1226h.m1962a();
        int iM1974a = C1228j.m1974a(c1226h.f2221a, c1226h.f2226f, 0);
        if (iM1974a != 0) {
            switch (iM1974a) {
                case 1:
                    c1226h.m1963a((char) 230);
                    c1226h.f2227g = 1;
                    return;
                case 2:
                    c1226h.m1963a((char) 239);
                    c1226h.f2227g = 2;
                    return;
                case 3:
                    c1226h.m1963a((char) 238);
                    c1226h.f2227g = 3;
                    return;
                case 4:
                    c1226h.m1963a((char) 240);
                    c1226h.f2227g = 4;
                    return;
                case 5:
                    c1226h.m1963a((char) 231);
                    c1226h.f2227g = 5;
                    return;
                default:
                    throw new IllegalStateException("Illegal mode: ".concat(String.valueOf(iM1974a)));
            }
        }
        if (C1228j.m1981b(cM1962a)) {
            c1226h.m1963a((char) 235);
            c1226h.m1963a((char) ((cM1962a - 128) + 1));
            c1226h.f2226f++;
        } else {
            c1226h.m1963a((char) (cM1962a + 1));
            c1226h.f2226f++;
        }
    }
}
