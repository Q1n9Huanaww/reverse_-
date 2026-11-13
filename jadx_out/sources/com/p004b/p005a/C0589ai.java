package com.p004b.p005a;

import com.p004b.p005a.p006a.AbstractC0484a;
import com.p004b.p005a.p006a.C0503c;
import com.p004b.p005a.p006a.p007a.InterfaceC0493i;
import com.p004b.p005a.p006a.p013g.C0573h;
import com.p004b.p005a.p006a.p014h.AbstractC0575b;
import java.net.Proxy;
import java.net.ProxySelector;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: renamed from: com.b.a.ai */
/* JADX INFO: compiled from: OkHttpClient.java */
/* JADX INFO: loaded from: classes.dex */
public class C0589ai implements Cloneable {

    /* JADX INFO: renamed from: a */
    static final List<EnumC0592al> f481a = C0503c.m113a(EnumC0592al.HTTP_2, EnumC0592al.HTTP_1_1);

    /* JADX INFO: renamed from: b */
    static final List<C0619p> f482b = C0503c.m113a(C0619p.f756a, C0619p.f757b, C0619p.f758c);

    /* JADX INFO: renamed from: A */
    final int f483A;

    /* JADX INFO: renamed from: B */
    final int f484B;

    /* JADX INFO: renamed from: c */
    final C0624u f485c;

    /* JADX INFO: renamed from: d */
    final Proxy f486d;

    /* JADX INFO: renamed from: e */
    final List<EnumC0592al> f487e;

    /* JADX INFO: renamed from: f */
    final List<C0619p> f488f;

    /* JADX INFO: renamed from: g */
    final List<InterfaceC0586af> f489g;

    /* JADX INFO: renamed from: h */
    final List<InterfaceC0586af> f490h;

    /* JADX INFO: renamed from: i */
    final ProxySelector f491i;

    /* JADX INFO: renamed from: j */
    final InterfaceC0622s f492j;

    /* JADX INFO: renamed from: k */
    final C0607d f493k;

    /* JADX INFO: renamed from: l */
    final InterfaceC0493i f494l;

    /* JADX INFO: renamed from: m */
    final SocketFactory f495m;

    /* JADX INFO: renamed from: n */
    final SSLSocketFactory f496n;

    /* JADX INFO: renamed from: o */
    final AbstractC0575b f497o;

    /* JADX INFO: renamed from: p */
    final HostnameVerifier f498p;

    /* JADX INFO: renamed from: q */
    final C0612i f499q;

    /* JADX INFO: renamed from: r */
    final InterfaceC0605b f500r;

    /* JADX INFO: renamed from: s */
    final InterfaceC0605b f501s;

    /* JADX INFO: renamed from: t */
    final C0617n f502t;

    /* JADX INFO: renamed from: u */
    final InterfaceC0625v f503u;

    /* JADX INFO: renamed from: v */
    final boolean f504v;

    /* JADX INFO: renamed from: w */
    final boolean f505w;

    /* JADX INFO: renamed from: x */
    final boolean f506x;

    /* JADX INFO: renamed from: y */
    final int f507y;

    /* JADX INFO: renamed from: z */
    final int f508z;

    static {
        AbstractC0484a.f81a = new C0590aj();
    }

    public C0589ai() {
        this(new C0591ak());
    }

