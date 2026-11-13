package com.p004b.p005a.p006a.p010d;

import com.p004b.p005a.p006a.C0503c;
import com.p004b.p015b.C0636f;
import com.p004b.p015b.InterfaceC0654x;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: com.b.a.a.d.f */
/* JADX INFO: compiled from: Http1Codec.java */
/* JADX INFO: loaded from: classes.dex */
final class C0523f extends AbstractC0519b {

    /* JADX INFO: renamed from: d */
    final /* synthetic */ C0518a f229d;

    /* JADX INFO: renamed from: e */
    private long f230e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0523f(C0518a c0518a, long j) {
        super(c0518a, (byte) 0);
        this.f229d = c0518a;
        this.f230e = j;
        if (this.f230e == 0) {
            m165a(true);
        }
    }

    @Override // com.p004b.p015b.InterfaceC0654x
    /* JADX INFO: renamed from: a */
    public final long mo47a(C0636f c0636f, long j) throws ProtocolException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.f216b) {
            throw new IllegalStateException("closed");
        }
        if (this.f230e == 0) {
            return -1L;
        }
        long jA = this.f229d.f212c.mo47a(c0636f, Math.min(this.f230e, j));
        if (jA == -1) {
            m165a(false);
            throw new ProtocolException("unexpected end of stream");
        }
        this.f230e -= jA;
        if (this.f230e == 0) {
            m165a(true);
        }
        return jA;
    }

    @Override // com.p004b.p015b.InterfaceC0654x, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f216b) {
            return;
        }
        if (this.f230e != 0 && !C0503c.m119a((InterfaceC0654x) this, TimeUnit.MILLISECONDS)) {
            m165a(false);
        }
        this.f216b = true;
    }
}
