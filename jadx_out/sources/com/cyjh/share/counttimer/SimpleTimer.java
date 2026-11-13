package com.cyjh.share.counttimer;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;

/* JADX INFO: loaded from: classes.dex */
public abstract class SimpleTimer extends CountDownTimer {
    private final int mCell;
    private Handler mHandler;

    protected abstract void onTick(int i);

    public SimpleTimer(int i, int i2) {
        super(i * 1000, i2 * 1000);
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mCell = i2;
    }

    @Override // android.os.CountDownTimer
    public void onTick(long j) {
        int iRound = Math.round((j * 1.0f) / 1000.0f);
        if (iRound == this.mCell * 2) {
            this.mHandler.postDelayed(new Runnable() { // from class: com.cyjh.share.counttimer.SimpleTimer.1
                @Override // java.lang.Runnable
                public void run() {
                    SimpleTimer.this.onTick(SimpleTimer.this.mCell);
                }
            }, this.mCell * 1000);
        }
        onTick(iRound);
    }
}
