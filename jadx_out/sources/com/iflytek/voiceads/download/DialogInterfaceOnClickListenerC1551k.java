package com.iflytek.voiceads.download;

import android.content.DialogInterface;

/* JADX INFO: renamed from: com.iflytek.voiceads.download.k */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class DialogInterfaceOnClickListenerC1551k implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C1541d f3288a;

    DialogInterfaceOnClickListenerC1551k(C1541d c1541d) {
        this.f3288a = c1541d;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        if (this.f3288a.f3240f != null) {
            this.f3288a.f3240f.onCancel();
            this.f3288a.f3240f = null;
        }
    }
}
