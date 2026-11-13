package com.googlecode.leptonica.android;

import android.graphics.Rect;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class Box {

    /* JADX INFO: renamed from: a */
    public static final int f2905a = 0;

    /* JADX INFO: renamed from: b */
    public static final int f2906b = 1;

    /* JADX INFO: renamed from: c */
    public static final int f2907c = 2;

    /* JADX INFO: renamed from: d */
    public static final int f2908d = 3;

    /* JADX INFO: renamed from: e */
    private static final String f2909e = "Box";

    /* JADX INFO: renamed from: f */
    private final long f2910f;

    /* JADX INFO: renamed from: g */
    private boolean f2911g;

    private static native long nativeCreate(int i, int i2, int i3, int i4);

    private static native void nativeDestroy(long j);

    private static native boolean nativeGetGeometry(long j, int[] iArr);

    private static native int nativeGetHeight(long j);

    private static native int nativeGetWidth(long j);

    private static native int nativeGetX(long j);

    private static native int nativeGetY(long j);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    Box(long j) {
        this.f2911g = false;
        this.f2910f = j;
        this.f2911g = false;
    }

    private Box(int i, int i2, int i3, int i4) {
        this.f2911g = false;
        if (i < 0 || i2 < 0 || i3 < 0 || i4 < 0) {
            throw new IllegalArgumentException("All box dimensions must be non-negative");
        }
        long jNativeCreate = nativeCreate(i, i2, i3, i4);
        if (jNativeCreate == 0) {
            throw new OutOfMemoryError();
        }
        this.f2910f = jNativeCreate;
        this.f2911g = false;
    }

    /* JADX INFO: renamed from: a */
    public final long m2654a() {
        if (this.f2911g) {
            throw new IllegalStateException();
        }
        return this.f2910f;
    }

    /* JADX INFO: renamed from: b */
    private int m2647b() {
        if (this.f2911g) {
            throw new IllegalStateException();
        }
        return nativeGetX(this.f2910f);
    }

    /* JADX INFO: renamed from: c */
    private int m2648c() {
        if (this.f2911g) {
            throw new IllegalStateException();
        }
        return nativeGetY(this.f2910f);
    }

    /* JADX INFO: renamed from: d */
    private int m2649d() {
        if (this.f2911g) {
            throw new IllegalStateException();
        }
        return nativeGetWidth(this.f2910f);
    }

    /* JADX INFO: renamed from: e */
    private int m2650e() {
        if (this.f2911g) {
            throw new IllegalStateException();
        }
        return nativeGetHeight(this.f2910f);
    }

    /* JADX INFO: renamed from: g */
    private int[] m2652g() {
        int[] iArr = new int[4];
        if (this.f2911g) {
            throw new IllegalStateException();
        }
        if (nativeGetGeometry(this.f2910f, iArr)) {
            return iArr;
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    private boolean m2646a(int[] iArr) {
        if (this.f2911g) {
            throw new IllegalStateException();
        }
        return nativeGetGeometry(this.f2910f, iArr);
    }

    /* JADX INFO: renamed from: h */
    private void m2653h() {
        if (this.f2911g) {
            return;
        }
        nativeDestroy(this.f2910f);
        this.f2911g = true;
    }

    protected void finalize() throws Throwable {
        try {
            if (!this.f2911g) {
                Log.w(f2909e, "Box was not terminated using recycle()");
                if (!this.f2911g) {
                    nativeDestroy(this.f2910f);
                    this.f2911g = true;
                }
            }
        } finally {
            super.finalize();
        }
    }

    /* JADX INFO: renamed from: f */
    private Rect m2651f() {
        int[] iArr = new int[4];
        if (this.f2911g) {
            throw new IllegalStateException();
        }
        if (!nativeGetGeometry(this.f2910f, iArr)) {
            iArr = null;
        }
        int i = iArr[0];
        int i2 = iArr[1];
        return new Rect(i, i2, iArr[2] + i, iArr[3] + i2);
    }
}
