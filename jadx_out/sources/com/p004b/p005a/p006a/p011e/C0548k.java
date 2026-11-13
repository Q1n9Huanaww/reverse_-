package com.p004b.p005a.p006a.p011e;

import com.p004b.p005a.p006a.AbstractRunnableC0494b;
import java.io.IOException;

/* JADX INFO: renamed from: com.b.a.a.e.k */
/* JADX INFO: compiled from: Http2Connection.java */
/* JADX INFO: loaded from: classes.dex */
final class C0548k extends AbstractRunnableC0494b {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ int f358a;

    /* JADX INFO: renamed from: c */
    final /* synthetic */ EnumC0539b f359c;

    /* JADX INFO: renamed from: d */
    final /* synthetic */ C0547j f360d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0548k(C0547j c0547j, String str, Object[] objArr, int i, EnumC0539b enumC0539b) {
        super(str, objArr);
        this.f360d = c0547j;
        this.f358a = i;
        this.f359c = enumC0539b;
    }

    @Override // com.p004b.p005a.p006a.AbstractRunnableC0494b
    /* JADX INFO: renamed from: b */
    public final void mo62b() {
        try {
            this.f360d.m269b(this.f358a, this.f359c);
        } catch (IOException unused) {
        }
    }
}
