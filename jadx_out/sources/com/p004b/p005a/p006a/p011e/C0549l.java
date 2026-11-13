package com.p004b.p005a.p006a.p011e;

import com.p004b.p005a.p006a.AbstractRunnableC0494b;
import java.io.IOException;

/* JADX INFO: renamed from: com.b.a.a.e.l */
/* JADX INFO: compiled from: Http2Connection.java */
/* JADX INFO: loaded from: classes.dex */
final class C0549l extends AbstractRunnableC0494b {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ int f361a;

    /* JADX INFO: renamed from: c */
    final /* synthetic */ long f362c;

    /* JADX INFO: renamed from: d */
    final /* synthetic */ C0547j f363d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0549l(C0547j c0547j, String str, Object[] objArr, int i, long j) {
        super(str, objArr);
        this.f363d = c0547j;
        this.f361a = i;
        this.f362c = j;
    }

    @Override // com.p004b.p005a.p006a.AbstractRunnableC0494b
    /* JADX INFO: renamed from: b */
    public final void mo62b() {
        try {
            this.f363d.f352p.m201a(this.f361a, this.f362c);
        } catch (IOException unused) {
        }
    }
}
