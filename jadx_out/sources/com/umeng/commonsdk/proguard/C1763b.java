package com.umeng.commonsdk.proguard;

import android.content.Context;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.b */
/* JADX INFO: compiled from: UMInternalData.java */
/* JADX INFO: loaded from: classes.dex */
public class C1763b {

    /* JADX INFO: renamed from: b */
    private static C1763b f4098b;

    /* JADX INFO: renamed from: a */
    private Context f4099a;

    /* JADX INFO: renamed from: c */
    private C1780c f4100c;

    private C1763b(Context context) {
        this.f4099a = context;
        this.f4100c = new C1780c(context);
    }

    /* JADX INFO: renamed from: a */
    public static synchronized C1763b m3919a(Context context) {
        if (f4098b == null) {
            f4098b = new C1763b(context.getApplicationContext());
        }
        return f4098b;
    }

    /* JADX INFO: renamed from: a */
    public C1780c m3920a() {
        return this.f4100c;
    }
}
