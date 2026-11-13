package com.iflytek.voiceads.download;

import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.download.p066a.InterfaceC1528a;
import com.iflytek.voiceads.download.p072e.C1545a;
import com.iflytek.voiceads.utils.C1593g;
import java.io.File;

/* JADX INFO: renamed from: com.iflytek.voiceads.download.q */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
final class C1557q implements InterfaceC1528a {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C1556p.a f3296a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ String f3297b;

    C1557q(C1556p.a aVar, String str) {
        this.f3296a = aVar;
        this.f3297b = str;
    }

    @Override // com.iflytek.voiceads.download.p066a.InterfaceC1528a
    /* JADX INFO: renamed from: a */
    public void mo2944a(int i) {
    }

    @Override // com.iflytek.voiceads.download.p066a.InterfaceC1528a
    /* JADX INFO: renamed from: a */
    public void mo2945a(long j, long j2, int i) {
    }

    @Override // com.iflytek.voiceads.download.p066a.InterfaceC1528a
    /* JADX INFO: renamed from: a */
    public void mo2946a(C1545a c1545a, int i) {
        C1593g.m3188a(SDKConstants.TAG, "video cache failed");
        this.f3296a.mo3033a();
        File file = new File(this.f3297b);
        if (file.exists()) {
            file.delete();
        }
    }

    @Override // com.iflytek.voiceads.download.p066a.InterfaceC1528a
    /* JADX INFO: renamed from: b */
    public void mo2947b(int i) {
        C1593g.m3188a(SDKConstants.TAG, "video cache success");
        this.f3296a.mo3034a(this.f3297b);
    }
}
