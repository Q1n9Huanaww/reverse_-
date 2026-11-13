package com.p004b.p015b;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

/* JADX INFO: renamed from: com.b.b.n */
/* JADX INFO: compiled from: Okio.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0644n {

    /* JADX INFO: renamed from: a */
    static final Logger f831a = Logger.getLogger(C0644n.class.getName());

    private C0644n() {
    }

    /* JADX INFO: renamed from: a */
    public static InterfaceC0638h m606a(InterfaceC0654x interfaceC0654x) {
        return new C0649s(interfaceC0654x);
    }

    /* JADX INFO: renamed from: a */
    public static InterfaceC0637g m605a(InterfaceC0653w interfaceC0653w) {
        return new C0648r(interfaceC0653w);
    }

    /* JADX INFO: renamed from: a */
    public static InterfaceC0653w m607a(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        }
        C0630a c0630aM610c = m610c(socket);
        OutputStream outputStream = socket.getOutputStream();
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        return new C0632b(c0630aM610c, new C0645o(c0630aM610c, outputStream));
    }

    /* JADX INFO: renamed from: b */
    public static InterfaceC0654x m609b(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        }
        C0630a c0630aM610c = m610c(socket);
        InputStream inputStream = socket.getInputStream();
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        return new C0633c(c0630aM610c, new C0646p(c0630aM610c, inputStream));
    }

    /* JADX INFO: renamed from: c */
    private static C0630a m610c(Socket socket) {
        return new C0647q(socket);
    }

    /* JADX INFO: renamed from: a */
    static boolean m608a(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }
}
