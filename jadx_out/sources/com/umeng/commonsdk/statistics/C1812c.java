package com.umeng.commonsdk.statistics;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.umeng.analytics.pro.C1708b;
import com.umeng.commonsdk.framework.C1732b;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.proguard.C1746aj;
import com.umeng.commonsdk.proguard.C1782e;
import com.umeng.commonsdk.proguard.C1794q;
import com.umeng.commonsdk.proguard.C1799v;
import com.umeng.commonsdk.statistics.common.C1817e;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.idtracking.C1824e;
import com.umeng.commonsdk.statistics.idtracking.C1826g;
import com.umeng.commonsdk.statistics.idtracking.Envelope;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.statistics.internal.StatTracer;
import com.umeng.commonsdk.statistics.noise.ImLatent;
import com.umeng.commonsdk.statistics.proto.C1855d;
import com.umeng.commonsdk.utils.UMUtils;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Marker;

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.c */
/* JADX INFO: compiled from: EnvelopeManager.java */
/* JADX INFO: loaded from: classes.dex */
public class C1812c {

    /* JADX INFO: renamed from: a */
    public static String f4490a = null;

    /* JADX INFO: renamed from: b */
    private static final String f4491b = "EnvelopeManager";

    /* JADX INFO: renamed from: c */
    private static StatTracer f4492c;

    /* JADX INFO: renamed from: d */
    private static ImLatent f4493d;

    /* JADX INFO: renamed from: e */
    private static String f4494e;

    /* JADX INFO: renamed from: g */
    private static boolean f4495g;

    /* JADX INFO: renamed from: f */
    private int f4496f = 0;

    /* JADX INFO: renamed from: a */
    public static long m4324a(Context context) {
        long j;
        long j2 = DataHelper.ENVELOPE_ENTITY_RAW_LENGTH_MAX - DataHelper.ENVELOPE_EXTRA_LENGTH;
        JSONObject jSONObjectM4329b = m4329b(context);
        if (jSONObjectM4329b == null || jSONObjectM4329b.toString() == null || jSONObjectM4329b.toString().getBytes() == null) {
            j = j2;
        } else {
            long length = jSONObjectM4329b.toString().getBytes().length;
            if (C1817e.f4515a) {
                Log.i(f4491b, "headerLen size is " + length);
            }
            j = j2 - length;
        }
        if (C1817e.f4515a) {
            Log.i(f4491b, "free size is " + j);
        }
        return j;
    }

    /* JADX INFO: renamed from: a */
    private JSONObject m4326a(int i, JSONObject jSONObject) {
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
    }

