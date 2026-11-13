package com.p024ds.daisi.activity.news;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.cyjh.share.counttimer.SecondTimer;
import com.cyjh.share.mvp.presenter.AppStatisticsPresenter;
import com.cyjh.share.util.SlLog;
import com.cyjh.share.util.SlToast;
import com.iflytek.voiceads.config.AdError;
import com.iflytek.voiceads.conn.NativeDataRef;
import com.p024ds.daisi.activity.BaseActivity;
import com.p024ds.daisi.entity.ParamsWrap;
import com.p024ds.daisi.listener.IFLYADListener;
import com.p024ds.daisi.util.GlideUtils;
import com.p024ds.daisi.util.IntentUtils;
import com.umeng.analytics.MobclickAgent;
import com.warnship.autoplay.R;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class FullScreenTwoAdActivity extends BaseActivity implements View.OnClickListener {
    private static final int BACKGROUND_AD = 102;
    public static final int BG_DISPLAY_SUCCESS_STATIS_MESSAGE = 1003;
    public static final int DELAY_SKIP_TIME = 200;
    private static final int FENG_LING_AD = 101;
    public static final int ILFY_DISPLAY_SUCCESS_STATIS_MESSAGE = 1001;
    public static final int LOAD_BACKGROUND_MESSAGE = 1002;
    public static final int PAGE_TIMING = 8;
    public static final int PAUSE_SKIP_TIME = 1000;
    public static final int SKIP_BUTTON_TIME = 3;
    private static final String TAG = "FullScreenTwoAdActivity";
    private static final int TOTAL_TIME = 5;
    private static final int WM_AD = 103;
    private static final int XUN_FEI_AD = 100;
    private NativeDataRef adItem;
    private ImageView mImgAdFullScreen;
    private TextView mTvAdMark;
    private TextView mTvCloseAd;
    private SubmitCountTimer submitCountTimer;
    private int adILFYType = 0;
    private int countAdTimer = 0;
    private boolean isDisplayAd = false;
    private MyHandler mHandler = new MyHandler();
    private boolean isClickImg = false;
    private int mCurrentAdType = 100;
    private IFLYADListener mNativeListener = new IFLYADListener() { // from class: com.ds.daisi.activity.news.FullScreenTwoAdActivity.4
        @Override // com.iflytek.voiceads.listener.IFLYNativeListener
        public void onAdLoaded(NativeDataRef nativeDataRef) {
            SlToast.showToastShort(FullScreenTwoAdActivity.this, "讯飞广告加载成功");
            FullScreenTwoAdActivity.this.adItem = nativeDataRef;
            FullScreenTwoAdActivity.this.displayFullScreenAD(FullScreenTwoAdActivity.this.adItem.getImgUrl());
        }

        @Override // com.iflytek.voiceads.listener.IFLYNativeListener
        public void onAdFailed(AdError adError) {
            SlLog.m1334i(FullScreenTwoAdActivity.TAG, "IFLYADListener --> adError message=" + adError.getMessage());
            SlToast.showToastShort(FullScreenTwoAdActivity.this, "讯飞广告加载失败 message=" + adError.getMessage());
            FullScreenTwoAdActivity.this.mHandler.obtainMessage(1002).sendToTarget();
        }
    };

    private void judgeLoadAdOperate() {
        toCall();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadBackgroundAd() {
        toCall();
    }

    private void loadIFLYAd() {
    }

    private void onClickImgMyselfAd() {
        this.isClickImg = true;
        toCall();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return i == 4;
    }

    static /* synthetic */ int access$408(FullScreenTwoAdActivity fullScreenTwoAdActivity) {
        int i = fullScreenTwoAdActivity.countAdTimer;
        fullScreenTwoAdActivity.countAdTimer = i + 1;
        return i;
    }

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_fullscreen_ad_two);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        this.mTvCloseAd.setOnClickListener(this);
    }

    private void initView() {
        this.mImgAdFullScreen = (ImageView) findViewById(R.id.iv_splash_ad_fullscreen);
        this.mTvAdMark = (TextView) findViewById(R.id.tv_splash_ad_mark);
        this.mTvCloseAd = (TextView) findViewById(R.id.tv_close_ad);
    }

    private void initData() {
        judgeLoadAdOperate();
    }

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() throws Throwable {
        super.onResume();
        MobclickAgent.onResume(this);
        if (this.isClickImg) {
            this.mHandler.postDelayed(new Runnable() { // from class: com.ds.daisi.activity.news.FullScreenTwoAdActivity.1
                @Override // java.lang.Runnable
                public void run() {
                    FullScreenTwoAdActivity.this.toCall();
                }
            }, 200L);
        }
    }

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() throws Throwable {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    private void setCurrentAdType(int i) {
        this.mCurrentAdType = i;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_splash_ad_fullscreen) {
            onClickImg();
        } else {
            if (id != R.id.tv_close_ad) {
                return;
            }
            if (this.countAdTimer < 3) {
                onClickImg();
            } else {
                toCall();
            }
        }
    }

    private void onClickImg() {
        cancelCountTimer();
        if (this.adItem != null) {
            onClickImageXunFeiAd();
        } else {
            onClickImgMyselfAd();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void displayFullScreenAD(String str) {
        if (str.endsWith(InterfaceRelatedConstants.PIC_GIF_SUFFIX)) {
            RequestOptions defaultOption = GlideUtils.getDefaultOption();
            defaultOption.diskCacheStrategy(DiskCacheStrategy.DATA);
            GlideUtils.loadGif(this, str, this.mImgAdFullScreen, defaultOption);
        } else {
            RequestOptions defaultOption2 = GlideUtils.getDefaultOption();
            defaultOption2.diskCacheStrategy(DiskCacheStrategy.NONE);
            GlideUtils.load(this, str, this.mImgAdFullScreen, defaultOption2);
        }
        displayAd();
    }

    private void displayAd() {
        if (this.adItem != null) {
            this.adItem.onExposure(this.mImgAdFullScreen);
            if (!TextUtils.isEmpty(this.adItem.getAdSourceMark())) {
                this.mTvAdMark.setText(String.format(getString(R.string.ad_source_mark), this.adItem.getAdSourceMark()));
                this.mTvAdMark.setVisibility(0);
            }
            this.adILFYType = this.adItem.getActionType();
        }
        this.isDisplayAd = true;
        cancelCountTimer();
        startCountTimer();
        this.mImgAdFullScreen.setOnClickListener(this);
        if (this.mCurrentAdType == 100 && this.adItem != null) {
            this.mHandler.obtainMessage(1001).sendToTarget();
        } else if (this.mCurrentAdType == 102) {
            this.mHandler.obtainMessage(1003).sendToTarget();
        }
    }

    private static class MyHandler extends Handler {
        private WeakReference<FullScreenTwoAdActivity> mWeakReference;

        private MyHandler(FullScreenTwoAdActivity fullScreenTwoAdActivity) {
            this.mWeakReference = new WeakReference<>(fullScreenTwoAdActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            FullScreenTwoAdActivity fullScreenTwoAdActivity = this.mWeakReference.get();
            if (fullScreenTwoAdActivity != null) {
                switch (message.what) {
                    case 1001:
                        AppStatisticsPresenter.getStatisticsPresenter().loadAd(fullScreenTwoAdActivity, 2, 2, 2L);
                        break;
                    case 1002:
                        fullScreenTwoAdActivity.loadBackgroundAd();
                        break;
                    case 1003:
                        AppStatisticsPresenter.getStatisticsPresenter().loadAd(fullScreenTwoAdActivity, 1, 2, ParamsWrap.getParamsWrap().mBackgroundSetAdInfo.f1680Id);
                        break;
                }
            }
        }
    }

    private class SubmitCountTimer extends SecondTimer {
        private SubmitCountTimer(int i) {
            super(i);
        }

        @Override // com.cyjh.share.counttimer.SimpleTimer
        protected void onTick(int i) {
            FullScreenTwoAdActivity.access$408(FullScreenTwoAdActivity.this);
            if (FullScreenTwoAdActivity.this.isDisplayAd) {
                FullScreenTwoAdActivity.this.mTvCloseAd.setText(FullScreenTwoAdActivity.this.getString(R.string.countdown_time, new Object[]{Integer.valueOf(i)}));
                FullScreenTwoAdActivity.this.mTvCloseAd.setVisibility(0);
            }
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            FullScreenTwoAdActivity.this.toCall();
        }
    }

    private void startCountTimer() {
        if (this.submitCountTimer == null) {
            this.submitCountTimer = new SubmitCountTimer(5);
        }
        this.submitCountTimer.start();
    }

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        cancelCountTimer();
        this.mHandler.removeCallbacksAndMessages(null);
        this.isDisplayAd = false;
        this.isClickImg = false;
        this.adItem = null;
        AppStatisticsPresenter.getStatisticsPresenter().onCancel();
    }

    private void cancelCountTimer() {
        if (this.submitCountTimer != null) {
            this.submitCountTimer.cancel();
            this.countAdTimer = 0;
            this.submitCountTimer = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toCall() {
        IntentUtils.toCallElfinPayActivity(this);
        finish();
    }

    private void onClickImageXunFeiAd() {
        this.isClickImg = this.adItem.onClick(this.mImgAdFullScreen);
        if (this.adILFYType == 3) {
            this.mHandler.postDelayed(new Runnable() { // from class: com.ds.daisi.activity.news.FullScreenTwoAdActivity.2
                @Override // java.lang.Runnable
                public void run() {
                    FullScreenTwoAdActivity.this.toCall();
                }
            }, 1000L);
        }
    }

    /* JADX INFO: renamed from: com.ds.daisi.activity.news.FullScreenTwoAdActivity$3 */
    class C09623 implements AppStatisticsPresenter.OnCallback {
        C09623() {
        }

        @Override // com.cyjh.share.mvp.presenter.AppStatisticsPresenter.OnCallback
        public void onCallbackSuc(Object obj) {
            AppStatisticsPresenter.getStatisticsPresenter().cleanCallback();
            ParamsWrap.getParamsWrap().onClickBackgroundAdOpera(FullScreenTwoAdActivity.this);
        }
    }
}
