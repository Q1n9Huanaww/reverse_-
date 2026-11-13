package com.iflytek.voiceads.download;

import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.download.p066a.InterfaceC1528a;
import com.iflytek.voiceads.download.p072e.C1545a;
import com.iflytek.voiceads.utils.C1593g;
import java.io.File;

/* JADX INFO: renamed from: com.iflytek.voiceads.download.m */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class C1553m implements InterfaceC1528a {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C1541d.b f3290a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ String f3291b;

    /* JADX INFO: renamed from: c */
    final /* synthetic */ C1541d f3292c;

    C1553m(C1541d c1541d, C1541d.b bVar, String str) {
        this.f3292c = c1541d;
        this.f3290a = bVar;
        this.f3291b = str;
    }

    @Override // com.iflytek.voiceads.download.p066a.InterfaceC1528a
    /* JADX INFO: renamed from: a */
    public void mo2944a(int i) {
        this.f3292c.m2969a(i);
    }

    @Override // com.iflytek.voiceads.download.p066a.InterfaceC1528a
    /* JADX INFO: renamed from: a */
    public void mo2945a(long j, long j2, int i) {
        this.f3292c.m2970a(i, (int) ((100 * j) / j2));
    }

    @Override // com.iflytek.voiceads.download.p066a.InterfaceC1528a
    /* JADX INFO: renamed from: a */
    public void mo2946a(C1545a c1545a, int i) {
        C1593g.m3188a(SDKConstants.TAG, "download failed " + c1545a.getMessage());
        this.f3292c.m2986b(i);
        this.f3292c.f3237c.remove(this.f3290a.f3252b);
        File file = new File(this.f3291b);
        if (file.exists()) {
            file.delete();
        }
    }

    @Override // com.iflytek.voiceads.download.p066a.InterfaceC1528a
    /* JADX INFO: renamed from: b */
    public void mo2947b(int i) {
        this.f3292c.m2985b();
        this.f3292c.m2986b(i);
        this.f3292c.f3237c.remove(this.f3290a.f3252b);
        this.f3292c.f3238d.put(this.f3290a.f3252b, this.f3290a);
        this.f3292c.m2989b(this.f3290a.f3252b);
        this.f3292c.m2994c(this.f3290a.f3252b);
        this.f3292c.m2972a(this.f3292c.f3235a, new File(this.f3291b));
    }
}
