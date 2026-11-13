package com.p004b.p005a.p006a.p010d;

import com.p004b.p005a.AbstractC0601au;
import com.p004b.p005a.C0581aa;
import com.p004b.p005a.C0582ab;
import com.p004b.p005a.C0583ac;
import com.p004b.p005a.C0589ai;
import com.p004b.p005a.C0595ao;
import com.p004b.p005a.C0599as;
import com.p004b.p005a.C0600at;
import com.p004b.p005a.p006a.AbstractC0484a;
import com.p004b.p005a.p006a.p008b.C0501g;
import com.p004b.p005a.p006a.p009c.C0509f;
import com.p004b.p005a.p006a.p009c.C0512i;
import com.p004b.p005a.p006a.p009c.C0513j;
import com.p004b.p005a.p006a.p009c.C0515l;
import com.p004b.p005a.p006a.p009c.InterfaceC0506c;
import com.p004b.p015b.C0641k;
import com.p004b.p015b.C0644n;
import com.p004b.p015b.C0655y;
import com.p004b.p015b.InterfaceC0637g;
import com.p004b.p015b.InterfaceC0638h;
import com.p004b.p015b.InterfaceC0653w;
import com.p004b.p015b.InterfaceC0654x;
import java.io.EOFException;
import java.io.IOException;
import java.net.Proxy;
import org.apache.commons.p092io.IOUtils;

