package com.iflytek.voiceads.p064c;

import android.app.Activity;
import com.iflytek.voiceads.config.AdError;
import com.iflytek.voiceads.config.ErrorCode;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.request.C1578a;
import com.iflytek.voiceads.utils.C1593g;

/* JADX INFO: renamed from: com.iflytek.voiceads.c.b */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class C1516b implements C1578a.a {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C1515a f3121a;

    C1516b(C1515a c1515a) {
        this.f3121a = c1515a;
    }

    @Override // com.iflytek.voiceads.request.C1578a.a
    /* JADX INFO: renamed from: a */
    public void mo2892a(Exception exc, int i) {
        this.f3121a.f3120f.m2897a(1, new AdError(i));
    }

    @Override // com.iflytek.voiceads.request.C1578a.a
    /* JADX INFO: renamed from: a */
    public void mo2893a(byte[] bArr) {
        try {
            if ((this.f3121a.f3117c instanceof Activity) && ((Activity) this.f3121a.f3117c).isFinishing()) {
                this.f3121a.f3120f.m2897a(1, new AdError(ErrorCode.ERROR_ACTIVITY_FINISH));
                C1593g.m3188a(SDKConstants.TAG, "activity is finishing");
            } else {
                this.f3121a.f3118d.m2906a(new String(bArr, "utf-8"));
                if (70200 == this.f3121a.f3118d.f3171a) {
                    this.f3121a.f3120f.m2897a(0, new C1517c(this.f3121a.f3117c, this.f3121a.f3118d, this.f3121a.f3116b, this.f3121a.f3119e));
                } else {
                    this.f3121a.f3120f.m2897a(1, new AdError(this.f3121a.f3118d.f3171a));
                }
            }
        } catch (AdError e) {
            this.f3121a.f3120f.m2897a(1, e);
        } catch (Exception e2) {
            this.f3121a.f3120f.m2897a(1, new AdError(ErrorCode.ERROR_NETWORK));
            C1593g.m3190b(SDKConstants.TAG, e2.getMessage());
        }
    }
}
