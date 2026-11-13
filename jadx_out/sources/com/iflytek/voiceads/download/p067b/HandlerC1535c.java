package com.iflytek.voiceads.download.p067b;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.voiceads.download.p066a.InterfaceC1528a;
import com.iflytek.voiceads.download.p071d.C1542a;

/* JADX INFO: renamed from: com.iflytek.voiceads.download.b.c */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class HandlerC1535c extends Handler {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C1533b f3218a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HandlerC1535c(C1533b c1533b, Looper looper) {
        super(looper);
        this.f3218a = c1533b;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        super.handleMessage(message);
        C1542a c1542a = (C1542a) message.obj;
        if (c1542a == null || c1542a.m3018f() == null) {
            return;
        }
        InterfaceC1528a interfaceC1528aM3018f = c1542a.m3018f();
        switch (c1542a.m3019g()) {
            case 1:
                interfaceC1528aM3018f.mo2944a(c1542a.m3020h());
                break;
            case 2:
                interfaceC1528aM3018f.mo2945a(c1542a.m3017e(), c1542a.m3016d(), c1542a.m3020h());
                break;
            case 4:
                interfaceC1528aM3018f.mo2947b(c1542a.m3020h());
                break;
            case 5:
                interfaceC1528aM3018f.mo2946a(c1542a.m3003a(), c1542a.m3020h());
                break;
        }
    }
}
