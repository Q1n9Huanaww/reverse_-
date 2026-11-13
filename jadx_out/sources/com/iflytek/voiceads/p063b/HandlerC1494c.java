package com.iflytek.voiceads.p063b;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: renamed from: com.iflytek.voiceads.b.c */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class HandlerC1494c extends Handler {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C1492a f3064a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HandlerC1494c(C1492a c1492a, Looper looper) {
        super(looper);
        this.f3064a = c1492a;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                this.f3064a.f3061e.mo2839a((Bitmap) message.obj);
                break;
        }
    }
}
