package com.p004b.p005a.p006a.p011e;

import java.util.concurrent.CountDownLatch;

/* JADX INFO: renamed from: com.b.a.a.e.ai */
/* JADX INFO: compiled from: Ping.java */
/* JADX INFO: loaded from: classes.dex */
final class C0534ai {

    /* JADX INFO: renamed from: a */
    private final CountDownLatch f273a = new CountDownLatch(1);

    /* JADX INFO: renamed from: b */
    private long f274b = -1;

    /* JADX INFO: renamed from: c */
    private long f275c = -1;

    C0534ai() {
    }

    /* JADX INFO: renamed from: a */
    final void m216a() {
        if (this.f274b != -1) {
            throw new IllegalStateException();
        }
        this.f274b = System.nanoTime();
    }

    /* JADX INFO: renamed from: b */
    final void m217b() {
        if (this.f275c != -1 || this.f274b == -1) {
            throw new IllegalStateException();
        }
        this.f275c = System.nanoTime();
        this.f273a.countDown();
    }

    /* JADX INFO: renamed from: c */
    final void m218c() {
        if (this.f275c != -1 || this.f274b == -1) {
            throw new IllegalStateException();
        }
        this.f275c = this.f274b - 1;
        this.f273a.countDown();
    }
}
