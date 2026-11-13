package com.androidquery.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AlphaAnimation;
import com.androidquery.AQuery;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.commons.p092io.IOUtils;

/* JADX INFO: loaded from: classes.dex */
public class AQUtility {
    private static final int IO_BUFFER_SIZE = 4096;
    public static boolean TEST_IO_EXCEPTION;
    private static File cacheDir;
    private static Context context;
    private static boolean debug;

    /* JADX INFO: renamed from: eh */
    private static Thread.UncaughtExceptionHandler f64eh;
    private static Handler handler;
    private static final byte[] map2;
    private static File pcacheDir;
    private static ScheduledExecutorService storeExe;
    private static Object wait;
    private static Map<String, Long> times = new HashMap();
    private static final char[] map1 = new char[64];

    public static void setDebug(boolean z) {
        debug = z;
    }

    public static boolean isDebug() {
        return debug;
    }

    public static void debugWait(long j) {
        if (debug) {
            if (wait == null) {
                wait = new Object();
            }
            synchronized (wait) {
                try {
                    wait.wait(j);
                } catch (InterruptedException e) {
                    ThrowableExtension.printStackTrace(e);
                }
            }
        }
    }

    public static void debugNotify() {
        if (!debug || wait == null) {
            return;
        }
        synchronized (wait) {
            wait.notifyAll();
        }
    }

    public static void debug(Object obj) {
        if (debug) {
            StringBuilder sb = new StringBuilder();
            sb.append(obj);
            Log.w("AQuery", sb.toString());
        }
    }

    public static void warn(Object obj, Object obj2) {
        Log.w("AQuery", obj + ":" + obj2);
    }

    public static void debug(Object obj, Object obj2) {
        if (debug) {
            Log.w("AQuery", obj + ":" + obj2);
        }
    }

    public static void debug(Throwable th) {
        if (debug) {
            Log.w("AQuery", Log.getStackTraceString(th));
        }
    }

    public static void report(Throwable th) {
        if (th == null) {
            return;
        }
        try {
            warn("reporting", Log.getStackTraceString(th));
            if (f64eh != null) {
                f64eh.uncaughtException(Thread.currentThread(), th);
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public static void setExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        f64eh = uncaughtExceptionHandler;
    }

    static {
        char c = 'A';
        int i = 0;
        while (c <= 'Z') {
            map1[i] = c;
            c = (char) (c + 1);
            i++;
        }
        char c2 = 'a';
        while (c2 <= 'z') {
            map1[i] = c2;
            c2 = (char) (c2 + 1);
            i++;
        }
        char c3 = '0';
        while (c3 <= '9') {
            map1[i] = c3;
            c3 = (char) (c3 + 1);
            i++;
        }
        map1[i] = '+';
        map1[i + 1] = IOUtils.DIR_SEPARATOR_UNIX;
        map2 = new byte[128];
        for (int i2 = 0; i2 < map2.length; i2++) {
            map2[i2] = -1;
        }
        for (int i3 = 0; i3 < 64; i3++) {
            map2[map1[i3]] = (byte) i3;
        }
    }

    public static void time(String str) {
        times.put(str, Long.valueOf(System.currentTimeMillis()));
    }

    public static long timeEnd(String str, long j) {
        Long l = times.get(str);
        if (l == null) {
            return 0L;
        }
        long jCurrentTimeMillis = System.currentTimeMillis() - l.longValue();
        if (j == 0 || jCurrentTimeMillis > j) {
            debug(str, Long.valueOf(jCurrentTimeMillis));
        }
        return jCurrentTimeMillis;
    }

    public static Object invokeHandler(Object obj, String str, boolean z, boolean z2, Class<?>[] clsArr, Object... objArr) {
        return invokeHandler(obj, str, z, z2, clsArr, null, objArr);
    }

    public static Object invokeHandler(Object obj, String str, boolean z, boolean z2, Class<?>[] clsArr, Class<?>[] clsArr2, Object... objArr) {
        try {
            return invokeMethod(obj, str, z, clsArr, clsArr2, objArr);
        } catch (Exception e) {
            if (z2) {
                report(e);
                return null;
            }
            debug((Throwable) e);
            return null;
        }
    }

    private static Object invokeMethod(Object obj, String str, boolean z, Class<?>[] clsArr, Class<?>[] clsArr2, Object... objArr) throws Exception {
        if (obj == null || str == null) {
            return null;
        }
        if (clsArr == null) {
            try {
                clsArr = new Class[0];
            } catch (NoSuchMethodException unused) {
                if (z) {
                    try {
                        if (clsArr2 == null) {
                            return obj.getClass().getMethod(str, new Class[0]).invoke(obj, new Object[0]);
                        }
                        return obj.getClass().getMethod(str, clsArr2).invoke(obj, objArr);
                    } catch (NoSuchMethodException unused2) {
                        return null;
                    }
                }
                return null;
            }
        }
        return obj.getClass().getMethod(str, clsArr).invoke(obj, objArr);
    }

    public static void transparent(View view, boolean z) {
        setAlpha(view, z ? 0.5f : 1.0f);
    }

    private static void setAlpha(View view, float f) {
        if (f == 1.0f) {
            view.clearAnimation();
            return;
        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(f, f);
        alphaAnimation.setDuration(0L);
        alphaAnimation.setFillAfter(true);
        view.startAnimation(alphaAnimation);
    }

    public static void ensureUIThread() {
        if (isUIThread()) {
            return;
        }
        report(new IllegalStateException("Not UI Thread"));
    }

    public static boolean isUIThread() {
        return Looper.getMainLooper().getThread().getId() == Thread.currentThread().getId();
    }

    public static Handler getHandler() {
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        return handler;
    }

    public static void post(Runnable runnable) {
        getHandler().post(runnable);
    }

    public static void post(Object obj, String str) {
        post(obj, str, new Class[0], new Object[0]);
    }

    public static void post(final Object obj, final String str, final Class<?>[] clsArr, final Object... objArr) {
        post(new Runnable() { // from class: com.androidquery.util.AQUtility.1
            @Override // java.lang.Runnable
            public void run() {
                AQUtility.invokeHandler(obj, str, false, true, clsArr, objArr);
            }
        });
    }

    public static void postAsync(final Runnable runnable) {
        new AsyncTask<Void, Void, String>() { // from class: com.androidquery.util.AQUtility.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public String doInBackground(Void... voidArr) {
                try {
                    runnable.run();
                    return null;
                } catch (Exception e) {
                    AQUtility.report(e);
                    return null;
                }
            }
        }.execute(new Void[0]);
    }

    public static void postAsync(Object obj, String str) {
        postAsync(obj, str, new Class[0], new Object[0]);
    }

    public static void postAsync(final Object obj, final String str, final Class<?>[] clsArr, final Object... objArr) {
        postAsync(new Runnable() { // from class: com.androidquery.util.AQUtility.3
            @Override // java.lang.Runnable
            public void run() {
                AQUtility.invokeHandler(obj, str, false, true, clsArr, objArr);
            }
        });
    }

    public static void removePost(Runnable runnable) {
        getHandler().removeCallbacks(runnable);
    }

    public static void postDelayed(Runnable runnable, long j) {
        getHandler().postDelayed(runnable, j);
    }

    public static void apply(SharedPreferences.Editor editor) {
        if (AQuery.SDK_INT >= 9) {
            invokeHandler(editor, "apply", false, true, null, null);
        } else {
            editor.commit();
        }
    }

    private static String getMD5Hex(String str) {
        return new BigInteger(getMD5(str.getBytes())).abs().toString(36);
    }

    private static byte[] getMD5(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            report(e);
            return null;
        }
    }

    public static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        copy(inputStream, outputStream, 0, null);
    }

