package com.p024ds.daisi.timer;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

/* JADX INFO: loaded from: classes.dex */
public abstract class CountTimer {
    private static final int MSG = 1;
    private final long mCountdownInterval;
    private final long mMillisInFuture;
    private long mStopTimeInFuture;
    private boolean mCancelled = false;
    private Handler mHandler = new Handler() { // from class: com.ds.daisi.timer.CountTimer.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            synchronized (CountTimer.this) {
                if (CountTimer.this.mCancelled) {
                    return;
                }
                long jElapsedRealtime = CountTimer.this.mStopTimeInFuture - SystemClock.elapsedRealtime();
                if (jElapsedRealtime <= 0) {
                    CountTimer.this.onFinish();
                } else if (jElapsedRealtime < CountTimer.this.mCountdownInterval) {
                    sendMessageDelayed(obtainMessage(1), jElapsedRealtime);
                } else {
                    long jElapsedRealtime2 = SystemClock.elapsedRealtime();
                    CountTimer.this.onTick(jElapsedRealtime);
                    long jElapsedRealtime3 = (jElapsedRealtime2 + CountTimer.this.mCountdownInterval) - SystemClock.elapsedRealtime();
                    while (jElapsedRealtime3 < 0) {
                        jElapsedRealtime3 += CountTimer.this.mCountdownInterval;
                    }
                    sendMessageDelayed(obtainMessage(1), jElapsedRealtime3);
                }
            }
        }
    };

    public abstract void onFinish();

    public abstract void onTick(long j);

    public CountTimer(long j, long j2) {
        this.mMillisInFuture = j;
        this.mCountdownInterval = j2;
    }

    public final synchronized void cancel() {
        this.mCancelled = true;
        this.mHandler.removeMessages(1);
    }

    public final synchronized CountTimer start() {
        this.mCancelled = false;
        if (this.mMillisInFuture <= 0) {
            onFinish();
            return this;
        }
        this.mStopTimeInFuture = SystemClock.elapsedRealtime() + this.mMillisInFuture;
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1));
        return this;
    }
}
