package com.android.volley.toolbox;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.android.volley.VolleyError;

/* JADX INFO: loaded from: classes.dex */
public class NetworkImageView extends ImageView {
    private int mDefaultImageId;
    private int mErrorImageId;
    private ImageLoader.ImageContainer mImageContainer;
    private ImageLoader mImageLoader;
    private ImageRequest.Transformation mTransformation;
    private String mUrl;
    private ImageURLBuilder mUrlBuilder;

    public interface ImageURLBuilder {
        String buildUrl(String str, int i, int i2, ImageView.ScaleType scaleType, Context context);
    }

    public NetworkImageView(Context context) {
        this(context, null);
    }

    public NetworkImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NetworkImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setImageUrl(String str, ImageLoader imageLoader) {
        setImageUrl(str, imageLoader, null);
    }

    public void setImageUrl(String str, ImageLoader imageLoader, ImageURLBuilder imageURLBuilder) {
        this.mUrl = str;
        this.mImageLoader = imageLoader;
        this.mUrlBuilder = imageURLBuilder;
        loadImageIfNecessary(false);
    }

    public void setDefaultImageResId(int i) {
        this.mDefaultImageId = i;
    }

    public void setErrorImageResId(int i) {
        this.mErrorImageId = i;
    }

    public void setTransformation(ImageRequest.Transformation transformation) {
        this.mTransformation = transformation;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0031  */
    /* JADX WARN: Code duplicated, block: B:23:0x0041  */
    /* JADX WARN: Code duplicated, block: B:25:0x0045  */
    /* JADX WARN: Code duplicated, block: B:28:0x0051 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:29:0x0053  */
    /* JADX WARN: Code duplicated, block: B:30:0x0055  */
    /* JADX WARN: Code duplicated, block: B:32:0x0058  */
    /* JADX WARN: Code duplicated, block: B:33:0x005a  */
    /* JADX WARN: Code duplicated, block: B:36:0x0061  */
    /* JADX WARN: Code duplicated, block: B:43:0x0090 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:44:0x0091  */
    void loadImageIfNecessary(boolean z) {
        boolean z2;
        boolean z3;
        int i;
        int i2;
        String strBuildUrl;
        String str;
        int width = getWidth();
        int height = getHeight();
        ImageView.ScaleType scaleType = getScaleType();
        if (getLayoutParams() != null) {
            z2 = getLayoutParams().width == -2;
            z3 = getLayoutParams().height == -2;
            boolean z4 = !z2 && z3;
            if (width == 0 || height != 0 || z4) {
                if (TextUtils.isEmpty(this.mUrl)) {
                    if (this.mImageContainer != null) {
                        this.mImageContainer.cancelRequest();
                        this.mImageContainer = null;
                    }
                    setDefaultImageOrNull();
                    return;
                }
                if (z2) {
                    i = 0;
                } else {
                    i = width;
                }
                if (z3) {
                    i2 = 0;
                } else {
                    i2 = height;
                }
                strBuildUrl = this.mUrl;
                if (this.mUrlBuilder != null) {
                    strBuildUrl = this.mUrlBuilder.buildUrl(this.mUrl, i, i2, getScaleType(), getContext().getApplicationContext());
                }
                str = strBuildUrl;
                if (this.mImageContainer != null && this.mImageContainer.getRequestUrl() != null) {
                    if (this.mImageContainer.getRequestUrl().equals(str)) {
                        return;
                    }
                    this.mImageContainer.cancelRequest();
                    setDefaultImageOrNull();
                }
                this.mImageContainer = this.mImageLoader.get(str, new C04761(z), i, i2, scaleType, this.mTransformation);
            }
            return;
        }
        z2 = false;
        if (z2) {
        }
        if (width == 0) {
        }
        if (TextUtils.isEmpty(this.mUrl)) {
            if (this.mImageContainer != null) {
                this.mImageContainer.cancelRequest();
                this.mImageContainer = null;
            }
            setDefaultImageOrNull();
            return;
        }
        if (z2) {
            i = 0;
        } else {
            i = width;
        }
        if (z3) {
            i2 = 0;
        } else {
            i2 = height;
        }
        strBuildUrl = this.mUrl;
        if (this.mUrlBuilder != null) {
            strBuildUrl = this.mUrlBuilder.buildUrl(this.mUrl, i, i2, getScaleType(), getContext().getApplicationContext());
        }
        str = strBuildUrl;
        if (this.mImageContainer != null) {
            if (this.mImageContainer.getRequestUrl().equals(str)) {
                return;
            }
            this.mImageContainer.cancelRequest();
            setDefaultImageOrNull();
        }
        this.mImageContainer = this.mImageLoader.get(str, new C04761(z), i, i2, scaleType, this.mTransformation);
    }

    /* JADX INFO: renamed from: com.android.volley.toolbox.NetworkImageView$1 */
    class C04761 implements ImageLoader.ImageListener {
        final /* synthetic */ boolean val$isInLayoutPass;

        C04761(boolean z) {
            this.val$isInLayoutPass = z;
        }

        @Override // com.android.volley.Response.ErrorListener
        public void onErrorResponse(VolleyError volleyError) {
            if (NetworkImageView.this.mErrorImageId != 0) {
                NetworkImageView.this.setImageResource(NetworkImageView.this.mErrorImageId);
            }
        }

        @Override // com.android.volley.toolbox.ImageLoader.ImageListener
        public void onResponse(final ImageLoader.ImageContainer imageContainer, boolean z) {
            if (z && this.val$isInLayoutPass) {
                NetworkImageView.this.post(new Runnable() { // from class: com.android.volley.toolbox.NetworkImageView.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        C04761.this.onResponse(imageContainer, false);
                    }
                });
            } else {
                if (imageContainer.getBitmap() == null) {
                    if (NetworkImageView.this.mDefaultImageId != 0) {
                        NetworkImageView.this.setImageResource(NetworkImageView.this.mDefaultImageId);
                        return;
                    }
                    return;
                }
                NetworkImageView.this.setImageBitmap(imageContainer.getBitmap());
            }
        }
    }

    private void setDefaultImageOrNull() {
        if (this.mDefaultImageId != 0) {
            setImageResource(this.mDefaultImageId);
        } else {
            setImageBitmap(null);
        }
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        loadImageIfNecessary(true);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        if (this.mImageContainer != null) {
            this.mImageContainer.cancelRequest();
            setImageBitmap(null);
            this.mImageContainer = null;
        }
        super.onDetachedFromWindow();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }
}
