package com.umeng.commonsdk.proguard;

import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.p024ds.daisi.constant.Constants;
import com.p024ds.daisi.net.URL.URLConstant;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.statistics.common.C1817e;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.j */
/* JADX INFO: compiled from: ApplicationLayerUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class C1787j {

    /* JADX INFO: renamed from: com.umeng.commonsdk.proguard.j$a */
    /* JADX INFO: compiled from: ApplicationLayerUtil.java */
    public static class a {

        /* JADX INFO: renamed from: a */
        public String f4165a;

        /* JADX INFO: renamed from: b */
        public String f4166b;
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.proguard.j$b */
    /* JADX INFO: compiled from: ApplicationLayerUtil.java */
    public static class b {

        /* JADX INFO: renamed from: a */
        public String f4167a = null;

        /* JADX INFO: renamed from: b */
        public int f4168b = -1;

        /* JADX INFO: renamed from: c */
        public String f4169c = null;
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.proguard.j$c */
    /* JADX INFO: compiled from: ApplicationLayerUtil.java */
    public static class c {

        /* JADX INFO: renamed from: a */
        public int f4170a;

        /* JADX INFO: renamed from: b */
        public String f4171b;

        /* JADX INFO: renamed from: c */
        public String f4172c;

        /* JADX INFO: renamed from: d */
        public int f4173d;

        /* JADX INFO: renamed from: e */
        public int f4174e;

        /* JADX INFO: renamed from: f */
        public int f4175f;

        /* JADX INFO: renamed from: g */
        public int f4176g;

        /* JADX INFO: renamed from: h */
        public String f4177h;

        /* JADX INFO: renamed from: i */
        public int f4178i;

        /* JADX INFO: renamed from: j */
        public int f4179j;

        /* JADX INFO: renamed from: k */
        public int f4180k;

        /* JADX INFO: renamed from: l */
        public long f4181l;
    }

    /* JADX INFO: renamed from: a */
    public static long m3986a(Context context, String str) throws Throwable {
        if (context == null) {
            return 0L;
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 0).firstInstallTime;
        } catch (PackageManager.NameNotFoundException e) {
            C1782e.m3972a(context, e);
            if (e == null) {
                return 0L;
            }
            C1817e.m4418e("getAppFirstInstallTime" + e.getMessage());
            return 0L;
        }
    }

    /* JADX INFO: renamed from: b */
    public static long m3989b(Context context, String str) throws Throwable {
        if (context == null) {
            return 0L;
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 0).lastUpdateTime;
        } catch (PackageManager.NameNotFoundException e) {
            C1782e.m3972a(context, e);
            if (e == null) {
                return 0L;
            }
            C1817e.m4418e("getAppLastUpdateTime:" + e.getMessage());
            return 0L;
        }
    }

    /* JADX INFO: renamed from: c */
    public static String m3994c(Context context, String str) throws Throwable {
        try {
            return context.getPackageManager().getInstallerPackageName(str);
        } catch (Exception e) {
            C1782e.m3972a(context, e);
            if (e != null) {
                C1817e.m4418e("getAppInstaller:" + e.getMessage());
            }
            return null;
        }
    }

    /* JADX INFO: renamed from: d */
    public static int m3995d(Context context, String str) throws Throwable {
        if (context == null) {
            return 0;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getPackageInfo(str, 0).applicationInfo;
            if (applicationInfo != null) {
                return applicationInfo.uid;
            }
            return 0;
        } catch (PackageManager.NameNotFoundException e) {
            C1782e.m3972a(context, e);
            if (e != null) {
                C1817e.m4418e("getAppUid:" + e.getMessage());
            }
            return 0;
        }
    }

    /* JADX INFO: renamed from: a */
    public static boolean m3988a() {
        return new C1793p().m4052a();
    }

    /* JADX INFO: renamed from: b */
    public static String m3990b() {
        return new SimpleDateFormat().format(new Date());
    }

    /* JADX INFO: renamed from: a */
    public static float m3985a(Context context) throws Throwable {
        if (context == null) {
            return 0.0f;
        }
        Configuration configuration = new Configuration();
        try {
            configuration.updateFrom(context.getResources().getConfiguration());
            if (configuration != null) {
                return configuration.fontScale;
            }
            return 0.0f;
        } catch (Exception e) {
            if (e == null) {
                return 0.0f;
            }
            C1817e.m4418e("getFontSize:" + e.getMessage());
            return 0.0f;
        }
    }

    /* JADX INFO: renamed from: b */
    public static List<ScanResult> m3991b(Context context) {
        WifiManager wifiManager;
        List<ScanResult> scanResults = null;
        if (context == null || (wifiManager = (WifiManager) context.getSystemService("wifi")) == null) {
            return null;
        }
        return (DeviceConfig.checkPermission(context, "android.permission.ACCESS_WIFI_STATE") && ((scanResults = wifiManager.getScanResults()) == null || scanResults.size() == 0)) ? scanResults : scanResults;
    }

    /* JADX INFO: renamed from: c */
    public static WifiInfo m3993c(Context context) {
        WifiManager wifiManager;
        if (context == null || !DeviceConfig.checkPermission(context, "android.permission.ACCESS_WIFI_STATE") || (wifiManager = (WifiManager) context.getSystemService("wifi")) == null) {
            return null;
        }
        return wifiManager.getConnectionInfo();
    }

    /* JADX INFO: renamed from: d */
    public static void m3997d(Context context) {
        if (context == null) {
            return;
        }
        UMWorkDispatch.sendEvent(context, C1736a.f4004j, C1763b.m3919a(context).m3920a(), null);
    }

    /* JADX INFO: renamed from: e */
    public static JSONArray m4000e(Context context) {
        if (context == null) {
            return null;
        }
        return C1791n.m4040b(context);
    }

    /* JADX INFO: renamed from: f */
    public static void m4002f(Context context) {
        if (context == null) {
            return;
        }
        C1791n.m4041c(context);
    }

    /* JADX INFO: renamed from: g */
    public static int m4003g(Context context) {
        WifiManager wifiManager;
        if (context == null || !DeviceConfig.checkPermission(context, "android.permission.ACCESS_WIFI_STATE") || (wifiManager = (WifiManager) context.getSystemService("wifi")) == null) {
            return -1;
        }
        return wifiManager.getWifiState();
    }

    /* JADX INFO: renamed from: h */
    public static int m4005h(Context context) {
        if (context == null) {
            return 0;
        }
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", Constants.ANDROID));
    }

    /* JADX INFO: renamed from: i */
    public static int m4007i(Context context) {
        if (context == null) {
            return 0;
        }
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("navigation_bar_height", "dimen", Constants.ANDROID));
    }

    /* JADX INFO: renamed from: j */
    public static DisplayMetrics m4008j(Context context) {
        if (context == null) {
            return null;
        }
        return context.getResources().getDisplayMetrics();
    }

    /* JADX INFO: renamed from: h */
    private static boolean m4006h() {
        String externalStorageState = Environment.getExternalStorageState();
        return "mounted".equals(externalStorageState) || "mounted_ro".equals(externalStorageState);
    }

    /* JADX INFO: renamed from: c */
    public static long m3992c() {
        if (!m4006h() || Build.VERSION.SDK_INT < 9) {
            return 0L;
        }
        return Environment.getExternalStorageDirectory().getFreeSpace();
    }

    /* JADX INFO: renamed from: d */
    public static long m3996d() {
        if (!m4006h() || Build.VERSION.SDK_INT < 9) {
            return 0L;
        }
        return Environment.getExternalStorageDirectory().getTotalSpace();
    }

    /* JADX INFO: renamed from: e */
    public static String m3998e() {
        return C1792o.m4043a("df");
    }

    /* JADX INFO: renamed from: k */
    public static String m4009k(Context context) {
        TelephonyManager telephonyManager;
        if (context == null || !DeviceConfig.checkPermission(context, "android.permission.READ_PHONE_STATE") || (telephonyManager = (TelephonyManager) context.getSystemService(URLConstant.USER_ACCOUNT)) == null) {
            return null;
        }
        return telephonyManager.getSubscriberId();
    }

    /* JADX INFO: renamed from: l */
    public static String m4010l(Context context) {
        if (context == null) {
            return null;
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(URLConstant.USER_ACCOUNT);
        if (!DeviceConfig.checkPermission(context, "android.permission.READ_PHONE_STATE") || telephonyManager == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT < 26) {
            return telephonyManager.getDeviceId();
        }
        return m4018t(context);
    }

    /* JADX INFO: renamed from: t */
    private static String m4018t(Context context) throws Throwable {
        if (context == null) {
            return null;
        }
        try {
            Object objInvoke = Class.forName("android.telephony.TelephonyManager").getMethod("getMeid", new Class[0]).invoke(null, new Object[0]);
            if (objInvoke == null || !(objInvoke instanceof String)) {
                return null;
            }
            return (String) objInvoke;
        } catch (Exception e) {
            if (e == null) {
                return null;
            }
            C1817e.m4418e("meid:" + e.getMessage());
            return null;
        }
    }

    /* JADX INFO: renamed from: m */
    public static List<InputMethodInfo> m4011m(Context context) {
        if (context == null) {
            return null;
        }
        return ((InputMethodManager) context.getSystemService("input_method")).getInputMethodList();
    }

    /* JADX INFO: renamed from: n */
    public static void m4012n(Context context) throws Throwable {
        if (context == null) {
            return;
        }
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            b bVar = new b();
            if (DeviceConfig.checkPermission(context, "android.permission.BLUETOOTH") && defaultAdapter.isEnabled()) {
                bVar.f4168b = defaultAdapter.getState();
                if (Build.VERSION.SDK_INT < 23) {
                    bVar.f4167a = defaultAdapter.getAddress();
                } else {
                    bVar.f4167a = m3987a(defaultAdapter);
                }
                bVar.f4169c = defaultAdapter.getName();
                UMWorkDispatch.sendEvent(context, C1736a.f4003i, C1763b.m3919a(context).m3920a(), bVar);
            }
        } catch (Exception e) {
            if (e != null) {
                C1817e.m4418e("startBluethInfo:" + e.getMessage());
            }
        }
    }

    /* JADX INFO: renamed from: o */
    public static JSONObject m4013o(Context context) {
        if (context == null) {
            return null;
        }
        return C1791n.m4036a(context);
    }

    /* JADX INFO: renamed from: a */
    private static String m3987a(BluetoothAdapter bluetoothAdapter) {
        if (bluetoothAdapter == null) {
            return null;
        }
        Class<?> cls = bluetoothAdapter.getClass();
        try {
            Class<?> cls2 = Class.forName("android.bluetooth.IBluetooth");
            Field declaredField = cls.getDeclaredField("mService");
            declaredField.setAccessible(true);
            Method method = cls2.getMethod("getAddress", new Class[0]);
            method.setAccessible(true);
            return (String) method.invoke(declaredField.get(bluetoothAdapter), new Object[0]);
        } catch (Exception unused) {
            return bluetoothAdapter.getAddress();
        }
    }

    /* JADX INFO: renamed from: p */
    public static List<a> m4014p(Context context) throws Throwable {
        String[] list;
        if (context == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            File file = new File(Environment.getExternalStorageDirectory() + "/Android/data/");
            if (file != null && file.isDirectory() && (list = file.list()) != null && list.length > 0) {
                for (String str : list) {
                    if (str != null && !str.startsWith(".")) {
                        a aVar = new a();
                        aVar.f4165a = str;
                        aVar.f4166b = m3999e(context, str);
                        arrayList.add(aVar);
                    }
                }
            }
        } catch (Exception e) {
            if (e != null) {
                C1817e.m4418e("getAppList:" + e.getMessage());
            }
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: e */
    private static String m3999e(Context context, String str) throws Throwable {
        if (context == null) {
            return null;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 128);
            if (applicationInfo != null) {
                return (String) applicationInfo.loadLabel(context.getPackageManager());
            }
            return null;
        } catch (Exception e) {
            if (e == null) {
                return null;
            }
            C1817e.m4418e("getLabel:" + e.getMessage());
            return null;
        }
    }

    /* JADX INFO: renamed from: q */
    public static ActivityManager.MemoryInfo m4015q(Context context) {
        if (context == null) {
            return null;
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo;
    }

    /* JADX INFO: renamed from: f */
    public static long m4001f() {
        return System.currentTimeMillis() - SystemClock.elapsedRealtime();
    }

    /* JADX INFO: renamed from: r */
    public static String m4016r(Context context) throws Throwable {
        String string;
        String str = null;
        if (context == null) {
            return null;
        }
        if (Looper.getMainLooper().getThread().getId() == Thread.currentThread().getId()) {
            try {
                String property = System.getProperty("http.agent");
                if (Build.VERSION.SDK_INT >= 17) {
                    StringBuilder sb = new StringBuilder();
                    new WebView(context).getSettings();
                    sb.append(WebSettings.getDefaultUserAgent(context));
                    sb.append("__");
                    sb.append(property);
                    string = sb.toString();
                } else {
                    string = new WebView(context).getSettings().getUserAgentString() + "__" + property;
                }
                str = string;
            } catch (Exception e) {
                if (e != null) {
                    C1817e.m4418e("getUA:" + e.getMessage());
                }
            }
            UMWorkDispatch.sendEvent(context, C1736a.f4005k, C1763b.m3919a(context).m3920a(), str);
        }
        return str;
    }

    /* JADX INFO: renamed from: s */
    public static String m4017s(Context context) {
        if (context == null) {
            return null;
        }
        return C1791n.m4042d(context);
    }

    /* JADX INFO: renamed from: g */
    public static String m4004g() throws Throwable {
        String str = null;
        try {
            Method declaredMethod = Build.class.getDeclaredMethod("getString", String.class);
            declaredMethod.setAccessible(true);
            String string = declaredMethod.invoke(null, "net.hostname").toString();
            if (string != null) {
                try {
                    if (!string.endsWith("")) {
                        return HelperUtils.getUmengMD5(string);
                    }
                } catch (Exception e) {
                    e = e;
                    str = string;
                    if (e == null) {
                        return str;
                    }
                    C1817e.m4418e("getHostName:" + e.getMessage());
                    return str;
                }
            }
            return string;
        } catch (Exception e2) {
            e = e2;
        }
    }
}
