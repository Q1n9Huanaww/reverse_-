package com.p024ds.daisi.activity.news;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cyjh.p019mq.service.IpcService;
import com.cyjh.share.bean.ILFYBean;
import com.cyjh.share.bean.response.AppRelatedInfo;
import com.cyjh.share.bean.response.AppStartupResponse;
import com.cyjh.share.bean.response.AuthorRelatedInfo;
import com.cyjh.share.bean.response.TemplateResponse;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.cyjh.share.counttimer.SecondTimer;
import com.cyjh.share.manager.ActivitysManager;
import com.cyjh.share.manager.VariableAndConstantsManager;
import com.cyjh.share.mvp.presenter.AppStartupPresenter;
import com.cyjh.share.mvp.presenter.AppStatisticsPresenter;
import com.cyjh.share.mvp.presenter.TemplateVerifyPresenter;
import com.cyjh.share.mvp.view.AppStartupView;
import com.cyjh.share.mvp.view.TemplateVerifyView;
import com.cyjh.share.util.ToastUtils;
import com.iflytek.voiceads.IFLYNativeAd;
import com.iflytek.voiceads.config.AdError;
import com.iflytek.voiceads.config.AdKeys;
import com.iflytek.voiceads.conn.NativeDataRef;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.activity.BaseActivity;
import com.p024ds.daisi.entity.ParamsWrap;
import com.p024ds.daisi.listener.IFLYADListener;
import com.p024ds.daisi.manager.DialogManager;
import com.p024ds.daisi.services.PhoneStateService;
import com.p024ds.daisi.util.AppDeviceUtils;
import com.p024ds.daisi.util.GlideUtils;
import com.p024ds.daisi.util.IntentUtils;
import com.umeng.analytics.MobclickAgent;
import com.warnship.autoplay.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import mobi.oneway.export.p083d.InterfaceC1925e;

