package com.p004b.p005a.p006a.p011e;

import com.p004b.p005a.p006a.AbstractRunnableC0494b;

/* JADX INFO: renamed from: com.b.a.a.e.q */
/* JADX INFO: compiled from: Http2Connection.java */
/* JADX INFO: loaded from: classes.dex */
final class C0554q extends AbstractRunnableC0494b {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ int f381a;

    /* JADX INFO: renamed from: c */
    final /* synthetic */ EnumC0539b f382c;

    /* JADX INFO: renamed from: d */
    final /* synthetic */ C0547j f383d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0554q(C0547j c0547j, String str, Object[] objArr, int i, EnumC0539b enumC0539b) {
        super(str, objArr);
        this.f383d = c0547j;
        this.f381a = i;
        this.f382c = enumC0539b;
    }

    @Override // com.p004b.p005a.p006a.AbstractRunnableC0494b
    /* JADX INFO: renamed from: b */
    public final void mo62b() {
        this.f383d.f345i.mo222c();
        synchronized (this.f383d) {
            this.f383d.f354r.remove(Integer.valueOf(this.f381a));
        }
    }
}
