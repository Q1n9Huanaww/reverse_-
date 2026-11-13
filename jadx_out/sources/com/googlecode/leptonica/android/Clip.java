package com.googlecode.leptonica.android;

/* JADX INFO: loaded from: classes.dex */
public class Clip {
    private static native int nativeClipRectangle(long j, long j2);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    /* JADX INFO: renamed from: a */
    private static Pix m2661a(Pix pix, Box box) {
        int iNativeClipRectangle = nativeClipRectangle(pix.m2684a(), box.m2654a());
        if (iNativeClipRectangle != 0) {
            return new Pix(iNativeClipRectangle);
        }
        return null;
    }
}
