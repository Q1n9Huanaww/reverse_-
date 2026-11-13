package com.iflytek.voiceads.p073e;

import android.app.Activity;
import com.iflytek.voiceads.config.AdError;
import com.iflytek.voiceads.config.ErrorCode;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.request.C1578a;
import com.iflytek.voiceads.utils.C1593g;

/* JADX INFO: renamed from: com.iflytek.voiceads.e.b */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class C1559b implements C1578a.a {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C1558a f3309a;

    C1559b(C1558a c1558a) {
        this.f3309a = c1558a;
    }

    @Override // com.iflytek.voiceads.request.C1578a.a
    /* JADX INFO: renamed from: a */
    public void mo2892a(Exception exc, int i) {
        this.f3309a.f3305h.m3064a(1, new AdError(i));
    }

    @Override // com.iflytek.voiceads.request.C1578a.a
    /* JADX INFO: renamed from: a */
    public void mo2893a(byte[] bArr) {
        try {
            if ((this.f3309a.f3301d instanceof Activity) && ((Activity) this.f3309a.f3301d).isFinishing()) {
                this.f3309a.f3305h.m3064a(1, new AdError(ErrorCode.ERROR_ACTIVITY_FINISH));
                C1593g.m3188a(SDKConstants.TAG, "activity is finishing");
            } else {
                this.f3309a.f3302e.m2906a(new String(bArr, "utf-8"));
                this.f3309a.f3303f = this.f3309a.f3302e.f3176f;
                this.f3309a.f3307j = new C1564g(this.f3309a.f3303f);
                if (70200 == this.f3309a.f3302e.f3171a) {
                    this.f3309a.f3305h.m3064a(0, new C1562e(this.f3309a.f3301d, this.f3309a.f3302e, this.f3309a.f3300c, this.f3309a.f3304g));
                } else {
                    this.f3309a.f3305h.m3064a(1, new AdError(this.f3309a.f3302e.f3171a));
                }
            }
        } catch (AdError e) {
            this.f3309a.f3305h.m3064a(1, e);
        } catch (Exception e2) {
            this.f3309a.f3305h.m3064a(1, new AdError(ErrorCode.ERROR_NETWORK));
        }
    }
}
