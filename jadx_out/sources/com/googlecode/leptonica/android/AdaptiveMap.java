package com.googlecode.leptonica.android;

/* JADX INFO: loaded from: classes.dex */
public class AdaptiveMap {

    /* JADX INFO: renamed from: a */
    public static final int f2888a = 10;

    /* JADX INFO: renamed from: b */
    public static final int f2889b = 15;

    /* JADX INFO: renamed from: c */
    public static final int f2890c = 40;

    /* JADX INFO: renamed from: d */
    public static final int f2891d = 2;

    /* JADX INFO: renamed from: e */
    public static final int f2892e = 1;

    /* JADX INFO: renamed from: f */
    private static final int f2893f = 16;

    /* JADX INFO: renamed from: g */
    private static final int f2894g = 3;

    /* JADX INFO: renamed from: h */
    private static final int f2895h = 200;

    private static native long nativeBackgroundNormMorph(long j, int i, int i2, int i3);

    private static native long nativePixContrastNorm(long j, int i, int i2, int i3, int i4, int i5);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    /* JADX INFO: renamed from: b */
    private static Pix m2639b(Pix pix) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        long jNativeBackgroundNormMorph = nativeBackgroundNormMorph(pix.m2684a(), 16, 3, 200);
        if (jNativeBackgroundNormMorph == 0) {
            throw new RuntimeException("Failed to normalize image background");
        }
        return new Pix(jNativeBackgroundNormMorph);
    }

    /* JADX INFO: renamed from: d */
    private static Pix m2641d(Pix pix) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        long jNativePixContrastNorm = nativePixContrastNorm(pix.m2684a(), 10, 15, 40, 2, 1);
        if (jNativePixContrastNorm == 0) {
            throw new RuntimeException("Failed to normalize image contrast");
        }
        return new Pix(jNativePixContrastNorm);
    }

    /* JADX INFO: renamed from: a */
    private static Pix m2638a(Pix pix) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        long jNativeBackgroundNormMorph = nativeBackgroundNormMorph(pix.m2684a(), 16, 3, 200);
        if (jNativeBackgroundNormMorph == 0) {
            throw new RuntimeException("Failed to normalize image background");
        }
        return new Pix(jNativeBackgroundNormMorph);
    }

    /* JADX INFO: renamed from: c */
    private static Pix m2640c(Pix pix) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        long jNativePixContrastNorm = nativePixContrastNorm(pix.m2684a(), 10, 15, 40, 2, 1);
        if (jNativePixContrastNorm == 0) {
            throw new RuntimeException("Failed to normalize image contrast");
        }
        return new Pix(jNativePixContrastNorm);
    }
}
