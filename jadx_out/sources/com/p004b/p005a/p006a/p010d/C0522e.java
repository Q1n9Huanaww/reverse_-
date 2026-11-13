package com.p004b.p005a.p006a.p010d;

import com.p004b.p005a.p006a.C0503c;
import com.p004b.p015b.C0636f;
import com.p004b.p015b.C0641k;
import com.p004b.p015b.C0655y;
import com.p004b.p015b.InterfaceC0653w;
import java.net.ProtocolException;

/* JADX INFO: renamed from: com.b.a.a.d.e */
/* JADX INFO: compiled from: Http1Codec.java */
/* JADX INFO: loaded from: classes.dex */
final class C0522e implements InterfaceC0653w {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C0518a f225a;

    /* JADX INFO: renamed from: b */
    private final C0641k f226b;

    /* JADX INFO: renamed from: c */
    private boolean f227c;

    /* JADX INFO: renamed from: d */
    private long f228d;

    C0522e(C0518a c0518a, long j) {
        this.f225a = c0518a;
        this.f226b = new C0641k(this.f225a.f213d.mo166a());
        this.f228d = j;
    }

    @Override // com.p004b.p015b.InterfaceC0653w
    /* JADX INFO: renamed from: a */
    public final C0655y mo166a() {
        return this.f226b;
    }

    @Override // com.p004b.p015b.InterfaceC0653w
    /* JADX INFO: renamed from: a_ */
    public final void mo167a_(C0636f c0636f, long j) throws ProtocolException {
        if (this.f227c) {
            throw new IllegalStateException("closed");
        }
        C0503c.m115a(c0636f.m540b(), j);
        if (j > this.f228d) {
            throw new ProtocolException("expected " + this.f228d + " bytes but received " + j);
        }
        this.f225a.f213d.mo167a_(c0636f, j);
        this.f228d -= j;
    }

    @Override // com.p004b.p015b.InterfaceC0653w, java.io.Flushable
    public final void flush() {
        if (this.f227c) {
            return;
        }
        this.f225a.f213d.flush();
    }

    @Override // com.p004b.p015b.InterfaceC0653w, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws ProtocolException {
        if (this.f227c) {
            return;
        }
        this.f227c = true;
        if (this.f228d > 0) {
            throw new ProtocolException("unexpected end of stream");
        }
        C0518a.m161a(this.f226b);
        this.f225a.f214e = 3;
    }
}
