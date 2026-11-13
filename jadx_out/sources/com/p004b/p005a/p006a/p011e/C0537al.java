package com.p004b.p005a.p006a.p011e;

import android.support.v7.widget.ActivityChooserView;

/* JADX INFO: renamed from: com.b.a.a.e.al */
/* JADX INFO: compiled from: Settings.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0537al {

    /* JADX INFO: renamed from: a */
    private int f277a;

    /* JADX INFO: renamed from: b */
    private final int[] f278b = new int[10];

    /* JADX INFO: renamed from: a */
    final C0537al m224a(int i, int i2) {
        if (i >= this.f278b.length) {
            return this;
        }
        this.f277a = (1 << i) | this.f277a;
        this.f278b[i] = i2;
        return this;
    }

    /* JADX INFO: renamed from: a */
    final boolean m225a(int i) {
        return ((1 << i) & this.f277a) != 0;
    }

    /* JADX INFO: renamed from: b */
    final int m227b(int i) {
        return this.f278b[i];
    }

    /* JADX INFO: renamed from: a */
    final int m223a() {
        return Integer.bitCount(this.f277a);
    }

    /* JADX INFO: renamed from: b */
    final int m226b() {
        if ((this.f277a & 2) != 0) {
            return this.f278b[1];
        }
        return -1;
    }

    /* JADX INFO: renamed from: c */
    final int m228c() {
        return (this.f277a & 16) != 0 ? this.f278b[4] : ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
    }

    /* JADX INFO: renamed from: c */
    final int m229c(int i) {
        return (this.f277a & 32) != 0 ? this.f278b[5] : i;
    }

    /* JADX INFO: renamed from: d */
    final int m230d() {
        if ((this.f277a & 128) != 0) {
            return this.f278b[7];
        }
        return 65535;
    }
}
