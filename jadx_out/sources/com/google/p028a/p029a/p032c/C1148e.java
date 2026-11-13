package com.google.p028a.p029a.p032c;

import com.google.p028a.p035c.C1190a;

/* JADX INFO: renamed from: com.google.a.a.c.e */
/* JADX INFO: compiled from: SimpleToken.java */
/* JADX INFO: loaded from: classes.dex */
final class C1148e extends AbstractC1150g {

    /* JADX INFO: renamed from: c */
    private final short f1928c;

    /* JADX INFO: renamed from: d */
    private final short f1929d;

    C1148e(AbstractC1150g abstractC1150g, int i, int i2) {
        super(abstractC1150g);
        this.f1928c = (short) i;
        this.f1929d = (short) i2;
    }

    @Override // com.google.p028a.p029a.p032c.AbstractC1150g
    /* JADX INFO: renamed from: a */
    final void mo1528a(C1190a c1190a, byte[] bArr) {
        c1190a.m1759b(this.f1928c, this.f1929d);
    }

    public final String toString() {
        return "<" + Integer.toBinaryString((this.f1928c & ((1 << this.f1929d) - 1)) | (1 << this.f1929d) | (1 << this.f1929d)).substring(1) + '>';
    }
}
