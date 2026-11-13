package com.p004b.p005a.p006a.p013g;

import android.util.Log;
import com.p004b.p005a.EnumC0592al;
import com.p004b.p005a.p006a.C0503c;
import com.p004b.p005a.p006a.p014h.AbstractC0575b;
import com.p004b.p015b.C0636f;
import com.p024ds.daisi.pay.PayConstants;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: renamed from: com.b.a.a.g.a */
/* JADX INFO: compiled from: AndroidPlatform.java */
/* JADX INFO: loaded from: classes.dex */
final class C0566a extends C0573h {

    /* JADX INFO: renamed from: a */
    private final Class<?> f411a;

    /* JADX INFO: renamed from: b */
    private final C0572g<Socket> f412b;

    /* JADX INFO: renamed from: c */
    private final C0572g<Socket> f413c;

    /* JADX INFO: renamed from: d */
    private final C0572g<Socket> f414d;

    /* JADX INFO: renamed from: e */
    private final C0572g<Socket> f415e;

    /* JADX INFO: renamed from: f */
    private final C0568c f416f = C0568c.m293a();

    private C0566a(Class<?> cls, C0572g<Socket> c0572g, C0572g<Socket> c0572g2, C0572g<Socket> c0572g3, C0572g<Socket> c0572g4) {
        this.f411a = cls;
        this.f412b = c0572g;
        this.f413c = c0572g2;
        this.f414d = c0572g3;
        this.f415e = c0572g4;
    }

    @Override // com.p004b.p005a.p006a.p013g.C0573h
    /* JADX INFO: renamed from: a */
    public final void mo289a(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        try {
            socket.connect(inetSocketAddress, i);
        } catch (AssertionError e) {
            if (!C0503c.m120a(e)) {
                throw e;
            }
            throw new IOException(e);
        } catch (SecurityException e2) {
            IOException iOException = new IOException("Exception in connect");
            iOException.initCause(e2);
            throw iOException;
        }
    }

    @Override // com.p004b.p005a.p006a.p013g.C0573h
    /* JADX INFO: renamed from: a */
    public final void mo290a(SSLSocket sSLSocket, String str, List<EnumC0592al> list) {
        if (str != null) {
            this.f412b.m303a(sSLSocket, true);
            this.f413c.m303a(sSLSocket, str);
        }
        if (this.f415e == null || !this.f415e.m304a(sSLSocket)) {
            return;
        }
        Object[] objArr = new Object[1];
        C0636f c0636f = new C0636f();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            EnumC0592al enumC0592al = list.get(i);
            if (enumC0592al != EnumC0592al.HTTP_1_0) {
                c0636f.mo562h(enumC0592al.toString().length());
                c0636f.mo542b(enumC0592al.toString());
            }
        }
        objArr[0] = c0636f.m571n();
        this.f415e.m305b(sSLSocket, objArr);
    }

    @Override // com.p004b.p005a.p006a.p013g.C0573h
    /* JADX INFO: renamed from: a */
    public final String mo286a(SSLSocket sSLSocket) {
        byte[] bArr;
        if (this.f414d == null || !this.f414d.m304a(sSLSocket) || (bArr = (byte[]) this.f414d.m305b(sSLSocket, new Object[0])) == null) {
            return null;
        }
        return new String(bArr, C0503c.f173e);
    }

    @Override // com.p004b.p005a.p006a.p013g.C0573h
    /* JADX INFO: renamed from: a */
    public final void mo287a(int i, String str, Throwable th) {
        int iMin;
        int i2 = i != 5 ? 3 : 5;
        if (th != null) {
            str = str + '\n' + Log.getStackTraceString(th);
        }
        int i3 = 0;
        int length = str.length();
        while (i3 < length) {
            int iIndexOf = str.indexOf(10, i3);
            if (iIndexOf == -1) {
                iIndexOf = length;
            }
            while (true) {
                iMin = Math.min(iIndexOf, i3 + PayConstants.PAY_SYSTEM_ERROR);
                Log.println(i2, "OkHttp", str.substring(i3, iMin));
                if (iMin >= iIndexOf) {
                    break;
                } else {
                    i3 = iMin;
                }
            }
            i3 = iMin + 1;
        }
    }

    @Override // com.p004b.p005a.p006a.p013g.C0573h
    /* JADX INFO: renamed from: a */
    public final Object mo285a(String str) {
        return this.f416f.m294a(str);
    }

    @Override // com.p004b.p005a.p006a.p013g.C0573h
    /* JADX INFO: renamed from: a */
    public final void mo288a(String str, Object obj) {
        if (this.f416f.m295a(obj)) {
            return;
        }
        mo287a(5, str, (Throwable) null);
    }

    @Override // com.p004b.p005a.p006a.p013g.C0573h
    /* JADX INFO: renamed from: b */
    public final boolean mo291b(String str) {
        try {
            Class<?> cls = Class.forName("android.security.NetworkSecurityPolicy");
            return ((Boolean) cls.getMethod("isCleartextTrafficPermitted", String.class).invoke(cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), str)).booleanValue();
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            return super.mo291b(str);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused2) {
            throw new AssertionError();
        }
    }

    @Override // com.p004b.p005a.p006a.p013g.C0573h
    /* JADX INFO: renamed from: a */
    public final AbstractC0575b mo284a(X509TrustManager x509TrustManager) {
        try {
            Class<?> cls = Class.forName("android.net.http.X509TrustManagerExtensions");
            return new C0567b(cls.getConstructor(X509TrustManager.class).newInstance(x509TrustManager), cls.getMethod("checkServerTrusted", X509Certificate[].class, String.class, String.class));
        } catch (Exception unused) {
            return super.mo284a(x509TrustManager);
        }
    }

    /* JADX INFO: renamed from: a */
    public static C0573h m283a() {
        Class<?> cls;
        C0572g c0572g;
        C0572g c0572g2;
        try {
            try {
                cls = Class.forName("com.android.org.conscrypt.SSLParametersImpl");
            } catch (ClassNotFoundException unused) {
                cls = Class.forName("org.apache.harmony.xnet.provider.jsse.SSLParametersImpl");
            }
            Class<?> cls2 = cls;
            C0572g c0572g3 = new C0572g(null, "setUseSessionTickets", Boolean.TYPE);
            C0572g c0572g4 = new C0572g(null, "setHostname", String.class);
            try {
                Class.forName("android.net.Network");
                c0572g = new C0572g(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
                try {
                    c0572g2 = new C0572g(null, "setAlpnProtocols", byte[].class);
                } catch (ClassNotFoundException unused2) {
                    c0572g2 = null;
                }
            } catch (ClassNotFoundException unused3) {
                c0572g = null;
            }
            return new C0566a(cls2, c0572g3, c0572g4, c0572g, c0572g2);
        } catch (ClassNotFoundException unused4) {
            return null;
        }
    }
}
