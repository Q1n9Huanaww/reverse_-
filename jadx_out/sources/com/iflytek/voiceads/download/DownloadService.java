package com.iflytek.voiceads.download;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.iflytek.voiceads.download.p071d.C1542a;

/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class DownloadService extends Service {

    /* JADX INFO: renamed from: a */
    private BinderC1526a f3192a = new BinderC1526a();

    /* JADX INFO: renamed from: com.iflytek.voiceads.download.DownloadService$a */
    class BinderC1526a extends Binder {
        BinderC1526a() {
        }

        /* JADX INFO: renamed from: a */
        public void m2930a(C1542a c1542a) {
            C1527a.m2932a(DownloadService.this.getBaseContext()).mo2941b(c1542a);
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f3192a;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }
}
