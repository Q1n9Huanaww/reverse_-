package com.cyjh.mobileanjian.ipc.utils;

import android.content.Context;
import android.support.v4.os.EnvironmentCompat;
import android.telephony.TelephonyManager;
import com.p024ds.daisi.net.URL.URLConstant;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.utils.r */
/* JADX INFO: compiled from: UniqIdUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0821r {

    /* JADX INFO: renamed from: a */
    private static String f1451a = "";

    /* JADX WARN: Code duplicated, block: B:14:0x0039  */
    /* JADX WARN: Code duplicated, block: B:23:0x0053 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Code duplicated, block: B:37:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: a */
    private static String m1119a() {
        int i;
        String strTrim = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/cpuinfo")));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                if (line.startsWith("Serial")) {
                    String strSubstring = line.substring(line.indexOf(":") + 1);
                    try {
                        strTrim = strSubstring.trim();
                    } catch (IOException unused) {
                        strTrim = strSubstring;
                    }
                }
                if (strTrim == null) {
                    strTrim = "";
                }
                i = 0;
                while (i < strTrim.length() && strTrim.charAt(i) == '0') {
                    i++;
                }
                if (i == strTrim.length()) {
                    return "";
                }
                return strTrim;
            }
            bufferedReader.close();
        } catch (IOException unused2) {
        }
        if (strTrim == null) {
            strTrim = "";
        }
        i = 0;
        while (i < strTrim.length()) {
            i++;
        }
        if (i == strTrim.length()) {
            return "";
        }
        return strTrim;
    }

    /* JADX INFO: renamed from: b */
    private static String m1122b() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls, "ro.serialno", "cyjhuser");
        } catch (ClassNotFoundException unused) {
            return "";
        } catch (IllegalAccessException | InvocationTargetException unused2) {
            return "";
        } catch (NoSuchMethodException unused3) {
            return "";
        }
    }

    /* JADX INFO: renamed from: c */
    private static String m1124c(Context context) {
        int phoneType;
        String deviceId;
        int phoneType2;
        String deviceId2;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(URLConstant.USER_ACCOUNT);
            phoneType = telephonyManager.getPhoneType();
            try {
                deviceId = telephonyManager.getDeviceId();
            } catch (Exception unused) {
                deviceId = "";
            }
        } catch (Exception unused2) {
            phoneType = 0;
        }
        try {
            TelephonyManager telephonyManager2 = (TelephonyManager) context.getSystemService(URLConstant.USER_ACCOUNT);
            phoneType2 = telephonyManager2.getPhoneType();
            try {
                deviceId2 = telephonyManager2.getDeviceId();
            } catch (Exception unused3) {
                deviceId2 = "";
            }
        } catch (Exception unused4) {
            phoneType2 = 0;
        }
        if (phoneType == 1) {
            if (phoneType2 == 2) {
                return deviceId + "|" + deviceId2;
            }
        } else {
            if (phoneType2 == 1) {
                if (phoneType != 2) {
                    return deviceId2;
                }
                return deviceId2 + "|" + deviceId;
            }
            if (phoneType != 2) {
                return "";
            }
        }
        return deviceId;
    }

    /* JADX INFO: renamed from: a */
    private static String m1121a(Context context, boolean z) {
        String str;
        String strM1124c = m1124c(context);
        String strM1119a = m1119a();
        String strM1122b = m1122b();
        if (!z || strM1124c == null || strM1124c == "") {
            if (strM1124c != null && strM1124c != "") {
                strM1119a = strM1124c;
            }
            str = (strM1119a == null || strM1119a == "") ? strM1122b : strM1119a;
        } else if (strM1122b == null || strM1122b == "") {
            str = strM1124c;
        } else {
            str = strM1124c + "-" + strM1122b;
        }
        if (strM1124c == "") {
            return "imei:none-" + str;
        }
        return "imei:" + str;
    }

    /* JADX INFO: renamed from: d */
    private static String m1125d(Context context) {
        String line;
        String strM1121a = m1121a(context, false);
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/cpuinfo")));
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                if (line.startsWith("Hardware")) {
                    line = line.split(":")[1].trim().replaceAll(" ", "_");
                    break;
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException unused) {
            line = EnvironmentCompat.MEDIA_UNKNOWN;
        } catch (IOException unused2) {
            line = EnvironmentCompat.MEDIA_UNKNOWN;
        } catch (Exception unused3) {
            line = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        return strM1121a + "[arm]@_@" + line;
    }

    /* JADX INFO: renamed from: e */
    private static String m1126e(Context context) {
        return m1121a(context, true) + "[x86]@_@model";
    }

    /* JADX INFO: renamed from: f */
    private static String m1127f(Context context) {
        String line;
        String strM1121a = m1121a(context, true);
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/cpuinfo")));
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                if (line.startsWith("model name")) {
                    line = line.split(":")[1].trim().replaceAll(" ", "_");
                    break;
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException unused) {
            line = EnvironmentCompat.MEDIA_UNKNOWN;
        } catch (IOException unused2) {
            line = EnvironmentCompat.MEDIA_UNKNOWN;
        } catch (Exception unused3) {
            line = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        return strM1121a + "[x86]@_@" + line;
    }

    /* JADX INFO: renamed from: g */
    private static String m1128g(Context context) {
        if (f1451a != "") {
            return f1451a;
        }
        String strM1125d = m1125d(context);
        if (strM1125d.endsWith("null") || strM1125d.endsWith(EnvironmentCompat.MEDIA_UNKNOWN)) {
            strM1125d = m1121a(context, true) + "[x86]@_@model";
        }
        f1451a = strM1125d;
        return strM1125d;
    }

    /* JADX INFO: renamed from: a */
    public static String m1120a(Context context) {
        String strM1125d = m1125d(context);
        return (strM1125d.endsWith("null") || strM1125d.endsWith(EnvironmentCompat.MEDIA_UNKNOWN)) ? m1127f(context) : strM1125d;
    }

    /* JADX INFO: renamed from: b */
    public static boolean m1123b(Context context) {
        String strM1125d = m1125d(context);
        return strM1125d.endsWith("null") || strM1125d.endsWith(EnvironmentCompat.MEDIA_UNKNOWN);
    }
}
