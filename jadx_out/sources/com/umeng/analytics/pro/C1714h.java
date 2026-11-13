package com.umeng.analytics.pro;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.p024ds.daisi.fragment.ScriptUipSetFragment;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.C1703b;
import com.umeng.analytics.CoreProtocol;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.umeng.analytics.pro.h */
/* JADX INFO: compiled from: AutoViewPageTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class C1714h {

    /* JADX INFO: renamed from: a */
    public static String f3839a;

    /* JADX INFO: renamed from: f */
    private static JSONObject f3840f = new JSONObject();

    /* JADX INFO: renamed from: g */
    private Application f3845g;

    /* JADX INFO: renamed from: e */
    private final Map<String, Long> f3844e = new HashMap();

    /* JADX INFO: renamed from: b */
    boolean f3841b = false;

    /* JADX INFO: renamed from: c */
    boolean f3842c = false;

    /* JADX INFO: renamed from: d */
    Application.ActivityLifecycleCallbacks f3843d = new Application.ActivityLifecycleCallbacks() { // from class: com.umeng.analytics.pro.h.1
        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) throws Throwable {
            if (!C1714h.this.f3842c) {
                C1714h.this.m3582b(activity);
                return;
            }
            C1714h.this.f3842c = false;
            if (C1714h.f3839a.equals(activity.getPackageName() + "." + activity.getLocalClassName())) {
                return;
            }
            C1714h.this.m3582b(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            C1714h.this.m3585c(activity);
            C1714h.this.f3842c = false;
        }
    };

    public C1714h(Activity activity) {
        this.f3845g = null;
        synchronized (this) {
            if (this.f3845g == null && activity != null) {
                this.f3845g = activity.getApplication();
                m3580a(activity);
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private void m3580a(Activity activity) throws Throwable {
        if (this.f3841b) {
            return;
        }
        this.f3841b = true;
        this.f3845g.registerActivityLifecycleCallbacks(this.f3843d);
        if (f3839a == null) {
            this.f3842c = true;
            m3582b(activity);
        }
    }

    /* JADX INFO: renamed from: a */
    public void m3586a() {
        this.f3841b = false;
        if (this.f3845g != null) {
            this.f3845g.unregisterActivityLifecycleCallbacks(this.f3843d);
            this.f3845g = null;
        }
    }

    /* JADX INFO: renamed from: a */
    public void m3587a(Context context) {
        m3585c(null);
        m3586a();
    }

    /* JADX INFO: renamed from: b */
    public static void m3583b(Context context) {
        try {
            synchronized (f3840f) {
                if (context != null) {
                    try {
                        if (f3840f.length() > 0) {
                            C1713g.m3553a(context).m3570a(C1721o.m3668a().m3682d(), f3840f, C1713g.a.AUTOPAGE);
                            f3840f = new JSONObject();
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b */
    public void m3582b(Activity activity) throws Throwable {
        f3839a = activity.getPackageName() + "." + activity.getLocalClassName();
        if (AnalyticsConfig.FLAG_DPLUS) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(C1708b.f3724ai, f3839a);
                jSONObject.put("_$!ts", System.currentTimeMillis());
                JSONObject jSONObjectM3501i = C1703b.m3462a().m3501i();
                if (jSONObjectM3501i.length() > 0) {
                    jSONObject.put(C1708b.f3717ab, jSONObjectM3501i);
                }
                String strM3680c = C1721o.m3668a().m3680c();
                if (TextUtils.isEmpty(strM3680c)) {
                    strM3680c = ScriptUipSetFragment.DISPLAY_TRY_TIME_FLAG;
                }
                jSONObject.put("__ii", strM3680c);
                if (C1721o.m3668a().m3679b()) {
                    jSONObject.put("__ii", ScriptUipSetFragment.DISPLAY_TRY_TIME_FLAG);
                }
                UMWorkDispatch.sendEvent(activity.getApplicationContext(), C1715i.a.f3886k, CoreProtocol.getInstance(activity.getApplicationContext()), jSONObject);
            } catch (JSONException unused) {
            }
        }
        synchronized (this.f3844e) {
            this.f3844e.put(f3839a, Long.valueOf(System.currentTimeMillis()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: c */
    public void m3585c(Activity activity) {
        long j = 0;
        try {
            synchronized (this.f3844e) {
                if (this.f3844e.containsKey(f3839a)) {
                    long jCurrentTimeMillis = System.currentTimeMillis() - this.f3844e.get(f3839a).longValue();
                    this.f3844e.remove(f3839a);
                    j = jCurrentTimeMillis;
                }
            }
            synchronized (f3840f) {
                try {
                    f3840f = new JSONObject();
                    f3840f.put(C1708b.f3752u, f3839a);
                    f3840f.put("duration", j);
                } catch (Throwable unused) {
                }
            }
        } catch (Throwable unused2) {
        }
    }
}
