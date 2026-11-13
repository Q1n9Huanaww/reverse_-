package com.google.p028a.p038d.p041c;

/* JADX INFO: renamed from: com.google.a.d.c.m */
/* JADX INFO: compiled from: TextEncoder.java */
/* JADX INFO: loaded from: classes.dex */
final class C1231m extends C1221c {
    @Override // com.google.p028a.p038d.p041c.C1221c, com.google.p028a.p038d.p041c.InterfaceC1225g
    /* JADX INFO: renamed from: a */
    public final int mo1923a() {
        return 2;
    }

    C1231m() {
    }

    @Override // com.google.p028a.p038d.p041c.C1221c
    /* JADX INFO: renamed from: a */
    final int mo1929a(char c, StringBuilder sb) {
        if (c == ' ') {
            sb.append((char) 3);
            return 1;
        }
        if (c >= '0' && c <= '9') {
            sb.append((char) ((c - '0') + 4));
            return 1;
        }
        if (c >= 'a' && c <= 'z') {
            sb.append((char) ((c - 'a') + 14));
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
        if (c == '`') {
            sb.append((char) 2);
            sb.append((char) (c - '`'));
            return 2;
        }
        if (c >= 'A' && c <= 'Z') {
            sb.append((char) 2);
            sb.append((char) ((c - 'A') + 1));
            return 2;
        }
        if (c >= '{' && c <= 127) {
            sb.append((char) 2);
            sb.append((char) ((c - '{') + 27));
            return 2;
        }
        sb.append("\u0001\u001e");
        return mo1929a((char) (c - 128), sb) + 2;
    }
}
