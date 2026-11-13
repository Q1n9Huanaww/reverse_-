package com.google.p028a.p047g;

import com.google.p028a.AbstractC1362q;
import com.google.p028a.C1358m;
import com.google.p028a.C1363r;
import com.google.p028a.C1365t;
import com.google.p028a.EnumC1138a;
import com.google.p028a.EnumC1364s;
import com.google.p028a.p035c.C1190a;
import com.p024ds.daisi.mvp.managers.AccountManager;
import java.util.EnumMap;
import org.apache.commons.p092io.FilenameUtils;

/* JADX INFO: renamed from: com.google.a.g.x */
/* JADX INFO: compiled from: UPCEANExtensionSupport.java */
/* JADX INFO: loaded from: classes.dex */
final class C1300x {

    /* JADX INFO: renamed from: a */
    private static final int[] f2527a = {1, 1, 2};

    /* JADX INFO: renamed from: b */
    private final C1298v f2528b = new C1298v();

    /* JADX INFO: renamed from: c */
    private final C1299w f2529c = new C1299w();

    C1300x() {
    }

    /* JADX WARN: Code duplicated, block: B:70:0x0142  */
    /* JADX WARN: Code duplicated, block: B:71:0x0144 A[Catch: q -> 0x0185, TryCatch #0 {q -> 0x0185, blocks: (B:3:0x000f, B:7:0x0033, B:9:0x0046, B:12:0x0050, B:14:0x0058, B:15:0x0060, B:16:0x0066, B:18:0x006c, B:19:0x0070, B:22:0x0074, B:24:0x007a, B:26:0x0087, B:27:0x0092, B:29:0x0097, B:30:0x00a0, B:32:0x00a5, B:33:0x00a9, B:34:0x00aa, B:72:0x0150, B:74:0x0176, B:37:0x00b8, B:64:0x0108, B:66:0x011a, B:68:0x0129, B:71:0x0144, B:67:0x0125, B:44:0x00ca, B:46:0x00d4, B:57:0x00f5, B:48:0x00d8, B:51:0x00e2, B:54:0x00ec, B:76:0x017a, B:77:0x0180, B:78:0x0184), top: B:81:0x000f }] */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX INFO: renamed from: a */
    final C1363r m2210a(int i, C1190a c1190a, int i2) throws C1358m {
        String str;
        String str2;
        EnumMap enumMap;
        int[] iArrM2214a = AbstractC1301y.m2214a(c1190a, i2, false, f2527a);
        try {
            C1299w c1299w = this.f2529c;
            StringBuilder sb = c1299w.f2526c;
            sb.setLength(0);
            int[] iArr = c1299w.f2525b;
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            iArr[3] = 0;
            int i3 = c1190a.f2065b;
            int i4 = iArrM2214a[1];
            int i5 = 0;
            for (int i6 = 0; i6 < 5 && i4 < i3; i6++) {
                int iM2211a = AbstractC1301y.m2211a(c1190a, iArr, i4, AbstractC1301y.f2535f);
                sb.append((char) ((iM2211a % 10) + 48));
                int iM1762d = i4;
                for (int i7 : iArr) {
                    iM1762d += i7;
                }
                if (iM2211a >= 10) {
                    i5 = (1 << (4 - i6)) | i5;
                }
                if (i6 != 4) {
                    iM1762d = c1190a.m1762d(c1190a.m1760c(iM1762d));
                }
                i4 = iM1762d;
            }
            if (sb.length() != 5) {
                throw C1358m.m2611a();
            }
            for (int i8 = 0; i8 < 10; i8++) {
                if (i5 == C1299w.f2524a[i8]) {
                    String string = sb.toString();
                    int length = string.length();
                    int iCharAt = 0;
                    for (int i9 = length - 2; i9 >= 0; i9 -= 2) {
                        iCharAt += string.charAt(i9) - '0';
                    }
                    int iCharAt2 = iCharAt * 3;
                    for (int i10 = length - 1; i10 >= 0; i10 -= 2) {
                        iCharAt2 += string.charAt(i10) - '0';
                    }
                    if ((iCharAt2 * 3) % 10 == i8) {
                        String string2 = sb.toString();
                        if (string2.length() == 5) {
                            char cCharAt = string2.charAt(0);
                            if (cCharAt == '0') {
                                str = "£";
                            } else if (cCharAt == '5') {
                                str = "$";
                            } else if (cCharAt != '9') {
                                str = "";
                            } else {
                                byte b = -1;
                                int iHashCode = string2.hashCode();
                                if (iHashCode != 54118329) {
                                    switch (iHashCode) {
                                        case 54395376:
                                            if (string2.equals("99990")) {
                                                b = 2;
                                            }
                                            break;
                                        case 54395377:
                                            if (string2.equals("99991")) {
                                                b = 1;
                                            }
                                            break;
                                    }
                                } else if (string2.equals("90000")) {
                                    b = 0;
                                }
                                switch (b) {
                                    case 0:
                                        str2 = null;
                                        break;
                                    case 1:
                                        str2 = "0.00";
                                        break;
                                    case 2:
                                        str2 = "Used";
                                        break;
                                    default:
                                        str = "";
                                        break;
                                }
                                if (str2 == null) {
                                    enumMap = null;
                                } else {
                                    enumMap = new EnumMap(EnumC1364s.class);
                                    enumMap.put(EnumC1364s.SUGGESTED_PRICE, str2);
                                }
                            }
                            int i11 = Integer.parseInt(string2.substring(1));
                            String strValueOf = String.valueOf(i11 / 100);
                            int i12 = i11 % 100;
                            str2 = str + strValueOf + FilenameUtils.EXTENSION_SEPARATOR + (i12 < 10 ? AccountManager.TYPE_LOGIN.concat(String.valueOf(i12)) : String.valueOf(i12));
                            if (str2 == null) {
                                enumMap = null;
                            } else {
                                enumMap = new EnumMap(EnumC1364s.class);
                                enumMap.put(EnumC1364s.SUGGESTED_PRICE, str2);
                            }
                        } else {
                            enumMap = null;
                        }
                        float f = i;
                        C1363r c1363r = new C1363r(string2, null, new C1365t[]{new C1365t((iArrM2214a[0] + iArrM2214a[1]) / 2.0f, f), new C1365t(i4, f)}, EnumC1138a.UPC_EAN_EXTENSION);
                        if (enumMap != null) {
                            c1363r.m2625a(enumMap);
                        }
                        return c1363r;
                    }
                    throw C1358m.m2611a();
                }
            }
            throw C1358m.m2611a();
        } catch (AbstractC1362q unused) {
            return this.f2528b.m2203a(i, c1190a, iArrM2214a);
        }
    }
}
