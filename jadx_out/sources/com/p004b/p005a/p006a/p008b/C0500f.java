package com.p004b.p005a.p006a.p008b;

import com.p004b.p005a.C0483a;
import com.p004b.p005a.C0583ac;
import com.p004b.p005a.C0603aw;
import com.p004b.p005a.p006a.C0503c;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: renamed from: com.b.a.a.b.f */
/* JADX INFO: compiled from: RouteSelector.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0500f {

    /* JADX INFO: renamed from: a */
    private final C0483a f148a;

    /* JADX INFO: renamed from: b */
    private final C0498d f149b;

    /* JADX INFO: renamed from: c */
    private Proxy f150c;

    /* JADX INFO: renamed from: d */
    private InetSocketAddress f151d;

    /* JADX INFO: renamed from: e */
    private List<Proxy> f152e;

    /* JADX INFO: renamed from: f */
    private int f153f;

    /* JADX INFO: renamed from: h */
    private int f155h;

    /* JADX INFO: renamed from: g */
    private List<InetSocketAddress> f154g = Collections.emptyList();

    /* JADX INFO: renamed from: i */
    private final List<C0603aw> f156i = new ArrayList();

    public C0500f(C0483a c0483a, C0498d c0498d) {
        List<Proxy> listM113a;
        this.f152e = Collections.emptyList();
        this.f148a = c0483a;
        this.f149b = c0498d;
        C0583ac c0583acM24a = c0483a.m24a();
        Proxy proxyM31h = c0483a.m31h();
        if (proxyM31h != null) {
            listM113a = Collections.singletonList(proxyM31h);
        } else {
            List<Proxy> listSelect = this.f148a.m30g().select(c0583acM24a.m344a());
            if (listSelect != null && !listSelect.isEmpty()) {
                listM113a = C0503c.m112a(listSelect);
            } else {
                listM113a = C0503c.m113a(Proxy.NO_PROXY);
            }
        }
        this.f152e = listM113a;
        this.f153f = 0;
    }

    /* JADX INFO: renamed from: a */
    public final boolean m87a() {
        return m84d() || m83c() || m85e();
    }

    /* JADX INFO: renamed from: b */
    public final C0603aw m88b() {
        while (true) {
            if (!m84d()) {
                if (!m83c()) {
                    if (!m85e()) {
                        throw new NoSuchElementException();
                    }
                    return this.f156i.remove(0);
                }
                if (!m83c()) {
                    throw new SocketException("No route to " + this.f148a.m24a().m351f() + "; exhausted proxy configurations: " + this.f152e);
                }
                List<Proxy> list = this.f152e;
                int i = this.f153f;
                this.f153f = i + 1;
                Proxy proxy = list.get(i);
                m82a(proxy);
                this.f150c = proxy;
            }
            if (!m84d()) {
                throw new SocketException("No route to " + this.f148a.m24a().m351f() + "; exhausted inet socket addresses: " + this.f154g);
            }
            List<InetSocketAddress> list2 = this.f154g;
            int i2 = this.f155h;
            this.f155h = i2 + 1;
            this.f151d = list2.get(i2);
            C0603aw c0603aw = new C0603aw(this.f148a, this.f150c, this.f151d);
            if (!this.f149b.m79c(c0603aw)) {
                return c0603aw;
            }
            this.f156i.add(c0603aw);
        }
    }

    /* JADX INFO: renamed from: a */
    public final void m86a(C0603aw c0603aw, IOException iOException) {
        if (c0603aw.m446b().type() != Proxy.Type.DIRECT && this.f148a.m30g() != null) {
            this.f148a.m30g().connectFailed(this.f148a.m24a().m344a(), c0603aw.m446b().address(), iOException);
        }
        this.f149b.m77a(c0603aw);
    }

    /* JADX INFO: renamed from: c */
    private boolean m83c() {
        return this.f153f < this.f152e.size();
    }

    /* JADX INFO: renamed from: a */
    private void m82a(Proxy proxy) throws SocketException {
        String strM351f;
        int iM352g;
        this.f154g = new ArrayList();
        if (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.SOCKS) {
            strM351f = this.f148a.m24a().m351f();
            iM352g = this.f148a.m24a().m352g();
        } else {
            SocketAddress socketAddressAddress = proxy.address();
            if (!(socketAddressAddress instanceof InetSocketAddress)) {
                throw new IllegalArgumentException("Proxy.address() is not an InetSocketAddress: " + socketAddressAddress.getClass());
            }
            InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddressAddress;
            InetAddress address = inetSocketAddress.getAddress();
            if (address == null) {
                strM351f = inetSocketAddress.getHostName();
            } else {
                strM351f = address.getHostAddress();
            }
            iM352g = inetSocketAddress.getPort();
        }
        if (iM352g <= 0 || iM352g > 65535) {
            throw new SocketException("No route to " + strM351f + ":" + iM352g + "; port is out of range");
        }
        if (proxy.type() == Proxy.Type.SOCKS) {
            this.f154g.add(InetSocketAddress.createUnresolved(strM351f, iM352g));
        } else {
            List<InetAddress> listMo501a = this.f148a.m25b().mo501a(strM351f);
            int size = listMo501a.size();
            for (int i = 0; i < size; i++) {
                this.f154g.add(new InetSocketAddress(listMo501a.get(i), iM352g));
            }
        }
        this.f155h = 0;
    }

    /* JADX INFO: renamed from: d */
    private boolean m84d() {
        return this.f155h < this.f154g.size();
    }

    /* JADX INFO: renamed from: e */
    private boolean m85e() {
        return !this.f156i.isEmpty();
    }
}
