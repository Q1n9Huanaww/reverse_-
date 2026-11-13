package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.proguard.C1794q;

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.idtracking.q */
/* JADX INFO: compiled from: UMTTZeroTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class C1836q extends AbstractC1820a {

    /* JADX INFO: renamed from: a */
    private static final String f4599a = "umtt0";

    /* JADX INFO: renamed from: b */
    private Context f4600b;

    public C1836q(Context context) {
        super(f4599a);
        this.f4600b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.AbstractC1820a
    /* JADX INFO: renamed from: f */
    public String mo4443f() {
        return new C1794q(this.f4600b).m4060b();
    }
}
