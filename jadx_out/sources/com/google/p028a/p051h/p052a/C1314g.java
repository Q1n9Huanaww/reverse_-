package com.google.p028a.p051h.p052a;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.Formatter;

/* JADX INFO: renamed from: com.google.a.h.a.g */
/* JADX INFO: compiled from: DetectionResultColumn.java */
/* JADX INFO: loaded from: classes.dex */
class C1314g {

    /* JADX INFO: renamed from: c */
    private static final int f2624c = 5;

    /* JADX INFO: renamed from: a */
    final C1310c f2625a;

    /* JADX INFO: renamed from: b */
    final C1311d[] f2626b;

    C1314g(C1310c c1310c) {
        this.f2625a = new C1310c(c1310c);
        this.f2626b = new C1311d[(c1310c.f2574i - c1310c.f2573h) + 1];
    }

    /* JADX INFO: renamed from: a */
    final C1311d m2302a(int i) {
        C1311d c1311d;
        C1311d c1311d2;
        C1311d c1311dM2305c = m2305c(i);
        if (c1311dM2305c != null) {
            return c1311dM2305c;
        }
        for (int i2 = 1; i2 < 5; i2++) {
            int iM2304b = m2304b(i) - i2;
            if (iM2304b >= 0 && (c1311d2 = this.f2626b[iM2304b]) != null) {
                return c1311d2;
            }
            int iM2304b2 = m2304b(i) + i2;
            if (iM2304b2 < this.f2626b.length && (c1311d = this.f2626b[iM2304b2]) != null) {
                return c1311d;
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: b */
    final int m2304b(int i) {
        return i - this.f2625a.f2573h;
    }

    /* JADX INFO: renamed from: a */
    final void m2303a(int i, C1311d c1311d) {
        this.f2626b[m2304b(i)] = c1311d;
    }

    /* JADX INFO: renamed from: c */
    final C1311d m2305c(int i) {
        return this.f2626b[m2304b(i)];
    }

    /* JADX INFO: renamed from: a */
    private C1310c m2300a() {
        return this.f2625a;
    }

    /* JADX INFO: renamed from: b */
    private C1311d[] m2301b() {
        return this.f2626b;
    }

    public String toString() {
        Formatter formatter = new Formatter();
        try {
            int i = 0;
            for (C1311d c1311d : this.f2626b) {
                if (c1311d == null) {
                    formatter.format("%3d:    |   %n", Integer.valueOf(i));
                    i++;
                } else {
                    formatter.format("%3d: %3d|%3d%n", Integer.valueOf(i), Integer.valueOf(c1311d.f2580e), Integer.valueOf(c1311d.f2579d));
                    i++;
                }
            }
            String string = formatter.toString();
            formatter.close();
            return string;
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    formatter.close();
                } catch (Throwable th2) {
                    ThrowableExtension.addSuppressed(null, th2);
                }
            } else {
                formatter.close();
            }
            throw th;
        }
    }
}
