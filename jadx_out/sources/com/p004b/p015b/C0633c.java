package com.p004b.p015b;

import java.io.IOException;

/* JADX INFO: renamed from: com.b.b.c */
/* JADX INFO: compiled from: AsyncTimeout.java */
/* JADX INFO: loaded from: classes.dex */
final class C0633c implements InterfaceC0654x {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ InterfaceC0654x f808a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ C0630a f809b;

    C0633c(C0630a c0630a, InterfaceC0654x interfaceC0654x) {
        this.f809b = c0630a;
        this.f808a = interfaceC0654x;
    }

    @Override // com.p004b.p015b.InterfaceC0654x
    /* JADX INFO: renamed from: a */
    public final long mo47a(C0636f c0636f, long j) throws IOException {
        this.f809b.m516c();
        try {
            try {
                long jMo47a = this.f808a.mo47a(c0636f, j);
                this.f809b.m513a(true);
                return jMo47a;
            } catch (IOException e) {
                throw this.f809b.m515b(e);
            }
        } catch (Throwable th) {
            this.f809b.m513a(false);
            throw th;
        }
    }

    @Override // com.p004b.p015b.InterfaceC0654x, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        try {
            try {
                this.f808a.close();
                this.f809b.m513a(true);
            } catch (IOException e) {
                throw this.f809b.m515b(e);
            }
        } catch (Throwable th) {
            this.f809b.m513a(false);
            throw th;
        }
    }

    @Override // com.p004b.p015b.InterfaceC0654x
    /* JADX INFO: renamed from: a */
    public final C0655y mo48a() {
        return this.f809b;
    }

    public final String toString() {
        return "AsyncTimeout.source(" + this.f808a + ")";
    }
}
