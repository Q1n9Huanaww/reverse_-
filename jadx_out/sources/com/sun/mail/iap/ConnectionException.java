package com.sun.mail.iap;

/* JADX INFO: loaded from: classes.dex */
public class ConnectionException extends ProtocolException {
    private static final long serialVersionUID = 5749739604257464727L;

    /* JADX INFO: renamed from: p */
    private transient Protocol f3597p;

    public ConnectionException() {
    }

    public ConnectionException(String str) {
        super(str);
    }

    public ConnectionException(Protocol protocol, Response response) {
        super(response);
        this.f3597p = protocol;
    }

    public Protocol getProtocol() {
        return this.f3597p;
    }
}
