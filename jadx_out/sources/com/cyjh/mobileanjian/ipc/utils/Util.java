package com.cyjh.mobileanjian.ipc.utils;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class Util {
    public static native String getAppSinature(Context context);

    static {
        System.loadLibrary("mqm");
    }
}
