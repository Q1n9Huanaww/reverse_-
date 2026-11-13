package com.iflytek.voiceads.videolib;

import android.content.DialogInterface;

/* JADX INFO: renamed from: com.iflytek.voiceads.videolib.q */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class DialogInterfaceOnClickListenerC1619q implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ JZVPStandard f3528a;

    DialogInterfaceOnClickListenerC1619q(JZVPStandard jZVPStandard) {
        this.f3528a = jZVPStandard;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
