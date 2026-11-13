package com.googlecode.leptonica.android;

import android.graphics.Rect;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class Boxa {

    /* JADX INFO: renamed from: a */
    private static final String f2912a = "Boxa";

    /* JADX INFO: renamed from: b */
    private final long f2913b;

    /* JADX INFO: renamed from: c */
    private boolean f2914c;

    private static native void nativeDestroy(long j);

    private static native int nativeGetCount(long j);

    private static native boolean nativeGetGeometry(long j, int i, int[] iArr);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    private Boxa(long j) {
        this.f2914c = false;
        this.f2913b = j;
        this.f2914c = false;
    }

    /* JADX INFO: renamed from: a */
    private long m2655a() {
        if (this.f2914c) {
            throw new IllegalStateException();
        }
        return this.f2913b;
    }

    /* JADX INFO: renamed from: b */
    private int m2658b() {
        if (this.f2914c) {
            throw new IllegalStateException();
        }
        return nativeGetCount(this.f2913b);
    }

    /* JADX INFO: renamed from: b */
    private int[] m2659b(int i) {
        if (this.f2914c) {
            throw new IllegalStateException();
        }
        int[] iArr = new int[4];
        if (this.f2914c) {
            throw new IllegalStateException();
        }
        if (nativeGetGeometry(this.f2913b, i, iArr)) {
            return iArr;
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    private boolean m2657a(int i, int[] iArr) {
        if (this.f2914c) {
            throw new IllegalStateException();
        }
        return nativeGetGeometry(this.f2913b, i, iArr);
    }

    /* JADX INFO: renamed from: c */
    private synchronized void m2660c() {
        if (!this.f2914c) {
            nativeDestroy(this.f2913b);
            this.f2914c = true;
        }
    }

    protected void finalize() throws Throwable {
        try {
            if (!this.f2914c) {
                Log.w(f2912a, "Boxa was not terminated using recycle()");
                m2660c();
            }
        } finally {
            super.finalize();
        }
    }

    /* JADX INFO: renamed from: a */
    private Rect m2656a(int i) {
        if (this.f2914c) {
            throw new IllegalStateException();
        }
        int[] iArr = new int[4];
        if (this.f2914c) {
            throw new IllegalStateException();
        }
        if (!nativeGetGeometry(this.f2913b, i, iArr)) {
            iArr = null;
        }
        int i2 = iArr[0];
        int i3 = iArr[1];
        return new Rect(i2, i3, iArr[2] + i2, iArr[3] + i3);
    }
}
