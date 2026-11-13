package com.cyjh.mobileanjian.ipc.utils;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.net.URL.URLConstant;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.net.NetworkInterface;
import java.util.Collections;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.utils.i */
/* JADX INFO: compiled from: PhoneUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0812i {
    private C0812i() {
        throw new RuntimeException();
    }

    /* JADX INFO: renamed from: a */
    private static String m1088a(Context context) {
        try {
            String deviceId = ((TelephonyManager) context.getSystemService(URLConstant.USER_ACCOUNT)).getDeviceId();
            return deviceId == null ? "" : deviceId;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return "";
        }
    }

    /* JADX INFO: renamed from: b */
    private static String m1092b(Context context) {
        try {
            String subscriberId = ((TelephonyManager) context.getSystemService(URLConstant.USER_ACCOUNT)).getSubscriberId();
            return subscriberId == null ? "" : subscriberId;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return "";
        }
    }

    /* JADX INFO: renamed from: c */
    private static String m1093c(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            return m1087a();
        }
        try {
            String macAddress = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            return macAddress == null ? "" : macAddress;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return "";
        }
    }

    /* JADX INFO: renamed from: a */
    private static String m1087a() {
        String line = "";
        String strTrim = "";
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address ").getInputStream()));
            while (line != null) {
                line = lineNumberReader.readLine();
                if (line != null) {
                    strTrim = line.trim();
                    break;
                }
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        if (strTrim != null && !"".equals(strTrim)) {
            return strTrim;
        }
        try {
            FileReader fileReader = new FileReader("/sys/class/net/eth0/address");
            String strM1089a = m1089a(fileReader);
            fileReader.close();
            return strM1089a.toUpperCase().substring(0, 17);
        } catch (Exception e2) {
            ThrowableExtension.printStackTrace(e2);
            return m1091b();
        }
    }

    /* JADX INFO: renamed from: b */
    private static String m1091b() {
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.getName().equalsIgnoreCase("wlan0")) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return "";
                    }
                    StringBuilder sb = new StringBuilder();
                    for (byte b : hardwareAddress) {
                        sb.append(String.format("%02X:", Byte.valueOf(b)));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString();
                }
            }
            return "";
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX INFO: renamed from: a */
    private static String m1090a(String str) throws Exception {
        FileReader fileReader = new FileReader(str);
        String strM1089a = m1089a(fileReader);
        fileReader.close();
        return strM1089a;
    }

    /* JADX INFO: renamed from: a */
    private static String m1089a(Reader reader) throws Exception {
        StringBuilder sb = new StringBuilder();
        char[] cArr = new char[4096];
        int i = reader.read(cArr);
        while (i >= 0) {
            sb.append(cArr, 0, i);
            i = reader.read(cArr);
        }
        return sb.toString();
    }
}
