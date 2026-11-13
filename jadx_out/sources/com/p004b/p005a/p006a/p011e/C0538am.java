package com.p004b.p005a.p006a.p011e;

import java.io.IOException;

/* JADX INFO: renamed from: com.b.a.a.e.am */
/* JADX INFO: compiled from: StreamResetException.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0538am extends IOException {

    /* JADX INFO: renamed from: a */
    public final EnumC0539b f279a;

    public C0538am(EnumC0539b enumC0539b) {
        super("stream was reset: " + enumC0539b);
        this.f279a = enumC0539b;
    }
}
