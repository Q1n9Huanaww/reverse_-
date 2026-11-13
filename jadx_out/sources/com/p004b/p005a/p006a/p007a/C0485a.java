package com.p004b.p005a.p006a.p007a;

import com.android.volley.toolbox.HttpClientStack;
import com.cyjh.mobileanjian.ipc.share.proto.UiMessage;
import com.github.kevinsawicki.http.HttpRequest;
import com.p004b.p005a.AbstractC0601au;
import com.p004b.p005a.C0581aa;
import com.p004b.p005a.C0582ab;
import com.p004b.p005a.C0595ao;
import com.p004b.p005a.C0599as;
import com.p004b.p005a.C0600at;
import com.p004b.p005a.EnumC0592al;
import com.p004b.p005a.InterfaceC0586af;
import com.p004b.p005a.InterfaceC0587ag;
import com.p004b.p005a.p006a.AbstractC0484a;
import com.p004b.p005a.p006a.C0503c;
import com.p004b.p005a.p006a.p009c.C0509f;
import com.p004b.p005a.p006a.p009c.C0512i;
import com.p004b.p015b.C0644n;
import com.p004b.p015b.InterfaceC0653w;

/* JADX INFO: renamed from: com.b.a.a.a.a */
/* JADX INFO: compiled from: CacheInterceptor.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0485a implements InterfaceC0586af {

    /* JADX INFO: renamed from: a */
    final InterfaceC0493i f82a;

    public C0485a(InterfaceC0493i interfaceC0493i) {
        this.f82a = interfaceC0493i;
    }

    @Override // com.p004b.p005a.InterfaceC0586af
    /* JADX INFO: renamed from: a */
    public final C0599as mo46a(InterfaceC0587ag interfaceC0587ag) {
        InterfaceC0653w interfaceC0653wM49a;
        InterfaceC0487c interfaceC0487cM61b = null;
        C0599as c0599asM60a = this.f82a != null ? this.f82a.m60a() : null;
        C0488d c0488dM51a = new C0489e(System.currentTimeMillis(), interfaceC0587ag.mo146a(), c0599asM60a).m51a();
        C0595ao c0595ao = c0488dM51a.f88a;
        C0599as c0599as = c0488dM51a.f89b;
        if (c0599asM60a != null && c0599as == null) {
            C0503c.m116a(c0599asM60a.m423e());
        }
        if (c0595ao == null && c0599as == null) {
            return new C0600at().m434a(interfaceC0587ag.mo146a()).m433a(EnumC0592al.HTTP_1_1).m430a(UiMessage.CommandToUi.Command_Type.SET_FULLSCREEN_VALUE).m438a("Unsatisfiable Request (only-if-cached)").m436a(C0503c.f171c).m431a(-1L).m440b(System.currentTimeMillis()).m429a();
        }
        if (c0595ao == null) {
            return c0599as.m424f().m441b(m44a(c0599as)).m429a();
        }
        try {
            C0599as c0599asMo147a = interfaceC0587ag.mo147a(c0595ao);
            if (c0599asMo147a == null && c0599asM60a != null) {
                C0503c.m116a(c0599asM60a.m423e());
            }
            if (c0599as != null) {
                if (c0599asMo147a.m420b() == 304) {
                    C0600at c0600atM424f = c0599as.m424f();
                    C0581aa c0581aaM422d = c0599as.m422d();
                    C0581aa c0581aaM422d2 = c0599asMo147a.m422d();
                    C0582ab c0582ab = new C0582ab();
                    int iM321a = c0581aaM422d.m321a();
                    for (int i = 0; i < iM321a; i++) {
                        String strM322a = c0581aaM422d.m322a(i);
                        String strM325b = c0581aaM422d.m325b(i);
                        if ((!"Warning".equalsIgnoreCase(strM322a) || !strM325b.startsWith("1")) && (!m45a(strM322a) || c0581aaM422d2.m323a(strM322a) == null)) {
                            AbstractC0484a.f81a.mo39a(c0582ab, strM322a, strM325b);
                        }
                    }
                    int iM321a2 = c0581aaM422d2.m321a();
                    for (int i2 = 0; i2 < iM321a2; i2++) {
                        String strM322a2 = c0581aaM422d2.m322a(i2);
                        if (!"Content-Length".equalsIgnoreCase(strM322a2) && m45a(strM322a2)) {
                            AbstractC0484a.f81a.mo39a(c0582ab, strM322a2, c0581aaM422d2.m325b(i2));
                        }
                    }
                    C0599as c0599asM429a = c0600atM424f.m432a(c0582ab.m327a()).m431a(c0599asMo147a.m426h()).m440b(c0599asMo147a.m427i()).m441b(m44a(c0599as)).m435a(m44a(c0599asMo147a)).m429a();
                    c0599asMo147a.m423e().close();
                    return c0599asM429a;
                }
                C0503c.m116a(c0599as.m423e());
            }
            C0599as c0599asM429a2 = c0599asMo147a.m424f().m441b(m44a(c0599as)).m435a(m44a(c0599asMo147a)).m429a();
            if (!C0509f.m143b(c0599asM429a2)) {
                return c0599asM429a2;
            }
            C0595ao c0595aoM418a = c0599asMo147a.m418a();
            InterfaceC0493i interfaceC0493i = this.f82a;
            if (interfaceC0493i != null) {
                if (!C0488d.m50a(c0599asM429a2, c0595aoM418a)) {
                    String strM400b = c0595aoM418a.m400b();
                    if (!strM400b.equals("POST") && !strM400b.equals(HttpClientStack.HttpPatch.METHOD_NAME) && !strM400b.equals(HttpRequest.METHOD_PUT) && !strM400b.equals(HttpRequest.METHOD_DELETE)) {
                        strM400b.equals("MOVE");
                    }
                } else {
                    interfaceC0487cM61b = interfaceC0493i.m61b();
                }
            }
            return (interfaceC0487cM61b == null || (interfaceC0653wM49a = interfaceC0487cM61b.m49a()) == null) ? c0599asM429a2 : c0599asM429a2.m424f().m436a(new C0512i(c0599asM429a2.m422d(), C0644n.m606a(new C0486b(this, c0599asM429a2.m423e().mo153c(), interfaceC0487cM61b, C0644n.m605a(interfaceC0653wM49a))))).m429a();
        } catch (Throwable th) {
            if (c0599asM60a != null) {
                C0503c.m116a(c0599asM60a.m423e());
            }
            throw th;
        }
    }

    /* JADX INFO: renamed from: a */
    private static C0599as m44a(C0599as c0599as) {
        return (c0599as == null || c0599as.m423e() == null) ? c0599as : c0599as.m424f().m436a((AbstractC0601au) null).m429a();
    }

    /* JADX INFO: renamed from: a */
    private static boolean m45a(String str) {
        return ("Connection".equalsIgnoreCase(str) || "Keep-Alive".equalsIgnoreCase(str) || "Proxy-Authenticate".equalsIgnoreCase(str) || HttpRequest.HEADER_PROXY_AUTHORIZATION.equalsIgnoreCase(str) || "TE".equalsIgnoreCase(str) || "Trailers".equalsIgnoreCase(str) || "Transfer-Encoding".equalsIgnoreCase(str) || "Upgrade".equalsIgnoreCase(str)) ? false : true;
    }
}
