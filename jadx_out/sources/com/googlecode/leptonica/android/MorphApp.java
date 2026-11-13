package com.googlecode.leptonica.android;

/* JADX INFO: loaded from: classes.dex */
public class MorphApp {

    /* JADX INFO: renamed from: a */
    public static final int f2922a = 0;

    /* JADX INFO: renamed from: b */
    public static final int f2923b = 1;

    /* JADX INFO: renamed from: c */
    public static final int f2924c = 7;

    /* JADX INFO: renamed from: d */
    public static final int f2925d = 7;

    private static native long nativePixFastTophat(long j, int i, int i2, int i3);

    private static native long nativePixTophat(long j, int i, int i2, int i3);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    /* JADX INFO: renamed from: a */
    private static Pix m2671a(Pix pix, int i, int i2, int i3) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        if (pix.m2688e() != 8) {
            throw new IllegalArgumentException("Source pix depth must be 8bpp");
        }
        if (i <= 0 || i2 <= 0) {
            throw new IllegalArgumentException("hsize or vsize < 1");
        }
        if (i3 < 0 || i3 > 1) {
            throw new IllegalArgumentException("Type must be L_TOPHAT_BLACK or L_TOPHAT_WHITE");
        }
        long jNativePixTophat = nativePixTophat(pix.m2684a(), i, i2, i3);
        if (jNativePixTophat == 0) {
            throw new RuntimeException("Failed to perform Tophat on image");
        }
        return new Pix(jNativePixTophat);
    }

    /* JADX INFO: renamed from: a */
    private static Pix m2669a(Pix pix) {
        return m2670a(pix, 1);
    }

    /* JADX INFO: renamed from: b */
    private static Pix m2672b(Pix pix) {
        return m2670a(pix, 0);
    }

    /* JADX INFO: renamed from: a */
    private static Pix m2670a(Pix pix, int i) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        if (pix.m2688e() != 8) {
            throw new IllegalArgumentException("Source pix depth must be 8bpp");
        }
        if (i < 0 || i > 1) {
            throw new IllegalArgumentException("Type must be L_TOPHAT_BLACK or L_TOPHAT_WHITE");
        }
        long jNativePixFastTophat = nativePixFastTophat(pix.m2684a(), 7, 7, i);
        if (jNativePixFastTophat == 0) {
            throw new RuntimeException("Failed to perform pixFastTophat on image");
        }
        return new Pix(jNativePixFastTophat);
    }
}
