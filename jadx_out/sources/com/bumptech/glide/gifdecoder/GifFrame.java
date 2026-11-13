package com.bumptech.glide.gifdecoder;

import android.support.annotation.ColorInt;

/* JADX INFO: loaded from: classes.dex */
class GifFrame {
    static final int DISPOSAL_BACKGROUND = 2;
    static final int DISPOSAL_NONE = 1;
    static final int DISPOSAL_PREVIOUS = 3;
    static final int DISPOSAL_UNSPECIFIED = 0;
    int bufferFrameStart;
    int delay;
    int dispose;

    /* JADX INFO: renamed from: ih */
    int f861ih;
    boolean interlace;

    /* JADX INFO: renamed from: iw */
    int f862iw;

    /* JADX INFO: renamed from: ix */
    int f863ix;

    /* JADX INFO: renamed from: iy */
    int f864iy;

    @ColorInt
    int[] lct;
    int transIndex;
    boolean transparency;

    GifFrame() {
    }
}
