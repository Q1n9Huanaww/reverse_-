package com.google.p028a.p056i.p057a;

import com.google.p028a.C1303h;
import com.google.p028a.EnumC1233e;
import com.google.p028a.p035c.C1200c;
import com.google.p028a.p035c.C1202e;
import com.google.p028a.p035c.C1209l;
import com.google.p028a.p035c.EnumC1201d;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.i.a.d */
/* JADX INFO: compiled from: DecodedBitStreamParser.java */
/* JADX INFO: loaded from: classes.dex */
final class C1335d {

    /* JADX INFO: renamed from: a */
    private static final char[] f2728a = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:".toCharArray();

    /* JADX INFO: renamed from: b */
    private static final int f2729b = 1;

    private C1335d() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:47:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:48:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:50:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:51:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:54:0x00fc A[LOOP:0: B:58:0x001e->B:54:0x00fc, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:63:0x00db A[SYNTHETIC] */
    /* JADX INFO: renamed from: a */
    static C1202e m2447a(byte[] bArr, C1341j c1341j, EnumC1337f enumC1337f, Map<EnumC1233e, ?> map) throws C1303h {
        EnumC1339h enumC1339hM2467a;
        int iM1825a;
        ArrayList arrayList;
        String string;
        C1200c c1200c = new C1200c(bArr);
        StringBuilder sb = new StringBuilder(50);
        int i = 1;
        ArrayList arrayList2 = new ArrayList(1);
        EnumC1201d enumC1201dM1827a = null;
        boolean z = false;
        int iM1825a2 = -1;
        int iM1825a3 = -1;
        while (true) {
            try {
                if (c1200c.m1824a() < 4) {
                    enumC1339hM2467a = EnumC1339h.TERMINATOR;
                } else {
                    enumC1339hM2467a = EnumC1339h.m2467a(c1200c.m1825a(4));
                }
                EnumC1339h enumC1339h = enumC1339hM2467a;
                switch (enumC1339h) {
                    case TERMINATOR:
                        if (enumC1339h == EnumC1339h.TERMINATOR) {
                            String string2 = sb.toString();
                            if (arrayList2.isEmpty()) {
                                arrayList = null;
                            } else {
                                arrayList = arrayList2;
                            }
                            if (enumC1337f == null) {
                                string = null;
                            } else {
                                string = enumC1337f.toString();
                            }
                            return new C1202e(bArr, string2, arrayList, string, iM1825a2, iM1825a3);
                        }
                        i = 1;
                        break;
                    case FNC1_FIRST_POSITION:
                    case FNC1_SECOND_POSITION:
                        z = true;
                        if (enumC1339h == EnumC1339h.TERMINATOR) {
                            String string3 = sb.toString();
                            if (arrayList2.isEmpty()) {
                                arrayList = null;
                            } else {
                                arrayList = arrayList2;
                            }
                            if (enumC1337f == null) {
                                string = null;
                            } else {
                                string = enumC1337f.toString();
                            }
                            return new C1202e(bArr, string3, arrayList, string, iM1825a2, iM1825a3);
                        }
                        i = 1;
                        break;
                    case STRUCTURED_APPEND:
                        if (c1200c.m1824a() < 16) {
                            throw C1303h.m2217a();
                        }
                        iM1825a2 = c1200c.m1825a(8);
                        iM1825a3 = c1200c.m1825a(8);
                        if (enumC1339h == EnumC1339h.TERMINATOR) {
                            String string4 = sb.toString();
                            if (arrayList2.isEmpty()) {
                                arrayList = null;
                            } else {
                                arrayList = arrayList2;
                            }
                            if (enumC1337f == null) {
                                string = null;
                            } else {
                                string = enumC1337f.toString();
                            }
                            return new C1202e(bArr, string4, arrayList, string, iM1825a2, iM1825a3);
                        }
                        i = 1;
                        break;
                        break;
                    case ECI:
                        int iM1825a4 = c1200c.m1825a(8);
                        if ((iM1825a4 & 128) == 0) {
                            iM1825a = iM1825a4 & 127;
                        } else if ((iM1825a4 & 192) == 128) {
                            iM1825a = ((iM1825a4 & 63) << 8) | c1200c.m1825a(8);
                        } else if ((iM1825a4 & 224) == 192) {
                            iM1825a = ((iM1825a4 & 31) << 16) | c1200c.m1825a(16);
                        } else {
                            throw C1303h.m2217a();
                        }
                        enumC1201dM1827a = EnumC1201d.m1827a(iM1825a);
                        if (enumC1201dM1827a == null) {
                            throw C1303h.m2217a();
                        }
                        if (enumC1339h == EnumC1339h.TERMINATOR) {
                            String string5 = sb.toString();
                            if (arrayList2.isEmpty()) {
                                arrayList = null;
                            } else {
                                arrayList = arrayList2;
                            }
                            if (enumC1337f == null) {
                                string = null;
                            } else {
                                string = enumC1337f.toString();
                            }
                            return new C1202e(bArr, string5, arrayList, string, iM1825a2, iM1825a3);
                        }
                        i = 1;
                        break;
                    case HANZI:
                        int iM1825a5 = c1200c.m1825a(4);
                        int iM1825a6 = c1200c.m1825a(enumC1339h.m2468a(c1341j));
                        if (iM1825a5 == i) {
                            m2448a(c1200c, sb, iM1825a6);
                        }
                        if (enumC1339h == EnumC1339h.TERMINATOR) {
                            String string6 = sb.toString();
                            if (arrayList2.isEmpty()) {
                                arrayList = null;
                            } else {
                                arrayList = arrayList2;
                            }
                            if (enumC1337f == null) {
                                string = null;
                            } else {
                                string = enumC1337f.toString();
                            }
                            return new C1202e(bArr, string6, arrayList, string, iM1825a2, iM1825a3);
                        }
                        i = 1;
                        break;
                    default:
                        int iM1825a7 = c1200c.m1825a(enumC1339h.m2468a(c1341j));
                        switch (enumC1339h) {
                            case NUMERIC:
                                m2452c(c1200c, sb, iM1825a7);
                                if (enumC1339h == EnumC1339h.TERMINATOR) {
                                    String string7 = sb.toString();
                                    if (arrayList2.isEmpty()) {
                                        arrayList = null;
                                    } else {
                                        arrayList = arrayList2;
                                    }
                                    if (enumC1337f == null) {
                                        string = null;
                                    } else {
                                        string = enumC1337f.toString();
                                    }
                                    return new C1202e(bArr, string7, arrayList, string, iM1825a2, iM1825a3);
                                }
                                i = 1;
                                break;
                            case ALPHANUMERIC:
                                m2450a(c1200c, sb, iM1825a7, z);
                                if (enumC1339h == EnumC1339h.TERMINATOR) {
                                    String string8 = sb.toString();
                                    if (arrayList2.isEmpty()) {
                                        arrayList = null;
                                    } else {
                                        arrayList = arrayList2;
                                    }
                                    if (enumC1337f == null) {
                                        string = null;
                                    } else {
                                        string = enumC1337f.toString();
                                    }
                                    return new C1202e(bArr, string8, arrayList, string, iM1825a2, iM1825a3);
                                }
                                i = 1;
                                break;
                            case BYTE:
                                m2449a(c1200c, sb, iM1825a7, enumC1201dM1827a, arrayList2, map);
                                if (enumC1339h == EnumC1339h.TERMINATOR) {
                                    String string9 = sb.toString();
                                    if (arrayList2.isEmpty()) {
                                        arrayList = null;
                                    } else {
                                        arrayList = arrayList2;
                                    }
                                    if (enumC1337f == null) {
                                        string = null;
                                    } else {
                                        string = enumC1337f.toString();
                                    }
                                    return new C1202e(bArr, string9, arrayList, string, iM1825a2, iM1825a3);
                                }
                                i = 1;
                                break;
                            case KANJI:
                                m2451b(c1200c, sb, iM1825a7);
                                if (enumC1339h == EnumC1339h.TERMINATOR) {
                                    String string10 = sb.toString();
                                    if (arrayList2.isEmpty()) {
                                        arrayList = null;
                                    } else {
                                        arrayList = arrayList2;
                                    }
                                    if (enumC1337f == null) {
                                        string = null;
                                    } else {
                                        string = enumC1337f.toString();
                                    }
                                    return new C1202e(bArr, string10, arrayList, string, iM1825a2, iM1825a3);
                                }
                                i = 1;
                                break;
                            default:
                                throw C1303h.m2217a();
                        }
                        break;
                }
            } catch (IllegalArgumentException unused) {
                throw C1303h.m2217a();
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m2448a(C1200c c1200c, StringBuilder sb, int i) throws C1303h {
        if (i * 13 > c1200c.m1824a()) {
            throw C1303h.m2217a();
        }
        byte[] bArr = new byte[i * 2];
        int i2 = 0;
        while (i > 0) {
            int iM1825a = c1200c.m1825a(13);
            int i3 = (iM1825a % 96) | ((iM1825a / 96) << 8);
            int i4 = i3 + (i3 < 959 ? 41377 : 42657);
            bArr[i2] = (byte) (i4 >> 8);
            bArr[i2 + 1] = (byte) i4;
            i2 += 2;
            i--;
        }
        try {
            sb.append(new String(bArr, C1209l.f2171b));
        } catch (UnsupportedEncodingException unused) {
            throw C1303h.m2217a();
        }
    }

    /* JADX INFO: renamed from: b */
    private static void m2451b(C1200c c1200c, StringBuilder sb, int i) throws C1303h {
        if (i * 13 > c1200c.m1824a()) {
            throw C1303h.m2217a();
        }
        byte[] bArr = new byte[i * 2];
        int i2 = 0;
        while (i > 0) {
            int iM1825a = c1200c.m1825a(13);
            int i3 = (iM1825a % 192) | ((iM1825a / 192) << 8);
            int i4 = i3 + (i3 < 7936 ? 33088 : 49472);
            bArr[i2] = (byte) (i4 >> 8);
            bArr[i2 + 1] = (byte) i4;
            i2 += 2;
            i--;
        }
        try {
            sb.append(new String(bArr, C1209l.f2170a));
        } catch (UnsupportedEncodingException unused) {
            throw C1303h.m2217a();
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m2449a(C1200c c1200c, StringBuilder sb, int i, EnumC1201d enumC1201d, Collection<byte[]> collection, Map<EnumC1233e, ?> map) throws C1303h {
        String strName;
        if ((i << 3) > c1200c.m1824a()) {
            throw C1303h.m2217a();
        }
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) c1200c.m1825a(8);
        }
        if (enumC1201d == null) {
            strName = C1209l.m1864a(bArr, map);
        } else {
            strName = enumC1201d.name();
        }
        try {
            sb.append(new String(bArr, strName));
            collection.add(bArr);
        } catch (UnsupportedEncodingException unused) {
            throw C1303h.m2217a();
        }
    }

    /* JADX INFO: renamed from: a */
    private static char m2445a(int i) throws C1303h {
        if (i >= f2728a.length) {
            throw C1303h.m2217a();
        }
        return f2728a[i];
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0069  */
    /* JADX INFO: renamed from: a */
    private static void m2450a(C1200c c1200c, StringBuilder sb, int i, boolean z) throws C1303h {
        while (i > 1) {
            if (c1200c.m1824a() < 11) {
                throw C1303h.m2217a();
            }
            int iM1825a = c1200c.m1825a(11);
            sb.append(m2445a(iM1825a / 45));
            sb.append(m2445a(iM1825a % 45));
            i -= 2;
        }
        if (i == 1) {
            if (c1200c.m1824a() < 6) {
                throw C1303h.m2217a();
            }
            sb.append(m2445a(c1200c.m1825a(6)));
        }
        if (z) {
            for (int length = sb.length(); length < sb.length(); length++) {
                if (sb.charAt(length) == '%') {
                    if (length < sb.length() - 1) {
                        int i2 = length + 1;
                        if (sb.charAt(i2) == '%') {
                            sb.deleteCharAt(i2);
                        } else {
                            sb.setCharAt(length, (char) 29);
                        }
                    } else {
                        sb.setCharAt(length, (char) 29);
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: c */
    private static void m2452c(C1200c c1200c, StringBuilder sb, int i) throws C1303h {
        while (i >= 3) {
            if (c1200c.m1824a() < 10) {
                throw C1303h.m2217a();
            }
            int iM1825a = c1200c.m1825a(10);
            if (iM1825a >= 1000) {
                throw C1303h.m2217a();
            }
            sb.append(m2445a(iM1825a / 100));
            sb.append(m2445a((iM1825a / 10) % 10));
            sb.append(m2445a(iM1825a % 10));
            i -= 3;
        }
        if (i == 2) {
            if (c1200c.m1824a() < 7) {
                throw C1303h.m2217a();
            }
            int iM1825a2 = c1200c.m1825a(7);
            if (iM1825a2 >= 100) {
                throw C1303h.m2217a();
            }
            sb.append(m2445a(iM1825a2 / 10));
            sb.append(m2445a(iM1825a2 % 10));
            return;
        }
        if (i == 1) {
            if (c1200c.m1824a() < 4) {
                throw C1303h.m2217a();
            }
            int iM1825a3 = c1200c.m1825a(4);
            if (iM1825a3 >= 10) {
                throw C1303h.m2217a();
            }
            sb.append(m2445a(iM1825a3));
        }
    }

    /* JADX INFO: renamed from: a */
    private static int m2446a(C1200c c1200c) throws C1303h {
        int iM1825a = c1200c.m1825a(8);
        if ((iM1825a & 128) == 0) {
            return iM1825a & 127;
        }
        if ((iM1825a & 192) == 128) {
            return c1200c.m1825a(8) | ((iM1825a & 63) << 8);
        }
        if ((iM1825a & 224) == 192) {
            return c1200c.m1825a(16) | ((iM1825a & 31) << 16);
        }
        throw C1303h.m2217a();
    }
}
