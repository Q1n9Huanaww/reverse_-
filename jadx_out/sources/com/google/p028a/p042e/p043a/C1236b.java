package com.google.p028a.p042e.p043a;

import com.google.p028a.p035c.C1202e;
import com.umeng.commonsdk.proguard.C1770bg;
import java.text.DecimalFormat;

/* JADX INFO: renamed from: com.google.a.e.a.b */
/* JADX INFO: compiled from: DecodedBitStreamParser.java */
/* JADX INFO: loaded from: classes.dex */
final class C1236b {

    /* JADX INFO: renamed from: a */
    private static final char f2288a = 65520;

    /* JADX INFO: renamed from: b */
    private static final char f2289b = 65521;

    /* JADX INFO: renamed from: c */
    private static final char f2290c = 65522;

    /* JADX INFO: renamed from: d */
    private static final char f2291d = 65523;

    /* JADX INFO: renamed from: e */
    private static final char f2292e = 65524;

    /* JADX INFO: renamed from: f */
    private static final char f2293f = 65525;

    /* JADX INFO: renamed from: g */
    private static final char f2294g = 65526;

    /* JADX INFO: renamed from: h */
    private static final char f2295h = 65527;

    /* JADX INFO: renamed from: i */
    private static final char f2296i = 65528;

    /* JADX INFO: renamed from: j */
    private static final char f2297j = 65529;

    /* JADX INFO: renamed from: k */
    private static final char f2298k = 65530;

    /* JADX INFO: renamed from: l */
    private static final char f2299l = 65531;

    /* JADX INFO: renamed from: m */
    private static final char f2300m = 65532;

    /* JADX INFO: renamed from: n */
    private static final char f2301n = 28;

    /* JADX INFO: renamed from: o */
    private static final char f2302o = 29;

    /* JADX INFO: renamed from: p */
    private static final char f2303p = 30;

    /* JADX INFO: renamed from: q */
    private static final String[] f2304q = {"\nABCDEFGHIJKLMNOPQRSTUVWXYZ\ufffa\u001c\u001d\u001e\ufffb ￼\"#$%&'()*+,-./0123456789:\ufff1\ufff2\ufff3\ufff4\ufff8", "`abcdefghijklmnopqrstuvwxyz\ufffa\u001c\u001d\u001e\ufffb{￼}~\u007f;<=>?[\\]^_ ,./:@!|￼\ufff5\ufff6￼\ufff0\ufff2\ufff3\ufff4\ufff7", "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖ×ØÙÚ\ufffa\u001c\u001d\u001eÛÜÝÞßª¬±²³µ¹º¼½¾\u0080\u0081\u0082\u0083\u0084\u0085\u0086\u0087\u0088\u0089\ufff7 \ufff9\ufff3\ufff4\ufff8", "àáâãäåæçèéêëìíîïðñòóôõö÷øùú\ufffa\u001c\u001d\u001e\ufffbûüýþÿ¡¨«¯°´·¸»¿\u008a\u008b\u008c\u008d\u008e\u008f\u0090\u0091\u0092\u0093\u0094\ufff7 \ufff2\ufff9\ufff4\ufff8", "\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\ufffa￼￼\u001b\ufffb\u001c\u001d\u001e\u001f\u009f ¢£¤¥¦§©\u00ad®¶\u0095\u0096\u0097\u0098\u0099\u009a\u009b\u009c\u009d\u009e\ufff7 \ufff2\ufff3\ufff9\ufff8", "\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&'()*+,-./0123456789:;<=>?"};

    private C1236b() {
    }

    /* JADX INFO: renamed from: a */
    static C1202e m2010a(byte[] bArr, int i) {
        String strValueOf;
        StringBuilder sb = new StringBuilder(144);
        switch (i) {
            case 2:
            case 3:
                if (i == 2) {
                    strValueOf = new DecimalFormat("0000000000".substring(0, m2009a(bArr, new byte[]{39, 40, 41, 42, 31, 32}))).format(m2009a(bArr, new byte[]{33, 34, 35, 36, 25, 26, 27, 28, 29, 30, 19, 20, 21, 22, 23, 24, C1770bg.f4123k, C1770bg.f4124l, C1770bg.f4125m, C1770bg.f4126n, 17, 18, 7, 8, 9, 10, 11, 12, 1, 2}));
                } else {
                    strValueOf = String.valueOf(new char[]{f2304q[0].charAt(m2009a(bArr, new byte[]{39, 40, 41, 42, 31, 32})), f2304q[0].charAt(m2009a(bArr, new byte[]{33, 34, 35, 36, 25, 26})), f2304q[0].charAt(m2009a(bArr, new byte[]{27, 28, 29, 30, 19, 20})), f2304q[0].charAt(m2009a(bArr, new byte[]{21, 22, 23, 24, C1770bg.f4123k, C1770bg.f4124l})), f2304q[0].charAt(m2009a(bArr, new byte[]{C1770bg.f4125m, C1770bg.f4126n, 17, 18, 7, 8})), f2304q[0].charAt(m2009a(bArr, new byte[]{9, 10, 11, 12, 1, 2}))});
                }
                DecimalFormat decimalFormat = new DecimalFormat("000");
                String str = decimalFormat.format(m2009a(bArr, new byte[]{53, 54, 43, 44, 45, 46, 47, 48, 37, 38}));
                String str2 = decimalFormat.format(m2009a(bArr, new byte[]{55, 56, 57, 58, 59, 60, 49, 50, 51, 52}));
                sb.append(m2011a(bArr, 10, 84));
                if (sb.toString().startsWith("[)>\u001e01\u001d")) {
                    sb.insert(9, strValueOf + f2302o + str + f2302o + str2 + f2302o);
                } else {
                    sb.insert(0, strValueOf + f2302o + str + f2302o + str2 + f2302o);
                }
                break;
            case 4:
                sb.append(m2011a(bArr, 1, 93));
                break;
            case 5:
                sb.append(m2011a(bArr, 1, 77));
                break;
        }
        return new C1202e(bArr, sb.toString(), null, String.valueOf(i));
    }

