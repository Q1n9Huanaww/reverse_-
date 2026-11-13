package com.cyjh.share.util;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.os.EnvironmentCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.constant.Constants;
import com.p024ds.daisi.net.URL.URLConstant;
import com.umeng.commonsdk.proguard.C1799v;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: classes.dex */
public class EmulatorUtils {
    public static boolean notHasBlueTooth() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null) {
            return true;
        }
        String name = defaultAdapter.getName();
        Log.e("zzz", "notHasBlueTooth:" + name);
        return TextUtils.isEmpty(name);
    }

    public static Boolean notHasLightSensorManager(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService(C1799v.f4284W);
        if (sensorManager != null) {
            return Boolean.valueOf(sensorManager.getDefaultSensor(5) == null);
        }
        return false;
    }

    public static boolean isFeatures(Context context) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("tel:123456"));
        intent.setAction("android.intent.action.DIAL");
        return Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.toLowerCase().contains("vbox") || Build.FINGERPRINT.toLowerCase().contains("test-keys") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.SERIAL.equalsIgnoreCase(EnvironmentCompat.MEDIA_UNKNOWN) || Build.SERIAL.equalsIgnoreCase(Constants.ANDROID) || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || "google_sdk".equals(Build.PRODUCT) || Constants.ANDROID.equals(((TelephonyManager) context.getSystemService(URLConstant.USER_ACCOUNT)).getNetworkOperatorName().toLowerCase()) || !(intent.resolveActivity(context.getPackageManager()) != null);
    }

    public static boolean checkIsNotRealPhone() {
        String cpuInfo = readCpuInfo();
        Log.e("zzz", "EmulatorUtils--checkIsNotRealPhone--" + cpuInfo);
        return cpuInfo.contains("intel") || cpuInfo.contains("amd");
    }

    private static String readCpuInfo() {
        String str = "";
        try {
            Process processStart = new ProcessBuilder("/system/bin/cat", "/proc/cpuinfo").start();
            StringBuilder sb = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processStart.getInputStream(), "utf-8"));
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    sb.append(line);
                } else {
                    bufferedReader.close();
                    String lowerCase = sb.toString().toLowerCase();
                    try {
                        Log.e("zzz", "readCpuInfo:" + lowerCase);
                        return lowerCase;
                    } catch (IOException e) {
                        e = e;
                        str = lowerCase;
                    }
                }
                ThrowableExtension.printStackTrace(e);
                return str;
            }
        } catch (IOException e2) {
            e = e2;
        }
    }

    public static boolean isEmulator(Context context) {
        return notHasBlueTooth() || notHasLightSensorManager(context).booleanValue() || isFeatures(context) || checkIsNotRealPhone();
    }
}
