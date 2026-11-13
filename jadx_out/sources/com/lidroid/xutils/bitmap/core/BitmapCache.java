package com.lidroid.xutils.bitmap.core;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.BitmapGlobalConfig;
import com.lidroid.xutils.bitmap.factory.BitmapFactory;
import com.lidroid.xutils.cache.FileNameGenerator;
import com.lidroid.xutils.cache.LruDiskCache;
import com.lidroid.xutils.cache.LruMemoryCache;
import com.lidroid.xutils.util.IOUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.util.OtherUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class BitmapCache {
    private BitmapGlobalConfig globalConfig;
    private LruDiskCache mDiskLruCache;
    private LruMemoryCache<MemoryCacheKey, Bitmap> mMemoryCache;
    private final int DISK_CACHE_INDEX = 0;
    private final Object mDiskCacheLock = new Object();

    public BitmapCache(BitmapGlobalConfig bitmapGlobalConfig) {
        if (bitmapGlobalConfig == null) {
            throw new IllegalArgumentException("globalConfig may not be null");
        }
        this.globalConfig = bitmapGlobalConfig;
    }

    public void initMemoryCache() {
        if (this.globalConfig.isMemoryCacheEnabled()) {
            if (this.mMemoryCache != null) {
                try {
                    clearMemoryCache();
                } catch (Throwable unused) {
                }
            }
            this.mMemoryCache = new LruMemoryCache<MemoryCacheKey, Bitmap>(this.globalConfig.getMemoryCacheSize()) { // from class: com.lidroid.xutils.bitmap.core.BitmapCache.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.lidroid.xutils.cache.LruMemoryCache
                public int sizeOf(MemoryCacheKey memoryCacheKey, Bitmap bitmap) {
                    if (bitmap == null) {
                        return 0;
                    }
                    return bitmap.getRowBytes() * bitmap.getHeight();
                }
            };
        }
    }

    public void initDiskCache() {
        synchronized (this.mDiskCacheLock) {
            if (this.globalConfig.isDiskCacheEnabled() && (this.mDiskLruCache == null || this.mDiskLruCache.isClosed())) {
                File file = new File(this.globalConfig.getDiskCachePath());
                if (file.exists() || file.mkdirs()) {
                    long availableSpace = OtherUtils.getAvailableSpace(file);
                    long diskCacheSize = this.globalConfig.getDiskCacheSize();
                    if (availableSpace > diskCacheSize) {
                        availableSpace = diskCacheSize;
                    }
                    try {
                        this.mDiskLruCache = LruDiskCache.open(file, 1, 1, availableSpace);
                        this.mDiskLruCache.setFileNameGenerator(this.globalConfig.getFileNameGenerator());
                        LogUtils.m3418d("create disk cache success");
                    } catch (Throwable th) {
                        this.mDiskLruCache = null;
                        LogUtils.m3421e("create disk cache error", th);
                    }
                }
            }
        }
    }

    public void setMemoryCacheSize(int i) {
        if (this.mMemoryCache != null) {
            this.mMemoryCache.setMaxSize(i);
        }
    }

    public void setDiskCacheSize(int i) {
        synchronized (this.mDiskCacheLock) {
            if (this.mDiskLruCache != null) {
                this.mDiskLruCache.setMaxSize(i);
            }
        }
    }

    public void setDiskCacheFileNameGenerator(FileNameGenerator fileNameGenerator) {
        synchronized (this.mDiskCacheLock) {
            if (this.mDiskLruCache != null && fileNameGenerator != null) {
                this.mDiskLruCache.setFileNameGenerator(fileNameGenerator);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:32:0x0078  */
    /* JADX WARN: Code duplicated, block: B:42:0x0097  */
    /* JADX WARN: Code duplicated, block: B:46:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:48:0x00ac A[Catch: all -> 0x00dc, Throwable -> 0x00df, TRY_LEAVE, TryCatch #1 {Throwable -> 0x00df, blocks: (B:48:0x00ac, B:64:0x00e6, B:45:0x009e), top: B:78:0x009e }] */
    /* JADX WARN: Code duplicated, block: B:52:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:54:0x00ca A[Catch: Throwable -> 0x00d9, all -> 0x0111, TRY_ENTER, TRY_LEAVE, TryCatch #5 {all -> 0x0111, blocks: (B:49:0x00b1, B:54:0x00ca, B:71:0x0103), top: B:82:0x00b1 }] */
    /* JADX WARN: Code duplicated, block: B:62:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:64:0x00e6 A[Catch: all -> 0x00dc, Throwable -> 0x00df, TRY_ENTER, TRY_LEAVE, TryCatch #1 {Throwable -> 0x00df, blocks: (B:48:0x00ac, B:64:0x00e6, B:45:0x009e), top: B:78:0x009e }] */
    /* JADX WARN: Code duplicated, block: B:84:0x0088 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:86:0x007c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public Bitmap downloadBitmap(String str, BitmapDisplayConfig bitmapDisplayConfig, BitmapUtils.BitmapLoadTask<?> bitmapLoadTask) {
        Throwable th;
        LruDiskCache.Snapshot snapshot;
        OutputStream outputStreamNewOutputStream;
        Throwable th2;
        OutputStream byteArrayOutputStream;
        Bitmap bitmapDecodeBitmapMeta;
        Bitmap bitmapAddBitmapToMemoryCache;
        Throwable th3;
        Bitmap bitmap;
        BitmapMeta bitmapMeta = new BitmapMeta(this, null);
        try {
            try {
                if (!this.globalConfig.isDiskCacheEnabled()) {
                    bitmapDecodeBitmapMeta = null;
                    snapshot = null;
                    outputStreamNewOutputStream = null;
                    if (bitmapDecodeBitmapMeta == null) {
                        byteArrayOutputStream = new ByteArrayOutputStream();
                        try {
                            try {
                                bitmapMeta.expiryTimestamp = this.globalConfig.getDownloader().downloadToStream(str, byteArrayOutputStream, bitmapLoadTask);
                                if (bitmapMeta.expiryTimestamp < 0) {
                                    IOUtils.closeQuietly(byteArrayOutputStream);
                                    IOUtils.closeQuietly(snapshot);
                                    return null;
                                }
                                bitmapMeta.data = ((ByteArrayOutputStream) byteArrayOutputStream).toByteArray();
                                bitmapAddBitmapToMemoryCache = decodeBitmapMeta(bitmapMeta, bitmapDisplayConfig);
                                outputStreamNewOutputStream = byteArrayOutputStream;
                            } catch (Throwable th4) {
                                th = th4;
                                outputStreamNewOutputStream = byteArrayOutputStream;
                            }
                        } catch (Throwable th5) {
                            th2 = th5;
                            LogUtils.m3421e(th2.getMessage(), th2);
                            IOUtils.closeQuietly(byteArrayOutputStream);
                            IOUtils.closeQuietly(snapshot);
                            return null;
                        }
                        th = th4;
                        outputStreamNewOutputStream = byteArrayOutputStream;
                        IOUtils.closeQuietly(outputStreamNewOutputStream);
                        IOUtils.closeQuietly(snapshot);
                        throw th;
                    }
                    bitmapAddBitmapToMemoryCache = bitmapDecodeBitmapMeta;
                    if (bitmapAddBitmapToMemoryCache != null) {
                        bitmapAddBitmapToMemoryCache = addBitmapToMemoryCache(str, bitmapDisplayConfig, rotateBitmapIfNeeded(str, bitmapDisplayConfig, bitmapAddBitmapToMemoryCache), bitmapMeta.expiryTimestamp);
                    }
                    IOUtils.closeQuietly(outputStreamNewOutputStream);
                    IOUtils.closeQuietly(snapshot);
                    return bitmapAddBitmapToMemoryCache;
                }
                if (this.mDiskLruCache == null) {
                    initDiskCache();
                }
                if (this.mDiskLruCache != null) {
                    try {
                        LruDiskCache.Snapshot snapshot2 = this.mDiskLruCache.get(str);
                        if (snapshot2 == null) {
                            try {
                                LruDiskCache.Editor editorEdit = this.mDiskLruCache.edit(str);
                                if (editorEdit != null) {
                                    outputStreamNewOutputStream = editorEdit.newOutputStream(0);
                                    try {
                                        bitmapMeta.expiryTimestamp = this.globalConfig.getDownloader().downloadToStream(str, outputStreamNewOutputStream, bitmapLoadTask);
                                        if (bitmapMeta.expiryTimestamp < 0) {
                                            editorEdit.abort();
                                            IOUtils.closeQuietly(outputStreamNewOutputStream);
                                            IOUtils.closeQuietly(snapshot2);
                                            return null;
                                        }
                                        editorEdit.setEntryExpiryTimestamp(bitmapMeta.expiryTimestamp);
                                        editorEdit.commit();
                                        snapshot = this.mDiskLruCache.get(str);
                                    } catch (Throwable th6) {
                                        th = th6;
                                        snapshot = snapshot2;
                                        bitmap = null;
                                        th3 = th;
                                        try {
                                            LogUtils.m3421e(th3.getMessage(), th3);
                                            bitmapDecodeBitmapMeta = bitmap;
                                            if (bitmapDecodeBitmapMeta == null) {
                                                byteArrayOutputStream = new ByteArrayOutputStream();
                                                bitmapMeta.expiryTimestamp = this.globalConfig.getDownloader().downloadToStream(str, byteArrayOutputStream, bitmapLoadTask);
                                                if (bitmapMeta.expiryTimestamp < 0) {
                                                    IOUtils.closeQuietly(byteArrayOutputStream);
                                                    IOUtils.closeQuietly(snapshot);
                                                    return null;
                                                }
                                                bitmapMeta.data = ((ByteArrayOutputStream) byteArrayOutputStream).toByteArray();
                                                bitmapAddBitmapToMemoryCache = decodeBitmapMeta(bitmapMeta, bitmapDisplayConfig);
                                                outputStreamNewOutputStream = byteArrayOutputStream;
                                                th = th4;
                                                outputStreamNewOutputStream = byteArrayOutputStream;
                                                IOUtils.closeQuietly(outputStreamNewOutputStream);
                                                IOUtils.closeQuietly(snapshot);
                                                throw th;
                                            }
                                            bitmapAddBitmapToMemoryCache = bitmapDecodeBitmapMeta;
                                            if (bitmapAddBitmapToMemoryCache != null) {
                                                bitmapAddBitmapToMemoryCache = addBitmapToMemoryCache(str, bitmapDisplayConfig, rotateBitmapIfNeeded(str, bitmapDisplayConfig, bitmapAddBitmapToMemoryCache), bitmapMeta.expiryTimestamp);
                                            }
                                            IOUtils.closeQuietly(outputStreamNewOutputStream);
                                            IOUtils.closeQuietly(snapshot);
                                            return bitmapAddBitmapToMemoryCache;
                                        } catch (Throwable th7) {
                                            th2 = th7;
                                            byteArrayOutputStream = outputStreamNewOutputStream;
                                        }
                                    }
                                } else {
                                    snapshot = snapshot2;
                                    outputStreamNewOutputStream = null;
                                }
                                if (snapshot != null) {
                                    try {
                                        try {
                                            bitmapMeta.inputStream = snapshot.getInputStream(0);
                                            bitmapDecodeBitmapMeta = decodeBitmapMeta(bitmapMeta, bitmapDisplayConfig);
                                            if (bitmapDecodeBitmapMeta == null) {
                                                try {
                                                    bitmapMeta.inputStream = null;
                                                    this.mDiskLruCache.remove(str);
                                                } catch (Throwable th8) {
                                                    th = th8;
                                                    bitmap = bitmapDecodeBitmapMeta;
                                                    th3 = th;
                                                    LogUtils.m3421e(th3.getMessage(), th3);
                                                    bitmapDecodeBitmapMeta = bitmap;
                                                }
                                            }
                                        } catch (Throwable th9) {
                                            th3 = th9;
                                            bitmap = null;
                                            LogUtils.m3421e(th3.getMessage(), th3);
                                            bitmapDecodeBitmapMeta = bitmap;
                                        }
                                    } catch (Throwable th10) {
                                        th = th10;
                                    }
                                } else {
                                    bitmapDecodeBitmapMeta = null;
                                }
                            } catch (Throwable th11) {
                                th = th11;
                                snapshot = snapshot2;
                                bitmap = null;
                                outputStreamNewOutputStream = null;
                            }
                        } else {
                            snapshot = snapshot2;
                            outputStreamNewOutputStream = null;
                            if (snapshot != null) {
                                bitmapMeta.inputStream = snapshot.getInputStream(0);
                                bitmapDecodeBitmapMeta = decodeBitmapMeta(bitmapMeta, bitmapDisplayConfig);
                                if (bitmapDecodeBitmapMeta == null) {
                                    bitmapMeta.inputStream = null;
                                    this.mDiskLruCache.remove(str);
                                }
                            } else {
                                bitmapDecodeBitmapMeta = null;
                            }
                        }
                    } catch (Throwable th12) {
                        th3 = th12;
                        bitmap = null;
                        snapshot = null;
                        outputStreamNewOutputStream = null;
                    }
                } else {
                    bitmapDecodeBitmapMeta = null;
                    snapshot = null;
                    outputStreamNewOutputStream = null;
                }
                if (bitmapDecodeBitmapMeta == null) {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmapMeta.expiryTimestamp = this.globalConfig.getDownloader().downloadToStream(str, byteArrayOutputStream, bitmapLoadTask);
                    if (bitmapMeta.expiryTimestamp < 0) {
                        IOUtils.closeQuietly(byteArrayOutputStream);
                        IOUtils.closeQuietly(snapshot);
                        return null;
                    }
                    bitmapMeta.data = ((ByteArrayOutputStream) byteArrayOutputStream).toByteArray();
                    bitmapAddBitmapToMemoryCache = decodeBitmapMeta(bitmapMeta, bitmapDisplayConfig);
                    outputStreamNewOutputStream = byteArrayOutputStream;
                    th = th4;
                    outputStreamNewOutputStream = byteArrayOutputStream;
                    IOUtils.closeQuietly(outputStreamNewOutputStream);
                    IOUtils.closeQuietly(snapshot);
                    throw th;
                }
                bitmapAddBitmapToMemoryCache = bitmapDecodeBitmapMeta;
                if (bitmapAddBitmapToMemoryCache != null) {
                    bitmapAddBitmapToMemoryCache = addBitmapToMemoryCache(str, bitmapDisplayConfig, rotateBitmapIfNeeded(str, bitmapDisplayConfig, bitmapAddBitmapToMemoryCache), bitmapMeta.expiryTimestamp);
                }
                IOUtils.closeQuietly(outputStreamNewOutputStream);
                IOUtils.closeQuietly(snapshot);
                return bitmapAddBitmapToMemoryCache;
            } catch (Throwable th13) {
                th2 = th13;
                byteArrayOutputStream = null;
                snapshot = null;
            }
        } catch (Throwable th14) {
            th = th14;
            snapshot = null;
            outputStreamNewOutputStream = null;
        }
        LogUtils.m3421e(th2.getMessage(), th2);
        IOUtils.closeQuietly(byteArrayOutputStream);
        IOUtils.closeQuietly(snapshot);
        return null;
    }

    public Bitmap addBitmapToMemoryCache(String str, BitmapDisplayConfig bitmapDisplayConfig, Bitmap bitmap, long j) throws IOException {
        BitmapFactory bitmapFactory;
        if (bitmapDisplayConfig != null && (bitmapFactory = bitmapDisplayConfig.getBitmapFactory()) != null) {
            bitmap = bitmapFactory.cloneNew().createBitmap(bitmap);
        }
        if (str != null && bitmap != null && this.globalConfig.isMemoryCacheEnabled() && this.mMemoryCache != null) {
            this.mMemoryCache.put(new MemoryCacheKey(this, str, bitmapDisplayConfig, null), bitmap, j);
        }
        return bitmap;
    }

    public Bitmap getBitmapFromMemCache(String str, BitmapDisplayConfig bitmapDisplayConfig) {
        MemoryCacheKey memoryCacheKey = null;
        if (this.mMemoryCache == null || !this.globalConfig.isMemoryCacheEnabled()) {
            return null;
        }
        return this.mMemoryCache.get(new MemoryCacheKey(this, str, bitmapDisplayConfig, memoryCacheKey));
    }

    public File getBitmapFileFromDiskCache(String str) {
        synchronized (this.mDiskCacheLock) {
            if (this.mDiskLruCache == null) {
                return null;
            }
            return this.mDiskLruCache.getCacheFile(str, 0);
        }
    }

    public Bitmap getBitmapFromDiskCache(String str, BitmapDisplayConfig bitmapDisplayConfig) {
        LruDiskCache.Snapshot snapshot;
        Bitmap bitmapDecodeSampledBitmapFromDescriptor;
        if (str == null || !this.globalConfig.isDiskCacheEnabled()) {
            return null;
        }
        if (this.mDiskLruCache == null) {
            initDiskCache();
        }
        LruDiskCache lruDiskCache = this.mDiskLruCache;
        try {
            if (lruDiskCache != null) {
                try {
                    snapshot = this.mDiskLruCache.get(str);
                    if (snapshot != null) {
                        if (bitmapDisplayConfig != null) {
                            try {
                                if (bitmapDisplayConfig.isShowOriginal()) {
                                    bitmapDecodeSampledBitmapFromDescriptor = BitmapDecoder.decodeFileDescriptor(snapshot.getInputStream(0).getFD());
                                } else {
                                    bitmapDecodeSampledBitmapFromDescriptor = BitmapDecoder.decodeSampledBitmapFromDescriptor(snapshot.getInputStream(0).getFD(), bitmapDisplayConfig.getBitmapMaxSize(), bitmapDisplayConfig.getBitmapConfig());
                                }
                            } catch (Throwable th) {
                                th = th;
                                LogUtils.m3421e(th.getMessage(), th);
                                IOUtils.closeQuietly(snapshot);
                                return null;
                            }
                        } else {
                            bitmapDecodeSampledBitmapFromDescriptor = BitmapDecoder.decodeFileDescriptor(snapshot.getInputStream(0).getFD());
                        }
                        Bitmap bitmapAddBitmapToMemoryCache = addBitmapToMemoryCache(str, bitmapDisplayConfig, rotateBitmapIfNeeded(str, bitmapDisplayConfig, bitmapDecodeSampledBitmapFromDescriptor), this.mDiskLruCache.getExpiryTimestamp(str));
                        IOUtils.closeQuietly(snapshot);
                        return bitmapAddBitmapToMemoryCache;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    snapshot = null;
                }
                IOUtils.closeQuietly(snapshot);
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public void clearCache() {
        clearMemoryCache();
        clearDiskCache();
    }

    public void clearMemoryCache() {
        if (this.mMemoryCache != null) {
            this.mMemoryCache.evictAll();
        }
    }

    public void clearDiskCache() {
        synchronized (this.mDiskCacheLock) {
            if (this.mDiskLruCache != null && !this.mDiskLruCache.isClosed()) {
                try {
                    this.mDiskLruCache.delete();
                    this.mDiskLruCache.close();
                } catch (Throwable th) {
                    LogUtils.m3421e(th.getMessage(), th);
                }
                this.mDiskLruCache = null;
            }
        }
        initDiskCache();
    }

    public void clearCache(String str) {
        clearMemoryCache(str);
        clearDiskCache(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void clearMemoryCache(String str) {
        MemoryCacheKey memoryCacheKey = new MemoryCacheKey(this, str, null, 0 == true ? 1 : 0);
        if (this.mMemoryCache != null) {
            while (this.mMemoryCache.containsKey(memoryCacheKey)) {
                this.mMemoryCache.remove(memoryCacheKey);
            }
        }
    }

    public void clearDiskCache(String str) {
        synchronized (this.mDiskCacheLock) {
            if (this.mDiskLruCache != null && !this.mDiskLruCache.isClosed()) {
                try {
                    this.mDiskLruCache.remove(str);
                } catch (Throwable th) {
                    LogUtils.m3421e(th.getMessage(), th);
                }
            }
        }
    }

    public void flush() {
        synchronized (this.mDiskCacheLock) {
            if (this.mDiskLruCache != null) {
                try {
                    this.mDiskLruCache.flush();
                } catch (Throwable th) {
                    LogUtils.m3421e(th.getMessage(), th);
                }
            }
        }
    }

    public void close() {
        synchronized (this.mDiskCacheLock) {
            if (this.mDiskLruCache != null) {
                try {
                    if (!this.mDiskLruCache.isClosed()) {
                        this.mDiskLruCache.close();
                    }
                } catch (Throwable th) {
                    LogUtils.m3421e(th.getMessage(), th);
                }
                this.mDiskLruCache = null;
            }
        }
    }

    private class BitmapMeta {
        public byte[] data;
        public long expiryTimestamp;
        public FileInputStream inputStream;

        private BitmapMeta() {
        }

        /* synthetic */ BitmapMeta(BitmapCache bitmapCache, BitmapMeta bitmapMeta) {
            this();
        }
    }

    private Bitmap decodeBitmapMeta(BitmapMeta bitmapMeta, BitmapDisplayConfig bitmapDisplayConfig) throws IOException {
        if (bitmapMeta == null) {
            return null;
        }
        if (bitmapMeta.inputStream != null) {
            if (bitmapDisplayConfig == null || bitmapDisplayConfig.isShowOriginal()) {
                return BitmapDecoder.decodeFileDescriptor(bitmapMeta.inputStream.getFD());
            }
            return BitmapDecoder.decodeSampledBitmapFromDescriptor(bitmapMeta.inputStream.getFD(), bitmapDisplayConfig.getBitmapMaxSize(), bitmapDisplayConfig.getBitmapConfig());
        }
        if (bitmapMeta.data == null) {
            return null;
        }
        if (bitmapDisplayConfig == null || bitmapDisplayConfig.isShowOriginal()) {
            return BitmapDecoder.decodeByteArray(bitmapMeta.data);
        }
        return BitmapDecoder.decodeSampledBitmapFromByteArray(bitmapMeta.data, bitmapDisplayConfig.getBitmapMaxSize(), bitmapDisplayConfig.getBitmapConfig());
    }

    /* JADX WARN: Code duplicated, block: B:29:0x005c  */
    private synchronized Bitmap rotateBitmapIfNeeded(String str, BitmapDisplayConfig bitmapDisplayConfig, Bitmap bitmap) {
        File bitmapFileFromDiskCache;
        Bitmap bitmapCreateBitmap;
        if (bitmapDisplayConfig == null) {
            bitmapCreateBitmap = bitmap;
        } else if (bitmapDisplayConfig.isAutoRotation() && (bitmapFileFromDiskCache = getBitmapFileFromDiskCache(str)) != null && bitmapFileFromDiskCache.exists()) {
            try {
                int i = 0;
                int attributeInt = new ExifInterface(bitmapFileFromDiskCache.getPath()).getAttributeInt("Orientation", 0);
                if (attributeInt == 3) {
                    i = 180;
                } else if (attributeInt == 6) {
                    i = 90;
                } else if (attributeInt == 8) {
                    i = 270;
                }
                if (i != 0) {
                    Matrix matrix = new Matrix();
                    matrix.postRotate(i);
                    bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                    bitmap.recycle();
                } else {
                    bitmapCreateBitmap = bitmap;
                }
            } catch (Throwable unused) {
                return bitmap;
            }
        } else {
            bitmapCreateBitmap = bitmap;
        }
        return bitmapCreateBitmap;
    }

    public class MemoryCacheKey {
        private String subKey;
        private String uri;

        private MemoryCacheKey(String str, BitmapDisplayConfig bitmapDisplayConfig) {
            this.uri = str;
            this.subKey = bitmapDisplayConfig == null ? null : bitmapDisplayConfig.toString();
        }

        /* synthetic */ MemoryCacheKey(BitmapCache bitmapCache, String str, BitmapDisplayConfig bitmapDisplayConfig, MemoryCacheKey memoryCacheKey) {
            this(str, bitmapDisplayConfig);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MemoryCacheKey)) {
                return false;
            }
            MemoryCacheKey memoryCacheKey = (MemoryCacheKey) obj;
            if (!this.uri.equals(memoryCacheKey.uri)) {
                return false;
            }
            if (this.subKey == null || memoryCacheKey.subKey == null) {
                return true;
            }
            return this.subKey.equals(memoryCacheKey.subKey);
        }

        public int hashCode() {
            return this.uri.hashCode();
        }
    }
}
