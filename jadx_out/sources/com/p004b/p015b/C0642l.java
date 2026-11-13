package com.p004b.p015b;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

/* JADX INFO: renamed from: com.b.b.l */
/* JADX INFO: compiled from: GzipSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0642l implements InterfaceC0654x {

    /* JADX INFO: renamed from: b */
    private final InterfaceC0638h f823b;

    /* JADX INFO: renamed from: c */
    private final Inflater f824c;

    /* JADX INFO: renamed from: d */
    private final C0643m f825d;

    /* JADX INFO: renamed from: a */
    private int f822a = 0;

    /* JADX INFO: renamed from: e */
    private final CRC32 f826e = new CRC32();

    public C0642l(InterfaceC0654x interfaceC0654x) {
        if (interfaceC0654x == null) {
            throw new IllegalArgumentException("source == null");
        }
        this.f824c = new Inflater(true);
        this.f823b = C0644n.m606a(interfaceC0654x);
        this.f825d = new C0643m(this.f823b, this.f824c);
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
        if (this.f822a == 0) {
            this.f823b.mo538a(10L);
            byte bM539b = this.f823b.mo546c().m539b(3L);
            boolean z = ((bM539b >> 1) & 1) == 1;
            if (z) {
                m602a(this.f823b.mo546c(), 0L, 10L);
            }
            m603a("ID1ID2", 8075, this.f823b.mo556f());
            this.f823b.mo557f(8L);
            if (((bM539b >> 2) & 1) == 1) {
                this.f823b.mo538a(2L);
                if (z) {
                    m602a(this.f823b.mo546c(), 0L, 2L);
                }
                long jMo563h = this.f823b.mo546c().mo563h();
                this.f823b.mo538a(jMo563h);
                if (z) {
                    m602a(this.f823b.mo546c(), 0L, jMo563h);
                }
                this.f823b.mo557f(jMo563h);
            }
            if (((bM539b >> 3) & 1) == 1) {
                long jMo527a = this.f823b.mo527a((byte) 0);
                if (jMo527a == -1) {
                    throw new EOFException();
                }
                if (z) {
                    m602a(this.f823b.mo546c(), 0L, jMo527a + 1);
                }
                this.f823b.mo557f(jMo527a + 1);
            }
            if (((bM539b >> 4) & 1) == 1) {
                long jMo527a2 = this.f823b.mo527a((byte) 0);
                if (jMo527a2 == -1) {
                    throw new EOFException();
                }
                if (z) {
                    m602a(this.f823b.mo546c(), 0L, jMo527a2 + 1);
                }
                this.f823b.mo557f(jMo527a2 + 1);
            }
            if (z) {
                m603a("FHCRC", this.f823b.mo563h(), (short) this.f826e.getValue());
                this.f826e.reset();
            }
            this.f822a = 1;
        }
        if (this.f822a == 1) {
            long j2 = c0636f.f814b;
            long jMo47a = this.f825d.mo47a(c0636f, j);
            if (jMo47a != -1) {
                m602a(c0636f, j2, jMo47a);
                return jMo47a;
            }
            this.f822a = 2;
        }
        if (this.f822a == 2) {
            m603a("CRC", this.f823b.mo564i(), (int) this.f826e.getValue());
            m603a("ISIZE", this.f823b.mo564i(), (int) this.f824c.getBytesWritten());
            this.f822a = 3;
            if (!this.f823b.mo551d()) {
                throw new IOException("gzip finished without exhausting source");
            }
        }
        return -1L;
    }

    @Override // com.p004b.p015b.InterfaceC0654x
    /* JADX INFO: renamed from: a */
    public final C0655y mo48a() {
        return this.f823b.mo48a();
    }

    @Override // com.p004b.p015b.InterfaceC0654x, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f825d.close();
    }

    /* JADX INFO: renamed from: a */
    private void m602a(C0636f c0636f, long j, long j2) {
        C0650t c0650t = c0636f.f813a;
        while (j >= c0650t.f845c - c0650t.f844b) {
            long j3 = j - ((long) (c0650t.f845c - c0650t.f844b));
            c0650t = c0650t.f848f;
            j = j3;
        }
        while (j2 > 0) {
            int i = (int) (((long) c0650t.f844b) + j);
            int iMin = (int) Math.min(c0650t.f845c - i, j2);
            this.f826e.update(c0650t.f843a, i, iMin);
            c0650t = c0650t.f848f;
            j = 0;
            j2 -= (long) iMin;
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m603a(String str, int i, int i2) throws IOException {
        if (i2 != i) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", str, Integer.valueOf(i2), Integer.valueOf(i)));
        }
    }
}
