package com.p004b.p005a;

import com.p004b.p005a.p006a.C0503c;
import com.p004b.p015b.C0636f;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.p092io.IOUtils;
import org.java_websocket.WebSocketImpl;
import org.slf4j.Marker;

/* JADX INFO: renamed from: com.b.a.ac */
/* JADX INFO: compiled from: HttpUrl.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0583ac {

    /* JADX INFO: renamed from: d */
    private static final char[] f451d = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* JADX INFO: renamed from: a */
    final String f452a;

    /* JADX INFO: renamed from: b */
    final String f453b;

    /* JADX INFO: renamed from: c */
    final int f454c;

    /* JADX INFO: renamed from: e */
    private final String f455e;

    /* JADX INFO: renamed from: f */
    private final String f456f;

    /* JADX INFO: renamed from: g */
    private final List<String> f457g;

    /* JADX INFO: renamed from: h */
    private final List<String> f458h;

    /* JADX INFO: renamed from: i */
    private final String f459i;

    /* JADX INFO: renamed from: j */
    private final String f460j;

    /* JADX INFO: renamed from: a */
    static int m332a(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'f') {
            return (c - 'a') + 10;
        }
        if (c < 'A' || c > 'F') {
            return -1;
        }
        return (c - 'A') + 10;
    }

    C0583ac(C0584ad c0584ad) {
        this.f452a = c0584ad.f461a;
        this.f455e = m337a(c0584ad.f462b, false);
        this.f456f = m337a(c0584ad.f463c, false);
        this.f453b = c0584ad.f464d;
        this.f454c = c0584ad.m362a();
        this.f457g = m338a(c0584ad.f466f, false);
        this.f458h = c0584ad.f467g != null ? m338a(c0584ad.f467g, true) : null;
        this.f459i = c0584ad.f468h != null ? m337a(c0584ad.f468h, false) : null;
        this.f460j = c0584ad.toString();
    }

    /* JADX INFO: renamed from: b */
    public final String m345b() {
        return this.f452a;
    }

    /* JADX INFO: renamed from: c */
    public final boolean m347c() {
        return this.f452a.equals("https");
    }

    /* JADX INFO: renamed from: d */
    public final String m349d() {
        if (this.f455e.isEmpty()) {
            return "";
        }
        int length = this.f452a.length() + 3;
        return this.f460j.substring(length, C0503c.m106a(this.f460j, length, this.f460j.length(), ":@"));
    }

    /* JADX INFO: renamed from: e */
    public final String m350e() {
        if (this.f456f.isEmpty()) {
            return "";
        }
        return this.f460j.substring(this.f460j.indexOf(58, this.f452a.length() + 3) + 1, this.f460j.indexOf(64));
    }

    /* JADX INFO: renamed from: f */
    public final String m351f() {
        return this.f453b;
    }

    /* JADX INFO: renamed from: g */
    public final int m352g() {
        return this.f454c;
    }

    /* JADX INFO: renamed from: a */
    public static int m333a(String str) {
        if (str.equals("http")) {
            return 80;
        }
        if (str.equals("https")) {
            return WebSocketImpl.DEFAULT_WSS_PORT;
        }
        return -1;
    }

    /* JADX INFO: renamed from: h */
    public final String m353h() {
        int iIndexOf = this.f460j.indexOf(47, this.f452a.length() + 3);
        return this.f460j.substring(iIndexOf, C0503c.m106a(this.f460j, iIndexOf, this.f460j.length(), "?#"));
    }

    /* JADX INFO: renamed from: a */
    static void m339a(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            sb.append(IOUtils.DIR_SEPARATOR_UNIX);
            sb.append(list.get(i));
        }
    }

    /* JADX INFO: renamed from: i */
    public final List<String> m354i() {
        int iIndexOf = this.f460j.indexOf(47, this.f452a.length() + 3);
        int iM106a = C0503c.m106a(this.f460j, iIndexOf, this.f460j.length(), "?#");
        ArrayList arrayList = new ArrayList();
        while (iIndexOf < iM106a) {
            int i = iIndexOf + 1;
            int iM105a = C0503c.m105a(this.f460j, i, iM106a, IOUtils.DIR_SEPARATOR_UNIX);
            arrayList.add(this.f460j.substring(i, iM105a));
            iIndexOf = iM105a;
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: j */
    public final String m355j() {
        if (this.f458h == null) {
            return null;
        }
        int iIndexOf = this.f460j.indexOf(63) + 1;
        return this.f460j.substring(iIndexOf, C0503c.m105a(this.f460j, iIndexOf + 1, this.f460j.length(), '#'));
    }

    /* JADX INFO: renamed from: b */
    static void m342b(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i = 0; i < size; i += 2) {
            String str = list.get(i);
            String str2 = list.get(i + 1);
            if (i > 0) {
                sb.append('&');
            }
            sb.append(str);
            if (str2 != null) {
                sb.append('=');
                sb.append(str2);
            }
        }
    }

    /* JADX INFO: renamed from: b */
    static List<String> m341b(String str) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i <= str.length()) {
            int iIndexOf = str.indexOf(38, i);
            if (iIndexOf == -1) {
                iIndexOf = str.length();
            }
            int iIndexOf2 = str.indexOf(61, i);
            if (iIndexOf2 == -1 || iIndexOf2 > iIndexOf) {
                arrayList.add(str.substring(i, iIndexOf));
                arrayList.add(null);
            } else {
                arrayList.add(str.substring(i, iIndexOf2));
                arrayList.add(str.substring(iIndexOf2 + 1, iIndexOf));
            }
            i = iIndexOf + 1;
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: k */
    public final String m356k() {
        if (this.f458h == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        m342b(sb, this.f458h);
        return sb.toString();
    }

    /* JADX INFO: renamed from: c */
    public final C0583ac m346c(String str) {
        C0584ad c0584adM348d = m348d(str);
        if (c0584adM348d != null) {
            return c0584adM348d.m365b();
        }
        return null;
    }

    /* JADX INFO: renamed from: d */
    public final C0584ad m348d(String str) {
        C0584ad c0584ad = new C0584ad();
        if (c0584ad.m363a(this, str) == C0585ae.f469a) {
            return c0584ad;
        }
        return null;
    }

    /* JADX INFO: renamed from: e */
    public static C0583ac m343e(String str) {
        C0584ad c0584ad = new C0584ad();
        if (c0584ad.m363a(null, str) == C0585ae.f469a) {
            return c0584ad.m365b();
        }
        return null;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof C0583ac) && ((C0583ac) obj).f460j.equals(this.f460j);
    }

    public final int hashCode() {
        return this.f460j.hashCode();
    }

    public final String toString() {
        return this.f460j;
    }

    /* JADX INFO: renamed from: a */
    private static String m337a(String str, boolean z) {
        return m335a(str, 0, str.length(), z);
    }

    /* JADX INFO: renamed from: a */
    private static List<String> m338a(List<String> list, boolean z) {
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            String str = list.get(i);
            arrayList.add(str != null ? m337a(str, z) : null);
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0052  */
    /* JADX INFO: renamed from: a */
    static String m335a(String str, int i, int i2, boolean z) {
        int i3;
        int iCharCount = i;
        while (iCharCount < i2) {
            char cCharAt = str.charAt(iCharCount);
            if (cCharAt == '%' || (cCharAt == '+' && z)) {
                C0636f c0636f = new C0636f();
                c0636f.m534a(str, i, iCharCount);
                while (iCharCount < i2) {
                    int iCodePointAt = str.codePointAt(iCharCount);
                    if (iCodePointAt == 37 && (i3 = iCharCount + 2) < i2) {
                        int iM332a = m332a(str.charAt(iCharCount + 1));
                        int iM332a2 = m332a(str.charAt(i3));
                        if (iM332a != -1 && iM332a2 != -1) {
                            c0636f.mo562h((iM332a << 4) + iM332a2);
                            iCharCount = i3;
                        } else {
                            c0636f.m530a(iCodePointAt);
                        }
                    } else if (iCodePointAt == 43 && z) {
                        c0636f.mo562h(32);
                    } else {
                        c0636f.m530a(iCodePointAt);
                    }
                    iCharCount += Character.charCount(iCodePointAt);
                }
                return c0636f.m569l();
            }
            iCharCount++;
        }
        return str.substring(i, i2);
    }

    /* JADX INFO: renamed from: a */
    private static boolean m340a(String str, int i, int i2) {
        int i3 = i + 2;
        return i3 < i2 && str.charAt(i) == '%' && m332a(str.charAt(i + 1)) != -1 && m332a(str.charAt(i3)) != -1;
    }

    /* JADX INFO: renamed from: a */
    static String m334a(String str, int i, int i2, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        int iCharCount = i;
        while (iCharCount < i2) {
            int iCodePointAt = str.codePointAt(iCharCount);
            int i3 = 43;
            if (iCodePointAt >= 32 && iCodePointAt != 127 && ((iCodePointAt < 128 || !z4) && str2.indexOf(iCodePointAt) == -1 && ((iCodePointAt != 37 || (z && (!z2 || m340a(str, iCharCount, i2)))) && (iCodePointAt != 43 || !z3)))) {
                iCharCount += Character.charCount(iCodePointAt);
            } else {
                C0636f c0636f = new C0636f();
                c0636f.m534a(str, i, iCharCount);
                C0636f c0636f2 = null;
                while (iCharCount < i2) {
                    int iCodePointAt2 = str.codePointAt(iCharCount);
                    if (!z || (iCodePointAt2 != 9 && iCodePointAt2 != 10 && iCodePointAt2 != 12 && iCodePointAt2 != 13)) {
                        if (iCodePointAt2 == i3 && z3) {
                            c0636f.mo542b(z ? Marker.ANY_NON_NULL_MARKER : "%2B");
                        } else {
                            if (iCodePointAt2 >= 32 && iCodePointAt2 != 127 && (iCodePointAt2 < 128 || !z4)) {
                                if (str2.indexOf(iCodePointAt2) == -1 && (iCodePointAt2 != 37 || (z && (!z2 || m340a(str, iCharCount, i2))))) {
                                    c0636f.m530a(iCodePointAt2);
                                }
                            }
                            if (c0636f2 == null) {
                                c0636f2 = new C0636f();
                            }
                            c0636f2.m530a(iCodePointAt2);
                            while (!c0636f2.mo551d()) {
                                int iMo552e = c0636f2.mo552e() & 255;
                                c0636f.mo562h(37);
                                c0636f.mo562h((int) f451d[(iMo552e >> 4) & 15]);
                                c0636f.mo562h((int) f451d[iMo552e & 15]);
                            }
                        }
                    }
                    iCharCount += Character.charCount(iCodePointAt2);
                    i3 = 43;
                }
                return c0636f.m569l();
            }
        }
        return str.substring(i, i2);
    }

    /* JADX INFO: renamed from: a */
    static String m336a(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        return m334a(str, 0, str.length(), str2, z, z2, z3, z4);
    }

    /* JADX INFO: renamed from: a */
    public final URI m344a() {
        C0584ad c0584ad = new C0584ad();
        c0584ad.f461a = this.f452a;
        c0584ad.f462b = m349d();
        c0584ad.f463c = m350e();
        c0584ad.f464d = this.f453b;
        c0584ad.f465e = this.f454c != m333a(this.f452a) ? this.f454c : -1;
        c0584ad.f466f.clear();
        c0584ad.f466f.addAll(m354i());
        c0584ad.m366b(m355j());
        c0584ad.f468h = this.f459i == null ? null : this.f460j.substring(this.f460j.indexOf(35) + 1);
        int size = c0584ad.f466f.size();
        for (int i = 0; i < size; i++) {
            c0584ad.f466f.set(i, m336a(c0584ad.f466f.get(i), "[]", true, true, false, true));
        }
        if (c0584ad.f467g != null) {
            int size2 = c0584ad.f467g.size();
            for (int i2 = 0; i2 < size2; i2++) {
                String str = c0584ad.f467g.get(i2);
                if (str != null) {
                    c0584ad.f467g.set(i2, m336a(str, "\\^`{|}", true, true, true, true));
                }
            }
        }
        if (c0584ad.f468h != null) {
            c0584ad.f468h = m336a(c0584ad.f468h, " \"#<>\\^`{|}", true, true, false, false);
        }
        String string = c0584ad.toString();
        try {
            return new URI(string);
        } catch (URISyntaxException e) {
            try {
                return URI.create(string.replaceAll("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]", ""));
            } catch (Exception unused) {
                throw new RuntimeException(e);
            }
        }
    }
}
