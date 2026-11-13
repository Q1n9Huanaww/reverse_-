package com.iflytek.voiceads.request;

import com.iflytek.voiceads.listener.DialogListener;

/* JADX INFO: renamed from: com.iflytek.voiceads.request.d */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class C1581d implements DialogListener {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ IFLYBrowser f3396a;

    C1581d(IFLYBrowser iFLYBrowser) {
        this.f3396a = iFLYBrowser;
    }

    @Override // com.iflytek.voiceads.listener.DialogListener
    public void onCancel() {
        this.f3396a.finish();
    }

    @Override // com.iflytek.voiceads.listener.DialogListener
    public void onConfirm() {
        this.f3396a.finish();
    }
}
