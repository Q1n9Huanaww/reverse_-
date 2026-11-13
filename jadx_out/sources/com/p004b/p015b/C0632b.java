package com.p004b.p015b;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;

/* JADX INFO: renamed from: com.b.b.b */
/* JADX INFO: compiled from: AsyncTimeout.java */
/* JADX INFO: loaded from: classes.dex */
final class C0632b implements InterfaceC0653w {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ InterfaceC0653w f806a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ C0630a f807b;

    C0632b(C0630a c0630a, InterfaceC0653w interfaceC0653w) {
        this.f807b = c0630a;
        this.f806a = interfaceC0653w;
    }

    @Override // com.p004b.p015b.InterfaceC0653w
    /* JADX INFO: renamed from: a_ */
    public final void mo167a_(C0636f c0636f, long j) throws IOException {
        C0631aa.m519a(c0636f.f814b, 0L, j);
        while (true) {
            long j2 = 0;
            if (j <= 0) {
                return;
            }
            C0650t c0650t = c0636f.f813a;
            while (j2 < PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
                long j3 = j2 + ((long) (c0636f.f813a.f845c - c0636f.f813a.f844b));
                if (j3 >= j) {
                    j2 = j;
                    break;
                }
                j2 = j3;
            }
            this.f807b.m516c();
            try {
                try {
                    this.f806a.mo167a_(c0636f, j2);
                    this.f807b.m513a(true);
                    j -= j2;
                } catch (IOException e) {
                    throw this.f807b.m515b(e);
                }
            } catch (Throwable th) {
                this.f807b.m513a(false);
                throw th;
            }
        }
    }

    @Override // com.p004b.p015b.InterfaceC0653w, java.io.Flushable
    public final void flush() throws IOException {
        this.f807b.m516c();
        try {
            try {
                this.f806a.flush();
                this.f807b.m513a(true);
            } catch (IOException e) {
                throw this.f807b.m515b(e);
            }
        } catch (Throwable th) {
            this.f807b.m513a(false);
            throw th;
        }
    }

    @Override // com.p004b.p015b.InterfaceC0653w, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.f807b.m516c();
        try {
            try {
                this.f806a.close();
                this.f807b.m513a(true);
            } catch (IOException e) {
                throw this.f807b.m515b(e);
            }
        } catch (Throwable th) {
            this.f807b.m513a(false);
            throw th;
        }
    }

    @Override // com.p004b.p015b.InterfaceC0653w
    /* JADX INFO: renamed from: a */
    public final C0655y mo166a() {
        return this.f807b;
    }

    public final String toString() {
        return "AsyncTimeout.sink(" + this.f806a + ")";
    }
}
