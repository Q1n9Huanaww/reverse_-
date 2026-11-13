package com.googlecode.leptonica.android;

/* JADX INFO: loaded from: classes.dex */
public class Enhance {

    /* JADX INFO: renamed from: a */
    public static final int f2918a = 1;

    /* JADX INFO: renamed from: b */
    public static final float f2919b = 0.3f;

    private static native long nativeUnsharpMasking(long j, int i, float f);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    /* JADX INFO: renamed from: b */
    private static Pix m2665b(Pix pix) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        long jNativeUnsharpMasking = nativeUnsharpMasking(pix.m2684a(), 1, 0.3f);
        if (jNativeUnsharpMasking == 0) {
            throw new OutOfMemoryError();
        }
        return new Pix(jNativeUnsharpMasking);
    }

    /* JADX INFO: renamed from: a */
    private static Pix m2664a(Pix pix) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        long jNativeUnsharpMasking = nativeUnsharpMasking(pix.m2684a(), 1, 0.3f);
        if (jNativeUnsharpMasking == 0) {
            throw new OutOfMemoryError();
        }
        return new Pix(jNativeUnsharpMasking);
    }
}
