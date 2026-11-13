package com.p004b.p015b;

/* JADX INFO: renamed from: com.b.b.d */
/* JADX INFO: compiled from: AsyncTimeout.java */
/* JADX INFO: loaded from: classes.dex */
final class C0634d extends Thread {
    public C0634d() {
        super("Okio Watchdog");
        setDaemon(true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0017, code lost:
    
        r1.mo196a();
     */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void run() {
        while (true) {
            try {
                synchronized (C0630a.class) {
                    C0630a c0630aM510e = C0630a.m510e();
                    if (c0630aM510e != null) {
                        if (c0630aM510e == C0630a.f801d) {
                            C0630a.m512g();
                            return;
                        }
                    }
                }
            } catch (InterruptedException unused) {
            }
        }
    }
}
