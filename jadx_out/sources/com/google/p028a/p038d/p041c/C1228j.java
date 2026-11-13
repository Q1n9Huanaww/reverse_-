package com.google.p028a.p038d.p041c;

import android.support.v7.widget.ActivityChooserView;
import com.cyjh.mobileanjian.ipc.share.proto.C0769a;
import com.google.p028a.C1238f;
import java.util.Arrays;

/* JADX INFO: renamed from: com.google.a.d.c.j */
/* JADX INFO: compiled from: HighLevelEncoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1228j {

    /* JADX INFO: renamed from: a */
    static final char f2235a = 230;

    /* JADX INFO: renamed from: b */
    static final char f2236b = 231;

    /* JADX INFO: renamed from: c */
    static final char f2237c = 235;

    /* JADX INFO: renamed from: d */
    static final char f2238d = 238;

    /* JADX INFO: renamed from: e */
    static final char f2239e = 239;

    /* JADX INFO: renamed from: f */
    static final char f2240f = 240;

    /* JADX INFO: renamed from: g */
    static final char f2241g = 254;

    /* JADX INFO: renamed from: h */
    static final char f2242h = 254;

    /* JADX INFO: renamed from: i */
    static final int f2243i = 0;

    /* JADX INFO: renamed from: j */
    static final int f2244j = 1;

    /* JADX INFO: renamed from: k */
    static final int f2245k = 2;

    /* JADX INFO: renamed from: l */
    static final int f2246l = 3;

    /* JADX INFO: renamed from: m */
    static final int f2247m = 4;

    /* JADX INFO: renamed from: n */
    static final int f2248n = 5;

    /* JADX INFO: renamed from: o */
    private static final char f2249o = 129;

    /* JADX INFO: renamed from: p */
    private static final char f2250p = 236;

    /* JADX INFO: renamed from: q */
    private static final char f2251q = 237;

    /* JADX INFO: renamed from: r */
    private static final String f2252r = "[)>\u001e05\u001d";

    /* JADX INFO: renamed from: s */
    private static final String f2253s = "[)>\u001e06\u001d";

    /* JADX INFO: renamed from: t */
    private static final String f2254t = "\u001e\u0004";

    /* JADX INFO: renamed from: a */
    private static boolean m1979a() {
        return false;
    }

    /* JADX INFO: renamed from: a */
    static boolean m1980a(char c) {
        return c >= '0' && c <= '9';
    }

    /* JADX INFO: renamed from: b */
    static boolean m1981b(char c) {
        return c >= 128 && c <= 255;
    }

    /* JADX INFO: renamed from: d */
    private static boolean m1983d(char c) {
        if (c == ' ') {
            return true;
        }
        if (c < '0' || c > '9') {
            return c >= 'A' && c <= 'Z';
        }
        return true;
    }

    /* JADX INFO: renamed from: e */
    private static boolean m1984e(char c) {
        if (c == ' ') {
            return true;
        }
        if (c < '0' || c > '9') {
            return c >= 'a' && c <= 'z';
        }
        return true;
    }

    /* JADX INFO: renamed from: g */
    private static boolean m1986g(char c) {
        return c == '\r' || c == '*' || c == '>';
    }

    /* JADX INFO: renamed from: h */
    private static boolean m1987h(char c) {
        return c >= ' ' && c <= '^';
    }

    private C1228j() {
    }

    /* JADX INFO: renamed from: a */
    private static char m1972a(int i) {
        int i2 = ((i * 149) % 253) + 1 + C0769a.f1097aC;
        if (i2 > 254) {
            i2 -= 254;
        }
        return (char) i2;
    }

    /* JADX INFO: renamed from: a */
    private static String m1977a(String str) {
        return m1978a(str, EnumC1230l.FORCE_NONE, (C1238f) null, (C1238f) null);
    }

    /* JADX INFO: renamed from: a */
    public static String m1978a(String str, EnumC1230l enumC1230l, C1238f c1238f, C1238f c1238f2) {
        int i = 0;
        InterfaceC1225g[] interfaceC1225gArr = {new C1219a(), new C1221c(), new C1231m(), new C1232n(), new C1224f(), new C1220b()};
        C1226h c1226h = new C1226h(str);
        c1226h.f2222b = enumC1230l;
        c1226h.f2223c = c1238f;
        c1226h.f2224d = c1238f2;
        if (str.startsWith(f2252r) && str.endsWith(f2254t)) {
            c1226h.m1963a(f2250p);
            c1226h.f2229i = 2;
            c1226h.f2226f += 7;
        } else if (str.startsWith(f2253s) && str.endsWith(f2254t)) {
            c1226h.m1963a(f2251q);
            c1226h.f2229i = 2;
            c1226h.f2226f += 7;
        }
        while (c1226h.m1966b()) {
            interfaceC1225gArr[i].mo1924a(c1226h);
            if (c1226h.f2227g >= 0) {
                i = c1226h.f2227g;
                c1226h.f2227g = -1;
            }
        }
        int length = c1226h.f2225e.length();
        c1226h.m1968d();
        int i2 = c1226h.f2228h.f2257b;
        if (length < i2 && i != 0 && i != 5 && i != 4) {
            c1226h.m1963a((char) 254);
        }
        StringBuilder sb = c1226h.f2225e;
        if (sb.length() < i2) {
            sb.append(f2249o);
        }
        while (sb.length() < i2) {
            int length2 = (((sb.length() + 1) * 149) % 253) + 1 + C0769a.f1097aC;
            if (length2 > 254) {
                length2 -= 254;
            }
            sb.append((char) length2);
        }
        return c1226h.f2225e.toString();
    }

    /* JADX INFO: renamed from: a */
    static int m1974a(CharSequence charSequence, int i, int i2) {
        float[] fArr;
        if (i >= charSequence.length()) {
            return i2;
        }
        if (i2 == 0) {
            fArr = new float[]{0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.25f};
        } else {
            float[] fArr2 = {1.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.25f};
            fArr2[i2] = 0.0f;
            fArr = fArr2;
        }
        int i3 = 0;
        while (true) {
            int i4 = i + i3;
            if (i4 == charSequence.length()) {
                byte[] bArr = new byte[6];
                int[] iArr = new int[6];
                int iM1976a = m1976a(fArr, iArr, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, bArr);
                int iM1975a = m1975a(bArr);
                if (iArr[0] == iM1976a) {
                    return 0;
                }
                if (iM1975a == 1 && bArr[5] > 0) {
                    return 5;
                }
                if (iM1975a == 1 && bArr[4] > 0) {
                    return 4;
                }
                if (iM1975a != 1 || bArr[2] <= 0) {
                    return (iM1975a != 1 || bArr[3] <= 0) ? 1 : 3;
                }
                return 2;
            }
            char cCharAt = charSequence.charAt(i4);
            i3++;
            if (m1980a(cCharAt)) {
                fArr[0] = fArr[0] + 0.5f;
            } else if (m1981b(cCharAt)) {
                fArr[0] = (float) Math.ceil(fArr[0]);
                fArr[0] = fArr[0] + 2.0f;
            } else {
                fArr[0] = (float) Math.ceil(fArr[0]);
                fArr[0] = fArr[0] + 1.0f;
            }
            if (cCharAt == ' ' || (cCharAt >= '0' && cCharAt <= '9') || (cCharAt >= 'A' && cCharAt <= 'Z')) {
                fArr[1] = fArr[1] + 0.6666667f;
            } else if (m1981b(cCharAt)) {
                fArr[1] = fArr[1] + 2.6666667f;
            } else {
                fArr[1] = fArr[1] + 1.3333334f;
            }
            if (cCharAt == ' ' || (cCharAt >= '0' && cCharAt <= '9') || (cCharAt >= 'a' && cCharAt <= 'z')) {
                fArr[2] = fArr[2] + 0.6666667f;
            } else if (m1981b(cCharAt)) {
                fArr[2] = fArr[2] + 2.6666667f;
            } else {
                fArr[2] = fArr[2] + 1.3333334f;
            }
            if (m1985f(cCharAt)) {
                fArr[3] = fArr[3] + 0.6666667f;
            } else if (m1981b(cCharAt)) {
                fArr[3] = fArr[3] + 4.3333335f;
            } else {
                fArr[3] = fArr[3] + 3.3333333f;
            }
            if (cCharAt >= ' ' && cCharAt <= '^') {
                fArr[4] = fArr[4] + 0.75f;
            } else if (m1981b(cCharAt)) {
                fArr[4] = fArr[4] + 4.25f;
            } else {
                fArr[4] = fArr[4] + 3.25f;
            }
            fArr[5] = fArr[5] + 1.0f;
            if (i3 >= 4) {
                int[] iArr2 = new int[6];
                byte[] bArr2 = new byte[6];
                m1976a(fArr, iArr2, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, bArr2);
                int iM1975a2 = m1975a(bArr2);
                if (iArr2[0] < iArr2[5] && iArr2[0] < iArr2[1] && iArr2[0] < iArr2[2] && iArr2[0] < iArr2[3] && iArr2[0] < iArr2[4]) {
                    return 0;
                }
                if (iArr2[5] < iArr2[0] || bArr2[1] + bArr2[2] + bArr2[3] + bArr2[4] == 0) {
                    return 5;
                }
                if (iM1975a2 == 1 && bArr2[4] > 0) {
                    return 4;
                }
                if (iM1975a2 == 1 && bArr2[2] > 0) {
                    return 2;
                }
                if (iM1975a2 == 1 && bArr2[3] > 0) {
                    return 3;
                }
                if (iArr2[1] + 1 < iArr2[0] && iArr2[1] + 1 < iArr2[5] && iArr2[1] + 1 < iArr2[4] && iArr2[1] + 1 < iArr2[2]) {
                    if (iArr2[1] < iArr2[3]) {
                        return 1;
                    }
                    if (iArr2[1] == iArr2[3]) {
                        for (int i5 = i + i3 + 1; i5 < charSequence.length(); i5++) {
                            char cCharAt2 = charSequence.charAt(i5);
                            if (m1986g(cCharAt2)) {
                                return 3;
                            }
                            if (!m1985f(cCharAt2)) {
                                break;
                            }
                        }
                        return 1;
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private static int m1976a(float[] fArr, int[] iArr, int i, byte[] bArr) {
        Arrays.fill(bArr, (byte) 0);
        int i2 = i;
        for (int i3 = 0; i3 < 6; i3++) {
            iArr[i3] = (int) Math.ceil(fArr[i3]);
            int i4 = iArr[i3];
            if (i2 > i4) {
                Arrays.fill(bArr, (byte) 0);
                i2 = i4;
            }
            if (i2 == i4) {
                bArr[i3] = (byte) (bArr[i3] + 1);
            }
        }
        return i2;
    }

    /* JADX INFO: renamed from: a */
    private static int m1975a(byte[] bArr) {
        int i = 0;
        for (int i2 = 0; i2 < 6; i2++) {
            i += bArr[i2];
        }
        return i;
    }

    /* JADX INFO: renamed from: f */
    private static boolean m1985f(char c) {
        if (m1986g(c) || c == ' ') {
            return true;
        }
        if (c < '0' || c > '9') {
            return c >= 'A' && c <= 'Z';
        }
        return true;
    }

    /* JADX INFO: renamed from: a */
    private static int m1973a(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        if (i < length) {
            char cCharAt = charSequence.charAt(i);
            while (m1980a(cCharAt) && i < length) {
                i2++;
                i++;
                if (i < length) {
                    cCharAt = charSequence.charAt(i);
                }
            }
        }
        return i2;
    }

    /* JADX INFO: renamed from: c */
    static void m1982c(char c) {
        String hexString = Integer.toHexString(c);
        throw new IllegalArgumentException("Illegal character: " + c + " (0x" + ("0000".substring(0, 4 - hexString.length()) + hexString) + ')');
    }
}
