package com.p004b.p005a.p006a.p011e;

import com.p004b.p005a.p006a.AbstractRunnableC0494b;
import java.io.IOException;

/* JADX INFO: renamed from: com.b.a.a.e.x */
/* JADX INFO: compiled from: Http2Connection.java */
/* JADX INFO: loaded from: classes.dex */
final class C0561x extends AbstractRunnableC0494b {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C0537al f397a;

    /* JADX INFO: renamed from: c */
    final /* synthetic */ C0558u f398c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0561x(C0558u c0558u, String str, Object[] objArr, C0537al c0537al) {
        super(str, objArr);
        this.f398c = c0558u;
        this.f397a = c0537al;
    }

    @Override // com.p004b.p005a.p006a.AbstractRunnableC0494b
    /* JADX INFO: renamed from: b */
    public final void mo62b() {
        try {
            this.f398c.f393c.f352p.m204a(this.f397a);
        } catch (IOException unused) {
        }
    }
}
