package com.p004b.p005a;

import java.net.InetSocketAddress;
import java.net.Proxy;

/* JADX INFO: renamed from: com.b.a.aw */
/* JADX INFO: compiled from: Route.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0603aw {

    /* JADX INFO: renamed from: a */
    final C0483a f591a;

    /* JADX INFO: renamed from: b */
    final Proxy f592b;

    /* JADX INFO: renamed from: c */
    final InetSocketAddress f593c;

    public C0603aw(C0483a c0483a, Proxy proxy, InetSocketAddress inetSocketAddress) {
        if (c0483a == null) {
            throw new NullPointerException("address == null");
        }
        if (proxy == null) {
            throw new NullPointerException("proxy == null");
        }
        if (inetSocketAddress == null) {
            throw new NullPointerException("inetSocketAddress == null");
        }
        this.f591a = c0483a;
        this.f592b = proxy;
        this.f593c = inetSocketAddress;
    }

    /* JADX INFO: renamed from: a */
    public final C0483a m445a() {
        return this.f591a;
    }

    /* JADX INFO: renamed from: b */
    public final Proxy m446b() {
        return this.f592b;
    }

    /* JADX INFO: renamed from: c */
    public final InetSocketAddress m447c() {
        return this.f593c;
    }

    /* JADX INFO: renamed from: d */
    public final boolean m448d() {
        return this.f591a.f78i != null && this.f592b.type() == Proxy.Type.HTTP;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0603aw)) {
            return false;
        }
        C0603aw c0603aw = (C0603aw) obj;
        return this.f591a.equals(c0603aw.f591a) && this.f592b.equals(c0603aw.f592b) && this.f593c.equals(c0603aw.f593c);
    }

    public final int hashCode() {
        return ((((this.f591a.hashCode() + 527) * 31) + this.f592b.hashCode()) * 31) + this.f593c.hashCode();
    }

    public final String toString() {
        return "Route{" + this.f593c + "}";
    }
}
