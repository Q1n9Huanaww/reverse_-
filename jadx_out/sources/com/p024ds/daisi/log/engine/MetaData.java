package com.p024ds.daisi.log.engine;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import com.cyjh.mobileanjian.ipc.utils.RomUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.constant.Constants;
import com.p024ds.daisi.mvp.managers.AccountManager;
import com.p024ds.daisi.util.AppDeviceUtils;
import com.p024ds.daisi.util.FileUtils;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class MetaData {
    private static final String ANJIAN_PACKAGE_NAME = "com.cyjh.mobileanjian";
    private static final File OLD_USER_MARK_FILE = new File(Environment.getExternalStorageDirectory(), ".oldmarkuser");
    private static MetaData mInstance;
    public static boolean sIsRedFinger;
    public final String androidVersion = Build.VERSION.RELEASE;
    public final String appId;
    public final String appName;
    public final String appVersion;
    public final String elfinVersion;
    public final boolean isAnjian;
    public final boolean isFreeElfin;
    public final boolean isNewUser;
    public final String linuxVersion;
    public final String macMd5Sum;
    public final String model;
    public final String packageName;
    public final String romVersion;

    public static void init(Context context, boolean z, String str, String str2) {
        if (mInstance != null) {
            return;
        }
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        mInstance = new MetaData(context, z, str, str2);
    }

    public static MetaData getInstance() {
        return mInstance;
    }

    private MetaData(Context context, boolean z, String str, String str2) throws Throwable {
        String macAddress = AppDeviceUtils.getMacAddress(context);
        if (TextUtils.isEmpty(macAddress)) {
            macAddress = AppDeviceUtils.getDeviceId(context);
            if (TextUtils.isEmpty(macAddress)) {
                File cacheDeviceId = Constants.getCacheDeviceId(context);
                String textFile = FileUtils.readTextFile(cacheDeviceId);
                if (TextUtils.isEmpty(textFile)) {
                    textFile = UUID.randomUUID().toString();
                    try {
                        org.apache.commons.p092io.FileUtils.writeStringToFile(cacheDeviceId, textFile, "UTF-8");
                    } catch (IOException e) {
                        ThrowableExtension.printStackTrace(e);
                    }
                }
                macAddress = textFile;
            }
        }
        this.macMd5Sum = md5(macAddress);
        this.romVersion = RomUtils.getKnownRomVersion();
        if (RomUtils.isEmulator()) {
            this.model = this.romVersion;
        } else {
            this.model = String.format("%s %s", Build.BRAND, Build.MODEL);
        }
        this.appName = AppDeviceUtils.getAppName(context);
        this.appVersion = AppDeviceUtils.getVersionName(context);
        this.packageName = context.getPackageName();
        this.isAnjian = "com.cyjh.mobileanjian".equals(this.packageName);
        this.isFreeElfin = z;
        this.appId = str;
        this.elfinVersion = str2;
        boolean z2 = !OLD_USER_MARK_FILE.exists();
        this.isNewUser = z2;
        if (z2) {
            try {
                OLD_USER_MARK_FILE.createNewFile();
            } catch (IOException e2) {
                ThrowableExtension.printStackTrace(e2);
            }
        }
        String fileToString = null;
        try {
            File file = new File("/proc/version");
            if (file.exists() && file.canRead()) {
                fileToString = org.apache.commons.p092io.FileUtils.readFileToString(file);
            }
        } catch (IOException e3) {
            ThrowableExtension.printStackTrace(e3);
        }
        this.linuxVersion = TextUtils.isEmpty(fileToString) ? "Unknown" : fileToString;
    }

    private static String getMacAddress(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        return (connectionInfo == null || connectionInfo.getMacAddress() == null) ? "UNKNOWN" : connectionInfo.getMacAddress();
    }

    private static final String md5(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            byte[] bArrDigest = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : bArrDigest) {
                String hexString = Integer.toHexString(b & 255);
                while (hexString.length() < 2) {
                    hexString = AccountManager.TYPE_LOGIN + hexString;
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            ThrowableExtension.printStackTrace(e);
            return str;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("手机型号: ");
        sb.append(this.model);
        sb.append("\nLinux: ");
        sb.append(this.linuxVersion);
        sb.append("\nMAC的MD5: ");
        sb.append(this.macMd5Sum);
        sb.append("\n安卓版本: ");
        sb.append(this.androidVersion);
        sb.append("\n定制安卓: ");
        sb.append(this.romVersion);
        sb.append("\n应用名称: ");
        sb.append(this.appName);
        sb.append("\n应用包名: ");
        sb.append(this.packageName);
        sb.append("\n应用版本: ");
        sb.append(this.appVersion);
        sb.append("\n唯一标识: ");
        sb.append(this.appId);
        sb.append("\n精灵版本: ");
        sb.append(this.elfinVersion);
        sb.append("\n是否付费: ");
        sb.append(this.isFreeElfin ? "免费" : "收费");
        sb.append("\n");
        return sb.toString();
    }
}
