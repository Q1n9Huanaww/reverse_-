package com.googlecode.leptonica.android;

/* JADX INFO: loaded from: classes.dex */
public class Binarize {

    /* JADX INFO: renamed from: a */
    public static final int f2896a = 32;

    /* JADX INFO: renamed from: b */
    public static final int f2897b = 32;

    /* JADX INFO: renamed from: c */
    public static final int f2898c = 2;

    /* JADX INFO: renamed from: d */
    public static final int f2899d = 2;

    /* JADX INFO: renamed from: e */
    public static final float f2900e = 0.1f;

    /* JADX INFO: renamed from: f */
    public static final int f2901f = 8;

    /* JADX INFO: renamed from: g */
    public static final float f2902g = 0.35f;

    /* JADX INFO: renamed from: h */
    public static final int f2903h = 1;

    /* JADX INFO: renamed from: i */
    public static final int f2904i = 1;

    private static native long nativeOtsuAdaptiveThreshold(long j, int i, int i2, int i3, int i4, float f);

    private static native long nativeSauvolaBinarizeTiled(long j, int i, float f, int i2, int i3);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    /* JADX INFO: renamed from: b */
    private static Pix m2643b(Pix pix) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        if (pix.m2688e() != 8) {
            throw new IllegalArgumentException("Source pix depth must be 8bpp");
        }
        long jNativeOtsuAdaptiveThreshold = nativeOtsuAdaptiveThreshold(pix.m2684a(), 32, 32, 2, 2, 0.1f);
        if (jNativeOtsuAdaptiveThreshold == 0) {
            throw new RuntimeException("Failed to perform Otsu adaptive threshold on image");
        }
        return new Pix(jNativeOtsuAdaptiveThreshold);
    }

    /* JADX INFO: renamed from: d */
    private static Pix m2645d(Pix pix) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        if (pix.m2688e() != 8) {
            throw new IllegalArgumentException("Source pix depth must be 8bpp");
        }
        long jNativeSauvolaBinarizeTiled = nativeSauvolaBinarizeTiled(pix.m2684a(), 8, 0.35f, 1, 1);
        if (jNativeSauvolaBinarizeTiled == 0) {
            throw new RuntimeException("Failed to perform Sauvola binarization on image");
        }
        return new Pix(jNativeSauvolaBinarizeTiled);
    }

    /* JADX INFO: renamed from: a */
    private static Pix m2642a(Pix pix) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        if (pix.m2688e() != 8) {
            throw new IllegalArgumentException("Source pix depth must be 8bpp");
        }
        long jNativeOtsuAdaptiveThreshold = nativeOtsuAdaptiveThreshold(pix.m2684a(), 32, 32, 2, 2, 0.1f);
        if (jNativeOtsuAdaptiveThreshold == 0) {
            throw new RuntimeException("Failed to perform Otsu adaptive threshold on image");
        }
        return new Pix(jNativeOtsuAdaptiveThreshold);
    }

    /* JADX INFO: renamed from: c */
    private static Pix m2644c(Pix pix) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        if (pix.m2688e() != 8) {
            throw new IllegalArgumentException("Source pix depth must be 8bpp");
        }
        long jNativeSauvolaBinarizeTiled = nativeSauvolaBinarizeTiled(pix.m2684a(), 8, 0.35f, 1, 1);
        if (jNativeSauvolaBinarizeTiled == 0) {
            throw new RuntimeException("Failed to perform Sauvola binarization on image");
        }
        return new Pix(jNativeSauvolaBinarizeTiled);
    }
}
