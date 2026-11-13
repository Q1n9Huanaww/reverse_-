package com.umeng.commonsdk.stateless;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.analytics.pro.C1708b;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.proguard.C1746aj;
import com.umeng.commonsdk.proguard.C1782e;
import com.umeng.commonsdk.proguard.C1794q;
import com.umeng.commonsdk.proguard.C1799v;
import com.umeng.commonsdk.statistics.C1819e;
import com.umeng.commonsdk.statistics.common.C1817e;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.idtracking.C1824e;
import com.umeng.commonsdk.utils.UMUtils;
import java.util.Iterator;
import org.json.JSONObject;
import org.slf4j.Marker;

/* JADX INFO: loaded from: classes.dex */
public class UMSLEnvelopeBuild {
    private static final String TAG = "UMSLEnvelopeBuild";
    private static String cacheSystemheader;
    private static boolean isEncryptEnabled;
    public static Context mContext;
    public static String module;

    public synchronized JSONObject buildSLBaseHeader(Context context) {
        JSONObject jSONObject;
        C1817e.m4397a("walle", "[stateless] begin build hader, thread is " + Thread.currentThread());
        if (context == null) {
            return null;
        }
        Context applicationContext = context.getApplicationContext();
        try {
            if (!TextUtils.isEmpty(cacheSystemheader)) {
                try {
                    jSONObject = new JSONObject(cacheSystemheader);
                } catch (Exception unused) {
                    jSONObject = null;
                }
            } else {
                jSONObject = new JSONObject();
                jSONObject.put(C1799v.f4320k, DeviceConfig.getAppMD5Signature(applicationContext));
                jSONObject.put(C1799v.f4321l, DeviceConfig.getAppSHA1Key(applicationContext));
                jSONObject.put(C1799v.f4322m, DeviceConfig.getAppHashKey(applicationContext));
                jSONObject.put("app_version", DeviceConfig.getAppVersionName(applicationContext));
                jSONObject.put("version_code", Integer.parseInt(DeviceConfig.getAppVersionCode(applicationContext)));
                jSONObject.put(C1799v.f4326q, DeviceConfig.getDeviceIdUmengMD5(applicationContext));
                jSONObject.put(C1799v.f4327r, DeviceConfig.getCPU());
                String mccmnc = DeviceConfig.getMCCMNC(applicationContext);
                if (!TextUtils.isEmpty(mccmnc)) {
                    jSONObject.put(C1799v.f4332w, mccmnc);
                } else {
                    jSONObject.put(C1799v.f4332w, "");
                }
                String subOSName = DeviceConfig.getSubOSName(applicationContext);
                if (!TextUtils.isEmpty(subOSName)) {
                    jSONObject.put(C1799v.f4267F, subOSName);
                }
                String subOSVersion = DeviceConfig.getSubOSVersion(applicationContext);
                if (!TextUtils.isEmpty(subOSVersion)) {
                    jSONObject.put(C1799v.f4268G, subOSVersion);
                }
                String deviceType = DeviceConfig.getDeviceType(applicationContext);
                if (!TextUtils.isEmpty(deviceType)) {
                    jSONObject.put(C1799v.f4290ab, deviceType);
                }
                jSONObject.put(C1799v.f4319j, DeviceConfig.getPackageName(applicationContext));
                jSONObject.put(C1799v.f4325p, "Android");
                jSONObject.put("device_id", DeviceConfig.getDeviceId(applicationContext));
                jSONObject.put("device_model", Build.MODEL);
                jSONObject.put(C1799v.f4335z, Build.BOARD);
                jSONObject.put(C1799v.f4262A, Build.BRAND);
                jSONObject.put(C1799v.f4263B, Build.TIME);
                jSONObject.put(C1799v.f4264C, Build.MANUFACTURER);
                jSONObject.put(C1799v.f4265D, Build.ID);
                jSONObject.put(C1799v.f4266E, Build.DEVICE);
                jSONObject.put(C1799v.f4328s, "Android");
                jSONObject.put(C1799v.f4329t, Build.VERSION.RELEASE);
                int[] resolutionArray = DeviceConfig.getResolutionArray(applicationContext);
                if (resolutionArray != null) {
                    jSONObject.put(C1799v.f4330u, resolutionArray[1] + Marker.ANY_MARKER + resolutionArray[0]);
                }
                jSONObject.put(C1799v.f4331v, DeviceConfig.getMac(applicationContext));
                jSONObject.put(C1799v.f4269H, DeviceConfig.getTimeZone(applicationContext));
                String[] localeInfo = DeviceConfig.getLocaleInfo(applicationContext);
                jSONObject.put(C1799v.f4271J, localeInfo[0]);
                jSONObject.put(C1799v.f4270I, localeInfo[1]);
                jSONObject.put(C1799v.f4272K, DeviceConfig.getNetworkOperatorName(applicationContext));
                jSONObject.put(C1799v.f4323n, DeviceConfig.getAppName(applicationContext));
                String[] networkAccessMode = DeviceConfig.getNetworkAccessMode(applicationContext);
                if ("Wi-Fi".equals(networkAccessMode[0])) {
                    jSONObject.put(C1799v.f4273L, "wifi");
                } else if ("2G/3G".equals(networkAccessMode[0])) {
                    jSONObject.put(C1799v.f4273L, "2G/3G");
                } else {
                    jSONObject.put(C1799v.f4273L, "unknow");
                }
                if (!"".equals(networkAccessMode[1])) {
                    jSONObject.put(C1799v.f4274M, networkAccessMode[1]);
                }
                jSONObject.put(C1799v.f4311b, C1819e.f4544a);
                if (!TextUtils.isEmpty(module)) {
                    jSONObject.put(C1799v.f4312c, module);
                }
                cacheSystemheader = jSONObject.toString();
            }
            if (jSONObject == null) {
                return null;
            }
            jSONObject.put("channel", UMUtils.getChannel(applicationContext));
            jSONObject.put("appkey", UMUtils.getAppkey(applicationContext));
            try {
                C1794q c1794q = new C1794q(applicationContext);
                if (c1794q != null && !TextUtils.isEmpty(c1794q.m4058a())) {
                    jSONObject.put(C1799v.f4313d, c1794q.m4058a());
                }
            } catch (Exception unused2) {
            }
            try {
                String strImprintProperty = UMEnvelopeBuild.imprintProperty(applicationContext, C1799v.f4314e, null);
                if (!TextUtils.isEmpty(strImprintProperty)) {
                    jSONObject.put(C1799v.f4314e, strImprintProperty);
                }
            } catch (Exception unused3) {
            }
            if (jSONObject != null && jSONObject.length() > 0) {
                JSONObject jSONObject2 = new JSONObject();
                C1817e.m4397a("walle", "[stateless] build header end , header is " + jSONObject.toString() + ", thread is " + Thread.currentThread());
                return jSONObject2.put("header", jSONObject);
            }
        } catch (Throwable th) {
            C1782e.m3972a(applicationContext, th);
        }
        C1817e.m4397a("walle", "[stateless] build header end , header is null !!! thread is " + Thread.currentThread());
        return null;
    }

