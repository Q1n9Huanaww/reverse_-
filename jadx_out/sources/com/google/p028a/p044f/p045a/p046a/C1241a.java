package com.google.p028a.p044f.p045a.p046a;

import com.google.p028a.AbstractC1362q;
import com.google.p028a.C1358m;
import com.google.p028a.p035c.C1194b;
import com.google.p028a.p035c.C1204g;
import com.google.p028a.p056i.p058b.C1345c;
import com.google.p028a.p056i.p058b.C1348f;
import java.util.ArrayList;

/* JADX INFO: renamed from: com.google.a.f.a.a.a */
/* JADX INFO: compiled from: MultiDetector.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1241a extends C1345c {

    /* JADX INFO: renamed from: b */
    private static final C1204g[] f2314b = new C1204g[0];

    public C1241a(C1194b c1194b) {
        super(c1194b);
    }

    /* JADX INFO: renamed from: a */
    public final C1204g[] m2026a() throws C1358m {
        C1348f[] c1348fArrM2028a = new C1242b(this.f2778a, (byte) 0).m2028a();
        if (c1348fArrM2028a.length == 0) {
            throw C1358m.m2611a();
        }
        ArrayList arrayList = new ArrayList();
        for (C1348f c1348f : c1348fArrM2028a) {
            try {
                arrayList.add(m2506a(c1348f));
            } catch (AbstractC1362q unused) {
            }
        }
        if (arrayList.isEmpty()) {
            return f2314b;
        }
        return (C1204g[]) arrayList.toArray(new C1204g[arrayList.size()]);
    }
}
