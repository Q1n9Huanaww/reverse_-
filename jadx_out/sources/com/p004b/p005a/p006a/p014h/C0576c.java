package com.p004b.p005a.p006a.p014h;

import javax.security.auth.x500.X500Principal;

/* JADX INFO: renamed from: com.b.a.a.h.c */
/* JADX INFO: compiled from: DistinguishedNameParser.java */
/* JADX INFO: loaded from: classes.dex */
final class C0576c {

    /* JADX INFO: renamed from: a */
    private final String f438a;

    /* JADX INFO: renamed from: b */
    private final int f439b;

    /* JADX INFO: renamed from: c */
    private int f440c;

    /* JADX INFO: renamed from: d */
    private int f441d;

    /* JADX INFO: renamed from: e */
    private int f442e;

    /* JADX INFO: renamed from: f */
    private int f443f;

    /* JADX INFO: renamed from: g */
    private char[] f444g;

    public C0576c(X500Principal x500Principal) {
        this.f438a = x500Principal.getName("RFC2253");
        this.f439b = this.f438a.length();
    }

    /* JADX INFO: renamed from: a */
    private String m310a() {
        while (this.f440c < this.f439b && this.f444g[this.f440c] == ' ') {
            this.f440c++;
        }
        if (this.f440c == this.f439b) {
            return null;
        }
        this.f441d = this.f440c;
        this.f440c++;
        while (this.f440c < this.f439b && this.f444g[this.f440c] != '=' && this.f444g[this.f440c] != ' ') {
            this.f440c++;
        }
        if (this.f440c >= this.f439b) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f438a);
        }
        this.f442e = this.f440c;
        if (this.f444g[this.f440c] == ' ') {
            while (this.f440c < this.f439b && this.f444g[this.f440c] != '=' && this.f444g[this.f440c] == ' ') {
                this.f440c++;
            }
            if (this.f444g[this.f440c] != '=' || this.f440c == this.f439b) {
                throw new IllegalStateException("Unexpected end of DN: " + this.f438a);
            }
        }
        do {
            this.f440c++;
            if (this.f440c >= this.f439b) {
                break;
            }
        } while (this.f444g[this.f440c] == ' ');
        if (this.f442e - this.f441d > 4 && this.f444g[this.f441d + 3] == '.' && ((this.f444g[this.f441d] == 'O' || this.f444g[this.f441d] == 'o') && ((this.f444g[this.f441d + 1] == 'I' || this.f444g[this.f441d + 1] == 'i') && (this.f444g[this.f441d + 2] == 'D' || this.f444g[this.f441d + 2] == 'd')))) {
            this.f441d += 4;
        }
        return new String(this.f444g, this.f441d, this.f442e - this.f441d);
    }

    /* JADX INFO: renamed from: b */
    private String m311b() {
        if (this.f440c + 4 >= this.f439b) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f438a);
        }
        this.f441d = this.f440c;
        this.f440c++;
        while (true) {
            if (this.f440c == this.f439b || this.f444g[this.f440c] == '+' || this.f444g[this.f440c] == ',' || this.f444g[this.f440c] == ';') {
                this.f442e = this.f440c;
                break;
            }
            if (this.f444g[this.f440c] == ' ') {
                this.f442e = this.f440c;
                this.f440c++;
                while (this.f440c < this.f439b && this.f444g[this.f440c] == ' ') {
                    this.f440c++;
                }
                break;
            }
            if (this.f444g[this.f440c] >= 'A' && this.f444g[this.f440c] <= 'F') {
                char[] cArr = this.f444g;
                int i = this.f440c;
                cArr[i] = (char) (cArr[i] + ' ');
            }
            this.f440c++;
        }
        int i2 = this.f442e - this.f441d;
        if (i2 < 5 || (i2 & 1) == 0) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f438a);
        }
        byte[] bArr = new byte[i2 / 2];
        int i3 = this.f441d + 1;
        for (int i4 = 0; i4 < bArr.length; i4++) {
            bArr[i4] = (byte) m309a(i3);
            i3 += 2;
        }
        return new String(this.f444g, this.f441d, i2);
    }

    /* JADX INFO: renamed from: c */
    private String m312c() {
        this.f441d = this.f440c;
        this.f442e = this.f440c;
        while (this.f440c < this.f439b) {
            char c = this.f444g[this.f440c];
            if (c != ' ') {
                if (c != ';') {
                    if (c == '\\') {
                        char[] cArr = this.f444g;
                        int i = this.f442e;
                        this.f442e = i + 1;
                        cArr[i] = m313d();
                        this.f440c++;
                    } else {
                        switch (c) {
                            case '+':
                            case ',':
                                break;
                            default:
                                char[] cArr2 = this.f444g;
                                int i2 = this.f442e;
                                this.f442e = i2 + 1;
                                cArr2[i2] = this.f444g[this.f440c];
                                this.f440c++;
                                continue;
                        }
                    }
                }
                return new String(this.f444g, this.f441d, this.f442e - this.f441d);
            }
            this.f443f = this.f442e;
            this.f440c++;
            char[] cArr3 = this.f444g;
            int i3 = this.f442e;
            this.f442e = i3 + 1;
            cArr3[i3] = ' ';
            while (this.f440c < this.f439b && this.f444g[this.f440c] == ' ') {
                char[] cArr4 = this.f444g;
                int i4 = this.f442e;
                this.f442e = i4 + 1;
                cArr4[i4] = ' ';
                this.f440c++;
            }
            if (this.f440c == this.f439b || this.f444g[this.f440c] == ',' || this.f444g[this.f440c] == '+' || this.f444g[this.f440c] == ';') {
                return new String(this.f444g, this.f441d, this.f443f - this.f441d);
            }
        }
        return new String(this.f444g, this.f441d, this.f442e - this.f441d);
    }

    /* JADX INFO: renamed from: d */
    private char m313d() {
        this.f440c++;
        if (this.f440c == this.f439b) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f438a);
        }
        char c = this.f444g[this.f440c];
        if (c != ' ' && c != '%' && c != '\\' && c != '_') {
            switch (c) {
                case '\"':
                case '#':
                    break;
                default:
                    switch (c) {
                        case '*':
                        case '+':
                        case ',':
                            break;
                        default:
                            switch (c) {
                                case ';':
                                case '<':
                                case '=':
                                case '>':
                                    break;
                                default:
                                    return m314e();
                            }
                            break;
                    }
                    break;
            }
        }
        return this.f444g[this.f440c];
    }

    /* JADX INFO: renamed from: e */
    private char m314e() {
        int i;
        int i2;
        int iM309a = m309a(this.f440c);
        this.f440c++;
        if (iM309a < 128) {
            return (char) iM309a;
        }
        if (iM309a < 192 || iM309a > 247) {
            return '?';
        }
        if (iM309a <= 223) {
            i2 = iM309a & 31;
            i = 1;
        } else if (iM309a <= 239) {
            i = 2;
            i2 = iM309a & 15;
        } else {
            i = 3;
            i2 = iM309a & 7;
        }
        for (int i3 = 0; i3 < i; i3++) {
            this.f440c++;
            if (this.f440c == this.f439b || this.f444g[this.f440c] != '\\') {
                return '?';
            }
            this.f440c++;
            int iM309a2 = m309a(this.f440c);
            this.f440c++;
            if ((iM309a2 & 192) != 128) {
                return '?';
            }
            i2 = (i2 << 6) + (iM309a2 & 63);
        }
        return (char) i2;
    }

    /* JADX INFO: renamed from: a */
    private int m309a(int i) {
        int i2;
        int i3;
        int i4 = i + 1;
        if (i4 >= this.f439b) {
            throw new IllegalStateException("Malformed DN: " + this.f438a);
        }
        char c = this.f444g[i];
        if (c >= '0' && c <= '9') {
            i2 = c - '0';
        } else if (c >= 'a' && c <= 'f') {
            i2 = c - 'W';
        } else {
            if (c < 'A' || c > 'F') {
                throw new IllegalStateException("Malformed DN: " + this.f438a);
            }
            i2 = c - '7';
        }
        char c2 = this.f444g[i4];
        if (c2 >= '0' && c2 <= '9') {
            i3 = c2 - '0';
        } else if (c2 >= 'a' && c2 <= 'f') {
            i3 = c2 - 'W';
        } else {
            if (c2 < 'A' || c2 > 'F') {
                throw new IllegalStateException("Malformed DN: " + this.f438a);
            }
            i3 = c2 - '7';
        }
        return (i2 << 4) + i3;
    }

    /* JADX INFO: renamed from: a */
    public final String m315a(String str) {
        this.f440c = 0;
        this.f441d = 0;
        this.f442e = 0;
        this.f443f = 0;
        this.f444g = this.f438a.toCharArray();
        String strM310a = m310a();
        if (strM310a == null) {
            return null;
        }
        do {
            String str2 = "";
            if (this.f440c == this.f439b) {
                return null;
            }
            switch (this.f444g[this.f440c]) {
                case '\"':
                    this.f440c++;
                    this.f441d = this.f440c;
                    this.f442e = this.f441d;
                    while (true) {
                        if (this.f440c == this.f439b) {
                            throw new IllegalStateException("Unexpected end of DN: " + this.f438a);
                        }
                        if (this.f444g[this.f440c] == '\"') {
                            this.f440c++;
                            while (this.f440c < this.f439b && this.f444g[this.f440c] == ' ') {
                                this.f440c++;
                            }
                            str2 = new String(this.f444g, this.f441d, this.f442e - this.f441d);
                        } else {
                            if (this.f444g[this.f440c] == '\\') {
                                this.f444g[this.f442e] = m313d();
                            } else {
                                this.f444g[this.f442e] = this.f444g[this.f440c];
                            }
                            this.f440c++;
                            this.f442e++;
                        }
                        break;
                    }
                    break;
                case '#':
                    str2 = m311b();
                    break;
                case '+':
                case ',':
                case ';':
                    break;
                default:
                    str2 = m312c();
                    break;
            }
            if (str.equalsIgnoreCase(strM310a)) {
                return str2;
            }
            if (this.f440c >= this.f439b) {
                return null;
            }
            if (this.f444g[this.f440c] != ',' && this.f444g[this.f440c] != ';' && this.f444g[this.f440c] != '+') {
                throw new IllegalStateException("Malformed DN: " + this.f438a);
            }
            this.f440c++;
            strM310a = m310a();
        } while (strM310a != null);
        throw new IllegalStateException("Malformed DN: " + this.f438a);
    }
}
