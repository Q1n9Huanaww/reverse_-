package com.p004b.p005a;

import com.p004b.p005a.p006a.C0503c;
import com.p004b.p015b.C0636f;
import com.p004b.p015b.InterfaceC0638h;
import java.io.Closeable;

/* JADX INFO: renamed from: com.b.a.au */
/* JADX INFO: compiled from: ResponseBody.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0601au implements Closeable {
    /* JADX INFO: renamed from: a */
    public abstract C0588ah mo151a();

    /* JADX INFO: renamed from: b */
    public abstract long mo152b();

    /* JADX INFO: renamed from: c */
    public abstract InterfaceC0638h mo153c();

    /* JADX INFO: renamed from: d */
    public final String m444d() {
        InterfaceC0638h interfaceC0638hMo153c = mo153c();
        try {
            C0588ah c0588ahMo151a = mo151a();
            return interfaceC0638hMo153c.mo537a(C0503c.m111a(interfaceC0638hMo153c, c0588ahMo151a != null ? c0588ahMo151a.m368a(C0503c.f173e) : C0503c.f173e));
        } finally {
            C0503c.m116a(interfaceC0638hMo153c);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        C0503c.m116a(mo153c());
    }

    /* JADX INFO: renamed from: a */
    public static AbstractC0601au m443a(byte[] bArr) {
        C0636f c0636fMo543b = new C0636f().mo543b(bArr);
        long length = bArr.length;
        if (c0636fMo543b == null) {
            throw new NullPointerException("source == null");
        }
        return new C0602av(length, c0636fMo543b);
    }
}
