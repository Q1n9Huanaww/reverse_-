package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.p024ds.daisi.fragment.ScriptUipSetFragment;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.C1703b;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.utils.UMUtils;
import java.lang.reflect.Method;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.umeng.analytics.pro.o */
/* JADX INFO: compiled from: SessionTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class C1721o {

    /* JADX INFO: renamed from: a */
    private static final String f3918a = "session_start_time";

    /* JADX INFO: renamed from: b */
    private static final String f3919b = "session_end_time";

    /* JADX INFO: renamed from: c */
    private static final String f3920c = "session_id";

    /* JADX INFO: renamed from: f */
    private static String f3921f;

    /* JADX INFO: renamed from: g */
    private static Context f3922g;

    /* JADX INFO: renamed from: d */
    private final String f3923d;

    /* JADX INFO: renamed from: e */
    private final String f3924e;

    private C1721o() {
        this.f3923d = "a_start_time";
        this.f3924e = "a_end_time";
    }

    /* JADX INFO: renamed from: com.umeng.analytics.pro.o$a */
    /* JADX INFO: compiled from: SessionTracker.java */
    private static class a {

        /* JADX INFO: renamed from: a */
        private static final C1721o f3925a = new C1721o();

        private a() {
        }
    }

    /* JADX INFO: renamed from: a */
    public static C1721o m3668a() {
        return a.f3925a;
    }

    /* JADX INFO: renamed from: a */
    public boolean m3674a(Context context) {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
        String string = sharedPreferences.getString(f3920c, null);
        if (string == null) {
            return false;
        }
        long j = sharedPreferences.getLong(f3918a, 0L);
        long j2 = sharedPreferences.getLong(f3919b, 0L);
        if (j2 != 0) {
            int i = (Math.abs(j2 - j) > 86400000L ? 1 : (Math.abs(j2 - j) == 86400000L ? 0 : -1));
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("__ii", string);
            jSONObject.put("__e", j);
            jSONObject.put(C1709c.e.a.f3804g, j2);
            double[] location = AnalyticsConfig.getLocation();
            if (location != null) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("lat", location[0]);
                jSONObject2.put("lng", location[1]);
                jSONObject2.put("ts", System.currentTimeMillis());
                jSONObject.put(C1709c.e.a.f3802e, jSONObject2);
            }
            Class<?> cls = Class.forName("android.net.TrafficStats");
            Method method = cls.getMethod("getUidRxBytes", Integer.TYPE);
            Method method2 = cls.getMethod("getUidTxBytes", Integer.TYPE);
            int i2 = context.getApplicationInfo().uid;
            if (i2 == -1) {
                return false;
            }
            long jLongValue = ((Long) method.invoke(null, Integer.valueOf(i2))).longValue();
            long jLongValue2 = ((Long) method2.invoke(null, Integer.valueOf(i2))).longValue();
            if (jLongValue > 0 && jLongValue2 > 0) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put(C1708b.f3691C, jLongValue);
                jSONObject3.put(C1708b.f3690B, jLongValue2);
                jSONObject.put(C1709c.e.a.f3801d, jSONObject3);
            }
            C1713g.m3553a(context).m3570a(string, jSONObject, C1713g.a.NEWSESSION);
            C1722p.m3684a(f3922g);
            C1714h.m3583b(f3922g);
            try {
                m3670a(sharedPreferences);
                return true;
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
        }
        return false;
    }

    /* JADX INFO: renamed from: a */
    private void m3670a(SharedPreferences sharedPreferences) {
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.remove(f3918a);
        editorEdit.remove(f3919b);
        editorEdit.remove("a_start_time");
        editorEdit.remove("a_end_time");
        editorEdit.commit();
    }

    /* JADX INFO: renamed from: b */
    public String m3676b(Context context) {
        String deviceId = DeviceConfig.getDeviceId(context);
        String appkey = UMUtils.getAppkey(context);
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (appkey == null) {
            throw new RuntimeException("Appkey is null or empty, Please check!");
        }
        f3921f = UMUtils.MD5(jCurrentTimeMillis + appkey + deviceId);
        return f3921f;
    }

    /* JADX INFO: renamed from: a */
    public void m3673a(Context context, Object obj) {
        try {
            if (f3922g == null && context != null) {
                f3922g = context.getApplicationContext();
            }
            long jLongValue = ((Long) obj).longValue();
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f3922g);
            if (sharedPreferences == null) {
                return;
            }
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            String string = sharedPreferences.getString(C1708b.f3727al, "");
            String appVersionName = UMUtils.getAppVersionName(f3922g);
            if (!TextUtils.isEmpty(string) && !string.equals(appVersionName)) {
                int i = sharedPreferences.getInt("versioncode", 0);
                String string2 = sharedPreferences.getString("pre_date", "");
                String string3 = sharedPreferences.getString("pre_version", "");
                String string4 = sharedPreferences.getString(C1708b.f3727al, "");
                editorEdit.putString("vers_date", string2);
                editorEdit.putString("vers_pre_version", string3);
                editorEdit.putString("cur_version", string4);
                editorEdit.putString("dp_vers_date", string2);
                editorEdit.putString("dp_vers_pre_version", string3);
                editorEdit.putString("dp_cur_version", string4);
                editorEdit.putInt("vers_code", i);
                editorEdit.putString("vers_name", string);
                editorEdit.putInt("dp_vers_code", i);
                editorEdit.putString("dp_vers_name", string);
                editorEdit.putLong("a_end_time", 0L);
                editorEdit.commit();
                if (m3681c(context) == null) {
                    f3921f = m3669a(context, sharedPreferences, jLongValue);
                }
                m3675a(f3922g, jLongValue);
                m3677b(f3922g, jLongValue);
                return;
            }
            if (m3671a(sharedPreferences, jLongValue)) {
                f3921f = m3669a(context, sharedPreferences, jLongValue);
                MLog.m4343i("Start new session: " + f3921f);
                return;
            }
            f3921f = sharedPreferences.getString(f3920c, null);
            editorEdit.putLong("a_start_time", jLongValue);
            editorEdit.putLong("a_end_time", 0L);
            editorEdit.commit();
            MLog.m4343i("Extend current session: " + f3921f);
            m3672d(context);
            C1715i.m3588a(f3922g).m3617a(false);
            C1715i.m3588a(f3922g).m3625d();
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: b */
    public void m3678b(Context context, Object obj) {
        try {
            if (f3922g == null && context != null) {
                f3922g = context.getApplicationContext();
            }
            long jLongValue = ((Long) obj).longValue();
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
            if (sharedPreferences == null) {
                return;
            }
            if (sharedPreferences.getLong("a_start_time", 0L) == 0 && AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
                MLog.m4337e("onPause called before onResume");
                return;
            }
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putLong("a_end_time", jLongValue);
            editorEdit.putLong(f3919b, jLongValue);
            editorEdit.commit();
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: b */
    public boolean m3679b() {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f3922g);
        if (sharedPreferences == null) {
            return false;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = sharedPreferences.getLong("a_start_time", 0L);
        return (j == 0 || jCurrentTimeMillis - j >= AnalyticsConfig.kContinueSessionMillis) && jCurrentTimeMillis - sharedPreferences.getLong("a_end_time", 0L) > AnalyticsConfig.kContinueSessionMillis;
    }

    /* JADX INFO: renamed from: a */
    private boolean m3671a(SharedPreferences sharedPreferences, long j) throws Throwable {
        long j2 = sharedPreferences.getLong("a_start_time", 0L);
        long j3 = sharedPreferences.getLong("a_end_time", 0L);
        if (j2 != 0 && j - j2 < AnalyticsConfig.kContinueSessionMillis) {
            MLog.m4337e("onResume called before onPause");
            return false;
        }
        if (j - j3 <= AnalyticsConfig.kContinueSessionMillis) {
            return false;
        }
        try {
            String string = sharedPreferences.getString(f3920c, ScriptUipSetFragment.DISPLAY_TRY_TIME_FLAG);
            long jCurrentTimeMillis = sharedPreferences.getLong(f3919b, 0L);
            if (ScriptUipSetFragment.DISPLAY_TRY_TIME_FLAG.equals(string)) {
                return true;
            }
            if (jCurrentTimeMillis == 0) {
                jCurrentTimeMillis = System.currentTimeMillis();
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(C1709c.e.a.f3804g, jCurrentTimeMillis);
            C1713g.m3553a(f3922g).m3570a(string, jSONObject, C1713g.a.END);
            if (!AnalyticsConfig.FLAG_DPLUS) {
                return true;
            }
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObjectM3501i = C1703b.m3462a().m3501i();
            if (jSONObjectM3501i.length() > 0) {
                jSONObject2.put(C1708b.f3717ab, jSONObjectM3501i);
            }
            jSONObject2.put(C1708b.f3721af, string);
            jSONObject2.put("__ii", string);
            jSONObject2.put(C1708b.f3722ag, jCurrentTimeMillis);
            C1715i.m3588a(f3922g).m3615a(jSONObject2, 3, false);
            return true;
        } catch (Throwable unused) {
            return true;
        }
    }

    /* JADX INFO: renamed from: a */
    private String m3669a(Context context, SharedPreferences sharedPreferences, long j) {
        if (f3922g == null && context != null) {
            f3922g = context.getApplicationContext();
        }
        String strM3676b = m3676b(f3922g);
        try {
            m3672d(context);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("__e", j);
            C1713g.m3553a(f3922g).m3570a(strM3676b, jSONObject, C1713g.a.BEGIN);
            m3674a(f3922g);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putString(f3920c, strM3676b);
            editorEdit.putLong(f3918a, j);
            editorEdit.putLong(f3919b, 0L);
            editorEdit.putLong("a_start_time", j);
            editorEdit.putLong("a_end_time", 0L);
            editorEdit.putInt("versioncode", Integer.parseInt(UMUtils.getAppVersionCode(context)));
            editorEdit.putString(C1708b.f3727al, UMUtils.getAppVersionName(context));
            editorEdit.commit();
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObjectM3501i = C1703b.m3462a().m3501i();
            if (jSONObjectM3501i.length() > 0) {
                jSONObject2.put(C1708b.f3717ab, jSONObjectM3501i);
            }
            jSONObject2.put(C1708b.f3719ad, strM3676b);
            jSONObject2.put("__ii", strM3676b);
            jSONObject2.put(C1708b.f3720ae, j);
            C1715i.m3588a(f3922g).m3624c((Object) jSONObject2);
        } catch (Throwable unused) {
        }
        return strM3676b;
    }

    /* JADX INFO: renamed from: d */
    private void m3672d(Context context) {
        C1715i.m3588a(context).m3621b(context);
        C1715i.m3588a(context).m3612a();
    }

    /* JADX INFO: renamed from: a */
    public boolean m3675a(Context context, long j) {
        String string;
        boolean z = true;
        try {
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
            if (sharedPreferences == null || (string = sharedPreferences.getString(f3920c, null)) == null) {
                return false;
            }
            long j2 = sharedPreferences.getLong("a_start_time", 0L);
            long j3 = sharedPreferences.getLong("a_end_time", 0L);
            if (j2 <= 0 || j3 != 0) {
                z = false;
            } else {
                try {
                    m3678b(f3922g, Long.valueOf(j));
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put(C1709c.e.a.f3804g, j);
                        C1713g.m3553a(context).m3570a(string, jSONObject, C1713g.a.END);
                        C1715i.m3588a(f3922g).m3620b();
                        if (AnalyticsConfig.FLAG_DPLUS) {
                            JSONObject jSONObject2 = new JSONObject();
                            JSONObject jSONObjectM3490c = C1703b.m3462a().m3490c();
                            if (jSONObjectM3490c == null || jSONObjectM3490c.length() < 1) {
                                jSONObjectM3490c = C1703b.m3462a().m3501i();
                            }
                            if (jSONObjectM3490c.length() > 0) {
                                jSONObject2.put(C1708b.f3717ab, jSONObjectM3490c);
                            }
                            jSONObject2.put(C1708b.f3721af, string);
                            jSONObject2.put("__ii", string);
                            jSONObject2.put(C1708b.f3722ag, j);
                            C1715i.m3588a(f3922g).m3615a(jSONObject2, 3, true);
                        }
                    } catch (Throwable unused) {
                    }
                } catch (Throwable unused2) {
                    return z;
                }
            }
            m3674a(context);
            return z;
        } catch (Throwable unused3) {
            return false;
        }
    }

    /* JADX INFO: renamed from: b */
    public void m3677b(Context context, long j) {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
        if (sharedPreferences == null) {
            return;
        }
        f3921f = m3676b(context);
        try {
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putString(f3920c, f3921f);
            editorEdit.putLong(f3918a, j);
            editorEdit.putLong(f3919b, 0L);
            editorEdit.putLong("a_start_time", j);
            editorEdit.putLong("a_end_time", 0L);
            editorEdit.putInt("versioncode", Integer.parseInt(UMUtils.getAppVersionCode(f3922g)));
            editorEdit.putString(C1708b.f3727al, UMUtils.getAppVersionName(f3922g));
            editorEdit.commit();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("__e", j);
            C1713g.m3553a(f3922g).m3570a(f3921f, jSONObject, C1713g.a.BEGIN);
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObjectM3501i = C1703b.m3462a().m3501i();
            if (jSONObjectM3501i.length() > 0) {
                jSONObject2.put(C1708b.f3717ab, jSONObjectM3501i);
            }
            jSONObject2.put(C1708b.f3719ad, f3921f);
            jSONObject2.put("__ii", f3921f);
            jSONObject2.put(C1708b.f3720ae, j);
            C1715i.m3588a(f3922g).m3622b((Object) jSONObject2);
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: c */
    public String m3680c() {
        return f3921f;
    }

    /* JADX INFO: renamed from: c */
    public String m3681c(Context context) {
        try {
            if (f3921f == null) {
                return PreferenceWrapper.getDefault(context).getString(f3920c, null);
            }
        } catch (Throwable unused) {
        }
        return f3921f;
    }

    /* JADX INFO: renamed from: d */
    public String m3682d() {
        return m3681c(f3922g);
    }
}
