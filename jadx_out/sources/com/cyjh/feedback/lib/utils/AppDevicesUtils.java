package com.cyjh.feedback.lib.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.orhanobut.logger.Logger;
import com.p024ds.daisi.constant.Constants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class AppDevicesUtils {
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";

    public static int getStatusBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", Constants.ANDROID);
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static Point getResolution(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        return point;
    }

    public static void installAPK(Context context, File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    public static void scriptVibration(Context context, long j) {
        ((Vibrator) context.getSystemService("vibrator")).vibrate(j);
    }

    public static boolean isMobileNO(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.matches("[1][34587]\\d{9}");
    }

    public static long getTotalMemorySize(Context context) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 2048);
            String line = bufferedReader.readLine();
            Logger.m3442e(line, new Object[0]);
            String strSubstring = line.substring(line.indexOf("MemTotal:"));
            bufferedReader.close();
            return ((long) Integer.parseInt(strSubstring.replaceAll("\\D+", ""))) * 1024;
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
            return 0L;
        }
    }

    public static int getScreenDPI(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.densityDpi;
    }

    public static String getKernelVersion() {
        try {
            FileInputStream fileInputStream = new FileInputStream("/proc/version");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream), 8192);
            String str = "";
            while (true) {
                try {
                    try {
                        try {
                            String line = bufferedReader.readLine();
                            if (line == null) {
                                break;
                            }
                            str = str + line;
                        } catch (IOException e) {
                            ThrowableExtension.printStackTrace(e);
                        }
                    } catch (Throwable th) {
                        try {
                            bufferedReader.close();
                            fileInputStream.close();
                        } catch (IOException e2) {
                            ThrowableExtension.printStackTrace(e2);
                        }
                        throw th;
                    }
                } catch (IOException e3) {
                    ThrowableExtension.printStackTrace(e3);
                    bufferedReader.close();
                    fileInputStream.close();
                }
            }
            bufferedReader.close();
            fileInputStream.close();
            if (str == "") {
                return "";
            }
            try {
                String strSubstring = str.substring(str.indexOf("version ") + "version ".length());
                return strSubstring.substring(0, strSubstring.indexOf(" "));
            } catch (IndexOutOfBoundsException e4) {
                ThrowableExtension.printStackTrace(e4);
                return "";
            }
        } catch (FileNotFoundException e5) {
            ThrowableExtension.printStackTrace(e5);
            return "";
        }
    }

    public static String getSerialNumber() {
        return Build.SERIAL;
    }

    public static String getBaseBandVer() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls.newInstance(), "gsm.version.baseband", "no message");
        } catch (Exception unused) {
            return "";
        }
    }

    public static int getNumCores() {
        try {
            File[] fileArrListFiles = new File("/sys/devices/system/cpu/").listFiles(new FileFilter() { // from class: com.cyjh.feedback.lib.utils.AppDevicesUtils.1CpuFilter
                @Override // java.io.FileFilter
                public boolean accept(File file) {
                    return Pattern.matches("cpu[0-9]", file.getName());
                }
            });
            Logger.m3442e("CPU Count=" + fileArrListFiles.length, new Object[0]);
            return fileArrListFiles.length;
        } catch (Exception e) {
            Logger.m3442e("CPU Count: Failed.", new Object[0]);
            ThrowableExtension.printStackTrace(e);
            return 1;
        }
    }

    public static String getCpuInfo() {
        String[] strArr = {"", ""};
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/cpuinfo"), 8192);
            String[] strArrSplit = bufferedReader.readLine().split("\\s+");
            for (int i = 2; i < strArrSplit.length; i++) {
                strArr[0] = strArr[0] + strArrSplit[i] + " ";
            }
            bufferedReader.close();
        } catch (IOException unused) {
        }
        return strArr[0];
    }

    public static boolean isFlyme() {
        try {
            return Build.class.getMethod("hasSmartBar", new Class[0]) != null;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isMIUI() {
        try {
            BuildProperties buildPropertiesNewInstance = BuildProperties.newInstance();
            return (buildPropertiesNewInstance.getProperty(KEY_MIUI_VERSION_CODE, null) == null && buildPropertiesNewInstance.getProperty(KEY_MIUI_VERSION_NAME, null) == null && buildPropertiesNewInstance.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) == null) ? false : true;
        } catch (IOException unused) {
            return false;
        }
    }

    public static boolean isEmpty(String str) {
        return str == null || "".equalsIgnoreCase(str.trim()) || "null".equalsIgnoreCase(str.trim());
    }

    public static boolean isMetCondition(String str) {
        return str.toLowerCase().endsWith(".jpg") || str.toLowerCase().endsWith(".jpeg") || str.toLowerCase().endsWith(".png");
    }

    public static boolean checkApkExist(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            context.getPackageManager().getApplicationInfo(str, 8192);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }
}
