package com.iflytek.voiceads.p064c;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.voiceads.config.AdError;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.conn.NativeDataRef;
import com.iflytek.voiceads.listener.IFLYNativeListener;
import com.iflytek.voiceads.utils.C1593g;

/* JADX INFO: renamed from: com.iflytek.voiceads.c.d */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class HandlerC1518d extends Handler {

    /* JADX INFO: renamed from: a */
    private IFLYNativeListener f3130a;

    public HandlerC1518d() {
        super(Looper.getMainLooper());
    }

    /* JADX INFO: renamed from: a */
    public void m2897a(int i, Object obj) {
        sendMessage(obtainMessage(i, obj));
    }

    /* JADX INFO: renamed from: a */
    public void m2898a(IFLYNativeListener iFLYNativeListener) {
        this.f3130a = iFLYNativeListener;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (this.f3130a == null) {
            C1593g.m3190b(SDKConstants.TAG, "native listener is null");
        }
        switch (message.what) {
            case 0:
                this.f3130a.onAdLoaded((NativeDataRef) message.obj);
                break;
            case 1:
                this.f3130a.onAdFailed((AdError) message.obj);
                break;
        }
    }
}
