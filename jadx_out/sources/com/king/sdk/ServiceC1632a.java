package com.king.sdk;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/* JADX INFO: renamed from: com.king.sdk.a */
/* JADX INFO: compiled from: ClientService.java */
/* JADX INFO: loaded from: classes.dex */
public final class ServiceC1632a extends Service {

    /* JADX INFO: renamed from: a */
    private static InterfaceC1634b f3580a;

    /* JADX INFO: renamed from: b */
    private AbstractBinderC1638f f3581b;

    /* JADX INFO: renamed from: a */
    public static InterfaceC1634b m3372a() {
        return f3580a;
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.f3581b;
    }
}
