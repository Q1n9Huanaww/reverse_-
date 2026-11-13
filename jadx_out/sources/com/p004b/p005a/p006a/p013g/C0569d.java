package com.p004b.p005a.p006a.p013g;

import com.p004b.p005a.EnumC0592al;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;

/* JADX INFO: renamed from: com.b.a.a.g.d */
/* JADX INFO: compiled from: Jdk9Platform.java */
/* JADX INFO: loaded from: classes.dex */
final class C0569d extends C0573h {

    /* JADX INFO: renamed from: a */
    final Method f422a;

    /* JADX INFO: renamed from: b */
    final Method f423b;

    private C0569d(Method method, Method method2) {
        this.f422a = method;
        this.f423b = method2;
    }

    @Override // com.p004b.p005a.p006a.p013g.C0573h
    /* JADX INFO: renamed from: a */
    public final void mo290a(SSLSocket sSLSocket, String str, List<EnumC0592al> list) {
        try {
            SSLParameters sSLParameters = sSLSocket.getSSLParameters();
            List<String> listA = m306a(list);
            this.f422a.invoke(sSLParameters, listA.toArray(new String[listA.size()]));
            sSLSocket.setSSLParameters(sSLParameters);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            throw new AssertionError();
        }
    }

    @Override // com.p004b.p005a.p006a.p013g.C0573h
    /* JADX INFO: renamed from: a */
    public final String mo286a(SSLSocket sSLSocket) {
        try {
            String str = (String) this.f423b.invoke(sSLSocket, new Object[0]);
            if (str == null || str.equals("")) {
                return null;
            }
            return str;
        } catch (IllegalAccessException | InvocationTargetException unused) {
            throw new AssertionError();
        }
    }

    /* JADX INFO: renamed from: a */
    public static C0569d m296a() {
        try {
            return new C0569d(SSLParameters.class.getMethod("setApplicationProtocols", String[].class), SSLSocket.class.getMethod("getApplicationProtocol", new Class[0]));
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }
}
