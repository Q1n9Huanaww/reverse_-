package com.cyjh.share.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.cyjh.share.bean.request.DeviceInfo;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.cyjh.share.rom.Rom;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.orhanobut.logger.Logger;
import com.p024ds.daisi.net.URL.URLConstant;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class AppUtils {
    public static final String TIME_FORMAT_DAY = "yyyy-MM-dd";
    public static final String TIME_FORMAT_SECOND = "yyyy-MM-dd HH:mm:ss";

    public static String timeStamp2Date(long j, String str) {
        if (str == null || str.isEmpty()) {
            str = "yyyy-MM-dd HH:mm:ss";
        }
        return new SimpleDateFormat(str, Locale.getDefault()).format(new Date(j * 1000));
    }

    public static String timeMillis2Date(long j, String str) {
        if (str == null || str.isEmpty()) {
            str = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(j);
        return simpleDateFormat.format(gregorianCalendar.getTime());
    }

    public static long timeToStamp(String str) {
        try {
            Date date = new SimpleDateFormat(TIME_FORMAT_DAY, Locale.getDefault()).parse(str);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(calendar.get(1), calendar.get(2), calendar.get(5) + 1, 0, 0);
            return calendar.getTimeInMillis();
        } catch (ParseException e) {
            ThrowableExtension.printStackTrace(e);
            return 0L;
        }
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static boolean isAppDayActivity(String str, Context context) {
        String fileContent = getFileContent(str, context);
        return !TextUtils.isEmpty(fileContent) && fileContent.compareTo(timeStamp2Date(System.currentTimeMillis() / 1000, TIME_FORMAT_DAY)) >= 0;
    }

    public static void writeDateToFile(String str, Context context, String str2) {
        try {
            File file = new File(getPackageFile(context), str);
            if (!file.exists()) {
                file.createNewFile();
            }
            writeStringToFile(file, str2);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            Log.e("zzz", "AppUtils--writeDateToFile:" + e.getMessage());
        }
    }

    public static File getPackageFile(Context context) {
        File file = new File(getApkPath(context));
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    private static String getApkPath(Context context) {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + context.getPackageName();
    }

    public static String getFileContent(String str, Context context) {
        File file = new File(getPackageFile(context), str);
        return !file.exists() ? "" : file2Text(file.getAbsolutePath());
    }

    public static void deleteFileContent(String str, Context context) {
        File file = new File(getPackageFile(context), str);
        if (file.exists()) {
            file.delete();
        }
    }

    private static String file2Text(String str) {
        StringBuilder sb = new StringBuilder();
        File file = new File(str);
        try {
            byte[] bArr = new byte[1024];
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i <= 0) {
                    break;
                }
                sb.append(new String(bArr, 0, i, "utf-8"));
            }
        } catch (FileNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
        } catch (IOException e2) {
            ThrowableExtension.printStackTrace(e2);
        }
        return sb.toString();
    }

    private static void writeStringToFile(File file, String str) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(str);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public static void writeDateToFile(String str, Context context, String str2, boolean z) {
        try {
            File file = new File(getPackageFile(context), str);
            if (!file.exists()) {
                file.createNewFile();
            }
            writeStringToFile(file, str2, z);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            Log.e("zzz", "AppUtils--writeDateToFile:" + e.getMessage());
        }
    }

    private static void writeStringToFile(File file, String str, boolean z) {
        try {
            FileWriter fileWriter = new FileWriter(file, z);
            fileWriter.write(str);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public static boolean isPackageInstalled(Context context, String str) {
        PackageInfo packageInfo;
        if (str == null) {
            return false;
        }
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        return packageInfo != null;
    }

    public static Point getResolution(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        return point;
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

    public static String getSerialNumber() {
        return Build.VERSION.SDK_INT >= 26 ? Build.getSerial() : Build.SERIAL;
    }

    public static int getNumCores() {
        try {
            File[] fileArrListFiles = new File("/sys/devices/system/cpu/").listFiles(new FileFilter() { // from class: com.cyjh.share.util.AppUtils.1CpuFilter
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

    public static String getPhoneSystemVersion() {
        return Rom.getPhoneSystem();
    }

    public static DeviceInfo acquireDevicesId(Context context) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("\n\n\n");
        String deviceId = getDeviceId(context);
        if (!TextUtils.isEmpty(deviceId)) {
            sb.append(deviceId);
            sb2.append("devicesId=" + deviceId);
            sb2.append(",");
        }
        String serial = getSerial();
        if (!TextUtils.isEmpty(serial)) {
            sb.append(serial);
            sb2.append("serial=" + serial);
            sb2.append(",");
        }
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.DeviceCode = sb.toString();
        String macAddress = getMacAddress(context);
        if (!TextUtils.isEmpty(macAddress)) {
            sb.append(macAddress);
            sb2.append("macAddress=" + macAddress);
            sb2.append(",");
        }
        File fileByPath = getFileByPath(InterfaceRelatedConstants.UUID_SDCARD_DIR, InterfaceRelatedConstants.ANDROID_ID_SDCARD_FILE);
        String strFile2Text = fileByPath != null ? file2Text(fileByPath.getAbsolutePath()) : null;
        if (TextUtils.isEmpty(strFile2Text)) {
            strFile2Text = getAndroidId(context);
            writeStringToFile(fileByPath, strFile2Text);
        }
        sb.append(strFile2Text);
        sb2.append("androidId=" + strFile2Text);
        sb2.append(",");
        File fileByPath2 = getFileByPath(InterfaceRelatedConstants.UUID_SDCARD_DIR, InterfaceRelatedConstants.UUID_SDCARD_FILE);
        String strFile2Text2 = fileByPath2 != null ? file2Text(fileByPath2.getAbsolutePath()) : "";
        if (TextUtils.isEmpty(strFile2Text2) || fileByPath2 == null) {
            strFile2Text2 = UUID.randomUUID().toString();
            if (fileByPath2 == null) {
                fileByPath2 = getFileByPath(InterfaceRelatedConstants.UUID_SDCARD_DIR, InterfaceRelatedConstants.UUID_SDCARD_FILE);
            }
            writeStringToFile(fileByPath2, strFile2Text2);
        }
        sb.append(strFile2Text2);
        sb2.append("uuid=" + strFile2Text2);
        sb2.append("\n\n\n");
        deviceInfo.DeviceId = MD5Util.MD5(sb.toString());
        return deviceInfo;
    }

    public static void writeFileContent(Context context, String str, String str2) {
        try {
            File file = new File(getPackageFile(context), str);
            if (!file.exists()) {
                file.createNewFile();
            }
            writeStringToFile(file, str2);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public static String getDeviceId(Context context) {
        return ((TelephonyManager) context.getSystemService(URLConstant.USER_ACCOUNT)).getDeviceId();
    }

    public static String getMacAddress(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            return getMacDefault(context);
        }
        if (Build.VERSION.SDK_INT >= 23 && Build.VERSION.SDK_INT < 24) {
            return getMacFromFile();
        }
        if (Build.VERSION.SDK_INT < 24) {
            return "02:00:00:00:00:00";
        }
        String macFromHardware = getMacFromHardware();
        if ("02:00:00:00:00:00".equals(macFromHardware)) {
            macFromHardware = getLocalMacAddressFromBusybox();
        }
        String str = macFromHardware;
        return "02:00:00:00:00:00".equals(str) ? getMacAddressByIp() : str;
    }

    private static String getMacDefault(Context context) {
        return ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
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

    private static InetAddress getLocalInetAddress() {
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

    public static String getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    @SuppressLint({"HardwareIds", "MissingPermission"})
    private static String getSerial() {
        return Build.VERSION.SDK_INT >= 26 ? Build.getSerial() : Build.SERIAL;
    }

    private static String getAndroidId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), InterfaceRelatedConstants.ANDROID_ID_SDCARD_FILE);
    }

    public static File getFileByPath(String str, String str2) {
        File file = null;
        try {
            File file2 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + str);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            File file3 = new File(file2, str2);
            try {
                if (file3.exists()) {
                    return file3;
                }
                file3.createNewFile();
                return file3;
            } catch (IOException e) {
                file = file3;
                e = e;
                ThrowableExtension.printStackTrace(e);
                return file;
            }
        } catch (IOException e2) {
            e = e2;
        }
    }
}
