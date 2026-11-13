package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.commons.p092io.IOUtils;

/* JADX INFO: loaded from: classes.dex */
public final class JSONLexer {

    /* JADX INFO: renamed from: CA */
    public static final char[] f40CA;
    public static final int END = 4;
    public static final char EOI = 26;

    /* JADX INFO: renamed from: IA */
    static final int[] f41IA;
    public static final int NOT_MATCH = -1;
    public static final int NOT_MATCH_NAME = -2;
    public static final int UNKNOWN = 0;

    /* JADX INFO: renamed from: V6 */
    private static boolean f42V6 = false;
    public static final int VALUE = 3;
    protected static final int[] digits;
    public static final boolean[] firstIdentifierFlags;
    public static final boolean[] identifierFlags;
    private static final ThreadLocal<char[]> sbufLocal;

    /* JADX INFO: renamed from: bp */
    protected int f43bp;
    public Calendar calendar;

    /* JADX INFO: renamed from: ch */
    protected char f44ch;
    public boolean disableCircularReferenceDetect;
    protected int eofPos;
    protected boolean exp;
    public int features;
    protected long fieldHash;
    protected boolean hasSpecial;
    protected boolean isDouble;
    protected final int len;
    public Locale locale;
    public int matchStat;

    /* JADX INFO: renamed from: np */
    protected int f45np;
    protected int pos;
    protected char[] sbuf;

    /* JADX INFO: renamed from: sp */
    protected int f46sp;
    protected String stringDefaultValue;
    protected final String text;
    public TimeZone timeZone;
    protected int token;

    static {
        int version = -1;
        try {
            Class<?> clazz = Class.forName("android.os.Build$VERSION");
            Field field = clazz.getField("SDK_INT");
            version = field.getInt(null);
        } catch (Exception e) {
        }
        f42V6 = version >= 23;
        sbufLocal = new ThreadLocal<>();
        digits = new int[103];
        for (int i = 48; i <= 57; i++) {
            digits[i] = i - 48;
        }
        for (int i2 = 97; i2 <= 102; i2++) {
            digits[i2] = (i2 - 97) + 10;
        }
        for (int i3 = 65; i3 <= 70; i3++) {
            digits[i3] = (i3 - 65) + 10;
        }
        f40CA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
        f41IA = new int[256];
        Arrays.fill(f41IA, -1);
        int iS = f40CA.length;
        for (int i4 = 0; i4 < iS; i4++) {
            f41IA[f40CA[i4]] = i4;
        }
        f41IA[61] = 0;
        firstIdentifierFlags = new boolean[256];
        for (char c = 0; c < firstIdentifierFlags.length; c = (char) (c + 1)) {
            if (c >= 'A' && c <= 'Z') {
                firstIdentifierFlags[c] = true;
            } else if (c >= 'a' && c <= 'z') {
                firstIdentifierFlags[c] = true;
            } else if (c == '_') {
                firstIdentifierFlags[c] = true;
            }
        }
        identifierFlags = new boolean[256];
        for (char c2 = 0; c2 < identifierFlags.length; c2 = (char) (c2 + 1)) {
            if (c2 >= 'A' && c2 <= 'Z') {
                identifierFlags[c2] = true;
            } else if (c2 >= 'a' && c2 <= 'z') {
                identifierFlags[c2] = true;
            } else if (c2 == '_') {
                identifierFlags[c2] = true;
            } else if (c2 >= '0' && c2 <= '9') {
                identifierFlags[c2] = true;
            }
        }
    }

