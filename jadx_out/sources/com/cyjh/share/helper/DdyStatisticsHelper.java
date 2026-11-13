package com.cyjh.share.helper;

import android.content.Context;
import android.os.Message;
import com.cyjh.share.util.SlLog;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes.dex */
public class DdyStatisticsHelper {
    public static final int MSG_DDY_DAILY_STATISTICS = 1000;
    private static String TAG = "DdyStatisticsHelper";
    public static final int TIMER_DELAY = 180000;
    public static final int TIMER_PERIOD = 7200000;
    private static DdyStatisticsHelper instance;
    private DdyDailyStatisticsListener mCallback;
    private Context mContext;
    private Timer mTimer;
    private MyTimerTask mTimerTask;

    public interface DdyDailyStatisticsListener {
        void msgDdyDailyStatistics(Message message);
    }

    private DdyStatisticsHelper() {
    }

    public static DdyStatisticsHelper get() {
        if (instance == null) {
            synchronized (DdyStatisticsHelper.class) {
                if (instance == null) {
                    instance = new DdyStatisticsHelper();
                }
            }
        }
        return instance;
    }

    public DdyStatisticsHelper init(Context context, DdyDailyStatisticsListener ddyDailyStatisticsListener) {
        this.mContext = context;
        this.mCallback = ddyDailyStatisticsListener;
        return this;
    }

    public void startTimer() {
        SlLog.m1332d(TAG, "startTimer --> ");
        if (this.mTimer == null) {
            this.mTimer = new Timer();
            this.mTimerTask = new MyTimerTask();
        }
        this.mTimer.schedule(this.mTimerTask, 180000L, 7200000L);
    }

    public void stopTimer() {
        SlLog.m1332d(TAG, "stopTimer --> ");
        if (this.mTimer != null) {
            this.mTimer.cancel();
            this.mTimer = null;
        }
    }

    private class MyTimerTask extends TimerTask {
        private MyTimerTask() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            Message message = new Message();
            message.what = 1000;
            if (DdyStatisticsHelper.this.mCallback != null) {
                DdyStatisticsHelper.this.mCallback.msgDdyDailyStatistics(message);
            }
        }
    }
}
