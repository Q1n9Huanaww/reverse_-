package com.p004b.p005a;

import android.support.v7.widget.ActivityChooserView;
import com.p004b.p005a.p006a.C0503c;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: com.b.a.u */
/* JADX INFO: compiled from: Dispatcher.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0624u {

    /* JADX INFO: renamed from: c */
    private Runnable f784c;

    /* JADX INFO: renamed from: d */
    private ExecutorService f785d;

    /* JADX INFO: renamed from: a */
    private int f782a = 64;

    /* JADX INFO: renamed from: b */
    private int f783b = 5;

    /* JADX INFO: renamed from: e */
    private final Deque<C0594an> f786e = new ArrayDeque();

    /* JADX INFO: renamed from: f */
    private final Deque<C0594an> f787f = new ArrayDeque();

    /* JADX INFO: renamed from: g */
    private final Deque<C0593am> f788g = new ArrayDeque();

    /* JADX INFO: renamed from: a */
    private synchronized ExecutorService m492a() {
        if (this.f785d == null) {
            this.f785d = new ThreadPoolExecutor(0, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, 60L, TimeUnit.SECONDS, new SynchronousQueue(), C0503c.m114a("OkHttp Dispatcher", false));
        }
        return this.f785d;
    }

    /* JADX INFO: renamed from: a */
    final synchronized void m498a(C0594an c0594an) {
        if (this.f787f.size() < this.f782a && m496c(c0594an) < this.f783b) {
            this.f787f.add(c0594an);
            m492a().execute(c0594an);
        } else {
            this.f786e.add(c0594an);
        }
    }

    /* JADX INFO: renamed from: b */
    private void m494b() {
        if (this.f787f.size() < this.f782a && !this.f786e.isEmpty()) {
            Iterator<C0594an> it2 = this.f786e.iterator();
            while (it2.hasNext()) {
                C0594an next = it2.next();
                if (m496c(next) < this.f783b) {
                    it2.remove();
                    this.f787f.add(next);
                    m492a().execute(next);
                }
                if (this.f787f.size() >= this.f782a) {
                    return;
                }
            }
        }
    }

    /* JADX INFO: renamed from: c */
    private int m496c(C0594an c0594an) {
        Iterator<C0594an> it2 = this.f787f.iterator();
        int i = 0;
        while (it2.hasNext()) {
            if (it2.next().m397a().equals(c0594an.m397a())) {
                i++;
            }
        }
        return i;
    }

    /* JADX INFO: renamed from: a */
    final synchronized void m497a(C0593am c0593am) {
        this.f788g.add(c0593am);
    }

    /* JADX INFO: renamed from: b */
    final void m500b(C0594an c0594an) {
        m493a(this.f787f, c0594an, true);
    }

    /* JADX INFO: renamed from: b */
    final void m499b(C0593am c0593am) {
        m493a(this.f788g, c0593am, false);
    }

    /* JADX INFO: renamed from: a */
    private <T> void m493a(Deque<T> deque, T t, boolean z) {
        int iM495c;
        Runnable runnable;
        synchronized (this) {
            if (!deque.remove(t)) {
                throw new AssertionError("Call wasn't in-flight!");
            }
            if (z) {
                m494b();
            }
            iM495c = m495c();
            runnable = this.f784c;
        }
        if (iM495c != 0 || runnable == null) {
            return;
        }
        runnable.run();
    }

    /* JADX INFO: renamed from: c */
    private synchronized int m495c() {
        return this.f787f.size() + this.f788g.size();
    }
}
