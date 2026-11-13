package com.umeng.analytics;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.net.xutils.HttpTools;
import com.umeng.analytics.pro.C1708b;
import com.umeng.analytics.pro.C1713g;
import com.umeng.analytics.pro.C1714h;
import com.umeng.analytics.pro.C1715i;
import com.umeng.analytics.pro.C1716j;
import com.umeng.analytics.pro.C1719m;
import com.umeng.analytics.pro.C1721o;
import com.umeng.analytics.pro.C1722p;
import com.umeng.analytics.pro.InterfaceC1720n;
import com.umeng.analytics.pro.InterfaceC1724r;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.utils.UMUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.microedition.khronos.opengles.GL10;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.umeng.analytics.b */
/* JADX INFO: compiled from: InternalAgent.java */
/* JADX INFO: loaded from: classes.dex */
public class C1703b implements InterfaceC1720n {

    /* JADX INFO: renamed from: g */
    private static final String f3630g = "sp";

    /* JADX INFO: renamed from: a */
    private Context f3631a;

    /* JADX INFO: renamed from: b */
    private InterfaceC1724r f3632b;

    /* JADX INFO: renamed from: c */
    private C1716j f3633c;

    /* JADX INFO: renamed from: d */
    private C1722p f3634d;

    /* JADX INFO: renamed from: e */
    private C1721o f3635e;

    /* JADX INFO: renamed from: f */
    private C1714h f3636f;

    /* JADX INFO: renamed from: h */
    private boolean f3637h;

    /* JADX INFO: renamed from: i */
    private volatile JSONObject f3638i;

    /* JADX INFO: renamed from: j */
    private volatile JSONObject f3639j;

    /* JADX INFO: renamed from: k */
    private boolean f3640k;

    private C1703b() {
        this.f3631a = null;
        this.f3633c = new C1716j();
        this.f3634d = new C1722p();
        this.f3635e = C1721o.m3668a();
        this.f3636f = null;
        this.f3637h = false;
        this.f3638i = null;
        this.f3639j = null;
        this.f3640k = false;
        this.f3633c.m3638a(this);
    }

    /* JADX INFO: renamed from: com.umeng.analytics.b$a */
    /* JADX INFO: compiled from: InternalAgent.java */
    private static class a {

        /* JADX INFO: renamed from: a */
        private static final C1703b f3641a = new C1703b();

        private a() {
        }
    }

    /* JADX INFO: renamed from: a */
    public static C1703b m3462a() {
        return a.f3641a;
    }

