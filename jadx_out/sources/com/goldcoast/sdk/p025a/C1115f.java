package com.goldcoast.sdk.p025a;

import android.os.Build;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.goldcoast.sdk.a.f */
/* JADX INFO: compiled from: PropInfo.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1115f extends AbstractC1112c<C1115f> {

    /* JADX INFO: renamed from: a */
    private String f1765a;

    /* JADX INFO: renamed from: b */
    private String f1766b;

    /* JADX INFO: renamed from: c */
    private String f1767c;

    /* JADX INFO: renamed from: d */
    private String f1768d;

    /* JADX INFO: renamed from: e */
    private String f1769e;

    public C1115f() {
    }

    public C1115f(String[] strArr) {
        if (strArr != null) {
            Map mapM1382a = m1382a(strArr);
            Iterator it2 = mapM1382a.keySet().iterator();
            if (it2 == null || !it2.hasNext()) {
                return;
            }
            while (it2.hasNext()) {
                String str = (String) it2.next();
                if (str.equals(".vendor")) {
                    this.f1765a = (String) mapM1382a.get(str);
                } else if (str.equals(".product")) {
                    this.f1766b = (String) mapM1382a.get(str);
                } else if (str.equals(".uname_r")) {
                    this.f1767c = (String) mapM1382a.get(str);
                } else if (str.equals(".uname_v")) {
                    this.f1768d = (String) mapM1382a.get(str);
                }
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public static String[] m1383a() {
        String[] strArr = new String[4];
        strArr[0] = ".vendor=" + Build.BRAND;
        strArr[1] = ".product=" + Build.MODEL;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/version"), 8192);
            String line = bufferedReader.readLine();
            String[] strArrSplit = line.split("\\s+");
            strArr[2] = ".uname_v=" + line.substring(line.lastIndexOf("#"), line.length());
            strArr[3] = ".uname_r=" + strArrSplit[2];
            bufferedReader.close();
        } catch (IOException unused) {
        }
        return strArr;
    }

    /* JADX INFO: renamed from: a */
    public final void m1384a(String str) {
        this.f1769e = str;
    }

    /* JADX INFO: renamed from: b */
    public final JSONObject m1385b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(".vendor", this.f1765a);
            jSONObject.put(".product", this.f1766b);
            jSONObject.put(".uname_r", this.f1767c);
            jSONObject.put(".uname_v", this.f1768d);
            jSONObject.put(".uname_v_utc", this.f1769e);
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
        return jSONObject;
    }

    /* JADX INFO: renamed from: a */
    private static Map m1382a(String[] strArr) {
        HashMap map = new HashMap();
        for (String str : strArr) {
            try {
                if (str.contains("=")) {
                    String[] strArrSplit = str.split("=");
                    map.put(strArrSplit[0].trim(), strArrSplit[1].trim());
                }
            } catch (Exception unused) {
            }
        }
        return map;
    }
}
