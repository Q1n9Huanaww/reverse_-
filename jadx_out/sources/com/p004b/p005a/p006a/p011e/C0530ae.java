package com.p004b.p005a.p006a.p011e;

import com.p004b.p015b.C0630a;
import java.io.IOException;
import java.net.SocketTimeoutException;

/* JADX INFO: renamed from: com.b.a.a.e.ae */
/* JADX INFO: compiled from: Http2Stream.java */
/* JADX INFO: loaded from: classes.dex */
final class C0530ae extends C0630a {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C0527ab f258a;

    C0530ae(C0527ab c0527ab) {
        this.f258a = c0527ab;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.p004b.p015b.C0630a
    /* JADX INFO: renamed from: a */
    public final void mo196a() {
        this.f258a.m182b(EnumC0539b.CANCEL);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.p004b.p015b.C0630a
    /* JADX INFO: renamed from: a */
    public final IOException mo195a(IOException iOException) {
        SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
        if (iOException != null) {
            socketTimeoutException.initCause(iOException);
        }
        return socketTimeoutException;
    }

    /* JADX INFO: renamed from: b */
    public final void m197b() {
        if (m514a_()) {
            throw mo195a((IOException) null);
        }
    }
}
