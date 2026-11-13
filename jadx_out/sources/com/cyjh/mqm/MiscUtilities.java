package com.cyjh.mqm;

/* JADX INFO: loaded from: classes.dex */
public class MiscUtilities {
    public native String LoadUIFile(String str, boolean z);

    static {
        System.loadLibrary("mqm");
    }
}
