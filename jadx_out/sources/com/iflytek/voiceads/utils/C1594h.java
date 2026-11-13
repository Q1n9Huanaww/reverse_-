package com.iflytek.voiceads.utils;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;
import com.iflytek.voiceads.config.SDKConstants;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.NetworkInterface;
import java.util.Collections;

/* JADX INFO: renamed from: com.iflytek.voiceads.utils.h */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1594h {

    /* JADX INFO: renamed from: a */
    private static String f3422a;

    /* JADX INFO: renamed from: a */
    private static String m3191a() {
        String line = "";
        String strTrim = "";
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address").getInputStream()));
            while (line != null) {
                line = lineNumberReader.readLine();
                if (line != null) {
                    strTrim = line.trim();
                    break;
                }
            }
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "mac error1:" + e.getMessage());
        }
        if (!TextUtils.isEmpty(strTrim)) {
            return strTrim;
        }
        try {
            return m3193a("/sys/class/net/eth0/address").toUpperCase().substring(0, 17);
        } catch (Exception e2) {
            C1593g.m3190b(SDKConstants.TAG, "mac error2:" + e2.getMessage());
            return strTrim;
        }
    }

    /* JADX INFO: renamed from: a */
    public static String m3192a(Context context) {
        if (!TextUtils.isEmpty(f3422a)) {
            return f3422a;
        }
        try {
            if (Build.VERSION.SDK_INT < 23) {
                f3422a = m3195b(context);
            } else if (Build.VERSION.SDK_INT < 24) {
                f3422a = m3191a();
            } else {
                f3422a = m3194b();
            }
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "getMac:" + e.getMessage());
        }
        return !TextUtils.isEmpty(f3422a) ? f3422a : "02:00:00:00:00:00";
    }

    /* JADX INFO: renamed from: a */
    public static String m3193a(String str) throws Exception {
        FileReader fileReader = new FileReader(str);
        StringBuilder sb = new StringBuilder();
        char[] cArr = new char[4096];
        for (int i = fileReader.read(cArr); i >= 0; i = fileReader.read(cArr)) {
            sb.append(cArr, 0, i);
        }
        String string = sb.toString();
        fileReader.close();
        return string;
    }

    /* JADX INFO: renamed from: b */
    private static String m3194b() {
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
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "mac error3:" + e.getMessage());
            return "";
        }
    }

    /* JADX INFO: renamed from: b */
    private static String m3195b(Context context) {
        return ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
    }
}
