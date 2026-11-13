package com.umeng.commonsdk;

import android.content.Context;
import android.text.TextUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.umeng.commonsdk.amap.C1729a;
import com.umeng.commonsdk.amap.C1730b;
import com.umeng.commonsdk.amap.UMAmapConfig;
import com.umeng.commonsdk.framework.C1732b;
import com.umeng.commonsdk.framework.C1733c;
import com.umeng.commonsdk.proguard.C1781d;
import com.umeng.commonsdk.proguard.C1782e;
import com.umeng.commonsdk.proguard.C1785h;
import com.umeng.commonsdk.proguard.C1787j;
import com.umeng.commonsdk.proguard.C1788k;
import com.umeng.commonsdk.proguard.C1789l;
import com.umeng.commonsdk.proguard.C1795r;
import com.umeng.commonsdk.proguard.C1797t;
import com.umeng.commonsdk.stateless.UMSLEnvelopeBuild;
import com.umeng.commonsdk.statistics.common.C1817e;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class UMConfigureImpl {

    /* JADX INFO: renamed from: a */
    private static C1730b f3934a;

    /* JADX INFO: renamed from: b */
    private static boolean f3935b;

    public static void init(Context context) {
        if (context == null) {
            return;
        }
        Context applicationContext = context.getApplicationContext();
        m3702a(applicationContext);
        m3703b(applicationContext);
        m3704c(applicationContext);
    }

    /* JADX INFO: renamed from: a */
    private static synchronized void m3702a(Context context) {
        Context applicationContext;
        Throwable th;
        if (context != null) {
            try {
                applicationContext = context.getApplicationContext();
                try {
                    C1729a c1729a = new C1729a(applicationContext);
                    if (c1729a != null && c1729a.m3705a() != null) {
                        String strM3715a = C1732b.m3715a(applicationContext);
                        String packageName = applicationContext.getPackageName();
                        if (!TextUtils.isEmpty(strM3715a) && !TextUtils.isEmpty(packageName) && strM3715a.equals(packageName)) {
                            if (f3934a == null) {
                                f3934a = new C1730b(applicationContext, 1, UMAmapConfig.AMAP_CACHE_WRITE_TIME);
                            }
                            if (!f3934a.isAlive()) {
                                C1817e.m4397a("AmapLBS", "[lbs-build] begin write LBS");
                                f3934a.start();
                            } else {
                                C1817e.m4397a("AmapLBS", "[lbs-build] not write start");
                            }
                        }
                    } else {
                        C1817e.m4397a("AmapLBS", "[lbs-send] not get AMAP !!!");
                    }
                } catch (Throwable th2) {
                    th = th2;
                    C1817e.m4409c("AmapLBS", "e is " + th.getMessage());
                    C1782e.m3972a(applicationContext, th);
                }
            } catch (Throwable th3) {
                applicationContext = context;
                th = th3;
            }
        }
    }

    /* JADX INFO: renamed from: b */
    private static void m3703b(final Context context) {
        if (context != null) {
            try {
                String strM3715a = C1732b.m3715a(context);
                String packageName = context.getPackageName();
                if (TextUtils.isEmpty(strM3715a) || TextUtils.isEmpty(packageName) || !strM3715a.equals(packageName)) {
                    return;
                }
                new Thread(new Runnable() { // from class: com.umeng.commonsdk.UMConfigureImpl.1
                    @Override // java.lang.Runnable
                    public void run() throws Throwable {
                        try {
                            JSONObject jSONObjectM3983b = C1785h.m3983b(context);
                            if (jSONObjectM3983b == null || jSONObjectM3983b.length() <= 0) {
                                return;
                            }
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("lbs", jSONObjectM3983b);
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("tp", jSONObject);
                            UMSLEnvelopeBuild uMSLEnvelopeBuild = new UMSLEnvelopeBuild();
                            uMSLEnvelopeBuild.buildSLEnvelope(context, uMSLEnvelopeBuild.buildSLBaseHeader(context), jSONObject2, UMAmapConfig.AMAP_CACHE_PATH);
                        } catch (Exception e) {
                            C1782e.m3972a(context, e);
                        }
                    }
                }).start();
            } catch (Throwable th) {
                C1782e.m3972a(context, th);
            }
        }
    }

    /* JADX INFO: renamed from: c */
    private static synchronized void m3704c(final Context context) {
        if (context != null) {
            try {
                if (!f3935b) {
                    new Thread(new Runnable() { // from class: com.umeng.commonsdk.UMConfigureImpl.2
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                String strM3715a = C1732b.m3715a(context);
                                String packageName = context.getPackageName();
                                if (TextUtils.isEmpty(strM3715a) || TextUtils.isEmpty(packageName) || !strM3715a.equals(packageName)) {
                                    return;
                                }
                                try {
                                    C1785h.m3982a(context);
                                } catch (Throwable th) {
                                    C1817e.m4409c(C1733c.f3961d, "e is " + th);
                                }
                                try {
                                    if (!C1789l.m4029a(context).m4031a()) {
                                        C1789l.m4029a(context).m4032b();
                                    }
                                } catch (Throwable th2) {
                                    C1817e.m4409c(C1733c.f3961d, "e is " + th2);
                                    ThrowableExtension.printStackTrace(th2);
                                }
                                try {
                                    C1797t.m4101b(context);
                                } catch (Exception e) {
                                    C1817e.m4409c(C1733c.f3961d, "e is " + e);
                                }
                                try {
                                    C1787j.m4012n(context);
                                } catch (Exception e2) {
                                    C1817e.m4409c(C1733c.f3961d, "e is " + e2);
                                }
                                try {
                                    C1787j.m3997d(context);
                                } catch (Exception e3) {
                                    C1817e.m4409c(C1733c.f3961d, "e is " + e3);
                                }
                                try {
                                    C1795r.m4072b(context);
                                } catch (Exception e4) {
                                    C1817e.m4409c(C1733c.f3961d, "e is " + e4);
                                }
                                try {
                                    C1781d.m3950a(context);
                                } catch (Exception e5) {
                                    C1817e.m4409c(C1733c.f3961d, "e is " + e5);
                                }
                                try {
                                    C1781d.m3954b(context);
                                } catch (Throwable unused) {
                                }
                            } catch (Throwable th3) {
                                C1782e.m3972a(context, th3);
                            }
                        }
                    }).start();
                    String strM3715a = C1732b.m3715a(context);
                    String packageName = context.getPackageName();
                    if (!TextUtils.isEmpty(strM3715a) && !TextUtils.isEmpty(packageName) && strM3715a.equals(packageName)) {
                        try {
                            C1787j.m4016r(context);
                        } catch (Exception e) {
                            C1817e.m4409c(C1733c.f3961d, "e is " + e);
                            C1782e.m3972a(context, e);
                        }
                    }
                    try {
                        if (!C1788k.m4021a(context).m4025a()) {
                            C1788k.m4021a(context).m4026b();
                        }
                    } catch (Throwable th) {
                        C1817e.m4409c(C1733c.f3961d, "e is " + th);
                        C1782e.m3972a(context, th);
                    }
                    f3935b = true;
                }
            } catch (Throwable th2) {
                C1817e.m4409c(C1733c.f3961d, "e is " + th2.getMessage());
                C1782e.m3972a(context, th2);
            }
        }
    }
}
