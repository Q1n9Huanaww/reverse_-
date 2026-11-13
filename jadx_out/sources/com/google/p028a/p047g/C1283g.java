package com.google.p028a.p047g;

import com.cyjh.mobileanjian.ipc.share.proto.UiMessage;
import com.google.p028a.C1210d;
import com.google.p028a.C1303h;
import com.google.p028a.C1358m;
import com.google.p028a.C1363r;
import com.google.p028a.C1365t;
import com.google.p028a.EnumC1138a;
import com.google.p028a.EnumC1233e;
import com.google.p028a.p035c.C1190a;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.g.g */
/* JADX INFO: compiled from: Code93Reader.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1283g extends AbstractC1294r {

    /* JADX INFO: renamed from: b */
    static final int[] f2491b;

    /* JADX INFO: renamed from: d */
    private static final int f2493d;

    /* JADX INFO: renamed from: e */
    private final StringBuilder f2494e = new StringBuilder(20);

    /* JADX INFO: renamed from: f */
    private final int[] f2495f = new int[6];

    /* JADX INFO: renamed from: a */
    static final String f2490a = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*";

    /* JADX INFO: renamed from: c */
    private static final char[] f2492c = f2490a.toCharArray();

    static {
        int[] iArr = {276, 328, 324, 322, 296, 292, 290, 336, 274, 266, 424, 420, 418, UiMessage.CommandToUi.Command_Type.GET_TEXT_VALUE, UiMessage.CommandToUi.Command_Type.GET_VISIBLE_VALUE, 394, 360, 356, 354, 308, 282, 344, 332, 326, 300, 278, 436, 434, 428, 422, UiMessage.CommandToUi.Command_Type.GET_TITLE_BACKCOLOR_VALUE, UiMessage.CommandToUi.Command_Type.GET_FW_VISIBLE_VALUE, 364, 358, UiMessage.CommandToUi.Command_Type.SET_FW_TEXT_COLOR_VALUE, 314, UiMessage.CommandToUi.Command_Type.SET_VISIBLE_VALUE, 468, 466, 458, 366, 374, 430, 294, 474, 470, UiMessage.CommandToUi.Command_Type.SET_TITLE_BACKCOLOR_VALUE, 350};
        f2491b = iArr;
        f2493d = iArr[47];
    }

    /* JADX INFO: renamed from: a */
    private static int m2172a(int[] iArr) {
        int i = 0;
        for (int i2 : iArr) {
            i += i2;
        }
        int length = iArr.length;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            int iRound = Math.round((iArr[i4] * 9.0f) / i);
            if (iRound <= 0 || iRound > 4) {
                return -1;
            }
            if ((i4 & 1) == 0) {
                int i5 = i3;
                for (int i6 = 0; i6 < iRound; i6++) {
                    i5 = (i5 << 1) | 1;
                }
                i3 = i5;
            } else {
                i3 <<= iRound;
            }
        }
        return i3;
    }

    /* JADX INFO: renamed from: a */
    private static char m2171a(int i) throws C1358m {
        for (int i2 = 0; i2 < f2491b.length; i2++) {
            if (f2491b[i2] == i) {
                return f2492c[i2];
            }
        }
        throw C1358m.m2611a();
    }

    /* JADX INFO: renamed from: a */
    private static String m2173a(CharSequence charSequence) throws C1303h {
        char c;
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        int i = 0;
        while (i < length) {
            char cCharAt = charSequence.charAt(i);
            if (cCharAt >= 'a' && cCharAt <= 'd') {
                if (i >= length - 1) {
                    throw C1303h.m2217a();
                }
                i++;
                char cCharAt2 = charSequence.charAt(i);
                switch (cCharAt) {
                    case 'a':
                        if (cCharAt2 < 'A' || cCharAt2 > 'Z') {
                            throw C1303h.m2217a();
                        }
                        c = (char) (cCharAt2 - '@');
                        break;
                    case 'b':
                        if (cCharAt2 >= 'A' && cCharAt2 <= 'E') {
                            c = (char) (cCharAt2 - '&');
                        } else if (cCharAt2 >= 'F' && cCharAt2 <= 'J') {
                            c = (char) (cCharAt2 - 11);
                        } else if (cCharAt2 >= 'K' && cCharAt2 <= 'O') {
                            c = (char) (cCharAt2 + 16);
                        } else if (cCharAt2 >= 'P' && cCharAt2 <= 'S') {
                            c = (char) (cCharAt2 + '+');
                        } else {
                            if (cCharAt2 < 'T' || cCharAt2 > 'Z') {
                                throw C1303h.m2217a();
                            }
                            c = 127;
                        }
                        break;
                    case 'c':
                        if (cCharAt2 >= 'A' && cCharAt2 <= 'O') {
                            c = (char) (cCharAt2 - ' ');
                        } else {
                            if (cCharAt2 != 'Z') {
                                throw C1303h.m2217a();
                            }
                            c = ':';
                        }
                        break;
                    case 'd':
                        if (cCharAt2 < 'A' || cCharAt2 > 'Z') {
                            throw C1303h.m2217a();
                        }
                        c = (char) (cCharAt2 + ' ');
                        break;
                    default:
                        c = 0;
                        break;
                }
                sb.append(c);
            } else {
                sb.append(cCharAt);
            }
            i++;
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: b */
    private static void m2176b(CharSequence charSequence) throws C1210d {
        int length = charSequence.length();
        m2174a(charSequence, length - 2, 20);
        m2174a(charSequence, length - 1, 15);
    }

    /* JADX INFO: renamed from: a */
    private static void m2174a(CharSequence charSequence, int i, int i2) throws C1210d {
        int iIndexOf = 0;
        int i3 = 1;
        for (int i4 = i - 1; i4 >= 0; i4--) {
            iIndexOf += f2490a.indexOf(charSequence.charAt(i4)) * i3;
            i3++;
            if (i3 > i2) {
                i3 = 1;
            }
        }
        if (charSequence.charAt(i) != f2492c[iIndexOf % 47]) {
            throw C1210d.m1865a();
        }
    }

    @Override // com.google.p028a.p047g.AbstractC1294r
    /* JADX INFO: renamed from: a */
    public final C1363r mo2038a(int i, C1190a c1190a, Map<EnumC1233e, ?> map) throws C1210d, C1358m, C1303h {
        char c;
        int i2 = c1190a.f2065b;
        int iM1760c = c1190a.m1760c(0);
        Arrays.fill(this.f2495f, 0);
        int[] iArr = this.f2495f;
        int length = iArr.length;
        int i3 = iM1760c;
        boolean z = false;
        int i4 = 0;
        while (iM1760c < i2) {
            if (c1190a.m1755a(iM1760c) != z) {
                iArr[i4] = iArr[i4] + 1;
            } else {
                if (i4 != length - 1) {
                    i4++;
                } else if (m2172a(iArr) == f2493d) {
                    int[] iArr2 = {i3, iM1760c};
                    int iM1760c2 = c1190a.m1760c(iArr2[1]);
                    int i5 = c1190a.f2065b;
                    int[] iArr3 = this.f2495f;
                    Arrays.fill(iArr3, 0);
                    StringBuilder sb = this.f2494e;
                    sb.setLength(0);
                    while (true) {
                        m2193a(c1190a, iM1760c2, iArr3);
                        int iM2172a = m2172a(iArr3);
                        if (iM2172a < 0) {
                            throw C1358m.m2611a();
                        }
                        int i6 = 0;
                        while (true) {
                            if (i6 < f2491b.length) {
                                if (f2491b[i6] == iM2172a) {
                                    break;
                                }
                                i6++;
                            } else {
                                throw C1358m.m2611a();
                            }
                        }
                        char c2 = f2492c[i6];
                        sb.append(c2);
                        int i7 = iM1760c2;
                        for (int i8 : iArr3) {
                            i7 += i8;
                        }
                        int iM1760c3 = c1190a.m1760c(i7);
                        if (c2 == '*') {
                            sb.deleteCharAt(sb.length() - 1);
                            int i9 = 0;
                            for (int i10 : iArr3) {
                                i9 += i10;
                            }
                            if (iM1760c3 == i5 || !c1190a.m1755a(iM1760c3)) {
                                throw C1358m.m2611a();
                            }
                            if (sb.length() < 2) {
                                throw C1358m.m2611a();
                            }
                            int length2 = sb.length();
                            m2174a(sb, length2 - 2, 20);
                            m2174a(sb, length2 - 1, 15);
                            sb.setLength(sb.length() - 2);
                            int length3 = sb.length();
                            StringBuilder sb2 = new StringBuilder(length3);
                            int i11 = 0;
                            while (i11 < length3) {
                                char cCharAt = sb.charAt(i11);
                                if (cCharAt >= 'a' && cCharAt <= 'd') {
                                    if (i11 >= length3 - 1) {
                                        throw C1303h.m2217a();
                                    }
                                    i11++;
                                    char cCharAt2 = sb.charAt(i11);
                                    switch (cCharAt) {
                                        case 'a':
                                            if (cCharAt2 < 'A' || cCharAt2 > 'Z') {
                                                throw C1303h.m2217a();
                                            }
                                            c = (char) (cCharAt2 - '@');
                                            break;
                                            break;
                                        case 'b':
                                            if (cCharAt2 >= 'A' && cCharAt2 <= 'E') {
                                                c = (char) (cCharAt2 - '&');
                                            } else if (cCharAt2 >= 'F' && cCharAt2 <= 'J') {
                                                c = (char) (cCharAt2 - 11);
                                            } else if (cCharAt2 >= 'K' && cCharAt2 <= 'O') {
                                                c = (char) (cCharAt2 + 16);
                                            } else if (cCharAt2 >= 'P' && cCharAt2 <= 'S') {
                                                c = (char) (cCharAt2 + '+');
                                            } else {
                                                if (cCharAt2 < 'T' || cCharAt2 > 'Z') {
                                                    throw C1303h.m2217a();
                                                }
                                                c = 127;
                                            }
                                            break;
                                        case 'c':
                                            if (cCharAt2 < 'A' || cCharAt2 > 'O') {
                                                if (cCharAt2 != 'Z') {
                                                    throw C1303h.m2217a();
                                                }
                                                c = ':';
                                            } else {
                                                c = (char) (cCharAt2 - ' ');
                                            }
                                            break;
                                        case 'd':
                                            if (cCharAt2 < 'A' || cCharAt2 > 'Z') {
                                                throw C1303h.m2217a();
                                            }
                                            c = (char) (cCharAt2 + ' ');
                                            break;
                                            break;
                                        default:
                                            c = 0;
                                            break;
                                    }
                                    sb2.append(c);
                                } else {
                                    sb2.append(cCharAt);
                                }
                                i11++;
                            }
                            float f = i;
                            return new C1363r(sb2.toString(), null, new C1365t[]{new C1365t((iArr2[1] + iArr2[0]) / 2.0f, f), new C1365t(iM1760c2 + (i9 / 2.0f), f)}, EnumC1138a.CODE_93);
                        }
                        iM1760c2 = iM1760c3;
                    }
                } else {
                    i3 += iArr[0] + iArr[1];
                    int i12 = i4 - 1;
                    System.arraycopy(iArr, 2, iArr, 0, i12);
                    iArr[i12] = 0;
                    iArr[i4] = 0;
                    i4--;
                }
                iArr[i4] = 1;
                z = !z;
            }
            iM1760c++;
        }
        throw C1358m.m2611a();
    }

    /* JADX INFO: renamed from: a */
    private int[] m2175a(C1190a c1190a) throws C1358m {
        int i = c1190a.f2065b;
        int iM1760c = c1190a.m1760c(0);
        Arrays.fill(this.f2495f, 0);
        int[] iArr = this.f2495f;
        int length = iArr.length;
        int i2 = iM1760c;
        boolean z = false;
        int i3 = 0;
        while (iM1760c < i) {
            if (c1190a.m1755a(iM1760c) != z) {
                iArr[i3] = iArr[i3] + 1;
            } else {
                if (i3 != length - 1) {
                    i3++;
                } else {
                    if (m2172a(iArr) == f2493d) {
                        return new int[]{i2, iM1760c};
                    }
                    i2 += iArr[0] + iArr[1];
                    int i4 = i3 - 1;
                    System.arraycopy(iArr, 2, iArr, 0, i4);
                    iArr[i4] = 0;
                    iArr[i3] = 0;
                    i3--;
                }
                iArr[i3] = 1;
                z = !z;
            }
            iM1760c++;
        }
        throw C1358m.m2611a();
    }
}
