package com.google.p028a.p047g;

import com.google.p028a.C1358m;
import com.google.p028a.C1363r;
import com.google.p028a.C1365t;
import com.google.p028a.EnumC1138a;
import com.google.p028a.EnumC1364s;
import com.google.p028a.p035c.C1190a;
import com.p024ds.daisi.mvp.managers.AccountManager;
import java.util.EnumMap;
import java.util.Map;
import org.apache.commons.p092io.FilenameUtils;

/* JADX INFO: renamed from: com.google.a.g.w */
/* JADX INFO: compiled from: UPCEANExtension5Support.java */
/* JADX INFO: loaded from: classes.dex */
final class C1299w {

    /* JADX INFO: renamed from: a */
    static final int[] f2524a = {24, 20, 18, 17, 12, 6, 3, 10, 9, 5};

    /* JADX INFO: renamed from: b */
    final int[] f2525b = new int[4];

    /* JADX INFO: renamed from: c */
    final StringBuilder f2526c = new StringBuilder();

    C1299w() {
    }

    /* JADX WARN: Code duplicated, block: B:35:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:70:0x0133  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX INFO: renamed from: a */
    private C1363r m2207a(int i, C1190a c1190a, int[] iArr) throws C1358m {
        String str;
        String str2;
        EnumMap enumMap;
        StringBuilder sb = this.f2526c;
        sb.setLength(0);
        int[] iArr2 = this.f2525b;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int i2 = c1190a.f2065b;
        int i3 = iArr[1];
        int i4 = 0;
        for (int i5 = 0; i5 < 5 && i3 < i2; i5++) {
            int iM2211a = AbstractC1301y.m2211a(c1190a, iArr2, i3, AbstractC1301y.f2535f);
            sb.append((char) ((iM2211a % 10) + 48));
            int iM1762d = i3;
            for (int i6 : iArr2) {
                iM1762d += i6;
            }
            if (iM2211a >= 10) {
                i4 |= 1 << (4 - i5);
            }
            if (i5 != 4) {
                iM1762d = c1190a.m1762d(c1190a.m1760c(iM1762d));
            }
            i3 = iM1762d;
        }
        if (sb.length() != 5) {
            throw C1358m.m2611a();
        }
        for (int i7 = 0; i7 < 10; i7++) {
            if (i4 == f2524a[i7]) {
                String string = sb.toString();
                int length = string.length();
                int iCharAt = 0;
                for (int i8 = length - 2; i8 >= 0; i8 -= 2) {
                    iCharAt += string.charAt(i8) - '0';
                }
                int iCharAt2 = iCharAt * 3;
                for (int i9 = length - 1; i9 >= 0; i9 -= 2) {
                    iCharAt2 += string.charAt(i9) - '0';
                }
                if ((iCharAt2 * 3) % 10 == i7) {
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
                        int i10 = Integer.parseInt(string2.substring(1));
                        String strValueOf = String.valueOf(i10 / 100);
                        int i11 = i10 % 100;
                        str2 = str + strValueOf + FilenameUtils.EXTENSION_SEPARATOR + (i11 < 10 ? AccountManager.TYPE_LOGIN.concat(String.valueOf(i11)) : String.valueOf(i11));
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
                    C1363r c1363r = new C1363r(string2, null, new C1365t[]{new C1365t((iArr[0] + iArr[1]) / 2.0f, f), new C1365t(i3, f)}, EnumC1138a.UPC_EAN_EXTENSION);
                    if (enumMap != null) {
                        c1363r.m2625a(enumMap);
                    }
                    return c1363r;
                }
                throw C1358m.m2611a();
            }
        }
        throw C1358m.m2611a();
    }

    /* JADX INFO: renamed from: a */
    private int m2205a(C1190a c1190a, int[] iArr, StringBuilder sb) throws C1358m {
        int[] iArr2 = this.f2525b;
        int iCharAt = 0;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int i = c1190a.f2065b;
        int iM1762d = iArr[1];
        int i2 = 0;
        int i3 = 0;
        while (i2 < 5 && iM1762d < i) {
            int iM2211a = AbstractC1301y.m2211a(c1190a, iArr2, iM1762d, AbstractC1301y.f2535f);
            sb.append((char) ((iM2211a % 10) + 48));
            int i4 = iM1762d;
            for (int i5 : iArr2) {
                i4 += i5;
            }
            if (iM2211a >= 10) {
                i3 |= 1 << (4 - i2);
            }
            iM1762d = i2 != 4 ? c1190a.m1762d(c1190a.m1760c(i4)) : i4;
            i2++;
        }
        if (sb.length() != 5) {
            throw C1358m.m2611a();
        }
        for (int i6 = 0; i6 < 10; i6++) {
            if (i3 == f2524a[i6]) {
                String string = sb.toString();
                int length = string.length();
                for (int i7 = length - 2; i7 >= 0; i7 -= 2) {
                    iCharAt += string.charAt(i7) - '0';
                }
                int iCharAt2 = iCharAt * 3;
                for (int i8 = length - 1; i8 >= 0; i8 -= 2) {
                    iCharAt2 += string.charAt(i8) - '0';
                }
                if ((iCharAt2 * 3) % 10 != i6) {
                    throw C1358m.m2611a();
                }
                return iM1762d;
            }
        }
        throw C1358m.m2611a();
    }

