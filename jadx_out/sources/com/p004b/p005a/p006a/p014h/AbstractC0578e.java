package com.p004b.p005a.p006a.p014h;

import java.lang.reflect.Method;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: renamed from: com.b.a.a.h.e */
/* JADX INFO: compiled from: TrustRootIndex.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0578e {
    /* JADX INFO: renamed from: a */
    public abstract X509Certificate mo320a(X509Certificate x509Certificate);

    /* JADX INFO: renamed from: a */
    public static AbstractC0578e m319a(X509TrustManager x509TrustManager) {
        try {
            Method declaredMethod = x509TrustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", X509Certificate.class);
            declaredMethod.setAccessible(true);
            return new C0579f(x509TrustManager, declaredMethod);
        } catch (NoSuchMethodException unused) {
            return new C0580g(x509TrustManager.getAcceptedIssuers());
        }
    }
}
