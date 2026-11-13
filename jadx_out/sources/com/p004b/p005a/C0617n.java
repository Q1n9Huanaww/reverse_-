package com.p004b.p005a;

import android.support.v7.widget.ActivityChooserView;
import com.p004b.p005a.p006a.C0503c;
import com.p004b.p005a.p006a.p008b.C0497c;
import com.p004b.p005a.p006a.p008b.C0498d;
import com.p004b.p005a.p006a.p008b.C0501g;
import com.p004b.p005a.p006a.p008b.C0502h;
import com.p004b.p005a.p006a.p013g.C0573h;
import java.lang.ref.Reference;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: com.b.a.n */
/* JADX INFO: compiled from: ConnectionPool.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0617n {

    /* JADX INFO: renamed from: c */
    static final /* synthetic */ boolean f747c = true;

    /* JADX INFO: renamed from: d */
    private static final Executor f748d = new ThreadPoolExecutor(0, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, 60, TimeUnit.SECONDS, new SynchronousQueue(), C0503c.m114a("OkHttp ConnectionPool", true));

    /* JADX INFO: renamed from: a */
    final C0498d f749a;

    /* JADX INFO: renamed from: b */
    boolean f750b;

    /* JADX INFO: renamed from: e */
    private final int f751e;

    /* JADX INFO: renamed from: f */
    private final long f752f;

    /* JADX INFO: renamed from: g */
    private final Runnable f753g;

    /* JADX INFO: renamed from: h */
    private final Deque<C0497c> f754h;

    public C0617n() {
        this(TimeUnit.MINUTES);
    }

    private C0617n(TimeUnit timeUnit) {
        this.f753g = new RunnableC0618o(this);
        this.f754h = new ArrayDeque();
        this.f749a = new C0498d();
        this.f751e = 5;
        this.f752f = timeUnit.toNanos(5L);
    }

    /* JADX INFO: renamed from: a */
    final C0497c m471a(C0483a c0483a, C0501g c0501g) {
        if (!f747c && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        for (C0497c c0497c : this.f754h) {
            if (c0497c.m72a(c0483a)) {
                c0501g.m96a(c0497c);
                return c0497c;
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: b */
    final Socket m473b(C0483a c0483a, C0501g c0501g) {
        if (!f747c && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        for (C0497c c0497c : this.f754h) {
            if (c0497c.m72a(c0483a) && c0497c.m76d() && c0497c != c0501g.m99b()) {
                return c0501g.m100b(c0497c);
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    final void m472a(C0497c c0497c) {
        if (!f747c && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        if (!this.f750b) {
            this.f750b = true;
            f748d.execute(this.f753g);
        }
        this.f754h.add(c0497c);
    }

    /* JADX INFO: renamed from: b */
    final boolean m474b(C0497c c0497c) {
        if (!f747c && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        if (c0497c.f131a || this.f751e == 0) {
            this.f754h.remove(c0497c);
            return true;
        }
        notifyAll();
        return false;
    }

    /* JADX INFO: renamed from: a */
    final long m470a(long j) {
        int size;
        synchronized (this) {
            long j2 = Long.MIN_VALUE;
            C0497c c0497c = null;
            int i = 0;
            int i2 = 0;
            for (C0497c c0497c2 : this.f754h) {
                List<Reference<C0501g>> list = c0497c2.f134d;
                int i3 = 0;
                while (true) {
                    if (i3 < list.size()) {
                        Reference<C0501g> reference = list.get(i3);
                        if (reference.get() == null) {
                            C0573h.m307b().mo288a("A connection to " + c0497c2.mo68a().f591a.f70a + " was leaked. Did you forget to close a response body?", ((C0502h) reference).f168a);
                            list.remove(i3);
                            c0497c2.f131a = true;
                            if (list.isEmpty()) {
                                c0497c2.f135e = j - this.f752f;
                                size = 0;
                                break;
                            }
                        } else {
                            i3++;
                        }
                    } else {
                        size = list.size();
                        break;
                    }
                }
                if (size > 0) {
                    i2++;
                } else {
                    i++;
                    long j3 = j - c0497c2.f135e;
                    if (j3 > j2) {
                        c0497c = c0497c2;
                        j2 = j3;
                    }
                }
            }
            if (j2 < this.f752f && i <= this.f751e) {
                if (i > 0) {
                    return this.f752f - j2;
                }
                if (i2 > 0) {
                    return this.f752f;
                }
                this.f750b = false;
                return -1L;
            }
            this.f754h.remove(c0497c);
            C0503c.m117a(c0497c.m74b());
            return 0L;
        }
    }
}
