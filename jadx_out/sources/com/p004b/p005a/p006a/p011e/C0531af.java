package com.p004b.p005a.p006a.p011e;

import android.support.v7.widget.ActivityChooserView;
import com.p004b.p005a.p006a.C0503c;
import com.p004b.p015b.C0636f;
import com.p004b.p015b.InterfaceC0637g;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: renamed from: com.b.a.a.e.af */
/* JADX INFO: compiled from: Http2Writer.java */
/* JADX INFO: loaded from: classes.dex */
final class C0531af implements Closeable {

    /* JADX INFO: renamed from: b */
    private static final Logger f259b = Logger.getLogger(C0544g.class.getName());

    /* JADX INFO: renamed from: c */
    private final InterfaceC0637g f261c;

    /* JADX INFO: renamed from: d */
    private final boolean f262d;

    /* JADX INFO: renamed from: g */
    private boolean f265g;

    /* JADX INFO: renamed from: e */
    private final C0636f f263e = new C0636f();

    /* JADX INFO: renamed from: a */
    final C0543f f260a = new C0543f(this.f263e);

    /* JADX INFO: renamed from: f */
    private int f264f = 16384;

    public C0531af(InterfaceC0637g interfaceC0637g, boolean z) {
        this.f261c = interfaceC0637g;
        this.f262d = z;
    }

    /* JADX INFO: renamed from: a */
    public final synchronized void m200a() {
        if (this.f265g) {
            throw new IOException("closed");
        }
        if (this.f262d) {
            if (f259b.isLoggable(Level.FINE)) {
                f259b.fine(C0503c.m110a(">> CONNECTION %s", C0544g.f317a.mo588e()));
            }
            this.f261c.mo543b(C0544g.f317a.mo591h());
            this.f261c.flush();
        }
    }

    /* JADX INFO: renamed from: a */
    public final synchronized void m204a(C0537al c0537al) {
        if (this.f265g) {
            throw new IOException("closed");
        }
        this.f264f = c0537al.m229c(this.f264f);
        if (c0537al.m226b() != -1) {
            this.f260a.m250a(c0537al.m226b());
        }
        m198a(0, 0, (byte) 4, (byte) 1);
        this.f261c.flush();
    }

    /* JADX INFO: renamed from: b */
    public final synchronized void m208b() {
        if (this.f265g) {
            throw new IOException("closed");
        }
        this.f261c.flush();
    }

    /* JADX INFO: renamed from: a */
    public final synchronized void m207a(boolean z, int i, List<C0540c> list) {
        if (this.f265g) {
            throw new IOException("closed");
        }
        if (this.f265g) {
            throw new IOException("closed");
        }
        this.f260a.m251a(list);
        long jM540b = this.f263e.m540b();
        int iMin = (int) Math.min(this.f264f, jM540b);
        long j = iMin;
        byte b = jM540b == j ? (byte) 4 : (byte) 0;
        if (z) {
            b = (byte) (b | 1);
        }
        m198a(i, iMin, (byte) 1, b);
        this.f261c.mo167a_(this.f263e, j);
        if (jM540b > j) {
            m199b(i, jM540b - j);
        }
    }

    /* JADX INFO: renamed from: a */
    public final synchronized void m202a(int i, EnumC0539b enumC0539b) {
        if (this.f265g) {
            throw new IOException("closed");
        }
        if (enumC0539b.f287g == -1) {
            throw new IllegalArgumentException();
        }
        m198a(i, 4, (byte) 3, (byte) 0);
        this.f261c.mo555f(enumC0539b.f287g);
        this.f261c.flush();
    }

    /* JADX INFO: renamed from: c */
    public final int m210c() {
        return this.f264f;
    }

    /* JADX INFO: renamed from: a */
    public final synchronized void m206a(boolean z, int i, C0636f c0636f, int i2) {
        if (this.f265g) {
            throw new IOException("closed");
        }
        m198a(i, i2, (byte) 0, z ? (byte) 1 : (byte) 0);
        if (i2 > 0) {
            this.f261c.mo167a_(c0636f, i2);
        }
    }

    /* JADX INFO: renamed from: b */
    public final synchronized void m209b(C0537al c0537al) {
        int i;
        if (this.f265g) {
            throw new IOException("closed");
        }
        int i2 = 0;
        m198a(0, c0537al.m223a() * 6, (byte) 4, (byte) 0);
        while (i2 < 10) {
            if (c0537al.m225a(i2)) {
                if (i2 == 4) {
                    i = 3;
                } else {
                    i = i2 == 7 ? 4 : i2;
                }
                this.f261c.mo560g(i);
                this.f261c.mo555f(c0537al.m227b(i2));
            }
            i2++;
        }
        this.f261c.flush();
    }

    /* JADX INFO: renamed from: a */
    public final synchronized void m205a(boolean z, int i, int i2) {
        if (this.f265g) {
            throw new IOException("closed");
        }
        m198a(0, 8, (byte) 6, z ? (byte) 1 : (byte) 0);
        this.f261c.mo555f(i);
        this.f261c.mo555f(i2);
        this.f261c.flush();
    }

    /* JADX INFO: renamed from: a */
    public final synchronized void m203a(int i, EnumC0539b enumC0539b, byte[] bArr) {
        if (this.f265g) {
            throw new IOException("closed");
        }
        if (enumC0539b.f287g == -1) {
            throw C0544g.m252a("errorCode.httpCode == -1", new Object[0]);
        }
        m198a(0, bArr.length + 8, (byte) 7, (byte) 0);
        this.f261c.mo555f(i);
        this.f261c.mo555f(enumC0539b.f287g);
        if (bArr.length > 0) {
            this.f261c.mo543b(bArr);
        }
        this.f261c.flush();
    }

    /* JADX INFO: renamed from: a */
    public final synchronized void m201a(int i, long j) {
        if (this.f265g) {
            throw new IOException("closed");
        }
        if (j != 0 && j <= 2147483647L) {
            m198a(i, 4, (byte) 8, (byte) 0);
            this.f261c.mo555f((int) j);
            this.f261c.flush();
        }
        throw C0544g.m252a("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", Long.valueOf(j));
    }

    /* JADX INFO: renamed from: a */
    private void m198a(int i, int i2, byte b, byte b2) {
        if (f259b.isLoggable(Level.FINE)) {
            f259b.fine(C0544g.m253a(false, i, i2, b, b2));
        }
        if (i2 > this.f264f) {
            throw C0544g.m252a("FRAME_SIZE_ERROR length > %d: %d", Integer.valueOf(this.f264f), Integer.valueOf(i2));
        }
        if ((Integer.MIN_VALUE & i) != 0) {
            throw C0544g.m252a("reserved bit set: %s", Integer.valueOf(i));
        }
        InterfaceC0637g interfaceC0637g = this.f261c;
        interfaceC0637g.mo562h((i2 >>> 16) & 255);
        interfaceC0637g.mo562h((i2 >>> 8) & 255);
        interfaceC0637g.mo562h(i2 & 255);
        this.f261c.mo562h(b & 255);
        this.f261c.mo562h(b2 & 255);
        this.f261c.mo555f(i & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        this.f265g = true;
        this.f261c.close();
    }

    /* JADX INFO: renamed from: b */
    private void m199b(int i, long j) {
        while (j > 0) {
            int iMin = (int) Math.min(this.f264f, j);
            long j2 = iMin;
            long j3 = j - j2;
            m198a(i, iMin, (byte) 9, j3 == 0 ? (byte) 4 : (byte) 0);
            this.f261c.mo167a_(this.f263e, j2);
            j = j3;
        }
    }
}
