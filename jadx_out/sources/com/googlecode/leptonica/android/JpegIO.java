package com.googlecode.leptonica.android;

import android.graphics.Bitmap;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class JpegIO {

    /* JADX INFO: renamed from: a */
    public static final int f2920a = 85;

    /* JADX INFO: renamed from: b */
    public static final boolean f2921b = false;

    private static native byte[] nativeCompressToJpeg(long j, int i, boolean z);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    /* JADX INFO: renamed from: a */
    private static byte[] m2667a(Pix pix) {
        return m2668b(pix);
    }

    /* JADX INFO: renamed from: b */
    private static byte[] m2668b(Pix pix) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Bitmap bitmapM2730a = WriteFile.m2730a(pix);
        bitmapM2730a.compress(Bitmap.CompressFormat.JPEG, 85, byteArrayOutputStream);
        bitmapM2730a.recycle();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        try {
            byteArrayOutputStream.close();
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
        }
        return byteArray;
    }
}
