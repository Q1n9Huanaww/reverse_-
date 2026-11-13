package com.p004b.p005a.p006a.p011e;

import com.p004b.p005a.p006a.AbstractRunnableC0494b;
import com.p004b.p015b.C0636f;
import java.io.IOException;

/* JADX INFO: renamed from: com.b.a.a.e.p */
/* JADX INFO: compiled from: Http2Connection.java */
/* JADX INFO: loaded from: classes.dex */
final class C0553p extends AbstractRunnableC0494b {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ int f376a;

    /* JADX INFO: renamed from: c */
    final /* synthetic */ C0636f f377c;

    /* JADX INFO: renamed from: d */
    final /* synthetic */ int f378d;

    /* JADX INFO: renamed from: e */
    final /* synthetic */ boolean f379e;

    /* JADX INFO: renamed from: f */
    final /* synthetic */ C0547j f380f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0553p(C0547j c0547j, String str, Object[] objArr, int i, C0636f c0636f, int i2, boolean z) {
        super(str, objArr);
        this.f380f = c0547j;
        this.f376a = i;
        this.f377c = c0636f;
        this.f378d = i2;
        this.f379e = z;
    }

    @Override // com.p004b.p005a.p006a.AbstractRunnableC0494b
    /* JADX INFO: renamed from: b */
    public final void mo62b() {
        try {
            this.f380f.f345i.mo220a(this.f377c, this.f378d);
            this.f380f.f352p.m202a(this.f376a, EnumC0539b.CANCEL);
            synchronized (this.f380f) {
                this.f380f.f354r.remove(Integer.valueOf(this.f376a));
            }
        } catch (IOException unused) {
        }
    }
}
