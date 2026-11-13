package com.p004b.p005a.p006a;

/* JADX INFO: renamed from: com.b.a.a.b */
/* JADX INFO: compiled from: NamedRunnable.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractRunnableC0494b implements Runnable {

    /* JADX INFO: renamed from: b */
    protected final String f125b;

    /* JADX INFO: renamed from: b */
    public abstract void mo62b();

    public AbstractRunnableC0494b(String str, Object... objArr) {
        this.f125b = C0503c.m110a(str, objArr);
    }

    @Override // java.lang.Runnable
    public final void run() {
        String name = Thread.currentThread().getName();
        Thread.currentThread().setName(this.f125b);
        try {
            mo62b();
        } finally {
            Thread.currentThread().setName(name);
        }
    }
}
