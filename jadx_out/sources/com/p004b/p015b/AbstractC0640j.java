package com.p004b.p015b;

/* JADX INFO: renamed from: com.b.b.j */
/* JADX INFO: compiled from: ForwardingSource.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0640j implements InterfaceC0654x {

    /* JADX INFO: renamed from: a */
    private final InterfaceC0654x f820a;

    public AbstractC0640j(InterfaceC0654x interfaceC0654x) {
        if (interfaceC0654x == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f820a = interfaceC0654x;
    }

    @Override // com.p004b.p015b.InterfaceC0654x
    /* JADX INFO: renamed from: a */
    public final long mo47a(C0636f c0636f, long j) {
        return this.f820a.mo47a(c0636f, j);
    }

    @Override // com.p004b.p015b.InterfaceC0654x
    /* JADX INFO: renamed from: a */
    public final C0655y mo48a() {
        return this.f820a.mo48a();
    }

    @Override // com.p004b.p015b.InterfaceC0654x, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f820a.close();
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.f820a.toString() + ")";
    }
}
