package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.statistics.common.DeviceConfig;

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.idtracking.b */
/* JADX INFO: compiled from: AndroidIdTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class C1821b extends AbstractC1820a {

    /* JADX INFO: renamed from: a */
    private static final String f4550a = "android_id";

    /* JADX INFO: renamed from: b */
    private Context f4551b;

    public C1821b(Context context) {
        super("android_id");
        this.f4551b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.AbstractC1820a
    /* JADX INFO: renamed from: f */
    public String mo4443f() {
        return DeviceConfig.getAndroidId(this.f4551b);
    }
}
