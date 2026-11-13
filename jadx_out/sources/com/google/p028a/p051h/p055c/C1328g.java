package com.google.p028a.p051h.p055c;

import com.alibaba.fastjson.parser.JSONLexer;
import com.google.p028a.C1368w;
import com.google.p028a.p035c.EnumC1201d;
import com.umeng.commonsdk.proguard.C1770bg;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/* JADX INFO: renamed from: com.google.a.h.c.g */
/* JADX INFO: compiled from: PDF417HighLevelEncoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1328g {

    /* JADX INFO: renamed from: a */
    private static final int f2687a = 0;

    /* JADX INFO: renamed from: b */
    private static final int f2688b = 1;

    /* JADX INFO: renamed from: c */
    private static final int f2689c = 2;

    /* JADX INFO: renamed from: d */
    private static final int f2690d = 0;

    /* JADX INFO: renamed from: e */
    private static final int f2691e = 1;

    /* JADX INFO: renamed from: f */
    private static final int f2692f = 2;

    /* JADX INFO: renamed from: g */
    private static final int f2693g = 3;

    /* JADX INFO: renamed from: h */
    private static final int f2694h = 900;

    /* JADX INFO: renamed from: i */
    private static final int f2695i = 901;

    /* JADX INFO: renamed from: j */
    private static final int f2696j = 902;

    /* JADX INFO: renamed from: k */
    private static final int f2697k = 913;

    /* JADX INFO: renamed from: l */
    private static final int f2698l = 924;

    /* JADX INFO: renamed from: m */
    private static final int f2699m = 925;

    /* JADX INFO: renamed from: n */
    private static final int f2700n = 926;

    /* JADX INFO: renamed from: o */
    private static final int f2701o = 927;

    /* JADX INFO: renamed from: p */
    private static final byte[] f2702p = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 38, C1770bg.f4123k, 9, 44, 58, 35, 45, 46, 36, 47, 43, 37, 42, 61, 94, 0, 32, 0, 0, 0};

    /* JADX INFO: renamed from: q */
    private static final byte[] f2703q = {59, 60, 62, 64, 91, 92, 93, 95, 96, 126, 33, C1770bg.f4123k, 9, 44, 58, 10, 45, 46, 36, 47, 34, 124, 42, 40, 41, 63, 123, 125, 39, 0};

    /* JADX INFO: renamed from: r */
    private static final byte[] f2704r = new byte[128];

    /* JADX INFO: renamed from: s */
    private static final byte[] f2705s = new byte[128];

    /* JADX INFO: renamed from: t */
    private static final Charset f2706t = StandardCharsets.ISO_8859_1;

    /* JADX INFO: renamed from: a */
    private static boolean m2412a(char c) {
        return c >= '0' && c <= '9';
    }

    /* JADX INFO: renamed from: b */
    private static boolean m2414b(char c) {
        if (c != ' ') {
            return c >= 'A' && c <= 'Z';
        }
        return true;
    }

    /* JADX INFO: renamed from: c */
    private static boolean m2415c(char c) {
        if (c != ' ') {
            return c >= 'a' && c <= 'z';
        }
        return true;
    }

    /* JADX INFO: renamed from: f */
    private static boolean m2418f(char c) {
        if (c == '\t' || c == '\n' || c == '\r') {
            return true;
        }
        return c >= ' ' && c <= '~';
    }

    static {
        Arrays.fill(f2704r, (byte) -1);
        for (int i = 0; i < f2702p.length; i++) {
            byte b = f2702p[i];
            if (b > 0) {
                f2704r[b] = (byte) i;
            }
        }
        Arrays.fill(f2705s, (byte) -1);
        for (int i2 = 0; i2 < f2703q.length; i2++) {
            byte b2 = f2703q[i2];
            if (b2 > 0) {
                f2705s[b2] = (byte) i2;
            }
        }
    }

    private C1328g() {
    }

    /* JADX INFO: renamed from: a */
    public static String m2408a(String str, EnumC1324c enumC1324c, Charset charset) throws C1368w {
        EnumC1201d enumC1201dM1828a;
        int iM2405a;
        StringBuilder sb = new StringBuilder(str.length());
        if (charset == null) {
            charset = f2706t;
        } else if (!f2706t.equals(charset) && (enumC1201dM1828a = EnumC1201d.m1828a(charset.name())) != null) {
            int i = enumC1201dM1828a.f2134B[0];
            if (i >= 0 && i < f2694h) {
                sb.append((char) 927);
                sb.append((char) i);
            } else if (i < 810900) {
                sb.append((char) 926);
                sb.append((char) ((i / f2694h) - 1));
                sb.append((char) (i % f2694h));
            } else if (i < 811800) {
                sb.append((char) 925);
                sb.append((char) (810900 - i));
            } else {
                throw new C1368w("ECI number not in valid range from 0..811799, but was ".concat(String.valueOf(i)));
            }
        }
        int length = str.length();
        switch (enumC1324c) {
            case TEXT:
                m2406a(str, 0, length, sb, 0);
                break;
            case BYTE:
                byte[] bytes = str.getBytes(charset);
                m2411a(bytes, bytes.length, 1, sb);
                break;
            case NUMERIC:
                sb.append((char) 902);
                m2410a(str, 0, length, sb);
                break;
            default:
                int i2 = 0;
                int i3 = 0;
                while (true) {
                    int iM2406a = 0;
                    while (true) {
                        if (i2 < length) {
                            iM2405a = m2405a(str, i2);
                            if (iM2405a >= 13) {
                            }
                            int iM2413b = m2413b(str, i2);
                            if (iM2413b >= 5 || iM2405a == length) {
                                if (i3 != 0) {
                                    sb.append((char) 900);
                                    i3 = 0;
                                    iM2406a = 0;
                                }
                                iM2406a = m2406a(str, i2, iM2413b, sb, iM2406a);
                                i2 += iM2413b;
                            } else {
                                int iM2407a = m2407a(str, i2, charset);
                                if (iM2407a == 0) {
                                    iM2407a = 1;
                                }
                                int i4 = iM2407a + i2;
                                byte[] bytes2 = str.substring(i2, i4).getBytes(charset);
                                if (bytes2.length == 1 && i3 == 0) {
                                    m2411a(bytes2, 1, 0, sb);
                                } else {
                                    m2411a(bytes2, bytes2.length, i3, sb);
                                    i3 = 1;
                                    iM2406a = 0;
                                }
                                i2 = i4;
                            }
                        }
                        break;
                    }
                    sb.append((char) 902);
                    i3 = 2;
                    m2410a(str, i2, iM2405a, sb);
                    i2 += iM2405a;
                }
                break;
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: a */
    private static int m2406a(CharSequence charSequence, int i, int i2, StringBuilder sb, int i3) {
        StringBuilder sb2 = new StringBuilder(i2);
        int i4 = i3;
        int i5 = 0;
        while (true) {
            int i6 = i + i5;
            char cCharAt = charSequence.charAt(i6);
            switch (i4) {
                case 0:
                    if (m2414b(cCharAt)) {
                        if (cCharAt == ' ') {
                            sb2.append(JSONLexer.EOI);
                        } else {
                            sb2.append((char) (cCharAt - 'A'));
                        }
                    } else if (m2415c(cCharAt)) {
                        sb2.append((char) 27);
                        i4 = 1;
                    } else if (m2416d(cCharAt)) {
                        sb2.append((char) 28);
                        i4 = 2;
                    } else {
                        sb2.append((char) 29);
                        sb2.append((char) f2705s[cCharAt]);
                    }
                    break;
                case 1:
                    if (m2415c(cCharAt)) {
                        if (cCharAt == ' ') {
                            sb2.append(JSONLexer.EOI);
                        } else {
                            sb2.append((char) (cCharAt - 'a'));
                        }
                    } else if (m2414b(cCharAt)) {
                        sb2.append((char) 27);
                        sb2.append((char) (cCharAt - 'A'));
                    } else if (m2416d(cCharAt)) {
                        sb2.append((char) 28);
                        i4 = 2;
                    } else {
                        sb2.append((char) 29);
                        sb2.append((char) f2705s[cCharAt]);
                    }
                    break;
                case 2:
                    if (m2416d(cCharAt)) {
                        sb2.append((char) f2704r[cCharAt]);
                    } else if (m2414b(cCharAt)) {
                        sb2.append((char) 28);
                        i4 = 0;
                    } else if (m2415c(cCharAt)) {
                        sb2.append((char) 27);
                        i4 = 1;
                    } else {
                        int i7 = i6 + 1;
                        if (i7 < i2 && m2417e(charSequence.charAt(i7))) {
                            i4 = 3;
                            sb2.append((char) 25);
                        } else {
                            sb2.append((char) 29);
                            sb2.append((char) f2705s[cCharAt]);
                        }
                    }
                    break;
                default:
                    if (m2417e(cCharAt)) {
                        sb2.append((char) f2705s[cCharAt]);
                    } else {
                        sb2.append((char) 29);
                        i4 = 0;
                    }
                    break;
            }
            i5++;
            if (i5 >= i2) {
                int length = sb2.length();
                char cCharAt2 = 0;
                for (int i8 = 0; i8 < length; i8++) {
                    if (i8 % 2 != 0) {
                        cCharAt2 = (char) ((cCharAt2 * 30) + sb2.charAt(i8));
                        sb.append(cCharAt2);
                    } else {
                        cCharAt2 = sb2.charAt(i8);
                    }
                }
                if (length % 2 != 0) {
                    sb.append((char) ((cCharAt2 * 30) + 29));
                }
                return i4;
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m2411a(byte[] bArr, int i, int i2, StringBuilder sb) {
        int i3;
        if (i == 1 && i2 == 0) {
            sb.append((char) 913);
        } else if (i % 6 == 0) {
            sb.append((char) 924);
        } else {
            sb.append((char) 901);
        }
        if (i >= 6) {
            char[] cArr = new char[5];
            i3 = 0;
            while ((i + 0) - i3 >= 6) {
                long j = 0;
                int i4 = 0;
                while (i4 < 6) {
                    long j2 = (j << 8) + ((long) (bArr[i3 + i4] & 255));
                    i4++;
                    j = j2;
                }
                for (int i5 = 0; i5 < 5; i5++) {
                    cArr[i5] = (char) (j % 900);
                    j /= 900;
                }
                for (int i6 = 4; i6 >= 0; i6--) {
                    sb.append(cArr[i6]);
                }
                i3 += 6;
            }
        } else {
            i3 = 0;
        }
        while (i3 < i + 0) {
            sb.append((char) (bArr[i3] & 255));
            i3++;
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m2410a(String str, int i, int i2, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder((i2 / 3) + 1);
        BigInteger bigIntegerValueOf = BigInteger.valueOf(900L);
        BigInteger bigIntegerValueOf2 = BigInteger.valueOf(0L);
        int i3 = 0;
        while (i3 < i2) {
            sb2.setLength(0);
            int iMin = Math.min(44, i2 - i3);
            StringBuilder sb3 = new StringBuilder("1");
            int i4 = i + i3;
            sb3.append(str.substring(i4, i4 + iMin));
            BigInteger bigInteger = new BigInteger(sb3.toString());
            do {
                sb2.append((char) bigInteger.mod(bigIntegerValueOf).intValue());
                bigInteger = bigInteger.divide(bigIntegerValueOf);
            } while (!bigInteger.equals(bigIntegerValueOf2));
            for (int length = sb2.length() - 1; length >= 0; length--) {
                sb.append(sb2.charAt(length));
            }
            i3 += iMin;
        }
    }

    /* JADX INFO: renamed from: d */
    private static boolean m2416d(char c) {
        return f2704r[c] != -1;
    }

    /* JADX INFO: renamed from: e */
    private static boolean m2417e(char c) {
        return f2705s[c] != -1;
    }

    /* JADX INFO: renamed from: a */
    private static int m2405a(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        if (i < length) {
            char cCharAt = charSequence.charAt(i);
            while (m2412a(cCharAt) && i < length) {
                i2++;
                i++;
                if (i < length) {
                    cCharAt = charSequence.charAt(i);
                }
            }
        }
        return i2;
    }

    /* JADX INFO: renamed from: b */
    private static int m2413b(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = i;
        while (i2 < length) {
            char cCharAt = charSequence.charAt(i2);
            boolean z = false;
            int i3 = i2;
            int i4 = 0;
            while (i4 < 13 && m2412a(cCharAt) && i3 < length) {
                i4++;
                i3++;
                if (i3 < length) {
                    cCharAt = charSequence.charAt(i3);
                }
            }
            if (i4 >= 13) {
                return (i3 - i) - i4;
            }
            if (i4 <= 0) {
                char cCharAt2 = charSequence.charAt(i3);
                if (cCharAt2 == '\t' || cCharAt2 == '\n' || cCharAt2 == '\r' || (cCharAt2 >= ' ' && cCharAt2 <= '~')) {
                    z = true;
                }
                if (!z) {
                    i2 = i3;
                    break;
                }
                i2 = i3 + 1;
            } else {
                i2 = i3;
            }
        }
        return i2 - i;
    }

    /* JADX INFO: renamed from: a */
    private static int m2407a(String str, int i, Charset charset) throws C1368w {
        int i2;
        CharsetEncoder charsetEncoderNewEncoder = charset.newEncoder();
        int length = str.length();
        int i3 = i;
        while (i3 < length) {
            char cCharAt = str.charAt(i3);
            int i4 = 0;
            while (i4 < 13 && m2412a(cCharAt) && (i2 = i3 + (i4 = i4 + 1)) < length) {
                cCharAt = str.charAt(i2);
            }
            if (i4 >= 13) {
                return i3 - i;
            }
            char cCharAt2 = str.charAt(i3);
            if (!charsetEncoderNewEncoder.canEncode(cCharAt2)) {
                throw new C1368w("Non-encodable character detected: " + cCharAt2 + " (Unicode: " + ((int) cCharAt2) + ')');
            }
            i3++;
        }
        return i3 - i;
    }

    /* JADX INFO: renamed from: a */
    private static void m2409a(int i, StringBuilder sb) throws C1368w {
        if (i >= 0 && i < f2694h) {
            sb.append((char) 927);
            sb.append((char) i);
        } else if (i < 810900) {
            sb.append((char) 926);
            sb.append((char) ((i / f2694h) - 1));
            sb.append((char) (i % f2694h));
        } else {
            if (i < 811800) {
                sb.append((char) 925);
                sb.append((char) (810900 - i));
                return;
            }
            throw new C1368w("ECI number not in valid range from 0..811799, but was ".concat(String.valueOf(i)));
        }
    }
}
