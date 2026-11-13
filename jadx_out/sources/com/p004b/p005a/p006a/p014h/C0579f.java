package com.p004b.p005a.p006a.p014h;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: renamed from: com.b.a.a.h.f */
/* JADX INFO: compiled from: TrustRootIndex.java */
/* JADX INFO: loaded from: classes.dex */
final class C0579f extends AbstractC0578e {

    /* JADX INFO: renamed from: a */
    private final X509TrustManager f446a;

    /* JADX INFO: renamed from: b */
    private final Method f447b;

    C0579f(X509TrustManager x509TrustManager, Method method) {
        this.f447b = method;
        this.f446a = x509TrustManager;
    }

    @Override // com.p004b.p005a.p006a.p014h.AbstractC0578e
    /* JADX INFO: renamed from: a */
    public final X509Certificate mo320a(X509Certificate x509Certificate) {
        try {
            TrustAnchor trustAnchor = (TrustAnchor) this.f447b.invoke(this.f446a, x509Certificate);
            if (trustAnchor != null) {
                return trustAnchor.getTrustedCert();
            }
            return null;
        } catch (IllegalAccessException unused) {
            throw new AssertionError();
        } catch (InvocationTargetException unused2) {
            return null;
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0579f)) {
            return false;
        }
        C0579f c0579f = (C0579f) obj;
        return this.f446a.equals(c0579f.f446a) && this.f447b.equals(c0579f.f447b);
    }

    public final int hashCode() {
        return this.f446a.hashCode() + (this.f447b.hashCode() * 31);
    }
}
