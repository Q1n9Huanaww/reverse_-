package com.p004b.p005a.p006a.p007a;

import com.p004b.p005a.p006a.C0503c;
import com.p004b.p015b.C0636f;
import com.p004b.p015b.C0655y;
import com.p004b.p015b.InterfaceC0637g;
import com.p004b.p015b.InterfaceC0638h;
import com.p004b.p015b.InterfaceC0654x;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: com.b.a.a.a.b */
/* JADX INFO: compiled from: CacheInterceptor.java */
/* JADX INFO: loaded from: classes.dex */
final class C0486b implements InterfaceC0654x {

    /* JADX INFO: renamed from: a */
    boolean f83a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ InterfaceC0638h f84b;

    /* JADX INFO: renamed from: c */
    final /* synthetic */ InterfaceC0487c f85c;

    /* JADX INFO: renamed from: d */
    final /* synthetic */ InterfaceC0637g f86d;

    /* JADX INFO: renamed from: e */
    final /* synthetic */ C0485a f87e;

    C0486b(C0485a c0485a, InterfaceC0638h interfaceC0638h, InterfaceC0487c interfaceC0487c, InterfaceC0637g interfaceC0637g) {
        this.f87e = c0485a;
        this.f84b = interfaceC0638h;
        this.f85c = interfaceC0487c;
        this.f86d = interfaceC0637g;
    }

    @Override // com.p004b.p015b.InterfaceC0654x
    /* JADX INFO: renamed from: a */
    public final long mo47a(C0636f c0636f, long j) throws IOException {
        try {
            long jA = this.f84b.mo47a(c0636f, j);
            if (jA == -1) {
                if (!this.f83a) {
                    this.f83a = true;
                    this.f86d.close();
                }
                return -1L;
            }
            c0636f.m531a(this.f86d.mo546c(), c0636f.m540b() - jA, jA);
            this.f86d.mo573p();
            return jA;
        } catch (IOException e) {
            if (!this.f83a) {
                this.f83a = true;
            }
            throw e;
        }
    }

    @Override // com.p004b.p015b.InterfaceC0654x
    /* JADX INFO: renamed from: a */
    public final C0655y mo48a() {
        return this.f84b.mo48a();
    }

    @Override // com.p004b.p015b.InterfaceC0654x, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (!this.f83a && !C0503c.m119a((InterfaceC0654x) this, TimeUnit.MILLISECONDS)) {
            this.f83a = true;
        }
        this.f84b.close();
    }
}
