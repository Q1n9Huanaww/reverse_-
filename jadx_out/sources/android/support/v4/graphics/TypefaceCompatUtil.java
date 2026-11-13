package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class TypefaceCompatUtil {
    private static final String CACHE_FILE_PREFIX = ".font";
    private static final String TAG = "TypefaceCompatUtil";

    private TypefaceCompatUtil() {
    }

    @Nullable
    public static File getTempFile(Context context) {
        String str = CACHE_FILE_PREFIX + Process.myPid() + "-" + Process.myTid() + "-";
        for (int i = 0; i < 100; i++) {
            File file = new File(context.getCacheDir(), str + i);
            try {
                if (file.createNewFile()) {
                    return file;
                }
            } catch (IOException unused) {
            }
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0027 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:20:0x0032 A[Catch: IOException -> 0x0036, TryCatch #3 {IOException -> 0x0036, blocks: (B:3:0x0001, B:6:0x0018, B:16:0x0029, B:20:0x0032, B:19:0x002e, B:21:0x0035), top: B:27:0x0001, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:25:0x0029 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:30:? A[Catch: IOException -> 0x0036, SYNTHETIC, TRY_LEAVE, TryCatch #3 {IOException -> 0x0036, blocks: (B:3:0x0001, B:6:0x0018, B:16:0x0029, B:20:0x0032, B:19:0x002e, B:21:0x0035), top: B:27:0x0001, inners: #1 }] */
    @RequiresApi(19)
    @Nullable
    private static ByteBuffer mmap(File file) throws Throwable {
        Throwable th;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                FileChannel channel = fileInputStream.getChannel();
                MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0L, channel.size());
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return map;
            } catch (Throwable th2) {
                th = th2;
                th = null;
                if (fileInputStream != null) {
                    throw th;
                }
                if (th == null) {
                    fileInputStream.close();
                    throw th;
                }
                fileInputStream.close();
                throw th;
            }
        } catch (IOException unused) {
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0042 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:27:0x004d A[Catch: all -> 0x0051, Throwable -> 0x0054, TryCatch #1 {all -> 0x0051, blocks: (B:8:0x0013, B:11:0x002e, B:23:0x0044, B:27:0x004d, B:26:0x0049, B:28:0x0050), top: B:44:0x0013 }] */
    /* JADX WARN: Code duplicated, block: B:36:0x005c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:41:0x0067 A[Catch: IOException -> 0x006b, TryCatch #7 {IOException -> 0x006b, blocks: (B:3:0x0005, B:6:0x000f, B:13:0x0033, B:37:0x005e, B:41:0x0067, B:40:0x0063, B:42:0x006a), top: B:51:0x0005, inners: #4 }] */
    /* JADX WARN: Code duplicated, block: B:47:0x005e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:52:0x0044 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:56:? A[Catch: all -> 0x0051, Throwable -> 0x0054, SYNTHETIC, TRY_LEAVE, TryCatch #1 {all -> 0x0051, blocks: (B:8:0x0013, B:11:0x002e, B:23:0x0044, B:27:0x004d, B:26:0x0049, B:28:0x0050), top: B:44:0x0013 }] */
    /* JADX WARN: Code duplicated, block: B:59:? A[Catch: IOException -> 0x006b, SYNTHETIC, TRY_LEAVE, TryCatch #7 {IOException -> 0x006b, blocks: (B:3:0x0005, B:6:0x000f, B:13:0x0033, B:37:0x005e, B:41:0x0067, B:40:0x0063, B:42:0x006a), top: B:51:0x0005, inners: #4 }] */
    @RequiresApi(19)
    @Nullable
    public static ByteBuffer mmap(Context context, CancellationSignal cancellationSignal, Uri uri) throws Throwable {
        Throwable th;
        Throwable th2;
        Throwable th3;
        try {
            ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r", cancellationSignal);
            if (parcelFileDescriptorOpenFileDescriptor == null) {
                if (parcelFileDescriptorOpenFileDescriptor != null) {
                    parcelFileDescriptorOpenFileDescriptor.close();
                }
                return null;
            }
            try {
                try {
                    FileInputStream fileInputStream = new FileInputStream(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor());
                    try {
                        FileChannel channel = fileInputStream.getChannel();
                        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0L, channel.size());
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (parcelFileDescriptorOpenFileDescriptor != null) {
                            parcelFileDescriptorOpenFileDescriptor.close();
                        }
                        return map;
                    } catch (Throwable th4) {
                        th = th4;
                        th3 = null;
                        if (fileInputStream != null) {
                            throw th;
                        }
                        if (th3 == null) {
                            fileInputStream.close();
                            throw th;
                        }
                        fileInputStream.close();
                        throw th;
                    }
                } catch (Throwable th5) {
                    th2 = th5;
                    th = null;
                    if (parcelFileDescriptorOpenFileDescriptor != null) {
                        throw th2;
                    }
                    if (th == null) {
                        parcelFileDescriptorOpenFileDescriptor.close();
                        throw th2;
                    }
                    try {
                        parcelFileDescriptorOpenFileDescriptor.close();
                        throw th2;
                    } catch (Throwable th6) {
                        ThrowableExtension.addSuppressed(th, th6);
                        throw th2;
                    }
                }
            } catch (Throwable th7) {
                try {
                    throw th7;
                } catch (Throwable th8) {
                    th = th7;
                    th2 = th8;
                    if (parcelFileDescriptorOpenFileDescriptor != null) {
                        throw th2;
                    }
                    if (th == null) {
                        parcelFileDescriptorOpenFileDescriptor.close();
                        throw th2;
                    }
                    parcelFileDescriptorOpenFileDescriptor.close();
                    throw th2;
                }
            }
        } catch (IOException unused) {
            return null;
        }
    }

    @RequiresApi(19)
    @Nullable
    public static ByteBuffer copyToDirectBuffer(Context context, Resources resources, int i) {
        File tempFile = getTempFile(context);
        if (tempFile == null) {
            return null;
        }
        try {
            if (copyToFile(tempFile, resources, i)) {
                return mmap(tempFile);
            }
            return null;
        } finally {
            tempFile.delete();
        }
    }

    public static boolean copyToFile(File file, InputStream inputStream) throws Throwable {
        FileOutputStream fileOutputStream = null;
        try {
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(file, false);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i = inputStream.read(bArr);
                        if (i != -1) {
                            fileOutputStream2.write(bArr, 0, i);
                        } else {
                            closeQuietly(fileOutputStream2);
                            return true;
                        }
                    }
                } catch (IOException e) {
                    e = e;
                    fileOutputStream = fileOutputStream2;
                    Log.e(TAG, "Error copying resource contents to temp file: " + e.getMessage());
                    closeQuietly(fileOutputStream);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    closeQuietly(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e2) {
            e = e2;
        }
    }

    public static boolean copyToFile(File file, Resources resources, int i) throws Throwable {
        InputStream inputStreamOpenRawResource;
        try {
            inputStreamOpenRawResource = resources.openRawResource(i);
            try {
                boolean zCopyToFile = copyToFile(file, inputStreamOpenRawResource);
                closeQuietly(inputStreamOpenRawResource);
                return zCopyToFile;
            } catch (Throwable th) {
                th = th;
                closeQuietly(inputStreamOpenRawResource);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStreamOpenRawResource = null;
        }
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
