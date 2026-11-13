package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.statistics.common.DeviceConfig;

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.idtracking.f */
/* JADX INFO: compiled from: ImeiTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class C1825f extends AbstractC1820a {

    /* JADX INFO: renamed from: a */
    private static final String f4568a = "imei";

    /* JADX INFO: renamed from: b */
    private Context f4569b;

    public C1825f(Context context) {
        super(f4568a);
        this.f4569b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.AbstractC1820a
    /* JADX INFO: renamed from: f */
    public String mo4443f() {
        return DeviceConfig.getImei(this.f4569b);
    }
}
