package com.umeng.commonsdk.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Looper;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.statistics.common.C1817e;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.h */
/* JADX INFO: compiled from: UMSysLocationCache.java */
/* JADX INFO: loaded from: classes.dex */
public class C1785h {

    /* JADX INFO: renamed from: a */
    public static final String f4156a = "umeng_common_config";

    /* JADX INFO: renamed from: b */
    public static final String f4157b = "location_json";

    /* JADX INFO: renamed from: c */
    public static final String f4158c = "lng";

    /* JADX INFO: renamed from: d */
    public static final String f4159d = "lat";

    /* JADX INFO: renamed from: e */
    public static final String f4160e = "ts";

    /* JADX INFO: renamed from: f */
    private static final String f4161f = "UMSysLocationCache";

    /* JADX INFO: renamed from: a */
    public static void m3982a(final Context context) throws Throwable {
        C1817e.m4397a(f4161f, "begin location");
        if (context == null) {
            return;
        }
        try {
            new Thread(new Runnable() { // from class: com.umeng.commonsdk.proguard.h.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Looper.prepare();
                        final C1784g c1784g = new C1784g(context);
                        c1784g.m3981a(new AbstractC1786i() { // from class: com.umeng.commonsdk.proguard.h.1.1
                            @Override // com.umeng.commonsdk.proguard.AbstractC1786i
                            /* JADX INFO: renamed from: a */
                            public void mo3984a(Location location) throws Throwable {
                                if (location != null) {
                                    double longitude = location.getLongitude();
                                    double latitude = location.getLatitude();
                                    C1817e.m4397a(C1785h.f4161f, "lon is " + longitude + ", lat is " + latitude);
                                    if (longitude != 0.0d && latitude != 0.0d) {
                                        long jCurrentTimeMillis = System.currentTimeMillis();
                                        JSONObject jSONObject = new JSONObject();
                                        try {
                                            jSONObject.put("lng", longitude);
                                            jSONObject.put("lat", latitude);
                                            jSONObject.put("ts", jCurrentTimeMillis);
                                        } catch (JSONException e) {
                                            C1817e.m4397a(C1785h.f4161f, "e is " + e);
                                        }
                                        C1817e.m4397a(C1785h.f4161f, "locationJSONObject is " + jSONObject.toString());
                                        UMWorkDispatch.sendEvent(context, C1736a.f4000f, C1763b.m3919a(context).m3920a(), jSONObject.toString());
                                    }
                                }
                                c1784g.m3980a();
                            }
                        });
                        Looper.loop();
                    } catch (Throwable th) {
                        C1782e.m3972a(context, th);
                    }
                }
            }).start();
        } catch (Exception e) {
            C1817e.m4397a(f4161f, "e is " + e);
            C1782e.m3972a(context, e);
        }
    }

    /* JADX INFO: renamed from: b */
    public static JSONObject m3983b(Context context) throws Throwable {
        SharedPreferences sharedPreferences = context.getSharedPreferences(f4156a, 0);
        JSONObject jSONObject = null;
        if (sharedPreferences == null) {
            return null;
        }
        try {
            String string = sharedPreferences.getString(f4157b, "");
            if (!TextUtils.isEmpty(string)) {
                jSONObject = new JSONObject(string);
            }
        } catch (JSONException e) {
            C1817e.m4397a(f4161f, "e is " + e);
            C1782e.m3972a(context, e);
        } catch (Throwable th) {
            C1817e.m4397a(f4161f, "e is " + th);
            C1782e.m3972a(context, th);
        }
        if (jSONObject != null) {
            C1817e.m4397a(f4161f, "json str is " + jSONObject.toString());
        }
        m3982a(context);
        return jSONObject;
    }
}
