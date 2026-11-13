package com.p004b.p005a.p006a.p009c;

import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import com.github.kevinsawicki.http.HttpRequest;
import com.p004b.p005a.AbstractC0597aq;
import com.p004b.p005a.C0581aa;
import com.p004b.p005a.C0588ah;
import com.p004b.p005a.C0595ao;
import com.p004b.p005a.C0596ap;
import com.p004b.p005a.C0599as;
import com.p004b.p005a.C0600at;
import com.p004b.p005a.C0621r;
import com.p004b.p005a.InterfaceC0586af;
import com.p004b.p005a.InterfaceC0587ag;
import com.p004b.p005a.InterfaceC0622s;
import com.p004b.p005a.p006a.C0503c;
import com.p004b.p015b.C0642l;
import com.p004b.p015b.C0644n;
import java.util.List;

/* JADX INFO: renamed from: com.b.a.a.c.a */
/* JADX INFO: compiled from: BridgeInterceptor.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0504a implements InterfaceC0586af {

    /* JADX INFO: renamed from: a */
    private final InterfaceC0622s f185a;

    public C0504a(InterfaceC0622s interfaceC0622s) {
        this.f185a = interfaceC0622s;
    }

    @Override // com.p004b.p005a.InterfaceC0586af
    /* JADX INFO: renamed from: a */
    public final C0599as mo46a(InterfaceC0587ag interfaceC0587ag) {
        boolean z;
        C0595ao c0595aoMo146a = interfaceC0587ag.mo146a();
        C0596ap c0596apM403e = c0595aoMo146a.m403e();
        AbstractC0597aq abstractC0597aqM402d = c0595aoMo146a.m402d();
        if (abstractC0597aqM402d != null) {
            C0588ah c0588ahMo415a = abstractC0597aqM402d.mo415a();
            if (c0588ahMo415a != null) {
                c0596apM403e.m412a("Content-Type", c0588ahMo415a.toString());
            }
            long jMo417b = abstractC0597aqM402d.mo417b();
            if (jMo417b != -1) {
                c0596apM403e.m412a("Content-Length", Long.toString(jMo417b));
                c0596apM403e.m413b("Transfer-Encoding");
            } else {
                c0596apM403e.m412a("Transfer-Encoding", "chunked");
                c0596apM403e.m413b("Content-Length");
            }
        }
        if (c0595aoMo146a.m399a(HttpHeaders.HOST) == null) {
            c0596apM403e.m412a(HttpHeaders.HOST, C0503c.m108a(c0595aoMo146a.m398a(), false));
        }
        if (c0595aoMo146a.m399a("Connection") == null) {
            c0596apM403e.m412a("Connection", "Keep-Alive");
        }
        if (c0595aoMo146a.m399a(HttpRequest.HEADER_ACCEPT_ENCODING) == null && c0595aoMo146a.m399a(HttpHeaders.RANGE) == null) {
            c0596apM403e.m412a(HttpRequest.HEADER_ACCEPT_ENCODING, HttpRequest.ENCODING_GZIP);
            z = true;
        } else {
            z = false;
        }
        List<C0621r> listMo491b = this.f185a.mo491b();
        if (!listMo491b.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            int size = listMo491b.size();
            for (int i = 0; i < size; i++) {
                if (i > 0) {
                    sb.append("; ");
                }
                C0621r c0621r = listMo491b.get(i);
                sb.append(c0621r.m488a());
                sb.append('=');
                sb.append(c0621r.m489b());
            }
            c0596apM403e.m412a("Cookie", sb.toString());
        }
        if (c0595aoMo146a.m399a("User-Agent") == null) {
            c0596apM403e.m412a("User-Agent", "okhttp/3.6.0");
        }
        C0599as c0599asMo147a = interfaceC0587ag.mo147a(c0596apM403e.m406a());
        C0509f.m141a(this.f185a, c0595aoMo146a.m398a(), c0599asMo147a.m422d());
        C0600at c0600atM434a = c0599asMo147a.m424f().m434a(c0595aoMo146a);
        if (z && HttpRequest.ENCODING_GZIP.equalsIgnoreCase(c0599asMo147a.m419a("Content-Encoding")) && C0509f.m143b(c0599asMo147a)) {
            C0642l c0642l = new C0642l(c0599asMo147a.m423e().mo153c());
            C0581aa c0581aaM327a = c0599asMo147a.m422d().m324b().m328a("Content-Encoding").m328a("Content-Length").m327a();
            c0600atM434a.m432a(c0581aaM327a);
            c0600atM434a.m436a(new C0512i(c0581aaM327a, C0644n.m606a(c0642l)));
        }
        return c0600atM434a.m429a();
    }
}
