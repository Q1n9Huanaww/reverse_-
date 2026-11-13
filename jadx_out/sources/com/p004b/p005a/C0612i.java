package com.p004b.p005a;

import com.p004b.p005a.p006a.C0503c;
import com.p004b.p005a.p006a.p014h.AbstractC0575b;
import com.p004b.p015b.C0639i;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLPeerUnverifiedException;

/* JADX INFO: renamed from: com.b.a.i */
/* JADX INFO: compiled from: CertificatePinner.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0612i {

    /* JADX INFO: renamed from: a */
    public static final C0612i f625a = new C0613j().m468a();

    /* JADX INFO: renamed from: b */
    private final Set<C0614k> f626b;

    /* JADX INFO: renamed from: c */
    private final AbstractC0575b f627c;

    C0612i(Set<C0614k> set, AbstractC0575b abstractC0575b) {
        this.f626b = set;
        this.f627c = abstractC0575b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0612i)) {
            return false;
        }
        C0612i c0612i = (C0612i) obj;
        return C0503c.m121a(this.f627c, c0612i.f627c) && this.f626b.equals(c0612i.f626b);
    }

    public final int hashCode() {
        return ((this.f627c != null ? this.f627c.hashCode() : 0) * 31) + this.f626b.hashCode();
    }

    /* JADX INFO: renamed from: a */
    final C0612i m466a(AbstractC0575b abstractC0575b) {
        return C0503c.m121a(this.f627c, abstractC0575b) ? this : new C0612i(this.f626b, abstractC0575b);
    }

    /* JADX INFO: renamed from: a */
    public static String m465a(Certificate certificate) {
        if (!(certificate instanceof X509Certificate)) {
            throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
        }
        return "sha256/" + m464a((X509Certificate) certificate).mo585b();
    }

    /* JADX INFO: renamed from: a */
    private static C0639i m464a(X509Certificate x509Certificate) {
        return C0639i.m576a(x509Certificate.getPublicKey().getEncoded()).mo587d();
    }

    /* JADX INFO: renamed from: a */
    public final void m467a(String str, List<Certificate> list) throws SSLPeerUnverifiedException {
        boolean zEquals;
        List listEmptyList = Collections.emptyList();
        for (C0614k c0614k : this.f626b) {
            if (c0614k.f629a.startsWith("*.")) {
                zEquals = str.regionMatches(false, str.indexOf(46) + 1, c0614k.f630b, 0, c0614k.f630b.length());
            } else {
                zEquals = str.equals(c0614k.f630b);
            }
            if (zEquals) {
                if (listEmptyList.isEmpty()) {
                    listEmptyList = new ArrayList();
                }
                listEmptyList.add(c0614k);
            }
        }
        if (listEmptyList.isEmpty()) {
            return;
        }
        if (this.f627c != null) {
            list = this.f627c.mo292a(list, str);
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            X509Certificate x509Certificate = (X509Certificate) list.get(i);
            int size2 = listEmptyList.size();
            C0639i c0639iM464a = null;
            C0639i c0639iMo586c = null;
            for (int i2 = 0; i2 < size2; i2++) {
                C0614k c0614k2 = (C0614k) listEmptyList.get(i2);
                if (c0614k2.f631c.equals("sha256/")) {
                    if (c0639iM464a == null) {
                        c0639iM464a = m464a(x509Certificate);
                    }
                    if (c0614k2.f632d.equals(c0639iM464a)) {
                        return;
                    }
                } else if (c0614k2.f631c.equals("sha1/")) {
                    if (c0639iMo586c == null) {
                        c0639iMo586c = C0639i.m576a(x509Certificate.getPublicKey().getEncoded()).mo586c();
                    }
                    if (c0614k2.f632d.equals(c0639iMo586c)) {
                        return;
                    }
                } else {
                    throw new AssertionError();
                }
            }
        }
        StringBuilder sb = new StringBuilder("Certificate pinning failure!\n  Peer certificate chain:");
        int size3 = list.size();
        for (int i3 = 0; i3 < size3; i3++) {
            X509Certificate x509Certificate2 = (X509Certificate) list.get(i3);
            sb.append("\n    ");
            sb.append(m465a((Certificate) x509Certificate2));
            sb.append(": ");
            sb.append(x509Certificate2.getSubjectDN().getName());
        }
        sb.append("\n  Pinned certificates for ");
        sb.append(str);
        sb.append(":");
        int size4 = listEmptyList.size();
        for (int i4 = 0; i4 < size4; i4++) {
            C0614k c0614k3 = (C0614k) listEmptyList.get(i4);
            sb.append("\n    ");
            sb.append(c0614k3);
        }
        throw new SSLPeerUnverifiedException(sb.toString());
    }
}
