package org.wlf.filedownloader.base;

/* JADX INFO: loaded from: classes2.dex */
public final class Log {
    private static boolean sIsDebugMode = true;

    public static void setDebugMode(boolean isDebugMode) {
        sIsDebugMode = isDebugMode;
    }

    /* JADX INFO: renamed from: v */
    public static int m5162v(String tag, String msg) {
        if (sIsDebugMode) {
            return android.util.Log.v(tag, msg);
        }
        return -1;
    }

    /* JADX INFO: renamed from: v */
    public static int m5163v(String tag, String msg, Throwable tr) {
        if (sIsDebugMode) {
            return android.util.Log.v(tag, msg, tr);
        }
        return -1;
    }

    /* JADX INFO: renamed from: d */
    public static int m5156d(String tag, String msg) {
        if (sIsDebugMode) {
            return android.util.Log.d(tag, msg);
        }
        return -1;
    }

    /* JADX INFO: renamed from: d */
    public static int m5157d(String tag, String msg, Throwable tr) {
        if (sIsDebugMode) {
            return android.util.Log.d(tag, msg, tr);
        }
        return -1;
    }

    /* JADX INFO: renamed from: i */
    public static int m5160i(String tag, String msg) {
        if (sIsDebugMode) {
            return android.util.Log.i(tag, msg);
        }
        return -1;
    }

    /* JADX INFO: renamed from: i */
    public static int m5161i(String tag, String msg, Throwable tr) {
        if (sIsDebugMode) {
            return android.util.Log.i(tag, msg, tr);
        }
        return -1;
    }

    /* JADX INFO: renamed from: w */
    public static int m5164w(String tag, String msg) {
        if (sIsDebugMode) {
            return android.util.Log.w(tag, msg);
        }
        return -1;
    }

    /* JADX INFO: renamed from: w */
    public static int m5165w(String tag, String msg, Throwable tr) {
        if (sIsDebugMode) {
            return android.util.Log.w(tag, msg, tr);
        }
        return -1;
    }

    /* JADX INFO: renamed from: w */
    public static int m5166w(String tag, Throwable tr) {
        if (sIsDebugMode) {
            return android.util.Log.w(tag, tr);
        }
        return -1;
    }

    /* JADX INFO: renamed from: e */
    public static int m5158e(String tag, String msg) {
        if (sIsDebugMode) {
            return android.util.Log.e(tag, msg);
        }
        return -1;
    }

    /* JADX INFO: renamed from: e */
    public static int m5159e(String tag, String msg, Throwable tr) {
        if (sIsDebugMode) {
            return android.util.Log.e(tag, msg, tr);
        }
        return -1;
    }
}
