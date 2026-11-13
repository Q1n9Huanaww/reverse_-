package com.p004b.p005a.p006a.p009c;

import com.p004b.p005a.AbstractC0597aq;
import com.p004b.p005a.AbstractC0601au;
import com.p004b.p005a.C0483a;
import com.p004b.p005a.C0583ac;
import com.p004b.p005a.C0589ai;
import com.p004b.p005a.C0595ao;
import com.p004b.p005a.C0596ap;
import com.p004b.p005a.C0599as;
import com.p004b.p005a.C0603aw;
import com.p004b.p005a.C0612i;
import com.p004b.p005a.InterfaceC0586af;
import com.p004b.p005a.InterfaceC0587ag;
import com.p004b.p005a.p006a.C0503c;
import com.p004b.p005a.p006a.p008b.C0497c;
import com.p004b.p005a.p006a.p008b.C0499e;
import com.p004b.p005a.p006a.p008b.C0501g;
import com.p004b.p005a.p006a.p011e.C0525a;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.HttpRetryException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: renamed from: com.b.a.a.c.k */
/* JADX INFO: compiled from: RetryAndFollowUpInterceptor.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0514k implements InterfaceC0586af {

    /* JADX INFO: renamed from: a */
    private final C0589ai f200a;

    /* JADX INFO: renamed from: b */
    private final boolean f201b;

    /* JADX INFO: renamed from: c */
    private C0501g f202c;

    /* JADX INFO: renamed from: d */
    private Object f203d;

    /* JADX INFO: renamed from: e */
    private volatile boolean f204e;

    public C0514k(C0589ai c0589ai, boolean z) {
        this.f200a = c0589ai;
        this.f201b = z;
    }

    /* JADX INFO: renamed from: a */
    public final boolean m159a() {
        return this.f204e;
    }

    /* JADX INFO: renamed from: a */
    public final void m158a(Object obj) {
        this.f203d = obj;
    }

    /* JADX WARN: Code duplicated, block: B:106:0x016b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:107:0x0182 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:108:0x01a3 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:109:0x01dc A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:110:0x01be A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:111:0x01e4 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:116:0x001f A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:67:0x016f  */
    /* JADX WARN: Code duplicated, block: B:69:0x0175  */
    /* JADX WARN: Code duplicated, block: B:73:0x019b  */
    /* JADX WARN: Code duplicated, block: B:77:0x01b4  */
    @Override // com.p004b.p005a.InterfaceC0586af
    /* JADX INFO: renamed from: a */
    public final C0599as mo46a(InterfaceC0587ag interfaceC0587ag) throws IOException {
        String strM419a;
        C0583ac c0583acM346c;
        Proxy proxyM375d;
        C0595ao c0595aoMo146a = interfaceC0587ag.mo146a();
        this.f202c = new C0501g(this.f200a.m385n(), m155a(c0595aoMo146a.m398a()), this.f203d);
        C0599as c0599asM429a = null;
        int i = 0;
        while (!this.f204e) {
            boolean z = true;
            try {
                try {
                    C0599as c0599asM148a = ((C0511h) interfaceC0587ag).m148a(c0595aoMo146a, this.f202c, null, null);
                    c0599asM429a = c0599asM429a != null ? c0599asM148a.m424f().m442c(c0599asM429a.m424f().m436a((AbstractC0601au) null).m429a()).m429a() : c0599asM148a;
                    if (c0599asM429a == null) {
                        throw new IllegalStateException();
                    }
                    C0497c c0497cM99b = this.f202c.m99b();
                    C0603aw c0603awMo68a = c0497cM99b != null ? c0497cM99b.mo68a() : null;
                    int iM420b = c0599asM429a.m420b();
                    String strM400b = c0599asM429a.m418a().m400b();
                    switch (iM420b) {
                        case 307:
                        case 308:
                            if (!strM400b.equals("GET") && !strM400b.equals("HEAD")) {
                                c0595aoMo146a = null;
                            }
                            if (c0595aoMo146a == null) {
                                if (!this.f201b) {
                                    this.f202c.m101c();
                                }
                                return c0599asM429a;
                            }
                            C0503c.m116a(c0599asM429a.m423e());
                            i++;
                            if (i > 20) {
                                this.f202c.m101c();
                                throw new ProtocolException("Too many follow-up requests: " + i);
                            }
                            if (c0595aoMo146a.m402d() instanceof InterfaceC0516m) {
                                this.f202c.m101c();
                                throw new HttpRetryException("Cannot retry streamed HTTP body", c0599asM429a.m420b());
                            }
                            if (!m156a(c0599asM429a, c0595aoMo146a.m398a())) {
                                this.f202c.m101c();
                                this.f202c = new C0501g(this.f200a.m385n(), m155a(c0595aoMo146a.m398a()), this.f203d);
                            } else if (this.f202c.m94a() != null) {
                                throw new IllegalStateException("Closing the body of " + c0599asM429a + " didn't close its backing stream. Bad interceptor?");
                            }
                            break;
                        case 300:
                        case SET_ENABLED_VALUE:
                        case SET_VISIBLE_VALUE:
                        case SET_TITLE_TEXT_VALUE:
                            if (!this.f200a.m387p() || (strM419a = c0599asM429a.m419a("Location")) == null || (c0583acM346c = c0599asM429a.m418a().m398a().m346c(strM419a)) == null) {
                                c0595aoMo146a = null;
                            } else if (c0583acM346c.m345b().equals(c0599asM429a.m418a().m398a().m345b()) || this.f200a.m386o()) {
                                C0596ap c0596apM403e = c0599asM429a.m418a().m403e();
                                if (C0510g.m145b(strM400b)) {
                                    boolean zEquals = strM400b.equals("PROPFIND");
                                    if (true ^ strM400b.equals("PROPFIND")) {
                                        c0596apM403e.m411a("GET", (AbstractC0597aq) null);
                                    } else {
                                        c0596apM403e.m411a(strM400b, zEquals ? c0599asM429a.m418a().m402d() : null);
                                    }
                                    if (!zEquals) {
                                        c0596apM403e.m413b("Transfer-Encoding");
                                        c0596apM403e.m413b("Content-Length");
                                        c0596apM403e.m413b("Content-Type");
                                    }
                                }
                                if (!m156a(c0599asM429a, c0583acM346c)) {
                                    c0596apM403e.m413b("Authorization");
                                }
                                c0595aoMo146a = c0596apM403e.m408a(c0583acM346c).m406a();
                            } else {
                                c0595aoMo146a = null;
                            }
                            if (c0595aoMo146a == null) {
                                if (!this.f201b) {
                                    this.f202c.m101c();
                                }
                                return c0599asM429a;
                            }
                            C0503c.m116a(c0599asM429a.m423e());
                            i++;
                            if (i > 20) {
                                this.f202c.m101c();
                                throw new ProtocolException("Too many follow-up requests: " + i);
                            }
                            if (c0595aoMo146a.m402d() instanceof InterfaceC0516m) {
                                this.f202c.m101c();
                                throw new HttpRetryException("Cannot retry streamed HTTP body", c0599asM429a.m420b());
                            }
                            if (!m156a(c0599asM429a, c0595aoMo146a.m398a())) {
                                this.f202c.m101c();
                                this.f202c = new C0501g(this.f200a.m385n(), m155a(c0595aoMo146a.m398a()), this.f203d);
                            } else if (this.f202c.m94a() != null) {
                                throw new IllegalStateException("Closing the body of " + c0599asM429a + " didn't close its backing stream. Bad interceptor?");
                            }
                            break;
                            break;
                        case GET_ENABLED_VALUE:
                            c0595aoMo146a = this.f200a.m383l().mo450a();
                            if (c0595aoMo146a == null) {
                                if (!this.f201b) {
                                    this.f202c.m101c();
                                }
                                return c0599asM429a;
                            }
                            C0503c.m116a(c0599asM429a.m423e());
                            i++;
                            if (i > 20) {
                                this.f202c.m101c();
                                throw new ProtocolException("Too many follow-up requests: " + i);
                            }
                            if (c0595aoMo146a.m402d() instanceof InterfaceC0516m) {
                                this.f202c.m101c();
                                throw new HttpRetryException("Cannot retry streamed HTTP body", c0599asM429a.m420b());
                            }
                            if (!m156a(c0599asM429a, c0595aoMo146a.m398a())) {
                                this.f202c.m101c();
                                this.f202c = new C0501g(this.f200a.m385n(), m155a(c0595aoMo146a.m398a()), this.f203d);
                            } else if (this.f202c.m94a() != null) {
                                throw new IllegalStateException("Closing the body of " + c0599asM429a + " didn't close its backing stream. Bad interceptor?");
                            }
                            break;
                            break;
                        case GET_TEXTCOLOR_VALUE:
                            if (c0603awMo68a != null) {
                                proxyM375d = c0603awMo68a.m446b();
                            } else {
                                proxyM375d = this.f200a.m375d();
                            }
                            if (proxyM375d.type() != Proxy.Type.HTTP) {
                                throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
                            }
                            c0595aoMo146a = this.f200a.m384m().mo450a();
                            if (c0595aoMo146a == null) {
                                if (!this.f201b) {
                                    this.f202c.m101c();
                                }
                                return c0599asM429a;
                            }
                            C0503c.m116a(c0599asM429a.m423e());
                            i++;
                            if (i > 20) {
                                this.f202c.m101c();
                                throw new ProtocolException("Too many follow-up requests: " + i);
                            }
                            if (c0595aoMo146a.m402d() instanceof InterfaceC0516m) {
                                this.f202c.m101c();
                                throw new HttpRetryException("Cannot retry streamed HTTP body", c0599asM429a.m420b());
                            }
                            if (!m156a(c0599asM429a, c0595aoMo146a.m398a())) {
                                this.f202c.m101c();
                                this.f202c = new C0501g(this.f200a.m385n(), m155a(c0595aoMo146a.m398a()), this.f203d);
                            } else if (this.f202c.m94a() != null) {
                                throw new IllegalStateException("Closing the body of " + c0599asM429a + " didn't close its backing stream. Bad interceptor?");
                            }
                            break;
                            break;
                        case GET_VALUE_VALUE:
                            c0595aoMo146a = !(c0599asM429a.m418a().m402d() instanceof InterfaceC0516m) ? c0599asM429a.m418a() : null;
                            if (c0595aoMo146a == null) {
                                if (!this.f201b) {
                                    this.f202c.m101c();
                                }
                                return c0599asM429a;
                            }
                            C0503c.m116a(c0599asM429a.m423e());
                            i++;
                            if (i > 20) {
                                this.f202c.m101c();
                                throw new ProtocolException("Too many follow-up requests: " + i);
                            }
                            if (c0595aoMo146a.m402d() instanceof InterfaceC0516m) {
                                this.f202c.m101c();
                                throw new HttpRetryException("Cannot retry streamed HTTP body", c0599asM429a.m420b());
                            }
                            if (!m156a(c0599asM429a, c0595aoMo146a.m398a())) {
                                this.f202c.m101c();
                                this.f202c = new C0501g(this.f200a.m385n(), m155a(c0595aoMo146a.m398a()), this.f203d);
                            } else if (this.f202c.m94a() != null) {
                                throw new IllegalStateException("Closing the body of " + c0599asM429a + " didn't close its backing stream. Bad interceptor?");
                            }
                            break;
                            break;
                        default:
                            c0595aoMo146a = null;
                            if (c0595aoMo146a == null) {
                                if (!this.f201b) {
                                    this.f202c.m101c();
                                }
                                return c0599asM429a;
                            }
                            C0503c.m116a(c0599asM429a.m423e());
                            i++;
                            if (i > 20) {
                                this.f202c.m101c();
                                throw new ProtocolException("Too many follow-up requests: " + i);
                            }
                            if (c0595aoMo146a.m402d() instanceof InterfaceC0516m) {
                                this.f202c.m101c();
                                throw new HttpRetryException("Cannot retry streamed HTTP body", c0599asM429a.m420b());
                            }
                            if (!m156a(c0599asM429a, c0595aoMo146a.m398a())) {
                                this.f202c.m101c();
                                this.f202c = new C0501g(this.f200a.m385n(), m155a(c0595aoMo146a.m398a()), this.f203d);
                            } else if (this.f202c.m94a() != null) {
                                throw new IllegalStateException("Closing the body of " + c0599asM429a + " didn't close its backing stream. Bad interceptor?");
                            }
                            break;
                            break;
                    }
                } catch (C0499e e) {
                    if (!m157a(e.m80a(), false, c0595aoMo146a)) {
                        throw e.m80a();
                    }
                } catch (IOException e2) {
                    if (e2 instanceof C0525a) {
                        z = false;
                    }
                    if (!m157a(e2, z, c0595aoMo146a)) {
                        throw e2;
                    }
                }
            } catch (Throwable th) {
                this.f202c.m97a((IOException) null);
                this.f202c.m101c();
                throw th;
            }
        }
        this.f202c.m101c();
        throw new IOException("Canceled");
    }

    /* JADX INFO: renamed from: a */
    private C0483a m155a(C0583ac c0583ac) {
        SSLSocketFactory sSLSocketFactory;
        HostnameVerifier hostnameVerifierM381j;
        C0612i c0612iM382k;
        if (c0583ac.m347c()) {
            SSLSocketFactory sSLSocketFactoryM380i = this.f200a.m380i();
            hostnameVerifierM381j = this.f200a.m381j();
            sSLSocketFactory = sSLSocketFactoryM380i;
            c0612iM382k = this.f200a.m382k();
        } else {
            sSLSocketFactory = null;
            hostnameVerifierM381j = null;
            c0612iM382k = null;
        }
        return new C0483a(c0583ac.m351f(), c0583ac.m352g(), this.f200a.m378g(), this.f200a.m379h(), sSLSocketFactory, hostnameVerifierM381j, c0612iM382k, this.f200a.m384m(), this.f200a.m375d(), this.f200a.m389r(), this.f200a.m390s(), this.f200a.m376e());
    }

    /* JADX INFO: renamed from: a */
    private boolean m157a(IOException iOException, boolean z, C0595ao c0595ao) {
        this.f202c.m97a(iOException);
        if (!this.f200a.m388q()) {
            return false;
        }
        if (z && (c0595ao.m402d() instanceof InterfaceC0516m)) {
            return false;
        }
        return (!(iOException instanceof ProtocolException) && (!(iOException instanceof InterruptedIOException) ? (!(iOException instanceof SSLHandshakeException) || !(iOException.getCause() instanceof CertificateException)) && !(iOException instanceof SSLPeerUnverifiedException) : (iOException instanceof SocketTimeoutException) && !z)) && this.f202c.m103e();
    }

    /* JADX INFO: renamed from: a */
    private static boolean m156a(C0599as c0599as, C0583ac c0583ac) {
        C0583ac c0583acM398a = c0599as.m418a().m398a();
        return c0583acM398a.m351f().equals(c0583ac.m351f()) && c0583acM398a.m352g() == c0583ac.m352g() && c0583acM398a.m345b().equals(c0583ac.m345b());
    }
}
