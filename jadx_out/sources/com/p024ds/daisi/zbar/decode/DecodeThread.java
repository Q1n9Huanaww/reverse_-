package com.p024ds.daisi.zbar.decode;

import android.os.Handler;
import android.os.Looper;
import com.p024ds.daisi.activity.SweepCodeActivity;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: classes.dex */
final class DecodeThread extends Thread {
    SweepCodeActivity activity;
    private Handler handler;
    private final CountDownLatch handlerInitLatch = new CountDownLatch(1);

    DecodeThread(SweepCodeActivity sweepCodeActivity) {
        this.activity = sweepCodeActivity;
    }

    Handler getHandler() {
        try {
            this.handlerInitLatch.await();
        } catch (InterruptedException unused) {
        }
        return this.handler;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Looper.prepare();
        this.handler = new DecodeHandler(this.activity);
        this.handlerInitLatch.countDown();
        Looper.loop();
    }
}
