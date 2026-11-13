package com.p004b.p015b;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.EOFException;
import java.nio.charset.Charset;

/* JADX INFO: renamed from: com.b.b.s */
/* JADX INFO: compiled from: RealBufferedSource.java */
/* JADX INFO: loaded from: classes.dex */
final class C0649s implements InterfaceC0638h {

    /* JADX INFO: renamed from: a */
    public final C0636f f840a = new C0636f();

    /* JADX INFO: renamed from: b */
    public final InterfaceC0654x f841b;

    /* JADX INFO: renamed from: c */
    boolean f842c;

    C0649s(InterfaceC0654x interfaceC0654x) {
        if (interfaceC0654x == null) {
            throw new NullPointerException("source == null");
        }
        this.f841b = interfaceC0654x;
    }

    @Override // com.p004b.p015b.InterfaceC0638h
    /* JADX INFO: renamed from: c */
    public final C0636f mo546c() {
        return this.f840a;
    }

    @Override // com.p004b.p015b.InterfaceC0654x
    /* JADX INFO: renamed from: a */
    public final long mo47a(C0636f c0636f, long j) {
        if (c0636f == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.f842c) {
            throw new IllegalStateException("closed");
        }
        if (this.f840a.f814b == 0 && this.f841b.mo47a(this.f840a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1L;
        }
        return this.f840a.mo47a(c0636f, Math.min(j, this.f840a.f814b));
    }

    @Override // com.p004b.p015b.InterfaceC0638h
    /* JADX INFO: renamed from: d */
    public final boolean mo551d() {
        if (this.f842c) {
            throw new IllegalStateException("closed");
        }
        return this.f840a.mo551d() && this.f841b.mo47a(this.f840a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1;
    }

    @Override // com.p004b.p015b.InterfaceC0638h
    /* JADX INFO: renamed from: a */
    public final void mo538a(long j) throws EOFException {
        if (!m611b(j)) {
            throw new EOFException();
        }
    }

    /* JADX INFO: renamed from: b */
    private boolean m611b(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.f842c) {
            throw new IllegalStateException("closed");
        }
        while (this.f840a.f814b < j) {
            if (this.f841b.mo47a(this.f840a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                return false;
            }
        }
        return true;
    }

    @Override // com.p004b.p015b.InterfaceC0638h
    /* JADX INFO: renamed from: e */
    public final byte mo552e() throws EOFException {
        mo538a(1L);
        return this.f840a.mo552e();
    }

    @Override // com.p004b.p015b.InterfaceC0638h
    /* JADX INFO: renamed from: c */
    public final C0639i mo548c(long j) throws EOFException {
        mo538a(j);
        return this.f840a.mo548c(j);
    }

    @Override // com.p004b.p015b.InterfaceC0638h
    /* JADX INFO: renamed from: e */
    public final byte[] mo554e(long j) throws EOFException {
        mo538a(j);
        return this.f840a.mo554e(j);
    }

    @Override // com.p004b.p015b.InterfaceC0638h
    /* JADX INFO: renamed from: a */
    public final String mo537a(Charset charset) {
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        this.f840a.m529a(this.f841b);
        return this.f840a.mo537a(charset);
    }

    @Override // com.p004b.p015b.InterfaceC0638h
    /* JADX INFO: renamed from: m */
    public final String mo570m() throws EOFException {
        long jMo527a = mo527a((byte) 10);
        if (jMo527a == -1) {
            C0636f c0636f = new C0636f();
            this.f840a.m531a(c0636f, 0L, Math.min(32L, this.f840a.f814b));
            throw new EOFException("\\n not found: size=" + this.f840a.f814b + " content=" + c0636f.m568k().mo588e() + "…");
        }
        return this.f840a.m550d(jMo527a);
    }

    @Override // com.p004b.p015b.InterfaceC0638h
    /* JADX INFO: renamed from: f */
    public final short mo556f() throws EOFException {
        mo538a(2L);
        return this.f840a.mo556f();
    }

    @Override // com.p004b.p015b.InterfaceC0638h
    /* JADX INFO: renamed from: h */
    public final short mo563h() throws EOFException {
        mo538a(2L);
        return C0631aa.m518a(this.f840a.mo556f());
    }

    @Override // com.p004b.p015b.InterfaceC0638h
    /* JADX INFO: renamed from: g */
    public final int mo558g() throws EOFException {
        mo538a(4L);
        return this.f840a.mo558g();
    }

    @Override // com.p004b.p015b.InterfaceC0638h
    /* JADX INFO: renamed from: i */
    public final int mo564i() throws EOFException {
        mo538a(4L);
        return C0631aa.m517a(this.f840a.mo558g());
    }

    @Override // com.p004b.p015b.InterfaceC0638h
    /* JADX INFO: renamed from: j */
    public final long mo566j() throws EOFException {
        mo538a(1L);
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (!m611b(i2)) {
                break;
            }
            byte bM539b = this.f840a.m539b(i);
            if ((bM539b < 48 || bM539b > 57) && ((bM539b < 97 || bM539b > 102) && (bM539b < 65 || bM539b > 70))) {
                if (i != 0) {
                    break;
                }
                throw new NumberFormatException(String.format("Expected leading [0-9a-fA-F] character but was %#x", Byte.valueOf(bM539b)));
            }
            i = i2;
        }
        return this.f840a.mo566j();
    }

    @Override // com.p004b.p015b.InterfaceC0638h
    /* JADX INFO: renamed from: f */
    public final void mo557f(long j) throws EOFException {
        if (this.f842c) {
            throw new IllegalStateException("closed");
        }
        while (j > 0) {
            if (this.f840a.f814b == 0 && this.f841b.mo47a(this.f840a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                throw new EOFException();
            }
            long jMin = Math.min(j, this.f840a.f814b);
            this.f840a.mo557f(jMin);
            j -= jMin;
        }
    }

    @Override // com.p004b.p015b.InterfaceC0638h
    /* JADX INFO: renamed from: b */
    public final boolean mo545b(C0639i c0639i) {
        int iMo590g = c0639i.mo590g();
        if (this.f842c) {
            throw new IllegalStateException("closed");
        }
        if (iMo590g < 0 || c0639i.mo590g() - 0 < iMo590g) {
            return false;
        }
        for (int i = 0; i < iMo590g; i++) {
            long j = ((long) i) + 0;
            if (!m611b(j + 1) || this.f840a.m539b(j) != c0639i.mo579a(i + 0)) {
                return false;
            }
        }
        return true;
    }

    @Override // com.p004b.p015b.InterfaceC0654x, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f842c) {
            return;
        }
        this.f842c = true;
        this.f841b.close();
        this.f840a.m572o();
    }

    @Override // com.p004b.p015b.InterfaceC0654x
    /* JADX INFO: renamed from: a */
    public final C0655y mo48a() {
        return this.f841b.mo48a();
    }

    public final String toString() {
        return "buffer(" + this.f841b + ")";
    }

    @Override // com.p004b.p015b.InterfaceC0638h
    /* JADX INFO: renamed from: a */
    public final long mo527a(byte b) {
        if (this.f842c) {
            throw new IllegalStateException("closed");
        }
        long jMax = 0;
        while (true) {
            long jM528a = this.f840a.m528a(b, jMax);
            if (jM528a != -1) {
                return jM528a;
            }
            long j = this.f840a.f814b;
            if (this.f841b.mo47a(this.f840a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                return -1L;
            }
            jMax = Math.max(jMax, j);
        }
    }
}
