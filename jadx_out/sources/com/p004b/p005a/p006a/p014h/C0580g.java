package com.p004b.p005a.p006a.p014h;

import java.security.cert.X509Certificate;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.security.auth.x500.X500Principal;

/* JADX INFO: renamed from: com.b.a.a.h.g */
/* JADX INFO: compiled from: TrustRootIndex.java */
/* JADX INFO: loaded from: classes.dex */
final class C0580g extends AbstractC0578e {

    /* JADX INFO: renamed from: a */
    private final Map<X500Principal, Set<X509Certificate>> f448a = new LinkedHashMap();

    public C0580g(X509Certificate... x509CertificateArr) {
        for (X509Certificate x509Certificate : x509CertificateArr) {
            X500Principal subjectX500Principal = x509Certificate.getSubjectX500Principal();
            Set<X509Certificate> linkedHashSet = this.f448a.get(subjectX500Principal);
            if (linkedHashSet == null) {
                linkedHashSet = new LinkedHashSet<>(1);
                this.f448a.put(subjectX500Principal, linkedHashSet);
            }
            linkedHashSet.add(x509Certificate);
        }
    }

    @Override // com.p004b.p005a.p006a.p014h.AbstractC0578e
    /* JADX INFO: renamed from: a */
    public final X509Certificate mo320a(X509Certificate x509Certificate) {
        Set<X509Certificate> set = this.f448a.get(x509Certificate.getIssuerX500Principal());
        if (set == null) {
            return null;
        }
        for (X509Certificate x509Certificate2 : set) {
            try {
                x509Certificate.verify(x509Certificate2.getPublicKey());
                return x509Certificate2;
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof C0580g) && ((C0580g) obj).f448a.equals(this.f448a);
    }

    public final int hashCode() {
        return this.f448a.hashCode();
    }
}
