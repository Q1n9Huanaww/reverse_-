package com.cyjh.mobileanjian.ipc.rpc;

import android.app.ActivityManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.net.URL.URLConstant;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import org.apache.commons.p092io.FilenameUtils;

/* JADX INFO: loaded from: classes.dex */
public class AndroidHelper {

    /* JADX INFO: renamed from: a */
    private static Context f1042a = null;

    /* JADX INFO: renamed from: b */
    private static int f1043b = 100;

    /* JADX INFO: renamed from: c */
    private static boolean f1044c;

    /* JADX INFO: renamed from: a */
    static /* synthetic */ boolean m752a() {
        f1044c = true;
        return true;
    }

    public static void init(Context context) {
        f1042a = context;
        Telephony.init(context);
    }

    public static void setClipboardText(String str) {
        ((ClipboardManager) f1042a.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("", str));
    }

    public static String getClipboardText() {
        ClipData primaryClip;
        ClipData.Item itemAt;
        ClipboardManager clipboardManager = (ClipboardManager) f1042a.getSystemService("clipboard");
        return (!clipboardManager.hasPrimaryClip() || (primaryClip = clipboardManager.getPrimaryClip()) == null || (itemAt = primaryClip.getItemAt(0)) == null) ? "" : itemAt.getText().toString();
    }

    public static String getForegroundPackage() {
        if (Build.VERSION.SDK_INT < 21) {
            return ((ActivityManager) f1042a.getSystemService("activity")).getRunningTasks(1).get(0).topActivity.getPackageName();
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        List<UsageStats> listQueryUsageStats = ((UsageStatsManager) f1042a.getSystemService("usagestats")).queryUsageStats(4, jCurrentTimeMillis - 2000, jCurrentTimeMillis);
        UsageStats usageStats = null;
        if (listQueryUsageStats == null || listQueryUsageStats.isEmpty()) {
            return null;
        }
        for (UsageStats usageStats2 : listQueryUsageStats) {
            if (usageStats == null || usageStats.getLastTimeUsed() < usageStats2.getLastTimeUsed()) {
                usageStats = usageStats2;
            }
        }
        return usageStats.getPackageName();
    }

    public static boolean isAppForeground(String str) {
        return str.equals(getForegroundPackage());
    }

    public static String getAppDataPath(String str) {
        try {
            return f1042a.getPackageManager().getApplicationInfo(str, 0).dataDir;
        } catch (PackageManager.NameNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
            return "";
        }
    }

    public static String getIp() {
        if (((ConnectivityManager) f1042a.getSystemService("connectivity")).getNetworkInfo(1).isConnected()) {
            WifiManager wifiManager = (WifiManager) f1042a.getSystemService("wifi");
            if (wifiManager.isWifiEnabled()) {
                int ipAddress = wifiManager.getConnectionInfo().getIpAddress();
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(ipAddress & 255);
                stringBuffer.append(FilenameUtils.EXTENSION_SEPARATOR);
                int i = ipAddress >>> 8;
                stringBuffer.append(i & 255);
                stringBuffer.append(FilenameUtils.EXTENSION_SEPARATOR);
                int i2 = i >>> 8;
                stringBuffer.append(i2 & 255);
                stringBuffer.append(FilenameUtils.EXTENSION_SEPARATOR);
                stringBuffer.append((i2 >>> 8) & 255);
                return stringBuffer.toString();
            }
        }
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddressNextElement = inetAddresses.nextElement();
                    if (!inetAddressNextElement.isLoopbackAddress() && (inetAddressNextElement instanceof Inet4Address)) {
                        return inetAddressNextElement.getHostAddress().toString();
                    }
                }
            }
            return null;
        } catch (SocketException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public static String getImsi() {
        return ((TelephonyManager) f1042a.getSystemService(URLConstant.USER_ACCOUNT)).getSubscriberId();
    }

    public static String getModel() {
        return Build.BRAND + " " + Build.MODEL;
    }

    public static String getIccid() {
        return ((TelephonyManager) f1042a.getSystemService(URLConstant.USER_ACCOUNT)).getSimSerialNumber();
    }

    public static String getNetworkTime() {
        try {
            URLConnection uRLConnectionOpenConnection = new URL("http://www.baidu.com").openConnection();
            uRLConnectionOpenConnection.setReadTimeout(5000);
            uRLConnectionOpenConnection.setConnectTimeout(5000);
            uRLConnectionOpenConnection.connect();
            Date date = new Date(uRLConnectionOpenConnection.getDate());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            Log.e("tag", "getNetworkTime: by baidu url:" + simpleDateFormat.format(date));
            return simpleDateFormat.format(date);
        } catch (MalformedURLException e) {
            ThrowableExtension.printStackTrace(e);
            try {
                URLConnection uRLConnectionOpenConnection2 = new URL("http://www.taobao.com").openConnection();
                uRLConnectionOpenConnection2.setReadTimeout(5000);
                uRLConnectionOpenConnection2.setConnectTimeout(5000);
                uRLConnectionOpenConnection2.connect();
                Date date2 = new Date(uRLConnectionOpenConnection2.getDate());
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                Log.e("tag", "getNetworkTime: by a li url:" + simpleDateFormat2.format(date2));
                return simpleDateFormat2.format(date2);
            } catch (MalformedURLException e2) {
                ThrowableExtension.printStackTrace(e2);
                return null;
            } catch (IOException e3) {
                ThrowableExtension.printStackTrace(e3);
                return null;
            }
        } catch (IOException e4) {
            ThrowableExtension.printStackTrace(e4);
            URLConnection uRLConnectionOpenConnection3 = new URL("http://www.taobao.com").openConnection();
            uRLConnectionOpenConnection3.setReadTimeout(5000);
            uRLConnectionOpenConnection3.setConnectTimeout(5000);
            uRLConnectionOpenConnection3.connect();
            Date date3 = new Date(uRLConnectionOpenConnection3.getDate());
            SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            Log.e("tag", "getNetworkTime: by a li url:" + simpleDateFormat3.format(date3));
            return simpleDateFormat3.format(date3);
        }
    }

    public static String batteryLevel() {
        f1044c = false;
        f1042a.registerReceiver(new BroadcastReceiver() { // from class: com.cyjh.mobileanjian.ipc.rpc.AndroidHelper.1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                context.unregisterReceiver(this);
                int intExtra = intent.getIntExtra("level", -1);
                int intExtra2 = intent.getIntExtra("scale", -1);
                if (intExtra >= 0 && intExtra2 > 0) {
                    int unused = AndroidHelper.f1043b = (intExtra * 100) / intExtra2;
                }
                AndroidHelper.m752a();
            }
        }, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        while (!f1044c) {
            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        return String.valueOf(f1043b);
    }

    public static String isScreenOn() {
        return ((PowerManager) f1042a.getSystemService("power")).isScreenOn() ? "true" : "false";
    }

    public static String getBacklightLevel() {
        int i = 0;
        try {
            int i2 = Settings.System.getInt(f1042a.getContentResolver(), "screen_brightness");
            try {
                return String.valueOf((int) (((double) (i2 * 100)) / 255.0d));
            } catch (Settings.SettingNotFoundException e) {
                e = e;
                i = i2;
                ThrowableExtension.printStackTrace(e);
                return String.valueOf(i);
            }
        } catch (Settings.SettingNotFoundException e2) {
            e = e2;
        }
    }
}
