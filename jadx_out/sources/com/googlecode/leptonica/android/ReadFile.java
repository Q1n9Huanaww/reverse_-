package com.googlecode.leptonica.android;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class ReadFile {

    /* JADX INFO: renamed from: a */
    private static final String f2938a = "ReadFile";

    private static native long nativeReadBitmap(Bitmap bitmap);

    private static native long nativeReadBytes8(byte[] bArr, int i, int i2);

    private static native long nativeReadFile(String str);

    private static native long nativeReadMem(byte[] bArr, int i);

    private static native boolean nativeReplaceBytes8(long j, byte[] bArr, int i, int i2);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    /* JADX INFO: renamed from: a */
    private static Pix m2715a(byte[] bArr) {
        if (bArr == null) {
            Log.e(f2938a, "Image data byte array must be non-null");
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        Pix pixM2713a = m2713a(bitmapDecodeByteArray);
        bitmapDecodeByteArray.recycle();
        return pixM2713a;
    }

    /* JADX INFO: renamed from: a */
    private static Pix m2716a(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("Byte array must be non-null");
        }
        if (i <= 0) {
            throw new IllegalArgumentException("Image width must be greater than 0");
        }
        if (i2 <= 0) {
            throw new IllegalArgumentException("Image height must be greater than 0");
        }
        if (bArr.length < i * i2) {
            throw new IllegalArgumentException("Array length does not match dimensions");
        }
        long jNativeReadBytes8 = nativeReadBytes8(bArr, i, i2);
        if (jNativeReadBytes8 == 0) {
            throw new RuntimeException("Failed to read pix from memory");
        }
        return new Pix(jNativeReadBytes8);
    }

    /* JADX INFO: renamed from: a */
    private static boolean m2717a(Pix pix, byte[] bArr, int i, int i2) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
        if (bArr == null) {
            throw new IllegalArgumentException("Byte array must be non-null");
        }
        if (i <= 0) {
            throw new IllegalArgumentException("Image width must be greater than 0");
        }
        if (i2 <= 0) {
            throw new IllegalArgumentException("Image height must be greater than 0");
        }
        if (bArr.length < i * i2) {
            throw new IllegalArgumentException("Array length does not match dimensions");
        }
        if (pix.m2686c() != i) {
            throw new IllegalArgumentException("Source pix width does not match image width");
        }
        if (pix.m2687d() != i2) {
            throw new IllegalArgumentException("Source pix height does not match image height");
        }
        return nativeReplaceBytes8(pix.m2684a(), bArr, i, i2);
    }

    /* JADX INFO: renamed from: a */
    public static Pix m2714a(File file) {
        if (file == null) {
            Log.e(f2938a, "File must be non-null");
            return null;
        }
        if (!file.exists()) {
            Log.e(f2938a, "File does not exist");
            return null;
        }
        if (!file.canRead()) {
            Log.e(f2938a, "Cannot read file");
            return null;
        }
        long jNativeReadFile = nativeReadFile(file.getAbsolutePath());
        if (jNativeReadFile != 0) {
            return new Pix(jNativeReadFile);
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        if (bitmapDecodeFile == null) {
            Log.e(f2938a, "Cannot decode bitmap");
            return null;
        }
        Pix pixM2713a = m2713a(bitmapDecodeFile);
        bitmapDecodeFile.recycle();
        return pixM2713a;
    }

    /* JADX INFO: renamed from: a */
    public static Pix m2713a(Bitmap bitmap) {
        if (bitmap == null) {
            Log.e(f2938a, "Bitmap must be non-null");
            return null;
        }
        if (bitmap.getConfig() != Bitmap.Config.ARGB_8888) {
            Log.e(f2938a, "Bitmap config must be ARGB_8888");
            return null;
        }
        long jNativeReadBitmap = nativeReadBitmap(bitmap);
        if (jNativeReadBitmap == 0) {
            Log.e(f2938a, "Failed to read pix from bitmap");
            return null;
        }
        return new Pix(jNativeReadBitmap);
    }
}
