package com.googlecode.leptonica.android;

import android.graphics.Rect;

/* JADX INFO: loaded from: classes.dex */
public class Pix {

    /* JADX INFO: renamed from: a */
    public static final int f2926a = 0;

    /* JADX INFO: renamed from: b */
    public static final int f2927b = 1;

    /* JADX INFO: renamed from: c */
    public static final int f2928c = 2;

    /* JADX INFO: renamed from: d */
    final long f2929d;

    /* JADX INFO: renamed from: e */
    boolean f2930e;

    private static native long nativeClone(long j);

    private static native long nativeCopy(long j);

    private static native long nativeCreateFromData(byte[] bArr, int i, int i2, int i3);

    private static native long nativeCreatePix(int i, int i2, int i3);

    private static native void nativeDestroy(long j);

    private static native byte[] nativeGetData(long j);

    private static native int nativeGetDepth(long j);

    static native boolean nativeGetDimensions(long j, int[] iArr);

    private static native int nativeGetHeight(long j);

    private static native int nativeGetPixel(long j, int i, int i2);

    private static native int nativeGetRefCount(long j);

    private static native int nativeGetWidth(long j);

    private static native boolean nativeInvert(long j);

    private static native void nativeSetPixel(long j, int i, int i2, int i3);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    public Pix(long j) {
        this.f2929d = j;
        this.f2930e = false;
    }

    private Pix(int i, int i2, int i3) {
        if (i <= 0 || i2 <= 0) {
            throw new IllegalArgumentException("Pix width and height must be > 0");
        }
        if (i3 != 1 && i3 != 2 && i3 != 4 && i3 != 8 && i3 != 16 && i3 != 24 && i3 != 32) {
            throw new IllegalArgumentException("Depth must be one of 1, 2, 4, 8, 16, or 32");
        }
        this.f2929d = nativeCreatePix(i, i2, i3);
        this.f2930e = false;
    }

    /* JADX INFO: renamed from: a */
    public final long m2684a() {
        if (this.f2930e) {
            throw new IllegalStateException();
        }
        return this.f2929d;
    }

    /* JADX INFO: renamed from: f */
    private byte[] m2677f() {
        if (this.f2930e) {
            throw new IllegalStateException();
        }
        byte[] bArrNativeGetData = nativeGetData(this.f2929d);
        if (bArrNativeGetData == null) {
            throw new RuntimeException("native getData failed");
        }
        return bArrNativeGetData;
    }

    /* JADX INFO: renamed from: g */
    private int[] m2678g() {
        if (this.f2930e) {
            throw new IllegalStateException();
        }
        int[] iArr = new int[3];
        if (this.f2930e) {
            throw new IllegalStateException();
        }
        if (nativeGetDimensions(this.f2929d, iArr)) {
            return iArr;
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    private boolean m2676a(int[] iArr) {
        if (this.f2930e) {
            throw new IllegalStateException();
        }
        return nativeGetDimensions(this.f2929d, iArr);
    }

    /* JADX INFO: renamed from: h */
    private Pix m2679h() {
        if (this.f2930e) {
            throw new IllegalStateException();
        }
        long jNativeClone = nativeClone(this.f2929d);
        if (jNativeClone == 0) {
            throw new OutOfMemoryError();
        }
        return new Pix(jNativeClone);
    }

    /* JADX INFO: renamed from: i */
    private Pix m2680i() {
        if (this.f2930e) {
            throw new IllegalStateException();
        }
        long jNativeCopy = nativeCopy(this.f2929d);
        if (jNativeCopy == 0) {
            throw new OutOfMemoryError();
        }
        return new Pix(jNativeCopy);
    }

    /* JADX INFO: renamed from: j */
    private boolean m2681j() {
        if (this.f2930e) {
            throw new IllegalStateException();
        }
        return nativeInvert(this.f2929d);
    }

    /* JADX INFO: renamed from: b */
    public final void m2685b() {
        if (this.f2930e) {
            return;
        }
        nativeDestroy(this.f2929d);
        this.f2930e = true;
    }

    /* JADX INFO: renamed from: a */
    private static Pix m2674a(byte[] bArr, int i, int i2, int i3) {
        long jNativeCreateFromData = nativeCreateFromData(bArr, i, i2, i3);
        if (jNativeCreateFromData == 0) {
            throw new OutOfMemoryError();
        }
        return new Pix(jNativeCreateFromData);
    }

    /* JADX INFO: renamed from: k */
    private Rect m2682k() {
        return new Rect(0, 0, m2686c(), m2687d());
    }

    /* JADX INFO: renamed from: c */
    public final int m2686c() {
        if (this.f2930e) {
            throw new IllegalStateException();
        }
        return nativeGetWidth(this.f2929d);
    }

    /* JADX INFO: renamed from: d */
    public final int m2687d() {
        if (this.f2930e) {
            throw new IllegalStateException();
        }
        return nativeGetHeight(this.f2929d);
    }

    /* JADX INFO: renamed from: e */
    public final int m2688e() {
        if (this.f2930e) {
            throw new IllegalStateException();
        }
        return nativeGetDepth(this.f2929d);
    }

    /* JADX INFO: renamed from: l */
    private int m2683l() {
        return nativeGetRefCount(this.f2929d);
    }

    /* JADX INFO: renamed from: a */
    private int m2673a(int i, int i2) {
        if (this.f2930e) {
            throw new IllegalStateException();
        }
        if (i < 0 || i >= m2686c()) {
            throw new IllegalArgumentException("Supplied x coordinate exceeds image bounds");
        }
        if (i2 < 0 || i2 >= m2687d()) {
            throw new IllegalArgumentException("Supplied y coordinate exceeds image bounds");
        }
        return nativeGetPixel(this.f2929d, i, i2);
    }

    /* JADX INFO: renamed from: a */
    private void m2675a(int i, int i2, int i3) {
        if (this.f2930e) {
            throw new IllegalStateException();
        }
        if (i < 0 || i >= m2686c()) {
            throw new IllegalArgumentException("Supplied x coordinate exceeds image bounds");
        }
        if (i2 < 0 || i2 >= m2687d()) {
            throw new IllegalArgumentException("Supplied y coordinate exceeds image bounds");
        }
        nativeSetPixel(this.f2929d, i, i2, i3);
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        if (this.f2930e) {
            throw new IllegalStateException();
        }
        long jNativeClone = nativeClone(this.f2929d);
        if (jNativeClone == 0) {
            throw new OutOfMemoryError();
        }
        return new Pix(jNativeClone);
    }
}
