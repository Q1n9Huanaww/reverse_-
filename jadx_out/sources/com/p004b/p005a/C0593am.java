package com.p004b.p005a;

import com.p004b.p005a.p006a.p007a.C0485a;
import com.p004b.p005a.p006a.p008b.C0495a;
import com.p004b.p005a.p006a.p009c.C0504a;
import com.p004b.p005a.p006a.p009c.C0505b;
import com.p004b.p005a.p006a.p009c.C0511h;
import com.p004b.p005a.p006a.p009c.C0514k;
import com.p004b.p005a.p006a.p013g.C0573h;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: renamed from: com.b.a.am */
/* JADX INFO: compiled from: RealCall.java */
/* JADX INFO: loaded from: classes.dex */
final class C0593am implements InterfaceC0610g {

    /* JADX INFO: renamed from: a */
    final C0589ai f541a;

    /* JADX INFO: renamed from: b */
    final C0514k f542b;

    /* JADX INFO: renamed from: c */
    final C0595ao f543c;

    /* JADX INFO: renamed from: d */
    final boolean f544d;

    /* JADX INFO: renamed from: e */
    private boolean f545e;

    C0593am(C0589ai c0589ai, C0595ao c0595ao, boolean z) {
        this.f541a = c0589ai;
        this.f543c = c0595ao;
        this.f544d = z;
        this.f542b = new C0514k(c0589ai, z);
    }

    @Override // com.p004b.p005a.InterfaceC0610g
    /* JADX INFO: renamed from: a */
    public final C0599as mo393a() {
        synchronized (this) {
            if (this.f545e) {
                throw new IllegalStateException("Already Executed");
            }
            this.f545e = true;
        }
        m392d();
        try {
            this.f541a.f485c.m497a(this);
            C0599as c0599asM396c = m396c();
            if (c0599asM396c == null) {
                throw new IOException("Canceled");
            }
            this.f541a.f485c.m499b(this);
            return c0599asM396c;
        } catch (Throwable th) {
            this.f541a.f485c.m499b(this);
            throw th;
        }
    }

    /* JADX INFO: renamed from: d */
    private void m392d() {
        this.f542b.m158a(C0573h.m307b().mo285a("response.body().close()"));
    }

    @Override // com.p004b.p005a.InterfaceC0610g
    /* JADX INFO: renamed from: a */
    public final void mo394a(InterfaceC0611h interfaceC0611h) {
        synchronized (this) {
            if (this.f545e) {
                throw new IllegalStateException("Already Executed");
            }
            this.f545e = true;
        }
        m392d();
        this.f541a.f485c.m498a(new C0594an(this, interfaceC0611h));
    }

    /* JADX INFO: renamed from: b */
    final String m395b() {
        C0584ad c0584adM348d = this.f543c.f548a.m348d("/...");
        c0584adM348d.f462b = C0583ac.m336a("", " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
        c0584adM348d.f463c = C0583ac.m336a("", " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
        return c0584adM348d.m365b().toString();
    }

    /* JADX INFO: renamed from: c */
    final C0599as m396c() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f541a.f489g);
        arrayList.add(this.f542b);
        arrayList.add(new C0504a(this.f541a.f492j));
        C0589ai c0589ai = this.f541a;
        arrayList.add(new C0485a(c0589ai.f493k != null ? c0589ai.f493k.f602a : c0589ai.f494l));
        arrayList.add(new C0495a(this.f541a));
        if (!this.f544d) {
            arrayList.addAll(this.f541a.f490h);
        }
        arrayList.add(new C0505b(this.f544d));
        return new C0511h(arrayList, null, null, null, 0, this.f543c).mo147a(this.f543c);
    }

    public final /* synthetic */ Object clone() {
        return new C0593am(this.f541a, this.f543c, this.f544d);
    }
}
