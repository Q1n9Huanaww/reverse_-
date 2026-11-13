package com.iflytek.collector.p060a.p061a;

import com.iflytek.voiceads.config.SDKLogger;

/* JADX INFO: renamed from: com.iflytek.collector.a.a.j */
/* JADX INFO: loaded from: classes.dex */
class C1487j implements C1484g.a {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ RunnableC1486i f3047a;

    C1487j(RunnableC1486i runnableC1486i) {
        this.f3047a = runnableC1486i;
    }

    @Override // com.iflytek.collector.p060a.p061a.C1484g.a
    /* JADX INFO: renamed from: a */
    public void mo2808a(C1484g c1484g, byte[] bArr) {
        if (bArr != null) {
            try {
                String str = new String(bArr, "utf-8");
                if (C1484g.m2815a(str)) {
                    return;
                }
                SDKLogger.m2900d("Collector", "upload success");
                this.f3047a.m2830a(str);
            } catch (Throwable th) {
                mo2809a(new Exception(th));
            }
        }
    }

    @Override // com.iflytek.collector.p060a.p061a.C1484g.a
    /* JADX INFO: renamed from: a */
    public void mo2809a(Exception exc) {
        SDKLogger.m2902e("Collector", "update error:" + exc);
    }
}
