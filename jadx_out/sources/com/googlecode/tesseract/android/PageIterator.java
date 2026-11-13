package com.googlecode.tesseract.android;

import android.graphics.Rect;

/* JADX INFO: loaded from: classes.dex */
public class PageIterator {

    /* JADX INFO: renamed from: a */
    private final long f2982a;

    private static native void nativeBegin(long j);

    private static native int[] nativeBoundingBox(long j, int i);

    private static native boolean nativeNext(long j, int i);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
        System.loadLibrary("tess");
    }

    PageIterator(long j) {
        this.f2982a = j;
    }

    /* JADX INFO: renamed from: a */
    private void m2733a() {
        nativeBegin(this.f2982a);
    }

    /* JADX INFO: renamed from: a */
    private boolean m2734a(int i) {
        return nativeNext(this.f2982a, i);
    }

    /* JADX INFO: renamed from: b */
    private int[] m2735b(int i) {
        return nativeBoundingBox(this.f2982a, i);
    }

    /* JADX INFO: renamed from: c */
    private Rect m2736c(int i) {
        int[] iArrNativeBoundingBox = nativeBoundingBox(this.f2982a, i);
        return new Rect(iArrNativeBoundingBox[0], iArrNativeBoundingBox[1], iArrNativeBoundingBox[2], iArrNativeBoundingBox[3]);
    }
}
