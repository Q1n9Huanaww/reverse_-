package com.p004b.p005a.p006a.p011e;

import android.support.v7.widget.ActivityChooserView;
import com.p004b.p015b.C0636f;
import com.p004b.p015b.C0655y;
import com.p004b.p015b.InterfaceC0638h;
import com.p004b.p015b.InterfaceC0654x;
import java.io.IOException;
import java.util.logging.Level;

/* JADX INFO: renamed from: com.b.a.a.e.z */
/* JADX INFO: compiled from: Http2Reader.java */
/* JADX INFO: loaded from: classes.dex */
final class C0563z implements InterfaceC0654x {

    /* JADX INFO: renamed from: a */
    int f404a;

    /* JADX INFO: renamed from: b */
    byte f405b;

    /* JADX INFO: renamed from: c */
    int f406c;

    /* JADX INFO: renamed from: d */
    int f407d;

    /* JADX INFO: renamed from: e */
    short f408e;

    /* JADX INFO: renamed from: f */
    private final InterfaceC0638h f409f;

    @Override // com.p004b.p015b.InterfaceC0654x, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    public C0563z(InterfaceC0638h interfaceC0638h) {
        this.f409f = interfaceC0638h;
    }

    @Override // com.p004b.p015b.InterfaceC0654x
    /* JADX INFO: renamed from: a */
    public final long mo47a(C0636f c0636f, long j) throws IOException {
        while (this.f407d == 0) {
            this.f409f.mo557f(this.f408e);
            this.f408e = (short) 0;
            if ((this.f405b & 4) != 0) {
                return -1L;
            }
            int i = this.f406c;
            int iM277a = C0562y.m277a(this.f409f);
            this.f407d = iM277a;
            this.f404a = iM277a;
            byte bMo552e = (byte) (this.f409f.mo552e() & 255);
            this.f405b = (byte) (this.f409f.mo552e() & 255);
            if (C0562y.f399a.isLoggable(Level.FINE)) {
                C0562y.f399a.fine(C0544g.m253a(true, this.f406c, this.f404a, bMo552e, this.f405b));
            }
            this.f406c = this.f409f.mo558g() & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            if (bMo552e != 9) {
                throw C0544g.m254b("%s != TYPE_CONTINUATION", Byte.valueOf(bMo552e));
            }
            if (this.f406c != i) {
                throw C0544g.m254b("TYPE_CONTINUATION streamId changed", new Object[0]);
            }
        }
        long jA = this.f409f.mo47a(c0636f, Math.min(j, this.f407d));
        if (jA == -1) {
            return -1L;
        }
        this.f407d = (int) (((long) this.f407d) - jA);
        return jA;
    }

    @Override // com.p004b.p015b.InterfaceC0654x
    /* JADX INFO: renamed from: a */
    public final C0655y mo48a() {
        return this.f409f.mo48a();
    }
}
