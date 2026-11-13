package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.cyjh.share.util.AppUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.mvp.managers.AccountManager;
import com.p024ds.daisi.net.xutils.HttpTools;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.C1703b;
import com.umeng.analytics.C1704c;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.common.ReportPolicy;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.statistics.internal.StatTracer;
import com.umeng.commonsdk.statistics.noise.ABTest;
import com.umeng.commonsdk.statistics.noise.Defcon;
import com.umeng.commonsdk.statistics.noise.ImLatent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.umeng.analytics.pro.i */
/* JADX INFO: compiled from: CoreProtocolImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class C1715i {

    /* JADX INFO: renamed from: A */
    private static boolean f3847A = false;

    /* JADX INFO: renamed from: B */
    private static final String f3848B = "n_sess_dp";

    /* JADX INFO: renamed from: C */
    private static final String f3849C = "n_sess_dp_type";

    /* JADX INFO: renamed from: a */
    private static Context f3850a = null;

    /* JADX INFO: renamed from: q */
    private static final String f3851q = "first_activate_time";

    /* JADX INFO: renamed from: r */
    private static final String f3852r = "ana_is_f";

    /* JADX INFO: renamed from: s */
    private static final String f3853s = "thtstart";

    /* JADX INFO: renamed from: t */
    private static final String f3854t = "dstk_last_time";

    /* JADX INFO: renamed from: u */
    private static final String f3855u = "dstk_cnt";

    /* JADX INFO: renamed from: v */
    private static final String f3856v = "gkvc";

    /* JADX INFO: renamed from: w */
    private static final String f3857w = "ekvc";

    /* JADX INFO: renamed from: y */
    private static final String f3858y = "-1";

    /* JADX INFO: renamed from: z */
    private static boolean f3859z;

    /* JADX INFO: renamed from: b */
    private ABTest f3860b;

    /* JADX INFO: renamed from: c */
    private ImLatent f3861c;

    /* JADX INFO: renamed from: d */
    private c f3862d;

    /* JADX INFO: renamed from: e */
    private SharedPreferences f3863e;

    /* JADX INFO: renamed from: f */
    private String f3864f;

    /* JADX INFO: renamed from: g */
    private String f3865g;

    /* JADX INFO: renamed from: h */
    private String f3866h;

    /* JADX INFO: renamed from: i */
    private String f3867i;

    /* JADX INFO: renamed from: j */
    private long f3868j;

    /* JADX INFO: renamed from: k */
    private int f3869k;

    /* JADX INFO: renamed from: l */
    private JSONArray f3870l;

    /* JADX INFO: renamed from: m */
    private final int f3871m;

    /* JADX INFO: renamed from: n */
    private int f3872n;

    /* JADX INFO: renamed from: o */
    private int f3873o;

    /* JADX INFO: renamed from: p */
    private long f3874p;

    /* JADX INFO: renamed from: x */
    private final long f3875x;

    /* JADX INFO: renamed from: com.umeng.analytics.pro.i$a */
    /* JADX INFO: compiled from: CoreProtocolImpl.java */
    public static class a {

        /* JADX INFO: renamed from: a */
        public static final int f3876a = 4097;

        /* JADX INFO: renamed from: b */
        public static final int f3877b = 4098;

        /* JADX INFO: renamed from: c */
        public static final int f3878c = 4099;

        /* JADX INFO: renamed from: d */
        public static final int f3879d = 4100;

        /* JADX INFO: renamed from: e */
        public static final int f3880e = 4101;

        /* JADX INFO: renamed from: f */
        public static final int f3881f = 4102;

        /* JADX INFO: renamed from: g */
        public static final int f3882g = 4103;

        /* JADX INFO: renamed from: h */
        public static final int f3883h = 4104;

        /* JADX INFO: renamed from: i */
        public static final int f3884i = 4105;

        /* JADX INFO: renamed from: j */
        public static final int f3885j = 4106;

        /* JADX INFO: renamed from: k */
        public static final int f3886k = 8193;

        /* JADX INFO: renamed from: l */
        public static final int f3887l = 8194;

        /* JADX INFO: renamed from: m */
        public static final int f3888m = 8195;

        /* JADX INFO: renamed from: n */
        public static final int f3889n = 8196;

        /* JADX INFO: renamed from: o */
        public static final int f3890o = 8197;
    }

    private C1715i() {
        this.f3860b = null;
        this.f3861c = null;
        this.f3862d = null;
        this.f3863e = null;
        this.f3864f = null;
        this.f3865g = null;
        this.f3866h = null;
        this.f3867i = null;
        this.f3868j = 0L;
        this.f3869k = 10;
        this.f3870l = new JSONArray();
        this.f3871m = 5000;
        this.f3872n = 0;
        this.f3873o = 0;
        this.f3874p = 0L;
        this.f3875x = 28800000L;
        try {
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f3850a);
            this.f3874p = sharedPreferences.getLong(f3853s, 0L);
            this.f3872n = sharedPreferences.getInt(f3856v, 0);
            this.f3873o = sharedPreferences.getInt(f3857w, 0);
            this.f3862d = new c();
            this.f3860b = ABTest.getService(f3850a);
            this.f3861c = ImLatent.getService(f3850a, StatTracer.getInstance(f3850a));
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: com.umeng.analytics.pro.i$b */
    /* JADX INFO: compiled from: CoreProtocolImpl.java */
    private static class b {

        /* JADX INFO: renamed from: a */
        private static final C1715i f3891a = new C1715i();

        private b() {
        }
    }

    /* JADX INFO: renamed from: a */
    public static C1715i m3588a(Context context) {
        if (f3850a == null && context != null) {
            f3850a = context.getApplicationContext();
        }
        return b.f3891a;
    }

    /* JADX INFO: renamed from: a */
    public void m3614a(Object obj, int i) throws Throwable {
        try {
            switch (i) {
                case 4097:
                    if (obj != null) {
                        m3596d(obj);
                    }
                    if (!"-1".equals(((JSONObject) obj).optString("__i"))) {
                        m3617a(false);
                    }
                    break;
                case 4098:
                    if (obj != null) {
                        m3596d(obj);
                    }
                    if (!"-1".equals(((JSONObject) obj).optString("__i"))) {
                        m3617a(false);
                    }
                    break;
                case 4099:
                    C1722p.m3684a(f3850a);
                    break;
                case a.f3879d /* 4100 */:
                    C1714h.m3583b(f3850a);
                    break;
                case 4101:
                    m3598e(obj);
                    break;
                case 4102:
                    m3610o();
                    break;
                case a.f3882g /* 4103 */:
                    C1721o.m3668a().m3673a(f3850a, obj);
                    break;
                case a.f3883h /* 4104 */:
                    C1721o.m3668a().m3678b(f3850a, obj);
                    break;
                case a.f3884i /* 4105 */:
                    m3612a();
                    break;
                case a.f3885j /* 4106 */:
                    m3601f(obj);
                    break;
                default:
                    switch (i) {
                        case a.f3886k /* 8193 */:
                            m3615a(obj, 1, false);
                            break;
                        case 8194:
                            C1719m.m3652a(f3850a).m3662a(obj);
                            break;
                        case a.f3888m /* 8195 */:
                            C1703b.m3462a().m3479a(obj);
                            break;
                        case a.f3889n /* 8196 */:
                            C1703b.m3462a().m3502j();
                            break;
                        case a.f3890o /* 8197 */:
                            C1703b.m3462a().m3500h();
                            break;
                    }
                    break;
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: a */
    public void m3615a(Object obj, int i, boolean z) {
        JSONArray jSONArray;
        try {
            JSONObject jSONObject = (JSONObject) obj;
            if (jSONObject == null || jSONObject.length() <= 0) {
                return;
            }
            String strOptString = jSONObject.optString("__ii");
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f3850a);
            if (i == 0) {
                long j = sharedPreferences.getLong(f3854t, 0L);
                int i2 = sharedPreferences.getInt(f3855u, 0);
                if (!m3591a(j, i2)) {
                    return;
                } else {
                    sharedPreferences.edit().putLong(f3854t, System.currentTimeMillis()).putInt(f3855u, i2 == 5000 ? 0 : i2 + 1).commit();
                }
            }
            String string = sharedPreferences.getString(f3848B, "");
            if (TextUtils.isEmpty(string)) {
                jSONArray = new JSONArray();
            } else {
                jSONArray = new JSONArray(string);
            }
            JSONObject jSONObjectM3501i = C1703b.m3462a().m3501i();
            if ("-1".equals(strOptString)) {
                jSONObject.put(f3849C, i);
                if (!jSONObject.has(C1708b.f3717ab) && jSONObjectM3501i.length() > 0) {
                    jSONObject.put(C1708b.f3717ab, jSONObjectM3501i);
                }
                jSONArray.put(jSONObject);
                sharedPreferences.edit().putString(f3848B, jSONArray.toString()).commit();
                return;
            }
            if (i != 3 && jSONArray.length() > 0) {
                for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i3);
                    if (jSONObject2 != null && jSONObject2.length() > 0) {
                        int i4 = jSONObject2.getInt(f3849C);
                        if (!jSONObject2.has(C1708b.f3717ab) && jSONObjectM3501i.length() > 0) {
                            jSONObject2.put(C1708b.f3717ab, jSONObjectM3501i);
                        }
                        switch (i4) {
                            case 0:
                                jSONObject2.put("_$!ts", System.currentTimeMillis());
                                break;
                            case 1:
                                jSONObject2.put("_$!ts", System.currentTimeMillis());
                                break;
                        }
                        jSONObject2.put("__ii", strOptString);
                        jSONObject2.remove(f3849C);
                        C1713g.m3553a(f3850a).m3566a(jSONObject2, i4);
                    }
                }
                C1703b.m3462a().m3495d();
                sharedPreferences.edit().remove(f3848B).commit();
            }
            C1713g.m3553a(f3850a).m3566a(jSONObject, i);
            if (z) {
                m3594c(true);
                return;
            }
            if (UMEnvelopeBuild.isReadyBuild(f3850a, UMLogDataProtocol.UMBusinessType.U_DPLUS)) {
                Defcon service = Defcon.getService(f3850a);
                if (service.isOpen()) {
                    if (new ReportPolicy.DefconPolicy(StatTracer.getInstance(f3850a), service).shouldSendMessage(false)) {
                        m3594c(true);
                        return;
                    }
                    return;
                }
                m3594c(true);
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: a */
    public void m3617a(boolean z) throws Throwable {
        if (m3599e(z) && UMEnvelopeBuild.isReadyBuild(f3850a, UMLogDataProtocol.UMBusinessType.U_APP)) {
            m3594c(false);
        }
    }

    /* JADX INFO: renamed from: c */
    private void m3594c(boolean z) throws Throwable {
        JSONObject jSONObjectBuildEnvelopeWithExtHeader;
        JSONObject jSONObject = new JSONObject();
        if (z) {
            if (AnalyticsConfig.FLAG_DPLUS) {
                jSONObject = m3618b(UMEnvelopeBuild.maxDataSpace(f3850a));
            }
        } else {
            jSONObject = m3611a(UMEnvelopeBuild.maxDataSpace(f3850a));
        }
        if (jSONObject == null || jSONObject.length() < 1) {
            return;
        }
        JSONObject jSONObject2 = (JSONObject) jSONObject.opt("header");
        JSONObject jSONObject3 = (JSONObject) jSONObject.opt(C1708b.f3711W);
        if (f3850a == null || jSONObject2 == null || jSONObject3 == null || (jSONObjectBuildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(f3850a, jSONObject2, jSONObject3)) == null) {
            return;
        }
        m3592b(jSONObjectBuildEnvelopeWithExtHeader);
        m3613a((Object) jSONObjectBuildEnvelopeWithExtHeader);
    }

    /* JADX INFO: renamed from: a */
    public JSONObject m3611a(long j) {
        if (TextUtils.isEmpty(C1721o.m3668a().m3681c(f3850a))) {
            return null;
        }
        JSONObject jSONObjectM3619b = m3619b(false);
        int iM3648a = C1718l.m3646a().m3648a(f3850a);
        if (jSONObjectM3619b.length() <= 0) {
            return null;
        }
        if (jSONObjectM3619b.length() == 1) {
            if (jSONObjectM3619b.optJSONObject(C1708b.f3695G) != null && iM3648a != 3) {
                return null;
            }
            if (!TextUtils.isEmpty(jSONObjectM3619b.optString("userlevel")) && iM3648a != 3) {
                return null;
            }
        } else if (jSONObjectM3619b.length() == 2 && jSONObjectM3619b.optJSONObject(C1708b.f3695G) != null && !TextUtils.isEmpty(jSONObjectM3619b.optString("userlevel")) && iM3648a != 3) {
            return null;
        }
        JSONObject jSONObjectM3595d = m3595d(false);
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            if (iM3648a == 3) {
                jSONObject2.put("analytics", new JSONObject());
            } else if (jSONObjectM3619b != null && jSONObjectM3619b.length() > 0) {
                jSONObject2.put("analytics", jSONObjectM3619b);
            }
            if (jSONObjectM3595d != null && jSONObjectM3595d.length() > 0) {
                jSONObject.put("header", jSONObjectM3595d);
            }
            if (jSONObject2.length() > 0) {
                jSONObject.put(C1708b.f3711W, jSONObject2);
            }
            return m3590a(jSONObject, j);
        } catch (Throwable unused) {
            return jSONObject;
        }
    }

    /* JADX INFO: renamed from: b */
    public JSONObject m3618b(long j) throws Throwable {
        if (!AnalyticsConfig.FLAG_DPLUS || TextUtils.isEmpty(C1721o.m3668a().m3681c(f3850a))) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        if (AnalyticsConfig.FLAG_DPLUS) {
            jSONObject = C1713g.m3553a(f3850a).m3574c();
            C1718l.m3646a().m3649a(jSONObject, f3850a);
            Defcon service = Defcon.getService(f3850a);
            if (service.isOpen() && !new ReportPolicy.DefconPolicy(StatTracer.getInstance(f3850a), service).shouldSendMessage(false)) {
                jSONObject = new JSONObject();
            }
        }
        if (jSONObject.length() <= 0) {
            return null;
        }
        JSONObject jSONObjectM3595d = m3595d(true);
        JSONObject jSONObject2 = new JSONObject();
        try {
            JSONObject jSONObject3 = new JSONObject();
            if (jSONObject != null && jSONObject.length() > 0) {
                jSONObject3.put("dplus", jSONObject);
            }
            if (jSONObjectM3595d != null && jSONObjectM3595d.length() > 0) {
                jSONObject2.put("header", jSONObjectM3595d);
            }
            if (jSONObject3.length() > 0) {
                jSONObject2.put(C1708b.f3711W, jSONObject3);
            }
            return m3590a(jSONObject2, j);
        } catch (Throwable unused) {
            return jSONObject2;
        }
    }

    /* JADX INFO: renamed from: a */
    private JSONObject m3590a(JSONObject jSONObject, long j) {
        try {
            if (C1717k.m3640a(jSONObject) <= j) {
                return jSONObject;
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject("header");
            jSONObject2.put(C1708b.f3729an, C1717k.m3640a(jSONObject));
            jSONObject.put("header", jSONObject2);
            return C1717k.m3642a(f3850a, j, jSONObject);
        } catch (Throwable unused) {
            return jSONObject;
        }
    }

    /* JADX INFO: renamed from: d */
    private JSONObject m3595d(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (AnalyticsConfig.mWrapperType != null && AnalyticsConfig.mWrapperVersion != null) {
                jSONObject.put(C1708b.f3738g, AnalyticsConfig.mWrapperVersion);
                jSONObject.put(C1708b.f3737f, AnalyticsConfig.mWrapperType);
            }
            int verticalType = AnalyticsConfig.getVerticalType(f3850a);
            if (verticalType == MobclickAgent.EScenarioType.E_DUM_NORMAL.toValue()) {
                AnalyticsConfig.FLAG_DPLUS = true;
                verticalType = MobclickAgent.EScenarioType.E_UM_NORMAL.toValue();
            } else if (verticalType == MobclickAgent.EScenarioType.E_DUM_GAME.toValue()) {
                AnalyticsConfig.FLAG_DPLUS = true;
                verticalType = MobclickAgent.EScenarioType.E_UM_GAME.toValue();
            } else {
                AnalyticsConfig.FLAG_DPLUS = false;
            }
            jSONObject.put(C1708b.f3740i, verticalType);
            jSONObject.put("sdk_version", C1723q.f3928a);
            String strMD5 = HelperUtils.MD5(AnalyticsConfig.getSecretKey(f3850a));
            if (!TextUtils.isEmpty(strMD5)) {
                jSONObject.put("secret", strMD5);
            }
            String strImprintProperty = UMEnvelopeBuild.imprintProperty(f3850a, "pr_ve", null);
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f3850a);
            if (z) {
                jSONObject.put(C1708b.f3743l, m3603h());
                jSONObject.put(C1708b.f3744m, m3604i());
                if (sharedPreferences != null) {
                    String string = sharedPreferences.getString("dp_vers_name", "");
                    if (!TextUtils.isEmpty(string)) {
                        jSONObject.put("app_version", string);
                        jSONObject.put("version_code", sharedPreferences.getInt("dp_vers_code", 0));
                        String str = new SimpleDateFormat(AppUtils.TIME_FORMAT_DAY, Locale.getDefault()).format(new Date(System.currentTimeMillis()));
                        if (TextUtils.isEmpty(strImprintProperty)) {
                            jSONObject.put(C1708b.f3743l, sharedPreferences.getString("dp_vers_pre_version", AccountManager.TYPE_LOGIN));
                            jSONObject.put(C1708b.f3744m, sharedPreferences.getString("dp_vers_date", str));
                        }
                        sharedPreferences.edit().putString("dp_pre_version", string).putString("dp_cur_version", DeviceConfig.getAppVersionName(f3850a)).putString("dp_pre_date", str).remove("dp_vers_name").remove("dp_vers_code").remove("dp_vers_date").remove("dp_vers_pre_version").commit();
                    }
                }
            } else {
                jSONObject.put(C1708b.f3743l, m3600f());
                jSONObject.put(C1708b.f3744m, m3602g());
                if (sharedPreferences != null) {
                    String string2 = sharedPreferences.getString("vers_name", "");
                    if (!TextUtils.isEmpty(string2)) {
                        jSONObject.put("app_version", string2);
                        jSONObject.put("version_code", sharedPreferences.getInt("vers_code", 0));
                        String str2 = new SimpleDateFormat(AppUtils.TIME_FORMAT_DAY, Locale.getDefault()).format(new Date(System.currentTimeMillis()));
                        if (TextUtils.isEmpty(strImprintProperty)) {
                            jSONObject.put(C1708b.f3743l, sharedPreferences.getString("vers_pre_version", AccountManager.TYPE_LOGIN));
                            jSONObject.put(C1708b.f3744m, sharedPreferences.getString("vers_date", str2));
                        }
                        sharedPreferences.edit().putString("pre_version", string2).putString("cur_version", DeviceConfig.getAppVersionName(f3850a)).putString("pre_date", str2).remove("vers_name").remove("vers_code").remove("vers_date").remove("vers_pre_version").commit();
                    }
                }
            }
        } catch (Throwable th) {
            ThrowableExtension.printStackTrace(th);
        }
        return jSONObject;
    }

    /* JADX INFO: renamed from: b */
    public JSONObject m3619b(boolean z) {
        JSONObject jSONObject = null;
        try {
            JSONObject jSONObjectM3562a = C1713g.m3553a(f3850a).m3562a(z);
            if (jSONObjectM3562a == null) {
                try {
                    jSONObject = new JSONObject();
                } catch (Throwable unused) {
                    return jSONObjectM3562a;
                }
            } else {
                jSONObject = jSONObjectM3562a;
            }
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f3850a);
            if (sharedPreferences != null) {
                String string = sharedPreferences.getString("userlevel", "");
                if (!TextUtils.isEmpty(string)) {
                    jSONObject.put("userlevel", string);
                }
            }
            if (m3609n()) {
                this.f3868j = m3623c();
                if (this.f3868j != 0) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("ts", this.f3868j);
                    jSONObject.put(C1708b.f3692D, jSONObject2);
                    sharedPreferences.edit().putLong(f3852r, 0L).commit();
                }
            }
            String[] strArrM3505a = C1704c.m3505a(f3850a);
            if (strArrM3505a != null && !TextUtils.isEmpty(strArrM3505a[0]) && !TextUtils.isEmpty(strArrM3505a[1])) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put(C1708b.f3696H, strArrM3505a[0]);
                jSONObject3.put(C1708b.f3697I, strArrM3505a[1]);
                if (jSONObject3.length() > 0) {
                    jSONObject.put(C1708b.f3695G, jSONObject3);
                }
            }
            if (ABTest.getService(f3850a).isInTest()) {
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put(ABTest.getService(f3850a).getTestName(), ABTest.getService(f3850a).getGroupInfo());
                jSONObject.put(C1708b.f3694F, jSONObject4);
            }
            C1718l.m3646a().m3651b(jSONObject, f3850a);
            if (jSONObject.length() <= 0) {
                return jSONObject;
            }
            new JSONObject().put("analytics", jSONObject);
            return jSONObject;
        } catch (Throwable unused2) {
            return jSONObject;
        }
    }

    /* JADX INFO: renamed from: f */
    private String m3600f() {
        String string = null;
        try {
            String strImprintProperty = UMEnvelopeBuild.imprintProperty(f3850a, "pr_ve", null);
            try {
                if (!TextUtils.isEmpty(strImprintProperty)) {
                    string = strImprintProperty;
                } else {
                    if (!TextUtils.isEmpty(this.f3864f)) {
                        return this.f3864f;
                    }
                    if (this.f3863e == null) {
                        this.f3863e = PreferenceWrapper.getDefault(f3850a);
                    }
                    string = this.f3863e.getString("pre_version", "");
                    String appVersionName = DeviceConfig.getAppVersionName(f3850a);
                    if (TextUtils.isEmpty(string)) {
                        this.f3863e.edit().putString("pre_version", AccountManager.TYPE_LOGIN).putString("cur_version", appVersionName).commit();
                        string = AccountManager.TYPE_LOGIN;
                    } else {
                        String string2 = this.f3863e.getString("cur_version", "");
                        if (!appVersionName.equals(string2)) {
                            this.f3863e.edit().putString("pre_version", string2).putString("cur_version", appVersionName).commit();
                            string = string2;
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
        }
        this.f3864f = string;
        return string;
    }

    /* JADX INFO: renamed from: g */
    private String m3602g() {
        String string = null;
        try {
            String strImprintProperty = UMEnvelopeBuild.imprintProperty(f3850a, "ud_da", null);
            try {
                if (!TextUtils.isEmpty(strImprintProperty)) {
                    string = strImprintProperty;
                } else {
                    if (!TextUtils.isEmpty(this.f3865g)) {
                        return this.f3865g;
                    }
                    if (this.f3863e == null) {
                        this.f3863e = PreferenceWrapper.getDefault(f3850a);
                    }
                    string = this.f3863e.getString("pre_date", "");
                    if (TextUtils.isEmpty(string)) {
                        string = new SimpleDateFormat(AppUtils.TIME_FORMAT_DAY, Locale.getDefault()).format(new Date(System.currentTimeMillis()));
                        this.f3863e.edit().putString("pre_date", string).commit();
                    } else {
                        String str = new SimpleDateFormat(AppUtils.TIME_FORMAT_DAY, Locale.getDefault()).format(new Date(System.currentTimeMillis()));
                        if (!string.equals(str)) {
                            this.f3863e.edit().putString("pre_date", str).commit();
                            string = str;
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
        }
        this.f3865g = string;
        return string;
    }

    /* JADX INFO: renamed from: h */
    private String m3603h() {
        String string = null;
        try {
            String strImprintProperty = UMEnvelopeBuild.imprintProperty(f3850a, "pr_ve", null);
            try {
                if (!TextUtils.isEmpty(strImprintProperty)) {
                    string = strImprintProperty;
                } else {
                    if (!TextUtils.isEmpty(this.f3866h)) {
                        return this.f3866h;
                    }
                    if (this.f3863e == null) {
                        this.f3863e = PreferenceWrapper.getDefault(f3850a);
                    }
                    string = this.f3863e.getString("dp_pre_version", "");
                    String appVersionName = DeviceConfig.getAppVersionName(f3850a);
                    if (TextUtils.isEmpty(string)) {
                        this.f3863e.edit().putString("dp_pre_version", AccountManager.TYPE_LOGIN).putString("dp_cur_version", appVersionName).commit();
                        string = AccountManager.TYPE_LOGIN;
                    } else {
                        String string2 = this.f3863e.getString("dp_cur_version", "");
                        if (!appVersionName.equals(string2)) {
                            this.f3863e.edit().putString("dp_pre_version", string2).putString("dp_cur_version", appVersionName).commit();
                            string = string2;
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
        }
        this.f3866h = string;
        return string;
    }

    /* JADX INFO: renamed from: i */
    private String m3604i() {
        String string = null;
        try {
            String strImprintProperty = UMEnvelopeBuild.imprintProperty(f3850a, "ud_da", null);
            try {
                if (!TextUtils.isEmpty(strImprintProperty)) {
                    string = strImprintProperty;
                } else {
                    if (!TextUtils.isEmpty(this.f3867i)) {
                        return this.f3867i;
                    }
                    if (this.f3863e == null) {
                        this.f3863e = PreferenceWrapper.getDefault(f3850a);
                    }
                    string = this.f3863e.getString("dp_pre_date", "");
                    if (TextUtils.isEmpty(string)) {
                        string = new SimpleDateFormat(AppUtils.TIME_FORMAT_DAY, Locale.getDefault()).format(new Date(System.currentTimeMillis()));
                        this.f3863e.edit().putString("dp_pre_date", string).commit();
                    } else {
                        String str = new SimpleDateFormat(AppUtils.TIME_FORMAT_DAY, Locale.getDefault()).format(new Date(System.currentTimeMillis()));
                        if (!string.equals(str)) {
                            this.f3863e.edit().putString("dp_pre_date", str).commit();
                            string = str;
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
        }
        this.f3867i = string;
        return string;
    }

    /* JADX INFO: renamed from: a */
    public void m3612a() {
        try {
            if (this.f3870l.length() > 0) {
                C1713g.m3553a(f3850a).m3565a(this.f3870l);
                this.f3870l = new JSONArray();
            }
            PreferenceWrapper.getDefault(f3850a).edit().putLong(f3853s, this.f3874p).putInt(f3856v, this.f3872n).putInt(f3857w, this.f3873o).commit();
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: com.umeng.analytics.pro.i$d */
    /* JADX INFO: compiled from: CoreProtocolImpl.java */
    public static class d {

        /* JADX INFO: renamed from: a */
        private Map<String, Object> f3898a;

        /* JADX INFO: renamed from: b */
        private String f3899b;

        /* JADX INFO: renamed from: c */
        private String f3900c;

        /* JADX INFO: renamed from: d */
        private long f3901d;

        private d() {
            this.f3898a = null;
            this.f3899b = null;
            this.f3900c = null;
            this.f3901d = 0L;
        }

        public d(String str, Map<String, Object> map, String str2, long j) {
            this.f3898a = null;
            this.f3899b = null;
            this.f3900c = null;
            this.f3901d = 0L;
            this.f3898a = map;
            this.f3899b = str;
            this.f3901d = j;
            this.f3900c = str2;
        }

        /* JADX INFO: renamed from: a */
        public Map<String, Object> m3633a() {
            return this.f3898a;
        }

        /* JADX INFO: renamed from: b */
        public String m3634b() {
            return this.f3900c;
        }

        /* JADX INFO: renamed from: c */
        public String m3635c() {
            return this.f3899b;
        }

        /* JADX INFO: renamed from: d */
        public long m3636d() {
            return this.f3901d;
        }
    }

    /* JADX INFO: renamed from: d */
    private void m3596d(Object obj) throws Throwable {
        try {
            JSONObject jSONObject = (JSONObject) obj;
            if (2050 == jSONObject.getInt("__t")) {
                if (!m3591a(this.f3874p, this.f3872n)) {
                    return;
                } else {
                    this.f3872n++;
                }
            } else if (2049 == jSONObject.getInt("__t")) {
                if (!m3591a(this.f3874p, this.f3873o)) {
                    return;
                } else {
                    this.f3873o++;
                }
            }
            if (this.f3870l.length() >= this.f3869k) {
                C1713g.m3553a(f3850a).m3565a(this.f3870l);
                this.f3870l = new JSONArray();
            }
            if (this.f3874p == 0) {
                this.f3874p = System.currentTimeMillis();
            }
            this.f3870l.put(jSONObject);
        } catch (Throwable th) {
            MLog.m4341e(th);
        }
    }

    /* JADX INFO: renamed from: a */
    private boolean m3591a(long j, int i) {
        if (j == 0) {
            return true;
        }
        if (System.currentTimeMillis() - j <= 28800000) {
            return i < 5000;
        }
        m3605j();
        return true;
    }

    /* JADX INFO: renamed from: j */
    private void m3605j() {
        try {
            this.f3872n = 0;
            this.f3873o = 0;
            this.f3874p = System.currentTimeMillis();
            PreferenceWrapper.getDefault(f3850a).edit().putLong(f3854t, System.currentTimeMillis()).putInt(f3855u, 0).commit();
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: e */
    private boolean m3599e(boolean z) throws Throwable {
        if (m3609n()) {
            return true;
        }
        if (this.f3862d == null) {
            this.f3862d = new c();
        }
        this.f3862d.m3629a();
        ReportPolicy.ReportStrategy reportStrategyM3632c = this.f3862d.m3632c();
        boolean zShouldSendMessage = reportStrategyM3632c.shouldSendMessage(z);
        if (zShouldSendMessage && (((reportStrategyM3632c instanceof ReportPolicy.ReportByInterval) || (reportStrategyM3632c instanceof ReportPolicy.DebugPolicy)) && m3606k())) {
            m3612a();
        }
        return zShouldSendMessage;
    }

    /* JADX INFO: renamed from: k */
    private boolean m3606k() {
        try {
            if (!TextUtils.isEmpty(C1721o.m3668a().m3680c())) {
                m3621b(f3850a);
            }
            if (this.f3870l.length() <= 0) {
                return false;
            }
            for (int i = 0; i < this.f3870l.length(); i++) {
                JSONObject jSONObjectOptJSONObject = this.f3870l.optJSONObject(i);
                if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject.length() > 0) {
                    String strOptString = jSONObjectOptJSONObject.optString("__i");
                    if (TextUtils.isEmpty(strOptString) || "-1".equals(strOptString)) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Throwable unused) {
            return true;
        }
    }

    /* JADX INFO: renamed from: com.umeng.analytics.pro.i$c */
    /* JADX INFO: compiled from: CoreProtocolImpl.java */
    public class c {

        /* JADX INFO: renamed from: b */
        private ReportPolicy.ReportStrategy f3893b = null;

        /* JADX INFO: renamed from: c */
        private int f3894c = -1;

        /* JADX INFO: renamed from: d */
        private int f3895d = -1;

        /* JADX INFO: renamed from: e */
        private int f3896e = -1;

        /* JADX INFO: renamed from: f */
        private int f3897f = -1;

        public c() {
        }

        /* JADX INFO: renamed from: a */
        public void m3629a() {
            try {
                int[] iArrM3630a = m3630a(-1, -1);
                this.f3894c = iArrM3630a[0];
                this.f3895d = iArrM3630a[1];
            } catch (Throwable unused) {
            }
        }

        /* JADX INFO: renamed from: a */
        public int[] m3630a(int i, int i2) {
            int iIntValue = Integer.valueOf(UMEnvelopeBuild.imprintProperty(C1715i.f3850a, "report_policy", "-1")).intValue();
            int iIntValue2 = Integer.valueOf(UMEnvelopeBuild.imprintProperty(C1715i.f3850a, "report_interval", "-1")).intValue();
            if (iIntValue == -1 || !ReportPolicy.isValid(iIntValue)) {
                return new int[]{i, i2};
            }
            int i3 = 90;
            if (iIntValue2 != -1 && iIntValue2 >= 90 && iIntValue2 <= 86400) {
                i3 = iIntValue2;
            }
            return new int[]{iIntValue, i3 * 1000};
        }

        /* JADX INFO: renamed from: a */
        public int m3628a(int i) {
            int iIntValue = Integer.valueOf(UMEnvelopeBuild.imprintProperty(C1715i.f3850a, "test_report_interval", "-1")).intValue();
            return (iIntValue == -1 || iIntValue < 90 || iIntValue > 86400) ? i : iIntValue * 1000;
        }

        /* JADX INFO: renamed from: b */
        protected void m3631b() throws Throwable {
            ReportPolicy.ReportStrategy defconPolicy;
            Defcon service = Defcon.getService(C1715i.f3850a);
            int iM3628a = 0;
            if (!service.isOpen()) {
                boolean z = Integer.valueOf(UMEnvelopeBuild.imprintProperty(C1715i.f3850a, "integrated_test", "-1")).intValue() == 1;
                if (!MLog.DEBUG || !z) {
                    if (C1715i.this.f3860b.isInTest() && "RPT".equals(C1715i.this.f3860b.getTestName())) {
                        if (C1715i.this.f3860b.getTestPolicy() == 6) {
                            if ((Integer.valueOf(UMEnvelopeBuild.imprintProperty(C1715i.f3850a, "test_report_interval", "-1")).intValue() != -1 ? 1 : 0) != 0) {
                                iM3628a = m3628a(90000);
                            } else if (this.f3895d > 0) {
                                iM3628a = this.f3895d;
                            } else {
                                iM3628a = this.f3897f;
                            }
                        }
                        this.f3893b = m3627b(C1715i.this.f3860b.getTestPolicy(), iM3628a);
                    } else {
                        int i = this.f3896e;
                        int i2 = this.f3897f;
                        if (this.f3894c != -1) {
                            i = this.f3894c;
                            i2 = this.f3895d;
                        }
                        this.f3893b = m3627b(i, i2);
                    }
                } else {
                    this.f3893b = new ReportPolicy.DebugPolicy(StatTracer.getInstance(C1715i.f3850a));
                }
            } else {
                if ((this.f3893b instanceof ReportPolicy.DefconPolicy) && this.f3893b.isValid()) {
                    iM3628a = 1;
                }
                if (iM3628a == 0) {
                    defconPolicy = new ReportPolicy.DefconPolicy(StatTracer.getInstance(C1715i.f3850a), service);
                } else {
                    defconPolicy = this.f3893b;
                }
                this.f3893b = defconPolicy;
            }
            MLog.m4331d("Report policy : " + this.f3893b.getClass().getSimpleName());
        }

        /* JADX INFO: renamed from: c */
        public ReportPolicy.ReportStrategy m3632c() throws Throwable {
            m3631b();
            return this.f3893b;
        }

        /* JADX INFO: renamed from: b */
        private ReportPolicy.ReportStrategy m3627b(int i, int i2) {
            switch (i) {
                case 0:
                    return this.f3893b instanceof ReportPolicy.ReportRealtime ? this.f3893b : new ReportPolicy.ReportRealtime();
                case 1:
                    return this.f3893b instanceof ReportPolicy.ReportAtLaunch ? this.f3893b : new ReportPolicy.ReportAtLaunch();
                case 2:
                case 3:
                case 7:
                default:
                    return this.f3893b instanceof ReportPolicy.ReportAtLaunch ? this.f3893b : new ReportPolicy.ReportAtLaunch();
                case 4:
                    return this.f3893b instanceof ReportPolicy.ReportDaily ? this.f3893b : new ReportPolicy.ReportDaily(StatTracer.getInstance(C1715i.f3850a));
                case 5:
                    return this.f3893b instanceof ReportPolicy.ReportWifiOnly ? this.f3893b : new ReportPolicy.ReportWifiOnly(C1715i.f3850a);
                case 6:
                    if (this.f3893b instanceof ReportPolicy.ReportByInterval) {
                        ReportPolicy.ReportStrategy reportStrategy = this.f3893b;
                        ((ReportPolicy.ReportByInterval) reportStrategy).setReportInterval(i2);
                        return reportStrategy;
                    }
                    return new ReportPolicy.ReportByInterval(StatTracer.getInstance(C1715i.f3850a), i2);
                case 8:
                    return this.f3893b instanceof ReportPolicy.SmartPolicy ? this.f3893b : new ReportPolicy.SmartPolicy(StatTracer.getInstance(C1715i.f3850a));
            }
        }
    }

    /* JADX INFO: renamed from: b */
    private void m3592b(JSONObject jSONObject) throws Throwable {
        if (jSONObject == null) {
            return;
        }
        try {
            if (jSONObject.length() <= 0) {
                return;
            }
            JSONObject jSONObject2 = new JSONObject();
            if (jSONObject.has("analytics")) {
                JSONObject jSONObject3 = jSONObject.getJSONObject("analytics");
                if (jSONObject3.has(C1708b.f3702N)) {
                    jSONObject2.put(C1708b.f3702N, jSONObject3.getJSONArray(C1708b.f3702N));
                }
                if (jSONObject3.has(C1708b.f3703O)) {
                    jSONObject2.put(C1708b.f3703O, jSONObject3.getJSONArray(C1708b.f3703O));
                }
                if (jSONObject3.has(C1708b.f3698J)) {
                    jSONObject2.put(C1708b.f3698J, jSONObject3.getJSONArray(C1708b.f3698J));
                }
                if (jSONObject3.has(C1708b.f3745n)) {
                    JSONArray jSONArray = jSONObject3.getJSONArray(C1708b.f3745n);
                    JSONArray jSONArray2 = new JSONArray();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject4 = jSONArray.getJSONObject(i);
                        if (jSONObject4 != null && jSONObject4.length() > 0) {
                            if (jSONObject4.has(C1708b.f3751t)) {
                                jSONObject4.remove(C1708b.f3751t);
                            }
                            jSONArray2.put(jSONObject4);
                        }
                    }
                    jSONObject2.put(C1708b.f3745n, jSONArray2);
                }
                if (jSONObject3.has(C1708b.f3692D)) {
                    jSONObject2.put(C1708b.f3692D, jSONObject3.getJSONObject(C1708b.f3692D));
                }
                if (jSONObject3.has(C1708b.f3695G)) {
                    jSONObject2.put(C1708b.f3695G, jSONObject3.getJSONObject(C1708b.f3695G));
                }
            }
            if (jSONObject.has("dplus")) {
                jSONObject2.put("dplus", jSONObject.getJSONObject("dplus"));
            }
            if (jSONObject.has("header")) {
                JSONObject jSONObject5 = jSONObject.getJSONObject("header");
                jSONObject2.put("sdk_version", jSONObject5.getString("sdk_version"));
                jSONObject2.put("device_id", jSONObject5.getString("device_id"));
                jSONObject2.put("device_model", jSONObject5.getString("device_model"));
                jSONObject2.put("version", jSONObject5.getString("version_code"));
                jSONObject2.put("appkey", jSONObject5.getString("appkey"));
                jSONObject2.put("channel", jSONObject5.getString("channel"));
            }
            if (jSONObject2.length() > 0) {
                MLog.m4331d("constructMessage:" + jSONObject2.toString());
            }
        } catch (Throwable th) {
            MLog.m4341e(th);
        }
    }

    /* JADX INFO: renamed from: a */
    public void m3613a(Object obj) {
        if (obj != null) {
            try {
                JSONObject jSONObject = (JSONObject) obj;
                if (jSONObject.length() > 0 && (!jSONObject.has(C1708b.f3730ao) || 101 != jSONObject.getInt(C1708b.f3730ao))) {
                    m3593c(jSONObject);
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: renamed from: c */
    private void m3593c(JSONObject jSONObject) throws Throwable {
        JSONObject jSONObjectOptJSONObject;
        try {
            if (jSONObject.getJSONObject("header").has(C1708b.f3729an)) {
                if (jSONObject.has(C1708b.f3711W)) {
                    jSONObject = jSONObject.getJSONObject(C1708b.f3711W);
                }
                if (jSONObject.has("analytics")) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("analytics");
                    if (jSONObject2.has(C1708b.f3745n)) {
                        JSONObject jSONObject3 = jSONObject2.getJSONArray(C1708b.f3745n).getJSONObject(0);
                        if (jSONObject3 != null) {
                            C1713g.m3553a(f3850a).m3567a(true, jSONObject3.getString("id"));
                        }
                    } else {
                        C1713g.m3553a(f3850a).m3578g();
                    }
                } else if (jSONObject.has("dplus")) {
                    JSONObject jSONObject4 = jSONObject.getJSONObject("dplus");
                    if (jSONObject4.has(C1708b.f3713Y)) {
                        C1713g.m3553a(f3850a).m3573b(0);
                    }
                    if (jSONObject4.has(C1708b.f3718ac)) {
                        C1713g.m3553a(f3850a).m3573b(4);
                    }
                    if (jSONObject4.has(C1708b.f3723ah)) {
                        C1713g.m3553a(f3850a).m3573b(1);
                    }
                }
                C1713g.m3553a(f3850a).m3563a();
                return;
            }
            if (jSONObject.has(C1708b.f3711W)) {
                jSONObject = jSONObject.getJSONObject(C1708b.f3711W);
            }
            if (jSONObject.has("analytics") && (jSONObjectOptJSONObject = jSONObject.optJSONObject("analytics")) != null && jSONObjectOptJSONObject.length() > 0) {
                if (jSONObjectOptJSONObject.has(C1708b.f3745n)) {
                    C1713g.m3553a(f3850a).m3568a(true, false);
                }
                if (jSONObjectOptJSONObject.has(C1708b.f3702N) || jSONObjectOptJSONObject.has(C1708b.f3703O)) {
                    C1713g.m3553a(f3850a).m3575d();
                }
                if (jSONObjectOptJSONObject.has(C1708b.f3698J)) {
                    C1713g.m3553a(f3850a).m3576e();
                }
            }
            if (jSONObject.has("dplus")) {
                C1713g.m3553a(f3850a).m3577f();
            }
            C1713g.m3553a(f3850a).m3563a();
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: renamed from: b */
    public void m3622b(Object obj) throws Throwable {
        f3859z = true;
        m3621b(f3850a);
        m3612a();
        m3594c(false);
        if (AnalyticsConfig.FLAG_DPLUS) {
            m3615a(obj, 2, false);
        }
    }

    /* JADX INFO: renamed from: b */
    public void m3621b(Context context) {
        try {
            C1713g.m3553a(context).m3572b();
            m3607l();
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: b */
    public void m3620b() throws Throwable {
        f3859z = true;
        m3594c(false);
    }

    /* JADX INFO: renamed from: c */
    public void m3624c(Object obj) throws Throwable {
        m3608m();
        f3847A = true;
        m3600f();
        m3602g();
        m3603h();
        m3604i();
        m3617a(true);
        if (AnalyticsConfig.FLAG_DPLUS) {
            m3615a(obj, 2, false);
        }
    }

    /* JADX INFO: renamed from: l */
    private void m3607l() {
        if (this.f3870l.length() > 0) {
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < this.f3870l.length(); i++) {
                try {
                    JSONObject jSONObject = this.f3870l.getJSONObject(i);
                    if (jSONObject != null && jSONObject.length() > 0) {
                        String strOptString = jSONObject.optString("__i");
                        if (TextUtils.isEmpty(strOptString) || "-1".equals(strOptString)) {
                            String strM3680c = C1721o.m3668a().m3680c();
                            if (TextUtils.isEmpty(strM3680c)) {
                                strM3680c = "-1";
                            }
                            jSONObject.put("__i", strM3680c);
                        }
                        jSONArray.put(jSONObject);
                    } else {
                        jSONArray.put(jSONObject);
                    }
                } catch (Throwable unused) {
                }
            }
            this.f3870l = jSONArray;
        }
    }

    /* JADX INFO: renamed from: m */
    private void m3608m() {
        SharedPreferences sharedPreferences;
        try {
            if (!m3609n() || f3850a == null || (sharedPreferences = PreferenceWrapper.getDefault(f3850a)) == null || sharedPreferences.getLong(f3851q, 0L) != 0) {
                return;
            }
            sharedPreferences.edit().putLong(f3851q, System.currentTimeMillis()).commit();
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: c */
    public long m3623c() {
        SharedPreferences sharedPreferences;
        long jCurrentTimeMillis = 0;
        try {
            if (f3850a == null || (sharedPreferences = PreferenceWrapper.getDefault(f3850a)) == null) {
                return 0L;
            }
            long j = sharedPreferences.getLong(f3851q, 0L);
            if (j == 0) {
                try {
                    jCurrentTimeMillis = System.currentTimeMillis();
                    sharedPreferences.edit().putLong(f3851q, jCurrentTimeMillis).commit();
                    return jCurrentTimeMillis;
                } catch (Throwable unused) {
                }
            }
            return j;
        } catch (Throwable unused2) {
            return jCurrentTimeMillis;
        }
    }

    /* JADX INFO: renamed from: n */
    private boolean m3609n() {
        SharedPreferences sharedPreferences;
        try {
            return (f3850a == null || (sharedPreferences = PreferenceWrapper.getDefault(f3850a)) == null || sharedPreferences.getLong(f3852r, -1L) == 0) ? false : true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* JADX INFO: renamed from: o */
    private void m3610o() throws Throwable {
        try {
            m3621b(f3850a);
            m3612a();
            String[] strArrM3505a = C1704c.m3505a(f3850a);
            if (strArrM3505a == null || TextUtils.isEmpty(strArrM3505a[0]) || TextUtils.isEmpty(strArrM3505a[1])) {
                return;
            }
            boolean zM3675a = C1721o.m3668a().m3675a(f3850a, System.currentTimeMillis());
            C1704c.m3506b(f3850a);
            if (zM3675a) {
                C1721o.m3668a().m3677b(f3850a, System.currentTimeMillis());
            }
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.m4339e(" Excepthon  in  onProfileSignOff", th);
            }
        }
    }

    /* JADX INFO: renamed from: e */
    private void m3598e(Object obj) {
        try {
            m3621b(f3850a);
            m3612a();
            JSONObject jSONObject = (JSONObject) obj;
            if (jSONObject != null && jSONObject.length() > 0) {
                String string = jSONObject.getString(C1708b.f3696H);
                String string2 = jSONObject.getString(HttpTools.JSON_USER_UID);
                String[] strArrM3505a = C1704c.m3505a(f3850a);
                if (strArrM3505a != null && string.equals(strArrM3505a[0]) && string2.equals(strArrM3505a[1])) {
                    return;
                }
                boolean zM3675a = C1721o.m3668a().m3675a(f3850a, System.currentTimeMillis());
                C1704c.m3504a(f3850a, string, string2);
                if (zM3675a) {
                    C1721o.m3668a().m3677b(f3850a, System.currentTimeMillis());
                }
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: f */
    private void m3601f(Object obj) {
        try {
            JSONObject jSONObject = (JSONObject) obj;
            if (jSONObject == null || jSONObject.length() <= 0 || !jSONObject.has("__ii")) {
                return;
            }
            String strOptString = jSONObject.optString("__ii");
            jSONObject.remove("__ii");
            if (TextUtils.isEmpty(strOptString)) {
                return;
            }
            C1713g.m3553a(f3850a).m3569a(strOptString, obj.toString(), 2);
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: d */
    public void m3625d() {
        JSONArray jSONArray;
        try {
            String strM3680c = C1721o.m3668a().m3680c();
            if (TextUtils.isEmpty(strM3680c)) {
                return;
            }
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f3850a);
            String string = sharedPreferences.getString(f3848B, "");
            if (TextUtils.isEmpty(string)) {
                jSONArray = new JSONArray();
            } else {
                jSONArray = new JSONArray(string);
            }
            if (jSONArray.length() > 0) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    if (jSONObject != null && jSONObject.length() > 0) {
                        int i2 = jSONObject.getInt(f3849C);
                        switch (i2) {
                            case 0:
                                jSONObject.put("_$!ts", System.currentTimeMillis());
                                break;
                            case 1:
                                jSONObject.put("_$!ts", System.currentTimeMillis());
                                break;
                        }
                        jSONObject.put("__ii", strM3680c);
                        jSONObject.remove(f3849C);
                        C1713g.m3553a(f3850a).m3566a(jSONObject, i2);
                    }
                }
                sharedPreferences.edit().remove(f3848B).commit();
                if (UMEnvelopeBuild.isReadyBuild(f3850a, UMLogDataProtocol.UMBusinessType.U_DPLUS)) {
                    m3594c(true);
                }
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: a */
    public void m3616a(JSONObject jSONObject) {
        JSONObject jSONObjectM3595d;
        try {
            if (!UMEnvelopeBuild.isReadyBuild(f3850a, UMLogDataProtocol.UMBusinessType.U_DPLUS) || jSONObject == null || (jSONObjectM3595d = m3595d(true)) == null || jSONObjectM3595d.length() <= 0) {
                return;
            }
            JSONObject jSONObject2 = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put(C1708b.f3718ac, jSONArray);
            jSONObject2.put("dplus", jSONObject3);
            if (f3850a == null || jSONObjectM3595d == null || jSONObject2 == null) {
                return;
            }
            UMEnvelopeBuild.buildEnvelopeWithExtHeader(f3850a, jSONObjectM3595d, jSONObject2);
        } catch (Throwable unused) {
        }
    }
}
