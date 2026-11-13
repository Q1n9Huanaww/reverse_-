package com.p004b.p005a.p006a.p009c;

import com.p004b.p005a.C0583ac;
import com.p004b.p005a.C0595ao;
import com.p004b.p005a.C0599as;
import com.p004b.p005a.InterfaceC0586af;
import com.p004b.p005a.InterfaceC0587ag;
import com.p004b.p005a.InterfaceC0616m;
import com.p004b.p005a.p006a.p008b.C0501g;
import java.util.List;

/* JADX INFO: renamed from: com.b.a.a.c.h */
/* JADX INFO: compiled from: RealInterceptorChain.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0511h implements InterfaceC0587ag {

    /* JADX INFO: renamed from: a */
    private final List<InterfaceC0586af> f191a;

    /* JADX INFO: renamed from: b */
    private final C0501g f192b;

    /* JADX INFO: renamed from: c */
    private final InterfaceC0506c f193c;

    /* JADX INFO: renamed from: d */
    private final InterfaceC0616m f194d;

    /* JADX INFO: renamed from: e */
    private final int f195e;

    /* JADX INFO: renamed from: f */
    private final C0595ao f196f;

    /* JADX INFO: renamed from: g */
    private int f197g;

    public C0511h(List<InterfaceC0586af> list, C0501g c0501g, InterfaceC0506c interfaceC0506c, InterfaceC0616m interfaceC0616m, int i, C0595ao c0595ao) {
        this.f191a = list;
        this.f194d = interfaceC0616m;
        this.f192b = c0501g;
        this.f193c = interfaceC0506c;
        this.f195e = i;
        this.f196f = c0595ao;
    }

    /* JADX INFO: renamed from: b */
    public final C0501g m149b() {
        return this.f192b;
    }

    /* JADX INFO: renamed from: c */
    public final InterfaceC0506c m150c() {
        return this.f193c;
    }

    @Override // com.p004b.p005a.InterfaceC0587ag
    /* JADX INFO: renamed from: a */
    public final C0595ao mo146a() {
        return this.f196f;
    }

    @Override // com.p004b.p005a.InterfaceC0587ag
    /* JADX INFO: renamed from: a */
    public final C0599as mo147a(C0595ao c0595ao) {
        return m148a(c0595ao, this.f192b, this.f193c, this.f194d);
    }

    /* JADX INFO: renamed from: a */
    public final C0599as m148a(C0595ao c0595ao, C0501g c0501g, InterfaceC0506c interfaceC0506c, InterfaceC0616m interfaceC0616m) {
        if (this.f195e >= this.f191a.size()) {
            throw new AssertionError();
        }
        this.f197g++;
        if (this.f193c != null) {
            C0583ac c0583acM398a = c0595ao.m398a();
            if (!(c0583acM398a.m351f().equals(this.f194d.mo68a().m445a().m24a().m351f()) && c0583acM398a.m352g() == this.f194d.mo68a().m445a().m24a().m352g())) {
                throw new IllegalStateException("network interceptor " + this.f191a.get(this.f195e - 1) + " must retain the same host and port");
            }
        }
        if (this.f193c != null && this.f197g > 1) {
            throw new IllegalStateException("network interceptor " + this.f191a.get(this.f195e - 1) + " must call proceed() exactly once");
        }
        C0511h c0511h = new C0511h(this.f191a, c0501g, interfaceC0506c, interfaceC0616m, this.f195e + 1, c0595ao);
        InterfaceC0586af interfaceC0586af = this.f191a.get(this.f195e);
        C0599as c0599asMo46a = interfaceC0586af.mo46a(c0511h);
        if (interfaceC0506c != null && this.f195e + 1 < this.f191a.size() && c0511h.f197g != 1) {
            throw new IllegalStateException("network interceptor " + interfaceC0586af + " must call proceed() exactly once");
        }
        if (c0599asMo46a != null) {
            return c0599asMo46a;
        }
        throw new NullPointerException("interceptor " + interfaceC0586af + " returned null");
    }
}
