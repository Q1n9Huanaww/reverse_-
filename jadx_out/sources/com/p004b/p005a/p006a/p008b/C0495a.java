package com.p004b.p005a.p006a.p008b;

import com.p004b.p005a.C0589ai;
import com.p004b.p005a.C0595ao;
import com.p004b.p005a.C0599as;
import com.p004b.p005a.InterfaceC0586af;
import com.p004b.p005a.InterfaceC0587ag;
import com.p004b.p005a.p006a.p009c.C0511h;

/* JADX INFO: renamed from: com.b.a.a.b.a */
/* JADX INFO: compiled from: ConnectInterceptor.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0495a implements InterfaceC0586af {

    /* JADX INFO: renamed from: a */
    public final C0589ai f126a;

    public C0495a(C0589ai c0589ai) {
        this.f126a = c0589ai;
    }

    @Override // com.p004b.p005a.InterfaceC0586af
    /* JADX INFO: renamed from: a */
    public final C0599as mo46a(InterfaceC0587ag interfaceC0587ag) {
        C0511h c0511h = (C0511h) interfaceC0587ag;
        C0595ao c0595aoMo146a = c0511h.mo146a();
        C0501g c0501gM149b = c0511h.m149b();
        return c0511h.m148a(c0595aoMo146a, c0501gM149b, c0501gM149b.m95a(this.f126a, !c0595aoMo146a.m400b().equals("GET")), c0501gM149b.m99b());
    }
}
