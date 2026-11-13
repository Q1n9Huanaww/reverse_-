package com.umeng.commonsdk.statistics.common;

import android.text.TextUtils;
import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Formatter;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class MLog {
    public static boolean DEBUG = false;
    private static final int LEVEL_DEBUG = 2;
    private static final int LEVEL_ERROR = 5;
    private static final int LEVEL_INFO = 3;
    private static final int LEVEL_VERBOSE = 1;
    private static final int LEVEL_WARN = 4;
    private static int LOG_MAXLENGTH = 2000;
    private static String TAG = "MobclickAgent";

    private MLog() {
    }

    /* JADX INFO: renamed from: i */
    public static void m4348i(Locale locale, String str, Object... objArr) throws Throwable {
        try {
            m4344i(TAG, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            m4341e(th);
        }
    }

    /* JADX INFO: renamed from: d */
    public static void m4336d(Locale locale, String str, Object... objArr) throws Throwable {
        try {
            m4332d(TAG, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            m4341e(th);
        }
    }

    /* JADX INFO: renamed from: e */
    public static void m4342e(Locale locale, String str, Object... objArr) throws Throwable {
        try {
            m4338e(TAG, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            m4341e(th);
        }
    }

    /* JADX INFO: renamed from: v */
    public static void m4354v(Locale locale, String str, Object... objArr) throws Throwable {
        try {
            m4350v(TAG, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            m4341e(th);
        }
    }

    /* JADX INFO: renamed from: w */
    public static void m4360w(Locale locale, String str, Object... objArr) throws Throwable {
        try {
            m4356w(TAG, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            m4341e(th);
        }
    }

    /* JADX INFO: renamed from: i */
    public static void m4346i(String str, Object... objArr) throws Throwable {
        try {
            if (str.contains("%")) {
                m4344i(TAG, new Formatter().format(str, objArr).toString(), (Throwable) null);
            } else {
                m4344i(str, objArr != null ? (String) objArr[0] : "", (Throwable) null);
            }
        } catch (Throwable th) {
            m4341e(th);
        }
    }

    /* JADX INFO: renamed from: d */
    public static void m4334d(String str, Object... objArr) throws Throwable {
        try {
            if (str.contains("%")) {
                m4332d(TAG, new Formatter().format(str, objArr).toString(), (Throwable) null);
            } else {
                m4332d(str, objArr != null ? (String) objArr[0] : "", (Throwable) null);
            }
        } catch (Throwable th) {
            m4341e(th);
        }
    }

    /* JADX INFO: renamed from: e */
    public static void m4340e(String str, Object... objArr) throws Throwable {
        try {
            if (str.contains("%")) {
                m4338e(TAG, new Formatter().format(str, objArr).toString(), (Throwable) null);
            } else {
                m4338e(str, objArr != null ? (String) objArr[0] : "", (Throwable) null);
            }
        } catch (Throwable th) {
            m4341e(th);
        }
    }

    /* JADX INFO: renamed from: v */
    public static void m4352v(String str, Object... objArr) throws Throwable {
        try {
            if (str.contains("%")) {
                m4350v(TAG, new Formatter().format(str, objArr).toString(), (Throwable) null);
            } else {
                m4350v(str, objArr != null ? (String) objArr[0] : "", (Throwable) null);
            }
        } catch (Throwable th) {
            m4341e(th);
        }
    }

    /* JADX INFO: renamed from: w */
    public static void m4358w(String str, Object... objArr) throws Throwable {
        try {
            if (str.contains("%")) {
                m4356w(TAG, new Formatter().format(str, objArr).toString(), (Throwable) null);
            } else {
                m4356w(str, objArr != null ? (String) objArr[0] : "", (Throwable) null);
            }
        } catch (Throwable th) {
            m4341e(th);
        }
    }

    /* JADX INFO: renamed from: i */
    public static void m4347i(Throwable th) throws Throwable {
        m4344i(TAG, (String) null, th);
    }

    /* JADX INFO: renamed from: v */
    public static void m4353v(Throwable th) throws Throwable {
        m4350v(TAG, (String) null, th);
    }

    /* JADX INFO: renamed from: w */
    public static void m4359w(Throwable th) throws Throwable {
        m4356w(TAG, (String) null, th);
    }

    /* JADX INFO: renamed from: d */
    public static void m4335d(Throwable th) throws Throwable {
        m4332d(TAG, (String) null, th);
    }

    /* JADX INFO: renamed from: e */
    public static void m4341e(Throwable th) throws Throwable {
        m4338e(TAG, (String) null, th);
    }

    /* JADX INFO: renamed from: i */
    public static void m4345i(String str, Throwable th) throws Throwable {
        m4344i(TAG, str, th);
    }

    /* JADX INFO: renamed from: v */
    public static void m4351v(String str, Throwable th) throws Throwable {
        m4350v(TAG, str, th);
    }

    /* JADX INFO: renamed from: w */
    public static void m4357w(String str, Throwable th) throws Throwable {
        m4356w(TAG, str, th);
    }

    /* JADX INFO: renamed from: d */
    public static void m4333d(String str, Throwable th) throws Throwable {
        m4332d(TAG, str, th);
    }

    /* JADX INFO: renamed from: e */
    public static void m4339e(String str, Throwable th) throws Throwable {
        m4338e(TAG, str, th);
    }

    /* JADX INFO: renamed from: v */
    public static void m4349v(String str) throws Throwable {
        m4350v(TAG, str, (Throwable) null);
    }

    /* JADX INFO: renamed from: d */
    public static void m4331d(String str) throws Throwable {
        m4332d(TAG, str, (Throwable) null);
    }

    /* JADX INFO: renamed from: i */
    public static void m4343i(String str) throws Throwable {
        m4344i(TAG, str, (Throwable) null);
    }

    /* JADX INFO: renamed from: w */
    public static void m4355w(String str) throws Throwable {
        m4356w(TAG, str, (Throwable) null);
    }

    /* JADX INFO: renamed from: e */
    public static void m4337e(String str) throws Throwable {
        m4338e(TAG, str, (Throwable) null);
    }

    /* JADX INFO: renamed from: v */
    public static void m4350v(String str, String str2, Throwable th) throws Throwable {
        if (DEBUG) {
            print(1, str, str2, th);
        }
    }

    /* JADX INFO: renamed from: d */
    public static void m4332d(String str, String str2, Throwable th) throws Throwable {
        if (DEBUG) {
            print(2, str, str2, th);
        }
    }

    /* JADX INFO: renamed from: i */
    public static void m4344i(String str, String str2, Throwable th) throws Throwable {
        if (DEBUG) {
            print(3, str, str2, th);
        }
    }

    /* JADX INFO: renamed from: w */
    public static void m4356w(String str, String str2, Throwable th) throws Throwable {
        if (DEBUG) {
            print(4, str, str2, th);
        }
    }

    /* JADX INFO: renamed from: e */
    public static void m4338e(String str, String str2, Throwable th) throws Throwable {
        if (DEBUG) {
            print(5, str, str2, th);
        }
    }

    private static void print(int i, String str, String str2, Throwable th) throws Throwable {
        if (!TextUtils.isEmpty(str2)) {
            int length = str2.length();
            int i2 = LOG_MAXLENGTH;
            int i3 = 0;
            int i4 = 0;
            while (i3 < 100) {
                if (length > i2) {
                    switch (i) {
                        case 1:
                            Log.v(str, str2.substring(i4, i2));
                            break;
                        case 2:
                            Log.d(str, str2.substring(i4, i2));
                            break;
                        case 3:
                            Log.i(str, str2.substring(i4, i2));
                            break;
                        case 4:
                            Log.w(str, str2.substring(i4, i2));
                            break;
                        case 5:
                            Log.e(str, str2.substring(i4, i2));
                            break;
                    }
                    i3++;
                    i4 = i2;
                    i2 = LOG_MAXLENGTH + i2;
                } else {
                    switch (i) {
                        case 1:
                            Log.v(str, str2.substring(i4, length));
                            break;
                        case 2:
                            Log.d(str, str2.substring(i4, length));
                            break;
                        case 3:
                            Log.i(str, str2.substring(i4, length));
                            break;
                        case 4:
                            Log.w(str, str2.substring(i4, length));
                            break;
                        case 5:
                            Log.e(str, str2.substring(i4, length));
                            break;
                    }
                }
            }
        }
        if (th != null) {
            String stackTrace = getStackTrace(th);
            if (TextUtils.isEmpty(stackTrace)) {
                return;
            }
            switch (i) {
                case 1:
                    Log.v(str, stackTrace);
                    break;
                case 2:
                    Log.d(str, stackTrace);
                    break;
                case 3:
                    Log.i(str, stackTrace);
                    break;
                case 4:
                    Log.w(str, stackTrace);
                    break;
                case 5:
                    Log.e(str, stackTrace);
                    break;
            }
        }
    }

    public static String getStackTrace(Throwable th) throws Throwable {
        StringWriter stringWriter;
        PrintWriter printWriter = null;
        try {
            stringWriter = new StringWriter();
            try {
                PrintWriter printWriter2 = new PrintWriter(stringWriter);
                try {
                    ThrowableExtension.printStackTrace(th, printWriter2);
                    printWriter2.flush();
                    stringWriter.flush();
                    String string = stringWriter.toString();
                    if (stringWriter != null) {
                        try {
                            stringWriter.close();
                        } catch (Throwable unused) {
                        }
                    }
                    if (printWriter2 == null) {
                        return string;
                    }
                    printWriter2.close();
                    return string;
                } catch (Throwable unused2) {
                    printWriter = printWriter2;
                    if (stringWriter != null) {
                        try {
                            stringWriter.close();
                        } catch (Throwable unused3) {
                        }
                    }
                    if (printWriter != null) {
                        printWriter.close();
                    }
                    return "";
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable unused4) {
            stringWriter = null;
        }
    }
}
