package com.cyjh.share.util;

import android.os.Environment;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class PathUtils {
    private static final String DIR_CSTEMP = "CSTemp";
    private static final String DIR_MOBILEANJIAN = "MobileAnJian";
    private static final String DIR_MQTEMP = "MQTemp";
    private static final String DIR_SCRIPT = "Script";
    private static final String DIR_UI_CONFIG = "UIConfig";

    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static String getMobileAnjianPath() {
        return makePath(getSDCardPath(), DIR_MOBILEANJIAN);
    }

    public static String getMobileAnjianScriptPath() {
        return makePath(getMobileAnjianPath(), "Script");
    }

    public static String getMobileAnjianCSTempPath() {
        return mkdirs(makePath(getMobileAnjianPath(), DIR_CSTEMP));
    }

    public static String getMqTempPath() {
        return mkdirs(makePath(getMobileAnjianPath(), DIR_MQTEMP));
    }

    public static String getMobileAnjianUIConfigPath() {
        return mkdirs(makePath(getMobileAnjianPath(), DIR_UI_CONFIG));
    }

    private static String mkdirs(String str) {
        File file = new File(str);
        try {
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        return file.getAbsolutePath();
    }

    private static String makePath(String str, String str2) {
        if (str.endsWith(File.separator)) {
            return str + str2;
        }
        return str + File.separator + str2;
    }
}
