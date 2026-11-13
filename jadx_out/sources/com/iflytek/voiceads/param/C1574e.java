package com.iflytek.voiceads.param;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Proxy;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.utils.C1589c;
import com.iflytek.voiceads.utils.C1593g;
import com.iflytek.voiceads.utils.C1595i;
import com.p024ds.daisi.fragment.ScriptUipSetFragment;
import com.p024ds.daisi.net.URL.URLConstant;
import com.umeng.commonsdk.proguard.C1799v;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.iflytek.voiceads.param.e */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1574e {

    /* JADX INFO: renamed from: a */
    private static SimpleDateFormat f3372a = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss");

    /* JADX INFO: renamed from: b */
    private static final String[] f3373b = {"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"};

    /* JADX INFO: renamed from: a */
    private static int m3111a() {
        return Build.VERSION.SDK_INT;
    }

    /* JADX INFO: renamed from: a */
    private static String m3112a(long j) {
        return f3372a.format(new Date(j));
    }

    /* JADX INFO: renamed from: a */
    private static String m3113a(Context context, String str) {
        for (Signature signature : m3127g(context)) {
            if ("SHA1".equals(str)) {
                return m3114a(signature, "SHA1");
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    private static String m3114a(Signature signature, String str) {
        byte[] byteArray = signature.toByteArray();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            if (messageDigest == null) {
                return "";
            }
            byte[] bArrDigest = messageDigest.digest(byteArray);
            StringBuilder sb = new StringBuilder();
            for (byte b : bArrDigest) {
                sb.append(Integer.toHexString((b & 255) | 256).substring(1, 3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            C1593g.m3190b(SDKConstants.TAG, e.getMessage());
            return "";
        }
    }

    /* JADX INFO: renamed from: a */
    public static JSONObject m3115a(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("and_ver", m3111a());
            jSONObject.put("build_info", m3117b());
            jSONObject.put("time_zone", m3118c());
            jSONObject.put("imsi", m3119c(context));
            jSONObject.put("local_ip", m3120d());
            jSONObject.put("cpu_freq", m3122e());
            jSONObject.put("cpu_info", m3125f());
            jSONObject.put("cpu_hardware", m3126g());
            jSONObject.put("mem_info", m3123e(context));
            jSONObject.put("sign", m3113a(context, "SHA1"));
            jSONObject.put("pkg_info", m3129h(context));
            jSONObject.put("task_info", m3133j(context));
            jSONObject.put("launcher_pkg", m3138n(context));
            jSONObject.put("wifi_proxy", m3139o(context));
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jCurrentTimeMillis - C1589c.m3172a(context, "extraInfoTime") >= 3600000) {
                jSONObject.put("scan_ssid", m3121d(context));
                jSONObject.put("sensor_info", m3131i(context));
                C1589c.m3174a(context, "extraInfoTime", jCurrentTimeMillis);
            }
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, e.getMessage());
        }
        return jSONObject;
    }

    /* JADX INFO: renamed from: b */
    public static int m3116b(Context context) {
        try {
            BatteryManager batteryManager = (BatteryManager) context.getSystemService("batterymanager");
            if (Build.VERSION.SDK_INT > 20) {
                return batteryManager.getIntProperty(4);
            }
            return 100;
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, e.getMessage());
            return 100;
        }
    }

    /* JADX INFO: renamed from: b */
    private static JSONObject m3117b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("build_id", Build.ID);
            jSONObject.put("build_hardware", Build.HARDWARE);
            jSONObject.put("build_finger", Build.FINGERPRINT);
            jSONObject.put("build_cpu_abi", Build.CPU_ABI);
            jSONObject.put("build_cpu_abi2", Build.CPU_ABI2);
            jSONObject.put("build_display", Build.DISPLAY);
        } catch (JSONException e) {
            C1593g.m3190b(SDKConstants.TAG, e.getMessage());
        }
        return jSONObject;
    }

    /* JADX INFO: renamed from: c */
    private static String m3118c() {
        return TimeZone.getDefault().getDisplayName(true, 0);
    }

    /* JADX INFO: renamed from: c */
    private static String m3119c(Context context) {
        String subscriberId;
        if (!C1595i.m3201b(context, "android.permission.READ_PHONE_STATE")) {
            return "";
        }
        try {
            subscriberId = ((TelephonyManager) context.getSystemService(URLConstant.USER_ACCOUNT)).getSubscriberId();
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, e.getMessage());
            subscriberId = "";
        }
        return TextUtils.isEmpty(subscriberId) ? "" : subscriberId;
    }

    /* JADX INFO: renamed from: d */
    private static String m3120d() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddressNextElement = inetAddresses.nextElement();
                    if (!inetAddressNextElement.isLoopbackAddress() && (inetAddressNextElement instanceof Inet4Address)) {
                        return inetAddressNextElement.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            C1593g.m3190b(SDKConstants.TAG, e.getMessage());
        }
        return "";
    }

    /* JADX INFO: renamed from: d */
    private static JSONArray m3121d(Context context) {
        List<ScanResult> scanResults;
        JSONArray jSONArray = new JSONArray();
        try {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
            if (wifiManager != null && (scanResults = wifiManager.getScanResults()) != null) {
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= scanResults.size()) {
                        break;
                    }
                    jSONArray.put(scanResults.get(i2).SSID);
                    i = i2 + 1;
                }
            }
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, e.getMessage());
        }
        return jSONArray;
    }

    /* JADX INFO: renamed from: e */
    private static JSONObject m3122e() {
        String str = "";
        String str2 = "";
        JSONObject jSONObject = new JSONObject();
        try {
            InputStream inputStream = new ProcessBuilder("/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq").start().getInputStream();
            byte[] bArr = new byte[1024];
            while (inputStream.read(bArr) != -1) {
                str = str + new String(bArr);
            }
            inputStream.close();
            String str3 = TextUtils.isEmpty(str) ? "null" : str;
            InputStream inputStream2 = new ProcessBuilder("/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq").start().getInputStream();
            byte[] bArr2 = new byte[1024];
            while (inputStream2.read(bArr2) != -1) {
                str2 = str2 + new String(bArr2);
            }
            inputStream2.close();
            String str4 = TextUtils.isEmpty(str2) ? "null" : str2;
            String line = new BufferedReader(new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq")).readLine();
            if (TextUtils.isEmpty(line)) {
                line = "null";
            }
            jSONObject.put("max_freq", str3.trim());
            jSONObject.put("min_freq", str4.trim());
            jSONObject.put("cur_freq", line);
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, e.getMessage());
        }
        return jSONObject;
    }

    /* JADX INFO: renamed from: e */
    private static JSONObject m3123e(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ava_mem", m3124f(context));
            jSONObject.put("total_in", m3128h());
            jSONObject.put("ava_in", m3130i());
            jSONObject.put("total_ex", m3132j());
            jSONObject.put("ava_ex", m3135k());
        } catch (JSONException e) {
            C1593g.m3190b(SDKConstants.TAG, e.getMessage());
        }
        return jSONObject;
    }

    /* JADX INFO: renamed from: f */
    private static long m3124f(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            return memoryInfo.availMem;
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, e.getMessage());
            return 0L;
        }
    }

    /* JADX INFO: renamed from: f */
    private static String m3125f() {
        try {
            return new BufferedReader(new FileReader("/proc/cpuinfo")).readLine();
        } catch (IOException e) {
            C1593g.m3190b(SDKConstants.TAG, e.getMessage());
            return "";
        }
    }

    /* JADX WARN: Code duplicated, block: B:21:0x005a  */
    /* JADX INFO: renamed from: g */
    private static String m3126g() throws Throwable {
        Scanner scanner;
        HashMap map = new HashMap();
        try {
            scanner = new Scanner(new File("/proc/cpuinfo"));
            while (scanner.hasNextLine()) {
                try {
                    try {
                        String[] strArrSplit = scanner.nextLine().split(": ");
                        if (strArrSplit.length > 1) {
                            map.put(strArrSplit[0].trim(), strArrSplit[1].trim());
                        }
                    } catch (Exception e) {
                        e = e;
                        C1593g.m3190b(SDKConstants.TAG, e.getMessage());
                        if (scanner != null) {
                            scanner.close();
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    if (scanner != null) {
                        scanner.close();
                    }
                    throw th;
                }
            }
            if (scanner != null) {
                scanner.close();
            }
        } catch (Exception e2) {
            e = e2;
            scanner = null;
        } catch (Throwable th2) {
            th = th2;
            scanner = null;
            if (scanner != null) {
                scanner.close();
            }
            throw th;
        }
        return (String) map.get("Hardware");
    }

    /* JADX INFO: renamed from: g */
    private static Signature[] m3127g(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures;
        } catch (PackageManager.NameNotFoundException e) {
            C1593g.m3190b(SDKConstants.TAG, e.getMessage());
            return null;
        }
    }

    /* JADX INFO: renamed from: h */
    private static long m3128h() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
    }

    /* JADX INFO: renamed from: h */
    private static JSONObject m3129h(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            PackageInfo packageInfo = context.getApplicationContext().getPackageManager().getPackageInfo(context.getPackageName(), 0);
            String str = packageInfo.packageName;
            String str2 = packageInfo.versionName;
            long j = packageInfo.firstInstallTime;
            long j2 = packageInfo.lastUpdateTime;
            jSONObject.put("first_install", m3112a(j));
            jSONObject.put("last_update", m3112a(j2));
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, e.getMessage());
        }
        return jSONObject;
    }

    /* JADX INFO: renamed from: i */
    private static long m3130i() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
    }

    /* JADX INFO: renamed from: i */
    private static JSONArray m3131i(Context context) {
        JSONArray jSONArray = new JSONArray();
        try {
            Iterator<Sensor> it2 = ((SensorManager) context.getSystemService(C1799v.f4284W)).getSensorList(-1).iterator();
            while (it2.hasNext()) {
                jSONArray.put(it2.next().getName());
            }
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, e.getMessage());
        }
        return jSONArray;
    }

    /* JADX INFO: renamed from: j */
    private static long m3132j() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
    }

    /* JADX INFO: renamed from: j */
    private static JSONObject m3133j(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("recent_task", m3134k(context));
            jSONObject.put("install_app", m3136l(context));
            jSONObject.put("running_app", m3137m(context));
        } catch (JSONException e) {
            C1593g.m3190b(SDKConstants.TAG, e.getMessage());
        }
        return jSONObject;
    }

    /* JADX INFO: renamed from: k */
    private static int m3134k(Context context) {
        try {
            List<ActivityManager.RecentTaskInfo> recentTasks = ((ActivityManager) context.getSystemService("activity")).getRecentTasks(30, 2);
            if (recentTasks != null) {
                return recentTasks.size();
            }
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, e.getMessage());
        }
        return 0;
    }

    /* JADX INFO: renamed from: k */
    private static long m3135k() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
    }

    /* JADX INFO: renamed from: l */
    private static int m3136l(Context context) {
        try {
            return context.getPackageManager().getInstalledPackages(0).size();
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, e.getMessage());
            return 0;
        }
    }

    /* JADX INFO: renamed from: m */
    private static int m3137m(Context context) {
        try {
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
            if (runningAppProcesses != null) {
                return runningAppProcesses.size();
            }
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, e.getMessage());
        }
        return 0;
    }

    /* JADX INFO: renamed from: n */
    private static String m3138n(Context context) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo resolveInfoResolveActivity = context.getPackageManager().resolveActivity(intent, 0);
        return resolveInfoResolveActivity.activityInfo == null ? "" : resolveInfoResolveActivity.activityInfo.packageName;
    }

    /* JADX INFO: renamed from: o */
    private static String m3139o(Context context) {
        String host;
        int port;
        if (Build.VERSION.SDK_INT >= 14) {
            host = System.getProperty("http.proxyHost");
            String property = System.getProperty("http.proxyPort");
            if (property == null) {
                property = ScriptUipSetFragment.DISPLAY_TRY_TIME_FLAG;
            }
            port = Integer.parseInt(property);
        } else {
            host = Proxy.getHost(context);
            port = Proxy.getPort(context);
        }
        return !TextUtils.isEmpty(host) ? host + ":" + port : "";
    }
}
