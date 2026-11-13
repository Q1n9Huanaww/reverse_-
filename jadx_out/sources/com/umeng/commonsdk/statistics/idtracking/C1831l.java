package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.proguard.C1794q;

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.idtracking.l */
/* JADX INFO: compiled from: UMTTFiveTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class C1831l extends AbstractC1820a {

    /* JADX INFO: renamed from: a */
    private static final String f4589a = "umtt5";

    /* JADX INFO: renamed from: b */
    private Context f4590b;

    public C1831l(Context context) {
        super(f4589a);
        this.f4590b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.AbstractC1820a
    /* JADX INFO: renamed from: f */
    public String mo4443f() {
        return new C1794q(this.f4590b).m4065g();
    }
}
