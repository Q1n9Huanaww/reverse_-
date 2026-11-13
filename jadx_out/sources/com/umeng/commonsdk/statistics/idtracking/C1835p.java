package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.proguard.C1794q;

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.idtracking.p */
/* JADX INFO: compiled from: UMTTTwoTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class C1835p extends AbstractC1820a {

    /* JADX INFO: renamed from: a */
    private static final String f4597a = "umtt2";

    /* JADX INFO: renamed from: b */
    private Context f4598b;

    public C1835p(Context context) {
        super(f4597a);
        this.f4598b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.AbstractC1820a
    /* JADX INFO: renamed from: f */
    public String mo4443f() {
        return new C1794q(this.f4598b).m4062d();
    }
}