    /* JADX INFO: renamed from: a */
    private static int m2206a(CharSequence charSequence) {
        int length = charSequence.length();
        int iCharAt = 0;
        for (int i = length - 2; i >= 0; i -= 2) {
            iCharAt += charSequence.charAt(i) - '0';
        }
        int iCharAt2 = iCharAt * 3;
        for (int i2 = length - 1; i2 >= 0; i2 -= 2) {
            iCharAt2 += charSequence.charAt(i2) - '0';
        }
        return (iCharAt2 * 3) % 10;
    }

    /* JADX INFO: renamed from: a */
    private static int m2204a(int i) throws C1358m {
        for (int i2 = 0; i2 < 10; i2++) {
            if (i == f2524a[i2]) {
                return i2;
            }
        }
        throw C1358m.m2611a();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:25:0x0049  */
    /* JADX WARN: Code duplicated, block: B:39:0x0099 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:40:0x009a  */
    /* JADX WARN: Failed to clean up code after switch over string restore
    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r3v6 int, still in use, count: 1, list:
  (r3v6 int) from 0x0026: IF  (r3v6 int) != (54118329 int)  -> B:14:0x0028 A[HIDDEN]
    	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
    	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
    	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:93)
    	at jadx.core.utils.InsnRemover.remove(InsnRemover.java:226)
    	at jadx.core.utils.InsnRemover.remove(InsnRemover.java:215)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.replaceWithMergedSwitch(SwitchOverStringVisitor.java:355)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:111)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:72)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:140)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:47)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visit(SwitchOverStringVisitor.java:66)
     */
    /* JADX INFO: renamed from: a */
    private static Map<EnumC1364s, Object> m2208a(String str) {
        String str2;
        String str3;
        if (str.length() != 5) {
            return null;
        }
        byte b = 0;
        char cCharAt = str.charAt(0);
        if (cCharAt == '0') {
            str2 = "£";
        } else if (cCharAt == '5') {
            str2 = "$";
        } else {
            if (cCharAt == '9') {
                if (str.hashCode() != 54118329) {
                    switch (str) {
                        case "99990":
                            b = 2;
                            break;
                        case "99991":
                            b = 1;
                            break;
                        default:
                            b = -1;
                            break;
                    }
                } else if (!str.equals("90000")) {
                    b = -1;
                }
                switch (b) {
                    case 0:
                        str3 = null;
                        break;
                    case 1:
                        str3 = "0.00";
                        break;
                    case 2:
                        str3 = "Used";
                        break;
                    default:
                        str2 = "";
                        break;
                }
                if (str3 == null) {
                    return null;
                }
                EnumMap enumMap = new EnumMap(EnumC1364s.class);
                enumMap.put(EnumC1364s.SUGGESTED_PRICE, str3);
                return enumMap;
            }
            str2 = "";
        }
        int i = Integer.parseInt(str.substring(1));
        String strValueOf = String.valueOf(i / 100);
        int i2 = i % 100;
        str3 = str2 + strValueOf + FilenameUtils.EXTENSION_SEPARATOR + (i2 < 10 ? AccountManager.TYPE_LOGIN.concat(String.valueOf(i2)) : String.valueOf(i2));
        if (str3 == null) {
            return null;
        }
        EnumMap enumMap2 = new EnumMap(EnumC1364s.class);
        enumMap2.put(EnumC1364s.SUGGESTED_PRICE, str3);
        return enumMap2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:22:0x0040  */
    /* JADX WARN: Failed to clean up code after switch over string restore
    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r2v6 int, still in use, count: 1, list:
  (r2v6 int) from 0x001d: IF  (r2v6 int) != (54118329 int)  -> B:11:0x001f A[HIDDEN]
    	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
    	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
    	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:93)
    	at jadx.core.utils.InsnRemover.remove(InsnRemover.java:226)
    	at jadx.core.utils.InsnRemover.remove(InsnRemover.java:215)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.replaceWithMergedSwitch(SwitchOverStringVisitor.java:355)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:111)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:72)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:140)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:47)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visit(SwitchOverStringVisitor.java:66)
     */
    /* JADX INFO: renamed from: b */
    private static String m2209b(String str) {
        String str2;
        byte b = 0;
        char cCharAt = str.charAt(0);
        if (cCharAt == '0') {
            str2 = "£";
        } else if (cCharAt == '5') {
            str2 = "$";
        } else if (cCharAt == '9') {
            if (str.hashCode() != 54118329) {
                switch (str) {
                    case "99990":
                        b = 2;
                        break;
                    case "99991":
                        b = 1;
                        break;
                    default:
                        b = -1;
                        break;
                }
            } else if (!str.equals("90000")) {
                b = -1;
            }
            switch (b) {
                case 0:
                    return null;
                case 1:
                    return "0.00";
                case 2:
                    return "Used";
                default:
                    str2 = "";
                    break;
            }
        } else {
            str2 = "";
        }
        int i = Integer.parseInt(str.substring(1));
        String strValueOf = String.valueOf(i / 100);
        int i2 = i % 100;
        return str2 + strValueOf + FilenameUtils.EXTENSION_SEPARATOR + (i2 < 10 ? AccountManager.TYPE_LOGIN.concat(String.valueOf(i2)) : String.valueOf(i2));
    }
}
