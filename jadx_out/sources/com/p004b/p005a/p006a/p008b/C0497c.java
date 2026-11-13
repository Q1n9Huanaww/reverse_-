package com.p004b.p005a.p006a.p008b;

import android.support.v7.widget.ActivityChooserView;
import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import com.p004b.p005a.C0483a;
import com.p004b.p005a.C0583ac;
import com.p004b.p005a.C0589ai;
import com.p004b.p005a.C0595ao;
import com.p004b.p005a.C0596ap;
import com.p004b.p005a.C0599as;
import com.p004b.p005a.C0603aw;
import com.p004b.p005a.C0612i;
import com.p004b.p005a.C0617n;
import com.p004b.p005a.C0619p;
import com.p004b.p005a.C0629z;
import com.p004b.p005a.EnumC0592al;
import com.p004b.p005a.InterfaceC0616m;
import com.p004b.p005a.p006a.C0503c;
import com.p004b.p005a.p006a.p009c.C0509f;
import com.p004b.p005a.p006a.p009c.InterfaceC0506c;
import com.p004b.p005a.p006a.p010d.C0518a;
import com.p004b.p005a.p006a.p011e.AbstractC0556s;
import com.p004b.p005a.p006a.p011e.C0527ab;
import com.p004b.p005a.p006a.p011e.C0545h;
import com.p004b.p005a.p006a.p011e.C0547j;
import com.p004b.p005a.p006a.p011e.C0555r;
import com.p004b.p005a.p006a.p011e.EnumC0539b;
import com.p004b.p005a.p006a.p013g.C0573h;
import com.p004b.p005a.p006a.p014h.C0577d;
import com.p004b.p015b.C0644n;
import com.p004b.p015b.InterfaceC0637g;
import com.p004b.p015b.InterfaceC0638h;
import com.p004b.p015b.InterfaceC0654x;
import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownServiceException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;

