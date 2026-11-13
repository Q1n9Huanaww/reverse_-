package com.google.p028a.p056i.p057a;

import com.google.p028a.C1365t;

/* JADX INFO: renamed from: com.google.a.i.a.i */
/* JADX INFO: compiled from: QRCodeDecoderMetaData.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1340i {

    /* JADX INFO: renamed from: a */
    private final boolean f2756a = true;

    C1340i() {
    }

    /* JADX INFO: renamed from: a */
    private boolean m2469a() {
        return this.f2756a;
    }

    /* JADX INFO: renamed from: a */
    public final void m2470a(C1365t[] c1365tArr) {
        if (!this.f2756a || c1365tArr == null || c1365tArr.length < 3) {
            return;
        }
        C1365t c1365t = c1365tArr[0];
        c1365tArr[0] = c1365tArr[2];
        c1365tArr[2] = c1365t;
    }
}
