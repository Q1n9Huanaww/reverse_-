package com.iflytek.voiceads.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.iflytek.voiceads.config.SDKConstants;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/* JADX INFO: renamed from: com.iflytek.voiceads.utils.k */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1597k {
    /* JADX INFO: renamed from: a */
    public static String m3205a() {
        String strM3207b = m3207b();
        if (strM3207b != null && strM3207b.contains("%")) {
            String str = strM3207b.split("%")[0];
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        }
        return "";
    }

    /* JADX INFO: renamed from: a */
    public static boolean m3206a(Context context) {
        if (C1595i.m3199a(context, "android.permission.INTERNET") || C1595i.m3199a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
        }
        C1593g.m3190b(SDKConstants.TAG, "INTERNET and ACCESS_NETWORK_STATE permission must be enabled in AndroidManifest.xml");
        return false;
    }

    /* JADX INFO: renamed from: b */
    public static String m3207b() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddressNextElement = inetAddresses.nextElement();
                    if (!inetAddressNextElement.isLoopbackAddress() && (inetAddressNextElement instanceof Inet6Address)) {
                        return inetAddressNextElement.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "IP Address" + e.getMessage());
        }
        return "";
    }

    /* JADX INFO: renamed from: b */
    public static boolean m3208b(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 1;
    }

    /* JADX INFO: renamed from: c */
    public static String m3209c(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return "";
        }
        try {
            if (activeNetworkInfo.getType() == 1) {
                return "wifi";
            }
            String lowerCase = activeNetworkInfo.getExtraInfo().toLowerCase();
            int subtype = activeNetworkInfo.getSubtype();
            if (TextUtils.isEmpty(lowerCase)) {
                return "";
            }
            switch (subtype) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                    return "2g";
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                case 14:
                case 15:
                case 17:
                    return "3g";
                case 13:
                    return "4g";
                case 16:
                default:
                    return "";
            }
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "get net class" + e.getMessage());
            return "";
        }
    }
}
