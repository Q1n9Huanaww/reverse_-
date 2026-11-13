package com.iflytek.voiceads.view;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.voiceads.config.AdError;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.listener.IFLYAdListener;
import com.iflytek.voiceads.listener.InternalListener;
import com.iflytek.voiceads.utils.C1593g;

/* JADX INFO: renamed from: com.iflytek.voiceads.view.a */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class HandlerC1624a extends Handler {

    /* JADX INFO: renamed from: a */
    private IFLYAdListener f3561a;

    /* JADX INFO: renamed from: b */
    private InternalListener f3562b;

    public HandlerC1624a() {
        super(Looper.getMainLooper());
    }

    /* JADX INFO: renamed from: a */
    public void m3357a(int i, int i2) {
        sendMessageDelayed(obtainMessage(i), i2);
    }

    /* JADX INFO: renamed from: a */
    public void m3358a(int i, Object obj) {
        sendMessage(obtainMessage(i, obj));
    }

    /* JADX INFO: renamed from: a */
    public void m3359a(IFLYAdListener iFLYAdListener) {
        this.f3561a = iFLYAdListener;
    }

    /* JADX INFO: renamed from: a */
    public void m3360a(InternalListener internalListener) {
        this.f3562b = internalListener;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (this.f3561a == null) {
            C1593g.m3190b(SDKConstants.TAG, "htmlAd listener is null");
        }
        switch (message.what) {
            case 0:
                this.f3561a.onAdReceive();
                break;
            case 1:
                this.f3561a.onAdFailed((AdError) message.obj);
                break;
            case 2:
                this.f3561a.onAdClick();
                break;
            case 3:
                this.f3561a.onAdClose();
                break;
            case 4:
                this.f3562b.onAdDestroy();
                break;
        }
    }
}