    private C0589ai(C0591ak c0591ak) {
        boolean z;
        this.f485c = c0591ak.f509a;
        this.f486d = c0591ak.f510b;
        this.f487e = c0591ak.f511c;
        this.f488f = c0591ak.f512d;
        this.f489g = C0503c.m112a(c0591ak.f513e);
        this.f490h = C0503c.m112a(c0591ak.f514f);
        this.f491i = c0591ak.f515g;
        this.f492j = c0591ak.f516h;
        this.f493k = c0591ak.f517i;
        this.f494l = c0591ak.f518j;
        this.f495m = c0591ak.f519k;
        Iterator<C0619p> it2 = this.f488f.iterator();
        loop0: while (true) {
            while (true) {
                if (!it2.hasNext()) {
                    break loop0;
                } else {
                    z = z || it2.next().f760d;
                }
            }
        }
        if (c0591ak.f520l != null || !z) {
            this.f496n = c0591ak.f520l;
            this.f497o = c0591ak.f521m;
        } else {
            X509TrustManager x509TrustManagerM370t = m370t();
            this.f496n = m369a(x509TrustManagerM370t);
            this.f497o = C0573h.m307b().mo284a(x509TrustManagerM370t);
        }
        this.f498p = c0591ak.f522n;
        this.f499q = c0591ak.f523o.m466a(this.f497o);
        this.f500r = c0591ak.f524p;
        this.f501s = c0591ak.f525q;
        this.f502t = c0591ak.f526r;
        this.f503u = c0591ak.f527s;
        this.f504v = c0591ak.f528t;
        this.f505w = c0591ak.f529u;
        this.f506x = c0591ak.f530v;
        this.f507y = c0591ak.f531w;
        this.f508z = c0591ak.f532x;
        this.f483A = c0591ak.f533y;
        this.f484B = c0591ak.f534z;
    }

    /* JADX INFO: renamed from: t */
    private static X509TrustManager m370t() {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length == 1 && (trustManagers[0] instanceof X509TrustManager)) {
                return (X509TrustManager) trustManagers[0];
            }
            throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
        } catch (GeneralSecurityException unused) {
            throw new AssertionError();
        }
    }

    /* JADX INFO: renamed from: a */
    private static SSLSocketFactory m369a(X509TrustManager x509TrustManager) {
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, new TrustManager[]{x509TrustManager}, null);
            return sSLContext.getSocketFactory();
        } catch (GeneralSecurityException unused) {
            throw new AssertionError();
        }
    }

    /* JADX INFO: renamed from: a */
    public final int m371a() {
        return this.f507y;
    }

    /* JADX INFO: renamed from: b */
    public final int m373b() {
        return this.f508z;
    }

    /* JADX INFO: renamed from: c */
    public final int m374c() {
        return this.f483A;
    }

    /* JADX INFO: renamed from: d */
    public final Proxy m375d() {
        return this.f486d;
    }

    /* JADX INFO: renamed from: e */
    public final ProxySelector m376e() {
        return this.f491i;
    }

    /* JADX INFO: renamed from: f */
    public final InterfaceC0622s m377f() {
        return this.f492j;
    }

    /* JADX INFO: renamed from: g */
    public final InterfaceC0625v m378g() {
        return this.f503u;
    }

    /* JADX INFO: renamed from: h */
    public final SocketFactory m379h() {
        return this.f495m;
    }

    /* JADX INFO: renamed from: i */
    public final SSLSocketFactory m380i() {
        return this.f496n;
    }

    /* JADX INFO: renamed from: j */
    public final HostnameVerifier m381j() {
        return this.f498p;
    }

    /* JADX INFO: renamed from: k */
    public final C0612i m382k() {
        return this.f499q;
    }

    /* JADX INFO: renamed from: l */
    public final InterfaceC0605b m383l() {
        return this.f501s;
    }

    /* JADX INFO: renamed from: m */
    public final InterfaceC0605b m384m() {
        return this.f500r;
    }

    /* JADX INFO: renamed from: n */
    public final C0617n m385n() {
        return this.f502t;
    }

    /* JADX INFO: renamed from: o */
    public final boolean m386o() {
        return this.f504v;
    }

    /* JADX INFO: renamed from: p */
    public final boolean m387p() {
        return this.f505w;
    }

    /* JADX INFO: renamed from: q */
    public final boolean m388q() {
        return this.f506x;
    }

    /* JADX INFO: renamed from: r */
    public final List<EnumC0592al> m389r() {
        return this.f487e;
    }

    /* JADX INFO: renamed from: s */
    public final List<C0619p> m390s() {
        return this.f488f;
    }

    /* JADX INFO: renamed from: a */
    public final InterfaceC0610g m372a(C0595ao c0595ao) {
        return new C0593am(this, c0595ao, false);
    }
}
