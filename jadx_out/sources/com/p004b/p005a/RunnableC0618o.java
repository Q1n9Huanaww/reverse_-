package com.p004b.p005a;

/* JADX INFO: renamed from: com.b.a.o */
/* JADX INFO: compiled from: ConnectionPool.java */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0618o implements Runnable {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C0617n f755a;

    RunnableC0618o(C0617n c0617n) {
        this.f755a = c0617n;
    }

    @Override // java.lang.Runnable
    public final void run() {
        while (true) {
            long jM470a = this.f755a.m470a(System.nanoTime());
            if (jM470a == -1) {
                return;
            }
            if (jM470a > 0) {
                long j = jM470a / 1000000;
                long j2 = jM470a - (1000000 * j);
                synchronized (this.f755a) {
                    try {
                        this.f755a.wait(j, (int) j2);
                    } catch (InterruptedException unused) {
                    }
                }
            }
        }
    }
}
