package com.p004b.p015b;

import android.support.v4.media.session.PlaybackStateCompat;
import com.umeng.commonsdk.amap.UMAmapConfig;
import java.io.EOFException;
import java.nio.charset.Charset;

/* JADX INFO: renamed from: com.b.b.f */
/* JADX INFO: compiled from: Buffer.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0636f implements InterfaceC0637g, InterfaceC0638h, Cloneable {

    /* JADX INFO: renamed from: c */
    private static final byte[] f812c = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};

    /* JADX INFO: renamed from: a */
    C0650t f813a;

    /* JADX INFO: renamed from: b */
    long f814b;

    @Override // com.p004b.p015b.InterfaceC0637g, com.p004b.p015b.InterfaceC0638h
    /* JADX INFO: renamed from: c */
    public final C0636f mo546c() {
        return this;
    }

    @Override // com.p004b.p015b.InterfaceC0653w, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // com.p004b.p015b.InterfaceC0637g, com.p004b.p015b.InterfaceC0653w, java.io.Flushable
    public final void flush() {
    }

    @Override // com.p004b.p015b.InterfaceC0637g
    /* JADX INFO: renamed from: p */
    public final /* bridge */ /* synthetic */ InterfaceC0637g mo573p() {
        return this;
    }

    /* JADX INFO: renamed from: b */
    public final long m540b() {
        return this.f814b;
    }

    @Override // com.p004b.p015b.InterfaceC0638h
    /* JADX INFO: renamed from: d */
    public final boolean mo551d() {
        return this.f814b == 0;
    }

    @Override // com.p004b.p015b.InterfaceC0638h
    /* JADX INFO: renamed from: a */
    public final void mo538a(long j) throws EOFException {
        if (this.f814b < j) {
            throw new EOFException();
        }
    }

    /* JADX INFO: renamed from: a */
    public final C0636f m531a(C0636f c0636f, long j, long j2) {
        if (c0636f == null) {
            throw new IllegalArgumentException("out == null");
        }
        C0631aa.m519a(this.f814b, j, j2);
        if (j2 == 0) {
            return this;
        }
        c0636f.f814b += j2;
        C0650t c0650t = this.f813a;
        while (j >= c0650t.f845c - c0650t.f844b) {
            long j3 = j - ((long) (c0650t.f845c - c0650t.f844b));
            c0650t = c0650t.f848f;
            j = j3;
        }
        while (j2 > 0) {
            C0650t c0650t2 = new C0650t(c0650t);
            c0650t2.f844b = (int) (((long) c0650t2.f844b) + j);
            c0650t2.f845c = Math.min(c0650t2.f844b + ((int) j2), c0650t2.f845c);
            if (c0636f.f813a == null) {
                c0650t2.f849g = c0650t2;
                c0650t2.f848f = c0650t2;
                c0636f.f813a = c0650t2;
            } else {
                c0636f.f813a.f849g.m613a(c0650t2);
            }
            long j4 = j2 - ((long) (c0650t2.f845c - c0650t2.f844b));
            c0650t = c0650t.f848f;
            j = 0;
            j2 = j4;
        }
        return this;
    }

    @Override // com.p004b.p015b.InterfaceC0638h
    /* JADX INFO: renamed from: e */
    public final byte mo552e() {
        if (this.f814b == 0) {
            throw new IllegalStateException("size == 0");
        }
        C0650t c0650t = this.f813a;
        int i = c0650t.f844b;
        int i2 = c0650t.f845c;
        int i3 = i + 1;
        byte b = c0650t.f843a[i];
        this.f814b--;
        if (i3 == i2) {
            this.f813a = c0650t.m612a();
            C0651u.m616a(c0650t);
        } else {
            c0650t.f844b = i3;
        }
        return b;
    }

    /* JADX INFO: renamed from: b */
    public final byte m539b(long j) {
        C0631aa.m519a(this.f814b, j, 1L);
        C0650t c0650t = this.f813a;
        while (true) {
            long j2 = c0650t.f845c - c0650t.f844b;
            if (j >= j2) {
                c0650t = c0650t.f848f;
                j -= j2;
            } else {
                return c0650t.f843a[c0650t.f844b + ((int) j)];
            }
        }
    }

    @Override // com.p004b.p015b.InterfaceC0638h
    /* JADX INFO: renamed from: f */
    public final short mo556f() {
        if (this.f814b < 2) {
            throw new IllegalStateException("size < 2: " + this.f814b);
        }
        C0650t c0650t = this.f813a;
        int i = c0650t.f844b;
        int i2 = c0650t.f845c;
        if (i2 - i < 2) {
            return (short) (((mo552e() & 255) << 8) | (mo552e() & 255));
        }
        byte[] bArr = c0650t.f843a;
        int i3 = i + 1;
        int i4 = i3 + 1;
        int i5 = ((bArr[i] & 255) << 8) | (bArr[i3] & 255);
        this.f814b -= 2;
        if (i4 == i2) {
            this.f813a = c0650t.m612a();
            C0651u.m616a(c0650t);
        } else {
            c0650t.f844b = i4;
        }
        return (short) i5;
    }

    @Override // com.p004b.p015b.InterfaceC0638h
    /* JADX INFO: renamed from: g */
    public final int mo558g() {
        if (this.f814b < 4) {
            throw new IllegalStateException("size < 4: " + this.f814b);
        }
        C0650t c0650t = this.f813a;
        int i = c0650t.f844b;
        int i2 = c0650t.f845c;
        if (i2 - i < 4) {
            return ((mo552e() & 255) << 24) | ((mo552e() & 255) << 16) | ((mo552e() & 255) << 8) | (mo552e() & 255);
        }
        byte[] bArr = c0650t.f843a;
        int i3 = i + 1;
        int i4 = i3 + 1;
        int i5 = ((bArr[i] & 255) << 24) | ((bArr[i3] & 255) << 16);
        int i6 = i4 + 1;
        int i7 = i5 | ((bArr[i4] & 255) << 8);
        int i8 = i6 + 1;
        int i9 = i7 | (bArr[i6] & 255);
        this.f814b -= 4;
        if (i8 == i2) {
            this.f813a = c0650t.m612a();
            C0651u.m616a(c0650t);
        } else {
            c0650t.f844b = i8;
        }
        return i9;
    }

    @Override // com.p004b.p015b.InterfaceC0638h
    /* JADX INFO: renamed from: h */
    public final short mo563h() {
        return C0631aa.m518a(mo556f());
    }

    @Override // com.p004b.p015b.InterfaceC0638h
    /* JADX INFO: renamed from: i */
    public final int mo564i() {
        return C0631aa.m517a(mo558g());
    }

    @Override // com.p004b.p015b.InterfaceC0638h
    /* JADX INFO: renamed from: j */
    public final long mo566j() {
        int i;
        if (this.f814b == 0) {
            throw new IllegalStateException("size == 0");
        }
        int i2 = 0;
        long j = 0;
        boolean z = false;
        do {
            C0650t c0650t = this.f813a;
            byte[] bArr = c0650t.f843a;
            int i3 = c0650t.f844b;
            int i4 = c0650t.f845c;
            while (i3 < i4) {
                byte b = bArr[i3];
                if (b >= 48 && b <= 57) {
                    i = b - 48;
                } else if (b >= 97 && b <= 102) {
                    i = (b - 97) + 10;
                } else {
                    if (b < 65 || b > 70) {
                        if (i2 != 0) {
                            z = true;
                            break;
                        }
                        throw new NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x" + Integer.toHexString(b));
                    }
                    i = (b - 65) + 10;
                }
                if ((j & (-1152921504606846976L)) != 0) {
                    throw new NumberFormatException("Number too large: " + new C0636f().mo565i(j).mo562h((int) b).m569l());
                }
                i3++;
                i2++;
                j = (j << 4) | ((long) i);
            }
            if (i3 == i4) {
                this.f813a = c0650t.m612a();
                C0651u.m616a(c0650t);
            } else {
                c0650t.f844b = i3;
            }
            if (z) {
                break;
            }
        } while (this.f813a != null);
        this.f814b -= (long) i2;
        return j;
    }

    /* JADX INFO: renamed from: k */
    public final C0639i m568k() {
        return new C0639i(m571n());
    }

    @Override // com.p004b.p015b.InterfaceC0638h
    /* JADX INFO: renamed from: c */
    public final C0639i mo548c(long j) {
        return new C0639i(mo554e(j));
    }

    /* JADX INFO: renamed from: l */
    public final String m569l() {
        try {
            return m524a(this.f814b, C0631aa.f805a);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    /* JADX INFO: renamed from: k */
    private String m526k(long j) {
        return m524a(j, C0631aa.f805a);
    }

    @Override // com.p004b.p015b.InterfaceC0638h
    /* JADX INFO: renamed from: a */
    public final String mo537a(Charset charset) {
        try {
            return m524a(this.f814b, charset);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    /* JADX INFO: renamed from: a */
    private String m524a(long j, Charset charset) {
        C0631aa.m519a(this.f814b, 0L, j);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        }
        if (j == 0) {
            return "";
        }
        C0650t c0650t = this.f813a;
        if (((long) c0650t.f844b) + j > c0650t.f845c) {
            return new String(mo554e(j), charset);
        }
        String str = new String(c0650t.f843a, c0650t.f844b, (int) j, charset);
        c0650t.f844b = (int) (((long) c0650t.f844b) + j);
        this.f814b -= j;
        if (c0650t.f844b == c0650t.f845c) {
            this.f813a = c0650t.m612a();
            C0651u.m616a(c0650t);
        }
        return str;
    }

    /* JADX INFO: renamed from: d */
    final String m550d(long j) throws EOFException {
        if (j > 0) {
            long j2 = j - 1;
            if (m539b(j2) == 13) {
                String strM526k = m526k(j2);
                mo557f(2L);
                return strM526k;
            }
        }
        String strM526k2 = m526k(j);
        mo557f(1L);
        return strM526k2;
    }

    /* JADX INFO: renamed from: n */
    public final byte[] m571n() {
        try {
            return mo554e(this.f814b);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // com.p004b.p015b.InterfaceC0638h
    /* JADX INFO: renamed from: e */
    public final byte[] mo554e(long j) throws EOFException {
        C0631aa.m519a(this.f814b, 0L, j);
        if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        }
        byte[] bArr = new byte[(int) j];
        m525c(bArr);
        return bArr;
    }

    /* JADX INFO: renamed from: c */
    private void m525c(byte[] bArr) throws EOFException {
        int iMin;
        int i = 0;
        while (i < bArr.length) {
            int length = bArr.length - i;
            C0631aa.m519a(bArr.length, i, length);
            C0650t c0650t = this.f813a;
            if (c0650t == null) {
                iMin = -1;
            } else {
                iMin = Math.min(length, c0650t.f845c - c0650t.f844b);
                System.arraycopy(c0650t.f843a, c0650t.f844b, bArr, i, iMin);
                c0650t.f844b += iMin;
                this.f814b -= (long) iMin;
                if (c0650t.f844b == c0650t.f845c) {
                    this.f813a = c0650t.m612a();
                    C0651u.m616a(c0650t);
                }
            }
            if (iMin == -1) {
                throw new EOFException();
            }
            i += iMin;
        }
    }

    /* JADX INFO: renamed from: o */
    public final void m572o() {
        try {
            mo557f(this.f814b);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // com.p004b.p015b.InterfaceC0638h
    /* JADX INFO: renamed from: f */
    public final void mo557f(long j) throws EOFException {
        while (j > 0) {
            if (this.f813a == null) {
                throw new EOFException();
            }
            int iMin = (int) Math.min(j, this.f813a.f845c - this.f813a.f844b);
            long j2 = iMin;
            this.f814b -= j2;
            long j3 = j - j2;
            this.f813a.f844b += iMin;
            if (this.f813a.f844b == this.f813a.f845c) {
                C0650t c0650t = this.f813a;
                this.f813a = c0650t.m612a();
                C0651u.m616a(c0650t);
            }
            j = j3;
        }
    }

    /* JADX INFO: renamed from: a */
    public final C0636f m532a(C0639i c0639i) {
        if (c0639i == null) {
            throw new IllegalArgumentException("byteString == null");
        }
        c0639i.mo582a(this);
        return this;
    }

    @Override // com.p004b.p015b.InterfaceC0637g
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final C0636f mo542b(String str) {
        return m534a(str, 0, str.length());
    }

    /* JADX INFO: renamed from: a */
    public final C0636f m534a(String str, int i, int i2) {
        char cCharAt;
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        }
        if (i < 0) {
            throw new IllegalAccessError("beginIndex < 0: " + i);
        }
        if (i2 < i) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + i2 + " < " + i);
        }
        if (i2 > str.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + i2 + " > " + str.length());
        }
        while (i < i2) {
            char cCharAt2 = str.charAt(i);
            if (cCharAt2 < 128) {
                C0650t c0650tM553e = m553e(1);
                byte[] bArr = c0650tM553e.f843a;
                int i3 = c0650tM553e.f845c - i;
                int iMin = Math.min(i2, 8192 - i3);
                int i4 = i + 1;
                bArr[i + i3] = (byte) cCharAt2;
                while (true) {
                    i = i4;
                    if (i >= iMin || (cCharAt = str.charAt(i)) >= 128) {
                        break;
                    }
                    i4 = i + 1;
                    bArr[i + i3] = (byte) cCharAt;
                }
                int i5 = (i3 + i) - c0650tM553e.f845c;
                c0650tM553e.f845c += i5;
                this.f814b += (long) i5;
            } else if (cCharAt2 < 2048) {
                mo562h((cCharAt2 >> 6) | 192);
                mo562h((cCharAt2 & '?') | 128);
                i++;
            } else if (cCharAt2 < 55296 || cCharAt2 > 57343) {
                mo562h((cCharAt2 >> '\f') | 224);
                mo562h(((cCharAt2 >> 6) & 63) | 128);
                mo562h((cCharAt2 & '?') | 128);
                i++;
            } else {
                int i6 = i + 1;
                char cCharAt3 = i6 < i2 ? str.charAt(i6) : (char) 0;
                if (cCharAt2 > 56319 || cCharAt3 < 56320 || cCharAt3 > 57343) {
                    mo562h(63);
                    i = i6;
                } else {
                    int i7 = (((cCharAt2 & 10239) << 10) | (9215 & cCharAt3)) + 65536;
                    mo562h((i7 >> 18) | 240);
                    mo562h(((i7 >> 12) & 63) | 128);
                    mo562h(((i7 >> 6) & 63) | 128);
                    mo562h((i7 & 63) | 128);
                    i += 2;
                }
            }
        }
        return this;
    }

    /* JADX INFO: renamed from: a */
    public final C0636f m530a(int i) {
        if (i < 128) {
            mo562h(i);
        } else if (i < 2048) {
            mo562h((i >> 6) | 192);
            mo562h((i & 63) | 128);
        } else if (i < 65536) {
            if (i >= 55296 && i <= 57343) {
                throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(i));
            }
            mo562h((i >> 12) | 224);
            mo562h(((i >> 6) & 63) | 128);
            mo562h((i & 63) | 128);
        } else if (i <= 1114111) {
            mo562h((i >> 18) | 240);
            mo562h(((i >> 12) & 63) | 128);
            mo562h(((i >> 6) & 63) | 128);
            mo562h((i & 63) | 128);
        } else {
            throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(i));
        }
        return this;
    }

    @Override // com.p004b.p015b.InterfaceC0637g
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final C0636f mo543b(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("source == null");
        }
        return mo544b(bArr, 0, bArr.length);
    }

    @Override // com.p004b.p015b.InterfaceC0637g
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final C0636f mo544b(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = i2;
        C0631aa.m519a(bArr.length, i, j);
        int i3 = i2 + i;
        while (i < i3) {
            C0650t c0650tM553e = m553e(1);
            int iMin = Math.min(i3 - i, 8192 - c0650tM553e.f845c);
            System.arraycopy(bArr, i, c0650tM553e.f843a, c0650tM553e.f845c, iMin);
            i += iMin;
            c0650tM553e.f845c += iMin;
        }
        this.f814b += j;
        return this;
    }

    /* JADX INFO: renamed from: a */
    public final long m529a(InterfaceC0654x interfaceC0654x) {
        if (interfaceC0654x == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = 0;
        while (true) {
            long jMo47a = interfaceC0654x.mo47a(this, PlaybackStateCompat.ACTION_PLAY_FROM_URI);
            if (jMo47a == -1) {
                return j;
            }
            j += jMo47a;
        }
    }

    @Override // com.p004b.p015b.InterfaceC0637g
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final C0636f mo562h(int i) {
        C0650t c0650tM553e = m553e(1);
        byte[] bArr = c0650tM553e.f843a;
        int i2 = c0650tM553e.f845c;
        c0650tM553e.f845c = i2 + 1;
        bArr[i2] = (byte) i;
        this.f814b++;
        return this;
    }

    @Override // com.p004b.p015b.InterfaceC0637g
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C0636f mo560g(int i) {
        C0650t c0650tM553e = m553e(2);
        byte[] bArr = c0650tM553e.f843a;
        int i2 = c0650tM553e.f845c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        bArr[i3] = (byte) (i & 255);
        c0650tM553e.f845c = i3 + 1;
        this.f814b += 2;
        return this;
    }

    @Override // com.p004b.p015b.InterfaceC0637g
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public final C0636f mo555f(int i) {
        C0650t c0650tM553e = m553e(4);
        byte[] bArr = c0650tM553e.f843a;
        int i2 = c0650tM553e.f845c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 24) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i >>> 16) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((i >>> 8) & 255);
        bArr[i5] = (byte) (i & 255);
        c0650tM553e.f845c = i5 + 1;
        this.f814b += 4;
        return this;
    }

    @Override // com.p004b.p015b.InterfaceC0637g
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public final C0636f mo567j(long j) {
        if (j == 0) {
            return mo562h(48);
        }
        boolean z = false;
        int i = 1;
        if (j < 0) {
            j = -j;
            if (j < 0) {
                return mo542b("-9223372036854775808");
            }
            z = true;
        }
        if (j < 100000000) {
            if (j < UMAmapConfig.AMAP_CACHE_WRITE_TIME) {
                if (j >= 100) {
                    i = j < 1000 ? 3 : 4;
                } else if (j >= 10) {
                    i = 2;
                }
            } else if (j < 1000000) {
                i = j < 100000 ? 5 : 6;
            } else {
                i = j < 10000000 ? 7 : 8;
            }
        } else if (j < 1000000000000L) {
            if (j < 10000000000L) {
                i = j < 1000000000 ? 9 : 10;
            } else {
                i = j < 100000000000L ? 11 : 12;
            }
        } else if (j < 1000000000000000L) {
            if (j < 10000000000000L) {
                i = 13;
            } else {
                i = j < 100000000000000L ? 14 : 15;
            }
        } else if (j < 100000000000000000L) {
            i = j < 10000000000000000L ? 16 : 17;
        } else {
            i = j < 1000000000000000000L ? 18 : 19;
        }
        if (z) {
            i++;
        }
        C0650t c0650tM553e = m553e(i);
        byte[] bArr = c0650tM553e.f843a;
        int i2 = c0650tM553e.f845c + i;
        while (j != 0) {
            i2--;
            bArr[i2] = f812c[(int) (j % 10)];
            j /= 10;
        }
        if (z) {
            bArr[i2 - 1] = 45;
        }
        c0650tM553e.f845c += i;
        this.f814b += (long) i;
        return this;
    }

    @Override // com.p004b.p015b.InterfaceC0637g
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public final C0636f mo565i(long j) {
        if (j == 0) {
            return mo562h(48);
        }
        int iNumberOfTrailingZeros = (Long.numberOfTrailingZeros(Long.highestOneBit(j)) / 4) + 1;
        C0650t c0650tM553e = m553e(iNumberOfTrailingZeros);
        byte[] bArr = c0650tM553e.f843a;
        int i = c0650tM553e.f845c;
        for (int i2 = (c0650tM553e.f845c + iNumberOfTrailingZeros) - 1; i2 >= i; i2--) {
            bArr[i2] = f812c[(int) (j & 15)];
            j >>>= 4;
        }
        c0650tM553e.f845c += iNumberOfTrailingZeros;
        this.f814b += (long) iNumberOfTrailingZeros;
        return this;
    }

    /* JADX INFO: renamed from: e */
    final C0650t m553e(int i) {
        if (i <= 0 || i > 8192) {
            throw new IllegalArgumentException();
        }
        if (this.f813a == null) {
            this.f813a = C0651u.m615a();
            C0650t c0650t = this.f813a;
            C0650t c0650t2 = this.f813a;
            C0650t c0650t3 = this.f813a;
            c0650t2.f849g = c0650t3;
            c0650t.f848f = c0650t3;
            return c0650t3;
        }
        C0650t c0650t4 = this.f813a.f849g;
        return (c0650t4.f845c + i > 8192 || !c0650t4.f847e) ? c0650t4.m613a(C0651u.m615a()) : c0650t4;
    }

    @Override // com.p004b.p015b.InterfaceC0653w
    /* JADX INFO: renamed from: a_ */
    public final void mo167a_(C0636f c0636f, long j) {
        C0650t c0650tM615a;
        if (c0636f == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (c0636f == this) {
            throw new IllegalArgumentException("source == this");
        }
        C0631aa.m519a(c0636f.f814b, 0L, j);
        while (j > 0) {
            if (j < c0636f.f813a.f845c - c0636f.f813a.f844b) {
                C0650t c0650t = this.f813a != null ? this.f813a.f849g : null;
                if (c0650t != null && c0650t.f847e) {
                    if ((j + ((long) c0650t.f845c)) - ((long) (c0650t.f846d ? 0 : c0650t.f844b)) <= PlaybackStateCompat.ACTION_PLAY_FROM_URI) {
                        c0636f.f813a.m614a(c0650t, (int) j);
                        c0636f.f814b -= j;
                        this.f814b += j;
                        return;
                    }
                }
                C0650t c0650t2 = c0636f.f813a;
                int i = (int) j;
                if (i <= 0 || i > c0650t2.f845c - c0650t2.f844b) {
                    throw new IllegalArgumentException();
                }
                if (i >= 1024) {
                    c0650tM615a = new C0650t(c0650t2);
                } else {
                    c0650tM615a = C0651u.m615a();
                    System.arraycopy(c0650t2.f843a, c0650t2.f844b, c0650tM615a.f843a, 0, i);
                }
                c0650tM615a.f845c = c0650tM615a.f844b + i;
                c0650t2.f844b += i;
                c0650t2.f849g.m613a(c0650tM615a);
                c0636f.f813a = c0650tM615a;
            }
            C0650t c0650t3 = c0636f.f813a;
            long j2 = c0650t3.f845c - c0650t3.f844b;
            c0636f.f813a = c0650t3.m612a();
            if (this.f813a == null) {
                this.f813a = c0650t3;
                C0650t c0650t4 = this.f813a;
                C0650t c0650t5 = this.f813a;
                C0650t c0650t6 = this.f813a;
                c0650t5.f849g = c0650t6;
                c0650t4.f848f = c0650t6;
            } else {
                C0650t c0650tM613a = this.f813a.f849g.m613a(c0650t3);
                if (c0650tM613a.f849g == c0650tM613a) {
                    throw new IllegalStateException();
                }
                if (c0650tM613a.f849g.f847e) {
                    int i2 = c0650tM613a.f845c - c0650tM613a.f844b;
                    if (i2 <= (8192 - c0650tM613a.f849g.f845c) + (c0650tM613a.f849g.f846d ? 0 : c0650tM613a.f849g.f844b)) {
                        c0650tM613a.m614a(c0650tM613a.f849g, i2);
                        c0650tM613a.m612a();
                        C0651u.m616a(c0650tM613a);
                    }
                }
            }
            c0636f.f814b -= j2;
            this.f814b += j2;
            j -= j2;
        }
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
        if (this.f814b == 0) {
            return -1L;
        }
        if (j > this.f814b) {
            j = this.f814b;
        }
        c0636f.mo167a_(this, j);
        return j;
    }

    @Override // com.p004b.p015b.InterfaceC0638h
    /* JADX INFO: renamed from: a */
    public final long mo527a(byte b) {
        return m528a(b, 0L);
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0045  */
    /* JADX WARN: Code duplicated, block: B:22:0x0053  */
    /* JADX WARN: Code duplicated, block: B:26:0x005e A[LOOP:2: B:21:0x0051->B:26:0x005e, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:31:0x0057 A[SYNTHETIC] */
    /* JADX INFO: renamed from: a */
    public final long m528a(byte b, long j) {
        byte[] bArr;
        int i;
        int i2;
        long j2 = 0;
        if (j < 0) {
            throw new IllegalArgumentException("fromIndex < 0");
        }
        C0650t c0650t = this.f813a;
        if (c0650t == null) {
            return -1L;
        }
        if (this.f814b - j >= j) {
            while (true) {
                long j3 = j2 + ((long) (c0650t.f845c - c0650t.f844b));
                if (j3 >= j) {
                    break;
                }
                c0650t = c0650t.f848f;
                j2 = j3;
            }
            while (j2 < this.f814b) {
                bArr = c0650t.f843a;
                i2 = c0650t.f845c;
                for (i = (int) ((((long) c0650t.f844b) + j) - j2); i < i2; i++) {
                    if (bArr[i] == b) {
                        return ((long) (i - c0650t.f844b)) + j2;
                    }
                }
                long j4 = j2 + ((long) (c0650t.f845c - c0650t.f844b));
                c0650t = c0650t.f848f;
                j = j4;
                j2 = j;
            }
            return -1L;
        }
        j2 = this.f814b;
        while (j2 > j) {
            c0650t = c0650t.f849g;
            j2 -= (long) (c0650t.f845c - c0650t.f844b);
        }
        while (j2 < this.f814b) {
            bArr = c0650t.f843a;
            i2 = c0650t.f845c;
            while (i < i2) {
                if (bArr[i] == b) {
                    return ((long) (i - c0650t.f844b)) + j2;
                }
            }
            long j5 = j2 + ((long) (c0650t.f845c - c0650t.f844b));
            c0650t = c0650t.f848f;
            j = j5;
            j2 = j;
        }
        return -1L;
    }

    @Override // com.p004b.p015b.InterfaceC0638h
    /* JADX INFO: renamed from: b */
    public final boolean mo545b(C0639i c0639i) {
        int iMo590g = c0639i.mo590g();
        if (iMo590g < 0 || this.f814b - 0 < iMo590g || c0639i.mo590g() - 0 < iMo590g) {
            return false;
        }
        for (int i = 0; i < iMo590g; i++) {
            if (m539b(((long) i) + 0) != c0639i.mo579a(i + 0)) {
                return false;
            }
        }
        return true;
    }

    @Override // com.p004b.p015b.InterfaceC0653w
    /* JADX INFO: renamed from: a */
    public final C0655y mo166a() {
        return C0655y.f854b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0636f)) {
            return false;
        }
        C0636f c0636f = (C0636f) obj;
        if (this.f814b != c0636f.f814b) {
            return false;
        }
        long j = 0;
        if (this.f814b == 0) {
            return true;
        }
        C0650t c0650t = this.f813a;
        C0650t c0650t2 = c0636f.f813a;
        int i = c0650t.f844b;
        int i2 = c0650t2.f844b;
        while (j < this.f814b) {
            long jMin = Math.min(c0650t.f845c - i, c0650t2.f845c - i2);
            int i3 = i2;
            int i4 = i;
            int i5 = 0;
            while (i5 < jMin) {
                int i6 = i4 + 1;
                int i7 = i3 + 1;
                if (c0650t.f843a[i4] != c0650t2.f843a[i3]) {
                    return false;
                }
                i5++;
                i4 = i6;
                i3 = i7;
            }
            if (i4 == c0650t.f845c) {
                c0650t = c0650t.f848f;
                i = c0650t.f844b;
            } else {
                i = i4;
            }
            if (i3 == c0650t2.f845c) {
                c0650t2 = c0650t2.f848f;
                i2 = c0650t2.f844b;
            } else {
                i2 = i3;
            }
            j += jMin;
        }
        return true;
    }

    public final int hashCode() {
        C0650t c0650t = this.f813a;
        if (c0650t == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = c0650t.f845c;
            for (int i3 = c0650t.f844b; i3 < i2; i3++) {
                i = (i * 31) + c0650t.f843a[i3];
            }
            c0650t = c0650t.f848f;
        } while (c0650t != this.f813a);
        return i;
    }

    @Override // com.p004b.p015b.InterfaceC0638h
    /* JADX INFO: renamed from: m */
    public final String mo570m() throws EOFException {
        long jM528a = m528a((byte) 10, 0L);
        if (jM528a == -1) {
            C0636f c0636f = new C0636f();
            m531a(c0636f, 0L, Math.min(32L, this.f814b));
            throw new EOFException("\\n not found: size=" + this.f814b + " content=" + c0636f.m568k().mo588e() + "…");
        }
        return m550d(jM528a);
    }

    public final String toString() {
        C0639i c0652v;
        if (this.f814b > 2147483647L) {
            throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.f814b);
        }
        int i = (int) this.f814b;
        if (i == 0) {
            c0652v = C0639i.f816b;
        } else {
            c0652v = new C0652v(this, i);
        }
        return c0652v.toString();
    }

    public final /* synthetic */ Object clone() {
        C0636f c0636f = new C0636f();
        if (this.f814b == 0) {
            return c0636f;
        }
        c0636f.f813a = new C0650t(this.f813a);
        C0650t c0650t = c0636f.f813a;
        C0650t c0650t2 = c0636f.f813a;
        C0650t c0650t3 = c0636f.f813a;
        c0650t2.f849g = c0650t3;
        c0650t.f848f = c0650t3;
        C0650t c0650t4 = this.f813a;
        while (true) {
            c0650t4 = c0650t4.f848f;
            if (c0650t4 != this.f813a) {
                c0636f.f813a.f849g.m613a(new C0650t(c0650t4));
            } else {
                c0636f.f814b = this.f814b;
                return c0636f;
            }
        }
    }
}
