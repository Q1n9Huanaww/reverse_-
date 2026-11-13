package com.iflytek.voiceads.dex;

import android.content.Context;
import com.iflytek.collector.p060a.p061a.C1479b;
import com.iflytek.voiceads.config.SDKLogger;
import dalvik.system.PathClassLoader;

/* JADX INFO: loaded from: classes.dex */
public class DexLoader {
    public static void initIFLYADModule(Context context) {
        SDKLogger.m2899d("initIFLYADModule");
        if (context == null) {
            SDKLogger.m2899d("context is null");
            return;
        }
        try {
            C1521a.f3180a = C1521a.m2907a(context);
            SDKLogger.m2899d("loadDex success");
        } catch (Exception e) {
            SDKLogger.m2901e("loadDex error:" + e.getMessage());
            C1521a.f3180a = C1521a.m2913b(context);
        }
        try {
            Object objM2909a = C1521a.m2909a((PathClassLoader) context.getClassLoader());
            C1521a.m2912a(objM2909a, objM2909a.getClass(), "dexElements", C1521a.m2911a(C1521a.m2915b(objM2909a), C1521a.m2915b(C1521a.m2909a(C1521a.f3180a))));
            SDKLogger.m2901e("dex inject success");
        } catch (Exception e2) {
            SDKLogger.m2901e("dex inject error:" + e2.getMessage());
        }
        if (SDKLogger.dev) {
            return;
        }
        C1479b c1479bM2789a = C1479b.m2789a(context);
        c1479bM2789a.m2795a("5c075f2e17");
        c1479bM2789a.m2797b(C1521a.m2916c(context));
    }
}
