package com.iflytek.voiceads.bridge;

import android.content.DialogInterface;
import android.webkit.JsPromptResult;
import android.widget.EditText;

/* JADX INFO: renamed from: com.iflytek.voiceads.bridge.k */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class DialogInterfaceOnClickListenerC1510k implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ JsPromptResult f3109a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ EditText f3110b;

    /* JADX INFO: renamed from: c */
    final /* synthetic */ C1507h f3111c;

    DialogInterfaceOnClickListenerC1510k(C1507h c1507h, JsPromptResult jsPromptResult, EditText editText) {
        this.f3111c = c1507h;
        this.f3109a = jsPromptResult;
        this.f3110b = editText;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        if (this.f3111c.f3104a.f3078i) {
            if (i == -1) {
                this.f3109a.confirm(this.f3110b.getText().toString());
            } else {
                this.f3109a.cancel();
            }
        }
    }
}
