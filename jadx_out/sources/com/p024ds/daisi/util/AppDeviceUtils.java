package com.p024ds.daisi.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.constant.Constants;
import com.p024ds.daisi.mvp.managers.AccountManager;
import com.p024ds.daisi.net.URL.URLConstant;
import com.warnship.autoplay.R;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class AppDeviceUtils {
    private static final long VIBRATOR_DEFAULT_DURATION = 1000;
    private static final long[] VIBRATOR_DEFAULT_DURATION_PATTERN = {500, 200, 500, 200};
    private static final int VIBRATOR_DEFAULT_NOREPEAT = -1;

    public static Point getResolution(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        return point;
    }

    public static void startVibrator(Context context) {
        startVibrator(context, VIBRATOR_DEFAULT_DURATION);
    }

    public static void startVibrator(Context context, long j) {
        Vibrator vibrator = (Vibrator) context.getSystemService("vibrator");
        if (vibrator.hasVibrator()) {
            vibrator.vibrate(j);
        }
    }

    public static void startVibratorPattern(Context context) {
        startVibratorPattern(context, VIBRATOR_DEFAULT_DURATION_PATTERN, -1);
    }

    public static void startVibratorPattern(Context context, long[] jArr, int i) {
        Vibrator vibrator = (Vibrator) context.getSystemService("vibrator");
        if (vibrator.hasVibrator()) {
            vibrator.vibrate(jArr, i);
        }
    }

    public static String getDeviceId(Context context) {
        return ((TelephonyManager) context.getSystemService(URLConstant.USER_ACCOUNT)).getDeviceId();
    }

    public static String getAndroidId(Context context) {
        return Settings.System.getString(context.getContentResolver(), InterfaceRelatedConstants.ANDROID_ID_SDCARD_FILE);
    }

    public static String getMac(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            String macDefault = getMacDefault(context);
            Log.e("zzz", "AppDeviceUtils--6.0之前" + macDefault);
            return macDefault;
        }
        if (Build.VERSION.SDK_INT >= 23 && Build.VERSION.SDK_INT < 24) {
            String macFromFile = getMacFromFile();
            Log.e("zzz", "AppDeviceUtils--6.0至7.0之前" + macFromFile);
            return macFromFile;
        }
        if (Build.VERSION.SDK_INT < 24) {
            return "02:00:00:00:00:00";
        }
        String macFromHardware = getMacFromHardware();
        Log.e("zzz", "AppDeviceUtils--7.0以后11" + macFromHardware);
        if ("02:00:00:00:00:00".equals(macFromHardware)) {
            macFromHardware = getLocalMacAddressFromBusybox();
        }
        Log.e("zzz", "AppDeviceUtils--7.0以后22" + macFromHardware);
        if ("02:00:00:00:00:00".equals(macFromHardware)) {
            macFromHardware = getMacAddressByIp();
        }
        String str = macFromHardware;
        Log.e("zzz", "AppDeviceUtils--7.0以后33" + str);
        return str;
    }

    private static String getMacDefault(Context context) {
        WifiManager wifiManager;
        WifiInfo connectionInfo;
        if (context == null || (wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi")) == null) {
            return "02:00:00:00:00:00";
        }
        try {
            connectionInfo = wifiManager.getConnectionInfo();
        } catch (Exception unused) {
            connectionInfo = null;
        }
        if (connectionInfo == null) {
            return null;
        }
        String macAddress = connectionInfo.getMacAddress();
        return !TextUtils.isEmpty(macAddress) ? macAddress.toUpperCase(Locale.ENGLISH) : macAddress;
    }

    private static String getMacFromFile() {
        try {
            return new BufferedReader(new FileReader(new File("/sys/class/net/wlan0/address"))).readLine();
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
            return "02:00:00:00:00:00";
        }
    }

    private static String getMacFromHardware() {
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
            return "02:00:00:00:00:00";
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return "02:00:00:00:00:00";
        }
    }

    public static String getLocalMacAddressFromBusybox() {
        String strCallCmd = callCmd("busybox ifconfig", "HWaddr");
        if (strCallCmd == null) {
            return "网络异常";
        }
        return (strCallCmd.length() <= 0 || !strCallCmd.contains("HWaddr")) ? strCallCmd : strCallCmd.substring(strCallCmd.indexOf("HWaddr") + 6, strCallCmd.length() - 1);
    }

    private static String callCmd(String str, String str2) {
        String str3 = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(str).getInputStream()));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null || line.contains(str2)) {
                    return line;
                }
                str3 = str3 + line;
            }
        } catch (Exception e) {
            String str4 = str3;
            ThrowableExtension.printStackTrace(e);
            return str4;
        }
    }

    public static String getMacAddressByIp() {
        try {
            byte[] hardwareAddress = NetworkInterface.getByInetAddress(getLocalInetAddress()).getHardwareAddress();
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < hardwareAddress.length; i++) {
                if (i != 0) {
                    stringBuffer.append(':');
                }
                String hexString = Integer.toHexString(hardwareAddress[i] & 255);
                if (hexString.length() == 1) {
                    hexString = 0 + hexString;
                }
                stringBuffer.append(hexString);
            }
            return stringBuffer.toString().toUpperCase();
        } catch (Exception unused) {
            return null;
        }
    }

    public static InetAddress getLocalInetAddress() {
        InetAddress inetAddress;
        SocketException e;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            inetAddress = null;
            do {
                try {
                    if (!networkInterfaces.hasMoreElements()) {
                        break;
                    }
                    Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress inetAddressNextElement = inetAddresses.nextElement();
                        try {
                            if (!inetAddressNextElement.isLoopbackAddress() && inetAddressNextElement.getHostAddress().indexOf(":") == -1) {
                                inetAddress = inetAddressNextElement;
                                break;
                            }
                            inetAddress = null;
                        } catch (SocketException e2) {
                            e = e2;
                            inetAddress = inetAddressNextElement;
                            ThrowableExtension.printStackTrace(e);
                        }
                    }
                } catch (SocketException e3) {
                    e = e3;
                }
            } while (inetAddress == null);
        } catch (SocketException e4) {
            inetAddress = null;
            e = e4;
        }
        return inetAddress;
    }

    public static String getVersionName(Context context) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
            packageInfo = null;
        }
        return packageInfo.versionName;
    }

    public static int getStatusBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", Constants.ANDROID);
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    @TargetApi(26)
    public static void installAPK(final Context context, File file) {
        if (Build.VERSION.SDK_INT >= 26 && !isHasInstallPermissionWithO(context)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("安装权限");
            builder.setMessage("需要打开允许来自此来源，请去设置中开启此权限");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener(context) { // from class: com.ds.daisi.util.AppDeviceUtils$$Lambda$0
                private final Context arg$1;

                {
                    this.arg$1 = context;
                }

                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    AppDeviceUtils.lambda$installAPK$0$AppDeviceUtils(this.arg$1, dialogInterface, i);
                }
            });
            builder.create().show();
            return;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        if (Build.VERSION.SDK_INT >= 24) {
            intent.setFlags(1);
            intent.setDataAndType(FileProvider.getUriForFile(context, context.getString(R.string.pay_appid), file), "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.parse("file://" + file.toString()), "application/vnd.android.package-archive");
            intent.setFlags(268435456);
        }
        context.startActivity(intent);
    }

    static final /* synthetic */ void lambda$installAPK$0$AppDeviceUtils(Context context, DialogInterface dialogInterface, int i) {
        startInstallPermissionSettingActivity(context);
        dialogInterface.dismiss();
    }

    @RequiresApi(api = 26)
    private static void startInstallPermissionSettingActivity(Context context) {
        if (context == null) {
            return;
        }
        ((Activity) context).startActivityForResult(new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES", Uri.parse("package:" + context.getPackageName())), Constants.REQUEST_CODE_APP_INSTALL);
    }

    @RequiresApi(api = 26)
    private static boolean isHasInstallPermissionWithO(Context context) {
        if (context == null) {
            return false;
        }
        return context.getPackageManager().canRequestPackageInstalls();
    }

    public static boolean isAInstallPackage(Context context, String str) {
        List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
        ArrayList arrayList = new ArrayList();
        if (installedPackages != null) {
            for (int i = 0; i < installedPackages.size(); i++) {
                arrayList.add(installedPackages.get(i).packageName);
            }
        }
        return arrayList.contains(str);
    }

    public static boolean openApp(Context context, String str) {
        try {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
            if (launchIntentForPackage == null) {
                return false;
            }
            launchIntentForPackage.addCategory("android.intent.category.LAUNCHER");
            context.startActivity(launchIntentForPackage);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isValidMobile(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.matches("[1][34587]\\d{9}");
    }

    public static boolean isValidAccountOrPassword(String str) {
        return Pattern.compile("[A-Za-z0-9]+").matcher(str).matches();
    }

    public static SharedPreferences getJarSp(Context context) {
        return context.getSharedPreferences(Constants.CYJH_PAY_SP, 0);
    }

    public static int getDipToPx(Context context, float f) {
        return (int) (((double) (context.getResources().getDisplayMetrics().density * f)) + 0.5d);
    }

    public static int getPxToDip(Context context, float f) {
        return (int) (((double) (f / context.getResources().getDisplayMetrics().density)) + 0.5d);
    }

    public static int getRandom(int i) {
        return new Random().nextInt(i) + 1;
    }

    public static String getAppName(Context context) {
        try {
            return context.getResources().getString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.labelRes);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public static String getAppNumber(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return "";
        }
    }

    public static String getDeviceVersionName(Context context) {
        return Build.VERSION.RELEASE;
    }

    public static String getOperateValue(Context context) {
        String subscriberId = ((TelephonyManager) context.getSystemService(URLConstant.USER_ACCOUNT)).getSubscriberId();
        if (subscriberId == null) {
            return AccountManager.TYPE_LOGIN;
        }
        if (subscriberId.startsWith("46000") || subscriberId.startsWith("46002") || subscriberId.startsWith("46007")) {
            return "1";
        }
        if (subscriberId.startsWith("46001") || subscriberId.startsWith("46006")) {
            return "2";
        }
        return subscriberId.startsWith("46003") ? "3" : AccountManager.TYPE_LOGIN;
    }

    public static String getDeviceMac(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            Log.e("=====", "6.0以下");
            return getLocalMacAddressFromWifiInfo(context);
        }
        if (Build.VERSION.SDK_INT < 24 && Build.VERSION.SDK_INT >= 23) {
            Log.e("=====", "6.0以上7.0以下");
            return getMacAddress(context);
        }
        if (Build.VERSION.SDK_INT < 24) {
            return "02:00:00:00:00:00";
        }
        Log.e("=====", "7.0以上");
        if (!TextUtils.isEmpty(getMacAddress(context))) {
            Log.e("=====", "7.0以上1");
            return getMacAddress(context);
        }
        if (!TextUtils.isEmpty(getMachineHardwareAddress())) {
            Log.e("=====", "7.0以上2");
            return getMachineHardwareAddress();
        }
        Log.e("=====", "7.0以上3");
        return getLocalMacAddressFromBusybox();
    }

    public static String getLocalMacAddressFromWifiInfo(Context context) {
        return ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
    }

    public static String getMacAddress(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            String macAddress0 = getMacAddress0(context);
            if (!TextUtils.isEmpty(macAddress0)) {
                return macAddress0;
            }
        }
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
            Log.e("----->NetInfoManager", "getMacAddress:" + e.toString());
        }
        if (strTrim == null || "".equals(strTrim)) {
            try {
                return loadFileAsString("/sys/class/net/eth0/address").toUpperCase().substring(0, 17);
            } catch (Exception e2) {
                ThrowableExtension.printStackTrace(e2);
                Log.e("----->NetInfoManager", "getMacAddress:" + e2.toString());
            }
        }
        return strTrim;
    }

    private static String getMacAddress0(Context context) {
        if (!isAccessWifiStateAuthorized(context)) {
            return "";
        }
        try {
            return ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        } catch (Exception e) {
            Log.e("----->NetInfoManager", "getMacAddress0:" + e.toString());
            return "";
        }
    }

    private static boolean isAccessWifiStateAuthorized(Context context) {
        if (context.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE") != 0) {
            return false;
        }
        Log.e("----->NetInfoManager", "isAccessWifiStateAuthorized:access wifi state is enabled");
        return true;
    }

    private static String loadFileAsString(String str) throws Exception {
        FileReader fileReader = new FileReader(str);
        String strLoadReaderAsString = loadReaderAsString(fileReader);
        fileReader.close();
        return strLoadReaderAsString;
    }

    private static String loadReaderAsString(Reader reader) throws Exception {
        StringBuilder sb = new StringBuilder();
        char[] cArr = new char[4096];
        int i = reader.read(cArr);
        while (i >= 0) {
            sb.append(cArr, 0, i);
            i = reader.read(cArr);
        }
        return sb.toString();
    }

    public static String getMachineHardwareAddress() {
        Enumeration<NetworkInterface> networkInterfaces;
        String str = null;
        try {
            networkInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            ThrowableExtension.printStackTrace(e);
            networkInterfaces = null;
        }
        if (networkInterfaces == null) {
            return null;
        }
        while (networkInterfaces.hasMoreElements()) {
            try {
                String strBytesToString = bytesToString(networkInterfaces.nextElement().getHardwareAddress());
                if (strBytesToString != null) {
                    return strBytesToString;
                }
                str = strBytesToString;
            } catch (SocketException e2) {
                ThrowableExtension.printStackTrace(e2);
            }
        }
        return str;
    }

    private static String bytesToString(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append(String.format("%02X:", Byte.valueOf(b)));
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
