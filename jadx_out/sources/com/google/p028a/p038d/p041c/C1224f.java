package com.google.p028a.p038d.p041c;

/* JADX INFO: renamed from: com.google.a.d.c.f */
/* JADX INFO: compiled from: EdifactEncoder.java */
/* JADX INFO: loaded from: classes.dex */
final class C1224f implements InterfaceC1225g {
    @Override // com.google.p028a.p038d.p041c.InterfaceC1225g
    /* JADX INFO: renamed from: a */
    public final int mo1923a() {
        return 4;
    }

    C1224f() {
    }

    @Override // com.google.p028a.p038d.p041c.InterfaceC1225g
    /* JADX INFO: renamed from: a */
    public final void mo1924a(C1226h c1226h) {
        boolean z;
        StringBuilder sb = new StringBuilder();
        while (true) {
            z = true;
            if (!c1226h.m1966b()) {
                break;
            }
            char cM1962a = c1226h.m1962a();
            if (cM1962a >= ' ' && cM1962a <= '?') {
                sb.append(cM1962a);
            } else if (cM1962a >= '@' && cM1962a <= '^') {
                sb.append((char) (cM1962a - '@'));
            } else {
                C1228j.m1982c(cM1962a);
            }
            c1226h.f2226f++;
            if (sb.length() >= 4) {
                c1226h.m1965a(m1946a(sb));
                sb.delete(0, 4);
                if (C1228j.m1974a(c1226h.f2221a, c1226h.f2226f, 4) != 4) {
                    c1226h.f2227g = 0;
                    break;
                }
            }
        }
        sb.append((char) 31);
        try {
            int length = sb.length();
            if (length == 0) {
                c1226h.f2227g = 0;
                return;
            }
            if (length == 1) {
                c1226h.m1968d();
                int length2 = c1226h.f2228h.f2257b - c1226h.f2225e.length();
                int iM1967c = c1226h.m1967c();
                if (iM1967c > length2) {
                    c1226h.m1964a(c1226h.f2225e.length() + 1);
                    length2 = c1226h.f2228h.f2257b - c1226h.f2225e.length();
                }
                if (iM1967c <= length2 && length2 <= 2) {
                    c1226h.f2227g = 0;
                    return;
                }
            }
            if (length > 4) {
                throw new IllegalStateException("Count must not exceed 4");
            }
            int i = length - 1;
            String strM1946a = m1946a(sb);
            if (!(!c1226h.m1966b()) || i > 2) {
                z = false;
            }
            if (i <= 2) {
                c1226h.m1964a(c1226h.f2225e.length() + i);
                if (c1226h.f2228h.f2257b - c1226h.f2225e.length() >= 3) {
                    c1226h.m1964a(c1226h.f2225e.length() + strM1946a.length());
                    z = false;
                }
            }
            if (!z) {
                c1226h.m1965a(strM1946a);
            } else {
                c1226h.f2228h = null;
                c1226h.f2226f -= i;
            }
            c1226h.f2227g = 0;
        } catch (Throwable th) {
            c1226h.f2227g = 0;
            throw th;
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m1948a(C1226h c1226h, CharSequence charSequence) {
        try {
            int length = charSequence.length();
            if (length == 0) {
                c1226h.f2227g = 0;
                return;
            }
            boolean z = true;
            if (length == 1) {
                c1226h.m1968d();
                int length2 = c1226h.f2228h.f2257b - c1226h.f2225e.length();
                int iM1967c = c1226h.m1967c();
                if (iM1967c > length2) {
                    c1226h.m1964a(c1226h.f2225e.length() + 1);
                    length2 = c1226h.f2228h.f2257b - c1226h.f2225e.length();
                }
                if (iM1967c <= length2 && length2 <= 2) {
                    c1226h.f2227g = 0;
                    return;
                }
            }
            if (length > 4) {
                throw new IllegalStateException("Count must not exceed 4");
            }
            int i = length - 1;
            String strM1946a = m1946a(charSequence);
            if (!(!c1226h.m1966b()) || i > 2) {
                z = false;
            }
            if (i <= 2) {
                c1226h.m1964a(c1226h.f2225e.length() + i);
                if (c1226h.f2228h.f2257b - c1226h.f2225e.length() >= 3) {
                    c1226h.m1964a(c1226h.f2225e.length() + strM1946a.length());
                    z = false;
                }
            }
            if (!z) {
                c1226h.m1965a(strM1946a);
            } else {
                c1226h.f2228h = null;
                c1226h.f2226f -= i;
            }
            c1226h.f2227g = 0;
        } catch (Throwable th) {
            c1226h.f2227g = 0;
            throw th;
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m1947a(char c, StringBuilder sb) {
        if (c >= ' ' && c <= '?') {
            sb.append(c);
        } else if (c >= '@' && c <= '^') {
            sb.append((char) (c - '@'));
        } else {
            C1228j.m1982c(c);
        }
    }

    /* JADX INFO: renamed from: a */
    private static String m1946a(CharSequence charSequence) {
        int length = charSequence.length() - 0;
        if (length == 0) {
            throw new IllegalStateException("StringBuilder must not be empty");
        }
        int iCharAt = (charSequence.charAt(0) << 18) + ((length >= 2 ? charSequence.charAt(1) : (char) 0) << '\f') + ((length >= 3 ? charSequence.charAt(2) : (char) 0) << 6) + (length >= 4 ? charSequence.charAt(3) : (char) 0);
        char c = (char) ((iCharAt >> 16) & 255);
        char c2 = (char) ((iCharAt >> 8) & 255);
        char c3 = (char) (iCharAt & 255);
        StringBuilder sb = new StringBuilder(3);
        sb.append(c);
        if (length >= 2) {
            sb.append(c2);
        }
        if (length >= 3) {
            sb.append(c3);
        }
        return sb.toString();
    }
}
