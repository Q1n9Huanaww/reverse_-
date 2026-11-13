package com.p024ds.daisi.activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import com.cyjh.feedback.lib.dialog.FeedBackCommitDialog;
import com.cyjh.feedback.lib.executor.ThreadPoolProxyFactory;
import com.cyjh.mobileanjian.ipc.uip.UipHelper;
import com.cyjh.p019mq.sdk.MqRunner;
import com.cyjh.p019mq.sdk.entity.Script4Run;
import com.cyjh.p019mq.service.IpcService;
import com.cyjh.share.bean.NotifyMsgBean;
import com.cyjh.share.bean.response.BaseResponseInfo;
import com.cyjh.share.bean.response.ScriptStartRunResponse;
import com.cyjh.share.bean.response.VersionUpdateInfo;
import com.cyjh.share.config.MyConfig;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.cyjh.share.dialog.LibraryUILoadingDialog;
import com.cyjh.share.helper.RecognitionLibraryDownloadHelper;
import com.cyjh.share.manager.ActivitysManager;
import com.cyjh.share.mvp.manager.VersionUpdateManager;
import com.cyjh.share.mvp.presenter.AppStatisticsPresenter;
import com.cyjh.share.mvp.presenter.NoticeListPresenter;
import com.cyjh.share.mvp.view.PushMessageView;
import com.cyjh.share.util.AppUtils;
import com.cyjh.share.util.CommonUtil;
import com.cyjh.share.util.FastClickUtil;
import com.cyjh.share.util.GsonExUtil;
import com.cyjh.share.util.SlLog;
import com.cyjh.share.util.SpUtil;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.adpter.TabAdapter;
import com.p024ds.daisi.constant.APPConstant;
import com.p024ds.daisi.constant.Constants;
import com.p024ds.daisi.customview.RTDViewPager;
import com.p024ds.daisi.database.MsgDatabaseOpera;
import com.p024ds.daisi.dialog.UpdateDialog;
import com.p024ds.daisi.entity.BindRegCodeResponse;
import com.p024ds.daisi.entity.MsgItem;
import com.p024ds.daisi.entity.ParamsWrap;
import com.p024ds.daisi.entity.RefreshTimeEvent;
import com.p024ds.daisi.entity.RegCodeStatusInfo;
import com.p024ds.daisi.entity.ScriptLog;
import com.p024ds.daisi.fragment.DescriptionFragment;
import com.p024ds.daisi.fragment.ScriptUipSetFragment;
import com.p024ds.daisi.mvp.presenters.news.BindRegisterCodePresenter;
import com.p024ds.daisi.mvp.views.BindRegCodeView;
import com.p024ds.daisi.services.BootService;
import com.p024ds.daisi.services.PhoneStateService;
import com.p024ds.daisi.services.TimerService;
import com.p024ds.daisi.services.VerifyService;
import com.p024ds.daisi.util.AppDeviceUtils;
import com.p024ds.daisi.util.ExpireTimeUtil;
import com.p024ds.daisi.util.FileComparator;
import com.p024ds.daisi.util.FileUtils;
import com.p024ds.daisi.util.IntentUtils;
import com.p024ds.daisi.util.NetworkUtils;
import com.p024ds.daisi.util.ScriptDownloadHelper;
import com.p024ds.daisi.util.ToastUtils;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.amap.UMAmapConfig;
import com.viewpagerindicator.TabPageIndicator;
import com.warnship.autoplay.R;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import killer.App;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.slf4j.Marker;

/* JADX INFO: loaded from: classes.dex */
public class ElfinPayActivity extends BaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, PushMessageView, BindRegCodeView {
    public static final int DEFAULT_THREE_SECOND = 3000;
    private static final int MINUTE_OF_DAY = 1440;
    public static final int MSG_DEFAULT_DELAY = 3000;
    public static final int MSG_UPDATE_VERSION_REQUEST_JUDGE_OPERATE = 1012;
    public static final int NOTIFY_MESSAGE_BOOT_SCRIPT_RUN = 1008;
    public static final int NOTIFY_MESSAGE_INIT_DB = 1005;
    public static final int NOTIFY_MESSAGE_LOAD_MORE_TIME = 1006;
    private static final int NOTIFY_MESSAGE_PROMPT = 1002;
    public static final int NOTIFY_MESSAGE_STATISTICS_DAY = 1007;
    private static final int NOTIFY_TYPE = 1001;
    private static final int NUMBER_VALUE_60 = 60;
    private static final int REQUEST_CODE_FEEDBACK = 1;
    public static final String TAG = "ElfinPayActivity";
    public static final int TIMER_NOTIFY_DAILY_STATISTICS = 1009;
    public static final int TIMER_NOTIFY_EXECUTION_TASK = 1010;
    public static final int TIME_INTERVAL = 1000;
    private AppContext appContext;
    private MsgDatabaseOpera databaseOpera;
    private FeedBackCommitDialog feedBackCommitDialog;
    private BindRegisterCodePresenter mBindRegisterCodePresenter;
    private NotificationCompat.Builder mBuilder;
    private DrawerLayout mDrawerLayout;
    private ScheduledFuture<?> mFuture;
    private ImageView mImgRedDot;
    private boolean mIsInvokingFromEngineHeart;
    private NoticeListPresenter mNoticeListPresenter;
    private NotificationManager mNotifyManager;
    private OnDisplayExpireTimeBackListener mOnDisplayExpireTimeBackListener;
    private ScriptUipSetFragment mScriptUipSetFragment;
    private SharedPreferences mSharedPres;
    private SubmitTimeCount mSubmitTimeCount;
    private TextView mTvExpireTime;
    private Switch swtCallStop;
    private Switch swtFloatviewCtrl;
    private Switch swtOpenRunScript;
    private Switch swtVibration;
    private Switch swtVolumeKeyCtrl;
    private Switch swtWaggleStop;
    private Switch swtWordRecognitionLibrary;
    private ArrayList<NotifyMsgBean> msgDataList = new ArrayList<>();
    private ArrayList<NotifyMsgBean> msgPartDataList = new ArrayList<>();
    private Set<String> msgIdList = new HashSet();
    private boolean doubleBackToExitPressedOnce = false;
    private boolean isFirstPushMessage = false;
    private MyHandler mHandler = new MyHandler();
    public long bindRegCodeBackTime = -1;
    public boolean isDisplayUI = false;
    private ScheduledExecutorService mScheduledExecutorService = new ScheduledThreadPoolExecutor(1);
    private ArrayList<MyTouchListener> myTouchListeners = new ArrayList<>();
    private boolean isRegCodeResponseNull = false;

