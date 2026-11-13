package com.p004b.p005a;

import java.io.IOException;

/* JADX INFO: renamed from: com.b.a.al */
/* JADX INFO: compiled from: Protocol.java */
/* JADX INFO: loaded from: classes.dex */
public enum EnumC0592al {
    HTTP_1_0("http/1.0"),
    HTTP_1_1("http/1.1"),
    SPDY_3("spdy/3.1"),
    HTTP_2("h2");


    /* JADX INFO: renamed from: e */
    private final String f540e;

    EnumC0592al(String str) {
        this.f540e = str;
    }

    /* JADX INFO: renamed from: a */
    public static EnumC0592al m391a(String str) throws IOException {
        if (str.equals(HTTP_1_0.f540e)) {
            return HTTP_1_0;
        }
        if (str.equals(HTTP_1_1.f540e)) {
            return HTTP_1_1;
        }
        if (str.equals(HTTP_2.f540e)) {
            return HTTP_2;
        }
        if (str.equals(SPDY_3.f540e)) {
            return SPDY_3;
        }
        throw new IOException("Unexpected protocol: " + str);
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.f540e;
    }
}
