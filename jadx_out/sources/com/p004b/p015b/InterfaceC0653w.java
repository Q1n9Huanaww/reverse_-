package com.p004b.p015b;

import java.io.Closeable;
import java.io.Flushable;

/* JADX INFO: renamed from: com.b.b.w */
/* JADX INFO: compiled from: Sink.java */
/* JADX INFO: loaded from: classes.dex */
public interface InterfaceC0653w extends Closeable, Flushable {
    /* JADX INFO: renamed from: a */
    C0655y mo166a();

    /* JADX INFO: renamed from: a_ */
    void mo167a_(C0636f c0636f, long j);

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    void flush();
}
