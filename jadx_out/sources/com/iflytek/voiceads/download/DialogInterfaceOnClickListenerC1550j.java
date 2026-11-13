package com.iflytek.voiceads.download;

import android.content.DialogInterface;

/* JADX INFO: renamed from: com.iflytek.voiceads.download.j */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class DialogInterfaceOnClickListenerC1550j implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C1541d.a f3286a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ C1541d f3287b;

    DialogInterfaceOnClickListenerC1550j(C1541d c1541d, C1541d.a aVar) {
        this.f3287b = c1541d;
        this.f3286a = aVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.f3286a.mo3002a();
        if (this.f3287b.f3240f != null) {
            this.f3287b.f3240f.onConfirm();
            this.f3287b.f3240f = null;
        }
    }
}
