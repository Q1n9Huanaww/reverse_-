package com.cyjh.mobileanjian.ipc.log;

/* JADX INFO: loaded from: classes.dex */
public class NativeLog {

    /* JADX INFO: renamed from: a */
    private static StringBuffer f1016a = new StringBuffer();

    public static void appendLog(String str) {
        if (f1016a.length() > 0) {
            f1016a.append("@_@");
        }
        f1016a.append(str);
    }

    public static String getExtraLog() {
        return f1016a.toString();
    }

    public static void reset() {
        f1016a.setLength(0);
    }
}
