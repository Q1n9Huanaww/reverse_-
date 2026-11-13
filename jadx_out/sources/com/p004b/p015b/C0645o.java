package com.p004b.p015b;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: renamed from: com.b.b.o */
/* JADX INFO: compiled from: Okio.java */
/* JADX INFO: loaded from: classes.dex */
final class C0645o implements InterfaceC0653w {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C0655y f832a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ OutputStream f833b;

    C0645o(C0655y c0655y, OutputStream outputStream) {
        this.f832a = c0655y;
        this.f833b = outputStream;
    }

    @Override // com.p004b.p015b.InterfaceC0653w
    /* JADX INFO: renamed from: a_ */
    public final void mo167a_(C0636f c0636f, long j) throws IOException {
        C0631aa.m519a(c0636f.f814b, 0L, j);
        while (j > 0) {
            this.f832a.mo601f_();
            C0650t c0650t = c0636f.f813a;
            int iMin = (int) Math.min(j, c0650t.f845c - c0650t.f844b);
            this.f833b.write(c0650t.f843a, c0650t.f844b, iMin);
            c0650t.f844b += iMin;
            long j2 = iMin;
            long j3 = j - j2;
            c0636f.f814b -= j2;
            if (c0650t.f844b == c0650t.f845c) {
                c0636f.f813a = c0650t.m612a();
                C0651u.m616a(c0650t);
            }
            j = j3;
        }
    }

    @Override // com.p004b.p015b.InterfaceC0653w, java.io.Flushable
    public final void flush() throws IOException {
        this.f833b.flush();
    }

    @Override // com.p004b.p015b.InterfaceC0653w, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.f833b.close();
    }

    @Override // com.p004b.p015b.InterfaceC0653w
    /* JADX INFO: renamed from: a */
    public final C0655y mo166a() {
        return this.f832a;
    }

    public final String toString() {
        return "sink(" + this.f833b + ")";
    }
}
