package com.umeng.analytics;

import android.content.Context;
import com.umeng.analytics.pro.C1715i;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CoreProtocol implements UMLogDataProtocol {

    /* JADX INFO: renamed from: a */
    private static Context f3618a;

    private CoreProtocol() {
    }

    /* JADX INFO: renamed from: com.umeng.analytics.CoreProtocol$a */
    private static class C1697a {

        /* JADX INFO: renamed from: a */
        private static final CoreProtocol f3619a = new CoreProtocol();

        private C1697a() {
        }
    }

    public static CoreProtocol getInstance(Context context) {
        if (f3618a == null && context != null) {
            f3618a = context.getApplicationContext();
        }
        return C1697a.f3619a;
    }

    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    public void workEvent(Object obj, int i) throws Throwable {
        C1715i.m3588a(f3618a).m3614a(obj, i);
    }

    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    public void removeCacheData(Object obj) {
        C1715i.m3588a(f3618a).m3613a(obj);
    }

    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    public JSONObject setupReportData(long j) {
        return C1715i.m3588a(f3618a).m3618b(j);
    }
}
