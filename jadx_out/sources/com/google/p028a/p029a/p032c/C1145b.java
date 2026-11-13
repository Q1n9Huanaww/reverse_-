package com.google.p028a.p029a.p032c;

import com.google.p028a.p035c.C1190a;

/* JADX INFO: renamed from: com.google.a.a.c.b */
/* JADX INFO: compiled from: BinaryShiftToken.java */
/* JADX INFO: loaded from: classes.dex */
final class C1145b extends AbstractC1150g {

    /* JADX INFO: renamed from: c */
    private final short f1910c;

    /* JADX INFO: renamed from: d */
    private final short f1911d;

    C1145b(AbstractC1150g abstractC1150g, int i, int i2) {
        super(abstractC1150g);
        this.f1910c = (short) i;
        this.f1911d = (short) i2;
    }

    @Override // com.google.p028a.p029a.p032c.AbstractC1150g
    /* JADX INFO: renamed from: a */
    public final void mo1528a(C1190a c1190a, byte[] bArr) {
        for (int i = 0; i < this.f1911d; i++) {
            if (i == 0 || (i == 31 && this.f1911d <= 62)) {
                c1190a.m1759b(31, 5);
                if (this.f1911d > 62) {
                    c1190a.m1759b(this.f1911d - 31, 16);
                } else if (i == 0) {
                    c1190a.m1759b(Math.min((int) this.f1911d, 31), 5);
                } else {
                    c1190a.m1759b(this.f1911d - 31, 5);
                }
            }
            c1190a.m1759b(bArr[this.f1910c + i], 8);
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("<");
        sb.append((int) this.f1910c);
        sb.append("::");
        sb.append((this.f1910c + this.f1911d) - 1);
        sb.append('>');
        return sb.toString();
    }
}
