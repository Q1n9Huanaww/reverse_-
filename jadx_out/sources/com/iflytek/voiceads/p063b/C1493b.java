package com.iflytek.voiceads.p063b;

import android.graphics.Bitmap;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.utils.C1593g;

/* JADX INFO: renamed from: com.iflytek.voiceads.b.b */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class C1493b implements C1496e.b {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C1492a f3063a;

    C1493b(C1492a c1492a) {
        this.f3063a = c1492a;
    }

    @Override // com.iflytek.voiceads.p063b.C1496e.b
    /* JADX INFO: renamed from: a */
    public void mo2840a(Bitmap bitmap) {
        this.f3063a.f3058b.sendMessage(this.f3063a.f3058b.obtainMessage(1, bitmap));
        this.f3063a.f3059c.m2846a(bitmap);
    }

    @Override // com.iflytek.voiceads.p063b.C1496e.b
    /* JADX INFO: renamed from: a */
    public void mo2841a(Exception exc) {
        this.f3063a.f3058b.sendMessage(this.f3063a.f3058b.obtainMessage(1, null));
        C1593g.m3190b(SDKConstants.TAG, "get image from network failed!");
    }
}
