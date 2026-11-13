package com.p024ds.daisi.activity.news;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.AsyncTask;
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
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cyjh.elfin.util.IpcSwap;
import com.cyjh.feedback.lib.executor.ThreadPoolProxyFactory;
import com.cyjh.p019mq.service.IpcService;
import com.cyjh.share.bean.ILFYBean;
import com.cyjh.share.bean.request.BaseRequestValueInfo;
import com.cyjh.share.bean.request.StartAppRequestInfo;
import com.cyjh.share.bean.response.AppRelatedInfo;
import com.cyjh.share.bean.response.AppStartupResponse;
import com.cyjh.share.bean.response.AuthorRelatedInfo;
import com.cyjh.share.bean.response.DomainResult;
import com.cyjh.share.bean.response.FengLingAdInfo;
import com.cyjh.share.bean.response.PhoneConfig;
import com.cyjh.share.bean.response.TemplateResponse;
import com.cyjh.share.config.MyConfig;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.cyjh.share.counttimer.SecondTimer;
import com.cyjh.share.helper.PhoneConfigHelper;
import com.cyjh.share.manager.ActivitysManager;
import com.cyjh.share.manager.VariableAndConstantsManager;
import com.cyjh.share.mvp.presenter.AppDomainRequestPresenter;
import com.cyjh.share.mvp.presenter.AppStatisticsPresenter;
import com.cyjh.share.util.CommonUtils;
import com.cyjh.share.util.DownloadUtil;
import com.cyjh.share.util.EmulatorUtils;
import com.cyjh.share.util.FileUtil;
import com.cyjh.share.util.GsonExUtil;
import com.cyjh.share.util.SlLog;
import com.cyjh.share.util.SlToast;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.iflytek.voiceads.IFLYNativeAd;
import com.iflytek.voiceads.config.AdError;
import com.iflytek.voiceads.config.AdKeys;
import com.iflytek.voiceads.conn.NativeDataRef;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.activity.BaseActivity;
import com.p024ds.daisi.activity.guide.RecognitionGuideActivity;
import com.p024ds.daisi.constant.Constants;
import com.p024ds.daisi.download.ApkDownloadHelper;
import com.p024ds.daisi.entity.AdEvent;
import com.p024ds.daisi.entity.FeLingAdReportInfo;
import com.p024ds.daisi.entity.ParamsWrap;
import com.p024ds.daisi.listener.BaseTextViewKnownListener;
import com.p024ds.daisi.listener.IFLYADListener;
import com.p024ds.daisi.manager.DialogManager;
import com.p024ds.daisi.mvp.managers.Callback;
import com.p024ds.daisi.mvp.managers.FengLingAdManager;
import com.p024ds.daisi.net.URL.MyBuildConfig;
import com.p024ds.daisi.net.URL.URLConstant;
import com.p024ds.daisi.services.PhoneStateService;
import com.p024ds.daisi.util.AppDeviceUtils;
import com.p024ds.daisi.util.CollectionUtils;
import com.p024ds.daisi.util.ErrorUtilHelper;
import com.p024ds.daisi.util.FeLingAdDBHelper;
import com.p024ds.daisi.util.GlideUtils;
import com.p024ds.daisi.util.IntentUtils;
import com.p024ds.daisi.util.LogUtils;
import com.p024ds.daisi.util.NetworkUtils;
import com.p024ds.daisi.util.ToastUtils;
import com.umeng.analytics.MobclickAgent;
import com.warnship.autoplay.R;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import killer.util.JumpElfinFreeActivity;
import mobi.oneway.export.p083d.InterfaceC1925e;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SplashActivity extends BaseActivity implements View.OnClickListener {
    public static final int ACQUIRE_APP_DATA_MESSAGE = 25;
    public static final int AD_SHOW_TIME = 8;
    private static final int APP_CLOSED = 0;
    private static final int APP_PASS = 1;
    public static final int APP_START_SUCCESS_MESSAGE = 20;
    private static final int APP_WILL_DEACTIVATED = 2;
    public static final int AUTO_JUDGE_PAGE_MESSAGE = 27;
    private static final int BACKGROUND_AD = 102;
    public static final int BG_DISPLAY_SUCCESS_STATIS_MESSAGE = 18;
    public static final int CHECK_REPORT_INFO_STATUS_MESSAGE = 26;
    private static final float CIRCLE_SIZE_VALUE = 8.0f;
    private static final int CIRCLE_TOTAL_COUNT = 6;
    private static final String COLOR_BLUE = "#3c7ac5";
    private static final String COLOR_GRAY = "#bababa";
    private static final int CYCLE_TIME_DOT = 100;
    public static final int DEFAULT_FOUR_VALUE = 4;
    private static final int DEFAULT_VALUE = 0;
    public static final int DISPLAY_FENG_LING_AD_SUCCESS_MESSAGE = 23;
    private static final int FENG_LING_AD = 101;
    public static final int FENG_LING_NET_REQUEST_MESSAGE = 22;
    private static final int ILFYAD_NET_REQUEST_MESSAGE = 17;
    public static final int ILFY_DISPLAY_SUCCESS_REQ_MESSAGE = 15;
    public static final int JUMP_TO_NEXT_PAGE_MESSAGE = 24;
    public static final int LOAD_BACKGROUND_MESSAGE = 16;
    public static final int LOAD_START_APP_MESSAGE = 21;
    public static final int MSG_DEFAULT_DELAY = 3000;
    public static final int MSG_GET_DOMAIN_NAME_FIRST_JUDGE_OPERATE = 28;
    public static final int MSG_GET_DOMAIN_NAME_SECOND_JUDGE_OPERATE = 29;
    private static final int ONE_WAY_AD = 103;
    private static final int PERMISSIONS_REQUEST_CODE = 1;
    private static final int REQUEST_FENG_LING_AD_WEB_VIEW = 300;
    private static final String TAG = "SplashActivity";
    public static final int TEMPLATE_VERIFY_SUCCESS_MESSAGE = 19;
    public static final int TIMED_PAGE = 20;
    private static final int XUN_FEI_AD = 100;
    private NativeDataRef adItem;
    private int currentDotCounts;
    private FengLingAdManager fengLingAdManager;
    private FrameLayout flSplashAd;
    private GradientDrawable mBlueGradientDrawable;
    private float mClickDwonX;
    private float mClickDwonY;
    private float mClickUpX;
    private float mClickUpY;
    private CloseButtonTimer mCloseAdTimer;
    private FengLingAdInfo mFengLingAdInfo;
    private GradientDrawable mGrayGradientDrawable;
    private ImageView mImgAdFullScreen;
    private LinearLayout mLinearCircleDot;
    private SmallBallPollingRunnable mPollingRunnable;
    private TextView mTvAdMark;
    private TextView mTvCloseAd;
    private List<ImageView> imageViewLists = new ArrayList();
    private MyHandler mHandler = new MyHandler();
    private int adILFYType = 0;
    private int countAdTimer = 0;
    private boolean isClickerFirst = false;
    private boolean isLoadCompleteStartup = false;
    private boolean isLoadCompleteTemplateVerify = false;
    private long templateTimeReq = 0;
    private long appStartTimeReq = 0;
    private int mCurrentAdType = 100;
    private boolean mCanJumpPage = true;
    private String[] permissions = {"android.permission.INTERNET", "android.permission.READ_PHONE_STATE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.CALL_PHONE", "android.permission.ACCESS_NETWORK_STATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.SEND_SMS", "android.permission.READ_SMS", "android.permission.READ_CONTACTS", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
    private IFLYADListener mNativeListener = new IFLYADListener() { // from class: com.ds.daisi.activity.news.SplashActivity.13
        @Override // com.iflytek.voiceads.listener.IFLYNativeListener
        public void onAdLoaded(NativeDataRef nativeDataRef) {
            SlLog.m1334i(SplashActivity.TAG, "IFLYADListener --> onAdLoaded");
            SlToast.showToastShort(SplashActivity.this, "讯飞广告加载成功");
            SplashActivity.this.adItem = nativeDataRef;
            SplashActivity.this.displayFullScreenAD(SplashActivity.this.adItem.getImgUrl());
        }

        @Override // com.iflytek.voiceads.listener.IFLYNativeListener
        public void onAdFailed(AdError adError) {
            SlLog.m1334i(SplashActivity.TAG, "IFLYADListener --> onAdFailed message=" + adError.getMessage());
            SlToast.showToastShort(SplashActivity.this, "讯飞广告加载失败 message=" + adError.getMessage());
            SplashActivity.this.setCurrentAdType(101);
            SplashActivity.this.mHandler.obtainMessage(22).sendToTarget();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void getDomainNameFirstJudgeOperate() {
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return i == 4;
    }

    static /* synthetic */ int access$1308(SplashActivity splashActivity) {
        int i = splashActivity.currentDotCounts;
        splashActivity.currentDotCounts = i + 1;
        return i;
    }

    static /* synthetic */ int access$3008(SplashActivity splashActivity) {
        int i = splashActivity.countAdTimer;
        splashActivity.countAdTimer = i + 1;
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
            return;
        }
        String stringExtra = getIntent().getStringExtra("type");
        SlLog.m1334i(TAG, "onCreate --> type=" + stringExtra);
        AppContext.getInstance().mLunchType = stringExtra;
        if (Build.VERSION.SDK_INT >= 23) {
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
        builder.setPositiveButton("去手动授权", new DialogInterface.OnClickListener() { // from class: com.ds.daisi.activity.news.SplashActivity.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent();
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse("package:" + SplashActivity.this.getPackageName()));
                intent.addFlags(268435456);
                intent.addFlags(InterfaceC1925e.f4896e);
                intent.addFlags(8388608);
                SplashActivity.this.startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", (DialogInterface.OnClickListener) null);
        builder.show();
    }

    private void init() {
        initView();
        initAfter();
        initListener();
        JumpElfinFreeActivity.jump(this);
    }

    private void initListener() {
        this.mTvCloseAd.setOnClickListener(this);
    }

    private void initAfter() {
        AppContext.getInstance().initEngine();
        this.fengLingAdManager = new FengLingAdManager();
        this.mPollingRunnable = new SmallBallPollingRunnable();
        this.mPollingRunnable.start();
        AppContext.getInstance().isEmulator = EmulatorUtils.isEmulator(this);
        getDomainNameFirstJudgeOperate();
        EventBus.getDefault().register(this);
    }

    private void getDomainNameFirst() {
        AppDomainRequestPresenter.getInstance().getDomainNameFirst(this, new AppDomainRequestPresenter.OnResultCallback() { // from class: com.ds.daisi.activity.news.SplashActivity.2
            @Override // com.cyjh.share.mvp.presenter.AppDomainRequestPresenter.OnResultCallback
            public void onSuccess(DomainResult domainResult) {
                SlLog.m1334i(SplashActivity.TAG, "getDomainNameFirst --> onSuccess domainName=" + domainResult.getAppDomainName() + ",domain2Name=" + domainResult.getApp2DomainName());
                MyBuildConfig.AUTHORITY = domainResult.getAppDomainName();
                MyBuildConfig.AUTHORITY_VERIFY = domainResult.getAppDomainName();
                MyBuildConfig.AUTHORITY_PAY = domainResult.getAppDomainName();
                MyConfig.setAuthority(domainResult.getAppDomainName());
                MyConfig.setApp2DomainName(domainResult.getApp2DomainName());
                MyConfig.setApp3DomainName(domainResult.getAppDomainName());
                PhoneConfig phoneConfigInit = PhoneConfigHelper.get().init(SplashActivity.this, domainResult.getPhoneConfig());
                if (phoneConfigInit != null) {
                    SplashActivity.this.ddyGuideImagePreLoad(phoneConfigInit);
                    return;
                }
                AppContext.getInstance().initElf();
                AppDomainRequestPresenter.getInstance().setCallback(null);
                SplashActivity.this.mHandler.sendEmptyMessage(26);
            }

            @Override // com.cyjh.share.mvp.presenter.AppDomainRequestPresenter.OnResultCallback
            public void onFailure(int i, String str) {
                SlLog.m1334i(SplashActivity.TAG, "getDomainNameFirst --> code=" + i + ",message=" + str);
                AppDomainRequestPresenter.getInstance().setCallback(null);
                SplashActivity.this.getDomainNameSecondJudgeOperate();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getDomainNameSecondJudgeOperate() {
        SlLog.m1334i(TAG, "getDomainNameSecondJudgeOperate --> ");
        if (NetworkUtils.isAvailable(this)) {
            getDomainNameSecond();
        } else {
            if (this.mHandler.hasMessages(29)) {
                return;
            }
            ToastUtils.showToastShort(this, "当前网络无法访问，请检查网络设置……");
            this.mHandler.sendEmptyMessageDelayed(29, 3000L);
        }
    }

    private void getDomainNameSecond() {
        AppDomainRequestPresenter.getInstance().getDomainNameSecond(this, new AppDomainRequestPresenter.OnResultCallback() { // from class: com.ds.daisi.activity.news.SplashActivity.3
            @Override // com.cyjh.share.mvp.presenter.AppDomainRequestPresenter.OnResultCallback
            public void onSuccess(DomainResult domainResult) {
                SlLog.m1334i(SplashActivity.TAG, "getDomainNameSecond --> onSuccess domainName=" + domainResult.getAppDomainName() + ",domain2Name=" + domainResult.getApp2DomainName());
                MyBuildConfig.AUTHORITY = domainResult.getAppDomainName();
                MyBuildConfig.AUTHORITY_VERIFY = domainResult.getAppDomainName();
                MyBuildConfig.AUTHORITY_PAY = domainResult.getAppDomainName();
                MyConfig.setAuthority(domainResult.getAppDomainName());
                MyConfig.setApp2DomainName(domainResult.getApp2DomainName());
                MyConfig.setApp3DomainName(domainResult.getApp3DomainName());
                PhoneConfig phoneConfigInit = PhoneConfigHelper.get().init(SplashActivity.this, domainResult.getPhoneConfig());
                if (phoneConfigInit != null) {
                    SplashActivity.this.ddyGuideImagePreLoad(phoneConfigInit);
                    return;
                }
                AppContext.getInstance().initElf();
                AppDomainRequestPresenter.getInstance().setCallback(null);
                SplashActivity.this.mHandler.sendEmptyMessage(26);
            }

            @Override // com.cyjh.share.mvp.presenter.AppDomainRequestPresenter.OnResultCallback
            public void onFailure(int i, String str) {
                SlLog.m1334i(SplashActivity.TAG, "getDomainNameSecond --> code=" + i + ",message=" + str);
                AppContext.getInstance().initElf();
                AppDomainRequestPresenter.getInstance().setCallback(null);
                SplashActivity.this.mHandler.sendEmptyMessage(26);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ddyGuideImagePreLoad(final PhoneConfig phoneConfig) {
        String imageUrl = phoneConfig.getImageUrl();
        final String str = getCacheDir() + File.separator + FileUtil.getFileNameByPath(phoneConfig.getImageUrl());
        SlLog.m1334i(TAG, "ddyGuideImagePreLoad --> imageUrl=" + phoneConfig.getImageUrl());
        DownloadUtil.get().download(imageUrl, getCacheDir().getAbsolutePath(), FileUtil.getFileNameByPath(phoneConfig.getImageUrl()), new DownloadUtil.OnDownloadListener() { // from class: com.ds.daisi.activity.news.SplashActivity.4
            @Override // com.cyjh.share.util.DownloadUtil.OnDownloadListener
            public void onDownloading(long j, long j2, int i) {
            }

            @Override // com.cyjh.share.util.DownloadUtil.OnDownloadListener
            public void onDownloadSuccess(File file) {
                SlLog.m1334i(SplashActivity.TAG, "ddyGuideImagePreLoad --> onDownloadSuccess --> file=" + file.getAbsolutePath());
                phoneConfig.setImageUrl(str);
                RecognitionGuideActivity.actionStart(SplashActivity.this, phoneConfig);
                SplashActivity.this.finish();
            }

            @Override // com.cyjh.share.util.DownloadUtil.OnDownloadListener
            public void onDownloadFailed(Exception exc) {
                SlLog.m1334i(SplashActivity.TAG, "ddyGuideImagePreLoad --> onDownloadFailed --> ex=" + exc.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void acquireAppData() {
        if (NetworkUtils.isAvailable(this)) {
            ThreadPoolProxyFactory.getNormalThreadPool().executeRunnable(new Runnable() { // from class: com.ds.daisi.activity.news.SplashActivity.5
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        BaseRequestValueInfo baseRequestParams = VariableAndConstantsManager.getInstance().getBaseRequestParams(SplashActivity.this);
                        SplashActivity.this.templateTimeReq = baseRequestParams.ClientTimestamp;
                        SplashActivity.this.parseJson(IpcSwap.checkTemplateReq(GsonExUtil.class2Data(baseRequestParams)), 19);
                    } catch (Exception e) {
                        ThrowableExtension.printStackTrace(e);
                        Log.e("zzz", "SplashActivity---initAfter()---2" + e.getMessage());
                    }
                }
            });
            return;
        }
        AlertDialog alertDialogShowMessageDialog = DialogManager.getInstance().showMessageDialog(this, R.layout.dialog_network_inspection, R.style.Theme_Dialog);
        alertDialogShowMessageDialog.setCancelable(false);
        alertDialogShowMessageDialog.show();
        TextView textView = (TextView) alertDialogShowMessageDialog.findViewById(R.id.id_tv_reset);
        TextView textView2 = (TextView) alertDialogShowMessageDialog.findViewById(R.id.id_tv_setting);
        textView.setOnClickListener(new ResetNetworkListener(alertDialogShowMessageDialog));
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.ds.daisi.activity.news.SplashActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SplashActivity.this.startActivity(new Intent("android.settings.DATA_ROAMING_SETTINGS"));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadStartAppRequest() {
        ThreadPoolProxyFactory.getNormalThreadPool().executeRunnable(new Runnable() { // from class: com.ds.daisi.activity.news.SplashActivity.7
            @Override // java.lang.Runnable
            public void run() {
                try {
                    BaseRequestValueInfo baseRequestParams = VariableAndConstantsManager.getInstance().getBaseRequestParams(SplashActivity.this);
                    SplashActivity.this.appStartTimeReq = VariableAndConstantsManager.sCurrentTimestamp;
                    StartAppRequestInfo startAppRequestInfo = new StartAppRequestInfo(baseRequestParams);
                    startAppRequestInfo.CloudPhoneType = PhoneConfigHelper.get().getCloudPhoneType();
                    SplashActivity.this.parseJson(IpcSwap.startAppReq(GsonExUtil.class2Data(startAppRequestInfo)), 20);
                } catch (Exception e) {
                    ThrowableExtension.printStackTrace(e);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseJson(String str, int i) throws JSONException {
        if (!TextUtils.isEmpty(str)) {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = jSONObject.getJSONObject(URLConstant.TOKEN_PARAMS_DATA);
            int i2 = jSONObject.getInt("Code");
            final String string = jSONObject.getString(Constants.DATABASE_TABLE_MSG);
            if (i2 == 200 && !TextUtils.isEmpty(jSONObject2.toString())) {
                this.mHandler.obtainMessage(i, jSONObject2.toString()).sendToTarget();
                return;
            }
            if (i2 == 1100 || i2 == 1200 || i2 == 1300 || i2 == 604 || i2 == 602) {
                this.mHandler.post(new Runnable() { // from class: com.ds.daisi.activity.news.SplashActivity.8
                    @Override // java.lang.Runnable
                    public void run() {
                        DialogManager.getInstance().displayDialogMessage(0, string, SplashActivity.this, new MyKnownButtonListener());
                    }
                });
                return;
            } else {
                this.mHandler.obtainMessage(i, jSONObject2.toString()).sendToTarget();
                return;
            }
        }
        this.mHandler.post(new Runnable() { // from class: com.ds.daisi.activity.news.SplashActivity.9
            @Override // java.lang.Runnable
            public void run() {
                com.cyjh.share.util.ToastUtils.showToastLong(SplashActivity.this, R.string.response_data_null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadILFYAd() {
        ILFYBean iLFYBeanDisplayILFYAd = ParamsWrap.getParamsWrap().displayILFYAd(1, IFLYADListener.IFLYAD_FULLSCREEN_KEY);
        SlLog.m1334i(TAG, "loadILFYAd --> ilfyAd.isDisplay =" + iLFYBeanDisplayILFYAd.isDisplay);
        if (iLFYBeanDisplayILFYAd.isDisplay) {
            try {
                IFLYNativeAd iFLYNativeAd = new IFLYNativeAd(this, iLFYBeanDisplayILFYAd.key, this.mNativeListener);
                iFLYNativeAd.setParameter(AdKeys.APP_VER, AppDeviceUtils.getVersionName(this));
                iFLYNativeAd.loadAd();
                return;
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
                toCall();
                return;
            }
        }
        setCurrentAdType(101);
        this.mHandler.obtainMessage(22).sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadFeLingAd() {
        LogUtils.m1356i(TAG, "loadFeLingAd -->");
        new MyAsyncTask().execute(new Void[0]);
    }

    private void initView() {
        this.mImgAdFullScreen = (ImageView) findViewById(R.id.iv_splash_ad_fullscreen);
        this.mTvAdMark = (TextView) findViewById(R.id.tv_splash_ad_mark);
        this.mLinearCircleDot = (LinearLayout) findViewById(R.id.id_ll_dot);
        this.mTvCloseAd = (TextView) findViewById(R.id.tv_close_ad);
        this.flSplashAd = (FrameLayout) findViewById(R.id.flSplashAd);
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

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.mClickDwonX = motionEvent.getX();
                this.mClickDwonY = motionEvent.getY();
                break;
            case 1:
                this.mClickUpX = motionEvent.getX();
                this.mClickUpY = motionEvent.getY();
                break;
        }
        return super.onTouchEvent(motionEvent);
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

    /* JADX INFO: Access modifiers changed from: private */
    public void checkReportInfoStatus() {
        SlLog.m1332d(TAG, "checkReportInfoStatus --> 1");
        List<FeLingAdReportInfo> feLingAdReportInfoList = FeLingAdDBHelper.getInstance().getFeLingAdReportInfoList(Constants.FENG_LING_AD_SPLASH_ID);
        if (CollectionUtils.isNotEmpty(feLingAdReportInfoList)) {
            for (FeLingAdReportInfo feLingAdReportInfo : feLingAdReportInfoList) {
                if (feLingAdReportInfo != null && feLingAdReportInfo.getState() >= 1) {
                    String url = feLingAdReportInfo.getUrl();
                    if (feLingAdReportInfo.getPostState() == 0) {
                        reportLogInfo(url);
                        feLingAdReportInfo.setPostState(1);
                        feLingAdReportInfo.save();
                        SlLog.m1332d(TAG, "checkReportInfoStatus --> 2 url=" + url);
                    }
                }
            }
            FeLingAdDBHelper.getInstance().deleteAllFeLingAdReportInfo(Constants.FENG_LING_AD_SPLASH_ID);
        }
        this.mHandler.sendEmptyMessageDelayed(25, 200L);
    }

    private void onClickImgFullScreenAd(View view) {
        LogUtils.m1356i(TAG, "onClickImgFullScreenAd --> isClickerFirst=" + this.isClickerFirst + ",mCurrentAdType=" + this.mCurrentAdType);
        if (this.isClickerFirst) {
            return;
        }
        this.isClickerFirst = true;
        cancelCloseTimer();
        if (this.mCurrentAdType == 100) {
            if (this.adItem != null) {
                clickXunFeiAd(view);
            }
        } else if (this.mCurrentAdType == 101) {
            onClickImgFeLingAd();
        } else if (this.mCurrentAdType == 102) {
            onClickImgBackGroundAd();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onShowImgFeLingAd() {
        SlLog.m1334i(TAG, "onShowImgFeLingAd -->");
        reportLogInfo(this.mFengLingAdInfo.getWebTracks().getImptrackUrls());
    }

    private void onClickImgFeLingAd() {
        int actionName = this.mFengLingAdInfo.getActionName();
        reportLogInfo(this.mFengLingAdInfo.getWebTracks().getCtrackUrls());
        String clickUrl = this.mFengLingAdInfo.getClickUrl();
        SlLog.m1334i(TAG, "onClickImgFeLingAd -->  actionName=" + actionName + ",clickUrl=" + clickUrl);
        if (actionName == 12) {
            this.countAdTimer = 4;
            saveInfoToDB();
            new ApkDownloadHelper(this, Constants.FENG_LING_AD_SPLASH_ID).downloadAPK(clickUrl);
            return;
        }
        toCallFeLingWebView(clickUrl);
    }

    private void reportLogInfo(List<String> list) {
        SlLog.m1334i(TAG, "reportLogInfo --> targetUrlList 1");
        if (list == null || list.size() <= 0) {
            return;
        }
        for (String str : list) {
            SlLog.m1334i(TAG, "reportLogInfo --> targetUrlList 2 url=" + str);
            reportLogInfo(str);
        }
    }

    private String dealWithUrl(String str) {
        String strReplace = str.contains(FengLingAdManager.EVENT_TIME) ? str.replace(FengLingAdManager.EVENT_TIME, String.valueOf(System.currentTimeMillis())) : str;
        if (str.contains(FengLingAdManager.CLICK_DOWN_X)) {
            strReplace = strReplace.replace(FengLingAdManager.CLICK_DOWN_X, String.valueOf(this.mClickDwonX));
        }
        if (str.contains(FengLingAdManager.CLICK_DOWN_Y)) {
            strReplace = strReplace.replace(FengLingAdManager.CLICK_DOWN_Y, String.valueOf(this.mClickDwonY));
        }
        if (str.contains(FengLingAdManager.CLICK_UP_X)) {
            strReplace = strReplace.replace(FengLingAdManager.CLICK_UP_X, String.valueOf(this.mClickUpX));
        }
        return str.contains(FengLingAdManager.CLICK_UP_Y) ? strReplace.replace(FengLingAdManager.CLICK_UP_Y, String.valueOf(this.mClickUpY)) : strReplace;
    }

    private void reportLogInfo(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String strDealWithUrl = dealWithUrl(str);
        SlLog.m1334i(TAG, "reportLogInfo --> srcUrl=" + strDealWithUrl);
        this.fengLingAdManager.reportLogInfo(this, strDealWithUrl, new Callback<FengLingAdInfo>() { // from class: com.ds.daisi.activity.news.SplashActivity.10
            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void finish(FengLingAdInfo fengLingAdInfo) {
                SlLog.m1334i(SplashActivity.TAG, "reportLogInfo --> finish ");
            }

            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void error(String str2) {
                SlLog.m1334i(SplashActivity.TAG, "reportLogInfo --> error=" + str2);
            }
        });
    }

    private void onClickImgBackGroundAd() {
        AppStatisticsPresenter.getStatisticsPresenter().setCallback(new AppStatisticsPresenter.OnCallback() { // from class: com.ds.daisi.activity.news.SplashActivity.11
            @Override // com.cyjh.share.mvp.presenter.AppStatisticsPresenter.OnCallback
            public void onCallbackSuc(Object obj) {
                AppStatisticsPresenter.getStatisticsPresenter().cleanCallback();
                ParamsWrap.getParamsWrap().onClickBackgroundAdOpera(SplashActivity.this);
            }
        }).loadAd(this, 1, 3, ParamsWrap.getParamsWrap().mBackgroundSetAdInfo.f1680Id);
    }

    private void clickXunFeiAd(View view) {
        this.isClickerFirst = true;
        this.adItem.onClick(view);
        if (this.adILFYType == 3) {
            this.mHandler.postDelayed(new Runnable() { // from class: com.ds.daisi.activity.news.SplashActivity.12
                @Override // java.lang.Runnable
                public void run() {
                    SplashActivity.this.toCall();
                }
            }, 300L);
        }
    }

    public void startupSuc(AppStartupResponse appStartupResponse) {
        AuthorRelatedInfo authorRelatedInfo = appStartupResponse.AuthorInfo;
        if (authorRelatedInfo.ServicePackStatus == 0) {
            appCloseDialog(authorRelatedInfo.ServicePackStatus, authorRelatedInfo.ServicePackPrompt);
            return;
        }
        LogUtils.m1356i(TAG, "startupSuc --> authorRelatedInfo.IsFreeAd=" + authorRelatedInfo.IsFreeAd);
        ParamsWrap.getParamsWrap().isFreeAd = authorRelatedInfo.IsFreeAd;
        ParamsWrap.getParamsWrap().engineToken = authorRelatedInfo.EngineToken;
        AppRelatedInfo appRelatedInfo = appStartupResponse.AppInfo;
        ParamsWrap.getParamsWrap().dailyTryTimes = appRelatedInfo.DailyTryTimes;
        ParamsWrap.getParamsWrap().feedbackTips = appRelatedInfo.FeedbackTips;
        ParamsWrap.getParamsWrap().onceTryMinute = appRelatedInfo.OnceTryMinute;
        ParamsWrap.getParamsWrap().unbindDeductHours = appRelatedInfo.UnbindDeductHours;
        if (!TextUtils.isEmpty(appRelatedInfo.ScriptKey)) {
            AppContext.getInstance().tokenKey = Long.parseLong(appRelatedInfo.ScriptKey);
        }
        ParamsWrap.getParamsWrap().crateCollect();
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
            ErrorUtilHelper.getInstance().writeProcessedStringToFile(AppContext.getInstance(), "SplashActivity  startupSuc --> 即将停用");
            DialogManager.getInstance().displayDialogMessage(authorRelatedInfo.ServicePackStatus, authorRelatedInfo.ServicePackPrompt, this, new MyKnownButtonListener());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void appCloseDialog(int i, String str) {
        this.isLoadCompleteStartup = true;
        DialogManager.getInstance().displayDialogMessage(i, str, this, new MyKnownButtonListener());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadAdOrCallNextPage() {
        if (this.isLoadCompleteTemplateVerify && !this.isLoadCompleteStartup) {
            this.mHandler.obtainMessage(21).sendToTarget();
        }
        if (!ParamsWrap.getParamsWrap().isFreeAd()) {
            LogUtils.m1356i(TAG, "loadAdOrCallNextPage --> 不免广告");
            loadAdMessage();
        } else {
            LogUtils.m1356i(TAG, "loadAdOrCallNextPage --> 免广告");
            IntentUtils.toCallElfinPayActivity(this);
            finish();
        }
    }

    private class SmallBallPollingRunnable implements Runnable {
        private SmallBallPollingRunnable() {
        }

        public void start() {
            SplashActivity.this.mHandler.removeCallbacks(this);
            SplashActivity.this.mHandler.postDelayed(this, 100L);
        }

        @Override // java.lang.Runnable
        public void run() {
            SplashActivity.access$1308(SplashActivity.this);
            for (int i = 0; i < SplashActivity.this.imageViewLists.size(); i++) {
                if (i == SplashActivity.this.currentDotCounts % SplashActivity.this.imageViewLists.size()) {
                    ((ImageView) SplashActivity.this.imageViewLists.get(i)).setImageDrawable(SplashActivity.this.mBlueGradientDrawable);
                } else {
                    ((ImageView) SplashActivity.this.imageViewLists.get(i)).setImageDrawable(SplashActivity.this.mGrayGradientDrawable);
                }
            }
            SplashActivity.this.mHandler.removeCallbacks(this);
            SplashActivity.this.mHandler.postDelayed(this, 100L);
        }
    }

    private static class MyHandler extends Handler {
        private WeakReference<SplashActivity> mWeakReference;

        private MyHandler(SplashActivity splashActivity) {
            this.mWeakReference = new WeakReference<>(splashActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            SplashActivity splashActivity = this.mWeakReference.get();
            if (splashActivity != null) {
                switch (message.what) {
                    case 15:
                        AppStatisticsPresenter.getStatisticsPresenter().loadAd(splashActivity, 2, 2, 1L);
                        break;
                    case 16:
                        splashActivity.loadBackgroundAd();
                        break;
                    case 17:
                        splashActivity.loadILFYAd();
                        break;
                    case 18:
                        AppStatisticsPresenter.getStatisticsPresenter().loadAd(splashActivity, 1, 2, ParamsWrap.getParamsWrap().mBackgroundSetAdInfo.f1680Id);
                        break;
                    case 19:
                        TemplateResponse templateResponse = (TemplateResponse) GsonExUtil.parsData((String) message.obj, TemplateResponse.class);
                        if (templateResponse != null && splashActivity.templateTimeReq == templateResponse.ClientTimestamp) {
                            splashActivity.templateVerifySuccess(templateResponse);
                            break;
                        } else if (splashActivity.templateTimeReq != templateResponse.ClientTimestamp && splashActivity.templateTimeReq != 0) {
                            splashActivity.appCloseDialog(0, splashActivity.getString(R.string.check_sign_fail));
                            break;
                        }
                        break;
                    case 20:
                        AppStartupResponse appStartupResponse = (AppStartupResponse) GsonExUtil.parsData((String) message.obj, AppStartupResponse.class);
                        if (appStartupResponse != null && splashActivity.appStartTimeReq == appStartupResponse.ClientTimestamp) {
                            splashActivity.startupSuc(appStartupResponse);
                            break;
                        } else if (splashActivity.appStartTimeReq != appStartupResponse.ClientTimestamp && splashActivity.appStartTimeReq != 0) {
                            splashActivity.appCloseDialog(0, splashActivity.getString(R.string.check_sign_fail));
                            break;
                        }
                        break;
                    case 21:
                        splashActivity.loadStartAppRequest();
                        break;
                    case 22:
                        splashActivity.loadFeLingAd();
                        break;
                    case 23:
                        splashActivity.onShowImgFeLingAd();
                        break;
                    case 24:
                        IntentUtils.toCallElfinPayActivity(splashActivity);
                        break;
                    case 25:
                        splashActivity.acquireAppData();
                        break;
                    case 26:
                        splashActivity.checkReportInfoStatus();
                        break;
                    case 27:
                        splashActivity.toCall();
                        break;
                    case 28:
                        splashActivity.getDomainNameFirstJudgeOperate();
                        break;
                    case 29:
                        splashActivity.getDomainNameSecondJudgeOperate();
                        break;
                }
            }
        }
    }

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        this.mHandler.removeCallbacksAndMessages(null);
        cancelCloseTimer();
        this.isClickerFirst = false;
        this.adItem = null;
        AppStatisticsPresenter.getStatisticsPresenter().onCancel();
        EventBus.getDefault().unregister(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadBackgroundAd() {
        boolean zIsDisplayBackgroundSetAd = ParamsWrap.getParamsWrap().isDisplayBackgroundSetAd(1);
        SlLog.m1334i(TAG, "加载后台广告 handleMessage --> isDisplay=" + zIsDisplayBackgroundSetAd);
        if (zIsDisplayBackgroundSetAd) {
            String str = ParamsWrap.getParamsWrap().mBackgroundSetAdInfo != null ? ParamsWrap.getParamsWrap().mBackgroundSetAdInfo.ImageUrl : "";
            SlLog.m1334i(TAG, "加载后台广告 handleMessage --> imageUrl=" + str);
            if (!TextUtils.isEmpty(str)) {
                SlToast.showToastShort(this, "后台广告加载成功");
                displayFullScreenAD(str);
                return;
            } else {
                toCall();
                return;
            }
        }
        toCall();
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
            SplashActivity.access$3008(SplashActivity.this);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            SplashActivity.this.toCall();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toCall() {
        IntentUtils.toCallFullscreenTwoAdActivity(this);
        finish();
    }

    private void saveInfoToDB() {
        FengLingAdInfo.WebTracks webTracks = this.mFengLingAdInfo.getWebTracks();
        List<String> dstrackUrls = webTracks.getDstrackUrls();
        List<String> istrackUrls = webTracks.getIstrackUrls();
        if (CollectionUtils.isNotEmpty(dstrackUrls)) {
            Iterator<String> it2 = dstrackUrls.iterator();
            while (it2.hasNext()) {
                String strDealWithUrl = dealWithUrl(it2.next());
                SlLog.m1334i(TAG, "saveInfoToDB --> dealWithDstrackUrl=" + strDealWithUrl);
                new FeLingAdReportInfo(Constants.FENG_LING_AD_SPLASH_ID, strDealWithUrl, 1, 0, 0).save();
            }
        }
        if (CollectionUtils.isNotEmpty(istrackUrls)) {
            Iterator<String> it3 = istrackUrls.iterator();
            while (it3.hasNext()) {
                new FeLingAdReportInfo(Constants.FENG_LING_AD_SPLASH_ID, dealWithUrl(it3.next()), 2, 0, 0).save();
            }
        }
    }

    private void toCallFeLingWebView(String str) {
        LogUtils.m1356i(TAG, "toCallFeLingWebView --->  url=" + str);
        IntentUtils.toCallFengLingAdWebViewActivity(this, str, 1);
        finish();
    }

    private class MyAsyncTask extends AsyncTask<Void, Void, String> {
        private MyAsyncTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public String doInBackground(Void... voidArr) {
            try {
                return CommonUtils.getPublicInternetIP();
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(String str) {
            SplashActivity.this.getFeLingAdInfoOperate(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getFeLingAdInfoOperate(String str) {
        FengLingAdManager.PUBLIC_INTERNET_IP = str;
        this.fengLingAdManager.getFengLingAdInfo(TAG, 2, new Callback<FengLingAdInfo>() { // from class: com.ds.daisi.activity.news.SplashActivity.14
            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void finish(FengLingAdInfo fengLingAdInfo) {
                SlToast.showToastShort(SplashActivity.this, "风灵广告加载成功");
                SlLog.m1334i(SplashActivity.TAG, "getFeLingAdInfoOperate --> finish");
                SplashActivity.this.mFengLingAdInfo = fengLingAdInfo;
                String src = fengLingAdInfo.getImgs().getSrc();
                if (TextUtils.isEmpty(src)) {
                    SplashActivity.this.toCall();
                } else {
                    SplashActivity.this.displayFullScreenAD(src);
                }
            }

            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void error(String str2) {
                SlToast.showToastShort(SplashActivity.this, "风灵广告加载失败 message=" + str2);
                SlLog.m1334i(SplashActivity.TAG, "getFeLingAdInfoOperate --> error=" + str2);
                SplashActivity.this.setCurrentAdType(102);
                SplashActivity.this.mHandler.obtainMessage(16).sendToTarget();
            }
        }, this, MyBuildConfig.FENG_LING_AD_URL);
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
        } else if (this.mCurrentAdType == 101) {
            this.mHandler.obtainMessage(23).sendToTarget();
        } else if (this.mCurrentAdType == 102) {
            this.mHandler.obtainMessage(18).sendToTarget();
        }
        this.mHandler.postDelayed(new Runnable() { // from class: com.ds.daisi.activity.news.SplashActivity.15
            @Override // java.lang.Runnable
            public void run() {
                SplashActivity.this.mTvCloseAd.setVisibility(0);
            }
        }, 300L);
    }

    public void templateVerifySuccess(TemplateResponse templateResponse) {
        this.isLoadCompleteTemplateVerify = true;
        VariableAndConstantsManager.getInstance().templateType = templateResponse.TemplateType;
        if (templateResponse.Status == 0 || templateResponse.Status == 2) {
            ErrorUtilHelper.getInstance().writeProcessedStringToFile(AppContext.getInstance(), "SplashActivity  templateVerifySuccess --> 停用，或即将停用的提示");
            DialogManager.getInstance().displayDialogMessage(templateResponse.Status, templateResponse.Remark, this, new MyKnownButtonListener());
        } else if (templateResponse.Status == 1) {
            this.mHandler.obtainMessage(21).sendToTarget();
        }
    }

    private class MyKnownButtonListener extends BaseTextViewKnownListener {
        private MyKnownButtonListener() {
        }

        @Override // com.p024ds.daisi.listener.BaseTextViewKnownListener
        public void pass() {
            SplashActivity.this.loadAdOrCallNextPage();
        }

        @Override // com.p024ds.daisi.listener.BaseTextViewKnownListener
        public void closeApp() {
            SplashActivity.this.stopService(new Intent(SplashActivity.this, (Class<?>) PhoneStateService.class));
            SplashActivity.this.stopService(new Intent(SplashActivity.this, (Class<?>) IpcService.class));
            ActivitysManager.getActivitysManager().finishAllActivity();
        }
    }

    private void loadAdMessage() {
        if (this.isLoadCompleteStartup && this.isLoadCompleteTemplateVerify) {
            this.isLoadCompleteTemplateVerify = false;
            this.isLoadCompleteStartup = false;
            judgeLoadAdOperate();
        }
    }

    private void judgeLoadAdOperate() {
        setCurrentAdType(100);
        this.mHandler.obtainMessage(17).sendToTarget();
    }

    private class ResetNetworkListener implements View.OnClickListener {
        private AlertDialog mAlertDialog;

        private ResetNetworkListener(AlertDialog alertDialog) {
            this.mAlertDialog = alertDialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.mAlertDialog.dismiss();
            SplashActivity.this.acquireAppData();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurrentAdType(int i) {
        this.mCurrentAdType = i;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void dealWithAdEvent(AdEvent adEvent) {
        switch (adEvent.getType()) {
            case 100:
                onDownloadSuccessOperate();
                break;
            case 101:
                onInstallSuccessOperate(adEvent.getPackageName());
                break;
        }
    }

    private void onDownloadSuccessOperate() {
        SlLog.m1334i(TAG, "onDownloadSuccessOperate --> 1 ");
        List<FeLingAdReportInfo> feLingAdReportInfoList = FeLingAdDBHelper.getInstance().getFeLingAdReportInfoList(Constants.FENG_LING_AD_SPLASH_ID);
        if (CollectionUtils.isNotEmpty(feLingAdReportInfoList)) {
            SlLog.m1334i(TAG, "onDownloadSuccessOperate --> 2");
            for (FeLingAdReportInfo feLingAdReportInfo : feLingAdReportInfoList) {
                if (feLingAdReportInfo != null && feLingAdReportInfo.getState() >= 1) {
                    String url = feLingAdReportInfo.getUrl();
                    if (1 == feLingAdReportInfo.getType() && feLingAdReportInfo.getPostState() == 0) {
                        reportLogInfo(url);
                        feLingAdReportInfo.setPostState(1);
                        feLingAdReportInfo.save();
                        SlLog.m1332d(TAG, "onDownloadSuccessOperate --> 3 url=" + url);
                    }
                }
            }
        }
    }

    private void onInstallSuccessOperate(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SlLog.m1334i(TAG, "onInstallSuccessOperate --> 1 ");
        List<FeLingAdReportInfo> feLingAdReportInfoList = FeLingAdDBHelper.getInstance().getFeLingAdReportInfoList(Constants.FENG_LING_AD_SPLASH_ID);
        if (CollectionUtils.isNotEmpty(feLingAdReportInfoList)) {
            SlLog.m1334i(TAG, "onInstallSuccessOperate --> 2 ");
            for (FeLingAdReportInfo feLingAdReportInfo : feLingAdReportInfoList) {
                String url = feLingAdReportInfo.getUrl();
                SlLog.m1334i(TAG, "onInstallSuccessOperate --> 3 from packageName=" + str + ",local packageName=" + feLingAdReportInfo.getApkPageName());
                if (str.contains(feLingAdReportInfo.getApkPageName())) {
                    if (2 == feLingAdReportInfo.getType() && feLingAdReportInfo.getPostState() == 0) {
                        LogUtils.m1356i(TAG, "onInstallSuccessOperate --> packageName=" + str);
                        reportLogInfo(url);
                        feLingAdReportInfo.setPostState(1);
                    }
                    feLingAdReportInfo.setState(2);
                    feLingAdReportInfo.save();
                }
            }
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == REQUEST_FENG_LING_AD_WEB_VIEW) {
            toCall();
        }
    }
}
