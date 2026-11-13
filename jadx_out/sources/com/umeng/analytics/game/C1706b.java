package com.umeng.analytics.game;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.constant.Constants;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.C1703b;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.pro.InterfaceC1724r;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/* JADX INFO: renamed from: com.umeng.analytics.game.b */
/* JADX INFO: compiled from: InternalGameAgent.java */
/* JADX INFO: loaded from: classes.dex */
class C1706b implements InterfaceC1724r {

    /* JADX INFO: renamed from: a */
    public static boolean f3661a = true;

    /* JADX INFO: renamed from: A */
    private Context f3662A;

    /* JADX INFO: renamed from: b */
    private C1703b f3663b = MobclickAgent.getAgent();

    /* JADX INFO: renamed from: c */
    private C1705a f3664c = null;

    /* JADX INFO: renamed from: d */
    private final int f3665d = 100;

    /* JADX INFO: renamed from: e */
    private final int f3666e = 1;

    /* JADX INFO: renamed from: f */
    private final int f3667f = 0;

    /* JADX INFO: renamed from: g */
    private final int f3668g = -1;

    /* JADX INFO: renamed from: h */
    private final int f3669h = 1;

    /* JADX INFO: renamed from: i */
    private final String f3670i = "level";

    /* JADX INFO: renamed from: j */
    private final String f3671j = "pay";

    /* JADX INFO: renamed from: k */
    private final String f3672k = "buy";

    /* JADX INFO: renamed from: l */
    private final String f3673l = Constants.SCRIPT_USE_MESSAGE;

    /* JADX INFO: renamed from: m */
    private final String f3674m = "bonus";

    /* JADX INFO: renamed from: n */
    private final String f3675n = "item";

    /* JADX INFO: renamed from: o */
    private final String f3676o = "cash";

    /* JADX INFO: renamed from: p */
    private final String f3677p = "coin";

    /* JADX INFO: renamed from: q */
    private final String f3678q = "source";

    /* JADX INFO: renamed from: r */
    private final String f3679r = "amount";

    /* JADX INFO: renamed from: s */
    private final String f3680s = "user_level";

    /* JADX INFO: renamed from: t */
    private final String f3681t = "bonus_source";

    /* JADX INFO: renamed from: u */
    private final String f3682u = "level";

    /* JADX INFO: renamed from: v */
    private final String f3683v = "status";

    /* JADX INFO: renamed from: w */
    private final String f3684w = "duration";

    /* JADX INFO: renamed from: x */
    private final String f3685x = "curtype";

    /* JADX INFO: renamed from: y */
    private final String f3686y = "orderid";

    /* JADX INFO: renamed from: z */
    private final String f3687z = "UMGameAgent.init(Context) should be called before any game api";

    public C1706b() {
        f3661a = true;
    }

    /* JADX INFO: renamed from: a */
    void m3525a(Context context) throws Throwable {
        if (context == null) {
            MLog.m4337e("Context is null, can't init GameAgent");
            return;
        }
        this.f3662A = context.getApplicationContext();
        this.f3663b.m3478a((InterfaceC1724r) this);
        this.f3664c = new C1705a(this.f3662A);
    }

    /* JADX INFO: renamed from: a */
    void m3529a(boolean z) throws Throwable {
        MLog.m4331d(String.format("Trace sleep time : %b", Boolean.valueOf(z)));
        f3661a = z;
    }