    /* JADX WARN: Code duplicated, block: B:150:0x0344 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:151:0x0346 A[Catch: Exception -> 0x0334, TRY_ENTER, TryCatch #4 {Exception -> 0x0334, blocks: (B:140:0x032e, B:151:0x0346, B:153:0x034e, B:154:0x0352, B:156:0x0358, B:158:0x035e, B:160:0x0362, B:162:0x0366), top: B:180:0x032e }] */
    /* JADX WARN: Code duplicated, block: B:153:0x034e A[Catch: Exception -> 0x0334, TryCatch #4 {Exception -> 0x0334, blocks: (B:140:0x032e, B:151:0x0346, B:153:0x034e, B:154:0x0352, B:156:0x0358, B:158:0x035e, B:160:0x0362, B:162:0x0366), top: B:180:0x032e }] */
    /* JADX WARN: Code duplicated, block: B:156:0x0358 A[Catch: Exception -> 0x0334, TryCatch #4 {Exception -> 0x0334, blocks: (B:140:0x032e, B:151:0x0346, B:153:0x034e, B:154:0x0352, B:156:0x0358, B:158:0x035e, B:160:0x0362, B:162:0x0366), top: B:180:0x032e }] */
    /* JADX INFO: renamed from: a */
    public JSONObject m4330a(Context context, JSONObject jSONObject, JSONObject jSONObject2) {
        Exception exc;
        Iterator<String> itKeys;
        String next;
        String str;
        String string;
        Envelope envelopeM4325a;
        String str2;
        if (C1817e.f4515a && jSONObject != null && jSONObject2 != null) {
            Log.i(f4491b, "headerJSONObject size is " + jSONObject.toString().getBytes().length);
            Log.i(f4491b, "bodyJSONObject size is " + jSONObject2.toString().getBytes().length);
        }
        JSONObject jSONObject3 = null;
        if (context == null || jSONObject2 == null) {
            return m4326a(110, (JSONObject) null);
        }
        try {
            JSONObject jSONObjectM4329b = m4329b(context);
            if (jSONObjectM4329b != null && jSONObject != null) {
                jSONObjectM4329b = m4327a(jSONObjectM4329b, jSONObject);
            }
            if (jSONObjectM4329b != null && jSONObject2 != null) {
                Iterator<String> itKeys2 = jSONObject2.keys();
                while (itKeys2.hasNext()) {
                    String next2 = itKeys2.next();
                    if (next2 != null && (next2 instanceof String) && (str2 = next2) != null && jSONObject2.opt(str2) != null) {
                        try {
                            jSONObjectM4329b.put(str2, jSONObject2.opt(str2));
                        } catch (Exception unused) {
                        }
                    }
                }
            }
            if (jSONObjectM4329b != null) {
                StringBuilder sb = new StringBuilder();
                if (jSONObjectM4329b.length() > 0) {
                    if (jSONObjectM4329b.has("push")) {
                        String strOptString = jSONObjectM4329b.optJSONObject("header").optString(C1799v.f4303ao);
                        if (!TextUtils.isEmpty(C1799v.f4299ak) && !TextUtils.isEmpty(strOptString)) {
                            sb.append(C1799v.f4299ak);
                            sb.append("==");
                            sb.append(strOptString);
                            sb.append("&=");
                        }
                    }
                    if (jSONObjectM4329b.has("share")) {
                        String strOptString2 = jSONObjectM4329b.optJSONObject("header").optString(C1799v.f4304ap);
                        if (!TextUtils.isEmpty(C1799v.f4300al) && !TextUtils.isEmpty(strOptString2)) {
                            sb.append(C1799v.f4300al);
                            sb.append("==");
                            sb.append(strOptString2);
                            sb.append("&=");
                        }
                    }
                    if (jSONObjectM4329b.has("analytics")) {
                        String str3 = jSONObjectM4329b.has("dplus") ? C1799v.f4298aj : C1799v.f4296ah;
                        String strOptString3 = jSONObjectM4329b.optJSONObject("header").optString("sdk_version");
                        if (!TextUtils.isEmpty(str3) && !TextUtils.isEmpty(strOptString3)) {
                            sb.append(str3);
                            sb.append("==");
                            sb.append(strOptString3);
                            sb.append("&=");
                        }
                    }
                    if (jSONObjectM4329b.has("dplus")) {
                        String strOptString4 = jSONObjectM4329b.optJSONObject("header").optString("sdk_version");
                        if (jSONObjectM4329b.has("analytics")) {
                            if (!sb.toString().contains(C1799v.f4298aj) && !TextUtils.isEmpty(C1799v.f4298aj) && !TextUtils.isEmpty(strOptString4)) {
                                sb.append(C1799v.f4298aj);
                                sb.append("==");
                                sb.append(strOptString4);
                                sb.append("&=");
                            }
                        } else if (!TextUtils.isEmpty(C1799v.f4297ai) && !TextUtils.isEmpty(strOptString4)) {
                            sb.append(C1799v.f4297ai);
                            sb.append("==");
                            sb.append(strOptString4);
                            sb.append("&=");
                        }
                    }
                    if (jSONObjectM4329b.has(C1799v.f4295ag)) {
                        String strOptString5 = jSONObjectM4329b.optJSONObject("header").optString(C1799v.f4305aq);
                        if (!TextUtils.isEmpty(C1799v.f4301am) && !TextUtils.isEmpty(strOptString5)) {
                            sb.append(C1799v.f4301am);
                            sb.append("==");
                            sb.append(strOptString5);
                            sb.append("&=");
                        }
                    }
                }
                string = sb.toString();
                if (TextUtils.isEmpty(string)) {
                    return m4326a(101, jSONObjectM4329b);
                }
                if (string.endsWith("&=")) {
                    string = string.substring(0, string.length() - 2);
                }
            } else {
                string = null;
            }
            if (jSONObjectM4329b != null) {
                try {
                    C1824e c1824eM4444a = C1824e.m4444a(context);
                    if (c1824eM4444a != null) {
                        c1824eM4444a.m4449a();
                        String strEncodeToString = Base64.encodeToString(new C1746aj().m3809a(c1824eM4444a.m4451b()), 0);
                        if (!TextUtils.isEmpty(strEncodeToString)) {
                            JSONObject jSONObject4 = jSONObjectM4329b.getJSONObject("header");
                            jSONObject4.put(C1799v.f4279R, strEncodeToString);
                            jSONObjectM4329b.put("header", jSONObject4);
                        }
                    }
                } catch (Exception unused2) {
                }
            }
            if (f4492c == null) {
                f4492c = StatTracer.getInstance(context);
                f4493d = ImLatent.getService(context, f4492c);
            }
            if (f4493d != null && f4493d.shouldStartLatency()) {
                long delayTime = f4493d.getDelayTime();
                long elapsedTime = f4493d.getElapsedTime();
                if (jSONObjectM4329b != null) {
                    try {
                        JSONObject jSONObject5 = new JSONObject();
                        JSONObject jSONObject6 = new JSONObject();
                        jSONObject6.put(C1799v.f4310av, elapsedTime / 1000);
                        jSONObject6.put(C1799v.f4309au, delayTime);
                        jSONObject5.put(C1799v.f4308at, jSONObject6);
                        JSONObject jSONObject7 = jSONObjectM4329b.getJSONObject("header");
                        jSONObject7.put(C1799v.f4307as, jSONObject5);
                        jSONObjectM4329b.put("header", jSONObject7);
                    } catch (Exception unused3) {
                    }
                }
            }
            if (jSONObjectM4329b != null && DataHelper.largeThanMaxSize(jSONObjectM4329b.toString().getBytes().length, DataHelper.ENVELOPE_ENTITY_RAW_LENGTH_MAX)) {
                SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
                if (sharedPreferences != null) {
                    sharedPreferences.edit().putInt("serial", sharedPreferences.getInt("serial", 1) + 1).commit();
                }
                return m4326a(113, jSONObjectM4329b);
            }
            if (jSONObjectM4329b != null) {
                envelopeM4325a = m4325a(context, jSONObjectM4329b.toString().getBytes());
                if (envelopeM4325a == null) {
                    return m4326a(111, jSONObjectM4329b);
                }
            } else {
                envelopeM4325a = null;
            }
            if (envelopeM4325a != null && DataHelper.largeThanMaxSize(envelopeM4325a.toBinary().length, DataHelper.ENVELOPE_LENGTH_MAX)) {
                return m4326a(114, jSONObjectM4329b);
            }
            int iM4323a = m4323a(context, envelopeM4325a, string, jSONObjectM4329b != null ? jSONObjectM4329b.optJSONObject("header").optString("app_version") : null);
            if (iM4323a != 0) {
                return m4326a(iM4323a, jSONObjectM4329b);
            }
            if (C1817e.f4515a) {
                Log.i(f4491b, "constructHeader size is " + jSONObjectM4329b.toString().getBytes().length);
            }
            return jSONObjectM4329b;
        } catch (Throwable th) {
            C1782e.m3972a(context, th);
            if (jSONObject != null) {
                try {
                    JSONObject jSONObject8 = new JSONObject();
                    try {
                        jSONObject8.put("header", jSONObject);
                    } catch (JSONException unused4) {
                    } catch (Exception e) {
                        exc = e;
                        jSONObject3 = jSONObject8;
                        C1782e.m3972a(context, exc);
                        return m4326a(110, jSONObject3);
                    }
                    jSONObject3 = jSONObject8;
                    if (jSONObject2 != null) {
                        if (jSONObject3 == null) {
                            jSONObject3 = new JSONObject();
                        }
                        if (jSONObject2 != null) {
                            itKeys = jSONObject2.keys();
                            while (itKeys.hasNext()) {
                                next = itKeys.next();
                                if (next == null && (next instanceof String) && (str = next) != null && jSONObject2.opt(str) != null) {
                                    try {
                                        jSONObject3.put(str, jSONObject2.opt(str));
                                    } catch (Exception unused5) {
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e2) {
                    exc = e2;
                }
            } else if (jSONObject2 != null) {
                if (jSONObject3 == null) {
                    jSONObject3 = new JSONObject();
                }
                if (jSONObject2 != null) {
                    itKeys = jSONObject2.keys();
                    while (itKeys.hasNext()) {
                        next = itKeys.next();
                        if (next == null) {
                        }
                    }
                }
            }
            return m4326a(110, jSONObject3);
        }
    }

    /* JADX WARN: Code duplicated, block: B:59:0x0229 A[Catch: Exception -> 0x022f, Throwable -> 0x0268, TRY_LEAVE, TryCatch #1 {Exception -> 0x022f, blocks: (B:57:0x021d, B:59:0x0229), top: B:80:0x021d, outer: #6 }] */
    /* JADX WARN: Code duplicated, block: B:65:0x023d A[Catch: Exception -> 0x0250, Throwable -> 0x0268, TRY_LEAVE, TryCatch #0 {Exception -> 0x0250, blocks: (B:63:0x0233, B:65:0x023d), top: B:78:0x0233, outer: #6 }] */
    /* JADX WARN: Code duplicated, block: B:69:0x0254 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:70:0x0256 A[Catch: Throwable -> 0x0268, TryCatch #6 {Throwable -> 0x0268, blocks: (B:3:0x0001, B:6:0x000e, B:41:0x01b7, B:42:0x01d8, B:43:0x01ea, B:45:0x01f4, B:49:0x01fe, B:51:0x0205, B:53:0x020f, B:57:0x021d, B:59:0x0229, B:63:0x0233, B:65:0x023d, B:70:0x0256, B:72:0x025c, B:68:0x0251, B:62:0x0230, B:56:0x021a, B:48:0x01fb, B:9:0x001a, B:11:0x006c, B:13:0x0079, B:15:0x0083, B:16:0x0088, B:18:0x0092, B:19:0x0097, B:21:0x00a1, B:22:0x00a6, B:24:0x0105, B:25:0x0122, B:27:0x0166, B:32:0x0187, B:34:0x0191, B:35:0x0198, B:37:0x01a7, B:38:0x01ae, B:28:0x016e, B:30:0x0178, B:31:0x0180, B:12:0x0072), top: B:90:0x0001, inners: #0, #1, #2, #3 }] */
    /* JADX WARN: Code duplicated, block: B:78:0x0233 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:80:0x021d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX INFO: renamed from: b */
    private static JSONObject m4329b(Context context) {
        JSONObject jSONObject;
        C1855d c1855dM4467a;
        String strImprintProperty;
        C1794q c1794q;
        try {
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
            if (!TextUtils.isEmpty(f4494e)) {
                try {
                    jSONObject = new JSONObject(f4494e);
                } catch (Exception unused) {
                    jSONObject = null;
                }
            } else {
                jSONObject = new JSONObject();
                jSONObject.put(C1799v.f4320k, DeviceConfig.getAppMD5Signature(context));
                jSONObject.put(C1799v.f4321l, DeviceConfig.getAppSHA1Key(context));
                jSONObject.put(C1799v.f4322m, DeviceConfig.getAppHashKey(context));
                jSONObject.put("app_version", DeviceConfig.getAppVersionName(context));
                jSONObject.put("version_code", Integer.parseInt(DeviceConfig.getAppVersionCode(context)));
                jSONObject.put(C1799v.f4326q, DeviceConfig.getDeviceIdUmengMD5(context));
                jSONObject.put(C1799v.f4327r, DeviceConfig.getCPU());
                String mccmnc = DeviceConfig.getMCCMNC(context);
                if (!TextUtils.isEmpty(mccmnc)) {
                    jSONObject.put(C1799v.f4332w, mccmnc);
                } else {
                    jSONObject.put(C1799v.f4332w, "");
                }
                String subOSName = DeviceConfig.getSubOSName(context);
                if (!TextUtils.isEmpty(subOSName)) {
                    jSONObject.put(C1799v.f4267F, subOSName);
                }
                String subOSVersion = DeviceConfig.getSubOSVersion(context);
                if (!TextUtils.isEmpty(subOSVersion)) {
                    jSONObject.put(C1799v.f4268G, subOSVersion);
                }
                String deviceType = DeviceConfig.getDeviceType(context);
                if (!TextUtils.isEmpty(deviceType)) {
                    jSONObject.put(C1799v.f4290ab, deviceType);
                }
                jSONObject.put(C1799v.f4319j, DeviceConfig.getPackageName(context));
                jSONObject.put(C1799v.f4325p, "Android");
                jSONObject.put("device_id", DeviceConfig.getDeviceId(context));
                jSONObject.put("device_model", Build.MODEL);
                jSONObject.put(C1799v.f4335z, Build.BOARD);
                jSONObject.put(C1799v.f4262A, Build.BRAND);
                jSONObject.put(C1799v.f4263B, Build.TIME);
                jSONObject.put(C1799v.f4264C, Build.MANUFACTURER);
                jSONObject.put(C1799v.f4265D, Build.ID);
                jSONObject.put(C1799v.f4266E, Build.DEVICE);
                jSONObject.put(C1799v.f4328s, "Android");
                jSONObject.put(C1799v.f4329t, Build.VERSION.RELEASE);
                int[] resolutionArray = DeviceConfig.getResolutionArray(context);
                if (resolutionArray != null) {
                    jSONObject.put(C1799v.f4330u, resolutionArray[1] + Marker.ANY_MARKER + resolutionArray[0]);
                }
                jSONObject.put(C1799v.f4331v, DeviceConfig.getMac(context));
                jSONObject.put(C1799v.f4269H, DeviceConfig.getTimeZone(context));
                String[] localeInfo = DeviceConfig.getLocaleInfo(context);
                jSONObject.put(C1799v.f4271J, localeInfo[0]);
                jSONObject.put(C1799v.f4270I, localeInfo[1]);
                jSONObject.put(C1799v.f4272K, DeviceConfig.getNetworkOperatorName(context));
                jSONObject.put(C1799v.f4323n, DeviceConfig.getAppName(context));
                String[] networkAccessMode = DeviceConfig.getNetworkAccessMode(context);
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
                if (!TextUtils.isEmpty(f4490a)) {
                    jSONObject.put(C1799v.f4312c, f4490a);
                }
                f4494e = jSONObject.toString();
            }
            if (jSONObject == null) {
                return null;
            }
            try {
                jSONObject.put(C1799v.f4275N, sharedPreferences.getInt("successful_request", 0));
                jSONObject.put(C1799v.f4276O, sharedPreferences.getInt(C1799v.f4276O, 0));
                jSONObject.put(C1799v.f4277P, sharedPreferences.getInt("last_request_spent_ms", 0));
            } catch (Exception unused2) {
            }
            jSONObject.put("channel", UMUtils.getChannel(context));
            jSONObject.put("appkey", UMUtils.getAppkey(context));
            try {
                String deviceToken = UMUtils.getDeviceToken(context);
                if (!TextUtils.isEmpty(deviceToken)) {
                    jSONObject.put(C1799v.f4288a, deviceToken);
                    try {
                        c1794q = new C1794q(context);
                        if (c1794q == null && !TextUtils.isEmpty(c1794q.m4058a())) {
                            jSONObject.put(C1799v.f4313d, c1794q.m4058a());
                            strImprintProperty = UMEnvelopeBuild.imprintProperty(context, C1799v.f4314e, null);
                            if (!TextUtils.isEmpty(strImprintProperty)) {
                                jSONObject.put(C1799v.f4314e, strImprintProperty);
                                c1855dM4467a = C1826g.m4461a(context).m4467a();
                                if (c1855dM4467a != null) {
                                    jSONObject.put(C1799v.f4278Q, Base64.encodeToString(new C1746aj().m3809a(c1855dM4467a), 0));
                                    if (jSONObject != null) {
                                        return new JSONObject().put("header", jSONObject);
                                    }
                                } else if (jSONObject != null) {
                                    return new JSONObject().put("header", jSONObject);
                                }
                            } else {
                                c1855dM4467a = C1826g.m4461a(context).m4467a();
                                if (c1855dM4467a != null) {
                                    jSONObject.put(C1799v.f4278Q, Base64.encodeToString(new C1746aj().m3809a(c1855dM4467a), 0));
                                    if (jSONObject != null) {
                                        return new JSONObject().put("header", jSONObject);
                                    }
                                } else if (jSONObject != null) {
                                    return new JSONObject().put("header", jSONObject);
                                }
                            }
                        } else {
                            try {
                                strImprintProperty = UMEnvelopeBuild.imprintProperty(context, C1799v.f4314e, null);
                                if (!TextUtils.isEmpty(strImprintProperty)) {
                                    jSONObject.put(C1799v.f4314e, strImprintProperty);
                                    try {
                                        c1855dM4467a = C1826g.m4461a(context).m4467a();
                                        if (c1855dM4467a != null) {
                                            jSONObject.put(C1799v.f4278Q, Base64.encodeToString(new C1746aj().m3809a(c1855dM4467a), 0));
                                            if (jSONObject != null && jSONObject.length() > 0) {
                                                return new JSONObject().put("header", jSONObject);
                                            }
                                        } else if (jSONObject != null) {
                                            return new JSONObject().put("header", jSONObject);
                                        }
                                    } catch (Exception e) {
                                        C1782e.m3972a(context, e);
                                    }
                                } else {
                                    c1855dM4467a = C1826g.m4461a(context).m4467a();
                                    if (c1855dM4467a != null) {
                                        jSONObject.put(C1799v.f4278Q, Base64.encodeToString(new C1746aj().m3809a(c1855dM4467a), 0));
                                        if (jSONObject != null) {
                                            return new JSONObject().put("header", jSONObject);
                                        }
                                    } else if (jSONObject != null) {
                                        return new JSONObject().put("header", jSONObject);
                                    }
                                }
                            } catch (Exception e2) {
                                C1782e.m3972a(context, e2);
                            }
                        }
                    } catch (Exception e3) {
                        C1782e.m3972a(context, e3);
                    }
                } else {
                    c1794q = new C1794q(context);
                    if (c1794q == null) {
                        strImprintProperty = UMEnvelopeBuild.imprintProperty(context, C1799v.f4314e, null);
                        if (!TextUtils.isEmpty(strImprintProperty)) {
                            jSONObject.put(C1799v.f4314e, strImprintProperty);
                            c1855dM4467a = C1826g.m4461a(context).m4467a();
                            if (c1855dM4467a != null) {
                                jSONObject.put(C1799v.f4278Q, Base64.encodeToString(new C1746aj().m3809a(c1855dM4467a), 0));
                                if (jSONObject != null) {
                                    return new JSONObject().put("header", jSONObject);
                                }
                            } else if (jSONObject != null) {
                                return new JSONObject().put("header", jSONObject);
                            }
                        } else {
                            c1855dM4467a = C1826g.m4461a(context).m4467a();
                            if (c1855dM4467a != null) {
                                jSONObject.put(C1799v.f4278Q, Base64.encodeToString(new C1746aj().m3809a(c1855dM4467a), 0));
                                if (jSONObject != null) {
                                    return new JSONObject().put("header", jSONObject);
                                }
                            } else if (jSONObject != null) {
                                return new JSONObject().put("header", jSONObject);
                            }
                        }
                    } else {
                        strImprintProperty = UMEnvelopeBuild.imprintProperty(context, C1799v.f4314e, null);
                        if (!TextUtils.isEmpty(strImprintProperty)) {
                            jSONObject.put(C1799v.f4314e, strImprintProperty);
                            c1855dM4467a = C1826g.m4461a(context).m4467a();
                            if (c1855dM4467a != null) {
                                jSONObject.put(C1799v.f4278Q, Base64.encodeToString(new C1746aj().m3809a(c1855dM4467a), 0));
                                if (jSONObject != null) {
                                    return new JSONObject().put("header", jSONObject);
                                }
                            } else if (jSONObject != null) {
                                return new JSONObject().put("header", jSONObject);
                            }
                        } else {
                            c1855dM4467a = C1826g.m4461a(context).m4467a();
                            if (c1855dM4467a != null) {
                                jSONObject.put(C1799v.f4278Q, Base64.encodeToString(new C1746aj().m3809a(c1855dM4467a), 0));
                                if (jSONObject != null) {
                                    return new JSONObject().put("header", jSONObject);
                                }
                            } else if (jSONObject != null) {
                                return new JSONObject().put("header", jSONObject);
                            }
                        }
                    }
                }
            } catch (Exception e4) {
                C1782e.m3972a(context, e4);
            }
        } catch (Throwable th) {
            C1782e.m3972a(context, th);
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    private JSONObject m4327a(JSONObject jSONObject, JSONObject jSONObject2) {
        String str;
        if (jSONObject != null && jSONObject2 != null && jSONObject.opt("header") != null && (jSONObject.opt("header") instanceof JSONObject)) {
            JSONObject jSONObject3 = (JSONObject) jSONObject.opt("header");
            Iterator<String> itKeys = jSONObject2.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                if (next != null && (next instanceof String) && (str = next) != null && jSONObject2.opt(str) != null) {
                    try {
                        jSONObject3.put(str, jSONObject2.opt(str));
                        if (str.equals(C1708b.f3740i) && (jSONObject2.opt(str) instanceof Integer)) {
                            this.f4496f = ((Integer) jSONObject2.opt(str)).intValue();
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        }
        return jSONObject;
    }

    /* JADX INFO: renamed from: a */
    private Envelope m4325a(Context context, byte[] bArr) {
        String strImprintProperty = UMEnvelopeBuild.imprintProperty(context, "codex", null);
        int iIntValue = -1;
        try {
            if (!TextUtils.isEmpty(strImprintProperty)) {
                iIntValue = Integer.valueOf(strImprintProperty).intValue();
            }
        } catch (NumberFormatException e) {
            C1782e.m3972a(context, e);
        }
        if (iIntValue == 0) {
            return Envelope.genEnvelope(context, UMUtils.getAppkey(context), bArr);
        }
        if (iIntValue == 1) {
            return Envelope.genEncryptEnvelope(context, UMUtils.getAppkey(context), bArr);
        }
        if (f4495g) {
            return Envelope.genEncryptEnvelope(context, UMUtils.getAppkey(context), bArr);
        }
        return Envelope.genEnvelope(context, UMUtils.getAppkey(context), bArr);
    }

    /* JADX INFO: renamed from: a */
    private int m4323a(Context context, Envelope envelope, String str, String str2) {
        if (context == null || envelope == null || TextUtils.isEmpty(str)) {
            return 101;
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = DeviceConfig.getAppVersionName(context);
        }
        return C1732b.m3713a(context, str + "&&" + str2 + "_" + System.currentTimeMillis() + "_envelope.log", envelope.toBinary());
    }

    /* JADX INFO: renamed from: a */
    public static void m4328a(boolean z) {
        f4495g = z;
    }
}
