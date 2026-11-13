package org.wlf.filedownloader.util;

/* JADX INFO: loaded from: classes2.dex */
public class MathUtil {
    public static double formatNumber(double number) {
        return Math.round(number * 100.0d) / 100.0d;
    }
}
