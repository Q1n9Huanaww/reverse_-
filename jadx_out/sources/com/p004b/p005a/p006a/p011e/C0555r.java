package com.p004b.p005a.p006a.p011e;

import com.p004b.p015b.InterfaceC0637g;
import com.p004b.p015b.InterfaceC0638h;
import java.net.Socket;

/* JADX INFO: renamed from: com.b.a.a.e.r */
/* JADX INFO: compiled from: Http2Connection.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0555r {

    /* JADX INFO: renamed from: a */
    Socket f384a;

    /* JADX INFO: renamed from: b */
    String f385b;

    /* JADX INFO: renamed from: c */
    InterfaceC0638h f386c;

    /* JADX INFO: renamed from: d */
    InterfaceC0637g f387d;

    /* JADX INFO: renamed from: e */
    AbstractC0556s f388e = AbstractC0556s.f391f;

    /* JADX INFO: renamed from: f */
    InterfaceC0535aj f389f = InterfaceC0535aj.f276a;

    /* JADX INFO: renamed from: g */
    boolean f390g = true;

    /* JADX INFO: renamed from: a */
    public final C0555r m275a(Socket socket, String str, InterfaceC0638h interfaceC0638h, InterfaceC0637g interfaceC0637g) {
        this.f384a = socket;
        this.f385b = str;
        this.f386c = interfaceC0638h;
        this.f387d = interfaceC0637g;
        return this;
    }

    /* JADX INFO: renamed from: a */
    public final C0555r m274a(AbstractC0556s abstractC0556s) {
        this.f388e = abstractC0556s;
        return this;
    }

    /* JADX INFO: renamed from: a */
    public final C0547j m273a() {
        return new C0547j(this);
    }
}
