package com.google.p028a.p047g.p048a;

import android.support.v7.widget.ActivityChooserView;
import com.google.p028a.C1358m;
import com.google.p028a.p035c.p036a.C1191a;
import com.google.p028a.p047g.AbstractC1294r;

/* JADX INFO: renamed from: com.google.a.g.a.a */
/* JADX INFO: compiled from: AbstractRSSReader.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1247a extends AbstractC1294r {

    /* JADX INFO: renamed from: g */
    private static final float f2346g = 0.2f;

    /* JADX INFO: renamed from: h */
    private static final float f2347h = 0.45f;

    /* JADX INFO: renamed from: i */
    private static final float f2348i = 0.7916667f;

    /* JADX INFO: renamed from: j */
    private static final float f2349j = 0.89285713f;

    /* JADX INFO: renamed from: a */
    protected final int[] f2350a = new int[4];

    /* JADX INFO: renamed from: b */
    protected final int[] f2351b = new int[8];

    /* JADX INFO: renamed from: c */
    protected final float[] f2352c = new float[4];

    /* JADX INFO: renamed from: d */
    protected final float[] f2353d = new float[4];

    /* JADX INFO: renamed from: e */
    protected final int[] f2354e = new int[this.f2351b.length / 2];

    /* JADX INFO: renamed from: f */
    protected final int[] f2355f = new int[this.f2351b.length / 2];

    protected AbstractC1247a() {
    }

    /* JADX INFO: renamed from: b */
    private int[] m2044b() {
        return this.f2350a;
    }

    /* JADX INFO: renamed from: c */
    private int[] m2045c() {
        return this.f2351b;
    }

    /* JADX INFO: renamed from: d */
    private float[] m2046d() {
        return this.f2352c;
    }

    /* JADX INFO: renamed from: e */
    private float[] m2047e() {
        return this.f2353d;
    }

    /* JADX INFO: renamed from: f */
    private int[] m2048f() {
        return this.f2354e;
    }

    /* JADX INFO: renamed from: g */
    private int[] m2049g() {
        return this.f2355f;
    }

    /* JADX INFO: renamed from: a */
    protected static int m2039a(int[] iArr, int[][] iArr2) throws C1358m {
        for (int i = 0; i < iArr2.length; i++) {
            if (m2192a(iArr, iArr2[i], f2347h) < f2346g) {
                return i;
            }
        }
        throw C1358m.m2611a();
    }

    @Deprecated
    /* JADX INFO: renamed from: b */
    private static int m2042b(int[] iArr) {
        return C1191a.m1766a(iArr);
    }

    /* JADX INFO: renamed from: a */
    protected static void m2040a(int[] iArr, float[] fArr) {
        float f = fArr[0];
        int i = 0;
        for (int i2 = 1; i2 < iArr.length; i2++) {
            if (fArr[i2] > f) {
                f = fArr[i2];
                i = i2;
            }
        }
        iArr[i] = iArr[i] + 1;
    }

    /* JADX INFO: renamed from: b */
    protected static void m2043b(int[] iArr, float[] fArr) {
        float f = fArr[0];
        int i = 0;
        for (int i2 = 1; i2 < iArr.length; i2++) {
            if (fArr[i2] < f) {
                f = fArr[i2];
                i = i2;
            }
        }
        iArr[i] = iArr[i] - 1;
    }

    /* JADX INFO: renamed from: a */
    protected static boolean m2041a(int[] iArr) {
        int i = iArr[0] + iArr[1];
        float f = i / ((iArr[2] + i) + iArr[3]);
        if (f < f2348i || f > f2349j) {
            return false;
        }
        int i2 = Integer.MIN_VALUE;
        int i3 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        for (int i4 : iArr) {
            if (i4 > i2) {
                i2 = i4;
            }
            if (i4 < i3) {
                i3 = i4;
            }
        }
        return i2 < i3 * 10;
    }
}
