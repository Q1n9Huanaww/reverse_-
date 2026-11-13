package mobi.oneway.export.p087g;

import android.annotation.SuppressLint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.net.URL.URLConstant;
import java.lang.reflect.Method;
import mobi.oneway.export.p078a.C1900b;

/* JADX INFO: renamed from: mobi.oneway.export.g.f */
/* JADX INFO: loaded from: classes.dex */
public class C1952f {

    /* JADX INFO: renamed from: mobi.oneway.export.g.f$a */
    private enum a {
        MAC,
        SSID,
        BSSID
    }

    @SuppressLint({"MissingPermission"})
    /* JADX INFO: renamed from: a */
    public static String m4985a() {
        String imei;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) C1900b.m4706a().getSystemService(URLConstant.USER_ACCOUNT);
            if (Build.VERSION.SDK_INT >= 26) {
                imei = TextUtils.isEmpty(telephonyManager.getImei()) ? "" : telephonyManager.getImei();
            } else {
                Method declaredMethod = telephonyManager.getClass().getDeclaredMethod("getImei", new Class[0]);
                declaredMethod.setAccessible(true);
                imei = (String) declaredMethod.invoke(telephonyManager, new Object[0]);
            }
            return TextUtils.isEmpty(imei) ? "" : imei;
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX INFO: renamed from: a */
    private static String m4986a(String str) {
        if (str == null) {
            return "";
        }
        return (str.startsWith("\"") && str.endsWith("\"")) ? str.substring(1, str.length() - 1) : str;
    }

    /* JADX INFO: renamed from: a */
    private static String m4987a(a aVar) {
        WifiInfo connectionInfo;
        WifiManager wifiManager = (WifiManager) C1900b.m4706a().getApplicationContext().getSystemService("wifi");
        if (wifiManager == null || (connectionInfo = wifiManager.getConnectionInfo()) == null) {
            return "";
        }
        switch (aVar) {
            case MAC:
                return connectionInfo.getMacAddress();
            case SSID:
                return m4986a(connectionInfo.getSSID());
            case BSSID:
                return connectionInfo.getBSSID();
            default:
                return "";
        }
    }

    /* JADX INFO: renamed from: b */
    public static String m4988b() {
        try {
            return C1900b.m4706a().getPackageName();
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX INFO: renamed from: c */
    public static String m4989c() {
        try {
            return C1900b.m4706a().getPackageManager().getPackageInfo(m4988b(), 0).versionName;
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX INFO: renamed from: d */
    public static String m4990d() {
        String str = System.getProperty("http.agent") + " AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Mobile Safari/537.36 ";
        return TextUtils.isEmpty(str) ? "Mozilla/5.0 (Linux; Android 5.0; SM-G900P Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Mobile Safari/537.36 OneWaySDK" : str;
    }

    /* JADX INFO: renamed from: e */
    public static String m4991e() {
        try {
            return Settings.Secure.getString(C1900b.m4706a().getContentResolver(), InterfaceRelatedConstants.ANDROID_ID_SDCARD_FILE);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    /* JADX INFO: renamed from: f */
    public static String m4992f() {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) C1900b.m4706a().getSystemService("connectivity");
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) {
            return "none";
        }
        switch (activeNetworkInfo.getType()) {
            case 0:
                return "moblie";
            case 1:
                return "wifi";
            default:
                return "none";
        }
    }

    /* JADX INFO: renamed from: g */
    public static int m4993g() {
        try {
            if (m4992f().equals("wifi")) {
                return 99;
            }
            return ((TelephonyManager) C1900b.m4706a().getSystemService(URLConstant.USER_ACCOUNT)).getNetworkType();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return 0;
        }
    }

    /* JADX INFO: renamed from: h */
    public static String m4994h() {
        return m4987a(a.SSID);
    }

    /* JADX INFO: renamed from: i */
    public static String m4995i() {
        return m4987a(a.BSSID);
    }

    /* JADX INFO: renamed from: j */
    public static String m4996j() {
        try {
            return ((TelephonyManager) C1900b.m4706a().getSystemService(URLConstant.USER_ACCOUNT)).getSimOperator();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return "none";
        }
    }

    /* JADX INFO: renamed from: k */
    public static String m4997k() {
        try {
            return ((TelephonyManager) C1900b.m4706a().getApplicationContext().getSystemService(URLConstant.USER_ACCOUNT)).getSimSerialNumber();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return "none";
        }
    }
}
