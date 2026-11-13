package com.p004b.p005a.p006a.p011e;

import com.p004b.p015b.InterfaceC0638h;
import com.p004b.p015b.InterfaceC0653w;
import com.p004b.p015b.InterfaceC0654x;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: com.b.a.a.e.ab */
/* JADX INFO: compiled from: Http2Stream.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0527ab {

    /* JADX INFO: renamed from: i */
    static final /* synthetic */ boolean f233i = true;

    /* JADX INFO: renamed from: b */
    long f235b;

    /* JADX INFO: renamed from: c */
    final int f236c;

    /* JADX INFO: renamed from: d */
    final C0547j f237d;

    /* JADX INFO: renamed from: e */
    final C0528ac f238e;

    /* JADX INFO: renamed from: j */
    private final List<C0540c> f242j;

    /* JADX INFO: renamed from: k */
    private List<C0540c> f243k;

    /* JADX INFO: renamed from: l */
    private boolean f244l;

    /* JADX INFO: renamed from: m */
    private final C0529ad f245m;

    /* JADX INFO: renamed from: a */
    long f234a = 0;

    /* JADX INFO: renamed from: f */
    final C0530ae f239f = new C0530ae(this);

    /* JADX INFO: renamed from: g */
    final C0530ae f240g = new C0530ae(this);

    /* JADX INFO: renamed from: h */
    EnumC0539b f241h = null;

    C0527ab(int i, C0547j c0547j, boolean z, boolean z2, List<C0540c> list) {
        if (c0547j == null) {
            throw new NullPointerException("connection == null");
        }
        if (list == null) {
            throw new NullPointerException("requestHeaders == null");
        }
        this.f236c = i;
        this.f237d = c0547j;
        this.f235b = c0547j.f349m.m230d();
        this.f245m = new C0529ad(this, c0547j.f348l.m230d());
        this.f238e = new C0528ac(this);
        this.f245m.f253b = z2;
        this.f238e.f248b = z;
        this.f242j = list;
    }

    /* JADX INFO: renamed from: a */
    public final synchronized boolean m181a() {
        if (this.f241h != null) {
            return false;
        }
        return ((this.f245m.f253b || this.f245m.f252a) && (this.f238e.f248b || this.f238e.f247a) && this.f244l) ? false : true;
    }

    /* JADX INFO: renamed from: b */
    public final boolean m183b() {
        return this.f237d.f338b == ((this.f236c & 1) == 1);
    }

    /* JADX INFO: renamed from: c */
    public final synchronized List<C0540c> m184c() {
        List<C0540c> list;
        if (!m183b()) {
            throw new IllegalStateException("servers cannot read response headers");
        }
        this.f239f.m516c();
        while (this.f243k == null && this.f241h == null) {
            try {
                m191i();
            } catch (Throwable th) {
                this.f239f.m197b();
                throw th;
            }
        }
        this.f239f.m197b();
        list = this.f243k;
        if (list != null) {
            this.f243k = null;
        } else {
            throw new C0538am(this.f241h);
        }
        return list;
    }

    /* JADX INFO: renamed from: d */
    public final InterfaceC0654x m186d() {
        return this.f245m;
    }

    /* JADX INFO: renamed from: e */
    public final InterfaceC0653w m187e() {
        synchronized (this) {
            if (!this.f244l && !m183b()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.f238e;
    }

    /* JADX INFO: renamed from: a */
    public final void m178a(EnumC0539b enumC0539b) {
        if (m176d(enumC0539b)) {
            this.f237d.m269b(this.f236c, enumC0539b);
        }
    }

    /* JADX INFO: renamed from: b */
    public final void m182b(EnumC0539b enumC0539b) {
        if (m176d(enumC0539b)) {
            this.f237d.m261a(this.f236c, enumC0539b);
        }
    }

    /* JADX INFO: renamed from: d */
    private boolean m176d(EnumC0539b enumC0539b) {
        if (!f233i && Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        synchronized (this) {
            if (this.f241h != null) {
                return false;
            }
            if (this.f245m.f253b && this.f238e.f248b) {
                return false;
            }
            this.f241h = enumC0539b;
            notifyAll();
            this.f237d.m267b(this.f236c);
            return true;
        }
    }

    /* JADX INFO: renamed from: a */
    final void m180a(List<C0540c> list) {
        boolean zM181a;
        if (!f233i && Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        synchronized (this) {
            zM181a = true;
            this.f244l = true;
            if (this.f243k == null) {
                this.f243k = list;
                zM181a = m181a();
                notifyAll();
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(this.f243k);
                arrayList.add(null);
                arrayList.addAll(list);
                this.f243k = arrayList;
            }
        }
        if (zM181a) {
            return;
        }
        this.f237d.m267b(this.f236c);
    }

    /* JADX INFO: renamed from: a */
    final void m179a(InterfaceC0638h interfaceC0638h, int i) throws EOFException {
        if (!f233i && Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        this.f245m.m194a(interfaceC0638h, i);
    }

    /* JADX INFO: renamed from: f */
    final void m188f() {
        boolean zM181a;
        if (!f233i && Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        synchronized (this) {
            this.f245m.f253b = true;
            zM181a = m181a();
            notifyAll();
        }
        if (zM181a) {
            return;
        }
        this.f237d.m267b(this.f236c);
    }

    /* JADX INFO: renamed from: c */
    final synchronized void m185c(EnumC0539b enumC0539b) {
        if (this.f241h == null) {
            this.f241h = enumC0539b;
            notifyAll();
        }
    }

    /* JADX INFO: renamed from: g */
    final void m189g() {
        boolean z;
        boolean zM181a;
        if (!f233i && Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        synchronized (this) {
            z = !this.f245m.f253b && this.f245m.f252a && (this.f238e.f248b || this.f238e.f247a);
            zM181a = m181a();
        }
        if (z) {
            m178a(EnumC0539b.CANCEL);
        } else {
            if (zM181a) {
                return;
            }
            this.f237d.m267b(this.f236c);
        }
    }

    /* JADX INFO: renamed from: a */
    final void m177a(long j) {
        this.f235b += j;
        if (j > 0) {
            notifyAll();
        }
    }

    /* JADX INFO: renamed from: h */
    final void m190h() throws IOException {
        if (this.f238e.f247a) {
            throw new IOException("stream closed");
        }
        if (this.f238e.f248b) {
            throw new IOException("stream finished");
        }
        if (this.f241h != null) {
            throw new C0538am(this.f241h);
        }
    }

    /* JADX INFO: renamed from: i */
    final void m191i() throws InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException unused) {
            throw new InterruptedIOException();
        }
    }
}
