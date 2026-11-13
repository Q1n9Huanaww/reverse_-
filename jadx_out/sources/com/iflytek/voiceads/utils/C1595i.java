package com.iflytek.voiceads.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.download.DownloadService;
import com.iflytek.voiceads.request.IFLYBrowser;

/* JADX INFO: renamed from: com.iflytek.voiceads.utils.i */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1595i {

    /* JADX INFO: renamed from: a */
    private static String[] f3423a = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.READ_PHONE_STATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.GET_TASKS", "android.permission.RECORD_AUDIO", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.REQUEST_INSTALL_PACKAGES"};

    /* JADX INFO: renamed from: a */
    private static void m3196a(Context context, String[] strArr) {
        for (String str : strArr) {
            if (!m3199a(context, str)) {
                C1593g.m3190b(SDKConstants.TAG, str + " must be enabled in AndroidManifest.xml");
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public static boolean m3197a(Context context) {
        m3196a(context, f3423a);
        return m3198a(context, (Class<?>) DownloadService.class) && m3200b(context, (Class<?>) IFLYBrowser.class);
    }

    /* JADX INFO: renamed from: a */
    public static boolean m3198a(Context context, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        if (context.getPackageManager().resolveService(intent, 65536) != null) {
            return true;
        }
        C1593g.m3190b(SDKConstants.TAG, "Can not found " + cls.toString() + ", please set it in AndroidManifest.xml");
        return false;
    }

    /* JADX INFO: renamed from: a */
    public static boolean m3199a(Context context, String str) {
        return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
    }

    /* JADX INFO: renamed from: b */
    public static boolean m3200b(Context context, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        if (context.getPackageManager().resolveActivity(intent, 65536) != null) {
            return true;
        }
        C1593g.m3190b(SDKConstants.TAG, "Can not found " + cls.toString() + ", please set it in AndroidManifest.xml");
        return false;
    }

    /* JADX INFO: renamed from: b */
    public static boolean m3201b(Context context, String str) {
        boolean zM3199a = false;
        try {
            int i = context.getApplicationInfo().targetSdkVersion;
            if (Build.VERSION.SDK_INT < 23 || i < 23) {
                zM3199a = m3199a(context, str);
            } else if (context.checkCallingOrSelfPermission(str) == 0) {
                zM3199a = true;
            }
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "checkPermissionGrant:" + e.getMessage());
        }
        return zM3199a;
    }
}
