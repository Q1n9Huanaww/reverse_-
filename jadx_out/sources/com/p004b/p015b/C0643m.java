package com.p004b.p015b;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* JADX INFO: renamed from: com.b.b.m */
/* JADX INFO: compiled from: InflaterSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0643m implements InterfaceC0654x {

    /* JADX INFO: renamed from: a */
    private final InterfaceC0638h f827a;

    /* JADX INFO: renamed from: b */
    private final Inflater f828b;

    /* JADX INFO: renamed from: c */
    private int f829c;

    /* JADX INFO: renamed from: d */
    private boolean f830d;

    C0643m(InterfaceC0638h interfaceC0638h, Inflater inflater) {
        if (interfaceC0638h == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (inflater == null) {
            throw new IllegalArgumentException("inflater == null");
        }
        this.f827a = interfaceC0638h;
        this.f828b = inflater;
    }

    @Override // com.p004b.p015b.InterfaceC0654x
    /* JADX INFO: renamed from: a */
    public final long mo47a(C0636f c0636f, long j) throws IOException {
        boolean z;
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.f830d) {
            throw new IllegalStateException("closed");
        }
        if (j == 0) {
            return 0L;
        }
        do {
            z = false;
            if (this.f828b.needsInput()) {
                m604b();
                if (this.f828b.getRemaining() != 0) {
                    throw new IllegalStateException("?");
                }
                if (this.f827a.mo551d()) {
                    z = true;
                } else {
                    C0650t c0650t = this.f827a.mo546c().f813a;
                    this.f829c = c0650t.f845c - c0650t.f844b;
                    this.f828b.setInput(c0650t.f843a, c0650t.f844b, this.f829c);
                }
            }
            try {
                C0650t c0650tM553e = c0636f.m553e(1);
                int iInflate = this.f828b.inflate(c0650tM553e.f843a, c0650tM553e.f845c, 8192 - c0650tM553e.f845c);
                if (iInflate > 0) {
                    c0650tM553e.f845c += iInflate;
                    long j2 = iInflate;
                    c0636f.f814b += j2;
                    return j2;
                }
                if (!this.f828b.finished() && !this.f828b.needsDictionary()) {
                }
                m604b();
                if (c0650tM553e.f844b != c0650tM553e.f845c) {
                    return -1L;
                }
                c0636f.f813a = c0650tM553e.m612a();
                C0651u.m616a(c0650tM553e);
                return -1L;
            } catch (DataFormatException e) {
                throw new IOException(e);
            }
        } while (!z);
        throw new EOFException("source exhausted prematurely");
    }

    /* JADX INFO: renamed from: b */
    private void m604b() {
        if (this.f829c == 0) {
            return;
        }
        int remaining = this.f829c - this.f828b.getRemaining();
        this.f829c -= remaining;
        this.f827a.mo557f(remaining);
    }

    @Override // com.p004b.p015b.InterfaceC0654x
    /* JADX INFO: renamed from: a */
    public final C0655y mo48a() {
        return this.f827a.mo48a();
    }

    @Override // com.p004b.p015b.InterfaceC0654x, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f830d) {
            return;
        }
        this.f828b.end();
        this.f830d = true;
        this.f827a.close();
    }
}
