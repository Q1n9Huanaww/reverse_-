package com.p024ds.daisi.services;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.cyjh.p019mq.sdk.MqRunner;
import com.orhanobut.logger.Logger;
import com.p024ds.daisi.entity.TokenBean;
import com.p024ds.daisi.mvp.presenters.VerifyTokenPresenter;
import com.p024ds.daisi.mvp.views.CheckTokenView;
import com.p024ds.daisi.timer.CountTimer;
import com.p024ds.daisi.util.LogUtils;
import com.p024ds.daisi.util.NetworkUtils;
import com.p024ds.daisi.util.ScriptVerifyUtils;
import com.p024ds.daisi.util.ToastUtils;
import com.umeng.commonsdk.amap.UMAmapConfig;

/* JADX INFO: loaded from: classes.dex */
public class VerifyService extends BaseService implements CheckTokenView {
    public static final int CHECK_TOKEN_MESSAGE = 80;
    private static final int NO_NETWORK_TIME_ONE = 15000;
    private static final int NO_NETWORK_TIME_THREE = 5000;
    private static final int NO_NETWORK_TIME_TWO = 10000;
    private RequestCountTimers countTimers;
    private VerifyTokenPresenter verifyTokenPresenter;
    private int checkNetworkCount = 0;
    private int reqErrorCount = 0;
    private boolean isPause = false;
    private Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.ds.daisi.services.VerifyService.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 80) {
                return;
            }
            if (NetworkUtils.isAvailable(VerifyService.this)) {
                if (VerifyService.this.checkNetworkCount > 0) {
                    VerifyService.this.checkNetworkCount = 0;
                }
                if (VerifyService.this.isPause && MqRunner.getInstance().isScriptStarted()) {
                    VerifyService.this.isPause = false;
                    MqRunner.getInstance().resume();
                    LogUtils.logError("isPause,恢复运行" + VerifyService.this.isPause);
                }
                Logger.m3442e("VerifyService token：" + ScriptVerifyUtils.acquireTokenKey(), new Object[0]);
                VerifyService.this.countTimers.start();
                VerifyService.this.verifyTokenPresenter.checkToken(VerifyService.this, ScriptVerifyUtils.acquireTokenKey(), ScriptVerifyUtils.acquireCodes());
                return;
            }
            VerifyService.access$008(VerifyService.this);
            LogUtils.logError("checkNetWorkCount=" + VerifyService.this.checkNetworkCount);
            switch (VerifyService.this.checkNetworkCount) {
                case 1:
                    VerifyService.this.mHandler.sendEmptyMessageDelayed(80, 15000L);
                    break;
                case 2:
                    VerifyService.this.mHandler.sendEmptyMessageDelayed(80, UMAmapConfig.AMAP_CACHE_WRITE_TIME);
                    break;
                case 3:
                    VerifyService.this.mHandler.sendEmptyMessageDelayed(80, 5000L);
                    break;
                case 4:
                    if (MqRunner.getInstance().isScriptStarted()) {
                        MqRunner.getInstance().pause();
                        VerifyService.this.isPause = true;
                        LogUtils.logError("isPause,暂停运行" + VerifyService.this.isPause);
                    }
                    VerifyService.this.mHandler.sendEmptyMessageDelayed(80, 15000L);
                    break;
                default:
                    LogUtils.logError("default checkNetworkCount++" + VerifyService.this.checkNetworkCount);
                    VerifyService.this.mHandler.sendEmptyMessageDelayed(80, 15000L);
                    break;
            }
        }
    };

    @Override // com.p024ds.daisi.mvp.views.CheckTokenView
    public void generatorToken(TokenBean tokenBean) {
    }

    static /* synthetic */ int access$008(VerifyService verifyService) {
        int i = verifyService.checkNetworkCount;
        verifyService.checkNetworkCount = i + 1;
        return i;
    }

    @Override // com.p024ds.daisi.services.BaseService, android.app.Service
    public void onCreate() {
        super.onCreate();
        this.verifyTokenPresenter = new VerifyTokenPresenter(this);
    }

    @Override // com.p024ds.daisi.services.BaseService, android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        this.countTimers = new RequestCountTimers(60000L, 1000L);
        this.countTimers.start();
        return super.onStartCommand(intent, i, i2);
    }

    @Override // com.p024ds.daisi.mvp.views.CheckTokenView
    public void checkToken(TokenBean tokenBean) {
        if (this.reqErrorCount > 0) {
            this.reqErrorCount = 0;
        }
    }

    @Override // com.p024ds.daisi.mvp.views.CheckTokenView
    public void checkTokenFail(String str) {
        this.reqErrorCount++;
        LogUtils.logError("reqErrorCount=" + this.reqErrorCount);
        switch (this.reqErrorCount) {
            case 1:
                this.mHandler.sendEmptyMessageDelayed(80, 30000L);
                break;
            case 2:
                this.mHandler.sendEmptyMessageDelayed(80, 20000L);
                break;
            case 3:
                this.mHandler.sendEmptyMessageDelayed(80, UMAmapConfig.AMAP_CACHE_WRITE_TIME);
                break;
            case 4:
                ToastUtils.showToastLong(this, str);
                if (MqRunner.getInstance().isScriptStarted()) {
                    MqRunner.getInstance().stop();
                }
                break;
        }
    }

    class RequestCountTimers extends CountTimer {
        @Override // com.p024ds.daisi.timer.CountTimer
        public void onTick(long j) {
        }

        public RequestCountTimers(long j, long j2) {
            super(j, j2);
        }

        @Override // com.p024ds.daisi.timer.CountTimer
        public void onFinish() {
            LogUtils.logError("timer: onFinish()");
            VerifyService.this.mHandler.sendEmptyMessage(80);
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.countTimers.cancel();
        this.mHandler.removeMessages(80);
        this.verifyTokenPresenter.cancelReqAboutToken();
        LogUtils.logError("stop VerifyService");
    }
}
