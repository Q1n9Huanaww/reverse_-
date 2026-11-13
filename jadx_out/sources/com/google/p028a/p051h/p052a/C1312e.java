package com.google.p028a.p051h.p052a;

import com.google.p028a.C1303h;
import com.google.p028a.p035c.C1202e;
import com.google.p028a.p035c.EnumC1201d;
import com.google.p028a.p051h.C1321c;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/* JADX INFO: renamed from: com.google.a.h.a.e */
/* JADX INFO: compiled from: DecodedBitStreamParser.java */
/* JADX INFO: loaded from: classes.dex */
final class C1312e {

    /* JADX INFO: renamed from: A */
    private static final char[] f2581A = ";<>@[\\]_`~!\r\t,:\n-.$/\"|*()?{}'".toCharArray();

    /* JADX INFO: renamed from: B */
    private static final char[] f2582B = "0123456789&\r\t,:#-.$/+%*=^".toCharArray();

    /* JADX INFO: renamed from: C */
    private static final BigInteger[] f2583C;

    /* JADX INFO: renamed from: D */
    private static final int f2584D = 2;

    /* JADX INFO: renamed from: a */
    private static final int f2585a = 900;

    /* JADX INFO: renamed from: b */
    private static final int f2586b = 901;

    /* JADX INFO: renamed from: c */
    private static final int f2587c = 902;

    /* JADX INFO: renamed from: d */
    private static final int f2588d = 924;

    /* JADX INFO: renamed from: e */
    private static final int f2589e = 925;

    /* JADX INFO: renamed from: f */
    private static final int f2590f = 926;

    /* JADX INFO: renamed from: g */
    private static final int f2591g = 927;

    /* JADX INFO: renamed from: h */
    private static final int f2592h = 928;

    /* JADX INFO: renamed from: i */
    private static final int f2593i = 923;

    /* JADX INFO: renamed from: j */
    private static final int f2594j = 922;

    /* JADX INFO: renamed from: k */
    private static final int f2595k = 913;

    /* JADX INFO: renamed from: l */
    private static final int f2596l = 15;

    /* JADX INFO: renamed from: m */
    private static final int f2597m = 0;

    /* JADX INFO: renamed from: n */
    private static final int f2598n = 1;

    /* JADX INFO: renamed from: o */
    private static final int f2599o = 2;

    /* JADX INFO: renamed from: p */
    private static final int f2600p = 3;

    /* JADX INFO: renamed from: q */
    private static final int f2601q = 4;

    /* JADX INFO: renamed from: r */
    private static final int f2602r = 5;

    /* JADX INFO: renamed from: s */
    private static final int f2603s = 6;

    /* JADX INFO: renamed from: t */
    private static final int f2604t = 25;

    /* JADX INFO: renamed from: u */
    private static final int f2605u = 27;

    /* JADX INFO: renamed from: v */
    private static final int f2606v = 27;

    /* JADX INFO: renamed from: w */
    private static final int f2607w = 28;

    /* JADX INFO: renamed from: x */
    private static final int f2608x = 28;

    /* JADX INFO: renamed from: y */
    private static final int f2609y = 29;

    /* JADX INFO: renamed from: z */
    private static final int f2610z = 29;

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: renamed from: com.google.a.h.a.e$a */
    /* JADX INFO: compiled from: DecodedBitStreamParser.java */
    private static final class a {

        /* JADX INFO: renamed from: a */
        public static final int f2612a = 1;

        /* JADX INFO: renamed from: b */
        public static final int f2613b = 2;

        /* JADX INFO: renamed from: c */
        public static final int f2614c = 3;

        /* JADX INFO: renamed from: d */
        public static final int f2615d = 4;

        /* JADX INFO: renamed from: e */
        public static final int f2616e = 5;

        /* JADX INFO: renamed from: f */
        public static final int f2617f = 6;

        /* JADX INFO: renamed from: g */
        private static final /* synthetic */ int[] f2618g = {f2612a, f2613b, f2614c, f2615d, f2616e, f2617f};

        private a(String str, int i) {
            super(str, i);
        }

        /* JADX INFO: renamed from: a */
        public static int[] m2282a() {
            return (int[]) f2618g.clone();
        }
    }

