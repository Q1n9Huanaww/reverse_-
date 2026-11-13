package com.googlecode.leptonica.android;

/* JADX INFO: loaded from: classes.dex */
public class Convert {
    private static native long nativeConvertTo8(long j);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    /* JADX INFO: renamed from: a */
    public static Pix m2662a(Pix pix) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        long jNativeConvertTo8 = nativeConvertTo8(pix.m2684a());
        if (jNativeConvertTo8 == 0) {
            throw new RuntimeException("Failed to natively convert pix");
        }
        return new Pix(jNativeConvertTo8);
    }
}
