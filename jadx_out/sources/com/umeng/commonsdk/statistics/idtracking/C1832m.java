package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.proguard.C1794q;

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.idtracking.m */
/* JADX INFO: compiled from: UMTTFourTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class C1832m extends AbstractC1820a {

    /* JADX INFO: renamed from: a */
    private static final String f4591a = "umtt4";

    /* JADX INFO: renamed from: b */
    private Context f4592b;

    public C1832m(Context context) {
        super(f4591a);
        this.f4592b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.AbstractC1820a
    /* JADX INFO: renamed from: f */
    public String mo4443f() {
        return new C1794q(this.f4592b).m4064f();
    }
}
