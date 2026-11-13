package com.p004b.p005a.p006a.p011e;

import com.p004b.p005a.p006a.AbstractRunnableC0494b;
import java.io.IOException;
import java.util.List;

/* JADX INFO: renamed from: com.b.a.a.e.o */
/* JADX INFO: compiled from: Http2Connection.java */
/* JADX INFO: loaded from: classes.dex */
final class C0552o extends AbstractRunnableC0494b {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ int f372a;

    /* JADX INFO: renamed from: c */
    final /* synthetic */ List f373c;

    /* JADX INFO: renamed from: d */
    final /* synthetic */ boolean f374d;

    /* JADX INFO: renamed from: e */
    final /* synthetic */ C0547j f375e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0552o(C0547j c0547j, String str, Object[] objArr, int i, List list, boolean z) {
        super(str, objArr);
        this.f375e = c0547j;
        this.f372a = i;
        this.f373c = list;
        this.f374d = z;
    }

    @Override // com.p004b.p005a.p006a.AbstractRunnableC0494b
    /* JADX INFO: renamed from: b */
    public final void mo62b() {
        this.f375e.f345i.mo221b();
        try {
            this.f375e.f352p.m202a(this.f372a, EnumC0539b.CANCEL);
            synchronized (this.f375e) {
                this.f375e.f354r.remove(Integer.valueOf(this.f372a));
            }
        } catch (IOException unused) {
        }
    }
}
