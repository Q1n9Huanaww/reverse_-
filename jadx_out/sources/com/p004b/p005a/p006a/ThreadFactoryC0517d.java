package com.p004b.p005a.p006a;

import java.util.concurrent.ThreadFactory;

/* JADX INFO: renamed from: com.b.a.a.d */
/* JADX INFO: compiled from: Util.java */
/* JADX INFO: loaded from: classes.dex */
final class ThreadFactoryC0517d implements ThreadFactory {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ String f208a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ boolean f209b;

    ThreadFactoryC0517d(String str, boolean z) {
        this.f208a = str;
        this.f209b = z;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, this.f208a);
        thread.setDaemon(this.f209b);
        return thread;
    }
}
