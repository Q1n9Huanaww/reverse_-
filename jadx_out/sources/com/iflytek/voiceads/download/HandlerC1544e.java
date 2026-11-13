package com.iflytek.voiceads.download;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.utils.C1593g;

/* JADX INFO: renamed from: com.iflytek.voiceads.download.e */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class HandlerC1544e extends Handler {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C1541d f3276a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HandlerC1544e(C1541d c1541d, Looper looper) {
        super(looper);
        this.f3276a = c1541d;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (message.what == 0) {
            try {
                this.f3276a.m2991c();
                C1593g.m3188a(SDKConstants.TAG, "移除广播 end");
            } catch (Exception e) {
                C1593g.m3190b(SDKConstants.TAG, "移除广播 end");
            }
        }
    }
}