/* JADX INFO: loaded from: classes.dex */
public class SplashAppActivity extends BaseActivity implements View.OnClickListener, TemplateVerifyView, AppStartupView {
    public static final int AD_SHOW_TIME = 8;
    private static final int APP_CLOSED = 0;
    private static final int APP_PASS = 1;
    private static final int APP_WILL_DEACTIVATED = 2;
    public static final int BG_DISPLAY_SUCCESS_STATIS_MESSAGE = 18;
    private static final float CIRCLE_SIZE_VALUE = 8.0f;
    private static final int CIRCLE_TOTAL_COUNT = 6;
    private static final String COLOR_BLUE = "#3c7ac5";
    private static final String COLOR_GRAY = "#bababa";
    private static final int CYCLE_TIME_DOT = 100;
    public static final int DEFAULT_FOUR_VALUE = 4;
    private static final int DEFAULT_VALUE = 0;
    private static final int ILFYAD_NET_REQUEST_MESSAGE = 17;
    public static final int ILFY_DISPLAY_SUCCESS_REQ_MESSAGE = 15;
    public static final int LOAD_BACKGROUND_MESSAGE = 16;
    private static final int PERMISSIONS_REQUEST_CODE = 1;
    public static final int TIMED_PAGE = 20;
    private NativeDataRef adItem;
    private int currentDotCounts;
    private GradientDrawable mBlueGradientDrawable;
    private CloseButtonTimer mCloseAdTimer;
    private GradientDrawable mGrayGradientDrawable;
    private ImageView mImgAdFullScreen;
    private LinearLayout mLinearCircleDot;
    private SmallBallPollingRunnable mPollingRunnable;
    private AppStartupPresenter mStartupPresenter;
    private TemplateVerifyPresenter mTemplateVerifyPresenter;
    private TextView mTvAdMark;
    private TextView mTvCloseAd;
    private List<ImageView> imageViewLists = new ArrayList();
    private MyHandler mHandler = new MyHandler();
    private int adILFYType = 0;
    private int countAdTimer = 0;
    private boolean isClickerFirst = false;
    private boolean isLoadCompleteStartup = false;
    private boolean isLoadCompleteTemplateVerify = false;
    private String[] permissions = {"android.permission.READ_PHONE_STATE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.CALL_PHONE", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_NETWORK_STATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.SEND_SMS", "android.permission.READ_SMS"};
    private IFLYADListener mNativeListener = new IFLYADListener() { // from class: com.ds.daisi.activity.news.SplashAppActivity.4
        @Override // com.iflytek.voiceads.listener.IFLYNativeListener
        public void onAdLoaded(NativeDataRef nativeDataRef) {
            SplashAppActivity.this.adItem = nativeDataRef;
            Log.e("zzz", "SplashActivity---onAdLoaded---2" + SplashAppActivity.this.adItem.getImgUrl());
            SplashAppActivity.this.displayFullScreenAD(SplashAppActivity.this.adItem.getImgUrl());
        }

        @Override // com.iflytek.voiceads.listener.IFLYNativeListener
        public void onAdFailed(AdError adError) {
            Log.e("zzz", "SplashActivity---onAdFailed，开始加载后台配置的广告---3" + adError.getMessage() + "," + adError.getErrorDescription());
            SplashAppActivity.this.mHandler.obtainMessage(16).sendToTarget();
        }
    };

    static /* synthetic */ int access$1208(SplashAppActivity splashAppActivity) {
        int i = splashAppActivity.countAdTimer;
        splashAppActivity.countAdTimer = i + 1;
        return i;
    }

    static /* synthetic */ int access$508(SplashAppActivity splashAppActivity) {
        int i = splashAppActivity.currentDotCounts;
        splashAppActivity.currentDotCounts = i + 1;
        return i;
    }

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_splash2);
        if (!isTaskRoot()) {
            finish();
        } else if (Build.VERSION.SDK_INT >= 23) {
            requestSystemPermissions();
        } else {
            init();
        }
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_from_left);
    }

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onPause() throws Throwable {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onResume() throws Throwable {
        super.onResume();
        MobclickAgent.onResume(this);
        if (this.isClickerFirst) {
            toCall();
        }
    }

    private void requestSystemPermissions() {
        ArrayList arrayList = new ArrayList();
        for (String str : this.permissions) {
            if (ContextCompat.checkSelfPermission(this, str) != 0) {
                arrayList.add(str);
            }
        }
        if (arrayList.isEmpty()) {
            init();
        } else {
            ActivityCompat.requestPermissions(this, (String[]) arrayList.toArray(new String[0]), 1);
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        boolean z = true;
        if (i == 1) {
            for (int i2 : iArr) {
                if (i2 != 0) {
                    z = false;
                    break;
                }
            }
            if (z) {
                init();
            } else {
                openAppDetails();
            }
        }
    }

    private void openAppDetails() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("没有授权的权限，请到 “应用信息 -> 权限” 中授予！");
        builder.setPositiveButton("去手动授权", new DialogInterface.OnClickListener() { // from class: com.ds.daisi.activity.news.SplashAppActivity.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent();
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse("package:" + SplashAppActivity.this.getPackageName()));
                intent.addFlags(268435456);
                intent.addFlags(InterfaceC1925e.f4896e);
                intent.addFlags(8388608);
                SplashAppActivity.this.startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", (DialogInterface.OnClickListener) null);
        builder.show();
    }

    private void init() {
        AppContext.getInstance().initEngine();
        initView();
        initAfter();
        initListener();
    }

    private void initListener() {
        this.mTvCloseAd.setOnClickListener(this);
    }

    private void initAfter() {
        initDot();
        this.mPollingRunnable = new SmallBallPollingRunnable();
        this.mPollingRunnable.start();
        this.mStartupPresenter = new AppStartupPresenter(this);
        this.mStartupPresenter.loadAd(this);
        this.mTemplateVerifyPresenter = new TemplateVerifyPresenter(this);
        this.mTemplateVerifyPresenter.loadAd(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadILFYAd() {
        ILFYBean iLFYBeanDisplayILFYAd = ParamsWrap.getParamsWrap().displayILFYAd(1, IFLYADListener.IFLYAD_FULLSCREEN_KEY);
        if (iLFYBeanDisplayILFYAd.isDisplay) {
            IFLYNativeAd iFLYNativeAd = new IFLYNativeAd(this, iLFYBeanDisplayILFYAd.key, this.mNativeListener);
            iFLYNativeAd.setParameter(AdKeys.APP_VER, AppDeviceUtils.getVersionName(this));
            iFLYNativeAd.loadAd();
        } else {
            this.mHandler.obtainMessage(16).sendToTarget();
        }
        this.mCloseAdTimer = new CloseButtonTimer(20);
        this.mCloseAdTimer.start();
    }

    private void initView() {
        this.mImgAdFullScreen = (ImageView) findViewById(R.id.iv_splash_ad_fullscreen);
        this.mTvAdMark = (TextView) findViewById(R.id.tv_splash_ad_mark);
        this.mLinearCircleDot = (LinearLayout) findViewById(R.id.id_ll_dot);
        this.mTvCloseAd = (TextView) findViewById(R.id.tv_close_ad);
    }

    private void initDot() {
        this.mLinearCircleDot.removeAllViews();
        this.imageViewLists.clear();
        for (int i = 0; i < 6; i++) {
            ImageView imageView = new ImageView(this);
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(1);
            gradientDrawable.setCornerRadius(AppDeviceUtils.getDipToPx(this, CIRCLE_SIZE_VALUE));
            gradientDrawable.setSize(AppDeviceUtils.getDipToPx(this, CIRCLE_SIZE_VALUE), AppDeviceUtils.getDipToPx(this, CIRCLE_SIZE_VALUE));
            if (i == 0) {
                gradientDrawable.setColor(Color.parseColor(COLOR_BLUE));
                this.mBlueGradientDrawable = gradientDrawable;
            } else {
                gradientDrawable.setColor(Color.parseColor(COLOR_GRAY));
                if (i == 5) {
                    this.mGrayGradientDrawable = gradientDrawable;
                }
            }
            imageView.setImageDrawable(gradientDrawable);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(0, 0, AppDeviceUtils.getDipToPx(this, CIRCLE_SIZE_VALUE), 0);
            this.mLinearCircleDot.addView(imageView, layoutParams);
            this.imageViewLists.add(imageView);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_splash_ad_fullscreen) {
            onClickImgFullScreenAd(this.mImgAdFullScreen);
        } else {
            if (id != R.id.tv_close_ad) {
                return;
            }
            if (this.countAdTimer < 4) {
                onClickImgFullScreenAd(this.mImgAdFullScreen);
            } else {
                toCall();
            }
        }
    }

    private void onClickImgFullScreenAd(View view) {
        if (this.isClickerFirst) {
            return;
        }
        this.isClickerFirst = true;
        cancelCloseTimer();
        if (this.adItem != null) {
            clickXunFeiAd(view);
        } else {
            onClickImgBackGroundAd();
        }
    }

    private void onClickImgBackGroundAd() {
        AppStatisticsPresenter.getStatisticsPresenter().setCallback(new AppStatisticsPresenter.OnCallback() { // from class: com.ds.daisi.activity.news.SplashAppActivity.2
            @Override // com.cyjh.share.mvp.presenter.AppStatisticsPresenter.OnCallback
            public void onCallbackSuc(Object obj) {
                AppStatisticsPresenter.getStatisticsPresenter().cleanCallback();
                ParamsWrap.getParamsWrap().onClickBackgroundAdOpera(SplashAppActivity.this);
            }
        }).loadAd(this, 1, 3, ParamsWrap.getParamsWrap().mBackgroundSetAdInfo.f1680Id);
    }

    private void clickXunFeiAd(View view) {
        this.isClickerFirst = true;
        this.adItem.onClick(view);
        if (this.adILFYType == 3) {
            this.mHandler.postDelayed(new Runnable() { // from class: com.ds.daisi.activity.news.SplashAppActivity.3
                @Override // java.lang.Runnable
                public void run() {
                    SplashAppActivity.this.toCall();
                }
            }, 300L);
        }
    }

    @Override // com.cyjh.share.mvp.view.AppStartupView
    public void startupSuc(AppStartupResponse appStartupResponse) {
        AuthorRelatedInfo authorRelatedInfo = appStartupResponse.AuthorInfo;
        if (authorRelatedInfo.ServicePackStatus == 0) {
            this.isLoadCompleteStartup = true;
            displayDialogMessage(authorRelatedInfo.ServicePackStatus, authorRelatedInfo.ServicePackPrompt);
            return;
        }
        ParamsWrap.getParamsWrap().isFreeAd = authorRelatedInfo.IsFreeAd;
        ParamsWrap.getParamsWrap().engineToken = authorRelatedInfo.EngineToken;
        AppRelatedInfo appRelatedInfo = appStartupResponse.AppInfo;
        ParamsWrap.getParamsWrap().dailyTryTimes = appRelatedInfo.DailyTryTimes;
        ParamsWrap.getParamsWrap().feedbackTips = appRelatedInfo.FeedbackTips;
        ParamsWrap.getParamsWrap().onceTryMinute = appRelatedInfo.OnceTryMinute;
        ParamsWrap.getParamsWrap().crateCollect();
        if (!TextUtils.isEmpty(appRelatedInfo.ScriptKey)) {
            AppContext.getInstance().tokenKey = Long.parseLong(appRelatedInfo.ScriptKey);
        }
        if (appRelatedInfo.BuyRegCodeConfig != null) {
            ParamsWrap.getParamsWrap().mBuyRegCodeConfigInfos.addAll(appRelatedInfo.BuyRegCodeConfig);
        }
        if (appRelatedInfo.FlySetting != null) {
            ParamsWrap.getParamsWrap().mFlySettingInfos.addAll(appRelatedInfo.FlySetting);
        }
        if (appRelatedInfo.RecommendSetting != null) {
            ParamsWrap.getParamsWrap().mRecommendSettingInfos.addAll(appRelatedInfo.RecommendSetting);
        }
        if (authorRelatedInfo.AdList != null) {
            ParamsWrap.getParamsWrap().mAdInfos.addAll(authorRelatedInfo.AdList);
        }
        this.isLoadCompleteStartup = true;
        if (authorRelatedInfo.ServicePackStatus == 1) {
            loadAdOrCallNextPage();
        } else if (authorRelatedInfo.ServicePackStatus == 2) {
            displayDialogMessage(authorRelatedInfo.ServicePackStatus, authorRelatedInfo.ServicePackPrompt);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadAdOrCallNextPage() {
        if (!ParamsWrap.getParamsWrap().isFreeAd()) {
            loadAdMessage();
        } else {
            IntentUtils.toCallElfinPayActivity(this);
            finish();
        }
    }

    @Override // com.cyjh.share.mvp.view.AppStartupView
    public void startupFail(String str) {
        ToastUtils.showToastLong(this, str);
    }

    private class SmallBallPollingRunnable implements Runnable {
        private SmallBallPollingRunnable() {
        }

        public void start() {
            SplashAppActivity.this.mHandler.removeCallbacks(this);
            SplashAppActivity.this.mHandler.postDelayed(this, 100L);
        }

        @Override // java.lang.Runnable
        public void run() {
            SplashAppActivity.access$508(SplashAppActivity.this);
            for (int i = 0; i < SplashAppActivity.this.imageViewLists.size(); i++) {
                if (i == SplashAppActivity.this.currentDotCounts % SplashAppActivity.this.imageViewLists.size()) {
                    ((ImageView) SplashAppActivity.this.imageViewLists.get(i)).setImageDrawable(SplashAppActivity.this.mBlueGradientDrawable);
                } else {
                    ((ImageView) SplashAppActivity.this.imageViewLists.get(i)).setImageDrawable(SplashAppActivity.this.mGrayGradientDrawable);
                }
            }
            SplashAppActivity.this.mHandler.removeCallbacks(this);
            SplashAppActivity.this.mHandler.postDelayed(this, 100L);
        }
    }

    private static class MyHandler extends Handler {
        private WeakReference<SplashAppActivity> mWeakReference;

        private MyHandler(SplashAppActivity splashAppActivity) {
            this.mWeakReference = new WeakReference<>(splashAppActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            SplashAppActivity splashAppActivity = this.mWeakReference.get();
            if (splashAppActivity != null) {
                switch (message.what) {
                    case 15:
                        Log.e("zzz", "SplashActivity--MyHandler--讯飞广告请求统计");
                        AppStatisticsPresenter.getStatisticsPresenter().loadAd(splashAppActivity, 2, 2, 1L);
                        break;
                    case 16:
                        Log.e("zzz", "SplashActivity--LOAD_BACKGROUND_MESSAGE--加载后台广告");
                        if (!ParamsWrap.getParamsWrap().isDisplayBackgroundSetAd(1)) {
                            splashAppActivity.toCall();
                        } else {
                            String str = ParamsWrap.getParamsWrap().mBackgroundSetAdInfo.ImageUrl;
                            Log.e("zzz", "SplashActivity--LOAD_BACKGROUND_MESSAGE--加载后台广告: " + str);
                            if (!TextUtils.isEmpty(str)) {
                                splashAppActivity.displayFullScreenAD(str);
                            } else {
                                splashAppActivity.toCall();
                            }
                        }
                        break;
                    case 17:
                        Log.e("zzz", "SplashActivity--ILFYAD_NET_REQUEST_MESSAGE--加载讯飞广告");
                        splashAppActivity.loadILFYAd();
                        break;
                    case 18:
                        Log.e("zzz", "SplashActivity--BG_DISPLAY_SUCCESS_STATIS_MESSAGE--后台广告显示统计");
                        AppStatisticsPresenter.getStatisticsPresenter().loadAd(splashAppActivity, 1, 2, ParamsWrap.getParamsWrap().mBackgroundSetAdInfo.f1680Id);
                        break;
                }
            }
        }
    }

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.mHandler.removeCallbacksAndMessages(null);
        if (this.mStartupPresenter != null) {
            this.mStartupPresenter.onCancel();
        }
        cancelCloseTimer();
        resetFlag();
        if (this.mTemplateVerifyPresenter != null) {
            this.mTemplateVerifyPresenter.onCancel();
        }
    }

    private void removePollingRunnable() {
        if (this.mPollingRunnable != null) {
            this.mHandler.removeCallbacks(this.mPollingRunnable);
            this.mPollingRunnable = null;
        }
    }

    private void displayEightSecond() {
        cancelCloseTimer();
        this.mCloseAdTimer = new CloseButtonTimer(8);
        this.mCloseAdTimer.start();
    }

    private void cancelCloseTimer() {
        if (this.mCloseAdTimer != null) {
            this.mCloseAdTimer.cancel();
            this.countAdTimer = 0;
            this.mCloseAdTimer = null;
        }
    }

    private class CloseButtonTimer extends SecondTimer {
        private CloseButtonTimer(int i) {
            super(i);
        }

        @Override // com.cyjh.share.counttimer.SimpleTimer
        protected void onTick(int i) {
            SplashAppActivity.access$1208(SplashAppActivity.this);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            SplashAppActivity.this.toCall();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toCall() {
        IntentUtils.toCallFullscreenTwoAdActivity(this);
        finish();
    }

    private void resetFlag() {
        this.isClickerFirst = false;
        this.adItem = null;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return i == 4 && super.onKeyDown(i, keyEvent);
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
        displayEightSecond();
        removePollingRunnable();
        this.mImgAdFullScreen.setOnClickListener(this);
        this.mImgAdFullScreen.setVisibility(0);
        if (this.adItem != null) {
            this.mHandler.obtainMessage(15).sendToTarget();
        } else {
            this.mHandler.obtainMessage(18).sendToTarget();
        }
        this.mHandler.postDelayed(new Runnable() { // from class: com.ds.daisi.activity.news.SplashAppActivity.5
            @Override // java.lang.Runnable
            public void run() {
                SplashAppActivity.this.mTvCloseAd.setVisibility(0);
            }
        }, 300L);
    }

    @Override // com.cyjh.share.mvp.view.TemplateVerifyView
    public void templateVerifySuccess(TemplateResponse templateResponse) {
        this.isLoadCompleteTemplateVerify = true;
        VariableAndConstantsManager.getInstance().templateType = templateResponse.TemplateType;
        if (templateResponse.Status == 0 || templateResponse.Status == 2) {
            displayDialogMessage(templateResponse.Status, templateResponse.Remark);
        } else if (templateResponse.Status == 1) {
            loadAdMessage();
        }
    }

    @Override // com.cyjh.share.mvp.view.TemplateVerifyView
    public void templateVerifyFailure(String str) {
        ToastUtils.showToastLong(this, str);
    }

    private void displayDialogMessage(int i, String str) {
        AlertDialog alertDialogShowMessageDialog = DialogManager.getInstance().showMessageDialog(this, R.layout.dialog_version_inspection, R.style.Theme_Dialog);
        alertDialogShowMessageDialog.setCancelable(false);
        alertDialogShowMessageDialog.show();
        TextView textView = (TextView) alertDialogShowMessageDialog.findViewById(R.id.id_tv_message);
        if (i == 0) {
            textView.setText(getString(R.string.version_disable));
        } else {
            textView.setText(str);
        }
        ((TextView) alertDialogShowMessageDialog.findViewById(R.id.id_tv_known)).setOnClickListener(new VersionKnownListener(alertDialogShowMessageDialog, i));
    }

    private class VersionKnownListener implements View.OnClickListener {
        private AlertDialog mAlertDialog;
        private int returnCode;

        private VersionKnownListener(AlertDialog alertDialog, int i) {
            this.mAlertDialog = alertDialog;
            this.returnCode = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.returnCode == 0) {
                this.mAlertDialog.dismiss();
                SplashAppActivity.this.stopService(new Intent(SplashAppActivity.this, (Class<?>) PhoneStateService.class));
                SplashAppActivity.this.stopService(new Intent(SplashAppActivity.this, (Class<?>) IpcService.class));
                ActivitysManager.getActivitysManager().finishAllActivity();
                return;
            }
            this.mAlertDialog.dismiss();
            SplashAppActivity.this.loadAdOrCallNextPage();
        }
    }

    private void loadAdMessage() {
        if (this.isLoadCompleteStartup && this.isLoadCompleteTemplateVerify) {
            this.isLoadCompleteTemplateVerify = false;
            this.isLoadCompleteStartup = false;
            this.mHandler.obtainMessage(17).sendToTarget();
        }
    }
}
