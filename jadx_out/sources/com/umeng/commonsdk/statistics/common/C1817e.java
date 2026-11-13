package com.umeng.commonsdk.statistics.common;

import android.text.TextUtils;
import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Formatter;
import java.util.Locale;

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.common.e */
/* JADX INFO: compiled from: ULog.java */
/* JADX INFO: loaded from: classes.dex */
public class C1817e {

    /* JADX INFO: renamed from: a */
    public static boolean f4515a = false;

    /* JADX INFO: renamed from: b */
    private static String f4516b = "ULog";

    /* JADX INFO: renamed from: c */
    private static final int f4517c = 1;

    /* JADX INFO: renamed from: d */
    private static final int f4518d = 2;

    /* JADX INFO: renamed from: e */
    private static final int f4519e = 3;

    /* JADX INFO: renamed from: f */
    private static final int f4520f = 4;

    /* JADX INFO: renamed from: g */
    private static final int f4521g = 5;

    /* JADX INFO: renamed from: h */
    private static int f4522h = 2000;

    private C1817e() {
    }

    /* JADX INFO: renamed from: a */
    public static void m4399a(Locale locale, String str, Object... objArr) throws Throwable {
        try {
            m4407c(f4516b, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            m4422e(th);
        }
    }

    /* JADX INFO: renamed from: b */
    public static void m4405b(Locale locale, String str, Object... objArr) throws Throwable {
        try {
            m4401b(f4516b, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            m4422e(th);
        }
    }

    /* JADX INFO: renamed from: c */
    public static void m4411c(Locale locale, String str, Object... objArr) throws Throwable {
        try {
            m4419e(f4516b, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            m4422e(th);
        }
    }

    /* JADX INFO: renamed from: d */
    public static void m4417d(Locale locale, String str, Object... objArr) throws Throwable {
        try {
            m4395a(f4516b, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            m4422e(th);
        }
    }

    /* JADX INFO: renamed from: e */
    public static void m4423e(Locale locale, String str, Object... objArr) throws Throwable {
        try {
            m4413d(f4516b, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            m4422e(th);
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m4397a(String str, Object... objArr) throws Throwable {
        try {
            if (str.contains("%")) {
                m4407c(f4516b, new Formatter().format(str, objArr).toString(), (Throwable) null);
            } else {
                m4407c(str, objArr != null ? (String) objArr[0] : "", (Throwable) null);
            }
        } catch (Throwable th) {
            m4422e(th);
        }
    }

    /* JADX INFO: renamed from: b */
    public static void m4403b(String str, Object... objArr) throws Throwable {
        try {
            if (str.contains("%")) {
                m4401b(f4516b, new Formatter().format(str, objArr).toString(), (Throwable) null);
            } else {
                m4401b(str, objArr != null ? (String) objArr[0] : "", (Throwable) null);
            }
        } catch (Throwable th) {
            m4422e(th);
        }
    }

    /* JADX INFO: renamed from: c */
    public static void m4409c(String str, Object... objArr) throws Throwable {
        try {
            if (str.contains("%")) {
                m4419e(f4516b, new Formatter().format(str, objArr).toString(), (Throwable) null);
            } else {
                m4419e(str, objArr != null ? (String) objArr[0] : "", (Throwable) null);
            }
        } catch (Throwable th) {
            m4422e(th);
        }
    }

    /* JADX INFO: renamed from: d */
    public static void m4415d(String str, Object... objArr) throws Throwable {
        try {
            if (str.contains("%")) {
                m4395a(f4516b, new Formatter().format(str, objArr).toString(), (Throwable) null);
            } else {
                m4395a(str, objArr != null ? (String) objArr[0] : "", (Throwable) null);
            }
        } catch (Throwable th) {
            m4422e(th);
        }
    }

    /* JADX INFO: renamed from: e */
    public static void m4421e(String str, Object... objArr) throws Throwable {
        try {
            if (str.contains("%")) {
                m4413d(f4516b, new Formatter().format(str, objArr).toString(), (Throwable) null);
            } else {
                m4413d(str, objArr != null ? (String) objArr[0] : "", (Throwable) null);
            }
        } catch (Throwable th) {
            m4422e(th);
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m4398a(Throwable th) throws Throwable {
        m4407c(f4516b, (String) null, th);
    }

    /* JADX INFO: renamed from: b */
    public static void m4404b(Throwable th) throws Throwable {
        m4395a(f4516b, (String) null, th);
    }

    /* JADX INFO: renamed from: c */
    public static void m4410c(Throwable th) throws Throwable {
        m4413d(f4516b, (String) null, th);
    }

    /* JADX INFO: renamed from: d */
    public static void m4416d(Throwable th) throws Throwable {
        m4401b(f4516b, (String) null, th);
    }

    /* JADX INFO: renamed from: e */
    public static void m4422e(Throwable th) throws Throwable {
        m4419e(f4516b, (String) null, th);
    }

    /* JADX INFO: renamed from: a */
    public static void m4396a(String str, Throwable th) throws Throwable {
        m4407c(f4516b, str, th);
    }

    /* JADX INFO: renamed from: b */
    public static void m4402b(String str, Throwable th) throws Throwable {
        m4395a(f4516b, str, th);
    }

    /* JADX INFO: renamed from: c */
    public static void m4408c(String str, Throwable th) throws Throwable {
        m4413d(f4516b, str, th);
    }

    /* JADX INFO: renamed from: d */
    public static void m4414d(String str, Throwable th) throws Throwable {
        m4401b(f4516b, str, th);
    }

    /* JADX INFO: renamed from: e */
    public static void m4420e(String str, Throwable th) throws Throwable {
        m4419e(f4516b, str, th);
    }

    /* JADX INFO: renamed from: a */
    public static void m4394a(String str) throws Throwable {
        m4395a(f4516b, str, (Throwable) null);
    }

    /* JADX INFO: renamed from: b */
    public static void m4400b(String str) throws Throwable {
        m4401b(f4516b, str, (Throwable) null);
    }

    /* JADX INFO: renamed from: c */
    public static void m4406c(String str) throws Throwable {
        m4407c(f4516b, str, (Throwable) null);
    }

    /* JADX INFO: renamed from: d */
    public static void m4412d(String str) throws Throwable {
        m4413d(f4516b, str, (Throwable) null);
    }

    /* JADX INFO: renamed from: e */
    public static void m4418e(String str) throws Throwable {
        m4419e(f4516b, str, (Throwable) null);
    }

    /* JADX INFO: renamed from: a */
    public static void m4395a(String str, String str2, Throwable th) throws Throwable {
        if (f4515a) {
            m4393a(1, str, str2, th);
        }
    }

    /* JADX INFO: renamed from: b */
    public static void m4401b(String str, String str2, Throwable th) throws Throwable {
        if (f4515a) {
            m4393a(2, str, str2, th);
        }
    }

    /* JADX INFO: renamed from: c */
    public static void m4407c(String str, String str2, Throwable th) throws Throwable {
        if (f4515a) {
            m4393a(3, str, str2, th);
        }
    }

    /* JADX INFO: renamed from: d */
    public static void m4413d(String str, String str2, Throwable th) throws Throwable {
        if (f4515a) {
            m4393a(4, str, str2, th);
        }
    }

    /* JADX INFO: renamed from: e */
    public static void m4419e(String str, String str2, Throwable th) throws Throwable {
        if (f4515a) {
            m4393a(5, str, str2, th);
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m4393a(int i, String str, String str2, Throwable th) throws Throwable {
        if (!TextUtils.isEmpty(str2)) {
            int length = str2.length();
            int i2 = f4522h;
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
                    i2 = f4522h + i2;
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
            String strM4424f = m4424f(th);
            if (TextUtils.isEmpty(strM4424f)) {
                return;
            }
            switch (i) {
                case 1:
                    Log.v(str, strM4424f);
                    break;
                case 2:
                    Log.d(str, strM4424f);
                    break;
                case 3:
                    Log.i(str, strM4424f);
                    break;
                case 4:
                    Log.w(str, strM4424f);
                    break;
                case 5:
                    Log.e(str, strM4424f);
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: f */
    public static String m4424f(Throwable th) throws Throwable {
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
