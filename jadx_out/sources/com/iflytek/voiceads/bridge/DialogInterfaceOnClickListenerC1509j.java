package com.iflytek.voiceads.bridge;

import android.content.DialogInterface;
import android.webkit.JsResult;

/* JADX INFO: renamed from: com.iflytek.voiceads.bridge.j */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class DialogInterfaceOnClickListenerC1509j implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ JsResult f3107a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ C1507h f3108b;

    DialogInterfaceOnClickListenerC1509j(C1507h c1507h, JsResult jsResult) {
        this.f3108b = c1507h;
        this.f3107a = jsResult;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        if (this.f3108b.f3104a.f3078i) {
            if (i == -1) {
                this.f3107a.confirm();
            } else {
                this.f3107a.cancel();
            }
        }
    }
}
