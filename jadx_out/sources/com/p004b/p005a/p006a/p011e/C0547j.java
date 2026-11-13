package com.p004b.p005a.p006a.p011e;

import android.support.v7.widget.ActivityChooserView;
import com.p004b.p005a.p006a.C0503c;
import com.p004b.p015b.C0636f;
import com.p004b.p015b.InterfaceC0638h;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: com.b.a.a.e.j */
/* JADX INFO: compiled from: Http2Connection.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0547j implements Closeable {

    /* JADX INFO: renamed from: a */
    static final ExecutorService f336a = new ThreadPoolExecutor(0, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, 60, TimeUnit.SECONDS, new SynchronousQueue(), C0503c.m114a("OkHttp Http2Connection", true));

    /* JADX INFO: renamed from: s */
    static final /* synthetic */ boolean f337s = true;

    /* JADX INFO: renamed from: b */
    final boolean f338b;

    /* JADX INFO: renamed from: c */
    final AbstractC0556s f339c;

    /* JADX INFO: renamed from: e */
    final String f341e;

    /* JADX INFO: renamed from: f */
    int f342f;

    /* JADX INFO: renamed from: g */
    int f343g;

    /* JADX INFO: renamed from: h */
    boolean f344h;

    /* JADX INFO: renamed from: i */
    final InterfaceC0535aj f345i;

    /* JADX INFO: renamed from: k */
    long f347k;

    /* JADX INFO: renamed from: o */
    final Socket f351o;

    /* JADX INFO: renamed from: p */
    final C0531af f352p;

    /* JADX INFO: renamed from: q */
    final C0558u f353q;

    /* JADX INFO: renamed from: t */
    private final ExecutorService f355t;

    /* JADX INFO: renamed from: u */
    private Map<Integer, C0534ai> f356u;

    /* JADX INFO: renamed from: v */
    private int f357v;

    /* JADX INFO: renamed from: d */
    final Map<Integer, C0527ab> f340d = new LinkedHashMap();

    /* JADX INFO: renamed from: j */
    long f346j = 0;

    /* JADX INFO: renamed from: l */
    C0537al f348l = new C0537al();

    /* JADX INFO: renamed from: m */
    final C0537al f349m = new C0537al();

    /* JADX INFO: renamed from: n */
    boolean f350n = false;

    /* JADX INFO: renamed from: r */
    final Set<Integer> f354r = new LinkedHashSet();

    /* JADX INFO: renamed from: d */
    static boolean m256d(int i) {
        return i != 0 && (i & 1) == 0;
    }

    C0547j(C0555r c0555r) {
        this.f345i = c0555r.f389f;
        this.f338b = c0555r.f390g;
        this.f339c = c0555r.f388e;
        this.f343g = c0555r.f390g ? 1 : 2;
        if (c0555r.f390g) {
            this.f343g += 2;
        }
        this.f357v = c0555r.f390g ? 1 : 2;
        if (c0555r.f390g) {
            this.f348l.m224a(7, 16777216);
        }
        this.f341e = c0555r.f385b;
        this.f355t = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), C0503c.m114a(C0503c.m110a("OkHttp %s Push Observer", this.f341e), true));
        this.f349m.m224a(7, 65535);
        this.f349m.m224a(5, 16384);
        this.f347k = this.f349m.m230d();
        this.f351o = c0555r.f384a;
        this.f352p = new C0531af(c0555r.f387d, this.f338b);
        this.f353q = new C0558u(this, new C0562y(c0555r.f386c, this.f338b));
    }

    /* JADX INFO: renamed from: a */
    final synchronized C0527ab m258a(int i) {
        return this.f340d.get(Integer.valueOf(i));
    }

    /* JADX INFO: renamed from: b */
    final synchronized C0527ab m267b(int i) {
        C0527ab c0527abRemove;
        c0527abRemove = this.f340d.remove(Integer.valueOf(i));
        notifyAll();
        return c0527abRemove;
    }

    /* JADX INFO: renamed from: a */
    public final synchronized int m257a() {
        return this.f349m.m228c();
    }

    /* JADX INFO: renamed from: a */
    public final C0527ab m259a(List<C0540c> list, boolean z) {
        return m255b(list, z);
    }

    /* JADX INFO: renamed from: b */
    private C0527ab m255b(List<C0540c> list, boolean z) {
        int i;
        C0527ab c0527ab;
        boolean z2;
        boolean z3 = !z;
        synchronized (this.f352p) {
            synchronized (this) {
                if (this.f344h) {
                    throw new C0525a();
                }
                i = this.f343g;
                this.f343g += 2;
                c0527ab = new C0527ab(i, this, z3, false, list);
                z2 = !z || this.f347k == 0 || c0527ab.f235b == 0;
                if (c0527ab.m181a()) {
                    this.f340d.put(Integer.valueOf(i), c0527ab);
                }
            }
            this.f352p.m207a(z3, i, list);
        }
        if (z2) {
            this.f352p.m208b();
        }
        return c0527ab;
    }

    /* JADX INFO: renamed from: a */
    public final void m265a(int i, boolean z, C0636f c0636f, long j) {
        int iMin;
        long j2;
        if (j == 0) {
            this.f352p.m206a(z, i, c0636f, 0);
            return;
        }
        while (j > 0) {
            synchronized (this) {
                while (this.f347k <= 0) {
                    try {
                        if (!this.f340d.containsKey(Integer.valueOf(i))) {
                            throw new IOException("stream closed");
                        }
                        wait();
                    } catch (InterruptedException unused) {
                        throw new InterruptedIOException();
                    }
                    throw th;
                }
                iMin = Math.min((int) Math.min(j, this.f347k), this.f352p.m210c());
                j2 = iMin;
                this.f347k -= j2;
            }
            long j3 = j - j2;
            this.f352p.m206a(z && j3 == 0, i, c0636f, iMin);
            j = j3;
        }
    }

    /* JADX INFO: renamed from: a */
    final void m261a(int i, EnumC0539b enumC0539b) {
        f336a.execute(new C0548k(this, "OkHttp %s stream %d", new Object[]{this.f341e, Integer.valueOf(i)}, i, enumC0539b));
    }

    /* JADX INFO: renamed from: b */
    final void m269b(int i, EnumC0539b enumC0539b) {
        this.f352p.m202a(i, enumC0539b);
    }

    /* JADX INFO: renamed from: a */
    final void m260a(int i, long j) {
        f336a.execute(new C0549l(this, "OkHttp Window Update %s stream %d", new Object[]{this.f341e, Integer.valueOf(i)}, i, j));
    }

    /* JADX INFO: renamed from: c */
    final synchronized C0534ai m270c(int i) {
        if (this.f356u == null) {
            return null;
        }
        return this.f356u.remove(Integer.valueOf(i));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        m266a(EnumC0539b.NO_ERROR, EnumC0539b.CANCEL);
    }

    /* JADX WARN: Code duplicated, block: B:41:0x0076  */
    /* JADX WARN: Code duplicated, block: B:43:0x007b  */
    /* JADX WARN: Code duplicated, block: B:52:0x008b  */
    /* JADX WARN: Code duplicated, block: B:54:0x008e A[LOOP:1: B:53:0x008c->B:54:0x008e, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:64:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:65:0x00aa A[RETURN] */
    /* JADX WARN: Instruction removed from duplicated block: B:41:0x0076, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:52:0x008b, please report this as an issue */
    /* JADX INFO: renamed from: a */
    final void m266a(EnumC0539b enumC0539b, EnumC0539b enumC0539b2) throws IOException {
        C0527ab[] c0527abArr;
        int i;
        int i2;
        if (!f337s && Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        C0534ai[] c0534aiArr = null;
        try {
            synchronized (this.f352p) {
                synchronized (this) {
                    if (!this.f344h) {
                        this.f344h = true;
                        this.f352p.m203a(this.f342f, enumC0539b, C0503c.f169a);
                    }
                }
                if (c0527abArr != null) {
                    IOException iOException = e;
                    for (C0527ab c0527ab : c0527abArr) {
                        try {
                            c0527ab.m178a(enumC0539b2);
                        } catch (IOException e) {
                            if (iOException != null) {
                                iOException = e;
                            }
                        }
                    }
                    e = iOException;
                }
                if (c0534aiArr != null) {
                    for (C0534ai c0534ai : c0534aiArr) {
                        c0534ai.m218c();
                    }
                }
                try {
                    this.f352p.close();
                } catch (IOException e2) {
                    if (e == null) {
                        e = e2;
                    }
                }
                try {
                    this.f351o.close();
                } catch (IOException e3) {
                    e = e3;
                }
                if (e != null) {
                    throw e;
                }
            }
            e = null;
        } catch (IOException e4) {
            e = e4;
        }
        synchronized (this) {
            if (this.f340d.isEmpty()) {
                c0527abArr = null;
            } else {
                c0527abArr = (C0527ab[]) this.f340d.values().toArray(new C0527ab[this.f340d.size()]);
                this.f340d.clear();
            }
            if (this.f356u != null) {
                C0534ai[] c0534aiArr2 = (C0534ai[]) this.f356u.values().toArray(new C0534ai[this.f356u.size()]);
                this.f356u = null;
                c0534aiArr = c0534aiArr2;
            }
            if (c0527abArr != null) {
                IOException iOException2 = e;
                while (i2 < r3) {
                    c0527ab.m178a(enumC0539b2);
                }
                e = iOException2;
            }
            if (c0534aiArr != null) {
                while (i < r8) {
                    c0534ai.m218c();
                }
            }
            this.f352p.close();
            this.f351o.close();
            if (e != null) {
                throw e;
            }
        }
    }

    /* JADX INFO: renamed from: c */
    public final synchronized boolean m272c() {
        return this.f344h;
    }

    /* JADX INFO: renamed from: a */
    final void m263a(int i, List<C0540c> list) {
        synchronized (this) {
            if (this.f354r.contains(Integer.valueOf(i))) {
                m261a(i, EnumC0539b.PROTOCOL_ERROR);
            } else {
                this.f354r.add(Integer.valueOf(i));
                this.f355t.execute(new C0551n(this, "OkHttp %s Push Request[%s]", new Object[]{this.f341e, Integer.valueOf(i)}, i, list));
            }
        }
    }

    /* JADX INFO: renamed from: a */
    final void m264a(int i, List<C0540c> list, boolean z) {
        this.f355t.execute(new C0552o(this, "OkHttp %s Push Headers[%s]", new Object[]{this.f341e, Integer.valueOf(i)}, i, list, z));
    }

    /* JADX INFO: renamed from: a */
    final void m262a(int i, InterfaceC0638h interfaceC0638h, int i2, boolean z) throws IOException {
        C0636f c0636f = new C0636f();
        long j = i2;
        interfaceC0638h.mo538a(j);
        interfaceC0638h.mo47a(c0636f, j);
        if (c0636f.m540b() == j) {
            this.f355t.execute(new C0553p(this, "OkHttp %s Push Data[%s]", new Object[]{this.f341e, Integer.valueOf(i)}, i, c0636f, i2, z));
            return;
        }
        throw new IOException(c0636f.m540b() + " != " + i2);
    }

    /* JADX INFO: renamed from: c */
    final void m271c(int i, EnumC0539b enumC0539b) {
        this.f355t.execute(new C0554q(this, "OkHttp %s Push Reset[%s]", new Object[]{this.f341e, Integer.valueOf(i)}, i, enumC0539b));
    }

    /* JADX INFO: renamed from: b */
    public final void m268b() {
        this.f352p.m200a();
        this.f352p.m209b(this.f348l);
        int iM230d = this.f348l.m230d();
        if (iM230d != 65535) {
            this.f352p.m201a(0, iM230d - 65535);
        }
        new Thread(this.f353q).start();
    }
}