    public interface MyTouchListener {
        boolean onTouchEvent(MotionEvent motionEvent);
    }

    public interface OnDisplayExpireTimeBackListener {
        void onDisplayExpireTimeBack();
    }

    private void updateVersionRequest(boolean z) {
    }

    public void judgeTimeOperate() {
    }

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(48);
        MqRunner.getInstance().setScript(new Script4Run());
        EventBus.getDefault().register(this);
        AppContext.getInstance().isEntryMainActivity = true;
        this.appContext = (AppContext) getApplicationContext();
        this.databaseOpera = new MsgDatabaseOpera();
        if (this.appContext.mUipHelper == null) {
            this.appContext.mUipHelper = new UipHelper(this);
        }
        initDatabase();
        initView();
        initViewAfter();
        App.init(this);
    }

    private void initViewAfter() {
        this.mNoticeListPresenter = new NoticeListPresenter(this);
        if (NetworkUtils.isAvailable(this)) {
            ThreadPoolProxyFactory.getNormalThreadPool().executeRunnable(new Runnable() { // from class: com.ds.daisi.activity.ElfinPayActivity.1
                @Override // java.lang.Runnable
                public void run() {
                    boolean zIsAppDayActivity = AppUtils.isAppDayActivity(InterfaceRelatedConstants.APP_PAY_DAY_LIFE_STATIS, ElfinPayActivity.this);
                    String strTimeStamp2Date = AppUtils.timeStamp2Date(System.currentTimeMillis() / 1000, AppUtils.TIME_FORMAT_DAY);
                    if (!zIsAppDayActivity) {
                        ElfinPayActivity.this.mHandler.obtainMessage(1007).sendToTarget();
                        AppUtils.writeDateToFile(InterfaceRelatedConstants.APP_PAY_DAY_LIFE_STATIS, ElfinPayActivity.this, strTimeStamp2Date);
                    }
                    ElfinPayActivity.this.mHandler.obtainMessage(1010, Long.valueOf((AppUtils.timeToStamp(strTimeStamp2Date) - System.currentTimeMillis()) / 1000)).sendToTarget();
                }
            });
            this.mNoticeListPresenter.loadAd(this);
        }
        updateVersionRequestJudgeOperate();
        initSwitchStatus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void statisDailyLife(long j) {
        if (j < 0) {
            return;
        }
        if (this.mFuture != null) {
            this.mFuture.cancel(false);
            this.mFuture = null;
        }
        this.mFuture = this.mScheduledExecutorService.schedule(new Runnable() { // from class: com.ds.daisi.activity.ElfinPayActivity.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    AppUtils.writeDateToFile(InterfaceRelatedConstants.APP_PAY_DAY_LIFE_STATIS, ElfinPayActivity.this, AppUtils.timeStamp2Date(System.currentTimeMillis() / 1000, AppUtils.TIME_FORMAT_DAY));
                    ElfinPayActivity.this.mHandler.obtainMessage(1009).sendToTarget();
                } catch (IllegalArgumentException e) {
                    ThrowableExtension.printStackTrace(e);
                }
            }
        }, j + ((long) (new Random().nextInt(18600) + 600)), TimeUnit.SECONDS);
    }

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() throws Throwable {
        super.onResume();
        MobclickAgent.onResume(this);
        judgeTimeOperate();
        if (this.isFirstPushMessage && NetworkUtils.isAvailable(this)) {
            this.mNoticeListPresenter.loadAd(this);
        }
    }

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Log.e("zzz", "ElfinPayActivity--onDestory:" + AppContext.sIsBooted);
        AppContext.getInstance().isForceUpdate = false;
        AppContext.getInstance().isEntryMainActivity = false;
        AppContext.getInstance().regCode = "";
        if (this.mFuture != null) {
            this.mFuture.cancel(false);
        }
        this.mHandler.removeCallbacksAndMessages(null);
        EventBus.getDefault().unregister(this);
        AppStatisticsPresenter.getStatisticsPresenter().cancelAllStatistics();
        ParamsWrap.getParamsWrap().cleanData();
        if (this.mNoticeListPresenter != null) {
            this.mNoticeListPresenter.onCancel();
        }
        if (this.mBindRegisterCodePresenter != null) {
            this.mBindRegisterCodePresenter.onCancel();
        }
        if (this.mSubmitTimeCount != null) {
            this.mSubmitTimeCount.cancel();
            this.mSubmitTimeCount = null;
        }
        AppContext.getInstance().isEmulator = false;
    }

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() throws Throwable {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageThreadMain(MsgItem msgItem) {
        int msgType = msgItem.getMsgType();
        if (msgType == 1001) {
            this.mImgRedDot.setVisibility(4);
            return;
        }
        if (msgType == 1003) {
            this.msgDataList.clear();
            this.databaseOpera.deleteAll();
        } else if (msgType == 1005) {
            this.mIsInvokingFromEngineHeart = true;
            updateVersionRequestJudgeOperate();
        } else {
            if (msgType != 1007) {
                return;
            }
            refreshSwitchScriptRun("1".equals(msgItem.getMessage()));
        }
    }

    private void refreshSwitchScriptRun(boolean z) {
        this.swtOpenRunScript.setChecked(z);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageThreadMain(MsgItem.BindRegCodeBus bindRegCodeBus) {
        Log.e("zzz", "ElfinPayActivity:onMessageThreadMain:--" + bindRegCodeBus.getExpireTime());
        if (bindRegCodeBus.getExpireTime() > 0) {
            MqRunner.getInstance().setRegCode(AppContext.getInstance().regCode);
            this.bindRegCodeBackTime = bindRegCodeBus.getExpireTime();
            this.appContext.STATE_APP = 0;
        } else if (bindRegCodeBus.getExpireTime() == -1) {
            this.bindRegCodeBackTime = bindRegCodeBus.getExpireTime();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshTime(RefreshTimeEvent refreshTimeEvent) {
        SlLog.m1334i(TAG, "onRefreshTime -->");
        judgeTimeOperate();
    }

    private void displayRemainTime(long j) {
        this.mTvExpireTime.setText(ExpireTimeUtil.convertTimeFormat(this, j));
    }

    private void displayExpireTimeOperate() {
        long jCurrentTimeMillis = (AppContext.getInstance().ExpireTime - (System.currentTimeMillis() / 1000)) / 60;
        if (jCurrentTimeMillis > 0) {
            displayRemainTime(jCurrentTimeMillis);
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        this.isFirstPushMessage = true;
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.doubleBackToExitPressedOnce) {
            if (MqRunner.getInstance().isScriptStarted()) {
                MqRunner.getInstance().stop();
            }
            AppContext.getInstance().deleteFloatView();
            exitAll();
            ActivitysManager.getActivitysManager().finishAllActivity();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        ToastUtils.showToastShort(this, R.string.main_toast_exit);
        this.mHandler.postDelayed(new Runnable() { // from class: com.ds.daisi.activity.ElfinPayActivity.3
            @Override // java.lang.Runnable
            public void run() {
                ElfinPayActivity.this.doubleBackToExitPressedOnce = false;
            }
        }, 2000L);
    }

    private void initDatabase() {
        this.databaseOpera.queryAll(this.mHandler);
        ThreadPoolProxyFactory.getNormalThreadPool().executeRunnable(new Runnable() { // from class: com.ds.daisi.activity.ElfinPayActivity.4
            @Override // java.lang.Runnable
            public void run() {
                String fileContent = AppUtils.getFileContent(InterfaceRelatedConstants.REG_CODE_FILE_SAVE + MyConfig.getAppid(), ElfinPayActivity.this);
                String fileContent2 = AppUtils.getFileContent(InterfaceRelatedConstants.REG_CODE_STATUS_FILE_SAVE + MyConfig.getAppid(), ElfinPayActivity.this);
                if (TextUtils.isEmpty(fileContent2)) {
                    ElfinPayActivity.this.notifyLoadMoreTime(fileContent);
                    return;
                }
                RegCodeStatusInfo regCodeStatusInfo = (RegCodeStatusInfo) GsonExUtil.parsData(fileContent2, RegCodeStatusInfo.class);
                if (regCodeStatusInfo == null) {
                    ElfinPayActivity.this.notifyLoadMoreTime(fileContent);
                    return;
                }
                AppContext.getInstance().regCode = regCodeStatusInfo.regCode;
                if (TextUtils.isEmpty(regCodeStatusInfo.regCode) || regCodeStatusInfo.status != 1) {
                    ElfinPayActivity.this.mHandler.obtainMessage(1008).sendToTarget();
                } else {
                    ElfinPayActivity.this.mHandler.obtainMessage(1006).sendToTarget();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyLoadMoreTime(String str) {
        AppContext.getInstance().regCode = str;
        if (!TextUtils.isEmpty(AppContext.getInstance().regCode)) {
            this.mHandler.obtainMessage(1006).sendToTarget();
        } else {
            this.mHandler.obtainMessage(1008).sendToTarget();
        }
    }

    private void initView() {
        initTitle();
        initSideMenu();
        judgeRecognitionLibraryAvailable();
        checkResolution();
        initPager();
        isRedVisibility();
    }

    private void initSideMenu() {
        this.mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawer);
        this.swtCallStop = (Switch) findViewById(R.id.swtich_setting_call_stop);
        this.swtVolumeKeyCtrl = (Switch) findViewById(R.id.swtich_setting_volume_key_ctrl);
        this.swtWaggleStop = (Switch) findViewById(R.id.swtich_setting_waggle_stop);
        this.swtFloatviewCtrl = (Switch) findViewById(R.id.swtich_setting_floatview_ctrl);
        this.swtVibration = (Switch) findViewById(R.id.swtich_setting_vibration);
        this.swtOpenRunScript = (Switch) findViewById(R.id.swtich_setting_open_run);
        this.swtWordRecognitionLibrary = (Switch) findViewById(R.id.switch_setting_word_recognition_library);
        TextView textView = (TextView) findViewById(R.id.button_setting_scriptlog);
        TextView textView2 = (TextView) findViewById(R.id.setting_button_update);
        TextView textView3 = (TextView) findViewById(R.id.button_setting_feedback);
        Button button = (Button) findViewById(R.id.btn_get_more_time);
        this.mTvExpireTime = (TextView) findViewById(R.id.id_tv_use_time);
        this.swtCallStop.setOnCheckedChangeListener(this);
        this.swtVolumeKeyCtrl.setOnCheckedChangeListener(this);
        this.swtWaggleStop.setOnCheckedChangeListener(this);
        this.swtFloatviewCtrl.setOnCheckedChangeListener(this);
        this.swtVibration.setOnCheckedChangeListener(this);
        this.swtOpenRunScript.setOnCheckedChangeListener(this);
        textView.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        button.setOnClickListener(this);
    }

    private void judgeRecognitionLibraryAvailable() {
        if (RecognitionLibraryDownloadHelper.get().isRecognitionLibraryAvailable()) {
            this.swtWordRecognitionLibrary.setChecked(true);
            this.swtWordRecognitionLibrary.setClickable(false);
        } else {
            this.swtWordRecognitionLibrary.setChecked(false);
            this.swtWordRecognitionLibrary.setOnCheckedChangeListener(this);
        }
    }

    private void initTitle() {
        ((TextView) findViewById(R.id.id_tv_title)).setText(R.string.app_name);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.id_ll_navigation);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.id_rl_msg);
        this.mImgRedDot = (ImageView) findViewById(R.id.id_img_red_dot);
        linearLayout.setOnClickListener(this);
        relativeLayout.setOnClickListener(this);
    }

    private void checkResolution() {
        TextView textView = (TextView) findViewById(R.id.tv_resolution_unsupport);
        String str = AppDeviceUtils.getResolution(this).x + Marker.ANY_MARKER + AppDeviceUtils.getResolution(this).y;
        String str2 = AppDeviceUtils.getResolution(this).y + Marker.ANY_MARKER + AppDeviceUtils.getResolution(this).x;
        String string = getString(R.string.app_resolution);
        if (string.contains(str) || string.contains(str2) || TextUtils.isEmpty(string)) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
        }
    }

    private void initPager() {
        ArrayList arrayList = new ArrayList();
        TabPageIndicator tabPageIndicator = (TabPageIndicator) findViewById(R.id.indicator);
        TabAdapter tabAdapter = new TabAdapter(this, getSupportFragmentManager(), arrayList);
        RTDViewPager rTDViewPager = (RTDViewPager) findViewById(R.id.viewpager);
        this.mScriptUipSetFragment = new ScriptUipSetFragment();
        DescriptionFragment descriptionFragment = new DescriptionFragment();
        arrayList.add(this.mScriptUipSetFragment);
        arrayList.add(descriptionFragment);
        rTDViewPager.setAdapter(tabAdapter);
        tabPageIndicator.setViewPager(rTDViewPager);
    }

    public void isRedVisibility() {
        this.mSharedPres = getSharedPreferences(Constants.SMALL_RED_DOT, 0);
        if (this.mSharedPres.getBoolean(Constants.RED_DOT_FLAG, false)) {
            this.mImgRedDot.setVisibility(0);
        }
    }

    private void exitAll() {
        try {
            this.mScriptUipSetFragment.generateOptionJson();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        exit();
    }

    private void exit() {
        stopService(new Intent(this, (Class<?>) VerifyService.class));
        stopService(new Intent(this, (Class<?>) IpcService.class));
        stopService(new Intent(this, (Class<?>) TimerService.class));
        stopService(new Intent(this, (Class<?>) BootService.class));
        stopService(new Intent(this, (Class<?>) PhoneStateService.class));
        AppContext.getInstance().onKillProcess();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_ll_navigation /* 2131755330 */:
                this.mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.id_rl_msg /* 2131755331 */:
                this.mImgRedDot.setVisibility(4);
                Intent intent = new Intent(this, (Class<?>) MessagePushActivity.class);
                if (this.msgDataList != null && this.msgDataList.size() > 0) {
                    intent.putParcelableArrayListExtra(Constants.MSG_DATA, this.msgDataList);
                }
                startActivity(intent);
                break;
            case R.id.btn_get_more_time /* 2131755339 */:
                IntentUtils.toCallBindRegCodeActivity(this, AppContext.getInstance().regCode);
                break;
            case R.id.button_setting_scriptlog /* 2131755347 */:
                File[] fileArrListFiles = new File(this.appContext.mPathPackagenameLog).listFiles(new FileUtils.FileSuffixrFilter(APPConstant.FILE_SUFFIX_LOG));
                if (fileArrListFiles == null || fileArrListFiles.length == 0) {
                    this.appContext.showToast(R.string.no_script_log);
                } else {
                    Arrays.sort(fileArrListFiles, new FileComparator());
                    File file = fileArrListFiles[0];
                    ScriptLog scriptLog = new ScriptLog();
                    scriptLog.setName(file.getName());
                    scriptLog.setSize(FileUtils.formetFileSize(file.length()));
                    scriptLog.setFile(file);
                    Intent intent2 = new Intent(this, (Class<?>) ScriptLogDetailActivity.class);
                    intent2.putExtra(APPConstant.INTENT_KEY_FLAG_LASTLOG, true);
                    intent2.putExtra(APPConstant.INTENT_KEY_SCRIPTLOG, scriptLog);
                    startActivity(intent2);
                }
                break;
            case R.id.setting_button_update /* 2131755348 */:
                if (!FastClickUtil.getInstance().isFastClick()) {
                    if (NetworkUtils.isAvailable(this)) {
                        App.onScriptStart();
                    } else {
                        ToastUtils.showToastLong(this, R.string.network_off);
                    }
                    break;
                }
                break;
            case R.id.button_setting_feedback /* 2131755349 */:
                Intent intent3 = new Intent(this, (Class<?>) FeedbackActivity.class);
                intent3.putExtra(FeedbackActivity.class.getCanonicalName(), ParamsWrap.getParamsWrap().feedbackTips);
                startActivityForResult(intent3, 1);
                break;
        }
    }

    @Override // com.cyjh.share.mvp.view.PushMessageView
    public void onSuccessfulAcquireNews(List<NotifyMsgBean> list) {
        this.msgPartDataList.clear();
        this.msgPartDataList.addAll(list);
        if (this.msgIdList.contains(this.msgPartDataList.get(0).f1679ID)) {
            return;
        }
        this.msgDataList.addAll(0, this.msgPartDataList);
        this.mHandler.obtainMessage(1002).sendToTarget();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            if (i2 == -1) {
                this.feedBackCommitDialog = new FeedBackCommitDialog(this);
                this.feedBackCommitDialog.show();
                this.mSubmitTimeCount = new SubmitTimeCount(3000L, 1000L);
                this.mSubmitTimeCount.start();
                return;
            }
            return;
        }
        if (i != 10000) {
            return;
        }
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + getPackageName() + ".apk");
        if (file.exists()) {
            Intent intent2 = new Intent("android.intent.action.VIEW");
            intent2.setFlags(1);
            intent2.setDataAndType(FileProvider.getUriForFile(this, getString(R.string.pay_appid), file), "application/vnd.android.package-archive");
            startActivity(intent2);
        }
    }

    private void initSwitchStatus() {
        this.swtCallStop.setChecked(this.appContext.sharedPreferences.getBoolean(APPConstant.SP_KEY_SWITCH_SETTING_CALLSTOP, true));
        this.swtVolumeKeyCtrl.setChecked(this.appContext.sharedPreferences.getBoolean(APPConstant.SP_KEY_SWITCH_SETTING_VOLUMEKEYCTRL, true));
        this.swtWaggleStop.setChecked(this.appContext.sharedPreferences.getBoolean(APPConstant.SP_KEY_SWITCH_SETTING_WAGGLESTOP, true));
        this.swtFloatviewCtrl.setChecked(this.appContext.sharedPreferences.getBoolean(APPConstant.SP_KEY_SWITCH_SETTING_FLOATVIEWCTRL, true));
        this.swtVibration.setChecked(this.appContext.sharedPreferences.getBoolean(APPConstant.SP_KEY_SWITCH_SETTING_VIBRATION, true));
        this.appContext.sharedPreferences.getBoolean(APPConstant.SP_KEY_SWITCH_SETTING_OPEN_RUN_SCRIPT, true);
        this.swtOpenRunScript.setChecked(true);
        SlLog.m1334i(TAG, "initSwitchStatus --> openRunScriptFlag=true");
        if (1 == 0 || !Constants.REBOOT.equals(AppContext.getInstance().mLunchType)) {
            return;
        }
        autoStartEngine();
    }

    private void autoStartEngine() {
        SlLog.m1334i(TAG, "autoStartEngine --> mScriptUipSetFragment=" + this.mScriptUipSetFragment);
        AppContext.getInstance().mLunchType = null;
        if (this.mScriptUipSetFragment != null) {
            this.mHandler.postDelayed(new Runnable(this) { // from class: com.ds.daisi.activity.ElfinPayActivity$$Lambda$0
                private final ElfinPayActivity arg$1;

                {
                    this.arg$1 = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    this.arg$1.lambda$autoStartEngine$0$ElfinPayActivity();
                }
            }, UMAmapConfig.AMAP_CACHE_WRITE_TIME);
            this.mHandler.postDelayed(ElfinPayActivity$$Lambda$1.$instance, 13000L);
        }
    }

    final /* synthetic */ void lambda$autoStartEngine$0$ElfinPayActivity() {
        this.mScriptUipSetFragment.btnRunScriptPerformClick();
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        SharedPreferences.Editor editorEdit = this.appContext.sharedPreferences.edit();
        switch (compoundButton.getId()) {
            case R.id.swtich_setting_volume_key_ctrl /* 2131755340 */:
                if (!z && !this.swtFloatviewCtrl.isChecked()) {
                    this.appContext.showToast(R.string.setting_illegal);
                    this.swtVolumeKeyCtrl.setChecked(true);
                } else {
                    editorEdit.putBoolean(APPConstant.SP_KEY_SWITCH_SETTING_VOLUMEKEYCTRL, z);
                }
                break;
            case R.id.swtich_setting_floatview_ctrl /* 2131755341 */:
                if (!z && !this.swtVolumeKeyCtrl.isChecked()) {
                    this.appContext.showToast(R.string.setting_illegal);
                    this.swtFloatviewCtrl.setChecked(true);
                } else {
                    editorEdit.putBoolean(APPConstant.SP_KEY_SWITCH_SETTING_FLOATVIEWCTRL, z);
                }
                break;
            case R.id.swtich_setting_waggle_stop /* 2131755342 */:
                editorEdit.putBoolean(APPConstant.SP_KEY_SWITCH_SETTING_WAGGLESTOP, z);
                break;
            case R.id.swtich_setting_call_stop /* 2131755343 */:
                editorEdit.putBoolean(APPConstant.SP_KEY_SWITCH_SETTING_CALLSTOP, z);
                break;
            case R.id.swtich_setting_vibration /* 2131755344 */:
                editorEdit.putBoolean(APPConstant.SP_KEY_SWITCH_SETTING_VIBRATION, z);
                break;
            case R.id.swtich_setting_open_run /* 2131755345 */:
                editorEdit.putBoolean(APPConstant.SP_KEY_SWITCH_SETTING_OPEN_RUN_SCRIPT, z);
                CommonUtil.operateElfinPackageNames(this, z);
                break;
            case R.id.switch_setting_word_recognition_library /* 2131755346 */:
                if (z) {
                    recognitionLibraryDownloadOperate();
                }
                break;
        }
        editorEdit.apply();
    }

    @Override // com.p024ds.daisi.mvp.views.BindRegCodeView
    public void onBindSuc(BindRegCodeResponse bindRegCodeResponse) {
        SlLog.m1334i(TAG, "onBindSuc --> bindRegCodeResponse.Code=" + bindRegCodeResponse.Code);
        if (bindRegCodeResponse.Code == 200) {
            BindRegCodeResponse.RegCodeTimeInfo regCodeTimeInfo = bindRegCodeResponse.Data;
            long j = regCodeTimeInfo.ExpireTime;
            AppContext.getInstance().ExpireTime = j;
            long j2 = regCodeTimeInfo.ServerTimestamp;
            this.bindRegCodeBackTime = (j - j2) / 60;
            long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
            SlLog.m1334i(TAG, "onBindSuc --> serverTimestamp=" + j2 + ",bindRegCodeBackTime=" + this.bindRegCodeBackTime + ",currentTimestamp=" + jCurrentTimeMillis + ",expireTime=" + j);
            if (this.bindRegCodeBackTime > 0) {
                MqRunner.getInstance().setRegCode(AppContext.getInstance().regCode);
                this.appContext.STATE_APP = 0;
                displayRemainTime(this.bindRegCodeBackTime);
            }
            if (this.mOnDisplayExpireTimeBackListener != null) {
                this.mOnDisplayExpireTimeBackListener.onDisplayExpireTimeBack();
            }
        } else if (bindRegCodeResponse.Code == 1401) {
            AppContext.getInstance().regCode = "";
            MqRunner.getInstance().setRegCode(AppContext.getInstance().regCode);
            AppUtils.deleteFileContent(InterfaceRelatedConstants.REG_CODE_STATUS_FILE_SAVE + MyConfig.getAppid(), AppContext.getInstance());
        }
        this.mHandler.obtainMessage(1008).sendToTarget();
    }

    @Override // com.p024ds.daisi.mvp.views.BindRegCodeView
    public void onBindFailure(BaseResponseInfo baseResponseInfo) {
        SlLog.m1334i(TAG, "onBindFailure --> info.Code=" + baseResponseInfo.Code);
        AppContext.getInstance().ExpireTime = 0L;
        if (baseResponseInfo.Code == -1 && !this.isRegCodeResponseNull) {
            this.isRegCodeResponseNull = true;
            this.mHandler.obtainMessage(1006).sendToTarget();
            return;
        }
        this.mHandler.obtainMessage(1008).sendToTarget();
        this.bindRegCodeBackTime = -1L;
        AppContext.getInstance().regCode = "";
        judgeTimeOperate();
        if (this.mOnDisplayExpireTimeBackListener != null) {
            this.mOnDisplayExpireTimeBackListener.onDisplayExpireTimeBack();
        }
        MqRunner.getInstance().setRegCode(AppContext.getInstance().regCode);
    }

    private void displayTryTime() {
        if (ParamsWrap.getParamsWrap().onceTryMinute <= 0) {
            this.mTvExpireTime.setText(getString(R.string.optionfragment_duetime_no));
            this.appContext.STATE_APP = 3;
            return;
        }
        this.appContext.STATE_APP = 1;
        String str = String.format(getString(R.string.optionfragment_unpaid), Integer.valueOf(ParamsWrap.getParamsWrap().onceTryMinute));
        this.mTvExpireTime.setText(getTextPartColor(str, String.valueOf(ParamsWrap.getParamsWrap().onceTryMinute) + "分钟", "", ""));
    }

    class SubmitTimeCount extends CountDownTimer {
        @Override // android.os.CountDownTimer
        public void onTick(long j) {
        }

        private SubmitTimeCount(long j, long j2) {
            super(j, j2);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            if (ElfinPayActivity.this.feedBackCommitDialog.isShowing()) {
                ElfinPayActivity.this.feedBackCommitDialog.dismiss();
            }
        }
    }

    private void initNotify() {
        this.mBuilder = new NotificationCompat.Builder(this);
        this.mNotifyManager = (NotificationManager) getSystemService("notification");
        Intent intent = new Intent(this, (Class<?>) MessagePushActivity.class);
        intent.putExtra(Constants.MSG_DATA, 1001);
        intent.putParcelableArrayListExtra(Constants.MSG_DATA, this.msgDataList);
        this.mBuilder.setSmallIcon(R.drawable.ic_launcher).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher)).setOngoing(false).setAutoCancel(true).setPriority(0).setDefaults(2).setContentIntent(PendingIntent.getActivity(this, 0, intent, 134217728));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processInitDbData(ArrayList<NotifyMsgBean> arrayList) {
        this.msgDataList.clear();
        this.msgIdList.clear();
        this.msgDataList.addAll(arrayList);
        Iterator<NotifyMsgBean> it2 = this.msgDataList.iterator();
        while (it2.hasNext()) {
            this.msgIdList.add(it2.next().f1679ID);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processPushMessage() {
        this.mImgRedDot.setVisibility(0);
        NotifyMsgBean notifyMsgBean = this.msgPartDataList.get(0);
        if (notifyMsgBean.IsSendMessage) {
            initNotify();
            this.mBuilder.setContentTitle(notifyMsgBean.NoticeTitle).setTicker(notifyMsgBean.NoticeTitle).setContentText(notifyMsgBean.NoticeTitle);
            this.mNotifyManager.notify(1001, this.mBuilder.build());
        }
        this.mSharedPres.edit().putBoolean(Constants.RED_DOT_FLAG, true).apply();
        this.databaseOpera.insertData(this.msgPartDataList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateVersionRequestJudgeOperate() {
    }

    /* JADX INFO: renamed from: com.ds.daisi.activity.ElfinPayActivity$5 */
    class C09395 implements VersionUpdateManager.OnUpdateVersionBack {
        C09395() {
        }

        @Override // com.cyjh.share.mvp.manager.VersionUpdateManager.OnUpdateVersionBack
        public void onUpdateHas(VersionUpdateInfo versionUpdateInfo) {
            if (versionUpdateInfo == null) {
                return;
            }
            if (versionUpdateInfo.UpgradeMode == 1) {
                if (versionUpdateInfo.UpdateType == 1) {
                    AppContext.getInstance().isForceUpdate = true;
                }
                new UpdateDialog(ElfinPayActivity.this, versionUpdateInfo, false).show();
            } else if (versionUpdateInfo.UpgradeMode == 2) {
                SpUtil.getInstance().setAppVersion(versionUpdateInfo.AppVersion);
                AppContext.getInstance().initElf();
                int i = ScriptDownloadHelper.HOT_SCRIPT_DOWNLOAD_NEXT_TIME;
                if (3 == versionUpdateInfo.UpdateType) {
                    if (ElfinPayActivity.this.mIsInvokingFromEngineHeart) {
                        ElfinPayActivity.this.mIsInvokingFromEngineHeart = false;
                        i = ScriptDownloadHelper.HOT_SCRIPT_DOWNLOAD_IMMEDIATELY_AND_ENGINE_RUNNING;
                    } else {
                        i = ScriptDownloadHelper.HOT_SCRIPT_DOWNLOAD_IMMEDIATELY;
                    }
                }
                ScriptDownloadHelper.get().init(ElfinPayActivity.this, ElfinPayActivity.this.appContext.mScript.getId(), i).downloadScriptOperate(versionUpdateInfo.UpdateUrl);
            }
        }
    }

    private void recognitionLibraryDownloadOperate() {
        final LibraryUILoadingDialog libraryUILoadingDialog = new LibraryUILoadingDialog(this);
        libraryUILoadingDialog.show();
        RecognitionLibraryDownloadHelper.get().download(InterfaceRelatedConstants.RECOGNITION_LIBRARY_URL, new RecognitionLibraryDownloadHelper.OnDownloadListener() { // from class: com.ds.daisi.activity.ElfinPayActivity.6
            @Override // com.cyjh.share.helper.RecognitionLibraryDownloadHelper.OnDownloadListener
            public void alreadyExist() {
                libraryUILoadingDialog.dismiss();
                ToastUtils.showToastShort(ElfinPayActivity.this, "文字识别库已下载！");
            }

            @Override // com.cyjh.share.helper.RecognitionLibraryDownloadHelper.OnDownloadListener
            public void onDownloadSuccess() {
                libraryUILoadingDialog.dismiss();
                ToastUtils.showToastShort(ElfinPayActivity.this, "文字识别库下载成功！");
                ElfinPayActivity.this.swtWordRecognitionLibrary.setClickable(false);
            }

            @Override // com.cyjh.share.helper.RecognitionLibraryDownloadHelper.OnDownloadListener
            public void onDownloadFailed(String str) {
                libraryUILoadingDialog.dismiss();
                ToastUtils.showToastShort(ElfinPayActivity.this, "文字识别库下载失败！");
                ElfinPayActivity.this.swtWordRecognitionLibrary.setClickable(true);
                ElfinPayActivity.this.swtWordRecognitionLibrary.setChecked(false);
            }
        });
    }

    private static class MyHandler extends Handler {
        WeakReference<ElfinPayActivity> mWeakReference;

        private MyHandler(ElfinPayActivity elfinPayActivity) {
            this.mWeakReference = new WeakReference<>(elfinPayActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            ElfinPayActivity elfinPayActivity = this.mWeakReference.get();
            if (elfinPayActivity == null) {
            }
            int i = message.what;
            if (i == 1002) {
                elfinPayActivity.processPushMessage();
                return;
            }
            if (i == 1012) {
                elfinPayActivity.updateVersionRequestJudgeOperate();
                return;
            }
            switch (i) {
                case 1005:
                    ArrayList arrayList = (ArrayList) message.obj;
                    if (arrayList != null) {
                        elfinPayActivity.processInitDbData(arrayList);
                    }
                    break;
                case 1006:
                    elfinPayActivity.loadMoreTime();
                    break;
                case 1007:
                    AppStatisticsPresenter.getStatisticsPresenter().loadAd(elfinPayActivity, 5, 0, 0L);
                    break;
                case 1008:
                    elfinPayActivity.startCircleRunnable();
                    break;
                case 1009:
                    elfinPayActivity.mHandler.obtainMessage(1007).sendToTarget();
                    long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
                    elfinPayActivity.statisDailyLife((AppUtils.timeToStamp(AppUtils.timeStamp2Date(jCurrentTimeMillis, AppUtils.TIME_FORMAT_DAY)) / 1000) - jCurrentTimeMillis);
                    break;
                case 1010:
                    elfinPayActivity.statisDailyLife(((Long) message.obj).longValue());
                    break;
            }
        }
    }

    public SpannableString getTextPartColor(String str, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        int iIndexOf = str.indexOf(str2);
        int length = (str2.length() + iIndexOf) - 2;
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new StyleSpan(1), iIndexOf, length, 17);
        spannableString.setSpan(new BackgroundColorSpan(Color.parseColor("#2964AA")), iIndexOf, length, 17);
        if (TextUtils.isEmpty(str3)) {
            return spannableString;
        }
        int iIndexOf2 = str.indexOf(str3);
        int length2 = (str3.length() + iIndexOf2) - 2;
        spannableString.setSpan(new StyleSpan(1), iIndexOf2, length2, 17);
        spannableString.setSpan(new BackgroundColorSpan(Color.parseColor("#2964AA")), iIndexOf2, length2, 17);
        if (TextUtils.isEmpty(str4)) {
            return spannableString;
        }
        int iIndexOf3 = str.indexOf(str4);
        int length3 = (str4.length() + iIndexOf3) - 1;
        spannableString.setSpan(new StyleSpan(1), iIndexOf3, length3, 17);
        spannableString.setSpan(new BackgroundColorSpan(Color.parseColor("#2964AA")), iIndexOf3, length3, 17);
        return spannableString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadMoreTime() {
        if (this.mBindRegisterCodePresenter == null) {
            this.mBindRegisterCodePresenter = new BindRegisterCodePresenter(this);
        }
        this.mBindRegisterCodePresenter.bindRegCode(AppContext.getInstance().regCode, this, 0, "");
    }

    public void setOnDisplayExpireTimeBack(OnDisplayExpireTimeBackListener onDisplayExpireTimeBackListener) {
        this.mOnDisplayExpireTimeBackListener = onDisplayExpireTimeBackListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bootRunScript() {
        moveTaskToBack(true);
        startService(new Intent(AppContext.getInstance(), (Class<?>) BootService.class));
    }

    private class CircleRunnable implements Runnable {
        private CircleRunnable() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onStart() {
            ElfinPayActivity.this.mHandler.removeCallbacks(this);
            ElfinPayActivity.this.mHandler.postDelayed(this, 50L);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!ElfinPayActivity.this.isDisplayUI) {
                ElfinPayActivity.this.mHandler.removeCallbacks(this);
                ElfinPayActivity.this.mHandler.postDelayed(this, 50L);
            } else {
                ElfinPayActivity.this.isDisplayUI = false;
                ElfinPayActivity.this.mHandler.removeCallbacks(this);
                ElfinPayActivity.this.bootRunScript();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startCircleRunnable() {
        if (AppContext.sIsBooted) {
            AppContext.sIsBooted = false;
            AppStatisticsPresenter.getStatisticsPresenter().setCallback(new AppStatisticsPresenter.OnCallback() { // from class: com.ds.daisi.activity.ElfinPayActivity.7
                @Override // com.cyjh.share.mvp.presenter.AppStatisticsPresenter.OnCallback
                public void onCallbackSuc(Object obj) {
                    String str = (String) obj;
                    if (!TextUtils.isEmpty(str)) {
                        ScriptStartRunResponse scriptStartRunResponse = (ScriptStartRunResponse) GsonExUtil.parsData(str, ScriptStartRunResponse.class);
                        if (scriptStartRunResponse != null && scriptStartRunResponse.Code == 200) {
                            new CircleRunnable().onStart();
                        } else if (scriptStartRunResponse != null) {
                            com.cyjh.share.util.ToastUtils.showToastLong(AppContext.getInstance(), scriptStartRunResponse.Message);
                        } else {
                            com.cyjh.share.util.ToastUtils.showToastLong(AppContext.getInstance(), R.string.json_parse_exception);
                        }
                    } else {
                        com.cyjh.share.util.ToastUtils.showToastLong(AppContext.getInstance(), R.string.json_data_null);
                    }
                    AppStatisticsPresenter.getStatisticsPresenter().cleanCallback();
                }
            }).loadScriptStartRunStatistics(AppContext.getInstance(), AppContext.getInstance().regCode, InterfaceRelatedConstants.SCRIPT_RUN_BEFORE_CHECK_NAME);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Iterator<MyTouchListener> it2 = this.myTouchListeners.iterator();
        while (it2.hasNext()) {
            it2.next().onTouchEvent(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void registerMyTouchListener(MyTouchListener myTouchListener) {
        this.myTouchListeners.add(myTouchListener);
    }

    public void unRegisterMyTouchListener(MyTouchListener myTouchListener) {
        this.myTouchListeners.remove(myTouchListener);
    }
}
