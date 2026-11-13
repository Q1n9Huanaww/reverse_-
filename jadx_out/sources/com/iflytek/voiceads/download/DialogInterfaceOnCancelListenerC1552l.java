package com.iflytek.voiceads.download;

import android.content.DialogInterface;

/* JADX INFO: renamed from: com.iflytek.voiceads.download.l */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class DialogInterfaceOnCancelListenerC1552l implements DialogInterface.OnCancelListener {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C1541d f3289a;

    DialogInterfaceOnCancelListenerC1552l(C1541d c1541d) {
        this.f3289a = c1541d;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        dialogInterface.dismiss();
    }
}
