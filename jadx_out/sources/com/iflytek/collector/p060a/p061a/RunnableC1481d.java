package com.iflytek.collector.p060a.p061a;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import com.didi.virtualapk.internal.Constants;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.iflytek.voiceads.config.SDKLogger;
import com.umeng.commonsdk.amap.UMAmapConfig;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.iflytek.collector.a.a.d */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1481d implements Runnable {

    /* JADX INFO: renamed from: a */
    protected Context f3034a;

    /* JADX INFO: renamed from: b */
    private C1484g.a f3035b = new C1482e(this);

    /* JADX INFO: renamed from: c */
    private boolean f3036c;

    public RunnableC1481d(Context context, boolean z) {
        this.f3034a = context;
        this.f3036c = z;
    }

    /* JADX INFO: renamed from: a */
    private void m2802a() {
        try {
            SystemClock.sleep(UMAmapConfig.AMAP_CACHE_WRITE_TIME);
            if (m2807b() && C1488k.m2831a(this.f3034a)) {
                C1484g c1484g = new C1484g();
                c1484g.m2825b(20000);
                c1484g.m2821a(1);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("appid", C1479b.m2789a(this.f3034a).m2800e());
                jSONObject.put("ver", C1479b.m2789a(this.f3034a).m2796b());
                c1484g.m2823a("https://logconf.iflytek.com/hotupdate", null, jSONObject.toString().getBytes("utf-8"));
                c1484g.m2826b(this.f3035b);
            }
        } catch (Exception unused) {
            SDKLogger.m2902e("Collector", "exception occur while update dex");
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m2804a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public void m2805a(JSONObject jSONObject) throws Throwable {
        if ("yes".equalsIgnoreCase(jSONObject.optString("update"))) {
            String strOptString = jSONObject.optString(Constants.OPTIMIZE_DIR);
            if (!TextUtils.isEmpty(strOptString)) {
                m2806a(Base64.decode(strOptString, 0));
            }
        } else {
            SDKLogger.m2900d("Collector", "no need to update");
        }
        SharedPreferences.Editor editorEdit = C1488k.m2833b(this.f3034a).edit();
        editorEdit.putLong("last_check_time", System.currentTimeMillis());
        editorEdit.apply();
    }

    /* JADX INFO: renamed from: a */
    private void m2806a(byte[] bArr) throws Throwable {
        FileOutputStream fileOutputStream;
        C1479b c1479bM2789a = C1479b.m2789a(this.f3034a);
        c1479bM2789a.m2799d();
        File file = new File(c1479bM2789a.m2798c());
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        BufferedOutputStream bufferedOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                try {
                    BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(fileOutputStream);
                    try {
                        bufferedOutputStream2.write(bArr);
                        m2804a(bufferedOutputStream2);
                    } catch (IOException e) {
                        e = e;
                        bufferedOutputStream = bufferedOutputStream2;
                        ThrowableExtension.printStackTrace(e);
                        m2804a(bufferedOutputStream);
                    } catch (Throwable th) {
                        th = th;
                        bufferedOutputStream = bufferedOutputStream2;
                        m2804a(bufferedOutputStream);
                        m2804a(fileOutputStream);
                        throw th;
                    }
                } catch (IOException e2) {
                    e = e2;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e3) {
            e = e3;
            fileOutputStream = null;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
        }
        m2804a(fileOutputStream);
    }

    /* JADX INFO: renamed from: b */
    private boolean m2807b() {
        SharedPreferences sharedPreferencesM2833b;
        if (this.f3036c || (sharedPreferencesM2833b = C1488k.m2833b(this.f3034a)) == null) {
            return true;
        }
        return System.currentTimeMillis() - sharedPreferencesM2833b.getLong("last_check_time", 0L) > 604800000;
    }

    @Override // java.lang.Runnable
    public void run() {
        m2802a();
    }
}