    /* JADX INFO: renamed from: a */
    private static int m2007a(int i, byte[] bArr) {
        int i2 = i - 1;
        return ((1 << (5 - (i2 % 6))) & bArr[i2 / 6]) == 0 ? 0 : 1;
    }

    /* JADX INFO: renamed from: a */
    private static int m2009a(byte[] bArr, byte[] bArr2) {
        if (bArr2.length == 0) {
            throw new IllegalArgumentException();
        }
        int length = 0;
        for (int i = 0; i < bArr2.length; i++) {
            int i2 = bArr2[i] - 1;
            length += (((1 << (5 - (i2 % 6))) & bArr[i2 / 6]) == 0 ? 0 : 1) << ((bArr2.length - i) - 1);
        }
        return length;
    }

    /* JADX INFO: renamed from: a */
    private static int m2008a(byte[] bArr) {
        return m2009a(bArr, new byte[]{53, 54, 43, 44, 45, 46, 47, 48, 37, 38});
    }

    /* JADX INFO: renamed from: b */
    private static int m2012b(byte[] bArr) {
        return m2009a(bArr, new byte[]{55, 56, 57, 58, 59, 60, 49, 50, 51, 52});
    }

    /* JADX INFO: renamed from: c */
    private static int m2013c(byte[] bArr) {
        return m2009a(bArr, new byte[]{39, 40, 41, 42, 31, 32});
    }

    /* JADX INFO: renamed from: d */
    private static int m2014d(byte[] bArr) {
        return m2009a(bArr, new byte[]{33, 34, 35, 36, 25, 26, 27, 28, 29, 30, 19, 20, 21, 22, 23, 24, C1770bg.f4123k, C1770bg.f4124l, C1770bg.f4125m, C1770bg.f4126n, 17, 18, 7, 8, 9, 10, 11, 12, 1, 2});
    }

    /* JADX INFO: renamed from: e */
    private static String m2015e(byte[] bArr) {
        return String.valueOf(new char[]{f2304q[0].charAt(m2009a(bArr, new byte[]{39, 40, 41, 42, 31, 32})), f2304q[0].charAt(m2009a(bArr, new byte[]{33, 34, 35, 36, 25, 26})), f2304q[0].charAt(m2009a(bArr, new byte[]{27, 28, 29, 30, 19, 20})), f2304q[0].charAt(m2009a(bArr, new byte[]{21, 22, 23, 24, C1770bg.f4123k, C1770bg.f4124l})), f2304q[0].charAt(m2009a(bArr, new byte[]{C1770bg.f4125m, C1770bg.f4126n, 17, 18, 7, 8})), f2304q[0].charAt(m2009a(bArr, new byte[]{9, 10, 11, 12, 1, 2}))});
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX INFO: renamed from: a */
    private static String m2011a(byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        int i3 = i;
        int i4 = 0;
        int i5 = -1;
        int i6 = 0;
        while (i3 < i + i2) {
            char cCharAt = f2304q[i4].charAt(bArr[i3]);
            switch (cCharAt) {
                case 65520:
                case 65521:
                case 65522:
                case 65523:
                case 65524:
                    i6 = i4;
                    i4 = cCharAt - f2288a;
                    i5 = 1;
                    break;
                case 65525:
                    i5 = 2;
                    i6 = i4;
                    i4 = 0;
                    break;
                case 65526:
                    i5 = 3;
                    i6 = i4;
                    i4 = 0;
                    break;
                case 65527:
                    i4 = 0;
                    i5 = -1;
                    break;
                case 65528:
                    i4 = 1;
                    i5 = -1;
                    break;
                case 65529:
                    i5 = -1;
                    break;
                case 65530:
                default:
                    sb.append(cCharAt);
                    break;
                case 65531:
                    int i7 = i3 + 1;
                    int i8 = bArr[i7] << 24;
                    int i9 = i7 + 1;
                    int i10 = i8 + (bArr[i9] << 18);
                    int i11 = i9 + 1;
                    int i12 = i10 + (bArr[i11] << 12);
                    int i13 = i11 + 1;
                    int i14 = i12 + (bArr[i13] << 6);
                    i3 = i13 + 1;
                    sb.append(new DecimalFormat("000000000").format(i14 + bArr[i3]));
                    break;
            }
            int i15 = i5 - 1;
            if (i5 == 0) {
                i4 = i6;
            }
            i3++;
            i5 = i15;
        }
        while (sb.length() > 0 && sb.charAt(sb.length() - 1) == 65532) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
}
