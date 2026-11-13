package com.androidquery.callback;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.ExifInterface;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import com.androidquery.auth.AccountHandle;
import com.androidquery.util.AQUtility;
import com.androidquery.util.BitmapCache;
import com.androidquery.util.Common;
import com.androidquery.util.Constants;
import com.androidquery.util.RatioDrawable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import org.apache.http.HttpHost;

/* JADX INFO: loaded from: classes.dex */
public class BitmapAjaxCallback extends AbstractAjaxCallback<Bitmap, BitmapAjaxCallback> {
    private static int BIG_MAX = 20;
    private static int BIG_PIXELS = 160000;
    private static int BIG_TPIXELS = 1000000;
    private static boolean DELAY_WRITE = false;
    private static final int FADE_DUR = 300;
    private static int SMALL_MAX = 20;
    private static int SMALL_PIXELS = 2500;
    private static Map<String, Bitmap> bigCache;
    private static Map<String, Bitmap> invalidCache;
    private static Map<String, Bitmap> smallCache;
    private int animation;

    /* JADX INFO: renamed from: bm */
    private Bitmap f57bm;
    private int fallback;
    private File imageFile;
    private boolean invalid;
    private Bitmap preset;
    private float ratio;
    private boolean rotate;
    private int round;
    private int targetWidth;

