package com.google.p028a.p038d.p041c;

/* JADX INFO: renamed from: com.google.a.d.c.c */
/* JADX INFO: compiled from: C40Encoder.java */
/* JADX INFO: loaded from: classes.dex */
class C1221c implements InterfaceC1225g {
    @Override // com.google.p028a.p038d.p041c.InterfaceC1225g
    /* JADX INFO: renamed from: a */
    public int mo1923a() {
        return 1;
    }

    C1221c() {
    }

    @Override // com.google.p028a.p038d.p041c.InterfaceC1225g
    /* JADX INFO: renamed from: a */
    public void mo1924a(C1226h c1226h) {
        StringBuilder sb = new StringBuilder();
        while (c1226h.m1966b()) {
            char cM1962a = c1226h.m1962a();
            c1226h.f2226f++;
            int iMo1929a = mo1929a(cM1962a, sb);
            int length = c1226h.f2225e.length() + ((sb.length() / 3) << 1);
            c1226h.m1964a(length);
            int i = c1226h.f2228h.f2257b - length;
            if (!c1226h.m1966b()) {
                StringBuilder sb2 = new StringBuilder();
                if (sb.length() % 3 == 2 && (i < 2 || i > 2)) {
                    iMo1929a = m1926a(c1226h, sb, sb2, iMo1929a);
                }
                while (sb.length() % 3 == 1 && ((iMo1929a <= 3 && i != 1) || iMo1929a > 3)) {
                    iMo1929a = m1926a(c1226h, sb, sb2, iMo1929a);
                }
                break;
            }
            if (sb.length() % 3 == 0 && C1228j.m1974a(c1226h.f2221a, c1226h.f2226f, mo1923a()) != mo1923a()) {
                c1226h.f2227g = 0;
                break;
            }
        }
        mo1930b(c1226h, sb);
    }

    /* JADX INFO: renamed from: a */
    private int m1926a(C1226h c1226h, StringBuilder sb, StringBuilder sb2, int i) {
        int length = sb.length();
        sb.delete(length - i, length);
        c1226h.f2226f--;
        int iMo1929a = mo1929a(c1226h.m1962a(), sb2);
        c1226h.f2228h = null;
        return iMo1929a;
    }

    /* JADX INFO: renamed from: b */
    void mo1930b(C1226h c1226h, StringBuilder sb) {
        int length = (sb.length() / 3) << 1;
        int length2 = sb.length() % 3;
        int length3 = c1226h.f2225e.length() + length;
        c1226h.m1964a(length3);
        int i = c1226h.f2228h.f2257b - length3;
        if (length2 == 2) {
            sb.append((char) 0);
            while (sb.length() >= 3) {
                m1928a(c1226h, sb);
            }
            if (c1226h.m1966b()) {
                c1226h.m1963a((char) 254);
            }
        } else if (i == 1 && length2 == 1) {
            while (sb.length() >= 3) {
                m1928a(c1226h, sb);
            }
            if (c1226h.m1966b()) {
                c1226h.m1963a((char) 254);
            }
            c1226h.f2226f--;
        } else if (length2 == 0) {
            while (sb.length() >= 3) {
                m1928a(c1226h, sb);
            }
            if (i > 0 || c1226h.m1966b()) {
                c1226h.m1963a((char) 254);
            }
        } else {
            throw new IllegalStateException("Unexpected case. Please report!");
        }
        c1226h.f2227g = 0;
    }

    /* JADX INFO: renamed from: a */
    int mo1929a(char c, StringBuilder sb) {
        if (c == ' ') {
            sb.append((char) 3);
            return 1;
        }
        if (c >= '0' && c <= '9') {
            sb.append((char) ((c - '0') + 4));
            return 1;
        }
        if (c >= 'A' && c <= 'Z') {
            sb.append((char) ((c - 'A') + 14));
            return 1;
        }
        if (c < ' ') {
            sb.append((char) 0);
            sb.append(c);
            return 2;
        }
        if (c >= '!' && c <= '/') {
            sb.append((char) 1);
            sb.append((char) (c - '!'));
            return 2;
        }
        if (c >= ':' && c <= '@') {
            sb.append((char) 1);
            sb.append((char) ((c - ':') + 15));
            return 2;
        }
        if (c >= '[' && c <= '_') {
            sb.append((char) 1);
            sb.append((char) ((c - '[') + 22));
            return 2;
        }
        if (c >= '`' && c <= 127) {
            sb.append((char) 2);
            sb.append((char) (c - '`'));
            return 2;
        }
        sb.append("\u0001\u001e");
        return mo1929a((char) (c - 128), sb) + 2;
    }

    /* JADX INFO: renamed from: a */
    private static String m1927a(CharSequence charSequence) {
        int iCharAt = (charSequence.charAt(0) * 1600) + (charSequence.charAt(1) * '(') + charSequence.charAt(2) + 1;
        return new String(new char[]{(char) (iCharAt / 256), (char) (iCharAt % 256)});
    }

    /* JADX INFO: renamed from: a */
    static void m1928a(C1226h c1226h, StringBuilder sb) {
        int iCharAt = (sb.charAt(0) * 1600) + (sb.charAt(1) * '(') + sb.charAt(2) + 1;
        c1226h.m1965a(new String(new char[]{(char) (iCharAt / 256), (char) (iCharAt % 256)}));
        sb.delete(0, 3);
    }
}
