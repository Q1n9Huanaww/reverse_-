package com.p004b.p015b;

import java.nio.charset.Charset;

/* JADX INFO: renamed from: com.b.b.aa */
/* JADX INFO: compiled from: Util.java */
/* JADX INFO: loaded from: classes.dex */
final class C0631aa {

    /* JADX INFO: renamed from: a */
    public static final Charset f805a = Charset.forName("UTF-8");

    /* JADX INFO: renamed from: a */
    public static int m517a(int i) {
        return ((i & 255) << 24) | (((-16777216) & i) >>> 24) | ((16711680 & i) >>> 8) | ((65280 & i) << 8);
    }

    /* JADX INFO: renamed from: a */
    public static short m518a(short s) {
        int i = s & 65535;
        return (short) (((i & 255) << 8) | ((65280 & i) >>> 8));
    }

    /* JADX INFO: renamed from: a */
    public static void m519a(long j, long j2, long j3) {
        if ((j2 | j3) < 0 || j2 > j || j - j2 < j3) {
            throw new ArrayIndexOutOfBoundsException(String.format("size=%s offset=%s byteCount=%s", Long.valueOf(j), Long.valueOf(j2), Long.valueOf(j3)));
        }
    }

    /* JADX INFO: renamed from: a */
    public static boolean m521a(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            if (bArr[i4 + i] != bArr2[i4 + i2]) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: renamed from: a */
    public static void m520a(Throwable th) throws Throwable {
        throw th;
    }
}
