package com.p004b.p015b;

/* JADX INFO: renamed from: com.b.b.r */
/* JADX INFO: compiled from: RealBufferedSink.java */
/* JADX INFO: loaded from: classes.dex */
final class C0648r implements InterfaceC0637g {

    /* JADX INFO: renamed from: a */
    public final C0636f f837a = new C0636f();

    /* JADX INFO: renamed from: b */
    public final InterfaceC0653w f838b;

    /* JADX INFO: renamed from: c */
    boolean f839c;

    C0648r(InterfaceC0653w interfaceC0653w) {
        if (interfaceC0653w == null) {
            throw new NullPointerException("sink == null");
        }
        this.f838b = interfaceC0653w;
    }

    @Override // com.p004b.p015b.InterfaceC0637g, com.p004b.p015b.InterfaceC0638h
    /* JADX INFO: renamed from: c */
    public final C0636f mo546c() {
        return this.f837a;
    }

    @Override // com.p004b.p015b.InterfaceC0653w
    /* JADX INFO: renamed from: a_ */
    public final void mo167a_(C0636f c0636f, long j) {
        if (this.f839c) {
            throw new IllegalStateException("closed");
        }
        this.f837a.mo167a_(c0636f, j);
        mo573p();
    }

    @Override // com.p004b.p015b.InterfaceC0637g
    /* JADX INFO: renamed from: b */
    public final InterfaceC0637g mo542b(String str) {
        if (this.f839c) {
            throw new IllegalStateException("closed");
        }
        this.f837a.mo542b(str);
        return mo573p();
    }

    @Override // com.p004b.p015b.InterfaceC0637g
    /* JADX INFO: renamed from: b */
    public final InterfaceC0637g mo543b(byte[] bArr) {
        if (this.f839c) {
            throw new IllegalStateException("closed");
        }
        this.f837a.mo543b(bArr);
        return mo573p();
    }

    @Override // com.p004b.p015b.InterfaceC0637g
    /* JADX INFO: renamed from: b */
    public final InterfaceC0637g mo544b(byte[] bArr, int i, int i2) {
        if (this.f839c) {
            throw new IllegalStateException("closed");
        }
        this.f837a.mo544b(bArr, i, i2);
        return mo573p();
    }

    @Override // com.p004b.p015b.InterfaceC0637g
    /* JADX INFO: renamed from: h */
    public final InterfaceC0637g mo562h(int i) {
        if (this.f839c) {
            throw new IllegalStateException("closed");
        }
        this.f837a.mo562h(i);
        return mo573p();
    }

    @Override // com.p004b.p015b.InterfaceC0637g
    /* JADX INFO: renamed from: g */
    public final InterfaceC0637g mo560g(int i) {
        if (this.f839c) {
            throw new IllegalStateException("closed");
        }
        this.f837a.mo560g(i);
        return mo573p();
    }

    @Override // com.p004b.p015b.InterfaceC0637g
    /* JADX INFO: renamed from: f */
    public final InterfaceC0637g mo555f(int i) {
        if (this.f839c) {
            throw new IllegalStateException("closed");
        }
        this.f837a.mo555f(i);
        return mo573p();
    }

    @Override // com.p004b.p015b.InterfaceC0637g
    /* JADX INFO: renamed from: j */
    public final InterfaceC0637g mo567j(long j) {
        if (this.f839c) {
            throw new IllegalStateException("closed");
        }
        this.f837a.mo567j(j);
        return mo573p();
    }

    @Override // com.p004b.p015b.InterfaceC0637g
    /* JADX INFO: renamed from: i */
    public final InterfaceC0637g mo565i(long j) {
        if (this.f839c) {
            throw new IllegalStateException("closed");
        }
        this.f837a.mo565i(j);
        return mo573p();
    }

    @Override // com.p004b.p015b.InterfaceC0637g
    /* JADX INFO: renamed from: p */
    public final InterfaceC0637g mo573p() {
        if (this.f839c) {
            throw new IllegalStateException("closed");
        }
        C0636f c0636f = this.f837a;
        long j = c0636f.f814b;
        if (j == 0) {
            j = 0;
        } else {
            C0650t c0650t = c0636f.f813a.f849g;
            if (c0650t.f845c < 8192 && c0650t.f847e) {
                j -= (long) (c0650t.f845c - c0650t.f844b);
            }
        }
        if (j > 0) {
            this.f838b.mo167a_(this.f837a, j);
        }
        return this;
    }

    @Override // com.p004b.p015b.InterfaceC0637g, com.p004b.p015b.InterfaceC0653w, java.io.Flushable
    public final void flush() {
        if (this.f839c) {
            throw new IllegalStateException("closed");
        }
        if (this.f837a.f814b > 0) {
            this.f838b.mo167a_(this.f837a, this.f837a.f814b);
        }
        this.f838b.flush();
    }

    @Override // com.p004b.p015b.InterfaceC0653w, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws Throwable {
        if (this.f839c) {
            return;
        }
        Throwable th = null;
        try {
            if (this.f837a.f814b > 0) {
                this.f838b.mo167a_(this.f837a, this.f837a.f814b);
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            this.f838b.close();
        } catch (Throwable th3) {
            if (th == null) {
                th = th3;
            }
        }
        this.f839c = true;
        if (th != null) {
            C0631aa.m520a(th);
        }
    }

    @Override // com.p004b.p015b.InterfaceC0653w
    /* JADX INFO: renamed from: a */
    public final C0655y mo166a() {
        return this.f838b.mo166a();
    }

    public final String toString() {
        return "buffer(" + this.f838b + ")";
    }
}
