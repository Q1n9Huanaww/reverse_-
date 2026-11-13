package com.googlecode.leptonica.android;

import android.graphics.Rect;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class Pixa implements Iterable<Pix> {

    /* JADX INFO: renamed from: e */
    private static final String f2931e = "Pixa";

    /* JADX INFO: renamed from: a */
    final long f2932a;

    /* JADX INFO: renamed from: b */
    final int f2933b;

    /* JADX INFO: renamed from: c */
    final int f2934c;

    /* JADX INFO: renamed from: d */
    boolean f2935d = false;

    private static native void nativeAdd(long j, long j2, long j3, int i);

    private static native void nativeAddBox(long j, long j2, int i);

    private static native void nativeAddPix(long j, long j2, int i);

    private static native int nativeCopy(long j);

    private static native int nativeCreate(int i);

    private static native void nativeDestroy(long j);

    private static native long nativeGetBox(long j, int i);

    private static native boolean nativeGetBoxGeometry(long j, int i, int[] iArr);

    static native int nativeGetCount(long j);

    static native long nativeGetPix(long j, int i);

    private static native boolean nativeJoin(long j, long j2);

    private static native void nativeMergeAndReplacePix(long j, int i, int i2);

    private static native void nativeReplacePix(long j, int i, long j2, long j3);

    private static native int nativeSort(long j, int i, int i2);

    private static native boolean nativeWriteToFileRandomCmap(long j, String str, int i, int i2);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    /* JADX INFO: renamed from: b */
    private static Pixa m2700b(int i) {
        long jNativeCreate = nativeCreate(i);
        if (jNativeCreate == 0) {
            throw new OutOfMemoryError();
        }
        return new Pixa(jNativeCreate, 0, 0);
    }

    public Pixa(long j, int i, int i2) {
        this.f2932a = j;
        this.f2933b = i;
        this.f2934c = i2;
    }

    /* JADX INFO: renamed from: a */
    private long m2689a() {
        if (this.f2935d) {
            throw new IllegalStateException();
        }
        return this.f2932a;
    }

    /* JADX INFO: renamed from: b */
    private Pixa m2699b() {
        if (this.f2935d) {
            throw new IllegalStateException();
        }
        int iNativeCopy = nativeCopy(this.f2932a);
        if (iNativeCopy == 0) {
            throw new OutOfMemoryError();
        }
        return new Pixa(iNativeCopy, this.f2933b, this.f2934c);
    }

    /* JADX INFO: renamed from: a */
    private Pixa m2691a(int i, int i2) {
        if (this.f2935d) {
            throw new IllegalStateException();
        }
        int iNativeSort = nativeSort(this.f2932a, i, i2);
        if (iNativeSort == 0) {
            throw new OutOfMemoryError();
        }
        return new Pixa(iNativeSort, this.f2933b, this.f2934c);
    }

    /* JADX INFO: renamed from: c */
    private int m2702c() {
        if (this.f2935d) {
            throw new IllegalStateException();
        }
        return nativeGetCount(this.f2932a);
    }

    /* JADX INFO: renamed from: d */
    private synchronized void m2705d() {
        if (!this.f2935d) {
            nativeDestroy(this.f2932a);
            this.f2935d = true;
        }
    }

    protected void finalize() throws Throwable {
        try {
            if (!this.f2935d) {
                Log.w(f2931e, "Pixa was not terminated using recycle()");
                m2705d();
            }
        } finally {
            super.finalize();
        }
    }

    /* JADX INFO: renamed from: a */
    private boolean m2697a(Pixa pixa) {
        if (this.f2935d) {
            throw new IllegalStateException();
        }
        return nativeJoin(this.f2932a, pixa.f2932a);
    }

    /* JADX INFO: renamed from: a */
    private void m2694a(Pix pix, int i) {
        if (this.f2935d) {
            throw new IllegalStateException();
        }
        nativeAddPix(this.f2932a, pix.m2684a(), i);
    }

    /* JADX INFO: renamed from: a */
    private void m2693a(Box box, int i) {
        if (this.f2935d) {
            throw new IllegalStateException();
        }
        nativeAddBox(this.f2932a, box.m2654a(), i);
    }

    /* JADX INFO: renamed from: a */
    private void m2695a(Pix pix, Box box, int i) {
        if (this.f2935d) {
            throw new IllegalStateException();
        }
        nativeAdd(this.f2932a, pix.m2684a(), box.m2654a(), i);
    }

    /* JADX INFO: renamed from: c */
    private Box m2703c(int i) {
        if (this.f2935d) {
            throw new IllegalStateException();
        }
        long jNativeGetBox = nativeGetBox(this.f2932a, i);
        if (jNativeGetBox == 0) {
            return null;
        }
        return new Box(jNativeGetBox);
    }

    /* JADX INFO: renamed from: d */
    private Pix m2704d(int i) {
        if (this.f2935d) {
            throw new IllegalStateException();
        }
        long jNativeGetPix = nativeGetPix(this.f2932a, i);
        if (jNativeGetPix == 0) {
            return null;
        }
        return new Pix(jNativeGetPix);
    }

    /* JADX INFO: renamed from: e */
    private int m2706e() {
        if (this.f2935d) {
            throw new IllegalStateException();
        }
        return this.f2933b;
    }

    /* JADX INFO: renamed from: f */
    private int m2708f() {
        if (this.f2935d) {
            throw new IllegalStateException();
        }
        return this.f2934c;
    }

    /* JADX INFO: renamed from: g */
    private Rect m2710g() {
        if (this.f2935d) {
            throw new IllegalStateException();
        }
        return new Rect(0, 0, this.f2933b, this.f2934c);
    }

    /* JADX INFO: renamed from: e */
    private int[] m2707e(int i) {
        if (this.f2935d) {
            throw new IllegalStateException();
        }
        int[] iArr = new int[4];
        if (m2696a(i, iArr)) {
            return iArr;
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    private boolean m2696a(int i, int[] iArr) {
        if (this.f2935d) {
            throw new IllegalStateException();
        }
        return nativeGetBoxGeometry(this.f2932a, i, iArr);
    }

    /* JADX INFO: renamed from: h */
    private ArrayList<Rect> m2711h() {
        if (this.f2935d) {
            throw new IllegalStateException();
        }
        int iNativeGetCount = nativeGetCount(this.f2932a);
        int[] iArr = new int[4];
        ArrayList<Rect> arrayList = new ArrayList<>(iNativeGetCount);
        for (int i = 0; i < iNativeGetCount; i++) {
            m2696a(i, iArr);
            int i2 = iArr[0];
            int i3 = iArr[1];
            arrayList.add(new Rect(i2, i3, iArr[2] + i2, iArr[3] + i3));
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: a */
    private void m2692a(int i, Pix pix, Box box) {
        if (this.f2935d) {
            throw new IllegalStateException();
        }
        nativeReplacePix(this.f2932a, i, pix.m2684a(), box.m2654a());
    }

    /* JADX INFO: renamed from: b */
    private void m2701b(int i, int i2) {
        if (this.f2935d) {
            throw new IllegalStateException();
        }
        nativeMergeAndReplacePix(this.f2932a, i, i2);
    }

    /* JADX INFO: renamed from: a */
    private boolean m2698a(File file) {
        if (this.f2935d) {
            throw new IllegalStateException();
        }
        return nativeWriteToFileRandomCmap(this.f2932a, file.getAbsolutePath(), this.f2933b, this.f2934c);
    }

    @Override // java.lang.Iterable
    public Iterator<Pix> iterator() {
        return new C1469a(this, (byte) 0);
    }

    /* JADX INFO: renamed from: com.googlecode.leptonica.android.Pixa$a */
    private class C1469a implements Iterator<Pix> {

        /* JADX INFO: renamed from: b */
        private int f2937b;

        /* synthetic */ C1469a(Pixa pixa, byte b) {
            this();
        }

        private C1469a() {
            this.f2937b = 0;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            Pixa pixa = Pixa.this;
            if (pixa.f2935d) {
                throw new IllegalStateException();
            }
            int iNativeGetCount = Pixa.nativeGetCount(pixa.f2932a);
            return iNativeGetCount > 0 && this.f2937b < iNativeGetCount;
        }

        /* JADX INFO: renamed from: a */
        private Pix m2712a() {
            Pixa pixa = Pixa.this;
            int i = this.f2937b;
            this.f2937b = i + 1;
            if (pixa.f2935d) {
                throw new IllegalStateException();
            }
            long jNativeGetPix = Pixa.nativeGetPix(pixa.f2932a, i);
            if (jNativeGetPix == 0) {
                return null;
            }
            return new Pix(jNativeGetPix);
        }

        @Override // java.util.Iterator
        public final void remove() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Iterator
        public final /* synthetic */ Pix next() {
            Pixa pixa = Pixa.this;
            int i = this.f2937b;
            this.f2937b = i + 1;
            if (pixa.f2935d) {
                throw new IllegalStateException();
            }
            long jNativeGetPix = Pixa.nativeGetPix(pixa.f2932a, i);
            if (jNativeGetPix == 0) {
                return null;
            }
            return new Pix(jNativeGetPix);
        }
    }

    /* JADX INFO: renamed from: a */
    private static Pixa m2690a(int i) {
        long jNativeCreate = nativeCreate(i);
        if (jNativeCreate == 0) {
            throw new OutOfMemoryError();
        }
        return new Pixa(jNativeCreate, 0, 0);
    }

    /* JADX INFO: renamed from: f */
    private Rect m2709f(int i) {
        if (this.f2935d) {
            throw new IllegalStateException();
        }
        int[] iArr = new int[4];
        if (!m2696a(i, iArr)) {
            iArr = null;
        }
        if (iArr == null) {
            return null;
        }
        int i2 = iArr[0];
        int i3 = iArr[1];
        return new Rect(i2, i3, iArr[2] + i2, iArr[3] + i3);
    }
}
