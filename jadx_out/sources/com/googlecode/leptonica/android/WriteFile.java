package com.googlecode.leptonica.android;

import android.graphics.Bitmap;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class WriteFile {
    private static native boolean nativeWriteBitmap(long j, Bitmap bitmap);

    private static native int nativeWriteBytes8(long j, byte[] bArr);

    private static native boolean nativeWriteImpliedFormat(long j, String str);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    /* JADX INFO: renamed from: b */
    private static byte[] m2732b(Pix pix) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        byte[] bArr = new byte[pix.m2686c() * pix.m2687d()];
        if (pix.m2688e() != 8) {
            Pix pixM2662a = Convert.m2662a(pix);
            m2729a(pixM2662a, bArr);
            pixM2662a.m2685b();
        } else {
            m2729a(pix, bArr);
        }
        return bArr;
    }

    /* JADX INFO: renamed from: a */
    private static int m2729a(Pix pix, byte[] bArr) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        if (bArr.length < pix.m2686c() * pix.m2687d()) {
            throw new IllegalArgumentException("Data array must be large enough to hold image bytes");
        }
        return nativeWriteBytes8(pix.m2684a(), bArr);
    }

    /* JADX INFO: renamed from: a */
    private static boolean m2731a(Pix pix, File file) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        if (file == null) {
            throw new IllegalArgumentException("File must be non-null");
        }
        return nativeWriteImpliedFormat(pix.m2684a(), file.getAbsolutePath());
    }

    /* JADX INFO: renamed from: a */
    public static Bitmap m2730a(Pix pix) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        if (pix.f2930e) {
            throw new IllegalStateException();
        }
        int[] iArr = new int[3];
        if (pix.f2930e) {
            throw new IllegalStateException();
        }
        if (!Pix.nativeGetDimensions(pix.f2929d, iArr)) {
            iArr = null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iArr[0], iArr[1], Bitmap.Config.ARGB_8888);
        if (nativeWriteBitmap(pix.m2684a(), bitmapCreateBitmap)) {
            return bitmapCreateBitmap;
        }
        bitmapCreateBitmap.recycle();
        return null;
    }
}
