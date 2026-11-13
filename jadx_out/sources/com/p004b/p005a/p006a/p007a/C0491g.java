package com.p004b.p005a.p006a.p007a;

/* JADX INFO: renamed from: com.b.a.a.a.g */
/* JADX INFO: compiled from: DiskLruCache.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0491g {

    /* JADX INFO: renamed from: a */
    final C0492h f116a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ C0490f f117b;

    /* JADX INFO: renamed from: c */
    private boolean f118c;

    /* JADX INFO: renamed from: a */
    public final void m58a() {
        synchronized (this.f117b) {
            if (this.f118c) {
                throw new IllegalStateException();
            }
            if (this.f116a.f124f == this) {
                this.f117b.m57a(this);
            }
            this.f118c = true;
        }
    }
}
