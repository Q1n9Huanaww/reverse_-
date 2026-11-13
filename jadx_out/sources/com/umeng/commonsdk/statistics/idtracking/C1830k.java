package com.umeng.commonsdk.statistics.idtracking;

import com.umeng.commonsdk.statistics.common.DeviceConfig;

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.idtracking.k */
/* JADX INFO: compiled from: SerialTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class C1830k extends AbstractC1820a {

    /* JADX INFO: renamed from: a */
    private static final String f4588a = "serial";

    public C1830k() {
        super(f4588a);
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.AbstractC1820a
    /* JADX INFO: renamed from: f */
    public String mo4443f() {
        return DeviceConfig.getSerial();
    }
}
