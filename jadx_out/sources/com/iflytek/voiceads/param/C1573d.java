package com.iflytek.voiceads.param;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.utils.C1590d;
import com.iflytek.voiceads.utils.C1593g;
import com.iflytek.voiceads.utils.C1594h;
import com.iflytek.voiceads.utils.C1595i;
import com.iflytek.voiceads.utils.C1597k;
import com.p024ds.daisi.net.URL.URLConstant;
import com.umeng.commonsdk.proguard.C1799v;
import java.util.Locale;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.iflytek.voiceads.param.d */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1573d {

    /* JADX INFO: renamed from: a */
    private static String f3371a;

    /* JADX INFO: renamed from: a */
    public static long m3090a() {
        return System.currentTimeMillis() / 1000;
    }

    /* JADX INFO: renamed from: a */
    public static JSONObject m3091a(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(C1799v.f4290ab, m3098e(context));
            jSONObject.put(C1799v.f4328s, m3092b());
            jSONObject.put("osv", m3095c());
            jSONObject.put("adid", m3102g(context));
            jSONObject.put("adid_md5", C1590d.m3179a(m3102g(context)));
            jSONObject.put("imei", m3103h(context));
            jSONObject.put("imei_md5", C1590d.m3179a(m3103h(context)));
            jSONObject.put("mac", C1594h.m3192a(context));
            jSONObject.put("mac_md5", C1590d.m3179a(C1594h.m3192a(context)));
            jSONObject.put("density", m3105j(context));
            jSONObject.put(C1799v.f4272K, m3106k(context));
            jSONObject.put("net", m3107l(context));
            jSONObject.put("ip", m3097d());
            jSONObject.put("ua", m3093b(context));
            jSONObject.put("dvw", m3094c(context));
            jSONObject.put("dvh", m3096d(context));
            jSONObject.put("orientation", m3109n(context));
            jSONObject.put("make", m3099e());
            jSONObject.put("model", m3100f());
            jSONObject.put("lan", m3110o(context));
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "get device:" + e.getMessage());
        }
        return jSONObject;
    }

    /* JADX INFO: renamed from: b */
    private static int m3092b() {
        return 0;
    }

    /* JADX INFO: renamed from: b */
    public static String m3093b(Context context) {
        if (TextUtils.isEmpty(f3371a)) {
            try {
                if (Build.VERSION.SDK_INT >= 19) {
                    f3371a = WebSettings.getDefaultUserAgent(context);
                } else {
                    f3371a = new WebView(context).getSettings().getUserAgentString();
                }
            } catch (Exception e) {
                f3371a = "";
            }
        }
        return f3371a;
    }

    /* JADX INFO: renamed from: c */
    public static int m3094c(Context context) {
        return m3108m(context).widthPixels;
    }

    /* JADX INFO: renamed from: c */
    private static String m3095c() {
        return Build.VERSION.RELEASE;
    }

    /* JADX INFO: renamed from: d */
    public static int m3096d(Context context) {
        return m3108m(context).heightPixels;
    }

    /* JADX INFO: renamed from: d */
    private static String m3097d() {
        return C1597k.m3205a();
    }

    /* JADX INFO: renamed from: e */
    private static int m3098e(Context context) {
        String strM3093b = m3093b(context);
        if (TextUtils.isEmpty(strM3093b)) {
            return -1;
        }
        return (!(strM3093b.contains("Mobile") && strM3093b.contains("Android")) && strM3093b.contains("Android") && m3101f(context)) ? 1 : 0;
    }

    /* JADX INFO: renamed from: e */
    private static String m3099e() {
        return Build.MANUFACTURER;
    }

    /* JADX INFO: renamed from: f */
    private static String m3100f() {
        return Build.MODEL;
    }

    /* JADX INFO: renamed from: f */
    private static boolean m3101f(Context context) {
        return (context.getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    /* JADX INFO: renamed from: g */
    private static String m3102g(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), InterfaceRelatedConstants.ANDROID_ID_SDCARD_FILE);
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "getAndroidID:" + e.getMessage());
            return "";
        }
    }

    /* JADX INFO: renamed from: h */
    private static String m3103h(Context context) {
        String deviceId;
        Exception e;
        if (!C1595i.m3201b(context, "android.permission.READ_PHONE_STATE")) {
            return "";
        }
        String deviceId2 = "";
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(URLConstant.USER_ACCOUNT);
            deviceId2 = telephonyManager.getDeviceId();
            if (!TextUtils.isEmpty(deviceId2)) {
                return deviceId2;
            }
            deviceId = Build.VERSION.SDK_INT >= 23 ? telephonyManager.getDeviceId(0) : deviceId2;
            try {
                return TextUtils.isEmpty(deviceId) ? m3104i(context) : deviceId;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            deviceId = deviceId2;
            e = e3;
        }
        C1593g.m3190b(SDKConstants.TAG, "get IMEI:" + e.getMessage());
        return deviceId;
    }

    /* JADX INFO: renamed from: i */
    private static String m3104i(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(URLConstant.USER_ACCOUNT);
        try {
            return telephonyManager.getClass().getDeclaredMethod("getImei", Integer.TYPE).invoke(telephonyManager, 1).toString();
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "Get invoke imei:" + e.getMessage());
            return "";
        }
    }

    /* JADX INFO: renamed from: j */
    private static int m3105j(Context context) {
        return context.getResources().getDisplayMetrics().densityDpi;
    }

    /* JADX INFO: renamed from: k */
    private static int m3106k(Context context) {
        try {
            String networkOperatorName = ((TelephonyManager) context.getSystemService(URLConstant.USER_ACCOUNT)).getNetworkOperatorName();
            if (!TextUtils.isEmpty(networkOperatorName)) {
                if (networkOperatorName.contains("46000") || networkOperatorName.contains("46002") || networkOperatorName.contains("46004") || networkOperatorName.contains("46007")) {
                    return 1;
                }
                if (networkOperatorName.contains("46001") || networkOperatorName.contains("46006") || networkOperatorName.contains("46009")) {
                    return 2;
                }
                return (networkOperatorName.contains("46003") || networkOperatorName.contains("46005") || networkOperatorName.contains("46011")) ? 3 : 0;
            }
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "Get carrier failed:" + e.getMessage());
        }
        return 0;
    }

    /* JADX INFO: renamed from: l */
    private static int m3107l(Context context) {
        if (!C1595i.m3199a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return 0;
        }
        String strM3209c = C1597k.m3209c(context);
        if (strM3209c.contains("wifi")) {
            return 2;
        }
        if (strM3209c.contains("2g")) {
            return 4;
        }
        if (strM3209c.contains("3g")) {
            return 5;
        }
        return strM3209c.contains("4g") ? 6 : 0;
    }

    /* JADX INFO: renamed from: m */
    private static DisplayMetrics m3108m(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if ((context instanceof Activity) && Build.VERSION.SDK_INT >= 17) {
            ((Activity) context).getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        }
        return displayMetrics;
    }

    /* JADX INFO: renamed from: n */
    private static int m3109n(Context context) {
        return 0;
    }

    /* JADX INFO: renamed from: o */
    private static String m3110o(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        return locale != null ? locale.getLanguage() + "-" + locale.getCountry() : "";
    }
}
