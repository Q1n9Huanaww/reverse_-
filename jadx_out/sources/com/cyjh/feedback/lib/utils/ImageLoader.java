package com.cyjh.feedback.lib.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.LruCache;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/* JADX INFO: loaded from: classes.dex */
public class ImageLoader {
    private static final int DEFAULT_COUNT = 3;
    private static final int DEFAULT_LOAD = 272;
    private static ImageLoader mImageLoader;
    private LruCache<String, Bitmap> mLruCache;
    private ExecutorService mPoolThread;
    private LinkedList<Runnable> mQueueList;
    private Semaphore mSemaphoreThreadHandler = new Semaphore(0);
    private Semaphore mSemaphoreThreadPool;
    private Thread mThreadBackground;
    private Handler mThreadHandler;
    private Type mType;
    private Handler mUIHandler;

    public enum Type {
        FIFO,
        FILO
    }

    private ImageLoader(Type type, int i) {
        init(type, i);
    }

    private void init(Type type, int i) {
        this.mType = type;
        this.mSemaphoreThreadPool = new Semaphore(i);
        this.mThreadBackground = new Thread() { // from class: com.cyjh.feedback.lib.utils.ImageLoader.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                Looper.prepare();
                ImageLoader.this.mThreadHandler = new Handler() { // from class: com.cyjh.feedback.lib.utils.ImageLoader.1.1
                    @Override // android.os.Handler
                    public void handleMessage(Message message) {
                        ImageLoader.this.mPoolThread.execute(ImageLoader.this.getTask());
                        try {
                            ImageLoader.this.mSemaphoreThreadPool.acquire();
                        } catch (InterruptedException e) {
                            ThrowableExtension.printStackTrace(e);
                        }
                    }
                };
                ImageLoader.this.mSemaphoreThreadHandler.release();
                Looper.loop();
            }
        };
        this.mThreadBackground.start();
        this.mLruCache = new LruCache<String, Bitmap>(((int) Runtime.getRuntime().maxMemory()) / 8) { // from class: com.cyjh.feedback.lib.utils.ImageLoader.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.util.LruCache
            public int sizeOf(String str, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        };
        this.mQueueList = new LinkedList<>();
        this.mPoolThread = Executors.newFixedThreadPool(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Runnable getTask() {
        if (Type.FIFO == this.mType) {
            return this.mQueueList.removeFirst();
        }
        if (Type.FILO == this.mType) {
            return this.mQueueList.removeLast();
        }
        return null;
    }

    public static ImageLoader getInstance() {
        if (mImageLoader == null) {
            synchronized (ImageLoader.class) {
                if (mImageLoader == null) {
                    mImageLoader = new ImageLoader(Type.FILO, 3);
                }
            }
        }
        return mImageLoader;
    }

    public static ImageLoader getInstance(Type type, int i) {
        if (mImageLoader == null) {
            synchronized (ImageLoader.class) {
                if (mImageLoader == null) {
                    mImageLoader = new ImageLoader(type, i);
                }
            }
        }
        return mImageLoader;
    }

    public void imageLoader(final String str, final ImageView imageView) {
        imageView.setTag(str);
        if (this.mUIHandler == null) {
            this.mUIHandler = new Handler() { // from class: com.cyjh.feedback.lib.utils.ImageLoader.3
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    ImageHolder imageHolder = (ImageHolder) message.obj;
                    Bitmap bitmap = imageHolder.bitmap;
                    String str2 = imageHolder.path;
                    ImageView imageView2 = imageHolder.imageView;
                    if (imageView2.getTag().toString().equals(str2)) {
                        imageView2.setImageBitmap(bitmap);
                    }
                }
            };
        }
        Bitmap bitmapFromLruCache = getBitmapFromLruCache(str);
        if (bitmapFromLruCache != null) {
            refreshBitmap(bitmapFromLruCache, str, imageView);
        } else {
            addTask(new Runnable() { // from class: com.cyjh.feedback.lib.utils.ImageLoader.4
                @Override // java.lang.Runnable
                public void run() {
                    Bitmap bitmapDecodeSampleBitmapFormPath = ImageLoader.this.decodeSampleBitmapFormPath(str, ImageLoader.this.getImageViewSize(imageView));
                    ImageLoader.this.addBitmapToLruCache(bitmapDecodeSampleBitmapFormPath, str);
                    ImageLoader.this.refreshBitmap(bitmapDecodeSampleBitmapFormPath, str, imageView);
                    ImageLoader.this.mSemaphoreThreadPool.release();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshBitmap(Bitmap bitmap, String str, ImageView imageView) {
        ImageHolder imageHolder = new ImageHolder();
        imageHolder.bitmap = bitmap;
        imageHolder.path = str;
        imageHolder.imageView = imageView;
        Message messageObtainMessage = this.mUIHandler.obtainMessage();
        messageObtainMessage.obj = imageHolder;
        this.mUIHandler.sendMessage(messageObtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addBitmapToLruCache(Bitmap bitmap, String str) {
        if (getBitmapFromLruCache(str) != null || bitmap == null) {
            return;
        }
        this.mLruCache.put(str, bitmap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap decodeSampleBitmapFormPath(String str, ImageSize imageSize) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inSampleSize = calculateInSample(options, imageSize);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(str, options);
    }

    private int calculateInSample(BitmapFactory.Options options, ImageSize imageSize) {
        int i = options.outWidth;
        int i2 = options.outHeight;
        if (i > imageSize.imageWidth || i2 > imageSize.imageHeight) {
            return Math.max(Math.round((i * 1.0f) / imageSize.imageWidth), Math.round((i2 * 1.0f) / imageSize.imageHeight));
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ImageSize getImageViewSize(ImageView imageView) {
        DisplayMetrics displayMetrics = imageView.getContext().getResources().getDisplayMetrics();
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        int width = imageView.getWidth();
        if (width <= 0) {
            width = layoutParams.width;
        }
        if (width <= 0) {
            width = getImageViewFiled(imageView, "mMaxWidth");
        }
        if (width <= 0) {
            width = displayMetrics.widthPixels;
        }
        int height = imageView.getHeight();
        if (height <= 0) {
            height = layoutParams.height;
        }
        if (height <= 0) {
            height = getImageViewFiled(imageView, "mMaxHeight");
        }
        if (height <= 0) {
            height = displayMetrics.heightPixels;
        }
        ImageSize imageSize = new ImageSize();
        imageSize.imageWidth = width;
        imageSize.imageHeight = height;
        return imageSize;
    }

    private int getImageViewFiled(Object obj, String str) {
        try {
            Field declaredField = ImageView.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            int i = declaredField.getInt(obj);
            if (i <= 0 || i >= Integer.MAX_VALUE) {
                return 0;
            }
            return i;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return 0;
        }
    }

    private synchronized void addTask(Runnable runnable) {
        this.mQueueList.add(runnable);
        try {
            if (this.mThreadHandler == null) {
                this.mSemaphoreThreadHandler.acquire();
            }
        } catch (InterruptedException e) {
            ThrowableExtension.printStackTrace(e);
        }
        this.mThreadHandler.sendEmptyMessage(272);
    }

    private Bitmap getBitmapFromLruCache(String str) {
        return this.mLruCache.get(str);
    }

    class ImageHolder {
        Bitmap bitmap;
        ImageView imageView;
        String path;

        ImageHolder() {
        }
    }

    class ImageSize {
        int imageHeight;
        int imageWidth;

        ImageSize() {
        }
    }
}
