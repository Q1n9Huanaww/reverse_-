package com.googlecode.leptonica.android;

/* JADX INFO: loaded from: classes.dex */
public class Rotate {

    /* JADX INFO: renamed from: a */
    public static final boolean f2939a = true;

    private static native long nativeRotate(long j, float f, boolean z, boolean z2);

    private static native int nativeRotateOrth(long j, int i);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    /* JADX INFO: renamed from: a */
    private static Pix m2719a(Pix pix, float f, boolean z) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        long jNativeRotate = nativeRotate(pix.m2684a(), f, z, true);
        if (jNativeRotate == 0) {
            return null;
        }
        return new Pix(jNativeRotate);
    }

    /* JADX INFO: renamed from: a */
    private static Pix m2720a(Pix pix, int i) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        if (i < 0 || i > 3) {
            throw new IllegalArgumentException("quads not in {0,1,2,3}");
        }
        int iNativeRotateOrth = nativeRotateOrth(pix.m2684a(), i);
        if (iNativeRotateOrth == 0) {
            return null;
        }
        return new Pix(iNativeRotateOrth);
    }

    /* JADX INFO: renamed from: a */
    private static Pix m2718a(Pix pix, float f) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        long jNativeRotate = nativeRotate(pix.m2684a(), f, false, true);
        if (jNativeRotate == 0) {
            return null;
        }
        return new Pix(jNativeRotate);
    }

    /* JADX INFO: renamed from: b */
    private static Pix m2721b(Pix pix, float f) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        long jNativeRotate = nativeRotate(pix.m2684a(), f, false, true);
        if (jNativeRotate == 0) {
            return null;
        }
        return new Pix(jNativeRotate);
    }
}
