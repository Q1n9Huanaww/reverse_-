package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.proguard.C1794q;

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.idtracking.n */
/* JADX INFO: compiled from: UMTTOneTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class C1833n extends AbstractC1820a {

    /* JADX INFO: renamed from: a */
    private static final String f4593a = "umtt1";

    /* JADX INFO: renamed from: b */
    private Context f4594b;

    public C1833n(Context context) {
        super(f4593a);
        this.f4594b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.AbstractC1820a
    /* JADX INFO: renamed from: f */
    public String mo4443f() {
        return new C1794q(this.f4594b).m4061c();
    }
}
