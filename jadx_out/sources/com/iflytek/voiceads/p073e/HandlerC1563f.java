package com.iflytek.voiceads.p073e;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.voiceads.config.AdError;
import com.iflytek.voiceads.conn.VideoDataRef;
import com.iflytek.voiceads.listener.IFLYVideoListener;

/* JADX INFO: renamed from: com.iflytek.voiceads.e.f */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class HandlerC1563f extends Handler {

    /* JADX INFO: renamed from: a */
    private IFLYVideoListener f3320a;

    public HandlerC1563f() {
        super(Looper.getMainLooper());
    }

    /* JADX INFO: renamed from: a */
    public void m3064a(int i, Object obj) {
        sendMessage(obtainMessage(i, obj));
    }

    /* JADX INFO: renamed from: a */
    public void m3065a(IFLYVideoListener iFLYVideoListener) {
        this.f3320a = iFLYVideoListener;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        switch (message.what) {
            case 0:
                this.f3320a.onAdLoaded((VideoDataRef) message.obj);
                break;
            case 1:
                this.f3320a.onAdFailed((AdError) message.obj);
                break;
        }
    }
}
