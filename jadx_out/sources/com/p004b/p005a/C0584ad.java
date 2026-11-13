package com.p004b.p005a;

import com.p004b.p005a.p006a.C0503c;
import com.p004b.p015b.C0636f;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.p092io.IOUtils;

/* JADX INFO: renamed from: com.b.a.ad */
/* JADX INFO: compiled from: HttpUrl.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0584ad {

    /* JADX INFO: renamed from: a */
    String f461a;

    /* JADX INFO: renamed from: d */
    String f464d;

    /* JADX INFO: renamed from: g */
    List<String> f467g;

    /* JADX INFO: renamed from: h */
    String f468h;

    /* JADX INFO: renamed from: b */
    String f462b = "";

    /* JADX INFO: renamed from: c */
    String f463c = "";

    /* JADX INFO: renamed from: e */
    int f465e = -1;

    /* JADX INFO: renamed from: f */
    final List<String> f466f = new ArrayList();

    public C0584ad() {
        this.f466f.add("");
    }

    /* JADX INFO: renamed from: a */
    public final C0584ad m364a(String str) {
        if (str == null) {
            throw new NullPointerException("host == null");
        }
        String strM359c = m359c(str, 0, str.length());
        if (strM359c == null) {
            throw new IllegalArgumentException("unexpected host: " + str);
        }
        this.f464d = strM359c;
        return this;
    }

    /* JADX INFO: renamed from: a */
    final int m362a() {
        return this.f465e != -1 ? this.f465e : C0583ac.m333a(this.f461a);
    }

    /* JADX INFO: renamed from: b */
    public final C0584ad m366b(String str) {
        this.f467g = str != null ? C0583ac.m341b(C0583ac.m336a(str, " \"'<>#", true, false, true, true)) : null;
        return this;
    }

    /* JADX INFO: renamed from: b */
    public final C0583ac m365b() {
        if (this.f461a == null) {
            throw new IllegalStateException("scheme == null");
        }
        if (this.f464d == null) {
            throw new IllegalStateException("host == null");
        }
        return new C0583ac(this);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f461a);
        sb.append("://");
        if (!this.f462b.isEmpty() || !this.f463c.isEmpty()) {
            sb.append(this.f462b);
            if (!this.f463c.isEmpty()) {
                sb.append(':');
                sb.append(this.f463c);
            }
            sb.append('@');
        }
        if (this.f464d.indexOf(58) != -1) {
            sb.append('[');
            sb.append(this.f464d);
            sb.append(']');
        } else {
            sb.append(this.f464d);
        }
        int iM362a = m362a();
        if (iM362a != C0583ac.m333a(this.f461a)) {
            sb.append(':');
            sb.append(iM362a);
        }
        C0583ac.m339a(sb, this.f466f);
        if (this.f467g != null) {
            sb.append('?');
            C0583ac.m342b(sb, this.f467g);
        }
        if (this.f468h != null) {
            sb.append('#');
            sb.append(this.f468h);
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: a */
    final int m363a(C0583ac c0583ac, String str) {
        int i;
        char c;
        char c2;
        int iM106a;
        int i2;
        int i3;
        char cCharAt;
        char cCharAt2;
        int iM104a = C0503c.m104a(str, 0, str.length());
        int iM125b = C0503c.m125b(str, iM104a, str.length());
        char c3 = ':';
        if (iM125b - iM104a >= 2 && (((cCharAt2 = str.charAt(iM104a)) >= 'a' && cCharAt2 <= 'z') || (cCharAt2 >= 'A' && cCharAt2 <= 'Z'))) {
            i = iM104a + 1;
            while (true) {
                if (i < iM125b) {
                    char cCharAt3 = str.charAt(i);
                    if ((cCharAt3 < 'a' || cCharAt3 > 'z') && ((cCharAt3 < 'A' || cCharAt3 > 'Z') && !((cCharAt3 >= '0' && cCharAt3 <= '9') || cCharAt3 == '+' || cCharAt3 == '-' || cCharAt3 == '.'))) {
                        if (cCharAt3 == ':') {
                            break;
                        }
                        break;
                    }
                    i++;
                }
                i = -1;
                break;
            }
        } else {
            i = -1;
            break;
        }
        if (i != -1) {
            if (str.regionMatches(true, iM104a, "https:", 0, 6)) {
                this.f461a = "https";
                iM104a += 6;
            } else if (str.regionMatches(true, iM104a, "http:", 0, 5)) {
                this.f461a = "http";
                iM104a += 5;
            } else {
                return C0585ae.f471c;
            }
        } else if (c0583ac != null) {
            this.f461a = c0583ac.f452a;
        } else {
            return C0585ae.f470b;
        }
        int i4 = iM104a;
        int i5 = 0;
        while (true) {
            c = IOUtils.DIR_SEPARATOR_UNIX;
            c2 = IOUtils.DIR_SEPARATOR_WINDOWS;
            if (i4 >= iM125b || !((cCharAt = str.charAt(i4)) == '\\' || cCharAt == '/')) {
                break;
            }
            i5++;
            i4++;
        }
        char c4 = '#';
        if (i5 >= 2 || c0583ac == null || !c0583ac.f452a.equals(this.f461a)) {
            int i6 = iM104a + i5;
            boolean z = false;
            boolean z2 = false;
            while (true) {
                iM106a = C0503c.m106a(str, i6, iM125b, "@/\\?#");
                byte bCharAt = iM106a != iM125b ? str.charAt(iM106a) : (byte) -1;
                if (bCharAt != -1 && bCharAt != c4 && bCharAt != c && bCharAt != c2) {
                    switch (bCharAt) {
                        case 63:
                            break;
                        case 64:
                            if (!z) {
                                int iM105a = C0503c.m105a(str, i6, iM106a, c3);
                                i3 = iM106a;
                                String strM334a = C0583ac.m334a(str, i6, iM105a, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
                                if (z2) {
                                    strM334a = this.f462b + "%40" + strM334a;
                                }
                                this.f462b = strM334a;
                                if (iM105a != i3) {
                                    this.f463c = C0583ac.m334a(str, iM105a + 1, i3, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
                                    z = true;
                                }
                                z2 = true;
                            } else {
                                i3 = iM106a;
                                this.f463c += "%40" + C0583ac.m334a(str, i6, i3, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
                            }
                            i6 = i3 + 1;
                            c4 = '#';
                            c2 = IOUtils.DIR_SEPARATOR_WINDOWS;
                            c3 = ':';
                            c = IOUtils.DIR_SEPARATOR_UNIX;
                            continue;
                        default:
                            continue;
                    }
                }
            }
            i2 = iM106a;
            int iM358b = m358b(str, i6, i2);
            int i7 = iM358b + 1;
            if (i7 < i2) {
                this.f464d = m359c(str, i6, iM358b);
                this.f465e = m361e(str, i7, i2);
                if (this.f465e == -1) {
                    return C0585ae.f472d;
                }
            } else {
                this.f464d = m359c(str, i6, iM358b);
                this.f465e = C0583ac.m333a(this.f461a);
            }
            if (this.f464d == null) {
                return C0585ae.f473e;
            }
        } else {
            this.f462b = c0583ac.m349d();
            this.f463c = c0583ac.m350e();
            this.f464d = c0583ac.f453b;
            this.f465e = c0583ac.f454c;
            this.f466f.clear();
            this.f466f.addAll(c0583ac.m354i());
            if (iM104a == iM125b || str.charAt(iM104a) == '#') {
                m366b(c0583ac.m355j());
            }
            i2 = iM104a;
        }
        int iM106a2 = C0503c.m106a(str, i2, iM125b, "?#");
        m357a(str, i2, iM106a2);
        if (iM106a2 < iM125b && str.charAt(iM106a2) == '?') {
            int iM105a2 = C0503c.m105a(str, iM106a2, iM125b, '#');
            this.f467g = C0583ac.m341b(C0583ac.m334a(str, iM106a2 + 1, iM105a2, " \"'<>#", true, false, true, true));
            iM106a2 = iM105a2;
        }
        if (iM106a2 < iM125b && str.charAt(iM106a2) == '#') {
            this.f468h = C0583ac.m334a(str, iM106a2 + 1, iM125b, "", true, false, false, false);
        }
        return C0585ae.f469a;
    }

    /* JADX INFO: renamed from: a */
    private void m357a(String str, int i, int i2) {
        if (i == i2) {
            return;
        }
        char cCharAt = str.charAt(i);
        if (cCharAt == '/' || cCharAt == '\\') {
            this.f466f.clear();
            this.f466f.add("");
            i++;
        } else {
            this.f466f.set(this.f466f.size() - 1, "");
        }
        while (true) {
            int i3 = i;
            if (i3 >= i2) {
                return;
            }
            i = C0503c.m106a(str, i3, i2, "/\\");
            boolean z = i < i2;
            String strM334a = C0583ac.m334a(str, i3, i, " \"<>^`{}|/\\?#", true, false, false, true);
            if (!(strM334a.equals(".") || strM334a.equalsIgnoreCase("%2e"))) {
                if (!(strM334a.equals("..") || strM334a.equalsIgnoreCase("%2e.") || strM334a.equalsIgnoreCase(".%2e") || strM334a.equalsIgnoreCase("%2e%2e"))) {
                    if (this.f466f.get(this.f466f.size() - 1).isEmpty()) {
                        this.f466f.set(this.f466f.size() - 1, strM334a);
                    } else {
                        this.f466f.add(strM334a);
                    }
                    if (z) {
                        this.f466f.add("");
                    }
                } else if (this.f466f.remove(this.f466f.size() - 1).isEmpty() && !this.f466f.isEmpty()) {
                    this.f466f.set(this.f466f.size() - 1, "");
                } else {
                    this.f466f.add("");
                }
            }
            if (z) {
                i++;
            }
        }
    }

    /* JADX INFO: renamed from: b */
    private static int m358b(String str, int i, int i2) {
        while (i < i2) {
            char cCharAt = str.charAt(i);
            if (cCharAt == ':') {
                return i;
            }
            if (cCharAt == '[') {
                do {
                    i++;
                    if (i >= i2) {
                        break;
                    }
                } while (str.charAt(i) != ']');
            }
            i++;
        }
        return i2;
    }

    /* JADX INFO: renamed from: c */
    private static String m359c(String str, int i, int i2) {
        InetAddress inetAddressM360d;
        int i3 = 0;
        String strM335a = C0583ac.m335a(str, i, i2, false);
        if (strM335a.contains(":")) {
            if (strM335a.startsWith("[") && strM335a.endsWith("]")) {
                inetAddressM360d = m360d(strM335a, 1, strM335a.length() - 1);
            } else {
                inetAddressM360d = m360d(strM335a, 0, strM335a.length());
            }
            if (inetAddressM360d == null) {
                return null;
            }
            byte[] address = inetAddressM360d.getAddress();
            if (address.length != 16) {
                throw new AssertionError();
            }
            int i4 = 0;
            int i5 = 0;
            int i6 = -1;
            while (i4 < address.length) {
                int i7 = i4;
                while (i7 < 16 && address[i7] == 0 && address[i7 + 1] == 0) {
                    i7 += 2;
                }
                int i8 = i7 - i4;
                if (i8 > i5) {
                    i6 = i4;
                    i5 = i8;
                }
                i4 = i7 + 2;
            }
            C0636f c0636f = new C0636f();
            while (i3 < address.length) {
                if (i3 == i6) {
                    c0636f.mo562h(58);
                    i3 += i5;
                    if (i3 == 16) {
                        c0636f.mo562h(58);
                    }
                } else {
                    if (i3 > 0) {
                        c0636f.mo562h(58);
                    }
                    c0636f.mo565i(((address[i3] & 255) << 8) | (address[i3 + 1] & 255));
                    i3 += 2;
                }
            }
            return c0636f.m569l();
        }
        return C0503c.m109a(strM335a);
    }

    /* JADX WARN: Code duplicated, block: B:60:0x00a6  */
    /* JADX WARN: Code duplicated, block: B:62:0x00b1 A[LOOP:1: B:59:0x00a4->B:62:0x00b1, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:89:0x00b7 A[EDGE_INSN: B:89:0x00b7->B:63:0x00b7 BREAK  A[LOOP:1: B:59:0x00a4->B:62:0x00b1], SYNTHETIC] */
    /* JADX INFO: renamed from: d */
    private static InetAddress m360d(String str, int i, int i2) {
        int i3;
        int i4;
        int iM332a;
        int i5 = 16;
        byte[] bArr = new byte[16];
        int i6 = -1;
        int i7 = 0;
        int i8 = i;
        int i9 = 0;
        int i10 = -1;
        int i11 = -1;
        while (i8 < i2) {
            if (i9 == i5) {
                return null;
            }
            int i12 = i8 + 2;
            if (i12 <= i2 && str.regionMatches(i8, "::", i7, 2)) {
                if (i10 != i6) {
                    return null;
                }
                i9 += 2;
                if (i12 == i2) {
                    i10 = i9;
                    break;
                }
                i10 = i9;
                i11 = i12;
                i8 = i11;
                i3 = 0;
                while (i8 < i2) {
                    iM332a = C0583ac.m332a(str.charAt(i8));
                    if (iM332a != -1) {
                        break;
                        break;
                    }
                    i3 = (i3 << 4) + iM332a;
                    i8++;
                }
                i4 = i8 - i11;
                if (i4 != 0) {
                }
                return null;
            }
            if (i9 != 0) {
                if (!str.regionMatches(i8, ":", i7, 1)) {
                    if (!str.regionMatches(i8, ".", i7, 1)) {
                        return null;
                    }
                    int i13 = i9 - 2;
                    int i14 = i13;
                    loop2: while (true) {
                        if (i11 >= i2) {
                            if (i14 == i13 + 4) {
                                i7 = 1;
                                break;
                            }
                            i7 = 0;
                            break loop2;
                        }
                        if (i14 == i5) {
                            break;
                        }
                        if (i14 != i13) {
                            if (str.charAt(i11) != '.') {
                                break;
                            }
                            i11++;
                        }
                        int i15 = i11;
                        int i16 = 0;
                        while (true) {
                            if (i15 < i2) {
                                char cCharAt = str.charAt(i15);
                                if (cCharAt >= '0' && cCharAt <= '9') {
                                    if ((i16 != 0 || i11 == i15) && (i16 = ((i16 * 10) + cCharAt) - 48) <= 255) {
                                        i15++;
                                    }
                                }
                                i7 = 0;
                                break loop2;
                            }
                            if (i15 - i11 == 0) {
                                i7 = 0;
                                break loop2;
                            }
                            bArr[i14] = (byte) i16;
                            i14++;
                            i11 = i15;
                            i5 = 16;
                            i7 = 0;
                        }
                    }
                    if (i7 != 0) {
                        i9 += 2;
                        break;
                    }
                    return null;
                }
                i8++;
            }
            i11 = i8;
            i8 = i11;
            i3 = 0;
            while (i8 < i2) {
                iM332a = C0583ac.m332a(str.charAt(i8));
                if (iM332a != -1) {
                    break;
                }
                i3 = (i3 << 4) + iM332a;
                i8++;
            }
            i4 = i8 - i11;
            if (i4 != 0 || i4 > 4) {
                return null;
            }
            int i17 = i9 + 1;
            bArr[i9] = (byte) ((i3 >>> 8) & 255);
            i9 = i17 + 1;
            bArr[i17] = (byte) (i3 & 255);
            i5 = 16;
            i6 = -1;
            i7 = 0;
        }
        if (i9 != 16) {
            if (i10 == -1) {
                return null;
            }
            int i18 = i9 - i10;
            System.arraycopy(bArr, i10, bArr, 16 - i18, i18);
            Arrays.fill(bArr, i10, (16 - i9) + i10, (byte) 0);
        }
        try {
            return InetAddress.getByAddress(bArr);
        } catch (UnknownHostException unused) {
            throw new AssertionError();
        }
    }

    /* JADX INFO: renamed from: e */
    private static int m361e(String str, int i, int i2) {
        try {
            int i3 = Integer.parseInt(C0583ac.m334a(str, i, i2, "", false, false, false, true));
            if (i3 <= 0 || i3 > 65535) {
                return -1;
            }
            return i3;
        } catch (NumberFormatException unused) {
            return -1;
        }
    }
}
