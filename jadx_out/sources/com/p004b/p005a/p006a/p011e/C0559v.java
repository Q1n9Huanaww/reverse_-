package com.p004b.p005a.p006a.p011e;

import com.p004b.p005a.p006a.AbstractRunnableC0494b;
import com.p004b.p005a.p006a.p013g.C0573h;
import java.io.IOException;

/* JADX INFO: renamed from: com.b.a.a.e.v */
/* JADX INFO: compiled from: Http2Connection.java */
/* JADX INFO: loaded from: classes.dex */
final class C0559v extends AbstractRunnableC0494b {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C0527ab f394a;

    /* JADX INFO: renamed from: c */
    final /* synthetic */ C0558u f395c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0559v(C0558u c0558u, String str, Object[] objArr, C0527ab c0527ab) {
        super(str, objArr);
        this.f395c = c0558u;
        this.f394a = c0527ab;
    }

    @Override // com.p004b.p005a.p006a.AbstractRunnableC0494b
    /* JADX INFO: renamed from: b */
    public final void mo62b() {
        try {
            this.f395c.f393c.f339c.mo70a(this.f394a);
        } catch (IOException e) {
            C0573h.m307b().mo287a(4, "Http2Connection.Listener failure for " + this.f395c.f393c.f341e, e);
            try {
                this.f394a.m178a(EnumC0539b.PROTOCOL_ERROR);
            } catch (IOException unused) {
            }
        }
    }
}
