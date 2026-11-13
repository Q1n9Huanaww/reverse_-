package com.p004b.p005a.p006a.p013g;

import com.p004b.p005a.C0589ai;
import com.p004b.p005a.EnumC0592al;
import com.p004b.p005a.p006a.p014h.AbstractC0575b;
import com.p004b.p005a.p006a.p014h.AbstractC0578e;
import com.p004b.p005a.p006a.p014h.C0574a;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: renamed from: com.b.a.a.g.h */
/* JADX INFO: compiled from: Platform.java */
/* JADX INFO: loaded from: classes.dex */
public class C0573h {

    /* JADX INFO: renamed from: a */
    private static final C0573h f435a;

    /* JADX INFO: renamed from: b */
    private static final Logger f436b;

    /* JADX INFO: renamed from: a */
    public String mo286a(SSLSocket sSLSocket) {
        return null;
    }

    /* JADX INFO: renamed from: a */
    public void mo290a(SSLSocket sSLSocket, String str, List<EnumC0592al> list) {
    }

    /* JADX INFO: renamed from: b */
    public void mo298b(SSLSocket sSLSocket) {
    }

    /* JADX INFO: renamed from: b */
    public boolean mo291b(String str) {
        return true;
    }

    /* JADX INFO: renamed from: b */
    public static C0573h m307b() {
        return f435a;
    }

    /* JADX INFO: renamed from: a */
    public void mo289a(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        socket.connect(inetSocketAddress, i);
    }

    /* JADX INFO: renamed from: a */
    public void mo287a(int i, String str, Throwable th) {
        f436b.log(i == 5 ? Level.WARNING : Level.INFO, str, th);
    }

    /* JADX INFO: renamed from: a */
    public Object mo285a(String str) {
        if (f436b.isLoggable(Level.FINE)) {
            return new Throwable(str);
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    public void mo288a(String str, Object obj) {
        if (obj == null) {
            str = str + " To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);";
        }
        mo287a(5, str, (Throwable) obj);
    }

    /* JADX INFO: renamed from: a */
    public static List<String> m306a(List<EnumC0592al> list) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            EnumC0592al enumC0592al = list.get(i);
            if (enumC0592al != EnumC0592al.HTTP_1_0) {
                arrayList.add(enumC0592al.toString());
            }
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: a */
    public AbstractC0575b mo284a(X509TrustManager x509TrustManager) {
        return new C0574a(AbstractC0578e.m319a(x509TrustManager));
    }

    static {
        C0573h c0573hM283a = C0566a.m283a();
        if (c0573hM283a == null && (c0573hM283a = C0569d.m296a()) == null && (c0573hM283a = C0570e.m297a()) == null) {
            c0573hM283a = new C0573h();
        }
        f435a = c0573hM283a;
        f436b = Logger.getLogger(C0589ai.class.getName());
    }
}
