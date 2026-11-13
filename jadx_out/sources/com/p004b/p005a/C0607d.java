package com.p004b.p005a;

import com.p004b.p005a.p006a.p007a.C0490f;
import com.p004b.p005a.p006a.p007a.InterfaceC0493i;
import java.io.Closeable;
import java.io.Flushable;

/* JADX INFO: renamed from: com.b.a.d */
/* JADX INFO: compiled from: Cache.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0607d implements Closeable, Flushable {

    /* JADX INFO: renamed from: a */
    final InterfaceC0493i f602a;

    /* JADX INFO: renamed from: b */
    final C0490f f603b;

    @Override // java.io.Flushable
    public final void flush() {
        this.f603b.flush();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f603b.close();
    }
}
