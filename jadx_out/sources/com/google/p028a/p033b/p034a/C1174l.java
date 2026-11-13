package com.google.p028a.p033b.p034a;

import com.google.p028a.C1363r;
import com.google.p028a.EnumC1138a;
import com.umeng.commonsdk.proguard.C1770bg;
import java.util.HashMap;

/* JADX INFO: renamed from: com.google.a.b.a.l */
/* JADX INFO: compiled from: ExpandedProductResultParser.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1174l extends AbstractC1183u {
    /* JADX INFO: renamed from: a */
    private static String m1688a(int i, String str) {
        if (str.charAt(i) != '(') {
            return null;
        }
        String strSubstring = str.substring(i + 1);
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < strSubstring.length(); i2++) {
            char cCharAt = strSubstring.charAt(i2);
            if (cCharAt == ')') {
                return sb.toString();
            }
            if (cCharAt < '0' || cCharAt > '9') {
                return null;
            }
            sb.append(cCharAt);
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: b */
    private static String m1689b(int i, String str) {
        StringBuilder sb = new StringBuilder();
        String strSubstring = str.substring(i);
        for (int i2 = 0; i2 < strSubstring.length(); i2++) {
            char cCharAt = strSubstring.charAt(i2);
            if (cCharAt == '(') {
                if (m1688a(i2, strSubstring) != null) {
                    break;
                }
                sb.append('(');
            } else {
                sb.append(cCharAt);
            }
        }
        return sb.toString();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:129:0x0202  */
    /* JADX WARN: Failed to clean up code after switch over string restore
    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r3v2 int, still in use, count: 8, list:
  (r3v2 int) from 0x0052: IF  (r3v2 int) != (1570 int)  -> B:13:0x0054 A[HIDDEN]
  (r3v2 int) from 0x0056: IF  (r3v2 int) != (1572 int)  -> B:15:0x0058 A[HIDDEN]
  (r3v2 int) from 0x005a: IF  (r3v2 int) != (1574 int)  -> B:17:0x005c A[HIDDEN]
  (r3v2 int) from 0x005f: SWITCH (r3v2 int)
 case 1567: goto B:111:0x01c6
 case 1568: goto B:108:0x01bc
 default: goto B:19:0x0062 A[RegionRef:SW:18]
  (r3v2 int) from 0x0062: SWITCH (r3v2 int)
 case 1567966: goto B:105:0x01b2
 case 1567967: goto B:102:0x01a7
 case 1567968: goto B:99:0x019c
 case 1567969: goto B:96:0x0190
 case 1567970: goto B:93:0x0184
 case 1567971: goto B:90:0x0178
 case 1567972: goto B:87:0x016c
 case 1567973: goto B:84:0x0160
 case 1567974: goto B:81:0x0154
 case 1567975: goto B:78:0x0148
 default: goto B:20:0x0065 A[RegionRef:SW:19]
  (r3v2 int) from 0x0065: SWITCH (r3v2 int)
 case 1568927: goto B:75:0x013c
 case 1568928: goto B:72:0x0130
 case 1568929: goto B:69:0x0124
 case 1568930: goto B:66:0x0118
 case 1568931: goto B:63:0x010c
 case 1568932: goto B:60:0x0100
 case 1568933: goto B:57:0x00f4
 case 1568934: goto B:54:0x00e8
 case 1568935: goto B:51:0x00dc
 case 1568936: goto B:48:0x00d0
 default: goto B:21:0x0068 A[RegionRef:SW:20]
  (r3v2 int) from 0x0068: SWITCH (r3v2 int)
 case 1575716: goto B:45:0x00c4
 case 1575717: goto B:42:0x00b8
 case 1575718: goto B:39:0x00ac
 case 1575719: goto B:36:0x00a0
 default: goto B:22:0x006b A[RegionRef:SW:21]
  (r3v2 int) from 0x006b: SWITCH (r3v2 int)
 case 1575747: goto B:33:0x0094
 case 1575748: goto B:30:0x0088
 case 1575749: goto B:27:0x007c
 case 1575750: goto B:24:0x0070
 default: goto B:129:0x0202 A[RegionRef:SW:22]
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
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX INFO: renamed from: c */
    private static C1173k m1690c(C1363r c1363r) {
        byte b;
        C1173k c1173k;
        C1173k c1173k2;
        C1173k c1173k3 = null;
        if (c1363r.f2843e != EnumC1138a.RSS_EXPANDED) {
            return null;
        }
        String strB = m1715b(c1363r);
        HashMap map = new HashMap();
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String strSubstring = null;
        String strSubstring2 = null;
        String strSubstring3 = null;
        String strSubstring4 = null;
        int length = 0;
        while (length < strB.length()) {
            String strM1688a = m1688a(length, strB);
            if (strM1688a == null) {
                return c1173k3;
            }
            int length2 = length + strM1688a.length() + 2;
            String strM1689b = m1689b(length2, strB);
            length = length2 + strM1689b.length();
            String str10 = strSubstring;
            if (iHashCode != 1570) {
                if (iHashCode != 1572) {
                    if (iHashCode != 1574) {
                        switch (strM1688a) {
                            case "00":
                                b = 0;
                                break;
                            case "01":
                                b = 1;
                                break;
                            default:
                                switch (strM1688a) {
                                    case 1567:
                                        if (!strM1688a.equals("10")) {
                                            b = -1;
                                        } else {
                                            b = 2;
                                        }
                                        break;
                                    case 1568:
                                        if (!strM1688a.equals("11")) {
                                            b = -1;
                                        } else {
                                            b = 3;
                                        }
                                        break;
                                    default:
                                        switch (strM1688a) {
                                            case 1567966:
                                                if (!strM1688a.equals("3100")) {
                                                    b = -1;
                                                } else {
                                                    b = 7;
                                                }
                                                break;
                                            case 1567967:
                                                if (!strM1688a.equals("3101")) {
                                                    b = -1;
                                                } else {
                                                    b = 8;
                                                }
                                                break;
                                            case 1567968:
                                                if (!strM1688a.equals("3102")) {
                                                    b = -1;
                                                } else {
                                                    b = 9;
                                                }
                                                break;
                                            case 1567969:
                                                if (!strM1688a.equals("3103")) {
                                                    b = -1;
                                                } else {
                                                    b = 10;
                                                }
                                                break;
                                            case 1567970:
                                                if (!strM1688a.equals("3104")) {
                                                    b = -1;
                                                } else {
                                                    b = 11;
                                                }
                                                break;
                                            case 1567971:
                                                if (!strM1688a.equals("3105")) {
                                                    b = -1;
                                                } else {
                                                    b = 12;
                                                }
                                                break;
                                            case 1567972:
                                                if (!strM1688a.equals("3106")) {
                                                    b = -1;
                                                } else {
                                                    b = C1770bg.f4123k;
                                                }
                                                break;
                                            case 1567973:
                                                if (!strM1688a.equals("3107")) {
                                                    b = -1;
                                                } else {
                                                    b = C1770bg.f4124l;
                                                }
                                                break;
                                            case 1567974:
                                                if (!strM1688a.equals("3108")) {
                                                    b = -1;
                                                } else {
                                                    b = C1770bg.f4125m;
                                                }
                                                break;
                                            case 1567975:
                                                if (!strM1688a.equals("3109")) {
                                                    b = -1;
                                                } else {
                                                    b = C1770bg.f4126n;
                                                }
                                                break;
                                            default:
                                                switch (strM1688a) {
                                                    case 1568927:
                                                        if (!strM1688a.equals("3200")) {
                                                            b = -1;
                                                        } else {
                                                            b = 17;
                                                        }
                                                        break;
                                                    case 1568928:
                                                        if (!strM1688a.equals("3201")) {
                                                            b = -1;
                                                        } else {
                                                            b = 18;
                                                        }
                                                        break;
                                                    case 1568929:
                                                        if (!strM1688a.equals("3202")) {
                                                            b = -1;
                                                        } else {
                                                            b = 19;
                                                        }
                                                        break;
                                                    case 1568930:
                                                        if (!strM1688a.equals("3203")) {
                                                            b = -1;
                                                        } else {
                                                            b = 20;
                                                        }
                                                        break;
                                                    case 1568931:
                                                        if (!strM1688a.equals("3204")) {
                                                            b = -1;
                                                        } else {
                                                            b = 21;
                                                        }
                                                        break;
                                                    case 1568932:
                                                        if (!strM1688a.equals("3205")) {
                                                            b = -1;
                                                        } else {
                                                            b = 22;
                                                        }
                                                        break;
                                                    case 1568933:
                                                        if (!strM1688a.equals("3206")) {
                                                            b = -1;
                                                        } else {
                                                            b = 23;
                                                        }
                                                        break;
                                                    case 1568934:
                                                        if (!strM1688a.equals("3207")) {
                                                            b = -1;
                                                        } else {
                                                            b = 24;
                                                        }
                                                        break;
                                                    case 1568935:
                                                        if (!strM1688a.equals("3208")) {
                                                            b = -1;
                                                        } else {
                                                            b = 25;
                                                        }
                                                        break;
                                                    case 1568936:
                                                        if (!strM1688a.equals("3209")) {
                                                            b = -1;
                                                        } else {
                                                            b = 26;
                                                        }
                                                        break;
                                                    default:
                                                        switch (strM1688a) {
                                                            case 1575716:
                                                                if (!strM1688a.equals("3920")) {
                                                                    b = -1;
                                                                } else {
                                                                    b = 27;
                                                                }
                                                                break;
                                                            case 1575717:
                                                                if (!strM1688a.equals("3921")) {
                                                                    b = -1;
                                                                } else {
                                                                    b = 28;
                                                                }
                                                                break;
                                                            case 1575718:
                                                                if (!strM1688a.equals("3922")) {
                                                                    b = -1;
                                                                } else {
                                                                    b = 29;
                                                                }
                                                                break;
                                                            case 1575719:
                                                                if (!strM1688a.equals("3923")) {
                                                                    b = -1;
                                                                } else {
                                                                    b = 30;
                                                                }
                                                                break;
                                                            default:
                                                                switch (strM1688a) {
                                                                    case 1575747:
                                                                        if (!strM1688a.equals("3930")) {
                                                                            b = -1;
                                                                        } else {
                                                                            b = 31;
                                                                        }
                                                                        break;
                                                                    case 1575748:
                                                                        if (!strM1688a.equals("3931")) {
                                                                            b = -1;
                                                                        } else {
                                                                            b = 32;
                                                                        }
                                                                        break;
                                                                    case 1575749:
                                                                        if (!strM1688a.equals("3932")) {
                                                                            b = -1;
                                                                        } else {
                                                                            b = 33;
                                                                        }
                                                                        break;
                                                                    case 1575750:
                                                                        if (!strM1688a.equals("3933")) {
                                                                            b = -1;
                                                                        } else {
                                                                            b = 34;
                                                                        }
                                                                        break;
                                                                    default:
                                                                        b = -1;
                                                                        break;
                                                                }
                                                                break;
                                                        }
                                                        break;
                                                }
                                                break;
                                        }
                                        break;
                                }
                        }
                    } else if (strM1688a.equals("17")) {
                        b = 6;
                    } else {
                        b = -1;
                    }
                } else if (strM1688a.equals("15")) {
                    b = 5;
                } else {
                    b = -1;
                }
            } else if (strM1688a.equals("13")) {
                b = 4;
            } else {
                b = -1;
            }
            switch (b) {
                case 0:
                    str2 = strM1689b;
                    strSubstring = str10;
                    c1173k3 = null;
                    break;
                case 1:
                    str = strM1689b;
                    strSubstring = str10;
                    c1173k3 = null;
                    break;
                case 2:
                    str3 = strM1689b;
                    strSubstring = str10;
                    c1173k3 = null;
                    break;
                case 3:
                    str4 = strM1689b;
                    strSubstring = str10;
                    c1173k3 = null;
                    break;
                case 4:
                    str5 = strM1689b;
                    strSubstring = str10;
                    c1173k3 = null;
                    break;
                case 5:
                    str6 = strM1689b;
                    strSubstring = str10;
                    c1173k3 = null;
                    break;
                case 6:
                    str7 = strM1689b;
                    strSubstring = str10;
                    c1173k3 = null;
                    break;
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                    c1173k2 = null;
                    str9 = C1173k.f2011a;
                    strSubstring = strM1688a.substring(3);
                    str8 = strM1689b;
                    c1173k3 = c1173k2;
                    break;
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                    c1173k2 = null;
                    str9 = C1173k.f2012b;
                    strSubstring = strM1688a.substring(3);
                    str8 = strM1689b;
                    c1173k3 = c1173k2;
                    break;
                case 27:
                case 28:
                case 29:
                case 30:
                    c1173k = null;
                    strSubstring3 = strM1688a.substring(3);
                    strSubstring2 = strM1689b;
                    c1173k3 = c1173k;
                    strSubstring = str10;
                    break;
                case 31:
                case 32:
                case 33:
                case 34:
                    if (strM1689b.length() < 4) {
                        return null;
                    }
                    c1173k = null;
                    strSubstring2 = strM1689b.substring(3);
                    strSubstring4 = strM1689b.substring(0, 3);
                    strSubstring3 = strM1688a.substring(3);
                    c1173k3 = c1173k;
                    strSubstring = str10;
                    break;
                default:
                    c1173k = null;
                    map.put(strM1688a, strM1689b);
                    c1173k3 = c1173k;
                    strSubstring = str10;
                    break;
            }
        }
        return new C1173k(strB, str, str2, str3, str4, str5, str6, str7, str8, str9, strSubstring, strSubstring2, strSubstring3, strSubstring4, map);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:129:0x0202  */
    /* JADX WARN: Failed to clean up code after switch over string restore
    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r3v2 int, still in use, count: 8, list:
  (r3v2 int) from 0x0052: IF  (r3v2 int) != (1570 int)  -> B:13:0x0054 A[HIDDEN]
  (r3v2 int) from 0x0056: IF  (r3v2 int) != (1572 int)  -> B:15:0x0058 A[HIDDEN]
  (r3v2 int) from 0x005a: IF  (r3v2 int) != (1574 int)  -> B:17:0x005c A[HIDDEN]
  (r3v2 int) from 0x005f: SWITCH (r3v2 int)
 case 1567: goto B:111:0x01c6
 case 1568: goto B:108:0x01bc
 default: goto B:19:0x0062 A[RegionRef:SW:18]
  (r3v2 int) from 0x0062: SWITCH (r3v2 int)
 case 1567966: goto B:105:0x01b2
 case 1567967: goto B:102:0x01a7
 case 1567968: goto B:99:0x019c
 case 1567969: goto B:96:0x0190
 case 1567970: goto B:93:0x0184
 case 1567971: goto B:90:0x0178
 case 1567972: goto B:87:0x016c
 case 1567973: goto B:84:0x0160
 case 1567974: goto B:81:0x0154
 case 1567975: goto B:78:0x0148
 default: goto B:20:0x0065 A[RegionRef:SW:19]
  (r3v2 int) from 0x0065: SWITCH (r3v2 int)
 case 1568927: goto B:75:0x013c
 case 1568928: goto B:72:0x0130
 case 1568929: goto B:69:0x0124
 case 1568930: goto B:66:0x0118
 case 1568931: goto B:63:0x010c
 case 1568932: goto B:60:0x0100
 case 1568933: goto B:57:0x00f4
 case 1568934: goto B:54:0x00e8
 case 1568935: goto B:51:0x00dc
 case 1568936: goto B:48:0x00d0
 default: goto B:21:0x0068 A[RegionRef:SW:20]
  (r3v2 int) from 0x0068: SWITCH (r3v2 int)
 case 1575716: goto B:45:0x00c4
 case 1575717: goto B:42:0x00b8
 case 1575718: goto B:39:0x00ac
 case 1575719: goto B:36:0x00a0
 default: goto B:22:0x006b A[RegionRef:SW:21]
  (r3v2 int) from 0x006b: SWITCH (r3v2 int)
 case 1575747: goto B:33:0x0094
 case 1575748: goto B:30:0x0088
 case 1575749: goto B:27:0x007c
 case 1575750: goto B:24:0x0070
 default: goto B:129:0x0202 A[RegionRef:SW:22]
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
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.google.p028a.p033b.p034a.AbstractC1183u
    /* JADX INFO: renamed from: a */
    public final /* synthetic */ AbstractC1179q mo1568a(C1363r c1363r) {
        byte b;
        AbstractC1179q abstractC1179q;
        AbstractC1179q abstractC1179q2;
        AbstractC1179q abstractC1179q3 = null;
        if (c1363r.f2843e != EnumC1138a.RSS_EXPANDED) {
            return null;
        }
        String strB = m1715b(c1363r);
        HashMap map = new HashMap();
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String strSubstring = null;
        String strSubstring2 = null;
        String strSubstring3 = null;
        String strSubstring4 = null;
        int length = 0;
        while (length < strB.length()) {
            String strM1688a = m1688a(length, strB);
            if (strM1688a == null) {
                return abstractC1179q3;
            }
            int length2 = length + strM1688a.length() + 2;
            String strM1689b = m1689b(length2, strB);
            length = length2 + strM1689b.length();
            String str10 = strSubstring;
            if (iHashCode != 1570) {
                if (iHashCode != 1572) {
                    if (iHashCode != 1574) {
                        switch (strM1688a) {
                            case "00":
                                b = 0;
                                break;
                            case "01":
                                b = 1;
                                break;
                            default:
                                switch (strM1688a) {
                                    case 1567:
                                        if (!strM1688a.equals("10")) {
                                            b = -1;
                                        } else {
                                            b = 2;
                                        }
                                        break;
                                    case 1568:
                                        if (!strM1688a.equals("11")) {
                                            b = -1;
                                        } else {
                                            b = 3;
                                        }
                                        break;
                                    default:
                                        switch (strM1688a) {
                                            case 1567966:
                                                if (!strM1688a.equals("3100")) {
                                                    b = -1;
                                                } else {
                                                    b = 7;
                                                }
                                                break;
                                            case 1567967:
                                                if (!strM1688a.equals("3101")) {
                                                    b = -1;
                                                } else {
                                                    b = 8;
                                                }
                                                break;
                                            case 1567968:
                                                if (!strM1688a.equals("3102")) {
                                                    b = -1;
                                                } else {
                                                    b = 9;
                                                }
                                                break;
                                            case 1567969:
                                                if (!strM1688a.equals("3103")) {
                                                    b = -1;
                                                } else {
                                                    b = 10;
                                                }
                                                break;
                                            case 1567970:
                                                if (!strM1688a.equals("3104")) {
                                                    b = -1;
                                                } else {
                                                    b = 11;
                                                }
                                                break;
                                            case 1567971:
                                                if (!strM1688a.equals("3105")) {
                                                    b = -1;
                                                } else {
                                                    b = 12;
                                                }
                                                break;
                                            case 1567972:
                                                if (!strM1688a.equals("3106")) {
                                                    b = -1;
                                                } else {
                                                    b = C1770bg.f4123k;
                                                }
                                                break;
                                            case 1567973:
                                                if (!strM1688a.equals("3107")) {
                                                    b = -1;
                                                } else {
                                                    b = C1770bg.f4124l;
                                                }
                                                break;
                                            case 1567974:
                                                if (!strM1688a.equals("3108")) {
                                                    b = -1;
                                                } else {
                                                    b = C1770bg.f4125m;
                                                }
                                                break;
                                            case 1567975:
                                                if (!strM1688a.equals("3109")) {
                                                    b = -1;
                                                } else {
                                                    b = C1770bg.f4126n;
                                                }
                                                break;
                                            default:
                                                switch (strM1688a) {
                                                    case 1568927:
                                                        if (!strM1688a.equals("3200")) {
                                                            b = -1;
                                                        } else {
                                                            b = 17;
                                                        }
                                                        break;
                                                    case 1568928:
                                                        if (!strM1688a.equals("3201")) {
                                                            b = -1;
                                                        } else {
                                                            b = 18;
                                                        }
                                                        break;
                                                    case 1568929:
                                                        if (!strM1688a.equals("3202")) {
                                                            b = -1;
                                                        } else {
                                                            b = 19;
                                                        }
                                                        break;
                                                    case 1568930:
                                                        if (!strM1688a.equals("3203")) {
                                                            b = -1;
                                                        } else {
                                                            b = 20;
                                                        }
                                                        break;
                                                    case 1568931:
                                                        if (!strM1688a.equals("3204")) {
                                                            b = -1;
                                                        } else {
                                                            b = 21;
                                                        }
                                                        break;
                                                    case 1568932:
                                                        if (!strM1688a.equals("3205")) {
                                                            b = -1;
                                                        } else {
                                                            b = 22;
                                                        }
                                                        break;
                                                    case 1568933:
                                                        if (!strM1688a.equals("3206")) {
                                                            b = -1;
                                                        } else {
                                                            b = 23;
                                                        }
                                                        break;
                                                    case 1568934:
                                                        if (!strM1688a.equals("3207")) {
                                                            b = -1;
                                                        } else {
                                                            b = 24;
                                                        }
                                                        break;
                                                    case 1568935:
                                                        if (!strM1688a.equals("3208")) {
                                                            b = -1;
                                                        } else {
                                                            b = 25;
                                                        }
                                                        break;
                                                    case 1568936:
                                                        if (!strM1688a.equals("3209")) {
                                                            b = -1;
                                                        } else {
                                                            b = 26;
                                                        }
                                                        break;
                                                    default:
                                                        switch (strM1688a) {
                                                            case 1575716:
                                                                if (!strM1688a.equals("3920")) {
                                                                    b = -1;
                                                                } else {
                                                                    b = 27;
                                                                }
                                                                break;
                                                            case 1575717:
                                                                if (!strM1688a.equals("3921")) {
                                                                    b = -1;
                                                                } else {
                                                                    b = 28;
                                                                }
                                                                break;
                                                            case 1575718:
                                                                if (!strM1688a.equals("3922")) {
                                                                    b = -1;
                                                                } else {
                                                                    b = 29;
                                                                }
                                                                break;
                                                            case 1575719:
                                                                if (!strM1688a.equals("3923")) {
                                                                    b = -1;
                                                                } else {
                                                                    b = 30;
                                                                }
                                                                break;
                                                            default:
                                                                switch (strM1688a) {
                                                                    case 1575747:
                                                                        if (!strM1688a.equals("3930")) {
                                                                            b = -1;
                                                                        } else {
                                                                            b = 31;
                                                                        }
                                                                        break;
                                                                    case 1575748:
                                                                        if (!strM1688a.equals("3931")) {
                                                                            b = -1;
                                                                        } else {
                                                                            b = 32;
                                                                        }
                                                                        break;
                                                                    case 1575749:
                                                                        if (!strM1688a.equals("3932")) {
                                                                            b = -1;
                                                                        } else {
                                                                            b = 33;
                                                                        }
                                                                        break;
                                                                    case 1575750:
                                                                        if (!strM1688a.equals("3933")) {
                                                                            b = -1;
                                                                        } else {
                                                                            b = 34;
                                                                        }
                                                                        break;
                                                                    default:
                                                                        b = -1;
                                                                        break;
                                                                }
                                                                break;
                                                        }
                                                        break;
                                                }
                                                break;
                                        }
                                        break;
                                }
                        }
                    } else if (strM1688a.equals("17")) {
                        b = 6;
                    } else {
                        b = -1;
                    }
                } else if (strM1688a.equals("15")) {
                    b = 5;
                } else {
                    b = -1;
                }
            } else if (strM1688a.equals("13")) {
                b = 4;
            } else {
                b = -1;
            }
            switch (b) {
                case 0:
                    str2 = strM1689b;
                    strSubstring = str10;
                    abstractC1179q3 = null;
                    break;
                case 1:
                    str = strM1689b;
                    strSubstring = str10;
                    abstractC1179q3 = null;
                    break;
                case 2:
                    str3 = strM1689b;
                    strSubstring = str10;
                    abstractC1179q3 = null;
                    break;
                case 3:
                    str4 = strM1689b;
                    strSubstring = str10;
                    abstractC1179q3 = null;
                    break;
                case 4:
                    str5 = strM1689b;
                    strSubstring = str10;
                    abstractC1179q3 = null;
                    break;
                case 5:
                    str6 = strM1689b;
                    strSubstring = str10;
                    abstractC1179q3 = null;
                    break;
                case 6:
                    str7 = strM1689b;
                    strSubstring = str10;
                    abstractC1179q3 = null;
                    break;
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                    abstractC1179q2 = null;
                    str9 = C1173k.f2011a;
                    strSubstring = strM1688a.substring(3);
                    str8 = strM1689b;
                    abstractC1179q3 = abstractC1179q2;
                    break;
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                    abstractC1179q2 = null;
                    str9 = C1173k.f2012b;
                    strSubstring = strM1688a.substring(3);
                    str8 = strM1689b;
                    abstractC1179q3 = abstractC1179q2;
                    break;
                case 27:
                case 28:
                case 29:
                case 30:
                    abstractC1179q = null;
                    strSubstring3 = strM1688a.substring(3);
                    strSubstring2 = strM1689b;
                    abstractC1179q3 = abstractC1179q;
                    strSubstring = str10;
                    break;
                case 31:
                case 32:
                case 33:
                case 34:
                    if (strM1689b.length() < 4) {
                        return null;
                    }
                    abstractC1179q = null;
                    strSubstring2 = strM1689b.substring(3);
                    strSubstring4 = strM1689b.substring(0, 3);
                    strSubstring3 = strM1688a.substring(3);
                    abstractC1179q3 = abstractC1179q;
                    strSubstring = str10;
                    break;
                default:
                    abstractC1179q = null;
                    map.put(strM1688a, strM1689b);
                    abstractC1179q3 = abstractC1179q;
                    strSubstring = str10;
                    break;
            }
        }
        return new C1173k(strB, str, str2, str3, str4, str5, str6, str7, str8, str9, strSubstring, strSubstring2, strSubstring3, strSubstring4, map);
    }
}
