package com.p004b.p005a.p006a.p011e;

import android.support.v4.media.session.PlaybackStateCompat;
import com.p004b.p015b.C0636f;
import com.p004b.p015b.C0655y;
import com.p004b.p015b.InterfaceC0653w;

/* JADX INFO: renamed from: com.b.a.a.e.ac */
/* JADX INFO: compiled from: Http2Stream.java */
/* JADX INFO: loaded from: classes.dex */
final class C0528ac implements InterfaceC0653w {

    /* JADX INFO: renamed from: c */
    static final /* synthetic */ boolean f246c = true;

    /* JADX INFO: renamed from: a */
    boolean f247a;

    /* JADX INFO: renamed from: b */
    boolean f248b;

    /* JADX INFO: renamed from: d */
    final /* synthetic */ C0527ab f249d;

    /* JADX INFO: renamed from: e */
    private final C0636f f250e = new C0636f();

    C0528ac(C0527ab c0527ab) {
        this.f249d = c0527ab;
    }

    @Override // com.p004b.p015b.InterfaceC0653w
    /* JADX INFO: renamed from: a_ */
    public final void mo167a_(C0636f c0636f, long j) {
        if (!f246c && Thread.holdsLock(this.f249d)) {
            throw new AssertionError();
        }
        this.f250e.mo167a_(c0636f, j);
        while (this.f250e.m540b() >= PlaybackStateCompat.ACTION_PREPARE) {
            m192a(false);
        }
    }

    /* JADX INFO: renamed from: a */
    private void m192a(boolean z) {
        long jMin;
        synchronized (this.f249d) {
            this.f249d.f240g.m516c();
            while (this.f249d.f235b <= 0 && !this.f248b && !this.f247a && this.f249d.f241h == null) {
                try {
                    this.f249d.m191i();
                } catch (Throwable th) {
                    this.f249d.f240g.m197b();
                    throw th;
                }
            }
            this.f249d.f240g.m197b();
            this.f249d.m190h();
            jMin = Math.min(this.f249d.f235b, this.f250e.m540b());
            this.f249d.f235b -= jMin;
        }
        this.f249d.f240g.m516c();
        try {
            this.f249d.f237d.m265a(this.f249d.f236c, z && jMin == this.f250e.m540b(), this.f250e, jMin);
        } finally {
            this.f249d.f240g.m197b();
        }
    }

    @Override // com.p004b.p015b.InterfaceC0653w, java.io.Flushable
    public final void flush() {
        if (!f246c && Thread.holdsLock(this.f249d)) {
            throw new AssertionError();
        }
        synchronized (this.f249d) {
            this.f249d.m190h();
        }
        while (this.f250e.m540b() > 0) {
            m192a(false);
            this.f249d.f237d.f352p.m208b();
        }
    }

    @Override // com.p004b.p015b.InterfaceC0653w
    /* JADX INFO: renamed from: a */
    public final C0655y mo166a() {
        return this.f249d.f240g;
    }

    @Override // com.p004b.p015b.InterfaceC0653w, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (!f246c && Thread.holdsLock(this.f249d)) {
            throw new AssertionError();
        }
        synchronized (this.f249d) {
            if (this.f247a) {
                return;
            }
            if (!this.f249d.f238e.f248b) {
                if (this.f250e.m540b() > 0) {
                    while (this.f250e.m540b() > 0) {
                        m192a(true);
                    }
                } else {
                    this.f249d.f237d.m265a(this.f249d.f236c, true, (C0636f) null, 0L);
                }
            }
            synchronized (this.f249d) {
                this.f247a = true;
            }
            this.f249d.f237d.f352p.m208b();
            this.f249d.m189g();
        }
    }
}
