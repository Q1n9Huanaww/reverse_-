package com.iflytek.voiceads.bridge;

import android.content.DialogInterface;
import android.webkit.JsResult;

/* JADX INFO: renamed from: com.iflytek.voiceads.bridge.i */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class DialogInterfaceOnClickListenerC1508i implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ JsResult f3105a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ C1507h f3106b;

    DialogInterfaceOnClickListenerC1508i(C1507h c1507h, JsResult jsResult) {
        this.f3106b = c1507h;
        this.f3105a = jsResult;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        if (this.f3106b.f3104a.f3078i) {
            this.f3105a.confirm();
        }
    }
}
