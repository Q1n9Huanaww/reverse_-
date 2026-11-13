package com.p004b.p005a.p006a.p011e;

import com.p004b.p015b.C0636f;
import com.p004b.p015b.C0655y;
import com.p004b.p015b.InterfaceC0638h;
import com.p004b.p015b.InterfaceC0654x;
import java.io.EOFException;
import java.io.IOException;

/* JADX INFO: renamed from: com.b.a.a.e.ad */
/* JADX INFO: compiled from: Http2Stream.java */
/* JADX INFO: loaded from: classes.dex */
final class C0529ad implements InterfaceC0654x {

    /* JADX INFO: renamed from: c */
    static final /* synthetic */ boolean f251c = true;

    /* JADX INFO: renamed from: a */
    boolean f252a;

    /* JADX INFO: renamed from: b */
    boolean f253b;

    /* JADX INFO: renamed from: d */
    final /* synthetic */ C0527ab f254d;

    /* JADX INFO: renamed from: e */
    private final C0636f f255e = new C0636f();

    /* JADX INFO: renamed from: f */
    private final C0636f f256f = new C0636f();

    /* JADX INFO: renamed from: g */
    private final long f257g;

    C0529ad(C0527ab c0527ab, long j) {
        this.f254d = c0527ab;
        this.f257g = j;
    }

    @Override // com.p004b.p015b.InterfaceC0654x
    /* JADX INFO: renamed from: a */
    public final long mo47a(C0636f c0636f, long j) {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        synchronized (this.f254d) {
            m193b();
            if (this.f252a) {
                throw new IOException("stream closed");
            }
            if (this.f254d.f241h != null) {
                throw new C0538am(this.f254d.f241h);
            }
            if (this.f256f.m540b() == 0) {
                return -1L;
            }
            long jMo47a = this.f256f.mo47a(c0636f, Math.min(j, this.f256f.m540b()));
            this.f254d.f234a += jMo47a;
            if (this.f254d.f234a >= this.f254d.f237d.f348l.m230d() / 2) {
                this.f254d.f237d.m260a(this.f254d.f236c, this.f254d.f234a);
                this.f254d.f234a = 0L;
            }
            synchronized (this.f254d.f237d) {
                this.f254d.f237d.f346j += jMo47a;
                if (this.f254d.f237d.f346j >= this.f254d.f237d.f348l.m230d() / 2) {
                    this.f254d.f237d.m260a(0, this.f254d.f237d.f346j);
                    this.f254d.f237d.f346j = 0L;
                }
            }
            return jMo47a;
        }
    }

    /* JADX INFO: renamed from: b */
    private void m193b() {
        this.f254d.f239f.m516c();
        while (this.f256f.m540b() == 0 && !this.f253b && !this.f252a && this.f254d.f241h == null) {
            try {
                this.f254d.m191i();
            } catch (Throwable th) {
                this.f254d.f239f.m197b();
                throw th;
            }
        }
        this.f254d.f239f.m197b();
    }

    /* JADX INFO: renamed from: a */
    final void m194a(InterfaceC0638h interfaceC0638h, long j) throws EOFException {
        boolean z;
        boolean z2;
        if (!f251c && Thread.holdsLock(this.f254d)) {
            throw new AssertionError();
        }
        while (j > 0) {
            synchronized (this.f254d) {
                z = this.f253b;
                z2 = j + this.f256f.m540b() > this.f257g;
            }
            if (z2) {
                interfaceC0638h.mo557f(j);
                this.f254d.m182b(EnumC0539b.FLOW_CONTROL_ERROR);
                return;
            }
            if (z) {
                interfaceC0638h.mo557f(j);
                return;
            }
            long jA = interfaceC0638h.mo47a(this.f255e, j);
            if (jA == -1) {
                throw new EOFException();
            }
            long j2 = j - jA;
            synchronized (this.f254d) {
                boolean z3 = this.f256f.m540b() == 0;
                this.f256f.m529a(this.f255e);
                if (z3) {
                    this.f254d.notifyAll();
                }
            }
            j = j2;
        }
    }

    @Override // com.p004b.p015b.InterfaceC0654x
    /* JADX INFO: renamed from: a */
    public final C0655y mo48a() {
        return this.f254d.f239f;
    }

    @Override // com.p004b.p015b.InterfaceC0654x, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        synchronized (this.f254d) {
            this.f252a = true;
            this.f256f.m572o();
            this.f254d.notifyAll();
        }
        this.f254d.m189g();
    }
}
