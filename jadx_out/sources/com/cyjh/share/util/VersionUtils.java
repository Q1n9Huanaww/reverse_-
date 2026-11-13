package com.cyjh.share.util;

import android.content.Context;
import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public class VersionUtils {
    public static boolean isTargetQ(Context context) {
        return Build.VERSION.SDK_INT >= 29 && context.getApplicationInfo().targetSdkVersion >= 29;
    }
}
