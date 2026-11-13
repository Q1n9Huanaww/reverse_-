package com.umeng.commonsdk.framework;

import android.content.Context;
import com.umeng.commonsdk.statistics.C1812c;
import com.umeng.commonsdk.statistics.common.C1817e;
import com.umeng.commonsdk.statistics.idtracking.C1826g;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.umeng.commonsdk.framework.a */
/* JADX INFO: compiled from: UMEnvelopeBuildImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class C1731a {
    /* JADX INFO: renamed from: a */
    public static long m3708a(Context context) {
        if (context == null) {
            return 0L;
        }
        return C1732b.m3729i(context.getApplicationContext());
    }

    /* JADX INFO: renamed from: a */
    public static boolean m3711a(Context context, UMLogDataProtocol.UMBusinessType uMBusinessType) {
        boolean z = false;
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            boolean zM3721b = C1732b.m3721b(applicationContext);
            int iM3722c = C1732b.m3722c(applicationContext);
            if (zM3721b && !C1732b.m3716a(applicationContext, uMBusinessType)) {
                z = true;
            }
            if (zM3721b && iM3722c > 0) {
                C1734d.m3745b();
            }
        }
        return z;
    }

    /* JADX INFO: renamed from: a */
    public static JSONObject m3710a(Context context, JSONObject jSONObject, JSONObject jSONObject2) throws Throwable {
        C1817e.m4400b("--->>> buildEnvelopeFile Enter.");
        return new C1812c().m4330a(context.getApplicationContext(), jSONObject, jSONObject2);
    }

    /* JADX INFO: renamed from: a */
    public static String m3709a(Context context, String str, String str2) {
        return context == null ? str2 : C1826g.m4461a(context.getApplicationContext()).m4471b().m4476a(str, str2);
    }

    /* JADX INFO: renamed from: b */
    public static long m3712b(Context context) {
        if (context == null) {
            return 0L;
        }
        return C1812c.m4324a(context.getApplicationContext());
    }
}
