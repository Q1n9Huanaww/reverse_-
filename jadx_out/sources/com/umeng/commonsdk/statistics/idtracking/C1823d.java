package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.statistics.common.DeviceConfig;

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.idtracking.d */
/* JADX INFO: compiled from: IDMD5Tracker.java */
/* JADX INFO: loaded from: classes.dex */
public class C1823d extends AbstractC1820a {

    /* JADX INFO: renamed from: a */
    private static final String f4554a = "idmd5";

    /* JADX INFO: renamed from: b */
    private Context f4555b;

    public C1823d(Context context) {
        super("idmd5");
        this.f4555b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.AbstractC1820a
    /* JADX INFO: renamed from: f */
    public String mo4443f() {
        return DeviceConfig.getDeviceIdUmengMD5(this.f4555b);
    }
}
