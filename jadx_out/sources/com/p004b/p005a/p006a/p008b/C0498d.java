package com.p004b.p005a.p006a.p008b;

import com.p004b.p005a.C0603aw;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: renamed from: com.b.a.a.b.d */
/* JADX INFO: compiled from: RouteDatabase.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0498d {

    /* JADX INFO: renamed from: a */
    private final Set<C0603aw> f145a = new LinkedHashSet();

    /* JADX INFO: renamed from: a */
    public final synchronized void m77a(C0603aw c0603aw) {
        this.f145a.add(c0603aw);
    }

    /* JADX INFO: renamed from: b */
    public final synchronized void m78b(C0603aw c0603aw) {
        this.f145a.remove(c0603aw);
    }

    /* JADX INFO: renamed from: c */
    public final synchronized boolean m79c(C0603aw c0603aw) {
        return this.f145a.contains(c0603aw);
    }
}
