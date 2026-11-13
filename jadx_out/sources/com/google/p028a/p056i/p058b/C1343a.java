package com.google.p028a.p056i.p058b;

import com.google.p028a.C1365t;

/* JADX INFO: renamed from: com.google.a.i.b.a */
/* JADX INFO: compiled from: AlignmentPattern.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1343a extends C1365t {

    /* JADX INFO: renamed from: c */
    final float f2768c;

    C1343a(float f, float f2, float f3) {
        super(f, f2);
        this.f2768c = f3;
    }

    /* JADX INFO: renamed from: a */
    private boolean m2488a(float f, float f2, float f3) {
        if (Math.abs(f2 - this.f2859b) > f || Math.abs(f3 - this.f2858a) > f) {
            return false;
        }
        float fAbs = Math.abs(f - this.f2768c);
        return fAbs <= 1.0f || fAbs <= this.f2768c;
    }

    /* JADX INFO: renamed from: b */
    private C1343a m2489b(float f, float f2, float f3) {
        return new C1343a((this.f2858a + f2) / 2.0f, (this.f2859b + f) / 2.0f, (this.f2768c + f3) / 2.0f);
    }
}
