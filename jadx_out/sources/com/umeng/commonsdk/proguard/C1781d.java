package com.umeng.commonsdk.proguard;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.os.Build;
import android.support.v4.os.EnvironmentCompat;
import android.support.v7.widget.ActivityChooserView;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodInfo;
import com.umeng.commonsdk.framework.C1733c;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.stateless.UMSLEnvelopeBuild;
import com.umeng.commonsdk.statistics.common.C1817e;
import com.umeng.commonsdk.statistics.idtracking.C1824e;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.d */
/* JADX INFO: compiled from: UMInternalManager.java */
/* JADX INFO: loaded from: classes.dex */
public class C1781d {
    /* JADX INFO: renamed from: a */
    public static void m3950a(Context context) throws Throwable {
        C1817e.m4397a("walle", "[internal] begin by stateful--->>>");
        if (context != null) {
            try {
                if (UMEnvelopeBuild.isReadyBuild(context, UMLogDataProtocol.UMBusinessType.U_INTERNAL)) {
                    UMWorkDispatch.sendEvent(context, C1736a.f3999e, C1763b.m3919a(context).m3920a(), null);
                }
            } catch (Throwable th) {
                C1782e.m3972a(context, th);
            }
        }
    }

    /* JADX INFO: renamed from: b */
    public static void m3954b(Context context) {
        Context applicationContext;
        Throwable th;
        if (context == null) {
            return;
        }
        try {
            C1817e.m4397a("walle", "[internal] begin by not stateful--->>>");
            applicationContext = context.getApplicationContext();
            try {
                UMSLEnvelopeBuild uMSLEnvelopeBuild = new UMSLEnvelopeBuild();
                JSONObject jSONObjectBuildSLBaseHeader = uMSLEnvelopeBuild.buildSLBaseHeader(applicationContext);
                if (jSONObjectBuildSLBaseHeader != null && jSONObjectBuildSLBaseHeader.has("header")) {
                    try {
                        JSONObject jSONObject = (JSONObject) jSONObjectBuildSLBaseHeader.opt("header");
                        if (jSONObject != null) {
                            jSONObject.put(C1799v.f4305aq, C1736a.f3998d);
                        }
                    } catch (Exception unused) {
                    }
                }
                C1817e.m4397a("walle", "[internal] header is " + jSONObjectBuildSLBaseHeader.toString());
                JSONObject jSONObjectM3956c = m3956c(applicationContext);
                C1817e.m4397a("walle", "[internal] body is " + jSONObjectM3956c.toString());
                C1817e.m4397a("walle", uMSLEnvelopeBuild.buildSLEnvelope(applicationContext, jSONObjectBuildSLBaseHeader, jSONObjectM3956c, C1736a.f3995a).toString());
            } catch (Throwable th2) {
                th = th2;
                C1782e.m3972a(applicationContext, th);
            }
        } catch (Throwable th3) {
            applicationContext = context;
            th = th3;
        }
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0030 A[Catch: Exception -> 0x0036, TRY_LEAVE, TryCatch #3 {Exception -> 0x0036, blocks: (B:13:0x0026, B:15:0x0030), top: B:37:0x0026, outer: #2 }] */
    /* JADX WARN: Code duplicated, block: B:21:0x0044 A[Catch: Exception -> 0x004a, TRY_LEAVE, TryCatch #1 {Exception -> 0x004a, blocks: (B:19:0x003a, B:21:0x0044), top: B:33:0x003a, outer: #2 }] */
    /* JADX WARN: Code duplicated, block: B:31:0x004e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:33:0x003a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX INFO: renamed from: c */
    public static JSONObject m3956c(Context context) {
        String strM4010l;
        String strM4009k;
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            try {
                try {
                    JSONArray jSONArrayM3968o = m3968o(applicationContext);
                    if (jSONArrayM3968o != null && jSONArrayM3968o.length() > 0) {
                        jSONObject2.put("run_server", jSONArrayM3968o);
                        strM4009k = C1787j.m4009k(applicationContext);
                        if (!TextUtils.isEmpty(strM4009k)) {
                            jSONObject2.put("imsi", strM4009k);
                            strM4010l = C1787j.m4010l(applicationContext);
                            if (!TextUtils.isEmpty(strM4010l)) {
                                jSONObject2.put("meid", strM4010l);
                                jSONObject.put(C1733c.f3961d, jSONObject2);
                            } else {
                                jSONObject.put(C1733c.f3961d, jSONObject2);
                            }
                        } else {
                            strM4010l = C1787j.m4010l(applicationContext);
                            if (!TextUtils.isEmpty(strM4010l)) {
                                jSONObject2.put("meid", strM4010l);
                                jSONObject.put(C1733c.f3961d, jSONObject2);
                            } else {
                                jSONObject.put(C1733c.f3961d, jSONObject2);
                            }
                        }
                    } else {
                        try {
                            strM4009k = C1787j.m4009k(applicationContext);
                            if (!TextUtils.isEmpty(strM4009k)) {
                                jSONObject2.put("imsi", strM4009k);
                                try {
                                    strM4010l = C1787j.m4010l(applicationContext);
                                    if (!TextUtils.isEmpty(strM4010l)) {
                                        jSONObject2.put("meid", strM4010l);
                                        try {
                                            jSONObject.put(C1733c.f3961d, jSONObject2);
                                        } catch (JSONException e) {
                                            C1782e.m3972a(applicationContext, e);
                                        }
                                    } else {
                                        jSONObject.put(C1733c.f3961d, jSONObject2);
                                    }
                                } catch (Exception e2) {
                                    C1782e.m3972a(applicationContext, e2);
                                }
                            } else {
                                strM4010l = C1787j.m4010l(applicationContext);
                                if (!TextUtils.isEmpty(strM4010l)) {
                                    jSONObject2.put("meid", strM4010l);
                                    jSONObject.put(C1733c.f3961d, jSONObject2);
                                } else {
                                    jSONObject.put(C1733c.f3961d, jSONObject2);
                                }
                            }
                        } catch (Exception e3) {
                            C1782e.m3972a(applicationContext, e3);
                        }
                    }
                } catch (Exception e4) {
                    C1782e.m3972a(applicationContext, e4);
                }
            } catch (Exception unused) {
            }
        }
        return jSONObject;
    }

    /* JADX INFO: renamed from: d */
    public static JSONObject m3957d(Context context) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            try {
                JSONArray jSONArrayM3968o = m3968o(applicationContext);
                if (jSONArrayM3968o != null && jSONArrayM3968o.length() > 0) {
                    jSONObject2.put("rs", jSONArrayM3968o);
                }
            } catch (Exception e) {
                C1782e.m3972a(applicationContext, e);
            }
            try {
                JSONArray jSONArrayM3969p = m3969p(applicationContext);
                if (jSONArrayM3969p != null && jSONArrayM3969p.length() > 0) {
                    jSONObject2.put("bstn", jSONArrayM3969p);
                }
            } catch (Exception e2) {
                C1782e.m3972a(applicationContext, e2);
            }
            try {
                JSONArray jSONArrayM3970q = m3970q(applicationContext);
                if (jSONArrayM3970q != null && jSONArrayM3970q.length() > 0) {
                    jSONObject2.put("by", jSONArrayM3970q);
                }
            } catch (Exception e3) {
                C1782e.m3972a(applicationContext, e3);
            }
            try {
                m3951a(applicationContext, jSONObject2);
            } catch (Exception e4) {
                C1782e.m3972a(applicationContext, e4);
            }
            try {
                m3955b(applicationContext, jSONObject2);
            } catch (Exception e5) {
                C1782e.m3972a(applicationContext, e5);
            }
            try {
                JSONObject jSONObjectM3949a = m3949a();
                if (jSONObjectM3949a != null && jSONObjectM3949a.length() > 0) {
                    jSONObject2.put("sd", jSONObjectM3949a);
                }
            } catch (Exception e6) {
                C1782e.m3972a(applicationContext, e6);
            }
            try {
                JSONObject jSONObjectM3953b = m3953b();
                if (jSONObjectM3953b != null && jSONObjectM3953b.length() > 0) {
                    jSONObject2.put("build", jSONObjectM3953b);
                }
            } catch (Exception e7) {
                C1782e.m3972a(applicationContext, e7);
            }
            try {
                JSONObject jSONObject3 = new JSONObject();
                JSONArray jSONArrayM3959f = m3959f(applicationContext);
                if (jSONArrayM3959f != null && jSONArrayM3959f.length() > 0) {
                    try {
                        jSONObject3.put("a_sr", jSONArrayM3959f);
                    } catch (JSONException unused) {
                    }
                }
                JSONArray jSONArrayM4074c = C1795r.m4074c(applicationContext);
                if (jSONArrayM4074c != null && jSONArrayM4074c.length() > 0) {
                    try {
                        jSONObject3.put("stat", jSONArrayM4074c);
                    } catch (JSONException unused2) {
                    }
                }
                jSONObject2.put("sr", jSONObject3);
            } catch (Exception e8) {
                C1782e.m3972a(applicationContext, e8);
            }
            try {
                JSONObject jSONObjectM3960g = m3960g(applicationContext);
                if (jSONObjectM3960g != null && jSONObjectM3960g.length() > 0) {
                    jSONObject2.put("scr", jSONObjectM3960g);
                }
            } catch (Exception e9) {
                C1782e.m3972a(applicationContext, e9);
            }
            try {
                JSONObject jSONObjectM3961h = m3961h(applicationContext);
                if (jSONObjectM3961h != null && jSONObjectM3961h.length() > 0) {
                    jSONObject2.put("sinfo", jSONObjectM3961h);
                }
            } catch (Exception e10) {
                C1782e.m3972a(applicationContext, e10);
            }
            try {
                JSONObject jSONObject4 = new JSONObject();
                JSONArray jSONArrayM4000e = C1787j.m4000e(applicationContext);
                if (jSONArrayM4000e != null && jSONArrayM4000e.length() > 0) {
                    try {
                        jSONObject4.put("wl", jSONArrayM4000e);
                    } catch (JSONException unused3) {
                    }
                }
                JSONArray jSONArrayM3962i = m3962i(applicationContext);
                if (jSONArrayM3962i != null && jSONArrayM3962i.length() > 0) {
                    try {
                        jSONObject4.put("a_wls", jSONArrayM3962i);
                    } catch (JSONException unused4) {
                    }
                }
                jSONObject2.put("winfo", jSONObject4);
            } catch (Exception e11) {
                C1782e.m3972a(applicationContext, e11);
            }
            try {
                JSONArray jSONArrayM3963j = m3963j(applicationContext);
                if (jSONArrayM3963j != null && jSONArrayM3963j.length() > 0) {
                    jSONObject2.put("input", jSONArrayM3963j);
                }
            } catch (Exception e12) {
                C1782e.m3972a(applicationContext, e12);
            }
            try {
                JSONObject jSONObjectM4013o = C1787j.m4013o(applicationContext);
                if (jSONObjectM4013o != null && jSONObjectM4013o.length() > 0) {
                    jSONObject2.put("bt", jSONObjectM4013o);
                }
            } catch (Exception e13) {
                C1782e.m3972a(applicationContext, e13);
            }
            try {
                JSONArray jSONArrayM3964k = m3964k(applicationContext);
                if (jSONArrayM3964k != null && jSONArrayM3964k.length() > 0) {
                    jSONObject2.put("cam", jSONArrayM3964k);
                }
            } catch (Exception e14) {
                C1782e.m3972a(applicationContext, e14);
            }
            try {
                JSONArray jSONArrayM3965l = m3965l(applicationContext);
                if (jSONArrayM3965l != null && jSONArrayM3965l.length() > 0) {
                    jSONObject2.put("appls", jSONArrayM3965l);
                }
            } catch (Exception e15) {
                C1782e.m3972a(applicationContext, e15);
            }
            try {
                JSONObject jSONObjectM3966m = m3966m(applicationContext);
                if (jSONObjectM3966m != null && jSONObjectM3966m.length() > 0) {
                    jSONObject2.put("mem", jSONObjectM3966m);
                }
            } catch (Exception e16) {
                C1782e.m3972a(applicationContext, e16);
            }
            try {
                JSONObject jSONObjectM3967n = m3967n(applicationContext);
                if (jSONObjectM3967n != null && jSONObjectM3967n.length() > 0) {
                    jSONObject2.put("lbs", jSONObjectM3967n);
                }
            } catch (Exception e17) {
                C1782e.m3972a(applicationContext, e17);
            }
            try {
                jSONObject.put(C1799v.f4295ag, jSONObject2);
            } catch (JSONException e18) {
                C1782e.m3972a(applicationContext, e18);
            }
        }
        return jSONObject;
    }

    /* JADX INFO: renamed from: n */
    private static JSONObject m3967n(Context context) {
        if (context != null) {
            return C1785h.m3983b(context.getApplicationContext());
        }
        return null;
    }

    /* JADX INFO: renamed from: o */
    private static JSONArray m3968o(Context context) {
        List<ActivityManager.RunningServiceInfo> runningServices;
        JSONArray jSONArray = null;
        jSONArray = null;
        if (context == null) {
            return null;
        }
        try {
            ActivityManager activityManager = (ActivityManager) context.getApplicationContext().getSystemService("activity");
            if (activityManager == null || (runningServices = activityManager.getRunningServices(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED)) == null || runningServices.isEmpty()) {
                return null;
            }
            int i = 0;
            while (i < runningServices.size()) {
                if (runningServices.get(i) != null && runningServices.get(i).service != null && runningServices.get(i).service.getClassName() != null && runningServices.get(i).service.getPackageName() != null) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("sn", runningServices.get(i).service.getClassName().toString());
                        jSONObject.put("pn", runningServices.get(i).service.getPackageName().toString());
                        jSONArray = jSONArray;
                        if (jSONArray == null) {
                            jSONArray = new JSONArray();
                        }
                        jSONArray.put(jSONObject);
                    } catch (JSONException unused) {
                    }
                }
                i++;
                jSONArray = jSONArray;
            }
            if (jSONArray == null) {
                return jSONArray;
            }
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("ts", System.currentTimeMillis());
                jSONObject2.put("ls", jSONArray);
            } catch (JSONException unused2) {
            }
            JSONObject jSONObject3 = new JSONObject();
            try {
                jSONObject3.put("sers", jSONObject2);
            } catch (JSONException unused3) {
            }
            JSONArray jSONArray2 = new JSONArray();
            try {
                jSONArray2.put(jSONObject3);
                return jSONArray2;
            } catch (Throwable th) {
                th = th;
                jSONArray = jSONArray2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        C1782e.m3972a(context, th);
        return jSONArray;
    }

    /* JADX INFO: renamed from: p */
    private static JSONArray m3969p(Context context) {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObjectM4092d = C1796s.m4092d(context);
        if (jSONObjectM4092d != null) {
            try {
                String strM4093e = C1796s.m4093e(context);
                if (!TextUtils.isEmpty(strM4093e)) {
                    jSONObjectM4092d.put("sig", strM4093e);
                }
                jSONArray.put(jSONObjectM4092d);
            } catch (Exception unused) {
            }
        }
        return jSONArray;
    }

    /* JADX INFO: renamed from: q */
    private static JSONArray m3970q(Context context) {
        JSONArray jSONArray = new JSONArray();
        String strM4094f = C1796s.m4094f(context);
        if (!TextUtils.isEmpty(strM4094f)) {
            try {
                JSONObject jSONObject = new JSONObject(strM4094f);
                if (jSONObject != null) {
                    jSONArray.put(jSONObject);
                }
            } catch (Exception unused) {
            }
        }
        return jSONArray;
    }

    /* JADX INFO: renamed from: r */
    private static JSONArray m3971r(Context context) {
        JSONArray jSONArray = new JSONArray();
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            String strM4087a = C1796s.m4087a(applicationContext);
            JSONObject jSONObject = null;
            if (!TextUtils.isEmpty(strM4087a)) {
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put(C1799v.f4281T, strM4087a);
                    } catch (Exception unused) {
                    }
                    jSONObject = jSONObject2;
                } catch (Exception unused2) {
                }
            }
            String strM4089b = C1796s.m4089b(applicationContext);
            if (!TextUtils.isEmpty(strM4089b)) {
                if (jSONObject == null) {
                    try {
                        jSONObject = new JSONObject();
                    } catch (Exception unused3) {
                    }
                }
                jSONObject.put(C1799v.f4282U, strM4089b);
            }
            String strM4091c = C1796s.m4091c(applicationContext);
            if (!TextUtils.isEmpty(strM4091c)) {
                if (jSONObject == null) {
                    try {
                        jSONObject = new JSONObject();
                    } catch (Exception unused4) {
                    }
                }
                jSONObject.put(C1799v.f4283V, strM4091c);
            }
            JSONObject jSONObjectM4092d = C1796s.m4092d(applicationContext);
            if (jSONObjectM4092d != null) {
                try {
                    String strM4093e = C1796s.m4093e(applicationContext);
                    if (!TextUtils.isEmpty(strM4093e)) {
                        jSONObjectM4092d.put("signalscale", strM4093e);
                    }
                    if (jSONObject == null) {
                        jSONObject = new JSONObject();
                    }
                    jSONObject.put(C1799v.f4285X, jSONObjectM4092d);
                } catch (Exception unused5) {
                }
            }
            String strM4094f = C1796s.m4094f(applicationContext);
            if (!TextUtils.isEmpty(strM4094f)) {
                if (jSONObject == null) {
                    try {
                        jSONObject = new JSONObject();
                    } catch (Exception unused6) {
                    }
                }
                jSONObject.put(C1799v.f4280S, new JSONObject(strM4094f));
            }
            if (jSONObject != null) {
                jSONArray.put(jSONObject);
            }
        }
        return jSONArray;
    }

    /* JADX INFO: renamed from: a */
    private static void m3951a(Context context, JSONObject jSONObject) {
        PackageManager packageManager;
        if (context == null || (packageManager = context.getApplicationContext().getPackageManager()) == null) {
            return;
        }
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        m3952a(jSONObject, "gp", packageManager.hasSystemFeature("android.hardware.location.gps"));
        m3952a(jSONObject, "to", packageManager.hasSystemFeature("android.hardware.touchscreen"));
        m3952a(jSONObject, "mo", packageManager.hasSystemFeature("android.hardware.telephony"));
        m3952a(jSONObject, "ca", packageManager.hasSystemFeature("android.hardware.camera"));
        m3952a(jSONObject, "fl", packageManager.hasSystemFeature("android.hardware.camera.flash"));
    }

    /* JADX INFO: renamed from: a */
    private static void m3952a(JSONObject jSONObject, String str, boolean z) {
        if (jSONObject == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            if (z) {
                jSONObject.put(str, 1);
            } else {
                jSONObject.put(str, 0);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: renamed from: b */
    private static void m3955b(Context context, JSONObject jSONObject) {
        if (context != null) {
            String strM4097a = C1797t.m4097a(context);
            if (TextUtils.isEmpty(strM4097a)) {
                return;
            }
            try {
                JSONObject jSONObject2 = new JSONObject(strM4097a);
                if (jSONObject2 != null) {
                    if (jSONObject == null) {
                        jSONObject = new JSONObject();
                    }
                    if (jSONObject2.has(C1797t.f4253d)) {
                        jSONObject.put(C1797t.f4253d, jSONObject2.opt(C1797t.f4253d));
                    }
                    if (jSONObject2.has(C1797t.f4252c)) {
                        jSONObject.put(C1797t.f4252c, jSONObject2.opt(C1797t.f4252c));
                    }
                    if (jSONObject2.has(C1797t.f4251b)) {
                        jSONObject.put(C1797t.f4251b, jSONObject2.opt(C1797t.f4251b));
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: renamed from: e */
    public static String m3958e(Context context) {
        try {
            C1824e c1824eM4444a = C1824e.m4444a(context);
            if (c1824eM4444a == null) {
                return null;
            }
            c1824eM4444a.m4449a();
            String strEncodeToString = Base64.encodeToString(new C1746aj().m3809a(c1824eM4444a.m4451b()), 0);
            if (TextUtils.isEmpty(strEncodeToString)) {
                return null;
            }
            return strEncodeToString;
        } catch (Exception e) {
            C1782e.m3972a(context, e);
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    public static JSONObject m3949a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("f", C1787j.m3992c());
            jSONObject.put("t", C1787j.m3996d());
            jSONObject.put("ts", System.currentTimeMillis());
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    /* JADX INFO: renamed from: b */
    public static JSONObject m3953b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("a_bl", Build.BOOTLOADER);
            if (Build.VERSION.SDK_INT >= 14) {
                jSONObject.put("a_rv", Build.getRadioVersion());
            }
            jSONObject.put("a_fp", Build.FINGERPRINT);
            jSONObject.put("a_hw", Build.HARDWARE);
            jSONObject.put("a_host", Build.HOST);
            if (Build.VERSION.SDK_INT >= 21) {
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < Build.SUPPORTED_32_BIT_ABIS.length; i++) {
                    jSONArray.put(Build.SUPPORTED_32_BIT_ABIS[i]);
                }
                if (jSONArray != null && jSONArray.length() > 0) {
                    jSONObject.put("a_s32", jSONArray);
                }
            }
            if (Build.VERSION.SDK_INT >= 21) {
                JSONArray jSONArray2 = new JSONArray();
                for (int i2 = 0; i2 < Build.SUPPORTED_64_BIT_ABIS.length; i2++) {
                    jSONArray2.put(Build.SUPPORTED_64_BIT_ABIS[i2]);
                }
                if (jSONArray2 != null && jSONArray2.length() > 0) {
                    jSONObject.put("a_s64", jSONArray2);
                }
            }
            if (Build.VERSION.SDK_INT >= 21) {
                JSONArray jSONArray3 = new JSONArray();
                for (int i3 = 0; i3 < Build.SUPPORTED_ABIS.length; i3++) {
                    jSONArray3.put(Build.SUPPORTED_ABIS[i3]);
                }
                if (jSONArray3 != null && jSONArray3.length() > 0) {
                    jSONObject.put("a_sa", jSONArray3);
                }
            }
            jSONObject.put("a_ta", Build.TAGS);
            jSONObject.put("a_uk", EnvironmentCompat.MEDIA_UNKNOWN);
            jSONObject.put("a_user", Build.USER);
            jSONObject.put("a_cpu1", Build.CPU_ABI);
            jSONObject.put("a_cpu2", Build.CPU_ABI2);
            jSONObject.put("a_ra", Build.RADIO);
            if (Build.VERSION.SDK_INT >= 23) {
                jSONObject.put("a_bos", Build.VERSION.BASE_OS);
                jSONObject.put("a_pre", Build.VERSION.PREVIEW_SDK_INT);
                jSONObject.put("a_sp", Build.VERSION.SECURITY_PATCH);
            }
            jSONObject.put("a_cn", Build.VERSION.CODENAME);
            jSONObject.put("a_intl", Build.VERSION.INCREMENTAL);
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    /* JADX INFO: renamed from: f */
    public static JSONArray m3959f(Context context) {
        if (context != null) {
            return C1796s.m4095g(context.getApplicationContext());
        }
        return null;
    }

    /* JADX INFO: renamed from: g */
    public static JSONObject m3960g(Context context) {
        DisplayMetrics displayMetrics;
        JSONObject jSONObject = new JSONObject();
        if (context != null) {
            try {
                jSONObject.put("a_st_h", C1787j.m4005h(context));
                jSONObject.put("a_nav_h", C1787j.m4007i(context));
                if (context.getResources() != null && (displayMetrics = context.getResources().getDisplayMetrics()) != null) {
                    jSONObject.put("a_den", displayMetrics.density);
                    jSONObject.put("a_dpi", displayMetrics.densityDpi);
                }
            } catch (Exception e) {
                C1782e.m3972a(context, e);
            }
        }
        return jSONObject;
    }

    /* JADX INFO: renamed from: h */
    public static JSONObject m3961h(Context context) {
        JSONObject jSONObject = new JSONObject();
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            String packageName = applicationContext.getPackageName();
            try {
                jSONObject.put("a_fit", C1787j.m3986a(applicationContext, packageName));
                jSONObject.put("a_alut", C1787j.m3989b(applicationContext, packageName));
                jSONObject.put("a_c", C1787j.m3994c(applicationContext, packageName));
                jSONObject.put("a_uid", C1787j.m3995d(applicationContext, packageName));
                if (C1787j.m3988a()) {
                    jSONObject.put("a_root", 1);
                } else {
                    jSONObject.put("a_root", 0);
                }
                jSONObject.put("a_ua", C1787j.m4017s(applicationContext));
                jSONObject.put("tf", C1787j.m3990b());
                jSONObject.put("s_fs", C1787j.m3985a(applicationContext));
                jSONObject.put("a_meid", C1787j.m4010l(applicationContext));
                jSONObject.put("a_imsi", C1787j.m4009k(applicationContext));
                jSONObject.put("st", C1787j.m4001f());
                String strM4089b = C1796s.m4089b(applicationContext);
                if (!TextUtils.isEmpty(strM4089b)) {
                    try {
                        jSONObject.put("a_iccid", strM4089b);
                    } catch (Exception unused) {
                    }
                }
                String strM4091c = C1796s.m4091c(applicationContext);
                if (!TextUtils.isEmpty(strM4091c)) {
                    try {
                        jSONObject.put("a_simei", strM4091c);
                    } catch (Exception unused2) {
                    }
                }
                jSONObject.put("hn", C1787j.m4004g());
                jSONObject.put("ts", System.currentTimeMillis());
            } catch (Exception e) {
                C1782e.m3972a(applicationContext, e);
            }
        }
        return jSONObject;
    }

    /* JADX INFO: renamed from: i */
    public static JSONArray m3962i(Context context) {
        Context applicationContext;
        List<ScanResult> listM3991b;
        JSONArray jSONArray = new JSONArray();
        if (context != null && (listM3991b = C1787j.m3991b((applicationContext = context.getApplicationContext()))) != null && listM3991b.size() > 0) {
            for (ScanResult scanResult : listM3991b) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("a_bssid", scanResult.BSSID);
                    jSONObject.put("a_ssid", scanResult.BSSID);
                    jSONObject.put("a_cap", scanResult.capabilities);
                    jSONObject.put("a_fcy", scanResult.frequency);
                    jSONObject.put("ts", System.currentTimeMillis());
                    if (Build.VERSION.SDK_INT >= 23) {
                        jSONObject.put("a_c0", scanResult.centerFreq0);
                        jSONObject.put("a_c1", scanResult.centerFreq1);
                        jSONObject.put("a_cw", scanResult.channelWidth);
                        if (scanResult.is80211mcResponder()) {
                            jSONObject.put("a_is80211", 1);
                        } else {
                            jSONObject.put("a_is80211", 0);
                        }
                        if (scanResult.isPasspointNetwork()) {
                            jSONObject.put("a_isppn", 1);
                        } else {
                            jSONObject.put("a_isppn", 0);
                        }
                        jSONObject.put("a_ofn", scanResult.operatorFriendlyName);
                        jSONObject.put("a_vn", scanResult.venueName);
                    }
                    jSONObject.put("a_dc", scanResult.describeContents());
                    if (jSONObject != null) {
                        jSONArray.put(jSONObject);
                    }
                } catch (Exception e) {
                    C1782e.m3972a(applicationContext, e);
                }
            }
        }
        return jSONArray;
    }

    /* JADX INFO: renamed from: j */
    public static JSONArray m3963j(Context context) {
        Context applicationContext;
        List<InputMethodInfo> listM4011m;
        JSONArray jSONArray = new JSONArray();
        if (context != null && (listM4011m = C1787j.m4011m((applicationContext = context.getApplicationContext()))) != null) {
            for (InputMethodInfo inputMethodInfo : listM4011m) {
                try {
                    CharSequence charSequenceLoadLabel = inputMethodInfo.loadLabel(applicationContext.getPackageManager());
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("a_la", charSequenceLoadLabel);
                    jSONObject.put("a_pn", inputMethodInfo.getPackageName());
                    jSONObject.put("ts", System.currentTimeMillis());
                    if (jSONObject != null) {
                        jSONArray.put(jSONObject);
                    }
                } catch (Exception e) {
                    C1782e.m3972a(applicationContext, e);
                }
            }
        }
        return jSONArray;
    }

    /* JADX INFO: renamed from: k */
    public static JSONArray m3964k(Context context) {
        Context applicationContext;
        List<C1795r.a> listM4078e;
        JSONArray jSONArray = new JSONArray();
        if (context != null && (listM4078e = C1795r.m4078e((applicationContext = context.getApplicationContext()))) != null && !listM4078e.isEmpty()) {
            for (C1795r.a aVar : listM4078e) {
                if (aVar != null) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("a_w", aVar.f4244a);
                        jSONObject.put("a_h", aVar.f4245b);
                        jSONObject.put("ts", System.currentTimeMillis());
                        if (jSONObject != null) {
                            jSONArray.put(jSONObject);
                        }
                    } catch (Exception e) {
                        C1782e.m3972a(applicationContext, e);
                    }
                }
            }
        }
        return jSONArray;
    }

    /* JADX INFO: renamed from: l */
    public static JSONArray m3965l(Context context) {
        Context applicationContext;
        List<C1787j.a> listM4014p;
        JSONArray jSONArray = new JSONArray();
        if (context != null && (listM4014p = C1787j.m4014p((applicationContext = context.getApplicationContext()))) != null && !listM4014p.isEmpty()) {
            for (C1787j.a aVar : listM4014p) {
                if (aVar != null) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("a_pn", aVar.f4165a);
                        jSONObject.put("a_la", aVar.f4166b);
                        jSONObject.put("ts", System.currentTimeMillis());
                        if (jSONObject != null) {
                            jSONArray.put(jSONObject);
                        }
                    } catch (Exception e) {
                        C1782e.m3972a(applicationContext, e);
                    }
                }
            }
        }
        return jSONArray;
    }

    /* JADX INFO: renamed from: m */
    public static JSONObject m3966m(Context context) {
        Context applicationContext;
        ActivityManager.MemoryInfo memoryInfoM4015q;
        JSONObject jSONObject = new JSONObject();
        if (context != null && (memoryInfoM4015q = C1787j.m4015q((applicationContext = context.getApplicationContext()))) != null) {
            try {
                if (Build.VERSION.SDK_INT >= 16) {
                    jSONObject.put("t", memoryInfoM4015q.totalMem);
                }
                jSONObject.put("f", memoryInfoM4015q.availMem);
                jSONObject.put("ts", System.currentTimeMillis());
            } catch (Exception e) {
                C1782e.m3972a(applicationContext, e);
            }
        }
        return jSONObject;
    }
}
