package com.umeng.commonsdk.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.umeng.commonsdk.statistics.common.C1817e;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.n */
/* JADX INFO: compiled from: InfoPreference.java */
/* JADX INFO: loaded from: classes.dex */
public class C1791n {

    /* JADX INFO: renamed from: a */
    private static final String f4198a = "info";

    /* JADX INFO: renamed from: b */
    private static final String f4199b = "a_na";

    /* JADX INFO: renamed from: c */
    private static final String f4200c = "a_st";

    /* JADX INFO: renamed from: d */
    private static final String f4201d = "a_ad";

    /* JADX INFO: renamed from: e */
    private static final String f4202e = "blueinfo";

    /* JADX INFO: renamed from: f */
    private static final String f4203f = "a_dc";

    /* JADX INFO: renamed from: g */
    private static final String f4204g = "bssid";

    /* JADX INFO: renamed from: h */
    private static final String f4205h = "ssid";

    /* JADX INFO: renamed from: i */
    private static final String f4206i = "a_fcy";

    /* JADX INFO: renamed from: j */
    private static final String f4207j = "a_hssid";

    /* JADX INFO: renamed from: k */
    private static final String f4208k = "a_ip";

    /* JADX INFO: renamed from: l */
    private static final String f4209l = "a_ls";

    /* JADX INFO: renamed from: m */
    private static final String f4210m = "a_mac";

    /* JADX INFO: renamed from: n */
    private static final String f4211n = "a_nid";

    /* JADX INFO: renamed from: o */
    private static final String f4212o = "rssi";

    /* JADX INFO: renamed from: p */
    private static final String f4213p = "sta";

    /* JADX INFO: renamed from: q */
    private static final String f4214q = "ts";

    /* JADX INFO: renamed from: r */
    private static final String f4215r = "wifiinfo";

    /* JADX INFO: renamed from: s */
    private static final String f4216s = "ua";

    /* JADX INFO: renamed from: a */
    public static JSONObject m4036a(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(f4198a, 0);
        if (sharedPreferences == null) {
            return null;
        }
        try {
            String string = sharedPreferences.getString(f4202e, null);
            if (string != null) {
                return new JSONObject(string);
            }
            return null;
        } catch (Exception e) {
            if (e == null) {
                return null;
            }
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m4037a(Context context, C1787j.b bVar) throws Throwable {
        try {
            SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(f4198a, 0);
            String string = null;
            if (sharedPreferences != null) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(f4199b, bVar.f4169c);
                jSONObject.put(f4200c, bVar.f4168b);
                jSONObject.put(f4201d, bVar.f4167a);
                jSONObject.put("ts", System.currentTimeMillis());
                if (jSONObject != null) {
                    string = jSONObject.toString();
                }
            }
            if (string != null) {
                sharedPreferences.edit().putString(f4202e, string).commit();
            }
        } catch (Exception e) {
            if (e != null) {
                C1817e.m4418e("saveBluetoothInfo:" + e.getMessage());
            }
        }
    }

    /* JADX INFO: renamed from: b */
    public static JSONArray m4040b(Context context) throws Throwable {
        String string;
        try {
            SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(f4198a, 0);
            if (sharedPreferences == null || (string = sharedPreferences.getString(f4215r, null)) == null) {
                return null;
            }
            return new JSONArray(string);
        } catch (Exception e) {
            if (e == null) {
                return null;
            }
            C1817e.m4418e(e.getMessage());
            return null;
        }
    }

    /* JADX INFO: renamed from: c */
    public static void m4041c(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(f4198a, 0);
        if (sharedPreferences != null) {
            sharedPreferences.edit().remove(f4215r).commit();
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m4038a(Context context, C1787j.c cVar) throws Throwable {
        JSONArray jSONArray;
        try {
            SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(f4198a, 0);
            String string = null;
            if (sharedPreferences != null) {
                String string2 = sharedPreferences.getString(f4215r, null);
                if (string2 == null) {
                    jSONArray = new JSONArray();
                } else {
                    jSONArray = new JSONArray(string2);
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(f4203f, cVar.f4170a);
                jSONObject.put(f4204g, cVar.f4171b);
                jSONObject.put(f4205h, cVar.f4172c);
                jSONObject.put(f4206i, cVar.f4173d);
                jSONObject.put(f4207j, cVar.f4174e);
                jSONObject.put(f4208k, cVar.f4175f);
                jSONObject.put(f4209l, cVar.f4176g);
                jSONObject.put(f4210m, cVar.f4177h);
                jSONObject.put(f4211n, cVar.f4178i);
                jSONObject.put(f4212o, cVar.f4179j);
                jSONObject.put(f4213p, cVar.f4180k);
                jSONObject.put("ts", cVar.f4181l);
                jSONArray.put(jSONObject);
                if (jSONObject != null) {
                    string = jSONArray.toString();
                }
            }
            if (string != null) {
                sharedPreferences.edit().putString(f4215r, string).commit();
            }
        } catch (Exception e) {
            if (e != null) {
                C1817e.m4418e(e.getMessage());
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m4039a(Context context, String str) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(f4198a, 0);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString(f4216s, str).commit();
        }
    }

    /* JADX INFO: renamed from: d */
    public static String m4042d(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(f4198a, 0);
        if (sharedPreferences != null) {
            return sharedPreferences.getString(f4216s, null);
        }
        return null;
    }
}
