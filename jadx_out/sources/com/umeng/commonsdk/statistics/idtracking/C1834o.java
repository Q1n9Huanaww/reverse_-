package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.proguard.C1794q;

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.idtracking.o */
/* JADX INFO: compiled from: UMTTThreeTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class C1834o extends AbstractC1820a {

    /* JADX INFO: renamed from: a */
    private static final String f4595a = "umtt3";

    /* JADX INFO: renamed from: b */
    private Context f4596b;

    public C1834o(Context context) {
        super(f4595a);
        this.f4596b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.AbstractC1820a
    /* JADX INFO: renamed from: f */
    public String mo4443f() {
        return new C1794q(this.f4596b).m4063e();
    }
}
