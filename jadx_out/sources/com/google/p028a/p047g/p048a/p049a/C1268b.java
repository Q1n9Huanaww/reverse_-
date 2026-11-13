package com.google.p028a.p047g.p048a.p049a;

import com.google.p028a.p047g.p048a.C1271b;
import com.google.p028a.p047g.p048a.C1272c;

/* JADX INFO: renamed from: com.google.a.g.a.a.b */
/* JADX INFO: compiled from: ExpandedPair.java */
/* JADX INFO: loaded from: classes.dex */
final class C1268b {

    /* JADX INFO: renamed from: a */
    final C1271b f2398a;

    /* JADX INFO: renamed from: b */
    final C1271b f2399b;

    /* JADX INFO: renamed from: c */
    final C1272c f2400c;

    /* JADX INFO: renamed from: d */
    private final boolean f2401d = true;

    C1268b(C1271b c1271b, C1271b c1271b2, C1272c c1272c) {
        this.f2398a = c1271b;
        this.f2399b = c1271b2;
        this.f2400c = c1272c;
    }

    /* JADX INFO: renamed from: a */
    private boolean m2107a() {
        return this.f2401d;
    }

    /* JADX INFO: renamed from: b */
    private C1271b m2109b() {
        return this.f2398a;
    }

    /* JADX INFO: renamed from: c */
    private C1271b m2110c() {
        return this.f2399b;
    }

    /* JADX INFO: renamed from: d */
    private C1272c m2111d() {
        return this.f2400c;
    }

    /* JADX INFO: renamed from: e */
    private boolean m2112e() {
        return this.f2399b == null;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        sb.append(this.f2398a);
        sb.append(" , ");
        sb.append(this.f2399b);
        sb.append(" : ");
        sb.append(this.f2400c == null ? "null" : Integer.valueOf(this.f2400c.f2424a));
        sb.append(" ]");
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C1268b)) {
            return false;
        }
        C1268b c1268b = (C1268b) obj;
        return m2108a(this.f2398a, c1268b.f2398a) && m2108a(this.f2399b, c1268b.f2399b) && m2108a(this.f2400c, c1268b.f2400c);
    }

    /* JADX INFO: renamed from: a */
    private static boolean m2108a(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    public final int hashCode() {
        return (m2106a(this.f2398a) ^ m2106a(this.f2399b)) ^ m2106a(this.f2400c);
    }

    /* JADX INFO: renamed from: a */
    private static int m2106a(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }
}
