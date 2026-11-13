package com.p024ds.daisi.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.p024ds.daisi.interfaces.GlideLoadBitmapCallback;
import com.p024ds.daisi.load_model.GlideApp;
import com.warnship.autoplay.R;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class GlideUtils {
    public static RequestOptions getDefaultOption() {
        return new RequestOptions().placeholder(R.drawable.bg_normal_pic).error(R.drawable.bg_normal_pic).priority(Priority.NORMAL);
    }

    public static RequestOptions getPhotoImageOption() {
        return new RequestOptions().centerCrop().placeholder(R.drawable.bg_normal_pic).error(R.drawable.bg_normal_pic).priority(Priority.NORMAL);
    }

    private GlideUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void loadGif(Context context, String str, ImageView imageView) {
        if (isValidContextForGlide(context)) {
            GlideApp.with(context).asGif().load(str).into(imageView);
        }
    }

    public static void loadGif(Context context, String str, ImageView imageView, RequestOptions requestOptions) {
        if (isValidContextForGlide(context)) {
            GlideApp.with(context).asGif().load(str).apply(requestOptions).into(imageView);
        }
    }

    public static void load(Context context, String str, ImageView imageView) {
        if (isValidContextForGlide(context)) {
            GlideApp.with(context).load(str).apply(getDefaultOption()).into(imageView);
        }
    }

    public static void load(Context context, int i, ImageView imageView) {
        if (isValidContextForGlide(context)) {
            GlideApp.with(context).load(Integer.valueOf(i)).apply(getDefaultOption()).into(imageView);
        }
    }

    public static void load(Context context, int i, ImageView imageView, RequestOptions requestOptions) {
        if (isValidContextForGlide(context)) {
            GlideApp.with(context).load(Integer.valueOf(i)).apply(requestOptions).into(imageView);
        }
    }

    public static void load(Context context, File file, ImageView imageView) {
        if (isValidContextForGlide(context)) {
            GlideApp.with(context).load(file).apply(getDefaultOption()).into(imageView);
        }
    }

    public static void load(Context context, String str, ImageView imageView, Transformation transformation) {
        if (isValidContextForGlide(context)) {
            GlideApp.with(context).load(str).apply(new RequestOptions().placeholder(R.drawable.bg_normal_pic).error(R.drawable.bg_normal_pic).priority(Priority.NORMAL).transform(transformation)).into(imageView);
        }
    }

    public static void load(Context context, String str, ImageView imageView, RequestOptions requestOptions) {
        if (isValidContextForGlide(context)) {
            GlideApp.with(context).load(str).apply(requestOptions).into(imageView);
        }
    }

    public static void load(Context context, byte[] bArr, ImageView imageView) {
        if (isValidContextForGlide(context)) {
            GlideApp.with(context).load(bArr).apply(new RequestOptions().placeholder(R.drawable.bg_normal_pic).error(R.drawable.bg_normal_pic).priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.ALL)).into(imageView);
        }
    }

    public static boolean isValidContextForGlide(Context context) {
        if (context == null) {
            return false;
        }
        if (!(context instanceof Activity)) {
            return true;
        }
        Activity activity = (Activity) context;
        if (Build.VERSION.SDK_INT >= 17) {
            return (activity.isDestroyed() || activity.isFinishing()) ? false : true;
        }
        return !activity.isFinishing();
    }

    public static void getBitmap(Context context, String str, RequestOptions requestOptions, final GlideLoadBitmapCallback glideLoadBitmapCallback) {
        if (isValidContextForGlide(context)) {
            GlideApp.with(context).asBitmap().load(str).apply(requestOptions).into(new SimpleTarget<Bitmap>() { // from class: com.ds.daisi.util.GlideUtils.1
                @Override // com.bumptech.glide.request.target.Target
                public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
                    onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                }

                public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                    if (glideLoadBitmapCallback != null) {
                        glideLoadBitmapCallback.getBitmapCallback(bitmap);
                    }
                }
            });
        }
    }

    @SuppressLint({"CheckResult"})
    public static void preload(Context context, String str, final RequestListener<File> requestListener) {
        if (isValidContextForGlide(context)) {
            RequestBuilder<File> requestBuilderDownloadOnly = Glide.with(context).downloadOnly();
            requestBuilderDownloadOnly.load(str);
            requestBuilderDownloadOnly.listener(new RequestListener<File>() { // from class: com.ds.daisi.util.GlideUtils.2
                @Override // com.bumptech.glide.request.RequestListener
                public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<File> target, boolean z) {
                    if (requestListener != null) {
                        return requestListener.onLoadFailed(glideException, obj, target, z);
                    }
                    return false;
                }

                @Override // com.bumptech.glide.request.RequestListener
                public boolean onResourceReady(File file, Object obj, Target<File> target, DataSource dataSource, boolean z) {
                    if (requestListener != null) {
                        return requestListener.onResourceReady(file, obj, target, dataSource, z);
                    }
                    return false;
                }
            });
            requestBuilderDownloadOnly.preload();
        }
    }
}
