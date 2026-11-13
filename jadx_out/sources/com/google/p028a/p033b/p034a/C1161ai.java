package com.google.p028a.p033b.p034a;

import com.google.p028a.C1363r;
import com.google.p028a.EnumC1138a;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: com.google.a.b.a.ai */
/* JADX INFO: compiled from: VINResultParser.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1161ai extends AbstractC1183u {

    /* JADX INFO: renamed from: a */
    private static final Pattern f1964a = Pattern.compile("[IOQ]");

    /* JADX INFO: renamed from: b */
    private static final Pattern f1965b = Pattern.compile("[A-Z0-9]{17}");

    @Override // com.google.p028a.p033b.p034a.AbstractC1183u
    /* JADX INFO: renamed from: a */
    public final /* synthetic */ AbstractC1179q mo1568a(C1363r c1363r) {
        return m1610c(c1363r);
    }

    /* JADX INFO: renamed from: a */
    private static boolean m1605a(CharSequence charSequence) {
        char c;
        int i;
        int i2;
        int i3 = 0;
        int i4 = 0;
        while (i3 < charSequence.length()) {
            int i5 = i3 + 1;
            if (i5 > 0 && i5 <= 7) {
                i = 9 - i5;
            } else if (i5 == 8) {
                i = 10;
            } else if (i5 == 9) {
                i = 0;
            } else {
                if (i5 < 10 || i5 > 17) {
                    throw new IllegalArgumentException();
                }
                i = 19 - i5;
            }
            char cCharAt = charSequence.charAt(i3);
            if (cCharAt >= 'A' && cCharAt <= 'I') {
                i2 = (cCharAt - 'A') + 1;
            } else if (cCharAt >= 'J' && cCharAt <= 'R') {
                i2 = (cCharAt - 'J') + 1;
            } else if (cCharAt >= 'S' && cCharAt <= 'Z') {
                i2 = (cCharAt - 'S') + 2;
            } else {
                if (cCharAt < '0' || cCharAt > '9') {
                    throw new IllegalArgumentException();
                }
                i2 = cCharAt - '0';
            }
            i4 += i * i2;
            i3 = i5;
        }
        char cCharAt2 = charSequence.charAt(8);
        int i6 = i4 % 11;
        if (i6 < 10) {
            c = (char) (i6 + 48);
        } else {
            if (i6 != 10) {
                throw new IllegalArgumentException();
            }
            c = 'X';
        }
        return cCharAt2 == c;
    }

    /* JADX INFO: renamed from: b */
    private static int m1607b(char c) {
        if (c >= 'A' && c <= 'I') {
            return (c - 'A') + 1;
        }
        if (c >= 'J' && c <= 'R') {
            return (c - 'J') + 1;
        }
        if (c >= 'S' && c <= 'Z') {
            return (c - 'S') + 2;
        }
        if (c < '0' || c > '9') {
            throw new IllegalArgumentException();
        }
        return c - '0';
    }

    /* JADX INFO: renamed from: a */
    private static int m1604a(int i) {
        if (i > 0 && i <= 7) {
            return 9 - i;
        }
        if (i == 8) {
            return 10;
        }
        if (i == 9) {
            return 0;
        }
        if (i < 10 || i > 17) {
            throw new IllegalArgumentException();
        }
        return 19 - i;
    }

    /* JADX INFO: renamed from: b */
    private static char m1606b(int i) {
        if (i < 10) {
            return (char) (i + 48);
        }
        if (i == 10) {
            return 'X';
        }
        throw new IllegalArgumentException();
    }

    /* JADX INFO: renamed from: c */
    private static int m1609c(char c) {
        if (c >= 'E' && c <= 'H') {
            return (c - 'E') + 1984;
        }
        if (c >= 'J' && c <= 'N') {
            return (c - 'J') + 1988;
        }
        if (c == 'P') {
            return 1993;
        }
        if (c >= 'R' && c <= 'T') {
            return (c - 'R') + 1994;
        }
        if (c >= 'V' && c <= 'Y') {
            return (c - 'V') + 1997;
        }
        if (c >= '1' && c <= '9') {
            return (c - '1') + 2001;
        }
        if (c < 'A' || c > 'D') {
            throw new IllegalArgumentException();
        }
        return (c - 'A') + 2010;
    }

    /* JADX INFO: renamed from: b */
    private static String m1608b(CharSequence charSequence) {
        char cCharAt = charSequence.charAt(0);
        char cCharAt2 = charSequence.charAt(1);
        if (cCharAt == '9') {
            if (cCharAt2 >= 'A' && cCharAt2 <= 'E') {
                return "BR";
            }
            if (cCharAt2 < '3' || cCharAt2 > '9') {
                return null;
            }
            return "BR";
        }
        if (cCharAt == 'S') {
            if (cCharAt2 >= 'A' && cCharAt2 <= 'M') {
                return "UK";
            }
            if (cCharAt2 < 'N' || cCharAt2 > 'T') {
                return null;
            }
            return "DE";
        }
        if (cCharAt == 'Z') {
            if (cCharAt2 < 'A' || cCharAt2 > 'R') {
                return null;
            }
            return "IT";
        }
        switch (cCharAt) {
            case '1':
            case '4':
            case '5':
                return "US";
            case '2':
                return "CA";
            case '3':
                if (cCharAt2 < 'A' || cCharAt2 > 'W') {
                    return null;
                }
                return "MX";
            default:
                switch (cCharAt) {
                    case 'J':
                        if (cCharAt2 < 'A' || cCharAt2 > 'T') {
                            return null;
                        }
                        return "JP";
                    case 'K':
                        if (cCharAt2 < 'L' || cCharAt2 > 'R') {
                            return null;
                        }
                        return "KO";
                    case 'L':
                        return "CN";
                    case 'M':
                        if (cCharAt2 < 'A' || cCharAt2 > 'E') {
                            return null;
                        }
                        return "IN";
                    default:
                        switch (cCharAt) {
                            case 'V':
                                if (cCharAt2 >= 'F' && cCharAt2 <= 'R') {
                                    return "FR";
                                }
                                if (cCharAt2 < 'S' || cCharAt2 > 'W') {
                                    return null;
                                }
                                return "ES";
                            case 'W':
                                return "DE";
                            case 'X':
                                if (cCharAt2 == '0') {
                                    return "RU";
                                }
                                if (cCharAt2 < '3' || cCharAt2 > '9') {
                                    return null;
                                }
                                return "RU";
                            default:
                                return null;
                        }
                }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:112:0x0151  */
    /* JADX INFO: renamed from: c */
    private static C1160ah m1610c(C1363r c1363r) {
        char c;
        String str;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        if (c1363r.f2843e != EnumC1138a.CODE_39) {
            return null;
        }
        String strTrim = f1964a.matcher(c1363r.f2839a).replaceAll("").trim();
        if (!f1965b.matcher(strTrim).matches()) {
            return null;
        }
        int i6 = 0;
        int i7 = 0;
        while (i6 < strTrim.length()) {
            try {
                int i8 = i6 + 1;
                if (i8 > 0 && i8 <= 7) {
                    i4 = 9 - i8;
                } else if (i8 == 8) {
                    i4 = 10;
                } else if (i8 == 9) {
                    i4 = 0;
                } else {
                    if (i8 < 10 || i8 > 17) {
                        throw new IllegalArgumentException();
                    }
                    i4 = 19 - i8;
                }
                char cCharAt = strTrim.charAt(i6);
                if (cCharAt >= 'A' && cCharAt <= 'I') {
                    i5 = (cCharAt - 'A') + 1;
                } else if (cCharAt >= 'J' && cCharAt <= 'R') {
                    i5 = (cCharAt - 'J') + 1;
                } else if (cCharAt >= 'S' && cCharAt <= 'Z') {
                    i5 = (cCharAt - 'S') + 2;
                } else {
                    if (cCharAt < '0' || cCharAt > '9') {
                        throw new IllegalArgumentException();
                    }
                    i5 = cCharAt - '0';
                }
                i7 += i4 * i5;
                i6 = i8;
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }
        char cCharAt2 = strTrim.charAt(8);
        int i9 = i7 % 11;
        try {
            if (i9 < 10) {
                c = (char) (i9 + 48);
            } else {
                if (i9 != 10) {
                    throw new IllegalArgumentException();
                }
                c = 'X';
            }
            if (!(cCharAt2 == c)) {
                return null;
            }
            String strSubstring = strTrim.substring(0, 3);
            String strSubstring2 = strTrim.substring(3, 9);
            String strSubstring3 = strTrim.substring(9, 17);
            char cCharAt3 = strSubstring.charAt(0);
            char cCharAt4 = strSubstring.charAt(1);
            if (cCharAt3 != '9') {
                if (cCharAt3 != 'S') {
                    if (cCharAt3 != 'Z') {
                        switch (cCharAt3) {
                            case '1':
                            case '4':
                            case '5':
                                str = "US";
                                break;
                            case '2':
                                str = "CA";
                                break;
                            case '3':
                                if (cCharAt4 >= 'A' && cCharAt4 <= 'W') {
                                    str = "MX";
                                } else {
                                    str = null;
                                }
                                break;
                            default:
                                switch (cCharAt3) {
                                    case 'J':
                                        if (cCharAt4 >= 'A' && cCharAt4 <= 'T') {
                                            str = "JP";
                                        } else {
                                            str = null;
                                        }
                                        break;
                                    case 'K':
                                        if (cCharAt4 >= 'L' && cCharAt4 <= 'R') {
                                            str = "KO";
                                        } else {
                                            str = null;
                                        }
                                        break;
                                    case 'L':
                                        str = "CN";
                                        break;
                                    case 'M':
                                        if (cCharAt4 >= 'A' && cCharAt4 <= 'E') {
                                            str = "IN";
                                        } else {
                                            str = null;
                                        }
                                        break;
                                    default:
                                        switch (cCharAt3) {
                                            case 'V':
                                                if (cCharAt4 >= 'F' && cCharAt4 <= 'R') {
                                                    str = "FR";
                                                } else if (cCharAt4 >= 'S' && cCharAt4 <= 'W') {
                                                    str = "ES";
                                                } else {
                                                    str = null;
                                                }
                                                break;
                                            case 'W':
                                                str = "DE";
                                                break;
                                            case 'X':
                                                if (cCharAt4 != '0' && (cCharAt4 < '3' || cCharAt4 > '9')) {
                                                    str = null;
                                                } else {
                                                    str = "RU";
                                                }
                                                break;
                                            default:
                                                str = null;
                                                break;
                                        }
                                        break;
                                }
                                break;
                        }
                    } else if (cCharAt4 < 'A' || cCharAt4 > 'R') {
                        str = null;
                    } else {
                        str = "IT";
                    }
                } else if (cCharAt4 >= 'A' && cCharAt4 <= 'M') {
                    str = "UK";
                } else if (cCharAt4 < 'N' || cCharAt4 > 'T') {
                    str = null;
                } else {
                    str = "DE";
                }
            } else if ((cCharAt4 < 'A' || cCharAt4 > 'E') && (cCharAt4 < '3' || cCharAt4 > '9')) {
                str = null;
            } else {
                str = "BR";
            }
            String strSubstring4 = strTrim.substring(3, 8);
            char cCharAt5 = strTrim.charAt(9);
            if (cCharAt5 >= 'E' && cCharAt5 <= 'H') {
                i = (cCharAt5 - 'E') + 1984;
            } else {
                if (cCharAt5 < 'J' || cCharAt5 > 'N') {
                    if (cCharAt5 == 'P') {
                        i2 = 10;
                        i3 = 1993;
                    } else if (cCharAt5 >= 'R' && cCharAt5 <= 'T') {
                        i = (cCharAt5 - 'R') + 1994;
                    } else if (cCharAt5 >= 'V' && cCharAt5 <= 'Y') {
                        i = (cCharAt5 - 'V') + 1997;
                    } else if (cCharAt5 >= '1' && cCharAt5 <= '9') {
                        i = (cCharAt5 - '1') + 2001;
                    } else {
                        if (cCharAt5 < 'A' || cCharAt5 > 'D') {
                            throw new IllegalArgumentException();
                        }
                        i = (cCharAt5 - 'A') + 2010;
                    }
                    return new C1160ah(strTrim, strSubstring, strSubstring2, strSubstring3, str, strSubstring4, i3, strTrim.charAt(i2), strTrim.substring(11));
                }
                i = (cCharAt5 - 'J') + 1988;
            }
            i3 = i;
            i2 = 10;
            return new C1160ah(strTrim, strSubstring, strSubstring2, strSubstring3, str, strSubstring4, i3, strTrim.charAt(i2), strTrim.substring(11));
        } catch (IllegalArgumentException unused2) {
            return null;
        }
    }
}
