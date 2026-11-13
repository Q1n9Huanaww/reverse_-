package com.p004b.p005a;

import com.p004b.p005a.p006a.C0503c;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLSocket;

/* JADX INFO: renamed from: com.b.a.p */
/* JADX INFO: compiled from: ConnectionSpec.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0619p {

    /* JADX INFO: renamed from: a */
    public static final C0619p f756a;

    /* JADX INFO: renamed from: b */
    public static final C0619p f757b;

    /* JADX INFO: renamed from: c */
    public static final C0619p f758c;

    /* JADX INFO: renamed from: h */
    private static final C0615l[] f759h = {C0615l.f682aW, C0615l.f713ba, C0615l.f683aX, C0615l.f714bb, C0615l.f720bh, C0615l.f719bg, C0615l.f709ax, C0615l.f667aH, C0615l.f710ay, C0615l.f668aI, C0615l.f691af, C0615l.f692ag, C0615l.f636D, C0615l.f640H, C0615l.f727h};

    /* JADX INFO: renamed from: d */
    final boolean f760d;

    /* JADX INFO: renamed from: e */
    final boolean f761e;

    /* JADX INFO: renamed from: f */
    final String[] f762f;

    /* JADX INFO: renamed from: g */
    final String[] f763g;

    static {
        C0620q c0620q = new C0620q(true);
        C0615l[] c0615lArr = f759h;
        if (!c0620q.f764a) {
            throw new IllegalStateException("no cipher suites for cleartext connections");
        }
        String[] strArr = new String[c0615lArr.length];
        for (int i = 0; i < c0615lArr.length; i++) {
            strArr[i] = c0615lArr[i].f746bi;
        }
        f756a = c0620q.m481a(strArr).m480a(EnumC0604ax.TLS_1_3, EnumC0604ax.TLS_1_2, EnumC0604ax.TLS_1_1, EnumC0604ax.TLS_1_0).m479a().m482b();
        f757b = new C0620q(f756a).m480a(EnumC0604ax.TLS_1_0).m479a().m482b();
        f758c = new C0620q(false).m482b();
    }

    C0619p(C0620q c0620q) {
        this.f760d = c0620q.f764a;
        this.f762f = c0620q.f765b;
        this.f763g = c0620q.f766c;
        this.f761e = c0620q.f767d;
    }

    /* JADX INFO: renamed from: b */
    private List<EnumC0604ax> m476b() {
        if (this.f763g == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(this.f763g.length);
        for (String str : this.f763g) {
            arrayList.add(EnumC0604ax.m449a(str));
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* JADX INFO: renamed from: a */
    public final boolean m477a() {
        return this.f761e;
    }

    /* JADX INFO: renamed from: a */
    public final boolean m478a(SSLSocket sSLSocket) {
        if (!this.f760d) {
            return false;
        }
        if (this.f763g == null || m475a(this.f763g, sSLSocket.getEnabledProtocols())) {
            return this.f762f == null || m475a(this.f762f, sSLSocket.getEnabledCipherSuites());
        }
        return false;
    }

    /* JADX INFO: renamed from: a */
    private static boolean m475a(String[] strArr, String[] strArr2) {
        if (strArr == null || strArr2 == null || strArr.length == 0 || strArr2.length == 0) {
            return false;
        }
        for (String str : strArr) {
            if (C0503c.m107a(strArr2, str) != -1) {
                return true;
            }
        }
        return false;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0619p)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        C0619p c0619p = (C0619p) obj;
        if (this.f760d != c0619p.f760d) {
            return false;
        }
        return !this.f760d || (Arrays.equals(this.f762f, c0619p.f762f) && Arrays.equals(this.f763g, c0619p.f763g) && this.f761e == c0619p.f761e);
    }

    public final int hashCode() {
        if (this.f760d) {
            return ((((Arrays.hashCode(this.f762f) + 527) * 31) + Arrays.hashCode(this.f763g)) * 31) + (!this.f761e ? 1 : 0);
        }
        return 17;
    }

    public final String toString() {
        String string;
        List listUnmodifiableList;
        if (!this.f760d) {
            return "ConnectionSpec()";
        }
        if (this.f762f != null) {
            if (this.f762f == null) {
                listUnmodifiableList = null;
            } else {
                ArrayList arrayList = new ArrayList(this.f762f.length);
                for (String str : this.f762f) {
                    arrayList.add(C0615l.m469a(str));
                }
                listUnmodifiableList = Collections.unmodifiableList(arrayList);
            }
            string = listUnmodifiableList.toString();
        } else {
            string = "[all enabled]";
        }
        return "ConnectionSpec(cipherSuites=" + string + ", tlsVersions=" + (this.f763g != null ? m476b().toString() : "[all enabled]") + ", supportsTlsExtensions=" + this.f761e + ")";
    }
}