    static {
        BigInteger[] bigIntegerArr = new BigInteger[16];
        f2583C = bigIntegerArr;
        bigIntegerArr[0] = BigInteger.ONE;
        BigInteger bigIntegerValueOf = BigInteger.valueOf(900L);
        f2583C[1] = bigIntegerValueOf;
        for (int i = 2; i < f2583C.length; i++) {
            BigInteger[] bigIntegerArr2 = f2583C;
            bigIntegerArr2[i] = bigIntegerArr2[i - 1].multiply(bigIntegerValueOf);
        }
    }

    private C1312e() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:17:0x004e  */
    /* JADX INFO: renamed from: a */
    static C1202e m2278a(int[] iArr, String str) throws C1303h {
        int iM2277a;
        StringBuilder sb = new StringBuilder(iArr.length << 1);
        Charset charsetForName = StandardCharsets.ISO_8859_1;
        int i = iArr[1];
        C1321c c1321c = new C1321c();
        int i2 = 2;
        while (i2 < iArr[0]) {
            if (i != f2595k) {
                switch (i) {
                    case f2585a /* 900 */:
                        iM2277a = m2277a(iArr, i2, sb);
                        break;
                    case f2586b /* 901 */:
                        iM2277a = m2275a(i, iArr, charsetForName, i2, sb);
                        break;
                    case f2587c /* 902 */:
                        iM2277a = m2281b(iArr, i2, sb);
                        break;
                    default:
                        switch (i) {
                            case f2594j /* 922 */:
                            case f2593i /* 923 */:
                                throw C1303h.m2217a();
                            case f2588d /* 924 */:
                                iM2277a = m2275a(i, iArr, charsetForName, i2, sb);
                                break;
                            case f2589e /* 925 */:
                                iM2277a = i2 + 1;
                                break;
                            case f2590f /* 926 */:
                                iM2277a = i2 + 2;
                                break;
                            case f2591g /* 927 */:
                                iM2277a = i2 + 1;
                                charsetForName = Charset.forName(EnumC1201d.m1827a(iArr[i2]).name());
                                break;
                            case 928:
                                iM2277a = m2276a(iArr, i2, c1321c);
                                break;
                            default:
                                iM2277a = m2277a(iArr, i2 - 1, sb);
                                break;
                        }
                        break;
                }
            } else {
                iM2277a = i2 + 1;
                sb.append((char) iArr[i2]);
            }
            if (iM2277a < iArr.length) {
                i2 = iM2277a + 1;
                i = iArr[iM2277a];
            } else {
                throw C1303h.m2217a();
            }
        }
        if (sb.length() == 0) {
            throw C1303h.m2217a();
        }
        C1202e c1202e = new C1202e(null, sb.toString(), null, str);
        c1202e.f2143h = c1321c;
        return c1202e;
    }

