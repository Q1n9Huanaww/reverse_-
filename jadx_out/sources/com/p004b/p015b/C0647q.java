package com.p004b.p015b;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;

/* JADX INFO: renamed from: com.b.b.q */
/* JADX INFO: compiled from: Okio.java */
/* JADX INFO: loaded from: classes.dex */
final class C0647q extends C0630a {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ Socket f836a;

    C0647q(Socket socket) {
        this.f836a = socket;
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.p004b.p015b.C0630a
    /* JADX INFO: renamed from: a */
    public final void mo196a() {
        try {
            this.f836a.close();
        } catch (AssertionError e) {
            if (C0644n.m608a(e)) {
                C0644n.f831a.log(Level.WARNING, "Failed to close timed out socket " + this.f836a, (Throwable) e);
                return;
            }
            throw e;
        } catch (Exception e2) {
            C0644n.f831a.log(Level.WARNING, "Failed to close timed out socket " + this.f836a, (Throwable) e2);
        }
    }
}
