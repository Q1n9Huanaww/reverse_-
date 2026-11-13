package com.p004b.p005a.p006a.p008b;

import com.p004b.p005a.C0619p;
import com.p004b.p005a.p006a.AbstractC0484a;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.UnknownServiceException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import javax.net.ssl.SSLSocket;

/* JADX INFO: renamed from: com.b.a.a.b.b */
/* JADX INFO: compiled from: ConnectionSpecSelector.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0496b {

    /* JADX INFO: renamed from: a */
    private final List<C0619p> f127a;

    /* JADX INFO: renamed from: b */
    private int f128b = 0;

    /* JADX INFO: renamed from: c */
    private boolean f129c;

    /* JADX INFO: renamed from: d */
    private boolean f130d;

    public C0496b(List<C0619p> list) {
        this.f127a = list;
    }

    /* JADX INFO: renamed from: a */
    public final C0619p m64a(SSLSocket sSLSocket) throws UnknownServiceException {
        C0619p c0619p;
        int i = this.f128b;
        int size = this.f127a.size();
        while (true) {
            if (i >= size) {
                c0619p = null;
                break;
            }
            c0619p = this.f127a.get(i);
            if (c0619p.m478a(sSLSocket)) {
                this.f128b = i + 1;
                break;
            }
            i++;
        }
        if (c0619p == null) {
            throw new UnknownServiceException("Unable to find acceptable protocols. isFallback=" + this.f130d + ", modes=" + this.f127a + ", supported protocols=" + Arrays.toString(sSLSocket.getEnabledProtocols()));
        }
        this.f129c = m63b(sSLSocket);
        AbstractC0484a.f81a.mo40a(c0619p, sSLSocket, this.f130d);
        return c0619p;
    }

    /* JADX INFO: renamed from: a */
    public final boolean m65a(IOException iOException) {
        this.f130d = true;
        if (!this.f129c || (iOException instanceof ProtocolException) || (iOException instanceof InterruptedIOException)) {
            return false;
        }
        boolean z = iOException instanceof SSLHandshakeException;
        if ((z && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) {
            return false;
        }
        return z || (iOException instanceof SSLProtocolException);
    }

    /* JADX INFO: renamed from: b */
    private boolean m63b(SSLSocket sSLSocket) {
        for (int i = this.f128b; i < this.f127a.size(); i++) {
            if (this.f127a.get(i).m478a(sSLSocket)) {
                return true;
            }
        }
        return false;
    }
}
