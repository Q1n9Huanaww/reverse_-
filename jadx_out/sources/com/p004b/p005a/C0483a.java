package com.p004b.p005a;

import com.p004b.p005a.p006a.C0503c;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: renamed from: com.b.a.a */
/* JADX INFO: compiled from: Address.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0483a {

    /* JADX INFO: renamed from: a */
    final C0583ac f70a;

    /* JADX INFO: renamed from: b */
    final InterfaceC0625v f71b;

    /* JADX INFO: renamed from: c */
    final SocketFactory f72c;

    /* JADX INFO: renamed from: d */
    final InterfaceC0605b f73d;

    /* JADX INFO: renamed from: e */
    final List<EnumC0592al> f74e;

    /* JADX INFO: renamed from: f */
    final List<C0619p> f75f;

    /* JADX INFO: renamed from: g */
    final ProxySelector f76g;

    /* JADX INFO: renamed from: h */
    final Proxy f77h;

    /* JADX INFO: renamed from: i */
    final SSLSocketFactory f78i;

    /* JADX INFO: renamed from: j */
    final HostnameVerifier f79j;

    /* JADX INFO: renamed from: k */
    final C0612i f80k;

    public C0483a(String str, int i, InterfaceC0625v interfaceC0625v, SocketFactory socketFactory, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier, C0612i c0612i, InterfaceC0605b interfaceC0605b, Proxy proxy, List<EnumC0592al> list, List<C0619p> list2, ProxySelector proxySelector) {
        C0584ad c0584ad = new C0584ad();
        String str2 = sSLSocketFactory != null ? "https" : "http";
        if (str2.equalsIgnoreCase("http")) {
            c0584ad.f461a = "http";
        } else if (str2.equalsIgnoreCase("https")) {
            c0584ad.f461a = "https";
        } else {
            throw new IllegalArgumentException("unexpected scheme: " + str2);
        }
        C0584ad c0584adM364a = c0584ad.m364a(str);
        if (i <= 0 || i > 65535) {
            throw new IllegalArgumentException("unexpected port: " + i);
        }
        c0584adM364a.f465e = i;
        this.f70a = c0584adM364a.m365b();
        if (interfaceC0625v == null) {
            throw new NullPointerException("dns == null");
        }
        this.f71b = interfaceC0625v;
        if (socketFactory == null) {
            throw new NullPointerException("socketFactory == null");
        }
        this.f72c = socketFactory;
        if (interfaceC0605b == null) {
            throw new NullPointerException("proxyAuthenticator == null");
        }
        this.f73d = interfaceC0605b;
        if (list == null) {
            throw new NullPointerException("protocols == null");
        }
        this.f74e = C0503c.m112a(list);
        if (list2 == null) {
            throw new NullPointerException("connectionSpecs == null");
        }
        this.f75f = C0503c.m112a(list2);
        if (proxySelector == null) {
            throw new NullPointerException("proxySelector == null");
        }
        this.f76g = proxySelector;
        this.f77h = proxy;
        this.f78i = sSLSocketFactory;
        this.f79j = hostnameVerifier;
        this.f80k = c0612i;
    }

    /* JADX INFO: renamed from: a */
    public final C0583ac m24a() {
        return this.f70a;
    }

    /* JADX INFO: renamed from: b */
    public final InterfaceC0625v m25b() {
        return this.f71b;
    }

    /* JADX INFO: renamed from: c */
    public final SocketFactory m26c() {
        return this.f72c;
    }

    /* JADX INFO: renamed from: d */
    public final InterfaceC0605b m27d() {
        return this.f73d;
    }

    /* JADX INFO: renamed from: e */
    public final List<EnumC0592al> m28e() {
        return this.f74e;
    }

    /* JADX INFO: renamed from: f */
    public final List<C0619p> m29f() {
        return this.f75f;
    }

    /* JADX INFO: renamed from: g */
    public final ProxySelector m30g() {
        return this.f76g;
    }

    /* JADX INFO: renamed from: h */
    public final Proxy m31h() {
        return this.f77h;
    }

    /* JADX INFO: renamed from: i */
    public final SSLSocketFactory m32i() {
        return this.f78i;
    }

    /* JADX INFO: renamed from: j */
    public final HostnameVerifier m33j() {
        return this.f79j;
    }

    /* JADX INFO: renamed from: k */
    public final C0612i m34k() {
        return this.f80k;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0483a)) {
            return false;
        }
        C0483a c0483a = (C0483a) obj;
        return this.f70a.equals(c0483a.f70a) && this.f71b.equals(c0483a.f71b) && this.f73d.equals(c0483a.f73d) && this.f74e.equals(c0483a.f74e) && this.f75f.equals(c0483a.f75f) && this.f76g.equals(c0483a.f76g) && C0503c.m121a(this.f77h, c0483a.f77h) && C0503c.m121a(this.f78i, c0483a.f78i) && C0503c.m121a(this.f79j, c0483a.f79j) && C0503c.m121a(this.f80k, c0483a.f80k);
    }

    public final int hashCode() {
        return ((((((((((((((((((this.f70a.hashCode() + 527) * 31) + this.f71b.hashCode()) * 31) + this.f73d.hashCode()) * 31) + this.f74e.hashCode()) * 31) + this.f75f.hashCode()) * 31) + this.f76g.hashCode()) * 31) + (this.f77h != null ? this.f77h.hashCode() : 0)) * 31) + (this.f78i != null ? this.f78i.hashCode() : 0)) * 31) + (this.f79j != null ? this.f79j.hashCode() : 0)) * 31) + (this.f80k != null ? this.f80k.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Address{");
        sb.append(this.f70a.f453b);
        sb.append(":");
        sb.append(this.f70a.f454c);
        if (this.f77h != null) {
            sb.append(", proxy=");
            sb.append(this.f77h);
        } else {
            sb.append(", proxySelector=");
            sb.append(this.f76g);
        }
        sb.append("}");
        return sb.toString();
    }
}
