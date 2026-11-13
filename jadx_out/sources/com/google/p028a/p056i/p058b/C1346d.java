package com.google.p028a.p056i.p058b;

import com.google.p028a.C1365t;

/* JADX INFO: renamed from: com.google.a.i.b.d */
/* JADX INFO: compiled from: FinderPattern.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1346d extends C1365t {

    /* JADX INFO: renamed from: c */
    public final float f2780c;

    /* JADX INFO: renamed from: d */
    final int f2781d;

    C1346d(float f, float f2, float f3) {
        this(f, f2, f3, 1);
    }

    C1346d(float f, float f2, float f3, int i) {
        super(f, f2);
        this.f2780c = f3;
        this.f2781d = i;
    }

    /* JADX INFO: renamed from: a */
    private float m2508a() {
        return this.f2780c;
    }

    /* JADX INFO: renamed from: b */
    private int m2510b() {
        return this.f2781d;
    }

    /* JADX INFO: renamed from: b */
    private C1346d m2511b(float f, float f2, float f3) {
        int i = this.f2781d + 1;
        float f4 = (this.f2781d * this.f2858a) + f2;
        float f5 = i;
        return new C1346d(f4 / f5, ((this.f2781d * this.f2859b) + f) / f5, ((this.f2781d * this.f2780c) + f3) / f5, i);
    }

    /* JADX INFO: renamed from: a */
    private boolean m2509a(float f, float f2, float f3) {
        if (Math.abs(f2 - this.f2859b) > f || Math.abs(f3 - this.f2858a) > f) {
            return false;
        }
        float fAbs = Math.abs(f - this.f2780c);
        return fAbs <= 1.0f || fAbs <= this.f2780c;
    }
}