/* JADX INFO: renamed from: com.b.a.a.d.a */
/* JADX INFO: compiled from: Http1Codec.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0518a implements InterfaceC0506c {

    /* JADX INFO: renamed from: a */
    final C0589ai f210a;

    /* JADX INFO: renamed from: b */
    final C0501g f211b;

    /* JADX INFO: renamed from: c */
    final InterfaceC0638h f212c;

    /* JADX INFO: renamed from: d */
    final InterfaceC0637g f213d;

    /* JADX INFO: renamed from: e */
    int f214e = 0;

    public C0518a(C0589ai c0589ai, C0501g c0501g, InterfaceC0638h interfaceC0638h, InterfaceC0637g interfaceC0637g) {
        this.f210a = c0589ai;
        this.f211b = c0501g;
        this.f212c = interfaceC0638h;
        this.f213d = interfaceC0637g;
    }

    @Override // com.p004b.p005a.p006a.p009c.InterfaceC0506c
    /* JADX INFO: renamed from: a */
    public final InterfaceC0653w mo130a(C0595ao c0595ao, long j) {
        if ("chunked".equalsIgnoreCase(c0595ao.m399a("Transfer-Encoding"))) {
            if (this.f214e == 1) {
                this.f214e = 2;
                return new C0520c(this);
            }
            throw new IllegalStateException("state: " + this.f214e);
        }
        if (j == -1) {
            throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
        }
        if (this.f214e == 1) {
            this.f214e = 2;
            return new C0522e(this, j);
        }
        throw new IllegalStateException("state: " + this.f214e);
    }

    @Override // com.p004b.p005a.p006a.p009c.InterfaceC0506c
    /* JADX INFO: renamed from: a */
    public final void mo132a(C0595ao c0595ao) {
        Proxy.Type type = this.f211b.m99b().mo68a().m446b().type();
        StringBuilder sb = new StringBuilder();
        sb.append(c0595ao.m400b());
        sb.append(' ');
        if (!c0595ao.m405g() && type == Proxy.Type.HTTP) {
            sb.append(c0595ao.m398a());
        } else {
            sb.append(C0513j.m154a(c0595ao.m398a()));
        }
        sb.append(" HTTP/1.1");
        m163a(c0595ao.m401c(), sb.toString());
    }

    @Override // com.p004b.p005a.p006a.p009c.InterfaceC0506c
    /* JADX INFO: renamed from: a */
    public final void mo131a() {
        this.f213d.flush();
    }

    @Override // com.p004b.p005a.p006a.p009c.InterfaceC0506c
    /* JADX INFO: renamed from: b */
    public final void mo133b() {
        this.f213d.flush();
    }

    /* JADX INFO: renamed from: a */
    public final void m163a(C0581aa c0581aa, String str) {
        if (this.f214e != 0) {
            throw new IllegalStateException("state: " + this.f214e);
        }
        this.f213d.mo542b(str).mo542b(IOUtils.LINE_SEPARATOR_WINDOWS);
        int iM321a = c0581aa.m321a();
        for (int i = 0; i < iM321a; i++) {
            this.f213d.mo542b(c0581aa.m322a(i)).mo542b(": ").mo542b(c0581aa.m325b(i)).mo542b(IOUtils.LINE_SEPARATOR_WINDOWS);
        }
        this.f213d.mo542b(IOUtils.LINE_SEPARATOR_WINDOWS);
        this.f214e = 1;
    }

    @Override // com.p004b.p005a.p006a.p009c.InterfaceC0506c
    /* JADX INFO: renamed from: a */
    public final C0600at mo128a(boolean z) throws IOException {
        if (this.f214e != 1 && this.f214e != 3) {
            throw new IllegalStateException("state: " + this.f214e);
        }
        try {
            C0515l c0515lM160a = C0515l.m160a(this.f212c.mo570m());
            C0600at c0600atM432a = new C0600at().m433a(c0515lM160a.f205a).m430a(c0515lM160a.f206b).m438a(c0515lM160a.f207c).m432a(m164c());
            if (z && c0515lM160a.f206b == 100) {
                return null;
            }
            this.f214e = 4;
            return c0600atM432a;
        } catch (EOFException e) {
            IOException iOException = new IOException("unexpected end of stream on " + this.f211b);
            iOException.initCause(e);
            throw iOException;
        }
    }

    /* JADX INFO: renamed from: c */
    public final C0581aa m164c() {
        C0582ab c0582ab = new C0582ab();
        while (true) {
            String strMo570m = this.f212c.mo570m();
            if (strMo570m.length() != 0) {
                AbstractC0484a.f81a.mo38a(c0582ab, strMo570m);
            } else {
                return c0582ab.m327a();
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public final InterfaceC0654x m162a(long j) {
        if (this.f214e == 4) {
            this.f214e = 5;
            return new C0523f(this, j);
        }
        throw new IllegalStateException("state: " + this.f214e);
    }

    /* JADX INFO: renamed from: a */
    static void m161a(C0641k c0641k) {
        C0655y c0655yM593a = c0641k.m593a();
        c0641k.m592a(C0655y.f854b);
        c0655yM593a.mo600e_();
        c0655yM593a.mo599d_();
    }

    @Override // com.p004b.p005a.p006a.p009c.InterfaceC0506c
    /* JADX INFO: renamed from: a */
    public final AbstractC0601au mo129a(C0599as c0599as) {
        InterfaceC0654x c0524g;
        if (!C0509f.m143b(c0599as)) {
            c0524g = m162a(0L);
        } else if ("chunked".equalsIgnoreCase(c0599as.m419a("Transfer-Encoding"))) {
            C0583ac c0583acM398a = c0599as.m418a().m398a();
            if (this.f214e != 4) {
                throw new IllegalStateException("state: " + this.f214e);
            }
            this.f214e = 5;
            c0524g = new C0521d(this, c0583acM398a);
        } else {
            long jM139a = C0509f.m139a(c0599as);
            if (jM139a != -1) {
                c0524g = m162a(jM139a);
            } else {
                if (this.f214e != 4) {
                    throw new IllegalStateException("state: " + this.f214e);
                }
                if (this.f211b == null) {
                    throw new IllegalStateException("streamAllocation == null");
                }
                this.f214e = 5;
                this.f211b.m102d();
                c0524g = new C0524g(this);
            }
        }
        return new C0512i(c0599as.m422d(), C0644n.m606a(c0524g));
    }
}
