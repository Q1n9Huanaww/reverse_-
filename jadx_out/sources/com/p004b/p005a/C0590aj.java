package com.p004b.p005a;

import com.p004b.p005a.p006a.AbstractC0484a;
import com.p004b.p005a.p006a.C0503c;
import com.p004b.p005a.p006a.p008b.C0497c;
import com.p004b.p005a.p006a.p008b.C0498d;
import com.p004b.p005a.p006a.p008b.C0501g;
import java.net.Socket;
import javax.net.ssl.SSLSocket;

/* JADX INFO: renamed from: com.b.a.aj */
/* JADX INFO: compiled from: OkHttpClient.java */
/* JADX INFO: loaded from: classes.dex */
final class C0590aj extends AbstractC0484a {
    C0590aj() {
    }

    @Override // com.p004b.p005a.p006a.AbstractC0484a
    /* JADX INFO: renamed from: a */
    public final void mo39a(C0582ab c0582ab, String str, String str2) {
        c0582ab.m330b(str, str2);
    }

    @Override // com.p004b.p005a.p006a.AbstractC0484a
    /* JADX INFO: renamed from: a */
    public final boolean mo41a(C0617n c0617n, C0497c c0497c) {
        return c0617n.m474b(c0497c);
    }

    @Override // com.p004b.p005a.p006a.AbstractC0484a
    /* JADX INFO: renamed from: a */
    public final C0497c mo36a(C0617n c0617n, C0483a c0483a, C0501g c0501g) {
        return c0617n.m471a(c0483a, c0501g);
    }

    @Override // com.p004b.p005a.p006a.AbstractC0484a
    /* JADX INFO: renamed from: b */
    public final Socket mo42b(C0617n c0617n, C0483a c0483a, C0501g c0501g) {
        return c0617n.m473b(c0483a, c0501g);
    }

    @Override // com.p004b.p005a.p006a.AbstractC0484a
    /* JADX INFO: renamed from: b */
    public final void mo43b(C0617n c0617n, C0497c c0497c) {
        c0617n.m472a(c0497c);
    }

    @Override // com.p004b.p005a.p006a.AbstractC0484a
    /* JADX INFO: renamed from: a */
    public final C0498d mo37a(C0617n c0617n) {
        return c0617n.f749a;
    }

    @Override // com.p004b.p005a.p006a.AbstractC0484a
    /* JADX INFO: renamed from: a */
    public final int mo35a(C0600at c0600at) {
        return c0600at.f578c;
    }

    @Override // com.p004b.p005a.p006a.AbstractC0484a
    /* JADX INFO: renamed from: a */
    public final void mo38a(C0582ab c0582ab, String str) {
        int iIndexOf = str.indexOf(":", 1);
        if (iIndexOf != -1) {
            c0582ab.m330b(str.substring(0, iIndexOf), str.substring(iIndexOf + 1));
        } else if (str.startsWith(":")) {
            c0582ab.m330b("", str.substring(1));
        } else {
            c0582ab.m330b("", str);
        }
    }

    @Override // com.p004b.p005a.p006a.AbstractC0484a
    /* JADX INFO: renamed from: a */
    public final void mo40a(C0619p c0619p, SSLSocket sSLSocket, boolean z) {
        String[] enabledCipherSuites;
        String[] enabledProtocols;
        if (c0619p.f762f != null) {
            enabledCipherSuites = (String[]) C0503c.m122a(String.class, c0619p.f762f, sSLSocket.getEnabledCipherSuites());
        } else {
            enabledCipherSuites = sSLSocket.getEnabledCipherSuites();
        }
        if (c0619p.f763g != null) {
            enabledProtocols = (String[]) C0503c.m122a(String.class, c0619p.f763g, sSLSocket.getEnabledProtocols());
        } else {
            enabledProtocols = sSLSocket.getEnabledProtocols();
        }
        if (z && C0503c.m107a(sSLSocket.getSupportedCipherSuites(), "TLS_FALLBACK_SCSV") != -1) {
            enabledCipherSuites = C0503c.m123a(enabledCipherSuites, "TLS_FALLBACK_SCSV");
        }
        C0619p c0619pM482b = new C0620q(c0619p).m481a(enabledCipherSuites).m483b(enabledProtocols).m482b();
        if (c0619pM482b.f763g != null) {
            sSLSocket.setEnabledProtocols(c0619pM482b.f763g);
        }
        if (c0619pM482b.f762f != null) {
            sSLSocket.setEnabledCipherSuites(c0619pM482b.f762f);
        }
    }
}
