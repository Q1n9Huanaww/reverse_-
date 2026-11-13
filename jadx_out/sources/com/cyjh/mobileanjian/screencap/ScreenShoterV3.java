package com.cyjh.mobileanjian.screencap;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.hardware.display.VirtualDisplay;
import android.media.Image;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.WindowManager;
import com.cyjh.p019mq.p023d.C0840e;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(21)
public final class ScreenShoterV3 {

    /* JADX INFO: renamed from: a */
    private static final String f1501a = "ScreenShoterV3";

    /* JADX INFO: renamed from: b */
    private Context f1502b;

    /* JADX INFO: renamed from: c */
    private MediaProjection f1503c;

    /* JADX INFO: renamed from: d */
    private Object f1504d;

    /* JADX INFO: renamed from: e */
    private HandlerThread f1505e;

    /* JADX INFO: renamed from: f */
    private Handler f1506f;

    /* JADX INFO: renamed from: g */
    private Intent f1507g;

    /* JADX INFO: renamed from: h */
    private ImageReader f1508h;

    /* JADX INFO: renamed from: i */
    private VirtualDisplay f1509i;

    /* JADX INFO: renamed from: j */
    private ScreenShotImage f1510j;

    /* JADX INFO: renamed from: k */
    private Bitmap f1511k;

    /* JADX INFO: renamed from: l */
    private int f1512l;

    /* JADX INFO: renamed from: m */
    private int f1513m;

    /* JADX INFO: renamed from: n */
    private int f1514n;

    /* JADX INFO: renamed from: o */
    private int f1515o;

    /* JADX INFO: renamed from: p */
    private boolean f1516p;

    /* JADX INFO: renamed from: q */
    private boolean f1517q;

    /* JADX INFO: renamed from: r */
    private volatile boolean f1518r;

    /* synthetic */ ScreenShoterV3(byte b) {
        this();
    }

    /* JADX INFO: renamed from: a */
    static /* synthetic */ Bitmap m1152a(Image image) {
        return m1153a(image, 0, 0);
    }

    /* JADX INFO: renamed from: c */
    static /* synthetic */ boolean m1164c(ScreenShoterV3 screenShoterV3) {
        screenShoterV3.f1516p = false;
        return false;
    }

    private ScreenShoterV3() {
        this.f1504d = new Object();
        this.f1516p = false;
        this.f1517q = false;
        this.f1518r = false;
    }

    /* JADX INFO: renamed from: com.cyjh.mobileanjian.screencap.ScreenShoterV3$a */
    private static class C0828a {

        /* JADX INFO: renamed from: a */
        private static final ScreenShoterV3 f1520a = new ScreenShoterV3(0);

        private C0828a() {
        }
    }

    public static synchronized ScreenShoterV3 getInstance() {
        return C0828a.f1520a;
    }

    public final void init(Context context, Intent intent) {
        if (this.f1517q) {
            return;
        }
        this.f1517q = true;
        this.f1502b = context.getApplicationContext();
        this.f1507g = intent;
        this.f1514n = Resources.getSystem().getDisplayMetrics().densityDpi;
    }

    public final void init(Context context, Intent intent, MediaProjection mediaProjection) {
        if (this.f1517q) {
            return;
        }
        this.f1517q = true;
        this.f1502b = context.getApplicationContext();
        this.f1507g = intent;
        this.f1514n = Resources.getSystem().getDisplayMetrics().densityDpi;
        this.f1503c = mediaProjection;
    }

    public final boolean isInited() {
        return this.f1517q;
    }

    /* JADX INFO: renamed from: a */
    private ScreenShotImage m1155a() throws IllegalStateException {
        Image imageAcquireLatestImage = this.f1508h.acquireLatestImage();
        if (imageAcquireLatestImage == null) {
            return this.f1510j;
        }
        ScreenShotImage screenShotImageM1161b = m1161b(imageAcquireLatestImage);
        this.f1510j = screenShotImageM1161b;
        return screenShotImageM1161b;
    }

    /* JADX INFO: renamed from: a */
    private Bitmap m1151a(int i, int i2) throws IllegalStateException {
        Image imageAcquireLatestImage = this.f1508h.acquireLatestImage();
        if (imageAcquireLatestImage == null) {
            return this.f1511k;
        }
        Bitmap bitmapM1153a = m1153a(imageAcquireLatestImage, i, i2);
        this.f1511k = bitmapM1153a;
        return bitmapM1153a;
    }