/* JADX INFO: renamed from: com.b.a.a.b.c */
/* JADX INFO: compiled from: RealConnection.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0497c extends AbstractC0556s implements InterfaceC0616m {

    /* JADX INFO: renamed from: a */
    public boolean f131a;

    /* JADX INFO: renamed from: b */
    public int f132b;

    /* JADX INFO: renamed from: c */
    public int f133c = 1;

    /* JADX INFO: renamed from: d */
    public final List<Reference<C0501g>> f134d = new ArrayList();

    /* JADX INFO: renamed from: e */
    public long f135e = Long.MAX_VALUE;

    /* JADX INFO: renamed from: g */
    private final C0617n f136g;

    /* JADX INFO: renamed from: h */
    private final C0603aw f137h;

    /* JADX INFO: renamed from: i */
    private Socket f138i;

    /* JADX INFO: renamed from: j */
    private Socket f139j;

    /* JADX INFO: renamed from: k */
    private C0629z f140k;

    /* JADX INFO: renamed from: l */
    private EnumC0592al f141l;

    /* JADX INFO: renamed from: m */
    private C0547j f142m;

    /* JADX INFO: renamed from: n */
    private InterfaceC0638h f143n;

    /* JADX INFO: renamed from: o */
    private InterfaceC0637g f144o;

    public C0497c(C0617n c0617n, C0603aw c0603aw) {
        this.f136g = c0617n;
        this.f137h = c0603aw;
    }

    /* JADX WARN: Code duplicated, block: B:105:0x033e  */
    /* JADX WARN: Code duplicated, block: B:107:0x0345  */
    /* JADX WARN: Code duplicated, block: B:109:0x034b  */
    /* JADX WARN: Code duplicated, block: B:112:0x0352 A[LOOP:0: B:120:0x0076->B:112:0x0352, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:127:0x0355 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:128:0x0355 A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v4, types: [com.b.a.a.g.h] */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1, types: [java.net.Socket, javax.net.ssl.SSLSocket] */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX INFO: renamed from: a */
    public final void m69a(int i, int i2, int i3, boolean z) throws Throwable {
        IOException iOException;
        C0499e c0499e;
        ?? r7;
        Throwable th;
        AssertionError assertionError;
        if (this.f141l != null) {
            throw new IllegalStateException("already connected");
        }
        List<C0619p> listM29f = this.f137h.m445a().m29f();
        C0496b c0496b = new C0496b(listM29f);
        if (this.f137h.m445a().m32i() == null) {
            if (!listM29f.contains(C0619p.f758c)) {
                throw new C0499e(new UnknownServiceException("CLEARTEXT communication not enabled for client"));
            }
            String strM351f = this.f137h.m445a().m24a().m351f();
            if (!C0573h.m307b().mo291b(strM351f)) {
                throw new C0499e(new UnknownServiceException("CLEARTEXT communication to " + strM351f + " not permitted by network security policy"));
            }
        }
        C0499e c0499e2 = null;
        while (true) {
            try {
                r7 = 1;
                try {
                    if (!this.f137h.m448d()) {
                        m66a(i, i2);
                        break;
                    }
                    C0595ao c0595aoM406a = new C0596ap().m408a(this.f137h.m445a().m24a()).m412a(HttpHeaders.HOST, C0503c.m108a(this.f137h.m445a().m24a(), true)).m412a("Proxy-Connection", "Keep-Alive").m412a("User-Agent", "okhttp/3.6.0").m406a();
                    C0583ac c0583acM398a = c0595aoM406a.m398a();
                    m66a(i, i2);
                    String str = "CONNECT " + C0503c.m108a(c0583acM398a, true) + " HTTP/1.1";
                    C0518a c0518a = new C0518a(null, null, this.f143n, this.f144o);
                    try {
                        this.f143n.mo48a().mo595a(i2, TimeUnit.MILLISECONDS);
                        this.f144o.mo166a().mo595a(i3, TimeUnit.MILLISECONDS);
                        c0518a.m163a(c0595aoM406a.m401c(), str);
                        c0518a.mo133b();
                        C0599as c0599asM429a = c0518a.mo128a(false).m434a(c0595aoM406a).m429a();
                        long jM139a = C0509f.m139a(c0599asM429a);
                        if (jM139a == -1) {
                            jM139a = 0;
                        }
                        InterfaceC0654x interfaceC0654xM162a = c0518a.m162a(jM139a);
                        C0503c.m118a(interfaceC0654xM162a, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, TimeUnit.MILLISECONDS);
                        interfaceC0654xM162a.close();
                        int iM420b = c0599asM429a.m420b();
                        if (iM420b == 200) {
                            if (!this.f143n.mo546c().mo551d() || !this.f144o.mo546c().mo551d()) {
                                throw new IOException("TLS tunnel buffered too many bytes!");
                            }
                            break;
                        } else {
                            if (iM420b == 407) {
                                this.f137h.m445a().m27d().mo450a();
                                throw new IOException("Failed to authenticate with proxy");
                            }
                            throw new IOException("Unexpected response code for CONNECT: " + c0599asM429a.m420b());
                        }
                    } catch (IOException e) {
                        e = e;
                        iOException = e;
                        C0503c.m117a(this.f139j);
                        C0503c.m117a(this.f138i);
                        this.f139j = null;
                        this.f138i = null;
                        this.f143n = null;
                        this.f144o = null;
                        this.f140k = null;
                        this.f141l = null;
                        this.f142m = null;
                        if (c0499e2 == null) {
                            c0499e2 = new C0499e(iOException);
                        } else {
                            c0499e2.m81a(iOException);
                        }
                        c0499e = c0499e2;
                        if (z) {
                            if (!c0496b.m65a(iOException)) {
                                throw c0499e;
                            }
                            c0499e2 = c0499e;
                        } else {
                            throw c0499e;
                        }
                    }
                } catch (IOException e2) {
                    e = e2;
                    iOException = e;
                    C0503c.m117a(this.f139j);
                    C0503c.m117a(this.f138i);
                    this.f139j = null;
                    this.f138i = null;
                    this.f143n = null;
                    this.f144o = null;
                    this.f140k = null;
                    this.f141l = null;
                    this.f142m = null;
                    if (c0499e2 == null) {
                        c0499e2 = new C0499e(iOException);
                    } else {
                        c0499e2.m81a(iOException);
                    }
                    c0499e = c0499e2;
                    if (z) {
                        if (!c0496b.m65a(iOException)) {
                            throw c0499e;
                        }
                        c0499e2 = c0499e;
                    } else {
                        throw c0499e;
                    }
                }
            } catch (IOException e3) {
                e = e3;
            }
            c0499e2 = c0499e;
        }
        if (this.f137h.m445a().m32i() == null) {
            this.f141l = EnumC0592al.HTTP_1_1;
            this.f139j = this.f138i;
        } else {
            try {
                C0483a c0483aM445a = this.f137h.m445a();
                try {
                    SSLSocket sSLSocket = (SSLSocket) c0483aM445a.m32i().createSocket(this.f138i, c0483aM445a.m24a().m351f(), c0483aM445a.m24a().m352g(), true);
                    try {
                        C0619p c0619pM64a = c0496b.m64a(sSLSocket);
                        if (c0619pM64a.m477a()) {
                            C0573h.m307b().mo290a(sSLSocket, c0483aM445a.m24a().m351f(), c0483aM445a.m28e());
                        }
                        sSLSocket.startHandshake();
                        C0629z c0629zM505a = C0629z.m505a(sSLSocket.getSession());
                        if (!c0483aM445a.m33j().verify(c0483aM445a.m24a().m351f(), sSLSocket.getSession())) {
                            X509Certificate x509Certificate = (X509Certificate) c0629zM505a.m507b().get(0);
                            throw new SSLPeerUnverifiedException("Hostname " + c0483aM445a.m24a().m351f() + " not verified:\n    certificate: " + C0612i.m465a((Certificate) x509Certificate) + "\n    DN: " + x509Certificate.getSubjectDN().getName() + "\n    subjectAltNames: " + C0577d.m316a(x509Certificate));
                        }
                        c0483aM445a.m34k().m467a(c0483aM445a.m24a().m351f(), c0629zM505a.m507b());
                        String strMo286a = c0619pM64a.m477a() ? C0573h.m307b().mo286a(sSLSocket) : null;
                        this.f139j = sSLSocket;
                        this.f143n = C0644n.m606a(C0644n.m609b(this.f139j));
                        this.f144o = C0644n.m605a(C0644n.m607a(this.f139j));
                        this.f140k = c0629zM505a;
                        this.f141l = strMo286a != null ? EnumC0592al.m391a(strMo286a) : EnumC0592al.HTTP_1_1;
                        if (sSLSocket != null) {
                            C0573h.m307b().mo298b(sSLSocket);
                        }
                        if (this.f141l == EnumC0592al.HTTP_2) {
                            this.f139j.setSoTimeout(0);
                            this.f142m = new C0555r().m275a(this.f139j, this.f137h.m445a().m24a().m351f(), this.f143n, this.f144o).m274a(this).m273a();
                            this.f142m.m268b();
                        }
                    } catch (AssertionError e4) {
                        assertionError = e4;
                        if (!C0503c.m120a(assertionError)) {
                            throw assertionError;
                        }
                        throw new IOException(assertionError);
                    }
                } catch (AssertionError e5) {
                    assertionError = e5;
                } catch (Throwable th2) {
                    th = th2;
                    r7 = 0;
                    if (r7 != 0) {
                        C0573h.m307b().mo298b(r7);
                    }
                    C0503c.m117a((Socket) r7);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
        if (this.f142m != null) {
            synchronized (this.f136g) {
                this.f133c = this.f142m.m257a();
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private void m66a(int i, int i2) throws IOException {
        Proxy proxyM446b = this.f137h.m446b();
        this.f138i = (proxyM446b.type() == Proxy.Type.DIRECT || proxyM446b.type() == Proxy.Type.HTTP) ? this.f137h.m445a().m26c().createSocket() : new Socket(proxyM446b);
        this.f138i.setSoTimeout(i2);
        try {
            C0573h.m307b().mo289a(this.f138i, this.f137h.m447c(), i);
            this.f143n = C0644n.m606a(C0644n.m609b(this.f138i));
            this.f144o = C0644n.m605a(C0644n.m607a(this.f138i));
        } catch (ConnectException e) {
            ConnectException connectException = new ConnectException("Failed to connect to " + this.f137h.m447c());
            connectException.initCause(e);
            throw connectException;
        }
    }

    /* JADX INFO: renamed from: a */
    public final boolean m72a(C0483a c0483a) {
        return this.f134d.size() < this.f133c && c0483a.equals(this.f137h.m445a()) && !this.f131a;
    }

    /* JADX INFO: renamed from: a */
    public final InterfaceC0506c m67a(C0589ai c0589ai, C0501g c0501g) throws SocketException {
        if (this.f142m != null) {
            return new C0545h(c0589ai, c0501g, this.f142m);
        }
        this.f139j.setSoTimeout(c0589ai.m373b());
        this.f143n.mo48a().mo595a(c0589ai.m373b(), TimeUnit.MILLISECONDS);
        this.f144o.mo166a().mo595a(c0589ai.m374c(), TimeUnit.MILLISECONDS);
        return new C0518a(c0589ai, c0501g, this.f143n, this.f144o);
    }

    @Override // com.p004b.p005a.InterfaceC0616m
    /* JADX INFO: renamed from: a */
    public final C0603aw mo68a() {
        return this.f137h;
    }

    /* JADX INFO: renamed from: b */
    public final Socket m74b() {
        return this.f139j;
    }

    /* JADX INFO: renamed from: a */
    public final boolean m73a(boolean z) {
        if (this.f139j.isClosed() || this.f139j.isInputShutdown() || this.f139j.isOutputShutdown()) {
            return false;
        }
        if (this.f142m != null) {
            return !this.f142m.m272c();
        }
        if (z) {
            try {
                int soTimeout = this.f139j.getSoTimeout();
                try {
                    this.f139j.setSoTimeout(1);
                    if (this.f143n.mo551d()) {
                        this.f139j.setSoTimeout(soTimeout);
                        return false;
                    }
                    this.f139j.setSoTimeout(soTimeout);
                    return true;
                } catch (Throwable th) {
                    this.f139j.setSoTimeout(soTimeout);
                    throw th;
                }
            } catch (SocketTimeoutException unused) {
            } catch (IOException unused2) {
                return false;
            }
        }
        return true;
    }

    @Override // com.p004b.p005a.p006a.p011e.AbstractC0556s
    /* JADX INFO: renamed from: a */
    public final void mo70a(C0527ab c0527ab) {
        c0527ab.m178a(EnumC0539b.REFUSED_STREAM);
    }

    @Override // com.p004b.p005a.p006a.p011e.AbstractC0556s
    /* JADX INFO: renamed from: a */
    public final void mo71a(C0547j c0547j) {
        synchronized (this.f136g) {
            this.f133c = c0547j.m257a();
        }
    }

    /* JADX INFO: renamed from: c */
    public final C0629z m75c() {
        return this.f140k;
    }

    /* JADX INFO: renamed from: d */
    public final boolean m76d() {
        return this.f142m != null;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Connection{");
        sb.append(this.f137h.m445a().m24a().m351f());
        sb.append(":");
        sb.append(this.f137h.m445a().m24a().m352g());
        sb.append(", proxy=");
        sb.append(this.f137h.m446b());
        sb.append(" hostAddress=");
        sb.append(this.f137h.m447c());
        sb.append(" cipherSuite=");
        sb.append(this.f140k != null ? this.f140k.m506a() : "none");
        sb.append(" protocol=");
        sb.append(this.f141l);
        sb.append('}');
        return sb.toString();
    }
}
