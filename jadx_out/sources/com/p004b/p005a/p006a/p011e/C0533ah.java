package com.p004b.p005a.p006a.p011e;

/* JADX INFO: renamed from: com.b.a.a.e.ah */
/* JADX INFO: compiled from: Huffman.java */
/* JADX INFO: loaded from: classes.dex */
final class C0533ah {

    /* JADX INFO: renamed from: a */
    final C0533ah[] f270a;

    /* JADX INFO: renamed from: b */
    final int f271b;

    /* JADX INFO: renamed from: c */
    final int f272c;

    C0533ah() {
        this.f270a = new C0533ah[256];
        this.f271b = 0;
        this.f272c = 0;
    }

    C0533ah(int i, int i2) {
        this.f270a = null;
        this.f271b = i;
        int i3 = i2 & 7;
        this.f272c = i3 == 0 ? 8 : i3;
    }
}
