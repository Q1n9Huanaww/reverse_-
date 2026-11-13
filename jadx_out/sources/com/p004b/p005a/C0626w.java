package com.p004b.p005a;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: renamed from: com.b.a.w */
/* JADX INFO: compiled from: Dns.java */
/* JADX INFO: loaded from: classes.dex */
final class C0626w implements InterfaceC0625v {
    C0626w() {
    }

    @Override // com.p004b.p005a.InterfaceC0625v
    /* JADX INFO: renamed from: a */
    public final List<InetAddress> mo501a(String str) throws UnknownHostException {
        if (str == null) {
            throw new UnknownHostException("hostname == null");
        }
        return Arrays.asList(InetAddress.getAllByName(str));
    }
}
