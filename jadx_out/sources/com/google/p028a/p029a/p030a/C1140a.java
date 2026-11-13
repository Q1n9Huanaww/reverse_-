package com.google.p028a.p029a.p030a;

import com.cyjh.mobileanjian.ipc.utils.C0816m;
import com.google.p028a.C1303h;
import com.google.p028a.p029a.C1139a;
import com.google.p028a.p035c.C1194b;
import com.google.p028a.p035c.C1202e;
import com.google.p028a.p035c.p037b.C1195a;
import com.google.p028a.p035c.p037b.C1197c;
import com.google.p028a.p035c.p037b.C1199e;
import com.googlecode.tesseract.android.TessBaseAPI;
import com.p024ds.daisi.mvp.managers.AccountManager;
import com.umeng.commonsdk.proguard.C1799v;
import java.util.Arrays;
import net.lingala.zip4j.util.InternalZipConstants;
import org.apache.commons.p092io.IOUtils;
import org.slf4j.Marker;

/* JADX INFO: renamed from: com.google.a.a.a.a */
/* JADX INFO: compiled from: Decoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1140a {

    /* JADX INFO: renamed from: a */
    private static final String[] f1882a = {"CTRL_PS", " ", "A", "B", "C", "D", "E", TessBaseAPI.f2988f, "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", TessBaseAPI.f2987e, "U", "V", "W", "X", "Y", "Z", "CTRL_LL", "CTRL_ML", "CTRL_DL", "CTRL_BS"};

    /* JADX INFO: renamed from: b */
    private static final String[] f1883b = {"CTRL_PS", " ", C1799v.f4296ah, "b", "c", C1799v.f4297ai, "e", "f", "g", "h", C1799v.f4301am, "j", "k", "l", C0816m.f1430a, "n", "o", C1799v.f4299ak, "q", "r", C1799v.f4300al, "t", "u", "v", "w", "x", "y", "z", "CTRL_US", "CTRL_ML", "CTRL_DL", "CTRL_BS"};

    /* JADX INFO: renamed from: c */
    private static final String[] f1884c = {"CTRL_PS", " ", "\u0001", "\u0002", "\u0003", "\u0004", "\u0005", "\u0006", "\u0007", "\b", "\t", "\n", "\u000b", "\f", "\r", "\u001b", "\u001c", "\u001d", "\u001e", "\u001f", "@", "\\", "^", "_", "`", "|", "~", "\u007f", "CTRL_LL", "CTRL_UL", "CTRL_PL", "CTRL_BS"};

    /* JADX INFO: renamed from: d */
    private static final String[] f1885d = {"", "\r", IOUtils.LINE_SEPARATOR_WINDOWS, ". ", ", ", ": ", "!", "\"", "#", "$", "%", "&", "'", "(", ")", Marker.ANY_MARKER, Marker.ANY_NON_NULL_MARKER, ",", "-", ".", InternalZipConstants.ZIP_FILE_SEPARATOR, ":", ";", "<", "=", ">", "?", "[", "]", "{", "}", "CTRL_UL"};

    /* JADX INFO: renamed from: e */
    private static final String[] f1886e = {"CTRL_PS", " ", AccountManager.TYPE_LOGIN, "1", "2", "3", AccountManager.TYPE_GET_PHONE, AccountManager.TYPE_ALTER_PASSWORD, AccountManager.TYPE_FIND_PASSWORD_CODE, AccountManager.TYPE_CHECKED_ACCOUNT_DUPLICATED, "8", "9", ",", ".", "CTRL_UL", "CTRL_US"};

    /* JADX INFO: renamed from: f */
    private C1139a f1887f;

    /* JADX INFO: renamed from: a */
    private static int m1479a(int i, boolean z) {
        return ((z ? 88 : 112) + (i << 4)) * i;
    }

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: renamed from: com.google.a.a.a.a$a */
    /* JADX INFO: compiled from: Decoder.java */
    private static final class a {

        /* JADX INFO: renamed from: a */
        public static final int f1889a = 1;

        /* JADX INFO: renamed from: b */
        public static final int f1890b = 2;

        /* JADX INFO: renamed from: c */
        public static final int f1891c = 3;

        /* JADX INFO: renamed from: d */
        public static final int f1892d = 4;

        /* JADX INFO: renamed from: e */
        public static final int f1893e = 5;

        /* JADX INFO: renamed from: f */
        public static final int f1894f = 6;

        /* JADX INFO: renamed from: g */
        private static final /* synthetic */ int[] f1895g = {f1889a, f1890b, f1891c, f1892d, f1893e, f1894f};

        private a(String str, int i) {
            super(str, i);
        }

        /* JADX INFO: renamed from: a */
        public static int[] m1488a() {
            return (int[]) f1895g.clone();
        }
    }

    /* JADX INFO: renamed from: a */
    public final C1202e m1487a(C1139a c1139a) throws C1303h {
        int iM1480a;
        this.f1887f = c1139a;
        C1194b c1194b = c1139a.f2146d;
        boolean z = this.f1887f.f1879a;
        int i = this.f1887f.f1881c;
        int i2 = (z ? 11 : 14) + (i << 2);
        int[] iArr = new int[i2];
        boolean[] zArr = new boolean[((z ? 88 : 112) + (i << 4)) * i];
        int i3 = 2;
        if (z) {
            for (int i4 = 0; i4 < iArr.length; i4++) {
                iArr[i4] = i4;
            }
        } else {
            int i5 = i2 / 2;
            int i6 = ((i2 + 1) + (((i5 - 1) / 15) * 2)) / 2;
            for (int i7 = 0; i7 < i5; i7++) {
                int i8 = (i7 / 15) + i7;
                iArr[(i5 - i7) - 1] = (i6 - i8) - 1;
                iArr[i5 + i7] = i8 + i6 + 1;
            }
        }
        int i9 = 0;
        int i10 = 0;
        while (i9 < i) {
            int i11 = ((i - i9) << i3) + (z ? 9 : 12);
            int i12 = i9 << 1;
            int i13 = (i2 - 1) - i12;
            int i14 = 0;
            while (i14 < i11) {
                int i15 = i14 << 1;
                int i16 = 0;
                while (i16 < i3) {
                    int i17 = i12 + i16;
                    int i18 = i12 + i14;
                    zArr[i10 + i15 + i16] = c1194b.m1789a(iArr[i17], iArr[i18]);
                    int i19 = i13 - i16;
                    zArr[(i11 * 2) + i10 + i15 + i16] = c1194b.m1789a(iArr[i18], iArr[i19]);
                    int i20 = i13 - i14;
                    zArr[(i11 * 4) + i10 + i15 + i16] = c1194b.m1789a(iArr[i19], iArr[i20]);
                    zArr[(i11 * 6) + i10 + i15 + i16] = c1194b.m1789a(iArr[i20], iArr[i17]);
                    i16++;
                    z = z;
                    i = i;
                    i2 = i2;
                    i3 = 2;
                }
                i14++;
                i3 = 2;
            }
            i10 += i11 << 3;
            i9++;
            z = z;
            i3 = 2;
        }
        boolean[] zArrM1485c = m1485c(zArr);
        byte[] bArr = new byte[(zArrM1485c.length + 7) / 8];
        for (int i21 = 0; i21 < bArr.length; i21++) {
            int i22 = i21 << 3;
            int length = zArrM1485c.length - i22;
            if (length >= 8) {
                iM1480a = m1480a(zArrM1485c, i22, 8);
            } else {
                iM1480a = m1480a(zArrM1485c, i22, length) << (8 - length);
            }
            bArr[i21] = (byte) iM1480a;
        }
        C1202e c1202e = new C1202e(bArr, m1484b(zArrM1485c), null, null);
        c1202e.f2137b = zArrM1485c.length;
        return c1202e;
    }

    /* JADX INFO: renamed from: a */
    private static String m1482a(boolean[] zArr) {
        return m1484b(zArr);
    }

    /* JADX INFO: renamed from: b */
    private static String m1484b(boolean[] zArr) {
        String str;
        int length = zArr.length;
        int i = a.f1889a;
        int i2 = a.f1889a;
        StringBuilder sb = new StringBuilder(20);
        int i3 = i;
        int i4 = 0;
        while (i4 < length) {
            if (i2 != a.f1894f) {
                int i5 = i2 == a.f1892d ? 4 : 5;
                if (length - i4 >= i5) {
                    int iM1480a = m1480a(zArr, i4, i5);
                    i4 += i5;
                    switch (AnonymousClass1.f1888a[i2 - 1]) {
                        case 1:
                            str = f1882a[iM1480a];
                            break;
                        case 2:
                            str = f1883b[iM1480a];
                            break;
                        case 3:
                            str = f1884c[iM1480a];
                            break;
                        case 4:
                            str = f1885d[iM1480a];
                            break;
                        case 5:
                            str = f1886e[iM1480a];
                            break;
                        default:
                            throw new IllegalStateException("Bad table");
                    }
                    if (str.startsWith("CTRL_")) {
                        char cCharAt = str.charAt(5);
                        if (cCharAt == 'B') {
                            i3 = a.f1894f;
                        } else if (cCharAt == 'D') {
                            i3 = a.f1892d;
                        } else if (cCharAt != 'P') {
                            switch (cCharAt) {
                                case 'L':
                                    i3 = a.f1890b;
                                    break;
                                case 'M':
                                    i3 = a.f1891c;
                                    break;
                                default:
                                    i3 = a.f1889a;
                                    break;
                            }
                        } else {
                            i3 = a.f1893e;
                        }
                        if (str.charAt(6) != 'L') {
                            int i6 = i3;
                            i3 = i2;
                            i2 = i6;
                        }
                    } else {
                        sb.append(str);
                    }
                    i2 = i3;
                } else {
                    return sb.toString();
                }
            } else {
                if (length - i4 >= 5) {
                    int iM1480a2 = m1480a(zArr, i4, 5);
                    int i7 = i4 + 5;
                    if (iM1480a2 == 0) {
                        if (length - i7 >= 11) {
                            iM1480a2 = m1480a(zArr, i7, 11) + 31;
                            i7 += 11;
                        }
                    }
                    int i8 = i7;
                    int i9 = 0;
                    while (true) {
                        if (i9 >= iM1480a2) {
                            i4 = i8;
                        } else if (length - i8 < 8) {
                            i4 = length;
                        } else {
                            sb.append((char) m1480a(zArr, i8, 8));
                            i8 += 8;
                            i9++;
                        }
                    }
                    i2 = i3;
                }
                return sb.toString();
            }
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: a */
    private static int m1478a(char c) {
        if (c == 'B') {
            return a.f1894f;
        }
        if (c == 'D') {
            return a.f1892d;
        }
        if (c != 'P') {
            switch (c) {
                case 'L':
                    return a.f1890b;
                case 'M':
                    return a.f1891c;
                default:
                    return a.f1889a;
            }
        }
        return a.f1893e;
    }

    /* JADX INFO: renamed from: com.google.a.a.a.a$1, reason: invalid class name */
    /* JADX INFO: compiled from: Decoder.java */
    static /* synthetic */ class AnonymousClass1 {

        /* JADX INFO: renamed from: a */
        static final /* synthetic */ int[] f1888a = new int[a.m1488a().length];

        static {
            try {
                f1888a[a.f1889a - 1] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1888a[a.f1890b - 1] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1888a[a.f1891c - 1] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1888a[a.f1893e - 1] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1888a[a.f1892d - 1] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private static String m1481a(int i, int i2) {
        switch (AnonymousClass1.f1888a[i - 1]) {
            case 1:
                return f1882a[i2];
            case 2:
                return f1883b[i2];
            case 3:
                return f1884c[i2];
            case 4:
                return f1885d[i2];
            case 5:
                return f1886e[i2];
            default:
                throw new IllegalStateException("Bad table");
        }
    }

    /* JADX INFO: renamed from: c */
    private boolean[] m1485c(boolean[] zArr) throws C1303h {
        C1195a c1195a;
        int i = 8;
        if (this.f1887f.f1881c <= 2) {
            i = 6;
            c1195a = C1195a.f2083c;
        } else if (this.f1887f.f1881c <= 8) {
            c1195a = C1195a.f2087g;
        } else if (this.f1887f.f1881c <= 22) {
            i = 10;
            c1195a = C1195a.f2082b;
        } else {
            i = 12;
            c1195a = C1195a.f2081a;
        }
        int i2 = this.f1887f.f1880b;
        int length = zArr.length / i;
        if (length < i2) {
            throw C1303h.m2217a();
        }
        int[] iArr = new int[length];
        int length2 = zArr.length % i;
        int i3 = 0;
        while (i3 < length) {
            iArr[i3] = m1480a(zArr, length2, i);
            i3++;
            length2 += i;
        }
        try {
            new C1197c(c1195a).m1819a(iArr, length - i2);
            int i4 = (1 << i) - 1;
            int i5 = 0;
            for (int i6 = 0; i6 < i2; i6++) {
                int i7 = iArr[i6];
                if (i7 == 0 || i7 == i4) {
                    throw C1303h.m2217a();
                }
                if (i7 == 1 || i7 == i4 - 1) {
                    i5++;
                }
            }
            boolean[] zArr2 = new boolean[(i2 * i) - i5];
            int i8 = 0;
            for (int i9 = 0; i9 < i2; i9++) {
                int i10 = iArr[i9];
                if (i10 == 1 || i10 == i4 - 1) {
                    Arrays.fill(zArr2, i8, (i8 + i) - 1, i10 > 1);
                    i8 += i - 1;
                } else {
                    int i11 = i - 1;
                    while (i11 >= 0) {
                        int i12 = i8 + 1;
                        zArr2[i8] = ((1 << i11) & i10) != 0;
                        i11--;
                        i8 = i12;
                    }
                }
            }
            return zArr2;
        } catch (C1199e e) {
            throw C1303h.m2218a(e);
        }
    }

    /* JADX INFO: renamed from: a */
    private boolean[] m1483a(C1194b c1194b) {
        boolean z = this.f1887f.f1879a;
        int i = this.f1887f.f1881c;
        int i2 = (z ? 11 : 14) + (i << 2);
        int[] iArr = new int[i2];
        boolean[] zArr = new boolean[((z ? 88 : 112) + (i << 4)) * i];
        int i3 = 2;
        if (z) {
            for (int i4 = 0; i4 < iArr.length; i4++) {
                iArr[i4] = i4;
            }
        } else {
            int i5 = i2 / 2;
            int i6 = ((i2 + 1) + (((i5 - 1) / 15) * 2)) / 2;
            for (int i7 = 0; i7 < i5; i7++) {
                int i8 = (i7 / 15) + i7;
                iArr[(i5 - i7) - 1] = (i6 - i8) - 1;
                iArr[i5 + i7] = i8 + i6 + 1;
            }
        }
        int i9 = 0;
        int i10 = 0;
        while (i9 < i) {
            int i11 = ((i - i9) << i3) + (z ? 9 : 12);
            int i12 = i9 << 1;
            int i13 = (i2 - 1) - i12;
            int i14 = 0;
            while (i14 < i11) {
                int i15 = i14 << 1;
                int i16 = 0;
                while (i16 < i3) {
                    int i17 = i12 + i16;
                    int i18 = i12 + i14;
                    zArr[i10 + i15 + i16] = c1194b.m1789a(iArr[i17], iArr[i18]);
                    int i19 = i13 - i16;
                    zArr[(i11 * 2) + i10 + i15 + i16] = c1194b.m1789a(iArr[i18], iArr[i19]);
                    int i20 = i13 - i14;
                    zArr[(i11 * 4) + i10 + i15 + i16] = c1194b.m1789a(iArr[i19], iArr[i20]);
                    zArr[(i11 * 6) + i10 + i15 + i16] = c1194b.m1789a(iArr[i20], iArr[i17]);
                    i16++;
                    z = z;
                    i = i;
                    i3 = 2;
                }
                i14++;
                i3 = 2;
            }
            i10 += i11 << 3;
            i9++;
            i3 = 2;
        }
        return zArr;
    }

    /* JADX INFO: renamed from: a */
    private static int m1480a(boolean[] zArr, int i, int i2) {
        int i3 = 0;
        for (int i4 = i; i4 < i + i2; i4++) {
            i3 <<= 1;
            if (zArr[i4]) {
                i3 |= 1;
            }
        }
        return i3;
    }

    /* JADX INFO: renamed from: a */
    private static byte m1477a(boolean[] zArr, int i) {
        int length = zArr.length - i;
        if (length >= 8) {
            return (byte) m1480a(zArr, i, 8);
        }
        return (byte) (m1480a(zArr, i, length) << (8 - length));
    }

    /* JADX INFO: renamed from: d */
    private static byte[] m1486d(boolean[] zArr) {
        int iM1480a;
        byte[] bArr = new byte[(zArr.length + 7) / 8];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i << 3;
            int length = zArr.length - i2;
            if (length >= 8) {
                iM1480a = m1480a(zArr, i2, 8);
            } else {
                iM1480a = m1480a(zArr, i2, length) << (8 - length);
            }
            bArr[i] = (byte) iM1480a;
        }
        return bArr;
    }
}
