package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;

/* JADX INFO: renamed from: com.umeng.analytics.pro.t */
/* JADX INFO: compiled from: SPHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1726t {

    /* JADX INFO: renamed from: a */
    private static C1726t f3929a = null;

    /* JADX INFO: renamed from: b */
    private static Context f3930b = null;

    /* JADX INFO: renamed from: c */
    private static String f3931c = null;

    /* JADX INFO: renamed from: d */
    private static final String f3932d = "mobclick_agent_user_";

    /* JADX INFO: renamed from: com.umeng.analytics.pro.t$a */
    /* JADX INFO: compiled from: SPHelper.java */
    private static final class a {

        /* JADX INFO: renamed from: a */
        private static final C1726t f3933a = new C1726t();

        private a() {
        }
    }

    /* JADX INFO: renamed from: a */
    public static synchronized C1726t m3692a(Context context) {
        if (f3930b == null && context != null) {
            f3930b = context.getApplicationContext();
        }
        if (f3930b != null) {
            f3931c = context.getPackageName();
        }
        return a.f3933a;
    }

    /* JADX INFO: renamed from: a */
    public void m3696a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        SharedPreferences.Editor editorEdit = m3693e().edit();
        editorEdit.putString("au_p", str);
        editorEdit.putString("au_u", str2);
        editorEdit.commit();
    }

    /* JADX INFO: renamed from: a */
    public String[] m3697a() {
        SharedPreferences sharedPreferencesM3693e = m3693e();
        if (sharedPreferencesM3693e != null) {
            String string = sharedPreferencesM3693e.getString("au_p", null);
            String string2 = sharedPreferencesM3693e.getString("au_u", null);
            if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                return new String[]{string, string2};
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: b */
    public void m3698b() {
        SharedPreferences sharedPreferencesM3693e = m3693e();
        if (sharedPreferencesM3693e != null) {
            sharedPreferencesM3693e.edit().remove("au_p").remove("au_u").commit();
        }
    }

    /* JADX INFO: renamed from: c */
    public String m3699c() {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f3930b);
        if (sharedPreferences != null) {
            return sharedPreferences.getString("st", null);
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    public void m3695a(String str) {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f3930b);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString("st", str).commit();
        }
    }

    /* JADX INFO: renamed from: a */
    public void m3694a(int i) {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f3930b);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putInt("vt", i).commit();
        }
    }

    /* JADX INFO: renamed from: d */
    public int m3700d() {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f3930b);
        if (sharedPreferences != null) {
            return sharedPreferences.getInt("vt", 0);
        }
        return 0;
    }

    /* JADX INFO: renamed from: e */
    private SharedPreferences m3693e() {
        if (f3930b == null) {
            return null;
        }
        return f3930b.getSharedPreferences(f3932d + f3931c, 0);
    }
}
