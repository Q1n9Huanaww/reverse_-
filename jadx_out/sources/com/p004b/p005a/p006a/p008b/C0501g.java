package com.p004b.p005a.p006a.p008b;

import com.p004b.p005a.C0483a;
import com.p004b.p005a.C0589ai;
import com.p004b.p005a.C0603aw;
import com.p004b.p005a.C0617n;
import com.p004b.p005a.p006a.AbstractC0484a;
import com.p004b.p005a.p006a.C0503c;
import com.p004b.p005a.p006a.p009c.InterfaceC0506c;
import com.p004b.p005a.p006a.p011e.C0525a;
import com.p004b.p005a.p006a.p011e.C0538am;
import com.p004b.p005a.p006a.p011e.EnumC0539b;
import java.io.IOException;
import java.lang.ref.Reference;
import java.net.Socket;

/* JADX INFO: renamed from: com.b.a.a.b.g */
/* JADX INFO: compiled from: StreamAllocation.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0501g {

    /* JADX INFO: renamed from: b */
    static final /* synthetic */ boolean f157b = true;

    /* JADX INFO: renamed from: a */
    public final C0483a f158a;

    /* JADX INFO: renamed from: c */
    private C0603aw f159c;

    /* JADX INFO: renamed from: d */
    private final C0617n f160d;

    /* JADX INFO: renamed from: e */
    private final Object f161e;

    /* JADX INFO: renamed from: f */
    private final C0500f f162f;

    /* JADX INFO: renamed from: g */
    private int f163g;

    /* JADX INFO: renamed from: h */
    private C0497c f164h;

    /* JADX INFO: renamed from: i */
    private boolean f165i;

    /* JADX INFO: renamed from: j */
    private boolean f166j;

    /* JADX INFO: renamed from: k */
    private InterfaceC0506c f167k;

    public C0501g(C0617n c0617n, C0483a c0483a, Object obj) {
        this.f160d = c0617n;
        this.f158a = c0483a;
        this.f162f = new C0500f(c0483a, m93f());
        this.f161e = obj;
    }

    /* JADX INFO: renamed from: a */
    public final InterfaceC0506c m95a(C0589ai c0589ai, boolean z) {
        try {
            InterfaceC0506c interfaceC0506cM67a = m90a(c0589ai.m371a(), c0589ai.m373b(), c0589ai.m374c(), c0589ai.m388q(), z).m67a(c0589ai, this);
            synchronized (this.f160d) {
                this.f167k = interfaceC0506cM67a;
            }
            return interfaceC0506cM67a;
        } catch (IOException e) {
            throw new C0499e(e);
        }
    }

    /* JADX INFO: renamed from: a */
    private C0497c m90a(int i, int i2, int i3, boolean z, boolean z2) throws Throwable {
        while (true) {
            C0497c c0497cM89a = m89a(i, i2, i3, z);
            synchronized (this.f160d) {
                if (c0497cM89a.f132b == 0) {
                    return c0497cM89a;
                }
                if (c0497cM89a.m73a(z2)) {
                    return c0497cM89a;
                }
                m102d();
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private C0497c m89a(int i, int i2, int i3, boolean z) throws Throwable {
        C0497c c0497c;
        synchronized (this.f160d) {
            if (this.f165i) {
                throw new IllegalStateException("released");
            }
            if (this.f167k != null) {
                throw new IllegalStateException("codec != null");
            }
            if (this.f166j) {
                throw new IOException("Canceled");
            }
            C0497c c0497c2 = this.f164h;
            if (c0497c2 != null && !c0497c2.f131a) {
                return c0497c2;
            }
            AbstractC0484a.f81a.mo36a(this.f160d, this.f158a, this);
            if (this.f164h != null) {
                return this.f164h;
            }
            C0603aw c0603awM88b = this.f159c;
            if (c0603awM88b == null) {
                c0603awM88b = this.f162f.m88b();
            }
            synchronized (this.f160d) {
                this.f159c = c0603awM88b;
                this.f163g = 0;
                c0497c = new C0497c(this.f160d, c0603awM88b);
                m96a(c0497c);
                if (this.f166j) {
                    throw new IOException("Canceled");
                }
            }
            c0497c.m69a(i, i2, i3, z);
            m93f().m78b(c0497c.mo68a());
            Socket socketMo42b = null;
            synchronized (this.f160d) {
                AbstractC0484a.f81a.mo43b(this.f160d, c0497c);
                if (c0497c.m76d()) {
                    socketMo42b = AbstractC0484a.f81a.mo42b(this.f160d, this.f158a, this);
                    c0497c = this.f164h;
                }
            }
            C0503c.m117a(socketMo42b);
            return c0497c;
        }
    }

    /* JADX INFO: renamed from: a */
    public final void m98a(boolean z, InterfaceC0506c interfaceC0506c) {
        Socket socketM91a;
        synchronized (this.f160d) {
            if (interfaceC0506c != null) {
                if (interfaceC0506c == this.f167k) {
                    if (!z) {
                        this.f164h.f132b++;
                    }
                    socketM91a = m91a(z, false, true);
                }
            }
            throw new IllegalStateException("expected " + this.f167k + " but was " + interfaceC0506c);
        }
        C0503c.m117a(socketM91a);
    }

    /* JADX INFO: renamed from: a */
    public final InterfaceC0506c m94a() {
        InterfaceC0506c interfaceC0506c;
        synchronized (this.f160d) {
            interfaceC0506c = this.f167k;
        }
        return interfaceC0506c;
    }

    /* JADX INFO: renamed from: f */
    private C0498d m93f() {
        return AbstractC0484a.f81a.mo37a(this.f160d);
    }

    /* JADX INFO: renamed from: b */
    public final synchronized C0497c m99b() {
        return this.f164h;
    }

    /* JADX INFO: renamed from: c */
    public final void m101c() {
        Socket socketM91a;
        synchronized (this.f160d) {
            socketM91a = m91a(false, true, false);
        }
        C0503c.m117a(socketM91a);
    }

    /* JADX INFO: renamed from: d */
    public final void m102d() {
        Socket socketM91a;
        synchronized (this.f160d) {
            socketM91a = m91a(true, false, false);
        }
        C0503c.m117a(socketM91a);
    }

    /* JADX WARN: Code duplicated, block: B:29:0x005e  */
    /* JADX INFO: renamed from: a */
    private Socket m91a(boolean z, boolean z2, boolean z3) {
        Socket socketM74b;
        if (!f157b && !Thread.holdsLock(this.f160d)) {
            throw new AssertionError();
        }
        if (z3) {
            this.f167k = null;
        }
        if (z2) {
            this.f165i = true;
        }
        if (this.f164h != null) {
            if (z) {
                this.f164h.f131a = true;
            }
            if (this.f167k == null && (this.f165i || this.f164h.f131a)) {
                m92c(this.f164h);
                if (this.f164h.f134d.isEmpty()) {
                    this.f164h.f135e = System.nanoTime();
                    if (AbstractC0484a.f81a.mo41a(this.f160d, this.f164h)) {
                        socketM74b = this.f164h.m74b();
                    } else {
                        socketM74b = null;
                    }
                } else {
                    socketM74b = null;
                }
                this.f164h = null;
                return socketM74b;
            }
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x004b  */
    /* JADX INFO: renamed from: a */
    public final void m97a(IOException iOException) {
        boolean z;
        Socket socketM91a;
        synchronized (this.f160d) {
            if (iOException instanceof C0538am) {
                C0538am c0538am = (C0538am) iOException;
                if (c0538am.f279a == EnumC0539b.REFUSED_STREAM) {
                    this.f163g++;
                }
                if (c0538am.f279a != EnumC0539b.REFUSED_STREAM || this.f163g > 1) {
                    this.f159c = null;
                    z = true;
                } else {
                    z = false;
                }
            } else if (this.f164h == null || (this.f164h.m76d() && !(iOException instanceof C0525a))) {
                z = false;
            } else {
                if (this.f164h.f132b == 0) {
                    if (this.f159c != null && iOException != null) {
                        this.f162f.m86a(this.f159c, iOException);
                    }
                    this.f159c = null;
                }
                z = true;
            }
            socketM91a = m91a(z, false, true);
        }
        C0503c.m117a(socketM91a);
    }

    /* JADX INFO: renamed from: a */
    public final void m96a(C0497c c0497c) {
        if (!f157b && !Thread.holdsLock(this.f160d)) {
            throw new AssertionError();
        }
        if (this.f164h != null) {
            throw new IllegalStateException();
        }
        this.f164h = c0497c;
        c0497c.f134d.add(new C0502h(this, this.f161e));
    }

    /* JADX INFO: renamed from: c */
    private void m92c(C0497c c0497c) {
        int size = c0497c.f134d.size();
        for (int i = 0; i < size; i++) {
            if (c0497c.f134d.get(i).get() == this) {
                c0497c.f134d.remove(i);
                return;
            }
        }
        throw new IllegalStateException();
    }

    /* JADX INFO: renamed from: b */
    public final Socket m100b(C0497c c0497c) {
        if (!f157b && !Thread.holdsLock(this.f160d)) {
            throw new AssertionError();
        }
        if (this.f167k != null || this.f164h.f134d.size() != 1) {
            throw new IllegalStateException();
        }
        Reference<C0501g> reference = this.f164h.f134d.get(0);
        Socket socketM91a = m91a(true, false, false);
        this.f164h = c0497c;
        c0497c.f134d.add(reference);
        return socketM91a;
    }

    /* JADX INFO: renamed from: e */
    public final boolean m103e() {
        return this.f159c != null || this.f162f.m87a();
    }

    public final String toString() {
        C0497c c0497cM99b = m99b();
        return c0497cM99b != null ? c0497cM99b.toString() : this.f158a.toString();
    }
}
