package com.p004b.p015b;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: com.b.b.a */
/* JADX INFO: compiled from: AsyncTimeout.java */
/* JADX INFO: loaded from: classes.dex */
public class C0630a extends C0655y {

    /* JADX INFO: renamed from: a */
    private static final long f799a = TimeUnit.SECONDS.toMillis(60);

    /* JADX INFO: renamed from: c */
    private static final long f800c = TimeUnit.MILLISECONDS.toNanos(f799a);

    /* JADX INFO: renamed from: d */
    private static C0630a f801d;

    /* JADX INFO: renamed from: e */
    private boolean f802e;

    /* JADX INFO: renamed from: f */
    private C0630a f803f;

    /* JADX INFO: renamed from: g */
    private long f804g;

    /* JADX INFO: renamed from: a */
    public void mo196a() {
    }

    /* JADX INFO: renamed from: g */
    static /* synthetic */ C0630a m512g() {
        f801d = null;
        return null;
    }

    /* JADX INFO: renamed from: c */
    public final void m516c() {
        if (this.f802e) {
            throw new IllegalStateException("Unbalanced enter/exit");
        }
        long jB_ = mo596b_();
        boolean zC_ = mo597c_();
        if (jB_ != 0 || zC_) {
            this.f802e = true;
            m508a(this, jB_, zC_);
        }
    }

    /* JADX INFO: renamed from: a */
    private static synchronized void m508a(C0630a c0630a, long j, boolean z) {
        if (f801d == null) {
            f801d = new C0630a();
            new C0634d().start();
        }
        long jNanoTime = System.nanoTime();
        if (j != 0 && z) {
            c0630a.f804g = jNanoTime + Math.min(j, c0630a.mo598d() - jNanoTime);
        } else if (j != 0) {
            c0630a.f804g = jNanoTime + j;
        } else if (z) {
            c0630a.f804g = c0630a.mo598d();
        } else {
            throw new AssertionError();
        }
        long j2 = c0630a.f804g - jNanoTime;
        C0630a c0630a2 = f801d;
        while (c0630a2.f803f != null && j2 >= c0630a2.f803f.f804g - jNanoTime) {
            c0630a2 = c0630a2.f803f;
        }
        c0630a.f803f = c0630a2.f803f;
        c0630a2.f803f = c0630a;
        if (c0630a2 == f801d) {
            C0630a.class.notify();
        }
    }

    /* JADX INFO: renamed from: a_ */
    public final boolean m514a_() {
        if (!this.f802e) {
            return false;
        }
        this.f802e = false;
        return m509a(this);
    }

    /* JADX INFO: renamed from: a */
    private static synchronized boolean m509a(C0630a c0630a) {
        for (C0630a c0630a2 = f801d; c0630a2 != null; c0630a2 = c0630a2.f803f) {
            if (c0630a2.f803f == c0630a) {
                c0630a2.f803f = c0630a.f803f;
                c0630a.f803f = null;
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: renamed from: a */
    final void m513a(boolean z) throws IOException {
        if (m514a_() && z) {
            throw mo195a((IOException) null);
        }
    }

    /* JADX INFO: renamed from: b */
    final IOException m515b(IOException iOException) {
        return !m514a_() ? iOException : mo195a(iOException);
    }

    /* JADX INFO: renamed from: a */
    public IOException mo195a(IOException iOException) {
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    /* JADX INFO: renamed from: e */
    static C0630a m510e() throws InterruptedException {
        C0630a c0630a = f801d.f803f;
        if (c0630a == null) {
            long jNanoTime = System.nanoTime();
            C0630a.class.wait(f799a);
            if (f801d.f803f != null || System.nanoTime() - jNanoTime < f800c) {
                return null;
            }
            return f801d;
        }
        long jNanoTime2 = c0630a.f804g - System.nanoTime();
        if (jNanoTime2 > 0) {
            long j = jNanoTime2 / 1000000;
            C0630a.class.wait(j, (int) (jNanoTime2 - (1000000 * j)));
            return null;
        }
        f801d.f803f = c0630a.f803f;
        c0630a.f803f = null;
        return c0630a;
    }
}
