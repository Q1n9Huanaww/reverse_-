package com.androidquery.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class RatioDrawable extends BitmapDrawable {
    private boolean adjusted;
    private float anchor;

    /* JADX INFO: renamed from: m */
    private Matrix f67m;
    private float ratio;
    private WeakReference<ImageView> ref;

    /* JADX INFO: renamed from: w */
    private int f68w;

    public RatioDrawable(Resources resources, Bitmap bitmap, ImageView imageView, float f, float f2) {
        super(resources, bitmap);
        this.ref = new WeakReference<>(imageView);
        this.ratio = f;
        this.anchor = f2;
        imageView.setScaleType(ImageView.ScaleType.MATRIX);
        imageView.setImageMatrix(new Matrix());
        adjust(imageView, bitmap, false);
    }

    private int getWidth(ImageView imageView) {
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        int width = layoutParams != null ? layoutParams.width : 0;
        if (width <= 0) {
            width = imageView.getWidth();
        }
        return width > 0 ? (width - imageView.getPaddingLeft()) - imageView.getPaddingRight() : width;
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        ImageView imageView = this.ref != null ? this.ref.get() : null;
        if (this.ratio == 0.0f || imageView == null) {
            super.draw(canvas);
        } else {
            draw(canvas, imageView, getBitmap());
        }
    }

    private void draw(Canvas canvas, ImageView imageView, Bitmap bitmap) {
        Matrix matrix = getMatrix(imageView, bitmap);
        if (matrix != null) {
            int paddingTop = imageView.getPaddingTop() + imageView.getPaddingBottom();
            int paddingLeft = imageView.getPaddingLeft() + imageView.getPaddingRight();
            if (paddingTop > 0 || paddingLeft > 0) {
                canvas.clipRect(0, 0, imageView.getWidth() - paddingLeft, imageView.getHeight() - paddingTop);
            }
            canvas.drawBitmap(bitmap, matrix, getPaint());
        }
        if (this.adjusted) {
            return;
        }
        adjust(imageView, bitmap, true);
    }

    private void adjust(ImageView imageView, Bitmap bitmap, boolean z) {
        int width = getWidth(imageView);
        if (width <= 0) {
            return;
        }
        int iTargetHeight = targetHeight(bitmap.getWidth(), bitmap.getHeight(), width) + imageView.getPaddingTop() + imageView.getPaddingBottom();
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        if (layoutParams == null) {
            return;
        }
        if (iTargetHeight != layoutParams.height) {
            layoutParams.height = iTargetHeight;
            imageView.setLayoutParams(layoutParams);
        }
        if (z) {
            this.adjusted = true;
        }
    }

    private int targetHeight(int i, int i2, int i3) {
        float f = this.ratio;
        if (this.ratio == Float.MAX_VALUE) {
            f = i2 / i;
        }
        return (int) (i3 * f);
    }

    private Matrix getMatrix(ImageView imageView, Bitmap bitmap) {
        float f;
        float yOffset;
        int width = bitmap.getWidth();
        if (this.f67m != null && width == this.f68w) {
            return this.f67m;
        }
        int height = bitmap.getHeight();
        int width2 = getWidth(imageView);
        int iTargetHeight = targetHeight(width, height, width2);
        if (width <= 0 || height <= 0 || width2 <= 0 || iTargetHeight <= 0) {
            return null;
        }
        if (this.f67m == null || width != this.f68w) {
            this.f67m = new Matrix();
            float f2 = 0.0f;
            if (width * iTargetHeight >= width2 * height) {
                float f3 = iTargetHeight / height;
                f2 = (width2 - (width * f3)) * 0.5f;
                f = f3;
                yOffset = 0.0f;
            } else {
                f = width2 / width;
                yOffset = (iTargetHeight - (height * f)) * getYOffset(width, height);
            }
            this.f67m.setScale(f, f);
            this.f67m.postTranslate(f2, yOffset);
            this.f68w = width;
        }
        return this.f67m;
    }

    private float getYOffset(int i, int i2) {
        if (this.anchor != Float.MAX_VALUE) {
            return (1.0f - this.anchor) / 2.0f;
        }
        return ((1.5f - Math.max(1.0f, Math.min(1.5f, i2 / i))) / 2.0f) + 0.25f;
    }
}
