package com.umeng.commonsdk.proguard;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.x */
/* JADX INFO: compiled from: EncodingUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class C1801x {
    /* JADX INFO: renamed from: a */
    public static final boolean m4196a(int i, int i2) {
        return (i & (1 << i2)) != 0;
    }

    /* JADX INFO: renamed from: a */
    public static final boolean m4197a(long j, int i) {
        return (j & (1 << i)) != 0;
    }

    /* JADX INFO: renamed from: b */
    public static final int m4200b(int i, int i2) {
        return i & ((1 << i2) ^ (-1));
    }

    /* JADX INFO: renamed from: b */
    public static final long m4201b(long j, int i) {
        return j & ((1 << i) ^ (-1));
    }

    /* JADX INFO: renamed from: a */
    public static final void m4193a(int i, byte[] bArr) {
        m4194a(i, bArr, 0);
    }

    /* JADX INFO: renamed from: a */
    public static final void m4194a(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) ((i >> 24) & 255);
        bArr[i2 + 1] = (byte) ((i >> 16) & 255);
        bArr[i2 + 2] = (byte) ((i >> 8) & 255);
        bArr[i2 + 3] = (byte) (i & 255);
    }

    /* JADX INFO: renamed from: a */
    public static final int m4189a(byte[] bArr) {
        return m4190a(bArr, 0);
    }

    /* JADX INFO: renamed from: a */
    public static final int m4190a(byte[] bArr, int i) {
        return (bArr[i + 3] & 255) | ((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
    }

    /* JADX INFO: renamed from: a */
    public static final boolean m4195a(byte b, int i) {
        return m4196a((int) b, i);
    }

    /* JADX INFO: renamed from: a */
    public static final boolean m4198a(short s, int i) {
        return m4196a((int) s, i);
    }

    /* JADX INFO: renamed from: b */
    public static final byte m4199b(byte b, int i) {
        return (byte) m4200b((int) b, i);
    }

    /* JADX INFO: renamed from: b */
    public static final short m4202b(short s, int i) {
        return (short) m4200b((int) s, i);
    }

    /* JADX INFO: renamed from: a */
    public static final byte m4187a(byte b, int i, boolean z) {
        return (byte) m4188a((int) b, i, z);
    }

    /* JADX INFO: renamed from: a */
    public static final short m4192a(short s, int i, boolean z) {
        return (short) m4188a((int) s, i, z);
    }

    /* JADX INFO: renamed from: a */
    public static final int m4188a(int i, int i2, boolean z) {
        return z ? i | (1 << i2) : m4200b(i, i2);
    }

    /* JADX INFO: renamed from: a */
    public static final long m4191a(long j, int i, boolean z) {
        return z ? j | (1 << i) : m4201b(j, i);
    }
}
