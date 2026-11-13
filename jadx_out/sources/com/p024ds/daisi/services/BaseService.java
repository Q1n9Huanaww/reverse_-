package com.p024ds.daisi.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.constant.APPConstant;
import com.p024ds.daisi.log.CommonLog;

/* JADX INFO: loaded from: classes.dex */
public class BaseService extends Service implements APPConstant {
    protected AppContext appContext;
    protected CommonLog commonLog = new CommonLog();

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.appContext = (AppContext) getApplicationContext();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }
}
