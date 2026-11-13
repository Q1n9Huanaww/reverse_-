package com.iflytek.voiceads.download.p067b;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.voiceads.download.p070c.InterfaceC1538a;
import com.iflytek.voiceads.download.p071d.C1542a;

/* JADX INFO: renamed from: com.iflytek.voiceads.download.b.b */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1533b implements InterfaceC1531a {

    /* JADX INFO: renamed from: a */
    private Handler f3210a = new HandlerC1535c(this, Looper.getMainLooper());

    /* JADX INFO: renamed from: b */
    private InterfaceC1538a f3211b;

    public C1533b(InterfaceC1538a interfaceC1538a) {
        this.f3211b = interfaceC1538a;
    }

    @Override // com.iflytek.voiceads.download.p067b.InterfaceC1531a
    /* JADX INFO: renamed from: a */
    public void mo2948a(C1542a c1542a) {
        if (c1542a == null) {
            return;
        }
        Message messageObtainMessage = this.f3210a.obtainMessage(c1542a.m3020h());
        messageObtainMessage.obj = c1542a;
        messageObtainMessage.sendToTarget();
        if (c1542a.m3019g() == 4) {
            this.f3211b.mo2963a(c1542a);
        }
    }
}
