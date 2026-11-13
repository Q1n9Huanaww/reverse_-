package com.p004b.p005a.p006a.p014h;

import java.security.GeneralSecurityException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;

/* JADX INFO: renamed from: com.b.a.a.h.a */
/* JADX INFO: compiled from: BasicCertificateChainCleaner.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0574a extends AbstractC0575b {

    /* JADX INFO: renamed from: a */
    private final AbstractC0578e f437a;

    public C0574a(AbstractC0578e abstractC0578e) {
        this.f437a = abstractC0578e;
    }

    @Override // com.p004b.p005a.p006a.p014h.AbstractC0575b
    /* JADX INFO: renamed from: a */
    public final List<Certificate> mo292a(List<Certificate> list, String str) throws SSLPeerUnverifiedException {
        X509Certificate x509Certificate;
        ArrayDeque arrayDeque = new ArrayDeque(list);
        ArrayList arrayList = new ArrayList();
        arrayList.add(arrayDeque.removeFirst());
        boolean z = false;
        for (int i = 0; i < 9; i++) {
            X509Certificate x509Certificate2 = (X509Certificate) arrayList.get(arrayList.size() - 1);
            X509Certificate x509CertificateMo320a = this.f437a.mo320a(x509Certificate2);
            if (x509CertificateMo320a != null) {
                if (arrayList.size() > 1 || !x509Certificate2.equals(x509CertificateMo320a)) {
                    arrayList.add(x509CertificateMo320a);
                }
                if (m308a(x509CertificateMo320a, x509CertificateMo320a)) {
                    return arrayList;
                }
                z = true;
            } else {
                Iterator it2 = arrayDeque.iterator();
                do {
                    if (!it2.hasNext()) {
                        if (z) {
                            return arrayList;
                        }
                        throw new SSLPeerUnverifiedException("Failed to find a trusted cert that signed " + x509Certificate2);
                    }
                    x509Certificate = (X509Certificate) it2.next();
                } while (!m308a(x509Certificate2, x509Certificate));
                it2.remove();
                arrayList.add(x509Certificate);
            }
        }
        throw new SSLPeerUnverifiedException("Certificate chain too long: " + arrayList);
    }

    /* JADX INFO: renamed from: a */
    private static boolean m308a(X509Certificate x509Certificate, X509Certificate x509Certificate2) {
        if (!x509Certificate.getIssuerDN().equals(x509Certificate2.getSubjectDN())) {
            return false;
        }
        try {
            x509Certificate.verify(x509Certificate2.getPublicKey());
            return true;
        } catch (GeneralSecurityException unused) {
            return false;
        }
    }

    public final int hashCode() {
        return this.f437a.hashCode();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof C0574a) && ((C0574a) obj).f437a.equals(this.f437a);
    }
}
