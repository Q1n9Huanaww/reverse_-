package com.p024ds.daisi.util;

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

/* JADX INFO: loaded from: classes.dex */
public class UniqIdUtil {
    /* JADX WARN: Code duplicated, block: B:15:0x003a  */
    /* JADX WARN: Code duplicated, block: B:25:0x0055 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Code duplicated, block: B:40:? A[RETURN, SYNTHETIC] */
    private static String getCpuSerial() {
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

    private static String getSerialno() {
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

    private static String getImei(Context context) {
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
            TelephonyManager telephonyManager2 = (TelephonyManager) context.getSystemService("phone2");
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

    private static String getUniqIdCommon(Context context, boolean z) {
        String str;
        String imei = getImei(context);
        String cpuSerial = getCpuSerial();
        String serialno = getSerialno();
        if (!z || imei == null || imei == "") {
            if (imei != null && imei != "") {
                cpuSerial = imei;
            }
            str = (cpuSerial == null || cpuSerial == "") ? serialno : cpuSerial;
        } else if (serialno == null || serialno == "") {
            str = imei;
        } else {
            str = imei + "-" + serialno;
        }
        if (imei == "") {
            return "imei:none-" + str;
        }
        return "imei:" + str;
    }

    private static String getArmUniqId(Context context) {
        String line;
        String uniqIdCommon = getUniqIdCommon(context, false);
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/cpuinfo")));
            while (true) {
                line = bufferedReader.readLine();
                if (line != null) {
                    if (line.startsWith("Hardware")) {
                        line = line.split(":")[1].trim().replaceAll(" ", "_");
                        break;
                    }
                } else {
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
        return uniqIdCommon + "-" + line;
    }

    private static String getOtherUniqId(Context context) {
        String uniqIdCommon = getUniqIdCommon(context, true);
        String strReplaceAll = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/cpuinfo")));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    strReplaceAll = line;
                    break;
                }
                try {
                    if (line.startsWith("model name")) {
                        strReplaceAll = line.split(":")[1].trim().replaceAll(" ", "_");
                        break;
                    }
                } catch (FileNotFoundException | IOException | Exception unused) {
                    strReplaceAll = line;
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException | IOException | Exception unused2) {
        }
        if (strReplaceAll == null) {
            return uniqIdCommon;
        }
        return uniqIdCommon + "-" + strReplaceAll;
    }

    public static String getUniqId(Context context) {
        if (System.getProperty("os.arch").indexOf("arm") != -1) {
            return getArmUniqId(context);
        }
        return getOtherUniqId(context);
    }
}
