package javax.mail.internet;

/* JADX INFO: compiled from: MailDateFormat.java */
/* JADX INFO: loaded from: classes.dex */
class MailDateParser {
    int index = 0;
    char[] orig;

    public MailDateParser(char[] cArr) {
        this.orig = null;
        this.orig = cArr;
    }

    public void skipUntilNumber() throws java.text.ParseException {
        while (true) {
            try {
                switch (this.orig[this.index]) {
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        return;
                    default:
                        this.index++;
                        break;
                }
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new java.text.ParseException("No Number Found", this.index);
            }
            throw new java.text.ParseException("No Number Found", this.index);
        }
    }

    public void skipWhiteSpace() {
        int length = this.orig.length;
        while (this.index < length) {
            char c = this.orig[this.index];
            if (c != '\r' && c != ' ') {
                switch (c) {
                    case '\t':
                    case '\n':
                        break;
                    default:
                        return;
                }
            }
            this.index++;
        }
    }

    public int peekChar() throws java.text.ParseException {
        if (this.index < this.orig.length) {
            return this.orig[this.index];
        }
        throw new java.text.ParseException("No more characters", this.index);
    }

    public void skipChar(char c) throws java.text.ParseException {
        if (this.index < this.orig.length) {
            if (this.orig[this.index] == c) {
                this.index++;
                return;
            }
            throw new java.text.ParseException("Wrong char", this.index);
        }
        throw new java.text.ParseException("No more characters", this.index);
    }

    public boolean skipIfChar(char c) throws java.text.ParseException {
        if (this.index < this.orig.length) {
            if (this.orig[this.index] != c) {
                return false;
            }
            this.index++;
            return true;
        }
        throw new java.text.ParseException("No more characters", this.index);
    }

    public int parseNumber() throws java.text.ParseException {
        int length = this.orig.length;
        boolean z = false;
        int i = 0;
        while (this.index < length) {
            switch (this.orig[this.index]) {
                case '0':
                    i *= 10;
                    break;
                case '1':
                    i = (i * 10) + 1;
                    break;
                case '2':
                    i = (i * 10) + 2;
                    break;
                case '3':
                    i = (i * 10) + 3;
                    break;
                case '4':
                    i = (i * 10) + 4;
                    break;
                case '5':
                    i = (i * 10) + 5;
                    break;
                case '6':
                    i = (i * 10) + 6;
                    break;
                case '7':
                    i = (i * 10) + 7;
                    break;
                case '8':
                    i = (i * 10) + 8;
                    break;
                case '9':
                    i = (i * 10) + 9;
                    break;
                default:
                    if (z) {
                        return i;
                    }
                    throw new java.text.ParseException("No Number found", this.index);
            }
            this.index++;
            z = true;
        }
        if (z) {
            return i;
        }
        throw new java.text.ParseException("No Number found", this.index);
    }

