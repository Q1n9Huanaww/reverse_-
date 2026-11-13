package com.googlecode.leptonica.android;

/* JADX INFO: loaded from: classes.dex */
public class GrayQuant {
    private static native long nativePixThresholdToBinary(long j, int i);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    /* JADX INFO: renamed from: a */
    private static Pix m2666a(Pix pix, int i) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        int iM2688e = pix.m2688e();
        if (iM2688e != 4 && iM2688e != 8) {
            throw new IllegalArgumentException("Source pix depth must be 4 or 8 bpp");
        }
        if (iM2688e == 4 && i > 16) {
            throw new IllegalArgumentException("4 bpp thresh not in {0-16}");
        }
        if (iM2688e == 8 && i > 256) {
            throw new IllegalArgumentException("8 bpp thresh not in {0-256}");
        }
        long jNativePixThresholdToBinary = nativePixThresholdToBinary(pix.m2684a(), i);
        if (jNativePixThresholdToBinary == 0) {
            throw new RuntimeException("Failed to perform binarization");
        }
        return new Pix(jNativePixThresholdToBinary);
    }
}
