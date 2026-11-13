package com.p004b.p005a.p006a.p009c;

import com.p004b.p005a.C0595ao;
import com.p004b.p005a.C0599as;
import com.p004b.p005a.C0600at;
import com.p004b.p005a.InterfaceC0586af;
import com.p004b.p005a.InterfaceC0587ag;
import com.p004b.p005a.p006a.C0503c;
import com.p004b.p005a.p006a.p008b.C0501g;
import com.p004b.p015b.C0644n;
import com.p004b.p015b.InterfaceC0637g;
import java.net.ProtocolException;

/* JADX INFO: renamed from: com.b.a.a.c.b */
/* JADX INFO: compiled from: CallServerInterceptor.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0505b implements InterfaceC0586af {

    /* JADX INFO: renamed from: a */
    private final boolean f186a;

    public C0505b(boolean z) {
        this.f186a = z;
    }

    @Override // com.p004b.p005a.InterfaceC0586af
    /* JADX INFO: renamed from: a */
    public final C0599as mo46a(InterfaceC0587ag interfaceC0587ag) throws ProtocolException {
        C0599as c0599asM429a;
        C0511h c0511h = (C0511h) interfaceC0587ag;
        InterfaceC0506c interfaceC0506cM150c = c0511h.m150c();
        C0501g c0501gM149b = c0511h.m149b();
        C0595ao c0595aoMo146a = interfaceC0587ag.mo146a();
        long jCurrentTimeMillis = System.currentTimeMillis();
        interfaceC0506cM150c.mo132a(c0595aoMo146a);
        C0600at c0600atMo128a = null;
        if (C0510g.m145b(c0595aoMo146a.m400b()) && c0595aoMo146a.m402d() != null) {
            if ("100-continue".equalsIgnoreCase(c0595aoMo146a.m399a("Expect"))) {
                interfaceC0506cM150c.mo131a();
                c0600atMo128a = interfaceC0506cM150c.mo128a(true);
            }
            if (c0600atMo128a == null) {
                InterfaceC0637g interfaceC0637gM605a = C0644n.m605a(interfaceC0506cM150c.mo130a(c0595aoMo146a, c0595aoMo146a.m402d().mo417b()));
                c0595aoMo146a.m402d().mo416a(interfaceC0637gM605a);
                interfaceC0637gM605a.close();
            }
        }
        interfaceC0506cM150c.mo133b();
        if (c0600atMo128a == null) {
            c0600atMo128a = interfaceC0506cM150c.mo128a(false);
        }
        C0599as c0599asM429a2 = c0600atMo128a.m434a(c0595aoMo146a).m437a(c0501gM149b.m99b().m75c()).m431a(jCurrentTimeMillis).m440b(System.currentTimeMillis()).m429a();
        int iM420b = c0599asM429a2.m420b();
        if (this.f186a && iM420b == 101) {
            c0599asM429a = c0599asM429a2.m424f().m436a(C0503c.f171c).m429a();
        } else {
            c0599asM429a = c0599asM429a2.m424f().m436a(interfaceC0506cM150c.mo129a(c0599asM429a2)).m429a();
        }
        if ("close".equalsIgnoreCase(c0599asM429a.m418a().m399a("Connection")) || "close".equalsIgnoreCase(c0599asM429a.m419a("Connection"))) {
            c0501gM149b.m102d();
        }
        if ((iM420b != 204 && iM420b != 205) || c0599asM429a.m423e().mo152b() <= 0) {
            return c0599asM429a;
        }
        throw new ProtocolException("HTTP " + iM420b + " had non-zero Content-Length: " + c0599asM429a.m423e().mo152b());
    }
}
