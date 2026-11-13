package com.cyjh.share.helper;

import android.content.Context;
import com.cyjh.share.util.AppUtils;
import com.cyjh.share.util.SlLog;

/* JADX INFO: loaded from: classes.dex */
public class RedFingerHelper {
    private static String TAG = "RedFingerHelper";
    private static RedFingerHelper instance;
    private int mRedFingerSwitch;

    private RedFingerHelper() {
    }

    public static RedFingerHelper get() {
        if (instance == null) {
            synchronized (RedFingerHelper.class) {
                if (instance == null) {
                    instance = new RedFingerHelper();
                }
            }
        }
        return instance;
    }

    public RedFingerHelper init(int i) {
        SlLog.m1334i(TAG, "init --> redFingerSwitch=" + i);
        this.mRedFingerSwitch = i;
        return this;
    }

    public boolean isRunningDisable(Context context) {
        return AppUtils.isPackageInstalled(context, "com.redfinger.appstore") && this.mRedFingerSwitch == 1;
    }
}
