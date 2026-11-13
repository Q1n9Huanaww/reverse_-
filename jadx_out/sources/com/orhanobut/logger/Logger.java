package com.orhanobut.logger;

/* JADX INFO: loaded from: classes.dex */
public final class Logger {
    private static final String DEFAULT_TAG = "PRETTYLOGGER";
    private static final Printer printer = new LoggerPrinter();

    private Logger() {
    }

    public static Settings init() {
        return printer.init(DEFAULT_TAG);
    }

    public static Settings init(String str) {
        return printer.init(str);
    }

    /* JADX INFO: renamed from: t */
    public static Printer m3446t(String str) {
        return printer.mo3454t(str, printer.getSettings().getMethodCount());
    }

    /* JADX INFO: renamed from: t */
    public static Printer m3445t(int i) {
        return printer.mo3454t(null, i);
    }

    /* JADX INFO: renamed from: t */
    public static Printer m3447t(String str, int i) {
        return printer.mo3454t(str, i);
    }

    /* JADX INFO: renamed from: d */
    public static void m3441d(String str, Object... objArr) {
        printer.mo3450d(str, objArr);
    }

    /* JADX INFO: renamed from: e */
    public static void m3442e(String str, Object... objArr) {
        printer.mo3452e(null, str, objArr);
    }

    /* JADX INFO: renamed from: e */
    public static void m3443e(Throwable th, String str, Object... objArr) {
        printer.mo3452e(th, str, objArr);
    }

    /* JADX INFO: renamed from: i */
    public static void m3444i(String str, Object... objArr) {
        printer.mo3453i(str, objArr);
    }

    /* JADX INFO: renamed from: v */
    public static void m3448v(String str, Object... objArr) {
        printer.mo3455v(str, objArr);
    }

    /* JADX INFO: renamed from: w */
    public static void m3449w(String str, Object... objArr) {
        printer.mo3456w(str, objArr);
    }

    public static void wtf(String str, Object... objArr) {
        printer.wtf(str, objArr);
    }

    public static void json(String str) {
        printer.json(str);
    }

    public static void xml(String str) {
        printer.xml(str);
    }
}