    public JSONLexer(String input) {
        this(input, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONLexer(char[] input, int inputLength) {
        this(input, inputLength, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONLexer(char[] input, int inputLength, int features) {
        this(new String(input, 0, inputLength), features);
    }

    public JSONLexer(String input, int features) {
        this.features = JSON.DEFAULT_PARSER_FEATURE;
        this.exp = false;
        this.isDouble = false;
        this.timeZone = JSON.defaultTimeZone;
        this.locale = JSON.defaultLocale;
        this.calendar = null;
        this.matchStat = 0;
        this.sbuf = sbufLocal.get();
        if (this.sbuf == null) {
            this.sbuf = new char[512];
        }
        this.features = features;
        this.text = input;
        this.len = this.text.length();
        this.f43bp = -1;
        int index = this.f43bp + 1;
        this.f43bp = index;
        this.f44ch = index >= this.len ? EOI : this.text.charAt(index);
        if (this.f44ch == 65279) {
            next();
        }
        this.stringDefaultValue = (Feature.InitStringFieldAsEmpty.mask & features) != 0 ? "" : null;
        this.disableCircularReferenceDetect = (Feature.DisableCircularReferenceDetect.mask & features) != 0;
    }

    public final int token() {
        return this.token;
    }

    public void close() {
        if (this.sbuf.length <= 8196) {
            sbufLocal.set(this.sbuf);
        }
        this.sbuf = null;
    }

    public char next() {
        int index = this.f43bp + 1;
        this.f43bp = index;
        char cCharAt = index >= this.len ? EOI : this.text.charAt(index);
        this.f44ch = cCharAt;
        return cCharAt;
    }

    public final void config(Feature feature, boolean state) {
        if (state) {
            this.features |= feature.mask;
        } else {
            this.features &= feature.mask ^ (-1);
        }
        if (feature == Feature.InitStringFieldAsEmpty) {
            this.stringDefaultValue = state ? "" : null;
        }
        this.disableCircularReferenceDetect = (this.features & Feature.DisableCircularReferenceDetect.mask) != 0;
    }

    public final boolean isEnabled(Feature feature) {
        return (this.features & feature.mask) != 0;
    }

    public final void nextTokenWithChar(char expect) {
        this.f46sp = 0;
        while (this.f44ch != expect) {
            if (this.f44ch == ' ' || this.f44ch == '\n' || this.f44ch == '\r' || this.f44ch == '\t' || this.f44ch == '\f' || this.f44ch == '\b') {
                next();
            } else {
                throw new JSONException("not match " + expect + " - " + this.f44ch);
            }
        }
        int index = this.f43bp + 1;
        this.f43bp = index;
        this.f44ch = index >= this.len ? EOI : this.text.charAt(index);
        nextToken();
    }

    public final String numberString() {
        int index = (this.f45np + this.f46sp) - 1;
        char chLocal = this.text.charAt(index);
        int sp = this.f46sp;
        if (chLocal == 'L' || chLocal == 'S' || chLocal == 'B' || chLocal == 'F' || chLocal == 'D') {
            sp--;
        }
        return subString(this.f45np, sp);
    }

    protected char charAt(int index) {
        return index >= this.len ? EOI : this.text.charAt(index);
    }

    public final void nextToken() {
        char cCharAt = EOI;
        this.f46sp = 0;
        while (true) {
            this.pos = this.f43bp;
            if (this.f44ch == '/') {
                skipComment();
            } else {
                if (this.f44ch == '\"') {
                    scanString();
                    return;
                }
                if ((this.f44ch >= '0' && this.f44ch <= '9') || this.f44ch == '-') {
                    scanNumber();
                    return;
                }
                if (this.f44ch == ',') {
                    next();
                    this.token = 16;
                    return;
                }
                switch (this.f44ch) {
                    case '\b':
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                    case ' ':
                        next();
                        break;
                    case '\'':
                        scanString();
                        return;
                    case '(':
                        next();
                        this.token = 10;
                        return;
                    case ')':
                        next();
                        this.token = 11;
                        return;
                    case ':':
                        next();
                        this.token = 17;
                        return;
                    case 'S':
                    case 'T':
                    case 'u':
                        scanIdent();
                        return;
                    case '[':
                        int index = this.f43bp + 1;
                        this.f43bp = index;
                        if (index < this.len) {
                            cCharAt = this.text.charAt(index);
                        }
                        this.f44ch = cCharAt;
                        this.token = 14;
                        return;
                    case ']':
                        next();
                        this.token = 15;
                        return;
                    case 'f':
                        if (this.text.startsWith("false", this.f43bp)) {
                            this.f43bp += 5;
                            this.f44ch = charAt(this.f43bp);
                            if (this.f44ch == ' ' || this.f44ch == ',' || this.f44ch == '}' || this.f44ch == ']' || this.f44ch == '\n' || this.f44ch == '\r' || this.f44ch == '\t' || this.f44ch == 26 || this.f44ch == '\f' || this.f44ch == '\b' || this.f44ch == ':') {
                                this.token = 7;
                                return;
                            }
                        }
                        throw new JSONException("scan false error");
                    case 'n':
                        int token = 0;
                        if (this.text.startsWith("null", this.f43bp)) {
                            this.f43bp += 4;
                            token = 8;
                        } else if (this.text.startsWith("new", this.f43bp)) {
                            this.f43bp += 3;
                            token = 9;
                        }
                        if (token != 0) {
                            this.f44ch = charAt(this.f43bp);
                            if (this.f44ch == ' ' || this.f44ch == ',' || this.f44ch == '}' || this.f44ch == ']' || this.f44ch == '\n' || this.f44ch == '\r' || this.f44ch == '\t' || this.f44ch == 26 || this.f44ch == '\f' || this.f44ch == '\b') {
                                this.token = token;
                                return;
                            }
                        }
                        throw new JSONException("scan null/new error");
                    case 't':
                        if (this.text.startsWith("true", this.f43bp)) {
                            this.f43bp += 4;
                            this.f44ch = charAt(this.f43bp);
                            if (this.f44ch == ' ' || this.f44ch == ',' || this.f44ch == '}' || this.f44ch == ']' || this.f44ch == '\n' || this.f44ch == '\r' || this.f44ch == '\t' || this.f44ch == 26 || this.f44ch == '\f' || this.f44ch == '\b' || this.f44ch == ':') {
                                this.token = 6;
                                return;
                            }
                        }
                        throw new JSONException("scan true error");
                    case '{':
                        int index2 = this.f43bp + 1;
                        this.f43bp = index2;
                        if (index2 < this.len) {
                            cCharAt = this.text.charAt(index2);
                        }
                        this.f44ch = cCharAt;
                        this.token = 12;
                        return;
                    case '}':
                        int index3 = this.f43bp + 1;
                        this.f43bp = index3;
                        if (index3 < this.len) {
                            cCharAt = this.text.charAt(index3);
                        }
                        this.f44ch = cCharAt;
                        this.token = 13;
                        return;
                    default:
                        boolean eof = this.f43bp == this.len || (this.f44ch == 26 && this.f43bp + 1 == this.len);
                        if (eof) {
                            if (this.token == 20) {
                                throw new JSONException("EOF error");
                            }
                            this.token = 20;
                            int i = this.eofPos;
                            this.f43bp = i;
                            this.pos = i;
                            return;
                        }
                        if (this.f44ch <= 31 || this.f44ch == 127) {
                            next();
                        } else {
                            this.token = 1;
                            next();
                            return;
                        }
                        break;
                        break;
                }
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:111:0x0170 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:112:0x0032 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:6:0x0016  */
    public final void nextToken(int expect) {
        char cCharAt = EOI;
        this.f46sp = 0;
        while (true) {
            switch (expect) {
                case 2:
                    if (this.f44ch >= '0' && this.f44ch <= '9') {
                        this.pos = this.f43bp;
                        scanNumber();
                    } else if (this.f44ch == '\"') {
                        this.pos = this.f43bp;
                        scanString();
                    } else if (this.f44ch == '[') {
                        this.token = 14;
                        next();
                    } else {
                        if (this.f44ch == '{') {
                            this.token = 12;
                            next();
                        }
                        if (this.f44ch != ' ' || this.f44ch == '\n' || this.f44ch == '\r' || this.f44ch == '\t' || this.f44ch == '\f' || this.f44ch == '\b') {
                            next();
                        } else {
                            nextToken();
                        }
                    }
                    break;
                case 3:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 13:
                case 17:
                case 19:
                default:
                    if (this.f44ch != ' ') {
                    }
                    next();
                    break;
                case 4:
                    if (this.f44ch == '\"') {
                        this.pos = this.f43bp;
                        scanString();
                    } else if (this.f44ch >= '0' && this.f44ch <= '9') {
                        this.pos = this.f43bp;
                        scanNumber();
                    } else {
                        if (this.f44ch == '{') {
                            this.token = 12;
                            int index = this.f43bp + 1;
                            this.f43bp = index;
                            if (index < this.len) {
                                cCharAt = this.text.charAt(index);
                            }
                            this.f44ch = cCharAt;
                        }
                        if (this.f44ch != ' ') {
                        }
                        next();
                    }
                    break;
                case 12:
                    if (this.f44ch == '{') {
                        this.token = 12;
                        int index2 = this.f43bp + 1;
                        this.f43bp = index2;
                        if (index2 < this.len) {
                            cCharAt = this.text.charAt(index2);
                        }
                        this.f44ch = cCharAt;
                    } else {
                        if (this.f44ch == '[') {
                            this.token = 14;
                            int index3 = this.f43bp + 1;
                            this.f43bp = index3;
                            if (index3 < this.len) {
                                cCharAt = this.text.charAt(index3);
                            }
                            this.f44ch = cCharAt;
                        }
                        if (this.f44ch != ' ') {
                        }
                        next();
                    }
                    break;
                case 14:
                    if (this.f44ch == '[') {
                        this.token = 14;
                        next();
                    } else {
                        if (this.f44ch == '{') {
                            this.token = 12;
                            next();
                        }
                        if (this.f44ch != ' ') {
                        }
                        next();
                    }
                    break;
                case 15:
                    if (this.f44ch == ']') {
                        this.token = 15;
                        next();
                    }
                    if (this.f44ch == 26) {
                        this.token = 20;
                    }
                    if (this.f44ch != ' ') {
                    }
                    next();
                    break;
                case 16:
                    if (this.f44ch == ',') {
                        this.token = 16;
                        int index4 = this.f43bp + 1;
                        this.f43bp = index4;
                        if (index4 < this.len) {
                            cCharAt = this.text.charAt(index4);
                        }
                        this.f44ch = cCharAt;
                    } else if (this.f44ch == '}') {
                        this.token = 13;
                        int index5 = this.f43bp + 1;
                        this.f43bp = index5;
                        if (index5 < this.len) {
                            cCharAt = this.text.charAt(index5);
                        }
                        this.f44ch = cCharAt;
                    } else if (this.f44ch == ']') {
                        this.token = 15;
                        int index6 = this.f43bp + 1;
                        this.f43bp = index6;
                        if (index6 < this.len) {
                            cCharAt = this.text.charAt(index6);
                        }
                        this.f44ch = cCharAt;
                    } else {
                        if (this.f44ch == 26) {
                            this.token = 20;
                        }
                        if (this.f44ch != ' ') {
                        }
                        next();
                    }
                    break;
                case 18:
                    nextIdent();
                    break;
                case 20:
                    if (this.f44ch == 26) {
                        this.token = 20;
                    }
                    if (this.f44ch != ' ') {
                    }
                    next();
                    break;
            }
            return;
        }
    }

    public final void nextIdent() {
        while (true) {
            boolean whitespace = this.f44ch <= ' ' && (this.f44ch == ' ' || this.f44ch == '\n' || this.f44ch == '\r' || this.f44ch == '\t' || this.f44ch == '\f' || this.f44ch == '\b');
            if (!whitespace) {
                break;
            } else {
                next();
            }
        }
        if (this.f44ch == '_' || Character.isLetter(this.f44ch)) {
            scanIdent();
        } else {
            nextToken();
        }
    }

    public final Number integerValue() throws NumberFormatException {
        long limit;
        int i;
        long result = 0;
        boolean negative = false;
        int i2 = this.f45np;
        int max = this.f45np + this.f46sp;
        char type = ' ';
        int charIndex = max - 1;
        char chLocal = charIndex >= this.len ? EOI : this.text.charAt(charIndex);
        switch (chLocal) {
            case 'B':
                max--;
                type = 'B';
                break;
            case 'L':
                max--;
                type = 'L';
                break;
            case 'S':
                max--;
                type = 'S';
                break;
        }
        char chLocal2 = this.f45np >= this.len ? EOI : this.text.charAt(this.f45np);
        if (chLocal2 == '-') {
            negative = true;
            limit = Long.MIN_VALUE;
            i = i2 + 1;
        } else {
            limit = -9223372036854775807L;
            i = i2;
        }
        if (i < max) {
            int i3 = i + 1;
            int charIndex2 = i;
            char chLocal3 = charIndex2 >= this.len ? EOI : this.text.charAt(charIndex2);
            result = -(chLocal3 - '0');
            i = i3;
        }
        while (i < max) {
            int i4 = i + 1;
            int charIndex3 = i;
            char chLocal4 = charIndex3 >= this.len ? EOI : this.text.charAt(charIndex3);
            int digit = chLocal4 - '0';
            if (result < -922337203685477580L) {
                return new BigInteger(numberString());
            }
            long result2 = result * 10;
            if (result2 < ((long) digit) + limit) {
                return new BigInteger(numberString());
            }
            result = result2 - ((long) digit);
            i = i4;
        }
        if (negative) {
            if (i <= this.f45np + 1) {
                throw new NumberFormatException(numberString());
            }
            if (result >= -2147483648L && type != 'L') {
                if (type == 'S') {
                    return Short.valueOf((short) result);
                }
                if (type == 'B') {
                    return Byte.valueOf((byte) result);
                }
                return Integer.valueOf((int) result);
            }
            return Long.valueOf(result);
        }
        long result3 = -result;
        if (result3 <= 2147483647L && type != 'L') {
            if (type == 'S') {
                return Short.valueOf((short) result3);
            }
            if (type == 'B') {
                return Byte.valueOf((byte) result3);
            }
            return Integer.valueOf((int) result3);
        }
        return Long.valueOf(result3);
    }

    public final String scanSymbol(SymbolTable symbolTable) {
        while (true) {
            if (this.f44ch != ' ' && this.f44ch != '\n' && this.f44ch != '\r' && this.f44ch != '\t' && this.f44ch != '\f' && this.f44ch != '\b') {
                break;
            }
            next();
        }
        if (this.f44ch == '\"') {
            return scanSymbol(symbolTable, '\"');
        }
        if (this.f44ch == '\'') {
            return scanSymbol(symbolTable, '\'');
        }
        if (this.f44ch == '}') {
            next();
            this.token = 13;
            return null;
        }
        if (this.f44ch == ',') {
            next();
            this.token = 16;
            return null;
        }
        if (this.f44ch == 26) {
            this.token = 20;
            return null;
        }
        return scanSymbolUnQuoted(symbolTable);
    }

    public String scanSymbol(SymbolTable symbolTable, char quoteChar) {
        String strVal;
        int hash = 0;
        boolean hasSpecial = false;
        int startIndex = this.f43bp + 1;
        int endIndex = this.text.indexOf(quoteChar, startIndex);
        if (endIndex == -1) {
            throw new JSONException("unclosed str, " + info());
        }
        int chars_len = endIndex - startIndex;
        char[] chars = sub_chars(this.f43bp + 1, chars_len);
        while (chars_len > 0 && chars[chars_len - 1] == '\\') {
            int slashCount = 1;
            for (int i = chars_len - 2; i >= 0 && chars[i] == '\\'; i--) {
                slashCount++;
            }
            if (slashCount % 2 == 0) {
                break;
            }
            int nextIndex = this.text.indexOf(quoteChar, endIndex + 1);
            int nextLen = nextIndex - endIndex;
            int next_chars_len = chars_len + nextLen;
            if (next_chars_len >= chars.length) {
                int newLen = (chars.length * 3) / 2;
                if (newLen < next_chars_len) {
                    newLen = next_chars_len;
                }
                char[] newChars = new char[newLen];
                System.arraycopy(chars, 0, newChars, 0, chars.length);
                chars = newChars;
            }
            this.text.getChars(endIndex, nextIndex, chars, chars_len);
            chars_len = next_chars_len;
            endIndex = nextIndex;
            hasSpecial = true;
        }
        if (!hasSpecial) {
            for (int i2 = 0; i2 < chars_len; i2++) {
                char ch = chars[i2];
                hash = (hash * 31) + ch;
                if (ch == '\\') {
                    hasSpecial = true;
                }
            }
            if (hasSpecial) {
                strVal = readString(chars, chars_len);
            } else {
                strVal = chars_len < 20 ? symbolTable.addSymbol(chars, 0, chars_len, hash) : new String(chars, 0, chars_len);
            }
        } else {
            strVal = readString(chars, chars_len);
        }
        this.f43bp = endIndex + 1;
        int index = this.f43bp;
        this.f44ch = index >= this.len ? EOI : this.text.charAt(index);
        return strVal;
    }

    private static String readString(char[] chars, int chars_len) {
        int len;
        char[] sbuf = new char[chars_len];
        int i = 0;
        int len2 = 0;
        while (i < chars_len) {
            char ch = chars[i];
            if (ch != '\\') {
                len = len2 + 1;
                sbuf[len2] = ch;
            } else {
                i++;
                switch (chars[i]) {
                    case '\"':
                        len = len2 + 1;
                        sbuf[len2] = '\"';
                        break;
                    case '\'':
                        len = len2 + 1;
                        sbuf[len2] = '\'';
                        break;
                    case '/':
                        len = len2 + 1;
                        sbuf[len2] = IOUtils.DIR_SEPARATOR_UNIX;
                        break;
                    case '0':
                        len = len2 + 1;
                        sbuf[len2] = 0;
                        break;
                    case '1':
                        len = len2 + 1;
                        sbuf[len2] = 1;
                        break;
                    case '2':
                        len = len2 + 1;
                        sbuf[len2] = 2;
                        break;
                    case '3':
                        len = len2 + 1;
                        sbuf[len2] = 3;
                        break;
                    case '4':
                        len = len2 + 1;
                        sbuf[len2] = 4;
                        break;
                    case '5':
                        len = len2 + 1;
                        sbuf[len2] = 5;
                        break;
                    case '6':
                        len = len2 + 1;
                        sbuf[len2] = 6;
                        break;
                    case '7':
                        len = len2 + 1;
                        sbuf[len2] = 7;
                        break;
                    case 'F':
                    case 'f':
                        len = len2 + 1;
                        sbuf[len2] = '\f';
                        break;
                    case '\\':
                        len = len2 + 1;
                        sbuf[len2] = IOUtils.DIR_SEPARATOR_WINDOWS;
                        break;
                    case 'b':
                        len = len2 + 1;
                        sbuf[len2] = '\b';
                        break;
                    case 'n':
                        len = len2 + 1;
                        sbuf[len2] = '\n';
                        break;
                    case 'r':
                        len = len2 + 1;
                        sbuf[len2] = '\r';
                        break;
                    case 't':
                        len = len2 + 1;
                        sbuf[len2] = '\t';
                        break;
                    case 'u':
                        len = len2 + 1;
                        int i2 = i + 1;
                        int i3 = i2 + 1;
                        int i4 = i3 + 1;
                        i = i4 + 1;
                        sbuf[len2] = (char) Integer.parseInt(new String(new char[]{chars[i2], chars[i3], chars[i4], chars[i]}), 16);
                        break;
                    case 'v':
                        len = len2 + 1;
                        sbuf[len2] = 11;
                        break;
                    case 'x':
                        len = len2 + 1;
                        int i5 = i + 1;
                        int i6 = digits[chars[i5]] * 16;
                        i = i5 + 1;
                        sbuf[len2] = (char) (i6 + digits[chars[i]]);
                        break;
                    default:
                        throw new JSONException("unclosed.str.lit");
                }
            }
            i++;
            len2 = len;
        }
        return new String(sbuf, 0, len2);
    }

    public String info() {
        return "pos " + this.f43bp + ", json : " + (this.len < 65536 ? this.text : this.text.substring(0, 65536));
    }

    protected void skipComment() {
        next();
        if (this.f44ch == '/') {
            do {
                next();
            } while (this.f44ch != '\n');
            next();
        } else {
            if (this.f44ch == '*') {
                next();
                while (this.f44ch != 26) {
                    if (this.f44ch == '*') {
                        next();
                        if (this.f44ch == '/') {
                            next();
                            return;
                        }
                    } else {
                        next();
                    }
                }
                return;
            }
            throw new JSONException("invalid comment");
        }
    }

    public final String scanSymbolUnQuoted(SymbolTable symbolTable) {
        char first = this.f44ch;
        boolean firstFlag = this.f44ch >= firstIdentifierFlags.length || firstIdentifierFlags[first];
        if (!firstFlag) {
            throw new JSONException("illegal identifier : " + this.f44ch + ", " + info());
        }
        int hash = first;
        this.f45np = this.f43bp;
        this.f46sp = 1;
        while (true) {
            char ch = next();
            if (ch < identifierFlags.length && !identifierFlags[ch]) {
                break;
            }
            hash = (hash * 31) + ch;
            this.f46sp++;
        }
        this.f44ch = charAt(this.f43bp);
        this.token = 18;
        if (this.f46sp == 4 && this.text.startsWith("null", this.f45np)) {
            return null;
        }
        return symbolTable.addSymbol(this.text, this.f45np, this.f46sp, hash);
    }

    public final void scanString() {
        char quoteChar = this.f44ch;
        boolean hasSpecial = false;
        int startIndex = this.f43bp + 1;
        int endIndex = this.text.indexOf(quoteChar, startIndex);
        if (endIndex == -1) {
            throw new JSONException("unclosed str, " + info());
        }
        int chars_len = endIndex - startIndex;
        char[] chars = sub_chars(this.f43bp + 1, chars_len);
        while (chars_len > 0 && chars[chars_len - 1] == '\\') {
            int slashCount = 1;
            for (int i = chars_len - 2; i >= 0 && chars[i] == '\\'; i--) {
                slashCount++;
            }
            if (slashCount % 2 == 0) {
                break;
            }
            int nextIndex = this.text.indexOf(quoteChar, endIndex + 1);
            int nextLen = nextIndex - endIndex;
            int next_chars_len = chars_len + nextLen;
            if (next_chars_len >= chars.length) {
                int newLen = (chars.length * 3) / 2;
                if (newLen < next_chars_len) {
                    newLen = next_chars_len;
                }
                char[] newChars = new char[newLen];
                System.arraycopy(chars, 0, newChars, 0, chars.length);
                chars = newChars;
            }
            this.text.getChars(endIndex, nextIndex, chars, chars_len);
            chars_len = next_chars_len;
            endIndex = nextIndex;
            hasSpecial = true;
        }
        if (!hasSpecial) {
            for (int i2 = 0; i2 < chars_len; i2++) {
                if (chars[i2] == '\\') {
                    hasSpecial = true;
                }
            }
        }
        this.sbuf = chars;
        this.f46sp = chars_len;
        this.f45np = this.f43bp;
        this.hasSpecial = hasSpecial;
        this.f43bp = endIndex + 1;
        int index = this.f43bp;
        this.f44ch = index >= this.len ? EOI : this.text.charAt(index);
        this.token = 4;
    }

    public String scanStringValue(char quoteChar) {
        String strVal;
        int startIndex = this.f43bp + 1;
        int endIndex = this.text.indexOf(quoteChar, startIndex);
        if (endIndex == -1) {
            throw new JSONException("unclosed str, " + info());
        }
        if (f42V6) {
            strVal = this.text.substring(startIndex, endIndex);
        } else {
            int chars_len = endIndex - startIndex;
            char[] chars = sub_chars(this.f43bp + 1, chars_len);
            strVal = new String(chars, 0, chars_len);
        }
        if (strVal.indexOf(92) != -1) {
            while (true) {
                int slashCount = 0;
                for (int i = endIndex - 1; i >= 0 && this.text.charAt(i) == '\\'; i--) {
                    slashCount++;
                }
                if (slashCount % 2 == 0) {
                    break;
                }
                endIndex = this.text.indexOf(quoteChar, endIndex + 1);
            }
            int chars_len2 = endIndex - startIndex;
            char[] chars2 = sub_chars(this.f43bp + 1, chars_len2);
            strVal = readString(chars2, chars_len2);
        }
        this.f43bp = endIndex + 1;
        int index = this.f43bp;
        this.f44ch = index >= this.len ? EOI : this.text.charAt(index);
        return strVal;
    }

    public final int intValue() {
        int limit;
        int i;
        int i2;
        int result = 0;
        boolean negative = false;
        int i3 = this.f45np;
        int max = this.f45np + this.f46sp;
        char chLocal = this.f45np >= this.len ? (char) 26 : this.text.charAt(this.f45np);
        if (chLocal == '-') {
            negative = true;
            limit = Integer.MIN_VALUE;
            i = i3 + 1;
        } else {
            limit = -2147483647;
            i = i3;
        }
        if (i < max) {
            int i4 = i + 1;
            int charIndex = i;
            char chLocal2 = charIndex >= this.len ? (char) 26 : this.text.charAt(charIndex);
            result = -(chLocal2 - '0');
            i = i4;
        }
        while (true) {
            if (i >= max) {
                i2 = i;
                break;
            }
            i2 = i + 1;
            int charIndex2 = i;
            char ch = charIndex2 >= this.len ? (char) 26 : this.text.charAt(charIndex2);
            if (ch == 'L' || ch == 'S' || ch == 'B') {
                break;
            }
            int digit = ch - '0';
            if (result < -214748364) {
                throw new NumberFormatException(numberString());
            }
            int result2 = result * 10;
            if (result2 < limit + digit) {
                throw new NumberFormatException(numberString());
            }
            result = result2 - digit;
            i = i2;
        }
        if (negative) {
            if (i2 <= this.f45np + 1) {
                throw new NumberFormatException(numberString());
            }
            return result;
        }
        return -result;
    }

    public byte[] bytesValue() {
        return decodeFast(this.text, this.f45np + 1, this.f46sp);
    }

    private void scanIdent() {
        this.f45np = this.f43bp - 1;
        this.hasSpecial = false;
        do {
            this.f46sp++;
            next();
        } while (Character.isLetterOrDigit(this.f44ch));
        String ident = stringVal();
        if (ident.equals("null")) {
            this.token = 8;
            return;
        }
        if (ident.equals("true")) {
            this.token = 6;
            return;
        }
        if (ident.equals("false")) {
            this.token = 7;
            return;
        }
        if (ident.equals("new")) {
            this.token = 9;
            return;
        }
        if (ident.equals("undefined")) {
            this.token = 23;
            return;
        }
        if (ident.equals("Set")) {
            this.token = 21;
        } else if (ident.equals("TreeSet")) {
            this.token = 22;
        } else {
            this.token = 18;
        }
    }

    public final String stringVal() {
        if (this.hasSpecial) {
            return readString(this.sbuf, this.f46sp);
        }
        return subString(this.f45np + 1, this.f46sp);
    }

    private final String subString(int offset, int count) {
        if (count < this.sbuf.length) {
            this.text.getChars(offset, offset + count, this.sbuf, 0);
            return new String(this.sbuf, 0, count);
        }
        char[] chars = new char[count];
        this.text.getChars(offset, offset + count, chars, 0);
        return new String(chars);
    }

    final char[] sub_chars(int offset, int count) {
        if (count < this.sbuf.length) {
            this.text.getChars(offset, offset + count, this.sbuf, 0);
            return this.sbuf;
        }
        char[] chars = new char[count];
        this.sbuf = chars;
        this.text.getChars(offset, offset + count, chars, 0);
        return chars;
    }

    public final boolean isBlankInput() {
        int i = 0;
        while (true) {
            char ch = charAt(i);
            if (ch == 26) {
                return true;
            }
            boolean whitespace = ch <= ' ' && (ch == ' ' || ch == '\n' || ch == '\r' || ch == '\t' || ch == '\f' || ch == '\b');
            if (!whitespace) {
                return false;
            }
            i++;
        }
    }

    final void skipWhitespace() {
        while (this.f44ch <= '/') {
            if (this.f44ch == ' ' || this.f44ch == '\r' || this.f44ch == '\n' || this.f44ch == '\t' || this.f44ch == '\f' || this.f44ch == '\b') {
                next();
            } else if (this.f44ch == '/') {
                skipComment();
            } else {
                return;
            }
        }
    }

    public final void scanNumber() {
        this.f45np = this.f43bp;
        this.exp = false;
        if (this.f44ch == '-') {
            this.f46sp++;
            int index = this.f43bp + 1;
            this.f43bp = index;
            this.f44ch = index >= this.len ? (char) 26 : this.text.charAt(index);
        }
        while (this.f44ch >= '0' && this.f44ch <= '9') {
            this.f46sp++;
            int index2 = this.f43bp + 1;
            this.f43bp = index2;
            this.f44ch = index2 >= this.len ? (char) 26 : this.text.charAt(index2);
        }
        this.isDouble = false;
        if (this.f44ch == '.') {
            this.f46sp++;
            int index3 = this.f43bp + 1;
            this.f43bp = index3;
            this.f44ch = index3 >= this.len ? (char) 26 : this.text.charAt(index3);
            this.isDouble = true;
            while (this.f44ch >= '0' && this.f44ch <= '9') {
                this.f46sp++;
                int index4 = this.f43bp + 1;
                this.f43bp = index4;
                this.f44ch = index4 >= this.len ? (char) 26 : this.text.charAt(index4);
            }
        }
        if (this.f44ch == 'L' || this.f44ch == 'S' || this.f44ch == 'B') {
            this.f46sp++;
            next();
        } else if (this.f44ch == 'F' || this.f44ch == 'D') {
            this.f46sp++;
            next();
            this.isDouble = true;
        } else if (this.f44ch == 'e' || this.f44ch == 'E') {
            this.f46sp++;
            int index5 = this.f43bp + 1;
            this.f43bp = index5;
            this.f44ch = index5 >= this.len ? (char) 26 : this.text.charAt(index5);
            if (this.f44ch == '+' || this.f44ch == '-') {
                this.f46sp++;
                int index6 = this.f43bp + 1;
                this.f43bp = index6;
                this.f44ch = index6 >= this.len ? (char) 26 : this.text.charAt(index6);
            }
            while (this.f44ch >= '0' && this.f44ch <= '9') {
                this.f46sp++;
                int index7 = this.f43bp + 1;
                this.f43bp = index7;
                this.f44ch = index7 >= this.len ? (char) 26 : this.text.charAt(index7);
            }
            if (this.f44ch == 'D' || this.f44ch == 'F') {
                this.f46sp++;
                next();
            }
            this.exp = true;
            this.isDouble = true;
        }
        if (this.isDouble) {
            this.token = 3;
        } else {
            this.token = 2;
        }
    }

    public boolean scanBoolean() {
        int offset;
        boolean value;
        if (this.text.startsWith("false", this.f43bp)) {
            offset = 5;
            value = false;
        } else if (this.text.startsWith("true", this.f43bp)) {
            offset = 4;
            value = true;
        } else if (this.f44ch == '1') {
            offset = 1;
            value = true;
        } else if (this.f44ch == '0') {
            offset = 1;
            value = false;
        } else {
            this.matchStat = -1;
            return false;
        }
        this.f43bp += offset;
        this.f44ch = charAt(this.f43bp);
        return value;
    }

    public final Number scanNumberValue() {
        boolean negative;
        long limit;
        char[] chars;
        Number number;
        int i;
        char[] chars2;
        int start = this.f43bp;
        boolean overflow = false;
        Number number2 = null;
        this.f45np = 0;
        if (this.f44ch == '-') {
            negative = true;
            limit = Long.MIN_VALUE;
            this.f45np++;
            int index = this.f43bp + 1;
            this.f43bp = index;
            this.f44ch = index >= this.len ? EOI : this.text.charAt(index);
        } else {
            negative = false;
            limit = -9223372036854775807L;
        }
        long longValue = 0;
        int precision = 1;
        while (this.f44ch >= '0' && this.f44ch <= '9') {
            int digit = this.f44ch - '0';
            if (precision < 18) {
                longValue = (10 * longValue) - ((long) digit);
            } else {
                if (longValue < -922337203685477580L) {
                    overflow = true;
                }
                long longValue2 = longValue * 10;
                if (longValue2 < ((long) digit) + limit) {
                    overflow = true;
                }
                longValue = longValue2 - ((long) digit);
            }
            this.f45np++;
            int index2 = this.f43bp + 1;
            this.f43bp = index2;
            this.f44ch = index2 >= this.len ? EOI : this.text.charAt(index2);
            precision++;
        }
        boolean isDouble = false;
        boolean exp = false;
        int scale = 0;
        if (this.f44ch == '.') {
            isDouble = true;
            this.f45np++;
            int index3 = this.f43bp + 1;
            this.f43bp = index3;
            this.f44ch = index3 >= this.len ? EOI : this.text.charAt(index3);
            while (this.f44ch >= '0' && this.f44ch <= '9') {
                scale++;
                int digit2 = this.f44ch - '0';
                if (precision < 18) {
                    longValue = (10 * longValue) - ((long) digit2);
                } else {
                    if (longValue < -922337203685477580L) {
                        overflow = true;
                    }
                    long longValue3 = longValue * 10;
                    if (longValue3 < ((long) digit2) + limit) {
                        overflow = true;
                    }
                    longValue = longValue3 - ((long) digit2);
                }
                this.f45np++;
                int index4 = this.f43bp + 1;
                this.f43bp = index4;
                this.f44ch = index4 >= this.len ? EOI : this.text.charAt(index4);
                precision++;
            }
            if (!negative) {
                longValue = -longValue;
            }
            if (this.f44ch == 'F' || this.f44ch == 'D') {
                this.f45np++;
                next();
            }
        } else {
            if (!negative) {
                longValue = -longValue;
            }
            if (this.f44ch == 'L') {
                this.f45np++;
                next();
                number2 = Long.valueOf(longValue);
            } else if (this.f44ch == 'S') {
                this.f45np++;
                next();
                number2 = Short.valueOf((short) longValue);
            } else if (this.f44ch == 'B') {
                this.f45np++;
                next();
                number2 = Byte.valueOf((byte) longValue);
            } else if (this.f44ch == 'F') {
                this.f45np++;
                next();
                number2 = Float.valueOf(longValue);
            } else if (this.f44ch == 'D') {
                this.f45np++;
                next();
                number2 = Double.valueOf(longValue);
            }
        }
        char type = 0;
        if (this.f44ch == 'e' || this.f44ch == 'E') {
            this.f45np++;
            int index5 = this.f43bp + 1;
            this.f43bp = index5;
            this.f44ch = index5 >= this.len ? EOI : this.text.charAt(index5);
            if (this.f44ch == '+' || this.f44ch == '-') {
                this.f45np++;
                int index6 = this.f43bp + 1;
                this.f43bp = index6;
                this.f44ch = index6 >= this.len ? EOI : this.text.charAt(index6);
            }
            while (this.f44ch >= '0' && this.f44ch <= '9') {
                this.f45np++;
                int index7 = this.f43bp + 1;
                this.f43bp = index7;
                this.f44ch = index7 >= this.len ? EOI : this.text.charAt(index7);
            }
            if (this.f44ch == 'D' || this.f44ch == 'F') {
                this.f45np++;
                type = this.f44ch;
                next();
            }
            exp = true;
        }
        if (!isDouble && !exp) {
            if (overflow) {
                char[] chars3 = new char[this.f43bp - start];
                this.text.getChars(start, this.f43bp, chars3, 0);
                number2 = new BigInteger(new String(chars3));
            }
            if (number2 == null) {
                if (longValue > -2147483648L && longValue < 2147483647L) {
                    number2 = Integer.valueOf((int) longValue);
                } else {
                    number2 = Long.valueOf(longValue);
                }
            }
            return number2;
        }
        int len = this.f43bp - start;
        if (type != 0) {
            len--;
        }
        if (!exp && (this.features & Feature.UseBigDecimal.mask) != 0) {
            if (!overflow) {
                number = BigDecimal.valueOf(longValue, scale);
            } else {
                if (len < this.sbuf.length) {
                    this.text.getChars(start, start + len, this.sbuf, 0);
                    chars2 = this.sbuf;
                } else {
                    chars2 = new char[len];
                    this.text.getChars(start, start + len, chars2, 0);
                }
                number = new BigDecimal(chars2, 0, len);
            }
        } else {
            if (len < this.sbuf.length) {
                this.text.getChars(start, start + len, this.sbuf, 0);
                chars = this.sbuf;
            } else {
                chars = new char[len];
                this.text.getChars(start, start + len, chars, 0);
            }
            try {
                if (len <= 9 && !exp) {
                    int i2 = 0 + 1;
                    char c = chars[0];
                    if (c == '-' || c == '+') {
                        i = i2 + 1;
                        c = chars[i2];
                    } else {
                        i = i2;
                    }
                    int intVal = c - '0';
                    int power = 0;
                    while (i < len) {
                        char c2 = chars[i];
                        if (c2 == '.') {
                            power = 1;
                        } else {
                            intVal = (intVal * 10) + (c2 - '0');
                            if (power != 0) {
                                power *= 10;
                            }
                        }
                        i++;
                    }
                    if (type == 'F') {
                        float floatVal = intVal / power;
                        if (negative) {
                            floatVal = -floatVal;
                        }
                        return Float.valueOf(floatVal);
                    }
                    double doubleVal = ((double) intVal) / ((double) power);
                    if (negative) {
                        doubleVal = -doubleVal;
                    }
                    return Double.valueOf(doubleVal);
                }
                String strVal = new String(chars, 0, len);
                if (type == 'F') {
                    number = Float.valueOf(strVal);
                } else {
                    number = Double.valueOf(Double.parseDouble(strVal));
                }
            } catch (NumberFormatException ex) {
                throw new JSONException(ex.getMessage() + ", " + info(), ex);
            }
        }
        return number;
    }

    public final long scanLongValue() {
        boolean negative;
        long limit;
        this.f45np = 0;
        if (this.f44ch == '-') {
            negative = true;
            limit = Long.MIN_VALUE;
            this.f45np++;
            int index = this.f43bp + 1;
            this.f43bp = index;
            if (index >= this.len) {
                throw new JSONException("syntax error, " + info());
            }
            this.f44ch = this.text.charAt(index);
        } else {
            negative = false;
            limit = -9223372036854775807L;
        }
        long longValue = 0;
        while (this.f44ch >= '0' && this.f44ch <= '9') {
            int digit = this.f44ch - '0';
            if (longValue < -922337203685477580L) {
                throw new JSONException("error long value, " + longValue + ", " + info());
            }
            long longValue2 = longValue * 10;
            if (longValue2 < ((long) digit) + limit) {
                throw new JSONException("error long value, " + longValue2 + ", " + info());
            }
            longValue = longValue2 - ((long) digit);
            this.f45np++;
            int index2 = this.f43bp + 1;
            this.f43bp = index2;
            this.f44ch = index2 >= this.len ? EOI : this.text.charAt(index2);
        }
        if (!negative) {
            return -longValue;
        }
        return longValue;
    }

    public final long longValue() throws NumberFormatException {
        long limit;
        int i;
        int i2;
        long result = 0;
        boolean negative = false;
        int i3 = this.f45np;
        int max = this.f45np + this.f46sp;
        if (charAt(this.f45np) == '-') {
            negative = true;
            limit = Long.MIN_VALUE;
            i = i3 + 1;
        } else {
            limit = -9223372036854775807L;
            i = i3;
        }
        if (i < max) {
            result = -(charAt(i) - '0');
            i++;
        }
        while (true) {
            if (i >= max) {
                i2 = i;
                break;
            }
            i2 = i + 1;
            int index = i;
            char chLocal = index >= this.len ? EOI : this.text.charAt(index);
            if (chLocal == 'L' || chLocal == 'S' || chLocal == 'B') {
                break;
            }
            int digit = chLocal - '0';
            if (result < -922337203685477580L) {
                throw new NumberFormatException(numberString());
            }
            long result2 = result * 10;
            if (result2 < ((long) digit) + limit) {
                throw new NumberFormatException(numberString());
            }
            result = result2 - ((long) digit);
            i = i2;
        }
        if (negative) {
            if (i2 <= this.f45np + 1) {
                throw new NumberFormatException(numberString());
            }
            return result;
        }
        return -result;
    }

    public final Number decimalValue(boolean decimal) {
        char[] chars;
        int i;
        int charIndex = (this.f45np + this.f46sp) - 1;
        char chLocal = charIndex >= this.len ? EOI : this.text.charAt(charIndex);
        try {
            if (chLocal == 'F') {
                return Float.valueOf(Float.parseFloat(numberString()));
            }
            if (chLocal == 'D') {
                return Double.valueOf(Double.parseDouble(numberString()));
            }
            if (decimal) {
                return decimalValue();
            }
            int index = (this.f45np + this.f46sp) - 1;
            char chLocal2 = this.text.charAt(index);
            int sp = this.f46sp;
            if (chLocal2 == 'L' || chLocal2 == 'S' || chLocal2 == 'B' || chLocal2 == 'F' || chLocal2 == 'D') {
                sp--;
            }
            int offset = this.f45np;
            int count = sp;
            if (count < this.sbuf.length) {
                this.text.getChars(offset, offset + count, this.sbuf, 0);
                chars = this.sbuf;
            } else {
                chars = new char[count];
                this.text.getChars(offset, offset + count, chars, 0);
            }
            if (count <= 9 && !this.exp) {
                boolean negative = false;
                int i2 = 0 + 1;
                char c = chars[0];
                if (c == '-') {
                    negative = true;
                    i = i2 + 1;
                    c = chars[i2];
                } else if (c == '+') {
                    i = i2 + 1;
                    c = chars[i2];
                } else {
                    i = i2;
                }
                int intVal = c - '0';
                int power = 0;
                while (i < count) {
                    char c2 = chars[i];
                    if (c2 == '.') {
                        power = 1;
                    } else {
                        int digit = c2 - '0';
                        intVal = (intVal * 10) + digit;
                        if (power != 0) {
                            power *= 10;
                        }
                    }
                    i++;
                }
                double doubleVal = ((double) intVal) / ((double) power);
                if (negative) {
                    doubleVal = -doubleVal;
                }
                return Double.valueOf(doubleVal);
            }
            String str = new String(chars, 0, count);
            return Double.valueOf(Double.parseDouble(str));
        } catch (NumberFormatException ex) {
            throw new JSONException(ex.getMessage() + ", " + info());
        }
    }

    public final BigDecimal decimalValue() {
        int index = (this.f45np + this.f46sp) - 1;
        char chLocal = this.text.charAt(index);
        int sp = this.f46sp;
        if (chLocal == 'L' || chLocal == 'S' || chLocal == 'B' || chLocal == 'F' || chLocal == 'D') {
            sp--;
        }
        int offset = this.f45np;
        int count = sp;
        if (count < this.sbuf.length) {
            this.text.getChars(offset, offset + count, this.sbuf, 0);
            return new BigDecimal(this.sbuf, 0, count);
        }
        char[] chars = new char[count];
        this.text.getChars(offset, offset + count, chars, 0);
        return new BigDecimal(chars);
    }

    public boolean matchField(long fieldHashCode) {
        int offset;
        int offset2 = 1;
        char fieldQuote = this.f44ch;
        int fieldStartIndex = this.f43bp + 1;
        while (true) {
            offset = offset2;
            if (fieldQuote == '\"' || fieldQuote == '\'') {
                break;
            }
            if (fieldQuote <= ' ' && (fieldQuote == ' ' || fieldQuote == '\n' || fieldQuote == '\r' || fieldQuote == '\t' || fieldQuote == '\f' || fieldQuote == '\b')) {
                offset2 = offset + 1;
                int charIndex = this.f43bp + offset;
                fieldQuote = charIndex >= this.len ? EOI : this.text.charAt(charIndex);
            } else {
                this.fieldHash = 0L;
                this.matchStat = -2;
                return false;
            }
        }
        long hash = -3750763034362895579L;
        for (int i = fieldStartIndex; i < this.len; i++) {
            char ch = this.text.charAt(i);
            if (ch == fieldQuote) {
                offset += (i - fieldStartIndex) + 1;
                break;
            }
            hash = (hash ^ ((long) ch)) * 1099511628211L;
        }
        if (hash != fieldHashCode) {
            this.matchStat = -2;
            this.fieldHash = hash;
            return false;
        }
        int offset3 = offset + 1;
        int charIndex2 = this.f43bp + offset;
        char chLocal = charIndex2 >= this.len ? EOI : this.text.charAt(charIndex2);
        while (true) {
            int offset4 = offset3;
            if (chLocal == ':') {
                int i2 = offset4 + 1;
                int charIndex3 = this.f43bp + offset4;
                char chLocal2 = charIndex3 >= this.len ? EOI : this.text.charAt(charIndex3);
                if (chLocal2 == '{') {
                    this.f43bp = charIndex3 + 1;
                    this.f44ch = this.f43bp >= this.len ? EOI : this.text.charAt(this.f43bp);
                    this.token = 12;
                } else if (chLocal2 == '[') {
                    this.f43bp = charIndex3 + 1;
                    this.f44ch = this.f43bp >= this.len ? EOI : this.text.charAt(this.f43bp);
                    this.token = 14;
                } else {
                    this.f43bp = charIndex3;
                    this.f44ch = this.f43bp >= this.len ? EOI : this.text.charAt(this.f43bp);
                    nextToken();
                }
                return true;
            }
            if (chLocal > ' ' || !(chLocal == ' ' || chLocal == '\n' || chLocal == '\r' || chLocal == '\t' || chLocal == '\f' || chLocal == '\b')) {
                break;
            }
            offset3 = offset4 + 1;
            int charIndex4 = this.f43bp + offset4;
            chLocal = charIndex4 >= this.len ? EOI : this.text.charAt(charIndex4);
        }
        throw new JSONException("match feild error expect ':'");
    }

    private int matchFieldHash(long fieldHashCode) {
        int offset;
        int offset2;
        int offset3 = 1;
        char fieldQuote = this.f44ch;
        int i = this.f43bp + 1;
        while (true) {
            offset = offset3;
            if (fieldQuote == '\"' || fieldQuote == '\'') {
                break;
            }
            if (fieldQuote == ' ' || fieldQuote == '\n' || fieldQuote == '\r' || fieldQuote == '\t' || fieldQuote == '\f' || fieldQuote == '\b') {
                offset3 = offset + 1;
                int charIndex = this.f43bp + offset;
                fieldQuote = charIndex >= this.len ? EOI : this.text.charAt(charIndex);
            } else {
                this.fieldHash = 0L;
                this.matchStat = -2;
                return 0;
            }
        }
        long hash = -3750763034362895579L;
        int i2 = this.f43bp + offset;
        while (true) {
            if (i2 >= this.len) {
                offset2 = offset;
                break;
            }
            char ch = this.text.charAt(i2);
            if (ch == fieldQuote) {
                offset2 = offset + ((i2 - this.f43bp) - offset);
                break;
            }
            hash = (hash ^ ((long) ch)) * 1099511628211L;
            i2++;
        }
        if (hash != fieldHashCode) {
            this.fieldHash = hash;
            this.matchStat = -2;
            return 0;
        }
        int offset4 = offset2 + 1;
        int charIndex2 = this.f43bp + offset4;
        char chLocal = charIndex2 >= this.len ? EOI : this.text.charAt(charIndex2);
        while (true) {
            int offset5 = offset4;
            if (chLocal == ':') {
                return offset5 + 1;
            }
            if (chLocal <= ' ' && (chLocal == ' ' || chLocal == '\n' || chLocal == '\r' || chLocal == '\t' || chLocal == '\f' || chLocal == '\b')) {
                offset4 = offset5 + 1;
                int charIndex3 = this.f43bp + offset5;
                chLocal = charIndex3 >= this.len ? EOI : this.text.charAt(charIndex3);
            } else {
                throw new JSONException("match feild error expect ':'");
            }
        }
    }

    public int scanFieldInt(long fieldHashCode) {
        int offset;
        int offset2;
        char chLocal;
        int offset3;
        int offset4;
        this.matchStat = 0;
        int offset5 = matchFieldHash(fieldHashCode);
        if (offset5 == 0) {
            return 0;
        }
        int offset6 = offset5 + 1;
        int charIndex = this.f43bp + offset5;
        char chLocal2 = charIndex >= this.len ? EOI : this.text.charAt(charIndex);
        boolean quote = chLocal2 == '\"';
        if (quote) {
            quote = true;
            int offset7 = offset6 + 1;
            int charIndex2 = this.f43bp + offset6;
            chLocal2 = charIndex2 >= this.len ? EOI : this.text.charAt(charIndex2);
            offset6 = offset7;
        }
        boolean negative = chLocal2 == '-';
        if (negative) {
            offset = offset6 + 1;
            int charIndex3 = this.f43bp + offset6;
            chLocal2 = charIndex3 >= this.len ? EOI : this.text.charAt(charIndex3);
        } else {
            offset = offset6;
        }
        if (chLocal2 >= '0' && chLocal2 <= '9') {
            int value = chLocal2 - '0';
            while (true) {
                offset2 = offset + 1;
                int charIndex4 = this.f43bp + offset;
                chLocal = charIndex4 >= this.len ? EOI : this.text.charAt(charIndex4);
                if (chLocal < '0' || chLocal > '9') {
                    break;
                }
                value = (value * 10) + (chLocal - '0');
                offset = offset2;
            }
            if (chLocal == '.') {
                this.matchStat = -1;
                return 0;
            }
            if (chLocal != '\"') {
                offset3 = offset2;
            } else {
                if (!quote) {
                    this.matchStat = -1;
                    return 0;
                }
                offset3 = offset2 + 1;
                int index = this.f43bp + offset2;
                chLocal = index >= this.len ? EOI : this.text.charAt(index);
            }
            if (value < 0) {
                this.matchStat = -1;
                return 0;
            }
            while (true) {
                offset4 = offset3;
                if (chLocal == ',') {
                    this.f43bp += offset4 - 1;
                    int index2 = this.f43bp + 1;
                    this.f43bp = index2;
                    this.f44ch = index2 >= this.len ? EOI : this.text.charAt(index2);
                    this.matchStat = 3;
                    this.token = 16;
                    if (negative) {
                        value = -value;
                    }
                    return value;
                }
                if (chLocal > ' ' || !(chLocal == ' ' || chLocal == '\n' || chLocal == '\r' || chLocal == '\t' || chLocal == '\f' || chLocal == '\b')) {
                    break;
                }
                offset3 = offset4 + 1;
                int charIndex5 = this.f43bp + offset4;
                chLocal = charIndex5 >= this.len ? EOI : this.text.charAt(charIndex5);
            }
            if (chLocal == '}') {
                int offset8 = offset4 + 1;
                char chLocal3 = charAt(this.f43bp + offset4);
                if (chLocal3 == ',') {
                    this.token = 16;
                    this.f43bp += offset8 - 1;
                    int index3 = this.f43bp + 1;
                    this.f43bp = index3;
                    this.f44ch = index3 >= this.len ? EOI : this.text.charAt(index3);
                } else if (chLocal3 == ']') {
                    this.token = 15;
                    this.f43bp += offset8 - 1;
                    int index4 = this.f43bp + 1;
                    this.f43bp = index4;
                    this.f44ch = index4 >= this.len ? EOI : this.text.charAt(index4);
                } else if (chLocal3 == '}') {
                    this.token = 13;
                    this.f43bp += offset8 - 1;
                    int index5 = this.f43bp + 1;
                    this.f43bp = index5;
                    this.f44ch = index5 >= this.len ? EOI : this.text.charAt(index5);
                } else if (chLocal3 == 26) {
                    this.token = 20;
                    this.f43bp += offset8 - 1;
                    this.f44ch = EOI;
                } else {
                    this.matchStat = -1;
                    return 0;
                }
                this.matchStat = 4;
                return negative ? -value : value;
            }
            this.matchStat = -1;
            return 0;
        }
        this.matchStat = -1;
        return 0;
    }

    public final int[] scanFieldIntArray(long fieldHashCode) {
        int offset;
        int offset2;
        int offset3;
        char chLocal;
        this.matchStat = 0;
        int offset4 = matchFieldHash(fieldHashCode);
        if (offset4 == 0) {
            return null;
        }
        int offset5 = offset4 + 1;
        int charIndex = this.f43bp + offset4;
        if ((charIndex >= this.len ? EOI : this.text.charAt(charIndex)) != '[') {
            this.matchStat = -1;
            return null;
        }
        int offset6 = offset5 + 1;
        int charIndex2 = this.f43bp + offset5;
        char chLocal2 = charIndex2 >= this.len ? EOI : this.text.charAt(charIndex2);
        int[] array = new int[16];
        int arrayIndex = 0;
        if (chLocal2 == ']') {
            int offset7 = offset6 + 1;
            int charIndex3 = this.f43bp + offset6;
            chLocal = charIndex3 >= this.len ? EOI : this.text.charAt(charIndex3);
            offset3 = offset7;
        } else {
            while (true) {
                int arrayIndex2 = arrayIndex;
                int offset8 = offset6;
                boolean nagative = false;
                if (chLocal2 == '-') {
                    offset = offset8 + 1;
                    int charIndex4 = this.f43bp + offset8;
                    chLocal2 = charIndex4 >= this.len ? EOI : this.text.charAt(charIndex4);
                    nagative = true;
                } else {
                    offset = offset8;
                }
                if (chLocal2 >= '0' && chLocal2 <= '9') {
                    int value = chLocal2 - '0';
                    while (true) {
                        offset2 = offset + 1;
                        int charIndex5 = this.f43bp + offset;
                        chLocal2 = charIndex5 >= this.len ? EOI : this.text.charAt(charIndex5);
                        if (chLocal2 < '0' || chLocal2 > '9') {
                            break;
                        }
                        value = (value * 10) + (chLocal2 - '0');
                        offset = offset2;
                    }
                    if (arrayIndex2 >= array.length) {
                        int[] tmp = new int[(array.length * 3) / 2];
                        System.arraycopy(array, 0, tmp, 0, arrayIndex2);
                        array = tmp;
                    }
                    arrayIndex = arrayIndex2 + 1;
                    if (nagative) {
                        value = -value;
                    }
                    array[arrayIndex2] = value;
                    if (chLocal2 == ',') {
                        offset6 = offset2 + 1;
                        int charIndex6 = this.f43bp + offset2;
                        chLocal2 = charIndex6 >= this.len ? EOI : this.text.charAt(charIndex6);
                    } else {
                        if (chLocal2 == ']') {
                            offset3 = offset2 + 1;
                            int charIndex7 = this.f43bp + offset2;
                            if (charIndex7 < this.len) {
                                chLocal = this.text.charAt(charIndex7);
                                break;
                            }
                            chLocal = EOI;
                            break;
                        }
                        offset6 = offset2;
                    }
                } else {
                    this.matchStat = -1;
                    return null;
                }
            }
        }
        if (arrayIndex != array.length) {
            int[] tmp2 = new int[arrayIndex];
            System.arraycopy(array, 0, tmp2, 0, arrayIndex);
            array = tmp2;
        }
        if (chLocal == ',') {
            this.f43bp += offset3 - 1;
            next();
            this.matchStat = 3;
            this.token = 16;
            return array;
        }
        if (chLocal == '}') {
            int offset9 = offset3 + 1;
            char chLocal3 = charAt(this.f43bp + offset3);
            if (chLocal3 == ',') {
                this.token = 16;
                this.f43bp += offset9 - 1;
                next();
            } else if (chLocal3 == ']') {
                this.token = 15;
                this.f43bp += offset9 - 1;
                next();
            } else if (chLocal3 == '}') {
                this.token = 13;
                this.f43bp += offset9 - 1;
                next();
            } else if (chLocal3 == 26) {
                this.f43bp += offset9 - 1;
                this.token = 20;
                this.f44ch = EOI;
            } else {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return array;
        }
        this.matchStat = -1;
        return null;
    }

    public long scanFieldLong(long fieldHashCode) {
        int offset;
        int offset2;
        char chLocal;
        this.matchStat = 0;
        int offset3 = matchFieldHash(fieldHashCode);
        if (offset3 == 0) {
            return 0L;
        }
        int offset4 = offset3 + 1;
        int index = this.f43bp + offset3;
        char chLocal2 = index >= this.len ? EOI : this.text.charAt(index);
        boolean quote = chLocal2 == '\"';
        if (quote) {
            int offset5 = offset4 + 1;
            int index2 = this.f43bp + offset4;
            chLocal2 = index2 >= this.len ? EOI : this.text.charAt(index2);
            offset4 = offset5;
        }
        boolean negative = chLocal2 == '-';
        if (negative) {
            offset = offset4 + 1;
            int index3 = this.f43bp + offset4;
            chLocal2 = index3 >= this.len ? EOI : this.text.charAt(index3);
        } else {
            offset = offset4;
        }
        if (chLocal2 >= '0' && chLocal2 <= '9') {
            long value = chLocal2 - '0';
            while (true) {
                offset2 = offset + 1;
                int index4 = this.f43bp + offset;
                chLocal = index4 >= this.len ? EOI : this.text.charAt(index4);
                if (chLocal < '0' || chLocal > '9') {
                    break;
                }
                value = (10 * value) + ((long) (chLocal - '0'));
                offset = offset2;
            }
            if (chLocal == '.') {
                this.matchStat = -1;
                return 0L;
            }
            if (chLocal == '\"') {
                if (!quote) {
                    this.matchStat = -1;
                    return 0L;
                }
                int offset6 = offset2 + 1;
                int index5 = this.f43bp + offset2;
                chLocal = index5 >= this.len ? EOI : this.text.charAt(index5);
                offset2 = offset6;
            }
            if (value < 0) {
                this.matchStat = -1;
                return 0L;
            }
            if (chLocal == ',') {
                this.f43bp += offset2 - 1;
                int index6 = this.f43bp + 1;
                this.f43bp = index6;
                this.f44ch = index6 >= this.len ? EOI : this.text.charAt(index6);
                this.matchStat = 3;
                this.token = 16;
                if (negative) {
                    value = -value;
                }
                return value;
            }
            if (chLocal == '}') {
                int offset7 = offset2 + 1;
                char chLocal3 = charAt(this.f43bp + offset2);
                if (chLocal3 == ',') {
                    this.token = 16;
                    this.f43bp += offset7 - 1;
                    int index7 = this.f43bp + 1;
                    this.f43bp = index7;
                    this.f44ch = index7 >= this.len ? EOI : this.text.charAt(index7);
                } else if (chLocal3 == ']') {
                    this.token = 15;
                    this.f43bp += offset7 - 1;
                    int index8 = this.f43bp + 1;
                    this.f43bp = index8;
                    this.f44ch = index8 >= this.len ? EOI : this.text.charAt(index8);
                } else if (chLocal3 == '}') {
                    this.token = 13;
                    this.f43bp += offset7 - 1;
                    int index9 = this.f43bp + 1;
                    this.f43bp = index9;
                    this.f44ch = index9 >= this.len ? EOI : this.text.charAt(index9);
                } else if (chLocal3 == 26) {
                    this.token = 20;
                    this.f43bp += offset7 - 1;
                    this.f44ch = EOI;
                } else {
                    this.matchStat = -1;
                    return 0L;
                }
                this.matchStat = 4;
                return negative ? -value : value;
            }
            this.matchStat = -1;
            return 0L;
        }
        this.matchStat = -1;
        return 0L;
    }

    public String scanFieldString(long fieldHashCode) {
        String strVal;
        this.matchStat = 0;
        int offset = matchFieldHash(fieldHashCode);
        if (offset == 0) {
            return null;
        }
        int offset2 = offset + 1;
        int index = this.f43bp + offset;
        if (index >= this.len) {
            throw new JSONException("unclosed str, " + info());
        }
        if (this.text.charAt(index) != '\"') {
            this.matchStat = -1;
            return this.stringDefaultValue;
        }
        boolean hasSpecial = false;
        int startIndex = this.f43bp + offset2;
        int endIndex = this.text.indexOf(34, startIndex);
        if (endIndex == -1) {
            throw new JSONException("unclosed str, " + info());
        }
        if (f42V6) {
            strVal = this.text.substring(startIndex, endIndex);
        } else {
            int chars_len = endIndex - startIndex;
            strVal = new String(sub_chars(this.f43bp + offset2, chars_len), 0, chars_len);
        }
        if (strVal.indexOf(92) != -1) {
            while (true) {
                int slashCount = 0;
                for (int i = endIndex - 1; i >= 0 && this.text.charAt(i) == '\\'; i--) {
                    hasSpecial = true;
                    slashCount++;
                }
                if (slashCount % 2 == 0) {
                    break;
                }
                endIndex = this.text.indexOf(34, endIndex + 1);
            }
            int chars_len2 = endIndex - startIndex;
            char[] chars = sub_chars(this.f43bp + offset2, chars_len2);
            if (hasSpecial) {
                strVal = readString(chars, chars_len2);
            } else {
                strVal = new String(chars, 0, chars_len2);
                if (strVal.indexOf(92) != -1) {
                    strVal = readString(chars, chars_len2);
                }
            }
        }
        int endIndex2 = endIndex + 1;
        char chLocal = endIndex2 >= this.len ? EOI : this.text.charAt(endIndex2);
        if (chLocal == ',') {
            this.f43bp = endIndex2;
            int index2 = this.f43bp + 1;
            this.f43bp = index2;
            this.f44ch = index2 >= this.len ? EOI : this.text.charAt(index2);
            this.matchStat = 3;
            this.token = 16;
            return strVal;
        }
        if (chLocal == '}') {
            int endIndex3 = endIndex2 + 1;
            char chLocal2 = endIndex3 >= this.len ? EOI : this.text.charAt(endIndex3);
            if (chLocal2 == ',') {
                this.token = 16;
                this.f43bp = endIndex3;
                next();
            } else if (chLocal2 == ']') {
                this.token = 15;
                this.f43bp = endIndex3;
                next();
            } else if (chLocal2 == '}') {
                this.token = 13;
                this.f43bp = endIndex3;
                next();
            } else if (chLocal2 == 26) {
                this.token = 20;
                this.f43bp = endIndex3;
                this.f44ch = EOI;
            } else {
                this.matchStat = -1;
                String strVal2 = this.stringDefaultValue;
                return strVal2;
            }
            this.matchStat = 4;
            return strVal;
        }
        this.matchStat = -1;
        String strVal3 = this.stringDefaultValue;
        return strVal3;
    }

    public Date scanFieldDate(long fieldHashCode) {
        char chLocal;
        int offset;
        Date dateVal;
        int offset2;
        this.matchStat = 0;
        int offset3 = matchFieldHash(fieldHashCode);
        if (offset3 == 0) {
            return null;
        }
        int startPos = this.f43bp;
        char startChar = this.f44ch;
        int offset4 = offset3 + 1;
        int index = this.f43bp + offset3;
        char chLocal2 = index >= this.len ? EOI : this.text.charAt(index);
        if (chLocal2 == '\"') {
            int startIndex = this.f43bp + offset4;
            int offset5 = offset4 + 1;
            int index2 = this.f43bp + offset4;
            if (index2 < this.len) {
                this.text.charAt(index2);
            }
            int endIndex = this.text.indexOf(34, this.f43bp + offset5);
            if (endIndex == -1) {
                throw new JSONException("unclosed str");
            }
            int rest = endIndex - startIndex;
            this.f43bp = startIndex;
            if (scanISO8601DateIfMatch(false, rest)) {
                dateVal = this.calendar.getTime();
                int offset6 = offset5 + rest;
                offset2 = offset6 + 1;
                chLocal = charAt(startPos + offset6);
                this.f43bp = startPos;
            } else {
                this.f43bp = startPos;
                this.matchStat = -1;
                return null;
            }
        } else if (chLocal2 >= '0' && chLocal2 <= '9') {
            long millis = chLocal2 - '0';
            while (true) {
                int offset7 = offset4;
                offset4 = offset7 + 1;
                int index3 = this.f43bp + offset7;
                chLocal = index3 >= this.len ? EOI : this.text.charAt(index3);
                if (chLocal < '0' || chLocal > '9') {
                    break;
                }
                millis = (10 * millis) + ((long) (chLocal - '0'));
            }
            if (chLocal == '.') {
                this.matchStat = -1;
                return null;
            }
            if (chLocal == '\"') {
                offset = offset4 + 1;
                int index4 = this.f43bp + offset4;
                chLocal = index4 >= this.len ? EOI : this.text.charAt(index4);
            } else {
                offset = offset4;
            }
            if (millis < 0) {
                this.matchStat = -1;
                return null;
            }
            dateVal = new Date(millis);
            offset2 = offset;
        } else {
            this.matchStat = -1;
            return null;
        }
        if (chLocal == ',') {
            this.f43bp += offset2 - 1;
            int index5 = this.f43bp + 1;
            this.f43bp = index5;
            this.f44ch = index5 >= this.len ? EOI : this.text.charAt(index5);
            this.matchStat = 3;
            this.token = 16;
            return dateVal;
        }
        if (chLocal == '}') {
            int offset8 = offset2 + 1;
            char chLocal3 = charAt(this.f43bp + offset2);
            if (chLocal3 == ',') {
                this.token = 16;
                this.f43bp += offset8 - 1;
                int index6 = this.f43bp + 1;
                this.f43bp = index6;
                this.f44ch = index6 >= this.len ? EOI : this.text.charAt(index6);
            } else if (chLocal3 == ']') {
                this.token = 15;
                this.f43bp += offset8 - 1;
                int index7 = this.f43bp + 1;
                this.f43bp = index7;
                this.f44ch = index7 >= this.len ? EOI : this.text.charAt(index7);
            } else if (chLocal3 == '}') {
                this.token = 13;
                this.f43bp += offset8 - 1;
                int index8 = this.f43bp + 1;
                this.f43bp = index8;
                this.f44ch = index8 >= this.len ? EOI : this.text.charAt(index8);
            } else if (chLocal3 == 26) {
                this.token = 20;
                this.f43bp += offset8 - 1;
                this.f44ch = EOI;
            } else {
                this.f43bp = startPos;
                this.f44ch = startChar;
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return dateVal;
        }
        this.f43bp = startPos;
        this.f44ch = startChar;
        this.matchStat = -1;
        return null;
    }

    public boolean scanFieldBoolean(long fieldHashCode) {
        int offset;
        boolean value;
        this.matchStat = 0;
        int offset2 = matchFieldHash(fieldHashCode);
        if (offset2 == 0) {
            return false;
        }
        if (this.text.startsWith("false", this.f43bp + offset2)) {
            offset = offset2 + 5;
            value = false;
        } else if (this.text.startsWith("true", this.f43bp + offset2)) {
            offset = offset2 + 4;
            value = true;
        } else if (this.text.startsWith("\"false\"", this.f43bp + offset2)) {
            offset = offset2 + 7;
            value = false;
        } else if (this.text.startsWith("\"true\"", this.f43bp + offset2)) {
            offset = offset2 + 6;
            value = true;
        } else if (this.text.charAt(this.f43bp + offset2) == '1') {
            offset = offset2 + 1;
            value = true;
        } else if (this.text.charAt(this.f43bp + offset2) == '0') {
            offset = offset2 + 1;
            value = false;
        } else if (this.text.startsWith("\"1\"", this.f43bp + offset2)) {
            offset = offset2 + 3;
            value = true;
        } else if (this.text.startsWith("\"0\"", this.f43bp + offset2)) {
            offset = offset2 + 3;
            value = false;
        } else {
            this.matchStat = -1;
            return false;
        }
        int offset3 = offset + 1;
        int charIndex = this.f43bp + offset;
        char chLocal = charIndex >= this.len ? EOI : this.text.charAt(charIndex);
        while (chLocal != ',') {
            if (chLocal != '}' && (chLocal == ' ' || chLocal == '\n' || chLocal == '\r' || chLocal == '\t' || chLocal == '\f' || chLocal == '\b')) {
                int offset4 = offset3 + 1;
                int charIndex2 = this.f43bp + offset3;
                chLocal = charIndex2 >= this.len ? EOI : this.text.charAt(charIndex2);
                offset3 = offset4;
            } else {
                if (chLocal == '}') {
                    int offset5 = offset3 + 1;
                    char chLocal2 = charAt(this.f43bp + offset3);
                    if (chLocal2 == ',') {
                        this.token = 16;
                        this.f43bp += offset5 - 1;
                        int index = this.f43bp + 1;
                        this.f43bp = index;
                        this.f44ch = index >= this.len ? EOI : this.text.charAt(index);
                    } else if (chLocal2 == ']') {
                        this.token = 15;
                        this.f43bp += offset5 - 1;
                        int index2 = this.f43bp + 1;
                        this.f43bp = index2;
                        this.f44ch = index2 >= this.len ? EOI : this.text.charAt(index2);
                    } else if (chLocal2 == '}') {
                        this.token = 13;
                        this.f43bp += offset5 - 1;
                        int index3 = this.f43bp + 1;
                        this.f43bp = index3;
                        this.f44ch = index3 >= this.len ? EOI : this.text.charAt(index3);
                    } else if (chLocal2 == 26) {
                        this.token = 20;
                        this.f43bp += offset5 - 1;
                        this.f44ch = EOI;
                    } else {
                        this.matchStat = -1;
                        return false;
                    }
                    this.matchStat = 4;
                    return value;
                }
                this.matchStat = -1;
                return false;
            }
        }
        this.f43bp += offset3 - 1;
        int index4 = this.f43bp + 1;
        this.f43bp = index4;
        this.f44ch = index4 >= this.len ? EOI : this.text.charAt(index4);
        this.matchStat = 3;
        this.token = 16;
        return value;
    }

    public final float scanFieldFloat(long fieldHashCode) {
        int offset;
        int offset2;
        char chLocal;
        float value;
        this.matchStat = 0;
        int offset3 = matchFieldHash(fieldHashCode);
        if (offset3 == 0) {
            return 0.0f;
        }
        int offset4 = offset3 + 1;
        char chLocal2 = charAt(this.f43bp + offset3);
        int start = (this.f43bp + offset4) - 1;
        boolean negative = chLocal2 == '-';
        if (negative) {
            offset = offset4 + 1;
            chLocal2 = charAt(this.f43bp + offset4);
        } else {
            offset = offset4;
        }
        if (chLocal2 >= '0' && chLocal2 <= '9') {
            int intVal = chLocal2 - '0';
            while (true) {
                offset2 = offset + 1;
                chLocal = charAt(this.f43bp + offset);
                if (chLocal < '0' || chLocal > '9') {
                    break;
                }
                intVal = (intVal * 10) + (chLocal - '0');
                offset = offset2;
            }
            int power = 1;
            boolean small = chLocal == '.';
            if (small) {
                int offset5 = offset2 + 1;
                char chLocal3 = charAt(this.f43bp + offset2);
                if (chLocal3 >= '0' && chLocal3 <= '9') {
                    intVal = (intVal * 10) + (chLocal3 - '0');
                    power = 10;
                    while (true) {
                        offset2 = offset5 + 1;
                        chLocal = charAt(this.f43bp + offset5);
                        if (chLocal < '0' || chLocal > '9') {
                            break;
                        }
                        intVal = (intVal * 10) + (chLocal - '0');
                        power *= 10;
                        offset5 = offset2;
                    }
                } else {
                    this.matchStat = -1;
                    return 0.0f;
                }
            }
            boolean exp = chLocal == 'e' || chLocal == 'E';
            if (exp) {
                int offset6 = offset2 + 1;
                chLocal = charAt(this.f43bp + offset2);
                if (chLocal == '+' || chLocal == '-') {
                    offset2 = offset6 + 1;
                    chLocal = charAt(this.f43bp + offset6);
                } else {
                    offset2 = offset6;
                }
                while (chLocal >= '0' && chLocal <= '9') {
                    chLocal = charAt(this.f43bp + offset2);
                    offset2++;
                }
            }
            int offset7 = offset2;
            int count = ((this.f43bp + offset7) - start) - 1;
            if (!exp && count < 10) {
                value = intVal / power;
                if (negative) {
                    value = -value;
                }
            } else {
                String text = subString(start, count);
                value = Float.parseFloat(text);
            }
            if (chLocal == ',') {
                this.f43bp += offset7 - 1;
                next();
                this.matchStat = 3;
                this.token = 16;
                return value;
            }
            if (chLocal == '}') {
                int offset8 = offset7 + 1;
                char chLocal4 = charAt(this.f43bp + offset7);
                if (chLocal4 == ',') {
                    this.token = 16;
                    this.f43bp += offset8 - 1;
                    next();
                } else if (chLocal4 == ']') {
                    this.token = 15;
                    this.f43bp += offset8 - 1;
                    next();
                } else if (chLocal4 == '}') {
                    this.token = 13;
                    this.f43bp += offset8 - 1;
                    next();
                } else if (chLocal4 == 26) {
                    this.f43bp += offset8 - 1;
                    this.token = 20;
                    this.f44ch = EOI;
                } else {
                    this.matchStat = -1;
                    return 0.0f;
                }
                this.matchStat = 4;
                return value;
            }
            this.matchStat = -1;
            return 0.0f;
        }
        this.matchStat = -1;
        return 0.0f;
    }

    public final float[] scanFieldFloatArray(long fieldHashCode) {
        int offset;
        float value;
        this.matchStat = 0;
        int offset2 = matchFieldHash(fieldHashCode);
        if (offset2 == 0) {
            return null;
        }
        int offset3 = offset2 + 1;
        int charIndex = this.f43bp + offset2;
        char chLocal = charIndex >= this.len ? EOI : this.text.charAt(charIndex);
        if (chLocal != '[') {
            this.matchStat = -1;
            return null;
        }
        int offset4 = offset3 + 1;
        int charIndex2 = this.f43bp + offset3;
        char chLocal2 = charIndex2 >= this.len ? EOI : this.text.charAt(charIndex2);
        float[] array = new float[16];
        int arrayIndex = 0;
        while (true) {
            int start = (this.f43bp + offset4) - 1;
            boolean negative = chLocal2 == '-';
            if (negative) {
                int offset5 = offset4 + 1;
                int charIndex3 = this.f43bp + offset4;
                chLocal2 = charIndex3 >= this.len ? EOI : this.text.charAt(charIndex3);
                offset4 = offset5;
            }
            if (chLocal2 < '0' || chLocal2 > '9') {
                break;
            }
            int intVal = chLocal2 - '0';
            while (true) {
                offset = offset4 + 1;
                int charIndex4 = this.f43bp + offset4;
                chLocal2 = charIndex4 >= this.len ? EOI : this.text.charAt(charIndex4);
                if (chLocal2 < '0' || chLocal2 > '9') {
                    break;
                }
                intVal = (intVal * 10) + (chLocal2 - '0');
                offset4 = offset;
            }
            int power = 1;
            boolean small = chLocal2 == '.';
            if (small) {
                int offset6 = offset + 1;
                int charIndex5 = this.f43bp + offset;
                char chLocal3 = charIndex5 >= this.len ? EOI : this.text.charAt(charIndex5);
                power = 1 * 10;
                if (chLocal3 >= '0' && chLocal3 <= '9') {
                    intVal = (intVal * 10) + (chLocal3 - '0');
                    while (true) {
                        offset = offset6 + 1;
                        int charIndex6 = this.f43bp + offset6;
                        chLocal2 = charIndex6 >= this.len ? EOI : this.text.charAt(charIndex6);
                        if (chLocal2 < '0' || chLocal2 > '9') {
                            break;
                        }
                        intVal = (intVal * 10) + (chLocal2 - '0');
                        power *= 10;
                        offset6 = offset;
                    }
                } else {
                    this.matchStat = -1;
                    return null;
                }
            }
            boolean exp = chLocal2 == 'e' || chLocal2 == 'E';
            if (exp) {
                int offset7 = offset + 1;
                int charIndex7 = this.f43bp + offset;
                chLocal2 = charIndex7 >= this.len ? EOI : this.text.charAt(charIndex7);
                if (chLocal2 == '+' || chLocal2 == '-') {
                    offset = offset7 + 1;
                    int charIndex8 = this.f43bp + offset7;
                    chLocal2 = charIndex8 >= this.len ? EOI : this.text.charAt(charIndex8);
                } else {
                    offset = offset7;
                }
                while (chLocal2 >= '0' && chLocal2 <= '9') {
                    int offset8 = offset + 1;
                    int charIndex9 = this.f43bp + offset;
                    chLocal2 = charIndex9 >= this.len ? EOI : this.text.charAt(charIndex9);
                    offset = offset8;
                }
            }
            offset4 = offset;
            int count = ((this.f43bp + offset4) - start) - 1;
            if (!exp && count < 10) {
                value = intVal / power;
                if (negative) {
                    value = -value;
                }
            } else {
                String text = subString(start, count);
                value = Float.parseFloat(text);
            }
            if (arrayIndex >= array.length) {
                float[] tmp = new float[(array.length * 3) / 2];
                System.arraycopy(array, 0, tmp, 0, arrayIndex);
                array = tmp;
            }
            int arrayIndex2 = arrayIndex + 1;
            array[arrayIndex] = value;
            if (chLocal2 == ',') {
                int offset9 = offset4 + 1;
                int charIndex10 = this.f43bp + offset4;
                chLocal2 = charIndex10 >= this.len ? EOI : this.text.charAt(charIndex10);
                offset4 = offset9;
            } else if (chLocal2 == ']') {
                int offset10 = offset4 + 1;
                int charIndex11 = this.f43bp + offset4;
                char chLocal4 = charIndex11 >= this.len ? EOI : this.text.charAt(charIndex11);
                if (arrayIndex2 != array.length) {
                    float[] tmp2 = new float[arrayIndex2];
                    System.arraycopy(array, 0, tmp2, 0, arrayIndex2);
                    array = tmp2;
                }
                if (chLocal4 == ',') {
                    this.f43bp += offset10 - 1;
                    next();
                    this.matchStat = 3;
                    this.token = 16;
                    return array;
                }
                if (chLocal4 == '}') {
                    int offset11 = offset10 + 1;
                    int charIndex12 = this.f43bp + offset10;
                    char chLocal5 = charIndex12 >= this.len ? EOI : this.text.charAt(charIndex12);
                    if (chLocal5 == ',') {
                        this.token = 16;
                        this.f43bp += offset11 - 1;
                        next();
                    } else if (chLocal5 == ']') {
                        this.token = 15;
                        this.f43bp += offset11 - 1;
                        next();
                    } else if (chLocal5 == '}') {
                        this.token = 13;
                        this.f43bp += offset11 - 1;
                        next();
                    } else if (chLocal5 == 26) {
                        this.f43bp += offset11 - 1;
                        this.token = 20;
                        this.f44ch = EOI;
                    } else {
                        this.matchStat = -1;
                        return null;
                    }
                    this.matchStat = 4;
                    return array;
                }
                this.matchStat = -1;
                return null;
            }
            arrayIndex = arrayIndex2;
        }
        this.matchStat = -1;
        return null;
    }

    public final float[][] scanFieldFloatArray2(long fieldHashCode) {
        int offset;
        float value;
        int offset2;
        this.matchStat = 0;
        int offset3 = matchFieldHash(fieldHashCode);
        if (offset3 == 0) {
            return (float[][]) null;
        }
        int offset4 = offset3 + 1;
        int charIndex = this.f43bp + offset3;
        char chLocal = charIndex >= this.len ? EOI : this.text.charAt(charIndex);
        if (chLocal != '[') {
            this.matchStat = -1;
            return (float[][]) null;
        }
        int offset5 = offset4 + 1;
        int charIndex2 = this.f43bp + offset4;
        char chLocal2 = charIndex2 >= this.len ? EOI : this.text.charAt(charIndex2);
        float[][] arrayarray = new float[16][];
        int arrayarrayIndex = 0;
        int offset6 = offset5;
        loop0: while (true) {
            if (chLocal2 == '[') {
                int offset7 = offset6 + 1;
                int charIndex3 = this.f43bp + offset6;
                char chLocal3 = charIndex3 >= this.len ? EOI : this.text.charAt(charIndex3);
                float[] array = new float[16];
                int arrayIndex = 0;
                while (true) {
                    int start = (this.f43bp + offset7) - 1;
                    boolean negative = chLocal3 == '-';
                    if (negative) {
                        int offset8 = offset7 + 1;
                        int charIndex4 = this.f43bp + offset7;
                        chLocal3 = charIndex4 >= this.len ? EOI : this.text.charAt(charIndex4);
                        offset7 = offset8;
                    }
                    if (chLocal3 < '0' || chLocal3 > '9') {
                        break loop0;
                    }
                    int intVal = chLocal3 - '0';
                    while (true) {
                        offset = offset7 + 1;
                        int charIndex5 = this.f43bp + offset7;
                        chLocal3 = charIndex5 >= this.len ? EOI : this.text.charAt(charIndex5);
                        if (chLocal3 < '0' || chLocal3 > '9') {
                            break;
                        }
                        intVal = (intVal * 10) + (chLocal3 - '0');
                        offset7 = offset;
                    }
                    int power = 1;
                    if (chLocal3 == '.') {
                        int offset9 = offset + 1;
                        int charIndex6 = this.f43bp + offset;
                        char chLocal4 = charIndex6 >= this.len ? EOI : this.text.charAt(charIndex6);
                        if (chLocal4 >= '0' && chLocal4 <= '9') {
                            intVal = (intVal * 10) + (chLocal4 - '0');
                            power = 1 * 10;
                            while (true) {
                                offset = offset9 + 1;
                                int charIndex7 = this.f43bp + offset9;
                                chLocal3 = charIndex7 >= this.len ? EOI : this.text.charAt(charIndex7);
                                if (chLocal3 < '0' || chLocal3 > '9') {
                                    break;
                                }
                                intVal = (intVal * 10) + (chLocal3 - '0');
                                power *= 10;
                                offset9 = offset;
                            }
                        } else {
                            this.matchStat = -1;
                            return (float[][]) null;
                        }
                    }
                    boolean exp = chLocal3 == 'e' || chLocal3 == 'E';
                    if (exp) {
                        int offset10 = offset + 1;
                        int charIndex8 = this.f43bp + offset;
                        chLocal3 = charIndex8 >= this.len ? EOI : this.text.charAt(charIndex8);
                        if (chLocal3 == '+' || chLocal3 == '-') {
                            offset = offset10 + 1;
                            int charIndex9 = this.f43bp + offset10;
                            chLocal3 = charIndex9 >= this.len ? EOI : this.text.charAt(charIndex9);
                        } else {
                            offset = offset10;
                        }
                        while (chLocal3 >= '0' && chLocal3 <= '9') {
                            int offset11 = offset + 1;
                            int charIndex10 = this.f43bp + offset;
                            chLocal3 = charIndex10 >= this.len ? EOI : this.text.charAt(charIndex10);
                            offset = offset11;
                        }
                    }
                    offset7 = offset;
                    int count = ((this.f43bp + offset7) - start) - 1;
                    if (!exp && count < 10) {
                        value = intVal / power;
                        if (negative) {
                            value = -value;
                        }
                    } else {
                        String text = subString(start, count);
                        value = Float.parseFloat(text);
                    }
                    if (arrayIndex >= array.length) {
                        float[] tmp = new float[(array.length * 3) / 2];
                        System.arraycopy(array, 0, tmp, 0, arrayIndex);
                        array = tmp;
                    }
                    int arrayIndex2 = arrayIndex + 1;
                    array[arrayIndex] = value;
                    if (chLocal3 == ',') {
                        int offset12 = offset7 + 1;
                        int charIndex11 = this.f43bp + offset7;
                        chLocal3 = charIndex11 >= this.len ? EOI : this.text.charAt(charIndex11);
                        offset7 = offset12;
                    } else if (chLocal3 == ']') {
                        int offset13 = offset7 + 1;
                        int charIndex12 = this.f43bp + offset7;
                        chLocal2 = charIndex12 >= this.len ? EOI : this.text.charAt(charIndex12);
                        if (arrayIndex2 != array.length) {
                            float[] tmp2 = new float[arrayIndex2];
                            System.arraycopy(array, 0, tmp2, 0, arrayIndex2);
                            array = tmp2;
                        }
                        if (arrayarrayIndex >= arrayarray.length) {
                            float[][] tmp3 = new float[(arrayarray.length * 3) / 2][];
                            System.arraycopy(array, 0, tmp3, 0, arrayIndex2);
                            arrayarray = tmp3;
                        }
                        int arrayarrayIndex2 = arrayarrayIndex + 1;
                        arrayarray[arrayarrayIndex] = array;
                        if (chLocal2 == ',') {
                            offset2 = offset13 + 1;
                            int charIndex13 = this.f43bp + offset13;
                            chLocal2 = charIndex13 >= this.len ? EOI : this.text.charAt(charIndex13);
                        } else {
                            if (chLocal2 == ']') {
                                int offset14 = offset13 + 1;
                                int charIndex14 = this.f43bp + offset13;
                                char chLocal5 = charIndex14 >= this.len ? EOI : this.text.charAt(charIndex14);
                                if (arrayarrayIndex2 != arrayarray.length) {
                                    float[][] tmp4 = new float[arrayarrayIndex2][];
                                    System.arraycopy(arrayarray, 0, tmp4, 0, arrayarrayIndex2);
                                    arrayarray = tmp4;
                                }
                                if (chLocal5 == ',') {
                                    this.f43bp += offset14 - 1;
                                    next();
                                    this.matchStat = 3;
                                    this.token = 16;
                                    return arrayarray;
                                }
                                if (chLocal5 == '}') {
                                    int offset15 = offset14 + 1;
                                    char chLocal6 = charAt(this.f43bp + offset14);
                                    if (chLocal6 == ',') {
                                        this.token = 16;
                                        this.f43bp += offset15 - 1;
                                        next();
                                    } else if (chLocal6 == ']') {
                                        this.token = 15;
                                        this.f43bp += offset15 - 1;
                                        next();
                                    } else if (chLocal6 == '}') {
                                        this.token = 13;
                                        this.f43bp += offset15 - 1;
                                        next();
                                    } else if (chLocal6 == 26) {
                                        this.f43bp += offset15 - 1;
                                        this.token = 20;
                                        this.f44ch = EOI;
                                    } else {
                                        this.matchStat = -1;
                                        return (float[][]) null;
                                    }
                                    this.matchStat = 4;
                                    return arrayarray;
                                }
                                this.matchStat = -1;
                                return (float[][]) null;
                            }
                            offset2 = offset13;
                        }
                        arrayarrayIndex = arrayarrayIndex2;
                        offset6 = offset2;
                        break;
                    }
                    arrayIndex = arrayIndex2;
                }
            }
        }
        this.matchStat = -1;
        return (float[][]) null;
    }

    public final double scanFieldDouble(long fieldHashCode) {
        int offset;
        int offset2;
        char chLocal;
        double value;
        this.matchStat = 0;
        int offset3 = matchFieldHash(fieldHashCode);
        if (offset3 == 0) {
            return 0.0d;
        }
        int offset4 = offset3 + 1;
        char chLocal2 = charAt(this.f43bp + offset3);
        int start = (this.f43bp + offset4) - 1;
        boolean negative = chLocal2 == '-';
        if (negative) {
            offset = offset4 + 1;
            chLocal2 = charAt(this.f43bp + offset4);
        } else {
            offset = offset4;
        }
        if (chLocal2 >= '0' && chLocal2 <= '9') {
            int intVal = chLocal2 - '0';
            while (true) {
                offset2 = offset + 1;
                chLocal = charAt(this.f43bp + offset);
                if (chLocal < '0' || chLocal > '9') {
                    break;
                }
                intVal = (intVal * 10) + (chLocal - '0');
                offset = offset2;
            }
            int power = 1;
            boolean small = chLocal == '.';
            if (small) {
                int offset5 = offset2 + 1;
                char chLocal3 = charAt(this.f43bp + offset2);
                if (chLocal3 >= '0' && chLocal3 <= '9') {
                    intVal = (intVal * 10) + (chLocal3 - '0');
                    power = 1 * 10;
                    while (true) {
                        offset2 = offset5 + 1;
                        chLocal = charAt(this.f43bp + offset5);
                        if (chLocal < '0' || chLocal > '9') {
                            break;
                        }
                        intVal = (intVal * 10) + (chLocal - '0');
                        power *= 10;
                        offset5 = offset2;
                    }
                } else {
                    this.matchStat = -1;
                    return 0.0d;
                }
            }
            boolean exp = chLocal == 'e' || chLocal == 'E';
            if (exp) {
                int offset6 = offset2 + 1;
                chLocal = charAt(this.f43bp + offset2);
                if (chLocal == '+' || chLocal == '-') {
                    offset2 = offset6 + 1;
                    chLocal = charAt(this.f43bp + offset6);
                } else {
                    offset2 = offset6;
                }
                while (chLocal >= '0' && chLocal <= '9') {
                    chLocal = charAt(this.f43bp + offset2);
                    offset2++;
                }
            }
            int offset7 = offset2;
            int count = ((this.f43bp + offset7) - start) - 1;
            if (!exp && count < 10) {
                value = ((double) intVal) / ((double) power);
                if (negative) {
                    value = -value;
                }
            } else {
                String text = subString(start, count);
                value = Double.parseDouble(text);
            }
            if (chLocal == ',') {
                this.f43bp += offset7 - 1;
                next();
                this.matchStat = 3;
                this.token = 16;
                return value;
            }
            if (chLocal == '}') {
                int offset8 = offset7 + 1;
                char chLocal4 = charAt(this.f43bp + offset7);
                if (chLocal4 == ',') {
                    this.token = 16;
                    this.f43bp += offset8 - 1;
                    next();
                } else if (chLocal4 == ']') {
                    this.token = 15;
                    this.f43bp += offset8 - 1;
                    next();
                } else if (chLocal4 == '}') {
                    this.token = 13;
                    this.f43bp += offset8 - 1;
                    next();
                } else if (chLocal4 == 26) {
                    this.f43bp += offset8 - 1;
                    this.token = 20;
                    this.f44ch = EOI;
                } else {
                    this.matchStat = -1;
                    return 0.0d;
                }
                this.matchStat = 4;
                return value;
            }
            this.matchStat = -1;
            return 0.0d;
        }
        this.matchStat = -1;
        return 0.0d;
    }

    public final double[] scanFieldDoubleArray(long fieldHashCode) {
        int offset;
        double value;
        this.matchStat = 0;
        int offset2 = matchFieldHash(fieldHashCode);
        if (offset2 == 0) {
            return null;
        }
        int offset3 = offset2 + 1;
        int charIndex = this.f43bp + offset2;
        char chLocal = charIndex >= this.len ? EOI : this.text.charAt(charIndex);
        if (chLocal != '[') {
            this.matchStat = -1;
            return null;
        }
        int offset4 = offset3 + 1;
        int charIndex2 = this.f43bp + offset3;
        char chLocal2 = charIndex2 >= this.len ? EOI : this.text.charAt(charIndex2);
        double[] array = new double[16];
        int arrayIndex = 0;
        while (true) {
            int start = (this.f43bp + offset4) - 1;
            boolean negative = chLocal2 == '-';
            if (negative) {
                int offset5 = offset4 + 1;
                int charIndex3 = this.f43bp + offset4;
                chLocal2 = charIndex3 >= this.len ? EOI : this.text.charAt(charIndex3);
                offset4 = offset5;
            }
            if (chLocal2 < '0' || chLocal2 > '9') {
                break;
            }
            int intVal = chLocal2 - '0';
            while (true) {
                offset = offset4 + 1;
                int charIndex4 = this.f43bp + offset4;
                chLocal2 = charIndex4 >= this.len ? EOI : this.text.charAt(charIndex4);
                if (chLocal2 < '0' || chLocal2 > '9') {
                    break;
                }
                intVal = (intVal * 10) + (chLocal2 - '0');
                offset4 = offset;
            }
            int power = 1;
            boolean small = chLocal2 == '.';
            if (small) {
                int offset6 = offset + 1;
                int charIndex5 = this.f43bp + offset;
                char chLocal3 = charIndex5 >= this.len ? EOI : this.text.charAt(charIndex5);
                power = 1 * 10;
                if (chLocal3 >= '0' && chLocal3 <= '9') {
                    intVal = (intVal * 10) + (chLocal3 - '0');
                    while (true) {
                        offset = offset6 + 1;
                        int charIndex6 = this.f43bp + offset6;
                        chLocal2 = charIndex6 >= this.len ? EOI : this.text.charAt(charIndex6);
                        if (chLocal2 < '0' || chLocal2 > '9') {
                            break;
                        }
                        intVal = (intVal * 10) + (chLocal2 - '0');
                        power *= 10;
                        offset6 = offset;
                    }
                } else {
                    this.matchStat = -1;
                    return null;
                }
            }
            boolean exp = chLocal2 == 'e' || chLocal2 == 'E';
            if (exp) {
                int offset7 = offset + 1;
                int charIndex7 = this.f43bp + offset;
                chLocal2 = charIndex7 >= this.len ? EOI : this.text.charAt(charIndex7);
                if (chLocal2 == '+' || chLocal2 == '-') {
                    offset = offset7 + 1;
                    int charIndex8 = this.f43bp + offset7;
                    chLocal2 = charIndex8 >= this.len ? EOI : this.text.charAt(charIndex8);
                } else {
                    offset = offset7;
                }
                while (chLocal2 >= '0' && chLocal2 <= '9') {
                    int offset8 = offset + 1;
                    int charIndex9 = this.f43bp + offset;
                    chLocal2 = charIndex9 >= this.len ? EOI : this.text.charAt(charIndex9);
                    offset = offset8;
                }
            }
            offset4 = offset;
            int count = ((this.f43bp + offset4) - start) - 1;
            if (!exp && count < 10) {
                value = ((double) intVal) / ((double) power);
                if (negative) {
                    value = -value;
                }
            } else {
                String text = subString(start, count);
                value = Double.parseDouble(text);
            }
            if (arrayIndex >= array.length) {
                double[] tmp = new double[(array.length * 3) / 2];
                System.arraycopy(array, 0, tmp, 0, arrayIndex);
                array = tmp;
            }
            int arrayIndex2 = arrayIndex + 1;
            array[arrayIndex] = value;
            if (chLocal2 == ',') {
                int offset9 = offset4 + 1;
                int charIndex10 = this.f43bp + offset4;
                chLocal2 = charIndex10 >= this.len ? EOI : this.text.charAt(charIndex10);
                offset4 = offset9;
            } else if (chLocal2 == ']') {
                int offset10 = offset4 + 1;
                int charIndex11 = this.f43bp + offset4;
                char chLocal4 = charIndex11 >= this.len ? EOI : this.text.charAt(charIndex11);
                if (arrayIndex2 != array.length) {
                    double[] tmp2 = new double[arrayIndex2];
                    System.arraycopy(array, 0, tmp2, 0, arrayIndex2);
                    array = tmp2;
                }
                if (chLocal4 == ',') {
                    this.f43bp += offset10 - 1;
                    next();
                    this.matchStat = 3;
                    this.token = 16;
                    return array;
                }
                if (chLocal4 == '}') {
                    int offset11 = offset10 + 1;
                    int charIndex12 = this.f43bp + offset10;
                    char chLocal5 = charIndex12 >= this.len ? EOI : this.text.charAt(charIndex12);
                    if (chLocal5 == ',') {
                        this.token = 16;
                        this.f43bp += offset11 - 1;
                        next();
                    } else if (chLocal5 == ']') {
                        this.token = 15;
                        this.f43bp += offset11 - 1;
                        next();
                    } else if (chLocal5 == '}') {
                        this.token = 13;
                        this.f43bp += offset11 - 1;
                        next();
                    } else if (chLocal5 == 26) {
                        this.f43bp += offset11 - 1;
                        this.token = 20;
                        this.f44ch = EOI;
                    } else {
                        this.matchStat = -1;
                        return null;
                    }
                    this.matchStat = 4;
                    return array;
                }
                this.matchStat = -1;
                return null;
            }
            arrayIndex = arrayIndex2;
        }
        this.matchStat = -1;
        return null;
    }

    public final double[][] scanFieldDoubleArray2(long fieldHashCode) {
        int offset;
        double value;
        int offset2;
        this.matchStat = 0;
        int offset3 = matchFieldHash(fieldHashCode);
        if (offset3 == 0) {
            return (double[][]) null;
        }
        int offset4 = offset3 + 1;
        int charIndex = this.f43bp + offset3;
        char chLocal = charIndex >= this.len ? EOI : this.text.charAt(charIndex);
        if (chLocal != '[') {
            this.matchStat = -1;
            return (double[][]) null;
        }
        int offset5 = offset4 + 1;
        int charIndex2 = this.f43bp + offset4;
        char chLocal2 = charIndex2 >= this.len ? EOI : this.text.charAt(charIndex2);
        double[][] arrayarray = new double[16][];
        int arrayarrayIndex = 0;
        int offset6 = offset5;
        loop0: while (true) {
            if (chLocal2 == '[') {
                int offset7 = offset6 + 1;
                int charIndex3 = this.f43bp + offset6;
                char chLocal3 = charIndex3 >= this.len ? EOI : this.text.charAt(charIndex3);
                double[] array = new double[16];
                int arrayIndex = 0;
                while (true) {
                    int start = (this.f43bp + offset7) - 1;
                    boolean negative = chLocal3 == '-';
                    if (negative) {
                        int offset8 = offset7 + 1;
                        int charIndex4 = this.f43bp + offset7;
                        chLocal3 = charIndex4 >= this.len ? EOI : this.text.charAt(charIndex4);
                        offset7 = offset8;
                    }
                    if (chLocal3 < '0' || chLocal3 > '9') {
                        break loop0;
                    }
                    int intVal = chLocal3 - '0';
                    while (true) {
                        offset = offset7 + 1;
                        int charIndex5 = this.f43bp + offset7;
                        chLocal3 = charIndex5 >= this.len ? EOI : this.text.charAt(charIndex5);
                        if (chLocal3 < '0' || chLocal3 > '9') {
                            break;
                        }
                        intVal = (intVal * 10) + (chLocal3 - '0');
                        offset7 = offset;
                    }
                    int power = 1;
                    if (chLocal3 == '.') {
                        int offset9 = offset + 1;
                        int charIndex6 = this.f43bp + offset;
                        char chLocal4 = charIndex6 >= this.len ? EOI : this.text.charAt(charIndex6);
                        if (chLocal4 >= '0' && chLocal4 <= '9') {
                            intVal = (intVal * 10) + (chLocal4 - '0');
                            power = 1 * 10;
                            while (true) {
                                offset = offset9 + 1;
                                int charIndex7 = this.f43bp + offset9;
                                chLocal3 = charIndex7 >= this.len ? EOI : this.text.charAt(charIndex7);
                                if (chLocal3 < '0' || chLocal3 > '9') {
                                    break;
                                }
                                intVal = (intVal * 10) + (chLocal3 - '0');
                                power *= 10;
                                offset9 = offset;
                            }
                        } else {
                            this.matchStat = -1;
                            return (double[][]) null;
                        }
                    }
                    boolean exp = chLocal3 == 'e' || chLocal3 == 'E';
                    if (exp) {
                        int offset10 = offset + 1;
                        int charIndex8 = this.f43bp + offset;
                        chLocal3 = charIndex8 >= this.len ? EOI : this.text.charAt(charIndex8);
                        if (chLocal3 == '+' || chLocal3 == '-') {
                            offset = offset10 + 1;
                            int charIndex9 = this.f43bp + offset10;
                            chLocal3 = charIndex9 >= this.len ? EOI : this.text.charAt(charIndex9);
                        } else {
                            offset = offset10;
                        }
                        while (chLocal3 >= '0' && chLocal3 <= '9') {
                            int offset11 = offset + 1;
                            int charIndex10 = this.f43bp + offset;
                            chLocal3 = charIndex10 >= this.len ? EOI : this.text.charAt(charIndex10);
                            offset = offset11;
                        }
                    }
                    offset7 = offset;
                    int count = ((this.f43bp + offset7) - start) - 1;
                    if (!exp && count < 10) {
                        value = ((double) intVal) / ((double) power);
                        if (negative) {
                            value = -value;
                        }
                    } else {
                        String text = subString(start, count);
                        value = Double.parseDouble(text);
                    }
                    if (arrayIndex >= array.length) {
                        double[] tmp = new double[(array.length * 3) / 2];
                        System.arraycopy(array, 0, tmp, 0, arrayIndex);
                        array = tmp;
                    }
                    int arrayIndex2 = arrayIndex + 1;
                    array[arrayIndex] = value;
                    if (chLocal3 == ',') {
                        int offset12 = offset7 + 1;
                        int charIndex11 = this.f43bp + offset7;
                        chLocal3 = charIndex11 >= this.len ? EOI : this.text.charAt(charIndex11);
                        offset7 = offset12;
                    } else if (chLocal3 == ']') {
                        int offset13 = offset7 + 1;
                        int charIndex12 = this.f43bp + offset7;
                        chLocal2 = charIndex12 >= this.len ? EOI : this.text.charAt(charIndex12);
                        if (arrayIndex2 != array.length) {
                            double[] tmp2 = new double[arrayIndex2];
                            System.arraycopy(array, 0, tmp2, 0, arrayIndex2);
                            array = tmp2;
                        }
                        if (arrayarrayIndex >= arrayarray.length) {
                            double[][] tmp3 = new double[(arrayarray.length * 3) / 2][];
                            System.arraycopy(array, 0, tmp3, 0, arrayIndex2);
                            arrayarray = tmp3;
                        }
                        int arrayarrayIndex2 = arrayarrayIndex + 1;
                        arrayarray[arrayarrayIndex] = array;
                        if (chLocal2 == ',') {
                            offset2 = offset13 + 1;
                            int charIndex13 = this.f43bp + offset13;
                            chLocal2 = charIndex13 >= this.len ? EOI : this.text.charAt(charIndex13);
                        } else {
                            if (chLocal2 == ']') {
                                int offset14 = offset13 + 1;
                                int charIndex14 = this.f43bp + offset13;
                                char chLocal5 = charIndex14 >= this.len ? EOI : this.text.charAt(charIndex14);
                                if (arrayarrayIndex2 != arrayarray.length) {
                                    double[][] tmp4 = new double[arrayarrayIndex2][];
                                    System.arraycopy(arrayarray, 0, tmp4, 0, arrayarrayIndex2);
                                    arrayarray = tmp4;
                                }
                                if (chLocal5 == ',') {
                                    this.f43bp += offset14 - 1;
                                    next();
                                    this.matchStat = 3;
                                    this.token = 16;
                                    return arrayarray;
                                }
                                if (chLocal5 == '}') {
                                    int offset15 = offset14 + 1;
                                    char chLocal6 = charAt(this.f43bp + offset14);
                                    if (chLocal6 == ',') {
                                        this.token = 16;
                                        this.f43bp += offset15 - 1;
                                        next();
                                    } else if (chLocal6 == ']') {
                                        this.token = 15;
                                        this.f43bp += offset15 - 1;
                                        next();
                                    } else if (chLocal6 == '}') {
                                        this.token = 13;
                                        this.f43bp += offset15 - 1;
                                        next();
                                    } else if (chLocal6 == 26) {
                                        this.f43bp += offset15 - 1;
                                        this.token = 20;
                                        this.f44ch = EOI;
                                    } else {
                                        this.matchStat = -1;
                                        return (double[][]) null;
                                    }
                                    this.matchStat = 4;
                                    return arrayarray;
                                }
                                this.matchStat = -1;
                                return (double[][]) null;
                            }
                            offset2 = offset13;
                        }
                        arrayarrayIndex = arrayarrayIndex2;
                        offset6 = offset2;
                        break;
                    }
                    arrayIndex = arrayIndex2;
                }
            }
        }
        this.matchStat = -1;
        return (double[][]) null;
    }

    public long scanFieldSymbol(long fieldHashCode) {
        char chLocal;
        this.matchStat = 0;
        int offset = matchFieldHash(fieldHashCode);
        if (offset == 0) {
            return 0L;
        }
        int offset2 = offset + 1;
        int charIndex = this.f43bp + offset;
        if ((charIndex >= this.len ? EOI : this.text.charAt(charIndex)) != '\"') {
            this.matchStat = -1;
            return 0L;
        }
        long hash = -3750763034362895579L;
        int i = this.f43bp + offset2;
        do {
            int offset3 = offset2;
            offset2 = offset3 + 1;
            int charIndex2 = this.f43bp + offset3;
            chLocal = charIndex2 >= this.len ? EOI : this.text.charAt(charIndex2);
            if (chLocal == '\"') {
                int offset4 = offset2 + 1;
                int charIndex3 = this.f43bp + offset2;
                char chLocal2 = charIndex3 >= this.len ? EOI : this.text.charAt(charIndex3);
                if (chLocal2 == ',') {
                    this.f43bp += offset4 - 1;
                    int index = this.f43bp + 1;
                    this.f43bp = index;
                    this.f44ch = index >= this.len ? EOI : this.text.charAt(index);
                    this.matchStat = 3;
                    return hash;
                }
                if (chLocal2 == '}') {
                    int offset5 = offset4 + 1;
                    int charIndex4 = this.f43bp + offset4;
                    char chLocal3 = charIndex4 >= this.len ? EOI : this.text.charAt(charIndex4);
                    if (chLocal3 == ',') {
                        this.token = 16;
                        this.f43bp += offset5 - 1;
                        next();
                    } else if (chLocal3 == ']') {
                        this.token = 15;
                        this.f43bp += offset5 - 1;
                        next();
                    } else if (chLocal3 == '}') {
                        this.token = 13;
                        this.f43bp += offset5 - 1;
                        next();
                    } else if (chLocal3 == 26) {
                        this.token = 20;
                        this.f43bp += offset5 - 1;
                        this.f44ch = EOI;
                    } else {
                        this.matchStat = -1;
                        return 0L;
                    }
                    this.matchStat = 4;
                    return hash;
                }
                this.matchStat = -1;
                return 0L;
            }
            hash = (hash ^ ((long) chLocal)) * 1099511628211L;
        } while (chLocal != '\\');
        this.matchStat = -1;
        return 0L;
    }

    public boolean scanISO8601DateIfMatch(boolean strict) {
        return scanISO8601DateIfMatch(strict, this.len - this.f43bp);
    }

    public boolean scanISO8601DateIfMatch(boolean strict, int rest) {
        char M0;
        char M1;
        char d0;
        char d1;
        int hour;
        int minute;
        int seconds;
        int millis;
        char h0;
        char h1;
        char m0;
        char m1;
        char s0;
        char s1;
        char y0;
        char y1;
        char y2;
        char y3;
        char M2;
        char M3;
        char d2;
        char d3;
        char t1;
        char c10;
        if (!strict && rest > 13) {
            char c0 = charAt(this.f43bp);
            char c1 = charAt(this.f43bp + 1);
            char c2 = charAt(this.f43bp + 2);
            char c3 = charAt(this.f43bp + 3);
            char c4 = charAt(this.f43bp + 4);
            char c5 = charAt(this.f43bp + 5);
            char c_r0 = charAt((this.f43bp + rest) - 1);
            char c_r1 = charAt((this.f43bp + rest) - 2);
            if (c0 == '/' && c1 == 'D' && c2 == 'a' && c3 == 't' && c4 == 'e' && c5 == '(' && c_r0 == '/' && c_r1 == ')') {
                int plusIndex = -1;
                for (int i = 6; i < rest; i++) {
                    char c = charAt(this.f43bp + i);
                    if (c == '+') {
                        plusIndex = i;
                    } else if (c < '0' || c > '9') {
                        break;
                    }
                }
                if (plusIndex == -1) {
                    return false;
                }
                int offset = this.f43bp + 6;
                String numberText = subString(offset, plusIndex - offset);
                long millis2 = Long.parseLong(numberText);
                this.calendar = Calendar.getInstance(this.timeZone, this.locale);
                this.calendar.setTimeInMillis(millis2);
                this.token = 5;
                return true;
            }
        }
        if (rest == 8 || rest == 14 || ((rest == 16 && ((c10 = charAt(this.f43bp + 10)) == 'T' || c10 == ' ')) || (rest == 17 && charAt(this.f43bp + 6) != '-'))) {
            if (strict) {
                return false;
            }
            char c6 = charAt(this.f43bp);
            char c7 = charAt(this.f43bp + 1);
            char c8 = charAt(this.f43bp + 2);
            char c9 = charAt(this.f43bp + 3);
            char c11 = charAt(this.f43bp + 4);
            char c12 = charAt(this.f43bp + 5);
            char c13 = charAt(this.f43bp + 6);
            char c14 = charAt(this.f43bp + 7);
            char c15 = charAt(this.f43bp + 8);
            boolean c_47 = c11 == '-' && c14 == '-';
            boolean sperate16 = c_47 && rest == 16;
            boolean sperate17 = c_47 && rest == 17;
            if (sperate17 || sperate16) {
                M0 = c12;
                M1 = c13;
                d0 = c15;
                d1 = charAt(this.f43bp + 9);
            } else {
                M0 = c11;
                M1 = c12;
                d0 = c13;
                d1 = c14;
            }
            if (!checkDate(c6, c7, c8, c9, M0, M1, d0, d1)) {
                return false;
            }
            setCalendar(c6, c7, c8, c9, M0, M1, d0, d1);
            if (rest != 8) {
                char c16 = charAt(this.f43bp + 9);
                char c17 = charAt(this.f43bp + 10);
                char c18 = charAt(this.f43bp + 11);
                char c19 = charAt(this.f43bp + 12);
                char c110 = charAt(this.f43bp + 13);
                if ((sperate17 && c17 == 'T' && c110 == ':' && charAt(this.f43bp + 16) == 'Z') || (sperate16 && ((c17 == ' ' || c17 == 'T') && c110 == ':'))) {
                    h0 = c18;
                    h1 = c19;
                    m0 = charAt(this.f43bp + 14);
                    m1 = charAt(this.f43bp + 15);
                    s0 = '0';
                    s1 = '0';
                } else {
                    h0 = c15;
                    h1 = c16;
                    m0 = c17;
                    m1 = c18;
                    s0 = c19;
                    s1 = c110;
                }
                if (!checkTime(h0, h1, m0, m1, s0, s1)) {
                    return false;
                }
                if (rest == 17 && !sperate17) {
                    char S0 = charAt(this.f43bp + 14);
                    char S1 = charAt(this.f43bp + 15);
                    char S2 = charAt(this.f43bp + 16);
                    if (S0 < '0' || S0 > '9' || S1 < '0' || S1 > '9' || S2 < '0' || S2 > '9') {
                        return false;
                    }
                    millis = ((S0 - '0') * 100) + ((S1 - '0') * 10) + (S2 - '0');
                } else {
                    millis = 0;
                }
                hour = ((h0 - '0') * 10) + (h1 - '0');
                minute = ((m0 - '0') * 10) + (m1 - '0');
                seconds = ((s0 - '0') * 10) + (s1 - '0');
            } else {
                hour = 0;
                minute = 0;
                seconds = 0;
                millis = 0;
            }
            this.calendar.set(11, hour);
            this.calendar.set(12, minute);
            this.calendar.set(13, seconds);
            this.calendar.set(14, millis);
            this.token = 5;
            return true;
        }
        if (rest < 9) {
            return false;
        }
        char c20 = charAt(this.f43bp);
        char c21 = charAt(this.f43bp + 1);
        char c22 = charAt(this.f43bp + 2);
        char c23 = charAt(this.f43bp + 3);
        char c24 = charAt(this.f43bp + 4);
        char c25 = charAt(this.f43bp + 5);
        char c26 = charAt(this.f43bp + 6);
        char c27 = charAt(this.f43bp + 7);
        char c28 = charAt(this.f43bp + 8);
        char c29 = charAt(this.f43bp + 9);
        int date_len = 10;
        if ((c24 == '-' && c27 == '-') || (c24 == '/' && c27 == '/')) {
            y0 = c20;
            y1 = c21;
            y2 = c22;
            y3 = c23;
            M2 = c25;
            M3 = c26;
            d2 = c28;
            d3 = c29;
        } else if (c24 == '-' && c26 == '-') {
            y0 = c20;
            y1 = c21;
            y2 = c22;
            y3 = c23;
            M2 = '0';
            M3 = c25;
            if (c28 == ' ') {
                d2 = '0';
                d3 = c27;
                date_len = 8;
            } else {
                d2 = c27;
                d3 = c28;
                date_len = 9;
            }
        } else if ((c22 == '.' && c25 == '.') || (c22 == '-' && c25 == '-')) {
            d2 = c20;
            d3 = c21;
            M2 = c23;
            M3 = c24;
            y0 = c26;
            y1 = c27;
            y2 = c28;
            y3 = c29;
        } else if (c24 == 24180 || c24 == 45380) {
            y0 = c20;
            y1 = c21;
            y2 = c22;
            y3 = c23;
            if (c27 == 26376 || c27 == 50900) {
                M2 = c25;
                M3 = c26;
                if (c29 == 26085 || c29 == 51068) {
                    d2 = '0';
                    d3 = c28;
                } else if (charAt(this.f43bp + 10) == 26085 || charAt(this.f43bp + 10) == 51068) {
                    d2 = c28;
                    d3 = c29;
                    date_len = 11;
                } else {
                    return false;
                }
            } else if (c26 == 26376 || c26 == 50900) {
                M2 = '0';
                M3 = c25;
                if (c28 == 26085 || c28 == 51068) {
                    d2 = '0';
                    d3 = c27;
                } else if (c29 == 26085 || c29 == 51068) {
                    d2 = c27;
                    d3 = c28;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
        if (!checkDate(y0, y1, y2, y3, M2, M3, d2, d3)) {
            return false;
        }
        setCalendar(y0, y1, y2, y3, M2, M3, d2, d3);
        char t = charAt(this.f43bp + date_len);
        if (t == 'T' || (t == ' ' && !strict)) {
            if (rest < date_len + 9 || charAt(this.f43bp + date_len + 3) != ':' || charAt(this.f43bp + date_len + 6) != ':') {
                return false;
            }
            char h2 = charAt(this.f43bp + date_len + 1);
            char h3 = charAt(this.f43bp + date_len + 2);
            char m2 = charAt(this.f43bp + date_len + 4);
            char m3 = charAt(this.f43bp + date_len + 5);
            char s2 = charAt(this.f43bp + date_len + 7);
            char s3 = charAt(this.f43bp + date_len + 8);
            if (!checkTime(h2, h3, m2, m3, s2, s3)) {
                return false;
            }
            setTime(h2, h3, m2, m3, s2, s3);
            char dot = charAt(this.f43bp + date_len + 9);
            if (dot == '.') {
                if (rest < date_len + 11) {
                    return false;
                }
                char S3 = charAt(this.f43bp + date_len + 10);
                if (S3 < '0' || S3 > '9') {
                    return false;
                }
                int millis3 = S3 - '0';
                int millisLen = 1;
                if (rest > date_len + 11) {
                    char S4 = charAt(this.f43bp + date_len + 11);
                    if (S4 >= '0' && S4 <= '9') {
                        millis3 = (millis3 * 10) + (S4 - '0');
                        millisLen = 2;
                    }
                }
                if (millisLen == 2) {
                    char S5 = charAt(this.f43bp + date_len + 12);
                    if (S5 >= '0' && S5 <= '9') {
                        millis3 = (millis3 * 10) + (S5 - '0');
                        millisLen = 3;
                    }
                }
                this.calendar.set(14, millis3);
                int timzeZoneLength = 0;
                char timeZoneFlag = charAt(this.f43bp + date_len + 10 + millisLen);
                if (timeZoneFlag == '+' || timeZoneFlag == '-') {
                    char t0 = charAt(this.f43bp + date_len + 10 + millisLen + 1);
                    if (t0 < '0' || t0 > '1' || (t1 = charAt(this.f43bp + date_len + 10 + millisLen + 2)) < '0' || t1 > '9') {
                        return false;
                    }
                    char t2 = charAt(this.f43bp + date_len + 10 + millisLen + 3);
                    if (t2 == ':') {
                        char t3 = charAt(this.f43bp + date_len + 10 + millisLen + 4);
                        if (t3 != '0') {
                            return false;
                        }
                        char t4 = charAt(this.f43bp + date_len + 10 + millisLen + 5);
                        if (t4 != '0') {
                            return false;
                        }
                        timzeZoneLength = 6;
                    } else if (t2 == '0') {
                        char t5 = charAt(this.f43bp + date_len + 10 + millisLen + 4);
                        if (t5 != '0') {
                            return false;
                        }
                        timzeZoneLength = 5;
                    } else {
                        timzeZoneLength = 3;
                    }
                    setTimeZone(timeZoneFlag, t0, t1);
                } else if (timeZoneFlag == 'Z') {
                    timzeZoneLength = 1;
                    if (this.calendar.getTimeZone().getRawOffset() != 0) {
                        String[] timeZoneIDs = TimeZone.getAvailableIDs(0);
                        if (timeZoneIDs.length > 0) {
                            TimeZone timeZone = TimeZone.getTimeZone(timeZoneIDs[0]);
                            this.calendar.setTimeZone(timeZone);
                        }
                    }
                }
                char end = charAt(this.f43bp + date_len + 10 + millisLen + timzeZoneLength);
                if (end != 26 && end != '\"') {
                    return false;
                }
                int i2 = this.f43bp + date_len + 10 + millisLen + timzeZoneLength;
                this.f43bp = i2;
                this.f44ch = charAt(i2);
                this.token = 5;
                return true;
            }
            this.calendar.set(14, 0);
            int i3 = this.f43bp + date_len + 9;
            this.f43bp = i3;
            this.f44ch = charAt(i3);
            this.token = 5;
            if (dot == 'Z' && this.calendar.getTimeZone().getRawOffset() != 0) {
                String[] timeZoneIDs2 = TimeZone.getAvailableIDs(0);
                if (timeZoneIDs2.length > 0) {
                    TimeZone timeZone2 = TimeZone.getTimeZone(timeZoneIDs2[0]);
                    this.calendar.setTimeZone(timeZone2);
                }
            }
            return true;
        }
        if (t == '\"' || t == 26 || t == 26085 || t == 51068) {
            this.calendar.set(11, 0);
            this.calendar.set(12, 0);
            this.calendar.set(13, 0);
            this.calendar.set(14, 0);
            int i4 = this.f43bp + date_len;
            this.f43bp = i4;
            this.f44ch = charAt(i4);
            this.token = 5;
            return true;
        }
        if ((t != '+' && t != '-') || this.len != date_len + 6 || charAt(this.f43bp + date_len + 3) != ':' || charAt(this.f43bp + date_len + 4) != '0' || charAt(this.f43bp + date_len + 5) != '0') {
            return false;
        }
        setTime('0', '0', '0', '0', '0', '0');
        this.calendar.set(14, 0);
        setTimeZone(t, charAt(this.f43bp + date_len + 1), charAt(this.f43bp + date_len + 2));
        return true;
    }

    protected void setTime(char h0, char h1, char m0, char m1, char s0, char s1) {
        int hour = ((h0 - '0') * 10) + (h1 - '0');
        int minute = ((m0 - '0') * 10) + (m1 - '0');
        int seconds = ((s0 - '0') * 10) + (s1 - '0');
        this.calendar.set(11, hour);
        this.calendar.set(12, minute);
        this.calendar.set(13, seconds);
    }

    protected void setTimeZone(char timeZoneFlag, char t0, char t1) {
        int timeZoneOffset = (((t0 - '0') * 10) + (t1 - '0')) * 3600 * 1000;
        if (timeZoneFlag == '-') {
            timeZoneOffset = -timeZoneOffset;
        }
        if (this.calendar.getTimeZone().getRawOffset() != timeZoneOffset) {
            String[] timeZoneIDs = TimeZone.getAvailableIDs(timeZoneOffset);
            if (timeZoneIDs.length > 0) {
                TimeZone timeZone = TimeZone.getTimeZone(timeZoneIDs[0]);
                this.calendar.setTimeZone(timeZone);
            }
        }
    }

    static boolean checkTime(char h0, char h1, char m0, char m1, char s0, char s1) {
        if (h0 == '0') {
            if (h1 < '0' || h1 > '9') {
                return false;
            }
        } else if (h0 == '1') {
            if (h1 < '0' || h1 > '9') {
                return false;
            }
        } else if (h0 != '2' || h1 < '0' || h1 > '4') {
            return false;
        }
        if (m0 >= '0' && m0 <= '5') {
            if (m1 < '0' || m1 > '9') {
                return false;
            }
        } else if (m0 != '6' || m1 != '0') {
            return false;
        }
        if (s0 >= '0' && s0 <= '5') {
            if (s1 < '0' || s1 > '9') {
                return false;
            }
        } else if (s0 != '6' || s1 != '0') {
            return false;
        }
        return true;
    }

    private void setCalendar(char y0, char y1, char y2, char y3, char M0, char M1, char d0, char d1) {
        this.calendar = Calendar.getInstance(this.timeZone, this.locale);
        int year = ((y0 - '0') * 1000) + ((y1 - '0') * 100) + ((y2 - '0') * 10) + (y3 - '0');
        int month = (((M0 - '0') * 10) + (M1 - '0')) - 1;
        int day = ((d0 - '0') * 10) + (d1 - '0');
        this.calendar.set(1, year);
        this.calendar.set(2, month);
        this.calendar.set(5, day);
    }

    static boolean checkDate(char y0, char y1, char y2, char y3, char M0, char M1, int d0, int d1) {
        if (y0 < '1' || y0 > '3' || y1 < '0' || y1 > '9' || y2 < '0' || y2 > '9' || y3 < '0' || y3 > '9') {
            return false;
        }
        if (M0 == '0') {
            if (M1 < '1' || M1 > '9') {
                return false;
            }
        } else {
            if (M0 != '1') {
                return false;
            }
            if (M1 != '0' && M1 != '1' && M1 != '2') {
                return false;
            }
        }
        if (d0 == 48) {
            if (d1 < 49 || d1 > 57) {
                return false;
            }
        } else if (d0 == 49 || d0 == 50) {
            if (d1 < 48 || d1 > 57) {
                return false;
            }
        } else {
            if (d0 != 51) {
                return false;
            }
            if (d1 != 48 && d1 != 49) {
                return false;
            }
        }
        return true;
    }

    public static final byte[] decodeFast(String chars, int offset, int charsLen) {
        int pad;
        int sepCnt;
        int sIx;
        if (charsLen == 0) {
            return new byte[0];
        }
        int sIx2 = offset;
        int eIx = (offset + charsLen) - 1;
        while (sIx2 < eIx && f41IA[chars.charAt(sIx2)] < 0) {
            sIx2++;
        }
        while (eIx > 0 && f41IA[chars.charAt(eIx)] < 0) {
            eIx--;
        }
        if (chars.charAt(eIx) == '=') {
            pad = chars.charAt(eIx + (-1)) == '=' ? 2 : 1;
        } else {
            pad = 0;
        }
        int cCnt = (eIx - sIx2) + 1;
        if (charsLen > 76) {
            sepCnt = (chars.charAt(76) == '\r' ? cCnt / 78 : 0) << 1;
        } else {
            sepCnt = 0;
        }
        int len = (((cCnt - sepCnt) * 6) >> 3) - pad;
        byte[] bytes = new byte[len];
        int cc = 0;
        int eLen = (len / 3) * 3;
        int d = 0;
        int sIx3 = sIx2;
        while (d < eLen) {
            int sIx4 = sIx3 + 1;
            int i = f41IA[chars.charAt(sIx3)] << 18;
            int sIx5 = sIx4 + 1;
            int i2 = i | (f41IA[chars.charAt(sIx4)] << 12);
            int sIx6 = sIx5 + 1;
            int i3 = i2 | (f41IA[chars.charAt(sIx5)] << 6);
            int sIx7 = sIx6 + 1;
            int i4 = i3 | f41IA[chars.charAt(sIx6)];
            int d2 = d + 1;
            bytes[d] = (byte) (i4 >> 16);
            int d3 = d2 + 1;
            bytes[d2] = (byte) (i4 >> 8);
            int d4 = d3 + 1;
            bytes[d3] = (byte) i4;
            if (sepCnt <= 0 || (cc = cc + 1) != 19) {
                sIx = sIx7;
            } else {
                sIx = sIx7 + 2;
                cc = 0;
            }
            d = d4;
            sIx3 = sIx;
        }
        if (d < len) {
            int i5 = 0;
            int j = 0;
            while (sIx3 <= eIx - pad) {
                i5 |= f41IA[chars.charAt(sIx3)] << (18 - (j * 6));
                j++;
                sIx3++;
            }
            int r = 16;
            while (d < len) {
                bytes[d] = (byte) (i5 >> r);
                r -= 8;
                d++;
            }
        }
        return bytes;
    }
}
