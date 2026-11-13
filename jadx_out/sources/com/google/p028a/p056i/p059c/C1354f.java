package com.google.p028a.p056i.p059c;

import com.google.p028a.p056i.p057a.C1341j;
import com.google.p028a.p056i.p057a.EnumC1337f;
import com.google.p028a.p056i.p057a.EnumC1339h;

/* JADX INFO: renamed from: com.google.a.i.c.f */
/* JADX INFO: compiled from: QRCode.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1354f {

    /* JADX INFO: renamed from: a */
    public static final int f2814a = 8;

    /* JADX INFO: renamed from: b */
    EnumC1339h f2815b;

    /* JADX INFO: renamed from: c */
    EnumC1337f f2816c;

    /* JADX INFO: renamed from: d */
    C1341j f2817d;

    /* JADX INFO: renamed from: e */
    int f2818e = -1;

    /* JADX INFO: renamed from: f */
    public C1350b f2819f;

    /* JADX INFO: renamed from: b */
    private static boolean m2602b(int i) {
        return i >= 0 && i < 8;
    }

    /* JADX INFO: renamed from: a */
    private EnumC1339h m2595a() {
        return this.f2815b;
    }

    /* JADX INFO: renamed from: b */
    private EnumC1337f m2601b() {
        return this.f2816c;
    }

    /* JADX INFO: renamed from: c */
    private C1341j m2603c() {
        return this.f2817d;
    }

    /* JADX INFO: renamed from: d */
    private int m2604d() {
        return this.f2818e;
    }

    /* JADX INFO: renamed from: e */
    private C1350b m2605e() {
        return this.f2819f;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(200);
        sb.append("<<\n");
        sb.append(" mode: ");
        sb.append(this.f2815b);
        sb.append("\n ecLevel: ");
        sb.append(this.f2816c);
        sb.append("\n version: ");
        sb.append(this.f2817d);
        sb.append("\n maskPattern: ");
        sb.append(this.f2818e);
        if (this.f2819f == null) {
            sb.append("\n matrix: null\n");
        } else {
            sb.append("\n matrix:\n");
            sb.append(this.f2819f);
        }
        sb.append(">>\n");
        return sb.toString();
    }

    /* JADX INFO: renamed from: a */
    private void m2598a(EnumC1339h enumC1339h) {
        this.f2815b = enumC1339h;
    }

    /* JADX INFO: renamed from: a */
    private void m2597a(EnumC1337f enumC1337f) {
        this.f2816c = enumC1337f;
    }

    /* JADX INFO: renamed from: a */
    private void m2599a(C1341j c1341j) {
        this.f2817d = c1341j;
    }

    /* JADX INFO: renamed from: a */
    private void m2596a(int i) {
        this.f2818e = i;
    }

    /* JADX INFO: renamed from: a */
    private void m2600a(C1350b c1350b) {
        this.f2819f = c1350b;
    }
}
