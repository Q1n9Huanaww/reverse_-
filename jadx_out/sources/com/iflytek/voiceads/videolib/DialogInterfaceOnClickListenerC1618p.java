package com.iflytek.voiceads.videolib;

import android.content.DialogInterface;

/* JADX INFO: renamed from: com.iflytek.voiceads.videolib.p */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class DialogInterfaceOnClickListenerC1618p implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ JZVPStandard f3527a;

    DialogInterfaceOnClickListenerC1618p(JZVPStandard jZVPStandard) {
        this.f3527a = jZVPStandard;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.f3527a.m3254k();
    }
}
