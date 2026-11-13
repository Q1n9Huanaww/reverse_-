package com.umeng.commonsdk.proguard;

import com.umeng.commonsdk.proguard.InterfaceC1737aa;
import com.umeng.commonsdk.proguard.InterfaceC1744ah;
import java.io.Serializable;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.aa */
/* JADX INFO: compiled from: TBase.java */
/* JADX INFO: loaded from: classes.dex */
public interface InterfaceC1737aa<T extends InterfaceC1737aa<?, ?>, F extends InterfaceC1744ah> extends Serializable {
    void clear();

    InterfaceC1737aa<T, F> deepCopy();

    F fieldForId(int i);

    void read(AbstractC1762az abstractC1762az) throws C1743ag;

    void write(AbstractC1762az abstractC1762az) throws C1743ag;
}
