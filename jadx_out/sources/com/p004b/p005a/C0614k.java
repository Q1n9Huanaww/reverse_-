package com.p004b.p005a;

import com.p004b.p015b.C0639i;

/* JADX INFO: renamed from: com.b.a.k */
/* JADX INFO: compiled from: CertificatePinner.java */
/* JADX INFO: loaded from: classes.dex */
final class C0614k {

    /* JADX INFO: renamed from: a */
    final String f629a;

    /* JADX INFO: renamed from: b */
    final String f630b;

    /* JADX INFO: renamed from: c */
    final String f631c;

    /* JADX INFO: renamed from: d */
    final C0639i f632d;

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0614k)) {
            return false;
        }
        C0614k c0614k = (C0614k) obj;
        return this.f629a.equals(c0614k.f629a) && this.f631c.equals(c0614k.f631c) && this.f632d.equals(c0614k.f632d);
    }

    public final int hashCode() {
        return ((((this.f629a.hashCode() + 527) * 31) + this.f631c.hashCode()) * 31) + this.f632d.hashCode();
    }

    public final String toString() {
        return this.f631c + this.f632d.mo585b();
    }
}
