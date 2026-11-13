package com.p004b.p005a.p006a.p013g;

import com.p004b.p005a.p006a.p014h.AbstractC0575b;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;

/* JADX INFO: renamed from: com.b.a.a.g.b */
/* JADX INFO: compiled from: AndroidPlatform.java */
/* JADX INFO: loaded from: classes.dex */
final class C0567b extends AbstractC0575b {

    /* JADX INFO: renamed from: a */
    private final Object f417a;

    /* JADX INFO: renamed from: b */
    private final Method f418b;

    public final int hashCode() {
        return 0;
    }

    C0567b(Object obj, Method method) {
        this.f417a = obj;
        this.f418b = method;
    }

    @Override // com.p004b.p005a.p006a.p014h.AbstractC0575b
    /* JADX INFO: renamed from: a */
    public final List<Certificate> mo292a(List<Certificate> list, String str) throws SSLPeerUnverifiedException {
        try {
            return (List) this.f418b.invoke(this.f417a, (X509Certificate[]) list.toArray(new X509Certificate[list.size()]), "RSA", str);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e2) {
            SSLPeerUnverifiedException sSLPeerUnverifiedException = new SSLPeerUnverifiedException(e2.getMessage());
            sSLPeerUnverifiedException.initCause(e2);
            throw sSLPeerUnverifiedException;
        }
    }

    public final boolean equals(Object obj) {
        return obj instanceof C0567b;
    }
}
