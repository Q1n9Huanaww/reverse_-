package com.google.p028a.p038d.p041c;

/* JADX INFO: renamed from: com.google.a.d.c.n */
/* JADX INFO: compiled from: X12Encoder.java */
/* JADX INFO: loaded from: classes.dex */
final class C1232n extends C1221c {
    @Override // com.google.p028a.p038d.p041c.C1221c, com.google.p028a.p038d.p041c.InterfaceC1225g
    /* JADX INFO: renamed from: a */
    public final int mo1923a() {
        return 3;
    }

    C1232n() {
    }

    @Override // com.google.p028a.p038d.p041c.C1221c, com.google.p028a.p038d.p041c.InterfaceC1225g
    /* JADX INFO: renamed from: a */
    public final void mo1924a(C1226h c1226h) {
        StringBuilder sb = new StringBuilder();
        while (c1226h.m1966b()) {
            char cM1962a = c1226h.m1962a();
            c1226h.f2226f++;
            mo1929a(cM1962a, sb);
            if (sb.length() % 3 == 0) {
                m1928a(c1226h, sb);
                if (C1228j.m1974a(c1226h.f2221a, c1226h.f2226f, 3) != 3) {
                    c1226h.f2227g = 0;
                    break;
                }
            }
        }
        mo1930b(c1226h, sb);
    }

    @Override // com.google.p028a.p038d.p041c.C1221c
    /* JADX INFO: renamed from: a */
    final int mo1929a(char c, StringBuilder sb) {
        if (c == '\r') {
            sb.append((char) 0);
        } else if (c == ' ') {
            sb.append((char) 3);
        } else if (c == '*') {
            sb.append((char) 1);
        } else if (c == '>') {
            sb.append((char) 2);
        } else if (c >= '0' && c <= '9') {
            sb.append((char) ((c - '0') + 4));
        } else if (c >= 'A' && c <= 'Z') {
            sb.append((char) ((c - 'A') + 14));
        } else {
            C1228j.m1982c(c);
        }
        return 1;
    }

    @Override // com.google.p028a.p038d.p041c.C1221c
    /* JADX INFO: renamed from: b */
    final void mo1930b(C1226h c1226h, StringBuilder sb) {
        c1226h.m1968d();
        int length = c1226h.f2228h.f2257b - c1226h.f2225e.length();
        c1226h.f2226f -= sb.length();
        if (c1226h.m1967c() > 1 || length > 1 || c1226h.m1967c() != length) {
            c1226h.m1963a((char) 254);
        }
        if (c1226h.f2227g < 0) {
            c1226h.f2227g = 0;
        }
    }
}
