package com.orhanobut.logger;

/* JADX INFO: loaded from: classes.dex */
public interface Printer {
    /* JADX INFO: renamed from: d */
    void mo3450d(String str, Object... objArr);

    /* JADX INFO: renamed from: e */
    void mo3451e(String str, Object... objArr);

    /* JADX INFO: renamed from: e */
    void mo3452e(Throwable th, String str, Object... objArr);

    Settings getSettings();

    /* JADX INFO: renamed from: i */
    void mo3453i(String str, Object... objArr);

    Settings init(String str);

    void json(String str);

    /* JADX INFO: renamed from: t */
    Printer mo3454t(String str, int i);

    /* JADX INFO: renamed from: v */
    void mo3455v(String str, Object... objArr);

    /* JADX INFO: renamed from: w */
    void mo3456w(String str, Object... objArr);

    void wtf(String str, Object... objArr);

    void xml(String str);
}
