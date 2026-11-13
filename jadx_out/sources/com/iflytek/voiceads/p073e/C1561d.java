package com.iflytek.voiceads.p073e;

import android.graphics.Bitmap;
import com.iflytek.voiceads.p063b.C1492a;

/* JADX INFO: renamed from: com.iflytek.voiceads.e.d */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class C1561d implements C1492a.a {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C1558a f3311a;

    C1561d(C1558a c1558a) {
        this.f3311a = c1558a;
    }

    @Override // com.iflytek.voiceads.p063b.C1492a.a
    /* JADX INFO: renamed from: a */
    public void mo2839a(Bitmap bitmap) {
        if (bitmap == null || this.f3311a.f3298a == null) {
            return;
        }
        this.f3311a.f3298a.f3476V.setImageBitmap(bitmap);
    }
}
