package com.umeng.analytics.pro;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.CoreProtocol;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.umeng.analytics.pro.m */
/* JADX INFO: compiled from: EventTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class C1719m {

    /* JADX INFO: renamed from: a */
    private static final String f3910a = "fs_lc_tl";

    /* JADX INFO: renamed from: e */
    private static final String f3911e = "-1";

    /* JADX INFO: renamed from: f */
    private static Context f3912f;

    /* JADX INFO: renamed from: b */
    private final int f3913b;

    /* JADX INFO: renamed from: c */
    private final int f3914c;

    /* JADX INFO: renamed from: d */
    private final int f3915d;

    /* JADX INFO: renamed from: g */
    private JSONObject f3916g;

    private C1719m() {
        this.f3913b = 128;
        this.f3914c = 256;
        this.f3915d = 10;
        this.f3916g = null;
        try {
            if (this.f3916g == null) {
                m3658b(f3912f);
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: com.umeng.analytics.pro.m$a */
    /* JADX INFO: compiled from: EventTracker.java */
    private static class a {

        /* JADX INFO: renamed from: a */
        private static final C1719m f3917a = new C1719m();

        private a() {
        }
    }

    /* JADX INFO: renamed from: a */
    public static C1719m m3652a(Context context) {
        if (f3912f == null && context != null) {
            f3912f = context.getApplicationContext();
        }
        return a.f3917a;
    }

    /* JADX INFO: renamed from: a */
    public void m3665a(String str, Map<String, Object> map, long j) {
        try {
            if (m3657a(str) && m3660b(map)) {
                if (Arrays.asList(C1708b.f3731ap).contains(str)) {
                    MLog.m4337e("the id is valid!");
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("id", str);
                jSONObject.put("ts", System.currentTimeMillis());
                if (j > 0) {
                    jSONObject.put(C1708b.f3706R, j);
                }
                jSONObject.put("__t", C1713g.f3822a);
                Iterator<Map.Entry<String, Object>> it2 = map.entrySet().iterator();
                for (int i = 0; i < 10 && it2.hasNext(); i++) {
                    Map.Entry<String, Object> next = it2.next();
                    if (!Arrays.asList(C1708b.f3731ap).contains(next.getKey())) {
                        Object value = next.getValue();
                        if ((value instanceof String) || (value instanceof Integer) || (value instanceof Long)) {
                            jSONObject.put(next.getKey(), value);
                        }
                    } else {
                        MLog.m4337e("the key in map is invalid.  ");
                    }
                }
                String strM3680c = C1721o.m3668a().m3680c();
                if (TextUtils.isEmpty(strM3680c)) {
                    strM3680c = "-1";
                }
                jSONObject.put("__i", strM3680c);
                UMWorkDispatch.sendEvent(f3912f, 4097, CoreProtocol.getInstance(f3912f), jSONObject);
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: a */
    public void m3663a(String str, String str2, long j, int i) {
        try {
            if (m3657a(str) && m3659b(str2)) {
                if (Arrays.asList(C1708b.f3731ap).contains(str)) {
                    MLog.m4337e("the id is valid!");
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("id", str);
                jSONObject.put("ts", System.currentTimeMillis());
                if (j > 0) {
                    jSONObject.put(C1708b.f3706R, j);
                }
                jSONObject.put("__t", C1713g.f3822a);
                if (str2 == null) {
                    str2 = "";
                }
                jSONObject.put(str, str2);
                String strM3680c = C1721o.m3668a().m3680c();
                if (TextUtils.isEmpty(strM3680c)) {
                    strM3680c = "-1";
                }
                jSONObject.put("__i", strM3680c);
                UMWorkDispatch.sendEvent(f3912f, 4097, CoreProtocol.getInstance(f3912f), jSONObject);
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: a */
    public void m3662a(Object obj) {
        try {
            C1715i.d dVar = (C1715i.d) obj;
            String strM3635c = dVar.m3635c();
            Map<String, Object> mapM3633a = dVar.m3633a();
            String strM3634b = dVar.m3634b();
            long jM3636d = dVar.m3636d();
            if (TextUtils.isEmpty(strM3635c)) {
                return;
            }
            String strSubStr = HelperUtils.subStr(strM3635c, 128);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(C1708b.f3716aa, strSubStr);
            jSONObject.put("_$!ts", jM3636d);
            String strM3680c = C1721o.m3668a().m3680c();
            if (TextUtils.isEmpty(strM3680c)) {
                strM3680c = "-1";
            }
            jSONObject.put("__ii", strM3680c);
            if (!TextUtils.isEmpty(strM3634b)) {
                try {
                    JSONObject jSONObject2 = new JSONObject(strM3634b);
                    if (jSONObject2.length() > 0) {
                        jSONObject.put(C1708b.f3717ab, jSONObject2);
                    }
                } catch (JSONException unused) {
                }
            }
            m3654a();
            if (mapM3633a != null) {
                JSONObject jSONObjectM3653a = m3653a(mapM3633a);
                if (jSONObjectM3653a.length() > 0) {
                    Iterator<String> itKeys = jSONObjectM3653a.keys();
                    while (itKeys.hasNext()) {
                        try {
                            String next = itKeys.next();
                            if (!Arrays.asList(C1708b.f3732aq).contains(next)) {
                                jSONObject.put(next, jSONObjectM3653a.get(next));
                            } else {
                                MLog.m4337e("the key in map about track interface is invalid.  ");
                            }
                        } catch (Throwable th) {
                            MLog.m4341e(th);
                        }
                    }
                }
            }
            if (this.f3916g != null && this.f3916g.has(strSubStr) && !((Boolean) this.f3916g.get(strSubStr)).booleanValue()) {
                jSONObject.put(C1708b.f3708T, 1);
                this.f3916g.put(strSubStr, true);
                m3661c(f3912f);
            }
            C1715i.m3588a(f3912f).m3615a(jSONObject, 0, false);
        } catch (Throwable unused2) {
        }
    }

    /* JADX INFO: renamed from: a */
    public void m3664a(String str, Map<String, Object> map) {
        try {
            if (m3657a(str)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("id", str);
                jSONObject.put("ts", System.currentTimeMillis());
                jSONObject.put(C1708b.f3706R, 0);
                jSONObject.put("__t", C1713g.f3823b);
                Iterator<Map.Entry<String, Object>> it2 = map.entrySet().iterator();
                for (int i = 0; i < 10 && it2.hasNext(); i++) {
                    Map.Entry<String, Object> next = it2.next();
                    if (!C1708b.f3708T.equals(next.getKey()) && !C1708b.f3706R.equals(next.getKey()) && !"id".equals(next.getKey()) && !"ts".equals(next.getKey())) {
                        Object value = next.getValue();
                        if ((value instanceof String) || (value instanceof Integer) || (value instanceof Long)) {
                            jSONObject.put(next.getKey(), value);
                        }
                    }
                }
                String strM3680c = C1721o.m3668a().m3680c();
                if (TextUtils.isEmpty(strM3680c)) {
                    strM3680c = "-1";
                }
                jSONObject.put("__i", strM3680c);
                UMWorkDispatch.sendEvent(f3912f, 4098, CoreProtocol.getInstance(f3912f), jSONObject);
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: b */
    private void m3658b(Context context) throws Throwable {
        try {
            String string = PreferenceWrapper.getDefault(context).getString(f3910a, null);
            if (!TextUtils.isEmpty(string)) {
                this.f3916g = new JSONObject(string);
            }
            m3654a();
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: renamed from: a */
    private void m3654a() throws Throwable {
        try {
            String strImprintProperty = UMEnvelopeBuild.imprintProperty(f3912f, "track_list", "");
            if (TextUtils.isEmpty(strImprintProperty)) {
                return;
            }
            String[] strArrSplit = strImprintProperty.split("!");
            JSONObject jSONObject = new JSONObject();
            int i = 0;
            if (this.f3916g != null) {
                for (String str : strArrSplit) {
                    String strSubStr = HelperUtils.subStr(str, 128);
                    if (this.f3916g.has(strSubStr)) {
                        jSONObject.put(strSubStr, this.f3916g.get(strSubStr));
                    }
                }
            }
            this.f3916g = new JSONObject();
            if (strArrSplit.length >= 10) {
                while (i < 10) {
                    m3655a(strArrSplit[i], jSONObject);
                    i++;
                }
            } else {
                while (i < strArrSplit.length) {
                    m3655a(strArrSplit[i], jSONObject);
                    i++;
                }
            }
            m3661c(f3912f);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: renamed from: a */
    private void m3655a(String str, JSONObject jSONObject) throws Throwable {
        String strSubStr = HelperUtils.subStr(str, 128);
        if (jSONObject.has(strSubStr)) {
            m3656a(strSubStr, ((Boolean) jSONObject.get(strSubStr)).booleanValue());
        } else {
            m3656a(strSubStr, false);
        }
    }

    /* JADX INFO: renamed from: a */
    private void m3656a(String str, boolean z) {
        try {
            if (C1708b.f3708T.equals(str) || C1708b.f3706R.equals(str) || "id".equals(str) || "ts".equals(str) || this.f3916g.has(str)) {
                return;
            }
            this.f3916g.put(str, z);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: renamed from: c */
    private void m3661c(Context context) {
        try {
            if (this.f3916g != null) {
                PreferenceWrapper.getDefault(f3912f).edit().putString(f3910a, this.f3916g.toString()).commit();
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: a */
    public void m3666a(List<String> list) throws Throwable {
        if (list != null) {
            try {
                if (list.size() <= 0) {
                    return;
                }
                m3654a();
                if (this.f3916g == null) {
                    this.f3916g = new JSONObject();
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        if (this.f3916g == null) {
                            this.f3916g = new JSONObject();
                        } else if (this.f3916g.length() >= 5) {
                            break;
                        }
                        String str = list.get(i);
                        if (!TextUtils.isEmpty(str)) {
                            m3656a(HelperUtils.subStr(str, 128), false);
                        }
                    }
                    m3661c(f3912f);
                    return;
                }
                if (this.f3916g.length() >= 5) {
                    MLog.m4331d("already setFistLaunchEvent, igone.");
                    return;
                }
                for (int i2 = 0; i2 < list.size(); i2++) {
                    if (this.f3916g.length() >= 5) {
                        MLog.m4331d(" add setFistLaunchEvent over.");
                        return;
                    }
                    m3656a(HelperUtils.subStr(list.get(i2), 128), false);
                }
                m3661c(f3912f);
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private JSONObject m3653a(Map<String, Object> map) throws Throwable {
        JSONObject jSONObject = new JSONObject();
        try {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                try {
                    String key = entry.getKey();
                    if (key != null) {
                        String strSubStr = HelperUtils.subStr(key, 128);
                        Object value = entry.getValue();
                        if (value != null) {
                            int i = 0;
                            if (value.getClass().isArray()) {
                                if (value instanceof int[]) {
                                    int[] iArr = (int[]) value;
                                    JSONArray jSONArray = new JSONArray();
                                    while (i < iArr.length) {
                                        jSONArray.put(iArr[i]);
                                        i++;
                                    }
                                    jSONObject.put(strSubStr, jSONArray);
                                } else if (value instanceof double[]) {
                                    double[] dArr = (double[]) value;
                                    JSONArray jSONArray2 = new JSONArray();
                                    while (i < dArr.length) {
                                        jSONArray2.put(dArr[i]);
                                        i++;
                                    }
                                    jSONObject.put(strSubStr, jSONArray2);
                                } else if (value instanceof long[]) {
                                    long[] jArr = (long[]) value;
                                    JSONArray jSONArray3 = new JSONArray();
                                    while (i < jArr.length) {
                                        jSONArray3.put(jArr[i]);
                                        i++;
                                    }
                                    jSONObject.put(strSubStr, jSONArray3);
                                } else if (value instanceof float[]) {
                                    float[] fArr = (float[]) value;
                                    JSONArray jSONArray4 = new JSONArray();
                                    while (i < fArr.length) {
                                        jSONArray4.put(fArr[i]);
                                        i++;
                                    }
                                    jSONObject.put(strSubStr, jSONArray4);
                                } else if (value instanceof short[]) {
                                    short[] sArr = (short[]) value;
                                    JSONArray jSONArray5 = new JSONArray();
                                    while (i < sArr.length) {
                                        jSONArray5.put((int) sArr[i]);
                                        i++;
                                    }
                                    jSONObject.put(strSubStr, jSONArray5);
                                }
                            } else if (value instanceof List) {
                                List list = (List) value;
                                JSONArray jSONArray6 = new JSONArray();
                                while (i < list.size()) {
                                    Object obj = list.get(i);
                                    if ((obj instanceof String) || (obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Float) || (obj instanceof Double) || (obj instanceof Short)) {
                                        jSONArray6.put(list.get(i));
                                    }
                                    i++;
                                }
                                if (jSONArray6.length() > 0) {
                                    jSONObject.put(strSubStr, jSONArray6);
                                }
                            } else if (value instanceof String) {
                                jSONObject.put(strSubStr, HelperUtils.subStr(value.toString(), 256));
                            } else if ((value instanceof Long) || (value instanceof Integer) || (value instanceof Float) || (value instanceof Double) || (value instanceof Short)) {
                                jSONObject.put(strSubStr, value);
                            } else {
                                MLog.m4337e("The param has not support type. please check !");
                            }
                        }
                    }
                } catch (Exception e) {
                    MLog.m4341e(e);
                }
            }
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    /* JADX INFO: renamed from: a */
    private boolean m3657a(String str) throws Throwable {
        if (str != null) {
            try {
                int length = str.trim().getBytes().length;
                if (length > 0 && length <= 128) {
                    return true;
                }
            } catch (Exception unused) {
                return false;
            }
        }
        MLog.m4337e("Event id is empty or too long in tracking Event");
        return false;
    }

    /* JADX INFO: renamed from: b */
    private boolean m3659b(String str) throws Throwable {
        if (str == null) {
            return true;
        }
        try {
            if (str.trim().getBytes().length <= 256) {
                return true;
            }
            MLog.m4337e("Event label or value is empty or too long in tracking Event");
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: renamed from: b */
    private boolean m3660b(Map<String, Object> map) throws Throwable {
        if (map != null) {
            try {
                if (!map.isEmpty()) {
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        if (!m3657a(entry.getKey())) {
                            MLog.m4337e("map has NULL key. please check!");
                            return false;
                        }
                        if (entry.getValue() == null) {
                            return false;
                        }
                        if ((entry.getValue() instanceof String) && !m3659b(entry.getValue().toString())) {
                            return false;
                        }
                    }
                    return true;
                }
            } catch (Exception unused) {
                return true;
            }
        }
        MLog.m4337e("map is null or empty in onEvent");
        return false;
    }
}
