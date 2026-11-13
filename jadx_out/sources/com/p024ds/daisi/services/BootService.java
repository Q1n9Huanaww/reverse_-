package com.p024ds.daisi.services;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.p024ds.daisi.AppContext;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class BootService extends BaseService {
    public static final int MSG_DEFAULT = 272;
    public static final int START_DELAY = 1;
    private ScheduledFuture<?> mFuture;
    private ScheduledExecutorService mScheduledExecutorService;
    private MyServiceHandler mServiceHandler = new MyServiceHandler();

    private static class MyServiceHandler extends Handler {
        private MyServiceHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 272) {
                Log.e("zzz", "BootService开机启动运行脚本--");
                AppContext.getInstance().showFloatView();
                AppContext.getInstance().STATE_SCRIPT = 1;
                AppContext.getInstance().runScript();
            }
        }
    }

    @Override // com.p024ds.daisi.services.BaseService, android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mScheduledExecutorService = new ScheduledThreadPoolExecutor(1);
    }

    @Override // com.p024ds.daisi.services.BaseService, android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        this.mFuture = this.mScheduledExecutorService.scheduleAtFixedRate(new TimerTask() { // from class: com.ds.daisi.services.BootService.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (AppContext.getInstance().isInitEngineComplete) {
                    BootService.this.mServiceHandler.obtainMessage(BootService.MSG_DEFAULT).sendToTarget();
                    AppContext.getInstance().isInitEngineComplete = false;
                    if (BootService.this.mFuture != null) {
                        BootService.this.mFuture.cancel(true);
                    }
                }
            }
        }, 0L, 1L, TimeUnit.SECONDS);
        return super.onStartCommand(intent, i, i2);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.mServiceHandler.removeCallbacksAndMessages(null);
        if (this.mFuture != null) {
            this.mFuture.cancel(true);
        }
    }
}
