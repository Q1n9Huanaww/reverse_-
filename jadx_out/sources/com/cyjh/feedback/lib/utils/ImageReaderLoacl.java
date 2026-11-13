package com.cyjh.feedback.lib.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.format.Formatter;
import android.util.Base64;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.orhanobut.logger.Logger;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

/* JADX INFO: loaded from: classes.dex */
public class ImageReaderLoacl {
    private static final int RATIO = 2;

    public static Bitmap readBitmapFromPath(String str) {
        try {
            return BitmapFactory.decodeFile(str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String img2Base64(Context context, String str) throws Throwable {
        Bitmap bitmapDecodeFile;
        ByteArrayOutputStream byteArrayOutputStream;
        if (str == null || str.length() <= 0) {
            bitmapDecodeFile = null;
        } else {
            Logger.m3442e("图片大小:" + Formatter.formatFileSize(context, new File(str).length()), new Object[0]);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            options.inSampleSize = 2;
            options.inJustDecodeBounds = false;
            bitmapDecodeFile = BitmapFactory.decodeFile(str, options);
        }
        if (bitmapDecodeFile == null) {
            return null;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                int i = 100;
                bitmapDecodeFile.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                while (byteArrayOutputStream.toByteArray().length / 1024 > 65) {
                    byteArrayOutputStream.reset();
                    i -= 10;
                    bitmapDecodeFile.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
                }
                Logger.m3442e("图片的大小:" + (byteArrayOutputStream.toByteArray().length / 1024), new Object[0]);
                String strEncode = URLEncoder.encode(Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0), "UTF-8");
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e) {
                        ThrowableExtension.printStackTrace(e);
                    }
                }
                return strEncode;
            } catch (Exception unused) {
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e2) {
                        ThrowableExtension.printStackTrace(e2);
                    }
                }
                return null;
            } catch (Throwable th) {
                th = th;
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e3) {
                        ThrowableExtension.printStackTrace(e3);
                    }
                }
                throw th;
            }
        } catch (Exception unused2) {
            byteArrayOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream = null;
        }
    }

    public static Bitmap compressBmpFromBmp(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        int i = 100;
        while (byteArrayOutputStream.toByteArray().length / 1024 > 100) {
            byteArrayOutputStream.reset();
            i -= 10;
            bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
        }
        BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), null, null);
        return bitmap;
    }
}
