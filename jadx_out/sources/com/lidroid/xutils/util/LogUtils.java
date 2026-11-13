package com.lidroid.xutils.util;

import android.text.TextUtils;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class LogUtils {
    public static boolean allowD = true;
    public static boolean allowE = true;
    public static boolean allowI = true;
    public static boolean allowV = true;
    public static boolean allowW = true;
    public static boolean allowWtf = true;
    public static CustomLogger customLogger = null;
    public static String customTagPrefix = "";

    public interface CustomLogger {
        /* JADX INFO: renamed from: d */
        void m3429d(String str, String str2);

        /* JADX INFO: renamed from: d */
        void m3430d(String str, String str2, Throwable th);

        /* JADX INFO: renamed from: e */
        void m3431e(String str, String str2);

        /* JADX INFO: renamed from: e */
        void m3432e(String str, String str2, Throwable th);

        /* JADX INFO: renamed from: i */
        void m3433i(String str, String str2);

        /* JADX INFO: renamed from: i */
        void m3434i(String str, String str2, Throwable th);

        /* JADX INFO: renamed from: v */
        void m3435v(String str, String str2);

        /* JADX INFO: renamed from: v */
        void m3436v(String str, String str2, Throwable th);

        /* JADX INFO: renamed from: w */
        void m3437w(String str, String str2);

        /* JADX INFO: renamed from: w */
        void m3438w(String str, String str2, Throwable th);

        /* JADX INFO: renamed from: w */
        void m3439w(String str, Throwable th);

        void wtf(String str, String str2);

        void wtf(String str, String str2, Throwable th);

        void wtf(String str, Throwable th);
    }

    private LogUtils() {
    }

    private static String generateTag(StackTraceElement stackTraceElement) {
        String className = stackTraceElement.getClassName();
        String str = String.format("%s.%s(L:%d)", className.substring(className.lastIndexOf(".") + 1), stackTraceElement.getMethodName(), Integer.valueOf(stackTraceElement.getLineNumber()));
        if (TextUtils.isEmpty(customTagPrefix)) {
            return str;
        }
        return String.valueOf(customTagPrefix) + ":" + str;
    }

    /* JADX INFO: renamed from: d */
    public static void m3418d(String str) {
        if (allowD) {
            String strGenerateTag = generateTag(OtherUtils.getCallerStackTraceElement());
            if (customLogger != null) {
                customLogger.m3429d(strGenerateTag, str);
            } else {
                Log.d(strGenerateTag, str);
            }
        }
    }

    /* JADX INFO: renamed from: d */
    public static void m3419d(String str, Throwable th) {
        if (allowD) {
            String strGenerateTag = generateTag(OtherUtils.getCallerStackTraceElement());
            if (customLogger != null) {
                customLogger.m3430d(strGenerateTag, str, th);
            } else {
                Log.d(strGenerateTag, str, th);
            }
        }
    }

    /* JADX INFO: renamed from: e */
    public static void m3420e(String str) {
        if (allowE) {
            String strGenerateTag = generateTag(OtherUtils.getCallerStackTraceElement());
            if (customLogger != null) {
                customLogger.m3431e(strGenerateTag, str);
            } else {
                Log.e(strGenerateTag, str);
            }
        }
    }

    /* JADX INFO: renamed from: e */
    public static void m3421e(String str, Throwable th) {
        if (allowE) {
            String strGenerateTag = generateTag(OtherUtils.getCallerStackTraceElement());
            if (customLogger != null) {
                customLogger.m3432e(strGenerateTag, str, th);
            } else {
                Log.e(strGenerateTag, str, th);
            }
        }
    }

    /* JADX INFO: renamed from: i */
    public static void m3422i(String str) {
        if (allowI) {
            String strGenerateTag = generateTag(OtherUtils.getCallerStackTraceElement());
            if (customLogger != null) {
                customLogger.m3433i(strGenerateTag, str);
            } else {
                Log.i(strGenerateTag, str);
            }
        }
    }

    /* JADX INFO: renamed from: i */
    public static void m3423i(String str, Throwable th) {
        if (allowI) {
            String strGenerateTag = generateTag(OtherUtils.getCallerStackTraceElement());
            if (customLogger != null) {
                customLogger.m3434i(strGenerateTag, str, th);
            } else {
                Log.i(strGenerateTag, str, th);
            }
        }
    }

    /* JADX INFO: renamed from: v */
    public static void m3424v(String str) {
        if (allowV) {
            String strGenerateTag = generateTag(OtherUtils.getCallerStackTraceElement());
            if (customLogger != null) {
                customLogger.m3435v(strGenerateTag, str);
            } else {
                Log.v(strGenerateTag, str);
            }
        }
    }

    /* JADX INFO: renamed from: v */
    public static void m3425v(String str, Throwable th) {
        if (allowV) {
            String strGenerateTag = generateTag(OtherUtils.getCallerStackTraceElement());
            if (customLogger != null) {
                customLogger.m3436v(strGenerateTag, str, th);
            } else {
                Log.v(strGenerateTag, str, th);
            }
        }
    }

    /* JADX INFO: renamed from: w */
    public static void m3426w(String str) {
        if (allowW) {
            String strGenerateTag = generateTag(OtherUtils.getCallerStackTraceElement());
            if (customLogger != null) {
                customLogger.m3437w(strGenerateTag, str);
            } else {
                Log.w(strGenerateTag, str);
            }
        }
    }

    /* JADX INFO: renamed from: w */
    public static void m3427w(String str, Throwable th) {
        if (allowW) {
            String strGenerateTag = generateTag(OtherUtils.getCallerStackTraceElement());
            if (customLogger != null) {
                customLogger.m3438w(strGenerateTag, str, th);
            } else {
                Log.w(strGenerateTag, str, th);
            }
        }
    }

    /* JADX INFO: renamed from: w */
    public static void m3428w(Throwable th) {
        if (allowW) {
            String strGenerateTag = generateTag(OtherUtils.getCallerStackTraceElement());
            if (customLogger != null) {
                customLogger.m3439w(strGenerateTag, th);
            } else {
                Log.w(strGenerateTag, th);
            }
        }
    }

    public static void wtf(String str) {
        if (allowWtf) {
            String strGenerateTag = generateTag(OtherUtils.getCallerStackTraceElement());
            if (customLogger != null) {
                customLogger.wtf(strGenerateTag, str);
            } else {
                Log.wtf(strGenerateTag, str);
            }
        }
    }

    public static void wtf(String str, Throwable th) {
        if (allowWtf) {
            String strGenerateTag = generateTag(OtherUtils.getCallerStackTraceElement());
            if (customLogger != null) {
                customLogger.wtf(strGenerateTag, str, th);
            } else {
                Log.wtf(strGenerateTag, str, th);
            }
        }
    }

    public static void wtf(Throwable th) {
        if (allowWtf) {
            String strGenerateTag = generateTag(OtherUtils.getCallerStackTraceElement());
            if (customLogger != null) {
                customLogger.wtf(strGenerateTag, th);
            } else {
                Log.wtf(strGenerateTag, th);
            }
        }
    }
}
