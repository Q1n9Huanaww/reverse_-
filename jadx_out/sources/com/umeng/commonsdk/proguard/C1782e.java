package com.umeng.commonsdk.proguard;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.analytics.pro.C1708b;
import com.umeng.commonsdk.stateless.C1804a;
import com.umeng.commonsdk.stateless.C1809f;
import com.umeng.commonsdk.stateless.UMSLEnvelopeBuild;
import net.lingala.zip4j.util.InternalZipConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.e */
/* JADX INFO: compiled from: UMCrashManager.java */
/* JADX INFO: loaded from: classes.dex */
public class C1782e {
    /* JADX INFO: renamed from: a */
    public static void m3972a(final Context context, final Throwable th) {
        new Thread(new Runnable() { // from class: com.umeng.commonsdk.proguard.e.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    synchronized (this) {
                        if (context != null && th != null) {
                            String strM3973a = C1783f.m3973a(th);
                            if (!TextUtils.isEmpty(strM3973a)) {
                                C1809f.m4310a(context, context.getFilesDir() + InternalZipConstants.ZIP_FILE_SEPARATOR + C1804a.f4397c + InternalZipConstants.ZIP_FILE_SEPARATOR + Base64.encodeToString(C1736a.f3996b.getBytes(), 0), 50);
                                UMSLEnvelopeBuild uMSLEnvelopeBuild = new UMSLEnvelopeBuild();
                                JSONObject jSONObjectBuildSLBaseHeader = uMSLEnvelopeBuild.buildSLBaseHeader(context);
                                try {
                                    JSONObject jSONObject = new JSONObject();
                                    jSONObject.put(C1708b.f3711W, strM3973a);
                                    jSONObject.put("ts", System.currentTimeMillis());
                                    JSONObject jSONObject2 = new JSONObject();
                                    jSONObject2.put("crash", jSONObject);
                                    JSONObject jSONObject3 = new JSONObject();
                                    jSONObject3.put("tp", jSONObject2);
                                    JSONObject jSONObjectBuildSLEnvelope = uMSLEnvelopeBuild.buildSLEnvelope(context, jSONObjectBuildSLBaseHeader, jSONObject3, C1736a.f3995a);
                                    if (jSONObjectBuildSLEnvelope != null) {
                                        jSONObjectBuildSLEnvelope.has(C1708b.f3730ao);
                                    }
                                } catch (JSONException unused) {
                                }
                            }
                        }
                    }
                } catch (Throwable unused2) {
                }
            }
        }).start();
    }
}
