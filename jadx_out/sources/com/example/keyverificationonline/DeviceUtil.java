package com.example.keyverificationonline;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.os.EnvironmentCompat;
import android.telephony.TelephonyManager;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.p024ds.daisi.net.URL.URLConstant;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceUtil {
    public static String getDeviceID(Context context) {
        String imei = "";
        if (Build.VERSION.SDK_INT < 23) {
            imei = getIMEI(context);
        }
        if (imei != null && !imei.isEmpty()) {
            return imei;
        }
        String serial = getSerialNumber();
        if (!serial.equals(EnvironmentCompat.MEDIA_UNKNOWN)) {
            return serial;
        }
        String androidID = getAndroidId(context);
        return !androidID.isEmpty() ? androidID : "";
    }

    private static String getIMEI(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(URLConstant.USER_ACCOUNT);
            return telephonyManager.getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String getSerialNumber() {
        return Build.SERIAL;
    }

    private static String getAndroidId(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), InterfaceRelatedConstants.ANDROID_ID_SDCARD_FILE);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
}