    /* JADX WARN: Code duplicated, block: B:53:0x00d1 A[Catch: ArrayIndexOutOfBoundsException -> 0x0174, TryCatch #0 {ArrayIndexOutOfBoundsException -> 0x0174, blocks: (B:2:0x0000, B:3:0x0022, B:5:0x0027, B:8:0x0035, B:13:0x0046, B:16:0x0054, B:22:0x0069, B:26:0x007b, B:32:0x0090, B:35:0x009e, B:47:0x00b9, B:53:0x00d1, B:65:0x00ec, B:70:0x00fc, B:73:0x010a, B:79:0x011e, B:82:0x012c, B:87:0x013d, B:93:0x0150, B:99:0x0164), top: B:108:0x0000 }] */
    /* JADX WARN: Code duplicated, block: B:65:0x00ec A[Catch: ArrayIndexOutOfBoundsException -> 0x0174, TryCatch #0 {ArrayIndexOutOfBoundsException -> 0x0174, blocks: (B:2:0x0000, B:3:0x0022, B:5:0x0027, B:8:0x0035, B:13:0x0046, B:16:0x0054, B:22:0x0069, B:26:0x007b, B:32:0x0090, B:35:0x009e, B:47:0x00b9, B:53:0x00d1, B:65:0x00ec, B:70:0x00fc, B:73:0x010a, B:79:0x011e, B:82:0x012c, B:87:0x013d, B:93:0x0150, B:99:0x0164), top: B:108:0x0000 }] */
    public int parseMonth() throws java.text.ParseException {
        char c;
        char c2;
        try {
            char[] cArr = this.orig;
            int i = this.index;
            this.index = i + 1;
            switch (cArr[i]) {
                case 'A':
                case 'a':
                    char[] cArr2 = this.orig;
                    int i2 = this.index;
                    this.index = i2 + 1;
                    char c3 = cArr2[i2];
                    if (c3 == 'P' || c3 == 'p') {
                        char[] cArr3 = this.orig;
                        int i3 = this.index;
                        this.index = i3 + 1;
                        char c4 = cArr3[i3];
                        if (c4 == 'R' || c4 == 'r') {
                            return 3;
                        }
                    } else if (c3 == 'U' || c3 == 'u') {
                        char[] cArr4 = this.orig;
                        int i4 = this.index;
                        this.index = i4 + 1;
                        char c5 = cArr4[i4];
                        if (c5 == 'G' || c5 == 'g') {
                            return 7;
                        }
                    }
                case 'D':
                case 'd':
                    char[] cArr5 = this.orig;
                    int i5 = this.index;
                    this.index = i5 + 1;
                    char c6 = cArr5[i5];
                    if (c6 == 'E' || c6 == 'e') {
                        char[] cArr6 = this.orig;
                        int i6 = this.index;
                        this.index = i6 + 1;
                        char c7 = cArr6[i6];
                        if (c7 == 'C' || c7 == 'c') {
                            return 11;
                        }
                    }
                    break;
                case 'F':
                case 'f':
                    char[] cArr7 = this.orig;
                    int i7 = this.index;
                    this.index = i7 + 1;
                    char c8 = cArr7[i7];
                    if (c8 == 'E' || c8 == 'e') {
                        char[] cArr8 = this.orig;
                        int i8 = this.index;
                        this.index = i8 + 1;
                        char c9 = cArr8[i8];
                        if (c9 == 'B' || c9 == 'b') {
                            return 1;
                        }
                    }
                    break;
                case 'J':
                case 'j':
                    char[] cArr9 = this.orig;
                    int i9 = this.index;
                    this.index = i9 + 1;
                    char c10 = cArr9[i9];
                    if (c10 == 'A') {
                        char[] cArr10 = this.orig;
                        int i10 = this.index;
                        this.index = i10 + 1;
                        c = cArr10[i10];
                        if (c != 'N' || c == 'n') {
                        }
                    } else {
                        if (c10 == 'U') {
                            char[] cArr11 = this.orig;
                            int i11 = this.index;
                            this.index = i11 + 1;
                            c2 = cArr11[i11];
                            if (c2 == 'N' && c2 != 'n') {
                                if (c2 != 'L' || c2 == 'l') {
                                }
                            }
                            return 5;
                        }
                        if (c10 == 'a') {
                            char[] cArr12 = this.orig;
                            int i12 = this.index;
                            this.index = i12 + 1;
                            c = cArr12[i12];
                            return c != 'N' ? 0 : 0;
                        }
                        if (c10 == 'u') {
                            char[] cArr13 = this.orig;
                            int i13 = this.index;
                            this.index = i13 + 1;
                            c2 = cArr13[i13];
                            if (c2 == 'N') {
                                return 5;
                            }
                            return c2 != 'L' ? 6 : 6;
                        }
                        break;
                    }
                    break;
                case 'M':
                case 'm':
                    char[] cArr14 = this.orig;
                    int i14 = this.index;
                    this.index = i14 + 1;
                    char c11 = cArr14[i14];
                    if (c11 == 'A' || c11 == 'a') {
                        char[] cArr15 = this.orig;
                        int i15 = this.index;
                        this.index = i15 + 1;
                        char c12 = cArr15[i15];
                        if (c12 != 'R' && c12 != 'r') {
                            if (c12 == 'Y' || c12 == 'y') {
                                return 4;
                            }
                        }
                        return 2;
                    }
                    break;
                case 'N':
                case 'n':
                    char[] cArr16 = this.orig;
                    int i16 = this.index;
                    this.index = i16 + 1;
                    char c13 = cArr16[i16];
                    if (c13 == 'O' || c13 == 'o') {
                        char[] cArr17 = this.orig;
                        int i17 = this.index;
                        this.index = i17 + 1;
                        char c14 = cArr17[i17];
                        if (c14 == 'V' || c14 == 'v') {
                            return 10;
                        }
                    }
                    break;
                case 'O':
                case 'o':
                    char[] cArr18 = this.orig;
                    int i18 = this.index;
                    this.index = i18 + 1;
                    char c15 = cArr18[i18];
                    if (c15 == 'C' || c15 == 'c') {
                        char[] cArr19 = this.orig;
                        int i19 = this.index;
                        this.index = i19 + 1;
                        char c16 = cArr19[i19];
                        if (c16 == 'T' || c16 == 't') {
                            return 9;
                        }
                    }
                    break;
                case 'S':
                case 's':
                    char[] cArr20 = this.orig;
                    int i20 = this.index;
                    this.index = i20 + 1;
                    char c17 = cArr20[i20];
                    if (c17 == 'E' || c17 == 'e') {
                        char[] cArr21 = this.orig;
                        int i21 = this.index;
                        this.index = i21 + 1;
                        char c18 = cArr21[i21];
                        if (c18 == 'P' || c18 == 'p') {
                            return 8;
                        }
                    }
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException unused) {
        }
        throw new java.text.ParseException("Bad Month", this.index);
    }

    public int parseTimeZone() throws java.text.ParseException {
        if (this.index >= this.orig.length) {
            throw new java.text.ParseException("No more characters", this.index);
        }
        char c = this.orig[this.index];
        if (c == '+' || c == '-') {
            return parseNumericTimeZone();
        }
        return parseAlphaTimeZone();
    }

    public int parseNumericTimeZone() throws java.text.ParseException {
        boolean z;
        char[] cArr = this.orig;
        int i = this.index;
        this.index = i + 1;
        char c = cArr[i];
        if (c == '+') {
            z = true;
        } else {
            if (c != '-') {
                throw new java.text.ParseException("Bad Numeric TimeZone", this.index);
            }
            z = false;
        }
        int number = parseNumber();
        int i2 = ((number / 100) * 60) + (number % 100);
        return z ? -i2 : i2;
    }

    /* JADX WARN: Code duplicated, block: B:27:0x006a  */
    /* JADX WARN: Code duplicated, block: B:29:0x0078  */
    /* JADX WARN: Code duplicated, block: B:45:0x00ad A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:48:0x00b9 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Code duplicated, block: B:56:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    public int parseAlphaTimeZone() throws java.text.ParseException {
        char c;
        char c2;
        try {
            char[] cArr = this.orig;
            int i = this.index;
            this.index = i + 1;
            char c3 = cArr[i];
            int i2 = 0;
            boolean z = true;
            switch (c3) {
                case 'C':
                case 'c':
                    i2 = 360;
                    if (z) {
                        return i2;
                    }
                    char[] cArr2 = this.orig;
                    int i3 = this.index;
                    this.index = i3 + 1;
                    c = cArr2[i3];
                    if (c != 'S' || c == 's') {
                        char[] cArr3 = this.orig;
                        int i4 = this.index;
                        this.index = i4 + 1;
                        c2 = cArr3[i4];
                        if (c2 != 'T' || c2 == 't') {
                            return i2;
                        }
                        throw new java.text.ParseException("Bad Alpha TimeZone", this.index);
                    }
                    if (c != 'D' && c != 'd') {
                        return i2;
                    }
                    char[] cArr4 = this.orig;
                    int i5 = this.index;
                    this.index = i5 + 1;
                    char c4 = cArr4[i5];
                    if (c4 == 'T' || c4 != 't') {
                        return i2 - 60;
                    }
                    throw new java.text.ParseException("Bad Alpha TimeZone", this.index);
                case 'E':
                case 'e':
                    i2 = 300;
                    if (z) {
                        return i2;
                    }
                    char[] cArr5 = this.orig;
                    int i6 = this.index;
                    this.index = i6 + 1;
                    c = cArr5[i6];
                    if (c != 'S') {
                        break;
                    }
                    char[] cArr6 = this.orig;
                    int i7 = this.index;
                    this.index = i7 + 1;
                    c2 = cArr6[i7];
                    if (c2 != 'T') {
                        return i2;
                    }
                    return i2;
                case 'G':
                case 'g':
                    char[] cArr7 = this.orig;
                    int i8 = this.index;
                    this.index = i8 + 1;
                    char c5 = cArr7[i8];
                    if (c5 == 'M' || c5 == 'm') {
                        char[] cArr8 = this.orig;
                        int i9 = this.index;
                        this.index = i9 + 1;
                        char c6 = cArr8[i9];
                        if (c6 != 'T') {
                            if (c6 == 't') {
                            }
                        }
                        z = false;
                        if (z) {
                            return i2;
                        }
                        char[] cArr9 = this.orig;
                        int i10 = this.index;
                        this.index = i10 + 1;
                        c = cArr9[i10];
                        if (c != 'S') {
                            break;
                        }
                        char[] cArr10 = this.orig;
                        int i11 = this.index;
                        this.index = i11 + 1;
                        c2 = cArr10[i11];
                        if (c2 != 'T') {
                            return i2;
                        }
                        return i2;
                    }
                    throw new java.text.ParseException("Bad Alpha TimeZone", this.index);
                case 'M':
                case 'm':
                    i2 = 420;
                    if (z) {
                        return i2;
                    }
                    char[] cArr11 = this.orig;
                    int i12 = this.index;
                    this.index = i12 + 1;
                    c = cArr11[i12];
                    if (c != 'S') {
                        break;
                    }
                    char[] cArr12 = this.orig;
                    int i13 = this.index;
                    this.index = i13 + 1;
                    c2 = cArr12[i13];
                    if (c2 != 'T') {
                        return i2;
                    }
                    return i2;
                case 'P':
                case 'p':
                    i2 = 480;
                    if (z) {
                        return i2;
                    }
                    char[] cArr13 = this.orig;
                    int i14 = this.index;
                    this.index = i14 + 1;
                    c = cArr13[i14];
                    if (c != 'S') {
                        break;
                    }
                    char[] cArr14 = this.orig;
                    int i15 = this.index;
                    this.index = i15 + 1;
                    c2 = cArr14[i15];
                    if (c2 != 'T') {
                        return i2;
                    }
                    return i2;
                case 'U':
                case 'u':
                    char[] cArr15 = this.orig;
                    int i16 = this.index;
                    this.index = i16 + 1;
                    char c7 = cArr15[i16];
                    if (c7 != 'T' && c7 != 't') {
                        throw new java.text.ParseException("Bad Alpha TimeZone", this.index);
                    }
                    z = false;
                    if (z) {
                        return i2;
                    }
                    char[] cArr16 = this.orig;
                    int i17 = this.index;
                    this.index = i17 + 1;
                    c = cArr16[i17];
                    if (c != 'S') {
                        break;
                    }
                    char[] cArr17 = this.orig;
                    int i18 = this.index;
                    this.index = i18 + 1;
                    c2 = cArr17[i18];
                    if (c2 != 'T') {
                        return i2;
                    }
                    return i2;
                default:
                    throw new java.text.ParseException("Bad Alpha TimeZone", this.index);
            }
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new java.text.ParseException("Bad Alpha TimeZone", this.index);
        }
    }

    int getIndex() {
        return this.index;
    }
}
