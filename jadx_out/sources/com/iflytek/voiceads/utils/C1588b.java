package com.iflytek.voiceads.utils;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import com.iflytek.voiceads.config.SDKConstants;
import java.util.List;

/* JADX INFO: renamed from: com.iflytek.voiceads.utils.b */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1588b {
    /* JADX INFO: renamed from: a */
    public static Rect m3164a(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return new Rect(iArr[0], iArr[1], iArr[0] + view.getWidth(), iArr[1] + view.getHeight());
    }

    /* JADX INFO: renamed from: a */
    public static boolean m3165a(Context context) {
        String packageName = context.getPackageName();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return true;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.processName.equals(packageName)) {
                return runningAppProcessInfo.importance != 100;
            }
        }
        return true;
    }

    /* JADX INFO: renamed from: a */
    public static boolean m3166a(Context context, Intent intent) {
        try {
            return !context.getPackageManager().queryIntentActivities(intent, 0).isEmpty();
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "deviceCanHandleIntent:" + e.getMessage());
            return false;
        }
    }

    /* JADX INFO: renamed from: a */
    public static boolean m3167a(Context context, View view) {
        Rect rectM3171c = m3171c(context);
        Rect rectM3164a = m3164a(view);
        int i = (int) (((double) (rectM3164a.bottom - rectM3164a.top)) * 0.3d);
        Rect rect = new Rect(rectM3171c.left, rectM3171c.top + i, rectM3171c.right, rectM3171c.bottom - i);
        return Rect.intersects(rect, rectM3164a) || rect.contains(rectM3164a);
    }

    /* JADX INFO: renamed from: a */
    public static boolean m3168a(Context context, String str) {
        try {
            if (context.getPackageManager().getPackageArchiveInfo(str, 1) != null) {
                C1593g.m3190b(SDKConstants.TAG, "checkUninstallApk info not null");
                return true;
            }
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "checkUninstallApk error:" + e.getMessage());
        }
        C1593g.m3190b(SDKConstants.TAG, "checkUninstallApk info is null");
        return false;
    }

    /* JADX INFO: renamed from: a */
    public static boolean m3169a(String str) {
        return (TextUtils.isEmpty(str) || str.startsWith("http:") || str.startsWith("https:")) ? false : true;
    }

    /* JADX INFO: renamed from: b */
    public static boolean m3170b(Context context) {
        return ((KeyguardManager) context.getSystemService("keyguard")).inKeyguardRestrictedInputMode();
    }

    /* JADX INFO: renamed from: c */
    public static Rect m3171c(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        return new Rect(0, 0, defaultDisplay.getWidth(), defaultDisplay.getHeight());
    }
}