    /* JADX INFO: renamed from: v */
    private WeakReference<ImageView> f58v;
    private static HashMap<String, WeakHashMap<ImageView, BitmapAjaxCallback>> queueMap = new HashMap<>();
    private static Bitmap empty = Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8);
    private static Bitmap dummy = Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8);
    private boolean targetDim = true;
    private float anchor = Float.MAX_VALUE;

    private static boolean fadeIn(int i, int i2) {
        switch (i) {
            case -3:
                if (i2 == 3) {
                    return true;
                }
                break;
            case -2:
                break;
            case -1:
                return true;
            default:
                return false;
        }
        return i2 == 1;
    }

    public BitmapAjaxCallback() {
        type(Bitmap.class).memCache(true).fileCache(true).url("");
    }

    public BitmapAjaxCallback imageView(ImageView imageView) {
        this.f58v = new WeakReference<>(imageView);
        return this;
    }

    public BitmapAjaxCallback targetWidth(int i) {
        this.targetWidth = i;
        return this;
    }

    public BitmapAjaxCallback file(File file) {
        this.imageFile = file;
        return this;
    }

    public BitmapAjaxCallback preset(Bitmap bitmap) {
        this.preset = bitmap;
        return this;
    }

    public BitmapAjaxCallback bitmap(Bitmap bitmap) {
        this.f57bm = bitmap;
        return this;
    }

    public BitmapAjaxCallback fallback(int i) {
        this.fallback = i;
        return this;
    }

    public BitmapAjaxCallback animation(int i) {
        this.animation = i;
        return this;
    }

    public BitmapAjaxCallback ratio(float f) {
        this.ratio = f;
        return this;
    }

    public BitmapAjaxCallback rotate(boolean z) {
        this.rotate = z;
        return this;
    }

    public BitmapAjaxCallback anchor(float f) {
        this.anchor = f;
        return this;
    }

    public BitmapAjaxCallback round(int i) {
        this.round = i;
        return this;
    }

    private static Bitmap decode(String str, byte[] bArr, BitmapFactory.Options options, boolean z) throws Throwable {
        Bitmap bitmapDecodeByteArray;
        if (str != null) {
            bitmapDecodeByteArray = decodeFile(str, options, z);
        } else {
            bitmapDecodeByteArray = bArr != null ? BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options) : null;
        }
        if (bitmapDecodeByteArray == null && options != null && !options.inJustDecodeBounds) {
            AQUtility.debug("decode image failed", str);
        }
        return bitmapDecodeByteArray;
    }

    private static Bitmap decodeFile(String str, BitmapFactory.Options options, boolean z) throws Throwable {
        Bitmap bitmapDecodeFileDescriptor;
        Bitmap bitmapRotate;
        if (options == null) {
            options = new BitmapFactory.Options();
        }
        options.inInputShareable = true;
        options.inPurgeable = true;
        FileInputStream fileInputStream = null;
        try {
            try {
                FileInputStream fileInputStream2 = new FileInputStream(str);
                try {
                    try {
                        bitmapDecodeFileDescriptor = BitmapFactory.decodeFileDescriptor(fileInputStream2.getFD(), null, options);
                        if (bitmapDecodeFileDescriptor == null || !z) {
                            bitmapRotate = bitmapDecodeFileDescriptor;
                        } else {
                            try {
                                bitmapRotate = rotate(str, bitmapDecodeFileDescriptor);
                            } catch (IOException e) {
                                e = e;
                                fileInputStream = fileInputStream2;
                                AQUtility.report(e);
                                AQUtility.close(fileInputStream);
                                bitmapRotate = bitmapDecodeFileDescriptor;
                            }
                        }
                        AQUtility.close(fileInputStream2);
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream = fileInputStream2;
                        AQUtility.close(fileInputStream);
                        throw th;
                    }
                } catch (IOException e2) {
                    e = e2;
                    bitmapDecodeFileDescriptor = null;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e3) {
            e = e3;
            bitmapDecodeFileDescriptor = null;
        }
        return bitmapRotate;
    }

    private static Bitmap rotate(String str, Bitmap bitmap) {
        int attributeInt;
        if (bitmap == null) {
            return null;
        }
        try {
            attributeInt = new ExifInterface(str).getAttributeInt("Orientation", 1);
        } catch (Exception e) {
            AQUtility.debug((Throwable) e);
            attributeInt = 1;
        }
        if (attributeInt <= 0) {
            return bitmap;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), getRotateMatrix(attributeInt), true);
        AQUtility.debug("before", String.valueOf(bitmap.getWidth()) + ":" + bitmap.getHeight());
        AQUtility.debug("after", String.valueOf(bitmapCreateBitmap.getWidth()) + ":" + bitmapCreateBitmap.getHeight());
        if (bitmap == bitmapCreateBitmap) {
            return bitmapCreateBitmap;
        }
        bitmap.recycle();
        return bitmapCreateBitmap;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static Matrix getRotateMatrix(int i) {
        Matrix matrix = new Matrix();
        switch (i) {
            case 2:
                matrix.setScale(-1.0f, 1.0f);
                return matrix;
            case 3:
                matrix.setRotate(180.0f);
                return matrix;
            case 4:
                matrix.setRotate(180.0f);
                matrix.postScale(-1.0f, 1.0f);
                return matrix;
            case 5:
                matrix.setRotate(90.0f);
                matrix.postScale(-1.0f, 1.0f);
                return matrix;
            case 6:
                matrix.setRotate(90.0f);
                return matrix;
            case 7:
                matrix.setRotate(-90.0f);
                matrix.postScale(-1.0f, 1.0f);
                return matrix;
            case 8:
                matrix.setRotate(-90.0f);
                return matrix;
            default:
                return matrix;
        }
    }

    public static Bitmap getResizedImage(String str, byte[] bArr, int i, boolean z, int i2) {
        return getResizedImage(str, bArr, i, z, i2, false);
    }

    public static Bitmap getResizedImage(String str, byte[] bArr, int i, boolean z, int i2, boolean z2) throws Throwable {
        BitmapFactory.Options options;
        Bitmap bitmapDecode;
        if (str == null && bArr == null) {
            return null;
        }
        if (i > 0) {
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inJustDecodeBounds = true;
            decode(str, bArr, options2, z2);
            int iMax = options2.outWidth;
            if (!z) {
                iMax = Math.max(iMax, options2.outHeight);
            }
            int iSampleSize = sampleSize(iMax, i);
            options = new BitmapFactory.Options();
            options.inSampleSize = iSampleSize;
        } else {
            options = null;
        }
        try {
            bitmapDecode = decode(str, bArr, options, z2);
        } catch (OutOfMemoryError e) {
            clearCache();
            AQUtility.report(e);
            bitmapDecode = null;
        }
        return i2 > 0 ? getRoundedCornerBitmap(bitmapDecode, i2) : bitmapDecode;
    }

    private static int sampleSize(int i, int i2) {
        int i3 = 1;
        for (int i4 = 0; i4 < 10 && i >= i2 * 2; i4++) {
            i /= 2;
            i3 *= 2;
        }
        return i3;
    }

    private Bitmap bmGet(String str, byte[] bArr) {
        return getResizedImage(str, bArr, this.targetWidth, this.targetDim, this.round, this.rotate);
    }

    @Override // com.androidquery.callback.AbstractAjaxCallback
    protected File accessFile(File file, String str) {
        if (this.imageFile != null && this.imageFile.exists()) {
            return this.imageFile;
        }
        return super.accessFile(file, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.androidquery.callback.AbstractAjaxCallback
    public Bitmap fileGet(String str, File file, AjaxStatus ajaxStatus) {
        return bmGet(file.getAbsolutePath(), null);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.androidquery.callback.AbstractAjaxCallback
    public Bitmap transform(String str, byte[] bArr, AjaxStatus ajaxStatus) {
        File file = ajaxStatus.getFile();
        Bitmap bitmapBmGet = bmGet(file != null ? file.getAbsolutePath() : null, bArr);
        if (bitmapBmGet == null) {
            if (this.fallback > 0) {
                bitmapBmGet = getFallback();
            } else if (this.fallback == -2 || this.fallback == -1) {
                bitmapBmGet = dummy;
            } else if (this.fallback == -3) {
                bitmapBmGet = this.preset;
            }
            if (ajaxStatus.getCode() != 200) {
                this.invalid = true;
            }
            if (ajaxStatus.getSource() == 1 && file != null) {
                AQUtility.debug("invalid bm from net");
                file.delete();
            }
        }
        return bitmapBmGet;
    }

    private Bitmap getFallback() {
        ImageView imageView = this.f58v.get();
        if (imageView == null) {
            return null;
        }
        String string = Integer.toString(this.fallback);
        Bitmap bitmapMemGet = memGet(string);
        if (bitmapMemGet != null) {
            return bitmapMemGet;
        }
        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(imageView.getResources(), this.fallback);
        if (bitmapDecodeResource == null) {
            return bitmapDecodeResource;
        }
        memPut(string, bitmapDecodeResource);
        return bitmapDecodeResource;
    }

    public static Bitmap getMemoryCached(Context context, int i) {
        String string = Integer.toString(i);
        Bitmap bitmapMemGet = memGet(string, 0, 0);
        if (bitmapMemGet == null && (bitmapMemGet = BitmapFactory.decodeResource(context.getResources(), i)) != null) {
            memPut(string, 0, 0, bitmapMemGet, false);
        }
        return bitmapMemGet;
    }

    public static Bitmap getEmptyBitmap() {
        return empty;
    }

    @Override // com.androidquery.callback.AbstractAjaxCallback
    public final void callback(String str, Bitmap bitmap, AjaxStatus ajaxStatus) {
        ImageView imageView = this.f58v.get();
        WeakHashMap<ImageView, BitmapAjaxCallback> weakHashMapRemove = queueMap.remove(str);
        if (weakHashMapRemove == null || !weakHashMapRemove.containsKey(imageView)) {
            checkCb(this, str, imageView, bitmap, ajaxStatus);
        }
        if (weakHashMapRemove != null) {
            for (ImageView imageView2 : weakHashMapRemove.keySet()) {
                BitmapAjaxCallback bitmapAjaxCallback = weakHashMapRemove.get(imageView2);
                bitmapAjaxCallback.status = ajaxStatus;
                checkCb(bitmapAjaxCallback, str, imageView2, bitmap, ajaxStatus);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.androidquery.callback.AbstractAjaxCallback
    public void skip(String str, Bitmap bitmap, AjaxStatus ajaxStatus) {
        queueMap.remove(str);
    }

    private void checkCb(BitmapAjaxCallback bitmapAjaxCallback, String str, ImageView imageView, Bitmap bitmap, AjaxStatus ajaxStatus) {
        if (imageView == null || bitmapAjaxCallback == null) {
            return;
        }
        if (str.equals(imageView.getTag(Constants.TAG_URL))) {
            if (imageView instanceof ImageView) {
                bitmapAjaxCallback.callback(str, imageView, bitmap, ajaxStatus);
            } else {
                bitmapAjaxCallback.setBitmap(str, imageView, bitmap, false);
            }
        }
        bitmapAjaxCallback.showProgress(false);
    }

    protected void callback(String str, ImageView imageView, Bitmap bitmap, AjaxStatus ajaxStatus) {
        setBitmap(str, imageView, bitmap, false);
    }

    public static void setIconCacheLimit(int i) {
        SMALL_MAX = i;
        clearCache();
    }

    public static void setCacheLimit(int i) {
        BIG_MAX = i;
        clearCache();
    }

    public static void setDelayWrite(boolean z) {
        DELAY_WRITE = z;
    }

    public static void setPixelLimit(int i) {
        BIG_PIXELS = i;
        clearCache();
    }

    public static void setSmallPixel(int i) {
        SMALL_PIXELS = i;
        clearCache();
    }

    public static void setMaxPixelLimit(int i) {
        BIG_TPIXELS = i;
        clearCache();
    }

    public static void clearCache() {
        bigCache = null;
        smallCache = null;
        invalidCache = null;
    }

    protected static void clearTasks() {
        queueMap.clear();
    }

    private static Map<String, Bitmap> getBCache() {
        if (bigCache == null) {
            bigCache = Collections.synchronizedMap(new BitmapCache(BIG_MAX, BIG_PIXELS, BIG_TPIXELS));
        }
        return bigCache;
    }

    private static Map<String, Bitmap> getSCache() {
        if (smallCache == null) {
            smallCache = Collections.synchronizedMap(new BitmapCache(SMALL_MAX, SMALL_PIXELS, 250000));
        }
        return smallCache;
    }

    private static Map<String, Bitmap> getICache() {
        if (invalidCache == null) {
            invalidCache = Collections.synchronizedMap(new BitmapCache(100, BIG_PIXELS, 250000));
        }
        return invalidCache;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.androidquery.callback.AbstractAjaxCallback
    public Bitmap memGet(String str) {
        if (this.f57bm != null) {
            return this.f57bm;
        }
        if (this.memCache) {
            return memGet(str, this.targetWidth, this.round);
        }
        return null;
    }

    public static boolean isMemoryCached(String str) {
        return getBCache().containsKey(str) || getSCache().containsKey(str) || getICache().containsKey(str);
    }

    public static Bitmap getMemoryCached(String str, int i) {
        return memGet(str, i, 0);
    }

    private static Bitmap memGet(String str, int i, int i2) {
        String key = getKey(str, i, i2);
        Bitmap bitmap = getBCache().get(key);
        if (bitmap == null) {
            bitmap = getSCache().get(key);
        }
        if (bitmap != null) {
            return bitmap;
        }
        Bitmap bitmap2 = getICache().get(key);
        if (bitmap2 == null || getLastStatus() != 200) {
            return bitmap2;
        }
        invalidCache = null;
        return null;
    }

    private static String getKey(String str, int i, int i2) {
        if (i > 0) {
            str = String.valueOf(str) + "#" + i;
        }
        if (i2 <= 0) {
            return str;
        }
        return String.valueOf(str) + "#" + i2;
    }

    private static void memPut(String str, int i, int i2, Bitmap bitmap, boolean z) {
        Map<String, Bitmap> bCache;
        if (bitmap == null) {
            return;
        }
        int width = bitmap.getWidth() * bitmap.getHeight();
        if (z) {
            bCache = getICache();
        } else if (width <= SMALL_PIXELS) {
            bCache = getSCache();
        } else {
            bCache = getBCache();
        }
        if (i > 0 || i2 > 0) {
            bCache.put(getKey(str, i, i2), bitmap);
            if (bCache.containsKey(str)) {
                return;
            }
            bCache.put(str, null);
            return;
        }
        bCache.put(str, bitmap);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.androidquery.callback.AbstractAjaxCallback
    public void memPut(String str, Bitmap bitmap) {
        memPut(str, this.targetWidth, this.round, bitmap, this.invalid);
    }

    private static Bitmap filter(View view, Bitmap bitmap, int i) {
        if (bitmap != null && bitmap.getWidth() == 1 && bitmap.getHeight() == 1 && bitmap != empty) {
            bitmap = null;
        }
        if (bitmap != null) {
            view.setVisibility(0);
        } else if (i == -2) {
            view.setVisibility(8);
        } else if (i == -1) {
            view.setVisibility(4);
        }
        return bitmap;
    }

    private void presetBitmap(String str, ImageView imageView) {
        if (str.equals(imageView.getTag(Constants.TAG_URL)) && this.preset == null) {
            return;
        }
        imageView.setTag(Constants.TAG_URL, str);
        if (this.preset != null && !cacheAvailable(imageView.getContext())) {
            setBitmap(str, imageView, this.preset, true);
        } else {
            setBitmap(str, imageView, null, true);
        }
    }

    private void setBitmap(String str, ImageView imageView, Bitmap bitmap, boolean z) {
        if (bitmap == null) {
            imageView.setImageDrawable(null);
        } else if (z) {
            imageView.setImageDrawable(makeDrawable(imageView, bitmap, this.ratio, this.anchor));
        } else if (this.status != null) {
            setBmAnimate(imageView, bitmap, this.preset, this.fallback, this.animation, this.ratio, this.anchor, this.status.getSource());
        }
    }

    private static Drawable makeDrawable(ImageView imageView, Bitmap bitmap, float f, float f2) {
        if (f > 0.0f) {
            return new RatioDrawable(imageView.getResources(), bitmap, imageView, f, f2);
        }
        return new BitmapDrawable(imageView.getResources(), bitmap);
    }

    private static void setBmAnimate(ImageView imageView, Bitmap bitmap, Bitmap bitmap2, int i, int i2, float f, float f2, int i3) {
        Animation animationLoadAnimation;
        Drawable drawable;
        Drawable drawable2;
        Bitmap bitmapFilter = filter(imageView, bitmap, i);
        if (bitmapFilter == null) {
            imageView.setImageBitmap(null);
            return;
        }
        Drawable drawableMakeDrawable = makeDrawable(imageView, bitmapFilter, f, f2);
        if (fadeIn(i2, i3)) {
            if (bitmap2 == null) {
                animationLoadAnimation = new AlphaAnimation(0.0f, 1.0f);
                animationLoadAnimation.setInterpolator(new DecelerateInterpolator());
                animationLoadAnimation.setDuration(300L);
                drawable2 = drawableMakeDrawable;
            } else {
                TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{makeDrawable(imageView, bitmap2, f, f2), drawableMakeDrawable});
                transitionDrawable.setCrossFadeEnabled(true);
                transitionDrawable.startTransition(FADE_DUR);
                drawable = transitionDrawable;
                animationLoadAnimation = null;
                drawable2 = drawable;
            }
        } else if (i2 > 0) {
            animationLoadAnimation = AnimationUtils.loadAnimation(imageView.getContext(), i2);
            drawable2 = drawableMakeDrawable;
        } else {
            animationLoadAnimation = null;
            drawable2 = drawable;
        }
        imageView.setImageDrawable(drawable2);
        if (animationLoadAnimation != null) {
            animationLoadAnimation.setStartTime(AnimationUtils.currentAnimationTimeMillis());
            imageView.startAnimation(animationLoadAnimation);
        } else {
            imageView.setAnimation(null);
        }
    }

    public static void async(Activity activity, Context context, ImageView imageView, String str, Object obj, AccountHandle accountHandle, ImageOptions imageOptions, HttpHost httpHost, String str2) {
        async(activity, context, imageView, str, imageOptions.memCache, imageOptions.fileCache, imageOptions.targetWidth, imageOptions.fallback, imageOptions.preset, imageOptions.animation, imageOptions.ratio, imageOptions.anchor, obj, accountHandle, imageOptions.policy, imageOptions.round, httpHost, str2);
    }

    public static void async(Activity activity, Context context, ImageView imageView, String str, boolean z, boolean z2, int i, int i2, Bitmap bitmap, int i3, float f, float f2, Object obj, AccountHandle accountHandle, int i4, int i5, HttpHost httpHost, String str2) {
        Bitmap bitmapMemGet = z ? memGet(str, i, i5) : null;
        if (bitmapMemGet != null) {
            imageView.setTag(Constants.TAG_URL, str);
            Common.showProgress(obj, str, false);
            setBmAnimate(imageView, bitmapMemGet, bitmap, i2, i3, f, f2, 4);
            return;
        }
        BitmapAjaxCallback bitmapAjaxCallback = new BitmapAjaxCallback();
        bitmapAjaxCallback.url(str).imageView(imageView).memCache(z).fileCache(z2).targetWidth(i).fallback(i2).preset(bitmap).animation(i3).ratio(f).anchor(f2).progress(obj).auth(accountHandle).policy(i4).round(i5).networkUrl(str2);
        if (httpHost != null) {
            bitmapAjaxCallback.proxy(httpHost.getHostName(), httpHost.getPort());
        }
        if (activity != null) {
            bitmapAjaxCallback.async(activity);
        } else {
            bitmapAjaxCallback.async(context);
        }
    }

    @Override // com.androidquery.callback.AbstractAjaxCallback
    public void async(Context context) {
        String url = getUrl();
        ImageView imageView = this.f58v.get();
        if (url == null) {
            showProgress(false);
            setBitmap(url, imageView, null, false);
            return;
        }
        Bitmap bitmapMemGet = memGet(url);
        if (bitmapMemGet != null) {
            imageView.setTag(Constants.TAG_URL, url);
            this.status = new AjaxStatus().source(4).done();
            callback(url, bitmapMemGet, this.status);
            return;
        }
        presetBitmap(url, imageView);
        if (!queueMap.containsKey(url)) {
            addQueue(url, imageView);
            super.async(imageView.getContext());
        } else {
            showProgress(true);
            addQueue(url, imageView);
        }
    }

    @Override // com.androidquery.callback.AbstractAjaxCallback
    protected boolean isStreamingContent() {
        return !DELAY_WRITE;
    }

    private void addQueue(String str, ImageView imageView) {
        WeakHashMap<ImageView, BitmapAjaxCallback> weakHashMap = queueMap.get(str);
        if (weakHashMap == null) {
            if (queueMap.containsKey(str)) {
                WeakHashMap<ImageView, BitmapAjaxCallback> weakHashMap2 = new WeakHashMap<>();
                weakHashMap2.put(imageView, this);
                queueMap.put(str, weakHashMap2);
                return;
            }
            queueMap.put(str, null);
            return;
        }
        weakHashMap.put(imageView, this);
    }

    private static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int i) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        float f = i;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return bitmapCreateBitmap;
    }
}
