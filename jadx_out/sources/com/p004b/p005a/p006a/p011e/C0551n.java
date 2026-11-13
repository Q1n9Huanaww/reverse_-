package com.p004b.p005a.p006a.p011e;

import com.p004b.p005a.p006a.AbstractRunnableC0494b;
import java.io.IOException;
import java.util.List;

/* JADX INFO: renamed from: com.b.a.a.e.n */
/* JADX INFO: compiled from: Http2Connection.java */
/* JADX INFO: loaded from: classes.dex */
final class C0551n extends AbstractRunnableC0494b {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ int f369a;

    /* JADX INFO: renamed from: c */
    final /* synthetic */ List f370c;

    /* JADX INFO: renamed from: d */
    final /* synthetic */ C0547j f371d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0551n(C0547j c0547j, String str, Object[] objArr, int i, List list) {
        super(str, objArr);
        this.f371d = c0547j;
        this.f369a = i;
        this.f370c = list;
    }

    @Override // com.p004b.p005a.p006a.AbstractRunnableC0494b
    /* JADX INFO: renamed from: b */
    public final void mo62b() {
        this.f371d.f345i.mo219a();
        try {
            this.f371d.f352p.m202a(this.f369a, EnumC0539b.CANCEL);
            synchronized (this.f371d) {
                this.f371d.f354r.remove(Integer.valueOf(this.f369a));
            }
        } catch (IOException unused) {
        }
    }
}
