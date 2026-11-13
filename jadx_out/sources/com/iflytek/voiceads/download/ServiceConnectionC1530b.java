package com.iflytek.voiceads.download;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/* JADX INFO: renamed from: com.iflytek.voiceads.download.b */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class ServiceConnectionC1530b implements ServiceConnection {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C1527a f3204a;

    ServiceConnectionC1530b(C1527a c1527a) {
        this.f3204a = c1527a;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f3204a.f3202i = (DownloadService.BinderC1526a) iBinder;
        this.f3204a.f3202i.m2930a(this.f3204a.f3201h);
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        this.f3204a.f3202i = null;
    }
}