    /* JADX INFO: renamed from: a */
    void m3526a(String str) {
        try {
            if (this.f3662A == null) {
                MLog.m4337e("UMGameAgent.init(Context) should be called before any game api");
                return;
            }
            this.f3664c.f3650b = str;
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(this.f3662A);
            if (sharedPreferences != null) {
                sharedPreferences.edit().putString("userlevel", str).commit();
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: b */
    void m3531b(String str) throws Throwable {
        if (this.f3662A == null) {
            MLog.m4337e("UMGameAgent.init(Context) should be called before any game api");
            return;
        }
        if (AnalyticsConfig.getVerticalType(this.f3662A) == MobclickAgent.EScenarioType.E_DUM_NORMAL.toValue() || AnalyticsConfig.getVerticalType(this.f3662A) == MobclickAgent.EScenarioType.E_UM_NORMAL.toValue()) {
            MLog.m4337e("UMGameAgent class is UMGameAgent API, can't be use in no-game scenario. ");
            return;
        }
        this.f3664c.f3649a = str;
        this.f3664c.m3508a(str);
        HashMap<String, Object> map = new HashMap<>();
        map.put("level", str);
        map.put("status", 0);
        if (this.f3664c.f3650b != null) {
            map.put("user_level", this.f3664c.f3650b);
        }
        this.f3663b.m3473a(this.f3662A, "level", map);
    }

    /* JADX INFO: renamed from: a */
    private void m3519a(String str, int i) throws Throwable {
        if (this.f3662A == null) {
            MLog.m4337e("UMGameAgent.init(Context) should be called before any game api");
            return;
        }
        if (AnalyticsConfig.getVerticalType(this.f3662A) == MobclickAgent.EScenarioType.E_DUM_NORMAL.toValue() || AnalyticsConfig.getVerticalType(this.f3662A) == MobclickAgent.EScenarioType.E_UM_NORMAL.toValue()) {
            MLog.m4337e("UMGameAgent class is UMGameAgent API, can't be use in no-game scenario. ");
            return;
        }
        C1705a.a aVarM3510b = this.f3664c.m3510b(str);
        if (aVarM3510b != null) {
            long jM3517e = aVarM3510b.m3517e();
            if (jM3517e <= 0) {
                MLog.m4331d("level duration is 0");
                return;
            }
            HashMap<String, Object> map = new HashMap<>();
            map.put("level", str);
            map.put("status", Integer.valueOf(i));
            map.put("duration", Long.valueOf(jM3517e));
            if (this.f3664c.f3650b != null) {
                map.put("user_level", this.f3664c.f3650b);
            }
            this.f3663b.m3473a(this.f3662A, "level", map);
            return;
        }
        MLog.m4355w(String.format("finishLevel(or failLevel) called before startLevel", new Object[0]));
    }

    /* JADX INFO: renamed from: c */
    void m3533c(String str) throws Throwable {
        if (this.f3662A == null) {
            MLog.m4337e("UMGameAgent.init(Context) should be called before any game api");
        } else if (AnalyticsConfig.getVerticalType(this.f3662A) == MobclickAgent.EScenarioType.E_DUM_NORMAL.toValue() || AnalyticsConfig.getVerticalType(this.f3662A) == MobclickAgent.EScenarioType.E_UM_NORMAL.toValue()) {
            MLog.m4337e("UMGameAgent class is UMGameAgent API, can't be use in no-game scenario. ");
        } else {
            m3519a(str, 1);
        }
    }

    /* JADX INFO: renamed from: d */
    void m3534d(String str) throws Throwable {
        if (this.f3662A == null) {
            MLog.m4337e("UMGameAgent.init(Context) should be called before any game api");
        } else if (AnalyticsConfig.getVerticalType(this.f3662A) == MobclickAgent.EScenarioType.E_DUM_NORMAL.toValue() || AnalyticsConfig.getVerticalType(this.f3662A) == MobclickAgent.EScenarioType.E_UM_NORMAL.toValue()) {
            MLog.m4337e("UMGameAgent class is UMGameAgent API, can't be use in no-game scenario. ");
        } else {
            m3519a(str, -1);
        }
    }

    /* JADX INFO: renamed from: a */
    void m3521a(double d, double d2, int i) throws Throwable {
        if (this.f3662A == null) {
            MLog.m4337e("UMGameAgent.init(Context) should be called before any game api");
            return;
        }
        if (AnalyticsConfig.getVerticalType(this.f3662A) == MobclickAgent.EScenarioType.E_DUM_NORMAL.toValue() || AnalyticsConfig.getVerticalType(this.f3662A) == MobclickAgent.EScenarioType.E_UM_NORMAL.toValue()) {
            MLog.m4337e("UMGameAgent class is UMGameAgent API, can't be use in no-game scenario. ");
            return;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("cash", Long.valueOf((long) (d * 100.0d)));
        map.put("coin", Long.valueOf((long) (d2 * 100.0d)));
        map.put("source", Integer.valueOf(i));
        if (this.f3664c.f3650b != null) {
            map.put("user_level", this.f3664c.f3650b);
        }
        if (this.f3664c.f3649a != null) {
            map.put("level", this.f3664c.f3649a);
        }
        this.f3663b.m3473a(this.f3662A, "pay", map);
    }

    /* JADX INFO: renamed from: a */
    void m3524a(double d, String str, int i, double d2, int i2) throws Throwable {
        if (AnalyticsConfig.getVerticalType(this.f3662A) == MobclickAgent.EScenarioType.E_DUM_NORMAL.toValue() || AnalyticsConfig.getVerticalType(this.f3662A) == MobclickAgent.EScenarioType.E_UM_NORMAL.toValue()) {
            MLog.m4337e("UMGameAgent class is UMGameAgent API, can't be use in no-game scenario. ");
        } else {
            m3521a(d, d2 * ((double) i), i2);
            m3527a(str, i, d2);
        }
    }

    /* JADX INFO: renamed from: a */
    void m3527a(String str, int i, double d) throws Throwable {
        if (this.f3662A == null) {
            MLog.m4337e("UMGameAgent.init(Context) should be called before any game api");
            return;
        }
        if (AnalyticsConfig.getVerticalType(this.f3662A) == MobclickAgent.EScenarioType.E_DUM_NORMAL.toValue() || AnalyticsConfig.getVerticalType(this.f3662A) == MobclickAgent.EScenarioType.E_UM_NORMAL.toValue()) {
            MLog.m4337e("UMGameAgent class is UMGameAgent API, can't be use in no-game scenario. ");
            return;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("item", str);
        map.put("amount", Integer.valueOf(i));
        map.put("coin", Long.valueOf((long) (((double) i) * d * 100.0d)));
        if (this.f3664c.f3650b != null) {
            map.put("user_level", this.f3664c.f3650b);
        }
        if (this.f3664c.f3649a != null) {
            map.put("level", this.f3664c.f3649a);
        }
        this.f3663b.m3473a(this.f3662A, "buy", map);
    }

    /* JADX INFO: renamed from: b */
    void m3532b(String str, int i, double d) throws Throwable {
        if (this.f3662A == null) {
            MLog.m4337e("UMGameAgent.init(Context) should be called before any game api");
            return;
        }
        if (AnalyticsConfig.getVerticalType(this.f3662A) == MobclickAgent.EScenarioType.E_DUM_NORMAL.toValue() || AnalyticsConfig.getVerticalType(this.f3662A) == MobclickAgent.EScenarioType.E_UM_NORMAL.toValue()) {
            MLog.m4337e("UMGameAgent class is UMGameAgent API, can't be use in no-game scenario. ");
            return;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("item", str);
        map.put("amount", Integer.valueOf(i));
        map.put("coin", Long.valueOf((long) (((double) i) * d * 100.0d)));
        if (this.f3664c.f3650b != null) {
            map.put("user_level", this.f3664c.f3650b);
        }
        if (this.f3664c.f3649a != null) {
            map.put("level", this.f3664c.f3649a);
        }
        this.f3663b.m3473a(this.f3662A, Constants.SCRIPT_USE_MESSAGE, map);
    }

    /* JADX INFO: renamed from: a */
    void m3522a(double d, int i) throws Throwable {
        if (this.f3662A == null) {
            MLog.m4337e("UMGameAgent.init(Context) should be called before any game api");
            return;
        }
        if (AnalyticsConfig.getVerticalType(this.f3662A) == MobclickAgent.EScenarioType.E_DUM_NORMAL.toValue() || AnalyticsConfig.getVerticalType(this.f3662A) == MobclickAgent.EScenarioType.E_UM_NORMAL.toValue()) {
            MLog.m4337e("UMGameAgent class is UMGameAgent API, can't be use in no-game scenario. ");
            return;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("coin", Long.valueOf((long) (d * 100.0d)));
        map.put("bonus_source", Integer.valueOf(i));
        if (this.f3664c.f3650b != null) {
            map.put("user_level", this.f3664c.f3650b);
        }
        if (this.f3664c.f3649a != null) {
            map.put("level", this.f3664c.f3649a);
        }
        this.f3663b.m3473a(this.f3662A, "bonus", map);
    }

    /* JADX INFO: renamed from: a */
    void m3528a(String str, int i, double d, int i2) throws Throwable {
        if (AnalyticsConfig.getVerticalType(this.f3662A) == MobclickAgent.EScenarioType.E_DUM_NORMAL.toValue() || AnalyticsConfig.getVerticalType(this.f3662A) == MobclickAgent.EScenarioType.E_UM_NORMAL.toValue()) {
            MLog.m4337e("UMGameAgent class is UMGameAgent API, can't be use in no-game scenario. ");
        } else {
            m3522a(((double) i) * d, i2);
            m3527a(str, i, d);
        }
    }

    @Override // com.umeng.analytics.pro.InterfaceC1724r
    /* JADX INFO: renamed from: a */
    public void mo3520a() throws Throwable {
        MLog.m4331d("App resume from background");
        if (this.f3662A == null) {
            MLog.m4337e("UMGameAgent.init(Context) should be called before any game api");
            return;
        }
        if (AnalyticsConfig.getVerticalType(this.f3662A) == MobclickAgent.EScenarioType.E_DUM_NORMAL.toValue() || AnalyticsConfig.getVerticalType(this.f3662A) == MobclickAgent.EScenarioType.E_UM_NORMAL.toValue()) {
            MLog.m4337e("UMGameAgent class is UMGameAgent API, can't be use in no-game scenario. ");
        } else if (f3661a) {
            this.f3664c.m3511b();
        }
    }

    @Override // com.umeng.analytics.pro.InterfaceC1724r
    /* JADX INFO: renamed from: b */
    public void mo3530b() throws Throwable {
        if (this.f3662A == null) {
            MLog.m4337e("UMGameAgent.init(Context) should be called before any game api");
            return;
        }
        if (AnalyticsConfig.getVerticalType(this.f3662A) == MobclickAgent.EScenarioType.E_DUM_NORMAL.toValue() || AnalyticsConfig.getVerticalType(this.f3662A) == MobclickAgent.EScenarioType.E_UM_NORMAL.toValue()) {
            MLog.m4337e("UMGameAgent class is UMGameAgent API, can't be use in no-game scenario. ");
        } else if (f3661a) {
            this.f3664c.m3509a();
        }
    }

    /* JADX INFO: renamed from: a */
    void m3523a(double d, String str, double d2, int i, String str2) throws Throwable {
        if (this.f3662A == null) {
            MLog.m4337e("UMGameAgent.init(Context) should be called before any game api");
            return;
        }
        if (AnalyticsConfig.getVerticalType(this.f3662A) == MobclickAgent.EScenarioType.E_DUM_NORMAL.toValue() || AnalyticsConfig.getVerticalType(this.f3662A) == MobclickAgent.EScenarioType.E_UM_NORMAL.toValue()) {
            MLog.m4337e("UMGameAgent class is UMGameAgent API, can't be use in no-game scenario. ");
            return;
        }
        if (d < 0.0d || d2 < 0.0d) {
            return;
        }
        HashMap<String, Object> map = new HashMap<>();
        if (!TextUtils.isEmpty(str) && str.length() > 0 && str.length() <= 3) {
            map.put("curtype", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            try {
                int length = str2.getBytes("UTF-8").length;
                if (length > 0 && length <= 1024) {
                    map.put("orderid", str2);
                }
            } catch (UnsupportedEncodingException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        map.put("cash", Long.valueOf((long) (d * 100.0d)));
        map.put("coin", Long.valueOf((long) (d2 * 100.0d)));
        map.put("source", Integer.valueOf(i));
        if (this.f3664c.f3650b != null) {
            map.put("user_level", this.f3664c.f3650b);
        }
        if (this.f3664c.f3649a != null) {
            map.put("level", this.f3664c.f3649a);
        }
        this.f3663b.m3473a(this.f3662A, "pay", map);
    }
}
