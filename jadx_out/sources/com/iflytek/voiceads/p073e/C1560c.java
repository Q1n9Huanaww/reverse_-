package com.iflytek.voiceads.p073e;

import android.os.Looper;
import com.iflytek.voiceads.config.AdError;
import com.iflytek.voiceads.config.ErrorCode;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.download.C1556p;
import com.iflytek.voiceads.utils.C1593g;

/* JADX INFO: renamed from: com.iflytek.voiceads.e.c */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class C1560c implements C1556p.a {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C1558a f3310a;

    C1560c(C1558a c1558a) {
        this.f3310a = c1558a;
    }

    @Override // com.iflytek.voiceads.download.C1556p.a
    /* JADX INFO: renamed from: a */
    public void mo3033a() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            Looper.prepare();
            C1593g.m3188a(SDKConstants.TAG, "not main thread");
        }
        this.f3310a.f3304g.onAdFailed(new AdError(ErrorCode.ERROR_VIDEO_CACHE));
    }

    @Override // com.iflytek.voiceads.download.C1556p.a
    /* JADX INFO: renamed from: a */
    public void mo3034a(String str) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            Looper.prepare();
            C1593g.m3188a(SDKConstants.TAG, "not main thread");
        }
        this.f3310a.f3308k = str;
        this.f3310a.f3304g.onVideoCached();
    }
}
