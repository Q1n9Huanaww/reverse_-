package com.p004b.p005a.p006a.p013g;

import com.p004b.p005a.EnumC0592al;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import javax.net.ssl.SSLSocket;

/* JADX INFO: renamed from: com.b.a.a.g.e */
/* JADX INFO: compiled from: JdkWithJettyBootPlatform.java */
/* JADX INFO: loaded from: classes.dex */
final class C0570e extends C0573h {

    /* JADX INFO: renamed from: a */
    private final Method f424a;

    /* JADX INFO: renamed from: b */
    private final Method f425b;

    /* JADX INFO: renamed from: c */
    private final Method f426c;

    /* JADX INFO: renamed from: d */
    private final Class<?> f427d;

    /* JADX INFO: renamed from: e */
    private final Class<?> f428e;

    private C0570e(Method method, Method method2, Method method3, Class<?> cls, Class<?> cls2) {
        this.f424a = method;
        this.f425b = method2;
        this.f426c = method3;
        this.f427d = cls;
        this.f428e = cls2;
    }

    @Override // com.p004b.p005a.p006a.p013g.C0573h
    /* JADX INFO: renamed from: a */
    public final void mo290a(SSLSocket sSLSocket, String str, List<EnumC0592al> list) {
        try {
            this.f424a.invoke(null, sSLSocket, Proxy.newProxyInstance(C0573h.class.getClassLoader(), new Class[]{this.f427d, this.f428e}, new C0571f(m306a(list))));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AssertionError(e);
        }
    }

    @Override // com.p004b.p005a.p006a.p013g.C0573h
    /* JADX INFO: renamed from: b */
    public final void mo298b(SSLSocket sSLSocket) {
        try {
            this.f426c.invoke(null, sSLSocket);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            throw new AssertionError();
        }
    }

    @Override // com.p004b.p005a.p006a.p013g.C0573h
    /* JADX INFO: renamed from: a */
    public final String mo286a(SSLSocket sSLSocket) {
        try {
            C0571f c0571f = (C0571f) Proxy.getInvocationHandler(this.f425b.invoke(null, sSLSocket));
            if (!c0571f.f429a && c0571f.f430b == null) {
                C0573h.m307b().mo287a(4, "ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", (Throwable) null);
                return null;
            }
            if (c0571f.f429a) {
                return null;
            }
            return c0571f.f430b;
        } catch (IllegalAccessException | InvocationTargetException unused) {
            throw new AssertionError();
        }
    }

    /* JADX INFO: renamed from: a */
    public static C0573h m297a() {
        try {
            Class<?> cls = Class.forName("org.eclipse.jetty.alpn.ALPN");
            Class<?> cls2 = Class.forName("org.eclipse.jetty.alpn.ALPN$Provider");
            return new C0570e(cls.getMethod("put", SSLSocket.class, cls2), cls.getMethod("get", SSLSocket.class), cls.getMethod("remove", SSLSocket.class), Class.forName("org.eclipse.jetty.alpn.ALPN$ClientProvider"), Class.forName("org.eclipse.jetty.alpn.ALPN$ServerProvider"));
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            return null;
        }
    }
}
