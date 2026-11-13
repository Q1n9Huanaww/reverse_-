package com.googlecode.leptonica.android;

/* JADX INFO: loaded from: classes.dex */
public class Edge {

    /* JADX INFO: renamed from: a */
    public static final int f2915a = 0;

    /* JADX INFO: renamed from: b */
    public static final int f2916b = 1;

    /* JADX INFO: renamed from: c */
    public static final int f2917c = 2;

    private static native long nativePixSobelEdgeFilter(long j, int i);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    /* JADX INFO: renamed from: a */
    private static Pix m2663a(Pix pix, int i) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        if (pix.m2688e() != 8) {
            throw new IllegalArgumentException("Source pix depth must be 8bpp");
        }
        if (i < 0 || i > 2) {
            throw new IllegalArgumentException("Invalid orientation flag");
        }
        long jNativePixSobelEdgeFilter = nativePixSobelEdgeFilter(pix.m2684a(), i);
        if (jNativePixSobelEdgeFilter == 0) {
            throw new RuntimeException("Failed to perform Sobel edge filter on image");
        }
        return new Pix(jNativePixSobelEdgeFilter);
    }
}
