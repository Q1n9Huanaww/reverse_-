package com.google.p028a.p047g;

import android.support.v7.widget.ActivityChooserView;
import com.cyjh.mobileanjian.ipc.share.proto.C0769a;
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

/* JADX INFO: renamed from: com.google.a.g.e */
/* JADX INFO: compiled from: Code39Reader.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1281e extends AbstractC1294r {

    /* JADX INFO: renamed from: a */
    static final String f2483a = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%";

    /* JADX INFO: renamed from: b */
    static final int[] f2484b = {52, 289, 97, 352, 49, UiMessage.CommandToUi.Command_Type.SET_TEXT_VALUE, 112, 37, 292, 100, 265, 73, 328, 25, 280, 88, 13, 268, 76, 28, C0769a.f1101aG, 67, 322, 19, 274, 82, 7, C0769a.f1104aJ, 70, 22, 385, 193, 448, 145, 400, UiMessage.CommandToUi.Command_Type.SET_TAB_VALUE, 133, 388, 196, 168, 162, 138, 42};

    /* JADX INFO: renamed from: c */
    static final int f2485c = 148;

    /* JADX INFO: renamed from: d */
    private final boolean f2486d;

    /* JADX INFO: renamed from: e */
    private final boolean f2487e;

    /* JADX INFO: renamed from: f */
    private final StringBuilder f2488f;

    /* JADX INFO: renamed from: g */
    private final int[] f2489g;

    public C1281e() {
        this(false);
    }

    public C1281e(boolean z) {
        this(z, (byte) 0);
    }

    private C1281e(boolean z, byte b) {
        this.f2486d = z;
        this.f2487e = false;
        this.f2488f = new StringBuilder(20);
        this.f2489g = new int[9];
    }

    @Override // com.google.p028a.p047g.AbstractC1294r
    /* JADX INFO: renamed from: a */
    public final C1363r mo2038a(int i, C1190a c1190a, Map<EnumC1233e, ?> map) throws C1210d, C1358m, C1303h {
        char cCharAt;
        String string;
        char c;
        int[] iArr = this.f2489g;
        Arrays.fill(iArr, 0);
        StringBuilder sb = this.f2488f;
        sb.setLength(0);
        int i2 = c1190a.f2065b;
        int iM1760c = c1190a.m1760c(0);
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
                } else if (m2166a(iArr) == f2485c && c1190a.m1756a(Math.max(0, i3 - ((iM1760c - i3) / 2)), i3)) {
                    int[] iArr2 = {i3, iM1760c};
                    int iM1760c2 = c1190a.m1760c(iArr2[1]);
                    int i5 = c1190a.f2065b;
                    while (true) {
                        m2193a(c1190a, iM1760c2, iArr);
                        int iM2166a = m2166a(iArr);
                        if (iM2166a < 0) {
                            throw C1358m.m2611a();
                        }
                        int i6 = 0;
                        while (true) {
                            if (i6 >= f2484b.length) {
                                if (iM2166a != f2485c) {
                                    throw C1358m.m2611a();
                                }
                                cCharAt = '*';
                                break;
                            }
                            if (f2484b[i6] == iM2166a) {
                                cCharAt = f2483a.charAt(i6);
                                break;
                            }
                            i6++;
                        }
                        sb.append(cCharAt);
                        int i7 = iM1760c2;
                        for (int i8 : iArr) {
                            i7 += i8;
                        }
                        int iM1760c3 = c1190a.m1760c(i7);
                        if (cCharAt == '*') {
                            sb.setLength(sb.length() - 1);
                            int i9 = 0;
                            for (int i10 : iArr) {
                                i9 += i10;
                            }
                            int i11 = (iM1760c3 - iM1760c2) - i9;
                            if (iM1760c3 != i5 && (i11 << 1) < i9) {
                                throw C1358m.m2611a();
                            }
                            char c2 = '+';
                            if (this.f2486d) {
                                int length2 = sb.length() - 1;
                                int iIndexOf = 0;
                                for (int i12 = 0; i12 < length2; i12++) {
                                    iIndexOf += f2483a.indexOf(this.f2488f.charAt(i12));
                                }
                                if (sb.charAt(length2) != f2483a.charAt(iIndexOf % 43)) {
                                    throw C1210d.m1865a();
                                }
                                sb.setLength(length2);
                            }
                            if (sb.length() == 0) {
                                throw C1358m.m2611a();
                            }
                            if (!this.f2487e) {
                                string = sb.toString();
                            } else {
                                int length3 = sb.length();
                                StringBuilder sb2 = new StringBuilder(length3);
                                int i13 = 0;
                                while (i13 < length3) {
                                    char cCharAt2 = sb.charAt(i13);
                                    if (cCharAt2 == c2 || cCharAt2 == '$' || cCharAt2 == '%' || cCharAt2 == '/') {
                                        i13++;
                                        char cCharAt3 = sb.charAt(i13);
                                        if (cCharAt2 == c2) {
                                            if (cCharAt3 < 'A' || cCharAt3 > 'Z') {
                                                throw C1303h.m2217a();
                                            }
                                            c = (char) (cCharAt3 + ' ');
                                        } else if (cCharAt2 != '/') {
                                            switch (cCharAt2) {
                                                case '$':
                                                    if (cCharAt3 < 'A' || cCharAt3 > 'Z') {
                                                        throw C1303h.m2217a();
                                                    }
                                                    c = (char) (cCharAt3 - '@');
                                                    break;
                                                    break;
                                                case '%':
                                                    if (cCharAt3 >= 'A' && cCharAt3 <= 'E') {
                                                        c = (char) (cCharAt3 - '&');
                                                        break;
                                                    } else if (cCharAt3 >= 'F' && cCharAt3 <= 'J') {
                                                        c = (char) (cCharAt3 - 11);
                                                        break;
                                                    } else if (cCharAt3 >= 'K' && cCharAt3 <= 'O') {
                                                        c = (char) (cCharAt3 + 16);
                                                        break;
                                                    } else if (cCharAt3 >= 'P' && cCharAt3 <= 'T') {
                                                        c = (char) (cCharAt3 + '+');
                                                        break;
                                                    } else if (cCharAt3 != 'U') {
                                                        if (cCharAt3 == 'V') {
                                                            c = '@';
                                                        } else if (cCharAt3 != 'W') {
                                                            if (cCharAt3 != 'X' && cCharAt3 != 'Y' && cCharAt3 != 'Z') {
                                                                throw C1303h.m2217a();
                                                            }
                                                            c = 127;
                                                        } else {
                                                            c = '`';
                                                        }
                                                        break;
                                                    }
                                                default:
                                                    c = 0;
                                                    break;
                                            }
                                        } else if (cCharAt3 >= 'A' && cCharAt3 <= 'O') {
                                            c = (char) (cCharAt3 - ' ');
                                        } else {
                                            if (cCharAt3 != 'Z') {
                                                throw C1303h.m2217a();
                                            }
                                            c = ':';
                                        }
                                        sb2.append(c);
                                    } else {
                                        sb2.append(cCharAt2);
                                    }
                                    i13++;
                                    c2 = '+';
                                }
                                string = sb2.toString();
                            }
                            float f = i;
                            return new C1363r(string, null, new C1365t[]{new C1365t((iArr2[1] + iArr2[0]) / 2.0f, f), new C1365t(iM1760c2 + (i9 / 2.0f), f)}, EnumC1138a.CODE_39);
                        }
                        iM1760c2 = iM1760c3;
                    }
                } else {
                    i3 += iArr[0] + iArr[1];
                    int i14 = i4 - 1;
                    System.arraycopy(iArr, 2, iArr, 0, i14);
                    iArr[i14] = 0;
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
    private static int m2166a(int[] iArr) {
        int length = iArr.length;
        int i = 0;
        while (true) {
            int i2 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            for (int i3 : iArr) {
                if (i3 < i2 && i3 > i) {
                    i2 = i3;
                }
            }
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            for (int i7 = 0; i7 < length; i7++) {
                int i8 = iArr[i7];
                if (i8 > i2) {
                    i5 |= 1 << ((length - 1) - i7);
                    i4++;
                    i6 += i8;
                }
            }
            if (i4 == 3) {
                for (int i9 = 0; i9 < length && i4 > 0; i9++) {
                    int i10 = iArr[i9];
                    if (i10 > i2) {
                        i4--;
                        if ((i10 << 1) >= i6) {
                            return -1;
                        }
                    }
                }
                return i5;
            }
            if (i4 <= 3) {
                return -1;
            }
            i = i2;
        }
    }

    /* JADX INFO: renamed from: a */
    private static char m2165a(int i) throws C1358m {
        for (int i2 = 0; i2 < f2484b.length; i2++) {
            if (f2484b[i2] == i) {
                return f2483a.charAt(i2);
            }
        }
        if (i == f2485c) {
            return '*';
        }
        throw C1358m.m2611a();
    }

    /* JADX INFO: renamed from: a */
    private static String m2167a(CharSequence charSequence) throws C1303h {
        char c;
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        int i = 0;
        while (i < length) {
            char cCharAt = charSequence.charAt(i);
            if (cCharAt == '+' || cCharAt == '$' || cCharAt == '%' || cCharAt == '/') {
                i++;
                char cCharAt2 = charSequence.charAt(i);
                if (cCharAt == '+') {
                    if (cCharAt2 < 'A' || cCharAt2 > 'Z') {
                        throw C1303h.m2217a();
                    }
                    c = (char) (cCharAt2 + ' ');
                } else if (cCharAt != '/') {
                    switch (cCharAt) {
                        case '$':
                            if (cCharAt2 < 'A' || cCharAt2 > 'Z') {
                                throw C1303h.m2217a();
                            }
                            c = (char) (cCharAt2 - '@');
                            break;
                        case '%':
                            if (cCharAt2 >= 'A' && cCharAt2 <= 'E') {
                                c = (char) (cCharAt2 - '&');
                                break;
                            } else if (cCharAt2 >= 'F' && cCharAt2 <= 'J') {
                                c = (char) (cCharAt2 - 11);
                                break;
                            } else if (cCharAt2 >= 'K' && cCharAt2 <= 'O') {
                                c = (char) (cCharAt2 + 16);
                                break;
                            } else if (cCharAt2 >= 'P' && cCharAt2 <= 'T') {
                                c = (char) (cCharAt2 + '+');
                                break;
                            } else if (cCharAt2 != 'U') {
                                if (cCharAt2 == 'V') {
                                    c = '@';
                                } else if (cCharAt2 == 'W') {
                                    c = '`';
                                } else {
                                    if (cCharAt2 != 'X' && cCharAt2 != 'Y' && cCharAt2 != 'Z') {
                                        throw C1303h.m2217a();
                                    }
                                    c = 127;
                                }
                                break;
                            }
                        default:
                            c = 0;
                            break;
                    }
                } else if (cCharAt2 >= 'A' && cCharAt2 <= 'O') {
                    c = (char) (cCharAt2 - ' ');
                } else {
                    if (cCharAt2 != 'Z') {
                        throw C1303h.m2217a();
                    }
                    c = ':';
                }
                sb.append(c);
            } else {
                sb.append(cCharAt);
            }
            i++;
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: a */
    private static int[] m2168a(C1190a c1190a, int[] iArr) throws C1358m {
        int i = c1190a.f2065b;
        int iM1760c = c1190a.m1760c(0);
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
                    if (m2166a(iArr) == f2485c && c1190a.m1756a(Math.max(0, i2 - ((iM1760c - i2) / 2)), i2)) {
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
