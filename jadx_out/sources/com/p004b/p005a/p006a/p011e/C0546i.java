package com.p004b.p005a.p006a.p011e;

import com.p004b.p005a.p006a.p009c.InterfaceC0506c;
import com.p004b.p015b.AbstractC0640j;
import com.p004b.p015b.InterfaceC0654x;

/* JADX INFO: renamed from: com.b.a.a.e.i */
/* JADX INFO: compiled from: Http2Codec.java */
/* JADX INFO: loaded from: classes.dex */
final class C0546i extends AbstractC0640j {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C0545h f335a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0546i(C0545h c0545h, InterfaceC0654x interfaceC0654x) {
        super(interfaceC0654x);
        this.f335a = c0545h;
    }

    @Override // com.p004b.p015b.AbstractC0640j, com.p004b.p015b.InterfaceC0654x, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f335a.f331a.m98a(false, (InterfaceC0506c) this.f335a);
        super.close();
    }
}
