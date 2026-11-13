package com.p024ds.daisi.log.engine;

/* JADX INFO: loaded from: classes.dex */
public class NativeLog {
    private static StringBuffer strBuffer = new StringBuffer();

    public static void appendLog(String str) {
        if (strBuffer.length() > 0) {
            strBuffer.append("@_@");
        }
        strBuffer.append(str);
    }

    public static String getExtraLog() {
        return strBuffer.toString();
    }

    public static void reset() {
        strBuffer.setLength(0);
    }
}
