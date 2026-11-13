package com.p024ds.daisi;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.cyjh.feedback.lib.executor.ThreadPoolProxyFactory;
import com.cyjh.mobileanjian.ipc.interfaces.OnEngineStartCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnKeyEventListener;
import com.cyjh.mobileanjian.ipc.interfaces.OnScriptListener;
import com.cyjh.mobileanjian.ipc.interfaces.OnSpecialMqCmdCallback;
import com.cyjh.mobileanjian.ipc.uip.UipHelper;
import com.cyjh.mobileanjian.ipc.view.ExToast;
import com.cyjh.p019mq.sdk.MqBridge;
import com.cyjh.p019mq.sdk.MqRunner;
import com.cyjh.p019mq.sdk.entity.Script4Run;
import com.cyjh.p019mq.sdk.inf.OnElfCallback;
import com.cyjh.share.bean.request.BaseRequestValueInfo;
import com.cyjh.share.bean.request.DeviceInfo;
import com.cyjh.share.bean.request.EngineParams;
import com.cyjh.share.bean.response.ScriptStartRunResponse;
import com.cyjh.share.config.MyConfig;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.cyjh.share.helper.DdyEnvironmentHelper;
import com.cyjh.share.helper.DdyStatisticsHelper;
import com.cyjh.share.helper.RecognitionLibraryDownloadHelper;
import com.cyjh.share.manager.VariableAndConstantsManager;
import com.cyjh.share.mvp.presenter.AppStatisticsPresenter;
import com.cyjh.share.mvp.presenter.DdyStatisticsRequestPresenter;
import com.cyjh.share.net.volley.VollerHttpManager;
import com.cyjh.share.util.AppUtils;
import com.cyjh.share.util.GsonExUtil;
import com.cyjh.share.util.MyToastUtils;
import com.cyjh.share.util.ShellUtils;
import com.cyjh.share.util.SlLog;
import com.cyjh.share.util.SpUtil;
import com.cyjh.share.util.ToastUtils;
import com.cyjh.share.util.WebInjectFileHelper;
import com.goldcoast.sdk.domain.EntryPoint;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.iflytek.voiceads.dex.DexLoader;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.p024ds.daisi.activity.ElfinPayActivity;
import com.p024ds.daisi.constant.APPConstant;
import com.p024ds.daisi.constant.Constants;
import com.p024ds.daisi.dialog.TipsDialog;
import com.p024ds.daisi.entity.MsgItem;
import com.p024ds.daisi.entity.ParamsWrap;
import com.p024ds.daisi.entity.Script;
import com.p024ds.daisi.floatview.DeleteFloatView;
import com.p024ds.daisi.floatview.ElfinFloatView;
import com.p024ds.daisi.log.AppCrashHandler;
import com.p024ds.daisi.log.CommonLog;
import com.p024ds.daisi.net.URL.MyBuildConfig;
import com.p024ds.daisi.pay.PayConstants;
import com.p024ds.daisi.services.PhoneStateService;
import com.p024ds.daisi.util.FileUtils;
import com.p024ds.daisi.util.IntentUtils;
import com.p024ds.daisi.util.JsonUtils;
import com.p024ds.daisi.util.NetworkUtils;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.proguard.C1799v;
import com.warnship.autoplay.R;
import java.io.File;
import java.io.FileInputStream;
import killer.App;
import net.lingala.zip4j.util.InternalZipConstants;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;
import org.litepal.LitePal;

