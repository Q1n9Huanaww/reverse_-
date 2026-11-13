package org.apache.harmony.misc;

/* JADX INFO: loaded from: classes.dex */
public class SystemUtils {
    public static final int ARC_IA32 = 1;
    public static final int ARC_IA64 = 2;
    public static final int ARC_UNKNOWN = -1;
    public static final int OS_LINUX = 2;
    public static final int OS_UNKNOWN = -1;
    public static final int OS_WINDOWS = 1;
    private static int arc;

    /* JADX INFO: renamed from: os */
    private static int f5066os;

    public static int getOS() {
        if (f5066os == 0) {
            String strSubstring = System.getProperty("os.name").substring(0, 3);
            if (strSubstring.compareToIgnoreCase("win") == 0) {
                f5066os = 1;
            } else if (strSubstring.compareToIgnoreCase("lin") == 0) {
                f5066os = 2;
            } else {
                f5066os = -1;
            }
        }
        return f5066os;
    }
}
