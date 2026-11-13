package com.p024ds.daisi.services;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.p024ds.daisi.activity.news.SplashActivity;
import com.p024ds.daisi.util.NetworkUtils;

/* JADX INFO: loaded from: classes.dex */
public class TimerService extends BaseService {
    private static final int NETWORK_LOOP_COUNT = 5;
    private static final int NETWORK_LOOP_DELAY_TIME = 200;
    private static final int NETWORK_LOOP_MESSAGE = 272;
    private Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.ds.daisi.services.TimerService.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 272) {
                return;
            }
            Intent intent = new Intent(TimerService.this, (Class<?>) SplashActivity.class);
            intent.addFlags(268435456);
            TimerService.this.startActivity(intent);
        }
    };
    private TimerRunnable mTimerRunnable;

    @Override // com.p024ds.daisi.services.BaseService, android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        this.mTimerRunnable = new TimerRunnable();
        this.mTimerRunnable.onStart();
        return super.onStartCommand(intent, i, i2);
    }

    class TimerRunnable implements Runnable {
        private int count = 0;

        TimerRunnable() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onStart() {
            TimerService.this.mHandler.removeCallbacks(this);
            TimerService.this.mHandler.postDelayed(this, 200L);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!NetworkUtils.isAvailable(TimerService.this)) {
                TimerService.this.mHandler.removeCallbacks(this);
                TimerService.this.mHandler.postDelayed(this, 200L);
                return;
            }
            this.count++;
            Log.e("zzz", "TimerService--run--" + this.count);
            if (this.count <= 5) {
                TimerService.this.mHandler.removeCallbacks(this);
                TimerService.this.mHandler.postDelayed(this, 200L);
            } else {
                TimerService.this.mHandler.removeCallbacks(this);
                TimerService.this.mHandler.obtainMessage(272).sendToTarget();
            }
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        if (this.mTimerRunnable != null) {
            this.mHandler.removeCallbacks(this.mTimerRunnable);
            this.mTimerRunnable = null;
        }
    }
}
