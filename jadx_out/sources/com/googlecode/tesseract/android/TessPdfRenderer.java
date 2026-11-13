package com.googlecode.tesseract.android;

/* JADX INFO: loaded from: classes.dex */
public class TessPdfRenderer {

    /* JADX INFO: renamed from: a */
    private final long f3019a;

    /* JADX INFO: renamed from: b */
    private boolean f3020b = false;

    private static native long nativeCreate(long j, String str);

    private static native void nativeRecycle(long j);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
        System.loadLibrary("tess");
    }

    private TessPdfRenderer(TessBaseAPI tessBaseAPI, String str) {
        this.f3019a = nativeCreate(tessBaseAPI.f2993a, str);
    }

    /* JADX INFO: renamed from: a */
    public final long m2785a() {
        if (this.f3020b) {
            throw new IllegalStateException();
        }
        return this.f3019a;
    }

    /* JADX INFO: renamed from: b */
    private void m2784b() {
        nativeRecycle(this.f3019a);
        this.f3020b = true;
    }
}
