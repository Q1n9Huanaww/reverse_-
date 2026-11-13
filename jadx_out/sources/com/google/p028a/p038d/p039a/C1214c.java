package com.google.p028a.p038d.p039a;

import android.support.v7.widget.helper.ItemTouchHelper;
import com.google.p028a.C1303h;
import com.google.p028a.p035c.C1200c;
import com.google.p028a.p035c.C1202e;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.p092io.FilenameUtils;
import org.apache.commons.p092io.IOUtils;

/* JADX INFO: renamed from: com.google.a.d.a.c */
/* JADX INFO: compiled from: DecodedBitStreamParser.java */
/* JADX INFO: loaded from: classes.dex */
final class C1214c {

    /* JADX INFO: renamed from: a */
    private static final char[] f2185a = {'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    /* JADX INFO: renamed from: b */
    private static final char[] f2186b = {'!', '\"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', FilenameUtils.EXTENSION_SEPARATOR, IOUtils.DIR_SEPARATOR_UNIX, ':', ';', '<', '=', '>', '?', '@', '[', IOUtils.DIR_SEPARATOR_WINDOWS, ']', '^', '_'};

    /* JADX INFO: renamed from: c */
    private static final char[] f2187c = {'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    /* JADX INFO: renamed from: d */
    private static final char[] f2188d = f2186b;

    /* JADX INFO: renamed from: e */
    private static final char[] f2189e = {'`', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '{', '|', '}', '~', 127};

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: renamed from: com.google.a.d.a.c$a */
    /* JADX INFO: compiled from: DecodedBitStreamParser.java */
    private static final class a {

        /* JADX INFO: renamed from: a */
        public static final int f2191a = 1;

        /* JADX INFO: renamed from: b */
        public static final int f2192b = 2;

        /* JADX INFO: renamed from: c */
        public static final int f2193c = 3;

        /* JADX INFO: renamed from: d */
        public static final int f2194d = 4;

        /* JADX INFO: renamed from: e */
        public static final int f2195e = 5;

        /* JADX INFO: renamed from: f */
        public static final int f2196f = 6;

        /* JADX INFO: renamed from: g */
        public static final int f2197g = 7;

        /* JADX INFO: renamed from: h */
        private static final /* synthetic */ int[] f2198h = {f2191a, f2192b, f2193c, f2194d, f2195e, f2196f, f2197g};

        private a(String str, int i) {
            super(str, i);
        }

        /* JADX INFO: renamed from: a */
        public static int[] m1891a() {
            return (int[]) f2198h.clone();
        }
    }

    private C1214c() {
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0056  */
    /* JADX WARN: Code duplicated, block: B:25:0x0066  */
    /* JADX INFO: renamed from: a */
    static C1202e m1884a(byte[] bArr) throws C1303h {
        C1200c c1200c = new C1200c(bArr);
        StringBuilder sb = new StringBuilder(100);
        StringBuilder sb2 = new StringBuilder(0);
        ArrayList arrayList = new ArrayList(1);
        int iM1883a = a.f2192b;
        do {
            if (iM1883a == a.f2192b) {
                iM1883a = m1883a(c1200c, sb, sb2);
            } else {
                switch (AnonymousClass1.f2190a[iM1883a - 1]) {
                    case 1:
                        m1886a(c1200c, sb);
                        break;
                    case 2:
                        m1888b(c1200c, sb);
                        break;
                    case 3:
                        m1889c(c1200c, sb);
                        break;
                    case 4:
                        m1890d(c1200c, sb);
                        break;
                    case 5:
                        m1887a(c1200c, sb, arrayList);
                        break;
                    default:
                        throw C1303h.m2217a();
                }
                iM1883a = a.f2192b;
            }
            if (iM1883a != a.f2191a) {
            }
            if (sb2.length() > 0) {
                sb.append((CharSequence) sb2);
            }
            String string = sb.toString();
            if (arrayList.isEmpty()) {
                arrayList = null;
            }
            return new C1202e(bArr, string, arrayList, null);
        } while (c1200c.m1824a() > 0);
        if (sb2.length() > 0) {
            sb.append((CharSequence) sb2);
        }
        String string2 = sb.toString();
        if (arrayList.isEmpty()) {
            arrayList = null;
        }
        return new C1202e(bArr, string2, arrayList, null);
    }

    /* JADX INFO: renamed from: com.google.a.d.a.c$1, reason: invalid class name */
    /* JADX INFO: compiled from: DecodedBitStreamParser.java */
    static /* synthetic */ class AnonymousClass1 {

        /* JADX INFO: renamed from: a */
        static final /* synthetic */ int[] f2190a = new int[a.m1891a().length];

        static {
            try {
                f2190a[a.f2193c - 1] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2190a[a.f2194d - 1] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2190a[a.f2195e - 1] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2190a[a.f2196f - 1] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2190a[a.f2197g - 1] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private static int m1883a(C1200c c1200c, StringBuilder sb, StringBuilder sb2) throws C1303h {
        boolean z = false;
        do {
            int iM1825a = c1200c.m1825a(8);
            if (iM1825a != 0) {
                if (iM1825a > 128) {
                    if (iM1825a != 129) {
                        if (iM1825a <= 229) {
                            int i = iM1825a - 130;
                            if (i < 10) {
                                sb.append('0');
                            }
                            sb.append(i);
                        } else {
                            switch (iM1825a) {
                                case 230:
                                    return a.f2193c;
                                case 231:
                                    return a.f2197g;
                                case 232:
                                    sb.append((char) 29);
                                    break;
                                case 233:
                                case 234:
                                case 241:
                                    break;
                                case 235:
                                    z = true;
                                    break;
                                case 236:
                                    sb.append("[)>\u001e05\u001d");
                                    sb2.insert(0, "\u001e\u0004");
                                    break;
                                case 237:
                                    sb.append("[)>\u001e06\u001d");
                                    sb2.insert(0, "\u001e\u0004");
                                    break;
                                case 238:
                                    return a.f2195e;
                                case 239:
                                    return a.f2194d;
                                case 240:
                                    return a.f2196f;
                                default:
                                    if (iM1825a != 254 || c1200c.m1824a() != 0) {
                                        throw C1303h.m2217a();
                                    }
                                    break;
                            }
                        }
                    } else {
                        return a.f2191a;
                    }
                } else {
                    if (z) {
                        iM1825a += 128;
                    }
                    sb.append((char) (iM1825a - 1));
                    return a.f2192b;
                }
            } else {
                throw C1303h.m2217a();
            }
        } while (c1200c.m1824a() > 0);
        return a.f2192b;
    }

    /* JADX INFO: renamed from: a */
    private static void m1886a(C1200c c1200c, StringBuilder sb) throws C1303h {
        int iM1825a;
        boolean z;
        int[] iArr = new int[3];
        boolean z2 = false;
        int i = 0;
        while (c1200c.m1824a() != 8 && (iM1825a = c1200c.m1825a(8)) != 254) {
            m1885a(iM1825a, c1200c.m1825a(8), iArr);
            boolean z3 = z2;
            for (int i2 = 0; i2 < 3; i2++) {
                int i3 = iArr[i2];
                switch (i) {
                    case 0:
                        if (i3 < 3) {
                            i = i3 + 1;
                        } else if (i3 < f2185a.length) {
                            char c = f2185a[i3];
                            if (z3) {
                                sb.append((char) (c + 128));
                                z3 = false;
                            } else {
                                sb.append(c);
                            }
                        } else {
                            throw C1303h.m2217a();
                        }
                        break;
                    case 1:
                        if (z3) {
                            sb.append((char) (i3 + 128));
                            z3 = false;
                        } else {
                            sb.append((char) i3);
                        }
                        i = 0;
                        break;
                    case 2:
                        if (i3 < f2186b.length) {
                            char c2 = f2186b[i3];
                            if (z3) {
                                sb.append((char) (c2 + 128));
                                z = false;
                            } else {
                                sb.append(c2);
                                z = z3;
                            }
                        } else if (i3 == 27) {
                            sb.append((char) 29);
                            z = z3;
                        } else {
                            if (i3 != 30) {
                                throw C1303h.m2217a();
                            }
                            z = true;
                        }
                        z3 = z;
                        i = 0;
                        break;
                    case 3:
                        if (z3) {
                            sb.append((char) (i3 + 224));
                            z3 = false;
                        } else {
                            sb.append((char) (i3 + 96));
                        }
                        i = 0;
                        break;
                    default:
                        throw C1303h.m2217a();
                }
            }
            if (c1200c.m1824a() <= 0) {
                return;
            } else {
                z2 = z3;
            }
        }
    }

    /* JADX INFO: renamed from: b */
    private static void m1888b(C1200c c1200c, StringBuilder sb) throws C1303h {
        int iM1825a;
        boolean z;
        int[] iArr = new int[3];
        boolean z2 = false;
        int i = 0;
        while (c1200c.m1824a() != 8 && (iM1825a = c1200c.m1825a(8)) != 254) {
            m1885a(iM1825a, c1200c.m1825a(8), iArr);
            boolean z3 = z2;
            for (int i2 = 0; i2 < 3; i2++) {
                int i3 = iArr[i2];
                switch (i) {
                    case 0:
                        if (i3 < 3) {
                            i = i3 + 1;
                        } else if (i3 < f2187c.length) {
                            char c = f2187c[i3];
                            if (z3) {
                                sb.append((char) (c + 128));
                                z3 = false;
                            } else {
                                sb.append(c);
                            }
                        } else {
                            throw C1303h.m2217a();
                        }
                        break;
                    case 1:
                        if (z3) {
                            sb.append((char) (i3 + 128));
                            z3 = false;
                        } else {
                            sb.append((char) i3);
                        }
                        i = 0;
                        break;
                    case 2:
                        if (i3 < f2188d.length) {
                            char c2 = f2188d[i3];
                            if (z3) {
                                sb.append((char) (c2 + 128));
                                z = false;
                            } else {
                                sb.append(c2);
                                z = z3;
                            }
                        } else if (i3 == 27) {
                            sb.append((char) 29);
                            z = z3;
                        } else {
                            if (i3 != 30) {
                                throw C1303h.m2217a();
                            }
                            z = true;
                        }
                        z3 = z;
                        i = 0;
                        break;
                    case 3:
                        if (i3 < f2189e.length) {
                            char c3 = f2189e[i3];
                            if (z3) {
                                sb.append((char) (c3 + 128));
                                z3 = false;
                            } else {
                                sb.append(c3);
                            }
                            i = 0;
                        } else {
                            throw C1303h.m2217a();
                        }
                        break;
                    default:
                        throw C1303h.m2217a();
                }
            }
            if (c1200c.m1824a() <= 0) {
                return;
            } else {
                z2 = z3;
            }
        }
    }

    /* JADX INFO: renamed from: c */
    private static void m1889c(C1200c c1200c, StringBuilder sb) throws C1303h {
        int iM1825a;
        int[] iArr = new int[3];
        while (c1200c.m1824a() != 8 && (iM1825a = c1200c.m1825a(8)) != 254) {
            m1885a(iM1825a, c1200c.m1825a(8), iArr);
            for (int i = 0; i < 3; i++) {
                int i2 = iArr[i];
                switch (i2) {
                    case 0:
                        sb.append('\r');
                        break;
                    case 1:
                        sb.append('*');
                        break;
                    case 2:
                        sb.append('>');
                        break;
                    case 3:
                        sb.append(' ');
                        break;
                    default:
                        if (i2 < 14) {
                            sb.append((char) (i2 + 44));
                        } else if (i2 < 40) {
                            sb.append((char) (i2 + 51));
                        } else {
                            throw C1303h.m2217a();
                        }
                        break;
                }
            }
            if (c1200c.m1824a() <= 0) {
                return;
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m1885a(int i, int i2, int[] iArr) {
        int i3 = ((i << 8) + i2) - 1;
        int i4 = i3 / 1600;
        iArr[0] = i4;
        int i5 = i3 - (i4 * 1600);
        int i6 = i5 / 40;
        iArr[1] = i6;
        iArr[2] = i5 - (i6 * 40);
    }

    /* JADX INFO: renamed from: d */
    private static void m1890d(C1200c c1200c, StringBuilder sb) {
        while (c1200c.m1824a() > 16) {
            for (int i = 0; i < 4; i++) {
                int iM1825a = c1200c.m1825a(6);
                if (iM1825a != 31) {
                    if ((iM1825a & 32) == 0) {
                        iM1825a |= 64;
                    }
                    sb.append((char) iM1825a);
                } else {
                    int i2 = 8 - c1200c.f2102b;
                    if (i2 != 8) {
                        c1200c.m1825a(i2);
                        return;
                    }
                    return;
                }
            }
            if (c1200c.m1824a() <= 0) {
                return;
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private static int m1882a(int i, int i2) {
        int i3 = i - (((i2 * 149) % 255) + 1);
        return i3 >= 0 ? i3 : i3 + 256;
    }

    /* JADX INFO: renamed from: a */
    private static void m1887a(C1200c c1200c, StringBuilder sb, Collection<byte[]> collection) throws C1303h {
        int i = c1200c.f2101a + 1;
        int i2 = i + 1;
        int iM1882a = m1882a(c1200c.m1825a(8), i);
        if (iM1882a == 0) {
            iM1882a = c1200c.m1824a() / 8;
        } else if (iM1882a >= 250) {
            iM1882a = ((iM1882a - 249) * ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION) + m1882a(c1200c.m1825a(8), i2);
            i2++;
        }
        if (iM1882a < 0) {
            throw C1303h.m2217a();
        }
        byte[] bArr = new byte[iM1882a];
        int i3 = 0;
        while (i3 < iM1882a) {
            if (c1200c.m1824a() < 8) {
                throw C1303h.m2217a();
            }
            bArr[i3] = (byte) m1882a(c1200c.m1825a(8), i2);
            i3++;
            i2++;
        }
        collection.add(bArr);
        try {
            sb.append(new String(bArr, "ISO8859_1"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Platform does not support required encoding: ".concat(String.valueOf(e)));
        }
    }
}
