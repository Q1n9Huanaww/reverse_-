package com.p004b.p005a;

import com.p004b.p005a.p006a.C0503c;
import java.security.cert.Certificate;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

/* JADX INFO: renamed from: com.b.a.z */
/* JADX INFO: compiled from: Handshake.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0629z {

    /* JADX INFO: renamed from: a */
    private final EnumC0604ax f795a;

    /* JADX INFO: renamed from: b */
    private final C0615l f796b;

    /* JADX INFO: renamed from: c */
    private final List<Certificate> f797c;

    /* JADX INFO: renamed from: d */
    private final List<Certificate> f798d;

    private C0629z(EnumC0604ax enumC0604ax, C0615l c0615l, List<Certificate> list, List<Certificate> list2) {
        this.f795a = enumC0604ax;
        this.f796b = c0615l;
        this.f797c = list;
        this.f798d = list2;
    }

    /* JADX INFO: renamed from: a */
    public static C0629z m505a(SSLSession sSLSession) {
        Certificate[] peerCertificates;
        List listEmptyList;
        List listEmptyList2;
        String cipherSuite = sSLSession.getCipherSuite();
        if (cipherSuite == null) {
            throw new IllegalStateException("cipherSuite == null");
        }
        C0615l c0615lM469a = C0615l.m469a(cipherSuite);
        String protocol = sSLSession.getProtocol();
        if (protocol == null) {
            throw new IllegalStateException("tlsVersion == null");
        }
        EnumC0604ax enumC0604axM449a = EnumC0604ax.m449a(protocol);
        try {
            peerCertificates = sSLSession.getPeerCertificates();
        } catch (SSLPeerUnverifiedException unused) {
            peerCertificates = null;
        }
        if (peerCertificates != null) {
            listEmptyList = C0503c.m113a(peerCertificates);
        } else {
            listEmptyList = Collections.emptyList();
        }
        Certificate[] localCertificates = sSLSession.getLocalCertificates();
        if (localCertificates != null) {
            listEmptyList2 = C0503c.m113a(localCertificates);
        } else {
            listEmptyList2 = Collections.emptyList();
        }
        return new C0629z(enumC0604axM449a, c0615lM469a, listEmptyList, listEmptyList2);
    }

    /* JADX INFO: renamed from: a */
    public final C0615l m506a() {
        return this.f796b;
    }

    /* JADX INFO: renamed from: b */
    public final List<Certificate> m507b() {
        return this.f797c;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0629z)) {
            return false;
        }
        C0629z c0629z = (C0629z) obj;
        return C0503c.m121a(this.f796b, c0629z.f796b) && this.f796b.equals(c0629z.f796b) && this.f797c.equals(c0629z.f797c) && this.f798d.equals(c0629z.f798d);
    }

    public final int hashCode() {
        return (((((((this.f795a != null ? this.f795a.hashCode() : 0) + 527) * 31) + this.f796b.hashCode()) * 31) + this.f797c.hashCode()) * 31) + this.f798d.hashCode();
    }
}
