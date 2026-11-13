package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.proguard.C1799v;

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.idtracking.i */
/* JADX INFO: compiled from: NewUMIDTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class C1828i extends AbstractC1820a {

    /* JADX INFO: renamed from: a */
    private static final String f4582a = "newumid";

    /* JADX INFO: renamed from: b */
    private Context f4583b;

    public C1828i(Context context) {
        super(f4582a);
        this.f4583b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.AbstractC1820a
    /* JADX INFO: renamed from: f */
    public String mo4443f() {
        return UMEnvelopeBuild.imprintProperty(this.f4583b, C1799v.f4314e, null);
    }
}
