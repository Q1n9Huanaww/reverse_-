package com.p004b.p005a.p006a.p011e;

import com.p004b.p005a.p006a.AbstractRunnableC0494b;
import java.io.IOException;

/* JADX INFO: renamed from: com.b.a.a.e.m */
/* JADX INFO: compiled from: Http2Connection.java */
/* JADX INFO: loaded from: classes.dex */
final class C0550m extends AbstractRunnableC0494b {

    /* JADX INFO: renamed from: c */
    final /* synthetic */ int f365c;

    /* JADX INFO: renamed from: d */
    final /* synthetic */ int f366d;

    /* JADX INFO: renamed from: f */
    final /* synthetic */ C0547j f368f;

    /* JADX INFO: renamed from: a */
    final /* synthetic */ boolean f364a = true;

    /* JADX INFO: renamed from: e */
    final /* synthetic */ C0534ai f367e = null;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0550m(C0547j c0547j, String str, Object[] objArr, int i, int i2) {
        super(str, objArr);
        this.f368f = c0547j;
        this.f365c = i;
        this.f366d = i2;
    }

    @Override // com.p004b.p005a.p006a.AbstractRunnableC0494b
    /* JADX INFO: renamed from: b */
    public final void mo62b() {
        try {
            C0547j c0547j = this.f368f;
            boolean z = this.f364a;
            int i = this.f365c;
            int i2 = this.f366d;
            C0534ai c0534ai = this.f367e;
            synchronized (c0547j.f352p) {
                if (c0534ai != null) {
                    try {
                        c0534ai.m216a();
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                c0547j.f352p.m205a(z, i, i2);
            }
        } catch (IOException unused) {
        }
    }
}