    public static void copy(InputStream inputStream, OutputStream outputStream, int i, Progress progress) throws IOException {
        if (progress != null) {
            progress.reset();
            progress.setBytes(i);
        }
        byte[] bArr = new byte[4096];
        int i2 = 0;
        while (true) {
            int i3 = inputStream.read(bArr);
            if (i3 == -1) {
                if (progress != null) {
                    progress.done();
                    return;
                }
                return;
            }
            outputStream.write(bArr, 0, i3);
            i2++;
            if (TEST_IO_EXCEPTION && i2 > 2) {
                debug("simulating internet error");
                throw new IOException();
            }
            if (progress != null) {
                progress.increment(i3);
            }
        }
    }

    public static byte[] toBytes(InputStream inputStream) {
        byte[] byteArray;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            copy(inputStream, byteArrayOutputStream);
            byteArray = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            report(e);
            byteArray = null;
        }
        close(inputStream);
        return byteArray;
    }

    public static void write(File file, byte[] bArr) {
        try {
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (Exception e) {
                    debug("file create fail", file);
                    report(e);
                }
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bArr);
            fileOutputStream.close();
        } catch (Exception e2) {
            report(e2);
        }
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }

    private static ScheduledExecutorService getFileStoreExecutor() {
        if (storeExe == null) {
            storeExe = Executors.newSingleThreadScheduledExecutor();
        }
        return storeExe;
    }

    public static void storeAsync(File file, byte[] bArr, long j) {
        getFileStoreExecutor().schedule(new Common().method(1, file, bArr), j, TimeUnit.MILLISECONDS);
    }

    public static File getCacheDir(Context context2, int i) {
        if (i == 1) {
            if (pcacheDir != null) {
                return pcacheDir;
            }
            pcacheDir = new File(getCacheDir(context2), "persistent");
            pcacheDir.mkdirs();
            return pcacheDir;
        }
        return getCacheDir(context2);
    }

    public static File getCacheDir(Context context2) {
        if (cacheDir == null) {
            cacheDir = new File(context2.getCacheDir(), "aquery");
            cacheDir.mkdirs();
        }
        return cacheDir;
    }

    public static void setCacheDir(File file) {
        cacheDir = file;
        if (cacheDir != null) {
            cacheDir.mkdirs();
        }
    }

    private static File makeCacheFile(File file, String str) {
        return new File(file, str);
    }

    private static String getCacheFileName(String str) {
        return getMD5Hex(str);
    }

    public static File getCacheFile(File file, String str) {
        if (str == null) {
            return null;
        }
        if (str.startsWith(File.separator)) {
            return new File(str);
        }
        return makeCacheFile(file, getCacheFileName(str));
    }

    public static File getExistedCacheByUrl(File file, String str) {
        File cacheFile = getCacheFile(file, str);
        if (cacheFile == null || !cacheFile.exists() || cacheFile.length() == 0) {
            return null;
        }
        return cacheFile;
    }

    public static File getExistedCacheByUrlSetAccess(File file, String str) {
        File existedCacheByUrl = getExistedCacheByUrl(file, str);
        if (existedCacheByUrl != null) {
            lastAccess(existedCacheByUrl);
        }
        return existedCacheByUrl;
    }

    private static void lastAccess(File file) {
        file.setLastModified(System.currentTimeMillis());
    }

    public static void store(File file, byte[] bArr) {
        if (file != null) {
            try {
                write(file, bArr);
            } catch (Exception e) {
                report(e);
            }
        }
    }

    public static void cleanCacheAsync(Context context2) {
        cleanCacheAsync(context2, 3000000L, 2000000L);
    }

    public static void cleanCacheAsync(Context context2, long j, long j2) {
        try {
            getFileStoreExecutor().schedule(new Common().method(2, getCacheDir(context2), Long.valueOf(j), Long.valueOf(j2)), 0L, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            report(e);
        }
    }

    public static void cleanCache(File file, long j, long j2) {
        try {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null) {
                return;
            }
            Arrays.sort(fileArrListFiles, new Common());
            if (testCleanNeeded(fileArrListFiles, j)) {
                cleanCache(fileArrListFiles, j2);
            }
            File tempDir = getTempDir();
            if (tempDir == null || !tempDir.exists()) {
                return;
            }
            cleanCache(tempDir.listFiles(), 0L);
        } catch (Exception e) {
            report(e);
        }
    }

    public static File getTempDir() {
        File file = new File(Environment.getExternalStorageDirectory(), "aquery/temp");
        file.mkdirs();
        if (file.exists() && file.canWrite()) {
            return file;
        }
        return null;
    }

    private static boolean testCleanNeeded(File[] fileArr, long j) {
        int length = fileArr.length;
        long j2 = 0;
        int i = 0;
        while (i < length) {
            long length2 = j2 + fileArr[i].length();
            if (length2 > j) {
                return true;
            }
            i++;
            j2 = length2;
        }
        return false;
    }

    private static void cleanCache(File[] fileArr, long j) {
        long j2 = 0;
        int i = 0;
        for (File file : fileArr) {
            if (file.isFile()) {
                long length = j2 + file.length();
                if (length >= j) {
                    file.delete();
                    i++;
                }
                j2 = length;
            }
        }
        debug("deleted", Integer.valueOf(i));
    }

    public static int dip2pixel(Context context2, float f) {
        return (int) TypedValue.applyDimension(1, f, context2.getResources().getDisplayMetrics());
    }

    public static float pixel2dip(Context context2, float f) {
        return f / (context2.getResources().getDisplayMetrics().densityDpi / 160.0f);
    }

    public static void setContext(Application application) {
        context = application.getApplicationContext();
    }

    public static Context getContext() {
        if (context == null) {
            warn("warn", "getContext with null");
            debug((Throwable) new IllegalStateException());
        }
        return context;
    }

    public static char[] encode64(byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7 = ((i2 * 4) + 2) / 3;
        char[] cArr = new char[((i2 + 2) / 3) * 4];
        int i8 = i2 + i;
        int i9 = 0;
        while (i < i8) {
            int i10 = i + 1;
            int i11 = bArr[i] & 255;
            if (i10 < i8) {
                i3 = i10 + 1;
                i4 = bArr[i10] & 255;
            } else {
                i3 = i10;
                i4 = 0;
            }
            if (i3 < i8) {
                i5 = i3 + 1;
                i6 = bArr[i3] & 255;
            } else {
                i5 = i3;
                i6 = 0;
            }
            int i12 = i11 >>> 2;
            int i13 = ((i11 & 3) << 4) | (i4 >>> 4);
            int i14 = ((i4 & 15) << 2) | (i6 >>> 6);
            int i15 = i6 & 63;
            int i16 = i9 + 1;
            cArr[i9] = map1[i12];
            int i17 = i16 + 1;
            cArr[i16] = map1[i13];
            char c = '=';
            cArr[i17] = i17 < i7 ? map1[i14] : '=';
            int i18 = i17 + 1;
            if (i18 < i7) {
                c = map1[i15];
            }
            cArr[i18] = c;
            i9 = i18 + 1;
            i = i5;
        }
        return cArr;
    }
}