    /* JADX INFO: renamed from: b */
    public void m3485b() {
        try {
            if (this.f3631a == null) {
                return;
            }
            if (!this.f3637h) {
                this.f3637h = true;
                this.f3638i = m3464f(this.f3631a);
                if (this.f3638i == null) {
                    this.f3638i = new JSONObject();
                }
                this.f3639j = new JSONObject(this.f3638i.toString());
            }
            if (Build.VERSION.SDK_INT > 13) {
                synchronized (this) {
                    if (!this.f3640k && (this.f3631a instanceof Activity)) {
                        this.f3636f = new C1714h((Activity) this.f3631a);
                        this.f3640k = true;
                    }
                }
            } else {
                this.f3640k = true;
            }
            this.f3631a = this.f3631a.getApplicationContext();
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: c */
    public JSONObject m3490c() {
        return this.f3639j;
    }

    /* JADX INFO: renamed from: d */
    public void m3495d() {
        this.f3639j = null;
    }

    /* JADX INFO: renamed from: a */
    void m3480a(String str) {
        if (AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
            return;
        }
        try {
            this.f3634d.m3686a(str);
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: b */
    void m3488b(String str) {
        if (AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
            return;
        }
        try {
            this.f3634d.m3687b(str);
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: a */
    public void m3478a(InterfaceC1724r interfaceC1724r) {
        this.f3632b = interfaceC1724r;
    }

    /* JADX INFO: renamed from: a */
    public void m3468a(Context context, int i) throws Throwable {
        if (context == null) {
            MLog.m4337e("unexpected null context in setVerticalType");
            return;
        }
        if (this.f3631a == null) {
            this.f3631a = context;
        }
        if (!this.f3637h || !this.f3640k) {
            m3485b();
        }
        AnalyticsConfig.m3458a(this.f3631a, i);
    }

    /* JADX INFO: renamed from: a */
    void m3467a(Context context) throws Throwable {
        if (context == null) {
            MLog.m4337e("unexpected null context in onResume");
            return;
        }
        if (this.f3631a == null) {
            this.f3631a = context;
        }
        try {
            if (!this.f3637h || !this.f3640k) {
                m3485b();
            }
            if (AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
                this.f3634d.m3686a(context.getClass().getName());
            }
            m3496e();
        } catch (Throwable th) {
            MLog.m4339e("Exception occurred in Mobclick.onResume(). ", th);
        }
    }

    /* JADX INFO: renamed from: b */
    void m3486b(Context context) throws Throwable {
        if (context == null) {
            MLog.m4337e("unexpected null context in onPause");
            return;
        }
        if (this.f3631a == null) {
            this.f3631a = context;
        }
        try {
            if (!this.f3637h || !this.f3640k) {
                m3485b();
            }
            if (AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
                this.f3634d.m3687b(context.getClass().getName());
            }
            m3498f();
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.m4339e("Exception occurred in Mobclick.onRause(). ", th);
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public void m3473a(Context context, String str, HashMap<String, Object> map) throws Throwable {
        if (context != null) {
            try {
                if (this.f3631a == null) {
                    this.f3631a = context;
                }
            } catch (Throwable th) {
                if (MLog.DEBUG) {
                    MLog.m4341e(th);
                    return;
                }
                return;
            }
        }
        if (!this.f3637h || !this.f3640k) {
            m3485b();
        }
        C1719m.m3652a(this.f3631a).m3664a(str, map);
    }

    /* JADX INFO: renamed from: a */
    void m3470a(Context context, String str) throws Throwable {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (context == null) {
            MLog.m4337e("unexpected null context in reportError");
            return;
        }
        if (this.f3631a == null) {
            this.f3631a = context;
        }
        try {
            if (!this.f3637h || !this.f3640k) {
                m3485b();
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ts", System.currentTimeMillis());
            jSONObject.put(C1708b.f3700L, 2);
            jSONObject.put(C1708b.f3701M, str);
            jSONObject.put("__ii", this.f3635e.m3682d());
            UMWorkDispatch.sendEvent(this.f3631a, C1715i.a.f3885j, CoreProtocol.getInstance(this.f3631a), jSONObject);
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.m4341e(th);
            }
        }
    }

    /* JADX INFO: renamed from: a */
    void m3476a(Context context, Throwable th) throws Throwable {
        if (context == null || th == null) {
            return;
        }
        if (this.f3631a == null) {
            this.f3631a = context;
        }
        try {
            if (!this.f3637h || !this.f3640k) {
                m3485b();
            }
            m3470a(this.f3631a, DataHelper.convertExceptionToString(th));
        } catch (Exception e) {
            if (MLog.DEBUG) {
                MLog.m4341e(e);
            }
        }
    }

    /* JADX INFO: renamed from: e */
    public void m3496e() throws Throwable {
        UMWorkDispatch.sendEvent(this.f3631a, C1715i.a.f3882g, CoreProtocol.getInstance(this.f3631a), Long.valueOf(System.currentTimeMillis()));
        if (this.f3632b != null) {
            this.f3632b.mo3520a();
        }
    }

    /* JADX INFO: renamed from: f */
    public void m3498f() {
        try {
            UMWorkDispatch.sendEvent(this.f3631a, C1715i.a.f3883h, CoreProtocol.getInstance(this.f3631a), Long.valueOf(System.currentTimeMillis()));
            UMWorkDispatch.sendEvent(this.f3631a, C1715i.a.f3879d, CoreProtocol.getInstance(this.f3631a), null);
            UMWorkDispatch.sendEvent(this.f3631a, 4099, CoreProtocol.getInstance(this.f3631a), null);
            UMWorkDispatch.sendEvent(this.f3631a, C1715i.a.f3884i, CoreProtocol.getInstance(this.f3631a), null);
        } catch (Throwable unused) {
        }
        if (this.f3632b != null) {
            this.f3632b.mo3530b();
        }
    }

    /* JADX INFO: renamed from: a */
    public void m3472a(Context context, String str, String str2, long j, int i) throws Throwable {
        if (context != null) {
            try {
                this.f3631a = context;
            } catch (Throwable th) {
                if (MLog.DEBUG) {
                    MLog.m4341e(th);
                    return;
                }
                return;
            }
        }
        if (!this.f3637h || !this.f3640k) {
            m3485b();
        }
        C1719m.m3652a(this.f3631a).m3663a(str, str2, j, i);
    }

    /* JADX INFO: renamed from: a */
    void m3475a(Context context, String str, Map<String, Object> map, long j) throws Throwable {
        if (context != null) {
            try {
                this.f3631a = context;
            } catch (Throwable th) {
                if (MLog.DEBUG) {
                    MLog.m4341e(th);
                    return;
                }
                return;
            }
        }
        if (!this.f3637h || !this.f3640k) {
            m3485b();
        }
        C1719m.m3652a(this.f3631a).m3665a(str, map, j);
    }

    /* JADX INFO: renamed from: a */
    public void m3474a(Context context, String str, Map<String, Object> map) {
        if (context != null) {
            try {
                if (this.f3631a == null) {
                    this.f3631a = context;
                }
            } catch (Throwable unused) {
                return;
            }
        }
        if (!this.f3637h || !this.f3640k) {
            m3485b();
        }
        if (TextUtils.isEmpty(str)) {
            MLog.m4337e("the eventName is empty! please check~");
            return;
        }
        String string = "";
        if (this.f3638i == null) {
            this.f3638i = new JSONObject();
        } else {
            string = this.f3638i.toString();
        }
        UMWorkDispatch.sendEvent(this.f3631a, 8194, CoreProtocol.getInstance(this.f3631a), new C1715i.d(str, map, string, System.currentTimeMillis()));
    }

    /* JADX INFO: renamed from: c */
    void m3491c(Context context) {
        try {
            if (this.f3631a == null && context != null) {
                this.f3631a = context;
            }
            this.f3634d.m3685a();
            this.f3636f.m3587a(this.f3631a);
            this.f3635e.m3678b(this.f3631a, Long.valueOf(System.currentTimeMillis()));
            C1715i.m3588a(this.f3631a).m3612a();
            C1722p.m3684a(this.f3631a);
            C1714h.m3583b(this.f3631a);
            PreferenceWrapper.getDefault(this.f3631a).edit().commit();
            UMWorkDispatch.Quit();
        } catch (Throwable unused) {
        }
    }

    @Override // com.umeng.analytics.pro.InterfaceC1720n
    /* JADX INFO: renamed from: a */
    public void mo3482a(Throwable th) throws Throwable {
        try {
            this.f3634d.m3685a();
            if (this.f3631a != null) {
                this.f3636f.m3587a(this.f3631a);
                this.f3635e.m3678b(this.f3631a, Long.valueOf(System.currentTimeMillis()));
                C1715i.m3588a(this.f3631a).m3612a();
                C1722p.m3684a(this.f3631a);
                C1714h.m3583b(this.f3631a);
                PreferenceWrapper.getDefault(this.f3631a).edit().commit();
                if (th != null) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("ts", System.currentTimeMillis());
                    jSONObject.put(C1708b.f3700L, 1);
                    jSONObject.put(C1708b.f3701M, DataHelper.convertExceptionToString(th));
                    C1713g.m3553a(this.f3631a).m3569a(this.f3635e.m3682d(), jSONObject.toString(), 1);
                }
            }
            UMWorkDispatch.Quit();
        } catch (Exception e) {
            if (MLog.DEBUG) {
                MLog.m4339e("Exception in onAppCrash", e);
            }
        }
    }

    /* JADX INFO: renamed from: a */
    void m3481a(String str, String str2) throws Throwable {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(C1708b.f3696H, str);
            jSONObject.put(HttpTools.JSON_USER_UID, str2);
            UMWorkDispatch.sendEvent(this.f3631a, 4101, CoreProtocol.getInstance(this.f3631a), jSONObject);
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.m4339e(" Excepthon  in  onProfileSignIn", th);
            }
        }
    }

    /* JADX INFO: renamed from: g */
    void m3499g() throws Throwable {
        UMWorkDispatch.sendEvent(this.f3631a, 4102, CoreProtocol.getInstance(this.f3631a), null);
    }

    /* JADX INFO: renamed from: a */
    void m3484a(boolean z) {
        AnalyticsConfig.CATCH_EXCEPTION = z;
    }

    /* JADX INFO: renamed from: a */
    void m3483a(GL10 gl10) {
        String[] gpu = UMUtils.getGPU(gl10);
        if (gpu.length == 2) {
            AnalyticsConfig.GPU_VENDER = gpu[0];
            AnalyticsConfig.GPU_RENDERER = gpu[1];
        }
    }

    /* JADX INFO: renamed from: b */
    void m3489b(boolean z) {
        AnalyticsConfig.ACTIVITY_DURATION_OPEN = z;
    }

    /* JADX INFO: renamed from: a */
    void m3465a(double d, double d2) {
        if (AnalyticsConfig.f3613a == null) {
            AnalyticsConfig.f3613a = new double[2];
        }
        AnalyticsConfig.f3613a[0] = d;
        AnalyticsConfig.f3613a[1] = d2;
    }

    /* JADX INFO: renamed from: a */
    void m3469a(Context context, MobclickAgent.EScenarioType eScenarioType) throws Throwable {
        if (context == null) {
            MLog.m4337e("unexpected null context in setScenarioType");
            return;
        }
        if (this.f3631a == null) {
            this.f3631a = context;
        }
        if (eScenarioType != null) {
            int value = eScenarioType.toValue();
            if (value == MobclickAgent.EScenarioType.E_DUM_NORMAL.toValue() || value == MobclickAgent.EScenarioType.E_DUM_GAME.toValue()) {
                AnalyticsConfig.FLAG_DPLUS = true;
            } else {
                AnalyticsConfig.FLAG_DPLUS = false;
            }
            m3468a(this.f3631a, value);
        }
        if (this.f3637h && this.f3640k) {
            return;
        }
        m3485b();
    }

    /* JADX INFO: renamed from: b */
    void m3487b(Context context, String str) throws Throwable {
        if (context == null) {
            MLog.m4337e("unexpected null context in setSecret");
            return;
        }
        if (this.f3631a == null) {
            this.f3631a = context;
        }
        if (!this.f3637h || !this.f3640k) {
            m3485b();
        }
        AnalyticsConfig.m3459a(this.f3631a, str);
    }

    /* JADX INFO: renamed from: a */
    void m3466a(long j) {
        AnalyticsConfig.kContinueSessionMillis = j;
    }

    /* JADX INFO: renamed from: a */
    public void m3471a(Context context, String str, Object obj) {
        try {
            if (context == null) {
                MLog.m4337e("unexpected null context in registerSuperProperty");
                return;
            }
            if (this.f3631a == null) {
                this.f3631a = context;
            }
            if (!this.f3637h || !this.f3640k) {
                m3485b();
            }
            if (!TextUtils.isEmpty(str) && obj != null) {
                String strSubStr = HelperUtils.subStr(str, 128);
                if (!Arrays.asList(C1708b.f3732aq).contains(strSubStr)) {
                    if (obj instanceof String) {
                        obj = HelperUtils.subStr(obj.toString(), 256);
                    }
                    m3463a(strSubStr, obj);
                    UMWorkDispatch.sendEvent(this.f3631a, C1715i.a.f3888m, CoreProtocol.getInstance(this.f3631a), this.f3638i.toString());
                    return;
                }
                MLog.m4337e("SuperProperty  key is invalid.  ");
                return;
            }
            MLog.m4337e("please check key or value, must not NULL!");
        } catch (Throwable th) {
            ThrowableExtension.printStackTrace(th);
        }
    }

    /* JADX INFO: renamed from: a */
    private void m3463a(String str, Object obj) {
        try {
            if (this.f3638i == null) {
                this.f3638i = new JSONObject();
            }
            new JSONObject();
            int i = 0;
            if (obj.getClass().isArray()) {
                if (obj instanceof String[]) {
                    String[] strArr = (String[]) obj;
                    JSONArray jSONArray = new JSONArray();
                    while (i < strArr.length) {
                        jSONArray.put(strArr[i]);
                        i++;
                    }
                    this.f3638i.put(str, jSONArray);
                    return;
                }
                if (obj instanceof long[]) {
                    long[] jArr = (long[]) obj;
                    JSONArray jSONArray2 = new JSONArray();
                    while (i < jArr.length) {
                        jSONArray2.put(jArr[i]);
                        i++;
                    }
                    this.f3638i.put(str, jSONArray2);
                    return;
                }
                if (obj instanceof int[]) {
                    int[] iArr = (int[]) obj;
                    JSONArray jSONArray3 = new JSONArray();
                    while (i < iArr.length) {
                        jSONArray3.put(iArr[i]);
                        i++;
                    }
                    this.f3638i.put(str, jSONArray3);
                    return;
                }
                if (obj instanceof float[]) {
                    float[] fArr = (float[]) obj;
                    JSONArray jSONArray4 = new JSONArray();
                    while (i < fArr.length) {
                        jSONArray4.put(fArr[i]);
                        i++;
                    }
                    this.f3638i.put(str, jSONArray4);
                    return;
                }
                if (obj instanceof double[]) {
                    double[] dArr = (double[]) obj;
                    JSONArray jSONArray5 = new JSONArray();
                    while (i < dArr.length) {
                        jSONArray5.put(dArr[i]);
                        i++;
                    }
                    this.f3638i.put(str, jSONArray5);
                    return;
                }
                if (obj instanceof short[]) {
                    short[] sArr = (short[]) obj;
                    JSONArray jSONArray6 = new JSONArray();
                    while (i < sArr.length) {
                        jSONArray6.put((int) sArr[i]);
                        i++;
                    }
                    this.f3638i.put(str, jSONArray6);
                    return;
                }
                return;
            }
            if (obj instanceof List) {
                List list = (List) obj;
                JSONArray jSONArray7 = new JSONArray();
                while (i < list.size()) {
                    Object obj2 = list.get(i);
                    if ((obj2 instanceof String) || (obj2 instanceof Long) || (obj2 instanceof Integer) || (obj2 instanceof Float) || (obj2 instanceof Double) || (obj2 instanceof Short)) {
                        jSONArray7.put(list.get(i));
                    }
                    i++;
                }
                this.f3638i.put(str, jSONArray7);
                return;
            }
            if ((obj instanceof String) || (obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Float) || (obj instanceof Double) || (obj instanceof Short)) {
                this.f3638i.put(str, obj);
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: a */
    public void m3479a(Object obj) {
        if (obj != null) {
            try {
                String str = (String) obj;
                SharedPreferences.Editor editorEdit = PreferenceWrapper.getDefault(this.f3631a).edit();
                if (editorEdit == null || TextUtils.isEmpty(str)) {
                    return;
                }
                editorEdit.putString(f3630g, this.f3638i.toString()).commit();
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: renamed from: c */
    public void m3492c(Context context, String str) {
        try {
            if (context == null) {
                MLog.m4337e("unexpected null context in unregisterSuperProperty");
                return;
            }
            if (this.f3631a == null) {
                this.f3631a = context;
            }
            if (!this.f3637h || !this.f3640k) {
                m3485b();
            }
            String strSubStr = HelperUtils.subStr(str, 128);
            if (this.f3638i == null) {
                this.f3638i = new JSONObject();
            }
            if (this.f3638i.has(strSubStr)) {
                this.f3638i.remove(strSubStr);
                UMWorkDispatch.sendEvent(this.f3631a, C1715i.a.f3890o, CoreProtocol.getInstance(this.f3631a), strSubStr);
            }
        } catch (Throwable th) {
            ThrowableExtension.printStackTrace(th);
        }
    }

    /* JADX INFO: renamed from: h */
    public void m3500h() {
        try {
            if (this.f3638i != null) {
                SharedPreferences.Editor editorEdit = PreferenceWrapper.getDefault(this.f3631a).edit();
                editorEdit.putString(f3630g, this.f3638i.toString());
                editorEdit.commit();
            } else {
                this.f3638i = new JSONObject();
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: d */
    public Object m3493d(Context context, String str) {
        try {
            if (context == null) {
                MLog.m4337e("unexpected null context in getSuperProperty");
                return null;
            }
            if (this.f3631a == null) {
                this.f3631a = context;
            }
            if (this.f3638i != null) {
                String strSubStr = HelperUtils.subStr(str, 128);
                if (this.f3638i.has(strSubStr)) {
                    return this.f3638i.opt(strSubStr);
                }
            } else {
                this.f3638i = new JSONObject();
            }
            return null;
        } catch (Throwable th) {
            ThrowableExtension.printStackTrace(th);
        }
    }

    /* JADX INFO: renamed from: d */
    public String m3494d(Context context) {
        try {
            if (context == null) {
                MLog.m4337e("unexpected null context in getSuperProperties");
                return null;
            }
            if (this.f3631a == null) {
                this.f3631a = context;
            }
            if (this.f3638i != null) {
                return this.f3638i.toString();
            }
            this.f3638i = new JSONObject();
            return null;
        } catch (Throwable th) {
            ThrowableExtension.printStackTrace(th);
        }
    }

    /* JADX INFO: renamed from: i */
    public JSONObject m3501i() {
        try {
            if (this.f3638i == null) {
                this.f3638i = new JSONObject();
            }
        } catch (Throwable unused) {
        }
        return this.f3638i;
    }

    /* JADX INFO: renamed from: f */
    private JSONObject m3464f(Context context) {
        try {
            if (context == null) {
                MLog.m4337e("unexpected null context in getNativeSuperProperties");
                return null;
            }
            if (this.f3631a == null) {
                this.f3631a = context;
            }
            String string = PreferenceWrapper.getDefault(context).getString(f3630g, null);
            if (!TextUtils.isEmpty(string)) {
                return new JSONObject(string);
            }
            return null;
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: e */
    public void m3497e(Context context) throws Throwable {
        if (context == null) {
            MLog.m4337e("unexpected null context in clearSuperProperties");
            return;
        }
        if (this.f3631a == null) {
            this.f3631a = context;
        }
        if (!this.f3637h || !this.f3640k) {
            m3485b();
        }
        this.f3638i = new JSONObject();
        UMWorkDispatch.sendEvent(this.f3631a, C1715i.a.f3889n, CoreProtocol.getInstance(this.f3631a), null);
    }

    /* JADX INFO: renamed from: j */
    public void m3502j() {
        try {
            SharedPreferences.Editor editorEdit = PreferenceWrapper.getDefault(this.f3631a).edit();
            editorEdit.remove(f3630g);
            editorEdit.commit();
        } catch (Throwable th) {
            ThrowableExtension.printStackTrace(th);
        }
    }

    /* JADX INFO: renamed from: a */
    public void m3477a(Context context, List<String> list) throws Throwable {
        try {
            if (context == null) {
                MLog.m4337e("unexpected null context in setFirstLaunchEvent");
                return;
            }
            if (this.f3631a == null) {
                this.f3631a = context;
            }
            if (!this.f3637h || !this.f3640k) {
                m3485b();
            }
            C1719m.m3652a(this.f3631a).m3666a(list);
        } catch (Throwable th) {
            MLog.m4341e(th);
        }
    }
}
