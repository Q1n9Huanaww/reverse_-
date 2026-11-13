package com.iflytek.voiceads.download;

import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.download.p066a.InterfaceC1528a;
import com.iflytek.voiceads.download.p072e.C1545a;
import com.iflytek.voiceads.utils.C1593g;
import java.io.File;

/* JADX INFO: renamed from: com.iflytek.voiceads.download.n */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class C1554n implements InterfaceC1528a {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ String f3293a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ C1541d f3294b;

    C1554n(C1541d c1541d, String str) {
        this.f3294b = c1541d;
        this.f3293a = str;
    }

    @Override // com.iflytek.voiceads.download.p066a.InterfaceC1528a
    /* JADX INFO: renamed from: a */
    public void mo2944a(int i) {
        this.f3294b.m2969a(i);
    }

    @Override // com.iflytek.voiceads.download.p066a.InterfaceC1528a
    /* JADX INFO: renamed from: a */
    public void mo2945a(long j, long j2, int i) {
        this.f3294b.m2970a(i, (int) ((100 * j) / j2));
    }

    @Override // com.iflytek.voiceads.download.p066a.InterfaceC1528a
    /* JADX INFO: renamed from: a */
    public void mo2946a(C1545a c1545a, int i) {
        C1593g.m3188a(SDKConstants.TAG, "download failed " + c1545a.getMessage());
        this.f3294b.m2986b(i);
        File file = new File(this.f3293a);
        if (file.exists()) {
            file.delete();
        }
    }

    @Override // com.iflytek.voiceads.download.p066a.InterfaceC1528a
    /* JADX INFO: renamed from: b */
    public void mo2947b(int i) {
        this.f3294b.m2986b(i);
        this.f3294b.m2972a(this.f3294b.f3235a, new File(this.f3293a));
    }
}