    /* JADX INFO: renamed from: a */
    private static Bitmap m1153a(Image image, int i, int i2) {
        Bitmap bitmap;
        Bitmap bitmapCreateBitmap;
        try {
            int width = image.getWidth();
            int height = image.getHeight();
            Image.Plane[] planes = image.getPlanes();
            ByteBuffer buffer = planes[0].getBuffer();
            int pixelStride = planes[0].getPixelStride();
            Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(((planes[0].getRowStride() - (pixelStride * width)) / pixelStride) + width, height, Bitmap.Config.ARGB_8888);
            bitmapCreateBitmap2.copyPixelsFromBuffer(buffer);
            if (width <= height || bitmapCreateBitmap2 == null) {
                bitmap = bitmapCreateBitmap2;
            } else {
                Matrix matrix = new Matrix();
                matrix.setRotate(90.0f, bitmapCreateBitmap2.getWidth() / 2, bitmapCreateBitmap2.getHeight() / 2);
                float height2 = bitmapCreateBitmap2.getHeight();
                float[] fArr = new float[9];
                matrix.getValues(fArr);
                matrix.postTranslate(height2 - fArr[2], 0.0f - fArr[5]);
                Bitmap bitmapCreateBitmap3 = Bitmap.createBitmap(bitmapCreateBitmap2.getHeight(), bitmapCreateBitmap2.getWidth(), Bitmap.Config.ARGB_8888);
                new Canvas(bitmapCreateBitmap3).drawBitmap(bitmapCreateBitmap2, matrix, new Paint());
                bitmapCreateBitmap2.recycle();
                bitmap = bitmapCreateBitmap3;
            }
            if (i == 0 && i2 == 0) {
                bitmapCreateBitmap = bitmap;
            } else {
                int width2 = bitmap.getWidth();
                int height3 = bitmap.getHeight();
                Log.e(f1501a, "load bitmap info return  1 width=" + width2 + " height = " + height3);
                bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, i2, width2 + 0, height3 - (i2 * 2), (Matrix) null, false);
                bitmap.recycle();
            }
            Log.e(f1501a, "load bitmap info return  2 width=" + bitmapCreateBitmap.getWidth() + " height = " + bitmapCreateBitmap.getHeight());
            return bitmapCreateBitmap;
        } catch (Exception unused) {
        } finally {
            if (image != null) {
                image.close();
            }
        }
    }

    public final void updateScreenSize() {
        WindowManager windowManager;
        if (this.f1517q) {
            int rotation = 0;
            this.f1518r = false;
            int[] iArrM1166a = C0829a.m1166a(this.f1502b);
            int i = iArrM1166a[0];
            int i2 = iArrM1166a[1];
            Context context = this.f1502b;
            if (context != null && (windowManager = (WindowManager) context.getSystemService("window")) != null) {
                rotation = windowManager.getDefaultDisplay().getRotation();
            }
            this.f1515o = rotation;
            if (this.f1512l == i && this.f1513m == i2) {
                return;
            }
            this.f1512l = i;
            this.f1513m = i2;
        }
    }

    public final void recycler() {
        this.f1518r = false;
        this.f1516p = false;
        this.f1513m = 0;
        this.f1512l = 0;
        this.f1515o = 0;
        synchronized (this.f1504d) {
            if (this.f1505e != null) {
                this.f1505e.quit();
                this.f1505e = null;
            }
            if (this.f1506f != null) {
                this.f1506f.removeCallbacksAndMessages(null);
                this.f1506f = null;
            }
            if (this.f1503c != null) {
                this.f1503c.stop();
                this.f1503c = null;
            }
            if (this.f1509i != null) {
                this.f1509i.release();
                this.f1509i = null;
            }
            if (this.f1508h != null) {
                this.f1508h.close();
                this.f1508h = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b */
    public ScreenShotImage m1161b(Image image) {
        try {
            ScreenShotImage screenShotImage = new ScreenShotImage();
            int width = image.getWidth();
            int height = image.getHeight();
            Image.Plane[] planes = image.getPlanes();
            ByteBuffer buffer = planes[0].getBuffer();
            int rowStride = planes[0].getRowStride();
            int pixelStride = planes[0].getPixelStride();
            byte[] bArr = new byte[buffer.capacity()];
            buffer.get(bArr);
            screenShotImage.width = width;
            screenShotImage.height = height;
            screenShotImage.rotation = this.f1515o;
            screenShotImage.rowStride = rowStride;
            screenShotImage.pixelStride = pixelStride;
            screenShotImage.data = bArr;
            return screenShotImage;
        } catch (Exception unused) {
        } finally {
            if (image != null) {
                image.close();
            }
        }
    }

    /* JADX INFO: renamed from: b */
    private synchronized void m1162b() {
        recycler();
        updateScreenSize();
        MediaProjectionManager mediaProjectionManager = (MediaProjectionManager) this.f1502b.getSystemService("media_projection");
        if (this.f1503c == null && mediaProjectionManager != null) {
            this.f1503c = mediaProjectionManager.getMediaProjection(-1, this.f1507g);
        }
        this.f1505e = new HandlerThread("imageReaderHandler");
        this.f1505e.start();
        this.f1506f = new Handler(this.f1505e.getLooper());
        C0840e.a aVarM1292a = C0840e.m1292a("getprop phone.id");
        if (aVarM1292a.f1615b != null && aVarM1292a.f1615b.length() > 0) {
            this.f1508h = ImageReader.newInstance(this.f1512l, this.f1513m, 2, 3);
        } else {
            this.f1508h = ImageReader.newInstance(this.f1512l, this.f1513m, 1, 3);
        }
        this.f1509i = this.f1503c.createVirtualDisplay("screen-mirror", this.f1512l, this.f1513m, this.f1514n, 16, this.f1508h.getSurface(), null, null);
        this.f1516p = true;
        if (this.f1508h != null) {
            this.f1508h.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() { // from class: com.cyjh.mobileanjian.screencap.ScreenShoterV3.1
                @Override // android.media.ImageReader.OnImageAvailableListener
                public final void onImageAvailable(ImageReader imageReader) {
                    if (ScreenShoterV3.this.f1516p) {
                        Image imageAcquireLatestImage = ScreenShoterV3.this.f1508h.acquireLatestImage();
                        if (imageAcquireLatestImage == null) {
                            ScreenShoterV3.this.f1518r = false;
                            return;
                        }
                        ScreenShoterV3.this.f1510j = ScreenShoterV3.this.m1161b(imageAcquireLatestImage);
                        ScreenShoterV3.this.f1511k = ScreenShoterV3.m1152a(imageAcquireLatestImage);
                        ScreenShoterV3.this.f1518r = true;
                        ScreenShoterV3.m1164c(ScreenShoterV3.this);
                        if (ScreenShoterV3.this.f1508h != null) {
                            ScreenShoterV3.this.f1508h.setOnImageAvailableListener(null, null);
                        }
                    }
                }
            }, this.f1506f);
        }
    }

    /* JADX INFO: renamed from: c */
    private boolean m1163c() {
        return this.f1518r;
    }

    public final ScreenShotImage obtainScreenShotImage() {
        ScreenShotImage screenShotImageM1155a;
        if (this.f1518r) {
            try {
                return m1155a();
            } catch (IllegalStateException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        synchronized (this.f1504d) {
            m1162b();
            int i = 0;
            while (!this.f1518r && (i = i + 1) < 50) {
                try {
                    Thread.sleep(20L);
                } catch (InterruptedException e2) {
                    ThrowableExtension.printStackTrace(e2);
                }
            }
            screenShotImageM1155a = m1155a();
        }
        return screenShotImageM1155a;
    }

    public final Bitmap obtainScreenShotImageReturnBitmap(int i, int i2) {
        Bitmap bitmapM1151a;
        if (this.f1518r) {
            try {
                return m1151a(i, i2);
            } catch (IllegalStateException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        synchronized (this.f1504d) {
            m1162b();
            int i3 = 0;
            while (!this.f1518r && (i3 = i3 + 1) < 50) {
                try {
                    Thread.sleep(20L);
                } catch (InterruptedException e2) {
                    ThrowableExtension.printStackTrace(e2);
                }
            }
            bitmapM1151a = m1151a(i, i2);
        }
        return bitmapM1151a;
    }
}
