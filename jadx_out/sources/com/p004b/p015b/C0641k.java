package com.p004b.p015b;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: com.b.b.k */
/* JADX INFO: compiled from: ForwardingTimeout.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0641k extends C0655y {

    /* JADX INFO: renamed from: a */
    private C0655y f821a;

    public C0641k(C0655y c0655y) {
        if (c0655y == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f821a = c0655y;
    }

    /* JADX INFO: renamed from: a */
    public final C0655y m593a() {
        return this.f821a;
    }

    /* JADX INFO: renamed from: a */
    public final C0641k m592a(C0655y c0655y) {
        if (c0655y == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f821a = c0655y;
        return this;
    }

    @Override // com.p004b.p015b.C0655y
    /* JADX INFO: renamed from: a */
    public final C0655y mo595a(long j, TimeUnit timeUnit) {
        return this.f821a.mo595a(j, timeUnit);
    }

    @Override // com.p004b.p015b.C0655y
    /* JADX INFO: renamed from: b_ */
    public final long mo596b_() {
        return this.f821a.mo596b_();
    }

    @Override // com.p004b.p015b.C0655y
    /* JADX INFO: renamed from: c_ */
    public final boolean mo597c_() {
        return this.f821a.mo597c_();
    }

    @Override // com.p004b.p015b.C0655y
    /* JADX INFO: renamed from: d */
    public final long mo598d() {
        return this.f821a.mo598d();
    }

    @Override // com.p004b.p015b.C0655y
    /* JADX INFO: renamed from: a */
    public final C0655y mo594a(long j) {
        return this.f821a.mo594a(j);
    }

    @Override // com.p004b.p015b.C0655y
    /* JADX INFO: renamed from: d_ */
    public final C0655y mo599d_() {
        return this.f821a.mo599d_();
    }

    @Override // com.p004b.p015b.C0655y
    /* JADX INFO: renamed from: e_ */
    public final C0655y mo600e_() {
        return this.f821a.mo600e_();
    }

    @Override // com.p004b.p015b.C0655y
    /* JADX INFO: renamed from: f_ */
    public final void mo601f_() throws InterruptedIOException {
        this.f821a.mo601f_();
    }
}
