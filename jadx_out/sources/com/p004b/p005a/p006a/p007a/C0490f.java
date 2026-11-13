package com.p004b.p005a.p006a.p007a;

import com.p004b.p005a.p006a.p012f.InterfaceC0564a;
import com.p004b.p015b.InterfaceC0637g;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.concurrent.Executor;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: com.b.a.a.a.f */
/* JADX INFO: compiled from: DiskLruCache.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0490f implements Closeable, Flushable {

    /* JADX INFO: renamed from: a */
    static final Pattern f102a = Pattern.compile("[a-z0-9_-]{1,120}");

    /* JADX INFO: renamed from: j */
    static final /* synthetic */ boolean f103j = true;

    /* JADX INFO: renamed from: b */
    final InterfaceC0564a f104b;

    /* JADX INFO: renamed from: c */
    final int f105c;

    /* JADX INFO: renamed from: d */
    InterfaceC0637g f106d;

    /* JADX INFO: renamed from: e */
    final LinkedHashMap<String, C0492h> f107e;

    /* JADX INFO: renamed from: f */
    int f108f;

    /* JADX INFO: renamed from: g */
    boolean f109g;

    /* JADX INFO: renamed from: h */
    boolean f110h;

    /* JADX INFO: renamed from: i */
    boolean f111i;

    /* JADX INFO: renamed from: k */
    private long f112k;

    /* JADX INFO: renamed from: l */
    private long f113l;

    /* JADX INFO: renamed from: m */
    private final Executor f114m;

    /* JADX INFO: renamed from: n */
    private final Runnable f115n;

    /* JADX INFO: renamed from: a */
    final synchronized void m57a(C0491g c0491g) {
        C0492h c0492h = c0491g.f116a;
        if (c0492h.f124f != c0491g) {
            throw new IllegalStateException();
        }
        for (int i = 0; i < this.f105c; i++) {
            this.f104b.mo282a(c0492h.f122d[i]);
        }
        this.f108f++;
        c0492h.f124f = null;
        if (false | c0492h.f123e) {
            c0492h.f123e = true;
            this.f106d.mo542b("CLEAN").mo562h(32);
            this.f106d.mo542b(c0492h.f119a);
            c0492h.m59a(this.f106d);
            this.f106d.mo562h(10);
        } else {
            this.f107e.remove(c0492h.f119a);
            this.f106d.mo542b("REMOVE").mo562h(32);
            this.f106d.mo542b(c0492h.f119a);
            this.f106d.mo562h(10);
        }
        this.f106d.flush();
        if (this.f113l > this.f112k || m52a()) {
            this.f114m.execute(this.f115n);
        }
    }

    /* JADX INFO: renamed from: a */
    private boolean m52a() {
        return this.f108f >= 2000 && this.f108f >= this.f107e.size();
    }

    /* JADX INFO: renamed from: a */
    private boolean m53a(C0492h c0492h) {
        if (c0492h.f124f != null) {
            C0491g c0491g = c0492h.f124f;
            if (c0491g.f116a.f124f == c0491g) {
                for (int i = 0; i < c0491g.f117b.f105c; i++) {
                    try {
                        c0491g.f117b.f104b.mo282a(c0491g.f116a.f122d[i]);
                    } catch (IOException unused) {
                    }
                }
                c0491g.f116a.f124f = null;
            }
        }
        for (int i2 = 0; i2 < this.f105c; i2++) {
            this.f104b.mo282a(c0492h.f121c[i2]);
            this.f113l -= c0492h.f120b[i2];
            c0492h.f120b[i2] = 0;
        }
        this.f108f++;
        this.f106d.mo542b("REMOVE").mo562h(32).mo542b(c0492h.f119a).mo562h(10);
        this.f107e.remove(c0492h.f119a);
        if (m52a()) {
            this.f114m.execute(this.f115n);
        }
        return true;
    }

    /* JADX INFO: renamed from: b */
    private synchronized boolean m54b() {
        return this.f110h;
    }

    /* JADX INFO: renamed from: c */
    private synchronized void m55c() {
        if (m54b()) {
            throw new IllegalStateException("cache is closed");
        }
    }

    @Override // java.io.Flushable
    public final synchronized void flush() {
        if (this.f109g) {
            m55c();
            m56d();
            this.f106d.flush();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        if (this.f109g && !this.f110h) {
            for (C0492h c0492h : (C0492h[]) this.f107e.values().toArray(new C0492h[this.f107e.size()])) {
                if (c0492h.f124f != null) {
                    c0492h.f124f.m58a();
                }
            }
            m56d();
            this.f106d.close();
            this.f106d = null;
            this.f110h = true;
            return;
        }
        this.f110h = true;
    }

    /* JADX INFO: renamed from: d */
    private void m56d() {
        while (this.f113l > this.f112k) {
            m53a(this.f107e.values().iterator().next());
        }
        this.f111i = false;
    }
}
