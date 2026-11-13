package com.cyjh.share.util;

/* JADX INFO: loaded from: classes.dex */
public class FastClickUtil {
    private static FastClickUtil fcu = null;
    private static long lastClickTime = 0;
    private static int spaceTime = 500;

    public static FastClickUtil getInstance() {
        if (fcu == null) {
            fcu = new FastClickUtil();
        }
        return fcu;
    }

    public boolean isFastClick() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = jCurrentTimeMillis - lastClickTime;
        if (j > 0 && j < spaceTime) {
            return true;
        }
        lastClickTime = jCurrentTimeMillis;
        return false;
    }
}
