package com.p004b.p015b;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: renamed from: com.b.b.p */
/* JADX INFO: compiled from: Okio.java */
/* JADX INFO: loaded from: classes.dex */
final class C0646p implements InterfaceC0654x {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C0655y f834a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ InputStream f835b;

    C0646p(C0655y c0655y, InputStream inputStream) {
        this.f834a = c0655y;
        this.f835b = inputStream;
    }

    @Override // com.p004b.p015b.InterfaceC0654x
    /* JADX INFO: renamed from: a */
    public final long mo47a(C0636f c0636f, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (j == 0) {
            return 0L;
        }
        try {
            this.f834a.mo601f_();
            C0650t c0650tM553e = c0636f.m553e(1);
            int i = this.f835b.read(c0650tM553e.f843a, c0650tM553e.f845c, (int) Math.min(j, 8192 - c0650tM553e.f845c));
            if (i == -1) {
                return -1L;
            }
            c0650tM553e.f845c += i;
            long j2 = i;
            c0636f.f814b += j2;
            return j2;
        } catch (AssertionError e) {
            if (C0644n.m608a(e)) {
                throw new IOException(e);
            }
            throw e;
        }
    }

    @Override // com.p004b.p015b.InterfaceC0654x, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.f835b.close();
    }

    @Override // com.p004b.p015b.InterfaceC0654x
    /* JADX INFO: renamed from: a */
    public final C0655y mo48a() {
        return this.f834a;
    }

    public final String toString() {
        return "source(" + this.f835b + ")";
    }
}