/* JADX INFO: loaded from: classes.dex */
public class AppContext extends Application implements APPConstant, PayConstants, OnKeyEventListener, OnScriptListener {
    private static final int FLAG_RESET_TIME = 3000;
    private static final String LOG_TAG = "ELFIN";
    private static final String TAG = "AppContext";
    private static AppContext appContext;
    private static RequestQueue mRequestQueue;
    public static boolean sIsBooted;
    public long ExpireTime;
    public Display display;
    public int dueTime;
    public boolean isUseSdcardScript;
    private String mAppFilePath;
    public ElfinFloatView mElfinFloatView;
    public String mLunchType;
    public String mPathPackagenameLog;
    public ScreenOrientationConversion mScreenConversion;
    public Script mScript;
    public boolean mScriptType;
    public SharedPreferences mSharedUsed;
    public UipHelper mUipHelper;
    private MqRunner mqRunner;
    public int payCheckType;
    public int runTime;
    public Script4Run script4Run;
    public SharedPreferences sharedPreferences;
    public long tokenKey;
    private WindowManager windowManager;
    public CommonLog commonLog = new CommonLog();
    public int STATE_APP = 3;
    public int STATE_SCRIPT = 0;
    public boolean checkedScreenOrientation = false;
    public boolean isDisableVolumeKey = false;
    private boolean isStatisStopScript = true;
    public boolean isInitEngineComplete = false;
    public String oldRegCode = "";
    public String regCode = "";
    public boolean isForceUpdate = false;
    public boolean isEntryMainActivity = false;
    public boolean isEmulator = false;
    public boolean isScriptStopRunning = true;
    private Handler mHandler = new Handler() { // from class: com.ds.daisi.AppContext.1
        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            if (message.what == 1000) {
                DdyStatisticsRequestPresenter.getInstance().report(AppContext.getInstance());
            }
        }
    };
    private OnSpecialMqCmdCallback onSpecialMqCmdCallback = new OnSpecialMqCmdCallback() { // from class: com.ds.daisi.AppContext.3
        @Override // com.cyjh.mobileanjian.ipc.interfaces.OnSpecialMqCmdCallback
        public void doSpecialFuction(int i, String str) {
        }

        @Override // com.cyjh.mobileanjian.ipc.interfaces.OnSpecialMqCmdCallback
        public String getForegroundPackage() {
            return null;
        }

        @Override // com.cyjh.mobileanjian.ipc.interfaces.OnSpecialMqCmdCallback
        public String getRunningPackages() {
            return null;
        }

        @Override // com.cyjh.mobileanjian.ipc.interfaces.OnSpecialMqCmdCallback
        public void inputText(String str) {
        }

        @Override // com.cyjh.mobileanjian.ipc.interfaces.OnSpecialMqCmdCallback
        public void keyPress(int i) {
        }

        @Override // com.cyjh.mobileanjian.ipc.interfaces.OnSpecialMqCmdCallback
        public void killApp(String str) {
        }

        @Override // com.cyjh.mobileanjian.ipc.interfaces.OnSpecialMqCmdCallback
        public void launchApp(String str) {
        }
    };
    boolean flag = true;
    private boolean isPreventRepeatedVolume = false;

    public interface ScreenOrientationConversion {
        void onLandscapeOrientation();

        void onPortraitOrientation();
    }

    private void checkPackageName() {
        if (!getPackageName().equals("com.warnship.autoplay")) {
            Process.killProcess(Process.myPid());
            System.exit(1);
        } else {
            ShellUtils.execCommand("settings put global window_animation_scale 0", true, 0);
            ShellUtils.execCommand("settings put global animator_duration_scale 0", true, 0);
            ShellUtils.execCommand("settings put global transition_animation_scale 0", true, 0);
        }
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        checkPackageName();
        appContext = this;
        if (MyBuildConfig.isOutputLog()) {
            Logger.init(LOG_TAG).setLogLevel(LogLevel.FULL);
        } else {
            Logger.init(LOG_TAG).setLogLevel(LogLevel.NONE);
        }
        MyConfig.init(MyBuildConfig.isOutputLog(), MyBuildConfig.AUTHORITY_PAY, Long.parseLong(getResources().getString(R.string.elfin_appid)), 499L, getResources().getString(R.string.pay_vsersion), 2, "5.6.0", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCwCG1cyW1mTtYpbeGML+5MPYlTWGPvAxplALWaI5vMccPeGYFrlDDaDmtJhVRzb4zSqH0i7mdUmhYk/1K1xPwxT4wnR6wMwWIvuYK4Gm2MpBJ7d0QVhPVwNKqIm9MiFdLIjM4cdHSrTJxdkUnWRA4uedioRvHAqx6j927rR00WwwIDAQAB", "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALKdmwW76LgoR3oe+xJ/A8Mu0j4FgEoEeJ8mZZzfN4AZbYBhcvmhnIvw3aMvoqRXCI5iSjVSb1wK8UKrRk+yTjVcAt82Dop8UK3wqkHPa/v07yWnrO4J4NSlL5rVy/g5f3vEXVyrsUjmgkDo9kr4iHpL93TGfYz/hj71IEhAmcODAgMBAAECgYAD7dxLe6m6kLBEgHVMyoco/lkDokKt3ialIZwZ9P3B5KKjBgHDJLSj43A3YcSLJvBPlDFGYQIwHSbXgkWxWnPGm/kCHCtOzrwohUaVBizszLPOtTwTr7xWj/FH1KUMGAlw4mYU844I0Ezc+mvp9nNXcJHF9zD56NmIiy2DgSr7CQJBAM9v84DQYgqKd5SYdViDzANQQQvOUScC7oL6INxqhyfTFsVP9Ky2y7YVFQm8YRpfhY4QakpGUC97EgAU7DUtgLcCQQDcblN/Pu59NEMCMyEIK4z/qNLI87LhFjIVsDgbrHuXDbfVJryk5IXj1PFIIaLkf6F56efOQ7V5bZljArcEpe+VAkBpW0zr0erZu7Y8jKqdCGOoEyzWWDWzgLOvZmcmTBzVR0s7b/6vcjBJDLAgv8L56CYnH4M8sjWrx/A+hmHqKHBFAkBFkCOVgnys0nNbHFuOQoAqplJuzEllMyX3JjdutE4Ea3XaNHFP8DY4ohjK1KKdTZHd8W0P8NoySd4gGtk7BErJAkBIeQdZ20U2KbltmStDDA224qM3TnPlJc/+4h3BwtbvEMs4eqMIcDj/u4XLOnmVWLpwJR6fmMDILLrN3WnorZ/J", getString(R.string.elfin_appinfo));
        VariableAndConstantsManager.getInstance().setContext(this);
        DexLoader.initIFLYADModule(this);
        System.loadLibrary("mqm");
        AppCrashHandler.getInstance().init(this);
        this.sharedPreferences = getSharedPreferences(APPConstant.SP_NAME_APPLICATION, 0);
        this.mSharedUsed = getSharedPreferences(Constants.USER_MESSAGE, 0);
        initPath();
        initMeasure();
        initMQScript();
        initShake();
        initlService();
        initScript4Run();
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        VollerHttpManager.getInstance().init(mRequestQueue);
        LitePal.initialize(this);
        SpUtil.getInstance().init(this.sharedPreferences);
        WebInjectFileHelper.INSTANCE.copyWebInjectFile(this);
        RecognitionLibraryDownloadHelper.get().init(this);
    }

    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(context);
    }

    public static AppContext getInstance() {
        return appContext;
    }

    private void initUmeng() {
        UMConfigure.init(this, null, "Umeng", 1, null);
        MobclickAgent.openActivityDurationTrack(false);
    }

    public static RequestQueue getmRequestQueue() {
        return mRequestQueue;
    }

    private void initPath() {
        this.mAppFilePath = getFilesDir().getAbsolutePath();
        this.mPathPackagenameLog = FileUtils.makePath(FileUtils.makePath(Environment.getExternalStorageDirectory().getAbsolutePath(), getPackageName()), "log");
        createDirs();
    }

    private void createDirs() {
        File file = new File(this.mPathPackagenameLog);
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }

    private void initScript4Run() {
        this.script4Run = new Script4Run(this.mScript.getLcFile().getAbsolutePath(), this.mScript.getAtcFile().getAbsolutePath(), this.mScript.getCfgFile().getAbsolutePath());
    }

    public void initMeasure() {
        this.windowManager = (WindowManager) getSystemService("window");
        this.windowManager.getDefaultDisplay().getMetrics(new DisplayMetrics());
        this.display = this.windowManager.getDefaultDisplay();
    }

    private void initlService() {
        startService(new Intent(this, (Class<?>) PhoneStateService.class));
    }

    private void initMQScript() {
        String str = getFilesDir() + File.separator + Constants.SD_CARD_SCRIPT_ASSETS;
        File file = new File(str, APPConstant.SCRIPT_FILENAME_LC);
        File file2 = new File(str, APPConstant.SCRIPT_FILENAME_PROP);
        File file3 = new File(str, APPConstant.SCRIPT_FILENAME_ATC);
        File file4 = new File(str, APPConstant.SCRIPT_FILENAME_UI);
        File file5 = new File(str, APPConstant.SCRIPT_FILENAME_UIP);
        File file6 = new File(str, APPConstant.SCRIPT_FILENAME_RTD);
        File file7 = new File(FileUtils.makePath(this.mAppFilePath, APPConstant.SCRIPT_FILENAME_LC));
        File file8 = new File(FileUtils.makePath(this.mAppFilePath, APPConstant.SCRIPT_FILENAME_PROP));
        File file9 = new File(FileUtils.makePath(this.mAppFilePath, APPConstant.SCRIPT_FILENAME_ATC));
        File file10 = new File(FileUtils.makePath(this.mAppFilePath, APPConstant.SCRIPT_FILENAME_UI));
        File file11 = new File(FileUtils.makePath(this.mAppFilePath, APPConstant.SCRIPT_FILENAME_UIP));
        File file12 = new File(FileUtils.makePath(this.mAppFilePath, APPConstant.SCRIPT_FILENAME_RTD));
        File file13 = new File(FileUtils.makePath(this.mAppFilePath, APPConstant.SCRIPT_FILENAME_CFG));
        this.isUseSdcardScript = file.exists() || file2.exists() || file3.exists() || file4.exists() || file5.exists() || file6.exists();
        SlLog.m1334i(TAG, "initMQScript --> 1 isUseSdcardScript=" + this.isUseSdcardScript);
        try {
            this.mScript = JsonUtils.parPropJson(file2.exists() ? new FileInputStream(file2) : getAssets().open(APPConstant.SCRIPT_FILENAME_PROP));
            this.mScript.setLcFile(this.isUseSdcardScript ? file : file7);
            Script script = this.mScript;
            if (!this.isUseSdcardScript) {
                file2 = file8;
            }
            script.setPropFile(file2);
            Script script2 = this.mScript;
            if (!this.isUseSdcardScript) {
                file3 = file9;
            }
            script2.setAtcFile(file3);
            Script script3 = this.mScript;
            if (!this.isUseSdcardScript) {
                file4 = file10;
            }
            script3.setUiFile(file4);
            Script script4 = this.mScript;
            if (!this.isUseSdcardScript) {
                file5 = file11;
            }
            script4.setUipFile(file5);
            Script script5 = this.mScript;
            if (this.isUseSdcardScript) {
                file12 = file6;
            }
            script5.setRtdFile(file12);
            this.mScript.setCfgFile(file13);
            SlLog.m1334i(TAG, "initMQScript --> 3 cfgFile absolutePath = " + this.mScript.getCfgFile().getAbsolutePath() + ",isUseSdcardScript=" + this.isUseSdcardScript);
            SlLog.m1334i(TAG, "initMQScript --> 4 AppId=" + this.mScript.getAppId() + ",Version=" + this.mScript.getVersion());
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            SlLog.m1334i(TAG, "initMQScript --> ex= " + e.getMessage());
        }
    }

    public int getInteger(int i) {
        return getResources().getInteger(i);
    }

    private void initShake() {
        final Vibrator vibrator = (Vibrator) getSystemService("vibrator");
        final ShakeListener shakeListener = new ShakeListener(this);
        shakeListener.setOnShakeListener(new ShakeListener.OnShakeListener() { // from class: com.ds.daisi.AppContext.2
            @Override // com.ds.daisi.AppContext.ShakeListener.OnShakeListener
            public void onShake() {
                if (AppContext.this.sharedPreferences.getBoolean(APPConstant.SP_KEY_SWITCH_SETTING_WAGGLESTOP, true)) {
                    AppContext.this.mqRunner.stop();
                    shakeListener.stop();
                    if (!AppContext.this.mqRunner.isScriptStarted() || vibrator == null) {
                        return;
                    }
                    vibrator.vibrate(new long[]{500, 200, 500, 200}, -1);
                    new Handler().postDelayed(new Runnable() { // from class: com.ds.daisi.AppContext.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            vibrator.cancel();
                            shakeListener.start();
                        }
                    }, 1500L);
                }
            }
        });
    }

    private void startVibrator() {
        Vibrator vibrator = (Vibrator) getSystemService("vibrator");
        if (!this.sharedPreferences.getBoolean(APPConstant.SP_KEY_SWITCH_SETTING_VIBRATION, true) || vibrator == null) {
            return;
        }
        vibrator.vibrate(1000L);
    }

    private void createElfinFloatView() {
        DeleteFloatView deleteFloatView = new DeleteFloatView(this);
        this.mElfinFloatView = new ElfinFloatView(this, deleteFloatView);
        this.windowManager.addView(deleteFloatView, deleteFloatView.getLayoutParams());
        this.windowManager.addView(this.mElfinFloatView, this.mElfinFloatView.getLayoutParams());
        if (2 == getResources().getConfiguration().orientation) {
            this.mElfinFloatView.rotateScreen();
        }
    }

    public void showFloatView() {
        if (this.mElfinFloatView == null) {
            createElfinFloatView();
        } else {
            this.mElfinFloatView.rotateScreen();
        }
        if (this.sharedPreferences.getBoolean(APPConstant.SP_KEY_SWITCH_SETTING_FLOATVIEWCTRL, true)) {
            this.mElfinFloatView.setVisibility(0);
        } else {
            this.mElfinFloatView.setVisibility(8);
        }
    }

    public void deleteFloatView() {
        if (this.mElfinFloatView != null) {
            if (this.mElfinFloatView.getVisibility() == 0) {
                this.mElfinFloatView.setVisibility(8);
            }
            this.mElfinFloatView = null;
        }
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.mElfinFloatView != null) {
            this.mElfinFloatView.rotateScreen();
        }
        if (configuration.orientation == 2) {
            this.checkedScreenOrientation = true;
            if (this.mScreenConversion != null) {
                this.mScreenConversion.onLandscapeOrientation();
                return;
            }
            return;
        }
        if (configuration.orientation == 1) {
            this.checkedScreenOrientation = false;
            if (this.mScreenConversion != null) {
                this.mScreenConversion.onPortraitOrientation();
            }
        }
    }

    public void showToast(int i) {
        ExToast.makeText(this, i, ExToast.LENGTH_SHORT).show();
    }

    public void showToast(String str) {
        Toast.makeText(this, str, 0).show();
    }

    public void checkJarApp() {
        runScriptWithAppStatus();
    }

    private void runScriptWithAppStatus() {
        switch (this.STATE_APP) {
            case 0:
                runScript();
                break;
            case 1:
                showToast(R.string.pay_check_trial);
                runScript();
                break;
            case 2:
                runScript();
                break;
            case 3:
                runScript();
                break;
            case 4:
                showToast(R.string.pay_check_decode_failed);
                break;
            default:
                showToast(R.string.pay_check_no_sync);
                break;
        }
    }

    public void runScript() {
        this.mElfinFloatView.changeToolBarState(false);
        scriptParamsRunSet();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scriptParamsRunSet() {
        this.script4Run.appId = getString(R.string.pay_appid);
        this.script4Run.username = getString(R.string.pay_vsersion);
        this.mqRunner.setScript(this.script4Run).setOnScriptListener(this).start();
    }

    public void updateEngineScript() {
        initMQScript();
        initScript4Run();
        this.mqRunner.setScript(this.script4Run);
        this.mqRunner.setOnSpecialMqCmdCallback(this.onSpecialMqCmdCallback);
        initElf();
    }

    public void backApp() {
        if (!getPackageName().equals(isDesktop())) {
            Intent intent = new Intent(getApplicationContext(), (Class<?>) ElfinPayActivity.class);
            intent.addFlags(268435456);
            intent.setAction("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            startActivity(intent);
        }
        if (this.mElfinFloatView != null) {
            this.mElfinFloatView.setVisibility(8);
        }
    }

    private String isDesktop() {
        ActivityManager activityManager = (ActivityManager) getSystemService("activity");
        if (activityManager != null) {
            return activityManager.getRunningTasks(1).get(0).topActivity.getPackageName();
        }
        return null;
    }

    @Override // com.cyjh.mobileanjian.ipc.interfaces.BasicScriptListener
    public void onStartScript() {
        this.isScriptStopRunning = false;
        this.STATE_SCRIPT = 2;
        startVibrator();
        if (this.mElfinFloatView != null) {
            this.mElfinFloatView.checkBtnState();
            this.mElfinFloatView.upadateElfinImages();
        }
        ddyStaticsJudgeOperate();
        App.onScriptStart();
    }

    /* JADX INFO: renamed from: com.ds.daisi.AppContext$4 */
    class C09254 implements AppStatisticsPresenter.OnClickScriptRunCallback {
        @Override // com.cyjh.share.mvp.presenter.AppStatisticsPresenter.OnClickScriptRunCallback
        public void onCallbackFail() {
        }

        C09254() {
        }

        @Override // com.cyjh.share.mvp.presenter.AppStatisticsPresenter.OnClickScriptRunCallback
        public void onCallbackSuc(Object obj) {
            AppContext.this.isStatisStopScript = true;
            String str = (String) obj;
            if (!TextUtils.isEmpty(str)) {
                ScriptStartRunResponse scriptStartRunResponse = (ScriptStartRunResponse) GsonExUtil.parsData(str, ScriptStartRunResponse.class);
                if (scriptStartRunResponse != null && scriptStartRunResponse.Code == 200) {
                    AppContext.this.isStatisStopScript = false;
                    ParamsWrap.getParamsWrap().mStartRunResponseInfo = scriptStartRunResponse.Data;
                } else {
                    if (scriptStartRunResponse != null) {
                        if (scriptStartRunResponse.Code == 1401 || 1400 == scriptStartRunResponse.Code) {
                            AppContext.this.regCode = "";
                            MqRunner.getInstance().setRegCode(AppContext.this.regCode);
                            AppUtils.deleteFileContent(InterfaceRelatedConstants.REG_CODE_STATUS_FILE_SAVE + MyConfig.getAppid(), AppContext.getInstance());
                        }
                        int i = scriptStartRunResponse.Code;
                        String str2 = scriptStartRunResponse.Message;
                        if (1100 == i || 1200 == i || 1201 == i || 1202 == i || 1203 == i || 1300 == i) {
                            if (TipsDialog.sIsShow && !TipsDialog.sIsFloat) {
                                EventBus.getDefault().post(new MsgItem(1006));
                            }
                            if (!TipsDialog.sIsShow) {
                                final TipsDialog tipsDialog = new TipsDialog(AppContext.getInstance(), true, str2);
                                tipsDialog.setClickListener(new View.OnClickListener() { // from class: com.ds.daisi.AppContext.4.1
                                    @Override // android.view.View.OnClickListener
                                    public void onClick(View view) {
                                        tipsDialog.dismiss();
                                    }
                                });
                                tipsDialog.show();
                            } else {
                                EventBus.getDefault().post(new MsgItem.UpdateMessageToDialog(str2));
                            }
                        } else {
                            ToastUtils.showToastLong(AppContext.this, scriptStartRunResponse.Message);
                            if (i == 1404) {
                                IntentUtils.toCallBindRegCodeActivity(AppContext.getInstance(), AppContext.this.regCode);
                            }
                        }
                    } else {
                        ToastUtils.showToastLong(AppContext.this, R.string.json_parse_exception);
                    }
                    if (MqRunner.getInstance().isScriptStarted()) {
                        MqRunner.getInstance().stop();
                    }
                }
            } else {
                ToastUtils.showToastLong(AppContext.this, R.string.json_data_null);
                if (MqRunner.getInstance().isScriptStarted()) {
                    MqRunner.getInstance().stop();
                }
            }
            AppStatisticsPresenter.getStatisticsPresenter().cleanCallback();
        }
    }

    @Override // com.cyjh.mobileanjian.ipc.interfaces.BasicScriptListener
    public void onPause() {
        if (4 == this.STATE_SCRIPT) {
            MyToastUtils.showToast(getInstance(), getString(R.string.script_run_pausing));
            return;
        }
        this.STATE_SCRIPT = 3;
        if (this.mElfinFloatView == null || !this.flag) {
            return;
        }
        this.mElfinFloatView.checkBtnState();
        this.mElfinFloatView.upadateElfinImages();
        this.flag = false;
    }

    @Override // com.cyjh.mobileanjian.ipc.interfaces.BasicScriptListener
    public void onResume() {
        this.STATE_SCRIPT = 2;
        if (this.mElfinFloatView != null) {
            this.mElfinFloatView.checkBtnState();
            this.mElfinFloatView.upadateElfinImages();
            this.flag = true;
        }
    }

    @Override // com.cyjh.mobileanjian.ipc.interfaces.BasicScriptListener
    public void onScriptIsRunning() {
        this.STATE_SCRIPT = 2;
    }

    @Override // com.cyjh.mobileanjian.ipc.interfaces.BasicScriptListener
    public void onStopScript(int i, String str) {
        App.onScriptStop();
        DdyStatisticsHelper.get().stopTimer();
        this.isScriptStopRunning = true;
        final StringBuilder sb = new StringBuilder();
        if (TextUtils.isEmpty(VariableAndConstantsManager.getInstance().mDeviceInfo.DeviceId) || TextUtils.isEmpty(VariableAndConstantsManager.getInstance().mDeviceInfo.DeviceCode)) {
            DeviceInfo deviceInfoAcquireDevicesId = AppUtils.acquireDevicesId(this);
            VariableAndConstantsManager.getInstance().mDeviceInfo.DeviceId = deviceInfoAcquireDevicesId.DeviceId;
            VariableAndConstantsManager.getInstance().mDeviceInfo.DeviceCode = deviceInfoAcquireDevicesId.DeviceCode;
            sb.append("保存的对象id丢失");
        }
        sb.append(GsonExUtil.class2Data(VariableAndConstantsManager.getInstance().mDeviceInfo));
        sb.append("\\r\\n");
        sb.append("returnCode=");
        sb.append(i);
        sb.append("msg=");
        sb.append(str);
        sb.append("\\r\\n");
        sb.append(AppUtils.timeMillis2Date(System.currentTimeMillis(), null));
        sb.append("\\r\\n");
        ThreadPoolProxyFactory.getNormalThreadPool().executeRunnable(new Runnable() { // from class: com.ds.daisi.AppContext.5
            @Override // java.lang.Runnable
            public void run() {
                AppUtils.writeDateToFile("scriptStopPay.txt", AppContext.getInstance(), sb.toString(), true);
            }
        });
        if (!this.isStatisStopScript && ParamsWrap.getParamsWrap().mStartRunResponseInfo != null) {
            AppStatisticsPresenter.getStatisticsPresenter().loadScriptStopRunStatistics(getInstance(), ParamsWrap.getParamsWrap().mStartRunResponseInfo.RunGuid);
        }
        this.STATE_SCRIPT = 1;
        startVibrator();
        if (this.mElfinFloatView != null) {
            this.mElfinFloatView.checkBtnState();
            this.mElfinFloatView.upadateElfinImages();
        }
        if (i != 0) {
            if (i != 1002) {
                switch (i) {
                    case 104:
                        showToast(getString(R.string.toast_script_checked_failed) + "(" + i + ")");
                        break;
                    case 105:
                        break;
                    case 106:
                        backApp();
                        if (this.STATE_APP == 0) {
                            showToast(R.string.toast_paid_time_finish);
                        } else {
                            showToast(R.string.toast_try_time_finish);
                        }
                        break;
                    default:
                        switch (i) {
                            case 108:
                                showToast(getString(R.string.toast_script_check_app_abnormal) + "(" + i + ")");
                                break;
                            case 109:
                                showToast(getString(R.string.toast_script_web_check_generate_token_err) + "(" + i + ")");
                                break;
                            case 110:
                                showToast(getString(R.string.toast_script_web_check_check_token_kicked_err) + "(" + i + ")");
                                break;
                            case 111:
                                showToast(getString(R.string.toast_script_web_check_check_app_err) + "(" + i + ")");
                                break;
                            case 112:
                                showToast(getString(R.string.toast_script_web_check_check_token_invalid_err) + "(" + i + ")");
                                break;
                        }
                        break;
                }
            }
            Log.e("zzz", "AppContext--onStopScript--MqmCode.MQM_RUNNER_EXCEPTION");
            initElf();
        }
    }

    @Override // com.cyjh.mobileanjian.ipc.interfaces.OnKeyEventListener
    public void onKeyEvent(int i) {
        if (this.sharedPreferences.getBoolean(APPConstant.SP_KEY_SWITCH_SETTING_VOLUMEKEYCTRL, true) && i == 114) {
            if (!NetworkUtils.isAvailable(this)) {
                ToastUtils.showToastShort(getInstance(), R.string.network_off);
                return;
            }
            if (this.isForceUpdate) {
                ToastUtils.showToastShort(getInstance(), R.string.version_too_low);
                return;
            }
            if (!this.isEntryMainActivity) {
                ToastUtils.showToastShort(getInstance(), R.string.volume_run_script_tips);
                return;
            }
            if (this.isPreventRepeatedVolume) {
                ToastUtils.showToastShort(getInstance(), R.string.volume_click_hips);
                return;
            }
            this.isPreventRepeatedVolume = true;
            if (!this.mqRunner.isScriptStarted()) {
                scriptParamsRunSet();
                this.isPreventRepeatedVolume = false;
            } else {
                this.mqRunner.stop();
                this.isPreventRepeatedVolume = false;
            }
            this.mHandler.postDelayed(new Runnable() { // from class: com.ds.daisi.AppContext.7
                @Override // java.lang.Runnable
                public void run() {
                    AppContext.this.isPreventRepeatedVolume = false;
                }
            }, 3000L);
        }
    }

    /* JADX INFO: renamed from: com.ds.daisi.AppContext$6 */
    class C09276 implements AppStatisticsPresenter.OnClickScriptRunCallback {
        C09276() {
        }

        @Override // com.cyjh.share.mvp.presenter.AppStatisticsPresenter.OnClickScriptRunCallback
        public void onCallbackSuc(Object obj) {
            String str = (String) obj;
            if (!TextUtils.isEmpty(str)) {
                ScriptStartRunResponse scriptStartRunResponse = (ScriptStartRunResponse) GsonExUtil.parsData(str, ScriptStartRunResponse.class);
                if (scriptStartRunResponse != null && scriptStartRunResponse.Code == 200) {
                    AppContext.this.scriptParamsRunSet();
                } else if (scriptStartRunResponse != null) {
                    ToastUtils.showToastLong(AppContext.getInstance(), scriptStartRunResponse.Message);
                } else {
                    ToastUtils.showToastLong(AppContext.getInstance(), R.string.json_parse_exception);
                }
            } else {
                ToastUtils.showToastLong(AppContext.getInstance(), R.string.json_data_null);
            }
            AppStatisticsPresenter.getStatisticsPresenter().cleanCallback();
            AppContext.this.isPreventRepeatedVolume = false;
        }

        @Override // com.cyjh.share.mvp.presenter.AppStatisticsPresenter.OnClickScriptRunCallback
        public void onCallbackFail() {
            AppContext.this.isPreventRepeatedVolume = false;
        }
    }

    @Override // com.cyjh.mobileanjian.ipc.interfaces.OnScriptListener
    public void onUpdateControlBarPos(float f, int i, int i2) {
        if (this.mElfinFloatView != null) {
            this.mElfinFloatView.upadateElfinImages();
            this.mElfinFloatView.posControlBar(f, i, i2);
        }
    }

    public static class ShakeListener implements SensorEventListener {
        private static final int SPEED_SHRESHOLD = 3000;
        private static final int UPTATE_INTERVAL_TIME = 100;
        private long lastUpdateTime;
        private float lastX;
        private float lastY;
        private float lastZ;
        private Context mContext;
        private OnShakeListener onShakeListener;
        private Sensor sensor;
        private SensorManager sensorManager;

        public interface OnShakeListener {
            void onShake();
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        private ShakeListener(Context context) {
            this.mContext = context;
            start();
        }

        public void start() {
            this.sensorManager = (SensorManager) this.mContext.getSystemService(C1799v.f4284W);
            if (this.sensorManager != null) {
                this.sensor = this.sensorManager.getDefaultSensor(1);
                if (this.sensor != null) {
                    this.sensorManager.registerListener(this, this.sensor, 1);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void stop() {
            this.sensorManager.unregisterListener(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOnShakeListener(OnShakeListener onShakeListener) {
            this.onShakeListener = onShakeListener;
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            long j = jCurrentTimeMillis - this.lastUpdateTime;
            if (j < 100) {
                return;
            }
            this.lastUpdateTime = jCurrentTimeMillis;
            float f = sensorEvent.values[0];
            float f2 = sensorEvent.values[1];
            float f3 = sensorEvent.values[2];
            float f4 = f - this.lastX;
            float f5 = f2 - this.lastY;
            float f6 = f3 - this.lastZ;
            this.lastX = f;
            this.lastY = f2;
            this.lastZ = f3;
            if ((Math.sqrt(((f4 * f4) + (f5 * f5)) + (f6 * f6)) / j) * 10000.0d >= 3000.0d) {
                this.onShakeListener.onShake();
            }
        }
    }

    public void setScreenConversion(ScreenOrientationConversion screenOrientationConversion) {
        this.mScreenConversion = screenOrientationConversion;
    }

    public void initEngine() {
        initElf();
        MqBridge.init(this, getString(R.string.pay_appid), getString(R.string.pay_vsersion), this, null, new OnEngineStartCallback() { // from class: com.ds.daisi.AppContext.8
            @Override // com.cyjh.mobileanjian.ipc.interfaces.OnEngineStartCallback
            public void onEngineStart(int i) {
                AppContext.this.isInitEngineComplete = true;
            }
        });
        EntryPoint.init(this, "", null, 10, (byte) 0);
        this.mqRunner = MqRunner.getInstance();
    }

    public void onKillProcess() {
        MobclickAgent.onKillProcess(this);
    }

    private void initHostToEngine() {
        String app3DomainName;
        if (MyConfig.getApp3DomainName().endsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
            app3DomainName = MyConfig.getApp3DomainName();
        } else {
            app3DomainName = MyConfig.getApp3DomainName() + InternalZipConstants.ZIP_FILE_SEPARATOR;
        }
        SlLog.m1334i(TAG, "initHostToEngine --> baseUrl=" + app3DomainName);
        MqRunner.getInstance().initHost(app3DomainName);
    }

    public void initElf() {
        String app3DomainName;
        BaseRequestValueInfo baseRequestParams = VariableAndConstantsManager.getInstance().getBaseRequestParams(getInstance());
        boolean z = false;
        String str = new String(Base64.decode("LS0tLS1CRUdJTiBQVUJMSUMgS0VZLS0tLS0KTUlHZk1BMEdDU3FHU0liM0RRRUJBUVVBQTRHTkFEQ0JpUUtCZ1FDd0NHMWN5VzFtVHRZcGJlR01MKzVNUFlsVApXR1B2QXhwbEFMV2FJNXZNY2NQZUdZRnJsRERhRG10SmhWUnpiNHpTcUgwaTdtZFVtaFlrLzFLMXhQd3hUNHduClI2d013V0l2dVlLNEdtMk1wQko3ZDBRVmhQVndOS3FJbTlNaUZkTElqTTRjZEhTclRKeGRrVW5XUkE0dWVkaW8KUnZIQXF4Nmo5MjdyUjAwV3d3SURBUUFCCi0tLS0tRU5EIFBVQkxJQyBLRVktLS0tLQo=", 0));
        String str2 = new String(Base64.decode("LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpNSUlDV3dJQkFBS0JnUUN5blpzRnUraTRLRWQ2SHZzU2Z3UERMdEkrQllCS0JIaWZKbVdjM3plQUdXMkFZWEw1Cm9aeUw4TjJqTDZLa1Z3aU9Za28xVW05Y0N2RkNxMFpQc2s0MVhBTGZOZzZLZkZDdDhLcEJ6MnY3OU84bHA2enUKQ2VEVXBTK2ExY3Y0T1g5N3hGMWNxN0ZJNW9KQTZQWksrSWg2Uy9kMHhuMk0vNFkrOVNCSVFKbkRnd0lEQVFBQgpBb0dBQSszY1MzdXB1cEN3UklCMVRNcUhLUDVaQTZKQ3JkNG1wU0djR2ZUOXdlU2lvd1lCd3lTMG8rTndOMkhFCml5YndUNVF4Um1FQ01CMG0xNEpGc1ZwenhwdjVBaHdyVHM2OEtJVkdsUVlzN015enpyVThFNis4Vm8veFI5U2wKREJnSmNPSm1GUE9PQ05CTTNQcHI2Zlp6VjNDUnhmY3crZWpaaUlzdGc0RXErd2tDUVFEUGIvT0EwR0lLaW5lVQptSFZZZzh3RFVFRUx6bEVuQXU2QytpRGNhb2NuMHhiRlQvU3N0c3UyRlJVSnZHRWFYNFdPRUdwS1JsQXZleElBCkZPdzFMWUMzQWtFQTNHNVRmejd1ZlRSREFqTWhDQ3VNLzZqU3lQT3k0Ull5RmJBNEc2eDdsdzIzMVNhOHBPU0YKNDlUeFNDR2k1SCtoZWVubnprTzFlVzJaWXdLM0JLWHZsUUpBYVZ0TTY5SHEyYnUyUEl5cW5RaGpxQk1zMWxnMQpzNEN6cjJabkprd2MxVWRMTzIvK3IzSXdTUXl3SUwvQytlZ21KeCtEUExJMXE4ZndQb1poNmlod1JRSkFSWkFqCmxZSjhyTkp6V3h4YmprS0FLcVpTYnN4SlpUTWw5eVkzYnJST0JHdDEyalJ4VC9BMk9LSVl5dFNpblUyUjNmRnQKRC9EYU1rbmVJQnJaT3dSS3lRSkFTSGtIV2R0Rk5pbTViWmtyUXd3TnR1S2pOMDV6NVNYUC91SWR3Y0xXN3hETApPSHFqQ0hBNC83dUZ5enA1bFZpNmNDVWVuNWpBeUN5NnpkMXA2SzJmeVE9PQotLS0tLUVORCBSU0EgUFJJVkFURSBLRVktLS0tLQo=", 0));
        EngineParams engineParams = new EngineParams(baseRequestParams);
        engineParams.PackageName = getPackageName();
        if (MyConfig.getApp3DomainName().endsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
            app3DomainName = MyConfig.getApp3DomainName();
        } else {
            app3DomainName = MyConfig.getApp3DomainName() + InternalZipConstants.ZIP_FILE_SEPARATOR;
        }
        engineParams.BaseURL = app3DomainName;
        engineParams.PayAppId = this.mScript.getAppId();
        if ((this.mScript == null || !TextUtils.isEmpty(this.mScript.getAppId())) && (this.mScript == null || !TextUtils.isEmpty(this.mScript.getVersion()))) {
            z = true;
        }
        engineParams.ScriptType = z;
        this.mScriptType = engineParams.ScriptType;
        engineParams.AppVersion = (this.mScript == null || !TextUtils.isEmpty(this.mScript.getVersion())) ? this.mScript.getVersion() : getString(R.string.pay_vsersion);
        MqRunner.getInstance().initElf(GsonExUtil.class2Data(engineParams), str2, str, new OnElfCallback() { // from class: com.ds.daisi.AppContext.9
            @Override // com.cyjh.p019mq.sdk.inf.OnElfCallback
            public void callback(int i, String str3) {
                try {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("code=");
                    sb.append(i);
                    sb.append("json=");
                    sb.append(str3);
                    sb.append("\n");
                    sb.append(AppUtils.timeStamp2Date(System.currentTimeMillis() / 1000, null));
                    sb.append("\n\n");
                    ThreadPoolProxyFactory.getNormalThreadPool().executeRunnable(new Runnable() { // from class: com.ds.daisi.AppContext.9.1
                        @Override // java.lang.Runnable
                        public void run() {
                            AppUtils.writeDateToFile("heartbeatCheckPay.txt", AppContext.getInstance(), sb.toString(), true);
                        }
                    });
                    if (i == 31) {
                        popDialog(AppContext.this.getString(R.string.check_heartbeat_failed));
                    } else if (i == 32) {
                        popDialog(new JSONObject(str3).getString(Constants.DATABASE_TABLE_MSG));
                    } else if (i == 33) {
                        popDialog(AppContext.this.getString(R.string.no_get_heartbeat_time));
                    } else if (i == 34) {
                        popDialog(new JSONObject(str3).getString(Constants.DATABASE_TABLE_MSG));
                    } else if (i == 35) {
                        popDialog(AppContext.this.getString(R.string.script_secret_key_error));
                    } else if (i == 36) {
                        SlLog.m1334i(AppContext.TAG, "initElf --> ENUM_SCRIPT_NEED_UPGRADE");
                        EventBus.getDefault().post(new MsgItem(1005));
                    } else if (i == 37) {
                        AppContext.this.STATE_SCRIPT = 4;
                    }
                } catch (Exception e) {
                    Log.e("zzz", "AppContext--initListener e:" + e.getMessage());
                    ThrowableExtension.printStackTrace(e);
                    ToastUtils.showToastLong(AppContext.getInstance(), e.getMessage());
                }
            }

            private void popDialog(String str3) {
                if (TipsDialog.sIsShow && !TipsDialog.sIsFloat) {
                    EventBus.getDefault().post(new MsgItem(1006));
                }
                if (!TipsDialog.sIsShow) {
                    final TipsDialog tipsDialog = new TipsDialog(AppContext.getInstance(), true, str3);
                    tipsDialog.setClickListener(new View.OnClickListener() { // from class: com.ds.daisi.AppContext.9.2
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            tipsDialog.dismiss();
                        }
                    });
                    tipsDialog.show();
                    return;
                }
                EventBus.getDefault().post(new MsgItem.UpdateMessageToDialog(str3));
            }
        });
    }

    private void ddyStaticsJudgeOperate() {
        SlLog.m1334i(TAG, "ddyStaticsJudgeOperate --> 1");
        boolean zIsDdy = DdyEnvironmentHelper.get().isDdy();
        SlLog.m1334i(TAG, "ddyStaticsJudgeOperate --> 2 isDdyEnvironment=" + zIsDdy);
        if (zIsDdy) {
            DdyStatisticsHelper.get().init(this, new DdyStatisticsHelper.DdyDailyStatisticsListener() { // from class: com.ds.daisi.AppContext.10
                @Override // com.cyjh.share.helper.DdyStatisticsHelper.DdyDailyStatisticsListener
                public void msgDdyDailyStatistics(Message message) {
                    SlLog.m1334i(AppContext.TAG, "ddyStaticsJudgeOperate --> 3");
                    AppContext.this.mHandler.sendMessage(message);
                }
            });
            DdyStatisticsHelper.get().startTimer();
        }
    }

    @Override // android.app.Application
    public void onTerminate() {
        super.onTerminate();
        DdyStatisticsHelper.get().stopTimer();
    }
}
