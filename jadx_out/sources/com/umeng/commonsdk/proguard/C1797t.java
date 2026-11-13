package com.umeng.commonsdk.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.os.EnvironmentCompat;
import android.text.TextUtils;
import com.umeng.commonsdk.statistics.common.C1817e;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.t */
/* JADX INFO: compiled from: UMProbe.java */
/* JADX INFO: loaded from: classes.dex */
public class C1797t {

    /* JADX INFO: renamed from: a */
    public static final String f4250a = "UM_PROBE_DATA";

    /* JADX INFO: renamed from: b */
    public static final String f4251b = "_dsk_s";

    /* JADX INFO: renamed from: c */
    public static final String f4252c = "_thm_z";

    /* JADX INFO: renamed from: d */
    public static final String f4253d = "_gdf_r";

    /* JADX INFO: renamed from: e */
    private static Object f4254e = new Object();

    /* JADX INFO: renamed from: a */
    public static String m4097a(Context context) {
        try {
            SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(f4250a, 0);
            if (sharedPreferences == null) {
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            synchronized (f4254e) {
                jSONObject.put(f4251b, sharedPreferences.getString(f4251b, ""));
                jSONObject.put(f4252c, sharedPreferences.getString(f4252c, ""));
                jSONObject.put(f4253d, sharedPreferences.getString(f4253d, ""));
            }
            return jSONObject.toString();
        } catch (Exception e) {
            C1782e.m3972a(context, e);
            return null;
        }
    }

    /* JADX INFO: renamed from: b */
    public static void m4101b(final Context context) {
        if (m4104c(context)) {
            return;
        }
        final String[] strArr = {EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN};
        new Thread() { // from class: com.umeng.commonsdk.proguard.t.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() throws Throwable {
                super.run();
                strArr[0] = C1797t.m4103c();
                strArr[1] = C1797t.m4096a();
                strArr[2] = C1797t.m4100b();
                C1817e.m4406c("diskType = " + strArr[0] + "; ThremalZone = " + strArr[1] + "; GoldFishRc = " + strArr[2]);
                C1797t.m4102b(context, strArr);
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b */
    public static void m4102b(Context context, String[] strArr) {
        SharedPreferences sharedPreferences;
        if (context == null || (sharedPreferences = context.getApplicationContext().getSharedPreferences(f4250a, 0)) == null) {
            return;
        }
        synchronized (f4254e) {
            sharedPreferences.edit().putString(f4251b, strArr[0]).putString(f4252c, strArr[1]).putString(f4253d, strArr[2]).commit();
        }
    }

    /* JADX INFO: renamed from: c */
    public static boolean m4104c(Context context) {
        SharedPreferences sharedPreferences;
        return (context == null || (sharedPreferences = context.getApplicationContext().getSharedPreferences(f4250a, 0)) == null || TextUtils.isEmpty(sharedPreferences.getString(f4251b, ""))) ? false : true;
    }

    /* JADX INFO: renamed from: a */
    public static boolean m4099a(String str, String str2) throws IOException {
        boolean z;
        Process processExec = Runtime.getRuntime().exec(str);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()));
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                z = false;
                break;
            }
            if (line.contains(str2)) {
                z = true;
                break;
            }
        }
        try {
            processExec.waitFor();
        } catch (InterruptedException unused) {
        }
        return z;
    }

    /* JADX INFO: renamed from: a */
    public static String m4096a() {
        boolean zM4099a;
        try {
            zM4099a = m4099a("ls /sys/class/thermal", "thermal_zone");
        } catch (IOException unused) {
            zM4099a = false;
        }
        return zM4099a ? "thermal_zone" : EnvironmentCompat.MEDIA_UNKNOWN;
    }

    /* JADX INFO: renamed from: b */
    public static String m4100b() {
        boolean zM4099a;
        try {
            zM4099a = m4099a("ls /", "goldfish");
        } catch (IOException unused) {
            zM4099a = false;
        }
        return zM4099a ? "goldfish" : EnvironmentCompat.MEDIA_UNKNOWN;
    }

    /* JADX INFO: renamed from: c */
    public static String m4103c() {
        BufferedReader bufferedReader;
        String str;
        String str2 = EnvironmentCompat.MEDIA_UNKNOWN;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/diskstats"));
            if (bufferedReader != null) {
                while (true) {
                    try {
                        String line = bufferedReader.readLine();
                        if (line != null) {
                            if (line.contains("mmcblk")) {
                                str = "mmcblk";
                            } else if (line.contains("sda")) {
                                str = "sda";
                            } else if (line.contains("mtd")) {
                                str = "mtd";
                            }
                            str2 = str;
                            break;
                        }
                        break;
                    } catch (Exception unused) {
                    }
                }
            }
        } catch (Exception unused2) {
            bufferedReader = null;
        }
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (Throwable unused3) {
            }
        }
        return str2;
    }
}
