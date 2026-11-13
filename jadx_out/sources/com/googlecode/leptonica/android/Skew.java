package com.googlecode.leptonica.android;

/* JADX INFO: loaded from: classes.dex */
public class Skew {

    /* JADX INFO: renamed from: a */
    public static final float f2945a = 30.0f;

    /* JADX INFO: renamed from: b */
    public static final float f2946b = 5.0f;

    /* JADX INFO: renamed from: c */
    public static final int f2947c = 8;

    /* JADX INFO: renamed from: d */
    public static final int f2948d = 4;

    /* JADX INFO: renamed from: e */
    public static final float f2949e = 0.01f;

    private static native float nativeFindSkew(long j, float f, float f2, int i, int i2, float f3);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    /* JADX INFO: renamed from: b */
    private static float m2728b(Pix pix) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        return nativeFindSkew(pix.m2684a(), 30.0f, 5.0f, 8, 4, 0.01f);
    }

    /* JADX INFO: renamed from: a */
    private static float m2727a(Pix pix) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        return nativeFindSkew(pix.m2684a(), 30.0f, 5.0f, 8, 4, 0.01f);
    }
}
