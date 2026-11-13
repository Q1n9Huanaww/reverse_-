package com.p004b.p005a.p006a.p010d;

import com.p004b.p015b.C0636f;
import com.p004b.p015b.C0641k;
import com.p004b.p015b.C0655y;
import com.p004b.p015b.InterfaceC0653w;
import org.apache.commons.p092io.IOUtils;

/* JADX INFO: renamed from: com.b.a.a.d.c */
/* JADX INFO: compiled from: Http1Codec.java */
/* JADX INFO: loaded from: classes.dex */
final class C0520c implements InterfaceC0653w {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C0518a f218a;

    /* JADX INFO: renamed from: b */
    private final C0641k f219b;

    /* JADX INFO: renamed from: c */
    private boolean f220c;

    C0520c(C0518a c0518a) {
        this.f218a = c0518a;
        this.f219b = new C0641k(this.f218a.f213d.mo166a());
    }

    @Override // com.p004b.p015b.InterfaceC0653w
    /* JADX INFO: renamed from: a */
    public final C0655y mo166a() {
        return this.f219b;
    }

    @Override // com.p004b.p015b.InterfaceC0653w
    /* JADX INFO: renamed from: a_ */
    public final void mo167a_(C0636f c0636f, long j) {
        if (this.f220c) {
            throw new IllegalStateException("closed");
        }
        if (j == 0) {
            return;
        }
        this.f218a.f213d.mo565i(j);
        this.f218a.f213d.mo542b(IOUtils.LINE_SEPARATOR_WINDOWS);
        this.f218a.f213d.mo167a_(c0636f, j);
        this.f218a.f213d.mo542b(IOUtils.LINE_SEPARATOR_WINDOWS);
    }

    @Override // com.p004b.p015b.InterfaceC0653w, java.io.Flushable
    public final synchronized void flush() {
        if (this.f220c) {
            return;
        }
        this.f218a.f213d.flush();
    }

    @Override // com.p004b.p015b.InterfaceC0653w, java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        if (this.f220c) {
            return;
        }
        this.f220c = true;
        this.f218a.f213d.mo542b("0\r\n\r\n");
        C0518a.m161a(this.f219b);
        this.f218a.f214e = 3;
    }
}
