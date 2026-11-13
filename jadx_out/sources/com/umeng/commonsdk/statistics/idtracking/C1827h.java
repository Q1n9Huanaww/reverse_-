package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.umeng.commonsdk.proguard.C1782e;
import com.umeng.commonsdk.statistics.C1811b;
import com.umeng.commonsdk.statistics.common.DeviceConfig;

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.idtracking.h */
/* JADX INFO: compiled from: MacTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class C1827h extends AbstractC1820a {

    /* JADX INFO: renamed from: a */
    private static final String f4580a = "mac";

    /* JADX INFO: renamed from: b */
    private Context f4581b;

    public C1827h(Context context) {
        super(f4580a);
        this.f4581b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.AbstractC1820a
    /* JADX INFO: renamed from: f */
    public String mo4443f() {
        try {
            return DeviceConfig.getMac(this.f4581b);
        } catch (Exception e) {
            if (C1811b.f4488f) {
                ThrowableExtension.printStackTrace(e);
            }
            C1782e.m3972a(this.f4581b, e);
            return null;
        }
    }
}