    /* JADX INFO: renamed from: a */
    private static int m2276a(int[] iArr, int i, C1321c c1321c) throws C1303h {
        if (i + 2 > iArr[0]) {
            throw C1303h.m2217a();
        }
        int[] iArr2 = new int[2];
        int i2 = i;
        int i3 = 0;
        while (i3 < 2) {
            iArr2[i3] = iArr[i2];
            i3++;
            i2++;
        }
        c1321c.f2646a = Integer.parseInt(m2279a(iArr2, 2));
        StringBuilder sb = new StringBuilder();
        int iM2277a = m2277a(iArr, i2, sb);
        c1321c.f2647b = sb.toString();
        int i4 = iArr[iM2277a] == f2593i ? iM2277a + 1 : -1;
        while (iM2277a < iArr[0]) {
            switch (iArr[iM2277a]) {
                case f2594j /* 922 */:
                    iM2277a++;
                    c1321c.f2648c = true;
                    break;
                case f2593i /* 923 */:
                    int i5 = iM2277a + 1;
                    switch (iArr[i5]) {
                        case 0:
                            StringBuilder sb2 = new StringBuilder();
                            iM2277a = m2277a(iArr, i5 + 1, sb2);
                            c1321c.f2652g = sb2.toString();
                            break;
                        case 1:
                            StringBuilder sb3 = new StringBuilder();
                            iM2277a = m2281b(iArr, i5 + 1, sb3);
                            c1321c.f2649d = Integer.parseInt(sb3.toString());
                            break;
                        case 2:
                            StringBuilder sb4 = new StringBuilder();
                            iM2277a = m2281b(iArr, i5 + 1, sb4);
                            c1321c.f2654i = Long.parseLong(sb4.toString());
                            break;
                        case 3:
                            StringBuilder sb5 = new StringBuilder();
                            iM2277a = m2277a(iArr, i5 + 1, sb5);
                            c1321c.f2650e = sb5.toString();
                            break;
                        case 4:
                            StringBuilder sb6 = new StringBuilder();
                            iM2277a = m2277a(iArr, i5 + 1, sb6);
                            c1321c.f2651f = sb6.toString();
                            break;
                        case 5:
                            StringBuilder sb7 = new StringBuilder();
                            iM2277a = m2281b(iArr, i5 + 1, sb7);
                            c1321c.f2653h = Long.parseLong(sb7.toString());
                            break;
                        case 6:
                            StringBuilder sb8 = new StringBuilder();
                            iM2277a = m2281b(iArr, i5 + 1, sb8);
                            c1321c.f2655j = Integer.parseInt(sb8.toString());
                            break;
                        default:
                            throw C1303h.m2217a();
                    }
                    break;
                default:
                    throw C1303h.m2217a();
            }
        }
        if (i4 != -1) {
            int i6 = iM2277a - i4;
            if (c1321c.f2648c) {
                i6--;
            }
            c1321c.f2656k = Arrays.copyOfRange(iArr, i4, i6 + i4);
        }
        return iM2277a;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:13:0x003a. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:14:0x003d. Please report as an issue. */
    /* JADX INFO: renamed from: a */
    private static int m2277a(int[] iArr, int i, StringBuilder sb) {
        int i2;
        char c;
        char c2;
        int[] iArr2 = new int[(iArr[0] - i) << 1];
        int[] iArr3 = new int[(iArr[0] - i) << 1];
        int i3 = i;
        boolean z = false;
        int i4 = 0;
        while (i3 < iArr[0] && !z) {
            int i5 = i3 + 1;
            int i6 = iArr[i3];
            if (i6 < f2585a) {
                iArr2[i4] = i6 / 30;
                iArr2[i4 + 1] = i6 % 30;
                i4 += 2;
            } else if (i6 != f2595k) {
                if (i6 != 928) {
                    switch (i6) {
                        case f2585a /* 900 */:
                            iArr2[i4] = f2585a;
                            i4++;
                            break;
                        case f2586b /* 901 */:
                        case f2587c /* 902 */:
                            break;
                        default:
                            switch (i6) {
                                case f2594j /* 922 */:
                                case f2593i /* 923 */:
                                case f2588d /* 924 */:
                                    break;
                                default:
                                    break;
                            }
                            break;
                    }
                }
                i3 = i5 - 1;
                z = true;
            } else {
                iArr2[i4] = f2595k;
                i3 = i5 + 1;
                iArr3[i4] = iArr[i5];
                i4++;
            }
            i3 = i5;
        }
        int i7 = a.f2612a;
        int i8 = a.f2612a;
        int i9 = i7;
        for (int i10 = 0; i10 < i4; i10++) {
            int i11 = iArr2[i10];
            char c3 = ' ';
            switch (AnonymousClass1.f2611a[i9 - 1]) {
                case 1:
                    if (i11 < 26) {
                        c = (char) (i11 + 65);
                        c3 = c;
                    } else {
                        if (i11 == f2585a) {
                            i9 = a.f2612a;
                        } else if (i11 != f2595k) {
                            switch (i11) {
                                case 27:
                                    i9 = a.f2613b;
                                    break;
                                case 28:
                                    i9 = a.f2614c;
                                    break;
                                case 29:
                                    i2 = a.f2617f;
                                    c3 = 0;
                                    int i12 = i2;
                                    i8 = i9;
                                    i9 = i12;
                                    break;
                            }
                        } else {
                            sb.append((char) iArr3[i10]);
                        }
                        c3 = 0;
                    }
                    break;
                case 2:
                    if (i11 < 26) {
                        c = (char) (i11 + 97);
                        c3 = c;
                    } else {
                        if (i11 == f2585a) {
                            i9 = a.f2612a;
                        } else if (i11 != f2595k) {
                            switch (i11) {
                                case 27:
                                    i2 = a.f2616e;
                                    c3 = 0;
                                    int i13 = i2;
                                    i8 = i9;
                                    i9 = i13;
                                    break;
                                case 28:
                                    i9 = a.f2614c;
                                    break;
                                case 29:
                                    i2 = a.f2617f;
                                    c3 = 0;
                                    int i14 = i2;
                                    i8 = i9;
                                    i9 = i14;
                                    break;
                            }
                        } else {
                            sb.append((char) iArr3[i10]);
                        }
                        c3 = 0;
                    }
                    break;
                case 3:
                    if (i11 < 25) {
                        c = f2582B[i11];
                        c3 = c;
                    } else {
                        if (i11 == f2585a) {
                            i9 = a.f2612a;
                        } else if (i11 != f2595k) {
                            switch (i11) {
                                case 25:
                                    i9 = a.f2615d;
                                    break;
                                case 27:
                                    i9 = a.f2613b;
                                    break;
                                case 28:
                                    i9 = a.f2612a;
                                    break;
                                case 29:
                                    i2 = a.f2617f;
                                    c3 = 0;
                                    int i15 = i2;
                                    i8 = i9;
                                    i9 = i15;
                                    break;
                            }
                        } else {
                            sb.append((char) iArr3[i10]);
                        }
                        c3 = 0;
                    }
                    break;
                case 4:
                    if (i11 < 29) {
                        c = f2581A[i11];
                        c3 = c;
                    } else {
                        if (i11 == 29 || i11 == f2585a) {
                            i9 = a.f2612a;
                        } else if (i11 == f2595k) {
                            sb.append((char) iArr3[i10]);
                        }
                        c3 = 0;
                    }
                    break;
                case 5:
                    if (i11 < 26) {
                        c2 = (char) (i11 + 65);
                        c3 = c2;
                    } else if (i11 != 26) {
                        i9 = i11 != f2585a ? i8 : a.f2612a;
                        c3 = 0;
                    }
                    i9 = i8;
                    break;
                case 6:
                    if (i11 < 29) {
                        c2 = f2581A[i11];
                        c3 = c2;
                        i9 = i8;
                    } else {
                        if (i11 == 29 || i11 == f2585a) {
                            i9 = a.f2612a;
                        } else if (i11 == f2595k) {
                            sb.append((char) iArr3[i10]);
                        }
                        c3 = 0;
                    }
                    break;
                default:
                    c3 = 0;
                    break;
            }
            if (c3 != 0) {
                sb.append(c3);
            }
        }
        return i3;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:68:0x00dd A[PHI: r1
  0x00dd: PHI (r1v16 int) = (r1v2 int), (r1v2 int), (r1v2 int), (r1v17 int) binds: [B:62:0x00cc, B:51:0x00ab, B:39:0x0083, B:24:0x0052] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX INFO: renamed from: a */
    private static void m2280a(int[] iArr, int[] iArr2, int i, StringBuilder sb) {
        char c;
        int i2;
        char c2;
        int i3 = a.f2612a;
        int i4 = a.f2612a;
        int i5 = i3;
        for (int i6 = 0; i6 < i; i6++) {
            int i7 = iArr[i6];
            switch (AnonymousClass1.f2611a[i5 - 1]) {
                case 1:
                    if (i7 < 26) {
                        c = (char) (i7 + 65);
                    } else {
                        if (i7 == f2585a) {
                            i5 = a.f2612a;
                        } else if (i7 != f2595k) {
                            switch (i7) {
                                case 26:
                                    c = ' ';
                                    break;
                                case 27:
                                    i5 = a.f2613b;
                                    break;
                                case 28:
                                    i5 = a.f2614c;
                                    break;
                                case 29:
                                    i2 = a.f2617f;
                                    c = 0;
                                    int i8 = i2;
                                    i4 = i5;
                                    i5 = i8;
                                    break;
                            }
                        } else {
                            sb.append((char) iArr2[i6]);
                        }
                        c = 0;
                    }
                    break;
                case 2:
                    if (i7 < 26) {
                        c = (char) (i7 + 97);
                    } else {
                        if (i7 == f2585a) {
                            i5 = a.f2612a;
                        } else if (i7 != f2595k) {
                            switch (i7) {
                                case 26:
                                    c = ' ';
                                    break;
                                case 27:
                                    i2 = a.f2616e;
                                    c = 0;
                                    int i9 = i2;
                                    i4 = i5;
                                    i5 = i9;
                                    break;
                                case 28:
                                    i5 = a.f2614c;
                                    break;
                                case 29:
                                    i2 = a.f2617f;
                                    c = 0;
                                    int i10 = i2;
                                    i4 = i5;
                                    i5 = i10;
                                    break;
                            }
                        } else {
                            sb.append((char) iArr2[i6]);
                        }
                        c = 0;
                    }
                    break;
                case 3:
                    if (i7 < 25) {
                        c = f2582B[i7];
                    } else {
                        if (i7 == f2585a) {
                            i5 = a.f2612a;
                        } else if (i7 != f2595k) {
                            switch (i7) {
                                case 25:
                                    i5 = a.f2615d;
                                    break;
                                case 26:
                                    c = ' ';
                                    break;
                                case 27:
                                    i5 = a.f2613b;
                                    break;
                                case 28:
                                    i5 = a.f2612a;
                                    break;
                                case 29:
                                    i2 = a.f2617f;
                                    c = 0;
                                    int i11 = i2;
                                    i4 = i5;
                                    i5 = i11;
                                    break;
                            }
                        } else {
                            sb.append((char) iArr2[i6]);
                        }
                        c = 0;
                    }
                    break;
                case 4:
                    if (i7 < 29) {
                        c = f2581A[i7];
                    } else {
                        if (i7 == 29 || i7 == f2585a) {
                            i5 = a.f2612a;
                        } else if (i7 == f2595k) {
                            sb.append((char) iArr2[i6]);
                        }
                        c = 0;
                    }
                    break;
                case 5:
                    if (i7 < 26) {
                        c2 = (char) (i7 + 65);
                        c = c2;
                        i5 = i4;
                    } else if (i7 != 26) {
                        i5 = i7 != f2585a ? i4 : a.f2612a;
                        c = 0;
                    } else {
                        i5 = i4;
                        c = ' ';
                    }
                    break;
                case 6:
                    if (i7 < 29) {
                        c2 = f2581A[i7];
                        c = c2;
                        i5 = i4;
                    } else {
                        if (i7 == 29 || i7 == f2585a) {
                            i5 = a.f2612a;
                        } else if (i7 == f2595k) {
                            sb.append((char) iArr2[i6]);
                        }
                        c = 0;
                    }
                    break;
                default:
                    c = 0;
                    break;
            }
            if (c != 0) {
                sb.append(c);
            }
        }
    }

    /* JADX INFO: renamed from: com.google.a.h.a.e$1, reason: invalid class name */
    /* JADX INFO: compiled from: DecodedBitStreamParser.java */
    static /* synthetic */ class AnonymousClass1 {

        /* JADX INFO: renamed from: a */
        static final /* synthetic */ int[] f2611a = new int[a.m2282a().length];

        static {
            try {
                f2611a[a.f2612a - 1] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2611a[a.f2613b - 1] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2611a[a.f2614c - 1] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2611a[a.f2615d - 1] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2611a[a.f2616e - 1] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2611a[a.f2617f - 1] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0043 A[FALL_THROUGH] */
    /* JADX INFO: renamed from: a */
    private static int m2275a(int i, int[] iArr, Charset charset, int i2, StringBuilder sb) {
        int i3;
        int i4;
        int i5;
        int i6;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        long j = 900;
        if (i == f2586b) {
            int[] iArr2 = new int[6];
            int i7 = i2 + 1;
            int i8 = iArr[i2];
            boolean z = false;
            while (true) {
                i3 = 0;
                long j2 = 0;
                while (true) {
                    if (i7 < iArr[0] && !z) {
                        i3++;
                        iArr2[i3] = i8;
                        j2 = (j2 * 900) + ((long) i8);
                        i6 = i7 + 1;
                        i8 = iArr[i7];
                        if (i8 != 928) {
                            switch (i8) {
                                case f2585a /* 900 */:
                                case f2586b /* 901 */:
                                case f2587c /* 902 */:
                                    break;
                                default:
                                    switch (i8) {
                                        case f2594j /* 922 */:
                                        case f2593i /* 923 */:
                                        case f2588d /* 924 */:
                                            break;
                                        default:
                                            if (i3 % 5 != 0 || i3 <= 0) {
                                                i7 = i6;
                                            }
                                            break;
                                    }
                                    break;
                            }
                        }
                        i7 = i6 - 1;
                        z = true;
                    }
                }
                for (int i9 = 0; i9 < 6; i9++) {
                    byteArrayOutputStream.write((byte) (j2 >> ((5 - i9) * 8)));
                }
                i7 = i6;
            }
            if (i7 != iArr[0] || i8 >= f2585a) {
                i4 = i3;
            } else {
                i4 = i3 + 1;
                iArr2[i3] = i8;
            }
            for (int i10 = 0; i10 < i4; i10++) {
                byteArrayOutputStream.write((byte) iArr2[i10]);
            }
            i5 = i7;
        } else if (i != f2588d) {
            i5 = i2;
        } else {
            i5 = i2;
            boolean z2 = false;
            int i11 = 0;
            long j3 = 0;
            while (i5 < iArr[0] && !z2) {
                int i12 = i5 + 1;
                int i13 = iArr[i5];
                if (i13 < f2585a) {
                    i11++;
                    long j4 = (j3 * j) + ((long) i13);
                    i5 = i12;
                    j3 = j4;
                } else if (i13 != 928) {
                    switch (i13) {
                        default:
                            switch (i13) {
                                case f2594j /* 922 */:
                                case f2593i /* 923 */:
                                case f2588d /* 924 */:
                                    break;
                                default:
                                    i5 = i12;
                                    break;
                            }
                        case f2585a /* 900 */:
                        case f2586b /* 901 */:
                        case f2587c /* 902 */:
                            i5 = i12 - 1;
                            z2 = true;
                            break;
                    }
                } else {
                    i5 = i12 - 1;
                    z2 = true;
                }
                if (i11 % 5 == 0 && i11 > 0) {
                    for (int i14 = 0; i14 < 6; i14++) {
                        byteArrayOutputStream.write((byte) (j3 >> ((5 - i14) * 8)));
                    }
                    i11 = 0;
                    j3 = 0;
                }
                j = 900;
            }
        }
        sb.append(new String(byteArrayOutputStream.toByteArray(), charset));
        return i5;
    }

    /* JADX WARN: Code duplicated, block: B:17:0x002b A[FALL_THROUGH] */
    /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1092)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:419)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:91)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:31)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaksForCase(SwitchRegionMaker.java:399)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaks(SwitchRegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.leaveRegion(PostProcessRegions.java:31)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:91)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:27)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.process(PostProcessRegions.java:21)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:31)
        */
    /* JADX INFO: renamed from: b */
    private static int m2281b(int[] r7, int r8, java.lang.StringBuilder r9) throws com.google.p028a.C1303h {
        /*
            r0 = 15
            int[] r0 = new int[r0]
            r1 = 0
            r2 = 0
            r3 = 0
        L7:
            r4 = r7[r1]
            if (r8 >= r4) goto L44
            if (r2 != 0) goto L44
            int r4 = r8 + 1
            r8 = r7[r8]
            r5 = r7[r1]
            r6 = 1
            if (r4 != r5) goto L17
            r2 = 1
        L17:
            r5 = 900(0x384, float:1.261E-42)
            if (r8 >= r5) goto L20
            r0[r3] = r8
            int r3 = r3 + 1
            goto L2e
        L20:
            r5 = 928(0x3a0, float:1.3E-42)
            if (r8 == r5) goto L2b
            switch(r8) {
                case 900: goto L2b;
                case 901: goto L2b;
                default: goto L27;
            }
        L27:
            switch(r8) {
                case 922: goto L2b;
                case 923: goto L2b;
                case 924: goto L2b;
                default: goto L2a;
            }
        L2a:
            goto L2e
        L2b:
            int r4 = r4 + (-1)
            r2 = 1
        L2e:
            int r5 = r3 % 15
            if (r5 == 0) goto L38
            r5 = 902(0x386, float:1.264E-42)
            if (r8 == r5) goto L38
            if (r2 == 0) goto L42
        L38:
            if (r3 <= 0) goto L42
            java.lang.String r8 = m2279a(r0, r3)
            r9.append(r8)
            r3 = 0
        L42:
            r8 = r4
            goto L7
        L44:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.p028a.p051h.p052a.C1312e.m2281b(int[], int, java.lang.StringBuilder):int");
    }

    /* JADX INFO: renamed from: a */
    private static String m2279a(int[] iArr, int i) throws C1303h {
        BigInteger bigIntegerAdd = BigInteger.ZERO;
        for (int i2 = 0; i2 < i; i2++) {
            bigIntegerAdd = bigIntegerAdd.add(f2583C[(i - i2) - 1].multiply(BigInteger.valueOf(iArr[i2])));
        }
        String string = bigIntegerAdd.toString();
        if (string.charAt(0) != '1') {
            throw C1303h.m2217a();
        }
        return string.substring(1);
    }
}
