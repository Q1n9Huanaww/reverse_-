package com.google.p028a.p047g.p048a.p049a;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: com.google.a.g.a.a.c */
/* JADX INFO: compiled from: ExpandedRow.java */
/* JADX INFO: loaded from: classes.dex */
final class C1269c {

    /* JADX INFO: renamed from: a */
    final List<C1268b> f2402a;

    /* JADX INFO: renamed from: b */
    final int f2403b;

    /* JADX INFO: renamed from: c */
    private final boolean f2404c = false;

    C1269c(List<C1268b> list, int i) {
        this.f2402a = new ArrayList(list);
        this.f2403b = i;
    }

    /* JADX INFO: renamed from: a */
    private List<C1268b> m2113a() {
        return this.f2402a;
    }

    /* JADX INFO: renamed from: b */
    private int m2114b() {
        return this.f2403b;
    }

    /* JADX INFO: renamed from: c */
    private boolean m2115c() {
        return this.f2404c;
    }

    /* JADX INFO: renamed from: a */
    final boolean m2116a(List<C1268b> list) {
        return this.f2402a.equals(list);
    }

    public final String toString() {
        return "{ " + this.f2402a + " }";
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C1269c)) {
            return false;
        }
        C1269c c1269c = (C1269c) obj;
        return this.f2402a.equals(c1269c.f2402a) && this.f2404c == c1269c.f2404c;
    }

    public final int hashCode() {
        return this.f2402a.hashCode() ^ Boolean.valueOf(this.f2404c).hashCode();
    }
}
