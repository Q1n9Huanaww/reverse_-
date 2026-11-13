package com.p004b.p005a.p006a.p010d;

import com.p004b.p005a.C0583ac;
import com.p004b.p005a.p006a.C0503c;
import com.p004b.p005a.p006a.p009c.C0509f;
import com.p004b.p015b.C0636f;
import com.p004b.p015b.InterfaceC0654x;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: com.b.a.a.d.d */
/* JADX INFO: compiled from: Http1Codec.java */
/* JADX INFO: loaded from: classes.dex */
final class C0521d extends AbstractC0519b {

    /* JADX INFO: renamed from: d */
    final /* synthetic */ C0518a f221d;

    /* JADX INFO: renamed from: e */
    private final C0583ac f222e;

    /* JADX INFO: renamed from: f */
    private long f223f;

    /* JADX INFO: renamed from: g */
    private boolean f224g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0521d(C0518a c0518a, C0583ac c0583ac) {
        super(c0518a, (byte) 0);
        this.f221d = c0518a;
        this.f223f = -1L;
        this.f224g = true;
        this.f222e = c0583ac;
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
        if (!this.f224g) {
            return -1L;
        }
        if (this.f223f == 0 || this.f223f == -1) {
            if (this.f223f != -1) {
                this.f221d.f212c.mo570m();
            }
            try {
                this.f223f = this.f221d.f212c.mo566j();
                String strTrim = this.f221d.f212c.mo570m().trim();
                if (this.f223f >= 0 && (strTrim.isEmpty() || strTrim.startsWith(";"))) {
                    if (this.f223f == 0) {
                        this.f224g = false;
                        C0509f.m141a(this.f221d.f210a.m377f(), this.f222e, this.f221d.m164c());
                        m165a(true);
                    }
                    if (!this.f224g) {
                        return -1L;
                    }
                } else {
                    throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.f223f + strTrim + "\"");
                }
            } catch (NumberFormatException e) {
                throw new ProtocolException(e.getMessage());
            }
        }
        long jA = this.f221d.f212c.mo47a(c0636f, Math.min(j, this.f223f));
        if (jA == -1) {
            m165a(false);
            throw new ProtocolException("unexpected end of stream");
        }
        this.f223f -= jA;
        return jA;
    }

    @Override // com.p004b.p015b.InterfaceC0654x, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f216b) {
            return;
        }
        if (this.f224g && !C0503c.m119a((InterfaceC0654x) this, TimeUnit.MILLISECONDS)) {
            m165a(false);
        }
        this.f216b = true;
    }
}
