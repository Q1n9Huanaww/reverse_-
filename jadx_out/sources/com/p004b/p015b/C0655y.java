package com.p004b.p015b;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: com.b.b.y */
/* JADX INFO: compiled from: Timeout.java */
/* JADX INFO: loaded from: classes.dex */
public class C0655y {

    /* JADX INFO: renamed from: b */
    public static final C0655y f854b = new C0656z();

    /* JADX INFO: renamed from: a */
    private boolean f855a;

    /* JADX INFO: renamed from: c */
    private long f856c;

    /* JADX INFO: renamed from: d */
    private long f857d;

    /* JADX INFO: renamed from: a */
    public C0655y mo595a(long j, TimeUnit timeUnit) {
        if (j < 0) {
            throw new IllegalArgumentException("timeout < 0: " + j);
        }
        if (timeUnit == null) {
            throw new IllegalArgumentException("unit == null");
        }
        this.f857d = timeUnit.toNanos(j);
        return this;
    }

    /* JADX INFO: renamed from: b_ */
    public long mo596b_() {
        return this.f857d;
    }

    /* JADX INFO: renamed from: c_ */
    public boolean mo597c_() {
        return this.f855a;
    }

    /* JADX INFO: renamed from: d */
    public long mo598d() {
        if (this.f855a) {
            return this.f856c;
        }
        throw new IllegalStateException("No deadline");
    }

    /* JADX INFO: renamed from: a */
    public C0655y mo594a(long j) {
        this.f855a = true;
        this.f856c = j;
        return this;
    }

    /* JADX INFO: renamed from: d_ */
    public C0655y mo599d_() {
        this.f857d = 0L;
        return this;
    }

    /* JADX INFO: renamed from: e_ */
    public C0655y mo600e_() {
        this.f855a = false;
        return this;
    }

    /* JADX INFO: renamed from: f_ */
    public void mo601f_() throws InterruptedIOException {
        if (Thread.interrupted()) {
            throw new InterruptedIOException("thread interrupted");
        }
        if (this.f855a && this.f856c - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }
}
