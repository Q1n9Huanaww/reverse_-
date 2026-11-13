package com.ime.input;

import android.inputmethodservice.InputMethodService;
import android.view.inputmethod.InputConnection;

/* JADX INFO: renamed from: com.ime.input.a */
/* JADX INFO: compiled from: InputMethodManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1631a {

    /* JADX INFO: renamed from: b */
    private static C1631a f3578b;

    /* JADX INFO: renamed from: a */
    public InputMethodService f3579a = null;

    private C1631a() {
    }

    /* JADX INFO: renamed from: a */
    public static C1631a m3367a() {
        if (f3578b == null) {
            f3578b = new C1631a();
        }
        return f3578b;
    }

    /* JADX INFO: renamed from: a */
    private void m3368a(InputMethodService inputMethodService) {
        this.f3579a = inputMethodService;
    }

    /* JADX INFO: renamed from: b */
    private void m3370b() {
        this.f3579a = null;
    }

    /* JADX INFO: renamed from: a */
    private void m3369a(String str) {
        InputConnection currentInputConnection;
        if (this.f3579a == null || (currentInputConnection = this.f3579a.getCurrentInputConnection()) == null) {
            return;
        }
        currentInputConnection.commitText(str, str.length());
    }

    /* JADX INFO: renamed from: b */
    private void m3371b(String str) {
        if (this.f3579a != null) {
            this.f3579a.switchInputMethod(str);
        }
    }
}
