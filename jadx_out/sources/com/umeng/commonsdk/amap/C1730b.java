package com.umeng.commonsdk.amap;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.commonsdk.proguard.C1782e;
import com.umeng.commonsdk.stateless.C1804a;
import com.umeng.commonsdk.stateless.C1809f;
import com.umeng.commonsdk.stateless.UMSLEnvelopeBuild;
import com.umeng.commonsdk.statistics.common.C1817e;
import java.util.Vector;
import net.lingala.zip4j.util.InternalZipConstants;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.umeng.commonsdk.amap.b */
/* JADX INFO: compiled from: UMAmapTimer.java */
/* JADX INFO: loaded from: classes.dex */
public class C1730b extends Thread {

    /* JADX INFO: renamed from: e */
    private static final String f3941e = "info";

    /* JADX INFO: renamed from: f */
    private static final String f3942f = "ts";

    /* JADX INFO: renamed from: g */
    private static final String f3943g = "amap_lbs";

    /* JADX INFO: renamed from: h */
    private static final String f3944h = "tp";

    /* JADX INFO: renamed from: a */
    private Context f3945a;

    /* JADX INFO: renamed from: b */
    private int f3946b;

    /* JADX INFO: renamed from: c */
    private long f3947c;

    /* JADX INFO: renamed from: d */
    private Vector<JSONObject> f3948d;

    /* JADX INFO: renamed from: i */
    private int f3949i = 1;

    /* JADX INFO: renamed from: j */
    private int f3950j = 1;

    public C1730b(Context context, int i, long j) throws Throwable {
        this.f3946b = 0;
        C1817e.m4397a("AmapLBS", "new UMAmapTimer");
        if (context != null) {
            this.f3945a = context.getApplicationContext();
            this.f3946b = i;
            this.f3947c = j;
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() throws Throwable {
        try {
            if (this.f3945a == null) {
                return;
            }
            while (true) {
                if (this.f3946b == 1) {
                    if (C1817e.f4515a) {
                        C1817e.m4397a("AmapLBS", "[lbs-build] timer begin : index is " + this.f3949i);
                        this.f3949i = this.f3949i + 1;
                    }
                    C1729a c1729a = new C1729a(this.f3945a);
                    if (c1729a != null) {
                        byte[] bArrM3706b = c1729a.m3706b();
                        if (bArrM3706b != null) {
                            String strTrim = Base64.encodeToString(bArrM3706b, 0).trim();
                            if (!TextUtils.isEmpty(strTrim)) {
                                JSONObject jSONObject = new JSONObject();
                                try {
                                    jSONObject.put(f3941e, strTrim);
                                    jSONObject.put("ts", System.currentTimeMillis());
                                } catch (Exception unused) {
                                }
                                if (this.f3948d == null) {
                                    this.f3948d = new Vector<>();
                                }
                                this.f3948d.add(jSONObject);
                                if (this.f3948d.size() == 30) {
                                    C1817e.m4397a("AmapLBS", "[lbs-build] begin build envelope , cache size is " + this.f3948d.size());
                                    JSONArray jSONArray = new JSONArray();
                                    for (JSONObject jSONObject2 : this.f3948d) {
                                        if (jSONObject2 != null) {
                                            try {
                                                jSONArray.put(jSONObject2);
                                            } catch (Exception unused2) {
                                            }
                                        }
                                    }
                                    JSONObject jSONObject3 = new JSONObject();
                                    try {
                                        jSONObject3.put(f3943g, jSONArray);
                                    } catch (Exception unused3) {
                                    }
                                    JSONObject jSONObject4 = new JSONObject();
                                    try {
                                        jSONObject4.put(f3944h, jSONObject3);
                                    } catch (Exception unused4) {
                                    }
                                    if (this.f3948d != null) {
                                        this.f3948d.clear();
                                        this.f3948d = null;
                                    }
                                    C1809f.m4310a(this.f3945a, this.f3945a.getFilesDir() + InternalZipConstants.ZIP_FILE_SEPARATOR + C1804a.f4397c + InternalZipConstants.ZIP_FILE_SEPARATOR + Base64.encodeToString(UMAmapConfig.AMAP_CACHE_PATH.getBytes(), 0), 50);
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("[lbs-build] begin build envelope is ");
                                    sb.append(jSONObject4.toString());
                                    C1817e.m4397a("AmapLBS", sb.toString());
                                    UMSLEnvelopeBuild uMSLEnvelopeBuild = new UMSLEnvelopeBuild();
                                    uMSLEnvelopeBuild.buildSLEnvelope(this.f3945a, uMSLEnvelopeBuild.buildSLBaseHeader(this.f3945a), jSONObject4, UMAmapConfig.AMAP_CACHE_PATH);
                                }
                            } else {
                                C1817e.m4397a("AmapLBS", "[lbs-build] amap result is empty");
                            }
                        } else {
                            C1817e.m4397a("AmapLBS", "[lbs-build] amap result is null");
                        }
                    }
                } else if (this.f3946b != 2) {
                    return;
                }
                try {
                    sleep(this.f3947c);
                } catch (Exception e) {
                    C1782e.m3972a(this.f3945a, e);
                }
            }
        } catch (Exception e2) {
            C1782e.m3972a(this.f3945a, e2);
        }
    }
}
