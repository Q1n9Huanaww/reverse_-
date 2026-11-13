package com.p004b.p005a.p006a.p010d;

import com.p004b.p015b.C0641k;
import com.p004b.p015b.C0655y;
import com.p004b.p015b.InterfaceC0654x;

/* JADX INFO: renamed from: com.b.a.a.d.b */
/* JADX INFO: compiled from: Http1Codec.java */
/* JADX INFO: loaded from: classes.dex */
abstract class AbstractC0519b implements InterfaceC0654x {

    /* JADX INFO: renamed from: a */
    protected final C0641k f215a;

    /* JADX INFO: renamed from: b */
    protected boolean f216b;

    /* JADX INFO: renamed from: c */
    final /* synthetic */ C0518a f217c;

    private AbstractC0519b(C0518a c0518a) {
        this.f217c = c0518a;
        this.f215a = new C0641k(this.f217c.f212c.mo48a());
    }

    /* synthetic */ AbstractC0519b(C0518a c0518a, byte b) {
        this(c0518a);
    }

    @Override // com.p004b.p015b.InterfaceC0654x
    /* JADX INFO: renamed from: a */
    public final C0655y mo48a() {
        return this.f215a;
    }

    /* JADX INFO: renamed from: a */
    protected final void m165a(boolean z) {
        if (this.f217c.f214e == 6) {
            return;
        }
        if (this.f217c.f214e != 5) {
            throw new IllegalStateException("state: " + this.f217c.f214e);
        }
        C0518a.m161a(this.f215a);
        this.f217c.f214e = 6;
        if (this.f217c.f211b != null) {
            this.f217c.f211b.m98a(!z, this.f217c);
        }
    }
}
