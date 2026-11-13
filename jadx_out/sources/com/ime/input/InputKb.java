package com.ime.input;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.KeyboardView;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;

/* JADX INFO: loaded from: classes.dex */
public class InputKb extends InputMethodService {

    /* JADX INFO: renamed from: b */
    public static Handler f3575b;

    /* JADX INFO: renamed from: a */
    EditorInfo f3576a;

    /* JADX INFO: renamed from: c */
    private KeyboardView f3577c;

    @Override // android.inputmethodservice.InputMethodService, android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.i("InputKb", "InputKb - onCreate()");
        C1631a.m3367a().f3579a = this;
    }

    @Override // android.inputmethodservice.InputMethodService
    public View onCreateInputView() {
        return this.f3577c;
    }

    @Override // android.inputmethodservice.InputMethodService
    public boolean onEvaluateFullscreenMode() {
        return (getResources().getConfiguration().orientation != 2 || this.f3576a == null || (this.f3576a.imeOptions & 268435456) == 0) ? false : false;
    }

    @Override // android.inputmethodservice.InputMethodService
    public void onFinishInputView(boolean z) {
        super.onFinishInputView(z);
    }

    @Override // android.inputmethodservice.InputMethodService, android.inputmethodservice.AbstractInputMethodService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        C1631a.m3367a().f3579a = null;
    }
}
