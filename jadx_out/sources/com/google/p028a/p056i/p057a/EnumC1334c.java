package com.google.p028a.p056i.p057a;

import com.google.p028a.p035c.C1194b;

/* JADX INFO: renamed from: com.google.a.i.a.c */
/* JADX INFO: compiled from: DataMask.java */
/* JADX INFO: loaded from: classes.dex */
enum EnumC1334c {
    DATA_MASK_000 { // from class: com.google.a.i.a.c.1
        @Override // com.google.p028a.p056i.p057a.EnumC1334c
        /* JADX INFO: renamed from: a */
        final boolean mo2444a(int i, int i2) {
            return ((i + i2) & 1) == 0;
        }
    },
    DATA_MASK_001 { // from class: com.google.a.i.a.c.2
        @Override // com.google.p028a.p056i.p057a.EnumC1334c
        /* JADX INFO: renamed from: a */
        final boolean mo2444a(int i, int i2) {
            return (i & 1) == 0;
        }
    },
    DATA_MASK_010 { // from class: com.google.a.i.a.c.3
        @Override // com.google.p028a.p056i.p057a.EnumC1334c
        /* JADX INFO: renamed from: a */
        final boolean mo2444a(int i, int i2) {
            return i2 % 3 == 0;
        }
    },
    DATA_MASK_011 { // from class: com.google.a.i.a.c.4
        @Override // com.google.p028a.p056i.p057a.EnumC1334c
        /* JADX INFO: renamed from: a */
        final boolean mo2444a(int i, int i2) {
            return (i + i2) % 3 == 0;
        }
    },
    DATA_MASK_100 { // from class: com.google.a.i.a.c.5
        @Override // com.google.p028a.p056i.p057a.EnumC1334c
        /* JADX INFO: renamed from: a */
        final boolean mo2444a(int i, int i2) {
            return (((i / 2) + (i2 / 3)) & 1) == 0;
        }
    },
    DATA_MASK_101 { // from class: com.google.a.i.a.c.6
        @Override // com.google.p028a.p056i.p057a.EnumC1334c
        /* JADX INFO: renamed from: a */
        final boolean mo2444a(int i, int i2) {
            return (i * i2) % 6 == 0;
        }
    },
    DATA_MASK_110 { // from class: com.google.a.i.a.c.7
        @Override // com.google.p028a.p056i.p057a.EnumC1334c
        /* JADX INFO: renamed from: a */
        final boolean mo2444a(int i, int i2) {
            return (i * i2) % 6 < 3;
        }
    },
    DATA_MASK_111 { // from class: com.google.a.i.a.c.8
        @Override // com.google.p028a.p056i.p057a.EnumC1334c
        /* JADX INFO: renamed from: a */
        final boolean mo2444a(int i, int i2) {
            return (((i + i2) + ((i * i2) % 3)) & 1) == 0;
        }
    };

    /* JADX INFO: renamed from: a */
    abstract boolean mo2444a(int i, int i2);

    /* synthetic */ EnumC1334c(byte b) {
        this();
    }

    /* JADX INFO: renamed from: a */
    final void m2443a(C1194b c1194b, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            for (int i3 = 0; i3 < i; i3++) {
                if (mo2444a(i2, i3)) {
                    c1194b.m1793c(i3, i2);
                }
            }
        }
    }
}
