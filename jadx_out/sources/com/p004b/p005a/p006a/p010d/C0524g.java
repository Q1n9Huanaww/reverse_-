package com.p004b.p005a.p006a.p010d;

import com.p004b.p015b.C0636f;

/* JADX INFO: renamed from: com.b.a.a.d.g */
/* JADX INFO: compiled from: Http1Codec.java */
/* JADX INFO: loaded from: classes.dex */
final class C0524g extends AbstractC0519b {

    /* JADX INFO: renamed from: d */
    final /* synthetic */ C0518a f231d;

    /* JADX INFO: renamed from: e */
    private boolean f232e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0524g(C0518a c0518a) {
        super(c0518a, (byte) 0);
        this.f231d = c0518a;
    }

    @Override // com.p004b.p015b.InterfaceC0654x
    /* JADX INFO: renamed from: a */
    public final long mo47a(C0636f c0636f, long j) {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.f216b) {
            throw new IllegalStateException("closed");
        }
        if (this.f232e) {
            return -1L;
        }
        long jA = this.f231d.f212c.mo47a(c0636f, j);
        if (jA != -1) {
            return jA;
        }
        this.f232e = true;
        m165a(true);
        return -1L;
    }

    @Override // com.p004b.p015b.InterfaceC0654x, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f216b) {
            return;
        }
        if (!this.f232e) {
            m165a(false);
        }
        this.f216b = true;
    }
}
