package com.google.p028a.p035c;

import com.google.p028a.EnumC1233e;
import java.nio.charset.Charset;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.c.l */
/* JADX INFO: compiled from: StringUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1209l {

    /* JADX INFO: renamed from: a */
    public static final String f2170a = "SJIS";

    /* JADX INFO: renamed from: b */
    public static final String f2171b = "GB2312";

    /* JADX INFO: renamed from: c */
    private static final String f2172c = Charset.defaultCharset().name();

    /* JADX INFO: renamed from: d */
    private static final String f2173d = "EUC_JP";

    /* JADX INFO: renamed from: e */
    private static final String f2174e = "UTF8";

    /* JADX INFO: renamed from: f */
    private static final String f2175f = "ISO8859_1";

    /* JADX INFO: renamed from: g */
    private static final boolean f2176g;

    static {
        f2176g = f2170a.equalsIgnoreCase(f2172c) || f2173d.equalsIgnoreCase(f2172c);
    }

    private C1209l() {
    }

    /* JADX WARN: Code duplicated, block: B:42:0x0081 A[PHI: r10
  0x0081: PHI (r10v6 int) = (r10v1 int), (r10v5 int), (r10v1 int) binds: [B:32:0x0064, B:40:0x007c, B:27:0x0059] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:68:0x00b4  */
    /* JADX INFO: renamed from: a */
    public static String m1864a(byte[] bArr, Map<EnumC1233e, ?> map) {
        byte[] bArr2 = bArr;
        if (map != null && map.containsKey(EnumC1233e.CHARACTER_SET)) {
            return map.get(EnumC1233e.CHARACTER_SET).toString();
        }
        int length = bArr2.length;
        int i = 0;
        boolean z = bArr2.length > 3 && bArr2[0] == -17 && bArr2[1] == -69 && bArr2[2] == -65;
        int i2 = 0;
        int i3 = 0;
        boolean z2 = true;
        boolean z3 = true;
        boolean z4 = true;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        while (i3 < length && (z2 || z3 || z4)) {
            int i13 = bArr2[i3] & 255;
            if (z4) {
                if (i4 > 0) {
                    if ((i13 & 128) != 0) {
                        i4--;
                    } else {
                        z4 = false;
                    }
                } else if ((i13 & 128) != 0) {
                    if ((i13 & 64) != 0) {
                        i4++;
                        if ((i13 & 32) == 0) {
                            i6++;
                        } else {
                            i4++;
                            if ((i13 & 16) == 0) {
                                i7++;
                            } else {
                                i4++;
                                if ((i13 & 8) == 0) {
                                    i8++;
                                } else {
                                    z4 = false;
                                }
                            }
                        }
                    } else {
                        z4 = false;
                    }
                }
            }
            if (z2) {
                if (i13 > 127 && i13 < 160) {
                    z2 = false;
                } else if (i13 > 159 && (i13 < 192 || i13 == 215 || i13 == 247)) {
                    i10++;
                }
            }
            if (z3) {
                if (i5 > 0) {
                    if (i13 < 64 || i13 == 127 || i13 > 252) {
                        z3 = false;
                    } else {
                        i5--;
                    }
                } else if (i13 == 128 || i13 == 160 || i13 > 239) {
                    z3 = false;
                } else {
                    if (i13 > 160 && i13 < 224) {
                        i2++;
                        int i14 = i11 + 1;
                        if (i14 > i9) {
                            i9 = i14;
                            i11 = i9;
                        } else {
                            i11 = i14;
                        }
                    } else if (i13 > 127) {
                        i5++;
                        int i15 = i12 + 1;
                        if (i15 > i) {
                            i = i15;
                            i12 = i;
                        } else {
                            i12 = i15;
                        }
                        i11 = 0;
                    } else {
                        i11 = 0;
                    }
                    i12 = 0;
                }
            }
            i3++;
            bArr2 = bArr;
        }
        if (z4 && i4 > 0) {
            z4 = false;
        }
        boolean z5 = (!z3 || i5 <= 0) ? z3 : false;
        if (z4 && (z || i6 + i7 + i8 > 0)) {
            return f2174e;
        }
        if (z5 && (f2176g || i9 >= 3 || i >= 3)) {
            return f2170a;
        }
        if (z2 && z5) {
            return (!(i9 == 2 && i2 == 2) && i10 * 10 < length) ? f2175f : f2170a;
        }
        if (z2) {
            return f2175f;
        }
        if (z5) {
            return f2170a;
        }
        return z4 ? f2174e : f2172c;
    }
}
