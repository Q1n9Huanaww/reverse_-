package com.iflytek.collector.p060a.p061a;

import com.iflytek.voiceads.config.SDKLogger;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.iflytek.collector.a.a.e */
/* JADX INFO: loaded from: classes.dex */
class C1482e implements C1484g.a {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ RunnableC1481d f3037a;

    C1482e(RunnableC1481d runnableC1481d) {
        this.f3037a = runnableC1481d;
    }

    @Override // com.iflytek.collector.p060a.p061a.C1484g.a
    /* JADX INFO: renamed from: a */
    public void mo2808a(C1484g c1484g, byte[] bArr) throws Throwable {
        if (bArr != null) {
            try {
                this.f3037a.m2805a(new JSONObject(new String(bArr, "utf-8")));
            } catch (Exception e) {
                mo2809a(e);
            }
        }
    }

    @Override // com.iflytek.collector.p060a.p061a.C1484g.a
    /* JADX INFO: renamed from: a */
    public void mo2809a(Exception exc) {
        SDKLogger.m2902e("Collector", "update dex error:" + exc);
    }
}