    private synchronized JSONObject makeErrorResult(int i, JSONObject jSONObject) {
        try {
            if (jSONObject != null) {
                try {
                    jSONObject.put(C1708b.f3730ao, i);
                } catch (Exception unused) {
                }
                return jSONObject;
            }
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put(C1708b.f3730ao, i);
            } catch (Exception unused2) {
            }
            return jSONObject2;
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized JSONObject buildSLEnvelope(Context context, JSONObject jSONObject, JSONObject jSONObject2, String str) {
        Context applicationContext;
        Throwable th;
        C1806c c1806cConstructEnvelope;
        String str2;
        C1817e.m4397a("walle", "[stateless] build envelope, heade is " + jSONObject.toString());
        C1817e.m4397a("walle", "[stateless] build envelope, body is " + jSONObject2.toString());
        C1817e.m4397a("walle", "[stateless] build envelope, thread is " + Thread.currentThread());
        if (context == null || jSONObject == null || jSONObject2 == null || str == null) {
            C1817e.m4397a("walle", "[stateless] build envelope, context is null or header is null or body is null");
            return makeErrorResult(110, null);
        }
        try {
            applicationContext = context.getApplicationContext();
            if (jSONObject != null && jSONObject2 != null) {
                try {
                    Iterator<String> itKeys = jSONObject2.keys();
                    while (itKeys.hasNext()) {
                        String next = itKeys.next();
                        if (next != null && (next instanceof String) && (str2 = next) != null && jSONObject2.opt(str2) != null) {
                            try {
                                jSONObject.put(str2, jSONObject2.opt(str2));
                            } catch (Exception unused) {
                            }
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    C1782e.m3972a(applicationContext, th);
                    C1817e.m4397a("walle", "build envelope end, thread is " + Thread.currentThread());
                    return makeErrorResult(110, null);
                }
            }
            if (jSONObject != null) {
                try {
                    C1824e c1824eM4444a = C1824e.m4444a(applicationContext);
                    if (c1824eM4444a != null) {
                        c1824eM4444a.m4449a();
                        String strEncodeToString = Base64.encodeToString(new C1746aj().m3809a(c1824eM4444a.m4451b()), 0);
                        if (!TextUtils.isEmpty(strEncodeToString)) {
                            JSONObject jSONObject3 = jSONObject.getJSONObject("header");
                            jSONObject3.put(C1799v.f4279R, strEncodeToString);
                            jSONObject.put("header", jSONObject3);
                        }
                    }
                } catch (Exception unused2) {
                }
            }
            if (jSONObject != null && C1809f.m4311a(jSONObject.toString().getBytes().length, C1804a.f4395a)) {
                C1817e.m4397a("walle", "[stateless] build envelope, json overstep!!!! size is " + jSONObject.toString().getBytes().length);
                return makeErrorResult(113, jSONObject);
            }
            C1817e.m4397a("walle", "[stateless] build envelope, json size is " + jSONObject.toString().getBytes().length);
            if (jSONObject != null) {
                c1806cConstructEnvelope = constructEnvelope(applicationContext, jSONObject.toString().getBytes());
                if (c1806cConstructEnvelope == null) {
                    C1817e.m4397a("walle", "[stateless] build envelope, envelope is null !!!!");
                    return makeErrorResult(111, jSONObject);
                }
            } else {
                c1806cConstructEnvelope = null;
            }
            if (c1806cConstructEnvelope != null && C1809f.m4311a(c1806cConstructEnvelope.m4295b().length, C1804a.f4396b)) {
                C1817e.m4397a("walle", "[stateless] build envelope, envelope overstep!!!! size is " + c1806cConstructEnvelope.m4295b().length);
                return makeErrorResult(114, jSONObject);
            }
            if (!C1809f.m4312a(applicationContext, Base64.encodeToString(str.getBytes(), 0), Base64.encodeToString((str + "_" + System.currentTimeMillis()).getBytes(), 0), c1806cConstructEnvelope.m4295b())) {
                C1817e.m4397a("walle", "[stateless] build envelope, save fail ----->>>>>");
                return makeErrorResult(101, jSONObject);
            }
            C1817e.m4397a("walle", "[stateless] build envelope, save ok ----->>>>>");
            C1817e.m4397a("walle", "[stateless] envelope file size is " + jSONObject.toString().getBytes().length);
            new C1807d(applicationContext);
            C1807d.m4301b(273);
            C1817e.m4397a("walle", "[stateless] build envelope end, thread is " + Thread.currentThread());
            return jSONObject;
        } catch (Throwable th3) {
            applicationContext = context;
            th = th3;
        }
    }

    private synchronized C1806c constructEnvelope(Context context, byte[] bArr) {
        C1806c c1806cM4288a;
        int iIntValue = -1;
        String strImprintProperty = UMEnvelopeBuild.imprintProperty(context, "slcodex", null);
        C1817e.m4397a("walle", "[stateless] build envelope, codexStr is " + strImprintProperty);
        try {
            if (!TextUtils.isEmpty(strImprintProperty)) {
                iIntValue = Integer.valueOf(strImprintProperty).intValue();
            }
        } catch (NumberFormatException e) {
            C1782e.m3972a(context, e);
        }
        if (iIntValue == 0) {
            C1817e.m4397a("walle", "[stateless] build envelope, codexValue is 0");
            c1806cM4288a = C1806c.m4288a(context, UMUtils.getAppkey(context), bArr);
        } else if (iIntValue == 1) {
            C1817e.m4397a("walle", "[stateless] build envelope, codexValue is 1");
            c1806cM4288a = C1806c.m4290b(context, UMUtils.getAppkey(context), bArr);
        } else if (isEncryptEnabled) {
            C1817e.m4397a("walle", "[stateless] build envelope, isEncryptEnabled is true");
            c1806cM4288a = C1806c.m4290b(context, UMUtils.getAppkey(context), bArr);
        } else {
            C1817e.m4397a("walle", "[stateless] build envelope, isEncryptEnabled is false");
            c1806cM4288a = C1806c.m4288a(context, UMUtils.getAppkey(context), bArr);
        }
        return c1806cM4288a;
    }

    public static void setEncryptEnabled(boolean z) {
        isEncryptEnabled = z;
    }
}
